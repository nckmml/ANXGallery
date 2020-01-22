package org.apache.thrift.protocol;

public class TField {
    public final short id;
    public final String name;
    public final byte type;

    public TField() {
        this("", (byte) 0, 0);
    }

    public TField(String str, byte b, short s) {
        this.name = str;
        this.type = b;
        this.id = s;
    }

    public String toString() {
        return "<TField name:'" + this.name + "' type:" + this.type + " field-id:" + this.id + ">";
    }
}
