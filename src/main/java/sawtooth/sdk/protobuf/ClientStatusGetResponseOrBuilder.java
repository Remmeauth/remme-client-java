// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_status.proto

package sawtooth.sdk.protobuf;

public interface ClientStatusGetResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ClientStatusGetResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ClientStatusGetResponse.Status status = 1;</code>
   */
  int getStatusValue();
  /**
   * <code>.ClientStatusGetResponse.Status status = 1;</code>
   */
  sawtooth.sdk.protobuf.ClientStatusGetResponse.Status getStatus();

  /**
   * <code>repeated .ClientStatusGetResponse.Peer peers = 2;</code>
   */
  java.util.List<sawtooth.sdk.protobuf.ClientStatusGetResponse.Peer> 
      getPeersList();
  /**
   * <code>repeated .ClientStatusGetResponse.Peer peers = 2;</code>
   */
  sawtooth.sdk.protobuf.ClientStatusGetResponse.Peer getPeers(int index);
  /**
   * <code>repeated .ClientStatusGetResponse.Peer peers = 2;</code>
   */
  int getPeersCount();
  /**
   * <code>repeated .ClientStatusGetResponse.Peer peers = 2;</code>
   */
  java.util.List<? extends sawtooth.sdk.protobuf.ClientStatusGetResponse.PeerOrBuilder> 
      getPeersOrBuilderList();
  /**
   * <code>repeated .ClientStatusGetResponse.Peer peers = 2;</code>
   */
  sawtooth.sdk.protobuf.ClientStatusGetResponse.PeerOrBuilder getPeersOrBuilder(
      int index);

  /**
   * <pre>
   * The validator's public network endpoint
   * </pre>
   *
   * <code>string endpoint = 3;</code>
   */
  java.lang.String getEndpoint();
  /**
   * <pre>
   * The validator's public network endpoint
   * </pre>
   *
   * <code>string endpoint = 3;</code>
   */
  com.google.protobuf.ByteString
      getEndpointBytes();
}
