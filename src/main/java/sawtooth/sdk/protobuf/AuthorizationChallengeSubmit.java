// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: authorization.proto

package sawtooth.sdk.protobuf;

/**
 * Protobuf type {@code AuthorizationChallengeSubmit}
 */
public  final class AuthorizationChallengeSubmit extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:AuthorizationChallengeSubmit)
    AuthorizationChallengeSubmitOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AuthorizationChallengeSubmit.newBuilder() to construct.
  private AuthorizationChallengeSubmit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AuthorizationChallengeSubmit() {
    publicKey_ = "";
    signature_ = "";
    roles_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AuthorizationChallengeSubmit(
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

            publicKey_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            signature_ = s;
            break;
          }
          case 32: {
            int rawValue = input.readEnum();
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              roles_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000004;
            }
            roles_.add(rawValue);
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int oldLimit = input.pushLimit(length);
            while(input.getBytesUntilLimit() > 0) {
              int rawValue = input.readEnum();
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                roles_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              roles_.add(rawValue);
            }
            input.popLimit(oldLimit);
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        roles_ = java.util.Collections.unmodifiableList(roles_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationChallengeSubmit_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationChallengeSubmit_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.class, sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.Builder.class);
  }

  private int bitField0_;
  public static final int PUBLIC_KEY_FIELD_NUMBER = 1;
  private volatile java.lang.Object publicKey_;
  /**
   * <pre>
   * public key of node
   * </pre>
   *
   * <code>string public_key = 1;</code>
   */
  public java.lang.String getPublicKey() {
    java.lang.Object ref = publicKey_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      publicKey_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * public key of node
   * </pre>
   *
   * <code>string public_key = 1;</code>
   */
  public com.google.protobuf.ByteString
      getPublicKeyBytes() {
    java.lang.Object ref = publicKey_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      publicKey_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SIGNATURE_FIELD_NUMBER = 3;
  private volatile java.lang.Object signature_;
  /**
   * <pre>
   * signature derived from signing the challenge payload
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
   * signature derived from signing the challenge payload
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

  public static final int ROLES_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Integer> roles_;
  private static final com.google.protobuf.Internal.ListAdapter.Converter<
      java.lang.Integer, sawtooth.sdk.protobuf.RoleType> roles_converter_ =
          new com.google.protobuf.Internal.ListAdapter.Converter<
              java.lang.Integer, sawtooth.sdk.protobuf.RoleType>() {
            public sawtooth.sdk.protobuf.RoleType convert(java.lang.Integer from) {
              @SuppressWarnings("deprecation")
              sawtooth.sdk.protobuf.RoleType result = sawtooth.sdk.protobuf.RoleType.valueOf(from);
              return result == null ? sawtooth.sdk.protobuf.RoleType.UNRECOGNIZED : result;
            }
          };
  /**
   * <pre>
   * A set of requested Roles
   * </pre>
   *
   * <code>repeated .RoleType roles = 4;</code>
   */
  public java.util.List<sawtooth.sdk.protobuf.RoleType> getRolesList() {
    return new com.google.protobuf.Internal.ListAdapter<
        java.lang.Integer, sawtooth.sdk.protobuf.RoleType>(roles_, roles_converter_);
  }
  /**
   * <pre>
   * A set of requested Roles
   * </pre>
   *
   * <code>repeated .RoleType roles = 4;</code>
   */
  public int getRolesCount() {
    return roles_.size();
  }
  /**
   * <pre>
   * A set of requested Roles
   * </pre>
   *
   * <code>repeated .RoleType roles = 4;</code>
   */
  public sawtooth.sdk.protobuf.RoleType getRoles(int index) {
    return roles_converter_.convert(roles_.get(index));
  }
  /**
   * <pre>
   * A set of requested Roles
   * </pre>
   *
   * <code>repeated .RoleType roles = 4;</code>
   */
  public java.util.List<java.lang.Integer>
  getRolesValueList() {
    return roles_;
  }
  /**
   * <pre>
   * A set of requested Roles
   * </pre>
   *
   * <code>repeated .RoleType roles = 4;</code>
   */
  public int getRolesValue(int index) {
    return roles_.get(index);
  }
  private int rolesMemoizedSerializedSize;

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
    getSerializedSize();
    if (!getPublicKeyBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, publicKey_);
    }
    if (!getSignatureBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, signature_);
    }
    if (getRolesList().size() > 0) {
      output.writeUInt32NoTag(34);
      output.writeUInt32NoTag(rolesMemoizedSerializedSize);
    }
    for (int i = 0; i < roles_.size(); i++) {
      output.writeEnumNoTag(roles_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getPublicKeyBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, publicKey_);
    }
    if (!getSignatureBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, signature_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < roles_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeEnumSizeNoTag(roles_.get(i));
      }
      size += dataSize;
      if (!getRolesList().isEmpty()) {  size += 1;
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32SizeNoTag(dataSize);
      }rolesMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof sawtooth.sdk.protobuf.AuthorizationChallengeSubmit)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.AuthorizationChallengeSubmit other = (sawtooth.sdk.protobuf.AuthorizationChallengeSubmit) obj;

    boolean result = true;
    result = result && getPublicKey()
        .equals(other.getPublicKey());
    result = result && getSignature()
        .equals(other.getSignature());
    result = result && roles_.equals(other.roles_);
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
    hash = (37 * hash) + PUBLIC_KEY_FIELD_NUMBER;
    hash = (53 * hash) + getPublicKey().hashCode();
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    if (getRolesCount() > 0) {
      hash = (37 * hash) + ROLES_FIELD_NUMBER;
      hash = (53 * hash) + roles_.hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parseFrom(
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
  public static Builder newBuilder(sawtooth.sdk.protobuf.AuthorizationChallengeSubmit prototype) {
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
   * Protobuf type {@code AuthorizationChallengeSubmit}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:AuthorizationChallengeSubmit)
      sawtooth.sdk.protobuf.AuthorizationChallengeSubmitOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationChallengeSubmit_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationChallengeSubmit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.class, sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.newBuilder()
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
      publicKey_ = "";

      signature_ = "";

      roles_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationChallengeSubmit_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationChallengeSubmit getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationChallengeSubmit build() {
      sawtooth.sdk.protobuf.AuthorizationChallengeSubmit result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationChallengeSubmit buildPartial() {
      sawtooth.sdk.protobuf.AuthorizationChallengeSubmit result = new sawtooth.sdk.protobuf.AuthorizationChallengeSubmit(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.publicKey_ = publicKey_;
      result.signature_ = signature_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        roles_ = java.util.Collections.unmodifiableList(roles_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.roles_ = roles_;
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
      if (other instanceof sawtooth.sdk.protobuf.AuthorizationChallengeSubmit) {
        return mergeFrom((sawtooth.sdk.protobuf.AuthorizationChallengeSubmit)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.AuthorizationChallengeSubmit other) {
      if (other == sawtooth.sdk.protobuf.AuthorizationChallengeSubmit.getDefaultInstance()) return this;
      if (!other.getPublicKey().isEmpty()) {
        publicKey_ = other.publicKey_;
        onChanged();
      }
      if (!other.getSignature().isEmpty()) {
        signature_ = other.signature_;
        onChanged();
      }
      if (!other.roles_.isEmpty()) {
        if (roles_.isEmpty()) {
          roles_ = other.roles_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureRolesIsMutable();
          roles_.addAll(other.roles_);
        }
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
      sawtooth.sdk.protobuf.AuthorizationChallengeSubmit parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.AuthorizationChallengeSubmit) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object publicKey_ = "";
    /**
     * <pre>
     * public key of node
     * </pre>
     *
     * <code>string public_key = 1;</code>
     */
    public java.lang.String getPublicKey() {
      java.lang.Object ref = publicKey_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        publicKey_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * public key of node
     * </pre>
     *
     * <code>string public_key = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPublicKeyBytes() {
      java.lang.Object ref = publicKey_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        publicKey_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * public key of node
     * </pre>
     *
     * <code>string public_key = 1;</code>
     */
    public Builder setPublicKey(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      publicKey_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * public key of node
     * </pre>
     *
     * <code>string public_key = 1;</code>
     */
    public Builder clearPublicKey() {
      
      publicKey_ = getDefaultInstance().getPublicKey();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * public key of node
     * </pre>
     *
     * <code>string public_key = 1;</code>
     */
    public Builder setPublicKeyBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      publicKey_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object signature_ = "";
    /**
     * <pre>
     * signature derived from signing the challenge payload
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
     * signature derived from signing the challenge payload
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
     * signature derived from signing the challenge payload
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
     * signature derived from signing the challenge payload
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
     * signature derived from signing the challenge payload
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

    private java.util.List<java.lang.Integer> roles_ =
      java.util.Collections.emptyList();
    private void ensureRolesIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        roles_ = new java.util.ArrayList<java.lang.Integer>(roles_);
        bitField0_ |= 0x00000004;
      }
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public java.util.List<sawtooth.sdk.protobuf.RoleType> getRolesList() {
      return new com.google.protobuf.Internal.ListAdapter<
          java.lang.Integer, sawtooth.sdk.protobuf.RoleType>(roles_, roles_converter_);
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public int getRolesCount() {
      return roles_.size();
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public sawtooth.sdk.protobuf.RoleType getRoles(int index) {
      return roles_converter_.convert(roles_.get(index));
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder setRoles(
        int index, sawtooth.sdk.protobuf.RoleType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureRolesIsMutable();
      roles_.set(index, value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder addRoles(sawtooth.sdk.protobuf.RoleType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureRolesIsMutable();
      roles_.add(value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder addAllRoles(
        java.lang.Iterable<? extends sawtooth.sdk.protobuf.RoleType> values) {
      ensureRolesIsMutable();
      for (sawtooth.sdk.protobuf.RoleType value : values) {
        roles_.add(value.getNumber());
      }
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder clearRoles() {
      roles_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public java.util.List<java.lang.Integer>
    getRolesValueList() {
      return java.util.Collections.unmodifiableList(roles_);
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public int getRolesValue(int index) {
      return roles_.get(index);
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder setRolesValue(
        int index, int value) {
      ensureRolesIsMutable();
      roles_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder addRolesValue(int value) {
      ensureRolesIsMutable();
      roles_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A set of requested Roles
     * </pre>
     *
     * <code>repeated .RoleType roles = 4;</code>
     */
    public Builder addAllRolesValue(
        java.lang.Iterable<java.lang.Integer> values) {
      ensureRolesIsMutable();
      for (int value : values) {
        roles_.add(value);
      }
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


    // @@protoc_insertion_point(builder_scope:AuthorizationChallengeSubmit)
  }

  // @@protoc_insertion_point(class_scope:AuthorizationChallengeSubmit)
  private static final sawtooth.sdk.protobuf.AuthorizationChallengeSubmit DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.AuthorizationChallengeSubmit();
  }

  public static sawtooth.sdk.protobuf.AuthorizationChallengeSubmit getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AuthorizationChallengeSubmit>
      PARSER = new com.google.protobuf.AbstractParser<AuthorizationChallengeSubmit>() {
    @java.lang.Override
    public AuthorizationChallengeSubmit parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AuthorizationChallengeSubmit(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AuthorizationChallengeSubmit> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AuthorizationChallengeSubmit> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.AuthorizationChallengeSubmit getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

