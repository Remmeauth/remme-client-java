// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: state_context.proto

package sawtooth.sdk.protobuf;

public interface TpStateDeleteResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TpStateDeleteResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string addresses = 1;</code>
   */
  java.util.List<java.lang.String>
      getAddressesList();
  /**
   * <code>repeated string addresses = 1;</code>
   */
  int getAddressesCount();
  /**
   * <code>repeated string addresses = 1;</code>
   */
  java.lang.String getAddresses(int index);
  /**
   * <code>repeated string addresses = 1;</code>
   */
  com.google.protobuf.ByteString
      getAddressesBytes(int index);

  /**
   * <code>.TpStateDeleteResponse.Status status = 2;</code>
   */
  int getStatusValue();
  /**
   * <code>.TpStateDeleteResponse.Status status = 2;</code>
   */
  sawtooth.sdk.protobuf.TpStateDeleteResponse.Status getStatus();
}
