package com.miui.gallery.activity.facebaby;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.BabyLockWallpaperDataManager;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.keyguard.LockScreenHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import miui.preference.PreferenceActivity;

public class BabyLockWallpaperSettingActivity extends PreferenceActivity {
    /* access modifiers changed from: private */
    public Map<String, Boolean> mNewStates = Maps.newHashMap();
    private Map<String, Boolean> mOldStates = Maps.newHashMap();
    private PreferenceGroup mPreferenceGroup;
    /* access modifiers changed from: private */
    public Set<String> mSelectedAlbums = Sets.newHashSet();

    /* access modifiers changed from: private */
    public boolean isBabyLockWallpaperInUse() {
        return "com.miui.gallery.cloud.baby.wallpaper_provider".equals(LockScreenHelper.getLockWallpaperProvider(getContentResolver()));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    private void onExit() {
        boolean z;
        int i;
        int i2;
        DialogInterface.OnClickListener onClickListener;
        Iterator<Map.Entry<String, Boolean>> it = this.mNewStates.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Map.Entry next = it.next();
            if (next.getValue() != null && !((Boolean) next.getValue()).equals(this.mOldStates.get(next.getKey()))) {
                z = true;
                break;
            }
        }
        if (!z) {
            finish();
            return;
        }
        if (this.mSelectedAlbums.isEmpty()) {
            onClickListener = new DialogInterface.OnClickListener() {
                /* JADX WARNING: type inference failed for: r1v4, types: [android.content.Context, com.miui.gallery.activity.facebaby.BabyLockWallpaperSettingActivity] */
                public void onClick(DialogInterface dialogInterface, int i) {
                    BabyLockWallpaperSettingActivity.this.saveState();
                    LockScreenHelper.setLockWallpaperProvider(BabyLockWallpaperSettingActivity.this.getContentResolver(), (String) null);
                    ToastUtils.makeText((Context) BabyLockWallpaperSettingActivity.this, (int) R.string.baby_lock_wallpaper_cancelled);
                    BabyLockWallpaperSettingActivity.this.finish();
                }
            };
            i2 = R.string.cancel_baby_lock_wallpaper_confirm_msg;
            i = 17039370;
        } else {
            onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BabyLockWallpaperSettingActivity.this.saveState();
                    LockScreenHelper.setLockWallpaperProvider(BabyLockWallpaperSettingActivity.this.getContentResolver(), "com.miui.gallery.cloud.baby.wallpaper_provider");
                    BabyLockWallpaperDataManager.getInstance().refresh();
                    BabyLockWallpaperSettingActivity.this.finish();
                }
            };
            i2 = R.string.baby_lock_wallpaper_save_settings_msg;
            i = R.string.apply;
        }
        BabyLockWallpaperDataManager.getInstance().refresh();
        DialogUtil.showConfirmAlertWithCancel(this, onClickListener, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BabyLockWallpaperSettingActivity.this.finish();
            }
        }, (String) null, getString(i2), getString(i), 17039360);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.miui.gallery.activity.facebaby.BabyLockWallpaperSettingActivity] */
    private void refresh() {
        boolean z;
        ArrayList<BabyLockWallpaperDataManager.BabyAlbumInfo> allBabyAlbums = BabyLockWallpaperDataManager.getInstance().getAllBabyAlbums();
        this.mPreferenceGroup.removeAll();
        this.mSelectedAlbums.clear();
        if (allBabyAlbums != null) {
            for (BabyLockWallpaperDataManager.BabyAlbumInfo next : allBabyAlbums) {
                final String createPathSuffix = GalleryPreferences.BabyLock.createPathSuffix(next.localId, next.isOtherShared);
                if (this.mNewStates.containsKey(createPathSuffix)) {
                    z = this.mNewStates.get(createPathSuffix).booleanValue();
                } else {
                    z = GalleryPreferences.BabyLock.isBabyAlbumForLockWallpaper(createPathSuffix);
                    this.mOldStates.put(createPathSuffix, Boolean.valueOf(z));
                }
                if (z) {
                    this.mSelectedAlbums.add(createPathSuffix);
                }
                this.mPreferenceGroup.addPreference(new BabyAlbumPreference(this, next, new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        BabyLockWallpaperSettingActivity.this.mNewStates.put(createPathSuffix, Boolean.valueOf(z));
                        if (z) {
                            BabyLockWallpaperSettingActivity.this.mSelectedAlbums.add(createPathSuffix);
                        } else {
                            BabyLockWallpaperSettingActivity.this.mSelectedAlbums.remove(createPathSuffix);
                        }
                    }
                }, z));
            }
        }
    }

    /* access modifiers changed from: private */
    public void saveState() {
        for (Map.Entry next : this.mNewStates.entrySet()) {
            GalleryPreferences.BabyLock.setBabyAlbumForLockWallpaper((String) next.getKey(), ((Boolean) next.getValue()).booleanValue());
        }
    }

    public void onBackPressed() {
        onExit();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        BabyLockWallpaperSettingActivity.super.onCreate(bundle);
        setContentView(R.layout.baby_lock_wallpaper_settings);
        addPreferencesFromResource(R.xml.baby_lock_wallpaper_preferences);
        findViewById(R.id.apply).setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r2v10, types: [android.content.Context, com.miui.gallery.activity.facebaby.BabyLockWallpaperSettingActivity] */
            public void onClick(View view) {
                boolean z = !BabyLockWallpaperSettingActivity.this.mSelectedAlbums.isEmpty();
                BabyLockWallpaperSettingActivity.this.saveState();
                if (z) {
                    LockScreenHelper.setLockWallpaperProvider(BabyLockWallpaperSettingActivity.this.getContentResolver(), "com.miui.gallery.cloud.baby.wallpaper_provider");
                    BabyLockWallpaperDataManager.getInstance().refresh();
                } else if (BabyLockWallpaperSettingActivity.this.isBabyLockWallpaperInUse()) {
                    LockScreenHelper.setLockWallpaperProvider(BabyLockWallpaperSettingActivity.this.getContentResolver(), (String) null);
                    ToastUtils.makeText((Context) BabyLockWallpaperSettingActivity.this, (int) R.string.baby_lock_wallpaper_cancelled);
                }
                BabyLockWallpaperSettingActivity.this.finish();
            }
        });
        this.mPreferenceGroup = (PreferenceGroup) findPreference("baby_lock_wallpaper_preference");
        if (!isBabyLockWallpaperInUse()) {
            GalleryPreferences.BabyLock.cleanBabyLockWallpaperAlbumState();
        }
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("album_checked_by_default");
            if (!TextUtils.isEmpty(stringExtra)) {
                boolean booleanExtra = getIntent().getBooleanExtra("is_other_shared", false);
                long parseLong = Long.parseLong(stringExtra);
                boolean isBabyAlbumForLockWallpaper = GalleryPreferences.BabyLock.isBabyAlbumForLockWallpaper(parseLong, booleanExtra);
                Log.i("faceface", "albumid;" + parseLong + " is share:" + booleanExtra);
                if (!isBabyAlbumForLockWallpaper) {
                    Log.i("faceface", "chect albumid;" + parseLong + " is share:" + booleanExtra);
                    this.mNewStates.put(GalleryPreferences.BabyLock.createPathSuffix(parseLong, booleanExtra), true);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        BabyLockWallpaperSettingActivity.super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return BabyLockWallpaperSettingActivity.super.onOptionsItemSelected(menuItem);
        }
        onExit();
        return true;
    }

    public void onPause() {
        BabyLockWallpaperSettingActivity.super.onPause();
        ImageLoader.getInstance().pause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        BabyLockWallpaperSettingActivity.super.onResume();
        refresh();
        ImageLoader.getInstance().resume();
    }
}
