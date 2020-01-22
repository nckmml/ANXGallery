.class public Lcom/miui/gallery/xmstreaming/XmsEffect;
.super Lcom/miui/gallery/xmstreaming/XmsObject;
.source "XmsEffect.java"


# instance fields
.field private animation:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/xmstreaming/XmsAnimation;",
            ">;"
        }
    .end annotation
.end field

.field private name:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/xmstreaming/XmsObject;-><init>()V

    return-void
.end method


# virtual methods
.method public getAnimation()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/xmstreaming/XmsAnimation;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->animation:Ljava/util/List;

    return-object v0
.end method

.method public getName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->name:Ljava/lang/String;

    return-object v0
.end method

.method protected mapData()V
    .locals 3

    invoke-super {p0}, Lcom/miui/gallery/xmstreaming/XmsObject;->mapData()V

    iget-object v0, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->m_attachmentMap:Ljava/util/HashMap;

    iget-object v1, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->name:Ljava/lang/String;

    const-string v2, "name"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->m_attachmentMap:Ljava/util/HashMap;

    iget-object v1, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->animation:Ljava/util/List;

    const-string v2, "animation"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->animation:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/xmstreaming/XmsAnimation;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsAnimation;->mapData()V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public setAnimation(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/xmstreaming/XmsAnimation;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->animation:Ljava/util/List;

    return-void
.end method

.method public setName(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/xmstreaming/XmsEffect;->name:Ljava/lang/String;

    return-void
.end method
