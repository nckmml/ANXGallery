package com.xiaomi.push.thrift;

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

public class StatsEvent implements Serializable, Cloneable, TBase<StatsEvent, Object> {
    private static final TField ANNOTATION_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField CHID_FIELD_DESC = new TField("", (byte) 3, 1);
    private static final TField CLIENT_IP_FIELD_DESC = new TField("", (byte) 8, 10);
    private static final TField CONNPT_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField HOST_FIELD_DESC = new TField("", (byte) 11, 5);
    private static final TStruct STRUCT_DESC = new TStruct("StatsEvent");
    private static final TField SUBVALUE_FIELD_DESC = new TField("", (byte) 8, 6);
    private static final TField TIME_FIELD_DESC = new TField("", (byte) 8, 9);
    private static final TField TYPE_FIELD_DESC = new TField("", (byte) 8, 2);
    private static final TField USER_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField VALUE_FIELD_DESC = new TField("", (byte) 8, 3);
    private BitSet __isset_bit_vector = new BitSet(6);
    public String annotation;
    public byte chid;
    public int clientIp;
    public String connpt;
    public String host;
    public int subvalue;
    public int time;
    public int type;
    public String user;
    public int value;

    public int compareTo(StatsEvent statsEvent) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        int compareTo9;
        int compareTo10;
        if (!getClass().equals(statsEvent.getClass())) {
            return getClass().getName().compareTo(statsEvent.getClass().getName());
        }
        int compareTo11 = Boolean.valueOf(isSetChid()).compareTo(Boolean.valueOf(statsEvent.isSetChid()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetChid() && (compareTo10 = TBaseHelper.compareTo(this.chid, statsEvent.chid)) != 0) {
            return compareTo10;
        }
        int compareTo12 = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(statsEvent.isSetType()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetType() && (compareTo9 = TBaseHelper.compareTo(this.type, statsEvent.type)) != 0) {
            return compareTo9;
        }
        int compareTo13 = Boolean.valueOf(isSetValue()).compareTo(Boolean.valueOf(statsEvent.isSetValue()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetValue() && (compareTo8 = TBaseHelper.compareTo(this.value, statsEvent.value)) != 0) {
            return compareTo8;
        }
        int compareTo14 = Boolean.valueOf(isSetConnpt()).compareTo(Boolean.valueOf(statsEvent.isSetConnpt()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetConnpt() && (compareTo7 = TBaseHelper.compareTo(this.connpt, statsEvent.connpt)) != 0) {
            return compareTo7;
        }
        int compareTo15 = Boolean.valueOf(isSetHost()).compareTo(Boolean.valueOf(statsEvent.isSetHost()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetHost() && (compareTo6 = TBaseHelper.compareTo(this.host, statsEvent.host)) != 0) {
            return compareTo6;
        }
        int compareTo16 = Boolean.valueOf(isSetSubvalue()).compareTo(Boolean.valueOf(statsEvent.isSetSubvalue()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetSubvalue() && (compareTo5 = TBaseHelper.compareTo(this.subvalue, statsEvent.subvalue)) != 0) {
            return compareTo5;
        }
        int compareTo17 = Boolean.valueOf(isSetAnnotation()).compareTo(Boolean.valueOf(statsEvent.isSetAnnotation()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetAnnotation() && (compareTo4 = TBaseHelper.compareTo(this.annotation, statsEvent.annotation)) != 0) {
            return compareTo4;
        }
        int compareTo18 = Boolean.valueOf(isSetUser()).compareTo(Boolean.valueOf(statsEvent.isSetUser()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetUser() && (compareTo3 = TBaseHelper.compareTo(this.user, statsEvent.user)) != 0) {
            return compareTo3;
        }
        int compareTo19 = Boolean.valueOf(isSetTime()).compareTo(Boolean.valueOf(statsEvent.isSetTime()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetTime() && (compareTo2 = TBaseHelper.compareTo(this.time, statsEvent.time)) != 0) {
            return compareTo2;
        }
        int compareTo20 = Boolean.valueOf(isSetClientIp()).compareTo(Boolean.valueOf(statsEvent.isSetClientIp()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (!isSetClientIp() || (compareTo = TBaseHelper.compareTo(this.clientIp, statsEvent.clientIp)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(StatsEvent statsEvent) {
        if (statsEvent == null || this.chid != statsEvent.chid || this.type != statsEvent.type || this.value != statsEvent.value) {
            return false;
        }
        boolean isSetConnpt = isSetConnpt();
        boolean isSetConnpt2 = statsEvent.isSetConnpt();
        if ((isSetConnpt || isSetConnpt2) && (!isSetConnpt || !isSetConnpt2 || !this.connpt.equals(statsEvent.connpt))) {
            return false;
        }
        boolean isSetHost = isSetHost();
        boolean isSetHost2 = statsEvent.isSetHost();
        if ((isSetHost || isSetHost2) && (!isSetHost || !isSetHost2 || !this.host.equals(statsEvent.host))) {
            return false;
        }
        boolean isSetSubvalue = isSetSubvalue();
        boolean isSetSubvalue2 = statsEvent.isSetSubvalue();
        if ((isSetSubvalue || isSetSubvalue2) && (!isSetSubvalue || !isSetSubvalue2 || this.subvalue != statsEvent.subvalue)) {
            return false;
        }
        boolean isSetAnnotation = isSetAnnotation();
        boolean isSetAnnotation2 = statsEvent.isSetAnnotation();
        if ((isSetAnnotation || isSetAnnotation2) && (!isSetAnnotation || !isSetAnnotation2 || !this.annotation.equals(statsEvent.annotation))) {
            return false;
        }
        boolean isSetUser = isSetUser();
        boolean isSetUser2 = statsEvent.isSetUser();
        if ((isSetUser || isSetUser2) && (!isSetUser || !isSetUser2 || !this.user.equals(statsEvent.user))) {
            return false;
        }
        boolean isSetTime = isSetTime();
        boolean isSetTime2 = statsEvent.isSetTime();
        if ((isSetTime || isSetTime2) && (!isSetTime || !isSetTime2 || this.time != statsEvent.time)) {
            return false;
        }
        boolean isSetClientIp = isSetClientIp();
        boolean isSetClientIp2 = statsEvent.isSetClientIp();
        if (isSetClientIp || isSetClientIp2) {
            return isSetClientIp && isSetClientIp2 && this.clientIp == statsEvent.clientIp;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof StatsEvent)) {
            return equals((StatsEvent) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAnnotation() {
        return this.annotation != null;
    }

    public boolean isSetChid() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetClientIp() {
        return this.__isset_bit_vector.get(5);
    }

    public boolean isSetConnpt() {
        return this.connpt != null;
    }

    public boolean isSetHost() {
        return this.host != null;
    }

    public boolean isSetSubvalue() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetTime() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetType() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetUser() {
        return this.user != null;
    }

    public boolean isSetValue() {
        return this.__isset_bit_vector.get(2);
    }

    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            if (readFieldBegin.type == 0) {
                tProtocol.readStructEnd();
                if (!isSetChid()) {
                    throw new TProtocolException("Required field 'chid' was not found in serialized data! Struct: " + toString());
                } else if (!isSetType()) {
                    throw new TProtocolException("Required field 'type' was not found in serialized data! Struct: " + toString());
                } else if (isSetValue()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'value' was not found in serialized data! Struct: " + toString());
                }
            } else {
                switch (readFieldBegin.id) {
                    case 1:
                        if (readFieldBegin.type != 3) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.chid = tProtocol.readByte();
                            setChidIsSet(true);
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
                        if (readFieldBegin.type != 8) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.value = tProtocol.readI32();
                            setValueIsSet(true);
                            break;
                        }
                    case 4:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.connpt = tProtocol.readString();
                            break;
                        }
                    case 5:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.host = tProtocol.readString();
                            break;
                        }
                    case 6:
                        if (readFieldBegin.type != 8) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.subvalue = tProtocol.readI32();
                            setSubvalueIsSet(true);
                            break;
                        }
                    case 7:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.annotation = tProtocol.readString();
                            break;
                        }
                    case 8:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.user = tProtocol.readString();
                            break;
                        }
                    case 9:
                        if (readFieldBegin.type != 8) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.time = tProtocol.readI32();
                            setTimeIsSet(true);
                            break;
                        }
                    case 10:
                        if (readFieldBegin.type != 8) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.clientIp = tProtocol.readI32();
                            setClientIpIsSet(true);
                            break;
                        }
                    default:
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                }
                tProtocol.readFieldEnd();
            }
        }
    }

    public StatsEvent setAnnotation(String str) {
        this.annotation = str;
        return this;
    }

    public StatsEvent setChid(byte b) {
        this.chid = b;
        setChidIsSet(true);
        return this;
    }

    public void setChidIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public void setClientIpIsSet(boolean z) {
        this.__isset_bit_vector.set(5, z);
    }

    public StatsEvent setConnpt(String str) {
        this.connpt = str;
        return this;
    }

    public StatsEvent setHost(String str) {
        this.host = str;
        return this;
    }

    public StatsEvent setSubvalue(int i) {
        this.subvalue = i;
        setSubvalueIsSet(true);
        return this;
    }

    public void setSubvalueIsSet(boolean z) {
        this.__isset_bit_vector.set(3, z);
    }

    public StatsEvent setTime(int i) {
        this.time = i;
        setTimeIsSet(true);
        return this;
    }

    public void setTimeIsSet(boolean z) {
        this.__isset_bit_vector.set(4, z);
    }

    public StatsEvent setType(int i) {
        this.type = i;
        setTypeIsSet(true);
        return this;
    }

    public void setTypeIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public StatsEvent setUser(String str) {
        this.user = str;
        return this;
    }

    public StatsEvent setValue(int i) {
        this.value = i;
        setValueIsSet(true);
        return this;
    }

    public void setValueIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append(this.chid);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.type);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.value);
        sb.append(", ");
        sb.append("connpt:");
        String str = this.connpt;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (isSetHost()) {
            sb.append(", ");
            sb.append("host:");
            String str2 = this.host;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (isSetSubvalue()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.subvalue);
        }
        if (isSetAnnotation()) {
            sb.append(", ");
            sb.append("annotation:");
            String str3 = this.annotation;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (isSetUser()) {
            sb.append(", ");
            sb.append("user:");
            String str4 = this.user;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (isSetTime()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.time);
        }
        if (isSetClientIp()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.clientIp);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.connpt == null) {
            throw new TProtocolException("Required field 'connpt' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(CHID_FIELD_DESC);
        tProtocol.writeByte(this.chid);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
        tProtocol.writeI32(this.type);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(VALUE_FIELD_DESC);
        tProtocol.writeI32(this.value);
        tProtocol.writeFieldEnd();
        if (this.connpt != null) {
            tProtocol.writeFieldBegin(CONNPT_FIELD_DESC);
            tProtocol.writeString(this.connpt);
            tProtocol.writeFieldEnd();
        }
        if (this.host != null && isSetHost()) {
            tProtocol.writeFieldBegin(HOST_FIELD_DESC);
            tProtocol.writeString(this.host);
            tProtocol.writeFieldEnd();
        }
        if (isSetSubvalue()) {
            tProtocol.writeFieldBegin(SUBVALUE_FIELD_DESC);
            tProtocol.writeI32(this.subvalue);
            tProtocol.writeFieldEnd();
        }
        if (this.annotation != null && isSetAnnotation()) {
            tProtocol.writeFieldBegin(ANNOTATION_FIELD_DESC);
            tProtocol.writeString(this.annotation);
            tProtocol.writeFieldEnd();
        }
        if (this.user != null && isSetUser()) {
            tProtocol.writeFieldBegin(USER_FIELD_DESC);
            tProtocol.writeString(this.user);
            tProtocol.writeFieldEnd();
        }
        if (isSetTime()) {
            tProtocol.writeFieldBegin(TIME_FIELD_DESC);
            tProtocol.writeI32(this.time);
            tProtocol.writeFieldEnd();
        }
        if (isSetClientIp()) {
            tProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            tProtocol.writeI32(this.clientIp);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
