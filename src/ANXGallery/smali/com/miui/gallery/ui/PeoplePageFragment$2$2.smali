.class Lcom/miui/gallery/ui/PeoplePageFragment$2$2;
.super Ljava/lang/Object;
.source "PeoplePageFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/PeoplePageFragment$2;->onAntiDoubleItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener<",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

.field final synthetic val$peopleId:Ljava/lang/String;

.field final synthetic val$peopleLocalId:Ljava/lang/String;

.field final synthetic val$peopleName:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PeoplePageFragment$2;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

    iput-object p2, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleLocalId:Ljava/lang/String;

    iput-object p3, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleId:Ljava/lang/String;

    iput-object p4, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleName:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCompleteProcess(Ljava/lang/Boolean;)V
    .locals 6

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

    iget-object v0, p1, Lcom/miui/gallery/ui/PeoplePageFragment$2;->this$0:Lcom/miui/gallery/ui/PeoplePageFragment;

    iget-object v1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleLocalId:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleId:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->val$peopleName:Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

    iget-object p1, p1, Lcom/miui/gallery/ui/PeoplePageFragment$2;->this$0:Lcom/miui/gallery/ui/PeoplePageFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/PeoplePageFragment;->access$900(Lcom/miui/gallery/ui/PeoplePageFragment;)Ljava/lang/String;

    move-result-object v4

    iget-object p1, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

    iget-object p1, p1, Lcom/miui/gallery/ui/PeoplePageFragment$2;->this$0:Lcom/miui/gallery/ui/PeoplePageFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/PeoplePageFragment;->access$800(Lcom/miui/gallery/ui/PeoplePageFragment;)Lcom/miui/gallery/model/PeopleContactInfo$Relation;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/model/PeopleContactInfo$Relation;->getRelationType()I

    move-result v5

    invoke-static/range {v0 .. v5}, Lcom/miui/gallery/ui/PeoplePageFragment;->access$1000(Lcom/miui/gallery/ui/PeoplePageFragment;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V

    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->this$1:Lcom/miui/gallery/ui/PeoplePageFragment$2;

    iget-object v0, v0, Lcom/miui/gallery/ui/PeoplePageFragment$2;->this$0:Lcom/miui/gallery/ui/PeoplePageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PeoplePageFragment;->access$800(Lcom/miui/gallery/ui/PeoplePageFragment;)Lcom/miui/gallery/model/PeopleContactInfo$Relation;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/model/PeopleContactInfo$Relation;->getRelationType()I

    move-result v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "relationType"

    invoke-interface {p1, v1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "people_mark"

    const-string v1, "mark_relation_in_people_page"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p1

    const v0, 0x7f10054d

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :goto_0
    return-void
.end method

.method public bridge synthetic onCompleteProcess(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PeoplePageFragment$2$2;->onCompleteProcess(Ljava/lang/Boolean;)V

    return-void
.end method
