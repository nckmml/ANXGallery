package com.miui.gallery.discovery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.discovery.BaseMessageOperator;
import com.miui.gallery.model.DiscoveryMessage;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.util.ArrayList;
import java.util.List;

public class RecentDiscoveryMessageOperator extends BaseMessageOperator<RecentSaveParams> {
    /* access modifiers changed from: private */
    public static Gson sGson;

    public static class RecentMessageDetail extends DiscoveryMessage.BaseMessageDetail {
        private String[] thumbUrls;
        private int unviewMediaCount;

        public static RecentMessageDetail fromJson(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return (RecentMessageDetail) RecentDiscoveryMessageOperator.sGson.fromJson(str, RecentMessageDetail.class);
                } catch (Exception e) {
                    Log.d("RecentDiscoveryMessageOperator", "Unable to parse extraData json to object: %s", (Object) str);
                    e.printStackTrace();
                }
            }
            return null;
        }

        public String[] getThumbUrls() {
            return this.thumbUrls;
        }

        public int getUnviewMediaCount() {
            return this.unviewMediaCount;
        }

        public void setThumbUrls(String[] strArr) {
            this.thumbUrls = strArr;
        }

        public void setUnviewMediaCount(int i) {
            this.unviewMediaCount = i;
        }

        public String toJson() {
            return RecentDiscoveryMessageOperator.sGson.toJson((Object) this);
        }
    }

    public static class RecentSaveParams {
        private int mMediaCount;
        private List<String> mThumbUrls;

        public RecentSaveParams(int i, List<String> list) {
            this.mMediaCount = i;
            this.mThumbUrls = list;
        }

        public int getMediaCount() {
            return this.mMediaCount;
        }

        public List<String> getThumbUrls() {
            return this.mThumbUrls;
        }
    }

    public RecentDiscoveryMessageOperator() {
        sGson = new Gson();
    }

    /* access modifiers changed from: protected */
    public boolean doMarkMessageAsRead(Context context, DiscoveryMessage discoveryMessage) {
        discoveryMessage.setConsumed(true);
        if (discoveryMessage.getMessageDetail() instanceof RecentMessageDetail) {
            ((RecentMessageDetail) discoveryMessage.getMessageDetail()).setThumbUrls((String[]) null);
            ((RecentMessageDetail) discoveryMessage.getMessageDetail()).setUnviewMediaCount(0);
        } else {
            Log.e("RecentDiscoveryMessageOperator", "messageDetail should be instance of RecentMessageDetail");
        }
        return doUpdateMessage(context, discoveryMessage);
    }

    /* access modifiers changed from: protected */
    public boolean doSaveMessage(Context context, RecentSaveParams recentSaveParams) {
        Cursor cursor;
        int i;
        String[] strArr;
        String[] strArr2;
        Context context2 = context;
        int mediaCount = recentSaveParams.getMediaCount();
        List thumbUrls = recentSaveParams.getThumbUrls() != null ? recentSaveParams.getThumbUrls() : new ArrayList();
        ContentValues contentValues = new ContentValues();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor queryMessageByType = queryMessageByType(context);
        if (queryMessageByType != null) {
            try {
                if (queryMessageByType.moveToFirst()) {
                    long j = queryMessageByType.getLong(queryMessageByType.getColumnIndex("_id"));
                    RecentMessageDetail fromJson = RecentMessageDetail.fromJson(queryMessageByType.getString(queryMessageByType.getColumnIndex("extraData")));
                    if (fromJson != null) {
                        i = mediaCount + fromJson.getUnviewMediaCount();
                        strArr = fromJson.getThumbUrls();
                    } else {
                        fromJson = new RecentMessageDetail();
                        i = mediaCount;
                        strArr = null;
                    }
                    if (strArr != null) {
                        int length = strArr.length;
                        cursor = queryMessageByType;
                        int i2 = 0;
                        while (i2 < length) {
                            int i3 = length;
                            try {
                                String str = strArr[i2];
                                if (!thumbUrls.contains(str)) {
                                    strArr2 = strArr;
                                    if (thumbUrls.size() < 3) {
                                        thumbUrls.add(str);
                                    }
                                } else {
                                    strArr2 = strArr;
                                    i--;
                                }
                                i2++;
                                Context context3 = context;
                                length = i3;
                                strArr = strArr2;
                            } catch (Exception e) {
                                e = e;
                                try {
                                    Log.e("RecentDiscoveryMessageOperator", "Something wrong happened when save message: %s.", (Object) e.getMessage());
                                    e.printStackTrace();
                                    MiscUtil.closeSilently(cursor);
                                    return false;
                                } catch (Throwable th) {
                                    th = th;
                                    MiscUtil.closeSilently(cursor);
                                    throw th;
                                }
                            }
                        }
                    } else {
                        cursor = queryMessageByType;
                    }
                    fromJson.setUnviewMediaCount(i);
                    String[] strArr3 = new String[Math.min(thumbUrls.size(), 3)];
                    for (int i4 = 0; i4 < strArr3.length; i4++) {
                        strArr3[i4] = (String) thumbUrls.get(i4);
                    }
                    fromJson.setThumbUrls(strArr3);
                    contentValues.put("_id", Long.valueOf(j));
                    contentValues.put("extraData", fromJson.toJson());
                    contentValues.put("isConsumed", 0);
                    contentValues.put("updateTime", Long.valueOf(currentTimeMillis));
                    contentValues.put("actionUri", GalleryContract.RecentAlbum.VIEW_PAGE_URI.toString());
                    boolean run = new BaseMessageOperator.UpdateTask(context, (DiscoveryMessage) null, contentValues).run();
                    MiscUtil.closeSilently(cursor);
                    return run;
                }
            } catch (Exception e2) {
                e = e2;
                cursor = queryMessageByType;
                Log.e("RecentDiscoveryMessageOperator", "Something wrong happened when save message: %s.", (Object) e.getMessage());
                e.printStackTrace();
                MiscUtil.closeSilently(cursor);
                return false;
            } catch (Throwable th2) {
                th = th2;
                cursor = queryMessageByType;
                MiscUtil.closeSilently(cursor);
                throw th;
            }
        }
        Context context4 = context2;
        cursor = queryMessageByType;
        RecentMessageDetail recentMessageDetail = new RecentMessageDetail();
        recentMessageDetail.setUnviewMediaCount(mediaCount);
        String[] strArr4 = thumbUrls.size() >= 3 ? new String[3] : new String[thumbUrls.size()];
        for (int i5 = 0; i5 < strArr4.length; i5++) {
            strArr4[i5] = (String) thumbUrls.get(i5);
        }
        recentMessageDetail.setThumbUrls(strArr4);
        contentValues.put("extraData", recentMessageDetail.toJson());
        contentValues.put(nexExportFormat.TAG_FORMAT_TYPE, Integer.valueOf(getMessageType()));
        contentValues.put("receiveTime", Long.valueOf(currentTimeMillis));
        contentValues.put("updateTime", Long.valueOf(currentTimeMillis));
        contentValues.put("isConsumed", 0);
        contentValues.put("actionUri", GalleryContract.RecentAlbum.VIEW_PAGE_URI.toString());
        boolean run2 = new BaseMessageOperator.InsertTask(context4, contentValues).run();
        MiscUtil.closeSilently(cursor);
        return run2;
    }

    public void doWrapMessage(DiscoveryMessage discoveryMessage, String str) {
        RecentMessageDetail fromJson = RecentMessageDetail.fromJson(str);
        if (fromJson != null) {
            int unviewMediaCount = fromJson.getUnviewMediaCount();
            discoveryMessage.setMessage(GalleryApp.sGetAndroidContext().getResources().getQuantityString(R.plurals.quickly_discovery_message_format, unviewMediaCount, new Object[]{Integer.valueOf(unviewMediaCount)}));
        }
        discoveryMessage.setMessageDetail(fromJson);
    }

    public int getMessageType() {
        return 1;
    }
}
