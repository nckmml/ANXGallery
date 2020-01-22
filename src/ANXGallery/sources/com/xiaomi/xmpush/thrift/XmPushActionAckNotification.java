package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionAckNotification implements Serializable, Cloneable, TBase<XmPushActionAckNotification, Object> {
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 11);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField ERROR_CODE_FIELD_DESC = new TField("", (byte) 10, 7);
    private static final TField EXTRA_FIELD_DESC = new TField("", (byte) 13, 9);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField REASON_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionAckNotification");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField TYPE_FIELD_DESC = new TField("", (byte) 11, 5);
    private BitSet __isset_bit_vector = new BitSet(1);
    public String appId;
    public String category;
    public String debug;
    public long errorCode = 0;
    public Map<String, String> extra;
    public String id;
    public String packageName;
    public String reason;
    public Target target;
    public String type;

    public int compareTo(XmPushActionAckNotification xmPushActionAckNotification) {
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
        if (!getClass().equals(xmPushActionAckNotification.getClass())) {
            return getClass().getName().compareTo(xmPushActionAckNotification.getClass().getName());
        }
        int compareTo11 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetDebug()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetDebug() && (compareTo10 = TBaseHelper.compareTo(this.debug, xmPushActionAckNotification.debug)) != 0) {
            return compareTo10;
        }
        int compareTo12 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetTarget()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetTarget() && (compareTo9 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionAckNotification.target)) != 0) {
            return compareTo9;
        }
        int compareTo13 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetId()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetId() && (compareTo8 = TBaseHelper.compareTo(this.id, xmPushActionAckNotification.id)) != 0) {
            return compareTo8;
        }
        int compareTo14 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetAppId()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetAppId() && (compareTo7 = TBaseHelper.compareTo(this.appId, xmPushActionAckNotification.appId)) != 0) {
            return compareTo7;
        }
        int compareTo15 = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetType()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetType() && (compareTo6 = TBaseHelper.compareTo(this.type, xmPushActionAckNotification.type)) != 0) {
            return compareTo6;
        }
        int compareTo16 = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetErrorCode()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetErrorCode() && (compareTo5 = TBaseHelper.compareTo(this.errorCode, xmPushActionAckNotification.errorCode)) != 0) {
            return compareTo5;
        }
        int compareTo17 = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetReason()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetReason() && (compareTo4 = TBaseHelper.compareTo(this.reason, xmPushActionAckNotification.reason)) != 0) {
            return compareTo4;
        }
        int compareTo18 = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetExtra()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetExtra() && (compareTo3 = TBaseHelper.compareTo((Map) this.extra, (Map) xmPushActionAckNotification.extra)) != 0) {
            return compareTo3;
        }
        int compareTo19 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetPackageName()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetPackageName() && (compareTo2 = TBaseHelper.compareTo(this.packageName, xmPushActionAckNotification.packageName)) != 0) {
            return compareTo2;
        }
        int compareTo20 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(xmPushActionAckNotification.isSetCategory()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (!isSetCategory() || (compareTo = TBaseHelper.compareTo(this.category, xmPushActionAckNotification.category)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionAckNotification xmPushActionAckNotification) {
        if (xmPushActionAckNotification == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionAckNotification.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionAckNotification.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionAckNotification.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionAckNotification.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionAckNotification.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionAckNotification.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionAckNotification.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionAckNotification.appId))) {
            return false;
        }
        boolean isSetType = isSetType();
        boolean isSetType2 = xmPushActionAckNotification.isSetType();
        if ((isSetType || isSetType2) && (!isSetType || !isSetType2 || !this.type.equals(xmPushActionAckNotification.type))) {
            return false;
        }
        boolean isSetErrorCode = isSetErrorCode();
        boolean isSetErrorCode2 = xmPushActionAckNotification.isSetErrorCode();
        if ((isSetErrorCode || isSetErrorCode2) && (!isSetErrorCode || !isSetErrorCode2 || this.errorCode != xmPushActionAckNotification.errorCode)) {
            return false;
        }
        boolean isSetReason = isSetReason();
        boolean isSetReason2 = xmPushActionAckNotification.isSetReason();
        if ((isSetReason || isSetReason2) && (!isSetReason || !isSetReason2 || !this.reason.equals(xmPushActionAckNotification.reason))) {
            return false;
        }
        boolean isSetExtra = isSetExtra();
        boolean isSetExtra2 = xmPushActionAckNotification.isSetExtra();
        if ((isSetExtra || isSetExtra2) && (!isSetExtra || !isSetExtra2 || !this.extra.equals(xmPushActionAckNotification.extra))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionAckNotification.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionAckNotification.packageName))) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = xmPushActionAckNotification.isSetCategory();
        if (isSetCategory || isSetCategory2) {
            return isSetCategory && isSetCategory2 && this.category.equals(xmPushActionAckNotification.category);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionAckNotification)) {
            return equals((XmPushActionAckNotification) obj);
        }
        return false;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public boolean isSetCategory() {
        return this.category != null;
    }

    public boolean isSetDebug() {
        return this.debug != null;
    }

    public boolean isSetErrorCode() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetExtra() {
        return this.extra != null;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetReason() {
        return this.reason != null;
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public boolean isSetType() {
        return this.type != null;
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
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.debug = tProtocol.readString();
                        break;
                    }
                case 2:
                    if (readFieldBegin.type != 12) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.target = new Target();
                        this.target.read(tProtocol);
                        break;
                    }
                case 3:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.id = tProtocol.readString();
                        break;
                    }
                case 4:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.appId = tProtocol.readString();
                        break;
                    }
                case 5:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.type = tProtocol.readString();
                        break;
                    }
                case 7:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.errorCode = tProtocol.readI64();
                        setErrorCodeIsSet(true);
                        break;
                    }
                case 8:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.reason = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 13) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        TMap readMapBegin = tProtocol.readMapBegin();
                        this.extra = new HashMap(readMapBegin.size * 2);
                        for (int i = 0; i < readMapBegin.size; i++) {
                            this.extra.put(tProtocol.readString(), tProtocol.readString());
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                case 10:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                case 11:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.category = tProtocol.readString();
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setErrorCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        if (isSetDebug()) {
            sb.append("debug:");
            String str = this.debug;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (isSetTarget()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            Target target2 = this.target;
            if (target2 == null) {
                sb.append("null");
            } else {
                sb.append(target2);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str2 = this.id;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (isSetAppId()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.appId;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (isSetType()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.type;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (isSetErrorCode()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.errorCode);
        }
        if (isSetReason()) {
            sb.append(", ");
            sb.append("reason:");
            String str5 = this.reason;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (isSetExtra()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.extra;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (isSetPackageName()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.packageName;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (isSetCategory()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.category;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.debug != null && isSetDebug()) {
            tProtocol.writeFieldBegin(DEBUG_FIELD_DESC);
            tProtocol.writeString(this.debug);
            tProtocol.writeFieldEnd();
        }
        if (this.target != null && isSetTarget()) {
            tProtocol.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.id != null) {
            tProtocol.writeFieldBegin(ID_FIELD_DESC);
            tProtocol.writeString(this.id);
            tProtocol.writeFieldEnd();
        }
        if (this.appId != null && isSetAppId()) {
            tProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
            tProtocol.writeString(this.appId);
            tProtocol.writeFieldEnd();
        }
        if (this.type != null && isSetType()) {
            tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
            tProtocol.writeString(this.type);
            tProtocol.writeFieldEnd();
        }
        if (isSetErrorCode()) {
            tProtocol.writeFieldBegin(ERROR_CODE_FIELD_DESC);
            tProtocol.writeI64(this.errorCode);
            tProtocol.writeFieldEnd();
        }
        if (this.reason != null && isSetReason()) {
            tProtocol.writeFieldBegin(REASON_FIELD_DESC);
            tProtocol.writeString(this.reason);
            tProtocol.writeFieldEnd();
        }
        if (this.extra != null && isSetExtra()) {
            tProtocol.writeFieldBegin(EXTRA_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap((byte) 11, (byte) 11, this.extra.size()));
            for (Map.Entry next : this.extra.entrySet()) {
                tProtocol.writeString((String) next.getKey());
                tProtocol.writeString((String) next.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.category != null && isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
