package com.xiaomi.smack;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.keyczar.Keyczar;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public final class SmackConfiguration {
    private static Vector<String> defaultMechs = new Vector<>();
    private static int keepAliveInterval = 330000;
    private static int packetReplyTimeout = 5000;
    private static int pingInterval = 600000;
    private static int serverShutdownTimeout = 330000;

    /* JADX WARNING: Can't wrap try/catch for region: R(5:30|34|35|36|37) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00a6 */
    static {
        InputStream inputStream;
        try {
            for (ClassLoader resources : getClassLoaders()) {
                Enumeration<URL> resources2 = resources.getResources("META-INF/smack-config.xml");
                while (resources2.hasMoreElements()) {
                    inputStream = null;
                    try {
                        inputStream = resources2.nextElement().openStream();
                        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                        newPullParser.setInput(inputStream, Keyczar.DEFAULT_ENCODING);
                        int eventType = newPullParser.getEventType();
                        do {
                            if (eventType == 2) {
                                if (newPullParser.getName().equals("className")) {
                                    parseClassToLoad(newPullParser);
                                } else if (newPullParser.getName().equals("packetReplyTimeout")) {
                                    packetReplyTimeout = parseIntProperty(newPullParser, packetReplyTimeout);
                                } else if (newPullParser.getName().equals("keepAliveInterval")) {
                                    keepAliveInterval = parseIntProperty(newPullParser, keepAliveInterval);
                                } else if (newPullParser.getName().equals("mechName")) {
                                    defaultMechs.add(newPullParser.nextText());
                                }
                            }
                            eventType = newPullParser.next();
                        } while (eventType != 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        inputStream.close();
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    private SmackConfiguration() {
    }

    public static int getCheckAliveInterval() {
        return keepAliveInterval;
    }

    private static ClassLoader[] getClassLoaders() {
        ClassLoader[] classLoaderArr = {SmackConfiguration.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    public static int getPingInteval() {
        return pingInterval;
    }

    public static String getVersion() {
        return "3.1.0";
    }

    private static void parseClassToLoad(XmlPullParser xmlPullParser) throws Exception {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException unused) {
            PrintStream printStream = System.err;
            printStream.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    private static int parseIntProperty(XmlPullParser xmlPullParser, int i) throws Exception {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }
}
