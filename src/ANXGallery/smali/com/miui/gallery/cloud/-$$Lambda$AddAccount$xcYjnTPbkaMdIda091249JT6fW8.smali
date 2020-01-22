.class public final synthetic Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;-><init>()V

    sput-object v0, Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;->INSTANCE:Lcom/miui/gallery/cloud/-$$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final handle(Landroid/database/Cursor;)Ljava/lang/Object;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/cloud/AddAccount;->lambda$queryExistSecretFiles$54(Landroid/database/Cursor;)Ljava/util/Set;

    move-result-object p1

    return-object p1
.end method
