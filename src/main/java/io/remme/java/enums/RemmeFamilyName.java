package io.remme.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RemmeFamilyName {
    ACCOUNT("account"),
    PUBLIC_KEY("pub_key"),
    SWAP("AtomicSwap");

    private String name;
}
