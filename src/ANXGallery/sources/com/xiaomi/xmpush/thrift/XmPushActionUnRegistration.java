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

public class XmPushActionUnRegistration implements Serializable, Cloneable, TBase<XmPushActionUnRegistration, Object> {
    private static final TField ALIAS_NAME_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField APP_VERSION_FIELD_DESC = new TField("", (byte) 11, 6);
    private static final TField CREATED_TS_FIELD_DESC = new TField("", (byte) 10, 12);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField DEVICE_ID_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField NEED_ACK_FIELD_DESC = new TField("", (byte) 2, 11);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField REG_ID_FIELD_DESC = new TField("", (byte) 11, 5);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionUnRegistration");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField TOKEN_FIELD_DESC = new TField("", (byte) 11, 8);
    private BitSet __isset_bit_vector = new BitSet(2);
    public String aliasName;
    public String appId;
    public String appVersion;
    public long createdTs;
    public String debug;
    public String deviceId;
    public String id;
    public boolean needAck = true;
    public String packageName;
    public String regId;
    public Target target;
    public String token;

    public int compareTo(XmPushActionUnRegistration xmPushActionUnRegistration) {
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
        int compareTo11;
        int compareTo12;
        if (!getClass().equals(xmPushActionUnRegistration.getClass())) {
            return getClass().getName().compareTo(xmPushActionUnRegistration.getClass().getName());
        }
        int compareTo13 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetDebug()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetDebug() && (compareTo12 = TBaseHelper.compareTo(this.debug, xmPushActionUnRegistration.debug)) != 0) {
            return compareTo12;
        }
        int compareTo14 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetTarget()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetTarget() && (compareTo11 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionUnRegistration.target)) != 0) {
            return compareTo11;
        }
        int compareTo15 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetId()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetId() && (compareTo10 = TBaseHelper.compareTo(this.id, xmPushActionUnRegistration.id)) != 0) {
            return compareTo10;
        }
        int compareTo16 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetAppId()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetAppId() && (compareTo9 = TBaseHelper.compareTo(this.appId, xmPushActionUnRegistration.appId)) != 0) {
            return compareTo9;
        }
        int compareTo17 = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetRegId()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetRegId() && (compareTo8 = TBaseHelper.compareTo(this.regId, xmPushActionUnRegistration.regId)) != 0) {
            return compareTo8;
        }
        int compareTo18 = Boolean.valueOf(isSetAppVersion()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetAppVersion()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetAppVersion() && (compareTo7 = TBaseHelper.compareTo(this.appVersion, xmPushActionUnRegistration.appVersion)) != 0) {
            return compareTo7;
        }
        int compareTo19 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetPackageName()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetPackageName() && (compareTo6 = TBaseHelper.compareTo(this.packageName, xmPushActionUnRegistration.packageName)) != 0) {
            return compareTo6;
        }
        int compareTo20 = Boolean.valueOf(isSetToken()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetToken()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetToken() && (compareTo5 = TBaseHelper.compareTo(this.token, xmPushActionUnRegistration.token)) != 0) {
            return compareTo5;
        }
        int compareTo21 = Boolean.valueOf(isSetDeviceId()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetDeviceId()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetDeviceId() && (compareTo4 = TBaseHelper.compareTo(this.deviceId, xmPushActionUnRegistration.deviceId)) != 0) {
            return compareTo4;
        }
        int compareTo22 = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetAliasName()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetAliasName() && (compareTo3 = TBaseHelper.compareTo(this.aliasName, xmPushActionUnRegistration.aliasName)) != 0) {
            return compareTo3;
        }
        int compareTo23 = Boolean.valueOf(isSetNeedAck()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetNeedAck()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (isSetNeedAck() && (compareTo2 = TBaseHelper.compareTo(this.needAck, xmPushActionUnRegistration.needAck)) != 0) {
            return compareTo2;
        }
        int compareTo24 = Boolean.valueOf(isSetCreatedTs()).compareTo(Boolean.valueOf(xmPushActionUnRegistration.isSetCreatedTs()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (!isSetCreatedTs() || (compareTo = TBaseHelper.compareTo(this.createdTs, xmPushActionUnRegistration.createdTs)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionUnRegistration xmPushActionUnRegistration) {
        if (xmPushActionUnRegistration == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionUnRegistration.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionUnRegistration.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionUnRegistration.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionUnRegistration.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionUnRegistration.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionUnRegistration.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionUnRegistration.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionUnRegistration.appId))) {
            return false;
        }
        boolean isSetRegId = isSetRegId();
        boolean isSetRegId2 = xmPushActionUnRegistration.isSetRegId();
        if ((isSetRegId || isSetRegId2) && (!isSetRegId || !isSetRegId2 || !this.regId.equals(xmPushActionUnRegistration.regId))) {
            return false;
        }
        boolean isSetAppVersion = isSetAppVersion();
        boolean isSetAppVersion2 = xmPushActionUnRegistration.isSetAppVersion();
        if ((isSetAppVersion || isSetAppVersion2) && (!isSetAppVersion || !isSetAppVersion2 || !this.appVersion.equals(xmPushActionUnRegistration.appVersion))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionUnRegistration.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionUnRegistration.packageName))) {
            return false;
        }
        boolean isSetToken = isSetToken();
        boolean isSetToken2 = xmPushActionUnRegistration.isSetToken();
        if ((isSetToken || isSetToken2) && (!isSetToken || !isSetToken2 || !this.token.equals(xmPushActionUnRegistration.token))) {
            return false;
        }
        boolean isSetDeviceId = isSetDeviceId();
        boolean isSetDeviceId2 = xmPushActionUnRegistration.isSetDeviceId();
        if ((isSetDeviceId || isSetDeviceId2) && (!isSetDeviceId || !isSetDeviceId2 || !this.deviceId.equals(xmPushActionUnRegistration.deviceId))) {
            return false;
        }
        boolean isSetAliasName = isSetAliasName();
        boolean isSetAliasName2 = xmPushActionUnRegistration.isSetAliasName();
        if ((isSetAliasName || isSetAliasName2) && (!isSetAliasName || !isSetAliasName2 || !this.aliasName.equals(xmPushActionUnRegistration.aliasName))) {
            return false;
        }
        boolean isSetNeedAck = isSetNeedAck();
        boolean isSetNeedAck2 = xmPushActionUnRegistration.isSetNeedAck();
        if ((isSetNeedAck || isSetNeedAck2) && (!isSetNeedAck || !isSetNeedAck2 || this.needAck != xmPushActionUnRegistration.needAck)) {
            return false;
        }
        boolean isSetCreatedTs = isSetCreatedTs();
        boolean isSetCreatedTs2 = xmPushActionUnRegistration.isSetCreatedTs();
        if (isSetCreatedTs || isSetCreatedTs2) {
            return isSetCreatedTs && isSetCreatedTs2 && this.createdTs == xmPushActionUnRegistration.createdTs;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionUnRegistration)) {
            return equals((XmPushActionUnRegistration) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAliasName() {
        return this.aliasName != null;
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public boolean isSetAppVersion() {
        return this.appVersion != null;
    }

    public boolean isSetCreatedTs() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetDebug() {
        return this.debug != null;
    }

    public boolean isSetDeviceId() {
        return this.deviceId != null;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetNeedAck() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public boolean isSetToken() {
        return this.token != null;
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
                        this.regId = tProtocol.readString();
                        break;
                    }
                case 6:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.appVersion = tProtocol.readString();
                        break;
                    }
                case 7:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                case 8:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.token = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.deviceId = tProtocol.readString();
                        break;
                    }
                case 10:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                case 11:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.needAck = tProtocol.readBool();
                        setNeedAckIsSet(true);
                        break;
                    }
                case 12:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.createdTs = tProtocol.readI64();
                        setCreatedTsIsSet(true);
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public XmPushActionUnRegistration setAppId(String str) {
        this.appId = str;
        return this;
    }

    public void setCreatedTsIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public XmPushActionUnRegistration setId(String str) {
        this.id = str;
        return this;
    }

    public void setNeedAckIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public XmPushActionUnRegistration setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public XmPushActionUnRegistration setRegId(String str) {
        this.regId = str;
        return this;
    }

    public XmPushActionUnRegistration setToken(String str) {
        this.token = str;
        return this;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
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
        if (isSetRegId()) {
            sb.append(", ");
            sb.append("regId:");
            String str4 = this.regId;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (isSetAppVersion()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str5 = this.appVersion;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
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
        if (isSetToken()) {
            sb.append(", ");
            sb.append("token:");
            String str7 = this.token;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (isSetDeviceId()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str8 = this.deviceId;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (isSetAliasName()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.aliasName;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (isSetNeedAck()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.needAck);
        }
        if (isSetCreatedTs()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.createdTs);
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
        if (this.regId != null && isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.appVersion != null && isSetAppVersion()) {
            tProtocol.writeFieldBegin(APP_VERSION_FIELD_DESC);
            tProtocol.writeString(this.appVersion);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.token != null && isSetToken()) {
            tProtocol.writeFieldBegin(TOKEN_FIELD_DESC);
            tProtocol.writeString(this.token);
            tProtocol.writeFieldEnd();
        }
        if (this.deviceId != null && isSetDeviceId()) {
            tProtocol.writeFieldBegin(DEVICE_ID_FIELD_DESC);
            tProtocol.writeString(this.deviceId);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
            tProtocol.writeFieldEnd();
        }
        if (isSetNeedAck()) {
            tProtocol.writeFieldBegin(NEED_ACK_FIELD_DESC);
            tProtocol.writeBool(this.needAck);
            tProtocol.writeFieldEnd();
        }
        if (isSetCreatedTs()) {
            tProtocol.writeFieldBegin(CREATED_TS_FIELD_DESC);
            tProtocol.writeI64(this.createdTs);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
