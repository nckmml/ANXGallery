package com.miui.gallery.ui;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.ComponentsStrategy;
import com.miui.gallery.threadpool.PriorityThreadFactory;
import com.miui.gallery.ui.PhotoPageFragmentBase;
import com.miui.gallery.ui.ShareStateRouter;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.util.ShareComponentSorter;
import com.miui.gallery.widget.CircleImageView;
import com.miui.gallery.widget.ViewPager;
import com.miui.mishare.app.view.MiShareGalleryTransferView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ChooserFragment extends Fragment implements ShareStateRouter.PrintStateObserver, ShareStateRouter.ProjectStateObserver, ViewPager.OnPageChangeListener {
    /* access modifiers changed from: private */
    public ResolverAdapter mAdapter;
    /* access modifiers changed from: private */
    public OnIntentSelectedListener mListener;
    /* access modifiers changed from: private */
    public MiShareGalleryTransferView mMiShareTransferView;
    /* access modifiers changed from: private */
    public OnMishareClickedListener mOnMishareClickedListener;
    /* access modifiers changed from: private */
    public OnProjectClickedListener mOnProjectClickedListener;
    private RecyclerView mRecyclerView;
    private ShareComponentSorter.OnInitializedListener mSorterInitializedListener;

    private static class Component {
        String mClassName;
        int mHashCode;
        String mPackageName;

        public Component() {
        }

        Component(ResolveInfo resolveInfo) {
            init(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }

        private void init(String str, String str2) {
            this.mPackageName = str;
            this.mClassName = str2;
            this.mHashCode = (this.mPackageName + this.mClassName).hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof Component) {
                Component component = (Component) obj;
                return this.mPackageName.equals(component.mPackageName) && this.mClassName.equals(component.mClassName);
            }
        }

        public int hashCode() {
            return this.mHashCode;
        }

        /* access modifiers changed from: package-private */
        public Component wrap(ResolveInfo resolveInfo) {
            init(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return this;
        }
    }

    public interface OnIntentSelectedListener {
        void onIntentSelected(Intent intent);
    }

    public interface OnMishareClickedListener {
        void onMishareClicked(PhotoPageFragmentBase.OnFilesProcessedListener onFilesProcessedListener);
    }

    public interface OnProjectClickedListener {
        void onProjectedClicked();
    }

    private static class PriorityComparator implements Comparator<ResolveInfo> {
        /* access modifiers changed from: private */
        public Comparator<ResolveInfo> mNormal;
        private HashMap<Component, Integer> mPriority;
        private Component mTemp;

        private PriorityComparator() {
            this.mPriority = new HashMap<>();
            this.mTemp = new Component();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: boolean} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        public void build(List<ComponentsStrategy.Priority> list, List<ResolveInfo> list2) {
            boolean z;
            Log.d("ChooserFragment", "build priority: %s", (Object) list);
            for (ResolveInfo next : list2) {
                this.mTemp.wrap(next);
                if (this.mPriority.get(this.mTemp) == null) {
                    Iterator<ComponentsStrategy.Priority> it = list.iterator();
                    while (true) {
                        z = false;
                        if (!it.hasNext()) {
                            break;
                        }
                        ComponentsStrategy.Priority next2 = it.next();
                        String str = next.activityInfo.packageName;
                        String str2 = next.activityInfo.name;
                        if ((next.activityInfo.applicationInfo.flags & 1) != 0) {
                            z = true;
                        }
                        if (next2.match(str, str2, z)) {
                            z = next2.value;
                            break;
                        }
                    }
                    this.mPriority.put(new Component(next), Integer.valueOf(z));
                    Log.d("ChooserFragment", "assign priority of %s, %s by %d", this.mTemp.mPackageName, this.mTemp.mClassName, Integer.valueOf(z));
                }
            }
            Log.d("ChooserFragment", "build finish");
        }

        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            int compare = this.mNormal.compare(resolveInfo, resolveInfo2);
            if (compare != 0) {
                return compare;
            }
            int unbox = Numbers.unbox(this.mPriority.get(this.mTemp.wrap(resolveInfo)), 0);
            int unbox2 = Numbers.unbox(this.mPriority.get(this.mTemp.wrap(resolveInfo2)), 0);
            if (unbox < unbox2) {
                return 1;
            }
            return unbox == unbox2 ? 0 : -1;
        }
    }

    private static class ResolverAdapter extends RecyclerView.Adapter<CellHolder> {
        private LinkedList<WeakReference<View>> mCachedViews = new LinkedList<>();
        /* access modifiers changed from: private */
        public Context mContext;
        private List<DisplayResolvedInfo> mData = new ArrayList();
        /* access modifiers changed from: private */
        public OnIntentSelectedListener mListener;
        private ResolveInfoLoader mLoader;
        private PackageManager mPm;
        private PriorityComparator mPriorityComparator = new PriorityComparator();
        private boolean mResumed = false;
        private Intent mTarIntent;
        /* access modifiers changed from: private */
        public int mTheme;

        private class CellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView mIcon;
            public DisplayResolvedInfo mInfo;
            public TextView mText;

            public CellHolder(ViewGroup viewGroup, boolean z) {
                super(LayoutInflater.from(ResolverAdapter.this.mContext).inflate(R.layout.chooser_item_cell, viewGroup, false));
                if (z) {
                    int paddingStart = this.itemView.getPaddingStart();
                    int paddingEnd = this.itemView.getPaddingEnd();
                    int paddingTop = this.itemView.getPaddingTop();
                    int paddingBottom = this.itemView.getPaddingBottom();
                    this.itemView.setPaddingRelative(paddingStart + ResolverAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.chooser_item_first_cell_extra_padding_start), paddingTop, paddingEnd, paddingBottom);
                }
                this.mIcon = (ImageView) this.itemView.findViewById(R.id.chooser_icon);
                this.mText = (TextView) this.itemView.findViewById(R.id.chooser_text);
                if (ResolverAdapter.this.mTheme == 0) {
                    this.mText.setTextAppearance(ResolverAdapter.this.mContext, 2131820734);
                } else {
                    this.mText.setTextAppearance(ResolverAdapter.this.mContext, 2131820733);
                }
                this.mIcon.setOnClickListener(this);
            }

            public boolean needRefresh(DisplayResolvedInfo displayResolvedInfo) {
                return !displayResolvedInfo.equals(this.mInfo) || this.mIcon.getDrawable() == null;
            }

            public void onClick(View view) {
                ShareComponentSorter.getInstance().touch(this.mInfo.mResolveInfo.activityInfo.packageName, this.mInfo.mResolveInfo.activityInfo.name);
                if (ResolverAdapter.this.mListener != null) {
                    ResolverAdapter.this.mListener.onIntentSelected(new Intent(this.mInfo.getResolvedIntent()));
                }
                ShareComponentSorter.getInstance().save();
            }
        }

        private class DisplayResolvedInfo {
            /* access modifiers changed from: private */
            public ResolveInfo mResolveInfo;
            private Intent mResolvedIntent;

            public DisplayResolvedInfo(Intent intent, ResolveInfo resolveInfo) {
                this.mResolveInfo = resolveInfo;
                this.mResolvedIntent = new Intent(intent);
                ActivityInfo activityInfo = this.mResolveInfo.activityInfo;
                this.mResolvedIntent.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            }

            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof DisplayResolvedInfo)) {
                    return false;
                }
                return ResolverAdapter.isSameResolvedComponent(getResolveInfo(), ((DisplayResolvedInfo) obj).getResolveInfo());
            }

            public ResolveInfo getResolveInfo() {
                return this.mResolveInfo;
            }

            public Intent getResolvedIntent() {
                return this.mResolvedIntent;
            }

            public int hashCode() {
                return (this.mResolveInfo.activityInfo.packageName + this.mResolveInfo.activityInfo.name).hashCode();
            }
        }

        private static class ResolveInfoLoader {
            private Map<Integer, String> mCacheKey = Collections.synchronizedMap(new HashMap());
            private Map<String, LoadResult> mCacheResult = new HashMap();
            private ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory("thread-pool", 10));

            private class IconResult {
                final Drawable drawable;

                public IconResult(Drawable drawable2) {
                    this.drawable = drawable2;
                }
            }

            private static class LoadResult {
                final Drawable mIcon;
                final CharSequence mLabel;
                LoadingInfo mLoadingInfo;

                public LoadResult(Drawable drawable, CharSequence charSequence) {
                    this.mIcon = drawable;
                    this.mLabel = charSequence;
                }

                /* access modifiers changed from: package-private */
                public LoadResult setLoadingInfo(LoadingInfo loadingInfo) {
                    this.mLoadingInfo = loadingInfo;
                    return this;
                }
            }

            private class LoadTask extends AsyncTask<LoadingInfo, Void, LoadResult> {
                private WeakReference<Context> mContextRef;

                LoadTask(Context context) {
                    this.mContextRef = new WeakReference<>(context);
                }

                private IconResult loadIcon(LoadingInfo loadingInfo) {
                    Drawable drawable = null;
                    try {
                        ResolveInfo resolveInfo = loadingInfo.mResolve;
                        Context context = (Context) this.mContextRef.get();
                        if (context != null) {
                            Resources resources = context.getResources();
                            String str = resolveInfo.activityInfo.name;
                            String str2 = resolveInfo.activityInfo.packageName;
                            if (TextUtils.equals(str, "com.tencent.mm.ui.tools.ShareImgUI")) {
                                drawable = resources.getDrawable(R.drawable.ic_share_wechat);
                            } else if (TextUtils.equals(str, "com.tencent.mm.ui.tools.ShareToTimeLineUI")) {
                                drawable = resources.getDrawable(R.drawable.ic_share_wechat_moments);
                            } else if (TextUtils.equals(str, "com.tencent.mobileqq.activity.JumpActivity") && TextUtils.equals(str2, "com.tencent.mobileqq")) {
                                drawable = resources.getDrawable(R.drawable.ic_share_qq);
                            } else if (TextUtils.equals(str, "com.sina.weibo.composerinde.ComposerDispatchActivity")) {
                                drawable = resources.getDrawable(R.drawable.ic_share_weibo);
                            } else if (TextUtils.equals(str2, "com.qzone")) {
                                drawable = resources.getDrawable(R.drawable.ic_share_qzone);
                            }
                            if (drawable == null) {
                                drawable = resolveInfo.loadIcon(context.getPackageManager());
                            }
                        }
                    } catch (Exception e) {
                        Log.e("ChooserFragment", (Throwable) e);
                    }
                    return new IconResult(drawable);
                }

                private CharSequence loadLabel(ResolveInfo resolveInfo) {
                    int i;
                    Context context = (Context) this.mContextRef.get();
                    if (context == null) {
                        return null;
                    }
                    try {
                        Resources resources = context.getResources();
                        String str = resolveInfo.activityInfo.name;
                        String str2 = resolveInfo.activityInfo.packageName;
                        if (TextUtils.equals(str, "com.tencent.mm.ui.tools.ShareImgUI")) {
                            i = resources.getIdentifier("com.tencent.mm_com.tencent.mm.ui.tools.ShareImgUI", "string", context.getPackageName());
                        } else if (TextUtils.equals(str, "com.tencent.mm.ui.tools.ShareToTimeLineUI")) {
                            i = resources.getIdentifier("com.tencent.mm_com.tencent.mm.ui.tools.ShareToTimeLineUI", "string", context.getPackageName());
                        } else if (TextUtils.equals(str, "com.tencent.mobileqq.activity.JumpActivity") && TextUtils.equals(str2, "com.tencent.mobileqq")) {
                            i = resources.getIdentifier("com.tencent.mobileqq_com.tencent.mobileqq.activity.JumpActivity", "string", context.getPackageName());
                        } else if (TextUtils.equals(str, "com.sina.weibo.composerinde.ComposerDispatchActivity")) {
                            i = resources.getIdentifier("com.sina.weibo_com.sina.weibo.composerinde.ComposerDispatchActivity", "string", context.getPackageName());
                        } else if (TextUtils.equals(str2, "com.qzone")) {
                            i = resources.getIdentifier("com.qzone_com.qzonex.module.operation.ui.QZonePublishMoodActivity", "string", context.getPackageName());
                        } else {
                            i = resources.getIdentifier(resolveInfo.activityInfo.packageName + "_" + resolveInfo.activityInfo.name, "string", context.getPackageName());
                        }
                        if (i != 0) {
                            return resources.getString(i);
                        }
                    } catch (Exception unused) {
                    }
                    return resolveInfo.loadLabel(context.getPackageManager());
                }

                /* access modifiers changed from: protected */
                public LoadResult doInBackground(LoadingInfo... loadingInfoArr) {
                    LoadingInfo loadingInfo = loadingInfoArr[0];
                    if (loadingInfo == null) {
                        return null;
                    }
                    IconResult loadIcon = loadIcon(loadingInfo);
                    return new LoadResult(loadIcon.drawable, loadLabel(loadingInfo.mResolve)).setLoadingInfo(loadingInfo);
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(LoadResult loadResult) {
                    ResolveInfoLoader.this.setResult(loadResult);
                }
            }

            private static class LoadingInfo {
                final WeakReference<ImageView> mIcon;
                final WeakReference<TextView> mLabel;
                final ResolveInfo mResolve;

                public LoadingInfo(ImageView imageView, TextView textView, ResolveInfo resolveInfo) {
                    this.mIcon = new WeakReference<>(imageView);
                    this.mLabel = new WeakReference<>(textView);
                    this.mResolve = resolveInfo;
                }

                public ImageView getIconView() {
                    WeakReference<ImageView> weakReference = this.mIcon;
                    if (weakReference != null) {
                        return (ImageView) weakReference.get();
                    }
                    return null;
                }

                public TextView getLabelView() {
                    WeakReference<TextView> weakReference = this.mLabel;
                    if (weakReference != null) {
                        return (TextView) weakReference.get();
                    }
                    return null;
                }

                public int getViewId() {
                    ImageView iconView = getIconView();
                    if (iconView != null) {
                        return iconView.hashCode();
                    }
                    return 0;
                }
            }

            private static String generateKey(ResolveInfo resolveInfo) {
                StringBuilder sb = new StringBuilder();
                if (resolveInfo != null) {
                    sb.append(resolveInfo.activityInfo.packageName);
                    sb.append("#");
                    sb.append(resolveInfo.activityInfo.name);
                }
                return sb.toString();
            }

            private boolean needApplyResult(LoadResult loadResult) {
                if (loadResult == null || loadResult.mLoadingInfo == null) {
                    return false;
                }
                this.mCacheResult.put(generateKey(loadResult.mLoadingInfo.mResolve), loadResult);
                return TextUtils.equals(generateKey(loadResult.mLoadingInfo.mResolve), this.mCacheKey.get(Integer.valueOf(loadResult.mLoadingInfo.getViewId())));
            }

            /* access modifiers changed from: private */
            public void setResult(LoadResult loadResult) {
                if (needApplyResult(loadResult)) {
                    ImageView iconView = loadResult.mLoadingInfo.getIconView();
                    if (iconView != null) {
                        if (iconView instanceof CircleImageView) {
                            ((CircleImageView) iconView).setDrawableInset(-10, false);
                        }
                        iconView.setImageDrawable(loadResult.mIcon);
                    }
                    TextView labelView = loadResult.mLoadingInfo.getLabelView();
                    if (labelView != null) {
                        labelView.setText(loadResult.mLabel);
                    }
                    this.mCacheKey.remove(generateKey(loadResult.mLoadingInfo.mResolve));
                }
            }

            private void submit(Context context, LoadingInfo loadingInfo) {
                new LoadTask(context).executeOnExecutor(this.mExecutor, new LoadingInfo[]{loadingInfo});
            }

            public void loadInfo(Context context, ImageView imageView, TextView textView, ResolveInfo resolveInfo) {
                if (resolveInfo != null) {
                    String generateKey = generateKey(resolveInfo);
                    LoadingInfo loadingInfo = new LoadingInfo(imageView, textView, resolveInfo);
                    this.mCacheKey.put(Integer.valueOf(loadingInfo.getViewId()), generateKey);
                    LoadResult loadResult = this.mCacheResult.get(generateKey);
                    if (loadResult != null) {
                        loadResult.setLoadingInfo(loadingInfo);
                        setResult(loadResult);
                        Log.d("ChooserFragment", "load from cache");
                        return;
                    }
                    Log.d("ChooserFragment", "load from file");
                    submit(context, loadingInfo);
                }
            }

            public void release() {
                try {
                    this.mCacheKey.clear();
                    this.mCacheResult.clear();
                    this.mExecutor.shutdownNow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ResolverAdapter(Context context, Intent intent, int i, boolean z) {
            this.mContext = context;
            this.mPm = context.getPackageManager();
            this.mTarIntent = intent;
            this.mTheme = i;
            this.mLoader = new ResolveInfoLoader();
            reBuildList();
            this.mResumed = z;
        }

        private void addResolveListDedupe(List<ResolveInfo> list, List<ResolveInfo> list2) {
            boolean z;
            int size = list2.size();
            int size2 = list.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = list2.get(i);
                int i2 = 0;
                while (true) {
                    if (i2 >= size2) {
                        z = false;
                        break;
                    } else if (isSameResolvedComponent(resolveInfo, list.get(i))) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    list.add(resolveInfo);
                }
            }
        }

        private ResolveInfo contains(ComponentsStrategy.Component component, List<ResolveInfo> list) {
            ListIterator<ResolveInfo> listIterator = list.listIterator();
            ResolveInfo resolveInfo = null;
            while (listIterator.hasNext()) {
                ResolveInfo next = listIterator.next();
                if (TextUtils.equals(next.activityInfo.packageName, component.getPackageName())) {
                    if (TextUtils.equals(next.activityInfo.name, component.getClassName())) {
                        return next;
                    }
                    resolveInfo = next;
                }
            }
            return resolveInfo;
        }

        private void filterResolveInfoList(List<ResolveInfo> list) {
            if (list != null) {
                int i = 0;
                while (i < list.size()) {
                    ResolveInfo resolveInfo = list.get(i);
                    if (!resolveInfo.activityInfo.exported) {
                        list.remove(i);
                    } else {
                        String str = resolveInfo.activityInfo.permission;
                        if (!TextUtils.isEmpty(str) && this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                            list.remove(i);
                        }
                        i++;
                    }
                    i--;
                    i++;
                }
            }
        }

        /* access modifiers changed from: private */
        public static boolean isSameResolvedComponent(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            return TextUtils.equals(resolveInfo.activityInfo.packageName, resolveInfo2.activityInfo.packageName) && TextUtils.equals(resolveInfo.activityInfo.name, resolveInfo2.activityInfo.name);
        }

        /* access modifiers changed from: private */
        public void reBuildList() {
            this.mData.clear();
            long currentTimeMillis = System.currentTimeMillis();
            List<ResolveInfo> queryIntentActivities = this.mPm.queryIntentActivities(this.mTarIntent, 65536);
            if (queryIntentActivities != null) {
                filterResolveInfoList(queryIntentActivities);
                LinkedList linkedList = new LinkedList();
                addResolveListDedupe(linkedList, queryIntentActivities);
                int size = linkedList.size();
                if (size > 0) {
                    ResolveInfo resolveInfo = (ResolveInfo) linkedList.get(0);
                    for (int i = 1; i < size; i++) {
                        ResolveInfo resolveInfo2 = (ResolveInfo) linkedList.get(i);
                        if (resolveInfo.priority != resolveInfo2.priority || resolveInfo.isDefault != resolveInfo2.isDefault) {
                            while (i < size) {
                                linkedList.remove(i);
                                size--;
                            }
                        }
                    }
                    long currentTimeMillis2 = System.currentTimeMillis();
                    Comparator<ResolveInfo> createComparator = ShareComponentSorter.getInstance().createComparator();
                    this.mPriorityComparator.build(CloudControlStrategyHelper.getComponentPriority(), linkedList);
                    Comparator unused = this.mPriorityComparator.mNormal = createComparator;
                    Collections.sort(linkedList, this.mPriorityComparator);
                    sortResolveList(linkedList);
                    Log.i("ChooserFragment", "sortResolveList cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                    for (int i2 = 0; i2 < size; i2++) {
                        this.mData.add(new DisplayResolvedInfo(this.mTarIntent, (ResolveInfo) linkedList.get(i2)));
                    }
                }
            }
            Log.i("ChooserFragment", "reBuildList cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }

        private void sortResolveList(List<ResolveInfo> list) {
            List<ComponentsStrategy.Component> shareComponents = CloudControlStrategyHelper.getShareComponents();
            LinkedList linkedList = new LinkedList();
            for (ComponentsStrategy.Component contains : shareComponents) {
                ResolveInfo contains2 = contains(contains, list);
                if (contains2 != null) {
                    linkedList.add(contains2);
                    list.remove(contains2);
                }
            }
            linkedList.addAll(list);
            list.clear();
            list.addAll(linkedList);
        }

        public int getItemCount() {
            List<DisplayResolvedInfo> list = this.mData;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public int getItemViewType(int i) {
            return i == 0 ? 0 : 1;
        }

        public void onBindViewHolder(CellHolder cellHolder, int i) {
            List<DisplayResolvedInfo> list = this.mData;
            if (list != null && list.size() != 0 && i < this.mData.size()) {
                DisplayResolvedInfo displayResolvedInfo = this.mData.get(i);
                if (cellHolder.needRefresh(displayResolvedInfo)) {
                    cellHolder.mInfo = displayResolvedInfo;
                    this.mLoader.loadInfo(this.mContext, cellHolder.mIcon, cellHolder.mText, cellHolder.mInfo.getResolveInfo());
                    return;
                }
                cellHolder.mInfo = displayResolvedInfo;
            }
        }

        public CellHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CellHolder(viewGroup, i == 0);
        }

        public void pause() {
            this.mResumed = false;
        }

        public void release() {
            this.mLoader.release();
            this.mListener = null;
        }

        public boolean requery(Intent intent) {
            if (intent == null || intent.filterEquals(this.mTarIntent)) {
                return false;
            }
            this.mTarIntent = intent;
            reBuildList();
            notifyDataSetChanged();
            return true;
        }

        public void resume() {
            if (!this.mResumed) {
                this.mResumed = true;
                notifyDataSetChanged();
            }
        }

        public void setOnIntentSelectedListener(OnIntentSelectedListener onIntentSelectedListener) {
            this.mListener = onIntentSelectedListener;
        }
    }

    private class SortInitializeListener implements ShareComponentSorter.OnInitializedListener {
        private SortInitializeListener() {
        }

        public void onInitialized() {
            Log.d("ChooserFragment", "sorter initialized, rebuild cells");
            if (ChooserFragment.this.mAdapter != null) {
                ChooserFragment.this.mAdapter.reBuildList();
                ChooserFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public static ChooserFragment newInstance(Intent intent, int i, boolean z, int i2) {
        ChooserFragment chooserFragment = new ChooserFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_target_intent", intent);
        bundle.putInt("key_theme", i);
        bundle.putBoolean("init_visible", z);
        bundle.putInt("share_mode", i2);
        chooserFragment.setArguments(bundle);
        return chooserFragment;
    }

    public void configView(View view, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mRecyclerView.getLayoutParams();
        if (ShareStateRouter.IS_MISHARE_AVAILABLE.get(getActivity()).booleanValue()) {
            ShareStateRouter.getInstance().registerProjectStateObserver(this);
            ShareStateRouter.getInstance().registerPrintStateObserver(this);
            this.mMiShareTransferView = (MiShareGalleryTransferView) view.findViewById(R.id.mishare);
            this.mMiShareTransferView.bind();
            this.mMiShareTransferView.setVisibility(0);
            this.mMiShareTransferView.setDeliveryTitle(getResources().getString(R.string.chooser_fragment_mishare_title));
            this.mMiShareTransferView.setScreenThrowClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ChooserFragment.this.mOnProjectClickedListener != null) {
                        ChooserFragment.this.mOnProjectClickedListener.onProjectedClicked();
                    }
                }
            });
            this.mMiShareTransferView.setFileIfNotYet(new MiShareGalleryTransferView.FilesNotYetSetListener() {
                public void fileNotYetSet() {
                    if (ChooserFragment.this.mOnMishareClickedListener != null) {
                        ChooserFragment.this.mOnMishareClickedListener.onMishareClicked(new PhotoPageFragmentBase.OnFilesProcessedListener() {
                            public void onFilesProcessed(List<Uri> list) {
                                ChooserFragment.this.mMiShareTransferView.sendFiles(list);
                            }
                        });
                    }
                }
            });
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.fast_share_chooser_margin_bottom);
        } else {
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.fast_share_chooser_without_mishare_margin_bottom);
        }
        if (i != 2) {
            if (i == 3) {
                if (ShareStateRouter.IS_MISHARE_AVAILABLE.get(getActivity()).booleanValue()) {
                    this.mMiShareTransferView.setMiPrintEnable(true);
                }
            } else {
                return;
            }
        }
        if (ShareStateRouter.IS_MISHARE_AVAILABLE.get(getActivity()).booleanValue()) {
            this.mMiShareTransferView.setScreenThrowEnable(false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intent intent;
        Bundle arguments = getArguments();
        if (arguments == null || (intent = (Intent) arguments.getParcelable("key_target_intent")) == null) {
            throw new IllegalArgumentException("target intent couldn't be null");
        }
        int i = arguments.getInt("key_theme", 0);
        boolean z = arguments.getBoolean("init_visible", true);
        int i2 = arguments.getInt("share_mode", 1);
        View inflate = layoutInflater.inflate(R.layout.chooser_layout, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.chooser_recycler);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.mAdapter = new ResolverAdapter(getActivity(), intent, i, z);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnIntentSelectedListener(new OnIntentSelectedListener() {
            public void onIntentSelected(Intent intent) {
                if (ChooserFragment.this.mListener != null) {
                    ChooserFragment.this.mListener.onIntentSelected(intent);
                }
            }
        });
        configView(inflate, i2);
        if (!ShareComponentSorter.getInstance().initialized()) {
            Log.d("ChooserFragment", "sorter not initialized");
            this.mSorterInitializedListener = new SortInitializeListener();
            ShareComponentSorter.getInstance().registerOnInitializedListener(this.mSorterInitializedListener);
            ShareComponentSorter.getInstance().initialize(getActivity().getApplicationContext());
        }
        return inflate;
    }

    public void onDestroy() {
        this.mListener = null;
        this.mOnProjectClickedListener = null;
        this.mOnMishareClickedListener = null;
        ResolverAdapter resolverAdapter = this.mAdapter;
        if (resolverAdapter != null) {
            resolverAdapter.release();
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        ShareStateRouter.getInstance().removeProjectStateObserver(this);
        ShareStateRouter.getInstance().removePrintStateObserver(this);
        if (this.mSorterInitializedListener != null) {
            ShareComponentSorter.getInstance().removeOnInitializedListener(this.mSorterInitializedListener);
        }
        MiShareGalleryTransferView miShareGalleryTransferView = this.mMiShareTransferView;
        if (miShareGalleryTransferView != null) {
            miShareGalleryTransferView.unbind();
        }
    }

    public void onObservePrintState(boolean z) {
        MiShareGalleryTransferView miShareGalleryTransferView = this.mMiShareTransferView;
        if (miShareGalleryTransferView != null) {
            miShareGalleryTransferView.setMiPrintEnable(z);
        }
    }

    public void onObserveProjectState(int i) {
        MiShareGalleryTransferView miShareGalleryTransferView = this.mMiShareTransferView;
        if (miShareGalleryTransferView != null) {
            if (i == 1) {
                miShareGalleryTransferView.setScreenThrowEnable(false);
                this.mMiShareTransferView.setScreenThrowHighLight(false);
            } else if (i == 2) {
                miShareGalleryTransferView.setScreenThrowEnable(true);
                this.mMiShareTransferView.setScreenThrowHighLight(false);
            } else if (i == 3) {
                miShareGalleryTransferView.setScreenThrowHighLight(true);
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
    }

    public void onVisibilityChanged(boolean z) {
        ResolverAdapter resolverAdapter = this.mAdapter;
        if (resolverAdapter == null) {
            return;
        }
        if (z) {
            resolverAdapter.resume();
        } else {
            resolverAdapter.pause();
        }
    }

    public void requery(Intent intent) {
        this.mAdapter.requery(intent);
    }

    public void setOnIntentSelectedListener(OnIntentSelectedListener onIntentSelectedListener) {
        this.mListener = onIntentSelectedListener;
    }

    public void setOnMishareClickedListener(OnMishareClickedListener onMishareClickedListener) {
        this.mOnMishareClickedListener = onMishareClickedListener;
    }

    public void setOnProjectClickedListener(OnProjectClickedListener onProjectClickedListener) {
        this.mOnProjectClickedListener = onProjectClickedListener;
    }
}
