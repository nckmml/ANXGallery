package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionSubscription implements Serializable, Cloneable, TBase<XmPushActionSubscription, Object> {
    private static final TField ALIASES_FIELD_DESC = new TField("", (byte) 15, 8);
    private static final TField APP_ID_FIELD_DESC = new TField("", (byte) 11, 4);
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField DEBUG_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField("", (byte) 11, 6);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionSubscription");
    private static final TField TARGET_FIELD_DESC = new TField("", (byte) 12, 2);
    private static final TField TOPIC_FIELD_DESC = new TField("", (byte) 11, 5);
    public List<String> aliases;
    public String appId;
    public String category;
    public String debug;
    public String id;
    public String packageName;
    public Target target;
    public String topic;

    public int compareTo(XmPushActionSubscription xmPushActionSubscription) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        if (!getClass().equals(xmPushActionSubscription.getClass())) {
            return getClass().getName().compareTo(xmPushActionSubscription.getClass().getName());
        }
        int compareTo9 = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetDebug()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetDebug() && (compareTo8 = TBaseHelper.compareTo(this.debug, xmPushActionSubscription.debug)) != 0) {
            return compareTo8;
        }
        int compareTo10 = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetTarget()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetTarget() && (compareTo7 = TBaseHelper.compareTo((Comparable) this.target, (Comparable) xmPushActionSubscription.target)) != 0) {
            return compareTo7;
        }
        int compareTo11 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetId()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetId() && (compareTo6 = TBaseHelper.compareTo(this.id, xmPushActionSubscription.id)) != 0) {
            return compareTo6;
        }
        int compareTo12 = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetAppId()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetAppId() && (compareTo5 = TBaseHelper.compareTo(this.appId, xmPushActionSubscription.appId)) != 0) {
            return compareTo5;
        }
        int compareTo13 = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetTopic()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetTopic() && (compareTo4 = TBaseHelper.compareTo(this.topic, xmPushActionSubscription.topic)) != 0) {
            return compareTo4;
        }
        int compareTo14 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetPackageName()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetPackageName() && (compareTo3 = TBaseHelper.compareTo(this.packageName, xmPushActionSubscription.packageName)) != 0) {
            return compareTo3;
        }
        int compareTo15 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetCategory()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetCategory() && (compareTo2 = TBaseHelper.compareTo(this.category, xmPushActionSubscription.category)) != 0) {
            return compareTo2;
        }
        int compareTo16 = Boolean.valueOf(isSetAliases()).compareTo(Boolean.valueOf(xmPushActionSubscription.isSetAliases()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (!isSetAliases() || (compareTo = TBaseHelper.compareTo((List) this.aliases, (List) xmPushActionSubscription.aliases)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionSubscription xmPushActionSubscription) {
        if (xmPushActionSubscription == null) {
            return false;
        }
        boolean isSetDebug = isSetDebug();
        boolean isSetDebug2 = xmPushActionSubscription.isSetDebug();
        if ((isSetDebug || isSetDebug2) && (!isSetDebug || !isSetDebug2 || !this.debug.equals(xmPushActionSubscription.debug))) {
            return false;
        }
        boolean isSetTarget = isSetTarget();
        boolean isSetTarget2 = xmPushActionSubscription.isSetTarget();
        if ((isSetTarget || isSetTarget2) && (!isSetTarget || !isSetTarget2 || !this.target.equals(xmPushActionSubscription.target))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = xmPushActionSubscription.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(xmPushActionSubscription.id))) {
            return false;
        }
        boolean isSetAppId = isSetAppId();
        boolean isSetAppId2 = xmPushActionSubscription.isSetAppId();
        if ((isSetAppId || isSetAppId2) && (!isSetAppId || !isSetAppId2 || !this.appId.equals(xmPushActionSubscription.appId))) {
            return false;
        }
        boolean isSetTopic = isSetTopic();
        boolean isSetTopic2 = xmPushActionSubscription.isSetTopic();
        if ((isSetTopic || isSetTopic2) && (!isSetTopic || !isSetTopic2 || !this.topic.equals(xmPushActionSubscription.topic))) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = xmPushActionSubscription.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(xmPushActionSubscription.packageName))) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = xmPushActionSubscription.isSetCategory();
        if ((isSetCategory || isSetCategory2) && (!isSetCategory || !isSetCategory2 || !this.category.equals(xmPushActionSubscription.category))) {
            return false;
        }
        boolean isSetAliases = isSetAliases();
        boolean isSetAliases2 = xmPushActionSubscription.isSetAliases();
        if (isSetAliases || isSetAliases2) {
            return isSetAliases && isSetAliases2 && this.aliases.equals(xmPushActionSubscription.aliases);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionSubscription)) {
            return equals((XmPushActionSubscription) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAliases() {
        return this.aliases != null;
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

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public boolean isSetTopic() {
        return this.topic != null;
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
                        this.topic = tProtocol.readString();
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
                        this.category = tProtocol.readString();
                        break;
                    }
                case 8:
                    if (readFieldBegin.type != 15) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        TList readListBegin = tProtocol.readListBegin();
                        this.aliases = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            this.aliases.add(tProtocol.readString());
                        }
                        tProtocol.readListEnd();
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public XmPushActionSubscription setAppId(String str) {
        this.appId = str;
        return this;
    }

    public XmPushActionSubscription setCategory(String str) {
        this.category = str;
        return this;
    }

    public XmPushActionSubscription setId(String str) {
        this.id = str;
        return this;
    }

    public XmPushActionSubscription setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public XmPushActionSubscription setTopic(String str) {
        this.topic = str;
        return this;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscription(");
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
        sb.append("topic:");
        String str4 = this.topic;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
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
        if (isSetCategory()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.category;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (isSetAliases()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.aliases;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
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
        } else if (this.topic == null) {
            throw new TProtocolException("Required field 'topic' was not present! Struct: " + toString());
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
        if (this.topic != null) {
            tProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
            tProtocol.writeString(this.topic);
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
        if (this.aliases != null && isSetAliases()) {
            tProtocol.writeFieldBegin(ALIASES_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 11, this.aliases.size()));
            for (String writeString : this.aliases) {
                tProtocol.writeString(writeString);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
