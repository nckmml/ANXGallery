package com.miui.gallery.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.cloudcontrol.observers.FeatureStatusObserver;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.reddot.DisplayStatusManager;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.MovieLibraryLoaderHelper;
import com.miui.gallery.util.PhotoMovieEntranceUtils;
import com.miui.gallery.util.PrintInstallHelper;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.widget.menu.ImmersionMenu;
import com.miui.gallery.widget.menu.ImmersionMenuItem;
import com.miui.gallery.widget.menu.ImmersionMenuListener;
import com.miui.gallery.widget.menu.PhoneImmersionMenu;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomePageImmersionMenuHelper implements ImmersionMenuListener {
    /* access modifiers changed from: private */
    public Context mContext;
    private String mCurrentPage;
    private Map<Integer, String> mFeatureItemsMap = new HashMap();
    private boolean mHasRedDotItem;
    private PrintInstallHelper.InstallStateListener mInstallStateListener;
    private MenuItemsCheckListener mMenuItemsCheckListener;
    private MovieLibraryLoaderHelper.DownloadStateListener mMovieDownloadStateListener;
    private PhoneImmersionMenu mPhoneImmersionMenu;
    private PrintStatusObserver mPrintStatusObserver;

    public interface MenuItemsCheckListener {
        void onMenuItemsChecked(boolean z);
    }

    private class PrintStatusObserver extends FeatureStatusObserver {
        private PrintStatusObserver() {
        }

        public void onStatusChanged(String str, FeatureProfile.Status status) {
            if ("photo-print".equals(str)) {
                HomePageImmersionMenuHelper.this.onPrintStatueChanged(status);
            }
        }
    }

    public HomePageImmersionMenuHelper(Context context) {
        this.mContext = context;
        this.mPhoneImmersionMenu = new PhoneImmersionMenu(context, this);
        registerPrintStatusObserver();
    }

    private boolean isTrashBinEnable() {
        return ApplicationHelper.isCloudTrashBinFeatureOpen() && SyncUtil.existXiaomiAccount(this.mContext);
    }

    /* access modifiers changed from: private */
    public void onPrintStatueChanged(FeatureProfile.Status status) {
        if (PrintInstallHelper.getInstance().isPhotoPrintEnable()) {
            DisplayStatusManager.regenerateRedDotMap();
            setMenuVisibility((int) R.id.menu_photo_print, true);
            GallerySamplingStatHelper.recordStringPropertyEvent("photo_print", "photo_print_enable", "true");
        }
    }

    private void registerFeature(int i, String str) {
        this.mFeatureItemsMap.put(Integer.valueOf(i), str);
    }

    private void registerPrintStatusObserver() {
        this.mPrintStatusObserver = new PrintStatusObserver();
        onPrintStatueChanged(CloudControlManager.getInstance().registerStatusObserver("photo-print", this.mPrintStatusObserver));
    }

    private boolean setMenuVisibility(int i, boolean z) {
        ImmersionMenu immersionMenu;
        PhoneImmersionMenu phoneImmersionMenu = this.mPhoneImmersionMenu;
        if (phoneImmersionMenu == null || (immersionMenu = phoneImmersionMenu.getImmersionMenu()) == null) {
            return false;
        }
        return setMenuVisibility(immersionMenu.findItem(i), z);
    }

    private boolean setMenuVisibility(ImmersionMenuItem immersionMenuItem, boolean z) {
        if (immersionMenuItem == null || immersionMenuItem.isVisible() == z) {
            return false;
        }
        immersionMenuItem.setVisible(z);
        checkRedDotFeature();
        return true;
    }

    private void unregisterPrintStatusObserver() {
        if (this.mPrintStatusObserver != null) {
            CloudControlManager.getInstance().unregisterStatusObserver(this.mPrintStatusObserver);
        }
    }

    public void checkRedDotFeature() {
        ImmersionMenu immersionMenu;
        PhoneImmersionMenu phoneImmersionMenu = this.mPhoneImmersionMenu;
        if (phoneImmersionMenu != null && (immersionMenu = phoneImmersionMenu.getImmersionMenu()) != null) {
            this.mHasRedDotItem = false;
            for (Map.Entry next : this.mFeatureItemsMap.entrySet()) {
                ImmersionMenuItem findItem = immersionMenu.findItem(((Integer) next.getKey()).intValue());
                boolean z = DisplayStatusManager.getRedDotStatus((String) next.getValue()) && findItem.isVisible();
                this.mHasRedDotItem |= z;
                findItem.setIsRedDotDisplayed(z);
            }
            MenuItemsCheckListener menuItemsCheckListener = this.mMenuItemsCheckListener;
            if (menuItemsCheckListener != null) {
                menuItemsCheckListener.onMenuItemsChecked(this.mHasRedDotItem);
            }
            updateImmersionMenu(immersionMenu);
        }
    }

    public void onActivityDestroy() {
        PhoneImmersionMenu phoneImmersionMenu = this.mPhoneImmersionMenu;
        if (phoneImmersionMenu != null && phoneImmersionMenu.isShowing()) {
            this.mPhoneImmersionMenu.dismiss();
        }
        PrintInstallHelper.getInstance().removeInstallStateListener(this.mInstallStateListener);
        MovieLibraryLoaderHelper.getInstance().removeDownloadStateListener(this.mMovieDownloadStateListener);
        unregisterPrintStatusObserver();
    }

    public void onCreateImmersionMenu(final ImmersionMenu immersionMenu) {
        if (this.mContext != null) {
            if (!ImageFeatureManager.isDeviceSupportStoryFunction()) {
                immersionMenu.add(R.id.menu_collage, this.mContext.getString(R.string.home_menu_collage)).setIconResource(R.drawable.home_menu_collage);
                registerFeature(R.id.menu_collage, "collage");
                if (PhotoMovieEntranceUtils.isPhotoMovieAvailable()) {
                    final ImmersionMenuItem iconResource = immersionMenu.add(R.id.menu_photo_movie, this.mContext.getString(R.string.home_menu_photo_movie)).setIconResource(R.drawable.home_menu_photo_movie);
                    registerFeature(R.id.menu_photo_movie, "photo_movie");
                    this.mMovieDownloadStateListener = new MovieLibraryLoaderHelper.DownloadStateListener() {
                        public void onDownloading() {
                            iconResource.setRemainWhenClick(true);
                            iconResource.setInformation((int) R.string.photo_movie_menu_loading);
                            HomePageImmersionMenuHelper.this.updateImmersionMenu(immersionMenu);
                        }

                        public void onFinish(boolean z, int i) {
                            iconResource.setRemainWhenClick(false);
                            iconResource.setInformation((CharSequence) null);
                            HomePageImmersionMenuHelper.this.updateImmersionMenu(immersionMenu);
                        }
                    };
                    MovieLibraryLoaderHelper.getInstance().addDownloadStateListener(this.mMovieDownloadStateListener);
                }
            }
            immersionMenu.add(R.id.menu_cleaner, this.mContext.getString(R.string.home_menu_cleaner)).setIconResource(R.drawable.home_menu_cleaner);
            registerFeature(R.id.menu_cleaner, "photo_cleaner");
            ImmersionMenuItem iconResource2 = immersionMenu.add(R.id.trash_bin, this.mContext.getString(R.string.trash_bin)).setIconResource(R.drawable.home_menu_trash_bin);
            registerFeature(R.id.trash_bin, "trash_bin");
            setMenuVisibility(iconResource2, isTrashBinEnable());
            final ImmersionMenuItem iconResource3 = immersionMenu.add(R.id.menu_photo_print, this.mContext.getString(R.string.home_menu_print)).setIconResource(R.drawable.home_menu_print);
            registerFeature(R.id.menu_photo_print, "photo_print");
            iconResource3.setVisible(PrintInstallHelper.getInstance().isPhotoPrintEnable());
            this.mInstallStateListener = new PrintInstallHelper.InstallStateListener() {
                public void onFinish(boolean z, int i, int i2) {
                    if (z) {
                        ToastUtils.makeText(HomePageImmersionMenuHelper.this.mContext, (int) R.string.photo_print_package_finish);
                    } else {
                        int failReasonMsg = PrintInstallHelper.getFailReasonMsg(i, i2);
                        if (failReasonMsg != 0) {
                            ToastUtils.makeText(HomePageImmersionMenuHelper.this.mContext, failReasonMsg);
                        }
                    }
                    iconResource3.setRemainWhenClick(false);
                    iconResource3.setInformation((CharSequence) null);
                    HomePageImmersionMenuHelper.this.updateImmersionMenu(immersionMenu);
                }

                public void onInstallLimited() {
                    ToastUtils.makeText(HomePageImmersionMenuHelper.this.mContext, (int) R.string.try_again_later);
                }

                public void onInstalling() {
                    iconResource3.setRemainWhenClick(true);
                    iconResource3.setInformation((int) R.string.photo_print_menu_loading);
                    HomePageImmersionMenuHelper.this.updateImmersionMenu(immersionMenu);
                }
            };
            PrintInstallHelper.getInstance().addInstallStateListener(this.mInstallStateListener);
            immersionMenu.add(R.id.menu_settings, this.mContext.getString(R.string.gallery_setting)).setIconResource(R.drawable.home_menu_settings);
            registerFeature(R.id.menu_settings, "settings");
        }
    }

    public void onImmersionMenuSelected(ImmersionMenu immersionMenu, ImmersionMenuItem immersionMenuItem) {
        if (this.mContext != null) {
            int itemId = immersionMenuItem.getItemId();
            String str = this.mFeatureItemsMap.get(Integer.valueOf(itemId));
            if (str != null && !str.isEmpty()) {
                DisplayStatusManager.setRedDotClicked(str);
                HashMap hashMap = new HashMap();
                hashMap.put(String.format(Locale.US, "%s_redDotDisplayed", new Object[]{this.mCurrentPage}), Boolean.valueOf(this.mHasRedDotItem));
                GallerySamplingStatHelper.recordCountEvent("feature_red_dot", String.format(Locale.US, "%s_%s", new Object[]{"click_menu", str, hashMap}));
            }
            checkRedDotFeature();
            switch (itemId) {
                case R.id.menu_cleaner:
                    ActionURIHandler.handleUri((Activity) this.mContext, GalleryContract.Common.URI_CLEANER_PAGE);
                    GallerySamplingStatHelper.recordCountEvent("home", "home_page_menu_cleaner");
                    return;
                case R.id.menu_collage:
                    ActionURIHandler.handleUri((Activity) this.mContext, GalleryContract.Common.URI_COLLAGE_PAGE);
                    GallerySamplingStatHelper.recordCountEvent("home", "home_page_menu_collage");
                    return;
                case R.id.menu_photo_movie:
                    if (MovieLibraryLoaderHelper.getInstance().checkAbleOrDownload((Activity) this.mContext)) {
                        ActionURIHandler.handleUri((Activity) this.mContext, GalleryContract.Common.URI_PHOTO_MOVIE);
                    }
                    GallerySamplingStatHelper.recordCountEvent("home", "home_page_menu_photo_movie");
                    return;
                case R.id.menu_photo_print:
                    PrintInstallHelper.getInstance().start(this.mContext);
                    GallerySamplingStatHelper.recordCountEvent("home", "home_page_menu_photo_print");
                    return;
                case R.id.menu_settings:
                    IntentUtil.enterGallerySetting(this.mContext);
                    return;
                case R.id.trash_bin:
                    IntentUtil.gotoTrashBin(this.mContext, "HomePageActivity");
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onPrepareImmersionMenu(ImmersionMenu immersionMenu) {
        if (this.mContext == null) {
            return false;
        }
        boolean menuVisibility = setMenuVisibility(immersionMenu.findItem(R.id.trash_bin), isTrashBinEnable());
        boolean isPhotoPrintEnable = PrintInstallHelper.getInstance().isPhotoPrintEnable();
        boolean menuVisibility2 = setMenuVisibility(immersionMenu.findItem(R.id.menu_photo_print), isPhotoPrintEnable) | menuVisibility;
        if (isPhotoPrintEnable) {
            GallerySamplingStatHelper.recordStringPropertyEvent("photo_print", "photo_print_menu_displayed", "true");
        }
        DisplayStatusManager.setRedDotClicked("action_bar_more");
        return menuVisibility2;
    }

    public void registerMenuItemsCheckListener(MenuItemsCheckListener menuItemsCheckListener) {
        this.mMenuItemsCheckListener = menuItemsCheckListener;
    }

    public void showImmersionMenu(View view, String str) {
        PhoneImmersionMenu phoneImmersionMenu = this.mPhoneImmersionMenu;
        if (phoneImmersionMenu != null) {
            phoneImmersionMenu.show(view, (ViewGroup) null);
        }
        this.mCurrentPage = str;
        HashMap hashMap = new HashMap();
        hashMap.put(String.format(Locale.US, "%s_redDotDisplayed", new Object[]{this.mCurrentPage}), Boolean.valueOf(this.mHasRedDotItem));
        GallerySamplingStatHelper.recordCountEvent("feature_red_dot", "show_immersion_menu", hashMap);
    }

    public void updateImmersionMenu(ImmersionMenu immersionMenu) {
        PhoneImmersionMenu phoneImmersionMenu = this.mPhoneImmersionMenu;
        if (phoneImmersionMenu != null) {
            phoneImmersionMenu.update(immersionMenu);
        }
    }
}
