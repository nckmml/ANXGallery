package com.miui.gallery.util.face;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import com.miui.account.AccountHelper;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CheckResult;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class CheckoutRecommendPeople {
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public CheckoutStatusListener mListener;

    public interface CheckoutStatusListener {
        void onFinishCheckoutPeopleFace(int i);
    }

    public CheckoutRecommendPeople(Handler handler, CheckoutStatusListener checkoutStatusListener) {
        this.mHandler = handler;
        this.mListener = checkoutStatusListener;
    }

    /* access modifiers changed from: private */
    public void doGetRecommendPeopleFromNet(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, String str) throws Exception {
        JSONArray jSONArray;
        JSONObject fromXiaomi = CloudUtils.getFromXiaomi(HostManager.PeopleFace.getPeopleRecommendUrl(str), (List<NameValuePair>) null, account, galleryExtendedAuthToken, 0, false);
        StringBuilder sb = new StringBuilder();
        sb.append("doGetRecommendPeopleFromNet peopleId is:");
        sb.append(str);
        sb.append("  json is:");
        sb.append(fromXiaomi == null ? "error" : fromXiaomi.toString());
        Log.d("syncface", sb.toString());
        if (CheckResult.parseErrorCode(fromXiaomi) == 0) {
            JSONObject jSONObject = fromXiaomi.getJSONObject("data");
            if (jSONObject.has("recommendPeoples") && (jSONArray = jSONObject.getJSONArray("recommendPeoples")) != null && jSONArray.length() > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("peopleServerId", str);
                contentValues.put("recommendPeoplesJson", fromXiaomi.toString());
                String[] strArr = {str};
                Cursor safeQueryPeopleRecommend = FaceDataManager.safeQueryPeopleRecommend(CloudUtils.getProjectionAll(), "peopleServerId = ?", strArr);
                if (safeQueryPeopleRecommend == null || safeQueryPeopleRecommend.getCount() <= 0) {
                    FaceDataManager.safeInsertPeopleRecommend(contentValues);
                } else {
                    FaceDataManager.safeUpdatePeopleRecommend(contentValues, "peopleServerId = ?", strArr);
                }
                if (safeQueryPeopleRecommend != null) {
                    safeQueryPeopleRecommend.close();
                }
                notifyStatus(jSONArray.length());
            }
        }
    }

    private void notifyStatus(final int i) {
        CheckoutStatusListener checkoutStatusListener = this.mListener;
        if (checkoutStatusListener != null) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new Runnable() {
                    public void run() {
                        CheckoutRecommendPeople.this.mListener.onFinishCheckoutPeopleFace(i);
                    }
                });
            } else {
                checkoutStatusListener.onFinishCheckoutPeopleFace(i);
            }
        }
    }

    public void clearListener() {
        this.mListener = null;
    }

    public void getRecommendPeopleFromNet(final String str) {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                if (!GalleryPreferences.CTA.canConnectNetwork()) {
                    Log.d("syncface", "getRecommendPeopleFromNet  cta not allowed");
                    return null;
                }
                Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
                Account xiaomiAccount = AccountHelper.getXiaomiAccount(sGetAndroidContext);
                if (xiaomiAccount == null) {
                    Log.d("syncface", "getRecommendPeopleFromNet  account is null");
                    return null;
                }
                GalleryExtendedAuthToken extToken = CloudUtils.getExtToken(sGetAndroidContext, xiaomiAccount);
                if (extToken == null) {
                    Log.d("syncface", "getRecommendPeopleFromNet  token is null");
                    return null;
                }
                try {
                    CheckoutRecommendPeople.this.doGetRecommendPeopleFromNet(sGetAndroidContext, xiaomiAccount, extToken, str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
