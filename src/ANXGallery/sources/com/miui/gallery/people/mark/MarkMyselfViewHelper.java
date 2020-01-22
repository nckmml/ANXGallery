package com.miui.gallery.people.mark;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.people.mark.MarkPeopleDialogFragment;
import com.miui.gallery.people.mark.MarkPeopleSuggestionContract;
import com.miui.gallery.people.model.People;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.ToastUtils;
import java.util.ArrayList;

public class MarkMyselfViewHelper implements MarkPeopleSuggestionContract.View {
    private DialogInterface.OnCancelListener mCancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            MarkMyselfViewHelper.this.mMarkMyselfPresenter.ignoreSuggestion();
        }
    };
    private Fragment mFragment;
    private DialogInterface.OnClickListener mLoadMoreClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            MarkMyselfViewHelper.this.mMarkMyselfPresenter.loadMore();
        }
    };
    private MarkPeopleDialogFragment mMarkDialogFragment = null;
    private MarkMyselfSuggestionModel mMarkMyselfModel = null;
    private Bundle mMarkMyselfOptions = null;
    /* access modifiers changed from: private */
    public MarkPeopleSuggestionPresenter mMarkMyselfPresenter = null;
    private MarkPeopleDialogFragment.OnPeopleSelectListener mPeopleSelectListener = new MarkPeopleDialogFragment.OnPeopleSelectListener() {
        public void onPeopleSelected(int i, People people) {
            MarkMyselfViewHelper.this.mMarkMyselfPresenter.markPeople(people);
        }
    };

    public MarkMyselfViewHelper(Fragment fragment) {
        this.mFragment = fragment;
    }

    public void dismissSuggestionDialog() {
        MarkPeopleDialogFragment markPeopleDialogFragment = this.mMarkDialogFragment;
        if (markPeopleDialogFragment != null) {
            markPeopleDialogFragment.dismissAllowingStateLoss();
            this.mMarkDialogFragment = null;
        }
    }

    public void goToActivity(Uri uri) {
        if (this.mFragment.getActivity() != null) {
            ActionURIHandler.handleUri(this.mFragment.getActivity(), uri);
        }
    }

    public boolean isSuggestionDialogVisible() {
        MarkPeopleDialogFragment markPeopleDialogFragment = this.mMarkDialogFragment;
        return markPeopleDialogFragment != null && markPeopleDialogFragment.isAdded();
    }

    public boolean onStart() {
        if (this.mFragment.getActivity() == null) {
            return false;
        }
        this.mMarkDialogFragment = (MarkPeopleDialogFragment) this.mFragment.getFragmentManager().findFragmentByTag("MarkMyself");
        if (this.mMarkMyselfOptions == null) {
            this.mMarkMyselfOptions = MarkPeopleSuggestionContract.buildMarkInfoForMarkMyself(this.mFragment.getActivity());
        }
        if (this.mMarkMyselfModel == null) {
            this.mMarkMyselfModel = new MarkMyselfSuggestionModel();
        }
        if (this.mMarkMyselfModel.needMark(this.mMarkMyselfOptions)) {
            this.mMarkMyselfPresenter = new MarkPeopleSuggestionPresenter(this.mFragment.getActivity(), this, this.mMarkMyselfModel, this.mMarkMyselfOptions);
            this.mMarkMyselfPresenter.start();
            return true;
        }
        if (isSuggestionDialogVisible()) {
            this.mMarkMyselfPresenter = new MarkPeopleSuggestionPresenter(this.mFragment.getActivity(), this, this.mMarkMyselfModel, this.mMarkMyselfOptions);
            this.mMarkMyselfPresenter.start();
        }
        return false;
    }

    public void onStop() {
        MarkPeopleSuggestionPresenter markPeopleSuggestionPresenter = this.mMarkMyselfPresenter;
        if (markPeopleSuggestionPresenter != null) {
            markPeopleSuggestionPresenter.stop();
        }
    }

    public void setLoadMoreMarkResult(boolean z) {
        this.mMarkMyselfPresenter.setLoadMoreMarkResult(z);
    }

    public void showInfoDialog(String str, String str2) {
        if (this.mFragment.getActivity() != null) {
            DialogUtil.showInfoDialog(this.mFragment.getActivity(), str2, str);
        }
    }

    public void showMarkResult(boolean z, String str) {
        if (this.mFragment.getActivity() != null && !TextUtils.isEmpty(str)) {
            ToastUtils.makeText((Context) this.mFragment.getActivity(), (CharSequence) str);
        }
    }

    public void showSuggestionDialog(String str, String str2, ArrayList<People> arrayList) {
        if (this.mFragment.getFragmentManager() != null) {
            if (this.mMarkDialogFragment == null) {
                this.mMarkDialogFragment = new MarkPeopleDialogFragment();
            }
            this.mMarkDialogFragment.setTitle(str);
            this.mMarkDialogFragment.setLoadMoreButton(str2, this.mLoadMoreClickListener);
            this.mMarkDialogFragment.setCancelButton(true, this.mCancelListener);
            this.mMarkDialogFragment.setPeopleList(arrayList, this.mPeopleSelectListener);
            if (!this.mMarkDialogFragment.isAdded()) {
                this.mMarkDialogFragment.showAllowingStateLoss(this.mFragment.getFragmentManager(), "MarkMyself");
            }
        }
    }
}
