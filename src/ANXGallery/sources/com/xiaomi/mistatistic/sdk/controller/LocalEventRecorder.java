package com.xiaomi.mistatistic.sdk.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.CustomSettings;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import com.xiaomi.mistatistic.sdk.data.f;
import com.xiaomi.mistatistic.sdk.data.g;
import com.xiaomi.xmsf.push.service.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class LocalEventRecorder {
    /* access modifiers changed from: private */
    public static volatile b a;
    /* access modifiers changed from: private */
    public static volatile boolean b;
    /* access modifiers changed from: private */
    public static List<AbstractEvent> c = new ArrayList();
    /* access modifiers changed from: private */
    public static Object d = new Object();
    /* access modifiers changed from: private */
    public static ServiceConnection e = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            j.a("LER", "IStatService connected");
            b unused = LocalEventRecorder.a = b.a.a(iBinder);
            if (LocalEventRecorder.a != null) {
                r.b.execute(new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0063, code lost:
                        r1 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
                        r2 = r1;
                        r1 = r3;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008d, code lost:
                        r1 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008f, code lost:
                        r1 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
                        com.xiaomi.mistatistic.sdk.controller.j.a("dispatch event to IStatService exception", (java.lang.Throwable) r1);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
                        com.xiaomi.mistatistic.sdk.controller.j.a("LER", "pending eventList size: " + com.xiaomi.mistatistic.sdk.controller.LocalEventRecorder.e().size());
                        r1 = com.xiaomi.mistatistic.sdk.controller.d.a();
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ea, code lost:
                        com.xiaomi.mistatistic.sdk.controller.j.a("LER", "pending eventList size: " + com.xiaomi.mistatistic.sdk.controller.LocalEventRecorder.e().size());
                        com.xiaomi.mistatistic.sdk.controller.LocalEventRecorder.a(com.xiaomi.mistatistic.sdk.controller.d.a());
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010f, code lost:
                        throw r1;
                     */
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f A[ExcHandler: RemoteException (r1v5 'e' android.os.RemoteException A[CUSTOM_DECLARE]), Splitter:B:3:0x0008] */
                    public void run() {
                        Context a2;
                        synchronized (LocalEventRecorder.d) {
                            AbstractEvent abstractEvent = null;
                            try {
                                j.a("LER", "start insert event to IStatService and eventList size: " + LocalEventRecorder.c.size());
                                if (LocalEventRecorder.c != null && !LocalEventRecorder.c.isEmpty()) {
                                    Iterator it = LocalEventRecorder.c.iterator();
                                    while (it.hasNext()) {
                                        AbstractEvent abstractEvent2 = (AbstractEvent) it.next();
                                        LocalEventRecorder.a.a(abstractEvent2.valueToJSon().toString());
                                        j.a("LER", "insert a reserved event into IStatService");
                                        it.remove();
                                        AbstractEvent abstractEvent3 = abstractEvent2;
                                    }
                                }
                                j.a("LER", "pending eventList size: " + LocalEventRecorder.c.size());
                                a2 = d.a();
                            } catch (JSONException e) {
                                e = e;
                                j.a("dispatch event to IStatService exception", (Throwable) e);
                                LocalEventRecorder.c.remove(abstractEvent);
                                j.a("LER", "pending eventList size: " + LocalEventRecorder.c.size());
                                a2 = d.a();
                                LocalEventRecorder.c(a2);
                            } catch (RemoteException e2) {
                            }
                            LocalEventRecorder.c(a2);
                        }
                    }
                });
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            j.a("LER", "IStatService has unexpectedly disconnected");
            b unused = LocalEventRecorder.a = null;
            boolean unused2 = LocalEventRecorder.b = false;
        }
    };

    public static class a implements e.a {
        private AbstractEvent a;

        public a(AbstractEvent abstractEvent) {
            this.a = abstractEvent;
        }

        public void execute() {
            StatEventPojo pojo = this.a.toPojo();
            h hVar = new h();
            AbstractEvent abstractEvent = this.a;
            if ((abstractEvent instanceof f) || (abstractEvent instanceof g)) {
                String str = pojo.mKey;
                String str2 = pojo.mCategory;
                StatEventPojo a2 = hVar.a(str2, str);
                if (a2 == null || !pojo.mType.equals(a2.mType)) {
                    hVar.a(pojo);
                    n.a("w", pojo);
                    return;
                }
                hVar.a(str, str2, pojo.mValue);
                return;
            }
            hVar.a(pojo);
            n.a("w", pojo);
        }
    }

    private static void b(Context context) throws InterruptedException {
        if (!b) {
            Intent intent = new Intent();
            intent.setClassName("com.xiaomi.xmsf", "com.xiaomi.xmsf.push.service.StatService");
            b = context.bindService(intent, e, 1);
            j.a("LER", "bind StatSystemService: " + b);
        }
    }

    /* access modifiers changed from: private */
    public static void b(final AbstractEvent abstractEvent) {
        try {
            b(d.a());
            if (a != null) {
                r.b.execute(new Runnable() {
                    public void run() {
                        try {
                            LocalEventRecorder.a.a(abstractEvent.valueToJSon().toString());
                        } catch (RemoteException e) {
                            j.a("LER", "dispatch event to IStatService exception", (Throwable) e);
                        } catch (JSONException e2) {
                            j.a("LER", "dispatch event to IStatService exception", (Throwable) e2);
                        }
                    }
                });
                j.b("LER", "StatSystemService is not null, insert a event");
                return;
            }
            j.a("LER", "StatSystemService is null, insert event into eventList");
            synchronized (d) {
                c.add(abstractEvent);
            }
        } catch (Throwable th) {
            j.a("LER", "insertEventUseSystemService exception: ", th);
        }
    }

    /* access modifiers changed from: private */
    public static void c(final Context context) {
        e.a().a((e.a) new e.a() {
            public void execute() {
                try {
                    if (LocalEventRecorder.b) {
                        context.unbindService(LocalEventRecorder.e);
                        boolean unused = LocalEventRecorder.b = false;
                        b unused2 = LocalEventRecorder.a = null;
                        j.a("LER", "unbind StatSystemService");
                    }
                } catch (Exception e) {
                    j.a("unbindStatSystemService exception", (Throwable) e);
                }
            }
        }, 180000);
    }

    public static void insertEvent(final AbstractEvent abstractEvent) {
        try {
            Context a2 = d.a();
            if (a2 == null) {
                j.d("LER", "mistats is not initialized properly.");
                return;
            }
            n.a("c", abstractEvent);
            if (BuildSetting.isCTABuild()) {
                j.d("LER", "disable local event upload for CTA build");
            } else if (CustomSettings.isUseSystemStatService()) {
                j.a("LER", "insert event use SystemStatService.");
                e.a().a((e.a) new e.a() {
                    public void execute() {
                        LocalEventRecorder.b(abstractEvent);
                    }
                });
            } else if (!BuildSetting.isDisabled(a2) || t.e(abstractEvent.getCategory()) || abstractEvent.getAnonymous() != 0) {
                e.a().a((e.a) new a(abstractEvent));
                s.a().c();
            } else {
                j.a("LER", "disabled local event upload, event category:" + abstractEvent.getCategory());
            }
        } catch (Exception e2) {
            j.a("LER", "insertEvent exception", (Throwable) e2);
        }
    }
}
