package com.miui.epoxy;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import androidx.collection.LongSparseArray;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;

class ViewHolderState extends LongSparseArray<ViewState> implements Parcelable {
    public static final Parcelable.Creator<ViewHolderState> CREATOR = new Parcelable.Creator<ViewHolderState>() {
        public ViewHolderState createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            ViewHolderState viewHolderState = new ViewHolderState(readInt);
            for (int i = 0; i < readInt; i++) {
                viewHolderState.put(parcel.readLong(), (ViewState) parcel.readParcelable(ViewState.class.getClassLoader()));
            }
            return viewHolderState;
        }

        public ViewHolderState[] newArray(int i) {
            return new ViewHolderState[i];
        }
    };

    public static class ViewState extends SparseArray<Parcelable> implements Parcelable {
        public static final Parcelable.Creator<ViewState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<ViewState>() {
            public ViewState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                int readInt = parcel.readInt();
                int[] iArr = new int[readInt];
                parcel.readIntArray(iArr);
                return new ViewState(readInt, iArr, parcel.readParcelableArray(classLoader));
            }

            public ViewState[] newArray(int i) {
                return new ViewState[i];
            }
        });

        public ViewState() {
        }

        private ViewState(int i, int[] iArr, Parcelable[] parcelableArr) {
            super(i);
            for (int i2 = 0; i2 < i; i2++) {
                put(iArr[i2], parcelableArr[i2]);
            }
        }

        private void setIdIfNoneExists(View view) {
            if (view.getId() == -1) {
                view.setId(R.id.view_model_state_saving_id);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void restore(View view) {
            int id = view.getId();
            setIdIfNoneExists(view);
            view.restoreHierarchyState(this);
            view.setId(id);
        }

        public void save(View view) {
            int id = view.getId();
            setIdIfNoneExists(view);
            view.saveHierarchyState(this);
            view.setId(id);
        }

        public void writeToParcel(Parcel parcel, int i) {
            int size = size();
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = keyAt(i2);
                parcelableArr[i2] = (Parcelable) valueAt(i2);
            }
            parcel.writeInt(size);
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }
    }

    public ViewHolderState() {
    }

    public ViewHolderState(int i) {
        super(i);
    }

    public int describeContents() {
        return 0;
    }

    public void restore(EpoxyViewHolder epoxyViewHolder) {
        ViewState viewState;
        if (epoxyViewHolder.shouldSaveViewState() && (viewState = (ViewState) get(epoxyViewHolder.getItemId())) != null) {
            viewState.restore(epoxyViewHolder.itemView);
        }
    }

    public void save(EpoxyViewHolder epoxyViewHolder) {
        if (epoxyViewHolder.shouldSaveViewState()) {
            ViewState viewState = (ViewState) get(epoxyViewHolder.getItemId());
            if (viewState == null) {
                viewState = new ViewState();
            }
            viewState.save(epoxyViewHolder.itemView);
            put(epoxyViewHolder.getItemId(), viewState);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int size = size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeLong(keyAt(i2));
            parcel.writeParcelable((Parcelable) valueAt(i2), 0);
        }
    }
}
