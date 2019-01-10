package io.remme.java.atomicswap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SwapState {
    EMPTY("EMPTY"),
    OPENED("OPENED"),
    SECRET_LOCK_PROVIDED("SECRET_LOCK_PROVIDED"),
    APPROVED("APPROVED"),
    CLOSED("CLOSED"),
    EXPIRED("EXPIRED");

    private String state;
}