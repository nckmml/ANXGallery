package com.miui.gallery.editor.photo.screen.text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.TextData;
import com.miui.gallery.editor.photo.core.imports.text.TextConfig;
import com.miui.gallery.editor.photo.core.imports.text.TextManager;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseMenuFragment;
import com.miui.gallery.editor.photo.screen.stat.ScreenEditorStatUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class ScreenTextMenuFragment extends ScreenBaseMenuFragment<IScreenTextOperation> {
    private SimpleRecyclerView.OnItemClickListener mBubbleItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ((IScreenTextOperation) ScreenTextMenuFragment.this.mScreenOperation).add((TextConfig) ScreenTextMenuFragment.this.mScreenTextBubbleAdapter.getItemData(i), i);
            ScreenTextMenuFragment.this.mScreenTextBubbleAdapter.setSelection(i);
            ScreenEditorStatUtils.statTextMenuItemClick(i);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public ScreenTextBubbleAdapter mScreenTextBubbleAdapter;
    private List<TextData> mTextDataList;

    /* access modifiers changed from: protected */
    public Class<IScreenTextOperation> getScreenOperationClass() {
        return IScreenTextOperation.class;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTextDataList = TextManager.getScreenTextBubbleData();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.screen_text_menu_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.preview_list);
        this.mScreenTextBubbleAdapter = new ScreenTextBubbleAdapter(getActivity(), this.mTextDataList, ((IScreenTextOperation) this.mScreenOperation).getCurrentMenuItemIndex());
        this.mScreenTextBubbleAdapter.setOnItemClickListener(this.mBubbleItemClickListener);
        this.mRecyclerView.setAdapter(this.mScreenTextBubbleAdapter);
        int dimension = (int) getResources().getDimension(R.dimen.screen_editor_text_item_start_margin);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimension, dimension, (int) getResources().getDimension(R.dimen.screen_editor_text_item_margin), 0, 0, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
    }
}
