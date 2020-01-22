package com.miui.gallery.activity;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.activity.HomePageImmersionMenuHelper;
import com.miui.gallery.activity.HomePageStartupHelper;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.card.ui.cardlist.AssistantPageFragment;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.DocumentProviderPreference;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.reddot.DisplayStatusManager;
import com.miui.gallery.scanner.MediaScannerReceiver;
import com.miui.gallery.scanner.MediaScannerUtil;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.AlbumPageFragment;
import com.miui.gallery.ui.BaseFragment;
import com.miui.gallery.ui.HomePageFragment;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.OnAppFocusedListener;
import miui.app.ActionBar;
import miui.view.ViewPager;

public class HomePageActivity extends BaseActivity implements DefaultLifecycleObserver, HomePageStartupHelper.Attacher {
    /* access modifiers changed from: private */
    public String MT_CAMERA_PACKAGE_NAME = "com.mlab.cam";
    /* access modifiers changed from: private */
    public String MT_CAMERA_SERVICE_CLASS_NAME = "com.mtlab.service.CameraRemoteService";
    /* access modifiers changed from: private */
    public int mCurrentPagePosition = 0;
    /* access modifiers changed from: private */
    public boolean mFirstTime = true;
    /* access modifiers changed from: private */
    public int mFragmentPagerScrollState = 0;
    private HomePageStartupHelper mHomePageStartupHelper;
    /* access modifiers changed from: private */
    public HomePageImmersionMenuHelper mImmersionMenuHelper;
    private boolean mIsInStartup = false;
    private boolean mIsProcessFirstResumed = true;
    /* access modifiers changed from: private */
    public ServiceConnection mMTCameraServiceConnection;
    private MediaScannerReceiver mMediaScannerReceiver;
    private HomePageImmersionMenuHelper.MenuItemsCheckListener mMenuItemsCheckListener = new HomePageImmersionMenuHelper.MenuItemsCheckListener() {
        public void onMenuItemsChecked(boolean z) {
            View customView = HomePageActivity.this.mActionBar.getCustomView();
            if (customView != null) {
                customView.findViewById(R.id.menu_update_view).setVisibility(z ? 0 : 4);
            }
        }
    };
    /* access modifiers changed from: private */
    public ViewPager mPager;
    private int mStartUpPage;

    private class CallbackWrapper implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public CallbackWrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (HomePageActivity.this.mPager != null) {
                HomePageActivity.this.mPager.setDraggable(true);
            }
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (HomePageActivity.this.mFirstTime) {
                ViewPager findViewById = HomePageActivity.this.getWindow().findViewById(miui.R.id.view_pager);
                if (findViewById instanceof ViewPager) {
                    ViewPager unused = HomePageActivity.this.mPager = findViewById;
                    HomePageActivity.this.mPager.setDraggable(false);
                }
                boolean unused2 = HomePageActivity.this.mFirstTime = false;
            }
            if (HomePageActivity.this.mPager != null) {
                HomePageActivity.this.mPager.setDraggable(false);
            }
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    private static class MediaScanJob implements ThreadPool.Job<Void> {
        private MediaScanJob() {
        }

        public Void run(ThreadPool.JobContext jobContext) {
            Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
            MediaScannerUtil.scanMediaProvider(sGetAndroidContext);
            MediaScannerUtil.scanAllAlbumDirectories(sGetAndroidContext, 5);
            MediaScannerUtil.cleanup(sGetAndroidContext);
            return null;
        }
    }

    private void bindMTCameraRemoteService() {
        if (Build.DEVICE.equalsIgnoreCase("vela")) {
            ThreadManager.getWorkHandler().post(new Runnable() {
                public void run() {
                    if (HomePageActivity.this.mMTCameraServiceConnection == null) {
                        ServiceConnection unused = HomePageActivity.this.mMTCameraServiceConnection = new ServiceConnection() {
                            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                Log.d("HomePageActivity", "CameraRemoteService connected");
                            }

                            public void onServiceDisconnected(ComponentName componentName) {
                                Log.d("HomePageActivity", "CameraRemoteService disconnected");
                            }
                        };
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(HomePageActivity.this.MT_CAMERA_PACKAGE_NAME, HomePageActivity.this.MT_CAMERA_SERVICE_CLASS_NAME));
                        try {
                            HomePageActivity.this.bindService(intent, HomePageActivity.this.mMTCameraServiceConnection, 5);
                            Log.d("HomePageActivity", "bind CameraRemoteService");
                        } catch (Exception e) {
                            ServiceConnection unused2 = HomePageActivity.this.mMTCameraServiceConnection = null;
                            Log.e("HomePageActivity", "bind CameraRemoteService failed", (Object) e);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String getPageName(int i) {
        if (i == 0) {
            return "home";
        }
        if (i == 1) {
            return "album";
        }
        if (i == 2) {
            return "assistant";
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void markAlbumPageVisible() {
        Fragment fragmentAt;
        if (1 < this.mActionBar.getFragmentTabCount() && (fragmentAt = this.mActionBar.getFragmentAt(1)) != null && (fragmentAt instanceof AlbumPageFragment)) {
            fragmentAt.setUserVisibleHint(true);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.miui.gallery.activity.HomePageActivity] */
    private void registerMediaScannerReceiver() {
        if (this.mMediaScannerReceiver == null) {
            this.mMediaScannerReceiver = new MediaScannerReceiver();
        }
        MediaScannerReceiver.register(this, this.mMediaScannerReceiver);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.miui.gallery.activity.HomePageActivity] */
    private void setupActionBar() {
        this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager(), false);
        this.mActionBar.setCustomView(R.layout.action_bar_more);
        View customView = this.mActionBar.getCustomView();
        this.mImmersionMenuHelper = new HomePageImmersionMenuHelper(this);
        this.mImmersionMenuHelper.registerMenuItemsCheckListener(this.mMenuItemsCheckListener);
        this.mImmersionMenuHelper.checkRedDotFeature();
        customView.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragmentAt = HomePageActivity.this.mActionBar.getFragmentAt(HomePageActivity.this.mCurrentPagePosition);
                HomePageActivity.this.mImmersionMenuHelper.showImmersionMenu(view, fragmentAt instanceof BaseFragment ? ((BaseFragment) fragmentAt).getPageName() : String.valueOf(HomePageActivity.this.mCurrentPagePosition));
            }
        });
        updateActionBarMore(this.mCurrentPagePosition);
    }

    private void setupHomePageFragment() {
        this.mActionBar.addFragmentTab("HomePageFragment", this.mActionBar.newTab().setText(R.string.home_page_label), HomePageFragment.class, (Bundle) null, false);
        this.mActionBar.addFragmentTab("AlbumStub", this.mActionBar.newTab().setText(R.string.album_page_label), Fragment.class, (Bundle) null, false);
        if (ImageFeatureManager.isDeviceSupportStoryFunction()) {
            this.mActionBar.addFragmentTab("StoryStub", this.mActionBar.newTab().setText(R.string.assistant_page_label), Fragment.class, (Bundle) null, false);
        }
        this.mActionBar.addOnFragmentViewPagerChangeListener(new ActionBar.FragmentViewPagerChangeListener() {
            private boolean mIsPendingSelectDispatched = false;

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    this.mIsPendingSelectDispatched = false;
                }
                int unused = HomePageActivity.this.mFragmentPagerScrollState = i;
                HomePageActivity.this.setupOtherFragments();
            }

            public void onPageScrolled(int i, float f, boolean z, boolean z2) {
                if (!this.mIsPendingSelectDispatched) {
                    if (i == HomePageActivity.this.mCurrentPagePosition && f >= 0.15f) {
                        int i2 = i + 1;
                        Log.d("HomePageActivity", "pending select page: %d, curr ratio: %f", Integer.valueOf(i2), Float.valueOf(f));
                        if (i2 == 1) {
                            HomePageActivity.this.markAlbumPageVisible();
                        }
                        this.mIsPendingSelectDispatched = true;
                    }
                    if (i == HomePageActivity.this.mCurrentPagePosition - 1 && f <= 0.8f) {
                        Log.d("HomePageActivity", "pending select page: %d, curr ratio: %f", Integer.valueOf(i), Float.valueOf(f));
                        if (i == 1) {
                            HomePageActivity.this.markAlbumPageVisible();
                        }
                        this.mIsPendingSelectDispatched = true;
                    }
                }
            }

            /* JADX WARNING: type inference failed for: r0v8, types: [android.app.Activity, com.miui.gallery.activity.HomePageActivity] */
            /* JADX WARNING: type inference failed for: r0v9, types: [android.app.Activity, com.miui.gallery.activity.HomePageActivity] */
            public void onPageSelected(int i) {
                Log.d("HomePageActivity", "onPageSelected %d", (Object) Integer.valueOf(i));
                if (HomePageActivity.this.mCurrentPagePosition != i) {
                    ? r0 = HomePageActivity.this;
                    GallerySamplingStatHelper.recordPageEnd(r0, r0.getPageName(r0.mCurrentPagePosition));
                    ? r02 = HomePageActivity.this;
                    GallerySamplingStatHelper.recordPageStart(r02, r02.getPageName(i));
                }
                HomePageActivity.this.updateActionBarMore(i);
                if (i == 2) {
                    DisplayStatusManager.setRedDotClicked("assistant_tab");
                    DisplayStatusManager.setRedDotClicked("story_album");
                }
                int unused = HomePageActivity.this.mCurrentPagePosition = i;
            }
        });
    }

    /* access modifiers changed from: private */
    public void setupOtherFragments() {
        if (this.mIsInStartup && this.mFragmentPagerScrollState == 0) {
            this.mIsInStartup = false;
            int i = this.mCurrentPagePosition;
            this.mActionBar.addFragmentTab("AlbumPageFragment", this.mActionBar.newTab().setText(R.string.album_page_label), 1, AlbumPageFragment.class, (Bundle) null, false);
            this.mActionBar.removeFragmentTabAt(2);
            if (ImageFeatureManager.isDeviceSupportStoryFunction()) {
                this.mActionBar.addFragmentTab("AssistantPageFragment", this.mActionBar.newTab().setCustomView(R.layout.assistant_tab), 2, AssistantPageFragment.class, (Bundle) null, false);
                this.mActionBar.removeFragmentTabAt(3);
            }
            int i2 = this.mStartUpPage;
            if (i2 <= 0 || i2 >= this.mActionBar.getTabCount()) {
                this.mActionBar.selectTab(this.mActionBar.getTabAt(i));
            } else {
                this.mActionBar.selectTab(this.mActionBar.getTabAt(this.mStartUpPage));
            }
        }
    }

    private void unbindMTCameraRemoteService() {
        ThreadManager.getWorkHandler().post(new Runnable() {
            public void run() {
                if (HomePageActivity.this.mMTCameraServiceConnection != null) {
                    try {
                        HomePageActivity.this.unbindService(HomePageActivity.this.mMTCameraServiceConnection);
                        ServiceConnection unused = HomePageActivity.this.mMTCameraServiceConnection = null;
                        Log.d("HomePageActivity", "unbind CameraRemoteService");
                    } catch (Exception e) {
                        Log.e("HomePageActivity", "unbind CameraRemoteService failed", (Object) e);
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.miui.gallery.activity.HomePageActivity] */
    private void unregisterMediaScannerReceiver() {
        MediaScannerReceiver.unregister(this, this.mMediaScannerReceiver);
        this.mMediaScannerReceiver = null;
    }

    /* access modifiers changed from: private */
    public void updateActionBarMore(int i) {
        if (ImageFeatureManager.isDeviceSupportStoryFunction() && this.mActionBar.getTabAt(2).getCustomView() != null) {
            this.mActionBar.getTabAt(2).getCustomView().findViewById(R.id.assistant_tab_red_dot).setVisibility(DisplayStatusManager.getRedDotStatus("assistant_tab") ? 0 : 4);
        }
        HomePageImmersionMenuHelper homePageImmersionMenuHelper = this.mImmersionMenuHelper;
        if (homePageImmersionMenuHelper != null) {
            homePageImmersionMenuHelper.checkRedDotFeature();
        }
        if (i == 1) {
            this.mActionBar.setDisplayShowCustomEnabled(true);
        } else if (i == 0) {
            this.mActionBar.setDisplayShowCustomEnabled(true);
        } else {
            this.mActionBar.setDisplayShowCustomEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchAppFocused() {
        Fragment fragmentAt = this.mActionBar.getFragmentAt(this.mCurrentPagePosition);
        if (fragmentAt instanceof OnAppFocusedListener) {
            ((OnAppFocusedListener) fragmentAt).onAppFocused();
        }
    }

    public HomePageStartupHelper getStartupHelper() {
        return this.mHomePageStartupHelper;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isCheckPermissionCustomized() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context, com.miui.gallery.activity.HomePageStartupHelper$Attacher, com.miui.gallery.activity.HomePageActivity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.mHomePageStartupHelper = new HomePageStartupHelper(this, this);
        this.mHomePageStartupHelper.onActivityCreate();
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.mStartUpPage = intent.getIntExtra("extra_start_page", 0);
        }
        this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
        setupHomePageFragment();
        GalleryPreferences.CTA.onCreateOrDestroyHomePage();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, android.app.Activity, com.miui.gallery.activity.HomePageActivity] */
    /* access modifiers changed from: protected */
    public void onCtaChecked(boolean z) {
        if (DocumentProviderPreference.isFirstEntrance() && DocumentProviderUtils.needRequestExternalSDCardPermission(this)) {
            DocumentProviderUtils.startFirstEntrancyPermissionActivityForResult(this);
        }
    }

    public void onDestroy() {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
        super.onDestroy();
        unregisterMediaScannerReceiver();
        this.mHomePageStartupHelper.onActivityDestroy();
        HomePageImmersionMenuHelper homePageImmersionMenuHelper = this.mImmersionMenuHelper;
        if (homePageImmersionMenuHelper != null) {
            homePageImmersionMenuHelper.onActivityDestroy();
        }
        GalleryPreferences.CTA.onCreateOrDestroyHomePage();
        unbindMTCameraRemoteService();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity, com.miui.gallery.activity.HomePageActivity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        GallerySamplingStatHelper.recordPageEnd(this, getPageName(this.mCurrentPagePosition));
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        Log.d("HomePageActivity", "onPermissionsChecked");
        super.onPermissionsChecked(runtimePermissionArr, iArr);
        ThreadManager.getMiscPool().submit(new MediaScanJob());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity, com.miui.gallery.activity.HomePageActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        GallerySamplingStatHelper.recordPageStart(this, getPageName(this.mCurrentPagePosition));
        updateActionBarMore(this.mCurrentPagePosition);
    }

    public void onResume(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner == ProcessLifecycleOwner.get() && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.d("HomePageActivity", "onProcessResumed");
            dispatchAppFocused();
            if (this.mIsProcessFirstResumed) {
                this.mIsProcessFirstResumed = false;
            } else {
                ThreadManager.getMiscPool().submit(new MediaScanJob());
            }
        }
    }

    public void onStartup() {
        this.mIsInStartup = true;
        setupOtherFragments();
        setupActionBar();
        registerMediaScannerReceiver();
        bindMTCameraRemoteService();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        checkPermission();
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(new CallbackWrapper(callback));
    }

    /* access modifiers changed from: protected */
    public boolean supportEnterSetting() {
        return true;
    }
}
