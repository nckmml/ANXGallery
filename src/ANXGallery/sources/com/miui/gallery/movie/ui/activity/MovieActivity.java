package com.miui.gallery.movie.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internal.WindowCompat;
import com.miui.gallery.app.Activity;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.R;
import com.miui.gallery.movie.core.MovieManager;
import com.miui.gallery.movie.entity.ImageEntity;
import com.miui.gallery.movie.entity.MovieInfo;
import com.miui.gallery.movie.entity.MovieShareData;
import com.miui.gallery.movie.ui.factory.MovieFactory;
import com.miui.gallery.movie.ui.fragment.MovieEditorMenuFragment;
import com.miui.gallery.movie.ui.fragment.MoviePreviewMenuFragment;
import com.miui.gallery.movie.ui.fragment.MovieSavingFragment;
import com.miui.gallery.movie.ui.listener.MenuActivityListener;
import com.miui.gallery.movie.ui.listener.MenuFragmentListener;
import com.miui.gallery.movie.ui.view.MovieControllerView;
import com.miui.gallery.movie.utils.AudioFocusHelper;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.movie.utils.MovieUtils;
import com.miui.gallery.permission.core.PermissionCheckCallback;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.util.BaseMiscUtil;
import com.miui.gallery.util.GalleryOpenProviderUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.util.SystemUiUtil;
import com.miui.gallery.util.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import miui.app.ActionBar;
import miui.view.animation.CubicEaseInInterpolator;
import miui.view.animation.CubicEaseInOutInterpolator;
import miui.view.animation.CubicEaseOutInterpolator;
import miui.view.animation.QuarticEaseInOutInterpolator;
import miui.view.animation.QuarticEaseOutInterpolator;

public class MovieActivity extends Activity implements View.OnClickListener, MenuFragmentListener, PermissionCheckCallback {
    private ActionBar mActionBar;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private AudioFocusHelper mAudioFocusHelper;
    private MovieEditorMenuFragment mEditorMenuFragment;
    private View mEditorMenuView;
    private RelativeLayout.LayoutParams mEditorMovieLps;
    private boolean mIsDestroyed = false;
    private boolean mIsEditorPreview;
    private MenuActivityListener mMenuActivityListener;
    /* access modifiers changed from: private */
    public MovieInfo mMovieInfo;
    /* access modifiers changed from: private */
    public MovieManager mMovieManager;
    /* access modifiers changed from: private */
    public MovieSavingFragment mMovieSavingFragment;
    /* access modifiers changed from: private */
    public MovieShareData mMovieShareData;
    private MovieControllerView mMovieView;
    /* access modifiers changed from: private */
    public MoviePreviewMenuFragment mPreviewMenuFragment;
    private View mPreviewMenuView;
    private RelativeLayout.LayoutParams mPreviewMovieLps;
    private ViewGroup mRootView;
    /* access modifiers changed from: private */
    public int mShowMode = -1;
    private SingleClickListener mSingleClickListener = new SingleClickListener() {
        /* JADX WARNING: type inference failed for: r0v5, types: [com.miui.gallery.app.Activity, android.app.Activity] */
        /* access modifiers changed from: protected */
        public void onSingleClick(View view) {
            int id = view.getId();
            if (id == R.id.movie_title) {
                MovieActivity.this.finish();
            } else if (id == R.id.movie_share) {
                MovieActivity.this.mMovieManager.pause();
                if (MovieActivity.this.mMovieShareData == null) {
                    MovieShareData unused = MovieActivity.this.mMovieShareData = new MovieShareData();
                    MovieActivity.this.mMovieShareData.setShortVideo(MovieActivity.this.mMovieInfo.isShortVideo);
                }
                String videoPath = MovieActivity.this.mMovieShareData.getVideoPath();
                if (TextUtils.isEmpty(videoPath)) {
                    if (MovieActivity.this.mMovieSavingFragment == null) {
                        MovieSavingFragment unused2 = MovieActivity.this.mMovieSavingFragment = new MovieSavingFragment();
                    }
                    MovieActivity.this.mMovieSavingFragment.showAndShare(MovieActivity.this.mActivity, MovieActivity.this.mMovieManager, MovieActivity.this.mMovieInfo);
                    return;
                }
                MovieActivity.this.handleShareEvent(videoPath);
            }
        }
    };
    private long mStoryMovieCardId;
    private ArrayList<String> mStorySha1List;

    private void configureActionBar() {
        this.mActionBar = getActionBar();
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setDisplayShowHomeEnabled(false);
        this.mActionBar.setDisplayShowTitleEnabled(false);
        this.mActionBar.setHomeButtonEnabled(true);
        this.mActionBar.setCustomView(R.layout.movie_fragment_top);
        View customView = this.mActionBar.getCustomView();
        ((TextView) customView.findViewById(R.id.movie_title)).setOnClickListener(this);
        ((ImageView) customView.findViewById(R.id.movie_share)).setOnClickListener(this.mSingleClickListener);
        this.mActionBar.hide();
    }

    private void doDestroy() {
        if (!this.mIsDestroyed) {
            this.mMovieShareData = null;
            MovieManager movieManager = this.mMovieManager;
            if (movieManager != null) {
                movieManager.destroy();
            }
            this.mIsDestroyed = true;
        }
    }

    private void doEditorPreviewChangeAnimal(final View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        int height = view.getHeight();
        if (z) {
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) height, 0.0f})});
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    view.setVisibility(0);
                }
            });
        } else {
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) height})});
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(4);
                }
            });
            animatorSet.setupStartValues();
        }
        animatorSet.setInterpolator(new CubicEaseInOutInterpolator());
        animatorSet.setDuration((long) getResources().getInteger(R.integer.movie_editor_preview_duration));
        animatorSet.start();
    }

    private void doFullScreenChangeAnimal(final View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        int height = view.getHeight();
        if (z) {
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) height, 0.0f})});
            animatorSet.setInterpolator(new CubicEaseOutInterpolator());
            animatorSet.setDuration((long) getResources().getInteger(R.integer.movie_background_appear_duration));
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    view.setVisibility(0);
                }
            });
        } else {
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) height})});
            animatorSet.setInterpolator(new CubicEaseInInterpolator());
            animatorSet.setDuration((long) getResources().getInteger(R.integer.movie_background_disappear_duration));
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(8);
                }
            });
            animatorSet.setupStartValues();
        }
        animatorSet.start();
    }

    private void doShare(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("MovieActivity", "share outFilePath is null");
            return;
        }
        Uri translateToContent = GalleryOpenProviderUtils.translateToContent(str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("video/*");
        intent.putExtra("android.intent.extra.STREAM", translateToContent);
        intent.addFlags(268435456);
        intent.addFlags(1);
        intent.addFlags(134742016);
        List<ResolveInfo> queryIntentActivities = this.mActivity.getPackageManager().queryIntentActivities(intent, 65536);
        if (!BaseMiscUtil.isValid(queryIntentActivities)) {
            Log.e("MovieActivity", "doShare: resInfoList is invalid.");
            return;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            this.mActivity.grantUriPermission(resolveInfo.activityInfo.packageName, translateToContent, 1);
        }
        this.mActivity.startActivityForResult(Intent.createChooser(intent, this.mActivity.getString(R.string.movie_preview_share_title)), 1);
    }

    private void initMode() {
        if (this.mShowMode == -1) {
            this.mShowMode = this.mMovieInfo.isFromStory ? 1 : 3;
        }
        if (this.mShowMode == 3) {
            this.mEditorMenuFragment = (MovieEditorMenuFragment) getFragmentManager().findFragmentById(R.id.editor_panel);
            if (this.mEditorMenuFragment == null) {
                this.mEditorMenuFragment = new MovieEditorMenuFragment();
                getFragmentManager().beginTransaction().add(R.id.editor_panel, this.mEditorMenuFragment).commit();
            }
        } else {
            MoviePreviewMenuFragment moviePreviewMenuFragment = (MoviePreviewMenuFragment) getFragmentManager().findFragmentById(R.id.preview_panel);
            if (moviePreviewMenuFragment == null) {
                moviePreviewMenuFragment = new MoviePreviewMenuFragment();
                getFragmentManager().beginTransaction().add(R.id.preview_panel, moviePreviewMenuFragment).commitAllowingStateLoss();
                getFragmentManager().executePendingTransactions();
                if (this.mShowMode == 1) {
                    this.mPreviewMenuView.setVisibility(4);
                }
            }
            this.mPreviewMenuFragment = moviePreviewMenuFragment;
        }
        setForMode();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.miui.gallery.movie.ui.activity.MovieActivity, android.app.Activity] */
    private void initMovieViewLayout() {
        int fullScreenHeight = ScreenUtils.getFullScreenHeight(this);
        int topNotchHeight = WindowCompat.getTopNotchHeight(this);
        float f = (float) fullScreenHeight;
        float screenWidth = (((float) ScreenUtils.getScreenWidth()) * 1.0f) / f;
        MovieConfig.setMovieRatio(screenWidth);
        int dimension = (int) ((((f - getResources().getDimension(R.dimen.movie_controller_height)) - getResources().getDimension(R.dimen.movie_editor_view_margin_top)) - getResources().getDimension(R.dimen.movie_editor_view_margin_bottom)) - ((float) topNotchHeight));
        this.mEditorMovieLps = new RelativeLayout.LayoutParams(this.mMovieView.getLayoutParams());
        RelativeLayout.LayoutParams layoutParams = this.mEditorMovieLps;
        layoutParams.width = (int) (((float) dimension) * screenWidth);
        layoutParams.height = dimension;
        layoutParams.topMargin = ((int) getResources().getDimension(R.dimen.movie_editor_view_margin_top)) + topNotchHeight;
        this.mEditorMovieLps.addRule(14);
        this.mPreviewMovieLps = new RelativeLayout.LayoutParams(this.mMovieView.getLayoutParams());
        this.mPreviewMovieLps.width = ScreenUtils.getScreenWidth();
        RelativeLayout.LayoutParams layoutParams2 = this.mPreviewMovieLps;
        layoutParams2.height = fullScreenHeight;
        layoutParams2.topMargin = 0;
        layoutParams2.addRule(14);
        if (this.mShowMode == 3) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mMovieView.getLayoutParams();
            layoutParams3.width = this.mEditorMovieLps.width;
            layoutParams3.height = this.mEditorMovieLps.height;
            layoutParams3.topMargin = this.mEditorMovieLps.topMargin;
            this.mMovieView.setLayoutParams(layoutParams3);
            return;
        }
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mMovieView.getLayoutParams();
        layoutParams4.width = this.mPreviewMovieLps.width;
        layoutParams4.height = this.mPreviewMovieLps.height;
        layoutParams4.topMargin = this.mPreviewMovieLps.topMargin;
        this.mMovieView.setLayoutParams(layoutParams4);
    }

    private void initStoryAlbumData() {
        Intent intent = getIntent();
        if (this.mMovieInfo.isFromStory && intent != null) {
            this.mStoryMovieCardId = intent.getLongExtra("card_id", 0);
            if (this.mStorySha1List == null) {
                this.mStorySha1List = new ArrayList<>();
            }
            ClipData clipData = intent.getClipData();
            for (int i = 0; i < clipData.getItemCount(); i++) {
                String charSequence = clipData.getItemAt(i).getText().toString();
                if (!this.mStorySha1List.contains(charSequence)) {
                    this.mStorySha1List.add(charSequence);
                }
            }
        }
    }

    private void initView() {
        this.mMovieView = (MovieControllerView) findViewById(R.id.movie_controller_view);
        this.mMovieView.init(this.mMovieManager);
        this.mMovieView.post(new Runnable() {
            public final void run() {
                MovieActivity.this.lambda$initView$21$MovieActivity();
            }
        });
        this.mMovieView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MovieActivity.this.lambda$initView$22$MovieActivity(view);
            }
        });
        this.mMovieManager = this.mMovieView.getMovieManager();
        this.mPreviewMenuView = findViewById(R.id.preview_panel);
        this.mEditorMenuView = findViewById(R.id.editor_panel);
        this.mRootView = (ViewGroup) findViewById(R.id.movie_root);
        this.mMovieView.setIvPlayListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MovieActivity.this.lambda$initView$23$MovieActivity(view);
            }
        });
        this.mMovieView.setProgressChangeListener(new MovieControllerView.ProgressChangeListener() {
            public void onChanged(float f, int i) {
                if (MovieActivity.this.mPreviewMenuFragment != null) {
                    MovieActivity.this.mPreviewMenuFragment.onProgressChange(f, i);
                }
            }

            public void onPlaybackEOF() {
                if (MovieActivity.this.mPreviewMenuFragment != null) {
                    MovieActivity.this.mPreviewMenuFragment.onPlaybackEOF();
                }
                if (MovieActivity.this.mShowMode == 1) {
                    int unused = MovieActivity.this.mShowMode = 2;
                    MovieActivity.this.changeFullScreen(false);
                }
            }

            public void onStateChanged(int i) {
                if (MovieActivity.this.mPreviewMenuFragment != null) {
                    MovieActivity.this.mPreviewMenuFragment.onStateChanged(i);
                }
            }
        });
        this.mMovieView.setDeleteVisibleListener(new MovieControllerView.DeleteIconVisibleListener() {
            public final void onChanged(boolean z) {
                MovieActivity.this.lambda$initView$24$MovieActivity(z);
            }
        });
        this.mMovieView.setPreviewBtnClickListener(new SingleClickListener() {
            /* access modifiers changed from: protected */
            public void onSingleClick(View view) {
                MovieActivity.this.changeEditorPreview();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.miui.gallery.app.Activity] */
    private boolean parseIntent() {
        List<ImageEntity> imageFromClipData = MovieUtils.getImageFromClipData(this, getIntent());
        if (imageFromClipData == null || imageFromClipData.size() < 3) {
            ToastUtils.makeText((Context) this.mActivity, R.string.movie_delete_disable);
            finish();
            return false;
        } else if (this.mMovieInfo != null) {
            return true;
        } else {
            this.mMovieInfo = new MovieInfo(imageFromClipData);
            this.mMovieInfo.isFromStory = getIntent().getBooleanExtra("movie_extra_preview_mode", false);
            this.mMovieInfo.title = getIntent().getStringExtra("card_title");
            this.mMovieInfo.subTitle = getIntent().getStringExtra("card_sub_title");
            int intExtra = getIntent().getIntExtra("movie_extra_template", 0);
            this.mMovieInfo.template = MovieFactory.getTemplateNameById(intExtra);
            MovieStatUtils.statEnter(imageFromClipData.size(), this.mMovieInfo.isFromStory);
            return true;
        }
    }

    private void setForMode() {
        if (this.mShowMode == 3) {
            this.mMovieView.setPlayProgressVisible(true);
            this.mMovieView.setShowPlayBtnMode(true);
            this.mMovieView.setTouchAvailable(true);
            this.mMovieView.setDeleteClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MovieActivity.this.lambda$setForMode$25$MovieActivity(view);
                }
            });
            this.mMovieView.showPreviewBtn(true);
            return;
        }
        this.mMovieView.setTouchAvailable(false);
        this.mMovieView.setPlayProgressVisible(false);
        this.mMovieView.setShowPlayBtnMode(false);
        this.mMovieView.showPreviewBtn(false);
    }

    private void setSystemBarVisible(boolean z) {
        if (z) {
            SystemUiUtil.showSystemBars(getWindow().getDecorView());
            this.mActionBar.show();
            return;
        }
        SystemUiUtil.hideSystemBars(getWindow().getDecorView());
        this.mActionBar.hide();
    }

    public void cancelExport() {
        this.mMovieManager.cancelExport();
    }

    public void changeEditor() {
        if (this.mShowMode == 3) {
            this.mShowMode = 2;
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.hide(this.mEditorMenuFragment);
            this.mPreviewMenuFragment = (MoviePreviewMenuFragment) getFragmentManager().findFragmentById(R.id.preview_panel);
            MoviePreviewMenuFragment moviePreviewMenuFragment = this.mPreviewMenuFragment;
            if (moviePreviewMenuFragment == null) {
                this.mPreviewMenuFragment = new MoviePreviewMenuFragment();
                beginTransaction.add(R.id.preview_panel, this.mPreviewMenuFragment);
            } else {
                beginTransaction.show(moviePreviewMenuFragment);
            }
            beginTransaction.commit();
            this.mMovieView.postDelayed(new Runnable() {
                public final void run() {
                    MovieActivity.this.lambda$changeEditor$26$MovieActivity();
                }
            }, (long) getResources().getInteger(R.integer.movie_preview_appear_delay));
        } else {
            this.mShowMode = 3;
            FragmentTransaction beginTransaction2 = getFragmentManager().beginTransaction();
            beginTransaction2.hide(this.mPreviewMenuFragment);
            this.mEditorMenuFragment = (MovieEditorMenuFragment) getFragmentManager().findFragmentById(R.id.editor_panel);
            MovieEditorMenuFragment movieEditorMenuFragment = this.mEditorMenuFragment;
            if (movieEditorMenuFragment == null) {
                this.mEditorMenuFragment = new MovieEditorMenuFragment();
                beginTransaction2.add(R.id.editor_panel, this.mEditorMenuFragment);
                Bundle bundle = new Bundle();
                bundle.putLong("card_id", this.mStoryMovieCardId);
                this.mEditorMenuFragment.setArguments(bundle);
            } else {
                beginTransaction2.show(movieEditorMenuFragment);
            }
            beginTransaction2.commit();
            setSystemBarVisible(false);
        }
        setForMode();
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new QuarticEaseOutInterpolator());
        changeBounds.setStartDelay((long) getResources().getInteger(R.integer.movie_editor_appear_delay));
        changeBounds.setDuration((long) getResources().getInteger(R.integer.movie_editor_appear_duration));
        TransitionManager.beginDelayedTransition(this.mRootView, changeBounds);
        if (this.mShowMode == 3) {
            this.mMovieView.setLayoutParams(this.mEditorMovieLps);
        } else {
            this.mMovieView.setLayoutParams(this.mPreviewMovieLps);
        }
    }

    public void changeEditorPreview() {
        this.mIsEditorPreview = !this.mIsEditorPreview;
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new QuarticEaseInOutInterpolator());
        changeBounds.setDuration((long) getResources().getInteger(R.integer.movie_editor_preview_duration));
        TransitionManager.beginDelayedTransition(this.mRootView, changeBounds);
        if (this.mIsEditorPreview) {
            doEditorPreviewChangeAnimal(this.mEditorMenuView, false);
            this.mMovieView.showExtraContent(false);
            this.mMovieView.setLoopPlay(true);
            this.mMovieView.setSeekDisable(true);
            this.mMovieView.setLayoutParams(this.mPreviewMovieLps);
            this.mMovieView.setTouchAvailable(false);
            this.mMovieManager.replay();
            return;
        }
        doEditorPreviewChangeAnimal(this.mEditorMenuView, true);
        this.mMovieView.showExtraContent(true);
        this.mMovieView.setLoopPlay(false);
        this.mMovieView.setSeekDisable(false);
        this.mMovieView.setLayoutParams(this.mEditorMovieLps);
        this.mMovieView.setTouchAvailable(true);
        this.mMovieManager.pause();
    }

    public void changeFullScreen(boolean z) {
        this.mShowMode = z ? 1 : 2;
        setSystemBarVisible(!z);
        if (z) {
            doFullScreenChangeAnimal(this.mPreviewMenuView, false);
        } else {
            doFullScreenChangeAnimal(this.mPreviewMenuView, true);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.app.Activity, android.app.Activity] */
    public void export(boolean z) {
        if (this.mMovieSavingFragment == null) {
            this.mMovieSavingFragment = new MovieSavingFragment();
        }
        this.mMovieSavingFragment.show(this.mActivity, this.mMovieManager, this.mMovieInfo, z, new MovieSavingFragment.OnSavingFinishListener() {
            public final void onFinish(boolean z, boolean z2, String str) {
                MovieActivity.this.lambda$export$27$MovieActivity(z, z2, str);
            }
        });
    }

    public MovieInfo getMovieInfo() {
        return this.mMovieInfo;
    }

    public MovieManager getMovieManager() {
        return this.mMovieManager;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.permission_storage_desc), true), new RuntimePermission("android.permission.INTERNET", "", true)};
    }

    public ArrayList<String> getStoryMovieSha1() {
        return this.mStorySha1List;
    }

    public void handleShareEvent(String str) {
        MovieShareData movieShareData = this.mMovieShareData;
        if (movieShareData != null) {
            movieShareData.setVideoPath(str, this.mMovieInfo.isShortVideo);
        }
        doShare(str);
    }

    public /* synthetic */ void lambda$changeEditor$26$MovieActivity() {
        setSystemBarVisible(true);
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.content.Context, com.miui.gallery.app.Activity] */
    public /* synthetic */ void lambda$export$27$MovieActivity(boolean z, boolean z2, String str) {
        if (isDestroyed()) {
            Log.w("MovieActivity", "movie activity is finish on saving finish");
        } else if (!this.mMovieInfo.isFromStory) {
            setResult(-1);
            finish();
            MovieUtils.goDetail(this.mActivity, Uri.fromFile(new File(str)));
        } else {
            finish();
        }
    }

    public /* synthetic */ void lambda$initView$21$MovieActivity() {
        this.mMovieView.setMovieInfo(this.mMovieInfo);
        MoviePreviewMenuFragment moviePreviewMenuFragment = this.mPreviewMenuFragment;
        if (moviePreviewMenuFragment != null) {
            moviePreviewMenuFragment.updateAfterSetInfo();
        }
    }

    public /* synthetic */ void lambda$initView$22$MovieActivity(View view) {
        int i = this.mShowMode;
        if (i == 2) {
            this.mShowMode = 1;
            changeFullScreen(true);
        } else if (i == 1) {
            this.mShowMode = 2;
            changeFullScreen(false);
        } else if (this.mIsEditorPreview) {
            changeEditorPreview();
        } else {
            this.mMovieManager.pauseOrResume();
        }
    }

    public /* synthetic */ void lambda$initView$23$MovieActivity(View view) {
        this.mMovieManager.resume();
        this.mMovieView.showDeleteIcon(false);
        MenuActivityListener menuActivityListener = this.mMenuActivityListener;
        if (menuActivityListener != null) {
            menuActivityListener.onResumeClick();
        }
    }

    public /* synthetic */ void lambda$initView$24$MovieActivity(boolean z) {
        MenuActivityListener menuActivityListener = this.mMenuActivityListener;
        if (menuActivityListener != null) {
            menuActivityListener.clearEditorAdapterSelected();
        }
    }

    public /* synthetic */ void lambda$setForMode$25$MovieActivity(View view) {
        MenuActivityListener menuActivityListener = this.mMenuActivityListener;
        if (menuActivityListener != null) {
            menuActivityListener.onDeleteClick();
        }
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof MenuActivityListener) {
            this.mMenuActivityListener = (MenuActivityListener) fragment;
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initMovieViewLayout();
        setSystemBarVisible(this.mShowMode == 2);
    }

    public void onBackPressed() {
        returnClick();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.movie_title) {
            finish();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.miui.gallery.movie.ui.activity.MovieActivity, com.miui.gallery.app.Activity] */
    public void onCreate(Bundle bundle) {
        setTheme(R.style.MovieTheme);
        if (bundle != null) {
            this.mMovieInfo = (MovieInfo) bundle.getParcelable("bundle_movie_info");
            this.mShowMode = bundle.getInt("bundle_show_mode");
        }
        this.mActivity = this;
        this.mMovieManager = MovieFactory.createMovieManager(this);
        super.onCreate(bundle);
        if (parseIntent()) {
            SystemUiUtil.setLayoutFullScreen(getWindow().getDecorView(), true);
            WindowCompat.setCutoutModeShortEdges(getWindow());
            initStoryAlbumData();
            setContentView(R.layout.movie_activity);
            configureActionBar();
            initView();
            initMode();
            this.mAudioFocusHelper = new AudioFocusHelper(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        doDestroy();
        this.mMovieManager = null;
    }

    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        if (!z) {
            initMovieViewLayout();
            setSystemBarVisible(this.mShowMode == 2);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().pause();
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.abandonAudioFocus();
        }
        MovieControllerView movieControllerView = this.mMovieView;
        if (movieControllerView != null) {
            movieControllerView.onPause();
        }
        if (isFinishing()) {
            doDestroy();
        }
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MovieControllerView movieControllerView = this.mMovieView;
        if (movieControllerView != null) {
            movieControllerView.onResume();
        }
        ImageLoader.getInstance().resume();
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.requestAudioFocus();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("bundle_movie_info", this.mMovieInfo);
        bundle.putInt("bundle_show_mode", this.mShowMode);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            setSystemBarVisible(this.mShowMode == 2);
        }
    }

    public void resetShareData() {
        MovieShareData movieShareData = this.mMovieShareData;
        if (movieShareData != null) {
            movieShareData.reset(this.mMovieInfo.isShortVideo);
        }
    }

    public void returnClick() {
        if (this.mIsEditorPreview) {
            changeEditorPreview();
        } else if (this.mShowMode != 3 || !this.mMovieInfo.isFromStory) {
            finish();
        } else {
            setShowDeleteMode(false);
            changeEditor();
            MenuActivityListener menuActivityListener = this.mMenuActivityListener;
            if (menuActivityListener != null) {
                menuActivityListener.clearEditorAdapterSelected();
            }
        }
    }

    public void seek(int i) {
        this.mMovieManager.seek(i);
        this.mMovieManager.pause();
        this.mMovieView.setCurrentPlayTime(i, ((float) i) / ((float) this.mMovieManager.getTotalTime()));
    }

    public void seekToIndex(int i) {
        int seekToIndex = this.mMovieManager.seekToIndex(i);
        float totalTime = ((float) seekToIndex) / ((float) this.mMovieManager.getTotalTime());
        this.mMovieView.setCurrentPlayTime(seekToIndex, totalTime);
        MoviePreviewMenuFragment moviePreviewMenuFragment = this.mPreviewMenuFragment;
        if (moviePreviewMenuFragment != null) {
            moviePreviewMenuFragment.onProgressChange(totalTime, seekToIndex);
        }
    }

    public void setDeleteVisible(boolean z) {
        this.mMovieView.setShowDeleteMode(true);
        this.mMovieView.setDeleteVisible(z);
    }

    public void setShowDeleteMode(boolean z) {
        this.mMovieView.setShowDeleteMode(z);
    }

    public void showLoadingView() {
        this.mMovieView.showLoadingView(true);
    }

    public void updateShareData(boolean z) {
        MovieShareData movieShareData = this.mMovieShareData;
        if (movieShareData != null) {
            movieShareData.setShortVideo(z);
        }
    }

    public void updateStorySha1Data() {
        ArrayList<String> arrayList = this.mStorySha1List;
        if (arrayList == null) {
            Log.e("MovieActivity", "mStorySha1List is null. ");
            return;
        }
        arrayList.clear();
        for (int i = 0; i < this.mMovieInfo.imageList.size(); i++) {
            this.mStorySha1List.add(this.mMovieInfo.imageList.get(i).sha1);
        }
    }
}
