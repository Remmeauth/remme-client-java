// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_block.proto

package sawtooth.sdk.protobuf;

/**
 * <pre>
 * A response that returns the block specified by a ClientBlockGetByIdRequest
 * or  ClientBlockGetByNumRequest.
 * Statuses:
 *   * OK - everything worked as expected
 *   * INTERNAL_ERROR - general error, such as protobuf failing to deserialize
 *   * NO_RESOURCE - no block with the specified id exists
 * </pre>
 *
 * Protobuf type {@code ClientBlockGetResponse}
 */
public  final class ClientBlockGetResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ClientBlockGetResponse)
    ClientBlockGetResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ClientBlockGetResponse.newBuilder() to construct.
  private ClientBlockGetResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ClientBlockGetResponse() {
    status_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ClientBlockGetResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            status_ = rawValue;
            break;
          }
          case 18: {
            sawtooth.sdk.protobuf.Block.Builder subBuilder = null;
            if (block_ != null) {
              subBuilder = block_.toBuilder();
            }
            block_ = input.readMessage(sawtooth.sdk.protobuf.Block.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(block_);
              block_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sawtooth.sdk.protobuf.ClientBlock.internal_static_ClientBlockGetResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.ClientBlock.internal_static_ClientBlockGetResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.ClientBlockGetResponse.class, sawtooth.sdk.protobuf.ClientBlockGetResponse.Builder.class);
  }

  /**
   * Protobuf enum {@code ClientBlockGetResponse.Status}
   */
  public enum Status
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>STATUS_UNSET = 0;</code>
     */
    STATUS_UNSET(0),
    /**
     * <code>OK = 1;</code>
     */
    OK(1),
    /**
     * <code>INTERNAL_ERROR = 2;</code>
     */
    INTERNAL_ERROR(2),
    /**
     * <code>NO_RESOURCE = 5;</code>
     */
    NO_RESOURCE(5),
    /**
     * <code>INVALID_ID = 8;</code>
     */
    INVALID_ID(8),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>STATUS_UNSET = 0;</code>
     */
    public static final int STATUS_UNSET_VALUE = 0;
    /**
     * <code>OK = 1;</code>
     */
    public static final int OK_VALUE = 1;
    /**
     * <code>INTERNAL_ERROR = 2;</code>
     */
    public static final int INTERNAL_ERROR_VALUE = 2;
    /**
     * <code>NO_RESOURCE = 5;</code>
     */
    public static final int NO_RESOURCE_VALUE = 5;
    /**
     * <code>INVALID_ID = 8;</code>
     */
    public static final int INVALID_ID_VALUE = 8;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Status valueOf(int value) {
      return forNumber(value);
    }

    public static Status forNumber(int value) {
      switch (value) {
        case 0: return STATUS_UNSET;
        case 1: return OK;
        case 2: return INTERNAL_ERROR;
        case 5: return NO_RESOURCE;
        case 8: return INVALID_ID;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Status>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Status> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Status>() {
            public Status findValueByNumber(int number) {
              return Status.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.ClientBlockGetResponse.getDescriptor().getEnumTypes().get(0);
    }

    private static final Status[] VALUES = values();

    public static Status valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Status(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ClientBlockGetResponse.Status)
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private int status_;
  /**
   * <code>.ClientBlockGetResponse.Status status = 1;</code>
   */
  public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.ClientBlockGetResponse.Status status = 1;</code>
   */
  public sawtooth.sdk.protobuf.ClientBlockGetResponse.Status getStatus() {
    @SuppressWarnings("deprecation")
    sawtooth.sdk.protobuf.ClientBlockGetResponse.Status result = sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.valueOf(status_);
    return result == null ? sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.UNRECOGNIZED : result;
  }

  public static final int BLOCK_FIELD_NUMBER = 2;
  private sawtooth.sdk.protobuf.Block block_;
  /**
   * <code>.Block block = 2;</code>
   */
  public boolean hasBlock() {
    return block_ != null;
  }
  /**
   * <code>.Block block = 2;</code>
   */
  public sawtooth.sdk.protobuf.Block getBlock() {
    return block_ == null ? sawtooth.sdk.protobuf.Block.getDefaultInstance() : block_;
  }
  /**
   * <code>.Block block = 2;</code>
   */
  public sawtooth.sdk.protobuf.BlockOrBuilder getBlockOrBuilder() {
    return getBlock();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (status_ != sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.STATUS_UNSET.getNumber()) {
      output.writeEnum(1, status_);
    }
    if (block_ != null) {
      output.writeMessage(2, getBlock());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.STATUS_UNSET.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, status_);
    }
    if (block_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getBlock());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sawtooth.sdk.protobuf.ClientBlockGetResponse)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.ClientBlockGetResponse other = (sawtooth.sdk.protobuf.ClientBlockGetResponse) obj;

    boolean result = true;
    result = result && status_ == other.status_;
    result = result && (hasBlock() == other.hasBlock());
    if (hasBlock()) {
      result = result && getBlock()
          .equals(other.getBlock());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    if (hasBlock()) {
      hash = (37 * hash) + BLOCK_FIELD_NUMBER;
      hash = (53 * hash) + getBlock().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientBlockGetResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sawtooth.sdk.protobuf.ClientBlockGetResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * A response that returns the block specified by a ClientBlockGetByIdRequest
   * or  ClientBlockGetByNumRequest.
   * Statuses:
   *   * OK - everything worked as expected
   *   * INTERNAL_ERROR - general error, such as protobuf failing to deserialize
   *   * NO_RESOURCE - no block with the specified id exists
   * </pre>
   *
   * Protobuf type {@code ClientBlockGetResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ClientBlockGetResponse)
      sawtooth.sdk.protobuf.ClientBlockGetResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.ClientBlock.internal_static_ClientBlockGetResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.ClientBlock.internal_static_ClientBlockGetResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.ClientBlockGetResponse.class, sawtooth.sdk.protobuf.ClientBlockGetResponse.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.ClientBlockGetResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (blockBuilder_ == null) {
        block_ = null;
      } else {
        block_ = null;
        blockBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.ClientBlock.internal_static_ClientBlockGetResponse_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientBlockGetResponse getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.ClientBlockGetResponse.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientBlockGetResponse build() {
      sawtooth.sdk.protobuf.ClientBlockGetResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientBlockGetResponse buildPartial() {
      sawtooth.sdk.protobuf.ClientBlockGetResponse result = new sawtooth.sdk.protobuf.ClientBlockGetResponse(this);
      result.status_ = status_;
      if (blockBuilder_ == null) {
        result.block_ = block_;
      } else {
        result.block_ = blockBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sawtooth.sdk.protobuf.ClientBlockGetResponse) {
        return mergeFrom((sawtooth.sdk.protobuf.ClientBlockGetResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.ClientBlockGetResponse other) {
      if (other == sawtooth.sdk.protobuf.ClientBlockGetResponse.getDefaultInstance()) return this;
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (other.hasBlock()) {
        mergeBlock(other.getBlock());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      sawtooth.sdk.protobuf.ClientBlockGetResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.ClientBlockGetResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.ClientBlockGetResponse.Status status = 1;</code>
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.ClientBlockGetResponse.Status status = 1;</code>
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.ClientBlockGetResponse.Status status = 1;</code>
     */
    public sawtooth.sdk.protobuf.ClientBlockGetResponse.Status getStatus() {
      @SuppressWarnings("deprecation")
      sawtooth.sdk.protobuf.ClientBlockGetResponse.Status result = sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.valueOf(status_);
      return result == null ? sawtooth.sdk.protobuf.ClientBlockGetResponse.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>.ClientBlockGetResponse.Status status = 1;</code>
     */
    public Builder setStatus(sawtooth.sdk.protobuf.ClientBlockGetResponse.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.ClientBlockGetResponse.Status status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private sawtooth.sdk.protobuf.Block block_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        sawtooth.sdk.protobuf.Block, sawtooth.sdk.protobuf.Block.Builder, sawtooth.sdk.protobuf.BlockOrBuilder> blockBuilder_;
    /**
     * <code>.Block block = 2;</code>
     */
    public boolean hasBlock() {
      return blockBuilder_ != null || block_ != null;
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public sawtooth.sdk.protobuf.Block getBlock() {
      if (blockBuilder_ == null) {
        return block_ == null ? sawtooth.sdk.protobuf.Block.getDefaultInstance() : block_;
      } else {
        return blockBuilder_.getMessage();
      }
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public Builder setBlock(sawtooth.sdk.protobuf.Block value) {
      if (blockBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        block_ = value;
        onChanged();
      } else {
        blockBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public Builder setBlock(
        sawtooth.sdk.protobuf.Block.Builder builderForValue) {
      if (blockBuilder_ == null) {
        block_ = builderForValue.build();
        onChanged();
      } else {
        blockBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public Builder mergeBlock(sawtooth.sdk.protobuf.Block value) {
      if (blockBuilder_ == null) {
        if (block_ != null) {
          block_ =
            sawtooth.sdk.protobuf.Block.newBuilder(block_).mergeFrom(value).buildPartial();
        } else {
          block_ = value;
        }
        onChanged();
      } else {
        blockBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public Builder clearBlock() {
      if (blockBuilder_ == null) {
        block_ = null;
        onChanged();
      } else {
        block_ = null;
        blockBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public sawtooth.sdk.protobuf.Block.Builder getBlockBuilder() {
      
      onChanged();
      return getBlockFieldBuilder().getBuilder();
    }
    /**
     * <code>.Block block = 2;</code>
     */
    public sawtooth.sdk.protobuf.BlockOrBuilder getBlockOrBuilder() {
      if (blockBuilder_ != null) {
        return blockBuilder_.getMessageOrBuilder();
      } else {
        return block_ == null ?
            sawtooth.sdk.protobuf.Block.getDefaultInstance() : block_;
      }
    }
    /**
     * <code>.Block block = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        sawtooth.sdk.protobuf.Block, sawtooth.sdk.protobuf.Block.Builder, sawtooth.sdk.protobuf.BlockOrBuilder> 
        getBlockFieldBuilder() {
      if (blockBuilder_ == null) {
        blockBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            sawtooth.sdk.protobuf.Block, sawtooth.sdk.protobuf.Block.Builder, sawtooth.sdk.protobuf.BlockOrBuilder>(
                getBlock(),
                getParentForChildren(),
                isClean());
        block_ = null;
      }
      return blockBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ClientBlockGetResponse)
  }

  // @@protoc_insertion_point(class_scope:ClientBlockGetResponse)
  private static final sawtooth.sdk.protobuf.ClientBlockGetResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.ClientBlockGetResponse();
  }

  public static sawtooth.sdk.protobuf.ClientBlockGetResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ClientBlockGetResponse>
      PARSER = new com.google.protobuf.AbstractParser<ClientBlockGetResponse>() {
    @java.lang.Override
    public ClientBlockGetResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ClientBlockGetResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ClientBlockGetResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ClientBlockGetResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.ClientBlockGetResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

