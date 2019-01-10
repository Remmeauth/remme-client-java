package io.remme.java.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certificate {
    X509Certificate cert;
    PrivateKey privateKey;

    public PublicKey getPublicKey() {
        return cert.getPublicKey();
    }

    public Date getNotAfter() {
        return cert.getNotAfter();
    }

    public Date getNotBefore() {
        return cert.getNotBefore();
    }
 }
