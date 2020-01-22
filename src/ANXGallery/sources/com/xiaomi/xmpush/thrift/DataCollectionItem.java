package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class DataCollectionItem implements Serializable, Cloneable, TBase<DataCollectionItem, Object> {
    private static final TField COLLECTED_AT_FIELD_DESC = new TField("", (byte) 10, 1);
    private static final TField COLLECTION_TYPE_FIELD_DESC = new TField("", (byte) 8, 2);
    private static final TField CONTENT_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TStruct STRUCT_DESC = new TStruct("DataCollectionItem");
    private BitSet __isset_bit_vector = new BitSet(1);
    public long collectedAt;
    public ClientCollectionType collectionType;
    public String content;

    public int compareTo(DataCollectionItem dataCollectionItem) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        if (!getClass().equals(dataCollectionItem.getClass())) {
            return getClass().getName().compareTo(dataCollectionItem.getClass().getName());
        }
        int compareTo4 = Boolean.valueOf(isSetCollectedAt()).compareTo(Boolean.valueOf(dataCollectionItem.isSetCollectedAt()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (isSetCollectedAt() && (compareTo3 = TBaseHelper.compareTo(this.collectedAt, dataCollectionItem.collectedAt)) != 0) {
            return compareTo3;
        }
        int compareTo5 = Boolean.valueOf(isSetCollectionType()).compareTo(Boolean.valueOf(dataCollectionItem.isSetCollectionType()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetCollectionType() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.collectionType, (Comparable) dataCollectionItem.collectionType)) != 0) {
            return compareTo2;
        }
        int compareTo6 = Boolean.valueOf(isSetContent()).compareTo(Boolean.valueOf(dataCollectionItem.isSetContent()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (!isSetContent() || (compareTo = TBaseHelper.compareTo(this.content, dataCollectionItem.content)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(DataCollectionItem dataCollectionItem) {
        if (dataCollectionItem == null || this.collectedAt != dataCollectionItem.collectedAt) {
            return false;
        }
        boolean isSetCollectionType = isSetCollectionType();
        boolean isSetCollectionType2 = dataCollectionItem.isSetCollectionType();
        if ((isSetCollectionType || isSetCollectionType2) && (!isSetCollectionType || !isSetCollectionType2 || !this.collectionType.equals(dataCollectionItem.collectionType))) {
            return false;
        }
        boolean isSetContent = isSetContent();
        boolean isSetContent2 = dataCollectionItem.isSetContent();
        if (isSetContent || isSetContent2) {
            return isSetContent && isSetContent2 && this.content.equals(dataCollectionItem.content);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof DataCollectionItem)) {
            return equals((DataCollectionItem) obj);
        }
        return false;
    }

    public String getContent() {
        return this.content;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetCollectedAt() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetCollectionType() {
        return this.collectionType != null;
    }

    public boolean isSetContent() {
        return this.content != null;
    }

    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            if (readFieldBegin.type == 0) {
                break;
            }
            short s = readFieldBegin.id;
            if (s != 1) {
                if (s != 2) {
                    if (s != 3) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    } else if (readFieldBegin.type == 11) {
                        this.content = tProtocol.readString();
                    } else {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    }
                } else if (readFieldBegin.type == 8) {
                    this.collectionType = ClientCollectionType.findByValue(tProtocol.readI32());
                } else {
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                }
            } else if (readFieldBegin.type == 10) {
                this.collectedAt = tProtocol.readI64();
                setCollectedAtIsSet(true);
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
        tProtocol.readStructEnd();
        if (isSetCollectedAt()) {
            validate();
            return;
        }
        throw new TProtocolException("Required field 'collectedAt' was not found in serialized data! Struct: " + toString());
    }

    public DataCollectionItem setCollectedAt(long j) {
        this.collectedAt = j;
        setCollectedAtIsSet(true);
        return this;
    }

    public void setCollectedAtIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public DataCollectionItem setCollectionType(ClientCollectionType clientCollectionType) {
        this.collectionType = clientCollectionType;
        return this;
    }

    public DataCollectionItem setContent(String str) {
        this.content = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.collectedAt);
        sb.append(", ");
        sb.append("collectionType:");
        ClientCollectionType clientCollectionType = this.collectionType;
        if (clientCollectionType == null) {
            sb.append("null");
        } else {
            sb.append(clientCollectionType);
        }
        sb.append(", ");
        sb.append("content:");
        String str = this.content;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.collectionType == null) {
            throw new TProtocolException("Required field 'collectionType' was not present! Struct: " + toString());
        } else if (this.content == null) {
            throw new TProtocolException("Required field 'content' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(COLLECTED_AT_FIELD_DESC);
        tProtocol.writeI64(this.collectedAt);
        tProtocol.writeFieldEnd();
        if (this.collectionType != null) {
            tProtocol.writeFieldBegin(COLLECTION_TYPE_FIELD_DESC);
            tProtocol.writeI32(this.collectionType.getValue());
            tProtocol.writeFieldEnd();
        }
        if (this.content != null) {
            tProtocol.writeFieldBegin(CONTENT_FIELD_DESC);
            tProtocol.writeString(this.content);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
