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

public class XmPushActionNormalConfig implements Serializable, Cloneable, TBase<XmPushActionNormalConfig, Object> {
    private static final TField NORMAL_CONFIGS_FIELD_DESC = new TField("", (byte) 15, 1);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionNormalConfig");
    public List<NormalConfig> normalConfigs;

    public int compareTo(XmPushActionNormalConfig xmPushActionNormalConfig) {
        int compareTo;
        if (!getClass().equals(xmPushActionNormalConfig.getClass())) {
            return getClass().getName().compareTo(xmPushActionNormalConfig.getClass().getName());
        }
        int compareTo2 = Boolean.valueOf(isSetNormalConfigs()).compareTo(Boolean.valueOf(xmPushActionNormalConfig.isSetNormalConfigs()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!isSetNormalConfigs() || (compareTo = TBaseHelper.compareTo((List) this.normalConfigs, (List) xmPushActionNormalConfig.normalConfigs)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionNormalConfig xmPushActionNormalConfig) {
        if (xmPushActionNormalConfig == null) {
            return false;
        }
        boolean isSetNormalConfigs = isSetNormalConfigs();
        boolean isSetNormalConfigs2 = xmPushActionNormalConfig.isSetNormalConfigs();
        if (isSetNormalConfigs || isSetNormalConfigs2) {
            return isSetNormalConfigs && isSetNormalConfigs2 && this.normalConfigs.equals(xmPushActionNormalConfig.normalConfigs);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionNormalConfig)) {
            return equals((XmPushActionNormalConfig) obj);
        }
        return false;
    }

    public List<NormalConfig> getNormalConfigs() {
        return this.normalConfigs;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetNormalConfigs() {
        return this.normalConfigs != null;
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
                this.normalConfigs = new ArrayList(readListBegin.size);
                for (int i = 0; i < readListBegin.size; i++) {
                    NormalConfig normalConfig = new NormalConfig();
                    normalConfig.read(tProtocol);
                    this.normalConfigs.add(normalConfig);
                }
                tProtocol.readListEnd();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        List<NormalConfig> list = this.normalConfigs;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.normalConfigs == null) {
            throw new TProtocolException("Required field 'normalConfigs' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.normalConfigs != null) {
            tProtocol.writeFieldBegin(NORMAL_CONFIGS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.normalConfigs.size()));
            for (NormalConfig write : this.normalConfigs) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
