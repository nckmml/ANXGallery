.class public final synthetic Lcom/miui/gallery/editor/photo/screen/doodle/-$$Lambda$ScreenDoodleMenuFragment$O7wLNv3V5r3DK7VC4F4SOk7tzLc;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;

.field private final synthetic f$1:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleAdapter;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleAdapter;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/doodle/-$$Lambda$ScreenDoodleMenuFragment$O7wLNv3V5r3DK7VC4F4SOk7tzLc;->f$0:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/screen/doodle/-$$Lambda$ScreenDoodleMenuFragment$O7wLNv3V5r3DK7VC4F4SOk7tzLc;->f$1:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleAdapter;

    return-void
.end method


# virtual methods
.method public final OnItemClick(Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;I)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/doodle/-$$Lambda$ScreenDoodleMenuFragment$O7wLNv3V5r3DK7VC4F4SOk7tzLc;->f$0:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/doodle/-$$Lambda$ScreenDoodleMenuFragment$O7wLNv3V5r3DK7VC4F4SOk7tzLc;->f$1:Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleAdapter;

    invoke-virtual {v0, v1, p1, p2, p3}, Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;->lambda$onViewCreated$71$ScreenDoodleMenuFragment(Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleAdapter;Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;I)Z

    move-result p1

    return p1
.end method
