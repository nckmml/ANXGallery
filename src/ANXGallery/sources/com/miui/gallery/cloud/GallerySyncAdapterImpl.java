package com.miui.gallery.cloud;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.net.ParseException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.cloud.AsyncUpDownloadService;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.ServerErrorCode;
import com.miui.gallery.cloud.adapter.PullCardAdapter;
import com.miui.gallery.cloud.adapter.PullFaceDataAdapter;
import com.miui.gallery.cloud.adapter.PullOwnerDataAdapter;
import com.miui.gallery.cloud.adapter.PullSecretDataAdapter;
import com.miui.gallery.cloud.adapter.PullShareAdapter;
import com.miui.gallery.cloud.adapter.PushBabyInfoAdapter;
import com.miui.gallery.cloud.adapter.PushCardAdapter;
import com.miui.gallery.cloud.adapter.PushFaceDataAdapter;
import com.miui.gallery.cloud.adapter.PushOwnerDataAdapter;
import com.miui.gallery.cloud.adapter.PushShareDataAdapter;
import com.miui.gallery.cloud.base.AbstractSyncAdapter;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.control.BatteryMonitor;
import com.miui.gallery.cloud.control.ServerTagCache;
import com.miui.gallery.cloud.download.BatchDownloadManager;
import com.miui.gallery.cloud.syncstate.SyncMonitor;
import com.miui.gallery.cloud.syncstate.SyncStateUtil;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.sdk.download.util.DownloadUtil;
import com.miui.gallery.settingssync.GallerySettingsSyncHelper;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deprecated.Preference;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class GallerySyncAdapterImpl {
    private static List<Pair<Long, Class<? extends AbstractSyncAdapter>>> sSyncAdapters = new LinkedList();
    Context mContext;
    private AsyncUpDownloadService.SyncLock mSyncLock;

    static {
        sSyncAdapters.add(new Pair(1L, PullOwnerDataAdapter.class));
        sSyncAdapters.add(new Pair(2L, PullSecretDataAdapter.class));
        sSyncAdapters.add(new Pair(4L, PullCardAdapter.class));
        sSyncAdapters.add(new Pair(8L, PullFaceDataAdapter.class));
        sSyncAdapters.add(new Pair(16L, PullShareAdapter.class));
        sSyncAdapters.add(new Pair(32L, PushOwnerDataAdapter.class));
        sSyncAdapters.add(new Pair(64L, PushCardAdapter.class));
        sSyncAdapters.add(new Pair(128L, PushFaceDataAdapter.class));
        sSyncAdapters.add(new Pair(256L, PushBabyInfoAdapter.class));
        sSyncAdapters.add(new Pair(512L, PushShareDataAdapter.class));
    }

    public GallerySyncAdapterImpl(Context context) {
        this.mContext = context;
    }

    private void acquireLock() {
        synchronized (this) {
            if (this.mSyncLock == null) {
                this.mSyncLock = AsyncUpDownloadService.newSyncLock("GallerySyncAdapterImpl");
            }
            this.mSyncLock.acquire();
        }
    }

    private static <T extends AbstractSyncAdapter> T create(Context context, Class<T> cls) {
        try {
            return (AbstractSyncAdapter) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private void doPostFirstSyncJob() {
        long currentTimeMillis = System.currentTimeMillis();
        if (GalleryPreferences.CloudControl.getLastRequestSucceedTime() <= 0) {
            GallerySettingsSyncHelper.doSync(this.mContext);
            SyncLog.d("GallerySyncAdapterImpl", "Request cloud control after first sync, result %s", (Object) String.valueOf(new CloudControlRequestHelper(this.mContext).execRequestSync(true)));
        }
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("com.miui.gallery.action.FIRST_SYNC_FINISHED"));
        if (BatchDownloadManager.canAutoDownload()) {
            BatchDownloadManager.getInstance().startBatchDownload(this.mContext, true);
        }
        statFirstSync(currentTimeMillis);
    }

    private <T> GallerySyncResult<T> executeAdapter(AbstractSyncAdapter<T> abstractSyncAdapter, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, Bundle bundle) {
        abstractSyncAdapter.isAsynchronous();
        SyncMonitor.getInstance().onHandleReason(abstractSyncAdapter.getBindingReason());
        return abstractSyncAdapter.startSync(account, bundle, galleryExtendedAuthToken);
    }

    private boolean fetchSyncExtraInfoFromV2ToV3(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        if (!Preference.getSyncFetchSyncExtraInfoFromV2ToV3()) {
            return true;
        }
        new FetchSyncExtraInfoTask(this.mContext, account, galleryExtendedAuthToken).run();
        if (!Preference.getSyncFetchSyncExtraInfoFromV2ToV3()) {
            return true;
        }
        SyncLog.e("GallerySyncAdapterImpl", "fail to fetch syncExtraInfo when upgrade from v2 to v3");
        return false;
    }

    private void handlePush(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, Bundle bundle) {
        Bundle parsePushParams = parsePushParams(this.mContext, account, bundle);
        int i = parsePushParams.getInt("sync_tag_type");
        SyncLog.d("GallerySyncAdapterImpl", "request caused by push: type[%s], data[%s].", Integer.valueOf(i), parsePushParams.getString("sync_tag_data"));
        if (parsePushParams.getBoolean("sync_data_exist", false)) {
            SyncLog.w("GallerySyncAdapterImpl", "push data[%s] exist, ignore!");
            return;
        }
        long parsePushReason = parsePushReason(parsePushParams);
        SyncLog.d("GallerySyncAdapterImpl", "request by push, sync reason[%s]", (Object) Long.toBinaryString(parsePushReason));
        for (AbstractSyncAdapter executeAdapter : maskAdapters(this.mContext, parsePushReason)) {
            executeAdapter(executeAdapter, account, galleryExtendedAuthToken, parsePushParams);
        }
    }

    private void handleRequest(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, Bundle bundle) {
        if (!Preference.sIsFirstSynced()) {
            GalleryPreferences.Sync.setFirstSyncStartTime(System.currentTimeMillis());
        }
        SyncType parseSyncType = parseSyncType(bundle);
        long parseSyncReason = parseSyncReason(bundle);
        SyncLog.d("GallerySyncAdapterImpl", "request caused by local: syncType[%s], reason[%s].", parseSyncType, Long.toBinaryString(parseSyncReason));
        GalleryPreferences.Sync.setLastSyncTimestamp(System.currentTimeMillis());
        statSyncDispatchInterval(parseSyncType);
        GallerySettingsSyncHelper.doSync(this.mContext);
        List<AbstractSyncAdapter> maskAdapters = maskAdapters(this.mContext, parseSyncReason);
        Boolean bool = null;
        for (AbstractSyncAdapter next : maskAdapters) {
            GallerySyncResult executeAdapter = executeAdapter(next, account, galleryExtendedAuthToken, bundle);
            if (next instanceof PullOwnerDataAdapter) {
                bool = Boolean.valueOf(executeAdapter != null && executeAdapter.code == GallerySyncCode.OK);
            }
        }
        if (bool != null) {
            onPullOwnerEnd(bool.booleanValue());
        }
    }

    private static boolean isPush(Bundle bundle) {
        return !TextUtils.isEmpty(bundle.getString("pushName"));
    }

    private static List<AbstractSyncAdapter> maskAdapters(Context context, long j) {
        LinkedList linkedList = new LinkedList();
        for (Pair next : sSyncAdapters) {
            if ((((Long) next.first).longValue() & j) == ((Long) next.first).longValue()) {
                AbstractSyncAdapter create = create(context, (Class) next.second);
                if (create != null) {
                    linkedList.add(create);
                } else {
                    SyncLog.e("GallerySyncAdapterImpl", "create instance error for %s", ((Class) next.second).getSimpleName());
                }
            }
        }
        return linkedList;
    }

    private void onPullOwnerEnd(boolean z) {
        if (z) {
            GalleryPreferences.Sync.setSyncCompletelyFinish(true);
            if (!Preference.sIsFirstSynced()) {
                Preference.sSetFirstSynced();
                doPostFirstSyncJob();
                return;
            }
            return;
        }
        GalleryPreferences.Sync.setSyncCompletelyFinish(false);
    }

    private static Bundle parsePushParams(Context context, Account account, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle.getString("pushType");
        String string = bundle.getString("pushName");
        String string2 = bundle.getString("pushData");
        for (Map.Entry next : GalleryCloudSyncTagUtils.sSyncTagConstantsMap.entrySet()) {
            if (TextUtils.equals(string, ((GalleryCloudSyncTagUtils.SyncTagConstant) next.getValue()).pushName)) {
                bundle2.putInt("sync_tag_type", ((Integer) next.getKey()).intValue());
                bundle2.putString("sync_tag_data", string2);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new GalleryCloudSyncTagUtils.SyncTagItem(((Integer) next.getKey()).intValue()));
                bundle2.putBoolean("sync_data_exist", TextUtils.equals(Long.toString(GalleryCloudSyncTagUtils.getSyncTag(context, account, arrayList).get(0).currentValue), string2));
            }
        }
        return bundle2;
    }

    private static long parsePushReason(Bundle bundle) {
        int i = bundle.getInt("sync_tag_type");
        String string = bundle.getString("sync_tag_data");
        SyncLog.d("GallerySyncAdapterImpl", "request caused by push: type[%s], data[%s].", Integer.valueOf(i), string);
        if (bundle.getBoolean("sync_data_exist", false)) {
            SyncLog.w("GallerySyncAdapterImpl", "push data[%s] exist, ignore!");
        } else if (i != 1) {
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                return 16;
            }
            if (i == 11) {
                return 8;
            }
            SyncLog.e("GallerySyncAdapterImpl", "unknown push %s", bundle);
        } else if (!ServerTagCache.getInstance().contains(string)) {
            return 1;
        } else {
            SyncLog.d("GallerySyncAdapterImpl", "operation server tag, ignore push: %s", (Object) string);
        }
        return 0;
    }

    private long parseSyncReason(Bundle bundle) {
        return isPush(bundle) ? parsePushReason(parsePushParams(this.mContext, AccountCache.getAccount(), bundle)) : bundle.getLong("sync_reason", Long.MAX_VALUE);
    }

    private SyncType parseSyncType(Bundle bundle) {
        return isPush(bundle) ? SyncType.NORMAL : SyncUtil.unpackSyncType(bundle);
    }

    private void recordSyncError(Throwable th) {
        if (th != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("error", Log.getStackTraceString(th));
            GallerySamplingStatHelper.recordErrorEvent("Sync", "sync_error_message", hashMap);
            if (!Preference.sIsFirstSynced()) {
                GalleryPreferences.Sync.increaseFirstSyncFailCount();
            }
        }
    }

    private void releaseLock() {
        synchronized (this) {
            if (this.mSyncLock != null) {
                this.mSyncLock.release();
                this.mSyncLock = null;
            }
        }
    }

    public static void resetAccountInfo(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        AccountCache.update(account, galleryExtendedAuthToken);
        SyncLog.d("GallerySyncAdapterImpl", "reset AccountCache");
        GalleryKssManager.reset();
        SyncLog.d("GallerySyncAdapterImpl", "reset GalleryKssManager");
    }

    private void statFirstSync(long j) {
        long firstSyncStartTime = GalleryPreferences.Sync.getFirstSyncStartTime();
        if (firstSyncStartTime == 0 || firstSyncStartTime > j) {
            SyncLog.w("GallerySyncAdapterImpl", "invalid first sync time, start: %d, finish: %d", Long.valueOf(firstSyncStartTime), Long.valueOf(j));
            return;
        }
        int[] syncedCount = SyncStateUtil.getSyncedCount(this.mContext);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(j - firstSyncStartTime);
        HashMap hashMap = new HashMap();
        hashMap.put("image_count", String.valueOf(syncedCount[0]));
        hashMap.put("video_count", String.valueOf(syncedCount[1]));
        hashMap.put("time_in_minutes", String.valueOf(minutes));
        hashMap.put("fail_count", String.valueOf(GalleryPreferences.Sync.getFirstSyncFailCount()));
        GallerySamplingStatHelper.recordCountEvent("Sync", "first_sync_duration", hashMap);
        GalleryPreferences.Sync.clearFirstSyncFailCount();
        SyncLog.d("GallerySyncAdapterImpl", "first sync finished: %s", (Object) hashMap.toString());
    }

    private void statSyncDispatchInterval(SyncType syncType) {
        long requestStartTime = GalleryPreferences.Sync.getRequestStartTime(syncType);
        if (requestStartTime > 0) {
            long currentTimeMillis = System.currentTimeMillis() - requestStartTime;
            HashMap hashMap = new HashMap();
            hashMap.put("syncType", syncType.name());
            hashMap.put("interval", String.valueOf(currentTimeMillis));
            GallerySamplingStatHelper.recordCountEvent("Sync", "sync_dispatch_interval", hashMap);
            GalleryPreferences.Sync.clearRequestStartTime();
            SyncLog.d("GallerySyncAdapterImpl", "sync for %s dispatch cost %d", syncType, Long.valueOf(currentTimeMillis));
        }
    }

    /* access modifiers changed from: protected */
    public void onPerformMiCloudSync(Bundle bundle, Account account, SyncResult syncResult, GalleryExtendedAuthToken galleryExtendedAuthToken) throws GalleryMiCloudServerException {
        acquireLock();
        BatteryMonitor.getInstance().start();
        SyncMonitor.getInstance().onSyncStart(parseSyncType(bundle), parseSyncReason(bundle));
        long currentTimeMillis = System.currentTimeMillis();
        if (account != null) {
            try {
                if (!TextUtils.isEmpty(account.name)) {
                    if (!TextUtils.isEmpty(account.type)) {
                        if (!CloudUtils.checkAccount((Activity) null, true, (Runnable) null)) {
                            SyncLog.e("GallerySyncAdapterImpl", "check account failed, return.");
                        } else {
                            resetAccountInfo(account, galleryExtendedAuthToken);
                            GalleryCloudSyncTagUtils.insertAccountToDB(this.mContext, account);
                            ServerErrorCode.MiCloudServerExceptionHandler.checkMiCloudServerException();
                            SyncConditionManager.setCancelledForAllBackground(false);
                            if (!fetchSyncExtraInfoFromV2ToV3(account, galleryExtendedAuthToken)) {
                                SyncLog.e("GallerySyncAdapterImpl", "fetchSyncExtraInfoFromV2ToV3 Exit");
                            } else {
                                if (ClearDataManager.getInstance().clearDataBaseIfNeeded(this.mContext, account)) {
                                    SyncLog.w("GallerySyncAdapterImpl", "clear data before syncing");
                                }
                                if (isPush(bundle)) {
                                    handlePush(account, galleryExtendedAuthToken, bundle);
                                } else {
                                    handleRequest(account, galleryExtendedAuthToken, bundle);
                                }
                                DownloadUtil.resumeInterrupted();
                                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                releaseLock();
                                BatteryMonitor.getInstance().end();
                                SyncMonitor.getInstance().onSyncEnd();
                                GalleryDBHelper.getInstance(this.mContext).analyze();
                                DeleteAccount.deleteAccountAfterSyncIfNeeded();
                            }
                        }
                        SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        releaseLock();
                        BatteryMonitor.getInstance().end();
                        SyncMonitor.getInstance().onSyncEnd();
                        GalleryDBHelper.getInstance(this.mContext).analyze();
                        DeleteAccount.deleteAccountAfterSyncIfNeeded();
                        return;
                    }
                }
            } catch (JSONException e) {
                e = e;
                syncResult.stats.numParseExceptions++;
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
            } catch (URISyntaxException e2) {
                e = e2;
                syncResult.stats.numParseExceptions++;
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
            } catch (IOException e3) {
                e = e3;
                syncResult.stats.numIoExceptions++;
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
            } catch (ParseException e4) {
                e = e4;
                syncResult.stats.numParseExceptions++;
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
            } catch (IllegalArgumentException e5) {
                e = e5;
                syncResult.stats.numParseExceptions++;
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
            } catch (Exception e6) {
                e = e6;
                if (!(e instanceof GalleryMiCloudServerException)) {
                    SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    releaseLock();
                    BatteryMonitor.getInstance().end();
                    SyncMonitor.getInstance().onSyncEnd();
                } else {
                    Exception cloudServerException = ((GalleryMiCloudServerException) e).getCloudServerException();
                    throw ((GalleryMiCloudServerException) e);
                }
            } catch (Throwable th) {
                SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                releaseLock();
                BatteryMonitor.getInstance().end();
                SyncMonitor.getInstance().onSyncEnd();
                if (0 != 0) {
                    SyncLog.e("GallerySyncAdapterImpl", (Throwable) null);
                    SyncMonitor.getInstance().onSyncThrowable((Throwable) null);
                    recordSyncError((Throwable) null);
                }
                GalleryDBHelper.getInstance(this.mContext).analyze();
                DeleteAccount.deleteAccountAfterSyncIfNeeded();
                throw th;
            }
        }
        String str = "";
        String str2 = account == null ? str : account.name;
        if (account != null) {
            str = account.type;
        }
        SyncLog.e("GallerySyncAdapterImpl", "account: %s, name: %s, type %s, have none value, return.", account, str2, str);
        SyncLog.d("GallerySyncAdapterImpl", "perform sync finished, cost: %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        releaseLock();
        BatteryMonitor.getInstance().end();
        SyncMonitor.getInstance().onSyncEnd();
        GalleryDBHelper.getInstance(this.mContext).analyze();
        DeleteAccount.deleteAccountAfterSyncIfNeeded();
        return;
        SyncLog.e("GallerySyncAdapterImpl", e);
        SyncMonitor.getInstance().onSyncThrowable(e);
        recordSyncError(e);
        GalleryDBHelper.getInstance(this.mContext).analyze();
        DeleteAccount.deleteAccountAfterSyncIfNeeded();
    }

    public void onSyncCanceled() {
        SyncConditionManager.setCancelledForAllBackground(true);
        UpDownloadManager.cancelAllBackgroundPriority(true, true);
    }
}
