// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_transaction.proto

package sawtooth.sdk.protobuf;

public interface ClientTransactionListRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ClientTransactionListRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string head_id = 1;</code>
   */
  java.lang.String getHeadId();
  /**
   * <code>string head_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getHeadIdBytes();

  /**
   * <code>repeated string transaction_ids = 2;</code>
   */
  java.util.List<java.lang.String>
      getTransactionIdsList();
  /**
   * <code>repeated string transaction_ids = 2;</code>
   */
  int getTransactionIdsCount();
  /**
   * <code>repeated string transaction_ids = 2;</code>
   */
  java.lang.String getTransactionIds(int index);
  /**
   * <code>repeated string transaction_ids = 2;</code>
   */
  com.google.protobuf.ByteString
      getTransactionIdsBytes(int index);

  /**
   * <code>.ClientPagingControls paging = 3;</code>
   */
  boolean hasPaging();
  /**
   * <code>.ClientPagingControls paging = 3;</code>
   */
  sawtooth.sdk.protobuf.ClientPagingControls getPaging();
  /**
   * <code>.ClientPagingControls paging = 3;</code>
   */
  sawtooth.sdk.protobuf.ClientPagingControlsOrBuilder getPagingOrBuilder();

  /**
   * <code>repeated .ClientSortControls sorting = 4;</code>
   */
  java.util.List<sawtooth.sdk.protobuf.ClientSortControls> 
      getSortingList();
  /**
   * <code>repeated .ClientSortControls sorting = 4;</code>
   */
  sawtooth.sdk.protobuf.ClientSortControls getSorting(int index);
  /**
   * <code>repeated .ClientSortControls sorting = 4;</code>
   */
  int getSortingCount();
  /**
   * <code>repeated .ClientSortControls sorting = 4;</code>
   */
  java.util.List<? extends sawtooth.sdk.protobuf.ClientSortControlsOrBuilder> 
      getSortingOrBuilderList();
  /**
   * <code>repeated .ClientSortControls sorting = 4;</code>
   */
  sawtooth.sdk.protobuf.ClientSortControlsOrBuilder getSortingOrBuilder(
      int index);
}