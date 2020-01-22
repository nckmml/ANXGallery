.class Lcom/miui/gallery/model/BaseDataItem$5;
.super Ljava/lang/Object;
.source "BaseDataItem.java"

# interfaces
.implements Lcom/miui/gallery/model/BaseDataItem$Func;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/BaseDataItem;->setPathDisplayBetter(ILjava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/model/BaseDataItem$Func<",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/BaseDataItem;

.field final synthetic val$path:Ljava/lang/String;

.field final synthetic val$type:I


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/BaseDataItem;ILjava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/BaseDataItem$5;->this$0:Lcom/miui/gallery/model/BaseDataItem;

    iput p2, p0, Lcom/miui/gallery/model/BaseDataItem$5;->val$type:I

    iput-object p3, p0, Lcom/miui/gallery/model/BaseDataItem$5;->val$path:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic doFunc()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/model/BaseDataItem$5;->doFunc()Ljava/lang/Void;

    move-result-object v0

    return-object v0
.end method

.method public doFunc()Ljava/lang/Void;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/model/BaseDataItem$5;->this$0:Lcom/miui/gallery/model/BaseDataItem;

    invoke-static {v0}, Lcom/miui/gallery/model/BaseDataItem;->access$200(Lcom/miui/gallery/model/BaseDataItem;)Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/model/BaseDataItem$5;->this$0:Lcom/miui/gallery/model/BaseDataItem;

    new-instance v2, Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;

    invoke-direct {v2, v0, v1}, Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;-><init>(Lcom/miui/gallery/model/BaseDataItem;Lcom/miui/gallery/model/BaseDataItem$1;)V

    invoke-static {v0, v2}, Lcom/miui/gallery/model/BaseDataItem;->access$202(Lcom/miui/gallery/model/BaseDataItem;Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;)Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/model/BaseDataItem$5;->this$0:Lcom/miui/gallery/model/BaseDataItem;

    invoke-static {v0}, Lcom/miui/gallery/model/BaseDataItem;->access$200(Lcom/miui/gallery/model/BaseDataItem;)Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;

    move-result-object v0

    iget v2, p0, Lcom/miui/gallery/model/BaseDataItem$5;->val$type:I

    iget-object v3, p0, Lcom/miui/gallery/model/BaseDataItem$5;->val$path:Ljava/lang/String;

    invoke-virtual {v0, v2, v3}, Lcom/miui/gallery/model/BaseDataItem$DisplayBetterPath;->setPath(ILjava/lang/String;)V

    return-object v1
.end method
