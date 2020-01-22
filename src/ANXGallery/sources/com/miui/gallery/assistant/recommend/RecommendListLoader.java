package com.miui.gallery.assistant.recommend;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.Merger;
import com.miui.gallery.cloudcontrol.observers.FeatureStrategyObserver;
import com.miui.gallery.cloudcontrol.strategies.RecommendStrategy;

public class RecommendListLoader extends AsyncTaskLoader<RecommendStrategy> {
    private Context mContext;
    private FeatureStrategyObserver mFeatureStrategyObserver = new FeatureStrategyObserver<RecommendStrategy>() {
        public void onStrategyChanged(String str, RecommendStrategy recommendStrategy) {
            RecommendListLoader.this.onContentChanged();
        }
    };

    public RecommendListLoader(Context context) {
        super(context);
        this.mContext = context;
    }

    public RecommendStrategy loadInBackground() {
        RecommendStrategy recommendStrategy = CloudControlStrategyHelper.getRecommendStrategy();
        return (recommendStrategy != null || !new CloudControlRequestHelper(this.mContext).execRecommendRequest()) ? recommendStrategy : CloudControlStrategyHelper.getRecommendStrategy();
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        CloudControlManager.getInstance().registerStrategyObserver("recommendation", RecommendStrategy.class, (Merger) null, this.mFeatureStrategyObserver);
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        super.onStopLoading();
        CloudControlManager.getInstance().unregisterStrategyObserver(this.mFeatureStrategyObserver);
    }
}
