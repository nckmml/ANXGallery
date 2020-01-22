package com.miui.gallery.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import com.miui.gallery.collage.CollageActivity;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.ui.JumpDialogFragment;
import com.miui.gallery.util.GalleryIntent;
import java.util.Map;

public class ActionURIHandler {
    protected static final UriMatcher sURIMatcher = new UriMatcher(-1);

    static {
        sURIMatcher.addURI("gallery.i.mi.com", "people", 1);
        sURIMatcher.addURI("gallery.i.mi.com", "album", 2);
        sURIMatcher.addURI("gallery.i.mi.com", "trash_bin", 3);
        sURIMatcher.addURI("gallery.i.mi.com", "secret_album", 4);
        sURIMatcher.addURI("gallery.i.mi.com", "peoples", 5);
        sURIMatcher.addURI("gallery.i.mi.com", "result", 6);
        sURIMatcher.addURI("gallery.i.mi.com", "search", 7);
        sURIMatcher.addURI("gallery.i.mi.com", "cloud_guide", 8);
        sURIMatcher.addURI("gallery.i.mi.com", "sync_switch", 9);
        sURIMatcher.addURI("gallery.i.mi.com", "photo_movie", 10);
        sURIMatcher.addURI("gallery.i.mi.com", "collage", 11);
        sURIMatcher.addURI("gallery.i.mi.com", "card_action", 12);
        sURIMatcher.addURI("gallery.i.mi.com", "collage_from_picker", 13);
        sURIMatcher.addURI("gallery.i.mi.com", "filter_sky", 14);
    }

    private static Intent buildIntent(Activity activity, Uri uri, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setPackage(activity.getApplicationContext().getPackageName());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

    public static void handleUri(Activity activity, Uri uri) {
        handleUri(activity, uri, (Bundle) null);
    }

    public static void handleUri(Activity activity, Uri uri, Bundle bundle) {
        if (activity != null && !activity.isDestroyed() && uri != null) {
            int match = sURIMatcher.match(uri);
            String queryParameter = uri.getQueryParameter("query");
            Map<String, String> buildSearchEventParams = SearchStatUtils.buildSearchEventParams(bundle);
            boolean z = false;
            switch (match) {
                case 1:
                    String queryParameter2 = uri.getQueryParameter("serverId");
                    JumpDialogFragment.showPeoplePage(activity, queryParameter2);
                    if (buildSearchEventParams != null) {
                        buildSearchEventParams.put("serverIds", queryParameter2);
                        buildSearchEventParams.put("queryText", queryParameter);
                        SearchStatUtils.reportEvent((String) null, "client_jump_to_people_page", buildSearchEventParams);
                        return;
                    }
                    return;
                case 2:
                    JumpDialogFragment.showAlbumPage(activity, uri);
                    if (buildSearchEventParams != null) {
                        String queryParameter3 = uri.getQueryParameter("screenshotAppName");
                        buildSearchEventParams.put("Uri", uri.toString());
                        buildSearchEventParams.put("queryText", queryParameter3);
                        SearchStatUtils.reportEvent((String) null, "client_jump_to_album_page", buildSearchEventParams);
                        return;
                    }
                    return;
                case 3:
                    IntentUtil.gotoTrashBin(activity, "Search");
                    if (buildSearchEventParams != null) {
                        SearchStatUtils.reportEvent((String) null, "client_jump_to_trash_bin", buildSearchEventParams);
                        return;
                    }
                    return;
                case 4:
                    JumpDialogFragment.enterPrivateAlbum(activity);
                    if (buildSearchEventParams != null) {
                        SearchStatUtils.reportEvent((String) null, "client_jump_to_secret_album", buildSearchEventParams);
                        return;
                    }
                    return;
                case 5:
                    if (uri.getBooleanQueryParameter("markMode", false)) {
                        startActivityForResult(activity, uri, bundle, 41);
                        if (buildSearchEventParams != null) {
                            buildSearchEventParams.put("url", uri.toString());
                            SearchStatUtils.reportEvent((String) null, "suggestion_open_mark_page", buildSearchEventParams);
                            return;
                        }
                        return;
                    }
                    openDirectly(activity, uri, bundle);
                    if (buildSearchEventParams != null) {
                        buildSearchEventParams.put("url", uri.toString());
                        SearchStatUtils.reportEvent((String) null, "client_jump_to_people_list_page", buildSearchEventParams);
                        return;
                    }
                    return;
                case 6:
                    openDirectly(activity, uri, bundle);
                    return;
                case 7:
                    openDirectly(activity, uri, bundle);
                    return;
                case 8:
                    IntentUtil.guideToLoginXiaomiAccount((Context) activity, GalleryIntent.CloudGuideSource.URL);
                    return;
                case 9:
                    IntentUtil.gotoTurnOnSyncSwitch(activity);
                    return;
                case 10:
                    PhotoMovieEntranceUtils.startPicker(activity);
                    return;
                case 11:
                    IntentUtil.startCollagePicker(activity);
                    return;
                case 12:
                    String queryParameter4 = uri.getQueryParameter("actionType");
                    if ("album".equals(queryParameter4)) {
                        if (bundle != null) {
                            IntentUtil.gotoStoryAlbum(activity, bundle.getLong("card_id"));
                            return;
                        } else {
                            Log.e("ActionURIHandler", "go to strory album ,invalid extra");
                            return;
                        }
                    } else if ("operation".equals(queryParameter4)) {
                        IntentUtil.gotoOperationCard(activity, bundle.getString("card_url"));
                        return;
                    } else {
                        Log.w("ActionURIHandler", "type %s can not delivery", queryParameter4);
                        return;
                    }
                case 13:
                    if (bundle != null) {
                        Intent intent = (Intent) bundle.getParcelable("extra_intent");
                        boolean z2 = bundle.getBoolean("start_activity_for_result", false);
                        int i = bundle.getInt("request_code", -1);
                        if (intent != null) {
                            if (IntentUtil.isSupportMeituCollage()) {
                                z = IntentUtil.startMeituCollageAction(activity, intent.getParcelableArrayListExtra("pick-result-data"), i);
                            }
                            if (!z) {
                                intent.setClass(activity, CollageActivity.class);
                                if (z2) {
                                    activity.startActivityForResult(intent, i);
                                    return;
                                } else {
                                    activity.startActivity(intent);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                case 14:
                    FilterSkyEntranceUtils.startFilterSkyFromPicker(activity);
                    return;
                default:
                    openDirectly(activity, uri, bundle);
                    return;
            }
        }
    }

    private static void openDirectly(Activity activity, Uri uri, Bundle bundle) {
        int i;
        boolean z = false;
        if (bundle != null) {
            try {
                z = bundle.getBoolean("start_activity_for_result", false);
                bundle.remove("start_activity_for_result");
                i = bundle.getInt("request_code");
                bundle.remove("request_code");
            } catch (Exception e) {
                Log.e("ActionURIHandler", "openDirectly %s occur error.\n", uri, e);
                return;
            }
        } else {
            i = 0;
        }
        Intent buildIntent = buildIntent(activity, uri, bundle);
        if (z) {
            activity.startActivityForResult(buildIntent, i);
        } else {
            activity.startActivity(buildIntent);
        }
    }

    private static void startActivityForResult(Activity activity, Uri uri, Bundle bundle, int i) {
        try {
            activity.startActivityForResult(buildIntent(activity, uri, bundle), i);
        } catch (Exception e) {
            Log.e("ActionURIHandler", "openDirectly %s occur error.\n", uri, e);
        }
    }
}
