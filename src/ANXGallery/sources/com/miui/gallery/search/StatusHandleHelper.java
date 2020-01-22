package com.miui.gallery.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;

public class StatusHandleHelper {
    private int mBaseStatus = -1;
    private ErrorViewAdapter mErrorViewAdapter;
    private View[] mFullScreenViews;
    private View mInfoFooterView;
    private View mInfoHeaderView;
    private boolean mInitiated;
    private int mResultStatus = -1;
    private int mTopVisibleView = -1;

    public static abstract class AbstractErrorViewAdapter implements ErrorViewAdapter {
        protected final Context mContext;
        protected final LayoutInflater mInflater = LayoutInflater.from(this.mContext);

        public AbstractErrorViewAdapter(Context context) {
            this.mContext = context;
        }

        private String getInfoSubTitleForStatus(int i) {
            int i2;
            if (i == 3) {
                i2 = R.string.search_login_sub_title;
            } else if (i != 4) {
                return null;
            } else {
                i2 = R.string.search_backup_sub_title;
            }
            return this.mContext.getString(i2);
        }

        /* access modifiers changed from: protected */
        public void bindFixActionForStatus(int i, View view, InfoViewPosition infoViewPosition) {
            final String str;
            if (i == 1) {
                str = "android.settings.SETTINGS";
            } else if (i == 10) {
                view.setVisibility(4);
                return;
            } else if (i == 3) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cloud_guide_source", GalleryIntent.CloudGuideSource.SEARCH);
                        IntentUtil.guideToLoginXiaomiAccount(AbstractErrorViewAdapter.this.mContext, bundle);
                    }
                });
                return;
            } else if (i == 4) {
                str = "com.miui.gallery.cloud.provider.SYNC_SETTINGS";
            } else if (i != 5) {
                switch (i) {
                    case 12:
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                AIAlbumStatusHelper.setLocalSearchStatus(AbstractErrorViewAdapter.this.mContext, true);
                                AbstractErrorViewAdapter.this.requestRetry();
                                GallerySamplingStatHelper.recordCountEvent("search", "search_open_switch");
                            }
                        });
                        return;
                    case 13:
                    case 14:
                        view.setOnClickListener((View.OnClickListener) null);
                        return;
                    default:
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                AbstractErrorViewAdapter.this.requestRetry();
                            }
                        });
                        return;
                }
            } else {
                str = "android.settings.WIFI_SETTINGS";
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AbstractErrorViewAdapter.this.mContext.startActivity(new Intent(str));
                }
            });
        }

        public void bindInfoView(View view, int i, InfoViewPosition infoViewPosition) {
            if (view != null) {
                setTextIfExist(view, R.id.title, getInfoTitleForStatus(i, infoViewPosition));
                setTextIfExist(view, R.id.sub_title, getInfoSubTitleForStatus(i));
                if (shouldShowFixButtonForStatus(i, infoViewPosition)) {
                    setVisibilityIfExist(view, R.id.fix_button, 0);
                    setTextIfExist(view, R.id.fix_button, getFixButtonTextForStatus(i, infoViewPosition));
                } else {
                    setVisibilityIfExist(view, R.id.fix_button, 4);
                }
                setIconIfExist(view, R.id.icon, getIconResForStatus(i, infoViewPosition));
                if (view.findViewById(R.id.fix_button) != null) {
                    bindFixActionForStatus(i, view.findViewById(R.id.fix_button), infoViewPosition);
                }
            }
        }

        public View bindLoadingView(View view, int i, InfoViewPosition infoViewPosition) {
            if (SearchConstants.isErrorStatus(i)) {
                setIconIfExist(view, R.id.icon, getIconResForStatus(i, infoViewPosition));
                if (view.findViewById(R.id.icon) != null) {
                    view.findViewById(R.id.icon).setVisibility(0);
                }
                if (view.findViewById(R.id.progress) != null) {
                    view.findViewById(R.id.progress).setVisibility(8);
                }
            } else {
                if (view.findViewById(R.id.icon) != null) {
                    view.findViewById(R.id.icon).setVisibility(8);
                }
                if (view.findViewById(R.id.progress) != null) {
                    view.findViewById(R.id.progress).setVisibility(0);
                }
            }
            return view;
        }

        /* access modifiers changed from: protected */
        public String getFixButtonTextForStatus(int i, InfoViewPosition infoViewPosition) {
            return this.mContext.getString(i != 1 ? i != 12 ? i != 3 ? i != 4 ? i != 5 ? R.string.search_retry : R.string.search_select_network : R.string.search_backup_now : R.string.search_login_now : R.string.ai_album_open_switch_button_title : R.string.search_set_network_connection);
        }

        /* access modifiers changed from: protected */
        public int getIconResForStatus(int i, InfoViewPosition infoViewPosition) {
            if (infoViewPosition == InfoViewPosition.FULL_SCREEN) {
                return (i == 3 || i == 4) ? R.drawable.search_error_cloud : R.drawable.search_error_common;
            }
            if (infoViewPosition == InfoViewPosition.FOOTER) {
                return R.drawable.search_connection_error_icon;
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public View getInfoItemView(View view, int i, InfoViewPosition infoViewPosition) {
            return (i == 3 || i == 4) ? this.mInflater.inflate(R.layout.search_set_cloud_back_up_item, (ViewGroup) null) : this.mInflater.inflate(R.layout.search_item_error_layout, (ViewGroup) null);
        }

        /* access modifiers changed from: protected */
        public String getInfoTitleForStatus(int i, InfoViewPosition infoViewPosition) {
            int i2;
            boolean z = infoViewPosition == InfoViewPosition.FULL_SCREEN;
            if (i == 1) {
                i2 = z ? R.string.search_connection_error : R.string.search_connection_error_and_set;
            } else if (i == 10) {
                i2 = R.string.search_syncing;
            } else if (i == 3) {
                i2 = z ? R.string.search_login_title_full_screen : R.string.search_login_title;
            } else if (i == 4) {
                i2 = z ? R.string.search_backup_full_screen : R.string.search_backup_title;
            } else if (i != 5) {
                switch (i) {
                    case 12:
                        i2 = R.string.search_empty_title;
                        break;
                    case 13:
                    case 14:
                        i2 = R.string.ai_album_requesting_title;
                        break;
                    default:
                        if (!z) {
                            i2 = R.string.search_error_and_retry;
                            break;
                        } else {
                            i2 = R.string.search_error;
                            break;
                        }
                }
            } else {
                i2 = R.string.search_net_error;
            }
            return this.mContext.getString(i2);
        }

        public View getInfoView(View view, int i, InfoViewPosition infoViewPosition) {
            if (!SearchConstants.isErrorStatus(i)) {
                return null;
            }
            return infoViewPosition == InfoViewPosition.FULL_SCREEN ? view : getInfoItemView(view, i, infoViewPosition);
        }

        /* access modifiers changed from: protected */
        public View getLoadingItemView(View view) {
            return this.mInflater.inflate(R.layout.search_item_loading_layout, (ViewGroup) null);
        }

        public View getLoadingView(View view, int i, InfoViewPosition infoViewPosition) {
            return infoViewPosition == InfoViewPosition.FULL_SCREEN ? view : getLoadingItemView(view);
        }

        public InfoViewPosition positionForBaseStatus(int i) {
            return !SearchConstants.isErrorStatus(i) ? InfoViewPosition.NONE : InfoViewPosition.HEADER;
        }

        public InfoViewPosition positionForResultStatus(int i) {
            return !SearchConstants.isErrorStatus(i) ? InfoViewPosition.NONE : InfoViewPosition.FOOTER;
        }

        /* access modifiers changed from: protected */
        public void setIconIfExist(View view, int i, int i2) {
            View findViewById;
            if (i2 > 0 && (findViewById = view.findViewById(i)) != null && (findViewById instanceof ImageView)) {
                ((ImageView) findViewById).setImageResource(i2);
            }
        }

        /* access modifiers changed from: protected */
        public void setTextIfExist(View view, int i, String str) {
            View findViewById = view.findViewById(i);
            if (findViewById == null) {
                return;
            }
            if (findViewById instanceof TextView) {
                ((TextView) findViewById).setText(str);
            } else if (findViewById instanceof Button) {
                ((Button) findViewById).setText(str);
            }
        }

        /* access modifiers changed from: protected */
        public void setVisibilityIfExist(View view, int i, int i2) {
            View findViewById = view.findViewById(i);
            if (findViewById != null) {
                findViewById.setVisibility(i2);
            }
        }

        /* access modifiers changed from: protected */
        public boolean shouldShowFixButtonForStatus(int i, InfoViewPosition infoViewPosition) {
            return (i == 13 || i == 14) ? false : true;
        }
    }

    public interface ErrorViewAdapter {
        void addFooterView(View view);

        void addHeaderView(View view);

        void bindInfoView(View view, int i, InfoViewPosition infoViewPosition);

        View bindLoadingView(View view, int i, InfoViewPosition infoViewPosition);

        View getInfoView(View view, int i, InfoViewPosition infoViewPosition);

        View getLoadingView(View view, int i, InfoViewPosition infoViewPosition);

        boolean isEmptyDataView();

        boolean isLoading();

        InfoViewPosition positionForBaseStatus(int i);

        InfoViewPosition positionForResultStatus(int i);

        void removeFooterView(View view);

        void removeHeaderView(View view);

        void requestRetry();
    }

    public enum InfoViewPosition {
        NONE,
        FULL_SCREEN,
        HEADER,
        FOOTER
    }

    private void setOnlyVisibleView(int i) {
        int i2 = 0;
        while (i2 < this.mFullScreenViews.length) {
            setVisibility(i2, i2 == i ? 0 : 8);
            i2++;
        }
        if (this.mTopVisibleView != i) {
            this.mTopVisibleView = i;
        }
    }

    private void setVisibility(int i, int i2) {
        View view = this.mFullScreenViews[i];
        boolean z = view instanceof ViewStub;
        if (i2 != 8) {
            if (z) {
                view = ((ViewStub) view).inflate();
                this.mFullScreenViews[i] = view;
            }
            view.setVisibility(i2);
        } else if (!z) {
            view.setVisibility(i2);
        }
    }

    public int getResultStatus() {
        return this.mResultStatus;
    }

    public void init(View view, View view2, View view3, View view4, ErrorViewAdapter errorViewAdapter) {
        if (errorViewAdapter != null) {
            this.mErrorViewAdapter = errorViewAdapter;
            this.mFullScreenViews = new View[]{view, view3, view4, view2};
            this.mInitiated = true;
            return;
        }
        throw new IllegalArgumentException("The ErrorViewAdapter cannot be null!");
    }

    public void refreshInfoViews() {
        if (this.mInitiated) {
            if (!this.mErrorViewAdapter.isEmptyDataView()) {
                setOnlyVisibleView(0);
                View view = this.mInfoHeaderView;
                View view2 = this.mInfoFooterView;
                if (view != null) {
                    this.mErrorViewAdapter.removeHeaderView(view);
                    this.mInfoHeaderView = null;
                }
                View view3 = this.mInfoFooterView;
                if (view3 != null) {
                    this.mErrorViewAdapter.removeFooterView(view3);
                    this.mInfoFooterView = null;
                }
                int i = -1;
                int i2 = this.mErrorViewAdapter.positionForResultStatus(this.mResultStatus) == InfoViewPosition.HEADER ? this.mResultStatus : this.mErrorViewAdapter.positionForBaseStatus(this.mBaseStatus) == InfoViewPosition.HEADER ? this.mBaseStatus : -1;
                this.mInfoHeaderView = this.mErrorViewAdapter.getInfoView(view, i2, InfoViewPosition.HEADER);
                View view4 = this.mInfoHeaderView;
                if (view4 != null) {
                    this.mErrorViewAdapter.bindInfoView(view4, i2, InfoViewPosition.HEADER);
                    this.mErrorViewAdapter.addHeaderView(this.mInfoHeaderView);
                }
                if (this.mErrorViewAdapter.isLoading()) {
                    this.mInfoFooterView = this.mErrorViewAdapter.getLoadingView(view2, this.mResultStatus, InfoViewPosition.FOOTER);
                    View view5 = this.mInfoFooterView;
                    if (view5 != null) {
                        this.mErrorViewAdapter.addFooterView(view5);
                        return;
                    }
                    return;
                }
                if (this.mErrorViewAdapter.positionForResultStatus(this.mResultStatus) == InfoViewPosition.FOOTER) {
                    i = this.mResultStatus;
                } else if (this.mErrorViewAdapter.positionForBaseStatus(this.mBaseStatus) == InfoViewPosition.FOOTER) {
                    i = this.mBaseStatus;
                }
                this.mInfoFooterView = this.mErrorViewAdapter.getInfoView(view2, i, InfoViewPosition.FOOTER);
                View view6 = this.mInfoFooterView;
                if (view6 != null) {
                    this.mErrorViewAdapter.bindInfoView(view6, i, InfoViewPosition.FOOTER);
                    this.mErrorViewAdapter.addFooterView(this.mInfoFooterView);
                }
            } else if (this.mErrorViewAdapter.isLoading()) {
                View[] viewArr = this.mFullScreenViews;
                viewArr[1] = this.mErrorViewAdapter.getLoadingView(viewArr[1], this.mResultStatus, InfoViewPosition.FULL_SCREEN);
                setOnlyVisibleView(1);
                this.mErrorViewAdapter.bindLoadingView(this.mFullScreenViews[1], this.mResultStatus, InfoViewPosition.FULL_SCREEN);
            } else if (SearchConstants.isErrorStatus(this.mResultStatus)) {
                View[] viewArr2 = this.mFullScreenViews;
                viewArr2[3] = this.mErrorViewAdapter.getInfoView(viewArr2[3], this.mResultStatus, InfoViewPosition.FULL_SCREEN);
                setOnlyVisibleView(3);
                this.mErrorViewAdapter.bindInfoView(this.mFullScreenViews[3], this.mResultStatus, InfoViewPosition.FULL_SCREEN);
            } else if (SearchConstants.isErrorStatus(this.mBaseStatus)) {
                View[] viewArr3 = this.mFullScreenViews;
                viewArr3[3] = this.mErrorViewAdapter.getInfoView(viewArr3[3], this.mBaseStatus, InfoViewPosition.FULL_SCREEN);
                setOnlyVisibleView(3);
                this.mErrorViewAdapter.bindInfoView(this.mFullScreenViews[3], this.mBaseStatus, InfoViewPosition.FULL_SCREEN);
            } else {
                setOnlyVisibleView(2);
            }
        }
    }

    public void updateBaseStatus(int i) {
        int i2;
        if (this.mInitiated && (i2 = this.mBaseStatus) != i) {
            this.mBaseStatus = i;
            if (SearchConstants.isErrorStatus(i2) && !SearchConstants.isErrorStatus(this.mBaseStatus)) {
                this.mErrorViewAdapter.requestRetry();
            }
            refreshInfoViews();
        }
    }

    public void updateResultStatus(int i) {
        this.mResultStatus = i;
        refreshInfoViews();
    }
}
