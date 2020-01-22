package com.miui.gallery.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.activity.BackupDetailActivity;
import com.miui.gallery.activity.CloudSpaceStatusActivity;
import com.miui.gallery.cloud.download.BatchDownloadManager;
import com.miui.gallery.cloud.syncstate.OnSyncStateChangeObserver;
import com.miui.gallery.cloud.syncstate.SyncStateInfo;
import com.miui.gallery.cloud.syncstate.SyncStateManager;
import com.miui.gallery.cloud.syncstate.SyncStatus;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.error.BaseErrorCodeTranslator;
import com.miui.gallery.error.core.ErrorActionCallback;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.error.core.ErrorCodeTranslator;
import com.miui.gallery.error.core.ErrorTip;
import com.miui.gallery.error.core.ErrorTranslateCallback;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.ui.AlertDialogFragment;
import com.miui.gallery.ui.HomePageTopBarController;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.OnAppFocusedListener;
import com.miui.gallery.util.deprecated.Preference;
import com.miui.gallery.widget.PanelItemLayout;
import com.miui.gallery.widget.PanelManager;
import com.miui.os.Rom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class SyncBar extends HomePageTopBarController.HomePageBar implements OnAppFocusedListener {
    private static LinkedList<SyncStatus> sDownloadOverlayStatus = new LinkedList<>();
    private TextView mDesc;
    private DownloadManager mDownloadManager = new DownloadManager(this, 1);
    private PanelData mDownloadPanelData;
    private PanelItemLayout mPanelItem;
    private SyncManager mSyncManager = new SyncManager(this, 0);
    private PanelData mSyncPanelData;

    private static abstract class ChildManager {
        private final SyncBar mBar;
        private final int mKey;

        public ChildManager(SyncBar syncBar, int i) {
            this.mBar = syncBar;
            this.mKey = i;
        }

        public Context getContext() {
            return this.mBar.getContext();
        }

        /* access modifiers changed from: protected */
        public final int getKey() {
            return this.mKey;
        }

        public boolean isPermanent() {
            return this.mBar.isPermanent();
        }

        public boolean refresh(PanelData panelData, boolean z) {
            return this.mBar.refresh(panelData, z, getKey());
        }
    }

    private static final class DownloadManager extends ChildManager implements View.OnClickListener, BatchDownloadManager.OnBatchDownloadListener {
        private static LinkedList<ErrorCode> sNeedShowError = new LinkedList<>();
        /* access modifiers changed from: private */
        public ErrorCode mCurError = ErrorCode.NO_ERROR;
        /* access modifiers changed from: private */
        public int mDownloadState = 0;
        private ErrorHandler mErrorHandler;

        private static class ErrorHandler {
            private Context mContext;
            private ErrorCodeTranslator mErrorCodeTranslator = new BaseErrorCodeTranslator();
            /* access modifiers changed from: private */
            public ErrorTip mErrorTip;

            ErrorHandler(Context context) {
                this.mContext = context;
            }

            public void clearError() {
                this.mErrorTip = null;
            }

            public ErrorTip getErrorTip() {
                return this.mErrorTip;
            }

            public void handleError(ErrorCode errorCode, final ErrorTranslateCallback errorTranslateCallback) {
                this.mErrorCodeTranslator.translate(this.mContext, errorCode, new ErrorTranslateCallback() {
                    public void onTranslate(ErrorTip errorTip) {
                        ErrorTip unused = ErrorHandler.this.mErrorTip = errorTip;
                        ErrorTranslateCallback errorTranslateCallback = errorTranslateCallback;
                        if (errorTranslateCallback != null) {
                            errorTranslateCallback.onTranslate(errorTip);
                        }
                    }
                });
            }
        }

        static {
            sNeedShowError.add(ErrorCode.STORAGE_FULL);
            sNeedShowError.add(ErrorCode.PRIMARY_STORAGE_WRITE_ERROR);
            sNeedShowError.add(ErrorCode.SECONDARY_STORAGE_WRITE_ERROR);
            sNeedShowError.add(ErrorCode.STORAGE_LOW);
        }

        public DownloadManager(SyncBar syncBar, int i) {
            super(syncBar, i);
        }

        private ErrorHandler getErrorHandler() {
            if (this.mErrorHandler == null) {
                this.mErrorHandler = new ErrorHandler(getContext());
            }
            return this.mErrorHandler;
        }

        private void statClickWhenDownloading() {
            GallerySamplingStatHelper.recordCountEvent("Sync", "sync_auto_download_click_when_downloading");
        }

        public boolean needShow() {
            ErrorTip errorTip;
            int i = this.mDownloadState;
            if (i == 1) {
                return true;
            }
            if (i != 3 || (errorTip = getErrorHandler().getErrorTip()) == null || !sNeedShowError.contains(errorTip.getCode())) {
                return false;
            }
            return this.mCurError != errorTip.getCode();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x003c, code lost:
            r0 = getErrorHandler().getErrorTip();
         */
        public void onClick(View view) {
            final ErrorTip errorTip;
            Resources resources = getContext().getResources();
            AlertDialogFragment.Builder builder = null;
            if (this.mDownloadState == 1) {
                statClickWhenDownloading();
                builder = new AlertDialogFragment.Builder().setTitle(resources.getString(R.string.downloading_title)).setMessage(resources.getString(R.string.downloading_dialog_message)).setPositiveButton(resources.getString(R.string.ok), (DialogInterface.OnClickListener) null);
            }
            if (this.mDownloadState == 3 && errorTip != null) {
                builder = new AlertDialogFragment.Builder().setMessage(errorTip.getMessage(getContext())).setPositiveButton(errorTip.getActionStr(getContext()), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        errorTip.action(DownloadManager.this.getContext(), (ErrorActionCallback) null);
                    }
                }).setNegativeButton(resources.getString(R.string.cancel_download_btn_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GalleryPreferences.Sync.setAutoDownload(false);
                        BatchDownloadManager.getInstance().stopBatchDownload(DownloadManager.this.getContext());
                    }
                });
            }
            if (builder != null && (getContext() instanceof Activity)) {
                builder.create().showAllowingStateLoss(((Activity) getContext()).getFragmentManager(), "download");
            }
        }

        public void onDownloadCancelled(List<BatchDownloadManager.BatchItem> list, List<BatchDownloadManager.BatchItem> list2) {
            this.mDownloadState = 0;
            refresh(new PanelData((String) null, (String) null, (Drawable) null, false, this), false);
        }

        public void onDownloadComplete(List<BatchDownloadManager.BatchItem> list, List<BatchDownloadManager.BatchItem> list2, ErrorCode errorCode) {
            if ((errorCode == null || errorCode == ErrorCode.NO_ERROR) ? false : true) {
                this.mDownloadState = 3;
                getErrorHandler().handleError(errorCode, new ErrorTranslateCallback() {
                    public void onTranslate(ErrorTip errorTip) {
                        if (DownloadManager.this.mDownloadState == 3) {
                            if (errorTip != null) {
                                CharSequence title = errorTip.getTitle(DownloadManager.this.getContext());
                                if (!TextUtils.isEmpty(title)) {
                                    if (DownloadManager.this.refresh(new PanelData(title + DownloadManager.this.getContext().getString(R.string.download_error_tip), (String) null, (Drawable) null, true, DownloadManager.this), true)) {
                                        ErrorCode unused = DownloadManager.this.mCurError = errorTip.getCode();
                                        return;
                                    }
                                    return;
                                }
                            }
                            DownloadManager downloadManager = DownloadManager.this;
                            downloadManager.refresh(new PanelData((String) null, (String) null, (Drawable) null, false, downloadManager), false);
                        }
                    }
                });
                return;
            }
            this.mDownloadState = 2;
            refresh(new PanelData((String) null, (String) null, (Drawable) null, false, this), false);
        }

        public void onDownloadProgress(List<BatchDownloadManager.BatchItem> list, List<BatchDownloadManager.BatchItem> list2) {
            this.mDownloadState = 1;
            ErrorHandler errorHandler = this.mErrorHandler;
            if (errorHandler != null) {
                errorHandler.clearError();
            }
            refresh(new PanelData(getContext().getResources().getString(R.string.downloading_title), getContext().getResources().getString(R.string.downloading_desc, new Object[]{Integer.valueOf(list.size()), Integer.valueOf(list2.size())}), (Drawable) null, false, this), false);
        }

        public void onPause() {
            BatchDownloadManager.getInstance().unregisterBatchDownloadListener(this);
        }

        public void onResume() {
            if (BatchDownloadManager.canAutoDownload()) {
                BatchDownloadManager.getInstance().startBatchDownload(getContext(), false);
            }
            BatchDownloadManager.getInstance().registerBatchDownloadListener(this);
        }
    }

    private static final class PanelData {
        public final String desc;
        public final View.OnClickListener listener;
        public final boolean showArrow;
        public final String title;
        public final Drawable titleDrawable;

        public PanelData(String str, String str2, Drawable drawable, boolean z, View.OnClickListener onClickListener) {
            this.title = str;
            this.desc = str2;
            this.titleDrawable = drawable;
            this.showArrow = z;
            this.listener = onClickListener;
        }
    }

    private static final class SyncManager extends ChildManager implements View.OnClickListener, OnSyncStateChangeObserver, OnAppFocusedListener {
        private static List<SyncStatus> sInstanceAutoShowStatus = new LinkedList();
        private static List<SyncStatus> sPersistentAutoShowStatus = new LinkedList();
        private static List<SyncStatus> sProcessAutoShowStatus = new LinkedList();
        private static List<SyncStatus> sWarnStatus = new LinkedList();
        private SyncStatus mCurSyncStatus;

        static {
            sInstanceAutoShowStatus.add(SyncStatus.SYNCING_METADATA);
            sInstanceAutoShowStatus.add(SyncStatus.SYNC_ERROR);
            sInstanceAutoShowStatus.add(SyncStatus.CTA_NOT_ALLOW);
            sProcessAutoShowStatus.add(SyncStatus.SYNCED_WITH_OVERSIZED_FILE);
            sProcessAutoShowStatus.add(SyncStatus.DISCONNECTED);
            sProcessAutoShowStatus.add(SyncStatus.NO_WIFI);
            sProcessAutoShowStatus.add(SyncStatus.BATTERY_LOW);
            sProcessAutoShowStatus.add(SyncStatus.UNKNOWN_ERROR);
            sPersistentAutoShowStatus.add(SyncStatus.NO_ACCOUNT);
            sPersistentAutoShowStatus.add(SyncStatus.MASTER_SYNC_OFF);
            sPersistentAutoShowStatus.add(SyncStatus.SYNC_OFF);
            sPersistentAutoShowStatus.add(SyncStatus.SYSTEM_SPACE_LOW);
            sPersistentAutoShowStatus.add(SyncStatus.CLOUD_SPACE_FULL);
            sWarnStatus.add(SyncStatus.SYNC_ERROR);
            sWarnStatus.add(SyncStatus.CTA_NOT_ALLOW);
            sWarnStatus.add(SyncStatus.SYSTEM_SPACE_LOW);
            sWarnStatus.add(SyncStatus.CLOUD_SPACE_FULL);
        }

        public SyncManager(SyncBar syncBar, int i) {
            super(syncBar, i);
        }

        /* access modifiers changed from: private */
        public boolean canAutoOpenDrawer(SyncStatus syncStatus) {
            if (isPermanent()) {
                return true;
            }
            if (sInstanceAutoShowStatus.contains(syncStatus)) {
                return this.mCurSyncStatus != syncStatus;
            }
            if (sProcessAutoShowStatus.contains(syncStatus)) {
                return GalleryPreferences.TopBar.getLastSyncStatus() != syncStatus;
            }
            if (sPersistentAutoShowStatus.contains(syncStatus)) {
                return !hasShowSyncStatusPersistent(syncStatus);
            }
            return false;
        }

        private Drawable getTitleDrawable(boolean z) {
            if (z) {
                return getContext().getResources().getDrawable(R.drawable.sync_icon_error);
            }
            return null;
        }

        private boolean hasShowSyncStatusPersistent() {
            for (SyncStatus hasShowedSyncStatusTip : sPersistentAutoShowStatus) {
                if (GalleryPreferences.TopBar.hasShowedSyncStatusTip(hasShowedSyncStatusTip)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasShowSyncStatusPersistent(SyncStatus syncStatus) {
            boolean hasShowedSyncStatusTip = GalleryPreferences.TopBar.hasShowedSyncStatusTip(syncStatus);
            if (!hasShowedSyncStatusTip) {
                GalleryPreferences.TopBar.setHasShowedSyncStatusTip(syncStatus, true);
            }
            return hasShowedSyncStatusTip;
        }

        private boolean needShowSyncBar(SyncStatus syncStatus) {
            if (!Rom.IS_INTERNATIONAL) {
                return true;
            }
            if (syncStatus == SyncStatus.NO_ACCOUNT || syncStatus == SyncStatus.MASTER_SYNC_OFF || syncStatus == SyncStatus.SYNC_OFF) {
                return CloudControlStrategyHelper.getSyncStrategy().isGuideCloudInternational();
            }
            return true;
        }

        private boolean needShowWarning(SyncStatus syncStatus) {
            return sWarnStatus.contains(syncStatus);
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0082, code lost:
            if (r5 != null) goto L_0x009c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x008a, code lost:
            if (r15.getSyncType() != com.miui.gallery.cloud.base.SyncType.GPRS_FORCE) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x008c, code lost:
            r0 = r0.getString(com.miui.gallery.R.string.syncing_gprs_title);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0094, code lost:
            r0 = r0.getString(com.miui.gallery.R.string.syncing_normal_title);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x009c, code lost:
            r0 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x009d, code lost:
            if (r3 <= 0) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x009f, code lost:
            r5 = java.lang.String.format("%d", new java.lang.Object[]{java.lang.Integer.valueOf(r3)});
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00af, code lost:
            if (hasShowSyncStatusPersistent() == false) goto L_0x00b6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b1, code lost:
            removeSyncStatusPersistent();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b5, code lost:
            r5 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b6, code lost:
            if (r3 <= 0) goto L_0x00b9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b8, code lost:
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b9, code lost:
            r9 = r0;
            r12 = r2;
            r10 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0101, code lost:
            r0 = canAutoOpenDrawer(r1);
            r11 = getTitleDrawable(needShowWarning(r1));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x010f, code lost:
            if (r12 == false) goto L_0x0113;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0111, code lost:
            r13 = r14;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0113, code lost:
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0114, code lost:
            r2 = refresh(new com.miui.gallery.ui.SyncBar.PanelData(r9, r10, r11, r12, r13), r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0120, code lost:
            if (needShowSyncBar(r1) == false) goto L_0x0130;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0122, code lost:
            if (r2 == false) goto L_0x0132;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0124, code lost:
            r14.mCurSyncStatus = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0126, code lost:
            if (r0 == false) goto L_0x0132;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0128, code lost:
            statSyncBarAutoShowEvent(r1.name());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0130, code lost:
            r14.mCurSyncStatus = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0132, code lost:
            com.miui.gallery.preference.GalleryPreferences.TopBar.saveLastSyncStatus(r1);
            com.miui.gallery.util.Log.i("SyncBar", "refresh sync bar: %s, showBar: %s", r15.getSyncStatus(), java.lang.Boolean.valueOf(r0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0144, code lost:
            return;
         */
        private void refreshSyncBar(SyncStateInfo syncStateInfo) {
            boolean z;
            String str;
            String str2;
            String str3;
            String str4;
            Resources resources = getContext().getResources();
            int[] dirtyCount = syncStateInfo.getDirtyCount();
            boolean z2 = false;
            int i = dirtyCount[0] + dirtyCount[1];
            SyncStatus syncStatus = syncStateInfo.getSyncStatus();
            switch (syncStatus) {
                case SYNCED:
                    int[] syncedCount = syncStateInfo.getSyncedCount();
                    int i2 = syncedCount[0] + syncedCount[1];
                    str3 = resources.getString(R.string.synced_title);
                    if (i2 > 0) {
                        str2 = str3;
                        str = String.format("%d", new Object[]{Integer.valueOf(i2)});
                        z = true;
                        break;
                    }
                case NO_ACCOUNT:
                case MASTER_SYNC_OFF:
                case SYNC_OFF:
                    str3 = resources.getString(R.string.sync_off_title);
                    str2 = str3;
                    z = true;
                    str = null;
                    break;
                case SYNC_PENDING:
                    if (i > 0) {
                        str4 = resources.getString(R.string.sync_pending_title);
                        break;
                    }
                case SYNCING:
                    str4 = null;
                    break;
                case SYNCING_METADATA:
                case SYNC_META_PENDING:
                    String string = resources.getString(R.string.syncing_metadata_title);
                    int[] syncedCount2 = syncStateInfo.getSyncedCount();
                    int i3 = syncedCount2[0] + syncedCount2[1];
                    str2 = string;
                    z = false;
                    str = i3 > 0 ? String.format("%d", new Object[]{Integer.valueOf(i3)}) : null;
                    break;
                case CTA_NOT_ALLOW:
                    str3 = resources.getString(R.string.sync_not_allow_cta_title);
                    str2 = str3;
                    z = true;
                    str = null;
                    break;
                case SYNCED_WITH_OVERSIZED_FILE:
                    str3 = resources.getQuantityString(R.plurals.sync_oversized_count, i, new Object[]{Integer.valueOf(i)});
                    str2 = str3;
                    z = true;
                    str = null;
                    break;
                default:
                    str3 = i > 0 ? resources.getQuantityString(R.plurals.sync_error_title, i, new Object[]{Integer.valueOf(i)}) : !Preference.sIsFirstSynced() ? resources.getString(R.string.sync_metatata_wait_title) : resources.getString(R.string.sync_state_unknow);
            }
        }

        private void removeSyncStatusPersistent() {
            for (SyncStatus hasShowedSyncStatusTip : sPersistentAutoShowStatus) {
                GalleryPreferences.TopBar.setHasShowedSyncStatusTip(hasShowedSyncStatusTip, false);
            }
        }

        private void statSyncBarAutoShowEvent(String str) {
            GallerySamplingStatHelper.recordCountEvent("home_sync_bar", String.format(Locale.US, "sync_bar_show_%s", new Object[]{str}));
        }

        private void statSyncBarClickEvent(String str, HashMap<String, String> hashMap) {
            GallerySamplingStatHelper.recordCountEvent("home_sync_bar", String.format(Locale.US, "sync_bar_click_%s", new Object[]{str}), hashMap);
        }

        public SyncStatus getCurSyncStatus() {
            return SyncStateManager.getInstance().getSyncState().getSyncStatus();
        }

        public boolean needShow() {
            return needShowSyncBar(SyncStateManager.getInstance().getSyncState().getSyncStatus());
        }

        public void onAppFocused() {
        }

        public void onClick(View view) {
            Context context = getContext();
            SyncStatus syncStatus = SyncStateManager.getInstance().getSyncState().getSyncStatus();
            HashMap hashMap = new HashMap();
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$cloud$syncstate$SyncStatus[syncStatus.ordinal()];
            if (i == 1) {
                context.startActivity(new Intent(context, CloudSpaceStatusActivity.class));
            } else if (i == 2) {
                hashMap.put("permanent", String.valueOf(isPermanent()));
                IntentUtil.guideToLoginXiaomiAccount(context, GalleryIntent.CloudGuideSource.TOPBAR);
            } else if (i == 3 || i == 4) {
                IntentUtil.gotoTurnOnSyncSwitch(context);
            } else {
                context.startActivity(new Intent(context, BackupDetailActivity.class));
            }
            statSyncBarClickEvent(syncStatus.name(), hashMap);
        }

        public void onPause() {
            SyncStateManager.getInstance().unregisterSyncStateObserver(getContext(), this);
        }

        public void onResume() {
            SyncStateManager.getInstance().registerSyncStateObserver(getContext(), this);
        }

        public void onSyncStateChanged(SyncStateInfo syncStateInfo) {
            refreshSyncBar(syncStateInfo);
        }
    }

    static {
        sDownloadOverlayStatus.add(SyncStatus.MASTER_SYNC_OFF);
        sDownloadOverlayStatus.add(SyncStatus.SYNC_OFF);
        sDownloadOverlayStatus.add(SyncStatus.SYNCED);
    }

    public SyncBar(Context context, int i, PanelManager panelManager) {
        super(context, i, panelManager);
    }

    private void ensureView() {
        if (this.mPanelItem == null) {
            this.mPanelItem = new PanelItemLayout(getContext());
            this.mDesc = new TextView(getContext());
            this.mDesc.setTextColor(getContext().getResources().getColor(R.color.custom_list_value_color));
            this.mDesc.setTextSize(0, (float) getContext().getResources().getDimensionPixelSize(R.dimen.custom_list_value_size));
            this.mPanelItem.addDetail((View) this.mDesc);
        }
    }

    public View getView() {
        ensureView();
        return this.mPanelItem.getView();
    }

    public void onAppFocused() {
        this.mSyncManager.onAppFocused();
    }

    public void onDestroy() {
    }

    public void onPause() {
        this.mSyncManager.onPause();
        this.mDownloadManager.onPause();
    }

    public void onResume() {
        this.mSyncManager.onResume();
        this.mDownloadManager.onResume();
    }

    public boolean refresh(PanelData panelData, boolean z, int i) {
        boolean z2;
        if (this.mPanelItem == null || this.mSyncManager.needShow() || this.mDownloadManager.needShow()) {
            if (i == 1) {
                this.mDownloadPanelData = panelData;
                z2 = this.mDownloadManager.needShow() && (!this.mSyncManager.needShow() || sDownloadOverlayStatus.contains(this.mSyncManager.getCurSyncStatus()));
                if (!z2) {
                    boolean z3 = this.mSyncManager.needShow() && this.mSyncPanelData != null;
                    SyncManager syncManager = this.mSyncManager;
                    z = syncManager.canAutoOpenDrawer(syncManager.getCurSyncStatus());
                    z2 = z3;
                    panelData = this.mSyncPanelData;
                }
            } else {
                z2 = false;
            }
            if (i == 0) {
                this.mSyncPanelData = panelData;
                if (this.mSyncManager.needShow()) {
                    z2 = !this.mDownloadManager.needShow() || !sDownloadOverlayStatus.contains(this.mSyncManager.getCurSyncStatus());
                }
                if (!z2) {
                    z2 = this.mDownloadManager.needShow() && this.mDownloadPanelData != null;
                    panelData = this.mDownloadPanelData;
                }
            }
            if (z2) {
                ensureView();
                this.mPanelItem.setTitle(panelData.title);
                this.mDesc.setText(panelData.desc);
                this.mPanelItem.setTitleDrawable(panelData.titleDrawable);
                this.mPanelItem.showArrow(panelData.showArrow);
                this.mPanelItem.setOnClickListener(panelData.listener);
                this.mPanelManager.addItem(this, z);
            }
            return z2;
        }
        this.mPanelManager.removeItem(this, false);
        return false;
    }
}
