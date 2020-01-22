package com.miui.gallery.cloud.thread;

import android.accounts.Account;
import android.content.Context;
import com.google.common.collect.Lists;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.RequestCloudItem;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.cloud.thread.RequestCommandQueue;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseTask<T> implements Runnable {
    protected final String TAG;
    private volatile boolean isRunning = false;
    private final RequestCommandQueue mCommandQueue;
    private AtomicBoolean mHasInterruptted = new AtomicBoolean(false);

    public BaseTask(int i, int i2, int i3, int i4, RequestCommandQueue.OnItemChangedListener onItemChangedListener) {
        this.TAG = String.format(Locale.US, "%s#%s", new Object[]{getClass().getSimpleName(), Integer.valueOf(i)});
        this.mCommandQueue = new RequestCommandQueue(i2, i3, i4, onItemChangedListener, this.TAG);
    }

    public void cancelAll(int i, boolean z) {
        this.mCommandQueue.cancelAll(i, z);
    }

    public int getPendingSize() {
        return this.mCommandQueue.getPengdingSize();
    }

    /* access modifiers changed from: protected */
    public abstract GallerySyncResult<T> handle(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, List<RequestCloudItem> list) throws Exception;

    public boolean hasDelayedItem() {
        return this.mCommandQueue.hasDelayedItem();
    }

    public int[] invoke(List<RequestCloudItem> list, boolean z, boolean z2) {
        int i;
        int i2;
        Account account = AccountCache.getAccount();
        if (account == null) {
            Log.e(this.TAG, "invoke: no account");
            return new int[]{0, 0};
        }
        if (!list.isEmpty()) {
            ArrayList newArrayList = Lists.newArrayList();
            for (RequestCloudItem requestCommand : list) {
                newArrayList.add(new RequestCommand(account, requestCommand));
            }
            i2 = this.mCommandQueue.put(newArrayList, z2);
            if (z) {
                List<RequestCommand> interruptIfNotExecuting = this.mCommandQueue.interruptIfNotExecuting(newArrayList);
                i = interruptIfNotExecuting != null ? interruptIfNotExecuting.size() : 0;
                this.mHasInterruptted.compareAndSet(false, i > 0);
            } else {
                i = 0;
            }
        } else {
            i2 = 0;
            i = 0;
        }
        return new int[]{i2, i};
    }

    /* access modifiers changed from: protected */
    public boolean needContinue(GallerySyncResult<T> gallerySyncResult) {
        if (Thread.currentThread().isInterrupted()) {
            return false;
        }
        if (gallerySyncResult.code == GallerySyncCode.CONDITION_INTERRUPTED || gallerySyncResult.code == GallerySyncCode.NOT_CONTINUE_ERROR || gallerySyncResult.code == GallerySyncCode.CANCEL) {
            this.mCommandQueue.cancelAll(true);
            return false;
        } else if (this.mCommandQueue.getPengdingSize() <= 0) {
            return this.mHasInterruptted.compareAndSet(true, false) && resumeInterruptted() > 0;
        } else {
            return true;
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    protected com.miui.gallery.cloud.base.GallerySyncResult<T> onPerformSync() throws java.lang.Throwable {
        /*
            r9 = this;
            java.util.ArrayList r0 = com.google.common.collect.Lists.newArrayList()
            java.util.ArrayList r1 = com.google.common.collect.Lists.newArrayList()
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r2 = new com.miui.gallery.cloud.base.GallerySyncResult$Builder
            r2.<init>()
            com.miui.gallery.cloud.thread.RequestCommandQueue r3 = r9.mCommandQueue     // Catch:{ InterruptedException -> 0x01ea }
            long r3 = r3.pollToExecute(r0)     // Catch:{ InterruptedException -> 0x01ea }
            boolean r5 = r0.isEmpty()     // Catch:{ InterruptedException -> 0x01ea }
            if (r5 == 0) goto L_0x00a2
            r5 = 0
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0056
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x01ea }
            monitor-enter(r5)     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.String r6 = r9.TAG     // Catch:{ InterruptedException -> 0x004b }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x004b }
            r7.<init>()     // Catch:{ InterruptedException -> 0x004b }
            java.lang.String r8 = "wait for "
            r7.append(r8)     // Catch:{ InterruptedException -> 0x004b }
            r7.append(r3)     // Catch:{ InterruptedException -> 0x004b }
            java.lang.String r7 = r7.toString()     // Catch:{ InterruptedException -> 0x004b }
            com.miui.gallery.util.Log.i(r6, r7)     // Catch:{ InterruptedException -> 0x004b }
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x004b }
            r6.wait(r3)     // Catch:{ InterruptedException -> 0x004b }
            java.lang.String r3 = r9.TAG     // Catch:{ InterruptedException -> 0x004b }
            java.lang.String r4 = "wait time out or notified"
            com.miui.gallery.util.Log.i(r3, r4)     // Catch:{ InterruptedException -> 0x004b }
            goto L_0x0052
        L_0x0049:
            r3 = move-exception
            goto L_0x0054
        L_0x004b:
            java.lang.String r3 = r9.TAG     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "resume from waiting by interuptted"
            com.miui.gallery.util.Log.i(r3, r4)     // Catch:{ all -> 0x0049 }
        L_0x0052:
            monitor-exit(r5)     // Catch:{ all -> 0x0049 }
            goto L_0x0056
        L_0x0054:
            monitor-exit(r5)     // Catch:{ all -> 0x0049 }
            throw r3     // Catch:{ InterruptedException -> 0x01ea }
        L_0x0056:
            com.miui.gallery.cloud.base.GallerySyncCode r3 = com.miui.gallery.cloud.base.GallerySyncCode.OK     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r3 = r2.setCode(r3)     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GallerySyncResult r2 = r3.build()     // Catch:{ InterruptedException -> 0x01ea }
            java.util.Iterator r1 = r1.iterator()
        L_0x0064:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x007a
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x0064
        L_0x007a:
            java.util.Iterator r0 = r0.iterator()
        L_0x007e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00a1
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x007e
        L_0x00a1:
            return r2
        L_0x00a2:
            java.util.Iterator r3 = r0.iterator()     // Catch:{ InterruptedException -> 0x01ea }
        L_0x00a6:
            boolean r4 = r3.hasNext()     // Catch:{ InterruptedException -> 0x01ea }
            if (r4 == 0) goto L_0x00b8
            java.lang.Object r4 = r3.next()     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.thread.RequestCommand r4 = (com.miui.gallery.cloud.thread.RequestCommand) r4     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.RequestCloudItem r4 = r4.mRequestItem     // Catch:{ InterruptedException -> 0x01ea }
            r1.add(r4)     // Catch:{ InterruptedException -> 0x01ea }
            goto L_0x00a6
        L_0x00b8:
            r3 = 0
            java.lang.Object r3 = r0.get(r3)     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.thread.RequestCommand r3 = (com.miui.gallery.cloud.thread.RequestCommand) r3     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.RequestCloudItem r4 = r3.mRequestItem     // Catch:{ InterruptedException -> 0x01ea }
            int r4 = com.miui.gallery.cloud.SyncConditionManager.checkForItem(r4)     // Catch:{ InterruptedException -> 0x01ea }
            r5 = 2
            if (r4 != r5) goto L_0x0114
            com.miui.gallery.cloud.base.GallerySyncCode r3 = com.miui.gallery.cloud.base.GallerySyncCode.CONDITION_INTERRUPTED     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r3 = r2.setCode(r3)     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GallerySyncResult r2 = r3.build()     // Catch:{ InterruptedException -> 0x01ea }
            java.util.Iterator r1 = r1.iterator()
        L_0x00d6:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00ec
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x00d6
        L_0x00ec:
            java.util.Iterator r0 = r0.iterator()
        L_0x00f0:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0113
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x00f0
        L_0x0113:
            return r2
        L_0x0114:
            android.accounts.Account r3 = r3.mAccount     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.AccountCache$AccountInfo r4 = com.miui.gallery.cloud.AccountCache.getAccountInfo()     // Catch:{ InterruptedException -> 0x01ea }
            if (r4 == 0) goto L_0x0172
            android.accounts.Account r5 = r4.mAccount     // Catch:{ InterruptedException -> 0x01ea }
            boolean r5 = r5.equals(r3)     // Catch:{ InterruptedException -> 0x01ea }
            if (r5 == 0) goto L_0x0172
            android.content.Context r3 = com.miui.gallery.GalleryApp.sGetAndroidContext()     // Catch:{ InterruptedException -> 0x01ea }
            android.accounts.Account r5 = r4.mAccount     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GalleryExtendedAuthToken r4 = r4.mToken     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.cloud.base.GallerySyncResult r2 = r9.handle(r3, r5, r4, r1)     // Catch:{ InterruptedException -> 0x01ea }
            java.util.Iterator r1 = r1.iterator()
        L_0x0134:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x014a
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x0134
        L_0x014a:
            java.util.Iterator r0 = r0.iterator()
        L_0x014e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0171
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x014e
        L_0x0171:
            return r2
        L_0x0172:
            if (r4 == 0) goto L_0x0195
            java.lang.String r5 = r9.TAG     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x01ea }
            r6.<init>()     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.String r7 = "execute: account is changed. old="
            r6.append(r7)     // Catch:{ InterruptedException -> 0x01ea }
            r6.append(r3)     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.String r3 = ", current="
            r6.append(r3)     // Catch:{ InterruptedException -> 0x01ea }
            android.accounts.Account r3 = r4.mAccount     // Catch:{ InterruptedException -> 0x01ea }
            r6.append(r3)     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.String r3 = r6.toString()     // Catch:{ InterruptedException -> 0x01ea }
            com.miui.gallery.util.Log.e((java.lang.String) r5, (java.lang.String) r3)     // Catch:{ InterruptedException -> 0x01ea }
            goto L_0x019c
        L_0x0195:
            java.lang.String r3 = r9.TAG     // Catch:{ InterruptedException -> 0x01ea }
            java.lang.String r4 = "execute: account is null"
            com.miui.gallery.util.Log.e((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ InterruptedException -> 0x01ea }
        L_0x019c:
            java.util.Iterator r1 = r1.iterator()
        L_0x01a0:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x01b6
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x01a0
        L_0x01b6:
            java.util.Iterator r0 = r0.iterator()
        L_0x01ba:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01dd
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x01ba
        L_0x01dd:
            com.miui.gallery.cloud.base.GallerySyncCode r0 = com.miui.gallery.cloud.base.GallerySyncCode.UNKNOWN
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r0 = r2.setCode(r0)
            com.miui.gallery.cloud.base.GallerySyncResult r0 = r0.build()
            return r0
        L_0x01e8:
            r2 = move-exception
            goto L_0x0242
        L_0x01ea:
            r3 = move-exception
            java.lang.String r4 = r9.TAG     // Catch:{ all -> 0x01e8 }
            java.lang.String r5 = "interruptted"
            com.miui.gallery.util.Log.d((java.lang.String) r4, (java.lang.String) r5, (java.lang.Object) r3)     // Catch:{ all -> 0x01e8 }
            com.miui.gallery.cloud.base.GallerySyncCode r4 = com.miui.gallery.cloud.base.GallerySyncCode.CANCEL     // Catch:{ all -> 0x01e8 }
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r2 = r2.setCode(r4)     // Catch:{ all -> 0x01e8 }
            com.miui.gallery.cloud.base.GallerySyncResult$Builder r2 = r2.setException(r3)     // Catch:{ all -> 0x01e8 }
            com.miui.gallery.cloud.base.GallerySyncResult r2 = r2.build()     // Catch:{ all -> 0x01e8 }
            java.util.Iterator r1 = r1.iterator()
        L_0x0204:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x021a
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x0204
        L_0x021a:
            java.util.Iterator r0 = r0.iterator()
        L_0x021e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0241
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x021e
        L_0x0241:
            return r2
        L_0x0242:
            java.util.Iterator r1 = r1.iterator()
        L_0x0246:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x025c
            java.lang.Object r3 = r1.next()
            com.miui.gallery.cloud.RequestCloudItem r3 = (com.miui.gallery.cloud.RequestCloudItem) r3
            com.miui.gallery.cloud.thread.RequestCommandQueue r4 = r9.mCommandQueue
            java.lang.String r3 = com.miui.gallery.cloud.thread.RequestCommand.getKey(r3)
            r4.removeFromExecuting(r3)
            goto L_0x0246
        L_0x025c:
            java.util.Iterator r0 = r0.iterator()
        L_0x0260:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0283
            java.lang.Object r1 = r0.next()
            com.miui.gallery.cloud.thread.RequestCommand r1 = (com.miui.gallery.cloud.thread.RequestCommand) r1
            java.lang.String r3 = r9.TAG
            java.lang.String r4 = r1.getKey()
            long r5 = android.os.SystemClock.uptimeMillis()
            long r7 = r1.mInvokeTime
            long r5 = r5 - r7
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = "execute: %s, invoke~finish cost=%s"
            com.miui.gallery.util.Log.d(r3, r5, r4, r1)
            goto L_0x0260
        L_0x0283:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.cloud.thread.BaseTask.onPerformSync():com.miui.gallery.cloud.base.GallerySyncResult");
    }

    /* access modifiers changed from: protected */
    public abstract void onPostExecute();

    /* access modifiers changed from: protected */
    public abstract void onPreExecute();

    /* access modifiers changed from: protected */
    public int resumeInterruptted() {
        return 0;
    }

    public void run() {
        GallerySyncResult gallerySyncResult;
        Log.i(this.TAG, "thread start %s", (Object) Integer.valueOf(System.identityHashCode(Thread.currentThread())));
        this.isRunning = true;
        onPreExecute();
        do {
            try {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                gallerySyncResult = null;
                gallerySyncResult = onPerformSync();
                if (gallerySyncResult == null) {
                    break;
                }
            } catch (Throwable th) {
                onPostExecute();
                Log.i(this.TAG, "thread end %s", (Object) Integer.valueOf(System.identityHashCode(Thread.currentThread())));
                this.isRunning = false;
                throw th;
            }
        } while (!needContinue(gallerySyncResult));
        onPostExecute();
        Log.i(this.TAG, "thread end %s", (Object) Integer.valueOf(System.identityHashCode(Thread.currentThread())));
        this.isRunning = false;
    }
}
