// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_transaction.proto

package sawtooth.sdk.protobuf;

public interface ClientTransactionGetResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ClientTransactionGetResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ClientTransactionGetResponse.Status status = 1;</code>
   */
  int getStatusValue();
  /**
   * <code>.ClientTransactionGetResponse.Status status = 1;</code>
   */
  sawtooth.sdk.protobuf.ClientTransactionGetResponse.Status getStatus();

  /**
   * <code>.Transaction transaction = 2;</code>
   */
  boolean hasTransaction();
  /**
   * <code>.Transaction transaction = 2;</code>
   */
  sawtooth.sdk.protobuf.Transaction getTransaction();
  /**
   * <code>.Transaction transaction = 2;</code>
   */
  sawtooth.sdk.protobuf.TransactionOrBuilder getTransactionOrBuilder();
}
