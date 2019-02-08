// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: processor.proto

package sawtooth.sdk.protobuf;

/**
 * <pre>
 * The request from the validator/executor of the transaction processor
 * to verify a transaction.
 * </pre>
 *
 * Protobuf type {@code TpProcessRequest}
 */
public  final class TpProcessRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TpProcessRequest)
    TpProcessRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TpProcessRequest.newBuilder() to construct.
  private TpProcessRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TpProcessRequest() {
    payload_ = com.google.protobuf.ByteString.EMPTY;
    signature_ = "";
    contextId_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TpProcessRequest(
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
          case 10: {
            sawtooth.sdk.protobuf.TransactionHeader.Builder subBuilder = null;
            if (header_ != null) {
              subBuilder = header_.toBuilder();
            }
            header_ = input.readMessage(sawtooth.sdk.protobuf.TransactionHeader.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(header_);
              header_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {

            payload_ = input.readBytes();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            signature_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            contextId_ = s;
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
    return sawtooth.sdk.protobuf.Processor.internal_static_TpProcessRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.Processor.internal_static_TpProcessRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.TpProcessRequest.class, sawtooth.sdk.protobuf.TpProcessRequest.Builder.class);
  }

  public static final int HEADER_FIELD_NUMBER = 1;
  private sawtooth.sdk.protobuf.TransactionHeader header_;
  /**
   * <pre>
   * The transaction header
   * </pre>
   *
   * <code>.TransactionHeader header = 1;</code>
   */
  public boolean hasHeader() {
    return header_ != null;
  }
  /**
   * <pre>
   * The transaction header
   * </pre>
   *
   * <code>.TransactionHeader header = 1;</code>
   */
  public sawtooth.sdk.protobuf.TransactionHeader getHeader() {
    return header_ == null ? sawtooth.sdk.protobuf.TransactionHeader.getDefaultInstance() : header_;
  }
  /**
   * <pre>
   * The transaction header
   * </pre>
   *
   * <code>.TransactionHeader header = 1;</code>
   */
  public sawtooth.sdk.protobuf.TransactionHeaderOrBuilder getHeaderOrBuilder() {
    return getHeader();
  }

  public static final int PAYLOAD_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString payload_;
  /**
   * <pre>
   * The transaction payload
   * </pre>
   *
   * <code>bytes payload = 2;</code>
   */
  public com.google.protobuf.ByteString getPayload() {
    return payload_;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 3;
  private volatile java.lang.Object signature_;
  /**
   * <pre>
   * The transaction header_signature
   * </pre>
   *
   * <code>string signature = 3;</code>
   */
  public java.lang.String getSignature() {
    java.lang.Object ref = signature_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      signature_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The transaction header_signature
   * </pre>
   *
   * <code>string signature = 3;</code>
   */
  public com.google.protobuf.ByteString
      getSignatureBytes() {
    java.lang.Object ref = signature_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      signature_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONTEXT_ID_FIELD_NUMBER = 4;
  private volatile java.lang.Object contextId_;
  /**
   * <pre>
   * The context_id for state requests.
   * </pre>
   *
   * <code>string context_id = 4;</code>
   */
  public java.lang.String getContextId() {
    java.lang.Object ref = contextId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      contextId_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The context_id for state requests.
   * </pre>
   *
   * <code>string context_id = 4;</code>
   */
  public com.google.protobuf.ByteString
      getContextIdBytes() {
    java.lang.Object ref = contextId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      contextId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (header_ != null) {
      output.writeMessage(1, getHeader());
    }
    if (!payload_.isEmpty()) {
      output.writeBytes(2, payload_);
    }
    if (!getSignatureBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, signature_);
    }
    if (!getContextIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, contextId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (header_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getHeader());
    }
    if (!payload_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, payload_);
    }
    if (!getSignatureBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, signature_);
    }
    if (!getContextIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, contextId_);
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
    if (!(obj instanceof sawtooth.sdk.protobuf.TpProcessRequest)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.TpProcessRequest other = (sawtooth.sdk.protobuf.TpProcessRequest) obj;

    boolean result = true;
    result = result && (hasHeader() == other.hasHeader());
    if (hasHeader()) {
      result = result && getHeader()
          .equals(other.getHeader());
    }
    result = result && getPayload()
        .equals(other.getPayload());
    result = result && getSignature()
        .equals(other.getSignature());
    result = result && getContextId()
        .equals(other.getContextId());
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
    if (hasHeader()) {
      hash = (37 * hash) + HEADER_FIELD_NUMBER;
      hash = (53 * hash) + getHeader().hashCode();
    }
    hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getPayload().hashCode();
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (37 * hash) + CONTEXT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getContextId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpProcessRequest parseFrom(
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
  public static Builder newBuilder(sawtooth.sdk.protobuf.TpProcessRequest prototype) {
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
   * The request from the validator/executor of the transaction processor
   * to verify a transaction.
   * </pre>
   *
   * Protobuf type {@code TpProcessRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TpProcessRequest)
      sawtooth.sdk.protobuf.TpProcessRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.Processor.internal_static_TpProcessRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.Processor.internal_static_TpProcessRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.TpProcessRequest.class, sawtooth.sdk.protobuf.TpProcessRequest.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.TpProcessRequest.newBuilder()
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
      if (headerBuilder_ == null) {
        header_ = null;
      } else {
        header_ = null;
        headerBuilder_ = null;
      }
      payload_ = com.google.protobuf.ByteString.EMPTY;

      signature_ = "";

      contextId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.Processor.internal_static_TpProcessRequest_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpProcessRequest getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.TpProcessRequest.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpProcessRequest build() {
      sawtooth.sdk.protobuf.TpProcessRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpProcessRequest buildPartial() {
      sawtooth.sdk.protobuf.TpProcessRequest result = new sawtooth.sdk.protobuf.TpProcessRequest(this);
      if (headerBuilder_ == null) {
        result.header_ = header_;
      } else {
        result.header_ = headerBuilder_.build();
      }
      result.payload_ = payload_;
      result.signature_ = signature_;
      result.contextId_ = contextId_;
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
      if (other instanceof sawtooth.sdk.protobuf.TpProcessRequest) {
        return mergeFrom((sawtooth.sdk.protobuf.TpProcessRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.TpProcessRequest other) {
      if (other == sawtooth.sdk.protobuf.TpProcessRequest.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
      }
      if (other.getPayload() != com.google.protobuf.ByteString.EMPTY) {
        setPayload(other.getPayload());
      }
      if (!other.getSignature().isEmpty()) {
        signature_ = other.signature_;
        onChanged();
      }
      if (!other.getContextId().isEmpty()) {
        contextId_ = other.contextId_;
        onChanged();
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
      sawtooth.sdk.protobuf.TpProcessRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.TpProcessRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private sawtooth.sdk.protobuf.TransactionHeader header_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        sawtooth.sdk.protobuf.TransactionHeader, sawtooth.sdk.protobuf.TransactionHeader.Builder, sawtooth.sdk.protobuf.TransactionHeaderOrBuilder> headerBuilder_;
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public boolean hasHeader() {
      return headerBuilder_ != null || header_ != null;
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public sawtooth.sdk.protobuf.TransactionHeader getHeader() {
      if (headerBuilder_ == null) {
        return header_ == null ? sawtooth.sdk.protobuf.TransactionHeader.getDefaultInstance() : header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public Builder setHeader(sawtooth.sdk.protobuf.TransactionHeader value) {
      if (headerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        header_ = value;
        onChanged();
      } else {
        headerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public Builder setHeader(
        sawtooth.sdk.protobuf.TransactionHeader.Builder builderForValue) {
      if (headerBuilder_ == null) {
        header_ = builderForValue.build();
        onChanged();
      } else {
        headerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public Builder mergeHeader(sawtooth.sdk.protobuf.TransactionHeader value) {
      if (headerBuilder_ == null) {
        if (header_ != null) {
          header_ =
            sawtooth.sdk.protobuf.TransactionHeader.newBuilder(header_).mergeFrom(value).buildPartial();
        } else {
          header_ = value;
        }
        onChanged();
      } else {
        headerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public Builder clearHeader() {
      if (headerBuilder_ == null) {
        header_ = null;
        onChanged();
      } else {
        header_ = null;
        headerBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public sawtooth.sdk.protobuf.TransactionHeader.Builder getHeaderBuilder() {
      
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    public sawtooth.sdk.protobuf.TransactionHeaderOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_ == null ?
            sawtooth.sdk.protobuf.TransactionHeader.getDefaultInstance() : header_;
      }
    }
    /**
     * <pre>
     * The transaction header
     * </pre>
     *
     * <code>.TransactionHeader header = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        sawtooth.sdk.protobuf.TransactionHeader, sawtooth.sdk.protobuf.TransactionHeader.Builder, sawtooth.sdk.protobuf.TransactionHeaderOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            sawtooth.sdk.protobuf.TransactionHeader, sawtooth.sdk.protobuf.TransactionHeader.Builder, sawtooth.sdk.protobuf.TransactionHeaderOrBuilder>(
                getHeader(),
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
    }

    private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * The transaction payload
     * </pre>
     *
     * <code>bytes payload = 2;</code>
     */
    public com.google.protobuf.ByteString getPayload() {
      return payload_;
    }
    /**
     * <pre>
     * The transaction payload
     * </pre>
     *
     * <code>bytes payload = 2;</code>
     */
    public Builder setPayload(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      payload_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The transaction payload
     * </pre>
     *
     * <code>bytes payload = 2;</code>
     */
    public Builder clearPayload() {
      
      payload_ = getDefaultInstance().getPayload();
      onChanged();
      return this;
    }

    private java.lang.Object signature_ = "";
    /**
     * <pre>
     * The transaction header_signature
     * </pre>
     *
     * <code>string signature = 3;</code>
     */
    public java.lang.String getSignature() {
      java.lang.Object ref = signature_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        signature_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The transaction header_signature
     * </pre>
     *
     * <code>string signature = 3;</code>
     */
    public com.google.protobuf.ByteString
        getSignatureBytes() {
      java.lang.Object ref = signature_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        signature_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The transaction header_signature
     * </pre>
     *
     * <code>string signature = 3;</code>
     */
    public Builder setSignature(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      signature_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The transaction header_signature
     * </pre>
     *
     * <code>string signature = 3;</code>
     */
    public Builder clearSignature() {
      
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The transaction header_signature
     * </pre>
     *
     * <code>string signature = 3;</code>
     */
    public Builder setSignatureBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      signature_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object contextId_ = "";
    /**
     * <pre>
     * The context_id for state requests.
     * </pre>
     *
     * <code>string context_id = 4;</code>
     */
    public java.lang.String getContextId() {
      java.lang.Object ref = contextId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        contextId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The context_id for state requests.
     * </pre>
     *
     * <code>string context_id = 4;</code>
     */
    public com.google.protobuf.ByteString
        getContextIdBytes() {
      java.lang.Object ref = contextId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        contextId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The context_id for state requests.
     * </pre>
     *
     * <code>string context_id = 4;</code>
     */
    public Builder setContextId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      contextId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The context_id for state requests.
     * </pre>
     *
     * <code>string context_id = 4;</code>
     */
    public Builder clearContextId() {
      
      contextId_ = getDefaultInstance().getContextId();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The context_id for state requests.
     * </pre>
     *
     * <code>string context_id = 4;</code>
     */
    public Builder setContextIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      contextId_ = value;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:TpProcessRequest)
  }

  // @@protoc_insertion_point(class_scope:TpProcessRequest)
  private static final sawtooth.sdk.protobuf.TpProcessRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.TpProcessRequest();
  }

  public static sawtooth.sdk.protobuf.TpProcessRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TpProcessRequest>
      PARSER = new com.google.protobuf.AbstractParser<TpProcessRequest>() {
    @java.lang.Override
    public TpProcessRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TpProcessRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TpProcessRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TpProcessRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.TpProcessRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

