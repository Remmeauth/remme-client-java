package io.remme.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration all currently available methods in our JSON-RPC.
 */
@Getter
@AllArgsConstructor
public enum RemmeMethod {

    PUBLIC_KEY("get_public_key_info"),
    TOKEN("get_balance"),
    BATCH_STATUS("get_batch_status"),
    ATOMIC_SWAP("get_atomic_swap_info"),
    ATOMIC_SWAP_PUBLIC_KEY("get_atomic_swap_public_key"),
    USER_PUBLIC_KEYS("get_public_keys_list"),
    NODE_KEY("get_node_public_key"),
    NODE_PRIVATE_KEY("export_node_key"),
    TRANSACTION("send_raw_transaction"),
    NETWORK_STATUS("get_node_info"),
    BLOCK_INFO("get_blocks"),
    BLOCKS("list_blocks"),
    BLOCK_NUMBER("get_block_number"),
    FETCH_BLOCK("fetch_block"),
    BATCHES("list_batches"),
    FETCH_BATCH("fetch_batch"),
    TRANSACTIONS("list_transactions"),
    FETCH_TRANSACTION("fetch_transaction"),
    STATE("list_state"),
    FETCH_STATE("fetch_state"),
    PEERS("fetch_peers"),
    RECEIPTS("list_receipts");

    private String methodName;
}
