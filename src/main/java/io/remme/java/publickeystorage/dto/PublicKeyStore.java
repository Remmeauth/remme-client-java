package io.remme.java.publickeystorage.dto;

import io.remme.java.keys.IRemmeKeys;
import io.remme.java.protobuf.PubKey;
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
    private Integer validFrom;
    private Integer validTo;
    private PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding;
}
