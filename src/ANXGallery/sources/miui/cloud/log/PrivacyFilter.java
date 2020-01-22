package miui.cloud.log;

import java.util.regex.Pattern;

public class PrivacyFilter {
    private static Pattern sIpv4Pattern;

    static {
        initIpv4PatternString();
    }

    public static String filterPrivacyLog(String str) {
        return replacePrivacyLog(str, sIpv4Pattern, "@IPV4");
    }

    private static void initIpv4PatternString() {
        sIpv4Pattern = Pattern.compile("(" + "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])" + "\\.){3}" + "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])");
    }

    private static String replacePrivacyLog(String str, Pattern pattern, String str2) {
        return pattern == null ? str : pattern.matcher(str).replaceAll(str2);
    }
}
