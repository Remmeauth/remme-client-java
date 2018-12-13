// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: batch.proto

package sawtooth.sdk.protobuf;

public interface BatchHeaderOrBuilder extends
    // @@protoc_insertion_point(interface_extends:BatchHeader)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Public key for the client that signed the BatchHeader
   * </pre>
   *
   * <code>string signer_public_key = 1;</code>
   */
  java.lang.String getSignerPublicKey();
  /**
   * <pre>
   * Public key for the client that signed the BatchHeader
   * </pre>
   *
   * <code>string signer_public_key = 1;</code>
   */
  com.google.protobuf.ByteString
      getSignerPublicKeyBytes();

  /**
   * <pre>
   * List of transaction.header_signatures that match the order of
   * transactions required for the batch
   * </pre>
   *
   * <code>repeated string transaction_ids = 2;</code>
   */
  java.util.List<java.lang.String>
      getTransactionIdsList();
  /**
   * <pre>
   * List of transaction.header_signatures that match the order of
   * transactions required for the batch
   * </pre>
   *
   * <code>repeated string transaction_ids = 2;</code>
   */
  int getTransactionIdsCount();
  /**
   * <pre>
   * List of transaction.header_signatures that match the order of
   * transactions required for the batch
   * </pre>
   *
   * <code>repeated string transaction_ids = 2;</code>
   */
  java.lang.String getTransactionIds(int index);
  /**
   * <pre>
   * List of transaction.header_signatures that match the order of
   * transactions required for the batch
   * </pre>
   *
   * <code>repeated string transaction_ids = 2;</code>
   */
  com.google.protobuf.ByteString
      getTransactionIdsBytes(int index);
}