package io.remme.java.publickeystorage.dto;

import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.IRemmeKeys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicKeyStore {
    private String data;
    private IRemmeKeys keys;
    private String signature;
    private Integer validFrom;
    private Integer validTo;
    private RSASignaturePadding rsaSignaturePadding;
}
