package com.miui.gallery.video.editor.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.Guideline;
import com.android.internal.WindowCompat;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.NexVideoEditor;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.activity.VideoEditorActivity;
import com.miui.gallery.video.editor.interfaces.IVideoEditorListener;
import com.miui.gallery.video.editor.model.MenuFragmentData;
import com.miui.gallery.video.editor.ui.AutoTrimProgressDialog;
import com.miui.gallery.video.editor.util.TempFileCollector;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.miui.gallery.video.editor.util.VideoEditorHelper;
import com.miui.gallery.video.editor.widget.DisplayWrapper;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.HashMap;
import java.util.List;

public class VideoEditorFragment extends Fragment implements VideoEditorActivity.OnBackPressedListener {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    /* access modifiers changed from: private */
    public Uri mData;
    /* access modifiers changed from: private */
    public DisplayWrapper mDisplayWrapperView;
    /* access modifiers changed from: private */
    public boolean mIsLoadSuccess;
    public IVideoEditorListener.IVideoEditorFragmentCallback mMenuFragmentCallBack = new IVideoEditorListener.IVideoEditorFragmentCallback() {
        public void changeEditMenu(MenuFragmentData menuFragmentData) {
            VideoEditorFragment.this.mVideoEditorHelper.changeEditMenu(menuFragmentData);
        }

        public List<MenuFragmentData> getNavigatorData(int i) {
            return VideoEditorFragment.this.mVideoEditorHelper.getNavigatorData(i);
        }

        public VideoEditor getVideoEditor() {
            return VideoEditorFragment.this.mVideoEditor;
        }

        public boolean isLoadSuccess() {
            return VideoEditorFragment.this.mIsLoadSuccess;
        }

        public void onCancel() {
            VideoEditorFragment.this.mVideoEditorHelper.onCancel();
        }

        public void onSave() {
            VideoEditorFragment.this.mVideoEditorHelper.onSave();
        }

        public void showNavEditMenu() {
            VideoEditorFragment.this.mVideoEditorHelper.showNavEditMenu();
        }

        public void updateAudioVoiceView(boolean z) {
            VideoEditorFragment.this.mVideoEditorHelper.updateAudioVoiceView(z);
        }

        public void updateAutoTrimView() {
            VideoEditorFragment.this.mVideoEditorHelper.updateAutoTrimView();
        }

        public void updatePlayBtnView() {
            VideoEditorFragment.this.mVideoEditorHelper.updatePlayView();
        }
    };
    /* access modifiers changed from: private */
    public View mMenuView;
    private MyStateChangeListener mMyStateChangeListener;
    ViewTreeObserver.OnGlobalFocusChangeListener mOnGlobalFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() {
        public void onGlobalFocusChanged(View view, View view2) {
            if (WindowCompat.isNotch(VideoEditorFragment.this.getActivity())) {
                VideoEditorFragment.this.mTopLine.setGuidelineBegin(WindowCompat.getTopNotchHeight(VideoEditorFragment.this.getActivity()) + VideoEditorFragment.this.getResources().getDimensionPixelSize(R.dimen.display_top_line));
            }
            VideoEditorFragment.this.mMenuView.getViewTreeObserver().removeOnGlobalFocusChangeListener(VideoEditorFragment.this.mOnGlobalFocusChangeListener);
        }
    };
    /* access modifiers changed from: private */
    public int mPlayProgress = 0;
    /* access modifiers changed from: private */
    public ProgressBar mProgressingView;
    /* access modifiers changed from: private */
    public Guideline mTopLine;
    /* access modifiers changed from: private */
    public VideoEditor mVideoEditor;
    /* access modifiers changed from: private */
    public VideoEditorHelper mVideoEditorHelper;

    private class InitializeListener extends MyVideoLoadCompletedListener {
        private InitializeListener() {
            super();
        }

        public void onCompleted() {
            if (VideoEditorFragment.this.mVideoEditor instanceof NexVideoEditor) {
                if (!((NexVideoEditor) VideoEditorFragment.this.mVideoEditor).checkIDRSupport()) {
                    GallerySamplingStatHelper.recordCountEvent("video_editor", "idr_warning");
                }
                VideoEditorFragment.this.mVideoEditor.seek(VideoEditorFragment.this.mVideoEditor.getVideoStartTime(), (VideoEditor.OnCompletedListener) null);
            }
            super.onCompleted();
        }
    }

    private class MyStateChangeListener implements VideoEditor.StateChangeListener {
        private MyStateChangeListener() {
        }

        public void onStateChanged(int i) {
            if (i == -500) {
                ToastUtils.makeText((Context) VideoEditorFragment.this.mActivity, (int) R.string.video_editor_fatal_error);
                VideoEditorFragment.this.exit();
            } else if (i == 200) {
                ToolsUtil.hideView(VideoEditorFragment.this.mProgressingView);
            } else if (i != 500) {
                if (i != 0) {
                    if (i == 1) {
                        ToolsUtil.hideView(VideoEditorFragment.this.mProgressingView);
                    } else if (i != 2) {
                        if (i == 3) {
                            ToolsUtil.hideView(VideoEditorFragment.this.mProgressingView);
                        }
                    }
                }
                ToolsUtil.hideView(VideoEditorFragment.this.mProgressingView);
            } else {
                VideoEditorFragment.this.handleNotSupportVideoFile();
            }
            VideoEditorFragment.this.mVideoEditorHelper.updatePlayView();
        }

        public void onTimeChanged(int i) {
            VideoEditorFragment.this.mDisplayWrapperView.showPlayProgress(true);
        }
    }

    private class MyVideoLoadCompletedListener implements VideoEditor.OnCompletedListener {
        private MyVideoLoadCompletedListener() {
        }

        public void onCompleted() {
            if (VideoEditorFragment.this.mVideoEditor != null) {
                if (VideoEditorFragment.this.mVideoEditor.haveSavedEditState()) {
                    VideoEditorFragment.this.mVideoEditor.restoreEditState();
                    VideoEditorFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                        public void onCompleted() {
                        }
                    });
                }
                VideoEditorFragment.this.mVideoEditorHelper.onVideoLoadCompleted();
                boolean unused = VideoEditorFragment.this.mIsLoadSuccess = true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: AutoTrimAction */
    public void lambda$initListener$123$VideoEditorFragment(final View view) {
        if (!this.mIsLoadSuccess) {
            Log.e("VideoEditorFragment", "the video has not load success.");
            return;
        }
        this.mVideoEditor.saveEditState();
        this.mVideoEditor.resetProject(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                VideoEditorFragment.this.mDisplayWrapperView.setAutoTrimEnable(false);
                VideoEditorFragment.this.mVideoEditorHelper.setVideoSaving(true);
                boolean unused = VideoEditorFragment.this.mIsLoadSuccess = false;
                AutoTrimProgressDialog.startAutoTrim(VideoEditorFragment.this.mVideoEditor, new AutoTrimProgressDialog.OnCompletedListener() {
                    public void onCompleted(boolean z, String str, int i, String str2) {
                        if (VideoEditorFragment.this.mVideoEditor == null) {
                            Log.d("VideoEditorFragment", "the video editor is null with auto trim. ");
                            return;
                        }
                        VideoEditorFragment.this.mDisplayWrapperView.setAutoTrimEnable(true);
                        VideoEditorFragment.this.mVideoEditorHelper.setVideoSaving(false);
                        if (z) {
                            Log.d("VideoEditorFragment", "AutoTrimAction is success.");
                            GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_autotrim_success");
                            TempFileCollector.add(str);
                            VideoEditorFragment.this.mVideoEditor.load(str, new MyVideoLoadCompletedListener());
                            return;
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("errormsg", String.valueOf(str2));
                        GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_autotrim_failed", hashMap);
                        ToastUtils.makeText(view.getContext(), (CharSequence) view.getResources().getString(R.string.video_editor_encode_video_error));
                        Log.e("VideoEditorFragment", "AutoTrimAction is fail, and video encode error  msg :" + str2);
                        VideoEditorFragment.this.mVideoEditor.load(VideoEditorFragment.this.mData.getPath(), new MyVideoLoadCompletedListener());
                    }
                }, VideoEditorFragment.this.getFragmentManager());
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleNotSupportVideoFile() {
        ToastUtils.makeText((Context) this.mActivity, (int) R.string.video_editor_not_support_tips);
        if (this.mData != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("file", this.mData.toString());
            GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_not_support", hashMap);
        }
        exit();
    }

    private void initListener() {
        this.mVideoEditor.addStateChangeListener(this.mMyStateChangeListener);
        this.mDisplayWrapperView.setIvPlayListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VideoEditorFragment.this.lambda$initListener$122$VideoEditorFragment(view);
            }
        });
        this.mDisplayWrapperView.setAutoTrimListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VideoEditorFragment.this.lambda$initListener$123$VideoEditorFragment(view);
            }
        });
        this.mVideoEditor.addStateChangeListener(new VideoEditor.StateChangeListener() {
            public void onStateChanged(int i) {
            }

            public void onTimeChanged(int i) {
                int unused = VideoEditorFragment.this.mCurrentTime = i;
                if (VideoEditorFragment.this.mVideoEditor != null) {
                    float projectTotalTime = ((float) i) / ((float) VideoEditorFragment.this.mVideoEditor.getProjectTotalTime());
                    VideoEditorFragment videoEditorFragment = VideoEditorFragment.this;
                    int unused2 = videoEditorFragment.mPlayProgress = (int) (projectTotalTime * videoEditorFragment.mDisplayWrapperView.getViewWidth());
                    VideoEditorFragment.this.mDisplayWrapperView.updatePlayProgress(0, 0, VideoEditorFragment.this.mPlayProgress, 0);
                }
            }
        });
        this.mDisplayWrapperView.setClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VideoEditorFragment.this.lambda$initListener$124$VideoEditorFragment(view);
            }
        });
        this.mDisplayWrapperView.setIProgress(new DisplayWrapper.IProgress() {
            int tempCurrentTime;

            public void onVideoProgressChanged() {
                int unused = VideoEditorFragment.this.mCurrentTime = this.tempCurrentTime;
            }

            public void onVideoProgressChanging(int i, float f) {
                this.tempCurrentTime = VideoEditorFragment.this.mCurrentTime;
                if (VideoEditorFragment.this.mVideoEditor != null && f > 0.0f) {
                    int projectTotalTime = (int) (((float) VideoEditorFragment.this.mVideoEditor.getProjectTotalTime()) * f);
                    boolean z = false;
                    if (i == DisplayWrapper.LEFT) {
                        if (VideoEditorFragment.this.mCurrentTime > 0) {
                            int i2 = this.tempCurrentTime;
                            this.tempCurrentTime = i2 > projectTotalTime ? i2 - projectTotalTime : 0;
                        } else {
                            return;
                        }
                    } else if (VideoEditorFragment.this.mCurrentTime < VideoEditorFragment.this.mVideoEditor.getProjectTotalTime()) {
                        this.tempCurrentTime += projectTotalTime;
                        if (this.tempCurrentTime > VideoEditorFragment.this.mVideoEditor.getProjectTotalTime()) {
                            this.tempCurrentTime = VideoEditorFragment.this.mVideoEditor.getProjectTotalTime();
                        }
                    } else {
                        return;
                    }
                    VideoEditorFragment.this.mVideoEditor.seek(this.tempCurrentTime, (VideoEditor.OnCompletedListener) null);
                    float projectTotalTime2 = ((float) this.tempCurrentTime) / ((float) VideoEditorFragment.this.mVideoEditor.getProjectTotalTime());
                    VideoEditorFragment videoEditorFragment = VideoEditorFragment.this;
                    int unused = videoEditorFragment.mPlayProgress = (int) (projectTotalTime2 * videoEditorFragment.mDisplayWrapperView.getViewWidth());
                    VideoEditorFragment.this.mDisplayWrapperView.updatePlayProgress(0, 0, VideoEditorFragment.this.mPlayProgress, 0);
                    DisplayWrapper access$600 = VideoEditorFragment.this.mDisplayWrapperView;
                    if (this.tempCurrentTime > 0) {
                        z = true;
                    }
                    access$600.showPlayProgress(z);
                }
            }
        });
    }

    private void loadData() {
        this.mData = this.mActivity.getIntent().getData();
        Uri uri = this.mData;
        if (uri == null || uri.getPath() == null) {
            handleNotSupportVideoFile();
        } else {
            this.mVideoEditor.load(this.mData.getPath(), new InitializeListener());
        }
    }

    private void playVideoAction() {
        if (!this.mIsLoadSuccess) {
            Log.e("VideoEditorFragment", "the video has not loaded success.");
            return;
        }
        this.mVideoEditorHelper.onPlayButtonClicked();
        int state = this.mVideoEditor.getState();
        if (state == 0) {
            this.mVideoEditor.play();
        } else if (state == 2) {
            this.mVideoEditor.resume();
        }
    }

    public void exit() {
        VideoEditor videoEditor = this.mVideoEditor;
        if (videoEditor != null && videoEditor.isInit()) {
            this.mVideoEditor.release();
        }
        TempFileCollector.deleteAllTempFile(GalleryApp.sGetAndroidContext());
        this.mActivity.finish();
    }

    public Uri getData() {
        return this.mData;
    }

    public int getEffectMenuContainerId() {
        View view = this.mMenuView;
        if (view != null) {
            return view.getId();
        }
        return 0;
    }

    public VideoEditor getVideoEditor() {
        return this.mVideoEditor;
    }

    public /* synthetic */ void lambda$initListener$122$VideoEditorFragment(View view) {
        playVideoAction();
    }

    public /* synthetic */ void lambda$initListener$124$VideoEditorFragment(View view) {
        if (this.mVideoEditorHelper.isWaterMarkEditMenu()) {
            if (this.mVideoEditor.getState() == 1) {
                this.mVideoEditor.pause();
            } else if (this.mVideoEditor.getState() == 2 || this.mVideoEditor.getState() == 0) {
                this.mVideoEditor.resume();
            }
        } else if (this.mVideoEditor.getState() == 1) {
            this.mVideoEditor.pause();
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        Activity activity2 = this.mActivity;
        if (activity2 instanceof VideoEditorActivity) {
            ((VideoEditorActivity) activity2).setOnBackPressedListener(this);
        }
    }

    public void onAttachMenuFragment(Fragment fragment) {
        if (fragment instanceof MenuFragment) {
            ((MenuFragment) fragment).setCallBack(this.mMenuFragmentCallBack);
        }
    }

    public boolean onBackPressed() {
        return this.mVideoEditorHelper.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mVideoEditor = VideoEditor.create(getActivity().getApplicationContext(), "nex");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_video_editor, (ViewGroup) null);
        this.mDisplayWrapperView = (DisplayWrapper) inflate.findViewById(R.id.display_wrapper);
        this.mMenuView = inflate.findViewById(R.id.menu_panel);
        this.mTopLine = (Guideline) inflate.findViewById(R.id.display_top_line);
        this.mProgressingView = (ProgressBar) inflate.findViewById(R.id.progressing);
        this.mMyStateChangeListener = new MyStateChangeListener();
        this.mVideoEditor.setDisplayWrapper(this.mDisplayWrapperView);
        loadData();
        this.mVideoEditorHelper = new VideoEditorHelper(viewGroup.getContext(), this);
        this.mVideoEditorHelper.showNavEditMenu();
        this.mMenuView.getViewTreeObserver().addOnGlobalFocusChangeListener(this.mOnGlobalFocusChangeListener);
        initListener();
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        VideoEditor videoEditor = this.mVideoEditor;
        if (videoEditor != null) {
            videoEditor.removeStateChangeListener(this.mMyStateChangeListener);
            this.mVideoEditor = null;
        }
        VideoEditorHelper videoEditorHelper = this.mVideoEditorHelper;
        if (videoEditorHelper != null) {
            videoEditorHelper.onDestroy();
        }
        TempFileCollector.deleteAllTempFile(GalleryApp.sGetAndroidContext());
    }

    public void onDetach() {
        super.onDetach();
        Activity activity = this.mActivity;
        if (activity instanceof VideoEditorActivity) {
            ((VideoEditorActivity) activity).setOnBackPressedListener((VideoEditorActivity.OnBackPressedListener) null);
        }
        this.mActivity = null;
    }

    public void onPause() {
        super.onPause();
        VideoEditor videoEditor = this.mVideoEditor;
        if (videoEditor != null) {
            videoEditor.pause();
        }
        ImageLoader.getInstance().pause();
        GallerySamplingStatHelper.recordPageEnd(getActivity(), "video_editor");
    }

    public void onResume() {
        super.onResume();
        VideoEditor videoEditor = this.mVideoEditor;
        if (videoEditor != null) {
            videoEditor.startPreview();
        }
        ImageLoader.getInstance().resume();
        this.mVideoEditorHelper.updatePlayView();
        GallerySamplingStatHelper.recordPageStart(this.mActivity, "video_editor");
    }
}
