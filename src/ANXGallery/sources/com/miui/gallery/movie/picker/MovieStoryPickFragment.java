package com.miui.gallery.movie.picker;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.picker.PickerFragment;
import com.miui.gallery.picker.helper.PickableBaseTimeLineAdapterWrapper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.widget.EmptyPage;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieStoryPickFragment extends PickerFragment {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public PickableBaseTimeLineAdapterWrapper mAdapter;
    private long mCardId;
    private EmptyPage mEmptyView;
    private StickyGridHeadersGridView mGridView;
    private Intent mIntent;
    private LoaderManager.LoaderCallbacks mLoaderCallbacks = new LoaderManager.LoaderCallbacks() {
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(MovieStoryPickFragment.this.mActivity);
            cursorLoader.setUri(MovieStoryPickFragment.this.getUri());
            cursorLoader.setProjection(MovieStoryPickFragment.this.getProjection());
            cursorLoader.setSelection(MovieStoryPickFragment.this.getSelection());
            cursorLoader.setSortOrder(MovieStoryPickFragment.this.getOrder());
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            MovieStoryPickFragment.this.mAdapter.swapCursor((Cursor) obj);
        }

        public void onLoaderReset(Loader loader) {
        }
    };
    private StoryMoviePickAdapter mStoryMoviePickAdapter;

    /* access modifiers changed from: private */
    public String getOrder() {
        return "alias_create_time DESC ";
    }

    /* access modifiers changed from: private */
    public String[] getProjection() {
        return StoryMoviePickAdapter.PROJECTION;
    }

    private boolean parseIntent() {
        Intent intent = this.mIntent;
        if (intent == null) {
            Log.d("StoryMoviePickFragment", "parseIntent is fail. ");
            this.mActivity.finish();
            return false;
        }
        this.mCardId = intent.getLongExtra("card_id", 0);
        ArrayList<String> stringArrayListExtra = this.mIntent.getStringArrayListExtra("pick_sha1");
        if (MiscUtil.isValid(stringArrayListExtra)) {
            Iterator<String> it = stringArrayListExtra.iterator();
            while (it.hasNext()) {
                this.mPicker.pick(it.next());
            }
        }
        Log.d("StoryMoviePickFragment", "parseIntent is success. ");
        return true;
    }

    public String getPageName() {
        return "story_picker_home";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        Card cardByCardId = CardManager.getInstance().getCardByCardId(this.mCardId);
        if (cardByCardId == null) {
            return "";
        }
        return String.format("%s IN ('%s')", new Object[]{"sha1", TextUtils.join("','", cardByCardId.getSelectedMediaSha1s())});
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build();
    }

    public /* synthetic */ void lambda$onInflateView$2$MovieStoryPickFragment(AdapterView adapterView, View view, int i, long j) {
        int i2 = i;
        Cursor cursor = (Cursor) this.mStoryMoviePickAdapter.getItem(i2);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(ItemViewInfo.getImageInfo(view, 0));
        ImageLoadParams imageLoadParams = new ImageLoadParams(this.mStoryMoviePickAdapter.getItemKey(i2), this.mStoryMoviePickAdapter.getLocalPath(i2), Config.ThumbConfig.get().sMicroTargetSize, (RectF) null, 0, this.mStoryMoviePickAdapter.getMimeType(i2), isPreviewFace(), this.mStoryMoviePickAdapter.getFileLength(i2));
        IntentUtil.gotoPhotoPageFromPicker(this.mActivity, getPreviewUri(), getPreviewSelection(cursor), getPreviewSelectionArgs(cursor), getPreviewOrder(), imageLoadParams, arrayList, !supportFoldBurstItems());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (parseIntent()) {
            getLoaderManager().initLoader(17, (Bundle) null, this.mLoaderCallbacks);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        this.mIntent = ((MovieStoryPickActivity) this.mActivity).getResultIntent();
    }

    public void onDestroy() {
        PickableBaseTimeLineAdapterWrapper pickableBaseTimeLineAdapterWrapper = this.mAdapter;
        if (pickableBaseTimeLineAdapterWrapper != null) {
            pickableBaseTimeLineAdapterWrapper.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.story_movie_pick_fragment, viewGroup, false);
        this.mGridView = (StickyGridHeadersGridView) inflate.findViewById(R.id.grid_view);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                MovieStoryPickFragment.this.lambda$onInflateView$2$MovieStoryPickFragment(adapterView, view, i, j);
            }
        });
        this.mStoryMoviePickAdapter = new StoryMoviePickAdapter((Context) this.mActivity, this.mPicker);
        this.mAdapter = new PickableBaseTimeLineAdapterWrapper(this.mPicker, this.mStoryMoviePickAdapter);
        this.mGridView.setAdapter((ListAdapter) this.mAdapter);
        this.mEmptyView = (EmptyPage) inflate.findViewById(16908292);
        this.mGridView.setEmptyView(this.mEmptyView);
        this.mEmptyView.setVisibility(8);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public boolean onPhotoItemClick(Cursor cursor) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean supportFoldBurstItems() {
        return false;
    }
}
