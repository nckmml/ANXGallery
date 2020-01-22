package com.miui.gallery.editor.photo.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.miui.arcsoftbeauty.ArcsoftBeautyJni;
import com.miui.filtersdk.BeautificationSDK;
import com.miui.gallery.R;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.compat.app.ActivityCompat;
import com.miui.gallery.compat.transition.TransitionCompat;
import com.miui.gallery.compat.view.WindowCompat;
import com.miui.gallery.editor.photo.app.AbstractNaviFragment;
import com.miui.gallery.editor.photo.app.AlertDialogFragment;
import com.miui.gallery.editor.photo.app.DraftManager;
import com.miui.gallery.editor.photo.app.ExportFragment;
import com.miui.gallery.editor.photo.app.InitializeController;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.PhotoEditor;
import com.miui.gallery.editor.photo.app.PreviewFragment;
import com.miui.gallery.editor.photo.app.SimpleSelectDialog;
import com.miui.gallery.editor.photo.app.adjust.AdjustMenuFragment;
import com.miui.gallery.editor.photo.app.beautify.BeautifyFragment;
import com.miui.gallery.editor.photo.app.crop.CropMenuFragment;
import com.miui.gallery.editor.photo.app.doodle.DoodleMenuFragment;
import com.miui.gallery.editor.photo.app.filter.FilterFragment;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyCheckHelper;
import com.miui.gallery.editor.photo.app.frame.FrameMenuFragment;
import com.miui.gallery.editor.photo.app.longcrop.LongCropFragment;
import com.miui.gallery.editor.photo.app.miuibeautify.MiuiBeautyFragment;
import com.miui.gallery.editor.photo.app.mosaic.MosaicMenuFragment;
import com.miui.gallery.editor.photo.app.navigator.PhotoNaviFragment;
import com.miui.gallery.editor.photo.app.navigator.ScreenshotNaviFragment;
import com.miui.gallery.editor.photo.app.remover.Inpaint;
import com.miui.gallery.editor.photo.app.remover.RemoverMenuFragment;
import com.miui.gallery.editor.photo.app.sticker.StickerMenuFragment;
import com.miui.gallery.editor.photo.app.text.TextMenuFragment;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.GLFragment;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.RenderFragment;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.origin.EditorOriginHandler;
import com.miui.gallery.editor.photo.utils.Callback;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.PermissionInjector;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.DocumentProviderPreference;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.sdk.editor.Constants;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SystemUiUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.view.BrightnessManager;
import com.miui.privacy.LockSettingsHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miui.view.animation.CubicEaseInOutInterpolator;
import miui.view.animation.CubicEaseOutInterpolator;

public class PhotoEditor extends Activity implements PermissionCheckCallback {
    private ArrayList<Integer> mActivatedEffects;
    private Intent mActivityIntent;
    private int mActivityResult = 0;
    private AlertDialogFragment.Callbacks mAlertDialogCallbacks = new AlertDialogFragment.Callbacks() {
        public void onCancel(AlertDialogFragment alertDialogFragment) {
        }

        public void onClick(AlertDialogFragment alertDialogFragment, int i) {
            Log.d("PhotoEditor", "confirm dialog from %s, event is %d", alertDialogFragment == null ? "unknown" : alertDialogFragment.getTag(), Integer.valueOf(i));
            if ("main_alert_dialog".equals(alertDialogFragment.getTag())) {
                if (i == -1) {
                    PhotoEditor.this.mNavigatorCallbacks.onExport();
                } else if (i == -2) {
                    PhotoEditor.this.mNavigatorCallbacks.onDiscard();
                }
            } else if ("menu_alert_dialog".equals(alertDialogFragment.getTag())) {
                Fragment findFragmentById = PhotoEditor.this.mFragments.findFragmentById(R.id.menu_panel);
                if (!(findFragmentById instanceof MenuFragment)) {
                    return;
                }
                if (i == -1) {
                    PhotoEditor.this.mMenuCallback.onSave((MenuFragment) findFragmentById);
                } else if (i == -2) {
                    PhotoEditor.this.mMenuCallback.onDiscard((MenuFragment) findFragmentById);
                }
            }
        }

        public void onDismiss(AlertDialogFragment alertDialogFragment) {
        }
    };
    private BrightnessManager mBrightnessManager;
    /* access modifiers changed from: private */
    public long mCreateTime;
    private InitializeController.Callbacks mDecoderCallbacks = new InitializeController.Callbacks() {
        public int doInitialize() {
            SdkManager.INSTANCE.onAttach(PhotoEditor.this.getApplication());
            SdkManager.INSTANCE.onActivityCreate();
            PhotoEditor photoEditor = PhotoEditor.this;
            boolean unused = photoEditor.mIsShowSkyGuideView = photoEditor.isShowSkyGuideView();
            try {
                DraftManager access$200 = PhotoEditor.this.mDraftManager;
                return access$200 != null ? access$200.initializeForPreview(PhotoEditor.this.mEditorOriginHandler.isInMainProcess()) : false ? 3 : 2;
            } catch (FileNotFoundException e) {
                Log.w("PhotoEditor", (Throwable) e);
                return 1;
            } catch (SecurityException e2) {
                Log.w("PhotoEditor", (Throwable) e2);
                return 2;
            }
        }

        public void onDone() {
            Fragment findFragmentByTag = PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            PreviewFragment previewFragment = (PreviewFragment) findFragmentByTag;
            if (findFragmentByTag != null && findFragmentByTag.isAdded()) {
                previewFragment.setLoadDone(true);
                previewFragment.showForLoadDown();
                if (PhotoEditor.this.mDraftManager != null) {
                    PhotoEditor.this.mDraftManager.setOnPreviewRefreshListener(PhotoEditor.this.mOnPreviewRefreshListener);
                }
            }
            PhotoEditor.this.mTransitionConfig.onImageLoaded();
            if (PhotoEditor.this.isSingleEffectMode()) {
                PhotoEditor photoEditor = PhotoEditor.this;
                photoEditor.showSingleEffectDisplay(photoEditor.mSingleEffect);
            }
        }
    };
    /* access modifiers changed from: private */
    public DraftManager mDraftManager;
    /* access modifiers changed from: private */
    public EditorOriginHandler mEditorOriginHandler;
    private ExportFragment.Callbacks mExportCallbacks = new ExportFragment.Callbacks() {
        private long mStartTime;

        private void sampleExportTime(String str, long j) {
            PhotoEditor.this.mSampler.recordNumericEvent("_main", str, j / 100);
        }

        public boolean doExport() {
            DraftManager access$200 = PhotoEditor.this.mDraftManager;
            if (access$200 == null) {
                return false;
            }
            this.mStartTime = System.currentTimeMillis();
            PhotoEditor.this.mExportTask.prepareToExport(access$200);
            Log.d("PhotoEditor", "doExport start");
            boolean doExport = PhotoEditor.this.mEditorOriginHandler.doExport(access$200, PhotoEditor.this.mExportTask.getExportUri());
            Log.d("PhotoEditor", "doExport end, use time: %d", (Object) Long.valueOf(System.currentTimeMillis() - this.mStartTime));
            boolean onExport = PhotoEditor.this.mExportTask.onExport(access$200, doExport);
            sampleExportTime("compress_finished", System.currentTimeMillis() - this.mStartTime);
            return onExport;
        }

        public void onCancelled(boolean z) {
            PhotoEditor.this.mExportTask.onCancelled(z);
            sampleExportTime("compress_cancelled", System.currentTimeMillis() - this.mStartTime);
        }

        public void onExported(boolean z) {
            PhotoEditor.this.mExportTask.onPostExport(z);
            if (z) {
                if (!PhotoEditor.this.mExportTask.isExternalCall()) {
                    Log.d("PhotoEditor", "internal call, pass result");
                    Intent intent = new Intent();
                    intent.setDataAndType(GalleryOpenProvider.translateToContent(PhotoEditor.this.mExportTask.getExportUri().getPath()), "image/jpeg");
                    intent.putExtra("photo_secret_id", PhotoEditor.this.mExportTask.getSecretId());
                    PhotoEditor.this.setActivityResult(-1, intent);
                }
                if (PhotoEditor.this.isSingleEffectMode()) {
                    PhotoEditor photoEditor = PhotoEditor.this;
                    IntentUtil.gotoSinglePhotoPage(photoEditor, photoEditor.mExportTask.getExportUri());
                }
                PhotoEditor.this.onActivityFinish(true);
                ActivityCompat.finishAfterTransition(PhotoEditor.this);
                return;
            }
            PhotoEditor.this.mExportTask.closeExportDialog();
            ToastUtils.makeText((Context) PhotoEditor.this, (int) R.string.main_save_error_msg);
        }
    };
    /* access modifiers changed from: private */
    public ExportTask mExportTask;
    /* access modifiers changed from: private */
    public FragmentData[] mFragmentData = new FragmentData[Effect.values().length];
    /* access modifiers changed from: private */
    public FragmentManager mFragments;
    private PreviewFragment.OnViewReadyListener mGLMaskOutReadyListener = new PreviewFragment.OnViewReadyListener() {
        public void onViewReady() {
            MenuFragment access$2600 = PhotoEditor.this.findActiveMenu();
            if (access$2600 != null) {
                PhotoEditor.this.onExit(access$2600);
            } else {
                Log.i("PhotoEditor", "menu already exit.");
            }
        }
    };
    private GLFragment.GLContext.OnCreatedListener mGLReadyListener = new GLFragment.GLContext.OnCreatedListener() {
        public void onCreated() {
            Fragment findFragmentByTag = PhotoEditor.this.mFragments.findFragmentByTag("gl_mask_in");
            if (findFragmentByTag != null) {
                PhotoEditor.this.mFragments.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
                Log.d("PhotoEditor", "mGLReadyListener remove mask");
            }
        }
    };
    private InitializeController mInitializeController;
    /* access modifiers changed from: private */
    public boolean mIsShowSkyGuideView;
    /* access modifiers changed from: private */
    public MenuFragment.Callbacks mMenuCallback = new MenuFragment.Callbacks() {
        private void sample(String str, RenderFragment renderFragment, String str2) {
            List<String> sample = renderFragment.sample();
            HashMap hashMap = new HashMap();
            if (sample == null || sample.isEmpty()) {
                hashMap.put("effect", "*none*");
                PhotoEditor.this.mSampler.recordEvent(str, str2, hashMap);
                return;
            }
            for (String put : sample) {
                hashMap.put("effect", put);
                PhotoEditor.this.mSampler.recordEvent(str, str2, hashMap);
            }
        }

        public void onDiscard(MenuFragment menuFragment) {
            RenderFragment renderFragment;
            if (PhotoEditor.this.isSingleEffectMode()) {
                PhotoEditor.this.mNavigatorCallbacks.onDiscard();
                return;
            }
            Fragment findFragmentByTag = PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            if ((findFragmentByTag == null || !((PreviewFragment) findFragmentByTag).isRunningPreviewAnimator()) && (renderFragment = menuFragment.getRenderFragment()) != null && renderFragment.isAdded()) {
                sample(PhotoEditor.this.mSampleTags[menuFragment.mEffect.ordinal()], renderFragment, "discard_detail");
                PhotoEditor.this.onExit(menuFragment);
            }
        }

        public Bitmap onLoadOrigin() {
            if (PhotoEditor.this.mDraftManager == null) {
                return null;
            }
            return PhotoEditor.this.mDraftManager.decodeOrigin();
        }

        public Bitmap onLoadPreview() {
            if (PhotoEditor.this.mDraftManager == null) {
                return null;
            }
            return PhotoEditor.this.mDraftManager.getPreview();
        }

        public List<RenderData> onLoadRenderData() {
            ArrayList arrayList = new ArrayList();
            if (PhotoEditor.this.mDraftManager != null) {
                PhotoEditor.this.mDraftManager.getRenderData(arrayList);
            }
            return arrayList;
        }

        public void onSave(MenuFragment menuFragment) {
            if (PhotoEditor.this.isSingleEffectMode()) {
                RenderFragment renderFragment = menuFragment.getRenderFragment();
                if (renderFragment != null) {
                    RenderData export = renderFragment.export();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(export);
                    PhotoEditor.this.mDraftManager.setRenderDataList(arrayList);
                }
                PhotoEditor.this.mNavigatorCallbacks.onExport();
                return;
            }
            Fragment findFragmentByTag = PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            if (findFragmentByTag == null || !((PreviewFragment) findFragmentByTag).isRunningPreviewAnimator()) {
                RenderFragment renderFragment2 = menuFragment.getRenderFragment();
                if (renderFragment2 == null || !renderFragment2.isAdded()) {
                    Log.d("PhotoEditor", "no active render fragment");
                    return;
                }
                RenderData export2 = renderFragment2.export();
                if (export2 != null) {
                    sample(PhotoEditor.this.mSampleTags[menuFragment.mEffect.ordinal()], renderFragment2, "save_detail");
                    if (PhotoEditor.this.mDraftManager != null) {
                        PhotoEditor.this.mDraftManager.enqueue(export2, PhotoEditor.this.mPreviewSaveCallback);
                        return;
                    }
                    return;
                }
                PhotoEditor.this.mPreviewSaveCallback.onCancel();
            }
        }
    };
    private int mMode = 0;
    /* access modifiers changed from: private */
    public AbstractNaviFragment.Callbacks mNavigatorCallbacks = new AbstractNaviFragment.Callbacks() {
        private void sampleExit(String str) {
            if (PhotoEditor.this.mDraftManager != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("step_count", String.valueOf(PhotoEditor.this.mDraftManager.getStepCount()));
                hashMap.put("total_time", String.valueOf((System.currentTimeMillis() - PhotoEditor.this.mCreateTime) / 100));
                if (PhotoEditor.this.mDraftManager.isRemoveWatermarkEnable()) {
                    hashMap.put("remove_watermark", String.valueOf(!PhotoEditor.this.mDraftManager.isWithWatermark()));
                }
                PhotoEditor.this.mSampler.recordEvent("_main", str, hashMap);
            }
        }

        private void sampleNavigate(Effect effect) {
            PhotoEditor.this.mSampler.recordEvent(PhotoEditor.this.mSampleTags[effect.ordinal()], "enter");
        }

        public /* synthetic */ boolean lambda$onExport$105$PhotoEditor$4(int i) {
            if (i == R.string.main_save_cancel) {
                return true;
            }
            PhotoEditor.this.mDraftManager.setSaveOrigin(i != R.string.main_save_compress);
            PhotoEditor.this.mExportTask.showExportDialog();
            return true;
        }

        public /* synthetic */ void lambda$onNavigate$104$PhotoEditor$4(Effect effect) {
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
            PhotoEditor.this.lambda$showSingleEffectDisplay$103$PhotoEditor(effect);
        }

        public void onDiscard() {
            PhotoEditor.this.onActivityFinish(false);
            ActivityCompat.finishAfterTransition(PhotoEditor.this);
            sampleExit("cancelled");
        }

        public void onExport() {
            if (PhotoEditor.this.mDraftManager.isNeedCompress()) {
                if (PhotoEditor.this.mSimpleSelectDialog == null) {
                    SimpleSelectDialog unused = PhotoEditor.this.mSimpleSelectDialog = new SimpleSelectDialog();
                }
                PhotoEditor.this.mSimpleSelectDialog.setCreationModels(R.string.main_save_origin, R.string.main_save_compress, R.string.main_save_cancel);
                PhotoEditor.this.mSimpleSelectDialog.setOnOperationSelectedListener(new SimpleSelectDialog.OnOperationSelectedListener() {
                    public final boolean onOperationSelected(int i) {
                        return PhotoEditor.AnonymousClass4.this.lambda$onExport$105$PhotoEditor$4(i);
                    }
                });
                PhotoEditor.this.mSimpleSelectDialog.showAllowingStateLoss(PhotoEditor.this.mFragments, "SimpleSelectDialog");
            } else {
                PhotoEditor.this.mExportTask.showExportDialog();
            }
            sampleExit("exported");
        }

        public void onNavigate(Effect effect) {
            if (PhotoEditor.this.mDraftManager == null || !PhotoEditor.this.mDraftManager.isPreviewEnable()) {
                Log.w("PhotoEditor", "has not load preview when click");
            } else if (!SdkManager.INSTANCE.getProvider(effect).initialized()) {
                Log.w("PhotoEditor", "SdkProvider: %s has not initialized when click", effect.name());
            } else {
                Log.d("PhotoEditor", "navigate %s", (Object) effect);
                if (PhotoEditor.this.mFragments.getBackStackEntryCount() != 0) {
                    Log.d("PhotoEditor", "last effect editor is still active");
                    return;
                }
                FragmentData fragmentData = PhotoEditor.this.mFragmentData[effect.ordinal()];
                RenderFragment renderFragment = (RenderFragment) PhotoEditor.this.mFragments.findFragmentByTag(fragmentData.renderTag);
                Fragment findFragmentByTag = PhotoEditor.this.mFragments.findFragmentByTag(fragmentData.gestureTag);
                MenuFragment newMenu = fragmentData.newMenu();
                boolean z = false;
                if (renderFragment == null) {
                    renderFragment = newMenu.createRenderFragment();
                    PhotoEditor.this.mFragments.beginTransaction().detach(renderFragment).add(R.id.display_panel, renderFragment, fragmentData.renderTag).hide(renderFragment).commit();
                    z = true;
                }
                if (!renderFragment.isSupportBitmap(PhotoEditor.this.mDraftManager.getPreview())) {
                    ToastUtils.makeText((Context) PhotoEditor.this, renderFragment.getUnSupportStringRes());
                    return;
                }
                if (findFragmentByTag == null && (findFragmentByTag = newMenu.createGestureFragment()) != null) {
                    PhotoEditor.this.mFragments.beginTransaction().detach(findFragmentByTag).add(R.id.display_panel, findFragmentByTag, fragmentData.gestureTag).hide(findFragmentByTag).commit();
                    z = true;
                }
                if (z) {
                    PhotoEditor.this.mFragments.executePendingTransactions();
                }
                Bundle bundle = new Bundle();
                PhotoEditor.this.mFragments.putFragment(bundle, "MenuFragment:display_fragment", renderFragment);
                if (findFragmentByTag != null) {
                    PhotoEditor.this.mFragments.putFragment(bundle, "MenuFragment:gesture_fragment", findFragmentByTag);
                }
                newMenu.setArguments(bundle);
                Fragment findFragmentByTag2 = PhotoEditor.this.mFragments.findFragmentByTag("navigator");
                if (findFragmentByTag2 != null) {
                    PhotoEditor.this.mFragments.beginTransaction().detach(findFragmentByTag2).commitAllowingStateLoss();
                    PhotoEditor.this.mFragments.executePendingTransactions();
                }
                PhotoEditor.this.mFragments.beginTransaction().add(R.id.menu_panel, newMenu, fragmentData.menuTag).commitAllowingStateLoss();
                PhotoEditor.this.mFragments.executePendingTransactions();
                if (renderFragment.isSupportAnimation()) {
                    Fragment findFragmentByTag3 = PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
                    if (findFragmentByTag3 != null) {
                        boolean unused = PhotoEditor.this.mSuspendInputs = true;
                        PreviewFragment.class.cast(findFragmentByTag3).prepareShowEditFragment(effect, new PreviewFragment.OnPrepareEditFragmentListener() {
                            public final void showEditFragment(Effect effect) {
                                PhotoEditor.AnonymousClass4.this.lambda$onNavigate$104$PhotoEditor$4(effect);
                            }
                        });
                    }
                } else {
                    PhotoEditor.this.lambda$showSingleEffectDisplay$103$PhotoEditor(effect);
                }
                sampleNavigate(effect);
            }
        }
    };
    private boolean mNeedConfirmPassword;
    /* access modifiers changed from: private */
    public DraftManager.OnPreviewRefreshListener mOnPreviewRefreshListener = new DraftManager.OnPreviewRefreshListener() {
        public void onRefresh(Bitmap bitmap) {
            boolean unused = PhotoEditor.this.mPreviewRefreshedWhenInit = true;
            PhotoEditor.this.tryRefreshPreview();
        }
    };
    private PreviewFragment.Callbacks mPreviewCallbacks = new PreviewFragment.Callbacks() {
        public boolean isPreviewChanged() {
            return PhotoEditor.this.mDraftManager != null && !PhotoEditor.this.mDraftManager.isEmpty();
        }

        public Bitmap onLoadPreview() {
            if (PhotoEditor.this.mDraftManager == null) {
                return null;
            }
            return PhotoEditor.this.mDraftManager.getPreview();
        }

        public Bitmap onLoadPreviewOriginal() {
            if (PhotoEditor.this.mDraftManager == null) {
                return null;
            }
            return PhotoEditor.this.mDraftManager.getPreviewOriginal();
        }

        public void reRender(boolean z) {
            if (PhotoEditor.this.mDraftManager != null) {
                PhotoEditor.this.mDraftManager.reRender(z, PhotoEditor.this.mReRenderCallback);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mPreviewRefreshedWhenInit = false;
    /* access modifiers changed from: private */
    public Callback mPreviewSaveCallback = new Callback<Bitmap, Void>() {
        public void onCancel() {
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
            PhotoEditor.this.onExit(PhotoEditor.this.findActiveMenu());
        }

        public void onDone(Bitmap bitmap) {
            PreviewFragment previewFragment = (PreviewFragment) PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            if (previewFragment != null) {
                previewFragment.setRemoveWatermarkEnable(PhotoEditor.this.mDraftManager.isRemoveWatermarkShow());
            }
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
            MenuFragment access$2600 = PhotoEditor.this.findActiveMenu();
            if (access$2600 != null) {
                access$2600.hideProcessDialog();
                PhotoEditor.this.onExit(access$2600);
            }
            if (PhotoEditor.this.mDraftManager == null || PhotoEditor.this.mDraftManager.getStepCount() <= 1) {
                PhotoEditor.this.setExposeButtonEnable(true);
            }
        }

        public void onError(Void voidR) {
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
            ToastUtils.makeText((Context) PhotoEditor.this, (int) R.string.main_save_error_msg);
        }

        public void onExecute(Bitmap bitmap) {
        }

        public void onPrepare() {
            boolean unused = PhotoEditor.this.mSuspendInputs = true;
        }
    };
    /* access modifiers changed from: private */
    public Callback mReRenderCallback = new Callback<Bitmap, Void>() {
        public void onCancel() {
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
        }

        public void onDone(Bitmap bitmap) {
            boolean z = false;
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
            PreviewFragment previewFragment = (PreviewFragment) PhotoEditor.this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            if (PhotoEditor.this.mDraftManager == null || PhotoEditor.this.mDraftManager.isEmpty()) {
                z = true;
            }
            if (previewFragment != null) {
                previewFragment.refreshPreview(bitmap);
                previewFragment.enableComparison(!z);
            }
            PhotoEditor.this.setExposeButtonEnable(!z);
        }

        public void onError(Void voidR) {
            boolean unused = PhotoEditor.this.mSuspendInputs = false;
        }

        public void onExecute(Bitmap bitmap) {
        }

        public void onPrepare() {
            boolean unused = PhotoEditor.this.mSuspendInputs = true;
        }
    };
    private boolean mResumed;
    /* access modifiers changed from: private */
    public String[] mSampleTags = new String[Effect.values().length];
    /* access modifiers changed from: private */
    public Sampler mSampler;
    /* access modifiers changed from: private */
    public SimpleSelectDialog mSimpleSelectDialog;
    /* access modifiers changed from: private */
    public Effect mSingleEffect;
    /* access modifiers changed from: private */
    public boolean mSuspendInputs;
    /* access modifiers changed from: private */
    public TransitionConfiguration mTransitionConfig = new TransitionConfiguration(this);
    private boolean mTransitionEnd = false;

    private static class FragmentData {
        final String gestureTag;
        final Class<? extends MenuFragment> menu;
        final String menuTag;
        final String renderTag;

        public FragmentData(Class<? extends MenuFragment> cls, Effect effect) {
            this.menu = cls;
            this.menuTag = effect.name() + ":menu";
            this.renderTag = effect.name() + ":render";
            this.gestureTag = effect.name() + ":gesture";
        }

        public MenuFragment newMenu() {
            try {
                return (MenuFragment) this.menu.newInstance();
            } catch (InstantiationException e) {
                Log.w("PhotoEditor", (Throwable) e);
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e2) {
                Log.w("PhotoEditor", (Throwable) e2);
                throw new IllegalStateException(e2);
            }
        }
    }

    private static class TransitionConfiguration {
        /* access modifiers changed from: private */
        public PhotoEditor mEditor;
        private int mEnterDuration;
        private int mExitDuration;
        private int mImageHeight;
        private int mImageWidth;
        private Matrix mMatrix;
        private int mMenuOffset;
        private String mPhotoViewName;
        private boolean mRunTransition;
        private Transition.TransitionListener mTransitionListener = new Transition.TransitionListener() {
            public void onTransitionCancel(Transition transition) {
                TransitionConfiguration.this.mEditor.onEnterTransitionEnd();
            }

            public void onTransitionEnd(Transition transition) {
                TransitionConfiguration.this.mEditor.onEnterTransitionEnd();
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
            }
        };

        public TransitionConfiguration(PhotoEditor photoEditor) {
            this.mEditor = photoEditor;
        }

        private void configureEnterTransition() {
            TransitionSet transitionSet = new TransitionSet();
            ImageTransition imageTransition = new ImageTransition(true, this.mMatrix, this.mImageWidth, this.mImageHeight);
            imageTransition.setInterpolator(new CubicEaseInOutInterpolator());
            TransitionCompat.addTarget(imageTransition, this.mPhotoViewName);
            MenuTransition menuTransition = new MenuTransition((float) this.mMenuOffset, true, this.mEditor.getResources());
            menuTransition.setInterpolator(new CubicEaseInOutInterpolator());
            menuTransition.addTarget(R.id.menu_panel);
            transitionSet.addTransition(imageTransition).addTransition(menuTransition).setDuration((long) this.mEnterDuration).addListener(this.mTransitionListener);
            WindowCompat.setSharedElementEnterTransition(this.mEditor.getWindow(), transitionSet);
        }

        private void configureExitTransition(boolean z) {
            TransitionSet transitionSet = new TransitionSet();
            Transition changeBounds = z ? new ChangeBounds() : new ImageTransition(false, this.mMatrix, this.mImageWidth, this.mImageHeight);
            changeBounds.setInterpolator(new CubicEaseOutInterpolator());
            changeBounds.setDuration((long) this.mExitDuration);
            TransitionCompat.addTarget(changeBounds, this.mPhotoViewName);
            MenuTransition menuTransition = new MenuTransition((float) this.mMenuOffset, false, this.mEditor.getResources());
            menuTransition.setInterpolator(new CubicEaseOutInterpolator());
            menuTransition.addTarget(R.id.menu_panel);
            transitionSet.addTransition(changeBounds).addTransition(menuTransition);
            WindowCompat.setSharedElementReturnTransition(this.mEditor.getWindow(), transitionSet);
        }

        /* access modifiers changed from: package-private */
        public void onActivityCreate(boolean z) {
            this.mRunTransition = WindowCompat.requestActivityTransition(this.mEditor.getWindow()) && this.mEditor.getIntent().getBooleanExtra("extra_custom_transition", false) && !z;
            SystemUiUtil.setDrawSystemBarBackground(this.mEditor.getWindow());
            if (this.mEditor.isSingleEffectMode()) {
                this.mRunTransition = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void onExit(boolean z) {
            PreviewFragment previewFragment = (PreviewFragment) this.mEditor.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
            if (previewFragment != null) {
                previewFragment.setRemoveWatermarkEnable(false);
            }
            if (this.mRunTransition) {
                configureExitTransition(z);
                ActivityCompat.setEnterSharedElementCallback(this.mEditor, new ActivityCompat.SharedElementCallback() {
                    public void onSharedElementStart() {
                        TransitionConfiguration.this.mEditor.mExportTask.closeExportDialog();
                    }
                });
                return;
            }
            this.mEditor.mExportTask.closeExportDialog();
        }

        /* access modifiers changed from: package-private */
        public void onImageLoaded() {
            if (this.mRunTransition) {
                ActivityCompat.startPostponedEnterTransition(this.mEditor);
            } else {
                this.mEditor.onEnterTransitionEnd();
            }
        }

        /* access modifiers changed from: package-private */
        public void postActivityCreate() {
            if (this.mRunTransition) {
                Intent intent = this.mEditor.getIntent();
                this.mImageWidth = intent.getIntExtra("extra_image_width", 0);
                this.mImageHeight = intent.getIntExtra("extra_image_height", 0);
                float[] floatArrayExtra = intent.getFloatArrayExtra("extra_image_matrix");
                this.mMatrix = new Matrix();
                this.mMatrix.setValues(floatArrayExtra);
                Resources resources = this.mEditor.getResources();
                this.mMenuOffset = resources.getDimensionPixelOffset(R.dimen.photo_editor_transition_menu_offset);
                this.mPhotoViewName = resources.getString(R.string.photo_editor_transition_image_view);
                this.mEnterDuration = resources.getInteger(R.integer.photo_editor_enter_transition_duration);
                this.mExitDuration = resources.getInteger(R.integer.photo_editor_exit_transition_duration);
                configureEnterTransition();
                ActivityCompat.postponeEnterTransition(this.mEditor);
            }
        }
    }

    public PhotoEditor() {
        this.mFragmentData[Effect.BEAUTIFY.ordinal()] = new FragmentData(BeautifyFragment.class, Effect.BEAUTIFY);
        this.mFragmentData[Effect.ADJUST.ordinal()] = new FragmentData(AdjustMenuFragment.class, Effect.ADJUST);
        this.mFragmentData[Effect.FILTER.ordinal()] = new FragmentData(FilterFragment.class, Effect.FILTER);
        this.mFragmentData[Effect.CROP.ordinal()] = new FragmentData(CropMenuFragment.class, Effect.CROP);
        this.mFragmentData[Effect.TEXT.ordinal()] = new FragmentData(TextMenuFragment.class, Effect.TEXT);
        this.mFragmentData[Effect.DOODLE.ordinal()] = new FragmentData(DoodleMenuFragment.class, Effect.DOODLE);
        this.mFragmentData[Effect.STICKER.ordinal()] = new FragmentData(StickerMenuFragment.class, Effect.STICKER);
        this.mFragmentData[Effect.LONG_CROP.ordinal()] = new FragmentData(LongCropFragment.class, Effect.LONG_CROP);
        this.mFragmentData[Effect.MOSAIC.ordinal()] = new FragmentData(MosaicMenuFragment.class, Effect.MOSAIC);
        this.mFragmentData[Effect.REMOVER.ordinal()] = new FragmentData(RemoverMenuFragment.class, Effect.REMOVER);
        this.mFragmentData[Effect.MIUIBEAUTIFY.ordinal()] = new FragmentData(MiuiBeautyFragment.class, Effect.MIUIBEAUTIFY);
        this.mFragmentData[Effect.FRAME.ordinal()] = new FragmentData(FrameMenuFragment.class, Effect.FRAME);
        this.mSampleTags[Effect.BEAUTIFY.ordinal()] = "_beautify";
        this.mSampleTags[Effect.ADJUST.ordinal()] = "_enhance";
        this.mSampleTags[Effect.FILTER.ordinal()] = "_filter";
        this.mSampleTags[Effect.CROP.ordinal()] = "_crop";
        this.mSampleTags[Effect.STICKER.ordinal()] = "_sticker";
        this.mSampleTags[Effect.LONG_CROP.ordinal()] = "_beautify";
        this.mSampleTags[Effect.TEXT.ordinal()] = "_text";
        this.mSampleTags[Effect.MOSAIC.ordinal()] = "_mosaic";
        this.mSampleTags[Effect.DOODLE.ordinal()] = "_doodle";
        this.mSampleTags[Effect.REMOVER.ordinal()] = "_remover";
        this.mSampleTags[Effect.MIUIBEAUTIFY.ordinal()] = "_miuibeautify";
        this.mSampleTags[Effect.FRAME.ordinal()] = "_frame";
    }

    /* access modifiers changed from: private */
    public MenuFragment findActiveMenu() {
        Fragment findFragmentById;
        if (this.mFragments.getBackStackEntryCount() > 0 && (findFragmentById = this.mFragments.findFragmentById(R.id.menu_panel)) != null && findFragmentById.isAdded()) {
            if (findFragmentById instanceof MenuFragment) {
                return (MenuFragment) findFragmentById;
            }
            Log.w("PhotoEditor", "not menu in menu panel: %s", findFragmentById);
        }
        Log.w("PhotoEditor", "no active menu fragment found");
        return null;
    }

    private void initScreenBrightness() {
        Intent intent = getIntent();
        if (intent != null) {
            float floatExtra = intent.getFloatExtra("photo-brightness-manual", -1.0f);
            float floatExtra2 = intent.getFloatExtra("photo-brightness-auto", -1.0f);
            if (floatExtra >= 0.0f || floatExtra2 >= 0.0f) {
                this.mBrightnessManager = new BrightnessManager(this, floatExtra, floatExtra2);
                if (this.mResumed) {
                    this.mBrightnessManager.onResume();
                }
            }
        }
    }

    private boolean isNeedConfirmPassword() {
        DraftManager draftManager = this.mDraftManager;
        return draftManager != null && draftManager.isSecret() && this.mNeedConfirmPassword;
    }

    /* access modifiers changed from: private */
    public boolean isShowSkyGuideView() {
        return SkyCheckHelper.isSkyEnable() && !GalleryPreferences.PhotoFilterSkyGuide.hasGuided();
    }

    /* access modifiers changed from: private */
    public boolean isSingleEffectMode() {
        return this.mMode != 0;
    }

    /* access modifiers changed from: private */
    public void onActivityFinish(boolean z) {
        this.mTransitionConfig.onExit(z);
        Fragment findFragmentById = this.mFragments.findFragmentById(R.id.display_panel);
        if (findFragmentById instanceof PreviewFragment) {
            ((PreviewFragment) findFragmentById).onExit(z);
        }
        if (isNeedConfirmPassword()) {
            setPhotoSecretFinishResult();
        }
    }

    private void onCtaChecked() {
        if (DocumentProviderPreference.isFirstEntrance() && DocumentProviderUtils.needRequestExternalSDCardPermission(this)) {
            DocumentProviderUtils.startFirstEntrancyPermissionActivityForResult(this);
        }
    }

    /* access modifiers changed from: private */
    public void onEnterTransitionEnd() {
        this.mTransitionEnd = true;
        tryRefreshPreview();
        PreviewFragment previewFragment = (PreviewFragment) this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
        if (previewFragment != null) {
            previewFragment.setRemoveWatermarkEnable(this.mDraftManager.isRemoveWatermarkEnable());
        }
        tryShowFilterSkyGuideView();
    }

    /* access modifiers changed from: private */
    public void onExit(MenuFragment menuFragment) {
        boolean z = menuFragment.getRenderFragment() instanceof GLFragment;
        if (z) {
            Log.d("PhotoEditor", "exiting gl render view");
            if (this.mFragments.findFragmentByTag("gl_mask_out") == null) {
                Log.d("PhotoEditor", "display mask first");
                PreviewFragment previewFragment = new PreviewFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("overwrite_background", true);
                previewFragment.setArguments(bundle);
                this.mFragments.beginTransaction().add(R.id.display_panel, previewFragment, "gl_mask_out").commitAllowingStateLoss();
                this.mFragments.executePendingTransactions();
                return;
            }
            Log.d("PhotoEditor", "mask displayed, remove render view");
            Fragment findFragmentByTag = this.mFragments.findFragmentByTag("gl_mask_in");
            if (findFragmentByTag != null) {
                Log.d("PhotoEditor", "in mask not gone");
                this.mFragments.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
                this.mFragments.executePendingTransactions();
            }
        } else {
            Log.d("PhotoEditor", "no gl view on top, do exit");
        }
        this.mFragments.popBackStackImmediate();
        FragmentTransaction beginTransaction = this.mFragments.beginTransaction();
        beginTransaction.remove(menuFragment.getRenderFragment());
        Fragment gestureFragment = menuFragment.getGestureFragment();
        if (gestureFragment != null) {
            beginTransaction.remove(gestureFragment);
        }
        beginTransaction.remove(menuFragment);
        beginTransaction.commitAllowingStateLoss();
        this.mFragments.executePendingTransactions();
        Fragment findFragmentByTag2 = this.mFragments.findFragmentByTag("navigator");
        if (findFragmentByTag2 != null) {
            this.mFragments.beginTransaction().attach(findFragmentByTag2).commitAllowingStateLoss();
            this.mFragments.executePendingTransactions();
        }
        if (z) {
            DraftManager draftManager = this.mDraftManager;
            if (draftManager != null && !draftManager.isEmpty()) {
                setExposeButtonEnable(true);
            }
            Fragment findFragmentByTag3 = this.mFragments.findFragmentByTag("gl_mask_out");
            if (findFragmentByTag3 != null) {
                Log.d("PhotoEditor", "remove mask view");
                this.mFragments.beginTransaction().remove(findFragmentByTag3).commitAllowingStateLoss();
                this.mFragments.executePendingTransactions();
            }
        }
    }

    private void prepareNavigator() {
        this.mActivatedEffects = resolveEffects(getIntent().getExtras());
        Effect[] values = Effect.values();
        Iterator it = ((ArrayList) this.mActivatedEffects.clone()).iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (SdkManager.INSTANCE.getProvider(values[intValue]) == null) {
                Log.d("PhotoEditor", "%s not supported, skip", (Object) values[intValue]);
                this.mActivatedEffects.remove(Integer.valueOf(intValue));
            }
        }
        showNavigator();
    }

    private void prepareResult(Intent intent) {
        DraftManager draftManager = this.mDraftManager;
        if (draftManager != null) {
            intent.putExtra("photo_edit_exported_width", draftManager.getExportedWidth());
            intent.putExtra("photo_edit_exported_height", this.mDraftManager.getExportedHeight());
        }
    }

    private ArrayList<Integer> resolveEffects(Bundle bundle) {
        if (bundle == null || !bundle.getBoolean(Constants.EXTRA_IS_SCREENSHOT)) {
            ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(Effect.BEAUTIFY.ordinal()), Integer.valueOf(Effect.CROP.ordinal()), Integer.valueOf(Effect.FILTER.ordinal()), Integer.valueOf(Effect.STICKER.ordinal()), Integer.valueOf(Effect.DOODLE.ordinal()), Integer.valueOf(Effect.TEXT.ordinal()), Integer.valueOf(Effect.ADJUST.ordinal()), Integer.valueOf(Effect.FRAME.ordinal()), Integer.valueOf(Effect.MOSAIC.ordinal())}));
            if (ArcsoftBeautyJni.idBeautyAvailable()) {
                arrayList.add(arrayList.indexOf(Integer.valueOf(Effect.FILTER.ordinal())), Integer.valueOf(Effect.MIUIBEAUTIFY.ordinal()));
                this.mSampler.recordEvent(this.mSampleTags[Effect.MIUIBEAUTIFY.ordinal()], "category_show");
            }
            if (Inpaint.isAvailable()) {
                arrayList.add(Integer.valueOf(Effect.REMOVER.ordinal()));
            }
            return arrayList;
        } else if (bundle.getBoolean(Constants.EXTRA_IS_LONG_SCREENSHOT)) {
            return new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(Effect.LONG_CROP.ordinal()), Integer.valueOf(Effect.DOODLE.ordinal()), Integer.valueOf(Effect.MOSAIC.ordinal())}));
        } else {
            return new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(Effect.CROP.ordinal()), Integer.valueOf(Effect.DOODLE.ordinal()), Integer.valueOf(Effect.TEXT.ordinal()), Integer.valueOf(Effect.MOSAIC.ordinal())}));
        }
    }

    private AbstractNaviFragment resolveNavigator(Bundle bundle) {
        return (bundle == null || !bundle.getBoolean(Constants.EXTRA_IS_SCREENSHOT)) ? new PhotoNaviFragment() : new ScreenshotNaviFragment();
    }

    /* access modifiers changed from: private */
    public void setExposeButtonEnable(boolean z) {
        AbstractNaviFragment abstractNaviFragment = (AbstractNaviFragment) this.mFragments.findFragmentByTag("navigator");
        if (abstractNaviFragment != null && abstractNaviFragment.isAdded() && abstractNaviFragment.getView() != null) {
            abstractNaviFragment.setExportEnabled(z);
        }
    }

    private void setPhotoSecretFinishResult() {
        if (this.mActivityIntent == null) {
            this.mActivityIntent = new Intent();
        }
        prepareResult(this.mActivityIntent);
        this.mActivityIntent.putExtra("photo_secret_finish", true);
        this.mActivityIntent.putExtra("extra_photo_edit_type", "extra_photo_editor_type_common");
        setResult(this.mActivityResult, this.mActivityIntent);
    }

    private void showNavigator() {
        AbstractNaviFragment resolveNavigator = resolveNavigator(getIntent().getExtras());
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("content", this.mActivatedEffects);
        resolveNavigator.setArguments(bundle);
        this.mFragments.beginTransaction().setTransition(8194).add(R.id.menu_panel, resolveNavigator, "navigator").commitAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public void showSingleEffectDisplay(Effect effect) {
        FragmentData fragmentData = this.mFragmentData[effect.ordinal()];
        RenderFragment createRenderFragment = fragmentData.newMenu().createRenderFragment();
        getFragmentManager().beginTransaction().detach(createRenderFragment).add(R.id.display_panel, createRenderFragment, fragmentData.renderTag).hide(createRenderFragment).commit();
        getFragmentManager().executePendingTransactions();
        Fragment findFragmentById = this.mFragments.findFragmentById(R.id.menu_panel);
        MenuFragment menuFragment = (MenuFragment) findFragmentById;
        if (findFragmentById != null && findFragmentById.isAdded()) {
            menuFragment.setRenderFragmentInSingleMode(createRenderFragment);
        }
        getWindow().getDecorView().post(new Runnable(effect) {
            private final /* synthetic */ Effect f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                PhotoEditor.this.lambda$showSingleEffectDisplay$103$PhotoEditor(this.f$1);
            }
        });
    }

    private void showSingleEffectMenu(Effect effect) {
        FragmentData fragmentData = this.mFragmentData[effect.ordinal()];
        MenuFragment newMenu = fragmentData.newMenu();
        Bundle bundle = new Bundle();
        bundle.putBoolean("single_effect_mode", true);
        newMenu.setArguments(bundle);
        this.mFragments.beginTransaction().add(R.id.menu_panel, newMenu, fragmentData.menuTag).commitAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public void tryRefreshPreview() {
        PreviewFragment previewFragment;
        if (this.mPreviewRefreshedWhenInit && this.mTransitionEnd && (previewFragment = (PreviewFragment) this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW)) != null) {
            previewFragment.refreshPreview(this.mDraftManager.getPreview());
        }
    }

    private void tryShowFilterSkyGuideView() {
        if (this.mIsShowSkyGuideView) {
            Fragment findFragmentByTag = this.mFragments.findFragmentByTag("navigator");
            if (findFragmentByTag instanceof PhotoNaviFragment) {
                ((PhotoNaviFragment) findFragmentByTag).showItemGuideView(Effect.FILTER, 0, R.string.filter_sky_hint);
                GalleryPreferences.PhotoFilterSkyGuide.setPhotoFilterSkyGuided(true);
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.mSuspendInputs || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mSuspendInputs || super.dispatchTouchEvent(motionEvent);
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.permission_storage_desc), true)};
    }

    public /* synthetic */ void lambda$onPermissionsChecked$102$PhotoEditor(boolean z) {
        onCtaChecked();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 27) {
            if (i2 != -1) {
                setPhotoSecretFinishResult();
                finish();
            } else {
                this.mNeedConfirmPassword = false;
            }
        }
        DocumentProviderUtils.handleRequestPermissionResult(this, i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof MenuFragment) {
            ((MenuFragment) fragment).mCallbacks = this.mMenuCallback;
        } else if (fragment instanceof AbstractNaviFragment) {
            ((AbstractNaviFragment) fragment).mCallbacks = this.mNavigatorCallbacks;
        } else if (fragment instanceof PreviewFragment) {
            PreviewFragment previewFragment = (PreviewFragment) fragment;
            previewFragment.mCallbacks = this.mPreviewCallbacks;
            if ("gl_mask_out".equals(previewFragment.getTag())) {
                previewFragment.mOnViewReadyListener = this.mGLMaskOutReadyListener;
            }
        } else if (fragment instanceof ExportFragment) {
            ((ExportFragment) fragment).setCallbacks(this.mExportCallbacks);
        } else if (fragment instanceof AlertDialogFragment) {
            ((AlertDialogFragment) fragment).setCallbacks(this.mAlertDialogCallbacks);
        }
        if (fragment instanceof GLFragment) {
            ((GLFragment) fragment).getGLContext().setOnCreatedListener(this.mGLReadyListener);
        }
    }

    public void onAttachedToWindow() {
        if (com.android.internal.WindowCompat.isNotch(this)) {
            SystemUiUtil.extendToStatusBar(getWindow().getDecorView());
        }
    }

    public void onBackPressed() {
        if (isSingleEffectMode()) {
            this.mNavigatorCallbacks.onDiscard();
            return;
        }
        Fragment findFragmentById = this.mFragments.findFragmentById(R.id.menu_panel);
        if (findFragmentById instanceof AbstractNaviFragment) {
            Log.d("PhotoEditor", "back pressed on navigator");
            DraftManager draftManager = this.mDraftManager;
            if (draftManager != null && !draftManager.isEmpty()) {
                Log.d("PhotoEditor", "have pending operation");
                new AlertDialogFragment.Builder().setMessage(R.string.main_discard_confirm_message).setPositiveButton(R.string.main_discard_positive_btn).setNegativeButton(R.string.main_discard_negative_btn).setCancellable(true).build().showAllowingStateLoss(this.mFragments, "main_alert_dialog");
                return;
            }
        } else if (findFragmentById instanceof MenuFragment) {
            Log.d("PhotoEditor", "back pressed on menu");
            MenuFragment menuFragment = (MenuFragment) findFragmentById;
            if (menuFragment.getRenderFragment() == null || menuFragment.getRenderFragment().isEmpty()) {
                onExit(menuFragment);
                return;
            }
            Log.d("PhotoEditor", "menu has pending operation");
            new AlertDialogFragment.Builder().setMessage(R.string.sub_discard_confirm_message).setPositiveButton(R.string.sub_discard_positive_btn).setNegativeButton(R.string.sub_discard_negative_btn).setCancellable(true).build().showAllowingStateLoss(this.mFragments, "menu_alert_dialog");
            return;
        }
        Log.d("PhotoEditor", "neither handled back press event, just call default");
        onActivityFinish(false);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z = false;
        GalleryPreferences.CTA.setToAllowUseOnOfflineGlobal(getIntent().getBooleanExtra("allow_use_on_offline_global", false));
        super.onCreate((Bundle) null);
        com.android.internal.WindowCompat.setCutoutModeShortEdges(getWindow());
        if (getIntent().getExtras() != null) {
            this.mMode = getIntent().getExtras().getInt("editor_mode", 0);
        }
        TransitionConfiguration transitionConfiguration = this.mTransitionConfig;
        if (bundle != null) {
            z = true;
        }
        transitionConfiguration.onActivityCreate(z);
        this.mDraftManager = new DraftManager(this, getIntent().getData(), getIntent().getExtras());
        this.mDraftManager.setSingleEffectMode(isSingleEffectMode());
        this.mExportTask = ExportTask.from(this);
        if (this.mExportTask == null) {
            ActivityCompat.finishAfterTransition(this);
            ToastUtils.makeText((Context) this, (int) R.string.image_invalid_path);
            return;
        }
        setContentView(R.layout.photo_editor_main);
        this.mSampler = Sampler.from(getIntent().getExtras());
        this.mEditorOriginHandler = new EditorOriginHandler(this, this.mExportTask.getSource());
        this.mFragments = getFragmentManager();
        PermissionInjector.injectIfNeededIn(this, this, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SimpleSelectDialog simpleSelectDialog = this.mSimpleSelectDialog;
        if (simpleSelectDialog != null) {
            simpleSelectDialog.dismissSafely();
            this.mSimpleSelectDialog = null;
        }
        DraftManager draftManager = this.mDraftManager;
        if (draftManager != null) {
            draftManager.release();
            this.mDraftManager = null;
        }
        ExportTask exportTask = this.mExportTask;
        if (exportTask != null) {
            exportTask.closeExportDialog();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        RenderFragment renderFragment;
        this.mResumed = false;
        super.onPause();
        MenuFragment findActiveMenu = findActiveMenu();
        if (findActiveMenu != null && (renderFragment = findActiveMenu.getRenderFragment()) != null && renderFragment.isAdded() && (renderFragment instanceof GLFragment)) {
            ((GLFragment) renderFragment).getGLContext().performPause();
        }
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onPause();
        }
        this.mSampler.recordPageEnd(this);
        ImageLoader.getInstance().onPagePause();
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        if (GalleryPreferences.CTA.allowUseOnOfflineGlobal() || GalleryPreferences.CTA.canConnectNetwork()) {
            onCtaChecked();
        } else {
            AgreementsUtils.showUserAgreements(this, new OnAgreementInvokedListener() {
                public final void onAgreementInvoked(boolean z) {
                    PhotoEditor.this.lambda$onPermissionsChecked$102$PhotoEditor(z);
                }
            });
        }
        this.mInitializeController = new InitializeController(this, this.mDecoderCallbacks);
        this.mInitializeController.doInitialize();
        this.mCreateTime = System.currentTimeMillis();
        this.mSampler.recordEvent("_main", "enter");
        BeautificationSDK.init(getApplicationContext());
        this.mTransitionConfig.postActivityCreate();
        initScreenBrightness();
        PreviewFragment previewFragment = new PreviewFragment();
        previewFragment.setLoadDone(false);
        this.mFragments.beginTransaction().add(R.id.display_panel, previewFragment, MovieStatUtils.PAGER_PREVIEW).commit();
        if (this.mMode == 0) {
            prepareNavigator();
            return;
        }
        this.mSingleEffect = Effect.FILTER;
        showSingleEffectMenu(this.mSingleEffect);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        RenderFragment renderFragment;
        super.onResume();
        MenuFragment findActiveMenu = findActiveMenu();
        if (findActiveMenu != null && (renderFragment = findActiveMenu.getRenderFragment()) != null && renderFragment.isAdded() && (renderFragment instanceof GLFragment)) {
            ((GLFragment) renderFragment).getGLContext().performResume();
        }
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onResume();
        }
        this.mSampler.recordPageStart(this);
        this.mResumed = true;
        ImageLoader.getInstance().onPageResume();
        if (isNeedConfirmPassword()) {
            this.mNeedConfirmPassword = false;
            LockSettingsHelper.startAuthenticatePasswordActivity((Activity) this, 27);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Log.d("PhotoEditor", "onSaveInstanceState");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        EditorOriginHandler editorOriginHandler = this.mEditorOriginHandler;
        if (editorOriginHandler != null) {
            editorOriginHandler.onStart();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mNeedConfirmPassword = true;
        super.onStop();
        EditorOriginHandler editorOriginHandler = this.mEditorOriginHandler;
        if (editorOriginHandler != null) {
            editorOriginHandler.onDestroy();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onWindowFocusChanged(z);
        }
    }

    public void setActivityResult(int i, Intent intent) {
        this.mActivityResult = i;
        this.mActivityIntent = intent;
        prepareResult(intent);
        intent.putExtra("extra_photo_edit_type", "extra_photo_editor_type_common");
        setResult(this.mActivityResult, this.mActivityIntent);
    }

    /* renamed from: showEditFragment */
    public void lambda$showSingleEffectDisplay$103$PhotoEditor(Effect effect) {
        boolean z;
        FragmentData fragmentData = this.mFragmentData[effect.ordinal()];
        RenderFragment renderFragment = (RenderFragment) this.mFragments.findFragmentByTag(fragmentData.renderTag);
        Fragment findFragmentByTag = this.mFragments.findFragmentByTag(fragmentData.gestureTag);
        if (renderFragment != null) {
            this.mFragments.beginTransaction().show(renderFragment).commit();
            z = true;
        } else {
            z = false;
        }
        if (findFragmentByTag != null) {
            this.mFragments.beginTransaction().show(findFragmentByTag).commit();
            z = true;
        }
        if (z) {
            this.mFragments.executePendingTransactions();
        }
        Fragment findFragmentByTag2 = this.mFragments.findFragmentByTag(MovieStatUtils.PAGER_PREVIEW);
        if (findFragmentByTag2 != null) {
            this.mFragments.beginTransaction().detach(findFragmentByTag2).addToBackStack((String) null).commit();
            this.mFragments.executePendingTransactions();
        }
        if (renderFragment instanceof GLFragment) {
            PreviewFragment previewFragment = new PreviewFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("overwrite_background", true);
            previewFragment.setArguments(bundle);
            this.mFragments.beginTransaction().add(R.id.display_panel, previewFragment, "gl_mask_in").commit();
            this.mFragments.executePendingTransactions();
        }
    }
}
