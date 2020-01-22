package com.miui.gallery.editor.photo.app.navigator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.AbstractNaviFragment;
import com.miui.gallery.editor.ui.menu.type.EditNavMenuView;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class ScreenshotNaviFragment extends AbstractNaviFragment {
    private Adapter mAdapter;
    private SimpleRecyclerView mNavigator;
    private SimpleRecyclerView.OnItemClickListener mOnItemSelectedListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScreenshotNaviFragment screenshotNaviFragment = ScreenshotNaviFragment.this;
            screenshotNaviFragment.notifyNavigate(((AbstractNaviFragment.NavigatorData) screenshotNaviFragment.getNaviData().get(i)).effect);
            return true;
        }
    };

    private static final class Adapter extends SimpleRecyclerView.Adapter<NavigatorHolder> {
        private List<AbstractNaviFragment.NavigatorData> mNavigatorData;

        public Adapter(List<AbstractNaviFragment.NavigatorData> list) {
            this.mNavigatorData = list;
        }

        public int getItemCount() {
            return this.mNavigatorData.size();
        }

        public void onBindViewHolder(NavigatorHolder navigatorHolder, int i) {
            super.onBindViewHolder(navigatorHolder, i);
            navigatorHolder.bind(this.mNavigatorData.get(i));
        }

        public NavigatorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new NavigatorHolder(getInflater().inflate(R.layout.common_editor_navigator_item, viewGroup, false));
        }
    }

    private static final class NavigatorHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mView;

        public NavigatorHolder(View view) {
            super(view);
            this.mView = (TextView) view.findViewById(R.id.label);
            this.mImageView = (ImageView) view.findViewById(R.id.img_nav);
        }

        /* access modifiers changed from: package-private */
        public void bind(AbstractNaviFragment.NavigatorData navigatorData) {
            this.mView.setText(navigatorData.name);
            this.mImageView.setImageResource(navigatorData.icon);
        }
    }

    /* access modifiers changed from: protected */
    public View onCreateNavigator(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new EditNavMenuView(viewGroup.getContext());
    }

    /* access modifiers changed from: protected */
    public void onNavigatorCreated(View view, Bundle bundle) {
        this.mNavigator = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mAdapter = new Adapter(getNaviData());
        this.mNavigator.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemSelectedListener);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.photo_editor_screen_nav_item_start);
        int size = getNaviData() == null ? 0 : getNaviData().size();
        int i = 1;
        if (size > 1) {
            i = size - 1;
        }
        this.mNavigator.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, ((ScreenUtils.getScreenWidth() - (dimensionPixelSize * 2)) - (size * getResources().getDimensionPixelSize(R.dimen.video_editor_nav_item_width))) / i, 0, 0, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mNavigator);
    }
}
