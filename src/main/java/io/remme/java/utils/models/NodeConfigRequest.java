package io.remme.java.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model that define node config params into request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeConfigRequest {
    private String node_public_key;
    private String storage_public_key;
}
