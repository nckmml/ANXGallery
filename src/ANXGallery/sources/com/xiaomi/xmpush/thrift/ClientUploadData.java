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

public class ClientUploadData implements Serializable, Cloneable, TBase<ClientUploadData, Object> {
    private static final TStruct STRUCT_DESC = new TStruct("ClientUploadData");
    private static final TField UPLOAD_DATA_ITEMS_FIELD_DESC = new TField("", (byte) 15, 1);
    public List<ClientUploadDataItem> uploadDataItems;

    public void addToUploadDataItems(ClientUploadDataItem clientUploadDataItem) {
        if (this.uploadDataItems == null) {
            this.uploadDataItems = new ArrayList();
        }
        this.uploadDataItems.add(clientUploadDataItem);
    }

    public int compareTo(ClientUploadData clientUploadData) {
        int compareTo;
        if (!getClass().equals(clientUploadData.getClass())) {
            return getClass().getName().compareTo(clientUploadData.getClass().getName());
        }
        int compareTo2 = Boolean.valueOf(isSetUploadDataItems()).compareTo(Boolean.valueOf(clientUploadData.isSetUploadDataItems()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!isSetUploadDataItems() || (compareTo = TBaseHelper.compareTo((List) this.uploadDataItems, (List) clientUploadData.uploadDataItems)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(ClientUploadData clientUploadData) {
        if (clientUploadData == null) {
            return false;
        }
        boolean isSetUploadDataItems = isSetUploadDataItems();
        boolean isSetUploadDataItems2 = clientUploadData.isSetUploadDataItems();
        if (isSetUploadDataItems || isSetUploadDataItems2) {
            return isSetUploadDataItems && isSetUploadDataItems2 && this.uploadDataItems.equals(clientUploadData.uploadDataItems);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ClientUploadData)) {
            return equals((ClientUploadData) obj);
        }
        return false;
    }

    public int getUploadDataItemsSize() {
        List<ClientUploadDataItem> list = this.uploadDataItems;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetUploadDataItems() {
        return this.uploadDataItems != null;
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
                this.uploadDataItems = new ArrayList(readListBegin.size);
                for (int i = 0; i < readListBegin.size; i++) {
                    ClientUploadDataItem clientUploadDataItem = new ClientUploadDataItem();
                    clientUploadDataItem.read(tProtocol);
                    this.uploadDataItems.add(clientUploadDataItem);
                }
                tProtocol.readListEnd();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        List<ClientUploadDataItem> list = this.uploadDataItems;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.uploadDataItems == null) {
            throw new TProtocolException("Required field 'uploadDataItems' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.uploadDataItems != null) {
            tProtocol.writeFieldBegin(UPLOAD_DATA_ITEMS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.uploadDataItems.size()));
            for (ClientUploadDataItem write : this.uploadDataItems) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
