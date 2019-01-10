package io.remme.java.certificate.dto;

import io.remme.java.keys.IRemmeKeys;
import io.remme.java.keys.RSA;
import io.remme.java.protobuf.PubKey;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;

/**
 * Base class for response on certificate creation
 */
public class CertificateTransactionResponse extends BaseTransactionResponse implements ICertificateTransactionResponse {
    public IRemmeKeys keys;
    public Certificate certificate;

    public CertificateTransactionResponse(String socketAddress, Boolean sslMode, String batchId, Certificate certificate) {
        super(socketAddress, sslMode, batchId);
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
     * @param rsaSignaturePadding {@link io.remme.java.protobuf.PubKey.NewPubKeyPayload.RSAConfiguration.Padding}
     * @return HEX string of signature
     */
    public String sign(String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding) {
        return this.keys.sign(data, rsaSignaturePadding);
    }

    /**
     * Sign data with a certificate's private key and output DigestInfo DER-encoded bytes
     * (defaults to PSS)
     * @param data data string
     * @return HEX string of signature
     */
    public String sign(String data) {
        return this.keys.sign(data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PSS);
    }

    /**
     * Verify data with a public key
     * (defaults to PSS)
     * @param signature HEX string of signature
     * @param data string data that was signed
     * @param rsaSignaturePadding {@link io.remme.java.protobuf.PubKey.NewPubKeyPayload.RSAConfiguration.Padding}
     * @return boolean result
     */
    public boolean verify(String data, String signature, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding) {
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
        return this.keys.verify(data, signature, PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PSS);
    }
}
