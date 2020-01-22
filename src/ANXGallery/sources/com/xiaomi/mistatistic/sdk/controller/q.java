package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.data.k;

/* compiled from: SessionManagerV2 */
public class q {
    private static volatile q a;
    private static boolean b;
    /* access modifiers changed from: private */
    public Handler c = new Handler(e.c()) {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                m.b(d.a(), "action_auto_end", System.currentTimeMillis());
                q.this.c.sendEmptyMessageDelayed(100, 15000);
            }
        }
    };

    private q() {
    }

    public static q a() {
        if (a == null) {
            synchronized (q.class) {
                if (a == null) {
                    a = new q();
                }
            }
        }
        return a;
    }

    private void a(String str, long j, long j2, boolean z) {
        String c2 = l.c(d.a());
        if (TextUtils.isEmpty(c2)) {
            c2 = "NULL";
        }
        LocalEventRecorder.insertEvent(new k(str, j, j2, z, c2));
    }

    private void b() {
        Context a2 = d.a();
        m.b(a2, "action_begin", 0);
        m.b(a2, "action_end", 0);
        m.b(a2, "action_auto_end", 0);
        m.b(a2, "action_name", "");
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        String str2;
        long j;
        String str3;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Context a2 = d.a();
            long a3 = m.a(a2, "action_begin", 0);
            long a4 = m.a(a2, "action_end", 0);
            long a5 = m.a(a2, "action_auto_end", 0);
            String a6 = m.a(a2, "action_name", "");
            if (TextUtils.isEmpty(a6) || a3 <= 0 || a4 != 0) {
                str2 = "action_name";
                j = a3;
                str3 = "SMV2";
            } else {
                j.a("SMV2", "Record the last unusual PA event with auto-end.");
                long j2 = a3;
                j = a3;
                long j3 = a5;
                str2 = "action_name";
                str3 = "SMV2";
                a(a6, j2, j3, true);
            }
            if (!b) {
                b = true;
                if (!TextUtils.isEmpty(a6) && j > 0 && a4 > 0) {
                    boolean j4 = new h().j(j);
                    j.a(str3, "check the last pa event whether been inserted to db. actionBeginTs =" + j + " isInserted = " + j4);
                    if (!j4) {
                        j.a(str3, "Record the last unusual PA event without auto-end.");
                        a(a6, j, a4, false);
                    }
                }
            }
            m.b(a2, "action_begin", currentTimeMillis);
            m.b(a2, "action_end", 0);
            m.b(a2, "action_auto_end", 0);
            m.b(a2, str2, str);
            try {
                this.c.sendEmptyMessageDelayed(100, 15000);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            j.a("processActActivated exception: ", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public void d(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Context a2 = d.a();
            long a3 = m.a(a2, "action_begin", 0);
            String a4 = m.a(a2, "action_name", "");
            this.c.removeMessages(100);
            if (!str.equals(a4)) {
                j.d("SMV2", "The actName of recordPageEnd is NOT equal to actName of recordPageStart.");
                b();
                return;
            }
            m.b(a2, "action_end", currentTimeMillis);
            if (!TextUtils.isEmpty(a4) && a3 > 0) {
                a(a4, a3, currentTimeMillis, false);
            }
        } catch (Exception e) {
            j.a("processActDeactivated exception: ", (Throwable) e);
        }
    }

    public void a(final String str) {
        e.a().a((e.a) new e.a() {
            public void execute() {
                if (!f.a(d.a()).a()) {
                    j.c("PA is disabled.");
                } else {
                    q.this.c(str);
                }
            }
        });
    }

    public void b(final String str) {
        e.a().a((e.a) new e.a() {
            public void execute() {
                if (!f.a(d.a()).a()) {
                    j.c("PA is disabled.");
                } else {
                    q.this.d(str);
                }
            }
        });
    }
}
