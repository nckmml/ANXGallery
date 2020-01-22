.class public Lcom/miui/gallery/util/TransitionPatching;
.super Ljava/lang/Object;
.source "TransitionPatching.java"


# direct methods
.method public static onActivityStopWhenEnterStarting(Landroid/app/Activity;)V
    .locals 7

    const-string v0, "removeListener"

    const-string v1, "forceViewsToAppear"

    const-string v2, "android.app.EnterTransitionCoordinator"

    const-string v3, "TransitionPatching"

    const-string v4, "onActivityStopWhenEnterStarting"

    invoke-static {v3, v4}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz p0, :cond_2

    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x17

    if-gt v4, v5, :cond_0

    goto :goto_0

    :cond_0
    :try_start_0
    const-string v4, "android.app.Activity"

    const-string v5, "mActivityTransitionState"

    invoke-static {v4, p0, v5}, Lcom/miui/gallery/util/ReflectUtils;->getField(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_2

    const-string v4, "android.app.ActivityTransitionState"

    const-string v5, "mEnterTransitionCoordinator"

    invoke-static {v4, p0, v5}, Lcom/miui/gallery/util/ReflectUtils;->getField(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_2

    invoke-static {v2, v1}, Lcom/miui/gallery/util/ReflectUtils;->getMethod(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/reflect/Method;

    move-result-object v4

    const/4 v5, 0x0

    if-eqz v4, :cond_1

    new-array v6, v5, [Ljava/lang/Object;

    invoke-static {p0, v4, v6}, Lcom/miui/gallery/util/ReflectUtils;->invokeMethod(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    const-string v1, "mViewsReadyListener"

    invoke-static {v2, p0, v1}, Lcom/miui/gallery/util/ReflectUtils;->getField(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_2

    const-string v1, "com.android.internal.view.OneShotPreDrawListener"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/ReflectUtils;->getMethod(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/reflect/Method;

    move-result-object v1

    if-eqz v1, :cond_2

    new-array v2, v5, [Ljava/lang/Object;

    invoke-static {p0, v1, v2}, Lcom/miui/gallery/util/ReflectUtils;->invokeMethod(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v3, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string v0, "preActivityStop occurs error.\n"

    invoke-static {v3, v0, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_2
    :goto_0
    return-void
.end method

.method public static setOnEnterStartedListener(Landroid/app/Activity;Ljava/lang/Runnable;)V
    .locals 2

    const-string v0, "TransitionPatching"

    const-string v1, "onStartEnterTransition"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz p0, :cond_4

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object p0

    if-nez p0, :cond_1

    return-void

    :cond_1
    invoke-virtual {p0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p0

    if-nez p0, :cond_2

    return-void

    :cond_2
    invoke-virtual {p0}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    if-nez v0, :cond_3

    return-void

    :cond_3
    new-instance v1, Lcom/miui/gallery/util/TransitionPatching$1;

    invoke-direct {v1, p1, v0, p0}, Lcom/miui/gallery/util/TransitionPatching$1;-><init>(Ljava/lang/Runnable;Landroid/view/ViewTreeObserver;Landroid/view/View;)V

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    :cond_4
    :goto_0
    return-void
.end method
