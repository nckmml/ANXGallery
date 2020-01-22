package com.xiaomi.mistatistic.sdk.controller;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.xiaomi.mistatistic.sdk.controller.e;

@TargetApi(14)
/* compiled from: ActivityLifecycleMonitor */
public class b implements Application.ActivityLifecycleCallbacks {
    private static Integer a = 0;
    private final Object b = new Object();
    private boolean c = false;
    private int d = 0;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        try {
            synchronized (this.b) {
                if (a.intValue() == 0) {
                    if (this.c) {
                        j.b("ALM", "The App enters foreground from the background.");
                    } else {
                        j.b("ALM", "The App enters foreground for the first time.");
                    }
                    e.a().a((e.a) new e.a() {
                        public void execute() {
                            s.a().h();
                        }
                    });
                    this.c = true;
                }
                if (this.d < 0) {
                    this.d++;
                } else {
                    a = Integer.valueOf(a.intValue() + 1);
                }
            }
        } catch (Exception e) {
            j.a("ALM", "onActivityStarted exception", (Throwable) e);
        }
    }

    public void onActivityStopped(Activity activity) {
        try {
            synchronized (this.b) {
                if (activity.isChangingConfigurations()) {
                    j.b("ALM", "Activity is changing configuration.");
                    this.d--;
                } else {
                    a = Integer.valueOf(a.intValue() - 1);
                    if (a.intValue() == 0) {
                        j.b("ALM", "The App enters background.");
                        e.a().a((e.a) new e.a() {
                            public void execute() {
                                s.a().g();
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            j.a("ALM", "onActivityStopped exception", (Throwable) e);
        }
    }
}
