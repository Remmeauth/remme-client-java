package io.remme.java.enums;

import io.remme.java.protobuf.PubKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RSASignaturePadding {
    PSS(PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PSS),
    PKCS1v15(PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PKCS1v15),
    EMPTY(PubKey.NewPubKeyPayload.RSAConfiguration.Padding.UNRECOGNIZED);

    private PubKey.NewPubKeyPayload.RSAConfiguration.Padding protoValue;
}
