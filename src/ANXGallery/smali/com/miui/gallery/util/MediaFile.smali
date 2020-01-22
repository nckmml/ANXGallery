.class public Lcom/miui/gallery/util/MediaFile;
.super Ljava/lang/Object;
.source "MediaFile.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/MediaFile$MediaFileType;
    }
.end annotation


# static fields
.field private static final sFileTypeMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/util/MediaFile$MediaFileType;",
            ">;"
        }
    .end annotation
.end field

.field private static final sFileTypeToFormatMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final sFormatToMimeTypeMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final sMimeTypeMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final sMimeTypeToFormatMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 6

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/MediaFile;->sFileTypeMap:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/MediaFile;->sMimeTypeMap:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/MediaFile;->sFileTypeToFormatMap:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/MediaFile;->sMimeTypeToFormatMap:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/MediaFile;->sFormatToMimeTypeMap:Ljava/util/HashMap;

    const/16 v0, 0x3009

    const-string v1, "audio/mpeg"

    const/4 v2, 0x1

    const-string v3, "MP3"

    invoke-static {v3, v2, v1, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v3, "MPGA"

    invoke-static {v3, v2, v1, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const/16 v0, 0x300b

    const-string v1, "M4A"

    const/4 v2, 0x2

    const-string v3, "audio/mp4"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "WAV"

    const/4 v2, 0x3

    const-string v3, "audio/x-wav"

    const/16 v4, 0x3008

    invoke-static {v1, v2, v3, v4}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "AMR"

    const/4 v2, 0x4

    const-string v3, "audio/amr"

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v1, "AWB"

    const/4 v2, 0x5

    const-string v3, "audio/amr-wb"

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v1, "WMA"

    const/4 v2, 0x6

    const-string v3, "audio/x-ms-wma"

    const v4, 0xb901

    invoke-static {v1, v2, v3, v4}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "OGG"

    const v2, 0xb902

    const/4 v3, 0x7

    const-string v4, "audio/ogg"

    invoke-static {v1, v3, v4, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v4, "application/ogg"

    invoke-static {v1, v3, v4, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "OGA"

    invoke-static {v1, v3, v4, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "AAC"

    const/16 v2, 0x8

    const-string v3, "audio/aac"

    const v4, 0xb903

    invoke-static {v1, v2, v3, v4}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v3, "audio/aac-adts"

    invoke-static {v1, v2, v3, v4}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "MKA"

    const/16 v2, 0x9

    const-string v3, "audio/x-matroska"

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v1, "audio/midi"

    const/16 v2, 0xb

    const-string v3, "MID"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "MIDI"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "XMF"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "RTTTL"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "SMF"

    const/16 v4, 0xc

    const-string v5, "audio/sp-midi"

    invoke-static {v3, v4, v5}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "IMY"

    const/16 v4, 0xd

    const-string v5, "audio/imelody"

    invoke-static {v3, v4, v5}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "RTX"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "OTA"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v3, "MXMF"

    invoke-static {v3, v2, v1}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const/16 v1, 0x15

    const-string v2, "MPEG"

    const-string v3, "video/mpeg"

    invoke-static {v2, v1, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v2, "MPG"

    const-string v3, "video/mpeg"

    invoke-static {v2, v1, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v2, "MP4"

    const-string v3, "video/mp4"

    invoke-static {v2, v1, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "M4V"

    const/16 v2, 0x16

    const-string v3, "video/mp4"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const v0, 0xb984

    const-string v1, "3GP"

    const/16 v2, 0x17

    const-string v3, "video/3gpp"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "3GPP"

    const-string v3, "video/3gpp"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "3G2"

    const/16 v2, 0x18

    const-string v3, "video/3gpp2"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v1, "3GPP2"

    const-string v3, "video/3gpp2"

    invoke-static {v1, v2, v3, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "MKV"

    const/16 v1, 0x1b

    const-string v2, "video/x-matroska"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "WEBM"

    const/16 v1, 0x1e

    const-string v2, "video/webm"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "TS"

    const/16 v1, 0x1c

    const-string v2, "video/mp2ts"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "AVI"

    const/16 v1, 0x1d

    const-string v2, "video/avi"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "WMV"

    const/16 v1, 0x19

    const-string v2, "video/x-ms-wmv"

    const v3, 0xb981

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "ASF"

    const/16 v1, 0x1a

    const-string v2, "video/x-ms-asf"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "JPG"

    const/16 v1, 0x1f

    const-string v2, "image/jpeg"

    const/16 v3, 0x3801

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "JPEG"

    const-string v2, "image/jpeg"

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "GIF"

    const/16 v1, 0x20

    const-string v2, "image/gif"

    const/16 v3, 0x3807

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "PNG"

    const/16 v1, 0x21

    const-string v2, "image/png"

    const/16 v3, 0x380b

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "BMP"

    const/16 v1, 0x22

    const-string v2, "image/x-ms-bmp"

    const/16 v3, 0x3804

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "WBMP"

    const/16 v1, 0x23

    const-string v2, "image/vnd.wap.wbmp"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "WEBP"

    const/16 v1, 0x24

    const-string v2, "image/webp"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "DNG"

    const/16 v1, 0x25

    const-string v2, "image/x-adobe-dng"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "M3U"

    const/16 v1, 0x29

    const-string v2, "audio/x-mpegurl"

    const v3, 0xba11

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "M3U"

    const-string v2, "application/x-mpegurl"

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "PLS"

    const/16 v1, 0x2a

    const-string v2, "audio/x-scpls"

    const v3, 0xba14

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "WPL"

    const/16 v1, 0x2b

    const-string v2, "application/vnd.ms-wpl"

    const v3, 0xba10

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const/16 v0, 0x2c

    const-string v1, "M3U8"

    const-string v2, "application/vnd.apple.mpegurl"

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v2, "audio/mpegurl"

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v2, "audio/x-mpegurl"

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "FL"

    const/16 v1, 0x33

    const-string v2, "application/x-android-drm-fl"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "DCF"

    const/16 v1, 0x34

    const-string v2, "application/vnd.oma.drm.content"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "TXT"

    const/16 v1, 0x64

    const-string v2, "text/plain"

    const/16 v3, 0x3004

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "HTM"

    const/16 v1, 0x65

    const-string v2, "text/html"

    const/16 v3, 0x3005

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "HTML"

    const-string v2, "text/html"

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "PDF"

    const/16 v1, 0x66

    const-string v2, "application/pdf"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "DOC"

    const/16 v1, 0x68

    const-string v2, "application/msword"

    const v3, 0xba83

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "XLS"

    const/16 v1, 0x69

    const-string v2, "application/vnd.ms-excel"

    const v3, 0xba85

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "PPT"

    const/16 v1, 0x6a

    const-string v2, "application/mspowerpoint"

    const v3, 0xba86

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "FLAC"

    const/16 v1, 0xa

    const-string v2, "audio/flac"

    const v3, 0xb906

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;I)V

    const-string v0, "ZIP"

    const/16 v1, 0x6b

    const-string v2, "application/zip"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "MPG"

    const/16 v1, 0xc8

    const-string v2, "video/mp2p"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "MPEG"

    const-string v2, "video/mp2p"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "DIVX"

    const/16 v1, 0xc9

    const-string v2, "video/divx"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "FLV"

    const/16 v1, 0xca

    const-string v2, "video/flv"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "MPD"

    const/16 v1, 0x2d

    const-string v2, "application/dash+xml"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "QCP"

    const/16 v1, 0x12f

    const-string v2, "audio/qcelp"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "AC3"

    const/16 v1, 0x12e

    const-string v2, "audio/ac3"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "EC3"

    const/16 v1, 0x131

    const-string v2, "audio/eac3"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "AIF"

    const/16 v1, 0x132

    const-string v2, "audio/x-aiff"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "AIFF"

    const-string v2, "audio/x-aiff"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "APE"

    const/16 v1, 0x133

    const-string v2, "audio/x-ape"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/MediaFile;->addMiuiFileType()V

    return-void
.end method

.method static addFileType(Ljava/lang/String;ILjava/lang/String;)V
    .locals 2

    sget-object v0, Lcom/miui/gallery/util/MediaFile;->sFileTypeMap:Ljava/util/HashMap;

    new-instance v1, Lcom/miui/gallery/util/MediaFile$MediaFileType;

    invoke-direct {v1, p1, p2}, Lcom/miui/gallery/util/MediaFile$MediaFileType;-><init>(ILjava/lang/String;)V

    invoke-virtual {v0, p0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p0, Lcom/miui/gallery/util/MediaFile;->sMimeTypeMap:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method static addFileType(Ljava/lang/String;ILjava/lang/String;I)V
    .locals 1

    invoke-static {p0, p1, p2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    sget-object p1, Lcom/miui/gallery/util/MediaFile;->sFileTypeToFormatMap:Ljava/util/HashMap;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p0, Lcom/miui/gallery/util/MediaFile;->sMimeTypeToFormatMap:Ljava/util/HashMap;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p0, Lcom/miui/gallery/util/MediaFile;->sFormatToMimeTypeMap:Ljava/util/HashMap;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method private static addMiuiFileType()V
    .locals 3

    const-string v0, "FLV"

    const/16 v1, 0x7d0

    const-string v2, "video/x-flv"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "video/x-pn-realvideo"

    const-string v1, "RM"

    const/16 v2, 0x7d1

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v1, "RMVB"

    const/16 v2, 0x7d2

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "MOV"

    const/16 v1, 0x7d3

    const-string v2, "video/quicktime"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "VOB"

    const/16 v1, 0x7d4

    const-string v2, "video/mpeg"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "F4V"

    const/16 v1, 0x7d5

    const-string v2, "video/mp4"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    const-string v0, "3G2B"

    const/16 v1, 0x7d6

    const-string v2, "video/3gpp"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MediaFile;->addFileType(Ljava/lang/String;ILjava/lang/String;)V

    return-void
.end method

.method public static getFileType(Ljava/lang/String;)Lcom/miui/gallery/util/MediaFile$MediaFileType;
    .locals 2

    const/16 v0, 0x2e

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    if-gez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    sget-object v1, Lcom/miui/gallery/util/MediaFile;->sFileTypeMap:Ljava/util/HashMap;

    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    sget-object v0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p0, v0}, Ljava/lang/String;->toUpperCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/util/MediaFile$MediaFileType;

    return-object p0
.end method

.method public static getMimeTypeForFile(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    invoke-static {p0}, Lcom/miui/gallery/util/MediaFile;->getFileType(Ljava/lang/String;)Lcom/miui/gallery/util/MediaFile$MediaFileType;

    move-result-object p0

    if-nez p0, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    :cond_0
    iget-object p0, p0, Lcom/miui/gallery/util/MediaFile$MediaFileType;->mimeType:Ljava/lang/String;

    :goto_0
    return-object p0
.end method
