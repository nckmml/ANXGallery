package com.miui.gallery.video.editor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.gallery.R;
import com.miui.gallery.net.base.ErrorCode;
import com.miui.gallery.net.base.ResponseListener;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.video.editor.SmartEffect;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.adapter.SmartEffectRecyclerViewAdapter;
import com.miui.gallery.video.editor.factory.SmartEffectFactory;
import com.miui.gallery.video.editor.manager.NexAssetTemplateManager;
import com.miui.gallery.video.editor.manager.SmartEffectManager;
import com.miui.gallery.video.editor.model.LocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseLocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import com.miui.gallery.video.editor.net.ResourceDownloadManager;
import com.miui.gallery.video.editor.net.VideoEditorResourceRequest;
import com.miui.gallery.video.editor.ui.SmartEffectFragment;
import com.miui.gallery.video.editor.ui.menu.SmartEffectView;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.nexstreaming.nexeditorsdk.nexTemplateManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartEffectFragment extends MenuFragment {
    private final String TAG = "SmartEffectFragment";
    /* access modifiers changed from: private */
    public SmartEffectRecyclerViewAdapter mAdapter;
    private View mCancelView;
    /* access modifiers changed from: private */
    public View mLayout;
    private ScrollLinearLayoutManager mLinearLayoutManager;
    /* access modifiers changed from: private */
    public LoadSmartEffectTemplateTask mLoadSmartEffectTemplateTask;
    private View mOkView;
    /* access modifiers changed from: private */
    public ResourceDownloadManager mResourceDownloadManager;
    private int mSavedSelectedSmartEffectIndex;
    /* access modifiers changed from: private */
    public SingleChoiceRecyclerView mSingleChoiceRecyclerView;
    /* access modifiers changed from: private */
    public SmartEffectManager mSmartEffectManager;
    /* access modifiers changed from: private */
    public ArrayList<SmartEffect> mSmartEffects = new ArrayList<>();
    private TextView mTitleView;
    /* access modifiers changed from: private */
    public Toast mToast;
    private VideoEditorResourceRequest mVideoEditorResourceRequest;
    /* access modifiers changed from: private */
    public int mVideoTotalTime;

    public static class LoadSmartEffectTemplateTask {
        /* access modifiers changed from: private */
        public Callback mCallback;
        private Future<List<nexTemplateManager.Template>> mFuture;

        interface Callback {
            void onLoadTemplateSuccess(List<nexTemplateManager.Template> list);
        }

        public void cancel() {
            Future<List<nexTemplateManager.Template>> future = this.mFuture;
            if (future != null) {
                future.cancel();
                this.mFuture = null;
            }
            this.mCallback = null;
        }

        public void execute(Callback callback) {
            Future<List<nexTemplateManager.Template>> future = this.mFuture;
            if (future != null) {
                future.cancel();
            }
            this.mCallback = callback;
            this.mFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<List<nexTemplateManager.Template>>() {
                public List<nexTemplateManager.Template> run(ThreadPool.JobContext jobContext) {
                    return NexAssetTemplateManager.getInstance().loadSmartEffectTemplateList();
                }
            }, new FutureHandler<List<nexTemplateManager.Template>>() {
                public void onPostExecute(Future<List<nexTemplateManager.Template>> future) {
                    if (!future.isCancelled() && LoadSmartEffectTemplateTask.this.mCallback != null) {
                        LoadSmartEffectTemplateTask.this.mCallback.onLoadTemplateSuccess(future.get());
                    }
                }
            });
        }
    }

    private class SmartEffectItemSelectChangeListener implements SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener {
        private SmartEffectItemSelectChangeListener() {
        }

        public boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z) {
            if (z && SmartEffectFragment.this.mHasLoadData) {
                ScrollLinearLayoutManager.onItemClick(SmartEffectFragment.this.mSingleChoiceRecyclerView, i);
                SmartEffect smartEffect = ((SmartEffectRecyclerViewAdapter) singleChoiceRecyclerViewAdapter).getSmartEffect(i);
                if (smartEffect != null) {
                    if (smartEffect.isNone()) {
                        SmartEffectFragment.this.updateSelectedItemPosition(i);
                        SmartEffectFragment.this.mVideoEditor.setSmartEffectTemplate(smartEffect);
                        return SmartEffectFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                            public void onCompleted() {
                                SmartEffectFragment.this.mVideoEditor.play();
                                SmartEffectFragment.this.recordEventWithEffectChanged();
                                SmartEffectFragment.this.updatePlayBtnView();
                            }
                        });
                    } else if (smartEffect.isExtra()) {
                        if (smartEffect.isDownloaded()) {
                            if (SmartEffectFragment.this.mToast != null) {
                                SmartEffectFragment.this.mToast.cancel();
                            }
                            int dimensionPixelSize = SmartEffectFragment.this.getResources().getDimensionPixelSize(R.dimen.video_editor_video_smart_effect_toast_gap);
                            if (SmartEffectFragment.this.mVideoTotalTime < smartEffect.getShortTime()) {
                                SmartEffectFragment smartEffectFragment = SmartEffectFragment.this;
                                Toast unused = smartEffectFragment.mToast = Toast.makeText(smartEffectFragment.mContext, R.string.video_editor_smart_effect_time_limit_txt_6, 0);
                                SmartEffectFragment.this.mToast.setGravity(80, 0, SmartEffectFragment.this.mLayout.getHeight() - dimensionPixelSize);
                                SmartEffectFragment.this.mToast.show();
                                return false;
                            }
                            if (smartEffect.isHasSpeedChange() && !SmartEffectFragment.this.isHighFrames()) {
                                SmartEffectFragment smartEffectFragment2 = SmartEffectFragment.this;
                                Toast unused2 = smartEffectFragment2.mToast = Toast.makeText(smartEffectFragment2.mContext, R.string.video_editor_smart_effect_high_iframes_text, 0);
                                SmartEffectFragment.this.mToast.setGravity(80, 0, SmartEffectFragment.this.mLayout.getHeight() - dimensionPixelSize);
                                SmartEffectFragment.this.mToast.show();
                            } else if (SmartEffectFragment.this.mVideoTotalTime > smartEffect.getLongTime() && smartEffect.isLimitSixtySeconds()) {
                                SmartEffectFragment smartEffectFragment3 = SmartEffectFragment.this;
                                Toast unused3 = smartEffectFragment3.mToast = Toast.makeText(smartEffectFragment3.mContext, R.string.video_editor_smart_effect_time_limit_txt_60, 0);
                                SmartEffectFragment.this.mToast.setGravity(80, 0, SmartEffectFragment.this.mLayout.getHeight() - dimensionPixelSize);
                                SmartEffectFragment.this.mToast.show();
                            } else if (SmartEffectFragment.this.mVideoTotalTime > smartEffect.getLongTime() && smartEffect.isLimitFourtySeconds()) {
                                SmartEffectFragment smartEffectFragment4 = SmartEffectFragment.this;
                                Toast unused4 = smartEffectFragment4.mToast = Toast.makeText(smartEffectFragment4.mContext, R.string.video_editor_smart_effect_time_limit_txt_40, 0);
                                SmartEffectFragment.this.mToast.setGravity(80, 0, SmartEffectFragment.this.mLayout.getHeight() - dimensionPixelSize);
                                SmartEffectFragment.this.mToast.show();
                            }
                            SmartEffectFragment.this.updateSelectedItemPosition(i);
                            SmartEffectFragment.this.mVideoEditor.setSmartEffectTemplate(smartEffect);
                            return SmartEffectFragment.this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
                                public void onCompleted() {
                                    SmartEffectFragment.this.mVideoEditor.play();
                                    SmartEffectFragment.this.recordEventWithEffectChanged();
                                    SmartEffectFragment.this.updatePlayBtnView();
                                }
                            });
                        }
                        SmartEffectFragment.this.mResourceDownloadManager.createDownloadCommand(smartEffect, i);
                    }
                }
            }
            return false;
        }
    }

    private void initListener() {
        this.mOkView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartEffectFragment.this.lambda$initListener$140$SmartEffectFragment(view);
            }
        });
        this.mCancelView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartEffectFragment.this.lambda$initListener$141$SmartEffectFragment(view);
            }
        });
        this.mResourceDownloadManager.setDownloadTaskListener(new ResourceDownloadManager.IDownloadTaskListener() {
            public /* synthetic */ void lambda$onCommandSuccess$142$SmartEffectFragment$1(VideoEditorBaseModel videoEditorBaseModel, int i, List list) {
                SmartEffectFragment.this.mSmartEffectManager.updateDataWithTemplate(list, (SmartEffect) videoEditorBaseModel);
                SmartEffectFragment.this.notifyDateSetChanged(i);
            }

            public void onCommandFail(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(20);
                    SmartEffectFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandStart(VideoEditorBaseModel videoEditorBaseModel, int i) {
                if (videoEditorBaseModel != null) {
                    videoEditorBaseModel.setDownloadState(18);
                    SmartEffectFragment.this.notifyDateSetChanged(i);
                }
            }

            public void onCommandSuccess(VideoEditorBaseModel videoEditorBaseModel, int i) {
                SmartEffectFragment.this.mLoadSmartEffectTemplateTask.execute(new LoadSmartEffectTemplateTask.Callback(videoEditorBaseModel, i) {
                    private final /* synthetic */ VideoEditorBaseModel f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onLoadTemplateSuccess(List list) {
                        SmartEffectFragment.AnonymousClass1.this.lambda$onCommandSuccess$142$SmartEffectFragment$1(this.f$1, this.f$2, list);
                    }
                });
            }

            public void onTaskCancel(VideoEditorBaseModel videoEditorBaseModel, int i) {
                int downloadState;
                if (videoEditorBaseModel != null && (downloadState = videoEditorBaseModel.getDownloadState()) != 0 && downloadState != 17) {
                    videoEditorBaseModel.setDownloadState(20);
                    SmartEffectFragment.this.notifyDateSetChanged(i);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        this.mSingleChoiceRecyclerView = (SingleChoiceRecyclerView) view.findViewById(R.id.recycler_view);
        this.mLinearLayoutManager = new ScrollLinearLayoutManager(this.mContext, 0, false);
        this.mLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(this.mContext));
        this.mSingleChoiceRecyclerView.setLayoutManager(this.mLinearLayoutManager);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.editor_smart_effect_item_start);
        this.mSingleChoiceRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.editor_smart_effect_item_horizontal_interval), 0, 0, 0));
        this.mAdapter = new SmartEffectRecyclerViewAdapter(this.mContext, this.mSmartEffects);
        this.mAdapter.setItemSelectChangeListener(new SmartEffectItemSelectChangeListener());
        this.mSingleChoiceRecyclerView.setAdapter(this.mAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mSingleChoiceRecyclerView);
        this.mAdapter.setSelectedItemPosition(this.mSavedSelectedSmartEffectIndex);
    }

    /* access modifiers changed from: private */
    public void refreshData(List<VideoEditorBaseLocalResource> list) {
        List<LocalResource> localTemplateEntities = this.mModuleFactory.getLocalTemplateEntities(this.mContext);
        if (list != null) {
            localTemplateEntities.addAll(list);
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(SmartEffectManager.loadSmartEffects(localTemplateEntities));
        NexAssetTemplateManager.getInstance().checkExpiredAsset(new NexAssetTemplateManager.ICheckExpiredAssetListener() {
            public void onFinished(List<nexTemplateManager.Template> list) {
                SmartEffectFragment.this.mSmartEffectManager.initDataWithTemplate(list, arrayList);
                ThreadManager.runOnMainThread(new Runnable() {
                    public void run() {
                        SmartEffectFragment.this.mSmartEffects.clear();
                        SmartEffectFragment.this.mSmartEffects.addAll(arrayList);
                        if (SmartEffectFragment.this.mAdapter != null) {
                            SmartEffectFragment.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSelectedItemPosition(int i) {
        SmartEffectRecyclerViewAdapter smartEffectRecyclerViewAdapter = this.mAdapter;
        if (smartEffectRecyclerViewAdapter != null) {
            smartEffectRecyclerViewAdapter.setSelectedItemPosition(i);
            this.mAdapter.clearLastSelectedPostion();
        }
    }

    public boolean doApply() {
        if (this.mVideoEditor == null) {
            Log.d("SmartEffectFragment", "doApply: videoEditor is null.");
            return false;
        }
        this.mVideoEditor.saveEditState();
        this.mSavedSelectedSmartEffectIndex = this.mAdapter.getSelectedItemPosition();
        recordEventWithApply();
        onExitMode();
        return true;
    }

    public boolean doCancel() {
        if (this.mVideoEditor == null) {
            Log.d("SmartEffectFragment", "doCancel: videoEditor is null.");
            return false;
        }
        this.mVideoEditor.restoreEditState();
        return this.mVideoEditor.apply(new VideoEditor.OnCompletedListener() {
            public void onCompleted() {
                if (SmartEffectFragment.this.mVideoEditor != null) {
                    SmartEffectFragment.this.mVideoEditor.play();
                    SmartEffectFragment.this.recordEventWithCancel();
                    SmartEffectFragment.this.onExitMode();
                }
            }
        });
    }

    public List<String> getCurrentEffect() {
        SmartEffect smartEffect;
        SmartEffectRecyclerViewAdapter smartEffectRecyclerViewAdapter = this.mAdapter;
        if (smartEffectRecyclerViewAdapter == null || (smartEffect = smartEffectRecyclerViewAdapter.getSmartEffect(smartEffectRecyclerViewAdapter.getSelectedItemPosition())) == null) {
            return null;
        }
        return Arrays.asList(new String[]{smartEffect.getLabel()});
    }

    public int getEffectId() {
        return R.id.video_editor_smart_effect;
    }

    public boolean isHighFrames() {
        return this.mVideoEditor != null && this.mVideoEditor.getVideoFrames() >= 100;
    }

    public /* synthetic */ void lambda$initListener$140$SmartEffectFragment(View view) {
        doApply();
    }

    public /* synthetic */ void lambda$initListener$141$SmartEffectFragment(View view) {
        doCancel();
    }

    public void loadResourceData() {
        this.mHasLoadData = false;
        this.mVideoEditorResourceRequest = new VideoEditorResourceRequest(getEffectId(), this.mModuleFactory);
        this.mVideoEditorResourceRequest.execute(new ResponseListener() {
            public void onResponse(Object... objArr) {
                SmartEffectFragment smartEffectFragment = SmartEffectFragment.this;
                smartEffectFragment.mHasLoadData = true;
                smartEffectFragment.refreshData(objArr[0]);
            }

            public void onResponseError(final ErrorCode errorCode, String str, Object obj) {
                Log.d("SmartEffectFragment", "errorCode: %s", (Object) errorCode);
                SmartEffectFragment.this.mHasLoadData = true;
                ThreadManager.runOnMainThread(new Runnable() {
                    public void run() {
                        if (SmartEffectFragment.this.mSmartEffects != null && SmartEffectFragment.this.mSmartEffects.size() == 0) {
                            SmartEffectFragment.this.mSmartEffects.add(new SmartEffect("smart_effect_none", R.drawable.video_editor_icon_smart_effect_none, "ve_type_none", false));
                            if (SmartEffectFragment.this.mAdapter != null) {
                                SmartEffectFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }
                        if (errorCode == ErrorCode.NETWORK_NOT_CONNECTED) {
                            ThreadManager.runOnMainThread(new Runnable() {
                                public void run() {
                                    ToastUtils.makeText(SmartEffectFragment.this.mContext, (int) R.string.video_editor_download_failed_for_notwork);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void notifyDateSetChanged(int i) {
        SmartEffectRecyclerViewAdapter smartEffectRecyclerViewAdapter = this.mAdapter;
        if (smartEffectRecyclerViewAdapter != null) {
            smartEffectRecyclerViewAdapter.notifyItemChanged(i, 1);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSmartEffectManager = new SmartEffectManager();
        this.mSmartEffects = SmartEffectManager.initDataWithBgColor();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLayout = new SmartEffectView(viewGroup.getContext());
        this.mCancelView = this.mLayout.findViewById(R.id.cancel);
        this.mOkView = this.mLayout.findViewById(R.id.ok);
        this.mTitleView = (TextView) this.mLayout.findViewById(R.id.title);
        this.mTitleView.setText(this.mContext.getResources().getString(R.string.video_editor_intelligent_special_effect));
        this.mVideoTotalTime = this.mVideoEditor.getProjectTotalTime();
        this.mModuleFactory = new SmartEffectFactory();
        this.mResourceDownloadManager = new ResourceDownloadManager(viewGroup.getContext(), getFragmentManager(), this.mModuleFactory);
        this.mLoadSmartEffectTemplateTask = new LoadSmartEffectTemplateTask();
        initRecyclerView(this.mLayout);
        initListener();
        loadResourceData();
        return this.mLayout;
    }

    public void onDestroyView() {
        super.onDestroyView();
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        SmartEffectRecyclerViewAdapter smartEffectRecyclerViewAdapter = this.mAdapter;
        if (smartEffectRecyclerViewAdapter != null) {
            smartEffectRecyclerViewAdapter.setItemSelectChangeListener((SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener) null);
            this.mAdapter = null;
        }
        VideoEditorResourceRequest videoEditorResourceRequest = this.mVideoEditorResourceRequest;
        if (videoEditorResourceRequest != null) {
            videoEditorResourceRequest.cancel();
            this.mVideoEditorResourceRequest = null;
        }
        ResourceDownloadManager resourceDownloadManager = this.mResourceDownloadManager;
        if (resourceDownloadManager != null) {
            resourceDownloadManager.cancel();
            this.mResourceDownloadManager = null;
        }
        LoadSmartEffectTemplateTask loadSmartEffectTemplateTask = this.mLoadSmartEffectTemplateTask;
        if (loadSmartEffectTemplateTask != null) {
            loadSmartEffectTemplateTask.cancel();
            this.mLoadSmartEffectTemplateTask = null;
        }
    }
}
