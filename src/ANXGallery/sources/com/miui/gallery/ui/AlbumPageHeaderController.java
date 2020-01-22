package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.Lists;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumPageHeaderAdapter;
import com.miui.gallery.app.Fragment;
import com.miui.gallery.model.FaceAlbumCover;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.SearchStatusLoader;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.query.QueryLoader;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.resultpage.DataListResultProcessor;
import com.miui.gallery.search.resultpage.DataListSourceResult;
import com.miui.gallery.threadpool.GallerySchedulers;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.face.FaceRegionRectF;
import com.miui.gallery.util.face.PeopleItem;
import com.miui.gallery.widget.recyclerview.GridItemSpacingDecoration;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlbumPageHeaderController implements LoaderManager.LoaderCallbacks, DefaultLifecycleObserver {
    private AlbumPageHeaderAdapter mAdapter;
    private int mAlbumCoverNum;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Fragment mFragment;
    private SparseBooleanArray mIsManualLoad = new SparseBooleanArray();
    private boolean mIsPeopleAlbumSnapshotValid = false;
    private SparseLongArray mLoaderCreateTime = new SparseLongArray();
    private ArrayList<Long> mLocationsAlbumCoverServerIds;
    private PublishSubject<ArrayList<Long>> mLocationsSubject;
    private RecyclerView mRecyclerView;
    private int mSearchStatus = -1;
    private ArrayList<Long> mTagsAlbumCoverServerIds;
    private PublishSubject<ArrayList<Long>> mTagsSubject;

    public AlbumPageHeaderController(Fragment fragment, ViewGroup viewGroup) {
        this.mFragment = fragment;
        this.mFragment.getLifecycle().addObserver(this);
        Resources resources = this.mFragment.getResources();
        this.mRecyclerView = (RecyclerView) LayoutInflater.from(this.mFragment.getActivity()).inflate(R.layout.album_page_header_recycler, viewGroup, false);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mFragment.getActivity(), resources.getInteger(R.integer.album_page_header_columns)));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.message_bar_vertical_margin);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.addItemDecoration(new GridItemSpacingDecoration(recyclerView, false, dimensionPixelSize, 0));
        this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.mAdapter = new AlbumPageHeaderAdapter(this.mFragment.getActivity());
        this.mAdapter.setHasStableIds(true);
        this.mAlbumCoverNum = this.mFragment.getResources().getInteger(R.integer.album_page_header_cover_num);
        this.mLocationsAlbumCoverServerIds = new ArrayList<>();
        this.mTagsAlbumCoverServerIds = new ArrayList<>();
        this.mLocationsSubject = PublishSubject.create();
        this.mTagsSubject = PublishSubject.create();
        subscribeSubject(this.mLocationsSubject, -3);
        subscribeSubject(this.mTagsSubject, -4);
        setSearchBackedAlbumCoversByPreferences();
        this.mRecyclerView.setAdapter(this.mAdapter);
        getLoaderManager().initLoader(-1, (Bundle) null, this);
    }

    private QueryLoader buildQueryLoaderByType(SearchConstants.SectionType sectionType) {
        QueryInfo.Builder builder = new QueryInfo.Builder(SearchConstants.SearchType.SEARCH_TYPE_RESULT_LIST);
        builder.addParam(nexExportFormat.TAG_FORMAT_TYPE, sectionType.getName());
        builder.addParam("pos", MovieStatUtils.DOWNLOAD_SUCCESS);
        builder.addParam("num", String.valueOf(this.mAlbumCoverNum));
        builder.addParam("secureMode", String.valueOf(true));
        builder.addParam("use_persistent_response", String.valueOf(true));
        return new QueryLoader(this.mFragment.getActivity(), builder.build(), new DataListResultProcessor());
    }

    private LoaderManager getLoaderManager() {
        return this.mFragment.getLoaderManager();
    }

    private boolean isAlbumCoverValid(Context context, long j) {
        Boolean bool = (Boolean) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, new String[]{"count(*)"}, "serverId = ? AND ((localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))) AND ((localGroupId!=-1000))", new String[]{String.valueOf(j)}, (String) null, $$Lambda$AlbumPageHeaderController$0Nm4CXZjLxeRBnOv1blumX5M.INSTANCE);
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ Boolean lambda$isAlbumCoverValid$36(Cursor cursor) {
        boolean z = false;
        if (cursor != null && cursor.moveToFirst() && cursor.getInt(0) > 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    static /* synthetic */ boolean lambda$null$38(Boolean bool) throws Exception {
        return !bool.booleanValue();
    }

    static /* synthetic */ Boolean lambda$null$40(Boolean bool, Boolean bool2) throws Exception {
        return Boolean.valueOf(bool.booleanValue() && bool2.booleanValue());
    }

    private String loaderId2Name(int i) {
        return i != -5 ? i != -4 ? i != -3 ? i != -2 ? i != -1 ? String.valueOf(i) : "people_album_cover_snapshot" : "people_album_cover" : "locations_album_cover" : "tags_album_cover" : "search_status";
    }

    private long parseAlbumCoverServerId(String str) {
        try {
            return Long.parseLong(Uri.parse(str).getQueryParameter("serverId"));
        } catch (Exception unused) {
            Log.e("AlbumPageHeaderController", "Invalid album cover Uri: %s", (Object) str);
            return -1;
        }
    }

    private ArrayList<FaceAlbumCover> parsePeopleCoversFromSnapshot(Cursor cursor) {
        if (cursor == null || cursor.isClosed() || cursor.getCount() <= 0) {
            return null;
        }
        ArrayList<FaceAlbumCover> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PeopleItem fromCursor = PeopleItem.fromCursor(cursor);
            if (fromCursor != null) {
                FaceAlbumCover faceAlbumCover = new FaceAlbumCover();
                faceAlbumCover.coverId = fromCursor.getCloudId();
                faceAlbumCover.coverPath = !TextUtils.isEmpty(fromCursor.getThumbFile()) ? fromCursor.getThumbFile() : !TextUtils.isEmpty(fromCursor.getLocalFile()) ? fromCursor.getLocalFile() : fromCursor.getMicroThumbFile();
                faceAlbumCover.coverSha1 = fromCursor.getSha1();
                faceAlbumCover.coverSyncState = 0;
                faceAlbumCover.coverSize = fromCursor.getSize();
                faceAlbumCover.faceRectF = new FaceRegionRectF(fromCursor.getFaceXScale(), fromCursor.getFaceYScale(), fromCursor.getFaceXScale() + fromCursor.getFaceWScale(), fromCursor.getFaceYScale() + fromCursor.getFaceHScale(), fromCursor.getExifOrientation());
                arrayList.add(faceAlbumCover);
            }
            cursor.moveToNext();
        }
        return arrayList;
    }

    private void setSearchBackedAlbumCoversByPreferences() {
        List<String> locationsAlbumServerIds = GalleryPreferences.SearchBackedAlbum.getLocationsAlbumServerIds();
        if (MiscUtil.isValid(locationsAlbumServerIds)) {
            this.mAdapter.setAlbumCover(1, Lists.newArrayList(locationsAlbumServerIds));
        }
        List<String> tagsAlbumServerIds = GalleryPreferences.SearchBackedAlbum.getTagsAlbumServerIds();
        if (MiscUtil.isValid(tagsAlbumServerIds)) {
            this.mAdapter.setAlbumCover(2, Lists.newArrayList(tagsAlbumServerIds));
        }
    }

    private void startDeferredLoad() {
        getLoaderManager().initLoader(-2, (Bundle) null, this);
        getLoaderManager().initLoader(-3, (Bundle) null, this);
        getLoaderManager().initLoader(-4, (Bundle) null, this);
        getLoaderManager().initLoader(-5, (Bundle) null, this);
        getLoaderManager().destroyLoader(-1);
    }

    private void statAlbumLoadTime(String str, long j, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("loader", str);
        hashMap.put("costs", String.valueOf(j));
        hashMap.put("count", String.valueOf(i));
        GallerySamplingStatHelper.recordCountEvent("album_page_header", "classify_album_cover_load_time", hashMap);
    }

    private void subscribeSubject(PublishSubject<ArrayList<Long>> publishSubject, int i) {
        this.mCompositeDisposable.add(publishSubject.observeOn(GallerySchedulers.misc()).switchMap(new Function(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return AlbumPageHeaderController.this.lambda$subscribeSubject$41$AlbumPageHeaderController(this.f$1, (ArrayList) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                AlbumPageHeaderController.this.lambda$subscribeSubject$42$AlbumPageHeaderController(this.f$1, (Boolean) obj);
            }
        }));
    }

    private void takeSnapshot4PeopleAlbum() {
        Completable.defer($$Lambda$AlbumPageHeaderController$sObEeRl8NQa8h9Kl2nOLHkZOZA.INSTANCE).delaySubscription(1500, TimeUnit.MILLISECONDS, GallerySchedulers.misc()).subscribe();
    }

    private void updateSearchStatus(int i) {
        int i2 = this.mSearchStatus;
        if (i != i2) {
            if (SearchConstants.isErrorStatus(i2) && !SearchConstants.isErrorStatus(i)) {
                getLoaderManager().restartLoader(-3, (Bundle) null, this);
                getLoaderManager().restartLoader(-4, (Bundle) null, this);
            }
            this.mSearchStatus = i;
        }
    }

    public View getView() {
        return this.mRecyclerView;
    }

    public /* synthetic */ Boolean lambda$null$37$AlbumPageHeaderController(Long l) throws Exception {
        return Boolean.valueOf(l != null && l.longValue() > 0 && isAlbumCoverValid(GalleryApp.sGetAndroidContext(), l.longValue()));
    }

    public /* synthetic */ ObservableSource lambda$subscribeSubject$41$AlbumPageHeaderController(int i, ArrayList arrayList) throws Exception {
        return Flowable.fromIterable(arrayList).map(new Function() {
            public final Object apply(Object obj) {
                return AlbumPageHeaderController.this.lambda$null$37$AlbumPageHeaderController((Long) obj);
            }
        }).takeUntil($$Lambda$AlbumPageHeaderController$_RSrnJ_oaChU5DdzrX3HGt9IVLI.INSTANCE).doOnCancel(new Action(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                Log.d("AlbumPageHeaderController", "doOnCancel for [%d]", (Object) Integer.valueOf(this.f$0));
            }
        }).reduce($$Lambda$AlbumPageHeaderController$oKjDg6ZdkrYrHksdgAHcwhVpI6g.INSTANCE).toObservable();
    }

    public /* synthetic */ void lambda$subscribeSubject$42$AlbumPageHeaderController(int i, Boolean bool) throws Exception {
        Log.d("AlbumPageHeaderController", "loader [%d] need restart [%b]", Integer.valueOf(i), Boolean.valueOf(!bool.booleanValue()));
        if (!bool.booleanValue()) {
            getLoaderManager().restartLoader(i, (Bundle) null, this);
        }
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        this.mIsManualLoad.put(i, true);
        this.mLoaderCreateTime.put(i, System.currentTimeMillis());
        if (i == -5) {
            return new SearchStatusLoader(this.mFragment.getActivity(), (SearchStatusLoader.StatusReportDelegate) null);
        }
        if (i == -4) {
            return buildQueryLoaderByType(SearchConstants.SectionType.SECTION_TYPE_TAG_LIST);
        }
        if (i == -3) {
            return buildQueryLoaderByType(SearchConstants.SectionType.SECTION_TYPE_LOCATION_LIST);
        }
        if (i == -2) {
            CursorLoader cursorLoader = new CursorLoader(this.mFragment.getActivity());
            cursorLoader.setUri(GalleryContract.PeopleFace.PEOPLE_FACE_COVER_URI);
            return cursorLoader;
        } else if (i != -1) {
            return null;
        } else {
            CursorLoader cursorLoader2 = new CursorLoader(this.mFragment.getActivity());
            CursorLoader cursorLoader3 = cursorLoader2;
            cursorLoader3.setUri(UriUtil.appendLimit(GalleryContract.PeopleFace.PEOPLE_SNAPSHOT_URI, this.mAlbumCoverNum));
            cursorLoader3.setProjection(PeopleItem.COMPAT_PROJECTION);
            return cursorLoader2;
        }
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        this.mFragment.getLifecycle().removeObserver(this);
        this.mCompositeDisposable.dispose();
    }

    public void onLoadFinished(Loader loader, Object obj) {
        int id = loader.getId();
        long currentTimeMillis = System.currentTimeMillis();
        int id2 = loader.getId();
        if (id2 != -5) {
            ArrayList arrayList = null;
            if (id2 == -4) {
                if (obj instanceof DataListSourceResult) {
                    this.mTagsAlbumCoverServerIds.clear();
                    SuggestionCursor data = ((DataListSourceResult) obj).getData();
                    if (data != null && data.getCount() > 0) {
                        arrayList = new ArrayList();
                        data.moveToFirst();
                        while (!data.isAfterLast()) {
                            String suggestionIcon = data.getSuggestionIcon();
                            arrayList.add(suggestionIcon);
                            this.mTagsAlbumCoverServerIds.add(Long.valueOf(parseAlbumCoverServerId(suggestionIcon)));
                            data.moveToNext();
                        }
                    }
                }
                this.mAdapter.setAlbumCover(2, arrayList);
                GalleryPreferences.SearchBackedAlbum.setTagsAlbumServerIds(arrayList);
            } else if (id2 == -3) {
                if (obj instanceof DataListSourceResult) {
                    this.mLocationsAlbumCoverServerIds.clear();
                    SuggestionCursor data2 = ((DataListSourceResult) obj).getData();
                    if (data2 != null && data2.getCount() > 0) {
                        arrayList = new ArrayList();
                        data2.moveToFirst();
                        while (!data2.isAfterLast()) {
                            String suggestionIcon2 = data2.getSuggestionIcon();
                            arrayList.add(suggestionIcon2);
                            this.mLocationsAlbumCoverServerIds.add(Long.valueOf(parseAlbumCoverServerId(suggestionIcon2)));
                            data2.moveToNext();
                        }
                    }
                }
                this.mAdapter.setAlbumCover(1, arrayList);
                GalleryPreferences.SearchBackedAlbum.setLocationsAlbumServerIds(arrayList);
            } else if (id2 == -2) {
                Cursor cursor = obj instanceof Cursor ? (Cursor) obj : null;
                if (!(cursor == null || cursor.getExtras() == null)) {
                    arrayList = cursor.getExtras().getParcelableArrayList("face_album_cover");
                }
                this.mAdapter.setAlbumCover(0, arrayList);
                if (MiscUtil.isValid(arrayList) && !this.mIsPeopleAlbumSnapshotValid) {
                    this.mIsPeopleAlbumSnapshotValid = true;
                    takeSnapshot4PeopleAlbum();
                }
            } else if (id2 == -1) {
                if (obj instanceof Cursor) {
                    try {
                        ArrayList<FaceAlbumCover> parsePeopleCoversFromSnapshot = parsePeopleCoversFromSnapshot((Cursor) obj);
                        if (MiscUtil.isValid(parsePeopleCoversFromSnapshot)) {
                            this.mAdapter.setAlbumCover(0, parsePeopleCoversFromSnapshot);
                            this.mIsPeopleAlbumSnapshotValid = true;
                        }
                    } finally {
                        MiscUtil.closeSilently((Cursor) obj);
                    }
                }
                startDeferredLoad();
            }
        } else if (obj instanceof Integer) {
            updateSearchStatus(((Integer) obj).intValue());
        }
        if (this.mIsManualLoad.get(id)) {
            this.mIsManualLoad.put(id, false);
            long j = currentTimeMillis - this.mLoaderCreateTime.get(id);
            Log.d("AlbumPageHeaderController", "load [%d] items for [%s] costs [%d ms]", Integer.valueOf(this.mAlbumCoverNum), loaderId2Name(id), Long.valueOf(j));
            statAlbumLoadTime(loaderId2Name(id), j, this.mAlbumCoverNum);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public void onResume(LifecycleOwner lifecycleOwner) {
        if (MiscUtil.isValid(this.mLocationsAlbumCoverServerIds)) {
            this.mLocationsSubject.onNext(new ArrayList(this.mLocationsAlbumCoverServerIds));
        }
        if (MiscUtil.isValid(this.mTagsAlbumCoverServerIds)) {
            this.mTagsSubject.onNext(new ArrayList(this.mTagsAlbumCoverServerIds));
        }
    }
}
