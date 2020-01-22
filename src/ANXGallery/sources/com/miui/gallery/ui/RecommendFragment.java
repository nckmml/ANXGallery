package com.miui.gallery.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.miui.gallery.R;
import com.miui.gallery.assistant.recommend.RecommendFragmentAdapter;
import com.miui.gallery.assistant.recommend.RecommendListLoader;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.cloudcontrol.RecommendItem;
import com.miui.gallery.cloudcontrol.strategies.RecommendStrategy;
import com.miui.gallery.reddot.DisplayStatusManager;
import com.miui.gallery.util.BaseNetworkUtils;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RecommendFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public RecommendFragmentAdapter mFragmentAdapter;
    /* access modifiers changed from: private */
    public View mLoadingView;
    private LoaderManager.LoaderCallbacks<RecommendStrategy> mRecommendStrategyLoaderCallbacks = new LoaderManager.LoaderCallbacks<RecommendStrategy>() {
        /* JADX WARNING: type inference failed for: r2v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<RecommendStrategy> onCreateLoader(int i, Bundle bundle) {
            return new RecommendListLoader(RecommendFragment.this.mActivity);
        }

        /* JADX WARNING: type inference failed for: r0v5, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        public void onLoadFinished(Loader<RecommendStrategy> loader, RecommendStrategy recommendStrategy) {
            if (recommendStrategy != null) {
                RecommendFragment.this.mFragmentAdapter.changeRecommendItems((ArrayList) recommendStrategy.getRecommendItems().clone());
                RecommendFragment.this.mLoadingView.setVisibility(8);
                RecommendFragment.this.mRecyclerView.setVisibility(0);
                DisplayStatusManager.setRedDotClicked("recommendation");
                new RequeryTask(RecommendFragment.this.mActivity).execute(new Void[0]);
                return;
            }
            RecommendFragment.this.mLoadingView.setVisibility(0);
            RecommendFragment.this.mLoadingView.findViewById(R.id.icon).setVisibility(0);
            ((TextView) RecommendFragment.this.mLoadingView.findViewById(R.id.text)).setText(R.string.assistant_page_server_error);
        }

        public void onLoaderReset(Loader<RecommendStrategy> loader) {
            RecommendFragment.this.mFragmentAdapter.changeRecommendItems((ArrayList<RecommendItem>) null);
        }
    };
    /* access modifiers changed from: private */
    public GalleryRecyclerView mRecyclerView;

    private static class RequeryTask extends AsyncTask<Void, Integer, Void> {
        private WeakReference<Activity> mActivityRef;

        public RequeryTask(Activity activity) {
            this.mActivityRef = new WeakReference<>(activity);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            if (((Activity) this.mActivityRef.get()) == null) {
                return null;
            }
            new CloudControlRequestHelper((Context) this.mActivityRef.get()).execRecommendRequest();
            return null;
        }
    }

    public String getPageName() {
        return this.mActivity.getTitle().toString();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (BaseNetworkUtils.isNetworkConnected()) {
            getLoaderManager().initLoader(1, (Bundle) null, this.mRecommendStrategyLoaderCallbacks);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r4v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recommend_fragment, viewGroup, false);
        if (!BaseNetworkUtils.isNetworkConnected()) {
            inflate.findViewById(R.id.network_error).setVisibility(0);
        } else {
            this.mLoadingView = inflate.findViewById(R.id.loading_view);
            this.mLoadingView.setVisibility(0);
            this.mRecyclerView = (GalleryRecyclerView) inflate.findViewById(R.id.recyclerview);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mActivity));
            this.mFragmentAdapter = new RecommendFragmentAdapter(this.mActivity);
            this.mRecyclerView.setAdapter(this.mFragmentAdapter);
        }
        return inflate;
    }
}
