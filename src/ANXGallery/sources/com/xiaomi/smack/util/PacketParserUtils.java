package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.CommonPacketExtensionProvider;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.provider.ProviderManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PacketParserUtils {
    private static XmlPullParser sDecryptedMsgParser;

    private static String getLanguageAttribute(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    private static String parseContent(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        String str = "";
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    public static XMPPError parseError(XmlPullParser xmlPullParser) throws Exception {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = MovieStatUtils.DOWNLOAD_FAILED;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals("code")) {
                str3 = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i).equals(nexExportFormat.TAG_FORMAT_TYPE)) {
                str = xmlPullParser.getAttributeValue("", nexExportFormat.TAG_FORMAT_TYPE);
            }
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str2 = xmlPullParser.getAttributeValue("", "reason");
            }
        }
        String str4 = null;
        String str5 = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str5 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str4 = name;
                    } else {
                        arrayList.add(parsePacketExtension(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                str5 = xmlPullParser.getText();
            }
        }
        return new XMPPError(Integer.parseInt(str3), str == null ? "cancel" : str, str2, str4, str5, arrayList);
    }

    public static IQ parseIQ(XmlPullParser xmlPullParser, Connection connection) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        IQ.Type fromString = IQ.Type.fromString(xmlPullParser.getAttributeValue("", nexExportFormat.TAG_FORMAT_TYPE));
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
        }
        IQ iq = null;
        XMPPError xMPPError = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    xMPPError = parseError(xmlPullParser);
                } else {
                    iq = new IQ();
                    iq.addExtension(parsePacketExtension(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (iq == null) {
            if (IQ.Type.GET == fromString || IQ.Type.SET == fromString) {
                AnonymousClass1 r13 = new IQ() {
                    public String getChildElementXML() {
                        return null;
                    }
                };
                r13.setPacketID(attributeValue);
                r13.setTo(attributeValue3);
                r13.setFrom(attributeValue2);
                r13.setType(IQ.Type.ERROR);
                r13.setChannelId(attributeValue4);
                r13.setError(new XMPPError(XMPPError.Condition.feature_not_implemented));
                connection.sendPacket(r13);
                MyLog.e("iq usage error. send packet in packet parser.");
                return null;
            }
            iq = new IQ() {
                public String getChildElementXML() {
                    return null;
                }
            };
        }
        iq.setPacketID(attributeValue);
        iq.setTo(attributeValue2);
        iq.setChannelId(attributeValue4);
        iq.setFrom(attributeValue3);
        iq.setType(fromString);
        iq.setError(xMPPError);
        iq.setAttributes(hashMap);
        return iq;
    }

    public static Packet parseMessage(XmlPullParser xmlPullParser) throws Exception {
        String str;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        boolean z = false;
        String str2 = null;
        if ("1".equals(xmlPullParser2.getAttributeValue("", "s"))) {
            String attributeValue = xmlPullParser2.getAttributeValue("", "chid");
            String attributeValue2 = xmlPullParser2.getAttributeValue("", "id");
            String attributeValue3 = xmlPullParser2.getAttributeValue("", "from");
            String attributeValue4 = xmlPullParser2.getAttributeValue("", "to");
            String attributeValue5 = xmlPullParser2.getAttributeValue("", nexExportFormat.TAG_FORMAT_TYPE);
            PushClientsManager.ClientLoginInfo clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(attributeValue, attributeValue4);
            if (clientLoginInfoByChidAndUserId == null) {
                clientLoginInfoByChidAndUserId = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(attributeValue, attributeValue3);
            }
            if (clientLoginInfoByChidAndUserId != null) {
                Packet packet = null;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        if (!"s".equals(xmlPullParser.getName())) {
                            throw new XMPPException("error while receiving a encrypted message with wrong format");
                        } else if (xmlPullParser.next() == 4) {
                            String text = xmlPullParser.getText();
                            if ("5".equals(attributeValue) || "6".equals(attributeValue)) {
                                Message message = new Message();
                                message.setChannelId(attributeValue);
                                message.setEncrypted(true);
                                message.setFrom(attributeValue3);
                                message.setTo(attributeValue4);
                                message.setPacketID(attributeValue2);
                                message.setType(attributeValue5);
                                String[] strArr = null;
                                CommonPacketExtension commonPacketExtension = new CommonPacketExtension("s", (String) null, strArr, strArr);
                                commonPacketExtension.setText(text);
                                message.addExtension(commonPacketExtension);
                                return message;
                            }
                            resetDecryptedMsgParser(RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(clientLoginInfoByChidAndUserId.security, attributeValue2), text));
                            sDecryptedMsgParser.next();
                            packet = parseMessage(sDecryptedMsgParser);
                        } else {
                            throw new XMPPException("error while receiving a encrypted message with wrong format");
                        }
                    } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                        z = true;
                    }
                }
                if (packet != null) {
                    return packet;
                }
                throw new XMPPException("error while receiving a encrypted message with wrong format");
            }
            throw new XMPPException("the channel id is wrong while receiving a encrypted message");
        }
        Message message2 = new Message();
        String attributeValue6 = xmlPullParser2.getAttributeValue("", "id");
        if (attributeValue6 == null) {
            attributeValue6 = "ID_NOT_AVAILABLE";
        }
        message2.setPacketID(attributeValue6);
        message2.setTo(xmlPullParser2.getAttributeValue("", "to"));
        message2.setFrom(xmlPullParser2.getAttributeValue("", "from"));
        message2.setChannelId(xmlPullParser2.getAttributeValue("", "chid"));
        message2.setAppId(xmlPullParser2.getAttributeValue("", "appid"));
        try {
            str = xmlPullParser2.getAttributeValue("", "transient");
        } catch (Exception unused) {
            str = null;
        }
        try {
            String attributeValue7 = xmlPullParser2.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                message2.setSeq(attributeValue7);
            }
        } catch (Exception unused2) {
        }
        try {
            String attributeValue8 = xmlPullParser2.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                message2.setMSeq(attributeValue8);
            }
        } catch (Exception unused3) {
        }
        try {
            String attributeValue9 = xmlPullParser2.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                message2.setFSeq(attributeValue9);
            }
        } catch (Exception unused4) {
        }
        try {
            String attributeValue10 = xmlPullParser2.getAttributeValue("", "status");
            if (!TextUtils.isEmpty(attributeValue10)) {
                message2.setStatus(attributeValue10);
            }
        } catch (Exception unused5) {
        }
        message2.setIsTransient(!TextUtils.isEmpty(str) && str.equalsIgnoreCase("true"));
        message2.setType(xmlPullParser2.getAttributeValue("", nexExportFormat.TAG_FORMAT_TYPE));
        String languageAttribute = getLanguageAttribute(xmlPullParser);
        if (languageAttribute == null || "".equals(languageAttribute.trim())) {
            Packet.getDefaultLanguage();
        } else {
            message2.setLanguage(languageAttribute);
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (name.equals("subject")) {
                    String languageAttribute2 = getLanguageAttribute(xmlPullParser);
                    message2.setSubject(parseContent(xmlPullParser));
                } else if (name.equals("body")) {
                    String attributeValue11 = xmlPullParser2.getAttributeValue("", "encode");
                    String parseContent = parseContent(xmlPullParser);
                    if (!TextUtils.isEmpty(attributeValue11)) {
                        message2.setBody(parseContent, attributeValue11);
                    } else {
                        message2.setBody(parseContent);
                    }
                } else if (name.equals("thread")) {
                    if (str2 == null) {
                        str2 = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    message2.setError(parseError(xmlPullParser));
                } else {
                    message2.addExtension(parsePacketExtension(name, namespace, xmlPullParser2));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        message2.setThread(str2);
        return message2;
    }

    public static CommonPacketExtension parsePacketExtension(String str, String str2, XmlPullParser xmlPullParser) throws Exception {
        Object extensionProvider = ProviderManager.getInstance().getExtensionProvider("all", "xm:chat");
        if (extensionProvider == null || !(extensionProvider instanceof CommonPacketExtensionProvider)) {
            return null;
        }
        return ((CommonPacketExtensionProvider) extensionProvider).parseExtension(xmlPullParser);
    }

    public static Presence parsePresence(XmlPullParser xmlPullParser) throws Exception {
        Presence.Type type = Presence.Type.available;
        String attributeValue = xmlPullParser.getAttributeValue("", nexExportFormat.TAG_FORMAT_TYPE);
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                type = Presence.Type.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        Presence presence = new Presence(type);
        presence.setTo(xmlPullParser.getAttributeValue("", "to"));
        presence.setFrom(xmlPullParser.getAttributeValue("", "from"));
        presence.setChannelId(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        presence.setPacketID(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    presence.setStatus(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        presence.setPriority(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        presence.setPriority(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        presence.setMode(Presence.Mode.valueOf(nextText));
                    } catch (IllegalArgumentException unused4) {
                        System.err.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    presence.setError(parseError(xmlPullParser));
                } else {
                    presence.addExtension(parsePacketExtension(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return presence;
    }

    public static StreamError parseStreamError(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        StreamError streamError = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                streamError = new StreamError(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return streamError;
    }

    private static void resetDecryptedMsgParser(byte[] bArr) throws XmlPullParserException {
        if (sDecryptedMsgParser == null) {
            try {
                sDecryptedMsgParser = XmlPullParserFactory.newInstance().newPullParser();
                sDecryptedMsgParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        sDecryptedMsgParser.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }
}
