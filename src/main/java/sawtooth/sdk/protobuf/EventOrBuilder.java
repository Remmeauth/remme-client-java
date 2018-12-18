// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: events.proto

package sawtooth.sdk.protobuf;

public interface EventOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Event)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Used to subscribe to events and servers as a hint for how to deserialize
   * event_data and what pairs to expect in attributes.
   * </pre>
   *
   * <code>string event_type = 1;</code>
   */
  java.lang.String getEventType();
  /**
   * <pre>
   * Used to subscribe to events and servers as a hint for how to deserialize
   * event_data and what pairs to expect in attributes.
   * </pre>
   *
   * <code>string event_type = 1;</code>
   */
  com.google.protobuf.ByteString
      getEventTypeBytes();

  /**
   * <code>repeated .Event.Attribute attributes = 2;</code>
   */
  java.util.List<sawtooth.sdk.protobuf.Event.Attribute> 
      getAttributesList();
  /**
   * <code>repeated .Event.Attribute attributes = 2;</code>
   */
  sawtooth.sdk.protobuf.Event.Attribute getAttributes(int index);
  /**
   * <code>repeated .Event.Attribute attributes = 2;</code>
   */
  int getAttributesCount();
  /**
   * <code>repeated .Event.Attribute attributes = 2;</code>
   */
  java.util.List<? extends sawtooth.sdk.protobuf.Event.AttributeOrBuilder> 
      getAttributesOrBuilderList();
  /**
   * <code>repeated .Event.Attribute attributes = 2;</code>
   */
  sawtooth.sdk.protobuf.Event.AttributeOrBuilder getAttributesOrBuilder(
      int index);

  /**
   * <pre>
   * Opaque data defined by the event_type.
   * </pre>
   *
   * <code>bytes data = 3;</code>
   */
  com.google.protobuf.ByteString getData();
}
