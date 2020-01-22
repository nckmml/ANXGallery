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

public class XmPushActionCustomConfig implements Serializable, Cloneable, TBase<XmPushActionCustomConfig, Object> {
    private static final TField CUSTOM_CONFIGS_FIELD_DESC = new TField("", (byte) 15, 1);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionCustomConfig");
    public List<OnlineConfigItem> customConfigs;

    public int compareTo(XmPushActionCustomConfig xmPushActionCustomConfig) {
        int compareTo;
        if (!getClass().equals(xmPushActionCustomConfig.getClass())) {
            return getClass().getName().compareTo(xmPushActionCustomConfig.getClass().getName());
        }
        int compareTo2 = Boolean.valueOf(isSetCustomConfigs()).compareTo(Boolean.valueOf(xmPushActionCustomConfig.isSetCustomConfigs()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!isSetCustomConfigs() || (compareTo = TBaseHelper.compareTo((List) this.customConfigs, (List) xmPushActionCustomConfig.customConfigs)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionCustomConfig xmPushActionCustomConfig) {
        if (xmPushActionCustomConfig == null) {
            return false;
        }
        boolean isSetCustomConfigs = isSetCustomConfigs();
        boolean isSetCustomConfigs2 = xmPushActionCustomConfig.isSetCustomConfigs();
        if (isSetCustomConfigs || isSetCustomConfigs2) {
            return isSetCustomConfigs && isSetCustomConfigs2 && this.customConfigs.equals(xmPushActionCustomConfig.customConfigs);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionCustomConfig)) {
            return equals((XmPushActionCustomConfig) obj);
        }
        return false;
    }

    public List<OnlineConfigItem> getCustomConfigs() {
        return this.customConfigs;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetCustomConfigs() {
        return this.customConfigs != null;
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
            if (readFieldBegin.id != 1) {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            } else if (readFieldBegin.type == 15) {
                TList readListBegin = tProtocol.readListBegin();
                this.customConfigs = new ArrayList(readListBegin.size);
                for (int i = 0; i < readListBegin.size; i++) {
                    OnlineConfigItem onlineConfigItem = new OnlineConfigItem();
                    onlineConfigItem.read(tProtocol);
                    this.customConfigs.add(onlineConfigItem);
                }
                tProtocol.readListEnd();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        List<OnlineConfigItem> list = this.customConfigs;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.customConfigs == null) {
            throw new TProtocolException("Required field 'customConfigs' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.customConfigs != null) {
            tProtocol.writeFieldBegin(CUSTOM_CONFIGS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.customConfigs.size()));
            for (OnlineConfigItem write : this.customConfigs) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
