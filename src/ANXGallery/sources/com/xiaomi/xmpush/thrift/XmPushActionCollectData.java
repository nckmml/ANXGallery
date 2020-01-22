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

public class XmPushActionCollectData implements Serializable, Cloneable, TBase<XmPushActionCollectData, Object> {
    private static final TField DATA_COLLECTION_ITEMS_FIELD_DESC = new TField("", (byte) 15, 1);
    private static final TStruct STRUCT_DESC = new TStruct("XmPushActionCollectData");
    public List<DataCollectionItem> dataCollectionItems;

    public int compareTo(XmPushActionCollectData xmPushActionCollectData) {
        int compareTo;
        if (!getClass().equals(xmPushActionCollectData.getClass())) {
            return getClass().getName().compareTo(xmPushActionCollectData.getClass().getName());
        }
        int compareTo2 = Boolean.valueOf(isSetDataCollectionItems()).compareTo(Boolean.valueOf(xmPushActionCollectData.isSetDataCollectionItems()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!isSetDataCollectionItems() || (compareTo = TBaseHelper.compareTo((List) this.dataCollectionItems, (List) xmPushActionCollectData.dataCollectionItems)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(XmPushActionCollectData xmPushActionCollectData) {
        if (xmPushActionCollectData == null) {
            return false;
        }
        boolean isSetDataCollectionItems = isSetDataCollectionItems();
        boolean isSetDataCollectionItems2 = xmPushActionCollectData.isSetDataCollectionItems();
        if (isSetDataCollectionItems || isSetDataCollectionItems2) {
            return isSetDataCollectionItems && isSetDataCollectionItems2 && this.dataCollectionItems.equals(xmPushActionCollectData.dataCollectionItems);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof XmPushActionCollectData)) {
            return equals((XmPushActionCollectData) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetDataCollectionItems() {
        return this.dataCollectionItems != null;
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
                this.dataCollectionItems = new ArrayList(readListBegin.size);
                for (int i = 0; i < readListBegin.size; i++) {
                    DataCollectionItem dataCollectionItem = new DataCollectionItem();
                    dataCollectionItem.read(tProtocol);
                    this.dataCollectionItems.add(dataCollectionItem);
                }
                tProtocol.readListEnd();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public XmPushActionCollectData setDataCollectionItems(List<DataCollectionItem> list) {
        this.dataCollectionItems = list;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        List<DataCollectionItem> list = this.dataCollectionItems;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.dataCollectionItems == null) {
            throw new TProtocolException("Required field 'dataCollectionItems' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.dataCollectionItems != null) {
            tProtocol.writeFieldBegin(DATA_COLLECTION_ITEMS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.dataCollectionItems.size()));
            for (DataCollectionItem write : this.dataCollectionItems) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
