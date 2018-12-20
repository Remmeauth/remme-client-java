package io.remme.java.certificate;

import io.remme.java.certificate.dto.CertificateTransactionResponse;
import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.certificate.dto.ICertificateTransactionResponse;
import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.keys.RSA;
import io.remme.java.keys.RemmeKeys;
import io.remme.java.keys.dto.GenerateOptions;
import io.remme.java.publickeystorage.IRemmePublicKeyStorage;
import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.publickeystorage.dto.PublicKeyStore;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;
import io.remme.java.utils.Functions;
import io.remme.java.utils.RemmeExecutorService;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Class for working with certificates, such as create, store, revoke, check, getInfo.
 */
public class RemmeCertificate implements IRemmeCertificate {

    private IRemmePublicKeyStorage remmePublicKeyStorage;
    private static final Integer RSA_KEY_SIZE = 2048;

    private X500Name createSubject(CreateCertificateDTO dto) {
        if (dto.getCommonName() == null || dto.getCommonName().isEmpty()) {
            throw new RemmeValidationException("Attribute commonName must have a value");
        }
        if (dto.getValidity() == null) {
            throw new RemmeValidationException("Attribute validity must have a value");
        }
        X500NameBuilder builder = new X500NameBuilder();
        if (dto.getCommonName() != null) {
            builder.addRDN(BCStyle.CN, dto.getCommonName());
        }
        if (dto.getEmail() != null) {
            builder.addRDN(BCStyle.E, dto.getEmail());
        }
        if (dto.getCountryName() != null) {
            builder.addRDN(BCStyle.C, dto.getCountryName());
        }
        if (dto.getLocalityName() != null) {
            builder.addRDN(BCStyle.L, dto.getLocalityName());
        }
        if (dto.getPostalAddress() != null) {
            builder.addRDN(BCStyle.POSTAL_ADDRESS, dto.getPostalAddress());
        }
        if (dto.getPostalCode() != null) {
            builder.addRDN(BCStyle.POSTAL_CODE, dto.getPostalCode());
        }
        if (dto.getStreetAddress() != null) {
            builder.addRDN(BCStyle.STREET, dto.getStreetAddress());
        }
        if (dto.getStateName() != null) {
            builder.addRDN(BCStyle.ST, dto.getStateName());
        }
        if (dto.getName() != null) {
            builder.addRDN(BCStyle.NAME, dto.getName());
        }
        if (dto.getSurname() != null) {
            builder.addRDN(BCStyle.SURNAME, dto.getSurname());
        }
        if (dto.getPseudonym() != null) {
            builder.addRDN(BCStyle.PSEUDONYM, dto.getPseudonym());
        }
        if (dto.getGenerationQualifier() != null) {
            builder.addRDN(BCStyle.GENERATION, dto.getGenerationQualifier());
        }
        if (dto.getTitle() != null) {
            builder.addRDN(BCStyle.T, dto.getTitle());
        }
        if (dto.getSerial() != null) {
            builder.addRDN(BCStyle.SERIALNUMBER, dto.getSerial());
        }
        if (dto.getBusinessCategory() != null) {
            builder.addRDN(BCStyle.BUSINESS_CATEGORY, dto.getBusinessCategory());
        }
        return builder.build();
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
     * @example Usage without remme main package
     * ```typescript
     * const rest = new RemmeRest();
     * const account = new RemmeAccount();
     * const transaction = new RemmeTransactionService(rest, account);
     * const publicKeyStorage = new RemmePublicKeyStorage(rest, account, transaction);
     * const certificate = new RemmeCertificate(publicKeyStorage);
     * ```
     */
    public RemmeCertificate(IRemmePublicKeyStorage remmePublicKeyStorage) {
        this.remmePublicKeyStorage = remmePublicKeyStorage;
    }

    /**
     * Create certificate.
     *
     * @param {CreateCertificateDto} certificateDataToCreate
     * @example ```typescript
     * const certificate = await remme.certificate.create({
     * commonName: "userName",
     * email: "user@email.com",
     * name: "John",
     * surname: "Smith",
     * countryName: "US",
     * validity: 360,
     * serial: `${Date.now()}`
     * });
     * console.log(certificate);
     * ```
     * @returns {Promise<module:node-forge.pki.Certificate>}
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
     * @param {CreateCertificateDto} certificateDataToCreate
     * @example ```typescript
     * const remme = new Remme.Client();
     * const certificateTransactionResult = await remme.certificate.createAndStore({
     * commonName: "userName",
     * email: "user@email.com",
     * name: "John",
     * surname: "Smith",
     * countryName: "US",
     * validity: 360,
     * serial: `${Date.now()}`
     * });
     * <p>
     * const certificateTransactionCallback = (err: Error, response: any) => {
     * if (err) {
     * console.log(err);
     * }
     * console.log("store", response);
     * }
     * ```
     * @returns {Promise<ICertificateTransactionResponse>}
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
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @example ```typescript
     * const certificate = await remme.certificate.create({
     * commonName: "userName",
     * email: "user@email.com",
     * name: "John",
     * surname: "Smith",
     * countryName: "US",
     * validity: 360,
     * serial: `${Date.now()}`
     * });
     * const storeResponse = await remme.certificate.store(certificate);
     * ```
     * @returns {Promise<ICertificateTransactionResponse>}
     */
    public Future<ICertificateTransactionResponse> store(Certificate certificate) {
        if (certificate.getPrivateKey() == null) {
            throw new RemmeValidationException("Your certificate does not have private key");
        }
        return RemmeExecutorService.getInstance().submit(() -> {
            String certificatePEM = Functions.certificateToPEM(certificate);
            int validFrom = (int) Math.floor(certificate.getNotBefore().getTime() / 1000d);
            int validTo = (int) Math.floor(certificate.getNotAfter().getTime() / 1000d);
            BaseTransactionResponse batchResponse = this.remmePublicKeyStorage.store(PublicKeyStore.builder()
                    .data(certificatePEM)
                    .keys(new RSA(certificate.getPublicKey(), certificate.getPrivateKey()))
                    .rsaSignaturePadding(RSASignaturePadding.PSS)
                    .validFrom(validFrom)
                    .validTo(validTo).build()).get();
            return new CertificateTransactionResponse(
                    batchResponse.getNodeAddress(),
                    batchResponse.isSslMode(),
                    batchResponse.getBatchId(),
                    certificate);
        });
    }

    /**
     * Check certificate's public key on validity and revocation.
     *
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @example ```typescript
     * const isValid = await remme.certificate.check(certificate);
     * console.log(isValid); // true or false
     * ```
     * @returns {Promise<boolean>}
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
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @example ```typescript
     * const isValid = await remme.certificate.getInfo(certificate);
     * console.log(info); // PublicKeyInfo
     * ```
     * @returns {Promise<PublicKeyInfo>}
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
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @example ```typescript
     * const revokeResponse = await remme.certificate.revoke(certificate);
     * revokeResponse.connectToWebSocket((err: Error, res: any) => {
     * if (err) {
     * console.log(err);
     * return;
     * }
     * console.log(res);
     * })
     * ```
     * @returns {Promise<IBaseTransactionResponse>}
     */
    public Future<BaseTransactionResponse> revoke(Certificate certificate) {
        String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, certificate.getPublicKey());
        return this.remmePublicKeyStorage.revoke(address);
    }

    /**
     * Sign data with a certificate's private key and output DigestInfo DER-encoded bytes
     * (defaults to PSS)
     *
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @param {string}                           data
     * @param {RSASignaturePadding}              rsaSignaturePadding
     * @returns {string}
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
     * @param {module:node-forge.pki.Certificate | module:node-forge.pki.PEM} certificate
     * @param {string}                           data
     * @param {string}                           signature
     * @param {RSASignaturePadding}              rsaSignaturePadding
     * @returns {boolean}
     */
    public boolean verify(Certificate certificate, String data, String signature, RSASignaturePadding rsaSignaturePadding) {
        RSA keys = new RSA(certificate.getPublicKey(), null);
        if (rsaSignaturePadding != null) {
            return keys.verify(data, signature, rsaSignaturePadding);
        } else {
            return keys.verify(data, signature);
        }
    }

}
