package com.miui.gallery.editor.photo.app.miuibeautify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.filtersdk.beauty.BeautyProcessorManager;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.miuibeautify.SmartBeautyLevelItemAdapter;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;

public class SmartBeautyFragment extends ChildMenuFragment {
    /* access modifiers changed from: private */
    public SmartBeautyLevelItemAdapter mBeautyLevelAdapter;
    private SimpleRecyclerView mLevelItemList;
    /* access modifiers changed from: private */
    public boolean mListItemClickable = true;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        int mPrePos = 0;

        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (i != this.mPrePos && SmartBeautyFragment.this.mListItemClickable) {
                this.mPrePos = i;
                SmartBeautyFragment.this.setBeautyParameterTable(BeautyProcessorManager.INSTANCE.getBeautyProcessor().getIntelligentLevelParams(i));
                SmartBeautyFragment.this.notifyBeautyParameterChanged();
                SmartBeautyFragment.this.mBeautyLevelAdapter.setSelection(i);
            }
            return true;
        }
    };

    private void initView(View view) {
        this.mLevelItemList = (SimpleRecyclerView) view.findViewById(R.id.beauty_level_item_list);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_0_n, R.drawable.icon_level_0_p));
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_1_n, R.drawable.icon_level_1_p));
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_2_n, R.drawable.icon_level_2_p));
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_3_n, R.drawable.icon_level_3_p));
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_4_n, R.drawable.icon_level_4_p));
        arrayList.add(new SmartBeautyLevelItemAdapter.LevelItem(R.drawable.icon_level_5_n, R.drawable.icon_level_5_p));
        this.mBeautyLevelAdapter = new SmartBeautyLevelItemAdapter(getActivity(), arrayList);
        this.mLevelItemList.setAdapter(this.mBeautyLevelAdapter);
        this.mBeautyLevelAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mBeautyLevelAdapter.notifyDataSetChanged();
        this.mCallbacks.changeTitle(getResources().getString(R.string.photo_editor_miui_beauty_menu_smart_beauty));
        this.mOnItemClickListener.OnItemClick((RecyclerView) null, (View) null, 3);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.editor_beauty_smart_start);
        this.mLevelItemList.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.editor_beauty_smart_horizontal_interval), 0, 0, 0));
    }

    public void onBeautyProcessEnd() {
        super.onBeautyProcessEnd();
        this.mListItemClickable = true;
    }

    public void onBeautyProcessStart() {
        super.onBeautyProcessStart();
        this.mListItemClickable = false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_beauty_level, viewGroup, false);
        initView(inflate);
        return inflate;
    }
}
