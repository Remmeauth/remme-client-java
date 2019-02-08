// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_event.proto

package sawtooth.sdk.protobuf;

public final class ClientEvent {
  private ClientEvent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsSubscribeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsSubscribeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsSubscribeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsSubscribeResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsUnsubscribeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsUnsubscribeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsUnsubscribeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsUnsubscribeResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsGetRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsGetRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ClientEventsGetResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ClientEventsGetResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022client_event.proto\032\014events.proto\"g\n\034Cl" +
      "ientEventsSubscribeRequest\022)\n\rsubscripti" +
      "ons\030\001 \003(\0132\022.EventSubscription\022\034\n\024last_kn" +
      "own_block_ids\030\002 \003(\t\"\273\001\n\035ClientEventsSubs" +
      "cribeResponse\0225\n\006status\030\001 \001(\0162%.ClientEv" +
      "entsSubscribeResponse.Status\022\030\n\020response" +
      "_message\030\002 \001(\t\"I\n\006Status\022\020\n\014STATUS_UNSET" +
      "\020\000\022\006\n\002OK\020\001\022\022\n\016INVALID_FILTER\020\002\022\021\n\rUNKNOW" +
      "N_BLOCK\020\003\" \n\036ClientEventsUnsubscribeRequ" +
      "est\"\222\001\n\037ClientEventsUnsubscribeResponse\022" +
      "7\n\006status\030\001 \001(\0162\'.ClientEventsUnsubscrib" +
      "eResponse.Status\"6\n\006Status\022\020\n\014STATUS_UNS" +
      "ET\020\000\022\006\n\002OK\020\001\022\022\n\016INTERNAL_ERROR\020\002\"V\n\026Clie" +
      "ntEventsGetRequest\022)\n\rsubscriptions\030\001 \003(" +
      "\0132\022.EventSubscription\022\021\n\tblock_ids\030\002 \003(\t" +
      "\"\301\001\n\027ClientEventsGetResponse\022/\n\006status\030\001" +
      " \001(\0162\037.ClientEventsGetResponse.Status\022\026\n" +
      "\006events\030\002 \003(\0132\006.Event\"]\n\006Status\022\020\n\014STATU" +
      "S_UNSET\020\000\022\006\n\002OK\020\001\022\022\n\016INTERNAL_ERROR\020\002\022\022\n" +
      "\016INVALID_FILTER\020\003\022\021\n\rUNKNOWN_BLOCK\020\004B+\n\025" +
      "sawtooth.sdk.protobufP\001Z\020client_event_pb" +
      "2b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          sawtooth.sdk.protobuf.Events.getDescriptor(),
        }, assigner);
    internal_static_ClientEventsSubscribeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ClientEventsSubscribeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsSubscribeRequest_descriptor,
        new java.lang.String[] { "Subscriptions", "LastKnownBlockIds", });
    internal_static_ClientEventsSubscribeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ClientEventsSubscribeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsSubscribeResponse_descriptor,
        new java.lang.String[] { "Status", "ResponseMessage", });
    internal_static_ClientEventsUnsubscribeRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ClientEventsUnsubscribeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsUnsubscribeRequest_descriptor,
        new java.lang.String[] { });
    internal_static_ClientEventsUnsubscribeResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ClientEventsUnsubscribeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsUnsubscribeResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_ClientEventsGetRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ClientEventsGetRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsGetRequest_descriptor,
        new java.lang.String[] { "Subscriptions", "BlockIds", });
    internal_static_ClientEventsGetResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ClientEventsGetResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ClientEventsGetResponse_descriptor,
        new java.lang.String[] { "Status", "Events", });
    sawtooth.sdk.protobuf.Events.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
