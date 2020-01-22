.class final Lcn/kuaipan/android/exception/ServerMsgMap;
.super Ljava/lang/Object;
.source "ServerMsgMap.java"


# static fields
.field private static CODE_MAP:Lcn/kuaipan/android/utils/TwoKeyHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcn/kuaipan/android/utils/TwoKeyHashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 10

    new-instance v0, Lcn/kuaipan/android/utils/TwoKeyHashMap;

    invoke-direct {v0}, Lcn/kuaipan/android/utils/TwoKeyHashMap;-><init>()V

    sput-object v0, Lcn/kuaipan/android/exception/ServerMsgMap;->CODE_MAP:Lcn/kuaipan/android/utils/TwoKeyHashMap;

    const/16 v0, 0xca

    const v1, 0x35c29

    const-string v2, "badEmailFormat"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c2a

    const-string v2, "sameEmailRegisteredBefore"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c2b

    const-string v2, "login fail"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c2c

    const-string v2, "bad openid"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c2d

    const-string v2, "wrong verification code"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c2e

    const-string v2, "cannot create app folder"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c37

    const-string v2, "pickupCodeNotSupport"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35c38

    const-string v2, "pickupCodeTooLong"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v1, "file exist"

    const v2, 0x35c2f

    invoke-static {v0, v2, v1}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v2, "file not exist"

    const v3, 0x35c30

    invoke-static {v0, v3, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v3, 0x35c31

    const-string v4, "tooManyFiles"

    invoke-static {v0, v3, v4}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v3, "file too large"

    const v4, 0x35c32

    invoke-static {v0, v4, v3}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v4, "over space"

    const v5, 0x35c33

    invoke-static {v0, v5, v4}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v5, 0x35c3b

    const-string v6, "fnameTooLong"

    invoke-static {v0, v5, v6}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v5, "commit fail"

    const v6, 0x35c34

    invoke-static {v0, v6, v5}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v6, "forbidden"

    const v7, 0x35c35

    invoke-static {v0, v7, v6}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x35c36

    const-string v8, "account server error"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x35c39

    const-string v8, "shared"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x35c3a

    const-string v8, "cannotBind"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a981

    const/16 v7, 0x190

    const-string v8, "bad parameters"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v8, 0x3a982

    const-string v9, "bad request"

    invoke-static {v7, v8, v9}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v8, 0x3a983

    const-string v9, "no such api implemented"

    invoke-static {v7, v8, v9}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v8, "clientBadParams"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a984

    const-string v8, "serverError"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a985

    const-string v8, "accountServerError"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a986

    const-string v8, "unknownError"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a987

    const-string v8, "requestFail"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a988

    const-string v8, "mobileExists"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a989

    const-string v8, "sendMsgError"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a98a

    const-string v8, "tooManyRequests"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a98b

    const-string v8, "tooOften"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a98c

    const-string v8, "invalidCode"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a98d

    const-string v8, "invalidMobile"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a98e

    const-string v8, "emptyPassword"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v8, 0x3a98f

    const-string v9, "passwordTooLong"

    invoke-static {v7, v8, v9}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v8, 0x3a990

    const-string v9, "noSuchUser"

    invoke-static {v7, v8, v9}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const-string v8, "needPassword"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a991

    const-string v8, "canNotSetPassword"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a992

    const-string v8, "verifyNotRequest"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a994

    const-string v8, "expiredCode"

    invoke-static {v7, v0, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v0, 0x3a993

    invoke-static {v7, v0, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x191

    const v7, 0x3a9e5

    const-string v8, "bad signature"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9e6

    const-string v8, "reused nonce"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9e7

    const-string v8, "bad consumer key"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9e8

    const-string v8, "request expired"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9e9

    const-string v8, "not supported auth mode"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9ea

    const-string v8, "authorization expired"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9eb

    const-string v8, "api daily limit"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9ec

    const-string v8, "no right to call this api"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9ed

    const-string v8, "bad verifier"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9ee

    const-string v8, "authorization failed"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3a9ef

    const-string v8, "invalid token"

    invoke-static {v0, v7, v8}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x193

    const v7, 0x3aaad

    invoke-static {v0, v7, v1}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v7, 0x3aaae

    invoke-static {v0, v7, v6}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x194

    const v7, 0x3ab11

    invoke-static {v0, v7, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x196

    const v2, 0x3abd9

    const-string v7, "too many files"

    invoke-static {v0, v2, v7}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x19d

    const v2, 0x3ae95

    invoke-static {v0, v2, v3}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x1f4

    const v2, 0x3d091

    const-string v7, "server error"

    invoke-static {v0, v2, v7}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0x1fb

    const v2, 0x3d34d

    invoke-static {v0, v2, v4}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const/16 v0, 0xc8

    const v2, 0x35b61

    invoke-static {v0, v2, v1}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b68

    invoke-static {v0, v1, v5}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b92

    const-string v2, "ERR_BAD_PARAMS"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b93

    const-string v2, "ERR_SERVER_EXCEPTION"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b94

    const-string v2, "ERR_INVALID_CUSTOMERID"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b95

    const-string v2, "ERR_INVALID_STOID"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b96

    const-string v2, "ERR_STORAGE_REQUEST_ERROR"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b97

    const-string v2, "ERR_STORAGE_REQUEST_FAILED"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b98

    const-string v2, "ERR_CHUNK_OUT_OF_RANGE"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b99

    const-string v2, "ERR_INVALID_UPLOAD_ID"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9a

    const-string v2, "ERR_INVALID_CHUNK_POS"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9b

    const-string v2, "ERR_INVALID_CHUNK_SIZE"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9c

    const-string v2, "ERR_CHUNK_CORRUPTED"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9d

    const-string v2, "ERR_BLOCK_CORRUPTED"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9e

    const-string v2, "ERR_TOO_MANY_CURRENT_BLOCKS"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b9f

    const-string v2, "ERR_STORAGE_COMMIT_ERROR"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b69

    invoke-static {v0, v1, v6}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6a

    invoke-static {v0, v1, v4}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6b

    const-string v2, "targetNotExist"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6c

    const-string v2, "get stub fail"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6d

    const-string v2, "unsupportedCharRange"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6e

    const-string v2, "dataOperationFailed"

    invoke-static {v0, v1, v2}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    const v1, 0x35b6f

    invoke-static {v0, v1, v3}, Lcn/kuaipan/android/exception/ServerMsgMap;->add2Map(IILjava/lang/String;)V

    return-void
.end method

.method private static add2Map(IILjava/lang/String;)V
    .locals 1

    sget-object v0, Lcn/kuaipan/android/exception/ServerMsgMap;->CODE_MAP:Lcn/kuaipan/android/utils/TwoKeyHashMap;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    if-nez p2, :cond_0

    const/4 p2, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {p2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p2

    :goto_0
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v0, p0, p2, p1}, Lcn/kuaipan/android/utils/TwoKeyHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public static getErrorCode(ILjava/lang/String;)I
    .locals 1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    :goto_0
    sget-object v0, Lcn/kuaipan/android/exception/ServerMsgMap;->CODE_MAP:Lcn/kuaipan/android/utils/TwoKeyHashMap;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {v0, p0, p1}, Lcn/kuaipan/android/utils/TwoKeyHashMap;->get(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    if-nez p0, :cond_1

    const p0, 0x30d40

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    :goto_1
    return p0
.end method
