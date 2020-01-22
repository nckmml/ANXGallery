package com.xiaomi.push.mpcd;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.push.mpcd.job.CollectionJob;
import com.xiaomi.xmpush.thrift.ClientCollectionType;
import com.xiaomi.xmpush.thrift.DataCollectionItem;

public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
    private String mActiveStartTS = "";
    private Context mContext;
    private String mCurrentActiveActivity;

    public ActivityLifecycleCallbacksImpl(Context context, String str) {
        this.mContext = context;
        this.mActiveStartTS = str;
    }

    private void writeData(String str) {
        DataCollectionItem dataCollectionItem = new DataCollectionItem();
        dataCollectionItem.setContent(str);
        dataCollectionItem.setCollectedAt(System.currentTimeMillis());
        dataCollectionItem.setCollectionType(ClientCollectionType.ActivityActiveTimeStamp);
        CollectionJob.writeItemToFile(this.mContext, dataCollectionItem);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (!TextUtils.isEmpty(this.mActiveStartTS) && !TextUtils.isEmpty(localClassName)) {
            this.mCurrentActiveActivity = "";
            if (TextUtils.isEmpty(this.mCurrentActiveActivity) || TextUtils.equals(this.mCurrentActiveActivity, localClassName)) {
                writeData(this.mContext.getPackageName() + "|" + localClassName + ":" + this.mActiveStartTS + "," + String.valueOf(System.currentTimeMillis() / 1000));
                this.mActiveStartTS = "";
                this.mCurrentActiveActivity = "";
                return;
            }
            this.mActiveStartTS = "";
        }
    }

    public void onActivityResumed(Activity activity) {
        if (TextUtils.isEmpty(this.mCurrentActiveActivity)) {
            this.mCurrentActiveActivity = activity.getLocalClassName();
        }
        this.mActiveStartTS = String.valueOf(System.currentTimeMillis() / 1000);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
