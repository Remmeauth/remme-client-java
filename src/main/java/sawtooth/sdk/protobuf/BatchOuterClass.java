// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: batch.proto

package sawtooth.sdk.protobuf;

public final class BatchOuterClass {
  private BatchOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BatchHeader_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BatchHeader_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Batch_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Batch_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BatchList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BatchList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013batch.proto\032\021transaction.proto\"A\n\013Batc" +
      "hHeader\022\031\n\021signer_public_key\030\001 \001(\t\022\027\n\017tr" +
      "ansaction_ids\030\002 \003(\t\"d\n\005Batch\022\016\n\006header\030\001" +
      " \001(\014\022\030\n\020header_signature\030\002 \001(\t\022\"\n\014transa" +
      "ctions\030\003 \003(\0132\014.Transaction\022\r\n\005trace\030\004 \001(" +
      "\010\"$\n\tBatchList\022\027\n\007batches\030\001 \003(\0132\006.BatchB" +
      "$\n\025sawtooth.sdk.protobufP\001Z\tbatch_pb2b\006p" +
      "roto3"
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
          sawtooth.sdk.protobuf.TransactionOuterClass.getDescriptor(),
        }, assigner);
    internal_static_BatchHeader_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_BatchHeader_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BatchHeader_descriptor,
        new java.lang.String[] { "SignerPublicKey", "TransactionIds", });
    internal_static_Batch_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Batch_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Batch_descriptor,
        new java.lang.String[] { "Header", "HeaderSignature", "Transactions", "Trace", });
    internal_static_BatchList_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_BatchList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BatchList_descriptor,
        new java.lang.String[] { "Batches", });
    sawtooth.sdk.protobuf.TransactionOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
