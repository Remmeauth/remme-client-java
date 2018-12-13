// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_state.proto

package sawtooth.sdk.protobuf;

public interface ClientStateListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ClientStateListResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ClientStateListResponse.Status status = 1;</code>
   */
  int getStatusValue();
  /**
   * <code>.ClientStateListResponse.Status status = 1;</code>
   */
  sawtooth.sdk.protobuf.ClientStateListResponse.Status getStatus();

  /**
   * <code>repeated .ClientStateListResponse.Entry entries = 2;</code>
   */
  java.util.List<sawtooth.sdk.protobuf.ClientStateListResponse.Entry> 
      getEntriesList();
  /**
   * <code>repeated .ClientStateListResponse.Entry entries = 2;</code>
   */
  sawtooth.sdk.protobuf.ClientStateListResponse.Entry getEntries(int index);
  /**
   * <code>repeated .ClientStateListResponse.Entry entries = 2;</code>
   */
  int getEntriesCount();
  /**
   * <code>repeated .ClientStateListResponse.Entry entries = 2;</code>
   */
  java.util.List<? extends sawtooth.sdk.protobuf.ClientStateListResponse.EntryOrBuilder> 
      getEntriesOrBuilderList();
  /**
   * <code>repeated .ClientStateListResponse.Entry entries = 2;</code>
   */
  sawtooth.sdk.protobuf.ClientStateListResponse.EntryOrBuilder getEntriesOrBuilder(
      int index);

  /**
   * <code>string state_root = 3;</code>
   */
  java.lang.String getStateRoot();
  /**
   * <code>string state_root = 3;</code>
   */
  com.google.protobuf.ByteString
      getStateRootBytes();

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
