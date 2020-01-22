package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.smack.util.StringUtils;

public class Presence extends Packet {
    private Mode mode = null;
    private int priority = Integer.MIN_VALUE;
    private String status = null;
    private Type type = Type.available;

    public enum Mode {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public Presence(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_pres_type")) {
            this.type = Type.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.status = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.priority = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.mode = Mode.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public Presence(Type type2) {
        setType(type2);
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    public void setPriority(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.priority = i;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setType(Type type2) {
        if (type2 != null) {
            this.type = type2;
            return;
        }
        throw new NullPointerException("Type cannot be null");
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        Type type2 = this.type;
        if (type2 != null) {
            bundle.putString("ext_pres_type", type2.toString());
        }
        String str = this.status;
        if (str != null) {
            bundle.putString("ext_pres_status", str);
        }
        int i = this.priority;
        if (i != Integer.MIN_VALUE) {
            bundle.putInt("ext_pres_prio", i);
        }
        Mode mode2 = this.mode;
        if (!(mode2 == null || mode2 == Mode.available)) {
            bundle.putString("ext_pres_mode", this.mode.toString());
        }
        return bundle;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (getXmlns() != null) {
            sb.append(" xmlns=\"");
            sb.append(getXmlns());
            sb.append("\"");
        }
        if (getPacketID() != null) {
            sb.append(" id=\"");
            sb.append(getPacketID());
            sb.append("\"");
        }
        if (getTo() != null) {
            sb.append(" to=\"");
            sb.append(StringUtils.escapeForXML(getTo()));
            sb.append("\"");
        }
        if (getFrom() != null) {
            sb.append(" from=\"");
            sb.append(StringUtils.escapeForXML(getFrom()));
            sb.append("\"");
        }
        if (getChannelId() != null) {
            sb.append(" chid=\"");
            sb.append(StringUtils.escapeForXML(getChannelId()));
            sb.append("\"");
        }
        if (this.type != null) {
            sb.append(" type=\"");
            sb.append(this.type);
            sb.append("\"");
        }
        sb.append(">");
        if (this.status != null) {
            sb.append("<status>");
            sb.append(StringUtils.escapeForXML(this.status));
            sb.append("</status>");
        }
        if (this.priority != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.priority);
            sb.append("</priority>");
        }
        Mode mode2 = this.mode;
        if (!(mode2 == null || mode2 == Mode.available)) {
            sb.append("<show>");
            sb.append(this.mode);
            sb.append("</show>");
        }
        sb.append(getExtensionsXML());
        XMPPError error = getError();
        if (error != null) {
            sb.append(error.toXML());
        }
        sb.append("</presence>");
        return sb.toString();
    }
}
