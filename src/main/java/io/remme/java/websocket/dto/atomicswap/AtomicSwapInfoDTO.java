package io.remme.java.websocket.dto.atomicswap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtomicSwapInfoDTO {
    private AtomicSwapState state;
    private String sender_address;
    private String sender_address_non_local;
    private String receiver_address;
    private Double amount;
    private String email_address_encrypted_optional;
    private String swap_id;
    private String secret_lock;
    private String secret_key;
    private Date created_at;
    private boolean is_initiator;
}
