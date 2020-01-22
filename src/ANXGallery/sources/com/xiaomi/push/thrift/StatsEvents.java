package com.xiaomi.push.thrift;

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

public class StatsEvents implements Serializable, Cloneable, TBase<StatsEvents, Object> {
    private static final TField EVENTS_FIELD_DESC = new TField("", (byte) 15, 3);
    private static final TField OPERATOR_FIELD_DESC = new TField("", (byte) 11, 2);
    private static final TStruct STRUCT_DESC = new TStruct("StatsEvents");
    private static final TField UUID_FIELD_DESC = new TField("", (byte) 11, 1);
    public List<StatsEvent> events;
    public String operator;
    public String uuid;

    public StatsEvents() {
    }

    public StatsEvents(String str, List<StatsEvent> list) {
        this();
        this.uuid = str;
        this.events = list;
    }

    public int compareTo(StatsEvents statsEvents) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        if (!getClass().equals(statsEvents.getClass())) {
            return getClass().getName().compareTo(statsEvents.getClass().getName());
        }
        int compareTo4 = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(statsEvents.isSetUuid()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (isSetUuid() && (compareTo3 = TBaseHelper.compareTo(this.uuid, statsEvents.uuid)) != 0) {
            return compareTo3;
        }
        int compareTo5 = Boolean.valueOf(isSetOperator()).compareTo(Boolean.valueOf(statsEvents.isSetOperator()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetOperator() && (compareTo2 = TBaseHelper.compareTo(this.operator, statsEvents.operator)) != 0) {
            return compareTo2;
        }
        int compareTo6 = Boolean.valueOf(isSetEvents()).compareTo(Boolean.valueOf(statsEvents.isSetEvents()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (!isSetEvents() || (compareTo = TBaseHelper.compareTo((List) this.events, (List) statsEvents.events)) == 0) {
            return 0;
        }
        return compareTo;
    }

    public boolean equals(StatsEvents statsEvents) {
        if (statsEvents == null) {
            return false;
        }
        boolean isSetUuid = isSetUuid();
        boolean isSetUuid2 = statsEvents.isSetUuid();
        if ((isSetUuid || isSetUuid2) && (!isSetUuid || !isSetUuid2 || !this.uuid.equals(statsEvents.uuid))) {
            return false;
        }
        boolean isSetOperator = isSetOperator();
        boolean isSetOperator2 = statsEvents.isSetOperator();
        if ((isSetOperator || isSetOperator2) && (!isSetOperator || !isSetOperator2 || !this.operator.equals(statsEvents.operator))) {
            return false;
        }
        boolean isSetEvents = isSetEvents();
        boolean isSetEvents2 = statsEvents.isSetEvents();
        if (isSetEvents || isSetEvents2) {
            return isSetEvents && isSetEvents2 && this.events.equals(statsEvents.events);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof StatsEvents)) {
            return equals((StatsEvents) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetEvents() {
        return this.events != null;
    }

    public boolean isSetOperator() {
        return this.operator != null;
    }

    public boolean isSetUuid() {
        return this.uuid != null;
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
            if (s != 1) {
                if (s != 2) {
                    if (s != 3) {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    } else if (readFieldBegin.type == 15) {
                        TList readListBegin = tProtocol.readListBegin();
                        this.events = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            StatsEvent statsEvent = new StatsEvent();
                            statsEvent.read(tProtocol);
                            this.events.add(statsEvent);
                        }
                        tProtocol.readListEnd();
                    } else {
                        TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                    }
                } else if (readFieldBegin.type == 11) {
                    this.operator = tProtocol.readString();
                } else {
                    TProtocolUtil.skip(tProtocol, readFieldBegin.type);
                }
            } else if (readFieldBegin.type == 11) {
                this.uuid = tProtocol.readString();
            } else {
                TProtocolUtil.skip(tProtocol, readFieldBegin.type);
            }
            tProtocol.readFieldEnd();
        }
    }

    public StatsEvents setOperator(String str) {
        this.operator = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append("uuid:");
        String str = this.uuid;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (isSetOperator()) {
            sb.append(", ");
            sb.append("operator:");
            String str2 = this.operator;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("events:");
        List<StatsEvent> list = this.events;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.uuid == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.events == null) {
            throw new TProtocolException("Required field 'events' was not present! Struct: " + toString());
        }
    }

    public void write(TProtocol tProtocol) throws TException {
        validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.uuid != null) {
            tProtocol.writeFieldBegin(UUID_FIELD_DESC);
            tProtocol.writeString(this.uuid);
            tProtocol.writeFieldEnd();
        }
        if (this.operator != null && isSetOperator()) {
            tProtocol.writeFieldBegin(OPERATOR_FIELD_DESC);
            tProtocol.writeString(this.operator);
            tProtocol.writeFieldEnd();
        }
        if (this.events != null) {
            tProtocol.writeFieldBegin(EVENTS_FIELD_DESC);
            tProtocol.writeListBegin(new TList((byte) 12, this.events.size()));
            for (StatsEvent write : this.events) {
                write.write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }
}
