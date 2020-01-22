package com.miui.gallery.editor.photo.screen.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.WindowCompat;
import com.miui.gallery.R;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.agreement.core.OnAgreementInvokedListener;
import com.miui.gallery.editor.photo.app.DraftManager;
import com.miui.gallery.editor.photo.app.ExportFragment;
import com.miui.gallery.editor.photo.app.ExportTask;
import com.miui.gallery.editor.photo.app.InitializeController;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.screen.base.ScreenRenderCallback;
import com.miui.gallery.editor.photo.screen.core.ScreenProviderManager;
import com.miui.gallery.editor.photo.screen.core.ScreenRenderData;
import com.miui.gallery.editor.photo.screen.core.ScreenRenderManager;
import com.miui.gallery.editor.photo.screen.doodle.ScreenDoodleMenuFragment;
import com.miui.gallery.editor.photo.screen.entity.ScreenNavigatorData;
import com.miui.gallery.editor.photo.screen.home.ScreenAnimatorHelper;
import com.miui.gallery.editor.photo.screen.home.ScreenDeleteDialogFragment;
import com.miui.gallery.editor.photo.screen.home.ScreenNavFragment;
import com.miui.gallery.editor.photo.screen.longcrop.ILongCropEditorController;
import com.miui.gallery.editor.photo.screen.longcrop.ScreenLongCropFragment;
import com.miui.gallery.editor.photo.screen.mosaic.ScreenMosaicMenuFragment;
import com.miui.gallery.editor.photo.screen.stat.ScreenEditorStatUtils;
import com.miui.gallery.editor.photo.screen.text.ScreenTextMenuFragment;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.PermissionInjector;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.preference.DocumentProviderPreference;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.sdk.editor.Constants;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.ChooserFragment;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.ui.PhotoPageFragmentBase;
import com.miui.gallery.ui.ProcessTask;
import com.miui.gallery.util.ContentUtils;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SystemUiUtil;
import com.miui.gallery.util.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class ScreenEditorActivity extends Activity implements PermissionCheckCallback {
    /* access modifiers changed from: private */
    public Activity mActivity;
    private ScreenAnimatorHelper.AnimatorViewCallback mAnimatorViewCallback = new ScreenAnimatorHelper.AnimatorViewCallback() {
        public View getBottomLayoutView() {
            return ScreenEditorActivity.this.mBottomLayoutView;
        }

        public Guideline getCommonChangeLine() {
            return ScreenEditorActivity.this.mCommonLine;
        }

        public Guideline getEditBottomLine() {
            return ScreenEditorActivity.this.mEditBottomLine;
        }

        public Guideline getEditTopLine() {
            return ScreenEditorActivity.this.mEditTopLine;
        }

        public View getEditTopView() {
            return ScreenEditorActivity.this.mEditTopLayoutView;
        }

        public Guideline getMenuBottomLine() {
            return ScreenEditorActivity.this.mMenuBottomLine;
        }

        public View getMenuLayoutView() {
            return ScreenEditorActivity.this.mMenuLayoutView;
        }

        public Guideline getMenuTopLine() {
            return ScreenEditorActivity.this.mMenuTopLine;
        }

        public View getScreenEditorBgView() {
            return ScreenEditorActivity.this.mScreenRootBg;
        }

        public Guideline getShareBottomLine() {
            return ScreenEditorActivity.this.mShareBottomLine;
        }

        public Guideline getShareTopLine() {
            return ScreenEditorActivity.this.mShareTopLine;
        }

        public View getShareTopView() {
            return ScreenEditorActivity.this.mShareTopLayoutView;
        }
    };
    private ImageView mBackView;
    /* access modifiers changed from: private */
    public FrameLayout mBottomLayoutView;
    private Fragment mChooserFragment;
    /* access modifiers changed from: private */
    public Guideline mCommonLine;
    /* access modifiers changed from: private */
    public ProcessTask.OnCompleteListener mCompleteListener = new ProcessTask.OnCompleteListener() {
        public final void onCompleteProcess(Object obj) {
            ScreenEditorActivity.this.lambda$new$86$ScreenEditorActivity(obj);
        }
    };
    private Fragment mCurrentBottomFragment;
    private InitializeController.Callbacks mDecoderCallbacks = new InitializeController.Callbacks() {
        public int doInitialize() {
            SdkManager.INSTANCE.onAttach(ScreenEditorActivity.this.getApplication());
            SdkManager.INSTANCE.onActivityCreate();
            try {
                return ScreenEditorActivity.this.mDraftManager.initializeForPreview(false) ? 3 : 2;
            } catch (FileNotFoundException e) {
                Log.w("ScreenEditorActivity", (Throwable) e);
                return 1;
            } catch (SecurityException e2) {
                Log.w("ScreenEditorActivity", (Throwable) e2);
                return 2;
            }
        }

        public void onDone() {
            ScreenEditorActivity.this.mScreenEditorListener.setPreviewBitmap(ScreenEditorActivity.this.mDraftManager.getPreview());
            ScreenEditorActivity.this.mScreenShareView.setShareBitmap(ScreenEditorActivity.this.mDraftManager.getPreview(), false);
            if (ScreenEditorActivity.this.mIsFromLongScreen) {
                if (!ScreenEditorActivity.this.mIsLongScreenMode) {
                    ScreenEditorActivity.this.mScreenAnimatorHelper.startEnterAnimator();
                    return;
                }
                boolean unused = ScreenEditorActivity.this.mIsLongCropShow = true;
                ScreenEditorActivity.this.showLongCropFragment();
                ScreenEditorActivity.this.mScreenEditorListener.startScreenThumbnailAnimate(ScreenEditorActivity.this.mScreenAnimatorHelper.mThumbnailAnimatorCallback);
            } else if (ScreenEditorActivity.this.isFromNormalEdit()) {
                ScreenEditorActivity.this.mScreenEditorListener.startScreenThumbnailAnimate(ScreenEditorActivity.this.mScreenAnimatorHelper.mThumbnailAnimatorCallback);
            } else if (ScreenEditorActivity.this.isFromNormalShare()) {
                ScreenEditorActivity.this.mScreenShareView.startShareViewAnimator(ScreenEditorActivity.this.mScreenAnimatorHelper.mShareViewAnimatorCallback);
            }
        }
    };
    /* access modifiers changed from: private */
    public ScreenDeleteDialogFragment.DialogClickListener mDeleteDialogClickListener = new ScreenDeleteDialogFragment.DialogClickListener() {
        public void onDelete() {
            ScreenEditorActivity.this.deleteSourceFileAndFinish();
            ScreenEditorStatUtils.statBtnDeletelClick(ScreenEditorActivity.this.mIsLongScreenMode);
        }
    };
    private View mDeleteView;
    private Fragment mDoodleFragment;
    /* access modifiers changed from: private */
    public DraftManager mDraftManager;
    /* access modifiers changed from: private */
    public Guideline mEditBottomLine;
    /* access modifiers changed from: private */
    public ConstraintLayout mEditTopLayoutView;
    /* access modifiers changed from: private */
    public Guideline mEditTopLine;
    private FrameLayout mEditViewLayoutView;
    private ExportFragment.Callbacks mExportCallbacks = new ExportFragment.Callbacks() {
        private long mStartTime;

        public boolean doExport() {
            this.mStartTime = System.currentTimeMillis();
            ScreenEditorActivity.this.mExportTask.prepareToExport(ScreenEditorActivity.this.mDraftManager);
            Log.d("ScreenEditorActivity", "doExport start");
            boolean export = ScreenEditorActivity.this.mDraftManager.export(ScreenEditorActivity.this.mScreenRenderManager.getRenderBitmap(), ScreenEditorActivity.this.mExportTask.getExportUri());
            Log.d("ScreenEditorActivity", "doExport end, use time: %d", (Object) Long.valueOf(System.currentTimeMillis() - this.mStartTime));
            return ScreenEditorActivity.this.mExportTask.onExport(ScreenEditorActivity.this.mDraftManager, export);
        }

        public void onCancelled(boolean z) {
            ScreenEditorActivity.this.mExportTask.onCancelled(z);
        }

        public void onExported(boolean z) {
            ScreenEditorActivity.this.mExportTask.onPostExport(z);
            ScreenEditorActivity.this.mExportTask.closeExportDialog();
            if (!z) {
                ToastUtils.makeText((Context) ScreenEditorActivity.this, (int) R.string.main_save_error_msg);
            } else if (ScreenEditorActivity.this.isSharePage()) {
                ScreenEditorActivity screenEditorActivity = ScreenEditorActivity.this;
                screenEditorActivity.parseUriAndShare(screenEditorActivity.mExportTask.getExportUri());
            } else {
                ScreenEditorActivity.this.deleteSourceFileAndFinish();
            }
        }
    };
    /* access modifiers changed from: private */
    public ExportTask mExportTask;
    private View mFinishEditorView;
    private InitializeController mInitializeController;
    /* access modifiers changed from: private */
    public boolean mIsFromLongScreen;
    private boolean mIsFromSendMode;
    /* access modifiers changed from: private */
    public boolean mIsLongCropShow;
    /* access modifiers changed from: private */
    public boolean mIsLongScreenMode;
    /* access modifiers changed from: private */
    public boolean mIsMenuExpand;
    private boolean mIsShared;
    /* access modifiers changed from: private */
    public ILongCropEditorController mLongCropEditorController;
    private View mLongCropLayout;
    private ScreenLongCropFragment.ScreenLongCropCallback mLongScreenCropCallback = new ScreenLongCropFragment.ScreenLongCropCallback() {
        public int[] getScreenRect() {
            return ScreenEditorActivity.this.getIntent().getIntArrayExtra("ThumbnailRect");
        }

        public Bitmap onLoadPreview() {
            return ScreenEditorActivity.this.mDraftManager.getPreview();
        }

        public void startAnimator() {
            ScreenEditorActivity.this.mScreenAnimatorHelper.startLongCropEditPageEnterAnimator();
        }
    };
    /* access modifiers changed from: private */
    public Guideline mMenuBottomLine;
    /* access modifiers changed from: private */
    public FrameLayout mMenuLayoutView;
    /* access modifiers changed from: private */
    public Guideline mMenuTopLine;
    private Fragment mMosaicFragment;
    private Fragment mNavFragment;
    /* access modifiers changed from: private */
    public boolean mNeedExported = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.action_back:
                    if (ScreenEditorActivity.this.isFromNormalShare()) {
                        ScreenEditorActivity.this.finish();
                        return;
                    }
                    int unused = ScreenEditorActivity.this.mPageMode = 0;
                    ScreenEditorActivity screenEditorActivity = ScreenEditorActivity.this;
                    screenEditorActivity.changeWithMode(screenEditorActivity.mPageMode);
                    return;
                case R.id.operation_revert:
                    ScreenEditorActivity.this.mScreenEditorListener.doRevert();
                    ScreenEditorActivity.this.updateLongScreenPreviewShow();
                    ScreenEditorStatUtils.statRevertClick(ScreenEditorActivity.this.mIsLongScreenMode);
                    return;
                case R.id.operation_revoke:
                    ScreenEditorActivity.this.mScreenEditorListener.doRevoke();
                    ScreenEditorActivity.this.updateLongScreenPreviewShow();
                    ScreenEditorStatUtils.statRevokeClick(ScreenEditorActivity.this.mIsLongScreenMode);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public PhotoPageFragmentBase.OnFilesProcessedListener mOnFilesProcessedListener;
    /* access modifiers changed from: private */
    public ChooserFragment.OnIntentSelectedListener mOnIntentSelectedListener = new ChooserFragment.OnIntentSelectedListener() {
        public void onIntentSelected(Intent intent) {
            if (intent != null) {
                Intent unused = ScreenEditorActivity.this.mSharePendingIntent = intent;
                ScreenEditorActivity.this.doExportTask();
            }
        }
    };
    private ChooserFragment.OnMishareClickedListener mOnMishareClickedListener = new ChooserFragment.OnMishareClickedListener() {
        public void onMishareClicked(PhotoPageFragmentBase.OnFilesProcessedListener onFilesProcessedListener) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.mishare.connectivity", "MiShareGalleryTransferView"));
            PhotoPageFragmentBase.OnFilesProcessedListener unused = ScreenEditorActivity.this.mOnFilesProcessedListener = onFilesProcessedListener;
            ScreenEditorActivity.this.mOnIntentSelectedListener.onIntentSelected(intent);
        }
    };
    /* access modifiers changed from: private */
    public int mPageMode;
    private View mRevertView;
    private View mRevokeView;
    /* access modifiers changed from: private */
    public ScreenAnimatorHelper mScreenAnimatorHelper;
    /* access modifiers changed from: private */
    public ScreenDeleteDialogFragment mScreenDeleteDialogFragment;
    private ScreenNavFragment.Callback mScreenEditBottomCallback = new ScreenNavFragment.Callback() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i, int i2, ScreenNavigatorData screenNavigatorData) {
            if (screenNavigatorData.id == 1) {
                int unused = ScreenEditorActivity.this.mPageMode = 1;
                ScreenEditorActivity screenEditorActivity = ScreenEditorActivity.this;
                screenEditorActivity.changeWithMode(screenEditorActivity.mPageMode);
            } else if (screenNavigatorData.id != 5) {
                if (ScreenEditorActivity.this.mLongCropEditorController != null) {
                    ScreenEditorActivity.this.mScreenEditorListener.setLongCropEntry(ScreenEditorActivity.this.mLongCropEditorController.onExport());
                }
                ScreenEditorActivity.this.showEditorNav(recyclerView, screenNavigatorData, i, i2);
                boolean unused2 = ScreenEditorActivity.this.mIsLongCropShow = false;
                ScreenEditorActivity.this.configEditModeView();
            } else if (i2 == i) {
                return true;
            } else {
                if (ScreenEditorActivity.this.mIsMenuExpand) {
                    ScreenEditorActivity.this.hideMenuFragment();
                }
                boolean unused3 = ScreenEditorActivity.this.mIsLongCropShow = true;
                ScreenEditorActivity.this.showLongCropFragment();
                ((ScreenNavAdapter) recyclerView.getAdapter()).setSelection(i);
                ScreenEditorActivity.this.mScreenLongCropFragment.setScreenDrawEntry(ScreenEditorActivity.this.mScreenEditorListener.onExport().mDrawEntry);
            }
            ScreenEditorStatUtils.statNavItemClick(ScreenEditorActivity.this.mIsLongScreenMode, screenNavigatorData, ScreenEditorActivity.this.mIsMenuExpand);
            return true;
        }
    };
    private ScreenEditorFragment mScreenEditorFragment;
    /* access modifiers changed from: private */
    public IScreenEditorController mScreenEditorListener;
    /* access modifiers changed from: private */
    public ScreenLongCropFragment mScreenLongCropFragment;
    private ScreenRenderCallback mScreenRenderCallback = new ScreenRenderCallback() {
        public void onComplete(boolean z) {
            if (!z) {
                ScreenEditorActivity.this.configShareModeView();
                ScreenEditorActivity.this.mScreenAnimatorHelper.startSharePageEnterAnimator();
            } else if (ScreenEditorActivity.this.mNeedExported) {
                ScreenEditorActivity.this.mExportTask.showExportDialog();
                ScreenEditorStatUtils.statShowExportFragment(ScreenEditorActivity.this.getRenderData(), ScreenEditorActivity.this.mIsLongScreenMode);
            } else if (ScreenEditorActivity.this.isSharePage()) {
                ScreenEditorActivity screenEditorActivity = ScreenEditorActivity.this;
                screenEditorActivity.parseUriAndShare(screenEditorActivity.mExportTask.getExportUri());
            } else {
                ScreenEditorActivity.this.deleteSourceFileAndFinish();
            }
        }

        public void setShareBitmap(Bitmap bitmap, boolean z) {
            ScreenEditorActivity.this.mScreenShareView.setShareBitmap(bitmap, false);
        }
    };
    /* access modifiers changed from: private */
    public ScreenRenderManager mScreenRenderManager;
    /* access modifiers changed from: private */
    public FrameLayout mScreenRootBg;
    /* access modifiers changed from: private */
    public ScreenShareView mScreenShareView;
    /* access modifiers changed from: private */
    public Guideline mShareBottomLine;
    private FrameLayout mShareLayoutView;
    /* access modifiers changed from: private */
    public Intent mSharePendingIntent;
    /* access modifiers changed from: private */
    public ConstraintLayout mShareTopLayoutView;
    /* access modifiers changed from: private */
    public Guideline mShareTopLine;
    private SingleClickListener mSingleClickListener = new SingleClickListener() {
        /* access modifiers changed from: protected */
        public void onSingleClick(View view) {
            int id = view.getId();
            if (id == R.id.screen_delete) {
                if (ScreenEditorActivity.this.mScreenDeleteDialogFragment == null) {
                    ScreenDeleteDialogFragment unused = ScreenEditorActivity.this.mScreenDeleteDialogFragment = new ScreenDeleteDialogFragment();
                }
                ScreenEditorActivity.this.mScreenDeleteDialogFragment.setDialogClickListener(ScreenEditorActivity.this.mDeleteDialogClickListener);
                ScreenEditorActivity.this.mScreenDeleteDialogFragment.showAllowingStateLoss(ScreenEditorActivity.this.getFragmentManager(), "ScreenDeleteDialogFragment");
            } else if (id == R.id.screen_finish) {
                ScreenEditorActivity.this.mScreenEditorListener.export();
                ScreenEditorActivity.this.doExportTask();
                ScreenEditorStatUtils.statBtnSavelClick(ScreenEditorActivity.this.mIsLongScreenMode);
            }
        }
    };
    private Fragment mTextFragment;

    /* access modifiers changed from: private */
    public void checkAndDoRender(boolean z) {
        ILongCropEditorController iLongCropEditorController = this.mLongCropEditorController;
        boolean z2 = iLongCropEditorController != null && iLongCropEditorController.isModifiedBaseLast();
        if (this.mScreenEditorListener.isModifiedBaseLast() || z2) {
            this.mNeedExported = true;
            this.mScreenRenderManager.renderBitmap(z, getRenderData(), this.mScreenRenderCallback);
            return;
        }
        this.mScreenRenderCallback.onComplete(z);
    }

    /* access modifiers changed from: private */
    public void deleteSourceFileAndFinish() {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job() {
            public final Object run(ThreadPool.JobContext jobContext) {
                return ScreenEditorActivity.this.lambda$deleteSourceFileAndFinish$85$ScreenEditorActivity(jobContext);
            }
        }, new FutureHandler<String>() {
            public void onPostExecute(Future<String> future) {
                if (!future.isCancelled()) {
                    String str = future.get();
                    if (!TextUtils.isEmpty(str)) {
                        DeletionTask deletionTask = new DeletionTask();
                        DeletionTask.Param param = new DeletionTask.Param(new String[]{str}, 0, 57);
                        deletionTask.showProgress(ScreenEditorActivity.this.mActivity);
                        deletionTask.setCompleteListener(ScreenEditorActivity.this.mCompleteListener);
                        deletionTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new DeletionTask.Param[]{param});
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void doExportTask() {
        if (hasEdited()) {
            checkAndDoRender(true);
        } else if (isSharePage()) {
            parseUriAndShare(this.mExportTask.getSource());
        } else {
            finish();
        }
    }

    private void doInitialize() {
        this.mInitializeController = new InitializeController(this, this.mDecoderCallbacks);
        this.mInitializeController.doInitialize();
    }

    /* access modifiers changed from: private */
    public void doShare(Uri uri) {
        this.mIsShared = false;
        Intent intent = this.mSharePendingIntent;
        if (intent == null || uri == null) {
            Log.e("ScreenEditorActivity", "share error.");
            return;
        }
        String packageName = intent.getComponent().getPackageName();
        if (GalleryOpenProvider.needReturnContentUri((Context) this.mActivity, this.mSharePendingIntent)) {
            uri = GalleryOpenProvider.translateToContent(uri.getPath());
            if (!TextUtils.isEmpty(packageName)) {
                this.mActivity.grantUriPermission(packageName, uri, 1);
                this.mActivity.grantUriPermission("com.miui.xman", uri, 1);
            }
        }
        if ("com.miui.mishare.connectivity".equals(packageName)) {
            this.mOnFilesProcessedListener.onFilesProcessed(Arrays.asList(new Uri[]{uri}));
            this.mSharePendingIntent = null;
            return;
        }
        this.mSharePendingIntent.setAction("android.intent.action.SEND");
        this.mSharePendingIntent.putExtra("android.intent.extra.STREAM", uri);
        this.mSharePendingIntent.addFlags(268435456);
        this.mSharePendingIntent.addFlags(134742016);
        Intent intent2 = new Intent("miui.intent.action.XMAN_SHARE_MANAGER");
        intent2.setPackage("com.miui.xman");
        if (!packageName.equals("com.tencent.mm") || !IntentUtil.IS_XMAN_AVAILABLE.get(null).booleanValue()) {
            startActivityForResult(this.mSharePendingIntent, 1);
            return;
        }
        intent2.putExtra("android.intent.extra.INTENT", this.mSharePendingIntent);
        startActivityForResult(intent2, 1);
    }

    private String getFilePathWithUri(Context context, Uri uri) {
        if (uri == null) {
            return "";
        }
        ImageDownloader.Scheme ofUri = ImageDownloader.Scheme.ofUri(uri.toString());
        return ofUri == ImageDownloader.Scheme.FILE ? ImageDownloader.Scheme.FILE.crop(uri.toString()) : ofUri == ImageDownloader.Scheme.CONTENT ? ContentUtils.getValidFilePathForContentUri(context, uri) : "";
    }

    private boolean hasEdited() {
        ILongCropEditorController iLongCropEditorController = this.mLongCropEditorController;
        boolean z = (iLongCropEditorController == null || iLongCropEditorController.onExport() == null || !this.mLongCropEditorController.onExport().isModified()) ? false : true;
        IScreenEditorController iScreenEditorController = this.mScreenEditorListener;
        return z || (iScreenEditorController != null && iScreenEditorController.isModified());
    }

    /* access modifiers changed from: private */
    public void hideMenuFragment() {
        this.mIsMenuExpand = false;
        this.mScreenAnimatorHelper.startMenuAnimator(this.mIsMenuExpand);
    }

    private void initScreenAnimatorHelper() {
        this.mScreenAnimatorHelper = new ScreenAnimatorHelper(this, getIntent().getIntArrayExtra("ThumbnailRect"), this.mAnimatorViewCallback);
        if (isFromNormalEdit()) {
            this.mScreenAnimatorHelper.setViewsAlpha(0.0f, this.mScreenRootBg, this.mBottomLayoutView);
        } else if (isFromNormalShare()) {
            this.mScreenAnimatorHelper.setViewsAlpha(0.0f, this.mScreenRootBg, this.mBottomLayoutView, this.mShareTopLayoutView);
        }
    }

    private void initView() {
        this.mDeleteView = findViewById(R.id.screen_delete);
        this.mDeleteView.setOnClickListener(this.mSingleClickListener);
        this.mFinishEditorView = findViewById(R.id.screen_finish);
        this.mFinishEditorView.setOnClickListener(this.mSingleClickListener);
        this.mRevokeView = findViewById(R.id.operation_revoke);
        this.mRevokeView.setOnClickListener(this.mOnClickListener);
        this.mRevertView = findViewById(R.id.operation_revert);
        this.mRevertView.setOnClickListener(this.mOnClickListener);
        this.mScreenRootBg = (FrameLayout) findViewById(R.id.screen_root_layout_bg);
        this.mEditTopLayoutView = (ConstraintLayout) findViewById(R.id.screen_edit_top_layout);
        this.mBottomLayoutView = (FrameLayout) findViewById(R.id.bottom_area_layout);
        this.mShareTopLayoutView = (ConstraintLayout) findViewById(R.id.top_send_layout);
        this.mEditViewLayoutView = (FrameLayout) findViewById(R.id.screen_editor_layout);
        this.mLongCropLayout = findViewById(R.id.screen_longcrop_layout);
        this.mMenuLayoutView = (FrameLayout) findViewById(R.id.screen_menu_layout);
        this.mScreenShareView = (ScreenShareView) findViewById(R.id.share_view);
        this.mShareTopLayoutView = (ConstraintLayout) findViewById(R.id.top_send_layout);
        this.mShareLayoutView = (FrameLayout) findViewById(R.id.share_view_layout);
        this.mBackView = (ImageView) findViewById(R.id.action_back);
        this.mBackView.setOnClickListener(this.mOnClickListener);
        this.mShareTopLine = (Guideline) findViewById(R.id.share_top_guide_line);
        this.mShareBottomLine = (Guideline) findViewById(R.id.share_bottom_guide_line);
        this.mCommonLine = (Guideline) findViewById(R.id.common_base_guide_line);
        this.mMenuTopLine = (Guideline) findViewById(R.id.menu_top_guide_line);
        this.mMenuBottomLine = (Guideline) findViewById(R.id.menu_bottom_guide_line);
        this.mEditBottomLine = (Guideline) findViewById(R.id.bottom_guide_line);
        this.mEditTopLine = (Guideline) findViewById(R.id.top_guide_line);
        this.mRevokeView.setEnabled(false);
        this.mRevertView.setEnabled(false);
        this.mScreenEditorFragment = new ScreenEditorFragment();
        getFragmentManager().beginTransaction().add(R.id.screen_editor_layout, this.mScreenEditorFragment, "fragment_tag_editor").commit();
        if (isFromNormalShare()) {
            configShareModeView();
        } else {
            configEditModeView();
        }
        showBottomViewWithMode(this.mPageMode);
    }

    /* access modifiers changed from: private */
    public boolean isFromNormalEdit() {
        return !this.mIsFromLongScreen && !this.mIsFromSendMode;
    }

    /* access modifiers changed from: private */
    public boolean isFromNormalShare() {
        return !this.mIsFromLongScreen && this.mIsFromSendMode;
    }

    private void onCtaChecked() {
        if (DocumentProviderPreference.isFirstEntrance() && DocumentProviderUtils.needRequestExternalSDCardPermission(this)) {
            DocumentProviderUtils.startFirstEntrancyPermissionActivityForResult(this);
        }
    }

    /* access modifiers changed from: private */
    public void parseUriAndShare(Uri uri) {
        if (!this.mIsShared) {
            this.mIsShared = true;
            ThreadManager.getMiscPool().submit(new ThreadPool.Job(uri) {
                private final /* synthetic */ Uri f$1;

                {
                    this.f$1 = r2;
                }

                public final Object run(ThreadPool.JobContext jobContext) {
                    return ScreenEditorActivity.this.lambda$parseUriAndShare$90$ScreenEditorActivity(this.f$1, jobContext);
                }
            }, new FutureHandler<String>() {
                public void onPostExecute(Future<String> future) {
                    if (!TextUtils.isEmpty(future.get())) {
                        ScreenEditorActivity.this.doShare(Uri.parse(future.get()));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showEditorNav(RecyclerView recyclerView, ScreenNavigatorData screenNavigatorData, int i, int i2) {
        if (i2 == i) {
            if (this.mIsMenuExpand) {
                hideMenuFragment();
            } else {
                showMenuFragment(screenNavigatorData);
            }
        } else if (this.mScreenEditorListener.setCurrentScreenEditor(screenNavigatorData.id)) {
            ((ScreenNavAdapter) recyclerView.getAdapter()).setSelection(i);
            if (this.mIsMenuExpand) {
                hideMenuFragment();
            }
        }
    }

    /* access modifiers changed from: private */
    public void showLongCropFragment() {
        if (this.mScreenLongCropFragment == null) {
            this.mScreenLongCropFragment = new ScreenLongCropFragment();
            this.mScreenLongCropFragment.setArguments(new Bundle());
            getFragmentManager().beginTransaction().add(R.id.screen_longcrop_layout, this.mScreenLongCropFragment, "fragment_tag_long_crop").commitAllowingStateLoss();
            this.mScreenRenderManager.setOriginLoadedListener(new ScreenRenderManager.OnOriginLoadedListener() {
                public final void onRefresh(Bitmap bitmap) {
                    ScreenEditorActivity.this.lambda$showLongCropFragment$89$ScreenEditorActivity(bitmap);
                }
            });
            this.mScreenRenderManager.decodeOrigin();
        }
        configEditModeView();
    }

    private void showMenuFragment(ScreenNavigatorData screenNavigatorData) {
        Fragment fragment;
        Fragment findFragmentById = getFragmentManager().findFragmentById(R.id.screen_menu_layout);
        if (screenNavigatorData.id == 2) {
            if (this.mDoodleFragment == null) {
                this.mDoodleFragment = new ScreenDoodleMenuFragment();
            }
            fragment = this.mDoodleFragment;
        } else if (screenNavigatorData.id == 3) {
            if (this.mTextFragment == null) {
                this.mTextFragment = new ScreenTextMenuFragment();
            }
            fragment = this.mTextFragment;
        } else if (screenNavigatorData.id == 4) {
            if (this.mMosaicFragment == null) {
                this.mMosaicFragment = new ScreenMosaicMenuFragment();
            }
            fragment = this.mMosaicFragment;
        } else {
            fragment = null;
        }
        if (fragment != null) {
            if (findFragmentById == null) {
                getFragmentManager().beginTransaction().add(R.id.screen_menu_layout, fragment).commitAllowingStateLoss();
            } else {
                getFragmentManager().beginTransaction().replace(R.id.screen_menu_layout, fragment).commitAllowingStateLoss();
            }
            this.mIsMenuExpand = true;
            this.mScreenAnimatorHelper.startMenuAnimator(this.mIsMenuExpand);
        }
    }

    /* access modifiers changed from: private */
    public void updateLongScreenPreviewShow() {
        ILongCropEditorController iLongCropEditorController = this.mLongCropEditorController;
        if (iLongCropEditorController != null) {
            iLongCropEditorController.setScreenDrawEntry(this.mScreenEditorListener.onExport().mDrawEntry);
        }
    }

    public void changeWithMode(final int i) {
        if (i == 0) {
            this.mScreenAnimatorHelper.startSharePageExitAnimator(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ScreenEditorActivity.this.mScreenEditorListener.checkTextEditor(false);
                    ScreenEditorActivity.this.showBottomViewWithMode(0);
                    ScreenEditorActivity.this.configEditModeView();
                    ScreenEditorActivity.this.mScreenAnimatorHelper.startEditPageEnterAnimator();
                }
            });
        } else if (i == 1) {
            this.mScreenAnimatorHelper.startEditPageExitAnimator(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ScreenEditorActivity.this.mScreenEditorListener.checkTextEditor(true);
                    ScreenEditorActivity.this.showBottomViewWithMode(i);
                    ScreenEditorActivity.this.checkAndDoRender(false);
                }
            });
        }
    }

    public void configEditModeView() {
        this.mShareTopLayoutView.setVisibility(8);
        this.mShareLayoutView.setVisibility(8);
        this.mEditTopLayoutView.setVisibility(0);
        this.mBottomLayoutView.setPadding(0, 0, 0, 0);
        if (!this.mIsLongScreenMode || !this.mIsLongCropShow) {
            this.mLongCropLayout.setVisibility(8);
            this.mEditViewLayoutView.setVisibility(0);
        } else {
            this.mLongCropLayout.setVisibility(0);
            this.mEditViewLayoutView.setVisibility(8);
        }
        if (WindowCompat.isNotch(this)) {
            int topNotchHeight = WindowCompat.getTopNotchHeight(this);
            this.mEditTopLine.setGuidelineBegin(getResources().getDimensionPixelSize(R.dimen.screen_editor_top_height) + topNotchHeight);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mEditTopLayoutView.getLayoutParams();
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.screen_editor_top_height) + topNotchHeight;
            this.mEditTopLayoutView.setLayoutParams(layoutParams);
            this.mEditTopLayoutView.setPadding(0, topNotchHeight, 0, 0);
        } else {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mEditTopLayoutView.getLayoutParams();
            layoutParams2.height = getResources().getDimensionPixelSize(R.dimen.screen_editor_top_height);
            this.mEditTopLayoutView.setLayoutParams(layoutParams2);
        }
        this.mScreenRootBg.setBackgroundColor(this.mActivity.getResources().getColor(R.color.screen_editor_view_background));
    }

    public void configShareModeView() {
        this.mEditTopLayoutView.setVisibility(8);
        this.mShareLayoutView.setVisibility(0);
        this.mShareTopLayoutView.setVisibility(0);
        this.mLongCropLayout.setVisibility(8);
        this.mEditViewLayoutView.setVisibility(8);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBottomLayoutView.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.screen_editor_base_guideline_end);
        layoutParams.topToBottom = R.id.common_base_guide_line;
        this.mBottomLayoutView.setLayoutParams(layoutParams);
        if (!isFromNormalShare()) {
            this.mShareLayoutView.setPadding(getResources().getDimensionPixelSize(R.dimen.screen_editor_share_view_padding_start), 0, getResources().getDimensionPixelSize(R.dimen.screen_editor_share_view_padding_end), 0);
        }
        if (WindowCompat.isNotch(this)) {
            int topNotchHeight = WindowCompat.getTopNotchHeight(this);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mShareTopLayoutView.getLayoutParams();
            layoutParams2.height = getResources().getDimensionPixelSize(R.dimen.screen_editor_share_mode_title_height) + topNotchHeight;
            this.mShareTopLayoutView.setLayoutParams(layoutParams2);
        }
        this.mScreenRootBg.setBackgroundColor(this.mActivity.getResources().getColor(R.color.photo_editor_white));
    }

    public ScreenRenderData getRenderData() {
        ScreenRenderData onExport = this.mScreenEditorListener.onExport();
        ILongCropEditorController iLongCropEditorController = this.mLongCropEditorController;
        if (!(iLongCropEditorController == null || iLongCropEditorController.onExport() == null)) {
            onExport.setLongCropEntry(this.mLongCropEditorController.onExport());
        }
        return onExport;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.permission_storage_desc), true)};
    }

    public boolean isMenuExpand() {
        return this.mIsMenuExpand;
    }

    public boolean isSharePage() {
        return this.mPageMode == 1;
    }

    public /* synthetic */ String lambda$deleteSourceFileAndFinish$85$ScreenEditorActivity(ThreadPool.JobContext jobContext) {
        return getFilePathWithUri(this.mActivity, this.mExportTask.getSource());
    }

    public /* synthetic */ void lambda$new$86$ScreenEditorActivity(Object obj) {
        Log.d("ScreenEditorActivity", "delete Screenshots file success!");
        finish();
    }

    public /* synthetic */ void lambda$onAttachFragment$88$ScreenEditorActivity(boolean z, boolean z2, boolean z3) {
        this.mRevokeView.setEnabled(z2);
        this.mRevertView.setEnabled(z3);
    }

    public /* synthetic */ void lambda$onPermissionsChecked$87$ScreenEditorActivity(boolean z) {
        onCtaChecked();
    }

    public /* synthetic */ String lambda$parseUriAndShare$90$ScreenEditorActivity(Uri uri, ThreadPool.JobContext jobContext) {
        return getFilePathWithUri(this.mActivity, uri);
    }

    public /* synthetic */ void lambda$showLongCropFragment$89$ScreenEditorActivity(Bitmap bitmap) {
        this.mScreenLongCropFragment.setOriginBitmap(bitmap);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            this.mNeedExported = false;
        }
        DocumentProviderUtils.handleRequestPermissionResult(this, i, i2, intent);
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ExportFragment) {
            ((ExportFragment) fragment).setCallbacks(this.mExportCallbacks);
        } else if (fragment instanceof IScreenEditorController) {
            this.mScreenEditorListener = (IScreenEditorController) fragment;
            this.mScreenEditorListener.setOperationUpdateListener(new OperationUpdateListener() {
                public final void onOperationUpdate(boolean z, boolean z2, boolean z3) {
                    ScreenEditorActivity.this.lambda$onAttachFragment$88$ScreenEditorActivity(z, z2, z3);
                }
            });
            this.mScreenEditorListener.setLongCrop(this.mIsLongScreenMode);
        } else if (fragment instanceof ILongCropEditorController) {
            this.mLongCropEditorController = (ILongCropEditorController) fragment;
            this.mLongCropEditorController.setScreenLongCropCallback(this.mLongScreenCropCallback);
        } else if (fragment instanceof ScreenNavFragment) {
            ((ScreenNavFragment) fragment).setCallback(this.mScreenEditBottomCallback);
        }
    }

    public void onAttachedToWindow() {
        if (WindowCompat.isNotch(this)) {
            SystemUiUtil.extendToStatusBar(getWindow().getDecorView());
        }
    }

    public void onBackPressed() {
        if (isFromNormalShare()) {
            finish();
        } else if (this.mPageMode == 0) {
            finish();
        } else {
            this.mPageMode = 0;
            changeWithMode(this.mPageMode);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        GalleryPreferences.CTA.setToAllowUseOnOfflineGlobal(getIntent().getBooleanExtra("allow_use_on_offline_global", false));
        super.onCreate((Bundle) null);
        WindowCompat.setCutoutModeShortEdges(getWindow());
        setContentView(R.layout.screen_editor_activity);
        this.mActivity = this;
        Intent intent = getIntent();
        this.mIsLongScreenMode = intent.getBooleanExtra(Constants.EXTRA_IS_LONG_SCREENSHOT, false);
        this.mIsFromLongScreen = intent.getBooleanExtra("FromLongScreenshot", false);
        this.mIsFromSendMode = intent.getBooleanExtra("is_from_send", false);
        this.mDraftManager = new DraftManager(this, intent.getData(), intent.getExtras());
        this.mExportTask = ExportTask.from(this);
        this.mPageMode = this.mIsFromSendMode ? 1 : 0;
        initView();
        initScreenAnimatorHelper();
        this.mScreenRenderManager = new ScreenRenderManager(this.mDraftManager);
        PermissionInjector.injectIfNeededIn(this, this, (Bundle) null);
        ScreenProviderManager.INSTANCE.onActivityCreate(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mScreenAnimatorHelper.release();
        this.mDraftManager.release();
        ExportTask exportTask = this.mExportTask;
        if (exportTask != null) {
            exportTask.closeExportDialog();
        }
        ScreenProviderManager.INSTANCE.onActivityDestroy();
        ScreenRenderManager screenRenderManager = this.mScreenRenderManager;
        if (screenRenderManager != null) {
            screenRenderManager.release();
        }
        ScreenDeleteDialogFragment screenDeleteDialogFragment = this.mScreenDeleteDialogFragment;
        if (screenDeleteDialogFragment != null) {
            screenDeleteDialogFragment.dismissSafely();
            this.mScreenDeleteDialogFragment = null;
        }
        this.mOnFilesProcessedListener = null;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().onPagePause();
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        if (GalleryPreferences.CTA.allowUseOnOfflineGlobal() || GalleryPreferences.CTA.canConnectNetwork()) {
            onCtaChecked();
        } else {
            AgreementsUtils.showUserAgreements(this, new OnAgreementInvokedListener() {
                public final void onAgreementInvoked(boolean z) {
                    ScreenEditorActivity.this.lambda$onPermissionsChecked$87$ScreenEditorActivity(z);
                }
            });
        }
        doInitialize();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ImageLoader.getInstance().onPageResume();
    }

    public void showBottomViewWithMode(int i) {
        Fragment fragment;
        String str;
        if (i == 0) {
            if (this.mNavFragment == null) {
                this.mNavFragment = ScreenNavFragment.newInstance(this.mIsLongScreenMode);
            }
            fragment = this.mNavFragment;
            str = "ScreenNavFragment";
        } else if (i == 1) {
            if (this.mChooserFragment == null) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/*");
                this.mChooserFragment = ChooserFragment.newInstance(intent, 0, true, 3);
            }
            ((ChooserFragment) this.mChooserFragment).setOnIntentSelectedListener(this.mOnIntentSelectedListener);
            ((ChooserFragment) this.mChooserFragment).setOnMishareClickedListener(this.mOnMishareClickedListener);
            fragment = this.mChooserFragment;
            str = "ChooserFragment";
        } else {
            fragment = null;
            str = "";
        }
        if (fragment != null) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            Fragment fragment2 = this.mCurrentBottomFragment;
            if (fragment2 == null) {
                beginTransaction.add(R.id.bottom_area_layout, fragment, str).commitAllowingStateLoss();
            } else if (fragment2 != fragment) {
                if (fragment.isAdded() || getFragmentManager().findFragmentByTag(str) != null) {
                    beginTransaction.hide(this.mCurrentBottomFragment).show(fragment).commitAllowingStateLoss();
                } else {
                    beginTransaction.hide(this.mCurrentBottomFragment).add(R.id.bottom_area_layout, fragment, str).commitAllowingStateLoss();
                }
            }
            this.mCurrentBottomFragment = fragment;
        }
    }
}
