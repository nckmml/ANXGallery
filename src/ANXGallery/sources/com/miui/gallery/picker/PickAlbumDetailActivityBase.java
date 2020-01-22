package com.miui.gallery.picker;

import android.content.Intent;
import android.database.DataSetObserver;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.picker.PickAlbumDetailFragmentBase;
import com.miui.gallery.picker.PickerActivity;
import com.miui.gallery.picker.helper.Picker;
import java.util.Set;
import miui.app.ActionBar;
import miui.gallery.support.MiuiSdkCompat;

public class PickAlbumDetailActivityBase extends PickerActivity {
    protected PickAlbumDetailFragmentBase mAlbumDetailFragment;
    protected PickAlbumDetailFragmentBase.ItemStateListener mItemStateListener;
    private int mResultCode;

    public static class SelectAllDecor extends PickerActivity.Decor {
        /* access modifiers changed from: private */
        public PickAlbumDetailActivityBase mActivity;
        /* access modifiers changed from: private */
        public boolean mAllSelected;
        private Button mCancelButton;
        private Button mDoneButton;
        private TextView mPickerTitle;
        private Button mSelectAllButton;
        private TextView mTitle;

        protected SelectAllDecor(PickAlbumDetailActivityBase pickAlbumDetailActivityBase) {
            super(pickAlbumDetailActivityBase);
            this.mActivity = pickAlbumDetailActivityBase;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.miui.gallery.picker.PickAlbumDetailActivityBase] */
        /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.miui.gallery.picker.PickAlbumDetailActivityBase] */
        private void setup() {
            MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mDoneButton, 2);
            MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mCancelButton, 3);
            Picker picker = this.mActivity.getPicker();
            this.mTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SelectAllDecor.this.mActivity.setResultCode(2);
                    SelectAllDecor.this.mActivity.getPicker().done();
                }
            });
            this.mCancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SelectAllDecor.this.mActivity.getPicker().cancel();
                }
            });
            this.mDoneButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SelectAllDecor.this.mActivity.setResultCode(-1);
                    SelectAllDecor.this.mActivity.getPicker().done();
                }
            });
            this.mSelectAllButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SelectAllDecor.this.mAllSelected) {
                        SelectAllDecor.this.mActivity.mAlbumDetailFragment.deselectAll();
                    } else {
                        SelectAllDecor.this.mActivity.mAlbumDetailFragment.selectAll();
                    }
                }
            });
            updateSelectAll();
            picker.registerObserver(new DataSetObserver() {
                public void onChanged() {
                    super.onChanged();
                    SelectAllDecor.this.mActivity.updateTitle();
                    SelectAllDecor.this.updateDoneButtonVisibility();
                }

                public void onInvalidated() {
                    super.onInvalidated();
                    SelectAllDecor.this.mActivity.updateTitle();
                    SelectAllDecor.this.updateDoneButtonVisibility();
                }
            });
            this.mActivity.mItemStateListener = new PickAlbumDetailFragmentBase.ItemStateListener() {
                public void onStateChanged() {
                    SelectAllDecor.this.updateSelectAll();
                }
            };
        }

        /* access modifiers changed from: private */
        public void updateDoneButtonVisibility() {
            this.mDoneButton.setEnabled(this.mActivity.mPicker.count() >= this.mActivity.mPicker.baseline());
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.miui.gallery.picker.PickAlbumDetailActivityBase] */
        /* access modifiers changed from: private */
        public void updateSelectAll() {
            this.mAllSelected = this.mActivity.mAlbumDetailFragment != null && (this.mActivity.mAlbumDetailFragment.isAllSelected() || (this.mActivity.getPicker().isFull() && !this.mActivity.mAlbumDetailFragment.isNoneSelected()));
            MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mSelectAllButton, this.mAllSelected ? 1 : 0);
        }

        public void applyActionBar() {
            ActionBar actionBar = this.mActivity.getActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTabsMode(false);
            actionBar.setCustomView(R.layout.picker_album_custom_title);
            View customView = actionBar.getCustomView();
            this.mTitle = (TextView) customView.findViewById(16908310);
            this.mPickerTitle = (TextView) customView.findViewById(R.id.picker_title);
            this.mCancelButton = (Button) customView.findViewById(16908313);
            this.mDoneButton = (Button) customView.findViewById(16908314);
            updateDoneButtonVisibility();
            this.mSelectAllButton = (Button) customView.findViewById(16908315);
            setup();
        }

        public void applyTheme() {
            this.mActivity.setTheme(2131820854);
        }

        public void setPickerTitle(CharSequence charSequence) {
            this.mPickerTitle.setText(charSequence);
        }

        public void setTitle(CharSequence charSequence) {
            this.mTitle.setText(charSequence);
        }
    }

    public void onBackPressed() {
        setResultCode(2);
        getPicker().done();
    }

    /* access modifiers changed from: protected */
    public PickerActivity.Decor onCreateDecor() {
        return getPicker().getMode() == Picker.Mode.SINGLE ? super.onCreateDecor() : new SelectAllDecor(this);
    }

    /* access modifiers changed from: protected */
    public Picker onCreatePicker() {
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("pick-upper-bound", 0);
        int intExtra2 = intent.getIntExtra("pick-lower-bound", 1);
        Picker.MediaType mediaType = Picker.MediaType.values()[intent.getIntExtra("picker_media_type", 0)];
        Picker.ResultType resultType = Picker.ResultType.values()[intent.getIntExtra("picker_result_type", 0)];
        PickerActivity.SimplePicker simplePicker = new PickerActivity.SimplePicker(this, intExtra, intExtra2, (Set) intent.getSerializableExtra("picker_result_set"));
        simplePicker.setMediaType(mediaType);
        simplePicker.setResultType(resultType);
        if (intent.hasExtra("extra_filter_media_type")) {
            simplePicker.setFilterMimeTypes(intent.getStringArrayExtra("extra_filter_media_type"));
        }
        return simplePicker;
    }

    /* access modifiers changed from: protected */
    public void onDone() {
        Intent intent = new Intent();
        intent.putExtra("internal_key_updated_selection", copyPicker(getPicker()));
        setResult(this.mResultCode, intent);
        finish();
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }
}
