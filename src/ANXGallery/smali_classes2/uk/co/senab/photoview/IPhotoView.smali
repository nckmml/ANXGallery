.class public interface abstract Luk/co/senab/photoview/IPhotoView;
.super Ljava/lang/Object;
.source "IPhotoView.java"


# static fields
.field public static final DEFAULT_ZOOM_INTERPOLATOR:Landroid/view/animation/Interpolator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Landroid/view/animation/AccelerateDecelerateInterpolator;

    invoke-direct {v0}, Landroid/view/animation/AccelerateDecelerateInterpolator;-><init>()V

    sput-object v0, Luk/co/senab/photoview/IPhotoView;->DEFAULT_ZOOM_INTERPOLATOR:Landroid/view/animation/Interpolator;

    return-void
.end method
