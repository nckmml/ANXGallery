package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class NormalConfig implements Serializable, Cloneable, TBase<NormalConfig, Object> {
    private static final TField CONFIG_ITEMS_FIELD_DESC = new TField("", (byte) 15, 2);
    private static final TStruct STRUCT_DESC = new TStruct("NormalConfig");
    private static final TField TYPE_FIELD_DESC = new TField("", (byte) 8, 3);
    private static final TField VERSION_FIELD_DESC = new TField("", (byte) 8, 1);
    private BitSet __isset_bit_vector = new BitSet(1);
    public List<OnlineConfigItem> configItems;
    public ConfigListType type;
    public int version;

    public int compareTo(NormalConfig normalConfig) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        if (!getClass().equals(normalConfig.getClass())) {
            return getClass().getName().compareTo(normalConfig.getClass().getName());
        }
        int compareTo4 = Boolean.valueOf(isSetVersion()).compareTo(Boolean.valueOf(normalConfig.isSetVersion()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (isSetVersion() && (compareTo3 = TBaseHelper.compareTo(this.version, normalConfig.version)) != 0) {
            return compareTo3;
        }
        int compareTo5 = Boolean.valueOf(isSetConfigItems()).compareTo(Boolean.valueOf(normalConfig.isSetConfigItems()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetConfigItems() && (compareTo2 = TBaseHelper.compareTo((List) this.configItems, (List) normalConfig.configItems)) != 0) {
            return compareTo2;
        }
        int compareTo6 = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(normalConfig.isSetType()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (!isSetType() || (compareTo = TBaseHelper.compareTo((Comparable) this.type, (Comparable) normalConfig.type)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(NormalConfig normalConfig) {
        if (normalConfig == null || this.version != normalConfig.version) {
            return false;
        }
        boolean isSetConfigItems = isSetConfigItems();
        boolean isSetConfigItems2 = normalConfig.isSetConfigItems();
        if ((isSetConfigItems || isSetConfigItems2) && (!isSetConfigItems || !isSetConfigItems2 || !this.configItems.equals(normalConfig.configItems))) {
            return false;
        }
        boolean isSetType = isSetType();
        boolean isSetType2 = normalConfig.isSetType();
        if (isSetType || isSetType2) {
            return isSetType && isSetType2 && this.type.equals(normalConfig.type);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof NormalConfig)) {
            return equals((NormalConfig) obj);
        }
        return false;
    }

    public ConfigListType getType() {
        return this.type;
    }

    public int getVersion() {
        return this.version;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetConfigItems() {
        return this.configItems != null;
    }

    public boolean isSetType() {
        return this.type != null;
    }

    public boolean isSetVersion() {
        return this.__isset_bit_vector.get(0);
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
                    } else if (readFieldBegin.type == 8) {
                        this.type = ConfigListType.findByValue(tProtocol.readI32());
                    } else {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    }
                } else if (readFieldBegin.type == 15) {
                    TList readListBegin = tProtocol.readListBegin();
                    this.configItems = new ArrayList(readListBegin.size);
                    for (int i = 0; i < readListBegin.size; i++) {
                        OnlineConfigItem onlineConfigItem = new OnlineConfigItem();
                        onlineConfigItem.read(tProtocol);
                        this.configItems.add(onlineConfigItem);
                    }
                    tProtocol.readListEnd();
                } else {
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                }
            } else if (readFieldBegin.type == 8) {
                this.version = tProtocol.readI32();
                setVersionIsSet(true);
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
        tProtocol.readStructEnd();
        if (isSetVersion()) {
            validate();
            return;
        }
        throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + toString());
    }

    public void setVersionIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.version);
        sb.append(", ");
        sb.append("configItems:");
        List<OnlineConfigItem> list = this.configItems;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        if (isSetType()) {
            sb.append(", ");
            sb.append("type:");
            ConfigListType configListType = this.type;
            if (configListType == null) {
                sb.append("null");
            } else {
                sb.append(configListType);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.configItems == null) {
            throw new TProtocolException("Required field 'configItems' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(VERSION_FIELD_DESC);
        tProtocol.writeI32(this.version);
        tProtocol.writeFieldEnd();
        if (this.configItems != null) {
            tProtocol.writeFieldBegin(CONFIG_ITEMS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.configItems.size()));
            for (OnlineConfigItem write : this.configItems) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.type != null && isSetType()) {
            tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
            tProtocol.writeI32(this.type.getValue());
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
