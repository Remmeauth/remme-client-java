package io.remme.java.atomicswap.dto;

import io.remme.java.error.RemmeValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwapInitDTO {
    private String receiverAddress;
    private String senderAddressNonLocal;
    private Long amount;
    private String swapId;
    private String secretLockBySolicitor;
    private String emailAddressEncryptedByInitiator;
    private Integer createdAt;

    public void setReceiverAddress(String receiverAddress) {
        if (receiverAddress != null && !receiverAddress.isEmpty() && receiverAddress.matches("^[0-9a-f]{70}$")) {
            this.receiverAddress = receiverAddress;
        } else{
            throw new RemmeValidationException("receiverAddress is not valid or not specified");
        }
    }

    public void setSecretLockBySolicitor(String secretLockBySolicitor) {
        if (secretLockBySolicitor != null && !secretLockBySolicitor.isEmpty()) {
            if (secretLockBySolicitor.matches("^[0-9a-f]{64}$")){
                this.secretLockBySolicitor = secretLockBySolicitor;
            } else{
                throw new RemmeValidationException("secretLockBySolicitor is not a valid");
            }
        }
    }

    public void setSwapId(String swapId) {
        if (swapId != null && !swapId.isEmpty() && swapId.matches("^[0-9a-f]{64}$")){
            this.swapId = swapId;
        } else{
            throw new RemmeValidationException("swapId is not a valid or not specified");
        }
    }

    public void setAmount(Long amount) {
        if (amount != null) {
            this.amount = amount;
        } else {
            throw new RemmeValidationException("amount is not specified");
        }
    }

    public void setSenderAddressNonLocal(String senderAddressNonLocal) {
        if (senderAddressNonLocal != null && !senderAddressNonLocal.isEmpty()) {
            this.senderAddressNonLocal = senderAddressNonLocal;
        } else {
            throw new RemmeValidationException("senderAddressNonLocal is not specified");
        }
    }

    public void setCreatedAt(Integer createdAt) {
        if (createdAt != null) {
            this.createdAt = createdAt;
        } else {
            throw new RemmeValidationException("createdAt is not specified");
        }
    }
}
