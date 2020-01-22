.class public Lcom/miui/gallery/card/SyncTag;
.super Lcom/miui/gallery/dao/base/Entity;
.source "SyncTag.java"


# instance fields
.field private mAccountName:Ljava/lang/String;

.field private mAccountType:Ljava/lang/String;

.field private mCardSyncInfo:Ljava/lang/String;

.field private mCardSyncTag:J


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    return-void
.end method

.method public constructor <init>(Landroid/accounts/Account;)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    iget-object v0, p1, Landroid/accounts/Account;->name:Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mAccountName:Ljava/lang/String;

    iget-object p1, p1, Landroid/accounts/Account;->type:Ljava/lang/String;

    iput-object p1, p0, Lcom/miui/gallery/card/SyncTag;->mAccountType:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public getCardSyncInfo()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncInfo:Ljava/lang/String;

    return-object v0
.end method

.method public getCardSyncTag()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncTag:J

    return-wide v0
.end method

.method protected getTableColumns()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/dao/base/TableColumn;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const-string v1, "TEXT"

    const-string v2, "accountName"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/SyncTag;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "accountType"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/SyncTag;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "cardSyncTag"

    const-string v3, "INTEGER"

    invoke-static {v0, v2, v3}, Lcom/miui/gallery/card/SyncTag;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "cardSyncInfo"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/SyncTag;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method protected onConvertToContents(Landroid/content/ContentValues;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mAccountName:Ljava/lang/String;

    const-string v1, "accountName"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mAccountType:Ljava/lang/String;

    const-string v1, "accountType"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncTag:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "cardSyncTag"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncInfo:Ljava/lang/String;

    const-string v1, "cardSyncInfo"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method protected onInitFromCursor(Landroid/database/Cursor;)V
    .locals 2

    const-string v0, "accountName"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/SyncTag;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mAccountName:Ljava/lang/String;

    const-string v0, "accountType"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/SyncTag;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/SyncTag;->mAccountType:Ljava/lang/String;

    const-string v0, "cardSyncTag"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/SyncTag;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncTag:J

    const-string v0, "cardSyncInfo"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/SyncTag;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/card/SyncTag;->mCardSyncInfo:Ljava/lang/String;

    return-void
.end method
