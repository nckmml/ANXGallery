package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smack.util.StringUtils;

public class Message extends Packet {
    private String fseq = "";
    private String language;
    private String mAppId;
    private String mBody;
    private String mBodyEncoding;
    private boolean mEncrypted = false;
    private String mSubject;
    private boolean mTransient = false;
    private String mseq = "";
    private String seq = "";
    private String status = "";
    private String thread = null;
    private String type = null;

    public Message() {
    }

    public Message(Bundle bundle) {
        super(bundle);
        this.type = bundle.getString("ext_msg_type");
        this.language = bundle.getString("ext_msg_lang");
        this.thread = bundle.getString("ext_msg_thread");
        this.mSubject = bundle.getString("ext_msg_sub");
        this.mBody = bundle.getString("ext_msg_body");
        this.mBodyEncoding = bundle.getString("ext_body_encode");
        this.mAppId = bundle.getString("ext_msg_appid");
        this.mTransient = bundle.getBoolean("ext_msg_trans", false);
        this.mEncrypted = bundle.getBoolean("ext_msg_encrypt", false);
        this.seq = bundle.getString("ext_msg_seq");
        this.mseq = bundle.getString("ext_msg_mseq");
        this.fseq = bundle.getString("ext_msg_fseq");
        this.status = bundle.getString("ext_msg_status");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message message = (Message) obj;
        if (!super.equals(message)) {
            return false;
        }
        String str = this.mBody;
        if (str == null ? message.mBody != null : !str.equals(message.mBody)) {
            return false;
        }
        String str2 = this.language;
        if (str2 == null ? message.language != null : !str2.equals(message.language)) {
            return false;
        }
        String str3 = this.mSubject;
        if (str3 == null ? message.mSubject != null : !str3.equals(message.mSubject)) {
            return false;
        }
        String str4 = this.thread;
        if (str4 == null ? message.thread == null : str4.equals(message.thread)) {
            return this.type == message.type;
        }
        return false;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getFSeq() {
        return this.fseq;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getMSeq() {
        return this.mseq;
    }

    public String getSeq() {
        return this.seq;
    }

    public String getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mBody;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.thread;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.language;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.mSubject;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    public void setBody(String str) {
        this.mBody = str;
    }

    public void setBody(String str, String str2) {
        this.mBody = str;
        this.mBodyEncoding = str2;
    }

    public void setEncrypted(boolean z) {
        this.mEncrypted = z;
    }

    public void setFSeq(String str) {
        this.fseq = str;
    }

    public void setIsTransient(boolean z) {
        this.mTransient = z;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setMSeq(String str) {
        this.mseq = str;
    }

    public void setSeq(String str) {
        this.seq = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSubject(String str) {
        this.mSubject = str;
    }

    public void setThread(String str) {
        this.thread = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (!TextUtils.isEmpty(this.type)) {
            bundle.putString("ext_msg_type", this.type);
        }
        String str = this.language;
        if (str != null) {
            bundle.putString("ext_msg_lang", str);
        }
        String str2 = this.mSubject;
        if (str2 != null) {
            bundle.putString("ext_msg_sub", str2);
        }
        String str3 = this.mBody;
        if (str3 != null) {
            bundle.putString("ext_msg_body", str3);
        }
        if (!TextUtils.isEmpty(this.mBodyEncoding)) {
            bundle.putString("ext_body_encode", this.mBodyEncoding);
        }
        String str4 = this.thread;
        if (str4 != null) {
            bundle.putString("ext_msg_thread", str4);
        }
        String str5 = this.mAppId;
        if (str5 != null) {
            bundle.putString("ext_msg_appid", str5);
        }
        if (this.mTransient) {
            bundle.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.seq)) {
            bundle.putString("ext_msg_seq", this.seq);
        }
        if (!TextUtils.isEmpty(this.mseq)) {
            bundle.putString("ext_msg_mseq", this.mseq);
        }
        if (!TextUtils.isEmpty(this.fseq)) {
            bundle.putString("ext_msg_fseq", this.fseq);
        }
        if (this.mEncrypted) {
            bundle.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.status)) {
            bundle.putString("ext_msg_status", this.status);
        }
        return bundle;
    }

    public String toXML() {
        XMPPError error;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (getXmlns() != null) {
            sb.append(" xmlns=\"");
            sb.append(getXmlns());
            sb.append("\"");
        }
        if (this.language != null) {
            sb.append(" xml:lang=\"");
            sb.append(getLanguage());
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
        if (!TextUtils.isEmpty(getSeq())) {
            sb.append(" seq=\"");
            sb.append(getSeq());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(getMSeq())) {
            sb.append(" mseq=\"");
            sb.append(getMSeq());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(getFSeq())) {
            sb.append(" fseq=\"");
            sb.append(getFSeq());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(getStatus())) {
            sb.append(" status=\"");
            sb.append(getStatus());
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
        if (this.mTransient) {
            sb.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.mAppId)) {
            sb.append(" appid=\"");
            sb.append(getAppId());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(this.type)) {
            sb.append(" type=\"");
            sb.append(this.type);
            sb.append("\"");
        }
        if (this.mEncrypted) {
            sb.append(" s=\"1\"");
        }
        sb.append(">");
        if (this.mSubject != null) {
            sb.append("<subject>");
            sb.append(StringUtils.escapeForXML(this.mSubject));
            sb.append("</subject>");
        }
        if (this.mBody != null) {
            sb.append("<body");
            if (!TextUtils.isEmpty(this.mBodyEncoding)) {
                sb.append(" encode=\"");
                sb.append(this.mBodyEncoding);
                sb.append("\"");
            }
            sb.append(">");
            sb.append(StringUtils.escapeForXML(this.mBody));
            sb.append("</body>");
        }
        if (this.thread != null) {
            sb.append("<thread>");
            sb.append(this.thread);
            sb.append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.type) && (error = getError()) != null) {
            sb.append(error.toXML());
        }
        sb.append(getExtensionsXML());
        sb.append("</message>");
        return sb.toString();
    }
}
