package io.remme.java.keys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Available options for keys generation process
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateOptions {
    private Integer rsaKeySize;
    private byte[] seed;
}
