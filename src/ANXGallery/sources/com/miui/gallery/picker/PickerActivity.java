package com.miui.gallery.picker;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.listener.SingleClickListener;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import miui.app.ActionBar;
import miui.gallery.support.MiuiSdkCompat;

public abstract class PickerActivity extends PickerCompatActivity {
    protected static final String[] PICKABLE_PROJECTION = {"_id", "sha1", "microthumbfile", "thumbnailFile", "localFile", "serverType", "size", "exifImageLength", "exifImageWidth"};
    private Decor mDecor;
    /* access modifiers changed from: protected */
    public Picker mPicker;
    private CharSequence mPickerTitle;
    private CharSequence mTitle;

    public static abstract class Decor {
        protected PickerActivity mActivity;

        private static class Multiple extends Decor {
            /* access modifiers changed from: private */
            public Button mDoneButton;
            private TextView mTitle;

            protected Multiple(PickerActivity pickerActivity) {
                super(pickerActivity);
            }

            /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.miui.gallery.picker.PickerActivity] */
            /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.miui.gallery.picker.PickerActivity] */
            public void applyActionBar() {
                Log.d("PickerActivity", "applyActionBar");
                ActionBar actionBar = this.mActivity.getActionBar();
                boolean z = true;
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setTabsMode(false);
                actionBar.setCustomView(R.layout.picker_custom_title);
                View customView = actionBar.getCustomView();
                this.mTitle = (TextView) customView.findViewById(16908310);
                Button button = (Button) customView.findViewById(16908313);
                MiuiSdkCompat.setEditActionModeButton(this.mActivity, button, 3);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Multiple.this.mActivity.mPicker.cancel();
                    }
                });
                this.mDoneButton = (Button) customView.findViewById(16908314);
                MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mDoneButton, 2);
                this.mDoneButton.setOnClickListener(new SingleClickListener() {
                    /* access modifiers changed from: protected */
                    public void onSingleClick(View view) {
                        Multiple.this.mActivity.mPicker.done();
                    }
                });
                Button button2 = this.mDoneButton;
                if (this.mActivity.mPicker.count() < this.mActivity.mPicker.baseline()) {
                    z = false;
                }
                button2.setEnabled(z);
                this.mActivity.mPicker.registerObserver(new DataSetObserver() {
                    public void onChanged() {
                        super.onChanged();
                        Multiple.this.mActivity.updateTitle();
                        Multiple.this.mDoneButton.setEnabled(Multiple.this.mActivity.mPicker.count() >= Multiple.this.mActivity.mPicker.baseline());
                    }

                    public void onInvalidated() {
                        super.onInvalidated();
                        Multiple.this.mActivity.updateTitle();
                        Multiple.this.mDoneButton.setEnabled(Multiple.this.mActivity.mPicker.count() >= Multiple.this.mActivity.mPicker.baseline());
                    }
                });
            }

            public void applyTheme() {
            }

            public void setPickerTitle(CharSequence charSequence) {
                this.mTitle.setText(charSequence);
            }

            public void setTitle(CharSequence charSequence) {
            }
        }

        protected Decor(PickerActivity pickerActivity) {
            this.mActivity = pickerActivity;
        }

        public static Decor create(PickerActivity pickerActivity) {
            return new Multiple(pickerActivity);
        }

        public abstract void applyActionBar();

        public abstract void applyTheme();

        public abstract void setPickerTitle(CharSequence charSequence);

        public abstract void setTitle(CharSequence charSequence);
    }

    protected static class SimplePicker implements Picker {
        private final int mBaseline;
        private final int mCapacity;
        private String[] mFilterMimeTypes;
        private Picker.ImageType mImageType = Picker.ImageType.THUMBNAIL;
        private Picker.MediaType mMediaType;
        private DataSetObservable mObservable;
        private Picker.Mode mPickMode;
        private PickerActivity mPickerActivity;
        private Picker.ResultType mResultType;
        private Set<String> mResults;

        public SimplePicker(PickerActivity pickerActivity, int i, int i2, Set<String> set) {
            if (set != null) {
                if (i < 0) {
                    this.mPickMode = Picker.Mode.UNLIMITED;
                    i = Reader.READ_DONE;
                } else if (i > 1) {
                    this.mPickMode = Picker.Mode.MULTIPLE;
                } else {
                    this.mPickMode = Picker.Mode.SINGLE;
                    i = 1;
                }
                if (set.size() <= i) {
                    this.mPickerActivity = pickerActivity;
                    this.mResults = set;
                    this.mCapacity = i;
                    this.mBaseline = i2;
                    this.mObservable = new DataSetObservable();
                    return;
                }
                throw new IllegalArgumentException(String.format("ResultMap size (%d) is too large this picker (%d)", new Object[]{Integer.valueOf(set.size()), Integer.valueOf(i)}));
            }
            throw new IllegalArgumentException("Result can't be null");
        }

        public int baseline() {
            return this.mBaseline;
        }

        public void cancel() {
            this.mPickerActivity.onCancel();
        }

        public int capacity() {
            return this.mCapacity;
        }

        public boolean clear() {
            if (this.mResults.isEmpty()) {
                return false;
            }
            this.mResults.clear();
            this.mObservable.notifyChanged();
            return true;
        }

        public boolean contains(String str) {
            return this.mResults.contains(str);
        }

        public int count() {
            return this.mResults.size();
        }

        public void done() {
            this.mPickerActivity.onDone();
        }

        public String[] getFilterMimeTypes() {
            return this.mFilterMimeTypes;
        }

        public Picker.ImageType getImageType() {
            return this.mImageType;
        }

        public Picker.MediaType getMediaType() {
            return this.mMediaType;
        }

        public Picker.Mode getMode() {
            return this.mPickMode;
        }

        public Picker.ResultType getResultType() {
            return this.mResultType;
        }

        public boolean isFull() {
            return count() >= capacity();
        }

        public Iterator<String> iterator() {
            return this.mResults.iterator();
        }

        public boolean pick(String str) {
            if (this.mPickMode == Picker.Mode.SINGLE) {
                this.mResults.clear();
            } else if (isFull()) {
                return false;
            }
            boolean add = this.mResults.add(str);
            if (add) {
                this.mObservable.notifyChanged();
            }
            return add;
        }

        public boolean pickAll(List<String> list) {
            if (isFull()) {
                return false;
            }
            int size = list.size();
            int i = 0;
            boolean z = false;
            while (!isFull() && i < size) {
                int min = Math.min(Math.max(0, capacity() - count()) + i, size);
                z |= this.mResults.addAll(list.subList(i, min));
                i = min;
            }
            if (z) {
                this.mObservable.notifyChanged();
            }
            return z;
        }

        public void registerObserver(DataSetObserver dataSetObserver) {
            this.mObservable.registerObserver(dataSetObserver);
        }

        public boolean remove(String str) {
            boolean remove = this.mResults.remove(str);
            if (remove) {
                this.mObservable.notifyChanged();
            }
            return remove;
        }

        public boolean removeAll(List<String> list) {
            boolean removeAll = this.mResults.removeAll(list);
            if (removeAll) {
                this.mObservable.notifyChanged();
            }
            return removeAll;
        }

        public void setFilterMimeTypes(String[] strArr) {
            this.mFilterMimeTypes = strArr;
        }

        public void setImageType(Picker.ImageType imageType) {
            this.mImageType = imageType;
        }

        public void setMediaType(Picker.MediaType mediaType) {
            this.mMediaType = mediaType;
        }

        public void setResultType(Picker.ResultType resultType) {
            this.mResultType = resultType;
        }

        public String toString() {
            return "SimplePicker{mResults=" + this.mResults + '}';
        }
    }

    public static HashSet<String> copyPicker(Picker picker) {
        if (picker == null) {
            return new LinkedHashSet(0);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(picker.count());
        Iterator it = picker.iterator();
        while (it.hasNext()) {
            linkedHashSet.add((String) it.next());
        }
        return linkedHashSet;
    }

    private void restoreInstanceState(Bundle bundle) {
        Log.d("PickerActivity", "restore instance state for picker: ");
        int i = bundle.getInt("com.miui.gallery.picker.PickerActivity.capacity", 1);
        int i2 = bundle.getInt("com.miui.gallery.picker.PickerActivity.baseline", 1);
        Set set = (Set) bundle.getSerializable("com.miui.gallery.picker.PickerActivity.results");
        if (set == null) {
            set = new LinkedHashSet(i);
        }
        this.mPicker = new SimplePicker(this, i, i2, set);
        int i3 = bundle.getInt("com.miui.gallery.picker.PickerActivity.media_type");
        int i4 = bundle.getInt("com.miui.gallery.picker.PickerActivity.result_type");
        this.mPicker.setMediaType(Picker.MediaType.values()[i3]);
        this.mPicker.setResultType(Picker.ResultType.values()[i4]);
        Log.d("PickerActivity", "picker[capacity:%d, size:%d] restored.", Integer.valueOf(i), Integer.valueOf(set.size()));
    }

    public Picker getPicker() {
        return this.mPicker;
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PickerFragment) {
            ((PickerFragment) fragment).attach(this.mPicker);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        super.finish();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initActionBar();
        this.mDecor.applyActionBar();
        this.mDecor.setTitle(this.mTitle);
        this.mDecor.setPickerTitle(this.mPickerTitle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            restoreInstanceState(bundle);
        } else {
            try {
                this.mPicker = onCreatePicker();
            } catch (RuntimeException e) {
                Log.e("PickerActivity", (Throwable) e);
                super.onCreate(bundle);
                finish();
                return;
            }
        }
        this.mDecor = onCreateDecor();
        this.mDecor.applyTheme();
        super.onCreate(bundle);
        this.mDecor.applyActionBar();
        updateTitle();
    }

    /* access modifiers changed from: protected */
    public Decor onCreateDecor() {
        return Decor.create(this);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.content.Context, com.miui.gallery.picker.PickerActivity] */
    /* access modifiers changed from: protected */
    public Picker onCreatePicker() {
        int i;
        Intent intent = getIntent();
        String resolveType = intent.resolveType(this);
        if (intent.hasExtra("pick-upper-bound")) {
            i = intent.getIntExtra("pick-upper-bound", -1);
            Log.d("PickerActivity", "initial pick bound: %d", (Object) Integer.valueOf(i));
        } else if (intent.getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false)) {
            Log.d("PickerActivity", "standard pick multiple");
            i = -1;
        } else {
            i = 1;
        }
        int intExtra = intent.getIntExtra("pick-lower-bound", 1);
        if (intExtra < 1) {
            intExtra = 1;
        }
        if (i == -1 || intExtra > i) {
            intExtra = 1;
        }
        SimplePicker simplePicker = new SimplePicker(this, i, intExtra, new LinkedHashSet());
        if ("image/*".equals(resolveType)) {
            simplePicker.setMediaType(Picker.MediaType.IMAGE);
        } else if ("video/*".equals(resolveType)) {
            simplePicker.setMediaType(Picker.MediaType.VIDEO);
        } else if ("vnd.android.cursor.dir/image".equals(resolveType)) {
            simplePicker.setMediaType(Picker.MediaType.IMAGE);
        } else if ("vnd.android.cursor.dir/video".equals(resolveType)) {
            simplePicker.setMediaType(Picker.MediaType.VIDEO);
        } else {
            simplePicker.setMediaType(Picker.MediaType.ALL);
        }
        if (intent.getBooleanExtra("pick-need-id", false)) {
            simplePicker.setResultType(Picker.ResultType.ID);
        } else if (GalleryOpenProvider.needReturnContentUri((Context) this, getCallingPackage())) {
            simplePicker.setResultType(Picker.ResultType.OPEN_URI);
        } else if ("vnd.android.cursor.dir/image".equals(resolveType) || "vnd.android.cursor.dir/video".equals(resolveType)) {
            simplePicker.setResultType(Picker.ResultType.LEGACY_MEDIA);
        } else {
            simplePicker.setResultType(Picker.ResultType.LEGACY_GENERAL);
        }
        if (intent.getBooleanExtra("pick-need-origin", false)) {
            simplePicker.setImageType(Picker.ImageType.ORIGIN);
        } else if (intent.getBooleanExtra("pick-need-origin-download-info", false)) {
            simplePicker.setImageType(Picker.ImageType.ORIGIN_OR_DOWNLOAD_INFO);
        }
        if (intent.hasExtra("extra_filter_media_type")) {
            simplePicker.setFilterMimeTypes(intent.getStringArrayExtra("extra_filter_media_type"));
        }
        Log.d("PickerActivity", "creating picker, capacity is %d", (Object) Integer.valueOf(i));
        return simplePicker;
    }

    /* access modifiers changed from: protected */
    public abstract void onDone();

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("com.miui.gallery.picker.PickerActivity.results", copyPicker(this.mPicker));
        bundle.putSerializable("com.miui.gallery.picker.PickerActivity.capacity", Integer.valueOf(this.mPicker.getMode() == Picker.Mode.UNLIMITED ? -1 : this.mPicker.capacity()));
        bundle.putSerializable("com.miui.gallery.picker.PickerActivity.baseline", Integer.valueOf(this.mPicker.baseline()));
        bundle.putInt("com.miui.gallery.picker.PickerActivity.media_type", this.mPicker.getMediaType().ordinal());
        bundle.putInt("com.miui.gallery.picker.PickerActivity.result_type", this.mPicker.getResultType().ordinal());
    }

    public final void setPickerTitle(CharSequence charSequence) {
        this.mPickerTitle = charSequence;
        this.mDecor.setPickerTitle(charSequence);
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mDecor.setTitle(this.mTitle);
    }

    /* access modifiers changed from: protected */
    public final void updateTitle() {
        String str;
        int count = this.mPicker.count();
        Picker.Mode mode = this.mPicker.getMode();
        if (count > 0) {
            if (mode == Picker.Mode.MULTIPLE) {
                str = getString(R.string.picker_title_selection_format_multiple, new Object[]{Integer.valueOf(this.mPicker.baseline()), Integer.valueOf(this.mPicker.capacity()), Integer.valueOf(count)});
            } else {
                str = getString(R.string.picker_title_selection_format, new Object[]{Integer.valueOf(count)});
            }
            setPickerTitle(str);
        } else if (mode == Picker.Mode.MULTIPLE) {
            if (this.mPicker.baseline() != this.mPicker.capacity()) {
                setPickerTitle(getString(R.string.picker_title_format, new Object[]{Integer.valueOf(this.mPicker.baseline()), Integer.valueOf(this.mPicker.capacity())}));
                return;
            }
            setPickerTitle(getString(R.string.picker_title_specify_format, new Object[]{Integer.valueOf(this.mPicker.baseline())}));
        } else if (mode == Picker.Mode.SINGLE) {
            setPickerTitle(getString(R.string.picker_title_specify_format_one, new Object[]{Integer.valueOf(this.mPicker.capacity())}));
        } else {
            setPickerTitle(getString(R.string.picker_title_unlimit));
        }
    }
}
