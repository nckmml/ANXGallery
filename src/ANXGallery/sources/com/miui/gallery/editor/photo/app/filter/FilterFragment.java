package com.miui.gallery.editor.photo.app.filter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.filter.FilterFragment;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyCheckHelper;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyDownloadStateListener;
import com.miui.gallery.editor.photo.app.menu.FilterView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.RenderFragment;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.FilterCategory;
import com.miui.gallery.editor.photo.core.common.model.FilterData;
import com.miui.gallery.editor.photo.core.imports.filter.FilterCategoryData;
import com.miui.gallery.editor.photo.core.imports.filter.FilterItem;
import com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment;
import com.miui.gallery.editor.photo.widgets.seekbar.BasicSeekBar;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.xiaomi.skytransfer.SkyTranFilter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;

public class FilterFragment extends MenuFragment<AbstractEffectFragment, SdkProvider<FilterCategory, AbstractEffectFragment>> {
    /* access modifiers changed from: private */
    public List<FilterCategory> mCategories;
    /* access modifiers changed from: private */
    public ViewPager mFilterPager;
    /* access modifiers changed from: private */
    public FilterHeadAdapter mHeaderAdapter;
    /* access modifiers changed from: private */
    public boolean mIsSkySegmentDone;
    private boolean mIsSkySegmentStart;
    /* access modifiers changed from: private */
    public boolean mIsSkySegmentSuccess;
    /* access modifiers changed from: private */
    public boolean mIsSkyToSelect;
    private SimpleRecyclerView.OnItemClickListener mItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (((FilterCategory) FilterFragment.this.mCategories.get(i)).getFilterCategory() != 7 || SkyCheckHelper.isSkyAvailable()) {
                FilterFragment.this.mFilterPager.setCurrentItem(i, true);
                FilterFragment.this.mHeaderAdapter.setSelectedItemPosition(i);
                return true;
            }
            boolean unused = FilterFragment.this.mIsSkyToSelect = true;
            SkyCheckHelper.getInstance().startDownloadWithCheck(FilterFragment.this.getActivity());
            return false;
        }
    };
    private FilterPagerAdapter mPagerAdapter;
    private SimpleRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public SeekBar mSeekBar;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                FilterFragment.this.doConfig(i);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    /* access modifiers changed from: private */
    public FilterData mSelectedItem;
    /* access modifiers changed from: private */
    public FilterCategoryData mSkyCategory;
    private SkyDownloadStateListener mSkyDownloadStateListener = new SkyDownloadStateListener() {
        private boolean mFinish = false;

        public /* synthetic */ void lambda$onFinish$112$FilterFragment$2() {
            FilterFragment.this.mSkyCategory.setDownloadState(0);
            FilterFragment.this.mHeaderAdapter.notifyItemChanged(FilterFragment.this.mSkyHeadIndex);
        }

        public void onDownloadStart(int i) {
            FilterFragment.this.mSkyCategory.setDownloadState(2);
            FilterFragment.this.mHeaderAdapter.notifyItemChanged(FilterFragment.this.mSkyHeadIndex);
        }

        public void onDownloading(int i, int i2) {
            FilterFragment.this.mSkyCategory.setDownloadPercent(((float) i2) / 100.0f);
            FilterFragment.this.mHeaderAdapter.notifyItemChanged(FilterFragment.this.mSkyHeadIndex);
        }

        public void onFinish(int i, boolean z, int i2) {
            if (!this.mFinish) {
                if (SkyCheckHelper.isSkyAvailable()) {
                    this.mFinish = true;
                    FilterFragment.this.doSkyTransferProcess();
                    if (FilterFragment.this.mIsSkyToSelect) {
                        FilterFragment.this.mFilterPager.setCurrentItem(FilterFragment.this.mSkyHeadIndex, true);
                        FilterFragment.this.mHeaderAdapter.setSelectedItemPosition(FilterFragment.this.mSkyHeadIndex);
                        boolean unused = FilterFragment.this.mIsSkyToSelect = false;
                    }
                    FilterFragment.this.mSkyCategory.setDownloadState(3);
                    FilterFragment.this.mHeaderAdapter.notifyItemChanged(FilterFragment.this.mSkyHeadIndex);
                    FilterFragment.this.mFilterPager.postDelayed(new Runnable() {
                        public final void run() {
                            FilterFragment.AnonymousClass2.this.lambda$onFinish$112$FilterFragment$2();
                        }
                    }, 1000);
                    return;
                }
                FilterFragment.this.mSkyCategory.setDownloadState(1);
                FilterFragment.this.mHeaderAdapter.notifyItemChanged(FilterFragment.this.mSkyHeadIndex);
            }
        }
    };
    /* access modifiers changed from: private */
    public int mSkyHeadIndex;
    private TextView mTitle;
    private LinearLayout mTopPanel;

    private class FilterPagerAdapter extends PagerAdapter {
        private FilterAdapter[] mFilterAdapters;
        private SimpleRecyclerView.OnItemClickListener mOnItemClickListener;

        private FilterPagerAdapter() {
            this.mFilterAdapters = new FilterAdapter[FilterFragment.this.mCategories.size()];
            this.mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
                private boolean mFirstCheckSegmentDone = true;
                private FilterItem mWaitFilterData;
                private int mWaitPosition;

                @SuppressLint({"CheckResult"})
                public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
                    ScrollLinearLayoutManager.onItemClick(recyclerView, i);
                    FilterAdapter filterAdapter = (FilterAdapter) recyclerView.getAdapter();
                    FilterItem filterItem = (FilterItem) filterAdapter.getItemData(i);
                    if (filterItem == null) {
                        Log.e("FilterFragment", "FilterAdapter get filterData null:pos is %d", (Object) Integer.valueOf(i));
                        return true;
                    }
                    if (!filterItem.equals(FilterFragment.this.mSelectedItem)) {
                        if (filterItem.isNone() && filterAdapter.isInEditMode()) {
                            filterAdapter.exitEditMode();
                            FilterFragment.this.showTopPanel(false);
                        }
                        if (!filterItem.isSkyTransfer() || filterItem.isNone()) {
                            FilterPagerAdapter.this.doSelectRender(filterItem, filterAdapter, i);
                        } else if (FilterFragment.this.mIsSkySegmentDone) {
                            FilterPagerAdapter.this.doSelectSky(filterItem, filterAdapter, i);
                        } else if (this.mFirstCheckSegmentDone) {
                            this.mFirstCheckSegmentDone = false;
                            this.mWaitFilterData = filterItem;
                            this.mWaitPosition = i;
                            FilterRenderFragment filterRenderFragment = (FilterRenderFragment) FilterFragment.this.getRenderFragment();
                            filterRenderFragment.showProgressView();
                            Observable.create($$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA.INSTANCE).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00ce: INVOKE  
                                  (wrap: io.reactivex.Observable : 0x00c5: INVOKE  (r6v4 io.reactivex.Observable) = 
                                  (wrap: io.reactivex.Observable : 0x00bd: INVOKE  (r6v3 io.reactivex.Observable) = 
                                  (wrap: io.reactivex.Observable : 0x00b3: INVOKE  (r6v2 io.reactivex.Observable) = 
                                  (wrap: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA : 0x00b1: SGET  (r6v1 com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA) =  com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA.INSTANCE com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA)
                                 io.reactivex.Observable.create(io.reactivex.ObservableOnSubscribe):io.reactivex.Observable type: STATIC)
                                  (wrap: io.reactivex.Scheduler : 0x00b9: INVOKE  (r1v13 io.reactivex.Scheduler) = 
                                  (wrap: java.util.concurrent.Executor : 0x00b7: SGET  (r1v12 java.util.concurrent.Executor) =  android.os.AsyncTask.THREAD_POOL_EXECUTOR java.util.concurrent.Executor)
                                 io.reactivex.schedulers.Schedulers.from(java.util.concurrent.Executor):io.reactivex.Scheduler type: STATIC)
                                 io.reactivex.Observable.subscribeOn(io.reactivex.Scheduler):io.reactivex.Observable type: VIRTUAL)
                                  (wrap: io.reactivex.Scheduler : 0x00c1: INVOKE  (r1v14 io.reactivex.Scheduler) =  io.reactivex.android.schedulers.AndroidSchedulers.mainThread():io.reactivex.Scheduler type: STATIC)
                                 io.reactivex.Observable.observeOn(io.reactivex.Scheduler):io.reactivex.Observable type: VIRTUAL)
                                  (wrap: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk : 0x00cb: CONSTRUCTOR  (r1v15 com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk) = 
                                  (r3v0 'this' com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter$1 A[THIS])
                                  (r5v6 'filterRenderFragment' com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment)
                                  (r4v2 'filterAdapter' com.miui.gallery.editor.photo.app.filter.FilterAdapter)
                                 call: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk.<init>(com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter$1, com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment, com.miui.gallery.editor.photo.app.filter.FilterAdapter):void type: CONSTRUCTOR)
                                 io.reactivex.Observable.subscribe(io.reactivex.functions.Consumer):io.reactivex.disposables.Disposable type: VIRTUAL in method: com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.1.OnItemClick(androidx.recyclerview.widget.RecyclerView, android.view.View, int):boolean, dex: classes.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.util.ArrayList.forEach(ArrayList.java:1257)
                                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:429)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.util.ArrayList.forEach(ArrayList.java:1257)
                                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.util.ArrayList.forEach(ArrayList.java:1257)
                                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00cb: CONSTRUCTOR  (r1v15 com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk) = 
                                  (r3v0 'this' com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter$1 A[THIS])
                                  (r5v6 'filterRenderFragment' com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment)
                                  (r4v2 'filterAdapter' com.miui.gallery.editor.photo.app.filter.FilterAdapter)
                                 call: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk.<init>(com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter$1, com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment, com.miui.gallery.editor.photo.app.filter.FilterAdapter):void type: CONSTRUCTOR in method: com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.1.OnItemClick(androidx.recyclerview.widget.RecyclerView, android.view.View, int):boolean, dex: classes.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	... 92 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	... 98 more
                                */
                            /*
                                this = this;
                                com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager.onItemClick(r4, r6)
                                androidx.recyclerview.widget.RecyclerView$Adapter r4 = r4.getAdapter()
                                com.miui.gallery.editor.photo.app.filter.FilterAdapter r4 = (com.miui.gallery.editor.photo.app.filter.FilterAdapter) r4
                                com.miui.gallery.editor.photo.core.common.model.FilterData r5 = r4.getItemData(r6)
                                com.miui.gallery.editor.photo.core.imports.filter.FilterItem r5 = (com.miui.gallery.editor.photo.core.imports.filter.FilterItem) r5
                                r0 = 1
                                if (r5 != 0) goto L_0x001e
                                java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
                                java.lang.String r5 = "FilterFragment"
                                java.lang.String r6 = "FilterAdapter get filterData null:pos is %d"
                                com.miui.gallery.util.Log.e((java.lang.String) r5, (java.lang.String) r6, (java.lang.Object) r4)
                                return r0
                            L_0x001e:
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                com.miui.gallery.editor.photo.core.common.model.FilterData r1 = r1.mSelectedItem
                                boolean r1 = r5.equals(r1)
                                r2 = 0
                                if (r1 == 0) goto L_0x0068
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r6 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r6 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                boolean r6 = r6.mSingleEffectMode
                                if (r6 != 0) goto L_0x00dc
                                boolean r6 = r4.isInEditMode()
                                if (r6 == 0) goto L_0x0049
                                r4.exitEditMode()
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                r4.showTopPanel(r2)
                                goto L_0x00dc
                            L_0x0049:
                                boolean r6 = r5.isNone()
                                if (r6 != 0) goto L_0x00dc
                                r4.enterEditMode()
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                android.widget.SeekBar r4 = r4.mSeekBar
                                int r5 = r5.progress
                                r4.setProgress(r5)
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r4 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                r4.showTopPanel(r0)
                                goto L_0x00dc
                            L_0x0068:
                                boolean r1 = r5.isNone()
                                if (r1 == 0) goto L_0x007e
                                boolean r1 = r4.isInEditMode()
                                if (r1 == 0) goto L_0x007e
                                r4.exitEditMode()
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                r1.showTopPanel(r2)
                            L_0x007e:
                                boolean r1 = r5.isSkyTransfer()
                                if (r1 == 0) goto L_0x00d7
                                boolean r1 = r5.isNone()
                                if (r1 != 0) goto L_0x00d7
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                boolean r1 = r1.mIsSkySegmentDone
                                if (r1 == 0) goto L_0x009a
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                r1.doSelectSky(r5, r4, r6)
                                goto L_0x00dc
                            L_0x009a:
                                boolean r1 = r3.mFirstCheckSegmentDone
                                if (r1 == 0) goto L_0x00d2
                                r3.mFirstCheckSegmentDone = r2
                                r3.mWaitFilterData = r5
                                r3.mWaitPosition = r6
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r5 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                com.miui.gallery.editor.photo.app.filter.FilterFragment r5 = com.miui.gallery.editor.photo.app.filter.FilterFragment.this
                                com.miui.gallery.editor.photo.core.RenderFragment r5 = r5.getRenderFragment()
                                com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment r5 = (com.miui.gallery.editor.photo.core.imports.filter.FilterRenderFragment) r5
                                r5.showProgressView()
                                com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA r6 = com.miui.gallery.editor.photo.app.filter.$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAMlNKWPeWc0CNFDEyrThqMhwA.INSTANCE
                                io.reactivex.Observable r6 = io.reactivex.Observable.create(r6)
                                java.util.concurrent.Executor r1 = android.os.AsyncTask.THREAD_POOL_EXECUTOR
                                io.reactivex.Scheduler r1 = io.reactivex.schedulers.Schedulers.from(r1)
                                io.reactivex.Observable r6 = r6.subscribeOn(r1)
                                io.reactivex.Scheduler r1 = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
                                io.reactivex.Observable r6 = r6.observeOn(r1)
                                com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk r1 = new com.miui.gallery.editor.photo.app.filter.-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk
                                r1.<init>(r3, r5, r4)
                                r6.subscribe(r1)
                                goto L_0x00dc
                            L_0x00d2:
                                r3.mWaitFilterData = r5
                                r3.mWaitPosition = r6
                                return r2
                            L_0x00d7:
                                com.miui.gallery.editor.photo.app.filter.FilterFragment$FilterPagerAdapter r1 = com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.this
                                r1.doSelectRender(r5, r4, r6)
                            L_0x00dc:
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.editor.photo.app.filter.FilterFragment.FilterPagerAdapter.AnonymousClass1.OnItemClick(androidx.recyclerview.widget.RecyclerView, android.view.View, int):boolean");
                        }

                        public /* synthetic */ void lambda$OnItemClick$116$FilterFragment$FilterPagerAdapter$1(FilterRenderFragment filterRenderFragment, FilterAdapter filterAdapter, Boolean bool) throws Exception {
                            boolean unused = FilterFragment.this.mIsSkySegmentSuccess = bool.booleanValue();
                            if (!bool.booleanValue()) {
                                filterRenderFragment.hideProgressView();
                            }
                            FilterPagerAdapter.this.doSelectSky(this.mWaitFilterData, filterAdapter, this.mWaitPosition);
                        }
                    };
                }

                private void clearOtherSelector() {
                    for (int i = 0; i < this.mFilterAdapters.length; i++) {
                        if (!(i == FilterFragment.this.mFilterPager.getCurrentItem() || this.mFilterAdapters[i] == null)) {
                            if (FilterFragment.this.mSelectedItem == null || !FilterFragment.this.mSelectedItem.isNone()) {
                                this.mFilterAdapters[i].clearSelected();
                            } else {
                                this.mFilterAdapters[i].setSelectedIndex(0);
                            }
                        }
                    }
                }

                /* access modifiers changed from: private */
                public void doSelectRender(FilterItem filterItem, FilterAdapter filterAdapter, int i) {
                    FilterFragment.this.doRender(filterItem, Integer.valueOf(filterItem.progress));
                    filterAdapter.setSelectedIndex(i);
                    FilterData unused = FilterFragment.this.mSelectedItem = filterItem;
                    if (!filterItem.isNone() && FilterFragment.this.mSingleEffectMode && !filterAdapter.isInEditMode()) {
                        filterAdapter.enterEditMode();
                        FilterFragment.this.showTopPanel(true);
                    }
                    if (filterAdapter.isInEditMode() && !filterItem.isNone()) {
                        FilterFragment.this.mSeekBar.setProgress(filterItem.progress);
                    }
                    if (FilterFragment.this.mSingleEffectMode) {
                        FilterFragment.this.setSaveEnable(!filterItem.isNone());
                    }
                    clearOtherSelector();
                }

                /* access modifiers changed from: private */
                public void doSelectSky(FilterItem filterItem, FilterAdapter filterAdapter, int i) {
                    if (FilterFragment.this.isAdded()) {
                        if (!FilterFragment.this.mIsSkySegmentSuccess) {
                            ToastUtils.makeText((Context) FilterFragment.this.getActivity(), (int) R.string.filter_sky_detect_forbidden_tips);
                        } else {
                            doSelectRender(filterItem, filterAdapter, i);
                        }
                    }
                }

                private int findSelected(List<FilterData> list) {
                    if (FilterFragment.this.mSelectedItem == null || FilterFragment.this.mSelectedItem.isNone()) {
                        return 0;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals(FilterFragment.this.mSelectedItem)) {
                            list.get(i).progress = FilterFragment.this.mSelectedItem.progress;
                            return i;
                        }
                    }
                    return -1;
                }

                private FilterAdapter getCurrentAdapter() {
                    return this.mFilterAdapters[FilterFragment.this.mFilterPager.getCurrentItem()];
                }

                public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                    viewGroup.removeView((View) obj);
                }

                public int getCount() {
                    return FilterFragment.this.mCategories.size();
                }

                public Object instantiateItem(ViewGroup viewGroup, int i) {
                    SimpleRecyclerView simpleRecyclerView = new SimpleRecyclerView(FilterFragment.this.getActivity());
                    FilterCategory filterCategory = (FilterCategory) FilterFragment.this.mCategories.get(i);
                    List<? extends FilterData> filterDatas = filterCategory.getFilterDatas();
                    ArrayList arrayList = new ArrayList(filterDatas.size());
                    arrayList.addAll(filterDatas);
                    ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(FilterFragment.this.getActivity());
                    scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(FilterFragment.this.getActivity()));
                    scrollLinearLayoutManager.setOrientation(0);
                    simpleRecyclerView.setLayoutManager(scrollLinearLayoutManager);
                    FilterAdapter filterAdapter = new FilterAdapter(arrayList, filterCategory.highlighColor, filterCategory.subHighlighColor, filterCategory.subItemSize);
                    filterAdapter.setOnItemClickListener(this.mOnItemClickListener);
                    filterAdapter.setSelectedIndex(findSelected(arrayList));
                    simpleRecyclerView.setAdapter(filterAdapter);
                    simpleRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(FilterFragment.this.getResources(), R.dimen.editor_menu_filter_item_gap, 0));
                    viewGroup.addView(simpleRecyclerView);
                    this.mFilterAdapters[i] = filterAdapter;
                    HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(simpleRecyclerView);
                    return simpleRecyclerView;
                }

                public boolean isViewFromObject(View view, Object obj) {
                    return view == obj;
                }

                public void updateProgressValue(int i) {
                    FilterAdapter currentAdapter = getCurrentAdapter();
                    if (currentAdapter != null && currentAdapter.isInEditMode()) {
                        currentAdapter.update(i);
                    }
                }
            }

            public FilterFragment() {
                super(Effect.FILTER);
            }

            /* access modifiers changed from: private */
            public void doConfig(int i) {
                this.mPagerAdapter.updateProgressValue(i);
                FilterData filterData = this.mSelectedItem;
                if (filterData != null) {
                    filterData.progress = i;
                }
                ((AbstractEffectFragment) getRenderFragment()).clear();
                ((AbstractEffectFragment) getRenderFragment()).add(this.mSelectedItem, Integer.valueOf(i));
                ((AbstractEffectFragment) getRenderFragment()).render();
            }

            /* access modifiers changed from: private */
            public void doRender(FilterData filterData, Object obj) {
                ((AbstractEffectFragment) getRenderFragment()).clear();
                ((AbstractEffectFragment) getRenderFragment()).add(filterData, obj);
                ((AbstractEffectFragment) getRenderFragment()).render();
            }

            /* access modifiers changed from: private */
            @SuppressLint({"CheckResult"})
            public void doSkyTransferProcess() {
                Log.d("FilterFragment", "doSkyTransferProcess");
                if (getPreview() == null) {
                    Log.d("FilterFragment", "doSkyTransferProcess before load preview");
                } else if (this.mIsSkySegmentStart) {
                    Log.d("FilterFragment", "SkySegmentStart ready");
                } else {
                    this.mIsSkySegmentStart = true;
                    Observable.create(new ObservableOnSubscribe() {
                        public final void subscribe(ObservableEmitter observableEmitter) {
                            FilterFragment.this.lambda$doSkyTransferProcess$113$FilterFragment(observableEmitter);
                        }
                    }).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
                        public final void accept(Object obj) {
                            FilterFragment.this.lambda$doSkyTransferProcess$114$FilterFragment((Integer) obj);
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$doSkyTransferProcess$113$FilterFragment(ObservableEmitter observableEmitter) throws Exception {
                observableEmitter.onNext(Integer.valueOf(SkyTranFilter.getInstance().segment(getPreview())));
            }

            public /* synthetic */ void lambda$doSkyTransferProcess$114$FilterFragment(Integer num) throws Exception {
                boolean z = true;
                this.mIsSkySegmentDone = true;
                if (num.intValue() == 2) {
                    z = false;
                }
                this.mIsSkySegmentSuccess = z;
                HashMap hashMap = new HashMap();
                hashMap.put("result", String.valueOf(num));
                GallerySamplingStatHelper.recordCountEvent("photo_editor", "sky_filter_result", hashMap);
            }

            public void onCreate(Bundle bundle) {
                super.onCreate(bundle);
                this.mCategories = new ArrayList();
                if (!this.mSingleEffectMode) {
                    this.mCategories.addAll(this.mSdkProvider.list());
                }
            }

            public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
                FilterView filterView = new FilterView(viewGroup.getContext());
                this.mTopPanel = (LinearLayout) filterView.findViewById(R.id.top_panel);
                this.mSeekBar = new BasicSeekBar(getActivity());
                this.mSeekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
                this.mSeekBar.setMax(100);
                this.mTopPanel.addView(this.mSeekBar, -1, -1);
                if (SkyCheckHelper.isSkyEnable()) {
                    this.mSkyCategory = new FilterCategoryData(7, GalleryApp.sGetAndroidContext().getString(R.string.filter_category_sky), GalleryApp.sGetAndroidContext().getResources().getColor(R.color.filter_category_sky));
                    this.mCategories.add(this.mSkyCategory);
                    this.mSkyHeadIndex = this.mCategories.size() - 1;
                }
                this.mHeaderAdapter = new FilterHeadAdapter(this.mCategories);
                this.mRecyclerView = (SimpleRecyclerView) filterView.findViewById(R.id.filter_type_list);
                this.mRecyclerView.setAdapter(this.mHeaderAdapter);
                this.mHeaderAdapter.setOnItemClickListener(this.mItemClickListener);
                this.mFilterPager = (ViewPager) filterView.findViewById(R.id.filter_pager);
                this.mFilterPager.setOffscreenPageLimit(1);
                this.mPagerAdapter = new FilterPagerAdapter();
                this.mFilterPager.setAdapter(this.mPagerAdapter);
                this.mFilterPager.setCurrentItem(0, false);
                this.mTitle = (TextView) filterView.findViewById(R.id.title);
                if (this.mSingleEffectMode) {
                    this.mTitle.setText(R.string.filter_single_sky);
                    this.mRecyclerView.setVisibility(4);
                } else {
                    this.mTitle.setText(R.string.photo_editor_filter);
                }
                if (SkyCheckHelper.isSkyEnable() && !SkyCheckHelper.isSkyAvailable()) {
                    this.mSkyCategory.setDownloadState(1);
                    SkyCheckHelper.getInstance().addDownloadStateListener(this.mSkyDownloadStateListener);
                    SkyCheckHelper.getInstance().startDownloadWithWifi();
                }
                return filterView;
            }

            public void onDestroy() {
                super.onDestroy();
                if (SkyCheckHelper.isSkyEnable()) {
                    SkyCheckHelper.getInstance().removeDownloadStateListener(this.mSkyDownloadStateListener);
                }
            }

            public void onViewCreated(View view, Bundle bundle) {
                super.onViewCreated(view, bundle);
                if (SkyCheckHelper.isSkyAvailable()) {
                    doSkyTransferProcess();
                }
            }

            public void setRenderFragmentInSingleMode(RenderFragment renderFragment) {
                super.setRenderFragmentInSingleMode(renderFragment);
                if (SkyCheckHelper.isSkyAvailable()) {
                    doSkyTransferProcess();
                }
            }

            public void showTopPanel(boolean z) {
                int i = 8;
                this.mTopPanel.setVisibility(z ? 0 : 8);
                SimpleRecyclerView simpleRecyclerView = this.mRecyclerView;
                if (!z) {
                    i = this.mSingleEffectMode ? 4 : 0;
                }
                simpleRecyclerView.setVisibility(i);
                if (z) {
                    ObjectAnimator objectAnimator = new ObjectAnimator();
                    objectAnimator.setTarget(this.mTopPanel);
                    objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{getResources().getDimension(R.dimen.photo_editor_filter_tab_offset), 0.0f}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f})});
                    objectAnimator.setInterpolator(new CubicEaseOutInterpolator());
                    objectAnimator.setDuration((long) getResources().getInteger(R.integer.photo_editor_filter_tab_appear_duration));
                    objectAnimator.start();
                    ObjectAnimator objectAnimator2 = new ObjectAnimator();
                    objectAnimator2.setTarget(this.mRecyclerView);
                    objectAnimator2.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f, -getResources().getDimension(R.dimen.photo_editor_filter_tab_offset)}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f, 0.0f})});
                    objectAnimator2.setInterpolator(new CubicEaseOutInterpolator());
                    objectAnimator2.setDuration((long) getResources().getInteger(R.integer.photo_editor_filter_tab_disappear_duration));
                    objectAnimator2.start();
                    return;
                }
                ObjectAnimator objectAnimator3 = new ObjectAnimator();
                objectAnimator3.setTarget(this.mRecyclerView);
                objectAnimator3.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.photo_editor_filter_tab_offset), 0.0f}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f})});
                objectAnimator3.setInterpolator(new CubicEaseOutInterpolator());
                objectAnimator3.setDuration((long) getResources().getInteger(R.integer.photo_editor_filter_tab_appear_duration));
                objectAnimator3.start();
                ObjectAnimator objectAnimator4 = new ObjectAnimator();
                objectAnimator4.setTarget(this.mTopPanel);
                objectAnimator4.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f, getResources().getDimension(R.dimen.photo_editor_filter_tab_offset)}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f, 0.0f})});
                objectAnimator4.setInterpolator(new CubicEaseOutInterpolator());
                objectAnimator4.setDuration((long) getResources().getInteger(R.integer.photo_editor_filter_tab_disappear_duration));
                objectAnimator4.start();
            }
        }
