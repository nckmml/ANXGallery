package com.miui.gallery.movie.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.miui.gallery.imodule.loader.ModuleOperator;
import com.miui.gallery.imodule.modules.MovieDependsModule;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.movie.R;
import com.miui.gallery.movie.core.MovieManager;
import com.miui.gallery.movie.entity.AudioResource;
import com.miui.gallery.movie.entity.ImageEntity;
import com.miui.gallery.movie.entity.MovieInfo;
import com.miui.gallery.movie.entity.MovieResource;
import com.miui.gallery.movie.entity.TemplateResource;
import com.miui.gallery.movie.net.AudioResourceRequest;
import com.miui.gallery.movie.net.TemplateResourceRequest;
import com.miui.gallery.movie.ui.adapter.AudioAdapter;
import com.miui.gallery.movie.ui.adapter.BaseAdapter;
import com.miui.gallery.movie.ui.adapter.EditAdapter;
import com.miui.gallery.movie.ui.adapter.TemplateAdapter;
import com.miui.gallery.movie.ui.factory.AudioFactory;
import com.miui.gallery.movie.ui.factory.TemplateFactory;
import com.miui.gallery.movie.ui.listener.MenuActivityListener;
import com.miui.gallery.movie.ui.listener.MenuFragmentListener;
import com.miui.gallery.movie.ui.listener.MovieDownloadListener;
import com.miui.gallery.movie.utils.MovieDownloadManager;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.movie.utils.MovieUtils;
import com.miui.gallery.net.NetApi;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.ConvertFilepathUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.OptionalResult;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import miui.view.animation.QuarticEaseOutInterpolator;

public class MovieEditorMenuFragment extends Fragment implements MenuActivityListener {
    /* access modifiers changed from: private */
    public AudioAdapter mAudioAdapter;
    private AudioResourceRequest mAudioResourceRequest;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentTabPos;
    /* access modifiers changed from: private */
    public boolean mDeleteClicked;
    private RadioGroup mDurationRadioGroup;
    /* access modifiers changed from: private */
    public EditAdapter mEditAdapter;
    /* access modifiers changed from: private */
    public boolean mIsShortVideo;
    private RadioButton mLongRadioButton;
    /* access modifiers changed from: private */
    public MenuFragmentListener mMenuFragmentListener;
    /* access modifiers changed from: private */
    public MovieInfo mMovieInfo;
    /* access modifiers changed from: private */
    public MovieManager mMovieManager;
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radio_button_video_short) {
                if (!MovieEditorMenuFragment.this.mMovieInfo.isShortVideo) {
                    if (!MovieEditorMenuFragment.this.mMovieManager.isReadyForSwitch()) {
                        MovieEditorMenuFragment.this.setShortRadioButtonCheck(false);
                        return;
                    }
                    MovieStatUtils.statDurationClick(MovieEditorMenuFragment.this.mMovieInfo, false, true);
                    boolean unused = MovieEditorMenuFragment.this.mIsShortVideo = true;
                    if (MovieEditorMenuFragment.this.mMovieInfo.changeToShortVideo()) {
                        MovieEditorMenuFragment.this.refreshEditAdapter();
                        MovieEditorMenuFragment.this.mMovieManager.resetImage(MovieEditorMenuFragment.this.mMovieInfo.imageList, true);
                    } else {
                        MovieEditorMenuFragment.this.mMovieManager.setIsShortVideo(true);
                    }
                    MovieEditorMenuFragment.this.mMovieManager.replay();
                    if (MovieEditorMenuFragment.this.mMovieInfo.isFromStory) {
                        MovieEditorMenuFragment.this.mMenuFragmentListener.updateStorySha1Data();
                    }
                    MovieEditorMenuFragment.this.refreshRadioButtonState(true);
                }
            } else if (i == R.id.radio_button_video_long && MovieEditorMenuFragment.this.mMovieInfo.isShortVideo) {
                if (!MovieEditorMenuFragment.this.mMovieManager.isReadyForSwitch()) {
                    MovieEditorMenuFragment.this.setShortRadioButtonCheck(true);
                    return;
                }
                MovieStatUtils.statDurationClick(MovieEditorMenuFragment.this.mMovieInfo, false, false);
                boolean unused2 = MovieEditorMenuFragment.this.mIsShortVideo = false;
                if (MovieEditorMenuFragment.this.mMovieInfo.backToLongVideo()) {
                    MovieEditorMenuFragment.this.refreshEditAdapter();
                    MovieEditorMenuFragment.this.mMovieManager.resetImage(MovieEditorMenuFragment.this.mMovieInfo.imageList, false);
                } else {
                    MovieEditorMenuFragment.this.mMovieManager.setIsShortVideo(false);
                }
                MovieEditorMenuFragment.this.mMovieManager.replay();
                if (MovieEditorMenuFragment.this.mMovieInfo.isFromStory) {
                    MovieEditorMenuFragment.this.mMenuFragmentListener.updateStorySha1Data();
                }
                MovieEditorMenuFragment.this.refreshRadioButtonState(false);
            }
        }
    };
    /* access modifiers changed from: private */
    public SimpleRecyclerView[] mRecyclerViews;
    private RadioButton mShortRadioButton;
    private SingleClickListener mSingleClickListener = new SingleClickListener() {
        /* access modifiers changed from: protected */
        public void onSingleClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_movie_save) {
                MovieEditorMenuFragment.this.mMenuFragmentListener.export(false);
            } else if (id == R.id.btn_movie_return) {
                MovieEditorMenuFragment.this.mMenuFragmentListener.returnClick();
            }
        }
    };
    /* access modifiers changed from: private */
    public long mStoryMovieCardId;
    /* access modifiers changed from: private */
    public TemplateAdapter mTemplateAdapter;
    private TemplateResourceRequest mTemplateResourceRequest;
    private ViewPager mViewPager;

    private class ControllerPagerAdapter extends PagerAdapter {
        private List<BaseAdapter> mAdapters = new ArrayList(3);

        public ControllerPagerAdapter() {
            this.mAdapters.add(MovieEditorMenuFragment.this.mTemplateAdapter);
            this.mAdapters.add(MovieEditorMenuFragment.this.mAudioAdapter);
            this.mAdapters.add(MovieEditorMenuFragment.this.mEditAdapter);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(MovieEditorMenuFragment.this.mRecyclerViews[i]);
        }

        public int getCount() {
            return this.mAdapters.size();
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0109  */
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int i2;
            int i3;
            int dimension;
            int dimensionPixelSize;
            SimpleRecyclerView simpleRecyclerView = MovieEditorMenuFragment.this.mRecyclerViews[i];
            if (simpleRecyclerView == null) {
                simpleRecyclerView = new SimpleRecyclerView(viewGroup.getContext());
                ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(MovieEditorMenuFragment.this.mContext);
                scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(MovieEditorMenuFragment.this.mContext));
                int i4 = 0;
                scrollLinearLayoutManager.setOrientation(0);
                simpleRecyclerView.setLayoutManager(scrollLinearLayoutManager);
                if (i == 0) {
                    this.mAdapters.get(i).setItemSelectChangeListener(new MyTemplateItemSelectChangeListener());
                    HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(simpleRecyclerView);
                    dimension = (int) MovieEditorMenuFragment.this.getResources().getDimension(R.dimen.photo_movie_edit_template_item_start);
                    dimensionPixelSize = MovieEditorMenuFragment.this.getResources().getDimensionPixelSize(R.dimen.photo_movie_edit_template_item_horizontal_interval);
                } else if (i == 1) {
                    this.mAdapters.get(i).setItemSelectChangeListener(new MyAudioItemSelectChangeListener());
                    HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(simpleRecyclerView);
                    dimension = (int) MovieEditorMenuFragment.this.getResources().getDimension(R.dimen.photo_movie_edit_template_item_start);
                    dimensionPixelSize = MovieEditorMenuFragment.this.getResources().getDimensionPixelSize(R.dimen.photo_movie_edit_audio_item_horizontal_interval);
                } else if (i == 2) {
                    this.mAdapters.get(i).setItemSelectChangeListener(new MyEditItemSelectChangeListener());
                    HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(simpleRecyclerView, MovieEditorMenuFragment.this.mEditAdapter.getCallback());
                    simpleRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    dimension = (int) MovieEditorMenuFragment.this.getResources().getDimension(R.dimen.photo_movie_edit_square_board_item_start);
                    dimensionPixelSize = MovieEditorMenuFragment.this.getResources().getDimensionPixelSize(R.dimen.photo_movie_edit_square_board_item_horizontal_interval);
                } else {
                    i3 = 0;
                    i2 = 0;
                    MovieEditorMenuFragment.this.mRecyclerViews[i] = simpleRecyclerView;
                    simpleRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(i3, i3, i2, 0, 0, 0));
                    simpleRecyclerView.setAdapter(this.mAdapters.get(i));
                    if (MovieEditorMenuFragment.this.mCurrentTabPos != i) {
                        i4 = 4;
                    }
                    simpleRecyclerView.setVisibility(i4);
                }
                i3 = dimension;
                i2 = dimensionPixelSize;
                MovieEditorMenuFragment.this.mRecyclerViews[i] = simpleRecyclerView;
                simpleRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(i3, i3, i2, 0, 0, 0));
                simpleRecyclerView.setAdapter(this.mAdapters.get(i));
                if (MovieEditorMenuFragment.this.mCurrentTabPos != i) {
                }
                simpleRecyclerView.setVisibility(i4);
            }
            viewGroup.addView(simpleRecyclerView, new ViewGroup.LayoutParams(-1, -1));
            return simpleRecyclerView;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return obj == view;
        }
    }

    private class MyAudioItemSelectChangeListener implements BaseAdapter.ItemSelectChangeListener {
        private MyAudioItemSelectChangeListener() {
        }

        public boolean onItemSelect(RecyclerView recyclerView, BaseAdapter.BaseHolder baseHolder, int i, boolean z) {
            AudioResource audioResource = (AudioResource) MovieEditorMenuFragment.this.mAudioAdapter.getItemData(i);
            if (audioResource != null) {
                if (audioResource.getResType() == 2) {
                    Intent intent = new Intent();
                    intent.setType("audio/*");
                    intent.setAction("android.intent.action.GET_CONTENT");
                    intent.setPackage("com.miui.player");
                    try {
                        MovieEditorMenuFragment.this.startActivityForResult(intent, 1000);
                    } catch (ActivityNotFoundException unused) {
                        Log.e("MovieEditorMenuFragment", "com.miui.player not found,try all picker");
                        try {
                            Intent intent2 = new Intent();
                            intent2.setType("audio/*");
                            intent2.setAction("android.intent.action.GET_CONTENT");
                            MovieEditorMenuFragment.this.startActivityForResult(intent2, 1000);
                        } catch (ActivityNotFoundException unused2) {
                            Log.e("MovieEditorMenuFragment", "picker not found");
                        }
                    }
                    return false;
                } else if (z) {
                    int downloadState = audioResource.getDownloadState();
                    if (downloadState == 17 || downloadState == 0) {
                        if (!MovieEditorMenuFragment.this.mMovieManager.isReadyForSwitch()) {
                            return false;
                        }
                        ScrollLinearLayoutManager.onItemClick(recyclerView, i);
                        MovieEditorMenuFragment.this.mMovieInfo.audio = audioResource.nameKey;
                        MovieEditorMenuFragment.this.mMovieManager.setAudio(audioResource);
                        MovieEditorMenuFragment.this.mMenuFragmentListener.showLoadingView();
                        MovieStatUtils.statItemChoose(audioResource);
                        MovieEditorMenuFragment.this.resetMovieShareData();
                        return true;
                    } else if (downloadState != 19 && downloadState != 20) {
                        return false;
                    } else {
                        MovieEditorMenuFragment.this.downloadResource(audioResource);
                        return false;
                    }
                }
            }
            return false;
        }
    }

    private class MyEditItemSelectChangeListener implements BaseAdapter.ItemSelectChangeListener {
        private MyEditItemSelectChangeListener() {
        }

        public boolean onItemSelect(RecyclerView recyclerView, BaseAdapter.BaseHolder baseHolder, int i, boolean z) {
            if (MovieEditorMenuFragment.this.mEditAdapter.isAddItem(i)) {
                if (MovieEditorMenuFragment.this.mMovieInfo.isFromStory) {
                    if (MovieEditorMenuFragment.this.mDeleteClicked) {
                        MovieEditorMenuFragment.this.mMenuFragmentListener.updateStorySha1Data();
                        boolean unused = MovieEditorMenuFragment.this.mDeleteClicked = false;
                    }
                    MovieDependsModule movieDependsModule = (MovieDependsModule) ModuleOperator.getModule(MovieDependsModule.class);
                    if (movieDependsModule != null) {
                        Intent intent = new Intent(MovieEditorMenuFragment.this.getActivity(), movieDependsModule.getStoryPickClass());
                        intent.putExtra("card_id", MovieEditorMenuFragment.this.mStoryMovieCardId);
                        intent.putStringArrayListExtra("pick_sha1", MovieEditorMenuFragment.this.mMenuFragmentListener.getStoryMovieSha1());
                        intent.putExtra("pick-upper-bound", 20);
                        intent.putExtra("pick-lower-bound", 3);
                        intent.putExtra("extra_filter_media_type", new String[]{"image/x-adobe-dng"});
                        MovieEditorMenuFragment.this.startActivityForResult(intent, 1001);
                    }
                } else if (MovieEditorMenuFragment.this.mEditAdapter.getListSize() >= 20) {
                    ToastUtils.makeText((Context) MovieEditorMenuFragment.this.getActivity(), R.string.movie_add_disable);
                } else {
                    Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                    intent2.setType("image/*");
                    intent2.putExtra("pick-upper-bound", 20 - MovieEditorMenuFragment.this.mEditAdapter.getList().size());
                    intent2.putExtra("extra_filter_media_type", new String[]{"image/x-adobe-dng"});
                    intent2.setPackage("com.miui.gallery");
                    MovieEditorMenuFragment.this.startActivityForResult(intent2, 1002);
                }
            } else if (z) {
                ScrollLinearLayoutManager.onItemClick(recyclerView, i);
                MovieEditorMenuFragment.this.mMenuFragmentListener.seekToIndex(i);
                MovieEditorMenuFragment.this.mMenuFragmentListener.setDeleteVisible(true);
                return true;
            }
            return false;
        }
    }

    private class MyTemplateItemSelectChangeListener implements BaseAdapter.ItemSelectChangeListener {
        private MyTemplateItemSelectChangeListener() {
        }

        public boolean onItemSelect(RecyclerView recyclerView, BaseAdapter.BaseHolder baseHolder, int i, boolean z) {
            TemplateResource templateResource;
            if (!z || (templateResource = (TemplateResource) MovieEditorMenuFragment.this.mTemplateAdapter.getItemData(i)) == null) {
                return false;
            }
            int downloadState = templateResource.getDownloadState();
            if (downloadState == 17 || downloadState == 0) {
                if (!MovieEditorMenuFragment.this.mMovieManager.isReadyForSwitch()) {
                    return false;
                }
                ScrollLinearLayoutManager.onItemClick(recyclerView, i);
                MovieEditorMenuFragment.this.mAudioAdapter.setSelectedItemPosition(-1);
                MovieStatUtils.statItemChoose(templateResource);
                MovieEditorMenuFragment.this.mMovieInfo.template = templateResource.nameKey;
                MovieEditorMenuFragment.this.mMovieManager.setTemplate(templateResource);
                MovieEditorMenuFragment.this.resetMovieShareData();
                MovieEditorMenuFragment.this.mMenuFragmentListener.showLoadingView();
                return true;
            } else if (downloadState != 19 && downloadState != 20) {
                return false;
            } else {
                MovieEditorMenuFragment.this.downloadResource(templateResource);
                return false;
            }
        }
    }

    private boolean checkChangeLongVideo() {
        return this.mMovieInfo.imageList.size() > 5 && this.mMovieInfo.isShortVideo;
    }

    /* access modifiers changed from: private */
    public void downloadResource(final MovieResource movieResource) {
        MovieDownloadManager.getInstance().downloadResourceWithCheck(getActivity(), movieResource, new MovieDownloadListener() {
            public void onCompleted(boolean z) {
                MovieStatUtils.statDownloadResult(movieResource, z);
                movieResource.downloadState = z ? 0 : 20;
                if (!z) {
                    ToastUtils.makeText(MovieEditorMenuFragment.this.mContext, R.string.movie_download_failed);
                }
                MovieEditorMenuFragment.this.notifyResourceAdapter(movieResource);
            }

            public void onStart() {
                MovieStatUtils.statDownload(movieResource);
                MovieResource movieResource = movieResource;
                movieResource.downloadState = 18;
                MovieEditorMenuFragment.this.notifyResourceAdapter(movieResource);
            }
        });
    }

    private void initView(View view) {
        this.mContext = view.getContext();
        this.mTemplateAdapter = new TemplateAdapter(this.mContext);
        this.mTemplateAdapter.setList(TemplateFactory.getLocalTemplateEntities());
        this.mShortRadioButton = (RadioButton) view.findViewById(R.id.radio_button_video_short);
        this.mLongRadioButton = (RadioButton) view.findViewById(R.id.radio_button_video_long);
        this.mRecyclerViews = new SimpleRecyclerView[3];
        this.mAudioAdapter = new AudioAdapter(this.mContext);
        this.mAudioAdapter.setList(AudioFactory.getAllLocalAudios());
        this.mEditAdapter = new EditAdapter(this.mContext);
        refreshEditAdapter();
        this.mEditAdapter.setOnActionListener(new EditAdapter.OnActionListener() {
            public final void onMove(int i, int i2) {
                MovieEditorMenuFragment.this.lambda$initView$5$MovieEditorMenuFragment(i, i2);
            }
        });
        this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        this.mViewPager.setAdapter(new ControllerPagerAdapter());
        ((RadioGroup) view.findViewById(R.id.radio_group_controller)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                MovieEditorMenuFragment.this.lambda$initView$6$MovieEditorMenuFragment(radioGroup, i);
            }
        });
        this.mDurationRadioGroup = (RadioGroup) view.findViewById(R.id.duration_group_controller);
        this.mIsShortVideo = this.mMovieInfo.isShortVideo;
        this.mDurationRadioGroup.check(this.mMovieInfo.isShortVideo ? R.id.radio_button_video_short : R.id.radio_button_video_long);
        refreshRadioButtonState(this.mMovieInfo.isShortVideo);
        this.mDurationRadioGroup.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        onTabSelect(this.mCurrentTabPos);
        view.findViewById(R.id.btn_movie_save).setOnClickListener(this.mSingleClickListener);
        view.findViewById(R.id.btn_movie_return).setOnClickListener(this.mSingleClickListener);
        loadTemplate();
        loadAudio();
    }

    static /* synthetic */ List lambda$loadAudio$12(OptionalResult optionalResult) throws Exception {
        List list = (List) optionalResult.getIncludeNull();
        ArrayList<AudioResource> allLocalAudios = AudioFactory.getAllLocalAudios();
        if (list != null) {
            MovieUtils.checkResourceExist(list);
            allLocalAudios.addAll(1, list);
        }
        return allLocalAudios;
    }

    static /* synthetic */ List lambda$loadTemplate$7(OptionalResult optionalResult) throws Exception {
        List list = (List) optionalResult.getIncludeNull();
        List<TemplateResource> localTemplateEntities = TemplateFactory.getLocalTemplateEntities();
        if (list != null) {
            MovieUtils.checkResourceExist(list);
            localTemplateEntities.addAll(list);
        }
        return localTemplateEntities;
    }

    private void loadAudio() {
        this.mAudioResourceRequest = new AudioResourceRequest();
        NetApi.create(this.mAudioResourceRequest).observeOn(Schedulers.io()).map($$Lambda$MovieEditorMenuFragment$VE6u2cEpnwxUcrWpH8PolDrrT0c.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                MovieEditorMenuFragment.this.lambda$loadAudio$13$MovieEditorMenuFragment((List) obj);
            }
        });
    }

    private void loadTemplate() {
        this.mTemplateResourceRequest = new TemplateResourceRequest();
        NetApi.create(this.mTemplateResourceRequest).observeOn(Schedulers.io()).map($$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                MovieEditorMenuFragment.this.lambda$loadTemplate$11$MovieEditorMenuFragment((List) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyResourceAdapter(MovieResource movieResource) {
        AndroidSchedulers.mainThread().createWorker().schedule(new Runnable(movieResource) {
            private final /* synthetic */ MovieResource f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MovieEditorMenuFragment.this.lambda$notifyResourceAdapter$14$MovieEditorMenuFragment(this.f$1);
            }
        });
    }

    private void onTabSelect(int i) {
        this.mCurrentTabPos = i;
        boolean z = false;
        int i2 = 0;
        while (true) {
            SimpleRecyclerView[] simpleRecyclerViewArr = this.mRecyclerViews;
            if (i2 >= simpleRecyclerViewArr.length) {
                break;
            }
            if (simpleRecyclerViewArr[i2] != null) {
                simpleRecyclerViewArr[i2].setVisibility(i == i2 ? 0 : 4);
            }
            i2++;
        }
        this.mViewPager.setCurrentItem(i);
        ViewPager viewPager = this.mViewPager;
        if (i != 2) {
            z = true;
        }
        viewPager.setClipChildren(z);
    }

    /* access modifiers changed from: private */
    public void refreshEditAdapter() {
        this.mEditAdapter.setList(this.mMovieInfo.imageList);
        this.mEditAdapter.notifyDataSetChanged();
        this.mIsShortVideo = this.mMovieInfo.isShortVideo;
    }

    /* access modifiers changed from: private */
    public void refreshRadioButtonState(boolean z) {
        this.mShortRadioButton.setSelected(z);
        this.mLongRadioButton.setSelected(!z);
    }

    /* access modifiers changed from: private */
    public void resetMovieShareData() {
        MenuFragmentListener menuFragmentListener = this.mMenuFragmentListener;
        if (menuFragmentListener != null) {
            menuFragmentListener.resetShareData();
        }
    }

    /* access modifiers changed from: private */
    public void setShortRadioButtonCheck(boolean z) {
        this.mDurationRadioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
        this.mIsShortVideo = z;
        this.mDurationRadioGroup.check(z ? R.id.radio_button_video_short : R.id.radio_button_video_long);
        this.mDurationRadioGroup.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        refreshRadioButtonState(z);
    }

    public void clearEditorAdapterSelected() {
        if (isAdded()) {
            this.mEditAdapter.setSelectedItemPosition(-1);
        }
    }

    public /* synthetic */ void lambda$initView$5$MovieEditorMenuFragment(int i, int i2) {
        MovieStatUtils.statEditorMove(this.mMovieInfo.imageList.size());
        this.mMovieManager.moveImage(i, i2);
    }

    public /* synthetic */ void lambda$initView$6$MovieEditorMenuFragment(RadioGroup radioGroup, int i) {
        boolean z = true;
        int i2 = 0;
        if (i == R.id.radio_button_template) {
            onTabSelect(0);
        } else if (i == R.id.radio_button_music) {
            onTabSelect(1);
        } else if (i == R.id.radio_button_edit) {
            onTabSelect(2);
        }
        MenuFragmentListener menuFragmentListener = this.mMenuFragmentListener;
        if (i != R.id.radio_button_edit) {
            z = false;
        }
        menuFragmentListener.setShowDeleteMode(z);
        RadioGroup radioGroup2 = this.mDurationRadioGroup;
        if (i != R.id.radio_button_template) {
            i2 = 4;
        }
        radioGroup2.setVisibility(i2);
    }

    public /* synthetic */ void lambda$loadAudio$13$MovieEditorMenuFragment(List list) throws Exception {
        this.mAudioAdapter.setList(list);
        this.mAudioAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$loadTemplate$11$MovieEditorMenuFragment(List list) throws Exception {
        Observable.fromIterable(list).filter(new Predicate() {
            public final boolean test(Object obj) {
                return MovieEditorMenuFragment.this.lambda$null$8$MovieEditorMenuFragment((TemplateResource) obj);
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return MovieEditorMenuFragment.this.lambda$null$9$MovieEditorMenuFragment((TemplateResource) obj);
            }
        }).subscribe(new Consumer(list) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                MovieEditorMenuFragment.this.lambda$null$10$MovieEditorMenuFragment(this.f$1, (TemplateResource) obj);
            }
        });
        this.mTemplateAdapter.setList(list);
        this.mTemplateAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$notifyResourceAdapter$14$MovieEditorMenuFragment(MovieResource movieResource) {
        if (movieResource instanceof TemplateResource) {
            TemplateAdapter templateAdapter = this.mTemplateAdapter;
            templateAdapter.notifyItemRangeChanged(0, templateAdapter.getItemCount(), new Object());
            return;
        }
        AudioAdapter audioAdapter = this.mAudioAdapter;
        audioAdapter.notifyItemRangeChanged(0, audioAdapter.getItemCount(), new Object());
    }

    public /* synthetic */ void lambda$null$10$MovieEditorMenuFragment(List list, TemplateResource templateResource) throws Exception {
        templateResource.pathKey = this.mMovieInfo.template;
        this.mTemplateAdapter.setSelectedItemPositionWithoutNotify(list.indexOf(templateResource));
    }

    public /* synthetic */ boolean lambda$null$8$MovieEditorMenuFragment(TemplateResource templateResource) throws Exception {
        return this.mMovieInfo.template != null && !TextUtils.equals(this.mMovieInfo.template, MovieStatUtils.FROM_NORMAL);
    }

    public /* synthetic */ boolean lambda$null$9$MovieEditorMenuFragment(TemplateResource templateResource) throws Exception {
        return templateResource.pathKey != null && this.mMovieInfo.template.contains(templateResource.pathKey);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && i2 == -1) {
            List<ImageEntity> imageFromClipData = MovieUtils.getImageFromClipData(this.mContext, intent);
            this.mMovieInfo.imageList.addAll(imageFromClipData);
            if (checkChangeLongVideo()) {
                this.mMovieInfo.discardToLongVideo();
                setShortRadioButtonCheck(false);
                ToastUtils.makeText(this.mContext, R.string.movie_change_to_long_video);
                this.mMovieManager.resetImage(this.mMovieInfo.imageList, false);
                this.mMovieManager.replay();
            } else {
                this.mMovieManager.addImage(imageFromClipData);
            }
            this.mEditAdapter.setList(this.mMovieInfo.imageList);
            this.mEditAdapter.notifyDataSetChanged();
            this.mEditAdapter.setSelectedItemPosition(-1);
            resetMovieShareData();
        } else if (i == 1000 && i2 == -1) {
            ClipData clipData = intent.getClipData();
            if (clipData == null) {
                str = ConvertFilepathUtil.getPath(getActivity(), intent.getData());
            } else {
                str = ConvertFilepathUtil.getPath(getActivity(), clipData.getItemAt(0).getUri());
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.mMovieManager.isSupportVideo(str)) {
                    AudioResource audioResource = new AudioResource(str);
                    this.mMovieInfo.audio = audioResource.nameKey;
                    this.mMovieManager.setAudio(audioResource);
                    AudioAdapter audioAdapter = this.mAudioAdapter;
                    audioAdapter.setSelectedItemPosition(audioAdapter.getItemCount() - 1);
                } else {
                    ToastUtils.makeText((Context) getActivity(), R.string.movie_customer_unsupport_audio_file);
                }
            }
            resetMovieShareData();
        } else if (i == 1001 && i2 == -1) {
            List<ImageEntity> imageFromClipData2 = MovieUtils.getImageFromClipData(this.mContext, intent);
            this.mMovieInfo.imageList.clear();
            this.mMovieInfo.imageList.addAll(imageFromClipData2);
            if (this.mMovieInfo.imageList.size() > 5) {
                if (this.mIsShortVideo) {
                    ToastUtils.makeText(this.mContext, R.string.movie_change_to_long_video);
                }
                this.mMovieInfo.discardToLongVideo();
                setShortRadioButtonCheck(false);
                this.mMovieManager.resetImage(this.mMovieInfo.imageList, false);
            } else {
                this.mMovieInfo.discardToShortVideo();
                setShortRadioButtonCheck(true);
                this.mMovieManager.resetImage(this.mMovieInfo.imageList, true);
            }
            refreshEditAdapter();
            this.mEditAdapter.setSelectedItemPosition(-1);
            resetMovieShareData();
            this.mMenuFragmentListener.updateStorySha1Data();
            this.mMovieManager.replay();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MenuFragmentListener) {
            this.mMenuFragmentListener = (MenuFragmentListener) context;
            this.mMovieManager = this.mMenuFragmentListener.getMovieManager();
            this.mMovieInfo = this.mMenuFragmentListener.getMovieInfo();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mMovieInfo.isFromStory) {
            Bundle arguments = getArguments();
            long j = 0;
            if (arguments != null) {
                j = arguments.getLong("card_id", 0);
            }
            this.mStoryMovieCardId = j;
        }
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        int dimensionPixelSize = getActivity().getResources().getDimensionPixelSize(R.dimen.movie_controller_height);
        if (z) {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(float) dimensionPixelSize, 0.0f})});
            objectAnimator.setInterpolator(new QuarticEaseOutInterpolator());
            objectAnimator.setStartDelay((long) getResources().getInteger(R.integer.movie_editor_appear_delay));
            objectAnimator.setDuration((long) getResources().getInteger(R.integer.movie_editor_appear_duration));
            getView().setAlpha(0.0f);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    MovieEditorMenuFragment.this.getView().setAlpha(1.0f);
                }
            });
        } else {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f, (float) dimensionPixelSize})});
            objectAnimator.setInterpolator(new QuarticEaseOutInterpolator());
            objectAnimator.setDuration((long) getResources().getInteger(R.integer.movie_editor_disappear_duration));
        }
        return objectAnimator;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MovieEditMenuView movieEditMenuView = new MovieEditMenuView(viewGroup.getContext());
        initView(movieEditMenuView);
        return movieEditMenuView;
    }

    public void onDeleteClick() {
        if (this.mEditAdapter.getItemCount() - 1 <= 3) {
            ToastUtils.makeText(this.mContext, R.string.movie_delete_disable);
            return;
        }
        int selectedItemPosition = this.mEditAdapter.getSelectedItemPosition();
        if (selectedItemPosition >= 0 && selectedItemPosition < this.mMovieInfo.imageList.size() && this.mMenuFragmentListener.getMovieManager().isReadyForSwitch()) {
            this.mMovieInfo.imageList.remove(selectedItemPosition);
            this.mMenuFragmentListener.getMovieManager().removeImageAtIndex(selectedItemPosition);
            this.mEditAdapter.setSelectedItemPosition(-1);
            this.mEditAdapter.notifyDataSetChanged();
            resetMovieShareData();
            this.mDeleteClicked = true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        TemplateResourceRequest templateResourceRequest = this.mTemplateResourceRequest;
        if (templateResourceRequest != null) {
            templateResourceRequest.cancel();
        }
        AudioResourceRequest audioResourceRequest = this.mAudioResourceRequest;
        if (audioResourceRequest != null) {
            audioResourceRequest.cancel();
        }
        MovieDownloadManager.getInstance().cancelAll();
        this.mMovieManager = null;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            if (this.mIsShortVideo != this.mMovieInfo.isShortVideo) {
                refreshEditAdapter();
                setShortRadioButtonCheck(this.mMovieInfo.isShortVideo);
            }
            if (this.mDeleteClicked) {
                refreshEditAdapter();
                this.mDeleteClicked = false;
            }
        }
    }

    public void onResumeClick() {
        this.mEditAdapter.setSelectedItemPosition(-1);
    }
}
