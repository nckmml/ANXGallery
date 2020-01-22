package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionContainer implements Serializable, Cloneable, TBase<XmPushActionContainer, Object> {
    private static final TField ACTION_FIELD_DESC = new TField("", (byte) 8, 1);
    private static final TField APPID_FIELD_DESC = new TField("", (byte) 11, 5);
    private static final TField ENCRYPT_ACTION_FIELD_DESC = new TField("", (byte) 2, 2);
    private static final TField IS_REQUEST_FIELD_DESC = new TField("", (byte) 2, 3);
    private static final TField META_INFO_FIELD_DESC = new TField("", (byte) 12, 8);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 6);
    private static final TField PUSH_ACTION_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionContainer");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 7);
    private BitSet __isset_bit_vector = new BitSet(2);
    public ActionType action;
    public String appid;
    public boolean encryptAction = true;
    public boolean isRequest = true;
    public PushMetaInfo metaInfo;
    public String packageName;
    public ByteBuffer pushAction;
    public Target target;

    public int compareTo(XmPushActionContainer xmPushActionContainer) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        if (!getClass().equals(xmPushActionContainer.getClass())) {
            return getClass().getName().compareTo(xmPushActionContainer.getClass().getName());
        }
        int compareTo9 = Boolean.valueOf(isSetAction()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetAction()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetAction() && (compareTo8 = TBaseHelper.compareTo((Comparable) this.action, (Comparable) xmPushActionContainer.action)) != 0) {
            return compareTo8;
        }
        int compareTo10 = Boolean.valueOf(isSetEncryptAction()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetEncryptAction()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetEncryptAction() && (compareTo7 = TBaseHelper.compareTo(this.encryptAction, xmPushActionContainer.encryptAction)) != 0) {
            return compareTo7;
        }
        int compareTo11 = Boolean.valueOf(isSetIsRequest()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetIsRequest()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetIsRequest() && (compareTo6 = TBaseHelper.compareTo(this.isRequest, xmPushActionContainer.isRequest)) != 0) {
            return compareTo6;
        }
        int compareTo12 = Boolean.valueOf(isSetPushAction()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetPushAction()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetPushAction() && (compareTo5 = TBaseHelper.compareTo((Comparable) this.pushAction, (Comparable) xmPushActionContainer.pushAction)) != 0) {
            return compareTo5;
        }
        int compareTo13 = Boolean.valueOf(isSetAppid()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetAppid()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetAppid() && (compareTo4 = TBaseHelper.compareTo(this.appid, xmPushActionContainer.appid)) != 0) {
            return compareTo4;
        }
        int compareTo14 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetPackageName()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetPackageName() && (compareTo3 = TBaseHelper.compareTo(this.packageName, xmPushActionContainer.packageName)) != 0) {
            return compareTo3;
        }
        int compareTo15 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetTarget()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetTarget() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionContainer.target)) != 0) {
            return compareTo2;
        }
        int compareTo16 = Boolean.valueOf(isSetMetaInfo()).compareTo(Boolean.valueOf(xmPushActionContainer.isSetMetaInfo()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (!isSetMetaInfo() || (compareTo = TBaseHelper.compareTo((Comparable) this.metaInfo, (Comparable) xmPushActionContainer.metaInfo)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionContainer xmPushActionContainer) {
        if (xmPushActionContainer == null) {
            return false;
        }
        boolean isSetAction = isSetAction();
        boolean isSetAction2 = xmPushActionContainer.isSetAction();
        if (((isSetAction || isSetAction2) && (!isSetAction || !isSetAction2 || !this.action.equals(xmPushActionContainer.action))) || this.encryptAction != xmPushActionContainer.encryptAction || this.isRequest != xmPushActionContainer.isRequest) {
            return false;
        }
        boolean isSetPushAction = isSetPushAction();
        boolean isSetPushAction2 = xmPushActionContainer.isSetPushAction();
        if ((isSetPushAction || isSetPushAction2) && (!isSetPushAction || !isSetPushAction2 || !this.pushAction.equals(xmPushActionContainer.pushAction))) {
            return false;
        }
        boolean isSetAppid = isSetAppid();
        boolean isSetAppid2 = xmPushActionContainer.isSetAppid();
        if ((isSetAppid || isSetAppid2) && (!isSetAppid || !isSetAppid2 || !this.appid.equals(xmPushActionContainer.appid))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionContainer.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionContainer.packageName))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionContainer.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionContainer.target))) {
            return false;
        }
        boolean isSetMetaInfo = isSetMetaInfo();
        boolean isSetMetaInfo2 = xmPushActionContainer.isSetMetaInfo();
        if (isSetMetaInfo || isSetMetaInfo2) {
            return isSetMetaInfo && isSetMetaInfo2 && this.metaInfo.equals(xmPushActionContainer.metaInfo);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionContainer)) {
            return equals((XmPushActionContainer) obj);
        }
        return false;
    }

    public ActionType getAction() {
        return this.action;
    }

    public String getAppid() {
        return this.appid;
    }

    public PushMetaInfo getMetaInfo() {
        return this.metaInfo;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public byte[] getPushAction() {
        setPushAction(TBaseHelper.rightSize(this.pushAction));
        return this.pushAction.array();
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEncryptAction() {
        return this.encryptAction;
    }

    public boolean isSetAction() {
        return this.action != null;
    }

    public boolean isSetAppid() {
        return this.appid != null;
    }

    public boolean isSetEncryptAction() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetIsRequest() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetMetaInfo() {
        return this.metaInfo != null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetPushAction() {
        return this.pushAction != null;
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
                if (!isSetEncryptAction()) {
                    throw new TProtocolException("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (isSetIsRequest()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            } else {
                switch (readFieldBegin.id) {
                    case 1:
                        if (readFieldBegin.type != 8) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.action = ActionType.findByValue(tProtocol.readI32());
                            break;
                        }
                    case 2:
                        if (readFieldBegin.type != 2) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.encryptAction = tProtocol.readBool();
                            setEncryptActionIsSet(true);
                            break;
                        }
                    case 3:
                        if (readFieldBegin.type != 2) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.isRequest = tProtocol.readBool();
                            setIsRequestIsSet(true);
                            break;
                        }
                    case 4:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.pushAction = tProtocol.readBinary();
                            break;
                        }
                    case 5:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.appid = tProtocol.readString();
                            break;
                        }
                    case 6:
                        if (readFieldBegin.type != 11) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.packageName = tProtocol.readString();
                            break;
                        }
                    case 7:
                        if (readFieldBegin.type != 12) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.target = new Target();
                            this.target.read(tProtocol);
                            break;
                        }
                    case 8:
                        if (readFieldBegin.type != 12) {
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                        } else {
                            this.metaInfo = new PushMetaInfo();
                            this.metaInfo.read(tProtocol);
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

    public XmPushActionContainer setAction(ActionType actionType) {
        this.action = actionType;
        return this;
    }

    public XmPushActionContainer setAppid(String str) {
        this.appid = str;
        return this;
    }

    public XmPushActionContainer setEncryptAction(boolean z) {
        this.encryptAction = z;
        setEncryptActionIsSet(true);
        return this;
    }

    public void setEncryptActionIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public XmPushActionContainer setIsRequest(boolean z) {
        this.isRequest = z;
        setIsRequestIsSet(true);
        return this;
    }

    public void setIsRequestIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public XmPushActionContainer setMetaInfo(PushMetaInfo pushMetaInfo) {
        this.metaInfo = pushMetaInfo;
        return this;
    }

    public XmPushActionContainer setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public XmPushActionContainer setPushAction(ByteBuffer byteBuffer) {
        this.pushAction = byteBuffer;
        return this;
    }

    public XmPushActionContainer setTarget(Target target2) {
        this.target = target2;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        ActionType actionType = this.action;
        if (actionType == null) {
            sb.append("null");
        } else {
            sb.append(actionType);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.encryptAction);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.isRequest);
        sb.append(", ");
        sb.append("pushAction:");
        ByteBuffer byteBuffer = this.pushAction;
        if (byteBuffer == null) {
            sb.append("null");
        } else {
            TBaseHelper.toString(byteBuffer, sb);
        }
        if (isSetAppid()) {
            sb.append(", ");
            sb.append("appid:");
            String str = this.appid;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (isSetPackageName()) {
            sb.append(", ");
            sb.append("packageName:");
            String str2 = this.packageName;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("target:");
        Target target2 = this.target;
        if (target2 == null) {
            sb.append("null");
        } else {
            sb.append(target2);
        }
        if (isSetMetaInfo()) {
            sb.append(", ");
            sb.append("metaInfo:");
            PushMetaInfo pushMetaInfo = this.metaInfo;
            if (pushMetaInfo == null) {
                sb.append("null");
            } else {
                sb.append(pushMetaInfo);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.action == null) {
            throw new TProtocolException("Required field 'action' was not present! Struct: " + toString());
        } else if (this.pushAction == null) {
            throw new TProtocolException("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.target == null) {
            throw new TProtocolException("Required field 'target' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.action != null) {
            tProtocol.writeFieldBegin(ACTION_FIELD_DESC);
            tProtocol.writeI32(this.action.getValue());
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(ENCRYPT_ACTION_FIELD_DESC);
        tProtocol.writeBool(this.encryptAction);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(IS_REQUEST_FIELD_DESC);
        tProtocol.writeBool(this.isRequest);
        tProtocol.writeFieldEnd();
        if (this.pushAction != null) {
            tProtocol.writeFieldBegin(PUSH_ACTION_FIELD_DESC);
            tProtocol.writeBinary(this.pushAction);
            tProtocol.writeFieldEnd();
        }
        if (this.appid != null && isSetAppid()) {
            tProtocol.writeFieldBegin(APPID_FIELD_DESC);
            tProtocol.writeString(this.appid);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.target != null) {
            tProtocol.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.metaInfo != null && isSetMetaInfo()) {
            tProtocol.writeFieldBegin(META_INFO_FIELD_DESC);
            this.metaInfo.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
