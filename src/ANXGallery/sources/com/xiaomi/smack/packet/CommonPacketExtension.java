package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonPacketExtension implements PacketExtension {
    private String[] mAttributeNames = null;
    private String[] mAttributeValues = null;
    private List<CommonPacketExtension> mChildrenEles = null;
    private String mExtensionElementName;
    private String mNamespace;
    private String mText;

    public CommonPacketExtension(String str, String str2, String[] strArr, String[] strArr2) {
        this.mExtensionElementName = str;
        this.mNamespace = str2;
        this.mAttributeNames = strArr;
        this.mAttributeValues = strArr2;
    }

    public CommonPacketExtension(String str, String str2, String[] strArr, String[] strArr2, String str3, List<CommonPacketExtension> list) {
        this.mExtensionElementName = str;
        this.mNamespace = str2;
        this.mAttributeNames = strArr;
        this.mAttributeValues = strArr2;
        this.mText = str3;
        this.mChildrenEles = list;
    }

    public static CommonPacketExtension parseFromBundle(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i = 0;
        for (String str : keySet) {
            strArr[i] = str;
            strArr2[i] = bundle2.getString(str);
            i++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            ArrayList arrayList2 = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add(parseFromBundle((Bundle) parcelable));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return new CommonPacketExtension(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] toParcelableArray(List<CommonPacketExtension> list) {
        return toParcelableArray((CommonPacketExtension[]) list.toArray(new CommonPacketExtension[list.size()]));
    }

    public static Parcelable[] toParcelableArray(CommonPacketExtension[] commonPacketExtensionArr) {
        if (commonPacketExtensionArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[commonPacketExtensionArr.length];
        for (int i = 0; i < commonPacketExtensionArr.length; i++) {
            parcelableArr[i] = commonPacketExtensionArr[i].toParcelable();
        }
        return parcelableArr;
    }

    public String getAttributeValue(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (this.mAttributeNames == null) {
            return null;
        } else {
            int i = 0;
            while (true) {
                String[] strArr = this.mAttributeNames;
                if (i >= strArr.length) {
                    return null;
                }
                if (str.equals(strArr[i])) {
                    return this.mAttributeValues[i];
                }
                i++;
            }
        }
    }

    public String getElementName() {
        return this.mExtensionElementName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getText() {
        return !TextUtils.isEmpty(this.mText) ? StringUtils.unescapeFromXML(this.mText) : this.mText;
    }

    public void setText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mText = StringUtils.escapeForXML(str);
        } else {
            this.mText = str;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.mExtensionElementName);
        bundle.putString("ext_ns", this.mNamespace);
        bundle.putString("ext_text", this.mText);
        Bundle bundle2 = new Bundle();
        String[] strArr = this.mAttributeNames;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.mAttributeNames;
                if (i >= strArr2.length) {
                    break;
                }
                bundle2.putString(strArr2[i], this.mAttributeValues[i]);
                i++;
            }
        }
        bundle.putBundle("attributes", bundle2);
        List<CommonPacketExtension> list = this.mChildrenEles;
        if (list != null && list.size() > 0) {
            bundle.putParcelableArray("children", toParcelableArray(this.mChildrenEles));
        }
        return bundle;
    }

    public Parcelable toParcelable() {
        return toBundle();
    }

    public String toString() {
        return toXML();
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.mExtensionElementName);
        if (!TextUtils.isEmpty(this.mNamespace)) {
            sb.append(" ");
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.mNamespace);
            sb.append("\"");
        }
        String[] strArr = this.mAttributeNames;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < this.mAttributeNames.length; i++) {
                if (!TextUtils.isEmpty(this.mAttributeValues[i])) {
                    sb.append(" ");
                    sb.append(this.mAttributeNames[i]);
                    sb.append("=\"");
                    sb.append(StringUtils.escapeForXML(this.mAttributeValues[i]));
                    sb.append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.mText)) {
            sb.append(">");
            sb.append(this.mText);
            sb.append("</");
            sb.append(this.mExtensionElementName);
            sb.append(">");
        } else {
            List<CommonPacketExtension> list = this.mChildrenEles;
            if (list == null || list.size() <= 0) {
                sb.append("/>");
            } else {
                sb.append(">");
                for (CommonPacketExtension xml : this.mChildrenEles) {
                    sb.append(xml.toXML());
                }
                sb.append("</");
                sb.append(this.mExtensionElementName);
                sb.append(">");
            }
        }
        return sb.toString();
    }
}
