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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class ClientUploadDataItem implements Serializable, Cloneable, TBase<ClientUploadDataItem, Object> {
    private static final TField CATEGORY_FIELD_DESC = new TField("", (byte) 11, 7);
    private static final TField CHANNEL_FIELD_DESC = new TField("", (byte) 11, 1);
    private static final TField COUNTER_FIELD_DESC = new TField("", (byte) 10, 4);
    private static final TField DATA_FIELD_DESC = new TField("", (byte) 11, 2);
    private static final TField EXTRA_FIELD_DESC = new TField("", (byte) 13, 10);
    private static final TField FROM_SDK_FIELD_DESC = new TField("", (byte) 2, 6);
    private static final TField ID_FIELD_DESC = new TField("", (byte) 11, 9);
    private static final TField NAME_FIELD_DESC = new TField("", (byte) 11, 3);
    private static final TField PKG_NAME_FIELD_DESC = new TField("", (byte) 11, 11);
    private static final TField SOURCE_PACKAGE_FIELD_DESC = new TField("", (byte) 11, 8);
    private static final TStruct STRUCT_DESC = new TStruct("ClientUploadDataItem");
    private static final TField TIMESTAMP_FIELD_DESC = new TField("", (byte) 10, 5);
    private BitSet __isset_bit_vector = new BitSet(3);
    public String category;
    public String channel;
    public long counter;
    public String data;
    public Map<String, String> extra;
    public boolean fromSdk;
    public String id;
    public String name;
    public String pkgName;
    public String sourcePackage;
    public long timestamp;

    public int compareTo(ClientUploadDataItem clientUploadDataItem) {
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
        if (!getClass().equals(clientUploadDataItem.getClass())) {
            return getClass().getName().compareTo(clientUploadDataItem.getClass().getName());
        }
        int compareTo12 = Boolean.valueOf(isSetChannel()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetChannel()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetChannel() && (compareTo11 = TBaseHelper.compareTo(this.channel, clientUploadDataItem.channel)) != 0) {
            return compareTo11;
        }
        int compareTo13 = Boolean.valueOf(isSetData()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetData()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetData() && (compareTo10 = TBaseHelper.compareTo(this.data, clientUploadDataItem.data)) != 0) {
            return compareTo10;
        }
        int compareTo14 = Boolean.valueOf(isSetName()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetName()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetName() && (compareTo9 = TBaseHelper.compareTo(this.name, clientUploadDataItem.name)) != 0) {
            return compareTo9;
        }
        int compareTo15 = Boolean.valueOf(isSetCounter()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetCounter()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetCounter() && (compareTo8 = TBaseHelper.compareTo(this.counter, clientUploadDataItem.counter)) != 0) {
            return compareTo8;
        }
        int compareTo16 = Boolean.valueOf(isSetTimestamp()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetTimestamp()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetTimestamp() && (compareTo7 = TBaseHelper.compareTo(this.timestamp, clientUploadDataItem.timestamp)) != 0) {
            return compareTo7;
        }
        int compareTo17 = Boolean.valueOf(isSetFromSdk()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetFromSdk()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetFromSdk() && (compareTo6 = TBaseHelper.compareTo(this.fromSdk, clientUploadDataItem.fromSdk)) != 0) {
            return compareTo6;
        }
        int compareTo18 = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetCategory()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetCategory() && (compareTo5 = TBaseHelper.compareTo(this.category, clientUploadDataItem.category)) != 0) {
            return compareTo5;
        }
        int compareTo19 = Boolean.valueOf(isSetSourcePackage()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetSourcePackage()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetSourcePackage() && (compareTo4 = TBaseHelper.compareTo(this.sourcePackage, clientUploadDataItem.sourcePackage)) != 0) {
            return compareTo4;
        }
        int compareTo20 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetId()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetId() && (compareTo3 = TBaseHelper.compareTo(this.id, clientUploadDataItem.id)) != 0) {
            return compareTo3;
        }
        int compareTo21 = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetExtra()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetExtra() && (compareTo2 = TBaseHelper.compareTo((Map) this.extra, (Map) clientUploadDataItem.extra)) != 0) {
            return compareTo2;
        }
        int compareTo22 = Boolean.valueOf(isSetPkgName()).compareTo(Boolean.valueOf(clientUploadDataItem.isSetPkgName()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (!isSetPkgName() || (compareTo = TBaseHelper.compareTo(this.pkgName, clientUploadDataItem.pkgName)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(ClientUploadDataItem clientUploadDataItem) {
        if (clientUploadDataItem == null) {
            return false;
        }
        boolean isSetChannel = isSetChannel();
        boolean isSetChannel2 = clientUploadDataItem.isSetChannel();
        if ((isSetChannel || isSetChannel2) && (!isSetChannel || !isSetChannel2 || !this.channel.equals(clientUploadDataItem.channel))) {
            return false;
        }
        boolean isSetData = isSetData();
        boolean isSetData2 = clientUploadDataItem.isSetData();
        if ((isSetData || isSetData2) && (!isSetData || !isSetData2 || !this.data.equals(clientUploadDataItem.data))) {
            return false;
        }
        boolean isSetName = isSetName();
        boolean isSetName2 = clientUploadDataItem.isSetName();
        if ((isSetName || isSetName2) && (!isSetName || !isSetName2 || !this.name.equals(clientUploadDataItem.name))) {
            return false;
        }
        boolean isSetCounter = isSetCounter();
        boolean isSetCounter2 = clientUploadDataItem.isSetCounter();
        if ((isSetCounter || isSetCounter2) && (!isSetCounter || !isSetCounter2 || this.counter != clientUploadDataItem.counter)) {
            return false;
        }
        boolean isSetTimestamp = isSetTimestamp();
        boolean isSetTimestamp2 = clientUploadDataItem.isSetTimestamp();
        if ((isSetTimestamp || isSetTimestamp2) && (!isSetTimestamp || !isSetTimestamp2 || this.timestamp != clientUploadDataItem.timestamp)) {
            return false;
        }
        boolean isSetFromSdk = isSetFromSdk();
        boolean isSetFromSdk2 = clientUploadDataItem.isSetFromSdk();
        if ((isSetFromSdk || isSetFromSdk2) && (!isSetFromSdk || !isSetFromSdk2 || this.fromSdk != clientUploadDataItem.fromSdk)) {
            return false;
        }
        boolean isSetCategory = isSetCategory();
        boolean isSetCategory2 = clientUploadDataItem.isSetCategory();
        if ((isSetCategory || isSetCategory2) && (!isSetCategory || !isSetCategory2 || !this.category.equals(clientUploadDataItem.category))) {
            return false;
        }
        boolean isSetSourcePackage = isSetSourcePackage();
        boolean isSetSourcePackage2 = clientUploadDataItem.isSetSourcePackage();
        if ((isSetSourcePackage || isSetSourcePackage2) && (!isSetSourcePackage || !isSetSourcePackage2 || !this.sourcePackage.equals(clientUploadDataItem.sourcePackage))) {
            return false;
        }
        boolean isSetId = isSetId();
        boolean isSetId2 = clientUploadDataItem.isSetId();
        if ((isSetId || isSetId2) && (!isSetId || !isSetId2 || !this.id.equals(clientUploadDataItem.id))) {
            return false;
        }
        boolean isSetExtra = isSetExtra();
        boolean isSetExtra2 = clientUploadDataItem.isSetExtra();
        if ((isSetExtra || isSetExtra2) && (!isSetExtra || !isSetExtra2 || !this.extra.equals(clientUploadDataItem.extra))) {
            return false;
        }
        boolean isSetPkgName = isSetPkgName();
        boolean isSetPkgName2 = clientUploadDataItem.isSetPkgName();
        if (isSetPkgName || isSetPkgName2) {
            return isSetPkgName && isSetPkgName2 && this.pkgName.equals(clientUploadDataItem.pkgName);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ClientUploadDataItem)) {
            return equals((ClientUploadDataItem) obj);
        }
        return false;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public String getSourcePackage() {
        return this.sourcePackage;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetCategory() {
        return this.category != null;
    }

    public boolean isSetChannel() {
        return this.channel != null;
    }

    public boolean isSetCounter() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetData() {
        return this.data != null;
    }

    public boolean isSetExtra() {
        return this.extra != null;
    }

    public boolean isSetFromSdk() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public boolean isSetName() {
        return this.name != null;
    }

    public boolean isSetPkgName() {
        return this.pkgName != null;
    }

    public boolean isSetSourcePackage() {
        return this.sourcePackage != null;
    }

    public boolean isSetTimestamp() {
        return this.__isset_bit_vector.get(1);
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
                        this.channel = tProtocol.readString();
                        break;
                    }
                case 2:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.data = tProtocol.readString();
                        break;
                    }
                case 3:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.name = tProtocol.readString();
                        break;
                    }
                case 4:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.counter = tProtocol.readI64();
                        setCounterIsSet(true);
                        break;
                    }
                case 5:
                    if (readFieldBegin.type != 10) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.timestamp = tProtocol.readI64();
                        setTimestampIsSet(true);
                        break;
                    }
                case 6:
                    if (readFieldBegin.type != 2) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.fromSdk = tProtocol.readBool();
                        setFromSdkIsSet(true);
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
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.sourcePackage = tProtocol.readString();
                        break;
                    }
                case 9:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.id = tProtocol.readString();
                        break;
                    }
                case 10:
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
                case 11:
                    if (readFieldBegin.type != 11) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                        break;
                    } else {
                        this.pkgName = tProtocol.readString();
                        break;
                    }
                default:
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    break;
            }
            tProtocol.readFieldEnd();
        }
    }

    public ClientUploadDataItem setCategory(String str) {
        this.category = str;
        return this;
    }

    public ClientUploadDataItem setChannel(String str) {
        this.channel = str;
        return this;
    }

    public ClientUploadDataItem setCounter(long j) {
        this.counter = j;
        setCounterIsSet(true);
        return this;
    }

    public void setCounterIsSet(boolean z) {
        this.__isset_bit_vector.set(0, z);
    }

    public ClientUploadDataItem setData(String str) {
        this.data = str;
        return this;
    }

    public ClientUploadDataItem setFromSdk(boolean z) {
        this.fromSdk = z;
        setFromSdkIsSet(true);
        return this;
    }

    public void setFromSdkIsSet(boolean z) {
        this.__isset_bit_vector.set(2, z);
    }

    public ClientUploadDataItem setId(String str) {
        this.id = str;
        return this;
    }

    public ClientUploadDataItem setName(String str) {
        this.name = str;
        return this;
    }

    public ClientUploadDataItem setPkgName(String str) {
        this.pkgName = str;
        return this;
    }

    public ClientUploadDataItem setSourcePackage(String str) {
        this.sourcePackage = str;
        return this;
    }

    public ClientUploadDataItem setTimestamp(long j) {
        this.timestamp = j;
        setTimestampIsSet(true);
        return this;
    }

    public void setTimestampIsSet(boolean z) {
        this.__isset_bit_vector.set(1, z);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        if (isSetChannel()) {
            sb.append("channel:");
            String str = this.channel;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (isSetData()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            String str2 = this.data;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
            z = false;
        }
        if (isSetName()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("name:");
            String str3 = this.name;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
            z = false;
        }
        if (isSetCounter()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.counter);
            z = false;
        }
        if (isSetTimestamp()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.timestamp);
            z = false;
        }
        if (isSetFromSdk()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.fromSdk);
            z = false;
        }
        if (isSetCategory()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("category:");
            String str4 = this.category;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
            z = false;
        }
        if (isSetSourcePackage()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            String str5 = this.sourcePackage;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
            z = false;
        }
        if (isSetId()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("id:");
            String str6 = this.id;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
            z = false;
        }
        if (isSetExtra()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("extra:");
            Map<String, String> map = this.extra;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
            z = false;
        }
        if (isSetPkgName()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            String str7 = this.pkgName;
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
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.channel != null && isSetChannel()) {
            tProtocol.writeFieldBegin(CHANNEL_FIELD_DESC);
            tProtocol.writeString(this.channel);
            tProtocol.writeFieldEnd();
        }
        if (this.data != null && isSetData()) {
            tProtocol.writeFieldBegin(DATA_FIELD_DESC);
            tProtocol.writeString(this.data);
            tProtocol.writeFieldEnd();
        }
        if (this.name != null && isSetName()) {
            tProtocol.writeFieldBegin(NAME_FIELD_DESC);
            tProtocol.writeString(this.name);
            tProtocol.writeFieldEnd();
        }
        if (isSetCounter()) {
            tProtocol.writeFieldBegin(COUNTER_FIELD_DESC);
            tProtocol.writeI64(this.counter);
            tProtocol.writeFieldEnd();
        }
        if (isSetTimestamp()) {
            tProtocol.writeFieldBegin(TIMESTAMP_FIELD_DESC);
            tProtocol.writeI64(this.timestamp);
            tProtocol.writeFieldEnd();
        }
        if (isSetFromSdk()) {
            tProtocol.writeFieldBegin(FROM_SDK_FIELD_DESC);
            tProtocol.writeBool(this.fromSdk);
            tProtocol.writeFieldEnd();
        }
        if (this.category != null && isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        if (this.sourcePackage != null && isSetSourcePackage()) {
            tProtocol.writeFieldBegin(SOURCE_PACKAGE_FIELD_DESC);
            tProtocol.writeString(this.sourcePackage);
            tProtocol.writeFieldEnd();
        }
        if (this.id != null && isSetId()) {
            tProtocol.writeFieldBegin(ID_FIELD_DESC);
            tProtocol.writeString(this.id);
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
        if (this.pkgName != null && isSetPkgName()) {
            tProtocol.writeFieldBegin(PKG_NAME_FIELD_DESC);
            tProtocol.writeString(this.pkgName);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
