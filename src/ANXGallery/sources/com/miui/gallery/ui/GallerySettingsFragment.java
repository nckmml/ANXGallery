package com.miui.gallery.ui;

import android.accounts.Account;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.miui.account.AccountHelper;
import com.miui.gallery.R;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.download.BatchDownloadManager;
import com.miui.gallery.cloud.download.BatchDownloadUtil;
import com.miui.gallery.pendingtask.PendingTaskManager;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.deprecated.GalleryCloudProvider;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.AlertDialogFragment;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.gallery.ui.SlideshowIntervalDialogFragment;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.DebugUtil;
import com.miui.gallery.util.FeatureUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.SyncStatusObserverWrapper;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.widget.MultiClickListener;
import com.miui.gallery.widget.ValueListPreference;
import com.miui.os.Rom;
import com.xiaomi.micloudsdk.request.utils.CloudUtils;
import java.util.HashMap;
import miui.preference.PreferenceFragment;
import miui.preference.ValuePreference;

public class GallerySettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private static final int[] DOWNLOAD_TYPE_ENTRIES = {R.string.download_type_thumbnail_desc, R.string.download_type_origin_desc};
    private static final int[] DOWNLOAD_TYPE_VALUES = {R.string.download_type_thumbnail, R.string.download_type_origin};
    private static SyncStatusObserverWrapper sSyncStatusObserverWrapper = new SyncStatusObserverWrapper();
    private BroadcastReceiver mAIAlbumStatusObserver = null;
    private CheckBoxPreference mAIAlbumStatusPref;
    /* access modifiers changed from: private */
    public CheckBoxPreference mAutoBackPref;
    private CheckBoxPreference mAutoDownloadPref;
    /* access modifiers changed from: private */
    public CheckBoxPreference mBackupOnlyInWifiPref;
    private PreferenceCategory mBrowseCategory;
    private ValuePreference mCloudAlbumListPref;
    private ValuePreference mCloudPrivacyPref;
    private MultiClickListener mDebugClickListener = new MultiClickListener(3, new View.OnClickListener() {
        public void onClick(View view) {
            GallerySettingsFragment.this.addDebugPreferences();
        }
    });
    private ValueListPreference mDownloadTypePref;
    private CheckBoxPreference mFaceLocalStatusPref;
    private CheckBoxPreference mImageSelectionPref;
    private CheckBoxPreference mImpunityDeclarationPref;
    /* access modifiers changed from: private */
    public Handler mMainHandler = new Handler();
    private PreferenceCategory mMiCloudCategory;
    private Object mObserverHandle;
    private PreferenceScreen mPreferenceRoot;
    private ValuePreference mPrivacyPolicyPref;
    private PreferenceCategory mRecommendCategory;
    private ValuePreference mShowHiddenAlbumPref;
    private CheckBoxPreference mShowLocalAlbumOnlyPref;
    private ValuePreference mSlideshowIntervalPref;
    private CheckBoxPreference mStoryFuctionPref;
    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() {
        public void onStatusChanged(int i) {
            if (i == 1) {
                GallerySettingsFragment.this.mMainHandler.post(new Runnable() {
                    public void run() {
                        Activity activity;
                        if (GallerySettingsFragment.this.mAutoBackPref != null && (activity = GallerySettingsFragment.this.getActivity()) != null && !activity.isFinishing()) {
                            GallerySettingsFragment.this.mAutoBackPref.setChecked(ContentResolver.getMasterSyncAutomatically() && ContentResolver.getSyncAutomatically(AccountHelper.getXiaomiAccount(activity), "com.miui.gallery.cloud.provider"));
                        }
                    }
                });
            }
        }
    };
    private ValuePreference mTrashBinPref;

    /* access modifiers changed from: private */
    public void addDebugPreferences() {
        Preference findPreference = findPreference("others");
        if (findPreference != null) {
            PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference;
            if (preferenceCategory.findPreference("debug_gallery") == null) {
                CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getActivity());
                checkBoxPreference.setKey("print_log");
                checkBoxPreference.setTitle(R.string.print_log);
                checkBoxPreference.setPersistent(false);
                checkBoxPreference.setChecked(BaseGalleryPreferences.Debug.isPrintLog());
                checkBoxPreference.setOnPreferenceChangeListener(this);
                preferenceCategory.addPreference(checkBoxPreference);
                ValuePreference valuePreference = new ValuePreference(getActivity());
                valuePreference.setKey("debug_gallery");
                valuePreference.setTitle(R.string.start_debug);
                valuePreference.setOnPreferenceClickListener(this);
                preferenceCategory.addPreference(valuePreference);
                ValuePreference valuePreference2 = new ValuePreference(getActivity());
                valuePreference2.setKey("correct_photo_time");
                valuePreference2.setTitle(R.string.correct_photo_time);
                valuePreference2.setOnPreferenceClickListener(this);
                preferenceCategory.addPreference(valuePreference2);
                ValuePreference valuePreference3 = new ValuePreference(getActivity());
                valuePreference3.setKey("delete_dup_medias");
                valuePreference3.setTitle(R.string.clear_dup_medias);
                valuePreference3.setOnPreferenceClickListener(this);
                preferenceCategory.addPreference(valuePreference3);
                ValuePreference valuePreference4 = new ValuePreference(getActivity());
                valuePreference4.setKey("thumbnail_build_error");
                valuePreference4.setTitle(R.string.thumbnail_build_error);
                valuePreference4.setOnPreferenceClickListener(this);
                preferenceCategory.addPreference(valuePreference4);
            }
        }
    }

    /* access modifiers changed from: private */
    public void changeBackupOnlyWifi(boolean z) {
        SyncUtil.setBackupOnlyWifi(getActivity(), z);
        getActivity().getContentResolver().notifyChange(GalleryCloudProvider.SYNC_SETTINGS_URI, (ContentObserver) null, false);
    }

    private void changeDownloadType(DownloadType downloadType) {
        GalleryPreferences.Sync.setDownloadType(downloadType);
        if (BatchDownloadManager.canAutoDownload()) {
            BatchDownloadManager.getInstance().startBatchDownload(getActivity(), true);
        }
    }

    private boolean getAIAlbumStatus() {
        return AIAlbumStatusHelper.isAIAlbumEnabled();
    }

    private String[] getDownloadTypeEntries() {
        String[] strArr = new String[DOWNLOAD_TYPE_ENTRIES.length];
        int i = 0;
        while (true) {
            int[] iArr = DOWNLOAD_TYPE_ENTRIES;
            if (i >= iArr.length) {
                return strArr;
            }
            strArr[i] = getString(iArr[i]);
            i++;
        }
    }

    private DownloadType getDownloadTypeFrom(String str) {
        if (getString(DOWNLOAD_TYPE_VALUES[0]).equals(str)) {
            return DownloadType.THUMBNAIL;
        }
        if (getString(DOWNLOAD_TYPE_VALUES[1]).equals(str)) {
            return DownloadType.ORIGIN;
        }
        return null;
    }

    private String getDownloadTypeValue() {
        DownloadType downloadType = GalleryPreferences.Sync.getDownloadType();
        if (downloadType == DownloadType.THUMBNAIL) {
            return getString(DOWNLOAD_TYPE_VALUES[0]);
        }
        if (downloadType == DownloadType.ORIGIN) {
            return getString(DOWNLOAD_TYPE_VALUES[1]);
        }
        return null;
    }

    private String[] getDownloadTypeValues() {
        String[] strArr = new String[DOWNLOAD_TYPE_VALUES.length];
        int i = 0;
        while (true) {
            int[] iArr = DOWNLOAD_TYPE_VALUES;
            if (i >= iArr.length) {
                return strArr;
            }
            strArr[i] = getString(iArr[i]);
            i++;
        }
    }

    private boolean getFaceAlbumStatus() {
        return AIAlbumStatusHelper.isFaceAlbumEnabled();
    }

    private boolean onBackupOnlyWifiChange(final boolean z) {
        if (!z) {
            new AlertDialogFragment.Builder().setTitle(getResources().getString(R.string.title_tip)).setMessage(getResources().getString(R.string.backup_only_wifi_dialog_message)).setNegativeButton(getResources().getString(R.string.cancel), (DialogInterface.OnClickListener) null).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (GallerySettingsFragment.this.mBackupOnlyInWifiPref != null) {
                        GallerySettingsFragment.this.mBackupOnlyInWifiPref.setChecked(z);
                        GallerySettingsFragment.this.changeBackupOnlyWifi(z);
                    }
                }
            }).create().showAllowingStateLoss(getChildFragmentManager(), "slimDialog");
            return false;
        }
        changeBackupOnlyWifi(z);
        return true;
    }

    private boolean onDownloadTypePreferenceChange(DownloadType downloadType) {
        if (downloadType == null) {
            return false;
        }
        changeDownloadType(downloadType);
        return true;
    }

    private boolean onlyCloud() {
        return "com.miui.gallery.cloud.provider.SYNC_SETTINGS".equals(getActivity().getIntent().getAction());
    }

    /* access modifiers changed from: private */
    public void refreshSlideshowIntervalPrefValue() {
        int slideShowInterval = GalleryPreferences.SlideShow.getSlideShowInterval();
        this.mSlideshowIntervalPref.setValue(getResources().getQuantityString(R.plurals.slideshow_interval_value, slideShowInterval, new Object[]{Integer.valueOf(slideShowInterval)}));
    }

    private boolean setImageSelectionFunctionState(boolean z) {
        GalleryPreferences.Assistant.setImageSelectionFunctionState(z);
        Log.d("GallerySettingsFragment", "The image selection fuction state:" + z);
        return true;
    }

    private boolean setStoryFunctionState(boolean z) {
        GalleryPreferences.Assistant.setStoryFunctionState(z);
        Log.d("GallerySettingsFragment", "The story fuction state:" + z);
        if (!z) {
            PendingTaskManager.getInstance().cancelAll(2);
            PendingTaskManager.getInstance().cancelAll(10);
            return true;
        }
        Account xiaomiAccount = CloudUtils.getXiaomiAccount();
        if (xiaomiAccount == null) {
            return true;
        }
        GalleryCloudSyncTagUtils.setCardSyncTag(xiaomiAccount, 0);
        GalleryCloudSyncTagUtils.setCardSyncInfo(xiaomiAccount, "");
        SyncUtil.requestSync(StaticContext.sGetAndroidContext(), new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(68).build());
        return true;
    }

    private void showSlideshowIntervalDialog() {
        SlideshowIntervalDialogFragment slideshowIntervalDialogFragment = new SlideshowIntervalDialogFragment();
        slideshowIntervalDialogFragment.setCompleteListener(new SlideshowIntervalDialogFragment.CompleteListener() {
            public void onComplete() {
                GallerySettingsFragment.this.refreshSlideshowIntervalPrefValue();
            }
        });
        slideshowIntervalDialogFragment.showAllowingStateLoss(getFragmentManager(), "SlideshowIntervalDialogFragment");
    }

    private void showSyncSettingDialog() {
        ConfirmDialog.showConfirmDialog(getFragmentManager(), getString(R.string.backup_sync_dialog_title), getString(R.string.backup_sync_dialog_tips), getString(R.string.cancel), getString(R.string.backup_sync_dialog_confirm), new ConfirmDialog.ConfirmDialogInterface() {
            public void onCancel(DialogFragment dialogFragment) {
            }

            public void onConfirm(DialogFragment dialogFragment) {
                Intent intent = new Intent("android.settings.ACCOUNT_LIST");
                intent.setPackage("com.android.settings");
                GallerySettingsFragment.this.startActivity(intent);
            }
        });
    }

    private void statAutoDownloadSwitchChanged(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("switch_status", z ? "enable" : "disable");
        GallerySamplingStatHelper.recordCountEvent("Sync", "sync_auto_download_switch_changed", hashMap);
    }

    private void switchAutoDownload(final boolean z) {
        GalleryPreferences.Sync.setAutoDownload(z);
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
            private boolean checkValidTask(boolean z) {
                return z == GalleryPreferences.Sync.isAutoDownload();
            }

            public Object run(ThreadPool.JobContext jobContext) {
                if (!checkValidTask(z)) {
                    Log.w("GallerySettingsFragment", "invalid switch download task, old %s, new %s", Boolean.valueOf(z), Boolean.valueOf(GalleryPreferences.Sync.isAutoDownload()));
                    return null;
                }
                Context applicationContext = GallerySettingsFragment.this.getActivity() != null ? GallerySettingsFragment.this.getActivity().getApplicationContext() : null;
                if (applicationContext != null) {
                    if (z) {
                        BatchDownloadUtil.cleanDownloadedMark(applicationContext);
                        if (checkValidTask(z) && BatchDownloadManager.canAutoDownload()) {
                            BatchDownloadManager.getInstance().startBatchDownload(applicationContext, true);
                        }
                    } else {
                        BatchDownloadManager.getInstance().stopBatchDownload(applicationContext);
                    }
                }
                return null;
            }
        });
        statAutoDownloadSwitchChanged(z);
    }

    /* access modifiers changed from: private */
    public void updateAIAlbumStatus() {
        CheckBoxPreference checkBoxPreference = this.mAIAlbumStatusPref;
        if (checkBoxPreference != null) {
            checkBoxPreference.setChecked(getAIAlbumStatus());
            return;
        }
        CheckBoxPreference checkBoxPreference2 = this.mFaceLocalStatusPref;
        if (checkBoxPreference2 != null) {
            checkBoxPreference2.setChecked(getFaceAlbumStatus());
        }
    }

    public void onActivityCreated(Bundle bundle) {
        GallerySettingsFragment.super.onActivityCreated(bundle);
        TextView textView = new TextView(getActivity());
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.debug_generate_view_size);
        textView.setWidth(dimensionPixelSize);
        textView.setHeight(dimensionPixelSize);
        getActionBar().setEndView(textView);
        textView.setOnClickListener(this.mDebugClickListener);
        this.mDownloadTypePref.setValue(getDownloadTypeValue());
    }

    public void onCreate(Bundle bundle) {
        GallerySettingsFragment.super.onCreate(bundle);
        getPreferenceManager().setSharedPreferencesName("com.miui.gallery_preferences_new");
        addPreferencesFromResource(R.xml.gallery_preferences);
        this.mPreferenceRoot = (PreferenceScreen) findPreference("root");
        this.mMiCloudCategory = (PreferenceCategory) findPreference("micloud_category");
        this.mRecommendCategory = (PreferenceCategory) findPreference("recommend_category");
        this.mAutoBackPref = (CheckBoxPreference) findPreference("backup_automatically");
        this.mAutoBackPref.setOnPreferenceChangeListener(this);
        this.mAutoBackPref.setOnPreferenceClickListener(this);
        this.mBackupOnlyInWifiPref = (CheckBoxPreference) findPreference(GalleryPreferences.PrefKeys.SYNC_BACKUP_ONLY_IN_WIFI);
        this.mBackupOnlyInWifiPref.setOnPreferenceChangeListener(this);
        this.mCloudAlbumListPref = findPreference("cloud_album_list");
        this.mCloudAlbumListPref.setOnPreferenceClickListener(this);
        this.mCloudAlbumListPref.setShowRightArrow(true);
        this.mCloudPrivacyPref = findPreference("cloud_privacy");
        this.mCloudPrivacyPref.setOnPreferenceClickListener(this);
        this.mCloudPrivacyPref.setShowRightArrow(true);
        this.mAIAlbumStatusPref = (CheckBoxPreference) findPreference("cloud_ai_album_status");
        this.mFaceLocalStatusPref = (CheckBoxPreference) findPreference("cloud_face_local_status");
        if (AIAlbumStatusHelper.useNewAIAlbumSwitch()) {
            this.mMiCloudCategory.removePreference(this.mFaceLocalStatusPref);
            this.mFaceLocalStatusPref = null;
            this.mAIAlbumStatusPref.setOnPreferenceChangeListener(this);
            this.mCloudPrivacyPref.setTitle(R.string.cloud_privacy_with_ai_album);
        } else {
            this.mMiCloudCategory.removePreference(this.mAIAlbumStatusPref);
            this.mAIAlbumStatusPref = null;
            this.mFaceLocalStatusPref.setOnPreferenceChangeListener(this);
            this.mCloudPrivacyPref.setTitle(R.string.cloud_privacy_with_face);
        }
        if (Rom.IS_INTERNATIONAL) {
            this.mMiCloudCategory.removePreference(this.mCloudPrivacyPref);
        }
        if (!ImageFeatureManager.isImageFeatureCalculationEnable()) {
            this.mPreferenceRoot.removePreference(this.mRecommendCategory);
        } else {
            this.mImageSelectionPref = (CheckBoxPreference) findPreference("image_selection_function");
            this.mImageSelectionPref.setOnPreferenceChangeListener(this);
            this.mStoryFuctionPref = (CheckBoxPreference) findPreference("story_function");
            this.mStoryFuctionPref.setOnPreferenceChangeListener(this);
            if (!ImageFeatureManager.isDeviceSupportStoryFunction()) {
                this.mRecommendCategory.removePreference(this.mStoryFuctionPref);
            }
        }
        this.mAutoDownloadPref = (CheckBoxPreference) findPreference("auto_download_media");
        this.mDownloadTypePref = (ValueListPreference) findPreference(GalleryPreferences.PrefKeys.SYNC_DOWNLOAD_TYPE);
        this.mAutoDownloadPref.setChecked(GalleryPreferences.Sync.isAutoDownload());
        this.mAutoDownloadPref.setOnPreferenceChangeListener(this);
        this.mDownloadTypePref.setWhichAsValue(1);
        this.mDownloadTypePref.setEntries(getDownloadTypeEntries());
        this.mDownloadTypePref.setEntryValues(getDownloadTypeValues());
        this.mDownloadTypePref.setValue(getDownloadTypeValue());
        this.mDownloadTypePref.setOnPreferenceChangeListener(this);
        if (onlyCloud()) {
            this.mPreferenceRoot.removeAll();
            this.mPreferenceRoot.addPreference(this.mMiCloudCategory);
            this.mMiCloudCategory.setTitle("");
        } else {
            this.mBrowseCategory = (PreferenceCategory) findPreference("browse_category");
            this.mShowLocalAlbumOnlyPref = (CheckBoxPreference) findPreference("show_local_album_only");
            this.mShowLocalAlbumOnlyPref.setOnPreferenceChangeListener(this);
            this.mShowHiddenAlbumPref = findPreference(GalleryPreferences.PrefKeys.HIDDEN_ALBUM_SHOW);
            this.mShowHiddenAlbumPref.setShowRightArrow(true);
            this.mShowHiddenAlbumPref.setOnPreferenceClickListener(this);
            this.mSlideshowIntervalPref = findPreference(GalleryPreferences.PrefKeys.SLIDESHOW_INTERVAL);
            this.mSlideshowIntervalPref.setShowRightArrow(true);
            this.mSlideshowIntervalPref.setOnPreferenceClickListener(this);
            this.mImpunityDeclarationPref = (CheckBoxPreference) findPreference("impunity_declaration");
            if (BuildUtil.isInternational()) {
                ((PreferenceCategory) findPreference("others")).removePreference(this.mImpunityDeclarationPref);
            } else {
                this.mImpunityDeclarationPref.setChecked(GalleryPreferences.CTA.remindConnectNetworkEveryTime());
                this.mImpunityDeclarationPref.setOnPreferenceChangeListener(this);
            }
            this.mTrashBinPref = findPreference("trash_bin");
            if (ApplicationHelper.isCloudTrashBinFeatureOpen()) {
                this.mTrashBinPref.setShowRightArrow(true);
                this.mTrashBinPref.setOnPreferenceClickListener(this);
            } else {
                ((PreferenceCategory) findPreference("others")).removePreference(this.mTrashBinPref);
            }
            this.mPrivacyPolicyPref = findPreference("privacy_policy");
            this.mPrivacyPolicyPref.setOnPreferenceClickListener(this);
            refreshSlideshowIntervalPrefValue();
        }
        if (!FeatureUtil.isSupportBackupOnlyWifi()) {
            this.mMiCloudCategory.removePreference(this.mBackupOnlyInWifiPref);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        getActivity().finish();
        return true;
    }

    public void onPause() {
        GallerySettingsFragment.super.onPause();
        sSyncStatusObserverWrapper.setSyncStatusObserverWorker((SyncStatusObserver) null);
        Object obj = this.mObserverHandle;
        if (obj != null) {
            ContentResolver.removeStatusChangeListener(obj);
            this.mObserverHandle = null;
        }
        if (this.mAIAlbumStatusObserver != null) {
            AIAlbumStatusHelper.unregisterAIAlbumStatusReceiver(getActivity(), this.mAIAlbumStatusObserver);
            this.mAIAlbumStatusObserver = null;
        }
        GallerySamplingStatHelper.recordPageEnd(getActivity(), "settings");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Activity activity = getActivity();
        if (preference == this.mShowLocalAlbumOnlyPref) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            GalleryPreferences.LocalMode.setOnlyShowLocalPhoto(booleanValue);
            GallerySamplingStatHelper.recordStringPropertyEvent("album", "show_local_album_only", String.valueOf(booleanValue));
            activity.getContentResolver().notifyChange(GalleryContract.Media.URI, (ContentObserver) null, false);
            activity.getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, false);
            activity.getContentResolver().notifyChange(GalleryContract.PeopleFace.PERSONS_URI, (ContentObserver) null, false);
            activity.getContentResolver().notifyChange(GalleryContract.RecentDiscoveredMedia.URI, (ContentObserver) null, false);
        } else if (preference == this.mAutoBackPref) {
            return SyncUtil.setSyncAutomatically(activity, ((Boolean) obj).booleanValue()) && ContentResolver.getMasterSyncAutomatically();
        } else {
            CheckBoxPreference checkBoxPreference = this.mAIAlbumStatusPref;
            if (checkBoxPreference == null || preference != checkBoxPreference) {
                CheckBoxPreference checkBoxPreference2 = this.mFaceLocalStatusPref;
                if (checkBoxPreference2 != null && preference == checkBoxPreference2) {
                    AIAlbumStatusHelper.setFaceAlbumStatus(activity, ((Boolean) obj).booleanValue());
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("switchTo", obj.toString());
                    GallerySamplingStatHelper.recordCountEvent("settings", "settings_change_face_switch", hashMap);
                } else if (preference == this.mImpunityDeclarationPref) {
                    GalleryPreferences.CTA.setRemindConnectNetworkEveryTime(((Boolean) obj).booleanValue());
                } else if (preference == this.mBackupOnlyInWifiPref) {
                    return onBackupOnlyWifiChange(((Boolean) obj).booleanValue());
                } else {
                    if (preference == this.mAutoDownloadPref) {
                        switchAutoDownload(((Boolean) obj).booleanValue());
                    } else if (preference == this.mDownloadTypePref) {
                        return onDownloadTypePreferenceChange(getDownloadTypeFrom((String) obj));
                    } else {
                        if (preference == this.mImageSelectionPref) {
                            setImageSelectionFunctionState(((Boolean) obj).booleanValue());
                            HashMap hashMap2 = new HashMap(1);
                            hashMap2.put("switchTo", obj.toString());
                            GallerySamplingStatHelper.recordCountEvent("settings", "settings_change_image_selection_switch", hashMap2);
                        } else if (preference == this.mStoryFuctionPref) {
                            setStoryFunctionState(((Boolean) obj).booleanValue());
                            HashMap hashMap3 = new HashMap(1);
                            hashMap3.put("switchTo", obj.toString());
                            GallerySamplingStatHelper.recordCountEvent("settings", "settings_change_story_generation_switch", hashMap3);
                        } else if (TextUtils.equals(preference.getKey(), "print_log")) {
                            BaseGalleryPreferences.Debug.printLog(((Boolean) obj).booleanValue());
                        }
                    }
                }
            } else {
                AIAlbumStatusHelper.setAIAlbumLocalStatus(activity, ((Boolean) obj).booleanValue());
                HashMap hashMap4 = new HashMap(1);
                hashMap4.put("switchTo", obj.toString());
                GallerySamplingStatHelper.recordCountEvent("settings", "settings_change_ai_album_switch", hashMap4);
            }
        }
        return true;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mSlideshowIntervalPref) {
            showSlideshowIntervalDialog();
            return true;
        } else if (preference == this.mTrashBinPref) {
            IntentUtil.gotoTrashBin(getActivity(), "GallerySettingsFragment");
            return true;
        } else if (preference == this.mAutoBackPref) {
            if (ContentResolver.getMasterSyncAutomatically()) {
                return true;
            }
            showSyncSettingDialog();
            return true;
        } else if (TextUtils.equals(preference.getKey(), "debug_gallery")) {
            DebugUtil.generateDebugLog(getActivity());
            return true;
        } else if (TextUtils.equals(preference.getKey(), "correct_photo_time")) {
            DebugUtil.correctPhotoTime(getActivity());
            return true;
        } else if (TextUtils.equals(preference.getKey(), "delete_dup_medias")) {
            DebugUtil.deleteDupMedias(getActivity());
            return true;
        } else if (TextUtils.equals(preference.getKey(), "thumbnail_build_error")) {
            DebugUtil.clearThumbnailErrorLog(getActivity());
            return true;
        } else if (preference == this.mPrivacyPolicyPref) {
            IntentUtil.gotoPrivacyPolicy(getActivity());
            return true;
        } else if (preference == this.mShowHiddenAlbumPref) {
            IntentUtil.gotoHiddenAlbumPage(getActivity());
            return true;
        } else if (preference == this.mCloudAlbumListPref) {
            IntentUtil.gotoCloudAlbumListPage(getActivity());
            return true;
        } else if (preference != this.mCloudPrivacyPref) {
            return true;
        } else {
            IntentUtil.gotoCloudPrivacy(getActivity());
            return true;
        }
    }

    public void onResume() {
        boolean z;
        GallerySettingsFragment.super.onResume();
        GallerySamplingStatHelper.recordPageStart(getActivity(), "settings");
        boolean z2 = true;
        if (this.mObserverHandle == null) {
            sSyncStatusObserverWrapper.setSyncStatusObserverWorker(this.mSyncStatusObserver);
            this.mObserverHandle = ContentResolver.addStatusChangeListener(1, sSyncStatusObserverWrapper);
        }
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(getActivity());
        if (xiaomiAccount == null) {
            this.mMiCloudCategory.removeAll();
            this.mMiCloudCategory.addPreference(this.mAutoBackPref);
            if (!onlyCloud()) {
                this.mBrowseCategory.removePreference(this.mShowLocalAlbumOnlyPref);
            }
        } else {
            if (!onlyCloud()) {
                if (this.mBrowseCategory.findPreference("show_local_album_only") == null) {
                    this.mBrowseCategory.addPreference(this.mShowLocalAlbumOnlyPref);
                }
                this.mShowLocalAlbumOnlyPref.setChecked(GalleryPreferences.LocalMode.isOnlyShowLocalPhoto());
            }
            if (this.mAIAlbumStatusPref != null) {
                if (this.mMiCloudCategory.findPreference("cloud_ai_album_status") == null) {
                    this.mMiCloudCategory.addPreference(this.mAIAlbumStatusPref);
                }
                this.mAIAlbumStatusPref.setChecked(getAIAlbumStatus());
            } else if (this.mFaceLocalStatusPref != null) {
                if (this.mMiCloudCategory.findPreference("cloud_face_local_status") == null) {
                    this.mMiCloudCategory.addPreference(this.mFaceLocalStatusPref);
                }
                this.mFaceLocalStatusPref.setChecked(getFaceAlbumStatus());
            } else {
                z = false;
                if (z && this.mAIAlbumStatusObserver == null) {
                    this.mAIAlbumStatusObserver = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            GallerySettingsFragment.this.updateAIAlbumStatus();
                        }
                    };
                    AIAlbumStatusHelper.registerAIAlbumStatusReceiver(getActivity(), this.mAIAlbumStatusObserver);
                }
            }
            z = true;
            this.mAIAlbumStatusObserver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    GallerySettingsFragment.this.updateAIAlbumStatus();
                }
            };
            AIAlbumStatusHelper.registerAIAlbumStatusReceiver(getActivity(), this.mAIAlbumStatusObserver);
        }
        CheckBoxPreference checkBoxPreference = this.mAutoBackPref;
        if (!ContentResolver.getMasterSyncAutomatically() || !ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider")) {
            z2 = false;
        }
        checkBoxPreference.setChecked(z2);
        CheckBoxPreference checkBoxPreference2 = this.mImpunityDeclarationPref;
        if (checkBoxPreference2 != null) {
            checkBoxPreference2.setChecked(GalleryPreferences.CTA.remindConnectNetworkEveryTime());
        }
        CheckBoxPreference checkBoxPreference3 = this.mImageSelectionPref;
        if (checkBoxPreference3 != null) {
            checkBoxPreference3.setChecked(GalleryPreferences.Assistant.isImageSelectionFunctionOn());
        }
        CheckBoxPreference checkBoxPreference4 = this.mStoryFuctionPref;
        if (checkBoxPreference4 != null) {
            checkBoxPreference4.setChecked(GalleryPreferences.Assistant.isStoryFunctionOn());
        }
    }
}
