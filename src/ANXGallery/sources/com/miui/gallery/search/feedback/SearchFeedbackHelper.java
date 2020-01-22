package com.miui.gallery.search.feedback;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.FeatureProfile;
import com.miui.gallery.net.base.RequestError;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.core.source.server.ServerSearchRequest;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.utils.SearchUtils;
import com.miui.gallery.ui.ProcessTask;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.PrivacyAgreementUtils;
import com.miui.gallery.util.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import miui.app.AlertDialog;

public class SearchFeedbackHelper {
    private static boolean CAN_USE_CACHE = false;
    private static final String LANGUAGE_CH = "cn";
    private static final String LANGUAGE_EH = "en";

    /* renamed from: com.miui.gallery.search.feedback.SearchFeedbackHelper$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType = new int[FeedbackType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType[FeedbackType.FALSE_POSITIVE.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType[FeedbackType.FALSE_NEGATIVE.ordinal()] = 2;
        }
    }

    private static class FeedbackReportResponseData {
        @SerializedName("failureImageId")
        public Set<Long> failureImageIds;

        private FeedbackReportResponseData() {
        }
    }

    private static class FeedbackTaskResponseData {
        @SerializedName("awardInfo")
        public String awardInfo = null;
        @SerializedName("finishImageNum")
        public int finishImageNum = 0;
        @SerializedName("needHandleImageNum")
        public int needHandleImageNum = 0;

        private FeedbackTaskResponseData() {
        }
    }

    private enum FeedbackType {
        FALSE_POSITIVE("FP"),
        FALSE_NEGATIVE("FN");
        
        private String mValue;

        private FeedbackType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public interface OnFeedbackCompleteListener {
        void onComplete(int i);
    }

    private static void addFeedbackReportedTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            String feedbackReportedTags = GalleryPreferences.Search.getFeedbackReportedTags();
            if (!TextUtils.isEmpty(str)) {
                str = feedbackReportedTags + "," + str;
            }
            GalleryPreferences.Search.setFeedbackReportedTags(str);
        }
    }

    private static String getFeedbackDialogText(Context context, String str, FeedbackType feedbackType) {
        int i;
        int i2 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType[feedbackType.ordinal()];
        if (i2 == 1) {
            i = R.string.search_feedback_false_positive_text;
        } else if (i2 == 2) {
            i = R.string.search_feedback_false_negative_text;
        } else {
            throw new IllegalArgumentException("Invalid feedback type " + feedbackType.getValue());
        }
        return context.getString(i, new Object[]{str});
    }

    /* access modifiers changed from: private */
    public static String getFeedbackResultText(Context context, String str, FeedbackType feedbackType, int i, boolean z) {
        int i2;
        if (i > 0) {
            int i3 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType[feedbackType.ordinal()];
            if (i3 == 1) {
                i2 = z ? R.plurals.search_feedback_done_mark_and_contribute_false_positive : R.plurals.search_feedback_done_mark_false_positive;
            } else if (i3 == 2) {
                i2 = z ? R.plurals.search_feedback_done_mark_and_contribute_false_negative : R.plurals.search_feedback_done_mark_false_negative;
            } else {
                throw new IllegalArgumentException("Invalid feedback type " + feedbackType.getValue());
            }
            return context.getResources().getQuantityString(i2, i, new Object[]{Integer.valueOf(i), str});
        }
        int i4 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$feedback$SearchFeedbackHelper$FeedbackType[feedbackType.ordinal()];
        if (i4 == 1) {
            return context.getString(R.string.search_failed_mark_feedback_false_positive);
        }
        if (i4 == 2) {
            return context.getString(R.string.search_failed_mark_feedback_false_negative);
        }
        throw new IllegalArgumentException("Invalid feedback type " + feedbackType.getValue());
    }

    public static Bundle getFeedbackTaskInfo() {
        String xiaomiId = SearchUtils.getXiaomiId();
        if (xiaomiId == null) {
            SearchLog.d("SearchFeedbackHelper", "Not logged in!");
            return null;
        } else if (!PrivacyAgreementUtils.isCloudServiceAgreementEnable(GalleryApp.sGetAndroidContext())) {
            SearchLog.d("SearchFeedbackHelper", "Cloud privacy agreement denied");
            return null;
        } else {
            ServerSearchRequest build = new ServerSearchRequest.Builder().setQueryPathPrefix(HostManager.Search.getSearchFeedbackUrlHost()).setUserPath(ServerSearchRequest.Builder.getDefaultUserPath(xiaomiId)).setUserId(xiaomiId).setQueryAppendPath("tag/feedback/task/num").setResultDataType(FeedbackTaskResponseData.class).setUseCache(CAN_USE_CACHE).build();
            try {
                Object[] executeSync = build.executeSync();
                if (executeSync != null && executeSync.length > 0 && (executeSync[0] instanceof FeedbackTaskResponseData)) {
                    FeedbackTaskResponseData feedbackTaskResponseData = (FeedbackTaskResponseData) executeSync[0];
                    Bundle bundle = new Bundle();
                    bundle.putInt("need_handle_image_num", feedbackTaskResponseData.needHandleImageNum);
                    bundle.putInt("finish_image_num", feedbackTaskResponseData.finishImageNum);
                    if (!TextUtils.isEmpty(feedbackTaskResponseData.awardInfo)) {
                        bundle.putString("task_award_info", feedbackTaskResponseData.awardInfo);
                    }
                    build.setCacheExpires(300000);
                    CAN_USE_CACHE = true;
                    return bundle;
                }
            } catch (RequestError e) {
                SearchLog.e("SearchFeedbackHelper", "Querying feedback info failed, %s", e);
            }
            SearchLog.e("SearchFeedbackHelper", "Empty feedback task info");
            return null;
        }
    }

    public static void goToFeedbackPolicyPage(Activity activity) {
        Object[] objArr = new Object[1];
        objArr[0] = Locale.getDefault().getLanguage().equals("zh") ? LANGUAGE_CH : LANGUAGE_EH;
        goToHybridPage(activity, String.format("https://i.mi.com/static2?filename=MicloudWebBill/event/gallery/privacy/%s.html", objArr));
    }

    private static void goToHybridPage(Activity activity, String str) {
        ActionURIHandler.handleUri(activity, GalleryContract.Common.URI_HYBRID_PAGE.buildUpon().appendQueryParameter("url", str).build());
    }

    private static boolean hasReportedTag(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String feedbackReportedTags = GalleryPreferences.Search.getFeedbackReportedTags();
        if (!TextUtils.isEmpty(feedbackReportedTags)) {
            for (String equals : feedbackReportedTags.split(",")) {
                if (str.equals(equals)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean needToQueryLikelyImagesForFeedbackTask(String str) {
        return !hasReportedTag(str);
    }

    /* access modifiers changed from: private */
    public static int reportFalseImages(String str, List<String> list, FeedbackType feedbackType, boolean z) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            SearchLog.d("SearchFeedbackHelper", "Invalid false image params!");
            return 0;
        }
        String xiaomiId = SearchUtils.getXiaomiId();
        if (xiaomiId == null) {
            SearchLog.d("SearchFeedbackHelper", "Not logged in!");
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            int min = Math.min(list.size(), i + 10);
            i2 += reportFalseImagesBatch(xiaomiId, str, list.subList(i, min), feedbackType, z);
            i = min;
        }
        if (i2 > 0) {
            CAN_USE_CACHE = false;
            if (feedbackType.equals(FeedbackType.FALSE_NEGATIVE)) {
                addFeedbackReportedTag(str);
            }
        }
        return i2;
    }

    private static int reportFalseImagesBatch(String str, String str2, List<String> list, FeedbackType feedbackType, boolean z) {
        if (!PrivacyAgreementUtils.isCloudServiceAgreementEnable(GalleryApp.sGetAndroidContext())) {
            SearchLog.d("SearchFeedbackHelper", "Cloud privacy agreement denied");
            return 0;
        }
        int size = list.size();
        String join = TextUtils.join(",", list);
        SearchLog.d("SearchFeedbackHelper", "Reporting false image info [%s: %s: %s]", feedbackType, str2, list);
        try {
            Object[] executeSync = new ServerSearchRequest.Builder().setQueryPathPrefix(HostManager.Search.getSearchFeedbackUrlHost()).setQueryAppendPath("tag/feedback").setUserPath(ServerSearchRequest.Builder.getDefaultUserPath(str)).setMethod(1002).setUserId(str).setResultDataType(FeedbackReportResponseData.class).addQueryParam("feedbackType", feedbackType.getValue()).addQueryParam("tagName", str2).addQueryParam("imageIds", join).addQueryParam("isDonate", String.valueOf(z)).build().executeSync();
            if (executeSync != null && executeSync.length > 0 && (executeSync[0] instanceof FeedbackReportResponseData)) {
                FeedbackReportResponseData feedbackReportResponseData = (FeedbackReportResponseData) executeSync[0];
                if (feedbackReportResponseData.failureImageIds != null) {
                    SearchLog.d("SearchFeedbackHelper", "[%s] report failed!", feedbackReportResponseData.failureImageIds);
                    return size - feedbackReportResponseData.failureImageIds.size();
                }
            }
            SearchLog.d("SearchFeedbackHelper", "Done report batch!");
            return size;
        } catch (RequestError e) {
            SearchLog.e("SearchFeedbackHelper", "On report batch error! %s", e);
            return 0;
        }
    }

    public static void reportFalseNegativeImages(Activity activity, String str, boolean z, ArrayList<String> arrayList, OnFeedbackCompleteListener onFeedbackCompleteListener) {
        reportInDialog(activity, str, z, arrayList, FeedbackType.FALSE_NEGATIVE, onFeedbackCompleteListener);
    }

    public static void reportFalsePositiveImages(Activity activity, String str, boolean z, ArrayList<String> arrayList, OnFeedbackCompleteListener onFeedbackCompleteListener) {
        reportInDialog(activity, str, z, arrayList, FeedbackType.FALSE_POSITIVE, onFeedbackCompleteListener);
    }

    private static void reportInDialog(Activity activity, String str, boolean z, ArrayList<String> arrayList, FeedbackType feedbackType, OnFeedbackCompleteListener onFeedbackCompleteListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (z || supportFeedbackTask()) {
            builder.setCheckBox(z, activity.getString(R.string.search_feedback_contribute));
        }
        final String str2 = str;
        final ArrayList<String> arrayList2 = arrayList;
        final FeedbackType feedbackType2 = feedbackType;
        final Activity activity2 = activity;
        final OnFeedbackCompleteListener onFeedbackCompleteListener2 = onFeedbackCompleteListener;
        builder.setTitle(getFeedbackDialogText(activity, str, feedbackType)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final boolean isChecked = ((AlertDialog) dialogInterface).isChecked();
                ProcessTask processTask = new ProcessTask(new ProcessTask.ProcessCallback<Void, Integer>() {
                    public Integer doProcess(Void[] voidArr) {
                        return Integer.valueOf(SearchFeedbackHelper.reportFalseImages(str2, arrayList2, feedbackType2, isChecked));
                    }
                });
                processTask.setCompleteListener(new ProcessTask.OnCompleteListener() {
                    public void onCompleteProcess(Object obj) {
                        int intValue = obj == null ? 0 : ((Integer) obj).intValue();
                        ToastUtils.makeText((Context) activity2, (CharSequence) SearchFeedbackHelper.getFeedbackResultText(activity2, str2, feedbackType2, intValue, isChecked));
                        if (onFeedbackCompleteListener2 != null) {
                            onFeedbackCompleteListener2.onComplete(intValue);
                        }
                    }
                });
                Activity activity = activity2;
                processTask.showProgress(activity, activity.getString(R.string.operation_in_process));
                processTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        try {
            builder.create().show();
        } catch (IllegalStateException e) {
            SearchLog.w("SearchFeedbackHelper", "Ignore exception: %s", e);
        }
    }

    public static boolean supportFeedbackTask() {
        return CloudControlManager.getInstance().queryFeatureStatus("search-feedback-task") == FeatureProfile.Status.ENABLE;
    }
}
