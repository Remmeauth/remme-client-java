// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: state_context.proto

package sawtooth.sdk.protobuf;

public interface TpReceiptAddDataRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TpReceiptAddDataRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The context id that references a context in the context manager
   * </pre>
   *
   * <code>string context_id = 1;</code>
   */
  java.lang.String getContextId();
  /**
   * <pre>
   * The context id that references a context in the context manager
   * </pre>
   *
   * <code>string context_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getContextIdBytes();

  /**
   * <code>bytes data = 3;</code>
   */
  com.google.protobuf.ByteString getData();
}
