// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client_list_control.proto

package sawtooth.sdk.protobuf;

/**
 * <pre>
 * Sorting controls to be sent with List requests. More than one can be sent.
 * If so, the first is used, and additional controls are tie-breakers.
 * Attributes:
 *     keys: Nested set of keys to sort by (i.e. ['default, block_num'])
 *     reverse: Whether or not to reverse the sort (i.e. descending order)
 * </pre>
 *
 * Protobuf type {@code ClientSortControls}
 */
public  final class ClientSortControls extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ClientSortControls)
    ClientSortControlsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ClientSortControls.newBuilder() to construct.
  private ClientSortControls(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ClientSortControls() {
    keys_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    reverse_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ClientSortControls(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              keys_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            keys_.add(s);
            break;
          }
          case 16: {

            reverse_ = input.readBool();
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        keys_ = keys_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sawtooth.sdk.protobuf.ClientListControl.internal_static_ClientSortControls_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.ClientListControl.internal_static_ClientSortControls_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.ClientSortControls.class, sawtooth.sdk.protobuf.ClientSortControls.Builder.class);
  }

  private int bitField0_;
  public static final int KEYS_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList keys_;
  /**
   * <code>repeated string keys = 1;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getKeysList() {
    return keys_;
  }
  /**
   * <code>repeated string keys = 1;</code>
   */
  public int getKeysCount() {
    return keys_.size();
  }
  /**
   * <code>repeated string keys = 1;</code>
   */
  public java.lang.String getKeys(int index) {
    return keys_.get(index);
  }
  /**
   * <code>repeated string keys = 1;</code>
   */
  public com.google.protobuf.ByteString
      getKeysBytes(int index) {
    return keys_.getByteString(index);
  }

  public static final int REVERSE_FIELD_NUMBER = 2;
  private boolean reverse_;
  /**
   * <code>bool reverse = 2;</code>
   */
  public boolean getReverse() {
    return reverse_;
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
    for (int i = 0; i < keys_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, keys_.getRaw(i));
    }
    if (reverse_ != false) {
      output.writeBool(2, reverse_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < keys_.size(); i++) {
        dataSize += computeStringSizeNoTag(keys_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getKeysList().size();
    }
    if (reverse_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, reverse_);
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
    if (!(obj instanceof sawtooth.sdk.protobuf.ClientSortControls)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.ClientSortControls other = (sawtooth.sdk.protobuf.ClientSortControls) obj;

    boolean result = true;
    result = result && getKeysList()
        .equals(other.getKeysList());
    result = result && (getReverse()
        == other.getReverse());
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
    if (getKeysCount() > 0) {
      hash = (37 * hash) + KEYS_FIELD_NUMBER;
      hash = (53 * hash) + getKeysList().hashCode();
    }
    hash = (37 * hash) + REVERSE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getReverse());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.ClientSortControls parseFrom(
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
  public static Builder newBuilder(sawtooth.sdk.protobuf.ClientSortControls prototype) {
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
   * Sorting controls to be sent with List requests. More than one can be sent.
   * If so, the first is used, and additional controls are tie-breakers.
   * Attributes:
   *     keys: Nested set of keys to sort by (i.e. ['default, block_num'])
   *     reverse: Whether or not to reverse the sort (i.e. descending order)
   * </pre>
   *
   * Protobuf type {@code ClientSortControls}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ClientSortControls)
      sawtooth.sdk.protobuf.ClientSortControlsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.ClientListControl.internal_static_ClientSortControls_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.ClientListControl.internal_static_ClientSortControls_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.ClientSortControls.class, sawtooth.sdk.protobuf.ClientSortControls.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.ClientSortControls.newBuilder()
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
      keys_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      reverse_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.ClientListControl.internal_static_ClientSortControls_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientSortControls getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.ClientSortControls.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientSortControls build() {
      sawtooth.sdk.protobuf.ClientSortControls result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.ClientSortControls buildPartial() {
      sawtooth.sdk.protobuf.ClientSortControls result = new sawtooth.sdk.protobuf.ClientSortControls(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        keys_ = keys_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.keys_ = keys_;
      result.reverse_ = reverse_;
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
      if (other instanceof sawtooth.sdk.protobuf.ClientSortControls) {
        return mergeFrom((sawtooth.sdk.protobuf.ClientSortControls)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.ClientSortControls other) {
      if (other == sawtooth.sdk.protobuf.ClientSortControls.getDefaultInstance()) return this;
      if (!other.keys_.isEmpty()) {
        if (keys_.isEmpty()) {
          keys_ = other.keys_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureKeysIsMutable();
          keys_.addAll(other.keys_);
        }
        onChanged();
      }
      if (other.getReverse() != false) {
        setReverse(other.getReverse());
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
      sawtooth.sdk.protobuf.ClientSortControls parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.ClientSortControls) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList keys_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureKeysIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        keys_ = new com.google.protobuf.LazyStringArrayList(keys_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getKeysList() {
      return keys_.getUnmodifiableView();
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public int getKeysCount() {
      return keys_.size();
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public java.lang.String getKeys(int index) {
      return keys_.get(index);
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public com.google.protobuf.ByteString
        getKeysBytes(int index) {
      return keys_.getByteString(index);
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public Builder setKeys(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureKeysIsMutable();
      keys_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public Builder addKeys(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureKeysIsMutable();
      keys_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public Builder addAllKeys(
        java.lang.Iterable<java.lang.String> values) {
      ensureKeysIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, keys_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public Builder clearKeys() {
      keys_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string keys = 1;</code>
     */
    public Builder addKeysBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureKeysIsMutable();
      keys_.add(value);
      onChanged();
      return this;
    }

    private boolean reverse_ ;
    /**
     * <code>bool reverse = 2;</code>
     */
    public boolean getReverse() {
      return reverse_;
    }
    /**
     * <code>bool reverse = 2;</code>
     */
    public Builder setReverse(boolean value) {
      
      reverse_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool reverse = 2;</code>
     */
    public Builder clearReverse() {
      
      reverse_ = false;
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


    // @@protoc_insertion_point(builder_scope:ClientSortControls)
  }

  // @@protoc_insertion_point(class_scope:ClientSortControls)
  private static final sawtooth.sdk.protobuf.ClientSortControls DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.ClientSortControls();
  }

  public static sawtooth.sdk.protobuf.ClientSortControls getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ClientSortControls>
      PARSER = new com.google.protobuf.AbstractParser<ClientSortControls>() {
    @java.lang.Override
    public ClientSortControls parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ClientSortControls(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ClientSortControls> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ClientSortControls> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.ClientSortControls getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

