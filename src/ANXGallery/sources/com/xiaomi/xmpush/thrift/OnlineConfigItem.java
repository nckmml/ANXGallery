package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class OnlineConfigItem implements Serializable, Cloneable, TBase<OnlineConfigItem, Object> {
    private static final TField BOOL_VALUE_FIELD_DESC = new TField("", (byte) 2, 7);
    private static final TField CLEAR_FIELD_DESC = new TField("", (byte) 2, 3);
    private static final TField INT_VALUE_FIELD_DESC = new TField("", (byte) 8, 4);
    private static final TField KEY_FIELD_DESC = new TField("", (byte) 8, 1);
    private static final TField LONG_VALUE_FIELD_DESC = new TField("", (byte) 10, 5);
    private static final TField STRING_VALUE_FIELD_DESC = new TField("", (byte) 11, 6);
    private static final TStruct STRUCT_DESC = new TStruct("OnlineConfigItem");
    private static final TField TYPE_FIELD_DESC = new TField("", (byte) 8, 2);
    private BitSet __isset_bit_vector = new BitSet(6);
    public boolean boolValue;
    public boolean clear;
    public int intValue;
    public int key;
    public long longValue;
    public String stringValue;
    public int type;

    public int compareTo(OnlineConfigItem onlineConfigItem) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        if (!getClass().equals(onlineConfigItem.getClass())) {
            return getClass().getName().compareTo(onlineConfigItem.getClass().getName());
        }
        int compareTo8 = Boolean.valueOf(isSetKey()).compareTo(Boolean.valueOf(onlineConfigItem.isSetKey()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetKey() && (compareTo7 = TBaseHelper.compareTo(this.key, onlineConfigItem.key)) != 0) {
            return compareTo7;
        }
        int compareTo9 = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(onlineConfigItem.isSetType()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetType() && (compareTo6 = TBaseHelper.compareTo(this.type, onlineConfigItem.type)) != 0) {
            return compareTo6;
        }
        int compareTo10 = Boolean.valueOf(isSetClear()).compareTo(Boolean.valueOf(onlineConfigItem.isSetClear()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetClear() && (compareTo5 = TBaseHelper.compareTo(this.clear, onlineConfigItem.clear)) != 0) {
            return compareTo5;
        }
        int compareTo11 = Boolean.valueOf(isSetIntValue()).compareTo(Boolean.valueOf(onlineConfigItem.isSetIntValue()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetIntValue() && (compareTo4 = TBaseHelper.compareTo(this.intValue, onlineConfigItem.intValue)) != 0) {
            return compareTo4;
        }
        int compareTo12 = Boolean.valueOf(isSetLongValue()).compareTo(Boolean.valueOf(onlineConfigItem.isSetLongValue()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetLongValue() && (compareTo3 = TBaseHelper.compareTo(this.longValue, onlineConfigItem.longValue)) != 0) {
            return compareTo3;
        }
        int compareTo13 = Boolean.valueOf(isSetStringValue()).compareTo(Boolean.valueOf(onlineConfigItem.isSetStringValue()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetStringValue() && (compareTo2 = TBaseHelper.compareTo(this.stringValue, onlineConfigItem.stringValue)) != 0) {
            return compareTo2;
        }
        int compareTo14 = Boolean.valueOf(isSetBoolValue()).compareTo(Boolean.valueOf(onlineConfigItem.isSetBoolValue()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (!isSetBoolValue() || (compareTo = TBaseHelper.compareTo(this.boolValue, onlineConfigItem.boolValue)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(OnlineConfigItem onlineConfigItem) {
        if (onlineConfigItem == null) {
            return false;
        }
        boolean isSetKey = isSetKey();
        boolean isSetKey2 = onlineConfigItem.isSetKey();
        if ((isSetKey || isSetKey2) && (!isSetKey || !isSetKey2 || this.key != onlineConfigItem.key)) {
            return false;
        }
        boolean isSetType = isSetType();
        boolean isSetType2 = onlineConfigItem.isSetType();
        if ((isSetType || isSetType2) && (!isSetType || !isSetType2 || this.type != onlineConfigItem.type)) {
            return false;
        }
        boolean isSetClear = isSetClear();
        boolean isSetClear2 = onlineConfigItem.isSetClear();
        if ((isSetClear || isSetClear2) && (!isSetClear || !isSetClear2 || this.clear != onlineConfigItem.clear)) {
            return false;
        }
        boolean isSetIntValue = isSetIntValue();
        boolean isSetIntValue2 = onlineConfigItem.isSetIntValue();
        if ((isSetIntValue || isSetIntValue2) && (!isSetIntValue || !isSetIntValue2 || this.intValue != onlineConfigItem.intValue)) {
            return false;
        }
        boolean isSetLongValue = isSetLongValue();
        boolean isSetLongValue2 = onlineConfigItem.isSetLongValue();
        if ((isSetLongValue || isSetLongValue2) && (!isSetLongValue || !isSetLongValue2 || this.longValue != onlineConfigItem.longValue)) {
            return false;
        }
        boolean isSetStringValue = isSetStringValue();
        boolean isSetStringValue2 = onlineConfigItem.isSetStringValue();
        if ((isSetStringValue || isSetStringValue2) && (!isSetStringValue || !isSetStringValue2 || !this.stringValue.equals(onlineConfigItem.stringValue))) {
            return false;
        }
        boolean isSetBoolValue = isSetBoolValue();
        boolean isSetBoolValue2 = onlineConfigItem.isSetBoolValue();
        if (isSetBoolValue || isSetBoolValue2) {
            return isSetBoolValue && isSetBoolValue2 && this.boolValue == onlineConfigItem.boolValue;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof OnlineConfigItem)) {
            return equals((OnlineConfigItem) obj);
        }
        return false;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public int getKey() {
        return this.key;
    }

    public long getLongValue() {
        return this.longValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isBoolValue() {
        return this.boolValue;
    }

    public boolean isSetBoolValue() {
        return this.__isset_bit_vector.get(5);
    }

    public boolean isSetClear() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetIntValue() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetKey() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetLongValue() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetStringValue() {
        return this.stringValue != null;
    }

    public boolean isSetType() {
        return this.__isset_bit_vector.get(1);
    }

    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            if (readFieldBegin.type == 0) {
                tProtocol.readStructEnd();
                validate();
                return;
            }
            switch (readFieldBegin.id) {
                case 1:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.key = tProtocol.readI32();
                        setKeyIsSet(true);
                        break;
                    }
                case 2:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.type = tProtocol.readI32();
                        setTypeIsSet(true);
                        break;
                    }
                case 3:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.clear = tProtocol.readBool();
                        setClearIsSet(true);
                        break;
                    }
                case 4:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.intValue = tProtocol.readI32();
                        setIntValueIsSet(true);
                        break;
                    }
                case 5:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.longValue = tProtocol.readI64();
                        setLongValueIsSet(true);
                        break;
                    }
                case 6:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.stringValue = tProtocol.readString();
                        break;
                    }
                case 7:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.boolValue = tProtocol.readBool();
                        setBoolValueIsSet(true);
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setBoolValueIsSet(boolean z) {
        this.__isset_bit_vector.set(5, z);
    }

    public void setClearIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public void setIntValueIsSet(boolean z) {
        this.__isset_bit_vector.set(3, z);
    }

    public void setKeyIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public void setLongValueIsSet(boolean z) {
        this.__isset_bit_vector.set(4, z);
    }

    public void setTypeIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        if (isSetKey()) {
            sb.append("key:");
            sb.append(this.key);
            z = false;
        } else {
            z = true;
        }
        if (isSetType()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.type);
            z = false;
        }
        if (isSetClear()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.clear);
            z = false;
        }
        if (isSetIntValue()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.intValue);
            z = false;
        }
        if (isSetLongValue()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.longValue);
            z = false;
        }
        if (isSetStringValue()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            String str = this.stringValue;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        }
        if (isSetBoolValue()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.boolValue);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (isSetKey()) {
            tProtocol.writeFieldBegin(KEY_FIELD_DESC);
            tProtocol.writeI32(this.key);
            tProtocol.writeFieldEnd();
        }
        if (isSetType()) {
            tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
            tProtocol.writeI32(this.type);
            tProtocol.writeFieldEnd();
        }
        if (isSetClear()) {
            tProtocol.writeFieldBegin(CLEAR_FIELD_DESC);
            tProtocol.writeBool(this.clear);
            tProtocol.writeFieldEnd();
        }
        if (isSetIntValue()) {
            tProtocol.writeFieldBegin(INT_VALUE_FIELD_DESC);
            tProtocol.writeI32(this.intValue);
            tProtocol.writeFieldEnd();
        }
        if (isSetLongValue()) {
            tProtocol.writeFieldBegin(LONG_VALUE_FIELD_DESC);
            tProtocol.writeI64(this.longValue);
            tProtocol.writeFieldEnd();
        }
        if (this.stringValue != null && isSetStringValue()) {
            tProtocol.writeFieldBegin(STRING_VALUE_FIELD_DESC);
            tProtocol.writeString(this.stringValue);
            tProtocol.writeFieldEnd();
        }
        if (isSetBoolValue()) {
            tProtocol.writeFieldBegin(BOOL_VALUE_FIELD_DESC);
            tProtocol.writeBool(this.boolValue);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
