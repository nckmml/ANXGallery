package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class MessageSchema<T> implements Schema<T> {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema2 != null && extensionSchema2.hasExtensions(messageLite);
        this.useCachedSizeField = z2;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema2;
        this.listFieldSchema = listFieldSchema2;
        this.unknownFieldSchema = unknownFieldSchema2;
        this.extensionSchema = extensionSchema2;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema2;
    }

    private boolean arePresentForEquals(T t, T t2, int i) {
        return isFieldPresent(t, i) == isFieldPresent(t2, i);
    }

    private static <T> boolean booleanAt(T t, long j) {
        return UnsafeUtil.getBoolean((Object) t, j);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v6, types: [byte, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] bArr, int i, int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws IOException {
        int i3;
        byte[] bArr2 = bArr;
        int i4 = i2;
        MapEntryLite.Metadata<K, V> metadata2 = metadata;
        ArrayDecoders.Registers registers2 = registers;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers2);
        int i5 = registers2.int1;
        if (i5 < 0 || i5 > i4 - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i6 = decodeVarint32 + i5;
        K k = metadata2.defaultKey;
        V v = metadata2.defaultValue;
        while (decodeVarint32 < i6) {
            int i7 = decodeVarint32 + 1;
            int i8 = bArr2[decodeVarint32];
            if (i8 < 0) {
                i3 = ArrayDecoders.decodeVarint32(i8, bArr, i7, registers2);
                i8 = registers2.int1;
            } else {
                i3 = i7;
            }
            int i9 = i8 >>> 3;
            int i10 = i8 & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == metadata2.valueType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata2.valueType, metadata2.defaultValue.getClass(), registers);
                    v = registers2.object1;
                }
            } else if (i10 == metadata2.keyType.getWireType()) {
                decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata2.keyType, (Class<?>) null, registers);
                k = registers2.object1;
            }
            decodeVarint32 = ArrayDecoders.skipField(i8, bArr, i3, i4, registers2);
        }
        if (decodeVarint32 == i6) {
            map.put(k, v);
            return i6;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r3 + 8;
     */
    private int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) throws IOException {
        switch (fieldType) {
            case BOOL:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case BYTES:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case DOUBLE:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i));
                break;
            case FIXED32:
            case SFIXED32:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i));
                break;
            case FIXED64:
            case SFIXED64:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i));
                break;
            case FLOAT:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i));
                break;
            case ENUM:
            case INT32:
            case UINT32:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case INT64:
            case UINT64:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case MESSAGE:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(cls), bArr, i, i2, registers);
            case SINT32:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case SINT64:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case STRING:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static <T> double doubleAt(T t, long j) {
        return UnsafeUtil.getDouble((Object) t, j);
    }

    private boolean equals(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(t, t2, i) && Double.doubleToLongBits(UnsafeUtil.getDouble((Object) t, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble((Object) t2, offset));
            case 1:
                return arePresentForEquals(t, t2, i) && Float.floatToIntBits(UnsafeUtil.getFloat((Object) t, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat((Object) t2, offset));
            case 2:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong((Object) t, offset) == UnsafeUtil.getLong((Object) t2, offset);
            case 3:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong((Object) t, offset) == UnsafeUtil.getLong((Object) t2, offset);
            case 4:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 5:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong((Object) t, offset) == UnsafeUtil.getLong((Object) t2, offset);
            case 6:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 7:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getBoolean((Object) t, offset) == UnsafeUtil.getBoolean((Object) t2, offset);
            case 8:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 9:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 10:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 11:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 12:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 13:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 14:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong((Object) t, offset) == UnsafeUtil.getLong((Object) t2, offset);
            case 15:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt((Object) t, offset) == UnsafeUtil.getInt((Object) t2, offset);
            case 16:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong((Object) t, offset) == UnsafeUtil.getLong((Object) t2, offset);
            case 17:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            default:
                return true;
        }
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema2) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) {
            return ub;
        }
        return filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema2);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema2) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema2.newBuilder();
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema2.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static <T> float floatAt(T t, long j) {
        return UnsafeUtil.getFloat((Object) t, j);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Schema schema = (Schema) this.objects[i2];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i2 + 1]);
        this.objects[i2] = schemaFor;
        return schemaFor;
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x037d, code lost:
        r5 = r5 + ((r9 + r10) + r8);
     */
    private int getSerializedSizeProto2(T t) {
        int i;
        int i2;
        int computeDoubleSize;
        int i3;
        int i4;
        int i5;
        T t2 = t;
        Unsafe unsafe = UNSAFE;
        int i6 = 0;
        int i7 = 0;
        int i8 = -1;
        for (int i9 = 0; i9 < this.buffer.length; i9 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i9);
            int numberAt = numberAt(i9);
            int type = type(typeAndOffsetAt);
            if (type <= 17) {
                i2 = this.buffer[i9 + 2];
                int i10 = OFFSET_MASK & i2;
                int i11 = 1 << (i2 >>> 20);
                if (i10 != i8) {
                    i7 = unsafe.getInt(t2, (long) i10);
                    i8 = i10;
                }
                i = i11;
            } else if (!this.useCachedSizeField || type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.buffer[i9 + 2] & OFFSET_MASK;
                i = 0;
            }
            long offset = offset(typeAndOffsetAt);
            switch (type) {
                case 0:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    } else {
                        continue;
                    }
                case 1:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    } else {
                        continue;
                    }
                case 2:
                    long j = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(t2, j));
                    } else {
                        continue;
                    }
                case 3:
                    long j2 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(t2, j2));
                    } else {
                        continue;
                    }
                case 4:
                    long j3 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(t2, j3));
                    } else {
                        continue;
                    }
                case 5:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 6:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 7:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                    } else {
                        continue;
                    }
                case 8:
                    long j4 = offset;
                    if ((i7 & i) != 0) {
                        Object object = unsafe.getObject(t2, j4);
                        computeDoubleSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object) : CodedOutputStream.computeStringSize(numberAt, (String) object);
                    } else {
                        continue;
                    }
                case 9:
                    long j5 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t2, j5), getMessageFieldSchema(i9));
                    } else {
                        continue;
                    }
                case 10:
                    long j6 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t2, j6));
                    } else {
                        continue;
                    }
                case 11:
                    long j7 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(t2, j7));
                    } else {
                        continue;
                    }
                case 12:
                    long j8 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(t2, j8));
                    } else {
                        continue;
                    }
                case 13:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 14:
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 15:
                    long j9 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(t2, j9));
                    } else {
                        continue;
                    }
                case 16:
                    long j10 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(t2, j10));
                    } else {
                        continue;
                    }
                case 17:
                    long j11 = offset;
                    if ((i7 & i) != 0) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t2, j11), getMessageFieldSchema(i9));
                    } else {
                        continue;
                    }
                case 18:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 19:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 20:
                    computeDoubleSize = SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 21:
                    computeDoubleSize = SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 22:
                    computeDoubleSize = SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 23:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 24:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 25:
                    computeDoubleSize = SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 26:
                    computeDoubleSize = SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(t2, offset));
                    i6 += computeDoubleSize;
                    break;
                case 27:
                    computeDoubleSize = SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(t2, offset), getMessageFieldSchema(i9));
                    i6 += computeDoubleSize;
                    break;
                case 28:
                    computeDoubleSize = SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(t2, offset));
                    i6 += computeDoubleSize;
                    break;
                case 29:
                    computeDoubleSize = SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 30:
                    computeDoubleSize = SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 31:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 32:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 33:
                    computeDoubleSize = SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 34:
                    computeDoubleSize = SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i6 += computeDoubleSize;
                    break;
                case 35:
                    i5 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 36:
                    i5 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 37:
                    i5 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 38:
                    i5 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 39:
                    i5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 40:
                    i5 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 41:
                    i5 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 42:
                    i5 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 43:
                    i5 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 44:
                    i5 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 45:
                    i5 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 46:
                    i5 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 47:
                    i5 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 48:
                    i5 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i5 <= 0) {
                        continue;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i2, i5);
                        }
                        i4 = CodedOutputStream.computeTagSize(numberAt);
                        i3 = CodedOutputStream.computeUInt32SizeNoTag(i5);
                    }
                case 49:
                    computeDoubleSize = SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(t2, offset), getMessageFieldSchema(i9));
                    i6 += computeDoubleSize;
                    break;
                case 50:
                    computeDoubleSize = this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(t2, offset), getMapFieldDefaultEntry(i9));
                    i6 += computeDoubleSize;
                    break;
                case 51:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    } else {
                        continue;
                    }
                case 52:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    } else {
                        continue;
                    }
                case 53:
                    long j12 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t2, j12));
                    } else {
                        continue;
                    }
                case 54:
                    long j13 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t2, j13));
                    } else {
                        continue;
                    }
                case 55:
                    long j14 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t2, j14));
                    } else {
                        continue;
                    }
                case 56:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 57:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 58:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                    } else {
                        continue;
                    }
                case 59:
                    long j15 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        Object object2 = unsafe.getObject(t2, j15);
                        computeDoubleSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2) : CodedOutputStream.computeStringSize(numberAt, (String) object2);
                    } else {
                        continue;
                    }
                case 60:
                    long j16 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t2, j16), getMessageFieldSchema(i9));
                    } else {
                        continue;
                    }
                case 61:
                    long j17 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t2, j17));
                    } else {
                        continue;
                    }
                case 62:
                    long j18 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t2, j18));
                    } else {
                        continue;
                    }
                case 63:
                    long j19 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t2, j19));
                    } else {
                        continue;
                    }
                case 64:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 65:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 66:
                    long j20 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t2, j20));
                    } else {
                        continue;
                    }
                case 67:
                    long j21 = offset;
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t2, j21));
                    } else {
                        continue;
                    }
                case 68:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t2, offset), getMessageFieldSchema(i9));
                    } else {
                        continue;
                    }
            }
            i6 += computeDoubleSize;
        }
        int unknownFieldsSerializedSize = i6 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t2).getSerializedSize() : unknownFieldsSerializedSize;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private int getSerializedSizeProto3(T t) {
        int computeDoubleSize;
        int i;
        int i2;
        int i3;
        T t2 = t;
        Unsafe unsafe = UNSAFE;
        int i4 = 0;
        for (int i5 = 0; i5 < this.buffer.length; i5 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i5);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i5);
            long offset = offset(typeAndOffsetAt);
            int i6 = (type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i5 + 2] & OFFSET_MASK;
            switch (type) {
                case 0:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    } else {
                        continue;
                    }
                case 1:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    } else {
                        continue;
                    }
                case 2:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, UnsafeUtil.getLong((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 3:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, UnsafeUtil.getLong((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 4:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, UnsafeUtil.getInt((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 5:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 6:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 7:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                    } else {
                        continue;
                    }
                case 8:
                    if (isFieldPresent(t2, i5)) {
                        Object object = UnsafeUtil.getObject((Object) t2, offset);
                        computeDoubleSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object) : CodedOutputStream.computeStringSize(numberAt, (String) object);
                    } else {
                        continue;
                    }
                case 9:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject((Object) t2, offset), getMessageFieldSchema(i5));
                    } else {
                        continue;
                    }
                case 10:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 11:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, UnsafeUtil.getInt((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 12:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, UnsafeUtil.getInt((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 13:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 14:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 15:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, UnsafeUtil.getInt((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 16:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, UnsafeUtil.getLong((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 17:
                    if (isFieldPresent(t2, i5)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject((Object) t2, offset), getMessageFieldSchema(i5));
                    } else {
                        continue;
                    }
                case 18:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 19:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 20:
                    computeDoubleSize = SchemaUtil.computeSizeInt64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 21:
                    computeDoubleSize = SchemaUtil.computeSizeUInt64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 22:
                    computeDoubleSize = SchemaUtil.computeSizeInt32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 23:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 24:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 25:
                    computeDoubleSize = SchemaUtil.computeSizeBoolList(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 26:
                    computeDoubleSize = SchemaUtil.computeSizeStringList(numberAt, listAt(t2, offset));
                    i4 += computeDoubleSize;
                    break;
                case 27:
                    computeDoubleSize = SchemaUtil.computeSizeMessageList(numberAt, listAt(t2, offset), getMessageFieldSchema(i5));
                    i4 += computeDoubleSize;
                    break;
                case 28:
                    computeDoubleSize = SchemaUtil.computeSizeByteStringList(numberAt, listAt(t2, offset));
                    i4 += computeDoubleSize;
                    break;
                case 29:
                    computeDoubleSize = SchemaUtil.computeSizeUInt32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 30:
                    computeDoubleSize = SchemaUtil.computeSizeEnumList(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 31:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 32:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 33:
                    computeDoubleSize = SchemaUtil.computeSizeSInt32List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 34:
                    computeDoubleSize = SchemaUtil.computeSizeSInt64List(numberAt, listAt(t2, offset), false);
                    i4 += computeDoubleSize;
                    break;
                case 35:
                    i2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 36:
                    i2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 37:
                    i2 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 38:
                    i2 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 39:
                    i2 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 40:
                    i2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 41:
                    i2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 42:
                    i2 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 43:
                    i2 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 44:
                    i2 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 45:
                    i2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 46:
                    i2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 47:
                    i2 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 48:
                    i2 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, (long) i6, i2);
                        }
                        i3 = CodedOutputStream.computeTagSize(numberAt);
                        i = CodedOutputStream.computeUInt32SizeNoTag(i2);
                        break;
                    } else {
                        continue;
                    }
                case 49:
                    computeDoubleSize = SchemaUtil.computeSizeGroupList(numberAt, listAt(t2, offset), getMessageFieldSchema(i5));
                    i4 += computeDoubleSize;
                    break;
                case 50:
                    computeDoubleSize = this.mapFieldSchema.getSerializedSize(numberAt, UnsafeUtil.getObject((Object) t2, offset), getMapFieldDefaultEntry(i5));
                    i4 += computeDoubleSize;
                    break;
                case 51:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    } else {
                        continue;
                    }
                case 52:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    } else {
                        continue;
                    }
                case 53:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t2, offset));
                    } else {
                        continue;
                    }
                case 54:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t2, offset));
                    } else {
                        continue;
                    }
                case 55:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t2, offset));
                    } else {
                        continue;
                    }
                case 56:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 57:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 58:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                    } else {
                        continue;
                    }
                case 59:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        Object object2 = UnsafeUtil.getObject((Object) t2, offset);
                        computeDoubleSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2) : CodedOutputStream.computeStringSize(numberAt, (String) object2);
                    } else {
                        continue;
                    }
                case 60:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject((Object) t2, offset), getMessageFieldSchema(i5));
                    } else {
                        continue;
                    }
                case 61:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject((Object) t2, offset));
                    } else {
                        continue;
                    }
                case 62:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t2, offset));
                    } else {
                        continue;
                    }
                case 63:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t2, offset));
                    } else {
                        continue;
                    }
                case 64:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 65:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0);
                    } else {
                        continue;
                    }
                case 66:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t2, offset));
                    } else {
                        continue;
                    }
                case 67:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t2, offset));
                    } else {
                        continue;
                    }
                case 68:
                    if (isOneofPresent(t2, numberAt, i5)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject((Object) t2, offset), getMessageFieldSchema(i5));
                    } else {
                        continue;
                    }
            }
            computeDoubleSize = i3 + i + i2;
            i4 += computeDoubleSize;
        }
        return i4 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema2, T t) {
        return unknownFieldSchema2.getSerializedSize(unknownFieldSchema2.getFromMessage(t));
    }

    private static <T> int intAt(T t, long j) {
        return UnsafeUtil.getInt((Object) t, j);
    }

    private static boolean isEnforceUtf8(int i) {
        return (i & ENFORCE_UTF8_MASK) != 0;
    }

    private boolean isFieldPresent(T t, int i) {
        if (this.proto3) {
            int typeAndOffsetAt = typeAndOffsetAt(i);
            long offset = offset(typeAndOffsetAt);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    return UnsafeUtil.getDouble((Object) t, offset) != 0.0d;
                case 1:
                    return UnsafeUtil.getFloat((Object) t, offset) != 0.0f;
                case 2:
                    return UnsafeUtil.getLong((Object) t, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong((Object) t, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong((Object) t, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean((Object) t, offset);
                case 8:
                    Object object = UnsafeUtil.getObject((Object) t, offset);
                    if (object instanceof String) {
                        return !((String) object).isEmpty();
                    }
                    if (object instanceof ByteString) {
                        return !ByteString.EMPTY.equals(object);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject((Object) t, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject((Object) t, offset));
                case 11:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong((Object) t, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt((Object) t, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong((Object) t, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject((Object) t, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
            return (UnsafeUtil.getInt((Object) t, (long) (presenceMaskAndOffsetAt & OFFSET_MASK)) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
    }

    private boolean isFieldPresent(T t, int i, int i2, int i3) {
        return this.proto3 ? isFieldPresent(t, i) : (i2 & i3) != 0;
    }

    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private <N> boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject((Object) t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema<?> schema = null;
        for (Object next : forMapData.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor(next.getClass());
            }
            if (!schema.isInitialized(next)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOneofCaseEqual(T t, T t2, int i) {
        long presenceMaskAndOffsetAt = (long) (presenceMaskAndOffsetAt(i) & OFFSET_MASK);
        return UnsafeUtil.getInt((Object) t, presenceMaskAndOffsetAt) == UnsafeUtil.getInt((Object) t2, presenceMaskAndOffsetAt);
    }

    private boolean isOneofPresent(T t, int i, int i2) {
        return UnsafeUtil.getInt((Object) t, (long) (presenceMaskAndOffsetAt(i2) & OFFSET_MASK)) == i;
    }

    private static boolean isRequired(int i) {
        return (i & REQUIRED_MASK) != 0;
    }

    private static List<?> listAt(Object obj, long j) {
        return (List) UnsafeUtil.getObject(obj, j);
    }

    private static <T> long longAt(T t, long j) {
        return UnsafeUtil.getLong((Object) t, j);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r17, com.google.protobuf.ExtensionSchema<ET> r18, T r19, com.google.protobuf.Reader r20, com.google.protobuf.ExtensionRegistryLite r21) throws java.io.IOException {
        /*
            r16 = this;
            r8 = r16
            r9 = r17
            r10 = r19
            r0 = r20
            r11 = r21
            r12 = 0
            r13 = r12
            r14 = r13
        L_0x000d:
            int r1 = r20.getFieldNumber()     // Catch:{ all -> 0x0610 }
            int r3 = r8.positionForFieldNumber(r1)     // Catch:{ all -> 0x0610 }
            if (r3 >= 0) goto L_0x008d
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x0033
            int r0 = r8.checkInitializedCount
        L_0x001e:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x002d
            int[] r1 = r8.intArray
            r1 = r1[r0]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r1, r13, r9)
            int r0 = r0 + 1
            goto L_0x001e
        L_0x002d:
            if (r13 == 0) goto L_0x0032
            r9.setBuilderToMessage(r10, r13)
        L_0x0032:
            return
        L_0x0033:
            boolean r2 = r8.hasExtensions     // Catch:{ all -> 0x0610 }
            if (r2 != 0) goto L_0x003b
            r15 = r18
            r3 = r12
            goto L_0x0044
        L_0x003b:
            com.google.protobuf.MessageLite r2 = r8.defaultInstance     // Catch:{ all -> 0x0610 }
            r15 = r18
            java.lang.Object r1 = r15.findExtensionByNumber(r11, r2, r1)     // Catch:{ all -> 0x0610 }
            r3 = r1
        L_0x0044:
            if (r3 == 0) goto L_0x005c
            if (r14 != 0) goto L_0x004d
            com.google.protobuf.FieldSet r1 = r18.getMutableExtensions(r19)     // Catch:{ all -> 0x0610 }
            r14 = r1
        L_0x004d:
            r1 = r18
            r2 = r20
            r4 = r21
            r5 = r14
            r6 = r13
            r7 = r17
            java.lang.Object r13 = r1.parseExtension(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0610 }
            goto L_0x000d
        L_0x005c:
            boolean r1 = r9.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x0610 }
            if (r1 == 0) goto L_0x0069
            boolean r1 = r20.skipField()     // Catch:{ all -> 0x0610 }
            if (r1 == 0) goto L_0x0076
            goto L_0x000d
        L_0x0069:
            if (r13 != 0) goto L_0x006f
            java.lang.Object r13 = r9.getBuilderFromMessage(r10)     // Catch:{ all -> 0x0610 }
        L_0x006f:
            boolean r1 = r9.mergeOneFieldFrom(r13, r0)     // Catch:{ all -> 0x0610 }
            if (r1 == 0) goto L_0x0076
            goto L_0x000d
        L_0x0076:
            int r0 = r8.checkInitializedCount
        L_0x0078:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0087
            int[] r1 = r8.intArray
            r1 = r1[r0]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r1, r13, r9)
            int r0 = r0 + 1
            goto L_0x0078
        L_0x0087:
            if (r13 == 0) goto L_0x008c
            r9.setBuilderToMessage(r10, r13)
        L_0x008c:
            return
        L_0x008d:
            r15 = r18
            int r4 = r8.typeAndOffsetAt(r3)     // Catch:{ all -> 0x0610 }
            int r2 = type(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            switch(r2) {
                case 0: goto L_0x059c;
                case 1: goto L_0x058c;
                case 2: goto L_0x057c;
                case 3: goto L_0x056c;
                case 4: goto L_0x055c;
                case 5: goto L_0x054c;
                case 6: goto L_0x053c;
                case 7: goto L_0x052c;
                case 8: goto L_0x0524;
                case 9: goto L_0x04ed;
                case 10: goto L_0x04dd;
                case 11: goto L_0x04cd;
                case 12: goto L_0x04aa;
                case 13: goto L_0x049a;
                case 14: goto L_0x048a;
                case 15: goto L_0x047a;
                case 16: goto L_0x046a;
                case 17: goto L_0x0433;
                case 18: goto L_0x0424;
                case 19: goto L_0x0415;
                case 20: goto L_0x0406;
                case 21: goto L_0x03f7;
                case 22: goto L_0x03e8;
                case 23: goto L_0x03d9;
                case 24: goto L_0x03ca;
                case 25: goto L_0x03bb;
                case 26: goto L_0x03b6;
                case 27: goto L_0x03a4;
                case 28: goto L_0x0395;
                case 29: goto L_0x0386;
                case 30: goto L_0x036f;
                case 31: goto L_0x0360;
                case 32: goto L_0x0351;
                case 33: goto L_0x0342;
                case 34: goto L_0x0333;
                case 35: goto L_0x0324;
                case 36: goto L_0x0315;
                case 37: goto L_0x0306;
                case 38: goto L_0x02f7;
                case 39: goto L_0x02e8;
                case 40: goto L_0x02d9;
                case 41: goto L_0x02ca;
                case 42: goto L_0x02bb;
                case 43: goto L_0x02ac;
                case 44: goto L_0x0295;
                case 45: goto L_0x0286;
                case 46: goto L_0x0277;
                case 47: goto L_0x0268;
                case 48: goto L_0x0259;
                case 49: goto L_0x0243;
                case 50: goto L_0x0232;
                case 51: goto L_0x021e;
                case 52: goto L_0x020a;
                case 53: goto L_0x01f6;
                case 54: goto L_0x01e2;
                case 55: goto L_0x01ce;
                case 56: goto L_0x01ba;
                case 57: goto L_0x01a6;
                case 58: goto L_0x0192;
                case 59: goto L_0x018a;
                case 60: goto L_0x0151;
                case 61: goto L_0x0141;
                case 62: goto L_0x012d;
                case 63: goto L_0x0106;
                case 64: goto L_0x00f2;
                case 65: goto L_0x00de;
                case 66: goto L_0x00ca;
                case 67: goto L_0x00b6;
                case 68: goto L_0x00a2;
                default: goto L_0x009a;
            }     // Catch:{ InvalidWireTypeException -> 0x05c9 }
        L_0x009a:
            if (r13 != 0) goto L_0x05ac
            java.lang.Object r13 = r17.newBuilder()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x05ac
        L_0x00a2:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = r0.readGroupBySchemaWithCheck(r2, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x00b6:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r6 = r20.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x00ca:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r2 = r20.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x00de:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r6 = r20.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x00f2:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r2 = r20.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0106:
            int r2 = r20.readEnum()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Internal$EnumVerifier r5 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r5 == 0) goto L_0x011d
            boolean r5 = r5.isInRange(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r5 == 0) goto L_0x0117
            goto L_0x011d
        L_0x0117:
            java.lang.Object r13 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r1, r2, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x011d:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x012d:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r2 = r20.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0141:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.ByteString r2 = r20.readBytes()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0151:
            boolean r2 = r8.isOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r2 == 0) goto L_0x0173
            long r5 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r5)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r5 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r5 = r0.readMessageBySchemaWithCheck(r5, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = com.google.protobuf.Internal.mergeMessage(r2, r5)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x0185
        L_0x0173:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = r0.readMessageBySchemaWithCheck(r2, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
        L_0x0185:
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x018a:
            r8.readString(r10, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0192:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            boolean r2 = r20.readBool()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x01a6:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r2 = r20.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x01ba:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r6 = r20.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x01ce:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r2 = r20.readInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x01e2:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r6 = r20.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x01f6:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r6 = r20.readInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x020a:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            float r2 = r20.readFloat()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x021e:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            double r6 = r20.readDouble()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Double r2 = java.lang.Double.valueOf(r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r4, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setOneofPresent(r10, r1, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0232:
            java.lang.Object r4 = r8.getMapFieldDefaultEntry(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r1 = r16
            r2 = r19
            r5 = r21
            r6 = r20
            r1.mergeMap(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0243:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r6 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r1 = r16
            r2 = r19
            r3 = r4
            r5 = r20
            r7 = r21
            r1.readGroupList(r2, r3, r5, r6, r7)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0259:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0268:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0277:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0286:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0295:
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r2 = r2.mutableListAt(r10, r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readEnumList(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Internal$EnumVerifier r3 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r13 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((int) r1, (java.util.List<java.lang.Integer>) r2, (com.google.protobuf.Internal.EnumVerifier) r3, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02ac:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02bb:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02ca:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02d9:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02e8:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x02f7:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0306:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0315:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0324:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0333:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0342:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0351:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0360:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x036f:
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r2 = r2.mutableListAt(r10, r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readEnumList(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Internal$EnumVerifier r3 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r13 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((int) r1, (java.util.List<java.lang.Integer>) r2, (com.google.protobuf.Internal.EnumVerifier) r3, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0386:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0395:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readBytesList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03a4:
            com.google.protobuf.Schema r5 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r1 = r16
            r2 = r19
            r3 = r4
            r4 = r20
            r6 = r21
            r1.readMessageList(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03b6:
            r8.readStringList(r10, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03bb:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03ca:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03d9:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03e8:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x03f7:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0406:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0415:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0424:
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0433:
            boolean r1 = r8.isFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r1 == 0) goto L_0x0456
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r1 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = r0.readGroupBySchemaWithCheck(r2, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r1 = com.google.protobuf.Internal.mergeMessage(r1, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0456:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r4 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r4 = r0.readGroupBySchemaWithCheck(r4, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x046a:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = r20.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x047a:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r4 = r20.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x048a:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = r20.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x049a:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r4 = r20.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x04aa:
            int r2 = r20.readEnum()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Internal$EnumVerifier r5 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r5 == 0) goto L_0x04c1
            boolean r5 = r5.isInRange(r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r5 == 0) goto L_0x04bb
            goto L_0x04c1
        L_0x04bb:
            java.lang.Object r13 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r1, r2, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x04c1:
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r4, (int) r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x04cd:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r4 = r20.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x04dd:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.ByteString r4 = r20.readBytes()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x04ed:
            boolean r1 = r8.isFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r1 == 0) goto L_0x0510
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r1 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r2 = r0.readMessageBySchemaWithCheck(r2, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r1 = com.google.protobuf.Internal.mergeMessage(r1, r2)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0510:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.Schema r4 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            java.lang.Object r4 = r0.readMessageBySchemaWithCheck(r4, r11)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x0524:
            r8.readString(r10, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x052c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            boolean r4 = r20.readBool()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r10, (long) r1, (boolean) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x053c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r4 = r20.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x054c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = r20.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x055c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            int r4 = r20.readInt32()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x056c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = r20.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x057c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            long r4 = r20.readInt64()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x058c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            float r4 = r20.readFloat()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r10, (long) r1, (float) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x059c:
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            double r4 = r20.readDouble()     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r10, (long) r1, (double) r4)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            r8.setFieldPresent(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            goto L_0x000d
        L_0x05ac:
            boolean r1 = r9.mergeOneFieldFrom(r13, r0)     // Catch:{ InvalidWireTypeException -> 0x05c9 }
            if (r1 != 0) goto L_0x000d
            int r0 = r8.checkInitializedCount
        L_0x05b4:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x05c3
            int[] r1 = r8.intArray
            r1 = r1[r0]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r1, r13, r9)
            int r0 = r0 + 1
            goto L_0x05b4
        L_0x05c3:
            if (r13 == 0) goto L_0x05c8
            r9.setBuilderToMessage(r10, r13)
        L_0x05c8:
            return
        L_0x05c9:
            boolean r1 = r9.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x0610 }
            if (r1 == 0) goto L_0x05ec
            boolean r1 = r20.skipField()     // Catch:{ all -> 0x0610 }
            if (r1 != 0) goto L_0x000d
            int r0 = r8.checkInitializedCount
        L_0x05d7:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x05e6
            int[] r1 = r8.intArray
            r1 = r1[r0]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r1, r13, r9)
            int r0 = r0 + 1
            goto L_0x05d7
        L_0x05e6:
            if (r13 == 0) goto L_0x05eb
            r9.setBuilderToMessage(r10, r13)
        L_0x05eb:
            return
        L_0x05ec:
            if (r13 != 0) goto L_0x05f3
            java.lang.Object r1 = r9.getBuilderFromMessage(r10)     // Catch:{ all -> 0x0610 }
            r13 = r1
        L_0x05f3:
            boolean r1 = r9.mergeOneFieldFrom(r13, r0)     // Catch:{ all -> 0x0610 }
            if (r1 != 0) goto L_0x000d
            int r0 = r8.checkInitializedCount
        L_0x05fb:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x060a
            int[] r1 = r8.intArray
            r1 = r1[r0]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r1, r13, r9)
            int r0 = r0 + 1
            goto L_0x05fb
        L_0x060a:
            if (r13 == 0) goto L_0x060f
            r9.setBuilderToMessage(r10, r13)
        L_0x060f:
            return
        L_0x0610:
            r0 = move-exception
            int r1 = r8.checkInitializedCount
        L_0x0613:
            int r2 = r8.repeatedFieldOffsetStart
            if (r1 >= r2) goto L_0x0622
            int[] r2 = r8.intArray
            r2 = r2[r1]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r2, r13, r9)
            int r1 = r1 + 1
            goto L_0x0613
        L_0x0622:
            if (r13 == 0) goto L_0x0627
            r9.setBuilderToMessage(r10, r13)
        L_0x0627:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    private final <K, V> void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private void mergeMessage(T t, T t2, int i) {
        long offset = offset(typeAndOffsetAt(i));
        if (isFieldPresent(t2, i)) {
            Object object = UnsafeUtil.getObject((Object) t, offset);
            Object object2 = UnsafeUtil.getObject((Object) t2, offset);
            if (object != null && object2 != null) {
                UnsafeUtil.putObject((Object) t, offset, Internal.mergeMessage(object, object2));
                setFieldPresent(t, i);
            } else if (object2 != null) {
                UnsafeUtil.putObject((Object) t, offset, object2);
                setFieldPresent(t, i);
            }
        }
    }

    private void mergeOneofMessage(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        int numberAt = numberAt(i);
        long offset = offset(typeAndOffsetAt);
        if (isOneofPresent(t2, numberAt, i)) {
            Object object = UnsafeUtil.getObject((Object) t, offset);
            Object object2 = UnsafeUtil.getObject((Object) t2, offset);
            if (object != null && object2 != null) {
                UnsafeUtil.putObject((Object) t, offset, Internal.mergeMessage(object, object2));
                setOneofPresent(t, numberAt, i);
            } else if (object2 != null) {
                UnsafeUtil.putObject((Object) t, offset, object2);
                setOneofPresent(t, numberAt, i);
            }
        }
    }

    private void mergeSingleField(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putDouble((Object) t, offset, UnsafeUtil.getDouble((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putFloat((Object) t, offset, UnsafeUtil.getFloat((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putBoolean((Object) t, offset, UnsafeUtil.getBoolean((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 9:
                mergeMessage(t, t2, i);
                return;
            case 10:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 17:
                mergeMessage(t, t2, i);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(t, t2, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t, t2, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(t, t2, i);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(t, t2, i);
                return;
            default:
                return;
        }
    }

    static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        return messageInfo instanceof RawMessageInfo ? newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2) : newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        int i;
        int i2;
        int i3;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            i2 = 0;
            i = 0;
        } else {
            i2 = fields[0].getFieldNumber();
            i = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[(length * 3)];
        Object[] objArr = new Object[(length * 2)];
        int i4 = 0;
        int i5 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i4++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i5++;
            }
        }
        int[] iArr2 = null;
        int[] iArr3 = i4 > 0 ? new int[i4] : null;
        if (i5 > 0) {
            iArr2 = new int[i5];
        }
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i6 < fields.length) {
            FieldInfo fieldInfo2 = fields[i6];
            int fieldNumber = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i7, z, objArr);
            if (i8 < checkInitialized.length && checkInitialized[i8] == fieldNumber) {
                checkInitialized[i8] = i7;
                i8++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr3[i9] = i7;
                i9++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i3 = i7;
                iArr2[i10] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i10++;
                i6++;
                i7 = i3 + 3;
            }
            i3 = i7;
            i6++;
            i7 = i3 + 3;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[(checkInitialized.length + iArr3.length + iArr2.length)];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length, iArr3.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length + iArr3.length, iArr2.length);
        return new MessageSchema(iArr, objArr, i2, i, structuralMessageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr3.length, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        int i;
        int i2;
        char c;
        char c2;
        char c3;
        char c4;
        int[] iArr;
        int i3;
        char c5;
        char c6;
        int i4;
        int i5;
        String str;
        int i6;
        char c7;
        int i7;
        int i8;
        Class<?> cls;
        int i9;
        int i10;
        char c8;
        Field field;
        char charAt;
        int i11;
        int i12;
        Field field2;
        Field field3;
        int i13;
        char charAt2;
        int i14;
        char charAt3;
        int i15;
        char charAt4;
        int i16;
        char c9;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        char charAt5;
        int i22;
        char charAt6;
        int i23;
        char charAt7;
        char charAt8;
        char charAt9;
        char charAt10;
        char charAt11;
        char charAt12;
        char charAt13;
        char charAt14;
        boolean z = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
        int length = stringInfo.length();
        char charAt15 = stringInfo.charAt(0);
        char c10 = 55296;
        if (charAt15 >= 55296) {
            char c11 = charAt15 & 8191;
            int i24 = 1;
            int i25 = 13;
            while (true) {
                i = i24 + 1;
                charAt14 = stringInfo.charAt(i24);
                if (charAt14 < 55296) {
                    break;
                }
                c11 |= (charAt14 & 8191) << i25;
                i25 += 13;
                i24 = i;
            }
            charAt15 = (charAt14 << i25) | c11;
        } else {
            i = 1;
        }
        int i26 = i + 1;
        char charAt16 = stringInfo.charAt(i);
        if (charAt16 >= 55296) {
            char c12 = charAt16 & 8191;
            int i27 = 13;
            while (true) {
                i2 = i26 + 1;
                charAt13 = stringInfo.charAt(i26);
                if (charAt13 < 55296) {
                    break;
                }
                c12 |= (charAt13 & 8191) << i27;
                i27 += 13;
                i26 = i2;
            }
            charAt16 = c12 | (charAt13 << i27);
        } else {
            i2 = i26;
        }
        if (charAt16 == 0) {
            c5 = 0;
            i3 = 0;
            c4 = 0;
            c3 = 0;
            c2 = 0;
            c = 0;
            iArr = EMPTY_INT_ARRAY;
            c6 = 0;
        } else {
            int i28 = i2 + 1;
            char charAt17 = stringInfo.charAt(i2);
            if (charAt17 >= 55296) {
                char c13 = charAt17 & 8191;
                int i29 = 13;
                while (true) {
                    i16 = i28 + 1;
                    charAt12 = stringInfo.charAt(i28);
                    if (charAt12 < 55296) {
                        break;
                    }
                    c13 |= (charAt12 & 8191) << i29;
                    i29 += 13;
                    i28 = i16;
                }
                c9 = (charAt12 << i29) | c13;
            } else {
                i16 = i28;
                c9 = charAt17;
            }
            int i30 = i16 + 1;
            char charAt18 = stringInfo.charAt(i16);
            if (charAt18 >= 55296) {
                char c14 = charAt18 & 8191;
                int i31 = 13;
                while (true) {
                    i17 = i30 + 1;
                    charAt11 = stringInfo.charAt(i30);
                    if (charAt11 < 55296) {
                        break;
                    }
                    c14 |= (charAt11 & 8191) << i31;
                    i31 += 13;
                    i30 = i17;
                }
                charAt18 = c14 | (charAt11 << i31);
            } else {
                i17 = i30;
            }
            int i32 = i17 + 1;
            char charAt19 = stringInfo.charAt(i17);
            if (charAt19 >= 55296) {
                char c15 = charAt19 & 8191;
                int i33 = 13;
                while (true) {
                    i18 = i32 + 1;
                    charAt10 = stringInfo.charAt(i32);
                    if (charAt10 < 55296) {
                        break;
                    }
                    c15 |= (charAt10 & 8191) << i33;
                    i33 += 13;
                    i32 = i18;
                }
                charAt19 = (charAt10 << i33) | c15;
            } else {
                i18 = i32;
            }
            int i34 = i18 + 1;
            char charAt20 = stringInfo.charAt(i18);
            if (charAt20 >= 55296) {
                char c16 = charAt20 & 8191;
                int i35 = 13;
                while (true) {
                    i19 = i34 + 1;
                    charAt9 = stringInfo.charAt(i34);
                    if (charAt9 < 55296) {
                        break;
                    }
                    c16 |= (charAt9 & 8191) << i35;
                    i35 += 13;
                    i34 = i19;
                }
                charAt20 = (charAt9 << i35) | c16;
            } else {
                i19 = i34;
            }
            int i36 = i19 + 1;
            c4 = stringInfo.charAt(i19);
            if (c4 >= 55296) {
                char c17 = c4 & 8191;
                int i37 = 13;
                while (true) {
                    i20 = i36 + 1;
                    charAt8 = stringInfo.charAt(i36);
                    if (charAt8 < 55296) {
                        break;
                    }
                    c17 |= (charAt8 & 8191) << i37;
                    i37 += 13;
                    i36 = i20;
                }
                c4 = (charAt8 << i37) | c17;
            } else {
                i20 = i36;
            }
            int i38 = i20 + 1;
            c3 = stringInfo.charAt(i20);
            if (c3 >= 55296) {
                char c18 = c3 & 8191;
                int i39 = 13;
                while (true) {
                    i23 = i38 + 1;
                    charAt7 = stringInfo.charAt(i38);
                    if (charAt7 < 55296) {
                        break;
                    }
                    c18 |= (charAt7 & 8191) << i39;
                    i39 += 13;
                    i38 = i23;
                }
                c3 = (charAt7 << i39) | c18;
                i38 = i23;
            }
            int i40 = i38 + 1;
            char charAt21 = stringInfo.charAt(i38);
            if (charAt21 >= 55296) {
                char c19 = charAt21 & 8191;
                int i41 = 13;
                while (true) {
                    i22 = i40 + 1;
                    charAt6 = stringInfo.charAt(i40);
                    if (charAt6 < 55296) {
                        break;
                    }
                    c19 |= (charAt6 & 8191) << i41;
                    i41 += 13;
                    i40 = i22;
                }
                charAt21 = c19 | (charAt6 << i41);
                i40 = i22;
            }
            int i42 = i40 + 1;
            c2 = stringInfo.charAt(i40);
            if (c2 >= 55296) {
                int i43 = 13;
                int i44 = i42;
                char c20 = c2 & 8191;
                int i45 = i44;
                while (true) {
                    i21 = i45 + 1;
                    charAt5 = stringInfo.charAt(i45);
                    if (charAt5 < 55296) {
                        break;
                    }
                    c20 |= (charAt5 & 8191) << i43;
                    i43 += 13;
                    i45 = i21;
                }
                c2 = c20 | (charAt5 << i43);
                i42 = i21;
            }
            i3 = (c9 * 2) + charAt18;
            int i46 = i42;
            c = c9;
            c6 = charAt19;
            i2 = i46;
            char c21 = charAt20;
            iArr = new int[(c2 + c3 + charAt21)];
            c5 = c21;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects2 = rawMessageInfo.getObjects();
        Class<?> cls2 = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr2 = new int[(c4 * 3)];
        Object[] objArr = new Object[(c4 * 2)];
        int i47 = c3 + c2;
        int i48 = i47;
        char c22 = c2;
        int i49 = 0;
        int i50 = 0;
        while (i2 < length) {
            int i51 = i2 + 1;
            int charAt22 = stringInfo.charAt(i2);
            if (charAt22 >= c10) {
                int i52 = 13;
                int i53 = i51;
                int i54 = charAt22 & 8191;
                int i55 = i53;
                while (true) {
                    i15 = i55 + 1;
                    charAt4 = stringInfo.charAt(i55);
                    if (charAt4 < c10) {
                        break;
                    }
                    i54 |= (charAt4 & 8191) << i52;
                    i52 += 13;
                    i55 = i15;
                }
                charAt22 = i54 | (charAt4 << i52);
                i4 = i15;
            } else {
                i4 = i51;
            }
            int i56 = i4 + 1;
            char charAt23 = stringInfo.charAt(i4);
            int i57 = length;
            char c23 = 55296;
            if (charAt23 >= 55296) {
                int i58 = 13;
                int i59 = i56;
                char c24 = charAt23 & 8191;
                int i60 = i59;
                while (true) {
                    i14 = i60 + 1;
                    charAt3 = stringInfo.charAt(i60);
                    if (charAt3 < c23) {
                        break;
                    }
                    c24 |= (charAt3 & 8191) << i58;
                    i58 += 13;
                    i60 = i14;
                    c23 = 55296;
                }
                charAt23 = c24 | (charAt3 << i58);
                i5 = i14;
            } else {
                i5 = i56;
            }
            int i61 = i47;
            char c25 = charAt23 & 255;
            char c26 = c2;
            if ((charAt23 & 1024) != 0) {
                iArr[i49] = i50;
                i49++;
            }
            boolean z2 = z;
            if (c25 >= '3') {
                int i62 = i5 + 1;
                char charAt24 = stringInfo.charAt(i5);
                char c27 = 55296;
                if (charAt24 >= 55296) {
                    char c28 = charAt24 & 8191;
                    int i63 = 13;
                    while (true) {
                        i13 = i62 + 1;
                        charAt2 = stringInfo.charAt(i62);
                        if (charAt2 < c27) {
                            break;
                        }
                        c28 |= (charAt2 & 8191) << i63;
                        i63 += 13;
                        i62 = i13;
                        c27 = 55296;
                    }
                    charAt24 = c28 | (charAt2 << i63);
                    i62 = i13;
                }
                int i64 = c25 - '3';
                int i65 = i62;
                if (i64 == 9 || i64 == 17) {
                    i12 = i3 + 1;
                    objArr[((i50 / 3) * 2) + 1] = objects2[i3];
                } else if (i64 == 12 && (charAt15 & 1) == 1) {
                    i12 = i3 + 1;
                    objArr[((i50 / 3) * 2) + 1] = objects2[i3];
                } else {
                    i12 = i3;
                }
                int i66 = charAt24 * 2;
                Object obj = objects2[i66];
                if (obj instanceof Field) {
                    field2 = (Field) obj;
                } else {
                    field2 = reflectField(cls2, (String) obj);
                    objects2[i66] = field2;
                }
                int i67 = i66 + 1;
                Object obj2 = objects2[i67];
                int objectFieldOffset = (int) unsafe.objectFieldOffset(field2);
                if (obj2 instanceof Field) {
                    field3 = (Field) obj2;
                } else {
                    field3 = reflectField(cls2, (String) obj2);
                    objects2[i67] = field3;
                }
                int objectFieldOffset2 = (int) unsafe.objectFieldOffset(field3);
                str = stringInfo;
                c8 = charAt15;
                i7 = i12;
                i8 = objectFieldOffset;
                i6 = i65;
                i9 = objectFieldOffset2;
                c7 = c5;
                i10 = 0;
                cls = cls2;
            } else {
                i7 = i3 + 1;
                Field reflectField = reflectField(cls2, (String) objects2[i3]);
                if (c25 == 9 || c25 == 17) {
                    c7 = c5;
                    objArr[((i50 / 3) * 2) + 1] = reflectField.getType();
                } else {
                    if (c25 == 27 || c25 == '1') {
                        c7 = c5;
                        i11 = i7 + 1;
                        objArr[((i50 / 3) * 2) + 1] = objects2[i7];
                    } else if (c25 == 12 || c25 == 30 || c25 == ',') {
                        c7 = c5;
                        if ((charAt15 & 1) == 1) {
                            i11 = i7 + 1;
                            objArr[((i50 / 3) * 2) + 1] = objects2[i7];
                        }
                    } else if (c25 == '2') {
                        int i68 = c22 + 1;
                        iArr[c22] = i50;
                        int i69 = (i50 / 3) * 2;
                        int i70 = i7 + 1;
                        objArr[i69] = objects2[i7];
                        if ((charAt23 & 2048) != 0) {
                            i7 = i70 + 1;
                            objArr[i69 + 1] = objects2[i70];
                            c7 = c5;
                            c22 = i68;
                        } else {
                            c22 = i68;
                            i7 = i70;
                            c7 = c5;
                        }
                    } else {
                        c7 = c5;
                    }
                    i7 = i11;
                }
                i8 = (int) unsafe.objectFieldOffset(reflectField);
                if ((charAt15 & 1) != 1 || c25 > 17) {
                    str = stringInfo;
                    c8 = charAt15;
                    cls = cls2;
                    i6 = i5;
                    i10 = 0;
                    i9 = 0;
                } else {
                    int i71 = i5 + 1;
                    char charAt25 = stringInfo.charAt(i5);
                    if (charAt25 >= 55296) {
                        char c29 = charAt25 & 8191;
                        int i72 = 13;
                        while (true) {
                            i6 = i71 + 1;
                            charAt = stringInfo.charAt(i71);
                            if (charAt < 55296) {
                                break;
                            }
                            c29 |= (charAt & 8191) << i72;
                            i72 += 13;
                            i71 = i6;
                        }
                        charAt25 = c29 | (charAt << i72);
                    } else {
                        i6 = i71;
                    }
                    int i73 = (c * 2) + (charAt25 / ' ');
                    Object obj3 = objects2[i73];
                    str = stringInfo;
                    if (obj3 instanceof Field) {
                        field = (Field) obj3;
                    } else {
                        field = reflectField(cls2, (String) obj3);
                        objects2[i73] = field;
                    }
                    c8 = charAt15;
                    cls = cls2;
                    i9 = (int) unsafe.objectFieldOffset(field);
                    i10 = charAt25 % ' ';
                }
                if (c25 >= 18 && c25 <= '1') {
                    iArr[i48] = i8;
                    i48++;
                }
            }
            int i74 = i50 + 1;
            iArr2[i50] = charAt22;
            int i75 = i74 + 1;
            iArr2[i74] = ((charAt23 & 256) != 0 ? REQUIRED_MASK : 0) | ((charAt23 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | (c25 << 20) | i8;
            i50 = i75 + 1;
            iArr2[i75] = (i10 << 20) | i9;
            charAt15 = c8;
            cls2 = cls;
            i3 = i7;
            i47 = i61;
            length = i57;
            c2 = c26;
            c5 = c7;
            i2 = i6;
            z = z2;
            stringInfo = str;
            c10 = 55296;
        }
        char c30 = c5;
        boolean z3 = z;
        int i76 = i47;
        return new MessageSchema(iArr2, objArr, c6, c5, rawMessageInfo.getDefaultInstance(), z, false, iArr, c2, i47, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private static long offset(int i) {
        return (long) (i & OFFSET_MASK);
    }

    private static <T> boolean oneofBooleanAt(T t, long j) {
        return ((Boolean) UnsafeUtil.getObject((Object) t, j)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T t, long j) {
        return ((Double) UnsafeUtil.getObject((Object) t, j)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t, long j) {
        return ((Float) UnsafeUtil.getObject((Object) t, j)).floatValue();
    }

    private static <T> int oneofIntAt(T t, long j) {
        return ((Integer) UnsafeUtil.getObject((Object) t, j)).intValue();
    }

    private static <T> long oneofLongAt(T t, long j) {
        return ((Long) UnsafeUtil.getObject((Object) t, j)).longValue();
    }

    private <K, V> int parseMapField(T t, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) throws IOException {
        T t2 = t;
        long j2 = j;
        Unsafe unsafe = UNSAFE;
        int i4 = i3;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(t, j2);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(t, j2, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) throws IOException {
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe = UNSAFE;
        long j3 = (long) (this.buffer[i13 + 2] & OFFSET_MASK);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                    int i14 = i9 + 8;
                    unsafe.putInt(t2, j3, i11);
                    return i14;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                    int i15 = i9 + 4;
                    unsafe.putInt(t2, j3, i11);
                    return i15;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Long.valueOf(registers2.long1));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint64;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Integer.valueOf(registers2.int1));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint32;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                    int i16 = i9 + 8;
                    unsafe.putInt(t2, j3, i11);
                    return i16;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                    int i17 = i9 + 4;
                    unsafe.putInt(t2, j3, i11);
                    return i17;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Boolean.valueOf(registers2.long1 != 0));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint642;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    int i18 = registers2.int1;
                    if (i18 == 0) {
                        unsafe.putObject(t2, j2, "");
                    } else if ((i6 & ENFORCE_UTF8_MASK) == 0 || Utf8.isValidUtf8(bArr2, decodeVarint322, decodeVarint322 + i18)) {
                        unsafe.putObject(t2, j2, new String(bArr2, decodeVarint322, i18, Internal.UTF_8));
                        decodeVarint322 += i18;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint322;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int decodeMessageField = ArrayDecoders.decodeMessageField(getMessageFieldSchema(i13), bArr2, i9, i2, registers2);
                    Object object = unsafe.getInt(t2, j3) == i11 ? unsafe.getObject(t2, j2) : null;
                    if (object == null) {
                        unsafe.putObject(t2, j2, registers2.object1);
                    } else {
                        unsafe.putObject(t2, j2, Internal.mergeMessage(object, registers2.object1));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return decodeMessageField;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, registers2.object1);
                    unsafe.putInt(t2, j3, i11);
                    return decodeBytes;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    int i19 = registers2.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i13);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i19)) {
                        unsafe.putObject(t2, j2, Integer.valueOf(i19));
                        unsafe.putInt(t2, j3, i11);
                    } else {
                        getMutableUnknownFields(t).storeField(i10, Long.valueOf((long) i19));
                    }
                    return decodeVarint323;
                }
                break;
            case 66:
                if (i12 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Integer.valueOf(CodedInputStream.decodeZigZag32(registers2.int1)));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint324;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Long.valueOf(CodedInputStream.decodeZigZag64(registers2.long1)));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint643;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int decodeGroupField = ArrayDecoders.decodeGroupField(getMessageFieldSchema(i13), bArr, i, i2, (i10 & -8) | 4, registers);
                    Object object2 = unsafe.getInt(t2, j3) == i11 ? unsafe.getObject(t2, j2) : null;
                    if (object2 == null) {
                        unsafe.putObject(t2, j2, registers2.object1);
                    } else {
                        unsafe.putObject(t2, j2, Internal.mergeMessage(object2, registers2.object1));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return decodeGroupField;
                }
                break;
        }
        return i9;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0107, code lost:
        r2 = r4;
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0139, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0155, code lost:
        r0 = r8 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0157, code lost:
        r1 = r7;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x015c, code lost:
        r24 = r7;
        r15 = r8;
        r18 = r9;
        r19 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01de, code lost:
        if (r0 != r15) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x020c, code lost:
        if (r0 != r15) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x022b, code lost:
        if (r0 != r15) goto L_0x023f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    private int parseProto3Message(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Unsafe unsafe;
        int i8;
        int decodeUnknownField;
        int i9;
        int i10;
        int i11;
        int i12;
        MessageSchema messageSchema = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i13 = i2;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i14 = -1;
        int i15 = i;
        int i16 = -1;
        int i17 = 0;
        while (i15 < i13) {
            int i18 = i15 + 1;
            byte b = bArr2[i15];
            if (b < 0) {
                i4 = ArrayDecoders.decodeVarint32(b, bArr2, i18, registers2);
                i3 = registers2.int1;
            } else {
                i3 = b;
                i4 = i18;
            }
            int i19 = i3 >>> 3;
            int i20 = i3 & 7;
            int positionForFieldNumber = i19 > i16 ? messageSchema.positionForFieldNumber(i19, i17 / 3) : messageSchema.positionForFieldNumber(i19);
            if (positionForFieldNumber == i14) {
                i6 = i19;
                i8 = i4;
                unsafe = unsafe2;
                i5 = i14;
                i7 = 0;
            } else {
                int i21 = messageSchema.buffer[positionForFieldNumber + 1];
                int type = type(i21);
                long offset = offset(i21);
                if (type <= 17) {
                    boolean z = true;
                    switch (type) {
                        case 0:
                            long j = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 1) {
                                UnsafeUtil.putDouble((Object) t2, j, ArrayDecoders.decodeDouble(bArr2, i4));
                                break;
                            }
                            break;
                        case 1:
                            long j2 = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 5) {
                                UnsafeUtil.putFloat((Object) t2, j2, ArrayDecoders.decodeFloat(bArr2, i4));
                                i11 = i4 + 4;
                                break;
                            }
                            break;
                        case 2:
                        case 3:
                            long j3 = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 0) {
                                i12 = ArrayDecoders.decodeVarint64(bArr2, i4, registers2);
                                unsafe2.putLong(t, j3, registers2.long1);
                                break;
                            }
                            break;
                        case 4:
                        case 11:
                            long j4 = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 0) {
                                i11 = ArrayDecoders.decodeVarint32(bArr2, i4, registers2);
                                unsafe2.putInt(t2, j4, registers2.int1);
                                break;
                            }
                            break;
                        case 5:
                        case 14:
                            long j5 = offset;
                            if (i20 == 1) {
                                i10 = positionForFieldNumber;
                                unsafe2.putLong(t, j5, ArrayDecoders.decodeFixed64(bArr2, i4));
                                break;
                            }
                            break;
                        case 6:
                        case 13:
                            long j6 = offset;
                            if (i20 == 5) {
                                unsafe2.putInt(t2, j6, ArrayDecoders.decodeFixed32(bArr2, i4));
                                i15 = i4 + 4;
                                break;
                            }
                            break;
                        case 7:
                            long j7 = offset;
                            if (i20 == 0) {
                                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i4, registers2);
                                if (registers2.long1 == 0) {
                                    z = false;
                                }
                                UnsafeUtil.putBoolean((Object) t2, j7, z);
                                i15 = decodeVarint64;
                                break;
                            }
                            break;
                        case 8:
                            long j8 = offset;
                            if (i20 == 2) {
                                i15 = (ENFORCE_UTF8_MASK & i21) == 0 ? ArrayDecoders.decodeString(bArr2, i4, registers2) : ArrayDecoders.decodeStringRequireUtf8(bArr2, i4, registers2);
                                unsafe2.putObject(t2, j8, registers2.object1);
                                break;
                            }
                            break;
                        case 9:
                            long j9 = offset;
                            if (i20 == 2) {
                                i15 = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(positionForFieldNumber), bArr2, i4, i13, registers2);
                                Object object = unsafe2.getObject(t2, j9);
                                if (object != null) {
                                    unsafe2.putObject(t2, j9, Internal.mergeMessage(object, registers2.object1));
                                    break;
                                } else {
                                    unsafe2.putObject(t2, j9, registers2.object1);
                                    break;
                                }
                            }
                            break;
                        case 10:
                            long j10 = offset;
                            if (i20 == 2) {
                                i15 = ArrayDecoders.decodeBytes(bArr2, i4, registers2);
                                unsafe2.putObject(t2, j10, registers2.object1);
                                break;
                            }
                            break;
                        case 12:
                            long j11 = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 0) {
                                i11 = ArrayDecoders.decodeVarint32(bArr2, i4, registers2);
                                unsafe2.putInt(t2, j11, registers2.int1);
                                break;
                            }
                            break;
                        case 15:
                            long j12 = offset;
                            i10 = positionForFieldNumber;
                            if (i20 == 0) {
                                i11 = ArrayDecoders.decodeVarint32(bArr2, i4, registers2);
                                unsafe2.putInt(t2, j12, CodedInputStream.decodeZigZag32(registers2.int1));
                                break;
                            }
                            break;
                        case 16:
                            if (i20 == 0) {
                                i12 = ArrayDecoders.decodeVarint64(bArr2, i4, registers2);
                                i10 = positionForFieldNumber;
                                unsafe2.putLong(t, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                break;
                            }
                            break;
                    }
                } else if (type == 27) {
                    if (i20 == 2) {
                        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t2, offset);
                        if (!protobufList.isModifiable()) {
                            int size = protobufList.size();
                            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
                            unsafe2.putObject(t2, offset, protobufList);
                        }
                        i15 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(positionForFieldNumber), i3, bArr, i4, i2, protobufList, registers);
                        i16 = i19;
                        i17 = positionForFieldNumber;
                    }
                    i7 = positionForFieldNumber;
                    i6 = i19;
                    i9 = i4;
                    unsafe = unsafe2;
                    i5 = -1;
                    i8 = i9;
                } else {
                    i7 = positionForFieldNumber;
                    if (type <= 49) {
                        i6 = i19;
                        int i22 = i4;
                        unsafe = unsafe2;
                        i5 = -1;
                        decodeUnknownField = parseRepeatedField(t, bArr, i4, i2, i3, i19, i20, i7, (long) i21, type, offset, registers);
                    } else {
                        long j13 = offset;
                        int i23 = i20;
                        i6 = i19;
                        i9 = i4;
                        unsafe = unsafe2;
                        i5 = -1;
                        int i24 = type;
                        if (i24 == 50) {
                            if (i23 == 2) {
                                decodeUnknownField = parseMapField(t, bArr, i9, i2, i7, j13, registers);
                            }
                            i8 = i9;
                        } else {
                            decodeUnknownField = parseOneofField(t, bArr, i9, i2, i3, i6, i23, i21, i24, j13, i7, registers);
                        }
                    }
                    i8 = decodeUnknownField;
                }
                i14 = -1;
            }
            decodeUnknownField = ArrayDecoders.decodeUnknownField(i3, bArr, i8, i2, getMutableUnknownFields(t), registers);
            messageSchema = this;
            t2 = t;
            bArr2 = bArr;
            i13 = i2;
            registers2 = registers;
            unsafe2 = unsafe;
            i17 = i7;
            i16 = i6;
            i14 = i5;
        }
        if (i15 == i13) {
            return i15;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    private int parseRepeatedField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) throws IOException {
        int i8;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i5;
        int i11 = i6;
        long j3 = j2;
        ArrayDecoders.Registers registers2 = registers;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) UNSAFE.getObject(t, j3);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(t, j3, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i, protobufList, registers2);
                }
                if (i10 == 1) {
                    return ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 19:
            case 36:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i, protobufList, registers2);
                }
                if (i10 == 5) {
                    return ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i, protobufList, registers2);
                }
                if (i10 == 1) {
                    return ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i, protobufList, registers2);
                }
                if (i10 == 5) {
                    return ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 25:
            case 42:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 26:
                if (i10 == 2) {
                    return (j & 536870912) == 0 ? ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufList, registers) : ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 27:
                if (i10 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i11), i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 28:
                if (i10 == 2) {
                    return ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 30:
            case 44:
                if (i10 == 2) {
                    i8 = ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers2);
                } else if (i10 == 0) {
                    i8 = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t2;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(i4, (List<Integer>) protobufList, getEnumFieldVerifier(i11), unknownFieldSetLite, this.unknownFieldSchema);
                if (unknownFieldSetLite2 != null) {
                    generatedMessageLite.unknownFields = unknownFieldSetLite2;
                }
                return i8;
            case 33:
            case 47:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 34:
            case 48:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 49:
                if (i10 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i11), i3, bArr, i, i2, protobufList, registers);
                }
                break;
        }
        return i9;
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private <E> void readGroupList(Object obj, long j, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private <E> void readMessageList(Object obj, int i, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private static Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private void setFieldPresent(T t, int i) {
        if (!this.proto3) {
            int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
            long j = (long) (presenceMaskAndOffsetAt & OFFSET_MASK);
            UnsafeUtil.putInt((Object) t, j, UnsafeUtil.getInt((Object) t, j) | (1 << (presenceMaskAndOffsetAt >>> 20)));
        }
    }

    private void setOneofPresent(T t, int i, int i2) {
        UnsafeUtil.putInt((Object) t, (long) (presenceMaskAndOffsetAt(i2) & OFFSET_MASK), i);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i, boolean z, Object[] objArr) {
        int i2;
        int i3;
        int i4;
        int i5;
        OneofInfo oneof = fieldInfo.getOneof();
        int i6 = 0;
        if (oneof != null) {
            i4 = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            int objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            i3 = fieldInfo.getType().id() + 51;
            i2 = objectFieldOffset;
        } else {
            FieldType type = fieldInfo.getType();
            i4 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            i3 = type.id();
            if (!z && !type.isList() && !type.isMap()) {
                i2 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getPresenceField());
                i5 = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
                iArr[i] = fieldInfo.getFieldNumber();
                int i7 = i + 1;
                if (fieldInfo.isEnforceUtf8()) {
                }
                if (fieldInfo.isRequired()) {
                }
                iArr[i7] = i6 | r5 | (i3 << 20) | i4;
                iArr[i + 2] = i2 | (i5 << 20);
                Class<?> messageFieldClass = fieldInfo.getMessageFieldClass();
                if (fieldInfo.getMapDefaultEntry() != null) {
                }
            } else if (fieldInfo.getCachedSizeField() == null) {
                i2 = 0;
                i5 = 0;
                iArr[i] = fieldInfo.getFieldNumber();
                int i72 = i + 1;
                int i8 = fieldInfo.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0;
                if (fieldInfo.isRequired()) {
                    i6 = REQUIRED_MASK;
                }
                iArr[i72] = i6 | i8 | (i3 << 20) | i4;
                iArr[i + 2] = i2 | (i5 << 20);
                Class<?> messageFieldClass2 = fieldInfo.getMessageFieldClass();
                if (fieldInfo.getMapDefaultEntry() != null) {
                    int i9 = (i / 3) * 2;
                    objArr[i9] = fieldInfo.getMapDefaultEntry();
                    if (messageFieldClass2 != null) {
                        objArr[i9 + 1] = messageFieldClass2;
                        return;
                    } else if (fieldInfo.getEnumVerifier() != null) {
                        objArr[i9 + 1] = fieldInfo.getEnumVerifier();
                        return;
                    } else {
                        return;
                    }
                } else if (messageFieldClass2 != null) {
                    objArr[((i / 3) * 2) + 1] = messageFieldClass2;
                    return;
                } else if (fieldInfo.getEnumVerifier() != null) {
                    objArr[((i / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                    return;
                } else {
                    return;
                }
            } else {
                i2 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getCachedSizeField());
            }
        }
        i5 = 0;
        iArr[i] = fieldInfo.getFieldNumber();
        int i722 = i + 1;
        if (fieldInfo.isEnforceUtf8()) {
        }
        if (fieldInfo.isRequired()) {
        }
        iArr[i722] = i6 | i8 | (i3 << 20) | i4;
        iArr[i + 2] = i2 | (i5 << 20);
        Class<?> messageFieldClass22 = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
        }
    }

    private static int type(int i) {
        return (i & FIELD_TYPE_MASK) >>> 20;
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0344, code lost:
        r14 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x051d, code lost:
        r5 = r12 + 3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0523  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    private void writeFieldsInAscendingOrderProto2(T t, Writer writer) throws IOException {
        Map.Entry entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        Map.Entry entry2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        T t2 = t;
        Writer writer2 = writer;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(t2);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = it.next();
                int i6 = -1;
                length = this.buffer.length;
                Unsafe unsafe = UNSAFE;
                entry2 = entry;
                i = 0;
                int i7 = 0;
                while (i < length) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    int type = type(typeAndOffsetAt);
                    if (this.proto3 || type > 17) {
                        i2 = i;
                        i3 = 0;
                    } else {
                        int i8 = this.buffer[i + 2];
                        int i9 = i8 & OFFSET_MASK;
                        if (i9 != i6) {
                            i2 = i;
                            i7 = unsafe.getInt(t2, (long) i9);
                            i6 = i9;
                        } else {
                            i2 = i;
                        }
                        i3 = 1 << (i8 >>> 20);
                    }
                    while (entry2 != null && this.extensionSchema.extensionNumber(entry2) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer2, entry2);
                        entry2 = it.hasNext() ? it.next() : null;
                    }
                    long offset = offset(typeAndOffsetAt);
                    switch (type) {
                        case 0:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeDouble(numberAt, doubleAt(t2, offset));
                                break;
                            }
                        case 1:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeFloat(numberAt, floatAt(t2, offset));
                                break;
                            }
                        case 2:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeInt64(numberAt, unsafe.getLong(t2, offset));
                                break;
                            }
                        case 3:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeUInt64(numberAt, unsafe.getLong(t2, offset));
                                break;
                            }
                        case 4:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeInt32(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 5:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeFixed64(numberAt, unsafe.getLong(t2, offset));
                                break;
                            }
                        case 6:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeFixed32(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 7:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeBool(numberAt, booleanAt(t2, offset));
                                break;
                            }
                        case 8:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writeString(numberAt, unsafe.getObject(t2, offset), writer2);
                                break;
                            }
                        case 9:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeMessage(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i4));
                                break;
                            }
                        case 10:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeBytes(numberAt, (ByteString) unsafe.getObject(t2, offset));
                                break;
                            }
                        case 11:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeUInt32(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 12:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeEnum(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 13:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeSFixed32(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 14:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeSFixed64(numberAt, unsafe.getLong(t2, offset));
                                break;
                            }
                        case 15:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeSInt32(numberAt, unsafe.getInt(t2, offset));
                                break;
                            }
                        case 16:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeSInt64(numberAt, unsafe.getLong(t2, offset));
                                break;
                            }
                        case 17:
                            i4 = i2;
                            if ((i3 & i7) == 0) {
                                break;
                            } else {
                                writer2.writeGroup(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i4));
                                break;
                            }
                        case 18:
                            i4 = i2;
                            SchemaUtil.writeDoubleList(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 19:
                            i4 = i2;
                            SchemaUtil.writeFloatList(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 20:
                            i4 = i2;
                            SchemaUtil.writeInt64List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 21:
                            i4 = i2;
                            SchemaUtil.writeUInt64List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 22:
                            i4 = i2;
                            SchemaUtil.writeInt32List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 23:
                            i4 = i2;
                            SchemaUtil.writeFixed64List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 24:
                            i4 = i2;
                            SchemaUtil.writeFixed32List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 25:
                            i4 = i2;
                            SchemaUtil.writeBoolList(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 26:
                            i5 = i2;
                            SchemaUtil.writeStringList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2);
                            break;
                        case 27:
                            i5 = i2;
                            SchemaUtil.writeMessageList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, getMessageFieldSchema(i5));
                            break;
                        case 28:
                            i5 = i2;
                            SchemaUtil.writeBytesList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2);
                            break;
                        case 29:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeUInt32List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 30:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeEnumList(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 31:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeSFixed32List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 32:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeSFixed64List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 33:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeSInt32List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 34:
                            i4 = i2;
                            z = false;
                            SchemaUtil.writeSInt64List(numberAt(i4), (List) unsafe.getObject(t2, offset), writer2, false);
                            break;
                        case 35:
                            i5 = i2;
                            SchemaUtil.writeDoubleList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 36:
                            i5 = i2;
                            SchemaUtil.writeFloatList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 37:
                            i5 = i2;
                            SchemaUtil.writeInt64List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 38:
                            i5 = i2;
                            SchemaUtil.writeUInt64List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 39:
                            i5 = i2;
                            SchemaUtil.writeInt32List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 40:
                            i5 = i2;
                            SchemaUtil.writeFixed64List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 41:
                            i5 = i2;
                            SchemaUtil.writeFixed32List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 42:
                            i5 = i2;
                            SchemaUtil.writeBoolList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 43:
                            i5 = i2;
                            SchemaUtil.writeUInt32List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 44:
                            i5 = i2;
                            SchemaUtil.writeEnumList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 45:
                            i5 = i2;
                            SchemaUtil.writeSFixed32List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 46:
                            i5 = i2;
                            SchemaUtil.writeSFixed64List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 47:
                            i5 = i2;
                            SchemaUtil.writeSInt32List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 48:
                            i5 = i2;
                            SchemaUtil.writeSInt64List(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, true);
                            break;
                        case 49:
                            i5 = i2;
                            SchemaUtil.writeGroupList(numberAt(i5), (List) unsafe.getObject(t2, offset), writer2, getMessageFieldSchema(i5));
                            break;
                        case 50:
                            i5 = i2;
                            writeMapHelper(writer2, numberAt, unsafe.getObject(t2, offset), i5);
                            break;
                        case 51:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeDouble(numberAt, oneofDoubleAt(t2, offset));
                                break;
                            }
                            break;
                        case 52:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeFloat(numberAt, oneofFloatAt(t2, offset));
                                break;
                            }
                            break;
                        case 53:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 54:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeUInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 55:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 56:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeFixed64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 57:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeFixed32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 58:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeBool(numberAt, oneofBooleanAt(t2, offset));
                                break;
                            }
                            break;
                        case 59:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writeString(numberAt, unsafe.getObject(t2, offset), writer2);
                                break;
                            }
                            break;
                        case 60:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeMessage(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i5));
                                break;
                            }
                            break;
                        case 61:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeBytes(numberAt, (ByteString) unsafe.getObject(t2, offset));
                                break;
                            }
                            break;
                        case 62:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeUInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 63:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeEnum(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 64:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeSFixed32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 65:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeSFixed64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 66:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeSInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 67:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeSInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 68:
                            i5 = i2;
                            if (isOneofPresent(t2, numberAt, i5)) {
                                writer2.writeGroup(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i5));
                                break;
                            }
                            break;
                        default:
                            i5 = i2;
                            break;
                    }
                }
                while (entry2 != null) {
                    this.extensionSchema.serializeExtension(writer2, entry2);
                    entry2 = it.hasNext() ? it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer2);
            }
        }
        it = null;
        entry = null;
        int i62 = -1;
        length = this.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        entry2 = entry;
        i = 0;
        int i72 = 0;
        while (i < length) {
        }
        while (entry2 != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:161:0x0589  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    private void writeFieldsInAscendingOrderProto3(T t, Writer writer) throws IOException {
        Map.Entry entry;
        Iterator it;
        int length;
        Map.Entry entry2;
        int i;
        if (this.hasExtensions) {
            FieldSet extensions = this.extensionSchema.getExtensions(t);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                entry2 = entry;
                for (i = 0; i < length; i += 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    while (entry2 != null && this.extensionSchema.extensionNumber(entry2) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry2);
                        entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, doubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 1:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, floatAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 2:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 3:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 4:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 5:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 6:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 7:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeBool(numberAt, booleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 8:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writeString(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                                break;
                            }
                        case 9:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            }
                        case 10:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 11:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 12:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 13:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 14:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 15:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 16:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 17:
                            if (!isFieldPresent(t, i)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), i);
                            break;
                        case 51:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, oneofDoubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 52:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, oneofFloatAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 53:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 54:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 55:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 56:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 57:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 58:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeBool(numberAt, oneofBooleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 59:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writeString(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                                break;
                            }
                        case 60:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            }
                        case 61:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 62:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 63:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 64:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 65:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 66:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 67:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(t, numberAt, i)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            }
                    }
                }
                while (entry2 != null) {
                    this.extensionSchema.serializeExtension(writer, entry2);
                    entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        entry2 = entry;
        while (i < length) {
        }
        while (entry2 != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:161:0x058e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    private void writeFieldsInDescendingOrder(T t, Writer writer) throws IOException {
        Map.Entry entry;
        Iterator it;
        int length;
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
        if (this.hasExtensions) {
            FieldSet extensions = this.extensionSchema.getExtensions(t);
            if (!extensions.isEmpty()) {
                it = extensions.descendingIterator();
                entry = (Map.Entry) it.next();
                for (length = this.buffer.length - 3; length >= 0; length -= 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(length);
                    int numberAt = numberAt(length);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) > numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, doubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 1:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, floatAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 2:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 3:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 4:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 5:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 6:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 7:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeBool(numberAt, booleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 8:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writeString(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                                break;
                            }
                        case 9:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            }
                        case 10:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 11:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 12:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 13:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 14:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 15:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 16:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 17:
                            if (!isFieldPresent(t, length)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), length);
                            break;
                        case 51:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, oneofDoubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 52:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, oneofFloatAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 53:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 54:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 55:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 56:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 57:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 58:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeBool(numberAt, oneofBooleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 59:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writeString(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), writer);
                                break;
                            }
                        case 60:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            }
                        case 61:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 62:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 63:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 64:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 65:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 66:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 67:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(t, numberAt, length)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject((Object) t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
            }
        }
        it = null;
        entry = null;
        while (length >= 0) {
        }
        while (entry != null) {
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema2, T t, Writer writer) throws IOException {
        unknownFieldSchema2.writeTo(unknownFieldSchema2.getFromMessage(t), writer);
    }

    public boolean equals(T t, T t2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(t, t2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getSchemaSize() {
        return this.buffer.length * 3;
    }

    public int getSerializedSize(T t) {
        return this.proto3 ? getSerializedSizeProto3(t) : getSerializedSizeProto2(t);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c1, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0225, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0226, code lost:
        r1 = r1 + 3;
     */
    public int hashCode(T t) {
        int i;
        int i2;
        int length = this.buffer.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int typeAndOffsetAt = typeAndOffsetAt(i3);
            int numberAt = numberAt(i3);
            long offset = offset(typeAndOffsetAt);
            int i5 = 37;
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i2 = i4 * 53;
                    i = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble((Object) t, offset)));
                case 1:
                    i2 = i4 * 53;
                    i = Float.floatToIntBits(UnsafeUtil.getFloat((Object) t, offset));
                case 2:
                    i2 = i4 * 53;
                    i = Internal.hashLong(UnsafeUtil.getLong((Object) t, offset));
                case 3:
                    i2 = i4 * 53;
                    i = Internal.hashLong(UnsafeUtil.getLong((Object) t, offset));
                case 4:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 5:
                    i2 = i4 * 53;
                    i = Internal.hashLong(UnsafeUtil.getLong((Object) t, offset));
                case 6:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 7:
                    i2 = i4 * 53;
                    i = Internal.hashBoolean(UnsafeUtil.getBoolean((Object) t, offset));
                case 8:
                    i2 = i4 * 53;
                    i = ((String) UnsafeUtil.getObject((Object) t, offset)).hashCode();
                case 9:
                    Object object = UnsafeUtil.getObject((Object) t, offset);
                    if (object != null) {
                        i5 = object.hashCode();
                        break;
                    }
                    break;
                case 10:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                case 11:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 12:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 13:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 14:
                    i2 = i4 * 53;
                    i = Internal.hashLong(UnsafeUtil.getLong((Object) t, offset));
                case 15:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getInt((Object) t, offset);
                case 16:
                    i2 = i4 * 53;
                    i = Internal.hashLong(UnsafeUtil.getLong((Object) t, offset));
                case 17:
                    Object object2 = UnsafeUtil.getObject((Object) t, offset);
                    if (object2 != null) {
                        i5 = object2.hashCode();
                        break;
                    }
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                case 50:
                    i2 = i4 * 53;
                    i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                case 51:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(t, offset)));
                    }
                case 52:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Float.floatToIntBits(oneofFloatAt(t, offset));
                    }
                case 53:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(oneofLongAt(t, offset));
                    }
                case 54:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(oneofLongAt(t, offset));
                    }
                case 55:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 56:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(oneofLongAt(t, offset));
                    }
                case 57:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 58:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashBoolean(oneofBooleanAt(t, offset));
                    }
                case 59:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = ((String) UnsafeUtil.getObject((Object) t, offset)).hashCode();
                    }
                case 60:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                    }
                case 61:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                    }
                case 62:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 63:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 64:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 65:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(oneofLongAt(t, offset));
                    }
                case 66:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = oneofIntAt(t, offset);
                    }
                case 67:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = Internal.hashLong(oneofLongAt(t, offset));
                    }
                case 68:
                    if (!isOneofPresent(t, numberAt, i3)) {
                        break;
                    } else {
                        i2 = i4 * 53;
                        i = UnsafeUtil.getObject((Object) t, offset).hashCode();
                    }
            }
        }
        int hashCode = (i4 * 53) + this.unknownFieldSchema.getFromMessage(t).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(t).hashCode() : hashCode;
    }

    public final boolean isInitialized(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        for (int i4 = 0; i4 < this.checkInitializedCount; i4++) {
            int i5 = this.intArray[i4];
            int numberAt = numberAt(i5);
            int typeAndOffsetAt = typeAndOffsetAt(i5);
            if (!this.proto3) {
                int i6 = this.buffer[i5 + 2];
                int i7 = OFFSET_MASK & i6;
                i = 1 << (i6 >>> 20);
                if (i7 != i3) {
                    i2 = UNSAFE.getInt(t, (long) i7);
                    i3 = i7;
                }
            } else {
                i = 0;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t, i5, i2, i)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type != 9 && type != 17) {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt, i5) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i5))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t, typeAndOffsetAt, i5)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t, typeAndOffsetAt, i5)) {
                    return false;
                }
            } else if (isFieldPresent(t, i5, i2, i) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i5))) {
                return false;
            }
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t).isInitialized();
    }

    public void makeImmutable(T t) {
        int i;
        int i2 = this.checkInitializedCount;
        while (true) {
            i = this.repeatedFieldOffsetStart;
            if (i2 >= i) {
                break;
            }
            long offset = offset(typeAndOffsetAt(this.intArray[i2]));
            Object object = UnsafeUtil.getObject((Object) t, offset);
            if (object != null) {
                UnsafeUtil.putObject((Object) t, offset, this.mapFieldSchema.toImmutable(object));
            }
            i2++;
        }
        int length = this.intArray.length;
        while (i < length) {
            this.listFieldSchema.makeImmutableListAt(t, (long) this.intArray[i]);
            i++;
        }
        this.unknownFieldSchema.makeImmutable(t);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(t);
        }
    }

    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (extensionRegistryLite != null) {
            mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
            return;
        }
        throw new NullPointerException();
    }

    public void mergeFrom(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.buffer.length; i += 3) {
                mergeSingleField(t, t2, i);
            }
            if (!this.proto3) {
                SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
                if (this.hasExtensions) {
                    SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(t, bArr, i, i2, registers);
        } else {
            parseProto2Message(t, bArr, i, i2, 0, registers);
        }
    }

    public T newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v74, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x035d, code lost:
        if (r0 != r15) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03a6, code lost:
        if (r0 != r15) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x03c9, code lost:
        if (r0 != r15) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x03cc, code lost:
        r2 = r0;
        r8 = r18;
        r0 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0100, code lost:
        r12 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0166, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0209, code lost:
        r6 = r6 | r20;
        r2 = r8;
        r3 = r13;
        r1 = r17;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0233, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x026d, code lost:
        r6 = r6 | r20;
        r2 = r8;
        r0 = r11;
        r3 = r13;
        r1 = r17;
        r13 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0276, code lost:
        r11 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02ac, code lost:
        r0 = r11 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02ae, code lost:
        r6 = r6 | r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02b0, code lost:
        r11 = r35;
        r2 = r8;
        r3 = r13;
        r1 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02b8, code lost:
        r0 = r35;
        r22 = r6;
        r19 = r7;
        r20 = r8;
        r28 = r10;
        r2 = r11;
        r8 = r13;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x03e7  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0403  */
    public int parseProto2Message(T t, byte[] bArr, int i, int i2, int i3, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i4;
        MessageSchema messageSchema;
        int i5;
        int i6;
        int i7;
        int i8;
        T t2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        ArrayDecoders.Registers registers2;
        int i17;
        byte[] bArr2;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int decodeUnknownField;
        byte[] bArr3;
        int i24;
        int i25;
        int decodeMessageList;
        int i26;
        char c;
        int i27;
        int i28;
        int i29;
        int i30;
        byte[] bArr4;
        int i31;
        MessageSchema messageSchema2 = this;
        T t3 = t;
        byte[] bArr5 = bArr;
        int i32 = i2;
        int i33 = i3;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i34 = i;
        int i35 = 0;
        int i36 = 0;
        int i37 = 0;
        int i38 = -1;
        int i39 = -1;
        while (true) {
            if (i34 < i32) {
                int i40 = i34 + 1;
                byte b = bArr5[i34];
                if (b < 0) {
                    i10 = ArrayDecoders.decodeVarint32(b, bArr5, i40, registers3);
                    i9 = registers3.int1;
                } else {
                    i9 = b;
                    i10 = i40;
                }
                int i41 = i9 >>> 3;
                int i42 = i9 & 7;
                int positionForFieldNumber = i41 > i38 ? messageSchema2.positionForFieldNumber(i41, i35 / 3) : messageSchema2.positionForFieldNumber(i41);
                if (positionForFieldNumber == -1) {
                    i14 = i41;
                    i5 = i10;
                    i15 = i9;
                    i11 = i37;
                    i13 = i39;
                    unsafe = unsafe2;
                    i16 = i33;
                    i12 = 0;
                    if (i15 == i16 || i16 == 0) {
                        int i43 = i16;
                        if (!this.hasExtensions) {
                            registers2 = registers;
                            if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                                decodeUnknownField = ArrayDecoders.decodeExtensionOrUnknownField(i15, bArr, i5, i2, t, this.defaultInstance, this.unknownFieldSchema, registers);
                                t3 = t;
                                bArr2 = bArr;
                                i18 = i2;
                                i19 = i15;
                                messageSchema2 = this;
                                registers3 = registers2;
                                i20 = i14;
                                i39 = i13;
                                i21 = i12;
                                i22 = i11;
                                i23 = i43;
                            }
                        } else {
                            registers2 = registers;
                        }
                        decodeUnknownField = ArrayDecoders.decodeUnknownField(i15, bArr, i5, i2, getMutableUnknownFields(t), registers);
                        t3 = t;
                        bArr2 = bArr;
                        i18 = i2;
                        i19 = i15;
                        messageSchema2 = this;
                        registers3 = registers2;
                        i20 = i14;
                        i39 = i13;
                        i21 = i12;
                        i22 = i11;
                        i23 = i43;
                    } else {
                        i8 = -1;
                        messageSchema = this;
                        i4 = i16;
                        i36 = i15;
                        i6 = i13;
                        i7 = i11;
                    }
                } else {
                    int i44 = messageSchema2.buffer[positionForFieldNumber + 1];
                    int type = type(i44);
                    long offset = offset(i44);
                    int i45 = i9;
                    int i46 = i44;
                    if (type <= 17) {
                        int i47 = messageSchema2.buffer[positionForFieldNumber + 2];
                        int i48 = 1 << (i47 >>> 20);
                        int i49 = i47 & OFFSET_MASK;
                        if (i49 != i39) {
                            c = 65535;
                            if (i39 != -1) {
                                i26 = positionForFieldNumber;
                                unsafe2.putInt(t3, (long) i39, i37);
                            } else {
                                i26 = positionForFieldNumber;
                            }
                            i37 = unsafe2.getInt(t3, (long) i49);
                            i39 = i49;
                        } else {
                            i26 = positionForFieldNumber;
                            c = 65535;
                        }
                        switch (type) {
                            case 0:
                                i29 = i26;
                                i14 = i41;
                                long j = offset;
                                bArr3 = bArr;
                                i28 = i10;
                                int i50 = i45;
                                char c2 = c;
                                i27 = i50;
                                if (i42 == 1) {
                                    UnsafeUtil.putDouble((Object) t3, j, ArrayDecoders.decodeDouble(bArr3, i28));
                                    break;
                                }
                                break;
                            case 1:
                                i29 = i26;
                                i14 = i41;
                                long j2 = offset;
                                bArr3 = bArr;
                                i28 = i10;
                                int i51 = i45;
                                char c3 = c;
                                i27 = i51;
                                if (i42 == 5) {
                                    UnsafeUtil.putFloat((Object) t3, j2, ArrayDecoders.decodeFloat(bArr3, i28));
                                    decodeMessageList = i28 + 4;
                                    break;
                                }
                                break;
                            case 2:
                            case 3:
                                i29 = i26;
                                i14 = i41;
                                long j3 = offset;
                                bArr4 = bArr;
                                i28 = i10;
                                int i52 = i45;
                                char c4 = c;
                                i27 = i52;
                                if (i42 == 0) {
                                    i30 = ArrayDecoders.decodeVarint64(bArr4, i28, registers3);
                                    unsafe2.putLong(t, j3, registers3.long1);
                                    break;
                                }
                                break;
                            case 4:
                            case 11:
                                i29 = i26;
                                i14 = i41;
                                long j4 = offset;
                                bArr3 = bArr;
                                i28 = i10;
                                int i53 = i45;
                                char c5 = c;
                                i27 = i53;
                                if (i42 == 0) {
                                    decodeMessageList = ArrayDecoders.decodeVarint32(bArr3, i28, registers3);
                                    unsafe2.putInt(t3, j4, registers3.int1);
                                    break;
                                }
                                break;
                            case 5:
                            case 14:
                                i29 = i26;
                                i14 = i41;
                                long j5 = offset;
                                bArr3 = bArr;
                                int i54 = i2;
                                int i55 = i45;
                                char c6 = c;
                                i27 = i55;
                                if (i42 == 1) {
                                    i28 = i10;
                                    unsafe2.putLong(t, j5, ArrayDecoders.decodeFixed64(bArr3, i10));
                                    break;
                                }
                                break;
                            case 6:
                            case 13:
                                i29 = i26;
                                i14 = i41;
                                long j6 = offset;
                                bArr5 = bArr;
                                i31 = i2;
                                int i56 = i45;
                                char c7 = c;
                                i27 = i56;
                                if (i42 == 5) {
                                    unsafe2.putInt(t3, j6, ArrayDecoders.decodeFixed32(bArr5, i10));
                                    i34 = i10 + 4;
                                    break;
                                }
                                break;
                            case 7:
                                i29 = i26;
                                i14 = i41;
                                long j7 = offset;
                                bArr5 = bArr;
                                i31 = i2;
                                int i57 = i45;
                                char c8 = c;
                                i27 = i57;
                                if (i42 == 0) {
                                    i34 = ArrayDecoders.decodeVarint64(bArr5, i10, registers3);
                                    UnsafeUtil.putBoolean((Object) t3, j7, registers3.long1 != 0);
                                    break;
                                }
                                break;
                            case 8:
                                i29 = i26;
                                i14 = i41;
                                long j8 = offset;
                                bArr5 = bArr;
                                i31 = i2;
                                int i58 = i45;
                                char c9 = c;
                                i27 = i58;
                                if (i42 == 2) {
                                    i34 = (i46 & ENFORCE_UTF8_MASK) == 0 ? ArrayDecoders.decodeString(bArr5, i10, registers3) : ArrayDecoders.decodeStringRequireUtf8(bArr5, i10, registers3);
                                    unsafe2.putObject(t3, j8, registers3.object1);
                                    break;
                                }
                                break;
                            case 9:
                                i29 = i26;
                                i27 = i45;
                                i14 = i41;
                                long j9 = offset;
                                bArr5 = bArr;
                                if (i42 != 2) {
                                    int i59 = i2;
                                    break;
                                } else {
                                    i31 = i2;
                                    i34 = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(i29), bArr5, i10, i31, registers3);
                                    if ((i37 & i48) != 0) {
                                        unsafe2.putObject(t3, j9, Internal.mergeMessage(unsafe2.getObject(t3, j9), registers3.object1));
                                        break;
                                    } else {
                                        unsafe2.putObject(t3, j9, registers3.object1);
                                        break;
                                    }
                                }
                            case 10:
                                i29 = i26;
                                i27 = i45;
                                i14 = i41;
                                long j10 = offset;
                                bArr3 = bArr;
                                if (i42 == 2) {
                                    decodeMessageList = ArrayDecoders.decodeBytes(bArr3, i10, registers3);
                                    unsafe2.putObject(t3, j10, registers3.object1);
                                    break;
                                }
                                break;
                            case 12:
                                i29 = i26;
                                i27 = i45;
                                i14 = i41;
                                long j11 = offset;
                                bArr3 = bArr;
                                if (i42 == 0) {
                                    decodeMessageList = ArrayDecoders.decodeVarint32(bArr3, i10, registers3);
                                    int i60 = registers3.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i29);
                                    if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(i60)) {
                                        getMutableUnknownFields(t).storeField(i27, Long.valueOf((long) i60));
                                        break;
                                    } else {
                                        unsafe2.putInt(t3, j11, i60);
                                        break;
                                    }
                                }
                                break;
                            case 15:
                                i29 = i26;
                                i27 = i45;
                                i14 = i41;
                                long j12 = offset;
                                bArr3 = bArr;
                                if (i42 == 0) {
                                    decodeMessageList = ArrayDecoders.decodeVarint32(bArr3, i10, registers3);
                                    unsafe2.putInt(t3, j12, CodedInputStream.decodeZigZag32(registers3.int1));
                                    break;
                                }
                                break;
                            case 16:
                                i29 = i26;
                                i27 = i45;
                                i14 = i41;
                                if (i42 == 0) {
                                    long j13 = offset;
                                    bArr4 = bArr;
                                    i30 = ArrayDecoders.decodeVarint64(bArr4, i10, registers3);
                                    unsafe2.putLong(t, j13, CodedInputStream.decodeZigZag64(registers3.long1));
                                    break;
                                }
                                break;
                            case 17:
                                if (i42 != 3) {
                                    i29 = i26;
                                    i27 = i45;
                                    i14 = i41;
                                    break;
                                } else {
                                    int i61 = i26;
                                    i29 = i61;
                                    i14 = i41;
                                    i27 = i45;
                                    decodeMessageList = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(i61), bArr, i10, i2, (i41 << 3) | 4, registers);
                                    if ((i37 & i48) == 0) {
                                        unsafe2.putObject(t3, offset, registers3.object1);
                                    } else {
                                        unsafe2.putObject(t3, offset, Internal.mergeMessage(unsafe2.getObject(t3, offset), registers3.object1));
                                    }
                                    i37 |= i48;
                                    bArr3 = bArr;
                                    break;
                                }
                            default:
                                byte[] bArr6 = bArr;
                                i28 = i10;
                                i29 = i26;
                                i14 = i41;
                                int i62 = i45;
                                char c10 = c;
                                i27 = i62;
                                break;
                        }
                    } else {
                        int i63 = positionForFieldNumber;
                        i14 = i41;
                        long j14 = offset;
                        int i64 = i45;
                        bArr3 = bArr;
                        int i65 = i10;
                        if (type != 27) {
                            i12 = i63;
                            i11 = i37;
                            if (type <= 49) {
                                long j15 = (long) i46;
                                i13 = i39;
                                unsafe = unsafe2;
                                int i66 = i65;
                                i24 = i64;
                                i17 = parseRepeatedField(t, bArr, i65, i2, i64, i14, i42, i12, j15, type, j14, registers);
                            } else {
                                int i67 = i42;
                                long j16 = j14;
                                unsafe = unsafe2;
                                i25 = i65;
                                i24 = i64;
                                int i68 = i46;
                                i13 = i39;
                                int i69 = type;
                                if (i69 != 50) {
                                    i17 = parseOneofField(t, bArr, i25, i2, i24, i14, i67, i68, i69, j16, i12, registers);
                                } else if (i67 == 2) {
                                    i17 = parseMapField(t, bArr, i25, i2, i12, j16, registers);
                                }
                            }
                            messageSchema2 = this;
                            t3 = t;
                            bArr2 = bArr;
                            i18 = i2;
                            i23 = i3;
                            registers3 = registers;
                            i20 = i14;
                            i19 = i24;
                            i39 = i13;
                            i21 = i12;
                            i22 = i11;
                        } else if (i42 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t3, j14);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(t3, j14, protobufList);
                            }
                            decodeMessageList = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(i63), i64, bArr, i65, i2, protobufList, registers);
                            i33 = i3;
                            int i70 = i64;
                            int i71 = i14;
                            int i72 = i63;
                            i37 = i37;
                        } else {
                            i12 = i63;
                            i11 = i37;
                            i13 = i39;
                            unsafe = unsafe2;
                            i25 = i65;
                            i24 = i64;
                        }
                        i16 = i3;
                        i5 = i25;
                        i15 = i24;
                        if (i15 == i16) {
                        }
                        int i432 = i16;
                        if (!this.hasExtensions) {
                        }
                        decodeUnknownField = ArrayDecoders.decodeUnknownField(i15, bArr, i5, i2, getMutableUnknownFields(t), registers);
                        t3 = t;
                        bArr2 = bArr;
                        i18 = i2;
                        i19 = i15;
                        messageSchema2 = this;
                        registers3 = registers2;
                        i20 = i14;
                        i39 = i13;
                        i21 = i12;
                        i22 = i11;
                        i23 = i432;
                    }
                    i32 = i2;
                }
                unsafe2 = unsafe;
            } else {
                unsafe = unsafe2;
                i4 = i33;
                messageSchema = messageSchema2;
                i5 = i34;
                i6 = i39;
                i7 = i37;
                i8 = -1;
            }
        }
        if (i6 != i8) {
            long j17 = (long) i6;
            t2 = t;
            unsafe.putInt(t2, j17, i7);
        } else {
            t2 = t;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i73 = messageSchema.checkInitializedCount; i73 < messageSchema.repeatedFieldOffsetStart; i73++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t2, messageSchema.intArray[i73], unknownFieldSetLite, messageSchema.unknownFieldSchema);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t2, unknownFieldSetLite);
        }
        if (i4 == 0) {
            if (i5 != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i5 > i2 || i36 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i5;
    }

    public void writeTo(T t, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t, writer);
        }
    }
}
