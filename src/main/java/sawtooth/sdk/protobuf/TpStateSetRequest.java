// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: state_context.proto

package sawtooth.sdk.protobuf;

/**
 * <pre>
 * A request from the handler/tp to put entries in the state of a context
 * </pre>
 *
 * Protobuf type {@code TpStateSetRequest}
 */
public  final class TpStateSetRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TpStateSetRequest)
    TpStateSetRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TpStateSetRequest.newBuilder() to construct.
  private TpStateSetRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TpStateSetRequest() {
    contextId_ = "";
    entries_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TpStateSetRequest(
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
            java.lang.String s = input.readStringRequireUtf8();

            contextId_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              entries_ = new java.util.ArrayList<sawtooth.sdk.protobuf.TpStateEntry>();
              mutable_bitField0_ |= 0x00000002;
            }
            entries_.add(
                input.readMessage(sawtooth.sdk.protobuf.TpStateEntry.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        entries_ = java.util.Collections.unmodifiableList(entries_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sawtooth.sdk.protobuf.StateContext.internal_static_TpStateSetRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.StateContext.internal_static_TpStateSetRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.TpStateSetRequest.class, sawtooth.sdk.protobuf.TpStateSetRequest.Builder.class);
  }

  private int bitField0_;
  public static final int CONTEXT_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object contextId_;
  /**
   * <code>string context_id = 1;</code>
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
   * <code>string context_id = 1;</code>
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

  public static final int ENTRIES_FIELD_NUMBER = 2;
  private java.util.List<sawtooth.sdk.protobuf.TpStateEntry> entries_;
  /**
   * <code>repeated .TpStateEntry entries = 2;</code>
   */
  public java.util.List<sawtooth.sdk.protobuf.TpStateEntry> getEntriesList() {
    return entries_;
  }
  /**
   * <code>repeated .TpStateEntry entries = 2;</code>
   */
  public java.util.List<? extends sawtooth.sdk.protobuf.TpStateEntryOrBuilder> 
      getEntriesOrBuilderList() {
    return entries_;
  }
  /**
   * <code>repeated .TpStateEntry entries = 2;</code>
   */
  public int getEntriesCount() {
    return entries_.size();
  }
  /**
   * <code>repeated .TpStateEntry entries = 2;</code>
   */
  public sawtooth.sdk.protobuf.TpStateEntry getEntries(int index) {
    return entries_.get(index);
  }
  /**
   * <code>repeated .TpStateEntry entries = 2;</code>
   */
  public sawtooth.sdk.protobuf.TpStateEntryOrBuilder getEntriesOrBuilder(
      int index) {
    return entries_.get(index);
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
    if (!getContextIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, contextId_);
    }
    for (int i = 0; i < entries_.size(); i++) {
      output.writeMessage(2, entries_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getContextIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, contextId_);
    }
    for (int i = 0; i < entries_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, entries_.get(i));
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
    if (!(obj instanceof sawtooth.sdk.protobuf.TpStateSetRequest)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.TpStateSetRequest other = (sawtooth.sdk.protobuf.TpStateSetRequest) obj;

    boolean result = true;
    result = result && getContextId()
        .equals(other.getContextId());
    result = result && getEntriesList()
        .equals(other.getEntriesList());
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
    hash = (37 * hash) + CONTEXT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getContextId().hashCode();
    if (getEntriesCount() > 0) {
      hash = (37 * hash) + ENTRIES_FIELD_NUMBER;
      hash = (53 * hash) + getEntriesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.TpStateSetRequest parseFrom(
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
  public static Builder newBuilder(sawtooth.sdk.protobuf.TpStateSetRequest prototype) {
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
   * A request from the handler/tp to put entries in the state of a context
   * </pre>
   *
   * Protobuf type {@code TpStateSetRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TpStateSetRequest)
      sawtooth.sdk.protobuf.TpStateSetRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.StateContext.internal_static_TpStateSetRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.StateContext.internal_static_TpStateSetRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.TpStateSetRequest.class, sawtooth.sdk.protobuf.TpStateSetRequest.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.TpStateSetRequest.newBuilder()
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
        getEntriesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      contextId_ = "";

      if (entriesBuilder_ == null) {
        entries_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        entriesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.StateContext.internal_static_TpStateSetRequest_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpStateSetRequest getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.TpStateSetRequest.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpStateSetRequest build() {
      sawtooth.sdk.protobuf.TpStateSetRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.TpStateSetRequest buildPartial() {
      sawtooth.sdk.protobuf.TpStateSetRequest result = new sawtooth.sdk.protobuf.TpStateSetRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.contextId_ = contextId_;
      if (entriesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          entries_ = java.util.Collections.unmodifiableList(entries_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.entries_ = entries_;
      } else {
        result.entries_ = entriesBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof sawtooth.sdk.protobuf.TpStateSetRequest) {
        return mergeFrom((sawtooth.sdk.protobuf.TpStateSetRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.TpStateSetRequest other) {
      if (other == sawtooth.sdk.protobuf.TpStateSetRequest.getDefaultInstance()) return this;
      if (!other.getContextId().isEmpty()) {
        contextId_ = other.contextId_;
        onChanged();
      }
      if (entriesBuilder_ == null) {
        if (!other.entries_.isEmpty()) {
          if (entries_.isEmpty()) {
            entries_ = other.entries_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureEntriesIsMutable();
            entries_.addAll(other.entries_);
          }
          onChanged();
        }
      } else {
        if (!other.entries_.isEmpty()) {
          if (entriesBuilder_.isEmpty()) {
            entriesBuilder_.dispose();
            entriesBuilder_ = null;
            entries_ = other.entries_;
            bitField0_ = (bitField0_ & ~0x00000002);
            entriesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEntriesFieldBuilder() : null;
          } else {
            entriesBuilder_.addAllMessages(other.entries_);
          }
        }
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
      sawtooth.sdk.protobuf.TpStateSetRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.TpStateSetRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object contextId_ = "";
    /**
     * <code>string context_id = 1;</code>
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
     * <code>string context_id = 1;</code>
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
     * <code>string context_id = 1;</code>
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
     * <code>string context_id = 1;</code>
     */
    public Builder clearContextId() {
      
      contextId_ = getDefaultInstance().getContextId();
      onChanged();
      return this;
    }
    /**
     * <code>string context_id = 1;</code>
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

    private java.util.List<sawtooth.sdk.protobuf.TpStateEntry> entries_ =
      java.util.Collections.emptyList();
    private void ensureEntriesIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        entries_ = new java.util.ArrayList<sawtooth.sdk.protobuf.TpStateEntry>(entries_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        sawtooth.sdk.protobuf.TpStateEntry, sawtooth.sdk.protobuf.TpStateEntry.Builder, sawtooth.sdk.protobuf.TpStateEntryOrBuilder> entriesBuilder_;

    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public java.util.List<sawtooth.sdk.protobuf.TpStateEntry> getEntriesList() {
      if (entriesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(entries_);
      } else {
        return entriesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public int getEntriesCount() {
      if (entriesBuilder_ == null) {
        return entries_.size();
      } else {
        return entriesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public sawtooth.sdk.protobuf.TpStateEntry getEntries(int index) {
      if (entriesBuilder_ == null) {
        return entries_.get(index);
      } else {
        return entriesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder setEntries(
        int index, sawtooth.sdk.protobuf.TpStateEntry value) {
      if (entriesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntriesIsMutable();
        entries_.set(index, value);
        onChanged();
      } else {
        entriesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder setEntries(
        int index, sawtooth.sdk.protobuf.TpStateEntry.Builder builderForValue) {
      if (entriesBuilder_ == null) {
        ensureEntriesIsMutable();
        entries_.set(index, builderForValue.build());
        onChanged();
      } else {
        entriesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder addEntries(sawtooth.sdk.protobuf.TpStateEntry value) {
      if (entriesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntriesIsMutable();
        entries_.add(value);
        onChanged();
      } else {
        entriesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder addEntries(
        int index, sawtooth.sdk.protobuf.TpStateEntry value) {
      if (entriesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntriesIsMutable();
        entries_.add(index, value);
        onChanged();
      } else {
        entriesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder addEntries(
        sawtooth.sdk.protobuf.TpStateEntry.Builder builderForValue) {
      if (entriesBuilder_ == null) {
        ensureEntriesIsMutable();
        entries_.add(builderForValue.build());
        onChanged();
      } else {
        entriesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder addEntries(
        int index, sawtooth.sdk.protobuf.TpStateEntry.Builder builderForValue) {
      if (entriesBuilder_ == null) {
        ensureEntriesIsMutable();
        entries_.add(index, builderForValue.build());
        onChanged();
      } else {
        entriesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder addAllEntries(
        java.lang.Iterable<? extends sawtooth.sdk.protobuf.TpStateEntry> values) {
      if (entriesBuilder_ == null) {
        ensureEntriesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, entries_);
        onChanged();
      } else {
        entriesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder clearEntries() {
      if (entriesBuilder_ == null) {
        entries_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        entriesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public Builder removeEntries(int index) {
      if (entriesBuilder_ == null) {
        ensureEntriesIsMutable();
        entries_.remove(index);
        onChanged();
      } else {
        entriesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public sawtooth.sdk.protobuf.TpStateEntry.Builder getEntriesBuilder(
        int index) {
      return getEntriesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public sawtooth.sdk.protobuf.TpStateEntryOrBuilder getEntriesOrBuilder(
        int index) {
      if (entriesBuilder_ == null) {
        return entries_.get(index);  } else {
        return entriesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public java.util.List<? extends sawtooth.sdk.protobuf.TpStateEntryOrBuilder> 
         getEntriesOrBuilderList() {
      if (entriesBuilder_ != null) {
        return entriesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(entries_);
      }
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public sawtooth.sdk.protobuf.TpStateEntry.Builder addEntriesBuilder() {
      return getEntriesFieldBuilder().addBuilder(
          sawtooth.sdk.protobuf.TpStateEntry.getDefaultInstance());
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public sawtooth.sdk.protobuf.TpStateEntry.Builder addEntriesBuilder(
        int index) {
      return getEntriesFieldBuilder().addBuilder(
          index, sawtooth.sdk.protobuf.TpStateEntry.getDefaultInstance());
    }
    /**
     * <code>repeated .TpStateEntry entries = 2;</code>
     */
    public java.util.List<sawtooth.sdk.protobuf.TpStateEntry.Builder> 
         getEntriesBuilderList() {
      return getEntriesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        sawtooth.sdk.protobuf.TpStateEntry, sawtooth.sdk.protobuf.TpStateEntry.Builder, sawtooth.sdk.protobuf.TpStateEntryOrBuilder> 
        getEntriesFieldBuilder() {
      if (entriesBuilder_ == null) {
        entriesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            sawtooth.sdk.protobuf.TpStateEntry, sawtooth.sdk.protobuf.TpStateEntry.Builder, sawtooth.sdk.protobuf.TpStateEntryOrBuilder>(
                entries_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        entries_ = null;
      }
      return entriesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:TpStateSetRequest)
  }

  // @@protoc_insertion_point(class_scope:TpStateSetRequest)
  private static final sawtooth.sdk.protobuf.TpStateSetRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.TpStateSetRequest();
  }

  public static sawtooth.sdk.protobuf.TpStateSetRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TpStateSetRequest>
      PARSER = new com.google.protobuf.AbstractParser<TpStateSetRequest>() {
    @java.lang.Override
    public TpStateSetRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TpStateSetRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TpStateSetRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TpStateSetRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.TpStateSetRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
