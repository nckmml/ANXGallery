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

public class XmPushActionRegistration implements Serializable, Cloneable, TBase<XmPushActionRegistration, Object> {
    private static final TField ALIAS_NAME_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TField ANDROID_ID_FIELD_DESC = new TField("", (byte) 11, 15);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField APP_VERSION_CODE_FIELD_DESC = new TField("", (byte) 8, 14);
    private static final TField APP_VERSION_FIELD_DESC = new TField("", (byte) 11, 5);
    private static final TField CLEAN_OLD_REG_INFO_FIELD_DESC = new TField("", (byte) 2, 101);
    private static final TField CONNECTION_ATTRS_FIELD_DESC = new TField("", (byte) 13, 100);
    private static final TField CREATED_TS_FIELD_DESC = new TField("", (byte) 10, 23);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField DEVICE_ID_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField IMEI_FIELD_DESC = new TField("", (byte) 11, 16);
    private static final TField IMEI_MD5_FIELD_DESC = new TField("", (byte) 11, 18);
    private static final TField MIID_FIELD_DESC = new TField("", (byte) 10, 22);
    private static final TField OLD_REG_ID_FIELD_DESC = new TField("", (byte) 11, 102);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 6);
    private static final TField PUSH_SDK_VERSION_CODE_FIELD_DESC = new TField("", (byte) 8, 13);
    private static final TField PUSH_SDK_VERSION_NAME_FIELD_DESC = new TField("", (byte) 11, 12);
    private static final TField REASON_FIELD_DESC = new TField("", (byte) 8, 20);
    private static final TField REG_ID_FIELD_DESC = new TField("", (byte) 11, 11);
    private static final TField SDK_VERSION_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField SERIAL_FIELD_DESC = new TField("", (byte) 11, 17);
    private static final TField SPACE_ID_FIELD_DESC = new TField("", (byte) 8, 19);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionRegistration");
    private static final TField SUB_IMEI_FIELD_DESC = new TField("", (byte) 11, 24);
    private static final TField SUB_IMEI_MD5_FIELD_DESC = new TField("", (byte) 11, 25);
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField TOKEN_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField VALIDATE_TOKEN_FIELD_DESC = new TField("", (byte) 2, 21);
    private BitSet __isset_bit_vector = new BitSet(7);
    public String aliasName;
    public String androidId;
    public String appId;
    public String appVersion;
    public int appVersionCode;
    public boolean cleanOldRegInfo = false;
    public Map<String, String> connectionAttrs;
    public long createdTs;
    public String debug;
    public String deviceId;
    public String id;
    public String imei;
    public String imeiMd5;
    public long miid;
    public String oldRegId;
    public String packageName;
    public int pushSdkVersionCode;
    public String pushSdkVersionName;
    public RegistrationReason reason;
    public String regId;
    public String sdkVersion;
    public String serial;
    public int spaceId;
    public String subImei;
    public String subImeiMd5;
    public Target target;
    public String token;
    public boolean validateToken = true;

    public int compareTo(XmPushActionRegistration xmPushActionRegistration) {
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
        int compareTo19;
        int compareTo20;
        int compareTo21;
        int compareTo22;
        int compareTo23;
        int compareTo24;
        int compareTo25;
        int compareTo26;
        int compareTo27;
        int compareTo28;
        if (!getClass().equals(xmPushActionRegistration.getClass())) {
            return getClass().getName().compareTo(xmPushActionRegistration.getClass().getName());
        }
        int compareTo29 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetDebug()));
        if (compareTo29 != 0) {
            return compareTo29;
        }
        if (isSetDebug() && (compareTo28 = TBaseHelper.compareTo(this.debug, xmPushActionRegistration.debug)) != 0) {
            return compareTo28;
        }
        int compareTo30 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetTarget()));
        if (compareTo30 != 0) {
            return compareTo30;
        }
        if (isSetTarget() && (compareTo27 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionRegistration.target)) != 0) {
            return compareTo27;
        }
        int compareTo31 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetId()));
        if (compareTo31 != 0) {
            return compareTo31;
        }
        if (isSetId() && (compareTo26 = TBaseHelper.compareTo(this.id, xmPushActionRegistration.id)) != 0) {
            return compareTo26;
        }
        int compareTo32 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetAppId()));
        if (compareTo32 != 0) {
            return compareTo32;
        }
        if (isSetAppId() && (compareTo25 = TBaseHelper.compareTo(this.appId, xmPushActionRegistration.appId)) != 0) {
            return compareTo25;
        }
        int compareTo33 = Boolean.valueOf(isSetAppVersion()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetAppVersion()));
        if (compareTo33 != 0) {
            return compareTo33;
        }
        if (isSetAppVersion() && (compareTo24 = TBaseHelper.compareTo(this.appVersion, xmPushActionRegistration.appVersion)) != 0) {
            return compareTo24;
        }
        int compareTo34 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetPackageName()));
        if (compareTo34 != 0) {
            return compareTo34;
        }
        if (isSetPackageName() && (compareTo23 = TBaseHelper.compareTo(this.packageName, xmPushActionRegistration.packageName)) != 0) {
            return compareTo23;
        }
        int compareTo35 = Boolean.valueOf(isSetToken()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetToken()));
        if (compareTo35 != 0) {
            return compareTo35;
        }
        if (isSetToken() && (compareTo22 = TBaseHelper.compareTo(this.token, xmPushActionRegistration.token)) != 0) {
            return compareTo22;
        }
        int compareTo36 = Boolean.valueOf(isSetDeviceId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetDeviceId()));
        if (compareTo36 != 0) {
            return compareTo36;
        }
        if (isSetDeviceId() && (compareTo21 = TBaseHelper.compareTo(this.deviceId, xmPushActionRegistration.deviceId)) != 0) {
            return compareTo21;
        }
        int compareTo37 = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetAliasName()));
        if (compareTo37 != 0) {
            return compareTo37;
        }
        if (isSetAliasName() && (compareTo20 = TBaseHelper.compareTo(this.aliasName, xmPushActionRegistration.aliasName)) != 0) {
            return compareTo20;
        }
        int compareTo38 = Boolean.valueOf(isSetSdkVersion()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetSdkVersion()));
        if (compareTo38 != 0) {
            return compareTo38;
        }
        if (isSetSdkVersion() && (compareTo19 = TBaseHelper.compareTo(this.sdkVersion, xmPushActionRegistration.sdkVersion)) != 0) {
            return compareTo19;
        }
        int compareTo39 = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetRegId()));
        if (compareTo39 != 0) {
            return compareTo39;
        }
        if (isSetRegId() && (compareTo18 = TBaseHelper.compareTo(this.regId, xmPushActionRegistration.regId)) != 0) {
            return compareTo18;
        }
        int compareTo40 = Boolean.valueOf(isSetPushSdkVersionName()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetPushSdkVersionName()));
        if (compareTo40 != 0) {
            return compareTo40;
        }
        if (isSetPushSdkVersionName() && (compareTo17 = TBaseHelper.compareTo(this.pushSdkVersionName, xmPushActionRegistration.pushSdkVersionName)) != 0) {
            return compareTo17;
        }
        int compareTo41 = Boolean.valueOf(isSetPushSdkVersionCode()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetPushSdkVersionCode()));
        if (compareTo41 != 0) {
            return compareTo41;
        }
        if (isSetPushSdkVersionCode() && (compareTo16 = TBaseHelper.compareTo(this.pushSdkVersionCode, xmPushActionRegistration.pushSdkVersionCode)) != 0) {
            return compareTo16;
        }
        int compareTo42 = Boolean.valueOf(isSetAppVersionCode()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetAppVersionCode()));
        if (compareTo42 != 0) {
            return compareTo42;
        }
        if (isSetAppVersionCode() && (compareTo15 = TBaseHelper.compareTo(this.appVersionCode, xmPushActionRegistration.appVersionCode)) != 0) {
            return compareTo15;
        }
        int compareTo43 = Boolean.valueOf(isSetAndroidId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetAndroidId()));
        if (compareTo43 != 0) {
            return compareTo43;
        }
        if (isSetAndroidId() && (compareTo14 = TBaseHelper.compareTo(this.androidId, xmPushActionRegistration.androidId)) != 0) {
            return compareTo14;
        }
        int compareTo44 = Boolean.valueOf(isSetImei()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetImei()));
        if (compareTo44 != 0) {
            return compareTo44;
        }
        if (isSetImei() && (compareTo13 = TBaseHelper.compareTo(this.imei, xmPushActionRegistration.imei)) != 0) {
            return compareTo13;
        }
        int compareTo45 = Boolean.valueOf(isSetSerial()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetSerial()));
        if (compareTo45 != 0) {
            return compareTo45;
        }
        if (isSetSerial() && (compareTo12 = TBaseHelper.compareTo(this.serial, xmPushActionRegistration.serial)) != 0) {
            return compareTo12;
        }
        int compareTo46 = Boolean.valueOf(isSetImeiMd5()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetImeiMd5()));
        if (compareTo46 != 0) {
            return compareTo46;
        }
        if (isSetImeiMd5() && (compareTo11 = TBaseHelper.compareTo(this.imeiMd5, xmPushActionRegistration.imeiMd5)) != 0) {
            return compareTo11;
        }
        int compareTo47 = Boolean.valueOf(isSetSpaceId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetSpaceId()));
        if (compareTo47 != 0) {
            return compareTo47;
        }
        if (isSetSpaceId() && (compareTo10 = TBaseHelper.compareTo(this.spaceId, xmPushActionRegistration.spaceId)) != 0) {
            return compareTo10;
        }
        int compareTo48 = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetReason()));
        if (compareTo48 != 0) {
            return compareTo48;
        }
        if (isSetReason() && (compareTo9 = TBaseHelper.compareTo((Comparable) this.reason, (Comparable) xmPushActionRegistration.reason)) != 0) {
            return compareTo9;
        }
        int compareTo49 = Boolean.valueOf(isSetValidateToken()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetValidateToken()));
        if (compareTo49 != 0) {
            return compareTo49;
        }
        if (isSetValidateToken() && (compareTo8 = TBaseHelper.compareTo(this.validateToken, xmPushActionRegistration.validateToken)) != 0) {
            return compareTo8;
        }
        int compareTo50 = Boolean.valueOf(isSetMiid()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetMiid()));
        if (compareTo50 != 0) {
            return compareTo50;
        }
        if (isSetMiid() && (compareTo7 = TBaseHelper.compareTo(this.miid, xmPushActionRegistration.miid)) != 0) {
            return compareTo7;
        }
        int compareTo51 = Boolean.valueOf(isSetCreatedTs()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetCreatedTs()));
        if (compareTo51 != 0) {
            return compareTo51;
        }
        if (isSetCreatedTs() && (compareTo6 = TBaseHelper.compareTo(this.createdTs, xmPushActionRegistration.createdTs)) != 0) {
            return compareTo6;
        }
        int compareTo52 = Boolean.valueOf(isSetSubImei()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetSubImei()));
        if (compareTo52 != 0) {
            return compareTo52;
        }
        if (isSetSubImei() && (compareTo5 = TBaseHelper.compareTo(this.subImei, xmPushActionRegistration.subImei)) != 0) {
            return compareTo5;
        }
        int compareTo53 = Boolean.valueOf(isSetSubImeiMd5()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetSubImeiMd5()));
        if (compareTo53 != 0) {
            return compareTo53;
        }
        if (isSetSubImeiMd5() && (compareTo4 = TBaseHelper.compareTo(this.subImeiMd5, xmPushActionRegistration.subImeiMd5)) != 0) {
            return compareTo4;
        }
        int compareTo54 = Boolean.valueOf(isSetConnectionAttrs()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetConnectionAttrs()));
        if (compareTo54 != 0) {
            return compareTo54;
        }
        if (isSetConnectionAttrs() && (compareTo3 = TBaseHelper.compareTo((Map) this.connectionAttrs, (Map) xmPushActionRegistration.connectionAttrs)) != 0) {
            return compareTo3;
        }
        int compareTo55 = Boolean.valueOf(isSetCleanOldRegInfo()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetCleanOldRegInfo()));
        if (compareTo55 != 0) {
            return compareTo55;
        }
        if (isSetCleanOldRegInfo() && (compareTo2 = TBaseHelper.compareTo(this.cleanOldRegInfo, xmPushActionRegistration.cleanOldRegInfo)) != 0) {
            return compareTo2;
        }
        int compareTo56 = Boolean.valueOf(isSetOldRegId()).compareTo(Boolean.valueOf(xmPushActionRegistration.isSetOldRegId()));
        if (compareTo56 != 0) {
            return compareTo56;
        }
        if (!isSetOldRegId() || (compareTo = TBaseHelper.compareTo(this.oldRegId, xmPushActionRegistration.oldRegId)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionRegistration xmPushActionRegistration) {
        if (xmPushActionRegistration == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionRegistration.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionRegistration.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionRegistration.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionRegistration.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionRegistration.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionRegistration.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionRegistration.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionRegistration.appId))) {
            return false;
        }
        boolean isSetAppVersion = isSetAppVersion();
        boolean isSetAppVersion2 = xmPushActionRegistration.isSetAppVersion();
        if ((isSetAppVersion || isSetAppVersion2) && (!isSetAppVersion || !isSetAppVersion2 || !this.appVersion.equals(xmPushActionRegistration.appVersion))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionRegistration.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionRegistration.packageName))) {
            return false;
        }
        boolean isSetToken = isSetToken();
        boolean isSetToken2 = xmPushActionRegistration.isSetToken();
        if ((isSetToken || isSetToken2) && (!isSetToken || !isSetToken2 || !this.token.equals(xmPushActionRegistration.token))) {
            return false;
        }
        boolean isSetDeviceId = isSetDeviceId();
        boolean isSetDeviceId2 = xmPushActionRegistration.isSetDeviceId();
        if ((isSetDeviceId || isSetDeviceId2) && (!isSetDeviceId || !isSetDeviceId2 || !this.deviceId.equals(xmPushActionRegistration.deviceId))) {
            return false;
        }
        boolean isSetAliasName = isSetAliasName();
        boolean isSetAliasName2 = xmPushActionRegistration.isSetAliasName();
        if ((isSetAliasName || isSetAliasName2) && (!isSetAliasName || !isSetAliasName2 || !this.aliasName.equals(xmPushActionRegistration.aliasName))) {
            return false;
        }
        boolean isSetSdkVersion = isSetSdkVersion();
        boolean isSetSdkVersion2 = xmPushActionRegistration.isSetSdkVersion();
        if ((isSetSdkVersion || isSetSdkVersion2) && (!isSetSdkVersion || !isSetSdkVersion2 || !this.sdkVersion.equals(xmPushActionRegistration.sdkVersion))) {
            return false;
        }
        boolean isSetRegId = isSetRegId();
        boolean isSetRegId2 = xmPushActionRegistration.isSetRegId();
        if ((isSetRegId || isSetRegId2) && (!isSetRegId || !isSetRegId2 || !this.regId.equals(xmPushActionRegistration.regId))) {
            return false;
        }
        boolean isSetPushSdkVersionName = isSetPushSdkVersionName();
        boolean isSetPushSdkVersionName2 = xmPushActionRegistration.isSetPushSdkVersionName();
        if ((isSetPushSdkVersionName || isSetPushSdkVersionName2) && (!isSetPushSdkVersionName || !isSetPushSdkVersionName2 || !this.pushSdkVersionName.equals(xmPushActionRegistration.pushSdkVersionName))) {
            return false;
        }
        boolean isSetPushSdkVersionCode = isSetPushSdkVersionCode();
        boolean isSetPushSdkVersionCode2 = xmPushActionRegistration.isSetPushSdkVersionCode();
        if ((isSetPushSdkVersionCode || isSetPushSdkVersionCode2) && (!isSetPushSdkVersionCode || !isSetPushSdkVersionCode2 || this.pushSdkVersionCode != xmPushActionRegistration.pushSdkVersionCode)) {
            return false;
        }
        boolean isSetAppVersionCode = isSetAppVersionCode();
        boolean isSetAppVersionCode2 = xmPushActionRegistration.isSetAppVersionCode();
        if ((isSetAppVersionCode || isSetAppVersionCode2) && (!isSetAppVersionCode || !isSetAppVersionCode2 || this.appVersionCode != xmPushActionRegistration.appVersionCode)) {
            return false;
        }
        boolean isSetAndroidId = isSetAndroidId();
        boolean isSetAndroidId2 = xmPushActionRegistration.isSetAndroidId();
        if ((isSetAndroidId || isSetAndroidId2) && (!isSetAndroidId || !isSetAndroidId2 || !this.androidId.equals(xmPushActionRegistration.androidId))) {
            return false;
        }
        boolean isSetImei = isSetImei();
        boolean isSetImei2 = xmPushActionRegistration.isSetImei();
        if ((isSetImei || isSetImei2) && (!isSetImei || !isSetImei2 || !this.imei.equals(xmPushActionRegistration.imei))) {
            return false;
        }
        boolean isSetSerial = isSetSerial();
        boolean isSetSerial2 = xmPushActionRegistration.isSetSerial();
        if ((isSetSerial || isSetSerial2) && (!isSetSerial || !isSetSerial2 || !this.serial.equals(xmPushActionRegistration.serial))) {
            return false;
        }
        boolean isSetImeiMd5 = isSetImeiMd5();
        boolean isSetImeiMd52 = xmPushActionRegistration.isSetImeiMd5();
        if ((isSetImeiMd5 || isSetImeiMd52) && (!isSetImeiMd5 || !isSetImeiMd52 || !this.imeiMd5.equals(xmPushActionRegistration.imeiMd5))) {
            return false;
        }
        boolean isSetSpaceId = isSetSpaceId();
        boolean isSetSpaceId2 = xmPushActionRegistration.isSetSpaceId();
        if ((isSetSpaceId || isSetSpaceId2) && (!isSetSpaceId || !isSetSpaceId2 || this.spaceId != xmPushActionRegistration.spaceId)) {
            return false;
        }
        boolean isSetReason = isSetReason();
        boolean isSetReason2 = xmPushActionRegistration.isSetReason();
        if ((isSetReason || isSetReason2) && (!isSetReason || !isSetReason2 || !this.reason.equals(xmPushActionRegistration.reason))) {
            return false;
        }
        boolean isSetValidateToken = isSetValidateToken();
        boolean isSetValidateToken2 = xmPushActionRegistration.isSetValidateToken();
        if ((isSetValidateToken || isSetValidateToken2) && (!isSetValidateToken || !isSetValidateToken2 || this.validateToken != xmPushActionRegistration.validateToken)) {
            return false;
        }
        boolean isSetMiid = isSetMiid();
        boolean isSetMiid2 = xmPushActionRegistration.isSetMiid();
        if ((isSetMiid || isSetMiid2) && (!isSetMiid || !isSetMiid2 || this.miid != xmPushActionRegistration.miid)) {
            return false;
        }
        boolean isSetCreatedTs = isSetCreatedTs();
        boolean isSetCreatedTs2 = xmPushActionRegistration.isSetCreatedTs();
        if ((isSetCreatedTs || isSetCreatedTs2) && (!isSetCreatedTs || !isSetCreatedTs2 || this.createdTs != xmPushActionRegistration.createdTs)) {
            return false;
        }
        boolean isSetSubImei = isSetSubImei();
        boolean isSetSubImei2 = xmPushActionRegistration.isSetSubImei();
        if ((isSetSubImei || isSetSubImei2) && (!isSetSubImei || !isSetSubImei2 || !this.subImei.equals(xmPushActionRegistration.subImei))) {
            return false;
        }
        boolean isSetSubImeiMd5 = isSetSubImeiMd5();
        boolean isSetSubImeiMd52 = xmPushActionRegistration.isSetSubImeiMd5();
        if ((isSetSubImeiMd5 || isSetSubImeiMd52) && (!isSetSubImeiMd5 || !isSetSubImeiMd52 || !this.subImeiMd5.equals(xmPushActionRegistration.subImeiMd5))) {
            return false;
        }
        boolean isSetConnectionAttrs = isSetConnectionAttrs();
        boolean isSetConnectionAttrs2 = xmPushActionRegistration.isSetConnectionAttrs();
        if ((isSetConnectionAttrs || isSetConnectionAttrs2) && (!isSetConnectionAttrs || !isSetConnectionAttrs2 || !this.connectionAttrs.equals(xmPushActionRegistration.connectionAttrs))) {
            return false;
        }
        boolean isSetCleanOldRegInfo = isSetCleanOldRegInfo();
        boolean isSetCleanOldRegInfo2 = xmPushActionRegistration.isSetCleanOldRegInfo();
        if ((isSetCleanOldRegInfo || isSetCleanOldRegInfo2) && (!isSetCleanOldRegInfo || !isSetCleanOldRegInfo2 || this.cleanOldRegInfo != xmPushActionRegistration.cleanOldRegInfo)) {
            return false;
        }
        boolean isSetOldRegId = isSetOldRegId();
        boolean isSetOldRegId2 = xmPushActionRegistration.isSetOldRegId();
        if (isSetOldRegId || isSetOldRegId2) {
            return isSetOldRegId && isSetOldRegId2 && this.oldRegId.equals(xmPushActionRegistration.oldRegId);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionRegistration)) {
            return equals((XmPushActionRegistration) obj);
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAliasName() {
        return this.aliasName != null;
    }

    public boolean isSetAndroidId() {
        return this.androidId != null;
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public boolean isSetAppVersion() {
        return this.appVersion != null;
    }

    public boolean isSetAppVersionCode() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetCleanOldRegInfo() {
        return this.__isset_bit_vector.get(6);
    }

    public boolean isSetConnectionAttrs() {
        return this.connectionAttrs != null;
    }

    public boolean isSetCreatedTs() {
        return this.__isset_bit_vector.get(5);
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

    public boolean isSetImei() {
        return this.imei != null;
    }

    public boolean isSetImeiMd5() {
        return this.imeiMd5 != null;
    }

    public boolean isSetMiid() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetOldRegId() {
        return this.oldRegId != null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetPushSdkVersionCode() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetPushSdkVersionName() {
        return this.pushSdkVersionName != null;
    }

    public boolean isSetReason() {
        return this.reason != null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public boolean isSetSdkVersion() {
        return this.sdkVersion != null;
    }

    public boolean isSetSerial() {
        return this.serial != null;
    }

    public boolean isSetSpaceId() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetSubImei() {
        return this.subImei != null;
    }

    public boolean isSetSubImeiMd5() {
        return this.subImeiMd5 != null;
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public boolean isSetToken() {
        return this.token != null;
    }

    public boolean isSetValidateToken() {
        return this.__isset_bit_vector.get(3);
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
            short s = readFieldBegin.id;
            switch (s) {
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
                        this.appVersion = tProtocol.readString();
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
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.token = tProtocol.readString();
                        break;
                    }
                case 8:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.deviceId = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                case 10:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.sdkVersion = tProtocol.readString();
                        break;
                    }
                case 11:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.regId = tProtocol.readString();
                        break;
                    }
                case 12:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.pushSdkVersionName = tProtocol.readString();
                        break;
                    }
                case 13:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.pushSdkVersionCode = tProtocol.readI32();
                        setPushSdkVersionCodeIsSet(true);
                        break;
                    }
                case 14:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.appVersionCode = tProtocol.readI32();
                        setAppVersionCodeIsSet(true);
                        break;
                    }
                case 15:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.androidId = tProtocol.readString();
                        break;
                    }
                case 16:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.imei = tProtocol.readString();
                        break;
                    }
                case 17:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.serial = tProtocol.readString();
                        break;
                    }
                case 18:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.imeiMd5 = tProtocol.readString();
                        break;
                    }
                case 19:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.spaceId = tProtocol.readI32();
                        setSpaceIdIsSet(true);
                        break;
                    }
                case 20:
                    if (readFieldBegin.type != 8) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.reason = RegistrationReason.findByValue(tProtocol.readI32());
                        break;
                    }
                case 21:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.validateToken = tProtocol.readBool();
                        setValidateTokenIsSet(true);
                        break;
                    }
                case 22:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.miid = tProtocol.readI64();
                        setMiidIsSet(true);
                        break;
                    }
                case 23:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.createdTs = tProtocol.readI64();
                        setCreatedTsIsSet(true);
                        break;
                    }
                case 24:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.subImei = tProtocol.readString();
                        break;
                    }
                case 25:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.subImeiMd5 = tProtocol.readString();
                        break;
                    }
                default:
                    switch (s) {
                        case 100:
                            if (readFieldBegin.type != 13) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                TMap readMapBegin = tProtocol.readMapBegin();
                                this.connectionAttrs = new HashMap(readMapBegin.size * 2);
                                for (int i = 0; i < readMapBegin.size; i++) {
                                    this.connectionAttrs.put(tProtocol.readString(), tProtocol.readString());
                                }
                                tProtocol.readMapEnd();
                                break;
                            }
                        case 101:
                            if (readFieldBegin.type != 2) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.cleanOldRegInfo = tProtocol.readBool();
                                setCleanOldRegInfoIsSet(true);
                                break;
                            }
                        case 102:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.oldRegId = tProtocol.readString();
                                break;
                            }
                        default:
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                    }
            }
            tProtocol.readFieldEnd();
        }
    }

    public XmPushActionRegistration setAndroidId(String str) {
        this.androidId = str;
        return this;
    }

    public XmPushActionRegistration setAppId(String str) {
        this.appId = str;
        return this;
    }

    public XmPushActionRegistration setAppVersion(String str) {
        this.appVersion = str;
        return this;
    }

    public XmPushActionRegistration setAppVersionCode(int i) {
        this.appVersionCode = i;
        setAppVersionCodeIsSet(true);
        return this;
    }

    public void setAppVersionCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public void setCleanOldRegInfoIsSet(boolean z) {
        this.__isset_bit_vector.set(6, z);
    }

    public void setCreatedTsIsSet(boolean z) {
        this.__isset_bit_vector.set(5, z);
    }

    public XmPushActionRegistration setDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public XmPushActionRegistration setId(String str) {
        this.id = str;
        return this;
    }

    public XmPushActionRegistration setImei(String str) {
        this.imei = str;
        return this;
    }

    public XmPushActionRegistration setImeiMd5(String str) {
        this.imeiMd5 = str;
        return this;
    }

    public void setMiidIsSet(boolean z) {
        this.__isset_bit_vector.set(4, z);
    }

    public XmPushActionRegistration setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public XmPushActionRegistration setPushSdkVersionCode(int i) {
        this.pushSdkVersionCode = i;
        setPushSdkVersionCodeIsSet(true);
        return this;
    }

    public void setPushSdkVersionCodeIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public XmPushActionRegistration setPushSdkVersionName(String str) {
        this.pushSdkVersionName = str;
        return this;
    }

    public XmPushActionRegistration setReason(RegistrationReason registrationReason) {
        this.reason = registrationReason;
        return this;
    }

    public XmPushActionRegistration setSerial(String str) {
        this.serial = str;
        return this;
    }

    public XmPushActionRegistration setSpaceId(int i) {
        this.spaceId = i;
        setSpaceIdIsSet(true);
        return this;
    }

    public void setSpaceIdIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public XmPushActionRegistration setToken(String str) {
        this.token = str;
        return this;
    }

    public void setValidateTokenIsSet(boolean z) {
        this.__isset_bit_vector.set(3, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
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
        if (isSetAppVersion()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str4 = this.appVersion;
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
        sb.append(", ");
        sb.append("token:");
        String str6 = this.token;
        if (str6 == null) {
            sb.append("null");
        } else {
            sb.append(str6);
        }
        if (isSetDeviceId()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str7 = this.deviceId;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
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
        if (isSetSdkVersion()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            String str9 = this.sdkVersion;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (isSetRegId()) {
            sb.append(", ");
            sb.append("regId:");
            String str10 = this.regId;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (isSetPushSdkVersionName()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            String str11 = this.pushSdkVersionName;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (isSetPushSdkVersionCode()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.pushSdkVersionCode);
        }
        if (isSetAppVersionCode()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.appVersionCode);
        }
        if (isSetAndroidId()) {
            sb.append(", ");
            sb.append("androidId:");
            String str12 = this.androidId;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        if (isSetImei()) {
            sb.append(", ");
            sb.append("imei:");
            String str13 = this.imei;
            if (str13 == null) {
                sb.append("null");
            } else {
                sb.append(str13);
            }
        }
        if (isSetSerial()) {
            sb.append(", ");
            sb.append("serial:");
            String str14 = this.serial;
            if (str14 == null) {
                sb.append("null");
            } else {
                sb.append(str14);
            }
        }
        if (isSetImeiMd5()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str15 = this.imeiMd5;
            if (str15 == null) {
                sb.append("null");
            } else {
                sb.append(str15);
            }
        }
        if (isSetSpaceId()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.spaceId);
        }
        if (isSetReason()) {
            sb.append(", ");
            sb.append("reason:");
            RegistrationReason registrationReason = this.reason;
            if (registrationReason == null) {
                sb.append("null");
            } else {
                sb.append(registrationReason);
            }
        }
        if (isSetValidateToken()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.validateToken);
        }
        if (isSetMiid()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.miid);
        }
        if (isSetCreatedTs()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.createdTs);
        }
        if (isSetSubImei()) {
            sb.append(", ");
            sb.append("subImei:");
            String str16 = this.subImei;
            if (str16 == null) {
                sb.append("null");
            } else {
                sb.append(str16);
            }
        }
        if (isSetSubImeiMd5()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            String str17 = this.subImeiMd5;
            if (str17 == null) {
                sb.append("null");
            } else {
                sb.append(str17);
            }
        }
        if (isSetConnectionAttrs()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            Map<String, String> map = this.connectionAttrs;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (isSetCleanOldRegInfo()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.cleanOldRegInfo);
        }
        if (isSetOldRegId()) {
            sb.append(", ");
            sb.append("oldRegId:");
            String str18 = this.oldRegId;
            if (str18 == null) {
                sb.append("null");
            } else {
                sb.append(str18);
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
        } else if (this.token == null) {
            throw new TProtocolException("Required field 'token' was not present! Struct: " + toString());
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
        if (this.token != null) {
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
        if (this.sdkVersion != null && isSetSdkVersion()) {
            tProtocol.writeFieldBegin(SDK_VERSION_FIELD_DESC);
            tProtocol.writeString(this.sdkVersion);
            tProtocol.writeFieldEnd();
        }
        if (this.regId != null && isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.pushSdkVersionName != null && isSetPushSdkVersionName()) {
            tProtocol.writeFieldBegin(PUSH_SDK_VERSION_NAME_FIELD_DESC);
            tProtocol.writeString(this.pushSdkVersionName);
            tProtocol.writeFieldEnd();
        }
        if (isSetPushSdkVersionCode()) {
            tProtocol.writeFieldBegin(PUSH_SDK_VERSION_CODE_FIELD_DESC);
            tProtocol.writeI32(this.pushSdkVersionCode);
            tProtocol.writeFieldEnd();
        }
        if (isSetAppVersionCode()) {
            tProtocol.writeFieldBegin(APP_VERSION_CODE_FIELD_DESC);
            tProtocol.writeI32(this.appVersionCode);
            tProtocol.writeFieldEnd();
        }
        if (this.androidId != null && isSetAndroidId()) {
            tProtocol.writeFieldBegin(ANDROID_ID_FIELD_DESC);
            tProtocol.writeString(this.androidId);
            tProtocol.writeFieldEnd();
        }
        if (this.imei != null && isSetImei()) {
            tProtocol.writeFieldBegin(IMEI_FIELD_DESC);
            tProtocol.writeString(this.imei);
            tProtocol.writeFieldEnd();
        }
        if (this.serial != null && isSetSerial()) {
            tProtocol.writeFieldBegin(SERIAL_FIELD_DESC);
            tProtocol.writeString(this.serial);
            tProtocol.writeFieldEnd();
        }
        if (this.imeiMd5 != null && isSetImeiMd5()) {
            tProtocol.writeFieldBegin(IMEI_MD5_FIELD_DESC);
            tProtocol.writeString(this.imeiMd5);
            tProtocol.writeFieldEnd();
        }
        if (isSetSpaceId()) {
            tProtocol.writeFieldBegin(SPACE_ID_FIELD_DESC);
            tProtocol.writeI32(this.spaceId);
            tProtocol.writeFieldEnd();
        }
        if (this.reason != null && isSetReason()) {
            tProtocol.writeFieldBegin(REASON_FIELD_DESC);
            tProtocol.writeI32(this.reason.getValue());
            tProtocol.writeFieldEnd();
        }
        if (isSetValidateToken()) {
            tProtocol.writeFieldBegin(VALIDATE_TOKEN_FIELD_DESC);
            tProtocol.writeBool(this.validateToken);
            tProtocol.writeFieldEnd();
        }
        if (isSetMiid()) {
            tProtocol.writeFieldBegin(MIID_FIELD_DESC);
            tProtocol.writeI64(this.miid);
            tProtocol.writeFieldEnd();
        }
        if (isSetCreatedTs()) {
            tProtocol.writeFieldBegin(CREATED_TS_FIELD_DESC);
            tProtocol.writeI64(this.createdTs);
            tProtocol.writeFieldEnd();
        }
        if (this.subImei != null && isSetSubImei()) {
            tProtocol.writeFieldBegin(SUB_IMEI_FIELD_DESC);
            tProtocol.writeString(this.subImei);
            tProtocol.writeFieldEnd();
        }
        if (this.subImeiMd5 != null && isSetSubImeiMd5()) {
            tProtocol.writeFieldBegin(SUB_IMEI_MD5_FIELD_DESC);
            tProtocol.writeString(this.subImeiMd5);
            tProtocol.writeFieldEnd();
        }
        if (this.connectionAttrs != null && isSetConnectionAttrs()) {
            tProtocol.writeFieldBegin(CONNECTION_ATTRS_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap((byte) 11, (byte) 11, this.connectionAttrs.size()));
            for (Map.Entry next : this.connectionAttrs.entrySet()) {
                tProtocol.writeString((String) next.getKey());
                tProtocol.writeString((String) next.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (isSetCleanOldRegInfo()) {
            tProtocol.writeFieldBegin(CLEAN_OLD_REG_INFO_FIELD_DESC);
            tProtocol.writeBool(this.cleanOldRegInfo);
            tProtocol.writeFieldEnd();
        }
        if (this.oldRegId != null && isSetOldRegId()) {
            tProtocol.writeFieldBegin(OLD_REG_ID_FIELD_DESC);
            tProtocol.writeString(this.oldRegId);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
