package com.miui.gallery.search.resultpage;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.feedback.FeedbackPolicyNoticeFragment;
import com.miui.gallery.search.resultpage.SearchResultFragment;
import com.miui.gallery.ui.SearchFeedbackTaskController;

public class TagListFragment extends SearchResultFragment {
    private FeedbackPolicyNoticeFragment mPolicyNoticeFragment = null;
    private SearchFeedbackTaskController mSearchFeedbackTaskController;
    private View mSearchFeedbackTaskIndicator;
    /* access modifiers changed from: private */
    public boolean mUserAgreedPolicy = false;

    private class TagListErrorViewAdapter extends SearchResultFragment.ErrorViewAdapter {
        public TagListErrorViewAdapter(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public int getIconResForStatus(int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            if (infoViewPosition == StatusHandleHelper.InfoViewPosition.FULL_SCREEN) {
                return R.drawable.empty_page_things;
            }
            if (infoViewPosition == StatusHandleHelper.InfoViewPosition.FOOTER) {
                return R.drawable.search_connection_error_icon;
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public String getInfoTitleForStatus(int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            boolean z = infoViewPosition == StatusHandleHelper.InfoViewPosition.FULL_SCREEN;
            int i2 = R.string.things_album_empty_title;
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
            this.mErrorViewAdapter = new TagListErrorViewAdapter(this.mActivity);
        }
        return this.mErrorViewAdapter;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.search_tag_list_fragment;
    }

    /* access modifiers changed from: protected */
    public QueryInfo.Builder getSectionDataQueryInfoBuilder() {
        QueryInfo.Builder sectionDataQueryInfoBuilder = super.getSectionDataQueryInfoBuilder();
        if (this.mInFeedbackTaskMode) {
            sectionDataQueryInfoBuilder.addParam("filterMode", "feedback");
        }
        return sectionDataQueryInfoBuilder;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mUserAgreedPolicy = bundle.getBoolean("UserAgreedPolicy", false);
        }
    }

    /* access modifiers changed from: protected */
    public void onInflateView(View view, Bundle bundle, Uri uri) {
        super.onInflateView(view, bundle, uri);
        if (this.mInFeedbackTaskMode) {
            if (this.mSearchFeedbackTaskIndicator == null) {
                this.mSearchFeedbackTaskIndicator = ((ViewStub) view.findViewById(R.id.search_feedback_task_indicator)).inflate();
            }
            if (this.mSearchFeedbackTaskController == null) {
                this.mSearchFeedbackTaskController = new SearchFeedbackTaskController(this, this.mSearchFeedbackTaskIndicator);
            }
            if (isResumed()) {
                this.mSearchFeedbackTaskController.onResume();
            }
        }
    }

    public void onPause() {
        super.onPause();
        SearchFeedbackTaskController searchFeedbackTaskController = this.mSearchFeedbackTaskController;
        if (searchFeedbackTaskController != null) {
            searchFeedbackTaskController.onPause();
        }
    }

    public void onResume() {
        super.onResume();
        SearchFeedbackTaskController searchFeedbackTaskController = this.mSearchFeedbackTaskController;
        if (searchFeedbackTaskController != null) {
            searchFeedbackTaskController.onResume();
        }
        if (this.mInFeedbackTaskMode && GalleryPreferences.Search.shouldShowFeedbackPolicy() && !this.mUserAgreedPolicy) {
            FeedbackPolicyNoticeFragment feedbackPolicyNoticeFragment = this.mPolicyNoticeFragment;
            if (feedbackPolicyNoticeFragment == null || !feedbackPolicyNoticeFragment.isAdded()) {
                if (this.mPolicyNoticeFragment == null) {
                    this.mPolicyNoticeFragment = (FeedbackPolicyNoticeFragment) getFragmentManager().findFragmentByTag("PolicyNoticeFragment");
                    if (this.mPolicyNoticeFragment == null) {
                        this.mPolicyNoticeFragment = new FeedbackPolicyNoticeFragment();
                        this.mPolicyNoticeFragment.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean unused = TagListFragment.this.mUserAgreedPolicy = true;
                            }
                        });
                    }
                }
                if (!this.mPolicyNoticeFragment.isAdded()) {
                    this.mPolicyNoticeFragment.showAllowingStateLoss(getFragmentManager(), "PolicyNoticeFragment");
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("UserAgreedPolicy", this.mUserAgreedPolicy);
    }
}
