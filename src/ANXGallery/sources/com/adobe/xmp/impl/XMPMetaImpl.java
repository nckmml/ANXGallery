package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.PropertyOptions;
import java.util.Iterator;

public class XMPMetaImpl implements XMPMeta {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String packetHeader;
    private XMPNode tree;

    public XMPMetaImpl() {
        this.packetHeader = null;
        this.tree = new XMPNode((String) null, (String) null, (PropertyOptions) null);
    }

    public XMPMetaImpl(XMPNode xMPNode) {
        this.packetHeader = null;
        this.tree = xMPNode;
    }

    private Object evaluateNodeValue(int i, XMPNode xMPNode) throws XMPException {
        String value = xMPNode.getValue();
        switch (i) {
            case 1:
                return new Boolean(XMPUtils.convertToBoolean(value));
            case 2:
                return new Integer(XMPUtils.convertToInteger(value));
            case 3:
                return new Long(XMPUtils.convertToLong(value));
            case 4:
                return new Double(XMPUtils.convertToDouble(value));
            case 5:
                return XMPUtils.convertToDate(value);
            case 6:
                return XMPUtils.convertToDate(value).getCalendar();
            case 7:
                return XMPUtils.decodeBase64(value);
            default:
                if (value == null && !xMPNode.getOptions().isCompositeProperty()) {
                    value = "";
                }
                return value;
        }
    }

    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    public Integer getPropertyInteger(String str, String str2) throws XMPException {
        return (Integer) getPropertyObject(str, str2, 2);
    }

    /* access modifiers changed from: protected */
    public Object getPropertyObject(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        if (i == 0 || !findNode.getOptions().isCompositeProperty()) {
            return evaluateNodeValue(i, findNode);
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public String getPropertyString(String str, String str2) throws XMPException {
        return (String) getPropertyObject(str, str2, 0);
    }

    public XMPNode getRoot() {
        return this.tree;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        if (r2 == null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009f, code lost:
        if (r8.getChildrenLength() <= 1) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a1, code lost:
        r8.removeChild(r2);
        r8.addChild(1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a7, code lost:
        r10 = com.adobe.xmp.impl.XMPNodeUtils.chooseLocalizedText(r8, r10, r11);
        r0 = ((java.lang.Integer) r10[0]).intValue();
        r10 = (com.adobe.xmp.impl.XMPNode) r10[1];
        r3 = "x-default".equals(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bb, code lost:
        if (r0 == 0) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bd, code lost:
        if (r0 == 1) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c0, code lost:
        if (r0 == 2) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c3, code lost:
        if (r0 == 3) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c6, code lost:
        if (r0 == 4) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c9, code lost:
        if (r0 != 5) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cb, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ce, code lost:
        if (r3 == false) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00db, code lost:
        throw new com.adobe.xmp.XMPException("Unexpected result from ChooseLocalizedText", 9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00dc, code lost:
        if (r2 == null) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        if (r8.getChildrenLength() != 1) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e4, code lost:
        r2.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e7, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ec, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ef, code lost:
        if (r3 == false) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f3, code lost:
        if (r9 == false) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f5, code lost:
        if (r2 == r10) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f7, code lost:
        if (r2 == null) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0105, code lost:
        if (r2.getValue().equals(r10.getValue()) == false) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0107, code lost:
        r2.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010a, code lost:
        r10.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010e, code lost:
        if (r3 != false) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0110, code lost:
        if (r9 == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0112, code lost:
        if (r2 == r10) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0114, code lost:
        if (r2 == null) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0122, code lost:
        if (r2.getValue().equals(r10.getValue()) == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0124, code lost:
        r2.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0127, code lost:
        r10.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x012b, code lost:
        r10 = r8.iterateChildren();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0133, code lost:
        if (r10.hasNext() == false) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0135, code lost:
        r11 = (com.adobe.xmp.impl.XMPNode) r10.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013b, code lost:
        if (r11 == r2) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        r0 = r11.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0141, code lost:
        if (r2 == null) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0143, code lost:
        r3 = r2.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0148, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x014d, code lost:
        if (r0.equals(r3) != false) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0150, code lost:
        r11.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0154, code lost:
        if (r2 == null) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0156, code lost:
        r2.setValue(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x015a, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, "x-default", r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x015d, code lost:
        if (r3 != false) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x015f, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0162, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0163, code lost:
        if (r9 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0169, code lost:
        if (r8.getChildrenLength() != 1) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x016b, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r8, "x-default", r12);
     */
    public void setLocalizedText(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        XMPNode xMPNode;
        boolean z;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertSpecificLang(str4);
        String normalizeLangValue = str3 != null ? Utils.normalizeLangValue(str3) : null;
        String normalizeLangValue2 = Utils.normalizeLangValue(str4);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), true, new PropertyOptions(7680));
        if (findNode != null) {
            if (!findNode.getOptions().isArrayAltText()) {
                if (findNode.hasChildren() || !findNode.getOptions().isArrayAlternate()) {
                    throw new XMPException("Specified property is no alt-text array", 102);
                }
                findNode.getOptions().setArrayAltText(true);
            }
            Iterator iterateChildren = findNode.iterateChildren();
            while (true) {
                if (!iterateChildren.hasNext()) {
                    xMPNode = null;
                    z = false;
                    break;
                }
                xMPNode = (XMPNode) iterateChildren.next();
                if (xMPNode.hasQualifier() && "xml:lang".equals(xMPNode.getQualifier(1).getName())) {
                    if ("x-default".equals(xMPNode.getQualifier(1).getValue())) {
                        z = true;
                        break;
                    }
                }
            }
            throw new XMPException("Language qualifier must be first", 102);
        }
        throw new XMPException("Failed to find or create array node", 102);
    }

    public void setPacketHeader(String str) {
        this.packetHeader = str;
    }
}
