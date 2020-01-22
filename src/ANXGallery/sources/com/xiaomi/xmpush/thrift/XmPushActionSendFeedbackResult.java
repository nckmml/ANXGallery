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

public class XmPushActionSendFeedbackResult implements Serializable, Cloneable, TBase<XmPushActionSendFeedbackResult, Object> {
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField ERROR_CODE_FIELD_DESC = new TField("", (byte) 10, 6);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField REASON_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionSendFeedbackResult");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private BitSet __isset_bit_vector = new BitSet(1);
    public String appId;
    public String category;
    public String debug;
    public long errorCode;
    public String id;
    public String reason;
    public Target target;

    public int compareTo(XmPushActionSendFeedbackResult xmPushActionSendFeedbackResult) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        if (!getClass().equals(xmPushActionSendFeedbackResult.getClass())) {
            return getClass().getName().compareTo(xmPushActionSendFeedbackResult.getClass().getName());
        }
        int compareTo8 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetDebug()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetDebug() && (compareTo7 = TBaseHelper.compareTo(this.debug, xmPushActionSendFeedbackResult.debug)) != 0) {
            return compareTo7;
        }
        int compareTo9 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetTarget()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetTarget() && (compareTo6 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionSendFeedbackResult.target)) != 0) {
            return compareTo6;
        }
        int compareTo10 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetId()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetId() && (compareTo5 = TBaseHelper.compareTo(this.id, xmPushActionSendFeedbackResult.id)) != 0) {
            return compareTo5;
        }
        int compareTo11 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetAppId()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetAppId() && (compareTo4 = TBaseHelper.compareTo(this.appId, xmPushActionSendFeedbackResult.appId)) != 0) {
            return compareTo4;
        }
        int compareTo12 = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetErrorCode()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetErrorCode() && (compareTo3 = TBaseHelper.compareTo(this.errorCode, xmPushActionSendFeedbackResult.errorCode)) != 0) {
            return compareTo3;
        }
        int compareTo13 = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetReason()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetReason() && (compareTo2 = TBaseHelper.compareTo(this.reason, xmPushActionSendFeedbackResult.reason)) != 0) {
            return compareTo2;
        }
        int compareTo14 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(xmPushActionSendFeedbackResult.isSetCategory()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (!isSetCategory() || (compareTo = TBaseHelper.compareTo(this.category, xmPushActionSendFeedbackResult.category)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionSendFeedbackResult xmPushActionSendFeedbackResult) {
        if (xmPushActionSendFeedbackResult == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionSendFeedbackResult.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionSendFeedbackResult.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionSendFeedbackResult.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionSendFeedbackResult.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionSendFeedbackResult.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionSendFeedbackResult.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionSendFeedbackResult.isSetAppId();
        if (((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionSendFeedbackResult.appId))) || this.errorCode != xmPushActionSendFeedbackResult.errorCode) {
            return false;
        }
        boolean isSetReason = isSetReason();
        boolean isSetReason2 = xmPushActionSendFeedbackResult.isSetReason();
        if ((isSetReason || isSetReason2) && (!isSetReason || !isSetReason2 || !this.reason.equals(xmPushActionSendFeedbackResult.reason))) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = xmPushActionSendFeedbackResult.isSetCategory();
        if (isSetCategory || isSetCategory2) {
            return isSetCategory && isSetCategory2 && this.category.equals(xmPushActionSendFeedbackResult.category);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionSendFeedbackResult)) {
            return equals((XmPushActionSendFeedbackResult) obj);
        }
        return false;
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

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetReason() {
        return this.reason != null;
    }

    public boolean isSetTarget() {
        return this.target != null;
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
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
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
        if (isSetCategory()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.category;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
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
        if (this.category != null && isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
