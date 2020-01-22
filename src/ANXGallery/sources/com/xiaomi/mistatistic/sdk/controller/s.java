package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.mistatistic.sdk.b;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;

/* compiled from: UploadPolicyEngine */
public class s {
    private static volatile s a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public long d;
    /* access modifiers changed from: private */
    public long e;
    private long f;
    private long g;
    private long h;
    private long i;
    private Handler j = new Handler(e.c()) {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                s.this.l();
                s.this.s();
            } else if (i == 2) {
                s.this.m();
            } else if (i == 3) {
                if (s.this.o()) {
                    s.this.p();
                }
                s.this.t();
            } else if (i == 4) {
                s.this.p();
                s.this.q();
            }
        }
    };

    private s() {
    }

    public static s a() {
        if (a == null) {
            synchronized (s.class) {
                if (a == null) {
                    a = new s();
                }
            }
        }
        return a;
    }

    private void b(long j2) {
        if (!this.j.hasMessages(4)) {
            this.j.sendEmptyMessageDelayed(4, j2);
            j.a("UPE", "delay to upload BASIC events.");
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (!this.j.hasMessages(1)) {
            this.j.sendEmptyMessageDelayed(1, this.d);
            this.g = System.currentTimeMillis() + this.d;
            j.a("UPE", "start the polling job to upload ALL events.");
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.j.hasMessages(1)) {
            this.j.removeMessages(1);
            j.a("UPE", "stop the polling job to upload ALL events.");
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        if (o.b()) {
            j.d("UPE", "The job of Uploading is running when trigger upload ALL events.");
            return;
        }
        int i2 = this.c;
        if (i2 == 0) {
            h hVar = new h();
            int a2 = hVar.a(1);
            boolean c2 = hVar.c();
            if ((a2 != 1 || c2) && a2 <= 1) {
                j.d("UPE", "There is no event(exclude mistat_monitor) in db, so don't trigger the uploading job.");
            } else {
                new o(1).a();
            }
        } else if (i2 == 1) {
            long j2 = 0;
            for (StatEventPojo a3 : new h().a(Long.MAX_VALUE)) {
                j2 += a3.a();
            }
            j.b("UPE", "total bytes is " + j2);
            if (j2 >= this.e) {
                new o(1).a();
            } else {
                j.d("UPE", "The size is not enough, so don't trigger the uploading job.");
            }
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (new h().a(1) <= 0 || o.b()) {
            j.d("UPE", "triggerUploadAllEventsForInitialization: The condition is NOT sufficient.");
            return;
        }
        j.a("UPE", "Upload ALL events during initialization.");
        new o(1).a();
    }

    /* access modifiers changed from: private */
    public void n() {
        Context a2 = d.a();
        this.h = m.a(a2, "upload_interval_basic", 90000);
        this.i = m.a(a2, "upload_interval_min", 15000);
        j.a("UPE", "initUploadIntervalForBasicEvent: basicInterval=" + this.h + " minInterval=" + this.i);
    }

    /* access modifiers changed from: private */
    public boolean o() {
        if (!t.a(this.f, this.i)) {
            j.a("UPE", String.format("The interval to last uploading < %d.", new Object[]{Long.valueOf(this.i)}));
            return false;
        } else if (this.c != 0 || t.a(this.g, this.i)) {
            return true;
        } else {
            j.a("UPE", String.format("The interval to next uploading < %d.", new Object[]{Long.valueOf(this.i)}));
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        if (!o.b()) {
            h hVar = new h();
            int a2 = hVar.a(2);
            boolean c2 = hVar.c();
            if ((a2 != 1 || c2) && a2 <= 1) {
                j.d("UPE", "No basic event(exclude mistat_monitor) in DB, so don't trigger the uploading job.");
            } else {
                new o(2).a();
            }
        } else {
            j.d("UPE", "The job of Uploading is running when trigger upload BASIC events.");
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        if (this.j.hasMessages(3)) {
            this.j.removeMessages(3);
            j.a("UPE", "stop the polling job to upload BASIC event.");
        }
    }

    private void r() {
        if (!this.j.hasMessages(3)) {
            this.j.sendEmptyMessageDelayed(3, this.h);
            j.a("UPE", "start the polling job to upload BASIC event.");
        }
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.c == 0) {
            j.a("UPE", "Continue the upload polling for ALL events.");
            this.j.sendEmptyMessageDelayed(1, this.d);
            this.g = System.currentTimeMillis() + this.d;
        }
    }

    /* access modifiers changed from: private */
    public void t() {
        j.a("UPE", "Continue the upload polling for BASIC events.");
        this.j.sendEmptyMessageDelayed(3, this.h);
    }

    public void a(int i2) {
        j.b("UPE", "Set new network: %d", Integer.valueOf(i2));
        this.b = i2;
        m.b(d.a(), "upload_network", this.b);
    }

    public void a(final int i2, final long j2) {
        e.a().a((e.a) new e.a() {
            public void execute() {
                j.b("UPE", "Set new policy, policy: %d, value: %d", Integer.valueOf(i2), Long.valueOf(j2));
                int unused = s.this.c = i2;
                Context a2 = d.a();
                m.b(a2, "upload_policy", i2);
                if (s.this.c == 0) {
                    long unused2 = s.this.d = j2;
                    m.b(a2, "upload_interval", s.this.d);
                    s.this.j();
                } else if (s.this.c == 1) {
                    long unused3 = s.this.e = j2;
                    m.b(a2, "upload_size", s.this.e);
                    s.this.k();
                }
            }
        });
    }

    public void a(long j2) {
        if (j2 > this.g) {
            this.g = j2;
        }
    }

    public void a(long j2, long j3) {
        if (j2 <= 0 || j3 <= 0 || j2 < j3 || j2 > 3600000) {
            j.d("UPE", "basicInterval or minInterval is wrong");
            return;
        }
        Context a2 = d.a();
        m.b(a2, "upload_interval_basic", j2);
        m.b(a2, "upload_interval_min", j3);
        this.h = j2;
        this.i = j3;
        j.a("UPE", "updateUploadIntervalForBasicEvent: basicInterval=" + this.h + " minInterval=" + this.i);
    }

    public void b() {
        e.a().a((e.a) new e.a() {
            public void execute() {
                Context a2 = d.a();
                int unused = s.this.c = m.a(a2, "upload_policy", 0);
                if (s.this.c == 0) {
                    long unused2 = s.this.d = m.a(a2, "upload_interval", 90000);
                    j.c("UPE", "Upload policy is UPLOAD_POLICY_INTERVAL and interval is " + s.this.d);
                    s.this.j();
                } else if (s.this.c == 1) {
                    long unused3 = s.this.e = m.a(a2, "upload_size", 3072);
                    j.c("UPE", "Upload policy is UPLOAD_POLICY_BATCH and size is " + s.this.e);
                }
                int unused4 = s.this.b = m.a(a2, "upload_network", 31);
                j.c("UPE", "Upload network is " + s.this.b);
                s.this.n();
            }
        });
        this.j.sendEmptyMessageDelayed(2, 5000);
    }

    public boolean b(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (!(i2 == 4 || i2 == 5)) {
                        return false;
                    }
                }
            }
            if (l.a(d.a())) {
                return true;
            }
            j.d("UPE", "Current network is not connected.");
            return false;
        }
        if ((l.i(d.a()) & this.b) != 0) {
            return true;
        }
        j.d("UPE", "Current network does not meet customized setting.");
        return false;
    }

    public void c() {
        if (this.c == 1 && !this.j.hasMessages(1)) {
            this.j.sendEmptyMessage(1);
        }
    }

    public void d() {
        this.f = System.currentTimeMillis();
        e.b().a((e.a) new e.a() {
            public void execute() {
                if (b.e() && !b.d()) {
                    for (b.a a2 : b.b()) {
                        b.a(a2, false);
                    }
                    b.c();
                }
            }
        });
    }

    public long e() {
        return this.d;
    }

    public int f() {
        return this.c;
    }

    public void g() {
        long currentTimeMillis = System.currentTimeMillis() - this.f;
        long j2 = this.i;
        if (currentTimeMillis >= j2) {
            q();
            p();
            return;
        }
        b(j2 - currentTimeMillis);
    }

    public void h() {
        if (this.j.hasMessages(4)) {
            j.a("UPE", "cancel the delayed uploading BASIC event.");
            this.j.removeMessages(4);
            return;
        }
        r();
    }
}
