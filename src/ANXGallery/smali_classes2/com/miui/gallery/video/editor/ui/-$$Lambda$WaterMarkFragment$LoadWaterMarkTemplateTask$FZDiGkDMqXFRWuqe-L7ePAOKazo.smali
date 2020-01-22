.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;

    invoke-direct {v0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;-><init>()V

    sput-object v0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;->INSTANCE:Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$LoadWaterMarkTemplateTask$FZDiGkDMqXFRWuqe-L7ePAOKazo;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask;->lambda$execute$139(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)[Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method
