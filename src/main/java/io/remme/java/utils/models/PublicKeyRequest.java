package io.remme.java.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model that define public key params into request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicKeyRequest {
    public String public_key_address;
}
