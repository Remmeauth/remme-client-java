package io.remme.java.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum RemmeFamilyName {
    ACCOUNT("account", "112007"),
    PUBLIC_KEY("pub_key", "a23be1"),
    SWAP("AtomicSwap", "78173b");

    private String name;
    private String namespace;

    public static RemmeFamilyName getByNamespace(String namespace) {
        return Stream.of(RemmeFamilyName.values()).filter(val -> val.getNamespace().equalsIgnoreCase(namespace))
                .findFirst().orElse(null);
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static RemmeFamilyName getByName(String name) {
        return Stream.of(RemmeFamilyName.values()).filter(val -> val.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
