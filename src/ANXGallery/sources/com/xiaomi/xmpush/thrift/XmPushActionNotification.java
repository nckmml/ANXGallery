package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.nio.ByteBuffer;
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

public class XmPushActionNotification implements Serializable, Cloneable, TBase<XmPushActionNotification, Object> {
    private static final TField ALIAS_NAME_FIELD_DESC = new TField("", (byte) 11, 13);
    private static final TField ALREADY_LOG_CLICK_IN_XMQ_FIELD_DESC = new TField("", (byte) 2, 20);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField BINARY_EXTRA_FIELD_DESC = new TField("", (byte) 11, 14);
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 10);
    private static final TField CREATED_TS_FIELD_DESC = new TField("", (byte) 10, 15);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField EXTRA_FIELD_DESC = new TField("", (byte) 13, 8);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TField PAYLOAD_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField REG_ID_FIELD_DESC = new TField("", (byte) 11, 12);
    private static final TField REQUIRE_ACK_FIELD_DESC = new TField("", (byte) 2, 6);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionNotification");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField TYPE_FIELD_DESC = new TField("", (byte) 11, 5);
    private BitSet __isset_bit_vector;
    public String aliasName;
    public boolean alreadyLogClickInXmq;
    public String appId;
    public ByteBuffer binaryExtra;
    public String category;
    public long createdTs;
    public String debug;
    public Map<String, String> extra;
    public String id;
    public String packageName;
    public String payload;
    public String regId;
    public boolean requireAck;
    public Target target;
    public String type;

    public XmPushActionNotification() {
        this.__isset_bit_vector = new BitSet(3);
        this.requireAck = true;
        this.alreadyLogClickInXmq = false;
    }

    public XmPushActionNotification(String str, boolean z) {
        this();
        this.id = str;
        this.requireAck = z;
        setRequireAckIsSet(true);
    }

    public int compareTo(XmPushActionNotification xmPushActionNotification) {
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
        if (!getClass().equals(xmPushActionNotification.getClass())) {
            return getClass().getName().compareTo(xmPushActionNotification.getClass().getName());
        }
        int compareTo16 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetDebug()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetDebug() && (compareTo15 = TBaseHelper.compareTo(this.debug, xmPushActionNotification.debug)) != 0) {
            return compareTo15;
        }
        int compareTo17 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetTarget()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetTarget() && (compareTo14 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionNotification.target)) != 0) {
            return compareTo14;
        }
        int compareTo18 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetId()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetId() && (compareTo13 = TBaseHelper.compareTo(this.id, xmPushActionNotification.id)) != 0) {
            return compareTo13;
        }
        int compareTo19 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetAppId()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetAppId() && (compareTo12 = TBaseHelper.compareTo(this.appId, xmPushActionNotification.appId)) != 0) {
            return compareTo12;
        }
        int compareTo20 = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetType()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetType() && (compareTo11 = TBaseHelper.compareTo(this.type, xmPushActionNotification.type)) != 0) {
            return compareTo11;
        }
        int compareTo21 = Boolean.valueOf(isSetRequireAck()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetRequireAck()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetRequireAck() && (compareTo10 = TBaseHelper.compareTo(this.requireAck, xmPushActionNotification.requireAck)) != 0) {
            return compareTo10;
        }
        int compareTo22 = Boolean.valueOf(isSetPayload()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetPayload()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetPayload() && (compareTo9 = TBaseHelper.compareTo(this.payload, xmPushActionNotification.payload)) != 0) {
            return compareTo9;
        }
        int compareTo23 = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetExtra()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (isSetExtra() && (compareTo8 = TBaseHelper.compareTo((Map) this.extra, (Map) xmPushActionNotification.extra)) != 0) {
            return compareTo8;
        }
        int compareTo24 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetPackageName()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (isSetPackageName() && (compareTo7 = TBaseHelper.compareTo(this.packageName, xmPushActionNotification.packageName)) != 0) {
            return compareTo7;
        }
        int compareTo25 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetCategory()));
        if (compareTo25 != 0) {
            return compareTo25;
        }
        if (isSetCategory() && (compareTo6 = TBaseHelper.compareTo(this.category, xmPushActionNotification.category)) != 0) {
            return compareTo6;
        }
        int compareTo26 = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetRegId()));
        if (compareTo26 != 0) {
            return compareTo26;
        }
        if (isSetRegId() && (compareTo5 = TBaseHelper.compareTo(this.regId, xmPushActionNotification.regId)) != 0) {
            return compareTo5;
        }
        int compareTo27 = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetAliasName()));
        if (compareTo27 != 0) {
            return compareTo27;
        }
        if (isSetAliasName() && (compareTo4 = TBaseHelper.compareTo(this.aliasName, xmPushActionNotification.aliasName)) != 0) {
            return compareTo4;
        }
        int compareTo28 = Boolean.valueOf(isSetBinaryExtra()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetBinaryExtra()));
        if (compareTo28 != 0) {
            return compareTo28;
        }
        if (isSetBinaryExtra() && (compareTo3 = TBaseHelper.compareTo((Comparable) this.binaryExtra, (Comparable) xmPushActionNotification.binaryExtra)) != 0) {
            return compareTo3;
        }
        int compareTo29 = Boolean.valueOf(isSetCreatedTs()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetCreatedTs()));
        if (compareTo29 != 0) {
            return compareTo29;
        }
        if (isSetCreatedTs() && (compareTo2 = TBaseHelper.compareTo(this.createdTs, xmPushActionNotification.createdTs)) != 0) {
            return compareTo2;
        }
        int compareTo30 = Boolean.valueOf(isSetAlreadyLogClickInXmq()).compareTo(Boolean.valueOf(xmPushActionNotification.isSetAlreadyLogClickInXmq()));
        if (compareTo30 != 0) {
            return compareTo30;
        }
        if (!isSetAlreadyLogClickInXmq() || (compareTo = TBaseHelper.compareTo(this.alreadyLogClickInXmq, xmPushActionNotification.alreadyLogClickInXmq)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionNotification xmPushActionNotification) {
        if (xmPushActionNotification == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionNotification.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionNotification.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionNotification.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionNotification.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionNotification.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionNotification.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionNotification.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionNotification.appId))) {
            return false;
        }
        boolean isSetType = isSetType();
        boolean isSetType2 = xmPushActionNotification.isSetType();
        if (((isSetType || isSetType2) && (!isSetType || !isSetType2 || !this.type.equals(xmPushActionNotification.type))) || this.requireAck != xmPushActionNotification.requireAck) {
            return false;
        }
        boolean isSetPayload = isSetPayload();
        boolean isSetPayload2 = xmPushActionNotification.isSetPayload();
        if ((isSetPayload || isSetPayload2) && (!isSetPayload || !isSetPayload2 || !this.payload.equals(xmPushActionNotification.payload))) {
            return false;
        }
        boolean isSetExtra = isSetExtra();
        boolean isSetExtra2 = xmPushActionNotification.isSetExtra();
        if ((isSetExtra || isSetExtra2) && (!isSetExtra || !isSetExtra2 || !this.extra.equals(xmPushActionNotification.extra))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionNotification.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionNotification.packageName))) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = xmPushActionNotification.isSetCategory();
        if ((isSetCategory || isSetCategory2) && (!isSetCategory || !isSetCategory2 || !this.category.equals(xmPushActionNotification.category))) {
            return false;
        }
        boolean isSetRegId = isSetRegId();
        boolean isSetRegId2 = xmPushActionNotification.isSetRegId();
        if ((isSetRegId || isSetRegId2) && (!isSetRegId || !isSetRegId2 || !this.regId.equals(xmPushActionNotification.regId))) {
            return false;
        }
        boolean isSetAliasName = isSetAliasName();
        boolean isSetAliasName2 = xmPushActionNotification.isSetAliasName();
        if ((isSetAliasName || isSetAliasName2) && (!isSetAliasName || !isSetAliasName2 || !this.aliasName.equals(xmPushActionNotification.aliasName))) {
            return false;
        }
        boolean isSetBinaryExtra = isSetBinaryExtra();
        boolean isSetBinaryExtra2 = xmPushActionNotification.isSetBinaryExtra();
        if ((isSetBinaryExtra || isSetBinaryExtra2) && (!isSetBinaryExtra || !isSetBinaryExtra2 || !this.binaryExtra.equals(xmPushActionNotification.binaryExtra))) {
            return false;
        }
        boolean isSetCreatedTs = isSetCreatedTs();
        boolean isSetCreatedTs2 = xmPushActionNotification.isSetCreatedTs();
        if ((isSetCreatedTs || isSetCreatedTs2) && (!isSetCreatedTs || !isSetCreatedTs2 || this.createdTs != xmPushActionNotification.createdTs)) {
            return false;
        }
        boolean isSetAlreadyLogClickInXmq = isSetAlreadyLogClickInXmq();
        boolean isSetAlreadyLogClickInXmq2 = xmPushActionNotification.isSetAlreadyLogClickInXmq();
        if (isSetAlreadyLogClickInXmq || isSetAlreadyLogClickInXmq2) {
            return isSetAlreadyLogClickInXmq && isSetAlreadyLogClickInXmq2 && this.alreadyLogClickInXmq == xmPushActionNotification.alreadyLogClickInXmq;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionNotification)) {
            return equals((XmPushActionNotification) obj);
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public byte[] getBinaryExtra() {
        setBinaryExtra(TBaseHelper.rightSize(this.binaryExtra));
        return this.binaryExtra.array();
    }

    public Map<String, String> getExtra() {
        return this.extra;
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

    public boolean isSetAlreadyLogClickInXmq() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public boolean isSetBinaryExtra() {
        return this.binaryExtra != null;
    }

    public boolean isSetCategory() {
        return this.category != null;
    }

    public boolean isSetCreatedTs() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetDebug() {
        return this.debug != null;
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

    public boolean isSetPayload() {
        return this.payload != null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public boolean isSetRequireAck() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public boolean isSetType() {
        return this.type != null;
    }

    public void putToExtra(String str, String str2) {
        if (this.extra == null) {
            this.extra = new HashMap();
        }
        this.extra.put(str, str2);
    }

    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        while (true) {
            TField readFieldBegin = tProtocol.readFieldBegin();
            if (readFieldBegin.type == 0) {
                tProtocol.readStructEnd();
                if (isSetRequireAck()) {
                    validate();
                    return;
                }
                throw new TProtocolException("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
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
                case 6:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.requireAck = tProtocol.readBool();
                        setRequireAckIsSet(true);
                        break;
                    }
                case 7:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.payload = tProtocol.readString();
                        break;
                    }
                case 8:
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
                case 9:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.packageName = tProtocol.readString();
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
                case 12:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.regId = tProtocol.readString();
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
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.binaryExtra = tProtocol.readBinary();
                        break;
                    }
                case 15:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.createdTs = tProtocol.readI64();
                        setCreatedTsIsSet(true);
                        break;
                    }
                case 20:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.alreadyLogClickInXmq = tProtocol.readBool();
                        setAlreadyLogClickInXmqIsSet(true);
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public void setAlreadyLogClickInXmqIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public XmPushActionNotification setAppId(String str) {
        this.appId = str;
        return this;
    }

    public XmPushActionNotification setBinaryExtra(ByteBuffer byteBuffer) {
        this.binaryExtra = byteBuffer;
        return this;
    }

    public XmPushActionNotification setBinaryExtra(byte[] bArr) {
        setBinaryExtra(ByteBuffer.wrap(bArr));
        return this;
    }

    public void setCreatedTsIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public XmPushActionNotification setExtra(Map<String, String> map) {
        this.extra = map;
        return this;
    }

    public XmPushActionNotification setId(String str) {
        this.id = str;
        return this;
    }

    public XmPushActionNotification setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public XmPushActionNotification setRequireAck(boolean z) {
        this.requireAck = z;
        setRequireAckIsSet(true);
        return this;
    }

    public void setRequireAckIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public XmPushActionNotification setType(String str) {
        this.type = str;
        return this;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
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
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.requireAck);
        if (isSetPayload()) {
            sb.append(", ");
            sb.append("payload:");
            String str5 = this.payload;
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
        if (isSetRegId()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.regId;
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
        if (isSetBinaryExtra()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            ByteBuffer byteBuffer = this.binaryExtra;
            if (byteBuffer == null) {
                sb.append("null");
            } else {
                TBaseHelper.toString(byteBuffer, sb);
            }
        }
        if (isSetCreatedTs()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.createdTs);
        }
        if (isSetAlreadyLogClickInXmq()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.alreadyLogClickInXmq);
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
        tProtocol.writeFieldBegin(REQUIRE_ACK_FIELD_DESC);
        tProtocol.writeBool(this.requireAck);
        tProtocol.writeFieldEnd();
        if (this.payload != null && isSetPayload()) {
            tProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
            tProtocol.writeString(this.payload);
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
        if (this.regId != null && isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
            tProtocol.writeFieldEnd();
        }
        if (this.binaryExtra != null && isSetBinaryExtra()) {
            tProtocol.writeFieldBegin(BINARY_EXTRA_FIELD_DESC);
            tProtocol.writeBinary(this.binaryExtra);
            tProtocol.writeFieldEnd();
        }
        if (isSetCreatedTs()) {
            tProtocol.writeFieldBegin(CREATED_TS_FIELD_DESC);
            tProtocol.writeI64(this.createdTs);
            tProtocol.writeFieldEnd();
        }
        if (isSetAlreadyLogClickInXmq()) {
            tProtocol.writeFieldBegin(ALREADY_LOG_CLICK_IN_XMQ_FIELD_DESC);
            tProtocol.writeBool(this.alreadyLogClickInXmq);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
