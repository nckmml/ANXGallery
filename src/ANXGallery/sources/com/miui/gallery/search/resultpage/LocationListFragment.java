package com.miui.gallery.search.resultpage;

import android.content.Context;
import com.miui.gallery.R;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.resultpage.SearchResultFragment;

public class LocationListFragment extends SearchResultFragment {

    private class LocationListErrorViewAdapter extends SearchResultFragment.ErrorViewAdapter {
        public LocationListErrorViewAdapter(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public int getIconResForStatus(int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            if (infoViewPosition == StatusHandleHelper.InfoViewPosition.FULL_SCREEN) {
                return R.drawable.empty_page_places;
            }
            if (infoViewPosition == StatusHandleHelper.InfoViewPosition.FOOTER) {
                return R.drawable.search_connection_error_icon;
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public String getInfoTitleForStatus(int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            boolean z = infoViewPosition == StatusHandleHelper.InfoViewPosition.FULL_SCREEN;
            int i2 = R.string.places_album_empty_title;
            if (i != 1) {
                if (i == 10) {
                    i2 = R.string.search_syncing;
                } else if (i == 13) {
                    i2 = R.string.ai_album_requesting_title;
                } else if (i != 3) {
                    if (i != 4) {
                        if (!z) {
                            i2 = R.string.search_error_and_retry;
                        }
                    } else if (!z) {
                        i2 = R.string.search_backup_title;
                    }
                } else if (!z) {
                    i2 = R.string.search_login_title;
                }
            } else if (!z) {
                i2 = R.string.search_connection_error_and_set;
            }
            return this.mContext.getString(i2);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public StatusHandleHelper.AbstractErrorViewAdapter getErrorViewAdapter() {
        if (this.mErrorViewAdapter == null) {
            this.mErrorViewAdapter = new LocationListErrorViewAdapter(this.mActivity);
        }
        return this.mErrorViewAdapter;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.search_location_list_fragment;
    }
}
