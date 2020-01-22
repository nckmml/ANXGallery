package com.miui.gallery.video.editor.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class FilterAdjustPageAdapter extends PagerAdapter {
    private List<View> mViewList;

    public FilterAdjustPageAdapter(List<View> list) {
        this.mViewList = list;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.mViewList.get(i));
    }

    public int getCount() {
        List<View> list = this.mViewList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.mViewList.get(i));
        return this.mViewList.get(i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
