package com.miui.gallery.editor.photo.app.sticker;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.StickerView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.StickerCategory;
import com.miui.gallery.editor.photo.core.common.model.StickerData;
import com.miui.gallery.editor.photo.core.common.provider.AbstractStickerProvider;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StickerMenuFragment extends MenuFragment<AbstractEffectFragment, AbstractStickerProvider> {
    /* access modifiers changed from: private */
    public List<StickerCategory> mCategories;
    /* access modifiers changed from: private */
    public HeaderAdapter mHeaderAdapter;
    private SimpleRecyclerView mHeaderView;
    private SimpleRecyclerView.OnItemClickListener mItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            StickerMenuFragment.this.mStickerPager.setCurrentItem(i + 1, true);
            if (StickerMenuFragment.this.mRecentView.isSelected()) {
                StickerMenuFragment.this.mRecentView.setSelected(false);
            }
            StickerMenuFragment.this.mHeaderAdapter.setSelection(i);
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            return true;
        }
    };
    /* access modifiers changed from: private */
    public SimpleRecyclerView.OnItemClickListener mOnStickerSelectedListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ((AbstractEffectFragment) StickerMenuFragment.this.getRenderFragment()).add(((CategoryDetailAdapter) recyclerView.getAdapter()).getDataList().get(i), (Object) null);
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            return true;
        }
    };
    /* access modifiers changed from: private */
    public View mRecentView;
    /* access modifiers changed from: private */
    public ViewPager mStickerPager;
    private StickerPagerAdapter mStickerPagerAdapter;
    private TextView mTitle;

    private class StickerPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
        private AbstractStickerProvider mProvider = ((AbstractStickerProvider) SdkManager.INSTANCE.getProvider(Effect.STICKER));
        private SparseArray<RecyclerView> mViewMapping = new SparseArray<>();

        public StickerPagerAdapter() {
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
            this.mViewMapping.remove(i);
        }

        public int getCount() {
            return StickerMenuFragment.this.mCategories.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            List<StickerData> list;
            SimpleRecyclerView simpleRecyclerView = new SimpleRecyclerView(StickerMenuFragment.this.getActivity());
            StickerCategory stickerCategory = (StickerCategory) StickerMenuFragment.this.mCategories.get(i);
            ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(StickerMenuFragment.this.getActivity());
            scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(StickerMenuFragment.this.getActivity()));
            scrollLinearLayoutManager.setOrientation(0);
            simpleRecyclerView.setLayoutManager(scrollLinearLayoutManager);
            viewGroup.addView(simpleRecyclerView);
            if (stickerCategory.id == -9223372036854775807L) {
                list = this.mProvider.recent();
                simpleRecyclerView.setTag("recent_tag");
            } else {
                list = this.mProvider.list(stickerCategory.id);
            }
            CategoryDetailAdapter categoryDetailAdapter = new CategoryDetailAdapter(StickerMenuFragment.this.getActivity(), list);
            simpleRecyclerView.setAdapter(categoryDetailAdapter);
            categoryDetailAdapter.setOnItemClickListener(StickerMenuFragment.this.mOnStickerSelectedListener);
            simpleRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(StickerMenuFragment.this.getResources(), R.dimen.editor_menu_sticker_view_pager_horizontal_interval, 0));
            HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(simpleRecyclerView);
            this.mViewMapping.put(i, simpleRecyclerView);
            return simpleRecyclerView;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            RecyclerView recyclerView = this.mViewMapping.get(i);
            if (recyclerView != null && "recent_tag".equals(recyclerView.getTag())) {
                CategoryDetailAdapter categoryDetailAdapter = (CategoryDetailAdapter) recyclerView.getAdapter();
                categoryDetailAdapter.setDataList(((AbstractStickerProvider) StickerMenuFragment.this.mSdkProvider).recent());
                categoryDetailAdapter.notifyDataSetChanged();
            }
        }
    }

    public StickerMenuFragment() {
        super(Effect.STICKER);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCategories = new ArrayList();
        this.mCategories.addAll(((AbstractStickerProvider) this.mSdkProvider).list());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new StickerView(viewGroup.getContext());
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mStickerPager.removeOnPageChangeListener(this.mStickerPagerAdapter);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mHeaderAdapter = new HeaderAdapter(new ArrayList(this.mCategories), new Selectable.Selector(getActivity().getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mCategories.add(0, new CategoryRecent());
        this.mHeaderView = (SimpleRecyclerView) view.findViewById(R.id.category);
        this.mHeaderView.setAdapter(this.mHeaderAdapter);
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity());
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        scrollLinearLayoutManager.setOrientation(0);
        this.mHeaderView.setLayoutManager(scrollLinearLayoutManager);
        this.mHeaderAdapter.setOnItemClickListener(this.mItemClickListener);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mHeaderView);
        this.mStickerPagerAdapter = new StickerPagerAdapter();
        this.mStickerPager = (ViewPager) view.findViewById(R.id.view_pager);
        this.mStickerPager.setAdapter(this.mStickerPagerAdapter);
        this.mStickerPager.setCurrentItem(1, false);
        this.mStickerPager.addOnPageChangeListener(this.mStickerPagerAdapter);
        this.mRecentView = view.findViewById(R.id.recent);
        this.mRecentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!StickerMenuFragment.this.mRecentView.isSelected()) {
                    StickerMenuFragment.this.mRecentView.setSelected(true);
                }
                StickerMenuFragment.this.mHeaderAdapter.setSelection(-1);
                StickerMenuFragment.this.mStickerPager.setCurrentItem(0, true);
            }
        });
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitle.setText(R.string.photo_editor_sticker);
    }
}
