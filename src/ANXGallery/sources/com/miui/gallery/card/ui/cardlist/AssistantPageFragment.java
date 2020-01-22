package com.miui.gallery.card.ui.cardlist;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AssistantPageHeaderAdapter;
import com.miui.gallery.adapter.HeaderFooterRecyclerAdapterWrapper;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.BaseFragment;
import com.miui.gallery.util.DividerItemDecoration;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.EmptyPage;
import com.miui.gallery.widget.LoadMoreLayout;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import java.util.List;

public class AssistantPageFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public CardAdapter mAdapter;
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            if (AssistantPageFragment.this.mAdapter.getDataItemSize() < 20) {
                AssistantPageFragment.this.loadMoreCard();
            }
        }
    };
    private AssistantPageAdapterWrapper mAdapterWrapper;
    private final CardManager.CardObserver mCardObserver = new CardManager.CardObserver() {
        public void onCardAdded(int i, Card card) {
            AssistantPageFragment.this.updateCardList();
        }

        public void onCardDeleted(int i, Card card) {
            AssistantPageFragment.this.updateCardList();
        }

        public void onCardUpdated(int i, Card card) {
            AssistantPageFragment.this.updateCard(i, card);
        }
    };
    private EmptyPage mEmptyPage;
    /* access modifiers changed from: private */
    public boolean mHasMore = true;
    private AssistantPageHeaderAdapter mHeaderGridAdapter;
    private GridView mHeaderGridView;
    private LinearLayout mHeaderLinearLayout;
    /* access modifiers changed from: private */
    public boolean mIsFirstLoad = true;
    /* access modifiers changed from: private */
    public boolean mIsLoading = false;
    private RecyclerView.LayoutManager mLayoutManager;
    /* access modifiers changed from: private */
    public LoadMoreLayout mLoadMoreLayout;
    private RecyclerView mRecyclerView;
    private final Runnable mScrollToBottomListener = new Runnable() {
        public void run() {
            AssistantPageFragment.this.loadMoreCard();
        }
    };

    private static class AssistantPageAdapterWrapper extends HeaderFooterRecyclerAdapterWrapper<CardAdapter, RecyclerView.ViewHolder> {
        public AssistantPageAdapterWrapper(CardAdapter cardAdapter) {
            super(cardAdapter);
        }

        /* access modifiers changed from: protected */
        public BaseViewHolder onCreateHeaderFooterViewHolder(View view) {
            BaseViewHolder baseViewHolder = new BaseViewHolder(view);
            baseViewHolder.setIsRecyclable(false);
            return baseViewHolder;
        }
    }

    /* access modifiers changed from: private */
    public void loadMoreCard() {
        if (!this.mIsLoading && this.mHasMore && this.mUserFirstVisible) {
            Log.d("AssistantPageFragment", "loadMoreCard");
            this.mIsLoading = true;
            this.mLoadMoreLayout.startLoad();
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<List<Card>>() {
                public List<Card> run(ThreadPool.JobContext jobContext) {
                    return CardManager.getInstance().loadMoreCard();
                }
            }, new FutureHandler<List<Card>>() {
                public void onPostExecute(Future<List<Card>> future) {
                    if (AssistantPageFragment.this.mActivity != null) {
                        List list = future.get();
                        if (list == null || list.size() < 20) {
                            AssistantPageFragment.this.mLoadMoreLayout.setLoadComplete();
                            List<Card> loadedCard = CardManager.getInstance().getLoadedCard();
                            if (loadedCard != null && loadedCard.size() > 20) {
                                AssistantPageFragment.this.mAdapter.setFooter(AssistantPageFragment.this.mLoadMoreLayout, true);
                            }
                            boolean unused = AssistantPageFragment.this.mHasMore = false;
                        } else if (AssistantPageFragment.this.mIsFirstLoad) {
                            AssistantPageFragment.this.mAdapter.setFooter(AssistantPageFragment.this.mLoadMoreLayout, true);
                        }
                        AssistantPageFragment.this.updateCardList();
                        boolean unused2 = AssistantPageFragment.this.mIsLoading = false;
                        boolean unused3 = AssistantPageFragment.this.mIsFirstLoad = false;
                    }
                }
            });
        }
    }

    private void postOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            ThreadManager.getMainHandler().post(runnable);
        }
    }

    /* access modifiers changed from: private */
    public void updateCard(final int i, Card card) {
        if (i >= 0) {
            postOnUiThread(new Runnable() {
                public void run() {
                    AssistantPageFragment.this.mAdapter.refreshNotifyItemChanged(i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateCardList() {
        postOnUiThread(new Runnable() {
            public void run() {
                AssistantPageFragment.this.mAdapter.updateDataList(CardManager.getInstance().getLoadedCard());
            }
        });
    }

    public String getPageName() {
        return "assistant";
    }

    public void onDestroy() {
        super.onDestroy();
        CardManager.getInstance().unregisterObserver(this.mCardObserver);
        CardAdapter cardAdapter = this.mAdapter;
        if (cardAdapter != null) {
            cardAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        }
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.assistant_page, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.mLayoutManager = new LinearLayoutManager(getActivity());
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.card_divider_no_stroke), 1, 1));
        this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.mHeaderLinearLayout = (LinearLayout) LayoutInflater.from(this.mActivity).inflate(R.layout.assistant_page_header_grid, this.mRecyclerView, false);
        this.mHeaderGridView = (GridView) this.mHeaderLinearLayout.findViewById(R.id.album_page_header_grid_view);
        this.mHeaderGridAdapter = new AssistantPageHeaderAdapter(this.mActivity);
        this.mHeaderGridView.setAdapter(this.mHeaderGridAdapter);
        MiscUtil.setRecyclerViewScrollToBottomListener(this.mRecyclerView, this.mScrollToBottomListener);
        this.mAdapter = new CardAdapter(getActivity());
        this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
        this.mLoadMoreLayout = (LoadMoreLayout) layoutInflater.inflate(R.layout.load_more_layout, this.mRecyclerView, false);
        this.mEmptyPage = (EmptyPage) layoutInflater.inflate(R.layout.cardlist_page_empty_view, this.mRecyclerView, false);
        this.mEmptyPage.setActionButtonVisible(false);
        this.mEmptyPage.setDescription(GalleryPreferences.Assistant.hasCardEver() ? R.string.empty_description_no_cards : R.string.empty_description_conditions);
        this.mAdapter.setEmptyView(this.mEmptyPage);
        this.mAdapterWrapper = new AssistantPageAdapterWrapper(this.mAdapter);
        this.mAdapterWrapper.addHeaderView(this.mHeaderLinearLayout);
        this.mRecyclerView.setAdapter(this.mAdapterWrapper);
        CardManager.getInstance().registerObserver(this.mCardObserver);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mHeaderGridAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void onUserFirstVisible() {
        Log.d("AssistantPageFragment", "onUserFirstVisible");
        loadMoreCard();
    }

    /* access modifiers changed from: protected */
    public boolean recordPageByDefault() {
        return false;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            this.mHeaderGridAdapter.notifyDataSetChanged();
            GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_tab_page_view");
        }
    }
}
