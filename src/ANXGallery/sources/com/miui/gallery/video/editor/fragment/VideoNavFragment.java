package com.miui.gallery.video.editor.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.ui.menu.type.EditNavMenuView;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.adapter.VideoNavAdapter;
import com.miui.gallery.video.editor.manager.SmartVideoJudgeManager;
import com.miui.gallery.video.editor.model.MenuFragmentData;
import com.miui.gallery.video.editor.ui.MenuFragment;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;
import miui.view.animation.QuarticEaseOutInterpolator;

public class VideoNavFragment extends MenuFragment {
    private static int sAnimAppearDelay;
    private static int sAnimAppearDuration;
    private static int sAnimDisappearDuration;
    private static int sAnimOffset;
    private VideoNavAdapter mAdapter;
    private Dialog mDialog;
    private SimpleRecyclerView mNavRecyclerView;
    /* access modifiers changed from: private */
    public List<MenuFragmentData> mNavigatorDataList = new ArrayList();
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (VideoNavFragment.this.mCallback.isLoadSuccess()) {
                MenuFragmentData menuFragmentData = (MenuFragmentData) VideoNavFragment.this.mNavigatorDataList.get(i);
                if (menuFragmentData == null) {
                    return true;
                }
                VideoNavFragment.this.mCallback.changeEditMenu(menuFragmentData);
                return true;
            }
            ToastUtils.makeText(VideoNavFragment.this.mContext, (int) R.string.video_editor_video_loading);
            return false;
        }
    };
    private TextView mTvDiscard;
    private TextView mTvExport;
    /* access modifiers changed from: private */
    public int mVideoFrames = 0;
    private VideoFramesManager mVideoFramesManager;
    private boolean showAnimate = false;

    private static class VideoFramesManager {
        private WeakReference<VideoNavFragment> mFragmentRef;
        private Future<Void> mFuture;
        private FutureHandler<Void> mFutureHandler = new FutureHandler<Void>() {
            public void onPostExecute(Future<Void> future) {
                VideoNavFragment access$400 = VideoFramesManager.this.getFragment();
                if (!future.isCancelled() && access$400 != null) {
                    access$400.initRecyclerView();
                }
            }
        };
        private ThreadPool.Job mVideoFramesJob = new ThreadPool.Job() {
            public Object run(ThreadPool.JobContext jobContext) {
                VideoNavFragment access$400 = VideoFramesManager.this.getFragment();
                if (jobContext.isCancelled() || access$400 == null) {
                    return null;
                }
                int unused = access$400.mVideoFrames = ToolsUtil.getVideoFrameRate(access$400.mVideoEditor.getVideoPath());
                return null;
            }
        };

        public VideoFramesManager(VideoNavFragment videoNavFragment) {
            this.mFragmentRef = new WeakReference<>(videoNavFragment);
        }

        /* access modifiers changed from: private */
        public VideoNavFragment getFragment() {
            WeakReference<VideoNavFragment> weakReference = this.mFragmentRef;
            if (weakReference == null) {
                return null;
            }
            return (VideoNavFragment) weakReference.get();
        }

        public void handleVideoFrames() {
            if (getFragment() == null) {
                Log.d("VideoNavFragment", "the mFragmentRef is null. ");
                return;
            }
            Future<Void> future = this.mFuture;
            if (future != null) {
                future.cancel();
            }
            this.mFuture = ThreadManager.getMiscPool().submit(this.mVideoFramesJob, this.mFutureHandler);
        }

        public void onDestroy() {
            Future<Void> future = this.mFuture;
            if (future != null) {
                future.cancel();
                this.mFuture = null;
            }
        }
    }

    private void initAnimatorData() {
        if (sAnimOffset == 0) {
            sAnimOffset = getActivity().getResources().getDimensionPixelSize(R.dimen.video_editor_enter_sub_editor_main_menu_offset);
        }
        if (sAnimAppearDuration == 0) {
            sAnimAppearDuration = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_main_menu_appear_duration);
        }
        if (sAnimDisappearDuration == 0) {
            sAnimDisappearDuration = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_main_menu_disappear_duration);
        }
        if (sAnimAppearDelay == 0) {
            sAnimAppearDelay = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_main_menu_appear_delay);
        }
    }

    private void initData() {
        Log.d("VideoNavFragment", "the video fps is: %s", (Object) Integer.valueOf(this.mVideoFrames));
        this.mNavigatorDataList = this.mCallback.getNavigatorData(this.mVideoFrames);
    }

    private void initListener() {
        this.mTvExport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VideoNavFragment.this.doApply();
            }
        });
        this.mTvDiscard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VideoNavFragment.this.doCancel();
            }
        });
    }

    /* access modifiers changed from: private */
    public void initRecyclerView() {
        int i;
        int i2;
        initData();
        this.mAdapter = new VideoNavAdapter(this.mNavigatorDataList);
        this.mNavRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        if (this.mNavigatorDataList.get(0).module == 17) {
            i2 = getResources().getDimensionPixelSize(R.dimen.video_editor_nav_item_has_smart_effect_start);
            i = getResources().getDimensionPixelSize(R.dimen.video_editor_nav_item_has_smart_effect_horizontal_interval);
        } else {
            i2 = getResources().getDimensionPixelSize(R.dimen.video_editor_nav_item_start);
            i = getResources().getDimensionPixelSize(R.dimen.video_editor_nav_item_horizontal_interval);
        }
        int i3 = i2;
        this.mNavRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(i3, i3, i, 0, 0, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mNavRecyclerView);
    }

    public boolean doApply() {
        if (this.mCallback == null) {
            return false;
        }
        this.mCallback.onSave();
        return true;
    }

    public boolean doCancel() {
        if (this.mCallback == null) {
            return false;
        }
        this.mCallback.onCancel();
        return true;
    }

    public List<String> getCurrentEffect() {
        return null;
    }

    public int getEffectId() {
        return R.id.video_editor_nav;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initAnimatorData();
        this.mVideoFramesManager = new VideoFramesManager(this);
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        if (!this.showAnimate) {
            return null;
        }
        if (8194 == i && z) {
            return null;
        }
        ObjectAnimator objectAnimator = new ObjectAnimator();
        if (z) {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(float) sAnimOffset, 0.0f}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f})});
            objectAnimator.setInterpolator(new CubicEaseOutInterpolator());
            if (getView() != null) {
                getView().setVisibility(4);
            }
            objectAnimator.setStartDelay((long) sAnimAppearDelay);
            objectAnimator.setDuration((long) sAnimAppearDuration);
            objectAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    if (VideoNavFragment.this.getView() != null) {
                        VideoNavFragment.this.getView().setVisibility(0);
                    }
                }
            });
        } else {
            PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f, (float) (-sAnimOffset)});
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f, 0.0f})});
            objectAnimator.setInterpolator(new QuarticEaseOutInterpolator());
            objectAnimator.setDuration((long) sAnimDisappearDuration);
        }
        return objectAnimator;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new EditNavMenuView(viewGroup.getContext());
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mDialog = null;
        }
        VideoFramesManager videoFramesManager = this.mVideoFramesManager;
        if (videoFramesManager != null) {
            videoFramesManager.onDestroy();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mNavRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mTvExport = (TextView) view.findViewById(R.id.export);
        this.mTvDiscard = (TextView) view.findViewById(R.id.discard);
        this.mTvExport.setEnabled(this.mVideoEditor != null && this.mVideoEditor.hasEdit());
        initListener();
        if (!SmartVideoJudgeManager.isAvailable() || this.mVideoFrames != 0) {
            initRecyclerView();
        } else {
            this.mVideoFramesManager.handleVideoFrames();
        }
    }

    public void updateLastFragment(MenuFragment menuFragment) {
        if (menuFragment == null) {
            this.showAnimate = false;
        } else {
            this.showAnimate = true;
        }
    }
}
