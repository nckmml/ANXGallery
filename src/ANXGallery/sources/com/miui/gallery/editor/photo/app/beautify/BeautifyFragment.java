package com.miui.gallery.editor.photo.app.beautify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.BeautifyView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.BeautifyData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BeautifyFragment extends MenuFragment<AbstractEffectFragment, SdkProvider<BeautifyData, AbstractEffectFragment>> {
    /* access modifiers changed from: private */
    public BeautifyAdapter mAdapter;
    private List<BeautifyData> mDataList;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (BeautifyFragment.this.mAdapter.getSelection() == i) {
                return true;
            }
            BeautifyFragment beautifyFragment = BeautifyFragment.this;
            beautifyFragment.performItemSelect(i, beautifyFragment.mValues[i], false);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    private TextView mTitle;
    /* access modifiers changed from: private */
    public int[] mValues;

    public BeautifyFragment() {
        super(Effect.BEAUTIFY);
    }

    private void doRender(Metadata metadata, Object obj) {
        ((AbstractEffectFragment) getRenderFragment()).clear();
        ((AbstractEffectFragment) getRenderFragment()).add(metadata, obj);
        ((AbstractEffectFragment) getRenderFragment()).render();
    }

    /* access modifiers changed from: private */
    public void performItemSelect(int i, int i2, boolean z) {
        BeautifyData beautifyData = this.mDataList.get(i);
        this.mAdapter.setSelection(i);
        if (!z) {
            doRender(beautifyData, Integer.valueOf(i2));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDataList = new ArrayList(this.mSdkProvider.list());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new BeautifyView(viewGroup.getContext());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mValues = new int[this.mDataList.size()];
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mAdapter = new BeautifyAdapter(getActivity(), this.mDataList, new Selectable.Selector(getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitle.setText(R.string.photo_editor_beautify);
        performItemSelect(1, 0, false);
    }
}
