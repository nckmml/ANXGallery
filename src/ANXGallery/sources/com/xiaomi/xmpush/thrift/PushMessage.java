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

public class PushMessage implements Serializable, Cloneable, TBase<PushMessage, Object> {
    private static final TField ALIAS_NAME_FIELD_DESC = new TField("", (byte) 11, 13);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField COLLAPSE_KEY_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField CREATE_AT_FIELD_DESC = new TField("", (byte) 10, 5);
    private static final TField DEVICE_ID_FIELD_DESC = new TField("", (byte) 11, 21);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 2);
    private static final TField IMEI_MD5_FIELD_DESC = new TField("", (byte) 11, 20);
    private static final TField IS_ONLINE_FIELD_DESC = new TField("", (byte) 2, 14);
    private static final TField META_INFO_FIELD_DESC = new TField("", (byte) 12, 12);
    private static final TField MIID_FIELD_DESC = new TField("", (byte) 10, 16);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TField PAYLOAD_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField REG_ID_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TStruct STRUCT_DESC = new TStruct("PushMessage");
    private static final TField TOPIC_FIELD_DESC = new TField("", (byte) 11, 11);
    private static final TField TO_FIELD_DESC = new TField("", (byte) 12, 1);
    private static final TField TTL_FIELD_DESC = new TField("", (byte) 10, 6);
    private static final TField USER_ACCOUNT_FIELD_DESC = new TField("", (byte) 11, 15);
    private BitSet __isset_bit_vector = new BitSet(4);
    public String aliasName;
    public String appId;
    public String category;
    public String collapseKey;
    public long createAt;
    public String deviceId;
    public String id;
    public String imeiMd5;
    public boolean isOnline = false;
    public PushMetaInfo metaInfo;
    public long miid;
    public String packageName;
    public String payload;
    public String regId;
    public Target to;
    public String topic;
    public long ttl;
    public String userAccount;

    public int compareTo(PushMessage pushMessage) {
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
        if (!getClass().equals(pushMessage.getClass())) {
            return getClass().getName().compareTo(pushMessage.getClass().getName());
        }
        int compareTo19 = Boolean.valueOf(isSetTo()).compareTo(Boolean.valueOf(pushMessage.isSetTo()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetTo() && (compareTo18 = TBaseHelper.compareTo((Comparable) this.to, (Comparable) pushMessage.to)) != 0) {
            return compareTo18;
        }
        int compareTo20 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(pushMessage.isSetId()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetId() && (compareTo17 = TBaseHelper.compareTo(this.id, pushMessage.id)) != 0) {
            return compareTo17;
        }
        int compareTo21 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(pushMessage.isSetAppId()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetAppId() && (compareTo16 = TBaseHelper.compareTo(this.appId, pushMessage.appId)) != 0) {
            return compareTo16;
        }
        int compareTo22 = Boolean.valueOf(isSetPayload()).compareTo(Boolean.valueOf(pushMessage.isSetPayload()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetPayload() && (compareTo15 = TBaseHelper.compareTo(this.payload, pushMessage.payload)) != 0) {
            return compareTo15;
        }
        int compareTo23 = Boolean.valueOf(isSetCreateAt()).compareTo(Boolean.valueOf(pushMessage.isSetCreateAt()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (isSetCreateAt() && (compareTo14 = TBaseHelper.compareTo(this.createAt, pushMessage.createAt)) != 0) {
            return compareTo14;
        }
        int compareTo24 = Boolean.valueOf(isSetTtl()).compareTo(Boolean.valueOf(pushMessage.isSetTtl()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (isSetTtl() && (compareTo13 = TBaseHelper.compareTo(this.ttl, pushMessage.ttl)) != 0) {
            return compareTo13;
        }
        int compareTo25 = Boolean.valueOf(isSetCollapseKey()).compareTo(Boolean.valueOf(pushMessage.isSetCollapseKey()));
        if (compareTo25 != 0) {
            return compareTo25;
        }
        if (isSetCollapseKey() && (compareTo12 = TBaseHelper.compareTo(this.collapseKey, pushMessage.collapseKey)) != 0) {
            return compareTo12;
        }
        int compareTo26 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(pushMessage.isSetPackageName()));
        if (compareTo26 != 0) {
            return compareTo26;
        }
        if (isSetPackageName() && (compareTo11 = TBaseHelper.compareTo(this.packageName, pushMessage.packageName)) != 0) {
            return compareTo11;
        }
        int compareTo27 = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(pushMessage.isSetRegId()));
        if (compareTo27 != 0) {
            return compareTo27;
        }
        if (isSetRegId() && (compareTo10 = TBaseHelper.compareTo(this.regId, pushMessage.regId)) != 0) {
            return compareTo10;
        }
        int compareTo28 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(pushMessage.isSetCategory()));
        if (compareTo28 != 0) {
            return compareTo28;
        }
        if (isSetCategory() && (compareTo9 = TBaseHelper.compareTo(this.category, pushMessage.category)) != 0) {
            return compareTo9;
        }
        int compareTo29 = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(pushMessage.isSetTopic()));
        if (compareTo29 != 0) {
            return compareTo29;
        }
        if (isSetTopic() && (compareTo8 = TBaseHelper.compareTo(this.topic, pushMessage.topic)) != 0) {
            return compareTo8;
        }
        int compareTo30 = Boolean.valueOf(isSetMetaInfo()).compareTo(Boolean.valueOf(pushMessage.isSetMetaInfo()));
        if (compareTo30 != 0) {
            return compareTo30;
        }
        if (isSetMetaInfo() && (compareTo7 = TBaseHelper.compareTo((Comparable) this.metaInfo, (Comparable) pushMessage.metaInfo)) != 0) {
            return compareTo7;
        }
        int compareTo31 = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(pushMessage.isSetAliasName()));
        if (compareTo31 != 0) {
            return compareTo31;
        }
        if (isSetAliasName() && (compareTo6 = TBaseHelper.compareTo(this.aliasName, pushMessage.aliasName)) != 0) {
            return compareTo6;
        }
        int compareTo32 = Boolean.valueOf(isSetIsOnline()).compareTo(Boolean.valueOf(pushMessage.isSetIsOnline()));
        if (compareTo32 != 0) {
            return compareTo32;
        }
        if (isSetIsOnline() && (compareTo5 = TBaseHelper.compareTo(this.isOnline, pushMessage.isOnline)) != 0) {
            return compareTo5;
        }
        int compareTo33 = Boolean.valueOf(isSetUserAccount()).compareTo(Boolean.valueOf(pushMessage.isSetUserAccount()));
        if (compareTo33 != 0) {
            return compareTo33;
        }
        if (isSetUserAccount() && (compareTo4 = TBaseHelper.compareTo(this.userAccount, pushMessage.userAccount)) != 0) {
            return compareTo4;
        }
        int compareTo34 = Boolean.valueOf(isSetMiid()).compareTo(Boolean.valueOf(pushMessage.isSetMiid()));
        if (compareTo34 != 0) {
            return compareTo34;
        }
        if (isSetMiid() && (compareTo3 = TBaseHelper.compareTo(this.miid, pushMessage.miid)) != 0) {
            return compareTo3;
        }
        int compareTo35 = Boolean.valueOf(isSetImeiMd5()).compareTo(Boolean.valueOf(pushMessage.isSetImeiMd5()));
        if (compareTo35 != 0) {
            return compareTo35;
        }
        if (isSetImeiMd5() && (compareTo2 = TBaseHelper.compareTo(this.imeiMd5, pushMessage.imeiMd5)) != 0) {
            return compareTo2;
        }
        int compareTo36 = Boolean.valueOf(isSetDeviceId()).compareTo(Boolean.valueOf(pushMessage.isSetDeviceId()));
        if (compareTo36 != 0) {
            return compareTo36;
        }
        if (!isSetDeviceId() || (compareTo = TBaseHelper.compareTo(this.deviceId, pushMessage.deviceId)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(PushMessage pushMessage) {
        if (pushMessage == null) {
            return false;
        }
        boolean isSetTo = isSetTo();
        boolean isSetTo2 = pushMessage.isSetTo();
        if ((isSetTo || isSetTo2) && (!isSetTo || !isSetTo2 || !this.to.equals(pushMessage.to))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = pushMessage.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(pushMessage.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = pushMessage.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(pushMessage.appId))) {
            return false;
        }
        boolean isSetPayload = isSetPayload();
        boolean isSetPayload2 = pushMessage.isSetPayload();
        if ((isSetPayload || isSetPayload2) && (!isSetPayload || !isSetPayload2 || !this.payload.equals(pushMessage.payload))) {
            return false;
        }
        boolean isSetCreateAt = isSetCreateAt();
        boolean isSetCreateAt2 = pushMessage.isSetCreateAt();
        if ((isSetCreateAt || isSetCreateAt2) && (!isSetCreateAt || !isSetCreateAt2 || this.createAt != pushMessage.createAt)) {
            return false;
        }
        boolean isSetTtl = isSetTtl();
        boolean isSetTtl2 = pushMessage.isSetTtl();
        if ((isSetTtl || isSetTtl2) && (!isSetTtl || !isSetTtl2 || this.ttl != pushMessage.ttl)) {
            return false;
        }
        boolean isSetCollapseKey = isSetCollapseKey();
        boolean isSetCollapseKey2 = pushMessage.isSetCollapseKey();
        if ((isSetCollapseKey || isSetCollapseKey2) && (!isSetCollapseKey || !isSetCollapseKey2 || !this.collapseKey.equals(pushMessage.collapseKey))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = pushMessage.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(pushMessage.packageName))) {
            return false;
        }
        boolean isSetRegId = isSetRegId();
        boolean isSetRegId2 = pushMessage.isSetRegId();
        if ((isSetRegId || isSetRegId2) && (!isSetRegId || !isSetRegId2 || !this.regId.equals(pushMessage.regId))) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = pushMessage.isSetCategory();
        if ((isSetCategory || isSetCategory2) && (!isSetCategory || !isSetCategory2 || !this.category.equals(pushMessage.category))) {
            return false;
        }
        boolean isSetTopic = isSetTopic();
        boolean isSetTopic2 = pushMessage.isSetTopic();
        if ((isSetTopic || isSetTopic2) && (!isSetTopic || !isSetTopic2 || !this.topic.equals(pushMessage.topic))) {
            return false;
        }
        boolean isSetMetaInfo = isSetMetaInfo();
        boolean isSetMetaInfo2 = pushMessage.isSetMetaInfo();
        if ((isSetMetaInfo || isSetMetaInfo2) && (!isSetMetaInfo || !isSetMetaInfo2 || !this.metaInfo.equals(pushMessage.metaInfo))) {
            return false;
        }
        boolean isSetAliasName = isSetAliasName();
        boolean isSetAliasName2 = pushMessage.isSetAliasName();
        if ((isSetAliasName || isSetAliasName2) && (!isSetAliasName || !isSetAliasName2 || !this.aliasName.equals(pushMessage.aliasName))) {
            return false;
        }
        boolean isSetIsOnline = isSetIsOnline();
        boolean isSetIsOnline2 = pushMessage.isSetIsOnline();
        if ((isSetIsOnline || isSetIsOnline2) && (!isSetIsOnline || !isSetIsOnline2 || this.isOnline != pushMessage.isOnline)) {
            return false;
        }
        boolean isSetUserAccount = isSetUserAccount();
        boolean isSetUserAccount2 = pushMessage.isSetUserAccount();
        if ((isSetUserAccount || isSetUserAccount2) && (!isSetUserAccount || !isSetUserAccount2 || !this.userAccount.equals(pushMessage.userAccount))) {
            return false;
        }
        boolean isSetMiid = isSetMiid();
        boolean isSetMiid2 = pushMessage.isSetMiid();
        if ((isSetMiid || isSetMiid2) && (!isSetMiid || !isSetMiid2 || this.miid != pushMessage.miid)) {
            return false;
        }
        boolean isSetImeiMd5 = isSetImeiMd5();
        boolean isSetImeiMd52 = pushMessage.isSetImeiMd5();
        if ((isSetImeiMd5 || isSetImeiMd52) && (!isSetImeiMd5 || !isSetImeiMd52 || !this.imeiMd5.equals(pushMessage.imeiMd5))) {
            return false;
        }
        boolean isSetDeviceId = isSetDeviceId();
        boolean isSetDeviceId2 = pushMessage.isSetDeviceId();
        if (isSetDeviceId || isSetDeviceId2) {
            return isSetDeviceId && isSetDeviceId2 && this.deviceId.equals(pushMessage.deviceId);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof PushMessage)) {
            return equals((PushMessage) obj);
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public long getCreateAt() {
        return this.createAt;
    }

    public String getId() {
        return this.id;
    }

    public String getPayload() {
        return this.payload;
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

    public boolean isSetCategory() {
        return this.category != null;
    }

    public boolean isSetCollapseKey() {
        return this.collapseKey != null;
    }

    public boolean isSetCreateAt() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetDeviceId() {
        return this.deviceId != null;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetImeiMd5() {
        return this.imeiMd5 != null;
    }

    public boolean isSetIsOnline() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetMetaInfo() {
        return this.metaInfo != null;
    }

    public boolean isSetMiid() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetPayload() {
        return this.payload != null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public boolean isSetTo() {
        return this.to != null;
    }

    public boolean isSetTopic() {
        return this.topic != null;
    }

    public boolean isSetTtl() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetUserAccount() {
        return this.userAccount != null;
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
            if (s != 20) {
                if (s != 21) {
                    switch (s) {
                        case 1:
                            if (readFieldBegin.type != 12) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.to = new Target();
                                this.to.read(tProtocol);
                                break;
                            }
                        case 2:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.id = tProtocol.readString();
                                break;
                            }
                        case 3:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.appId = tProtocol.readString();
                                break;
                            }
                        case 4:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.payload = tProtocol.readString();
                                break;
                            }
                        case 5:
                            if (readFieldBegin.type != 10) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.createAt = tProtocol.readI64();
                                setCreateAtIsSet(true);
                                break;
                            }
                        case 6:
                            if (readFieldBegin.type != 10) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.ttl = tProtocol.readI64();
                                setTtlIsSet(true);
                                break;
                            }
                        case 7:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.collapseKey = tProtocol.readString();
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
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.regId = tProtocol.readString();
                                break;
                            }
                        case 10:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.category = tProtocol.readString();
                                break;
                            }
                        case 11:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.topic = tProtocol.readString();
                                break;
                            }
                        case 12:
                            if (readFieldBegin.type != 12) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.metaInfo = new PushMetaInfo();
                                this.metaInfo.read(tProtocol);
                                break;
                            }
                        case 13:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.aliasName = tProtocol.readString();
                                break;
                            }
                        case 14:
                            if (readFieldBegin.type != 2) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.isOnline = tProtocol.readBool();
                                setIsOnlineIsSet(true);
                                break;
                            }
                        case 15:
                            if (readFieldBegin.type != 11) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.userAccount = tProtocol.readString();
                                break;
                            }
                        case 16:
                            if (readFieldBegin.type != 10) {
                                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                                break;
                            } else {
                                this.miid = tProtocol.readI64();
                                setMiidIsSet(true);
                                break;
                            }
                        default:
                            TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                            break;
                    }
                } else if (readFieldBegin.type == 11) {
                    this.deviceId = tProtocol.readString();
                } else {
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                }
            } else if (readFieldBegin.type == 11) {
                this.imeiMd5 = tProtocol.readString();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setCreateAtIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public void setIsOnlineIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public void setMiidIsSet(boolean z) {
        this.__isset_bit_vector.set(3, z);
    }

    public void setTtlIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("PushMessage(");
        if (isSetTo()) {
            sb.append("to:");
            Target target = this.to;
            if (target == null) {
                sb.append("null");
            } else {
                sb.append(target);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str = this.id;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.appId;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("payload:");
        String str3 = this.payload;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (isSetCreateAt()) {
            sb.append(", ");
            sb.append("createAt:");
            sb.append(this.createAt);
        }
        if (isSetTtl()) {
            sb.append(", ");
            sb.append("ttl:");
            sb.append(this.ttl);
        }
        if (isSetCollapseKey()) {
            sb.append(", ");
            sb.append("collapseKey:");
            String str4 = this.collapseKey;
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
        if (isSetRegId()) {
            sb.append(", ");
            sb.append("regId:");
            String str6 = this.regId;
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
        if (isSetTopic()) {
            sb.append(", ");
            sb.append("topic:");
            String str8 = this.topic;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
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
        if (isSetIsOnline()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.isOnline);
        }
        if (isSetUserAccount()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.userAccount;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (isSetMiid()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.miid);
        }
        if (isSetImeiMd5()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.imeiMd5;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (isSetDeviceId()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.deviceId;
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
        } else if (this.payload == null) {
            throw new TProtocolException("Required field 'payload' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.to != null && isSetTo()) {
            tProtocol.writeFieldBegin(TO_FIELD_DESC);
            this.to.write(tProtocol);
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
        if (this.payload != null) {
            tProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
            tProtocol.writeString(this.payload);
            tProtocol.writeFieldEnd();
        }
        if (isSetCreateAt()) {
            tProtocol.writeFieldBegin(CREATE_AT_FIELD_DESC);
            tProtocol.writeI64(this.createAt);
            tProtocol.writeFieldEnd();
        }
        if (isSetTtl()) {
            tProtocol.writeFieldBegin(TTL_FIELD_DESC);
            tProtocol.writeI64(this.ttl);
            tProtocol.writeFieldEnd();
        }
        if (this.collapseKey != null && isSetCollapseKey()) {
            tProtocol.writeFieldBegin(COLLAPSE_KEY_FIELD_DESC);
            tProtocol.writeString(this.collapseKey);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.regId != null && isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.category != null && isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        if (this.topic != null && isSetTopic()) {
            tProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
            tProtocol.writeString(this.topic);
            tProtocol.writeFieldEnd();
        }
        if (this.metaInfo != null && isSetMetaInfo()) {
            tProtocol.writeFieldBegin(META_INFO_FIELD_DESC);
            this.metaInfo.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
            tProtocol.writeFieldEnd();
        }
        if (isSetIsOnline()) {
            tProtocol.writeFieldBegin(IS_ONLINE_FIELD_DESC);
            tProtocol.writeBool(this.isOnline);
            tProtocol.writeFieldEnd();
        }
        if (this.userAccount != null && isSetUserAccount()) {
            tProtocol.writeFieldBegin(USER_ACCOUNT_FIELD_DESC);
            tProtocol.writeString(this.userAccount);
            tProtocol.writeFieldEnd();
        }
        if (isSetMiid()) {
            tProtocol.writeFieldBegin(MIID_FIELD_DESC);
            tProtocol.writeI64(this.miid);
            tProtocol.writeFieldEnd();
        }
        if (this.imeiMd5 != null && isSetImeiMd5()) {
            tProtocol.writeFieldBegin(IMEI_MD5_FIELD_DESC);
            tProtocol.writeString(this.imeiMd5);
            tProtocol.writeFieldEnd();
        }
        if (this.deviceId != null && isSetDeviceId()) {
            tProtocol.writeFieldBegin(DEVICE_ID_FIELD_DESC);
            tProtocol.writeString(this.deviceId);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
