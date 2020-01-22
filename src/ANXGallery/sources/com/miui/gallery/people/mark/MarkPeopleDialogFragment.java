package com.miui.gallery.people.mark;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.people.PeopleDisplayHelper;
import com.miui.gallery.people.model.People;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.GalleryDialogFragment;
import com.miui.gallery.widget.recyclerview.GridLayoutInnerPaddingItemDecoration;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.ArrayList;

public class MarkPeopleDialogFragment extends GalleryDialogFragment implements View.OnClickListener {
    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<PeopleItemViewHolder>() {
        public int getItemCount() {
            return MarkPeopleDialogFragment.this.mPeopleList.size();
        }

        public void onBindViewHolder(PeopleItemViewHolder peopleItemViewHolder, int i) {
            peopleItemViewHolder.bindView((People) MarkPeopleDialogFragment.this.mPeopleList.get(i));
        }

        public PeopleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MarkPeopleDialogFragment markPeopleDialogFragment = MarkPeopleDialogFragment.this;
            return new PeopleItemViewHolder(LayoutInflater.from(markPeopleDialogFragment.getActivity()).inflate(R.layout.mark_people_item, (ViewGroup) null, false));
        }
    };
    private DialogInterface.OnCancelListener mCancelButtonListener;
    private View mCancelView;
    private RecyclerView mDataView;
    private Dialog mDialog;
    private DisplayImageOptions mDisplayImageOptions = PeopleDisplayHelper.getDefaultPeopleDisplayOptionsBuilder().build();
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;
    private DialogInterface.OnClickListener mLoadMoreButtonListener;
    private String mLoadMoreButtonText;
    private TextView mLoadMoreView;
    /* access modifiers changed from: private */
    public ArrayList<People> mPeopleList;
    private OnPeopleSelectListener mPeopleSelectListener;
    private boolean mShowCancelButton = false;
    private View mSingleView;
    private ViewStub mSingleViewStub;
    private String mTitle;
    private TextView mTitleView;

    public interface OnPeopleSelectListener {
        void onPeopleSelected(int i, People people);
    }

    private class PeopleItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCover;

        public PeopleItemViewHolder(View view) {
            super(view);
            this.mCover = (ImageView) view.findViewById(R.id.icon);
        }

        public void bindView(People people) {
            MarkPeopleDialogFragment.this.bindPeopleCover(this.mCover, people);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MarkPeopleDialogFragment.this.onClickPeople(PeopleItemViewHolder.this.getAdapterPosition());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void bindPeopleCover(ImageView imageView, People people) {
        PeopleDisplayHelper.bindImage(imageView, people.getCoverPath(), ContentUris.withAppendedId(GalleryContract.Cloud.CLOUD_URI, people.getCoverImageId()), this.mDisplayImageOptions, people.getCoverFaceRect(), people.getCoverType());
    }

    private void configCancelButton() {
        View view = this.mCancelView;
        if (view == null) {
            return;
        }
        if (this.mShowCancelButton) {
            view.setVisibility(0);
            this.mCancelView.setOnClickListener(this);
            return;
        }
        view.setVisibility(8);
        this.mCancelView.setOnClickListener((View.OnClickListener) null);
    }

    private void configDataView() {
        ViewStub viewStub;
        ArrayList<People> arrayList = this.mPeopleList;
        if (arrayList == null) {
            RecyclerView recyclerView = this.mDataView;
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
            }
            View view = this.mSingleView;
            if (view != null) {
                view.setVisibility(8);
            }
        } else if (arrayList.size() == 1) {
            if (this.mSingleView == null && (viewStub = this.mSingleViewStub) != null) {
                this.mSingleView = viewStub.inflate();
            }
            View view2 = this.mSingleView;
            if (view2 != null) {
                bindPeopleCover((ImageView) view2.findViewById(R.id.icon), this.mPeopleList.get(0));
                this.mSingleView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MarkPeopleDialogFragment.this.onClickPeople(0);
                    }
                });
                this.mSingleView.setVisibility(0);
            }
            RecyclerView recyclerView2 = this.mDataView;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(8);
            }
        } else {
            RecyclerView recyclerView3 = this.mDataView;
            if (recyclerView3 != null) {
                recyclerView3.setAdapter(this.mAdapter);
                this.mDataView.setVisibility(0);
            }
            View view3 = this.mSingleView;
            if (view3 != null) {
                view3.setVisibility(8);
            }
        }
    }

    private void configLoadMoreButton() {
        if (this.mLoadMoreView == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.mLoadMoreButtonText)) {
            SpannableString spannableString = new SpannableString(this.mLoadMoreButtonText);
            spannableString.setSpan(new CharacterStyle() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MarkPeopleDialogFragment.this.getResources().getColor(R.color.text_blue));
                    textPaint.setUnderlineText(true);
                }
            }, 0, this.mLoadMoreButtonText.length(), 33);
            this.mLoadMoreView.setText(spannableString);
            this.mLoadMoreView.setVisibility(0);
            this.mLoadMoreView.setOnClickListener(this);
            return;
        }
        this.mLoadMoreView.setVisibility(8);
        this.mLoadMoreView.setOnClickListener((View.OnClickListener) null);
    }

    /* access modifiers changed from: private */
    public void onClickPeople(int i) {
        OnPeopleSelectListener onPeopleSelectListener;
        if (i >= 0 && i < this.mPeopleList.size() && (onPeopleSelectListener = this.mPeopleSelectListener) != null) {
            onPeopleSelectListener.onPeopleSelected(i, this.mPeopleList.get(i));
        }
    }

    public void configTitle() {
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(this.mTitle);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.mCancelButtonListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public void onClick(View view) {
        DialogInterface.OnClickListener onClickListener;
        if (view != null) {
            int id = view.getId();
            if (id == R.id.close_button) {
                DialogInterface.OnCancelListener onCancelListener = this.mCancelButtonListener;
                if (onCancelListener != null) {
                    onCancelListener.onCancel(this.mDialog);
                }
            } else if (id == R.id.load_more_button && (onClickListener = this.mLoadMoreButtonListener) != null) {
                onClickListener.onClick(this.mDialog, -10);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        GridLayoutManager gridLayoutManager;
        super.onConfigurationChanged(configuration);
        if (isAdded() && (gridLayoutManager = this.mGridLayoutManager) != null && this.mDataView != null) {
            gridLayoutManager.setSpanCount(getResources().getInteger(R.integer.mark_people_column_count));
            MiscUtil.invokeSafely(this.mDataView, "markItemDecorInsetsDirty", (Class<?>[]) null, new Object[0]);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (bundle != null) {
            this.mTitle = bundle.getString("MarkPeopleDialogFragment_Title");
            this.mLoadMoreButtonText = bundle.getString("MarkPeopleDialogFragment_LoadMoreButtonText");
            this.mShowCancelButton = bundle.getBoolean("MarkPeopleDialogFragment_ShowCancelButton", false);
            if (bundle.getSerializable("MarkPeopleDialogFragment_PeopleList") != null) {
                this.mPeopleList = (ArrayList) bundle.getSerializable("MarkPeopleDialogFragment_PeopleList");
            }
        }
        Dialog dialog = new Dialog(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.mark_people_dialog_layout, (ViewGroup) null, false);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        this.mTitleView = (TextView) inflate.findViewById(R.id.dialog_title);
        this.mCancelView = inflate.findViewById(R.id.close_button);
        this.mLoadMoreView = (TextView) inflate.findViewById(R.id.load_more_button);
        this.mDataView = (RecyclerView) inflate.findViewById(R.id.data_view);
        this.mGridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.mark_people_column_count));
        this.mDataView.setLayoutManager(this.mGridLayoutManager);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.people_dialog_people_view_horizontal_padding);
        this.mItemDecoration = new GridLayoutInnerPaddingItemDecoration(dimensionPixelSize, 0, dimensionPixelSize, 0, 0, getResources().getDimensionPixelSize(R.dimen.people_dialog_people_item_vertical_padding), false, this.mGridLayoutManager);
        this.mDataView.addItemDecoration(this.mItemDecoration);
        this.mSingleViewStub = (ViewStub) inflate.findViewById(R.id.single_people_view);
        configTitle();
        configCancelButton();
        configLoadMoreButton();
        configDataView();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(80);
            window.setLayout(-1, -2);
        }
        this.mDialog = dialog;
        return this.mDialog;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!TextUtils.isEmpty(this.mTitle)) {
            bundle.putString("MarkPeopleDialogFragment_Title", this.mTitle);
        }
        if (!TextUtils.isEmpty(this.mLoadMoreButtonText)) {
            bundle.putString("MarkPeopleDialogFragment_LoadMoreButtonText", this.mLoadMoreButtonText);
        }
        bundle.putBoolean("MarkPeopleDialogFragment_ShowCancelButton", this.mShowCancelButton);
        ArrayList<People> arrayList = this.mPeopleList;
        if (arrayList != null) {
            bundle.putParcelableArrayList("MarkPeopleDialogFragment_PeopleList", arrayList);
        }
    }

    public void setCancelButton(boolean z, DialogInterface.OnCancelListener onCancelListener) {
        this.mShowCancelButton = z;
        this.mCancelButtonListener = onCancelListener;
        configCancelButton();
    }

    public void setLoadMoreButton(String str, DialogInterface.OnClickListener onClickListener) {
        this.mLoadMoreButtonText = str;
        this.mLoadMoreButtonListener = onClickListener;
        configLoadMoreButton();
    }

    public void setPeopleList(ArrayList<People> arrayList, OnPeopleSelectListener onPeopleSelectListener) {
        this.mPeopleList = arrayList;
        this.mPeopleSelectListener = onPeopleSelectListener;
        configDataView();
        if (isAdded()) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
        configTitle();
    }
}
