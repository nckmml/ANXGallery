package com.miui.gallery.editor.photo.app.navigator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.editor.photo.app.AbstractNaviFragment;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.ui.menu.type.EditNavMenuView;
import com.miui.gallery.module.GalleryModuleManager;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;
import miui.widget.GuidePopupWindow;

public class PhotoNaviFragment extends AbstractNaviFragment {
    private Adapter mAdapter;
    private Context mContext;
    private Dialog mDialog;
    private GuidePopupWindow mGuideView;
    private SimpleRecyclerView mNavigator;
    private SimpleRecyclerView.OnItemClickListener mOnItemSelectedListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            final AbstractNaviFragment.NavigatorData navigatorData = (AbstractNaviFragment.NavigatorData) PhotoNaviFragment.this.getNaviData().get(i);
            if (navigatorData.isPlugin) {
                GalleryModuleManager instance = GalleryModuleManager.getInstance();
                if (!instance.isModuleLoaded(navigatorData.pluginModuleName)) {
                    String string = PhotoNaviFragment.this.getString(navigatorData.name);
                    if (instance.isModuleLoading(navigatorData.pluginModuleName)) {
                        ToastUtils.makeText((Context) PhotoNaviFragment.this.getActivity(), (CharSequence) PhotoNaviFragment.this.getString(R.string.photo_editor_navigator_module_downloading, new Object[]{string}));
                        return true;
                    } else if (!NetworkUtils.isNetworkConnected()) {
                        ToastUtils.makeText((Context) PhotoNaviFragment.this.getActivity(), (CharSequence) PhotoNaviFragment.this.getString(R.string.photo_editor_navigator_need_download, new Object[]{string}));
                        return true;
                    } else {
                        if (NetworkUtils.isActiveNetworkMetered()) {
                            PhotoNaviFragment photoNaviFragment = PhotoNaviFragment.this;
                            photoNaviFragment.showDialog(photoNaviFragment.getString(R.string.photo_editor_navigator_download_network_metered_title), PhotoNaviFragment.this.getString(R.string.photo_editor_navigator_download_network_metered_msg, new Object[]{string}), R.string.photo_editor_navigator_module_download_now, R.string.photo_editor_navigator_module_download_wait_wlan, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (!GalleryModuleManager.getInstance().isModuleLoaded(navigatorData.pluginModuleName)) {
                                        PhotoNaviFragment.this.loadEffectModule(navigatorData, false, true);
                                    }
                                }
                            }, (DialogInterface.OnClickListener) null);
                        } else {
                            PhotoNaviFragment.this.loadEffectModule(navigatorData, false, true);
                            ToastUtils.makeText((Context) PhotoNaviFragment.this.getActivity(), (CharSequence) PhotoNaviFragment.this.getString(R.string.photo_editor_navigator_module_downloading, new Object[]{string}));
                        }
                        return true;
                    }
                }
            }
            PhotoNaviFragment.this.notifyNavigate(navigatorData.effect);
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

    private static class EffectModuleLoadListener implements GalleryModuleManager.ModuleLoadListener {
        private AbstractNaviFragment.NavigatorData mNavigatorData;
        private boolean mNotifyResult;

        public EffectModuleLoadListener(AbstractNaviFragment.NavigatorData navigatorData, boolean z) {
            this.mNavigatorData = navigatorData;
            this.mNotifyResult = z;
        }

        public void onLoadFailed() {
            Log.w("PhotoNaviFragment", "Module %s load failed. Notify result %s", this.mNavigatorData.pluginModuleName, Boolean.valueOf(this.mNotifyResult));
            if (this.mNotifyResult) {
                Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
                Resources resources = sGetAndroidContext.getResources();
                ToastUtils.makeText(sGetAndroidContext, (CharSequence) resources.getString(R.string.photo_editor_navigator_module_download_failed, new Object[]{resources.getString(this.mNavigatorData.name)}));
            }
        }

        public void onLoadSuccess() {
            Log.d("PhotoNaviFragment", "Module %s load success", (Object) this.mNavigatorData.pluginModuleName);
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

        public ImageView getImageView() {
            return this.mImageView;
        }
    }

    private void checkAndLoadEffectModule() {
        GalleryModuleManager instance = GalleryModuleManager.getInstance();
        for (AbstractNaviFragment.NavigatorData next : getNaviData()) {
            if (next.isPlugin) {
                String str = next.pluginModuleName;
                if (!instance.isModuleLoaded(str) && !instance.isModuleLoading(str)) {
                    loadEffectModule(next, !NetworkUtils.isNetworkConnected() || NetworkUtils.isActiveNetworkMetered(), false);
                }
            }
        }
    }

    private RecyclerView.ViewHolder findItemViewHolderByEffect(Effect effect) {
        if (effect == null) {
            return null;
        }
        for (int i = 0; i < getNaviData().size(); i++) {
            if (getNaviData().get(i).isSelectedEffect(effect)) {
                return this.mNavigator.findViewHolderForLayoutPosition(i);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void loadEffectModule(final AbstractNaviFragment.NavigatorData navigatorData, final boolean z, final boolean z2) {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
            public Object run(ThreadPool.JobContext jobContext) {
                Log.d("PhotoNaviFragment", "Module %s load start", (Object) navigatorData.pluginModuleName);
                GalleryModuleManager.getInstance().loadModule(z, new EffectModuleLoadListener(navigatorData, z2), navigatorData.pluginModuleName);
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDialog(String str, String str2, int i, int i2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.mDialog = new AlertDialog.Builder(getActivity()).setTitle(str).setMessage(str2).setPositiveButton(i, onClickListener).setNegativeButton(i2, onClickListener2).create();
        this.mDialog.show();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        checkAndLoadEffectModule();
    }

    /* access modifiers changed from: protected */
    public View onCreateNavigator(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new EditNavMenuView(viewGroup.getContext());
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mDialog = null;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        getView().findViewById(R.id.export).setOnClickListener((View.OnClickListener) null);
        getView().findViewById(R.id.discard).setOnClickListener((View.OnClickListener) null);
    }

    /* access modifiers changed from: protected */
    public void onNavigatorCreated(View view, Bundle bundle) {
        super.onNavigatorCreated(view, bundle);
        this.mContext = getActivity();
        this.mNavigator = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mAdapter = new Adapter(getNaviData());
        this.mNavigator.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemSelectedListener);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.photo_editor_nav_item_start);
        this.mNavigator.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.photo_editor_nav_item_horizontal_interval), 0, 0, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mNavigator);
    }

    public void showItemGuideView(Effect effect, int i, int i2) {
        RecyclerView.ViewHolder findItemViewHolderByEffect = findItemViewHolderByEffect(effect);
        if (findItemViewHolderByEffect != null) {
            this.mGuideView = new GuidePopupWindow(this.mContext);
            this.mGuideView.setArrowMode(i);
            this.mGuideView.setGuideText(i2);
            this.mGuideView.show(((NavigatorHolder) findItemViewHolderByEffect).getImageView(), 0, getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_nav_sky_guide_hint_offsetY), false);
        }
    }
}
