// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_batch.proto

package sawtooth.sdk.protobuf;

public interface ClientBatchListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ClientBatchListResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ClientBatchListResponse.Status status = 1;</code>
   */
  int getStatusValue();
  /**
   * <code>.ClientBatchListResponse.Status status = 1;</code>
   */
  sawtooth.sdk.protobuf.ClientBatchListResponse.Status getStatus();

  /**
   * <code>repeated .Batch batches = 2;</code>
   */
  java.util.List<sawtooth.sdk.protobuf.Batch> 
      getBatchesList();
  /**
   * <code>repeated .Batch batches = 2;</code>
   */
  sawtooth.sdk.protobuf.Batch getBatches(int index);
  /**
   * <code>repeated .Batch batches = 2;</code>
   */
  int getBatchesCount();
  /**
   * <code>repeated .Batch batches = 2;</code>
   */
  java.util.List<? extends sawtooth.sdk.protobuf.BatchOrBuilder> 
      getBatchesOrBuilderList();
  /**
   * <code>repeated .Batch batches = 2;</code>
   */
  sawtooth.sdk.protobuf.BatchOrBuilder getBatchesOrBuilder(
      int index);

  /**
   * <code>string head_id = 3;</code>
   */
  java.lang.String getHeadId();
  /**
   * <code>string head_id = 3;</code>
   */
  com.google.protobuf.ByteString
      getHeadIdBytes();

  /**
   * <code>.ClientPagingResponse paging = 4;</code>
   */
  boolean hasPaging();
  /**
   * <code>.ClientPagingResponse paging = 4;</code>
   */
  sawtooth.sdk.protobuf.ClientPagingResponse getPaging();
  /**
   * <code>.ClientPagingResponse paging = 4;</code>
   */
  sawtooth.sdk.protobuf.ClientPagingResponseOrBuilder getPagingOrBuilder();
}
