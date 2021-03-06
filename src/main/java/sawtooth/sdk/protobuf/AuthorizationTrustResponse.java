// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: authorization.proto

package sawtooth.sdk.protobuf;

/**
 * Protobuf type {@code AuthorizationTrustResponse}
 */
public  final class AuthorizationTrustResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:AuthorizationTrustResponse)
    AuthorizationTrustResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AuthorizationTrustResponse.newBuilder() to construct.
  private AuthorizationTrustResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AuthorizationTrustResponse() {
    roles_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AuthorizationTrustResponse(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              roles_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            roles_.add(rawValue);
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int oldLimit = input.pushLimit(length);
            while(input.getBytesUntilLimit() > 0) {
              int rawValue = input.readEnum();
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                roles_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000001;
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        roles_ = java.util.Collections.unmodifiableList(roles_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationTrustResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationTrustResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sawtooth.sdk.protobuf.AuthorizationTrustResponse.class, sawtooth.sdk.protobuf.AuthorizationTrustResponse.Builder.class);
  }

  public static final int ROLES_FIELD_NUMBER = 1;
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
   * The actual set the requester has access to
   * </pre>
   *
   * <code>repeated .RoleType roles = 1;</code>
   */
  public java.util.List<sawtooth.sdk.protobuf.RoleType> getRolesList() {
    return new com.google.protobuf.Internal.ListAdapter<
        java.lang.Integer, sawtooth.sdk.protobuf.RoleType>(roles_, roles_converter_);
  }
  /**
   * <pre>
   * The actual set the requester has access to
   * </pre>
   *
   * <code>repeated .RoleType roles = 1;</code>
   */
  public int getRolesCount() {
    return roles_.size();
  }
  /**
   * <pre>
   * The actual set the requester has access to
   * </pre>
   *
   * <code>repeated .RoleType roles = 1;</code>
   */
  public sawtooth.sdk.protobuf.RoleType getRoles(int index) {
    return roles_converter_.convert(roles_.get(index));
  }
  /**
   * <pre>
   * The actual set the requester has access to
   * </pre>
   *
   * <code>repeated .RoleType roles = 1;</code>
   */
  public java.util.List<java.lang.Integer>
  getRolesValueList() {
    return roles_;
  }
  /**
   * <pre>
   * The actual set the requester has access to
   * </pre>
   *
   * <code>repeated .RoleType roles = 1;</code>
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
    if (getRolesList().size() > 0) {
      output.writeUInt32NoTag(10);
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
    if (!(obj instanceof sawtooth.sdk.protobuf.AuthorizationTrustResponse)) {
      return super.equals(obj);
    }
    sawtooth.sdk.protobuf.AuthorizationTrustResponse other = (sawtooth.sdk.protobuf.AuthorizationTrustResponse) obj;

    boolean result = true;
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
    if (getRolesCount() > 0) {
      hash = (37 * hash) + ROLES_FIELD_NUMBER;
      hash = (53 * hash) + roles_.hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse parseFrom(
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
  public static Builder newBuilder(sawtooth.sdk.protobuf.AuthorizationTrustResponse prototype) {
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
   * Protobuf type {@code AuthorizationTrustResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:AuthorizationTrustResponse)
      sawtooth.sdk.protobuf.AuthorizationTrustResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationTrustResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationTrustResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sawtooth.sdk.protobuf.AuthorizationTrustResponse.class, sawtooth.sdk.protobuf.AuthorizationTrustResponse.Builder.class);
    }

    // Construct using sawtooth.sdk.protobuf.AuthorizationTrustResponse.newBuilder()
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
      roles_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sawtooth.sdk.protobuf.Authorization.internal_static_AuthorizationTrustResponse_descriptor;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationTrustResponse getDefaultInstanceForType() {
      return sawtooth.sdk.protobuf.AuthorizationTrustResponse.getDefaultInstance();
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationTrustResponse build() {
      sawtooth.sdk.protobuf.AuthorizationTrustResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sawtooth.sdk.protobuf.AuthorizationTrustResponse buildPartial() {
      sawtooth.sdk.protobuf.AuthorizationTrustResponse result = new sawtooth.sdk.protobuf.AuthorizationTrustResponse(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        roles_ = java.util.Collections.unmodifiableList(roles_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.roles_ = roles_;
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
      if (other instanceof sawtooth.sdk.protobuf.AuthorizationTrustResponse) {
        return mergeFrom((sawtooth.sdk.protobuf.AuthorizationTrustResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sawtooth.sdk.protobuf.AuthorizationTrustResponse other) {
      if (other == sawtooth.sdk.protobuf.AuthorizationTrustResponse.getDefaultInstance()) return this;
      if (!other.roles_.isEmpty()) {
        if (roles_.isEmpty()) {
          roles_ = other.roles_;
          bitField0_ = (bitField0_ & ~0x00000001);
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
      sawtooth.sdk.protobuf.AuthorizationTrustResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sawtooth.sdk.protobuf.AuthorizationTrustResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Integer> roles_ =
      java.util.Collections.emptyList();
    private void ensureRolesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        roles_ = new java.util.ArrayList<java.lang.Integer>(roles_);
        bitField0_ |= 0x00000001;
      }
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public java.util.List<sawtooth.sdk.protobuf.RoleType> getRolesList() {
      return new com.google.protobuf.Internal.ListAdapter<
          java.lang.Integer, sawtooth.sdk.protobuf.RoleType>(roles_, roles_converter_);
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public int getRolesCount() {
      return roles_.size();
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public sawtooth.sdk.protobuf.RoleType getRoles(int index) {
      return roles_converter_.convert(roles_.get(index));
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
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
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
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
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
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
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public Builder clearRoles() {
      roles_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public java.util.List<java.lang.Integer>
    getRolesValueList() {
      return java.util.Collections.unmodifiableList(roles_);
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public int getRolesValue(int index) {
      return roles_.get(index);
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
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
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
     */
    public Builder addRolesValue(int value) {
      ensureRolesIsMutable();
      roles_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The actual set the requester has access to
     * </pre>
     *
     * <code>repeated .RoleType roles = 1;</code>
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


    // @@protoc_insertion_point(builder_scope:AuthorizationTrustResponse)
  }

  // @@protoc_insertion_point(class_scope:AuthorizationTrustResponse)
  private static final sawtooth.sdk.protobuf.AuthorizationTrustResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sawtooth.sdk.protobuf.AuthorizationTrustResponse();
  }

  public static sawtooth.sdk.protobuf.AuthorizationTrustResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AuthorizationTrustResponse>
      PARSER = new com.google.protobuf.AbstractParser<AuthorizationTrustResponse>() {
    @java.lang.Override
    public AuthorizationTrustResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AuthorizationTrustResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AuthorizationTrustResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AuthorizationTrustResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sawtooth.sdk.protobuf.AuthorizationTrustResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

