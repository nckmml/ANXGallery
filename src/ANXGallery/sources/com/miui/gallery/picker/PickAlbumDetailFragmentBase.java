package com.miui.gallery.picker;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import com.google.common.collect.Lists;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.picker.helper.AdapterHolder;
import com.miui.gallery.picker.helper.CursorUtils;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.picker.helper.PickerItemCheckedListener;
import com.miui.gallery.ui.MicroThumbGridItem;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.ToastUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class PickAlbumDetailFragmentBase extends PickerFragment {
    protected StickyGridHeadersGridView mAlbumDetailGridView;
    private String mPageName;
    protected SelectionHolder mSelections;
    private boolean mShouldContainUnique = true;

    protected class AlbumItemCheckListener extends PickerItemCheckedListener {
        public AlbumItemCheckListener(Picker picker) {
            super(picker);
        }

        public void onItemChecked(Cursor cursor, View view) {
            String sha1 = CursorUtils.getSha1(cursor);
            if (PickAlbumDetailFragmentBase.this.mPicker.contains(sha1)) {
                PickAlbumDetailFragmentBase pickAlbumDetailFragmentBase = PickAlbumDetailFragmentBase.this;
                pickAlbumDetailFragmentBase.removeInternal(pickAlbumDetailFragmentBase.mPicker, sha1);
            } else if (!PickAlbumDetailFragmentBase.this.mPicker.isFull()) {
                PickAlbumDetailFragmentBase pickAlbumDetailFragmentBase2 = PickAlbumDetailFragmentBase.this;
                pickAlbumDetailFragmentBase2.pickInternal(pickAlbumDetailFragmentBase2.mPicker, sha1);
            } else {
                ToastUtils.makeText(view.getContext(), (CharSequence) view.getContext().getString(R.string.picker_full_format, new Object[]{Integer.valueOf(PickAlbumDetailFragmentBase.this.mPicker.capacity())}));
            }
            if (view instanceof Checkable) {
                ((Checkable) view).setChecked(PickAlbumDetailFragmentBase.this.mPicker.contains(sha1));
            }
        }
    }

    public interface ItemStateListener {
        void onStateChanged();
    }

    static class SelectionHolder implements Iterable<String> {
        private AdapterHolder mAdapter;
        private ItemStateListener mItemStateListener;
        private LinkedHashSet<String> mSelection = new LinkedHashSet<>();

        public SelectionHolder(AdapterHolder adapterHolder) {
            this.mAdapter = adapterHolder;
        }

        private void updateState() {
            ItemStateListener itemStateListener = this.mItemStateListener;
            if (itemStateListener != null) {
                itemStateListener.onStateChanged();
            }
        }

        public boolean add(String str) {
            boolean add = this.mSelection.add(str);
            updateState();
            return add;
        }

        public boolean addAll(List<String> list) {
            boolean addAll = this.mSelection.addAll(list);
            updateState();
            return addAll;
        }

        public void copyFrom(Picker picker) {
            this.mSelection.clear();
            BaseAdapter baseAdapter = this.mAdapter.get();
            for (int i = 0; i < baseAdapter.getCount(); i++) {
                String sha1 = CursorUtils.getSha1((Cursor) baseAdapter.getItem(i));
                if (picker.contains(sha1)) {
                    this.mSelection.add(sha1);
                }
            }
            updateState();
        }

        /* access modifiers changed from: package-private */
        public boolean isAllSelected() {
            BaseAdapter baseAdapter = this.mAdapter.get();
            int size = this.mSelection.size();
            return size > 0 && baseAdapter != null && size == baseAdapter.getCount();
        }

        /* access modifiers changed from: package-private */
        public boolean isNoneSelected() {
            return this.mSelection.size() == 0;
        }

        public Iterator<String> iterator() {
            return this.mSelection.iterator();
        }

        public boolean remove(String str) {
            boolean remove = this.mSelection.remove(str);
            updateState();
            return remove;
        }

        public boolean removeAll(List<String> list) {
            boolean removeAll = this.mSelection.removeAll(list);
            updateState();
            return removeAll;
        }

        public void setItemStateListener(ItemStateListener itemStateListener) {
            this.mItemStateListener = itemStateListener;
        }
    }

    public PickAlbumDetailFragmentBase(String str) {
        this.mPageName = str;
    }

    /* access modifiers changed from: private */
    public void pickInternal(Picker picker, String str) {
        if (picker.pick(str) || !this.mShouldContainUnique) {
            this.mSelections.add(str);
        }
        GallerySamplingStatHelper.recordStringPropertyEvent("picker", "pick_event_page", this.mPageName);
    }

    private void pickInternal(Picker picker, List<String> list) {
        if (picker.pickAll(list) || !this.mShouldContainUnique) {
            this.mSelections.addAll(list);
        }
        GallerySamplingStatHelper.recordStringPropertyEvent("picker", "pick_event_page", this.mPageName);
    }

    private void refreshPickState() {
        int firstVisiblePosition = this.mAlbumDetailGridView.getFirstVisiblePosition();
        for (int i = 0; i < this.mAlbumDetailGridView.getChildCount(); i++) {
            View childAt = this.mAlbumDetailGridView.getChildAt(i);
            if (childAt instanceof com.miui.gallery.ui.Checkable) {
                ((com.miui.gallery.ui.Checkable) childAt).setChecked(this.mPicker.contains(CursorUtils.getSha1((Cursor) this.mAlbumDetailGridView.getItemAtPosition(firstVisiblePosition + i))));
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeInternal(Picker picker, String str) {
        if (picker.remove(str) || !this.mShouldContainUnique) {
            this.mSelections.remove(str);
        }
    }

    private void removeInternal(Picker picker, List<String> list) {
        if (picker.removeAll(list) || !this.mShouldContainUnique) {
            this.mSelections.removeAll(list);
        }
    }

    /* access modifiers changed from: protected */
    public void bindCheckState(View view, Cursor cursor) {
        if (this.mPicker.getMode() != Picker.Mode.SINGLE) {
            com.miui.gallery.ui.Checkable checkable = (com.miui.gallery.ui.Checkable) view;
            checkable.setCheckable(true);
            checkable.setChecked(this.mPicker.contains(CursorUtils.getSha1(cursor)));
        } else if (view instanceof MicroThumbGridItem) {
            ((MicroThumbGridItem) view).setSimilarMarkEnable(true);
        }
    }

    /* access modifiers changed from: protected */
    public void copy2Pick() {
        this.mSelections.copyFrom(getPicker());
    }

    public void deselectAll() {
        ListAdapter realAdapter = this.mAlbumDetailGridView.getRealAdapter();
        if (realAdapter != null) {
            LinkedList newLinkedList = Lists.newLinkedList();
            for (int i = 0; i < realAdapter.getCount(); i++) {
                newLinkedList.add(CursorUtils.getSha1((Cursor) realAdapter.getItem(i)));
                if (newLinkedList.size() >= 1000 || i == realAdapter.getCount() - 1) {
                    removeInternal(this.mPicker, (List<String>) newLinkedList);
                    newLinkedList.clear();
                }
                if (this.mPicker.count() <= 0) {
                    break;
                }
            }
            refreshPickState();
        }
    }

    /* access modifiers changed from: protected */
    public void initialSelections() {
        this.mSelections = new SelectionHolder(new AdapterHolder() {
            public BaseAdapter get() {
                return (BaseAdapter) PickAlbumDetailFragmentBase.this.mAlbumDetailGridView.getRealAdapter();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isAllSelected() {
        SelectionHolder selectionHolder = this.mSelections;
        return selectionHolder != null && selectionHolder.isAllSelected();
    }

    /* access modifiers changed from: protected */
    public boolean isNoneSelected() {
        SelectionHolder selectionHolder = this.mSelections;
        return selectionHolder == null || selectionHolder.isNoneSelected();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int firstVisiblePosition = this.mAlbumDetailGridView.getFirstVisiblePosition();
        if (configuration.orientation == 2) {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsLand);
        } else {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsPortrait);
        }
        this.mAlbumDetailGridView.setSelection(firstVisiblePosition);
    }

    /* access modifiers changed from: protected */
    public boolean onPhotoItemClick(Cursor cursor) {
        if (this.mPicker.getMode() != Picker.Mode.SINGLE) {
            return false;
        }
        this.mPicker.pick(CursorUtils.getSha1(cursor));
        ((PickAlbumDetailActivityBase) this.mActivity).setResultCode(-1);
        this.mPicker.done();
        return true;
    }

    public void selectAll() {
        ListAdapter realAdapter = this.mAlbumDetailGridView.getRealAdapter();
        if (realAdapter != null) {
            int count = realAdapter.getCount();
            LinkedList newLinkedList = Lists.newLinkedList();
            int i = count;
            int i2 = 0;
            while (!this.mPicker.isFull() && i2 < count) {
                i = Math.min(Math.max(0, this.mPicker.capacity() - this.mPicker.count()) + i2, count);
                while (i2 < i) {
                    newLinkedList.add(CursorUtils.getSha1((Cursor) realAdapter.getItem(i2)));
                    i2++;
                }
                pickInternal(this.mPicker, (List<String>) newLinkedList);
                newLinkedList.clear();
            }
            if (this.mPicker.isFull() && i < count) {
                ToastUtils.makeText((Context) getActivity(), (CharSequence) getActivity().getString(R.string.picker_full_format, new Object[]{Integer.valueOf(this.mPicker.capacity())}));
            }
            refreshPickState();
        }
    }

    public void setItemStateListener(ItemStateListener itemStateListener) {
        this.mSelections.setItemStateListener(itemStateListener);
    }

    public void setSelectionCloudNotContainUnique() {
        this.mShouldContainUnique = false;
    }
}
