package io.remme.java.publickeystorage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Model that return to user which want to see information about public key (publicKeyStorage.getInfo)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicKeyInfo {
    @JsonProperty(value = "owner_public_key")
    private String ownerPublicKey;
    private String address;
    @JsonProperty(value = "is_revoked")
    private Boolean isRevoked;
    @JsonProperty(value = "is_valid")
    private Boolean isValid;
    private Date validFrom;
    private Date validTo;
    @JsonProperty(value = "entity_hash")
    private String entityHash;
    @JsonProperty(value = "entity_hash_signature")
    private String entityHashSignature;
    @JsonProperty(value = "public_key")
    private String publicKey;
    private String type;

    @JsonProperty(value = "valid_from")
    public void setValidFrom(long seconds) {
        this.validFrom = new Date(seconds * 1000);
    }

    @JsonProperty(value = "valid_to")
    public void setValidTo(long seconds) {
        this.validTo = new Date(seconds * 1000);
    }
}
