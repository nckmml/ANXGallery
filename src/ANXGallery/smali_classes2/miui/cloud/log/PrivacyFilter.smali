.class public Lmiui/cloud/log/PrivacyFilter;
.super Ljava/lang/Object;
.source "PrivacyFilter.java"


# static fields
.field private static sIpv4Pattern:Ljava/util/regex/Pattern;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    invoke-static {}, Lmiui/cloud/log/PrivacyFilter;->initIpv4PatternString()V

    return-void
.end method

.method public static filterPrivacyLog(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    sget-object v0, Lmiui/cloud/log/PrivacyFilter;->sIpv4Pattern:Ljava/util/regex/Pattern;

    const-string v1, "@IPV4"

    invoke-static {p0, v0, v1}, Lmiui/cloud/log/PrivacyFilter;->replacePrivacyLog(Ljava/lang/String;Ljava/util/regex/Pattern;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static initIpv4PatternString()V
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\\.){3}"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lmiui/cloud/log/PrivacyFilter;->sIpv4Pattern:Ljava/util/regex/Pattern;

    return-void
.end method

.method private static replacePrivacyLog(Ljava/lang/String;Ljava/util/regex/Pattern;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    if-nez p1, :cond_0

    return-object p0

    :cond_0
    invoke-virtual {p1, p0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object p0

    invoke-virtual {p0, p2}, Ljava/util/regex/Matcher;->replaceAll(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
