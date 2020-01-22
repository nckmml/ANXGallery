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

public class XmPushActionUnRegistrationResult implements Serializable, Cloneable, TBase<XmPushActionUnRegistrationResult, Object> {
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField COST_TIME_FIELD_DESC = new TField("", (byte) 10, 10);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField ERROR_CODE_FIELD_DESC = new TField("", (byte) 10, 6);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField REASON_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionUnRegistrationResult");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField UN_REGISTERED_AT_FIELD_DESC = new TField("", (byte) 10, 9);
    private BitSet __isset_bit_vector = new BitSet(3);
    public String appId;
    public long costTime;
    public String debug;
    public long errorCode;
    public String id;
    public String packageName;
    public String reason;
    public Target target;
    public long unRegisteredAt;

    public int compareTo(XmPushActionUnRegistrationResult xmPushActionUnRegistrationResult) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        int compareTo9;
        if (!getClass().equals(xmPushActionUnRegistrationResult.getClass())) {
            return getClass().getName().compareTo(xmPushActionUnRegistrationResult.getClass().getName());
        }
        int compareTo10 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetDebug()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetDebug() && (compareTo9 = TBaseHelper.compareTo(this.debug, xmPushActionUnRegistrationResult.debug)) != 0) {
            return compareTo9;
        }
        int compareTo11 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetTarget()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetTarget() && (compareTo8 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionUnRegistrationResult.target)) != 0) {
            return compareTo8;
        }
        int compareTo12 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetId()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetId() && (compareTo7 = TBaseHelper.compareTo(this.id, xmPushActionUnRegistrationResult.id)) != 0) {
            return compareTo7;
        }
        int compareTo13 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetAppId()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetAppId() && (compareTo6 = TBaseHelper.compareTo(this.appId, xmPushActionUnRegistrationResult.appId)) != 0) {
            return compareTo6;
        }
        int compareTo14 = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetErrorCode()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetErrorCode() && (compareTo5 = TBaseHelper.compareTo(this.errorCode, xmPushActionUnRegistrationResult.errorCode)) != 0) {
            return compareTo5;
        }
        int compareTo15 = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetReason()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetReason() && (compareTo4 = TBaseHelper.compareTo(this.reason, xmPushActionUnRegistrationResult.reason)) != 0) {
            return compareTo4;
        }
        int compareTo16 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetPackageName()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetPackageName() && (compareTo3 = TBaseHelper.compareTo(this.packageName, xmPushActionUnRegistrationResult.packageName)) != 0) {
            return compareTo3;
        }
        int compareTo17 = Boolean.valueOf(isSetUnRegisteredAt()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetUnRegisteredAt()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetUnRegisteredAt() && (compareTo2 = TBaseHelper.compareTo(this.unRegisteredAt, xmPushActionUnRegistrationResult.unRegisteredAt)) != 0) {
            return compareTo2;
        }
        int compareTo18 = Boolean.valueOf(isSetCostTime()).compareTo(Boolean.valueOf(xmPushActionUnRegistrationResult.isSetCostTime()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (!isSetCostTime() || (compareTo = TBaseHelper.compareTo(this.costTime, xmPushActionUnRegistrationResult.costTime)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionUnRegistrationResult xmPushActionUnRegistrationResult) {
        if (xmPushActionUnRegistrationResult == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionUnRegistrationResult.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionUnRegistrationResult.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionUnRegistrationResult.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionUnRegistrationResult.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionUnRegistrationResult.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionUnRegistrationResult.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionUnRegistrationResult.isSetAppId();
        if (((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionUnRegistrationResult.appId))) || this.errorCode != xmPushActionUnRegistrationResult.errorCode) {
            return false;
        }
        boolean isSetReason = isSetReason();
        boolean isSetReason2 = xmPushActionUnRegistrationResult.isSetReason();
        if ((isSetReason || isSetReason2) && (!isSetReason || !isSetReason2 || !this.reason.equals(xmPushActionUnRegistrationResult.reason))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionUnRegistrationResult.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionUnRegistrationResult.packageName))) {
            return false;
        }
        boolean isSetUnRegisteredAt = isSetUnRegisteredAt();
        boolean isSetUnRegisteredAt2 = xmPushActionUnRegistrationResult.isSetUnRegisteredAt();
        if ((isSetUnRegisteredAt || isSetUnRegisteredAt2) && (!isSetUnRegisteredAt || !isSetUnRegisteredAt2 || this.unRegisteredAt != xmPushActionUnRegistrationResult.unRegisteredAt)) {
            return false;
        }
        boolean isSetCostTime = isSetCostTime();
        boolean isSetCostTime2 = xmPushActionUnRegistrationResult.isSetCostTime();
        if (isSetCostTime || isSetCostTime2) {
            return isSetCostTime && isSetCostTime2 && this.costTime == xmPushActionUnRegistrationResult.costTime;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionUnRegistrationResult)) {
            return equals((XmPushActionUnRegistrationResult) obj);
        }
        return false;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public boolean isSetCostTime() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetDebug() {
        return this.debug != null;
    }

    public boolean isSetErrorCode() {
        return this.__isset_bit_vector.get(0);
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

    public boolean isSetUnRegisteredAt() {
        return this.__isset_bit_vector.get(1);
    }

    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            if (readFieldBegin.type == 0) {
                tProtocol.readStructEnd();
                if (isSetErrorCode()) {
                    validate();
                    return;
                }
                throw new TProtocolException("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
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
                case 6:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.errorCode = tProtocol.readI64();
                        setErrorCodeIsSet(true);
                        break;
                    }
                case 7:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.reason = tProtocol.readString();
                        break;
                    }
                case 8:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.unRegisteredAt = tProtocol.readI64();
                        setUnRegisteredAtIsSet(true);
                        break;
                    }
                case 10:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.costTime = tProtocol.readI64();
                        setCostTimeIsSet(true);
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setCostTimeIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public void setErrorCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public void setUnRegisteredAtIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
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
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.appId;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.errorCode);
        if (isSetReason()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.reason;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (isSetPackageName()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.packageName;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (isSetUnRegisteredAt()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.unRegisteredAt);
        }
        if (isSetCostTime()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.costTime);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.appId == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
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
        if (this.appId != null) {
            tProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
            tProtocol.writeString(this.appId);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(ERROR_CODE_FIELD_DESC);
        tProtocol.writeI64(this.errorCode);
        tProtocol.writeFieldEnd();
        if (this.reason != null && isSetReason()) {
            tProtocol.writeFieldBegin(REASON_FIELD_DESC);
            tProtocol.writeString(this.reason);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (isSetUnRegisteredAt()) {
            tProtocol.writeFieldBegin(UN_REGISTERED_AT_FIELD_DESC);
            tProtocol.writeI64(this.unRegisteredAt);
            tProtocol.writeFieldEnd();
        }
        if (isSetCostTime()) {
            tProtocol.writeFieldBegin(COST_TIME_FIELD_DESC);
            tProtocol.writeI64(this.costTime);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
