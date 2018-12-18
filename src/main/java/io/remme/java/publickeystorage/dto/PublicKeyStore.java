package io.remme.java.publickeystorage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.IRemmeKeys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicKeyStore {
    private String data;
    private IRemmeKeys keys;
    private Date validFrom;
    private Date validTo;
    private RSASignaturePadding rsaSignaturePadding;
}
