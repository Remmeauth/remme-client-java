package io.remme.java.certificate.dto;

import io.remme.java.api.NetworkConfig;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.keys.RSA;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;
import lombok.Getter;

/**
 * Base class for response on certificate creation
 */
@Getter
public class CertificateTransactionResponse extends BaseTransactionResponse implements ICertificateTransactionResponse {
    private IRemmeKeys keys;
    private Certificate certificate;

    public CertificateTransactionResponse(String socketAddress, Boolean sslMode, String batchId, Certificate certificate) {
        this(new NetworkConfig(socketAddress, sslMode), batchId, certificate);
    }

    public CertificateTransactionResponse(NetworkConfig networkConfig, String batchId, Certificate certificate) {
        super(networkConfig, batchId);
        this.certificate = certificate;
        this.keys = new RSA(
                this.certificate.getPublicKey(),
                this.certificate.getPrivateKey()
        );
    }

    /**
     * Sign data with a certificate's private key and output DigestInfo DER-encoded bytes
     * (defaults to PSS)
     * @param data data string
     * @param rsaSignaturePadding {@link RSASignaturePadding}
     * @return HEX string of signature
     */
    public String sign(String data, RSASignaturePadding rsaSignaturePadding) {
        return this.keys.sign(data, rsaSignaturePadding);
    }

    /**
     * Sign data with a certificate's private key and output DigestInfo DER-encoded bytes
     * (defaults to PSS)
     * @param data data string
     * @return HEX string of signature
     */
    public String sign(String data) {
        return this.keys.sign(data, RSASignaturePadding.PSS);
    }

    /**
     * Verify data with a public key
     * (defaults to PSS)
     * @param signature HEX string of signature
     * @param data string data that was signed
     * @param rsaSignaturePadding {@link RSASignaturePadding}
     * @return boolean result
     */
    public boolean verify(String data, String signature, RSASignaturePadding rsaSignaturePadding) {
        return this.keys.verify(data, signature, rsaSignaturePadding);
    }

    /**
     * Verify data with a public key
     * (defaults to PSS)
     * @param signature HEX string of signature
     * @param data string data that was signed
     * @return boolean result
     */
    public boolean verify(String data, String signature) {
        return this.keys.verify(data, signature, RSASignaturePadding.PSS);
    }
}
