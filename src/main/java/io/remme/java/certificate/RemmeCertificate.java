package io.remme.java.certificate;

import io.remme.java.certificate.dto.CertificateTransactionResponse;
import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.certificate.dto.ICertificateTransactionResponse;
import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.SubjectField;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.keys.RSA;
import io.remme.java.keys.RemmeKeys;
import io.remme.java.keys.dto.GenerateOptions;
import io.remme.java.publickeystorage.IRemmePublicKeyStorage;
import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.publickeystorage.dto.PublicKeyCreate;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;
import io.remme.java.utils.Functions;
import io.remme.java.utils.RemmeExecutorService;
import org.apache.commons.beanutils.PropertyUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.concurrent.Future;

/**
 * Class for working with certificates, such as create, store, revoke, check, getInfo.
 */
public class RemmeCertificate implements IRemmeCertificate {

    private IRemmePublicKeyStorage remmePublicKeyStorage;
    private static final Integer RSA_KEY_SIZE = 2048;

    private X500Name createSubject(CreateCertificateDTO dto) {
        try {
            if (dto.getCommonName() == null || dto.getCommonName().isEmpty()) {
                throw new RemmeValidationException("Attribute commonName must have a value");
            }
            if (dto.getValidity() == null) {
                throw new RemmeValidationException("Attribute validity must have a value");
            }
            X500NameBuilder builder = new X500NameBuilder();
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                SubjectField subjectField = SubjectField.getByFieldName(field.getName());
                Object value = PropertyUtils.getProperty(dto, field.getName());
                if (subjectField != null && value != null) {
                    builder.addRDN(subjectField.getRdn(), (String) value);
                }
            }
            return builder.build();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
        }
    }

    private Certificate createCertificate(KeyPair keyPair, CreateCertificateDTO certificateDataToCreate) {
        try {
            X500Name subject = this.createSubject(certificateDataToCreate);
            Calendar notBefore = Calendar.getInstance();
            Calendar notAfter = Calendar.getInstance();
            if (certificateDataToCreate.getValidAfter() != null) {
                notBefore.add(Calendar.HOUR, 24 * certificateDataToCreate.getValidAfter());
            }
            notAfter.setTime(notBefore.getTime());
            notAfter.add(Calendar.HOUR, certificateDataToCreate.getValidity());
            // GENERATE THE X509 CERTIFICATE
            X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
                    subject, BigInteger.valueOf(System.currentTimeMillis()), notBefore.getTime(), notAfter.getTime(), subject, keyPair.getPublic());
            ContentSigner sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption")
                    .setProvider(BouncyCastleProvider.PROVIDER_NAME).build(keyPair.getPrivate());
            X509Certificate cert = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
                    .getCertificate(builder.build(sigGen));
            return Certificate.builder().cert(cert).privateKey(keyPair.getPrivate()).build();
        } catch (OperatorCreationException | CertificateException e) {
            throw new RemmeKeyException(e);
        }
    }

    /**
     * @param remmePublicKeyStorage {@link io.remme.java.publickeystorage.RemmePublicKeyStorage}
     *                              <pre>
     *                              RemmeApi remmeApi = new RemmeApi
     *                              RemmeAccount account = new RemmeAccount();
     *                              RemmeTransactionService transaction = new RemmeTransactionService(remmeApi, account);
     *                              RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(remmeApi, account, transaction);
     *                              RemmeCertificate remmeCertificate = new RemmeCertificate(publicKeyStorage);
     *                              </pre>
     */
    public RemmeCertificate(IRemmePublicKeyStorage remmePublicKeyStorage) {
        this.remmePublicKeyStorage = remmePublicKeyStorage;
    }

    /**
     * Create certificate.
     *
     * @param certificateDataToCreate {@link CreateCertificateDTO}
     *                                <pre>
     *                                Certificate certificate = remmeCertificate.create(CreateCertificateDTO.builder()
     *                                                .email("test@email.com")
     *                                                .commonName("testCert")
     *                                                .name("test")
     *                                                .title("test")
     *                                                .stateName("CA")
     *                                                .countryName("USA")
     *                                                .validity(365)
     *                                                .validAfter(0)
     *                                                .build()).get();
     *                                System.out.println(certificate.getNotBefore());
     *                                </pre>
     * @return {@link Certificate}
     */
    public Future<Certificate> create(CreateCertificateDTO certificateDataToCreate) {
        return RemmeExecutorService.getInstance().submit(() -> {
            KeyPair keys = RSA.generateKeyPair(GenerateOptions.builder().rsaKeySize(RSA_KEY_SIZE).build());
            return this.createCertificate(keys, certificateDataToCreate);
        });
    }

    /**
     * Method that creates certificate and stores it in to REMChain.
     * Send transaction to chain.
     *
     * @param certificateDataToCreate {@link CreateCertificateDTO}
     *                                <pre>
     *                                CertificateTransactionResponse certificateTransactionResult = remmeCertificate.createAndStore(CreateCertificateDTO.builder()
     *                                                .email("test@email.com")
     *                                                .commonName("testCert")
     *                                                .name("test")
     *                                                .title("test")
     *                                                .stateName("CA")
     *                                                .countryName("USA")
     *                                                .validity(365)
     *                                                .validAfter(0)
     *                                                .build()).get();
     *
     *                                SocketEventListener certificateTransactionCallback = (err, response) {@code ->} {
     *                                try {
     *                                                if (error != null) {
     *                                                    System.out.println(MAPPER.writeValueAsString(error));
     *                                                    return;
     *                                                }
     *                                                System.out.println(MAPPER.writeValueAsString(result));
     *                                            } catch (JsonProcessingException e) {
     *                                                e.printStackTrace();
     *                                            }
     *                                }
     *                                </pre>
     * @return {@link CertificateTransactionResponse}
     */
    public Future<ICertificateTransactionResponse> createAndStore(CreateCertificateDTO certificateDataToCreate) {
        return RemmeExecutorService.getInstance().submit(() -> {
            Certificate certificate = this.create(certificateDataToCreate).get();
            return store(certificate).get();
        });
    }

    /**
     * Store your certificate public key and hash of certificate into REMChain.
     * Your certificate should contains private and public keys.
     * Send transaction to chain.
     *
     * @param certificate {@link Certificate}
     *                    <pre>
     *                    Certificate certificate = remmeCertificate.create(CreateCertificateDTO.builder()
     *                                    .email("test@email.com")
     *                                    .commonName("testCert")
     *                                    .name("test")
     *                                    .title("test")
     *                                    .stateName("CA")
     *                                    .countryName("USA")
     *                                    .validity(365)
     *                                    .validAfter(0)
     *                                    .build()).get();
     *                    CertificateTransactionResponse response = remmeCertificate.store(certificate);
     *                    </pre>
     * @return {@link CertificateTransactionResponse}
     */
    public Future<ICertificateTransactionResponse> store(Certificate certificate) {
        if (certificate.getPrivateKey() == null) {
            throw new RemmeValidationException("Your certificate does not have private key");
        }
        return RemmeExecutorService.getInstance().submit(() -> {
            String certificatePEM = Functions.certificateToPEM(certificate, false);
            int validFrom = (int) Math.floor(certificate.getNotBefore().getTime() / 1000d);
            int validTo = (int) Math.floor(certificate.getNotAfter().getTime() / 1000d);
            BaseTransactionResponse batchResponse = this.remmePublicKeyStorage.createAndStore(PublicKeyCreate.builder()
                    .data(certificatePEM)
                    .keys(new RSA(certificate.getPublicKey(), certificate.getPrivateKey()))
                    .rsaSignaturePadding(RSASignaturePadding.PSS)
                    .validFrom(validFrom)
                    .validTo(validTo).build()).get();
            return new CertificateTransactionResponse(
                    batchResponse.getNetworkConfig(),
                    batchResponse.getBatchId(),
                    certificate);
        });
    }

    /**
     * Check certificate's public key on validity and revocation.
     *
     * @param certificate {@link Certificate}
     *                    <pre>
     *                    boolean isValid = remmeCertificate.check(certificate).get();
     *                    System.out.println(isValid); // true or false
     *                    </pre>
     * @return {@code true} if valid or {@code false} if not
     */
    public Future<Boolean> check(Certificate certificate) {
        return RemmeExecutorService.getInstance().submit(() -> {
            try {
                String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, certificate.getPublicKey());
                return remmePublicKeyStorage.check(address).get();
            } catch (Exception e) {
                throw new RemmeValidationException("This certificate was not found");
            }
        });
    }

    /**
     * Get info about certificate's public key.
     *
     * @param certificate {@link Certificate}
     *                    <pre>
     *                    PublicKeyInfo info = remmeCertificate.getInfo(certificate).get();
     *                    </pre>
     * @return {@link PublicKeyInfo}
     */
    public Future<PublicKeyInfo> getInfo(Certificate certificate) {
        return RemmeExecutorService.getInstance().submit(() -> {
            try {
                String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, certificate.getPublicKey());
                return this.remmePublicKeyStorage.getInfo(address).get();
            } catch (Exception e) {
                throw new RemmeValidationException("This certificate was not found");
            }
        });
    }

    /**
     * Revoke certificate's public key into REMChain.
     * Send transaction to chain.
     *
     * @param certificate {@link Certificate}
     *                    <pre>
     *                    BaseTransactionResponse revokeResponse = remmeCertificate.revoke(certificate).get();
     *                    revokeResponse.connectToWebSocket((err, res) {@code ->} {
     *                    try {
     *                                    if (error != null) {
     *                                        System.out.println(MAPPER.writeValueAsString(error));
     *                                        return;
     *                                    }
     *                                    System.out.println(MAPPER.writeValueAsString(result));
     *                                } catch (JsonProcessingException e) {
     *                                    e.printStackTrace();
     *                                }
     *                    });
     *                    </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> revoke(Certificate certificate) {
        String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, certificate.getPublicKey());
        return this.remmePublicKeyStorage.revoke(address);
    }

    /**
     * Sign data with a certificate's private key and output DigestInfo DER-encoded bytes
     * (defaults to PSS)
     *
     * @param certificate {@link Certificate}
     * @param data        data string
     * @param padding     padding (optional)
     * @return HEX string signature
     */
    public String sign(Certificate certificate, String data, RSASignaturePadding padding) {
        if (certificate.getPrivateKey() == null) {
            throw new RemmeValidationException("Your certificate does not have private key");
        }
        RSA keys = new RSA(null, certificate.getPrivateKey());
        if (padding == null) {
            return keys.sign(data);
        } else {
            return keys.sign(data, padding);
        }
    }

    /**
     * verify data with a public key
     * (defaults to PSS)
     *
     * @param certificate         {@link Certificate}
     * @param data                data string
     * @param signature           HEX signature string
     * @param rsaSignaturePadding padding (optional)
     * @return {@code true} if valid or {@code false} if not
     */
    public boolean verify(Certificate certificate, String data, String signature, RSASignaturePadding rsaSignaturePadding) {
        RSA keys = new RSA(certificate.getPublicKey(), null);
        if (rsaSignaturePadding != null) {
            return keys.verify(data, signature, rsaSignaturePadding);
        } else {
            return keys.verify(data, signature);
        }
    }

    /**
     * Same as {@link #store(Certificate)} but accepts PEM string
     *
     * @param certificatePem certificate PEM string
     * @return {@link CertificateTransactionResponse}
     */
    @Override
    public Future<ICertificateTransactionResponse> store(String certificatePem) {
        return store(Functions.certificateFromPEM(certificatePem));
    }

    /**
     * Same as {@link #check(Certificate)} but accepts PEM string
     *
     * @param certificatePem certificate PEM string
     * @return {@code true} if valid or {@code false} if not
     */
    @Override
    public Future<Boolean> check(String certificatePem) {
        return check(Functions.certificateFromPEM(certificatePem));
    }

    /**
     * Same as {@link #getInfo(Certificate)} but accepts PEM string
     *
     * @param certificatePem certificate PEM string
     * @return {@link PublicKeyInfo}
     */
    @Override
    public Future<PublicKeyInfo> getInfo(String certificatePem) {
        return getInfo(Functions.certificateFromPEM(certificatePem));
    }

    /**
     * Same as {@link #revoke(Certificate)} but accepts PEM string
     *
     * @param certificatePem certificate PEM string
     * @return {@link BaseTransactionResponse}
     */
    @Override
    public Future<BaseTransactionResponse> revoke(String certificatePem) {
        return revoke(Functions.certificateFromPEM(certificatePem));
    }

    /**
     * Same as {@link #sign(Certificate, String, RSASignaturePadding)} but accepts PEM string
     *
     * @param certificatePem      certificate PEM string
     * @param data                data string
     * @param rsaSignaturePadding padding (optional)
     * @return HEX string signature
     */
    @Override
    public String sign(String certificatePem, String data, RSASignaturePadding rsaSignaturePadding) {
        return sign(Functions.certificateFromPEM(certificatePem), data, rsaSignaturePadding);
    }

    /**
     * Same as {@link #verify(Certificate, String, String, RSASignaturePadding)} but accepts PEM string
     *
     * @param certificatePem      certificate PEM string
     * @param data                data string
     * @param signatureData       HEX signature string
     * @param rsaSignaturePadding padding (optional)
     * @return {@code true} if valid or {@code false} if not
     */
    @Override
    public boolean verify(String certificatePem, String data, String signatureData, RSASignaturePadding rsaSignaturePadding) {
        return verify(Functions.certificateFromPEM(certificatePem), data, signatureData, rsaSignaturePadding);
    }

}
