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

public class XmPushActionRegistrationResult implements Serializable, Cloneable, TBase<XmPushActionRegistrationResult, Object> {
    private static final TField ALIAS_NAME_FIELD_DESC = new TField("", (byte) 11, 12);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField APP_VERSION_CODE_FIELD_DESC = new TField("", (byte) 8, 18);
    private static final TField APP_VERSION_FIELD_DESC = new TField("", (byte) 11, 15);
    private static final TField CLIENT_ID_FIELD_DESC = new TField("", (byte) 11, 13);
    private static final TField COST_TIME_FIELD_DESC = new TField("", (byte) 10, 14);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField ERROR_CODE_FIELD_DESC = new TField("", (byte) 10, 6);
    private static final TField HYBRID_PUSH_ENDPOINT_FIELD_DESC = new TField("", (byte) 11, 17);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField PUSH_SDK_VERSION_CODE_FIELD_DESC = new TField("", (byte) 8, 16);
    private static final TField REASON_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField REGION_FIELD_DESC = new TField("", (byte) 11, 19);
    private static final TField REGISTERED_AT_FIELD_DESC = new TField("", (byte) 10, 11);
    private static final TField REG_ID_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField REG_SECRET_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionRegistrationResult");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private BitSet __isset_bit_vector = new BitSet(5);
    public String aliasName;
    public String appId;
    public String appVersion;
    public int appVersionCode;
    public String clientId;
    public long costTime;
    public String debug;
    public long errorCode;
    public String hybridPushEndpoint;
    public String id;
    public String packageName;
    public int pushSdkVersionCode;
    public String reason;
    public String regId;
    public String regSecret;
    public String region;
    public long registeredAt;
    public Target target;

    public int compareTo(XmPushActionRegistrationResult xmPushActionRegistrationResult) {
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
        int compareTo13;
        int compareTo14;
        int compareTo15;
        int compareTo16;
        int compareTo17;
        int compareTo18;
        if (!getClass().equals(xmPushActionRegistrationResult.getClass())) {
            return getClass().getName().compareTo(xmPushActionRegistrationResult.getClass().getName());
        }
        int compareTo19 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetDebug()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetDebug() && (compareTo18 = TBaseHelper.compareTo(this.debug, xmPushActionRegistrationResult.debug)) != 0) {
            return compareTo18;
        }
        int compareTo20 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetTarget()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetTarget() && (compareTo17 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionRegistrationResult.target)) != 0) {
            return compareTo17;
        }
        int compareTo21 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetId()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetId() && (compareTo16 = TBaseHelper.compareTo(this.id, xmPushActionRegistrationResult.id)) != 0) {
            return compareTo16;
        }
        int compareTo22 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetAppId()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetAppId() && (compareTo15 = TBaseHelper.compareTo(this.appId, xmPushActionRegistrationResult.appId)) != 0) {
            return compareTo15;
        }
        int compareTo23 = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetErrorCode()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (isSetErrorCode() && (compareTo14 = TBaseHelper.compareTo(this.errorCode, xmPushActionRegistrationResult.errorCode)) != 0) {
            return compareTo14;
        }
        int compareTo24 = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetReason()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (isSetReason() && (compareTo13 = TBaseHelper.compareTo(this.reason, xmPushActionRegistrationResult.reason)) != 0) {
            return compareTo13;
        }
        int compareTo25 = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetRegId()));
        if (compareTo25 != 0) {
            return compareTo25;
        }
        if (isSetRegId() && (compareTo12 = TBaseHelper.compareTo(this.regId, xmPushActionRegistrationResult.regId)) != 0) {
            return compareTo12;
        }
        int compareTo26 = Boolean.valueOf(isSetRegSecret()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetRegSecret()));
        if (compareTo26 != 0) {
            return compareTo26;
        }
        if (isSetRegSecret() && (compareTo11 = TBaseHelper.compareTo(this.regSecret, xmPushActionRegistrationResult.regSecret)) != 0) {
            return compareTo11;
        }
        int compareTo27 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetPackageName()));
        if (compareTo27 != 0) {
            return compareTo27;
        }
        if (isSetPackageName() && (compareTo10 = TBaseHelper.compareTo(this.packageName, xmPushActionRegistrationResult.packageName)) != 0) {
            return compareTo10;
        }
        int compareTo28 = Boolean.valueOf(isSetRegisteredAt()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetRegisteredAt()));
        if (compareTo28 != 0) {
            return compareTo28;
        }
        if (isSetRegisteredAt() && (compareTo9 = TBaseHelper.compareTo(this.registeredAt, xmPushActionRegistrationResult.registeredAt)) != 0) {
            return compareTo9;
        }
        int compareTo29 = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetAliasName()));
        if (compareTo29 != 0) {
            return compareTo29;
        }
        if (isSetAliasName() && (compareTo8 = TBaseHelper.compareTo(this.aliasName, xmPushActionRegistrationResult.aliasName)) != 0) {
            return compareTo8;
        }
        int compareTo30 = Boolean.valueOf(isSetClientId()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetClientId()));
        if (compareTo30 != 0) {
            return compareTo30;
        }
        if (isSetClientId() && (compareTo7 = TBaseHelper.compareTo(this.clientId, xmPushActionRegistrationResult.clientId)) != 0) {
            return compareTo7;
        }
        int compareTo31 = Boolean.valueOf(isSetCostTime()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetCostTime()));
        if (compareTo31 != 0) {
            return compareTo31;
        }
        if (isSetCostTime() && (compareTo6 = TBaseHelper.compareTo(this.costTime, xmPushActionRegistrationResult.costTime)) != 0) {
            return compareTo6;
        }
        int compareTo32 = Boolean.valueOf(isSetAppVersion()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetAppVersion()));
        if (compareTo32 != 0) {
            return compareTo32;
        }
        if (isSetAppVersion() && (compareTo5 = TBaseHelper.compareTo(this.appVersion, xmPushActionRegistrationResult.appVersion)) != 0) {
            return compareTo5;
        }
        int compareTo33 = Boolean.valueOf(isSetPushSdkVersionCode()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetPushSdkVersionCode()));
        if (compareTo33 != 0) {
            return compareTo33;
        }
        if (isSetPushSdkVersionCode() && (compareTo4 = TBaseHelper.compareTo(this.pushSdkVersionCode, xmPushActionRegistrationResult.pushSdkVersionCode)) != 0) {
            return compareTo4;
        }
        int compareTo34 = Boolean.valueOf(isSetHybridPushEndpoint()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetHybridPushEndpoint()));
        if (compareTo34 != 0) {
            return compareTo34;
        }
        if (isSetHybridPushEndpoint() && (compareTo3 = TBaseHelper.compareTo(this.hybridPushEndpoint, xmPushActionRegistrationResult.hybridPushEndpoint)) != 0) {
            return compareTo3;
        }
        int compareTo35 = Boolean.valueOf(isSetAppVersionCode()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetAppVersionCode()));
        if (compareTo35 != 0) {
            return compareTo35;
        }
        if (isSetAppVersionCode() && (compareTo2 = TBaseHelper.compareTo(this.appVersionCode, xmPushActionRegistrationResult.appVersionCode)) != 0) {
            return compareTo2;
        }
        int compareTo36 = Boolean.valueOf(isSetRegion()).compareTo(Boolean.valueOf(xmPushActionRegistrationResult.isSetRegion()));
        if (compareTo36 != 0) {
            return compareTo36;
        }
        if (!isSetRegion() || (compareTo = TBaseHelper.compareTo(this.region, xmPushActionRegistrationResult.region)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionRegistrationResult xmPushActionRegistrationResult) {
        if (xmPushActionRegistrationResult == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionRegistrationResult.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionRegistrationResult.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionRegistrationResult.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionRegistrationResult.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionRegistrationResult.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionRegistrationResult.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionRegistrationResult.isSetAppId();
        if (((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionRegistrationResult.appId))) || this.errorCode != xmPushActionRegistrationResult.errorCode) {
            return false;
        }
        boolean isSetReason = isSetReason();
        boolean isSetReason2 = xmPushActionRegistrationResult.isSetReason();
        if ((isSetReason || isSetReason2) && (!isSetReason || !isSetReason2 || !this.reason.equals(xmPushActionRegistrationResult.reason))) {
            return false;
        }
        boolean isSetRegId = isSetRegId();
        boolean isSetRegId2 = xmPushActionRegistrationResult.isSetRegId();
        if ((isSetRegId || isSetRegId2) && (!isSetRegId || !isSetRegId2 || !this.regId.equals(xmPushActionRegistrationResult.regId))) {
            return false;
        }
        boolean isSetRegSecret = isSetRegSecret();
        boolean isSetRegSecret2 = xmPushActionRegistrationResult.isSetRegSecret();
        if ((isSetRegSecret || isSetRegSecret2) && (!isSetRegSecret || !isSetRegSecret2 || !this.regSecret.equals(xmPushActionRegistrationResult.regSecret))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionRegistrationResult.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionRegistrationResult.packageName))) {
            return false;
        }
        boolean isSetRegisteredAt = isSetRegisteredAt();
        boolean isSetRegisteredAt2 = xmPushActionRegistrationResult.isSetRegisteredAt();
        if ((isSetRegisteredAt || isSetRegisteredAt2) && (!isSetRegisteredAt || !isSetRegisteredAt2 || this.registeredAt != xmPushActionRegistrationResult.registeredAt)) {
            return false;
        }
        boolean isSetAliasName = isSetAliasName();
        boolean isSetAliasName2 = xmPushActionRegistrationResult.isSetAliasName();
        if ((isSetAliasName || isSetAliasName2) && (!isSetAliasName || !isSetAliasName2 || !this.aliasName.equals(xmPushActionRegistrationResult.aliasName))) {
            return false;
        }
        boolean isSetClientId = isSetClientId();
        boolean isSetClientId2 = xmPushActionRegistrationResult.isSetClientId();
        if ((isSetClientId || isSetClientId2) && (!isSetClientId || !isSetClientId2 || !this.clientId.equals(xmPushActionRegistrationResult.clientId))) {
            return false;
        }
        boolean isSetCostTime = isSetCostTime();
        boolean isSetCostTime2 = xmPushActionRegistrationResult.isSetCostTime();
        if ((isSetCostTime || isSetCostTime2) && (!isSetCostTime || !isSetCostTime2 || this.costTime != xmPushActionRegistrationResult.costTime)) {
            return false;
        }
        boolean isSetAppVersion = isSetAppVersion();
        boolean isSetAppVersion2 = xmPushActionRegistrationResult.isSetAppVersion();
        if ((isSetAppVersion || isSetAppVersion2) && (!isSetAppVersion || !isSetAppVersion2 || !this.appVersion.equals(xmPushActionRegistrationResult.appVersion))) {
            return false;
        }
        boolean isSetPushSdkVersionCode = isSetPushSdkVersionCode();
        boolean isSetPushSdkVersionCode2 = xmPushActionRegistrationResult.isSetPushSdkVersionCode();
        if ((isSetPushSdkVersionCode || isSetPushSdkVersionCode2) && (!isSetPushSdkVersionCode || !isSetPushSdkVersionCode2 || this.pushSdkVersionCode != xmPushActionRegistrationResult.pushSdkVersionCode)) {
            return false;
        }
        boolean isSetHybridPushEndpoint = isSetHybridPushEndpoint();
        boolean isSetHybridPushEndpoint2 = xmPushActionRegistrationResult.isSetHybridPushEndpoint();
        if ((isSetHybridPushEndpoint || isSetHybridPushEndpoint2) && (!isSetHybridPushEndpoint || !isSetHybridPushEndpoint2 || !this.hybridPushEndpoint.equals(xmPushActionRegistrationResult.hybridPushEndpoint))) {
            return false;
        }
        boolean isSetAppVersionCode = isSetAppVersionCode();
        boolean isSetAppVersionCode2 = xmPushActionRegistrationResult.isSetAppVersionCode();
        if ((isSetAppVersionCode || isSetAppVersionCode2) && (!isSetAppVersionCode || !isSetAppVersionCode2 || this.appVersionCode != xmPushActionRegistrationResult.appVersionCode)) {
            return false;
        }
        boolean isSetRegion = isSetRegion();
        boolean isSetRegion2 = xmPushActionRegistrationResult.isSetRegion();
        if (isSetRegion || isSetRegion2) {
            return isSetRegion && isSetRegion2 && this.region.equals(xmPushActionRegistrationResult.region);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionRegistrationResult)) {
            return equals((XmPushActionRegistrationResult) obj);
        }
        return false;
    }

    public long getErrorCode() {
        return this.errorCode;
    }

    public String getId() {
        return this.id;
    }

    public String getPackageName() {
        return this.packageName;
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

    public boolean isSetAppVersionCode() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetClientId() {
        return this.clientId != null;
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

    public boolean isSetHybridPushEndpoint() {
        return this.hybridPushEndpoint != null;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetPushSdkVersionCode() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetReason() {
        return this.reason != null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public boolean isSetRegSecret() {
        return this.regSecret != null;
    }

    public boolean isSetRegion() {
        return this.region != null;
    }

    public boolean isSetRegisteredAt() {
        return this.__isset_bit_vector.get(1);
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
                        this.regId = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.regSecret = tProtocol.readString();
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
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.registeredAt = tProtocol.readI64();
                        setRegisteredAtIsSet(true);
                        break;
                    }
                case 12:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                case 13:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.clientId = tProtocol.readString();
                        break;
                    }
                case 14:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.costTime = tProtocol.readI64();
                        setCostTimeIsSet(true);
                        break;
                    }
                case 15:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.appVersion = tProtocol.readString();
                        break;
                    }
                case 16:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.pushSdkVersionCode = tProtocol.readI32();
                        setPushSdkVersionCodeIsSet(true);
                        break;
                    }
                case 17:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.hybridPushEndpoint = tProtocol.readString();
                        break;
                    }
                case 18:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.appVersionCode = tProtocol.readI32();
                        setAppVersionCodeIsSet(true);
                        break;
                    }
                case 19:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.region = tProtocol.readString();
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setAppVersionCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(4, z);
    }

    public void setCostTimeIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public void setErrorCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public void setPushSdkVersionCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(3, z);
    }

    public void setRegisteredAtIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
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
        if (isSetRegId()) {
            sb.append(", ");
            sb.append("regId:");
            String str5 = this.regId;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (isSetRegSecret()) {
            sb.append(", ");
            sb.append("regSecret:");
            String str6 = this.regSecret;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (isSetPackageName()) {
            sb.append(", ");
            sb.append("packageName:");
            String str7 = this.packageName;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (isSetRegisteredAt()) {
            sb.append(", ");
            sb.append("registeredAt:");
            sb.append(this.registeredAt);
        }
        if (isSetAliasName()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str8 = this.aliasName;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (isSetClientId()) {
            sb.append(", ");
            sb.append("clientId:");
            String str9 = this.clientId;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (isSetCostTime()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.costTime);
        }
        if (isSetAppVersion()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str10 = this.appVersion;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (isSetPushSdkVersionCode()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.pushSdkVersionCode);
        }
        if (isSetHybridPushEndpoint()) {
            sb.append(", ");
            sb.append("hybridPushEndpoint:");
            String str11 = this.hybridPushEndpoint;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (isSetAppVersionCode()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.appVersionCode);
        }
        if (isSetRegion()) {
            sb.append(", ");
            sb.append("region:");
            String str12 = this.region;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
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
        if (this.regId != null && isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.regSecret != null && isSetRegSecret()) {
            tProtocol.writeFieldBegin(REG_SECRET_FIELD_DESC);
            tProtocol.writeString(this.regSecret);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (isSetRegisteredAt()) {
            tProtocol.writeFieldBegin(REGISTERED_AT_FIELD_DESC);
            tProtocol.writeI64(this.registeredAt);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
            tProtocol.writeFieldEnd();
        }
        if (this.clientId != null && isSetClientId()) {
            tProtocol.writeFieldBegin(CLIENT_ID_FIELD_DESC);
            tProtocol.writeString(this.clientId);
            tProtocol.writeFieldEnd();
        }
        if (isSetCostTime()) {
            tProtocol.writeFieldBegin(COST_TIME_FIELD_DESC);
            tProtocol.writeI64(this.costTime);
            tProtocol.writeFieldEnd();
        }
        if (this.appVersion != null && isSetAppVersion()) {
            tProtocol.writeFieldBegin(APP_VERSION_FIELD_DESC);
            tProtocol.writeString(this.appVersion);
            tProtocol.writeFieldEnd();
        }
        if (isSetPushSdkVersionCode()) {
            tProtocol.writeFieldBegin(PUSH_SDK_VERSION_CODE_FIELD_DESC);
            tProtocol.writeI32(this.pushSdkVersionCode);
            tProtocol.writeFieldEnd();
        }
        if (this.hybridPushEndpoint != null && isSetHybridPushEndpoint()) {
            tProtocol.writeFieldBegin(HYBRID_PUSH_ENDPOINT_FIELD_DESC);
            tProtocol.writeString(this.hybridPushEndpoint);
            tProtocol.writeFieldEnd();
        }
        if (isSetAppVersionCode()) {
            tProtocol.writeFieldBegin(APP_VERSION_CODE_FIELD_DESC);
            tProtocol.writeI32(this.appVersionCode);
            tProtocol.writeFieldEnd();
        }
        if (this.region != null && isSetRegion()) {
            tProtocol.writeFieldBegin(REGION_FIELD_DESC);
            tProtocol.writeString(this.region);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
