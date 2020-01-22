package com.miui.gallery.ui;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AutoBackupChooserAdapter;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CloudGuideAutoBackupFragment extends ListFragment implements AutoBackupChooserAdapter.OnAutoBackupStateChangedListener {
    /* access modifiers changed from: private */
    public AutoBackupChooserAdapter mAdapter;
    /* access modifiers changed from: private */
    public GalleryIntent.CloudGuideSource mSource;

    private class AlbumListLoaderCallback implements LoaderManager.LoaderCallbacks {
        private AlbumListLoaderCallback() {
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(CloudGuideAutoBackupFragment.this.getActivity());
            if (i == 1) {
                cursorLoader.setUri(GalleryContract.Album.URI);
                cursorLoader.setProjection(CloudGuideAutoBackupFragment.this.mAdapter.getProjection());
                cursorLoader.setSelection("immutable = 0 AND attributes & 16 = 0 AND _id < 2147383647");
                cursorLoader.setSortOrder("sortBy ASC ");
            } else if (i == 2) {
                cursorLoader.setUri(GalleryContract.Album.URI_SHARE_ALL);
                cursorLoader.setProjection(AlbumConstants.SHARED_ALBUM_PROJECTION);
                cursorLoader.setSelection("count > 0");
            }
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            if (loader.getId() == 1) {
                CloudGuideAutoBackupFragment.this.mAdapter.swapCursor((Cursor) obj);
            } else if (loader.getId() == 2) {
                CloudGuideAutoBackupFragment.this.mAdapter.setSharedAlbums((Cursor) obj);
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private void changeAutoUpload(long j, boolean z) {
        MediaAndAlbumOperations.doChangeAutoUpload(getActivity(), j, z);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        AlbumListLoaderCallback albumListLoaderCallback = new AlbumListLoaderCallback();
        getLoaderManager().initLoader(1, (Bundle) null, albumListLoaderCallback);
        getLoaderManager().initLoader(2, (Bundle) null, albumListLoaderCallback);
    }

    public void onAutoBackupStateChanged(int i, boolean z) {
        changeAutoUpload(this.mAdapter.getItemId(i), z);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getActivity().getIntent();
        long longExtra = intent.getLongExtra("autobackup_album_id", -1);
        this.mSource = (GalleryIntent.CloudGuideSource) intent.getSerializableExtra("cloud_guide_source");
        if (longExtra != -1) {
            changeAutoUpload(longExtra, true);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cloud_guide_auto_backup, viewGroup, false);
        inflate.findViewById(R.id.enable_service_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SyncUtil.existXiaomiAccount(CloudGuideAutoBackupFragment.this.getActivity())) {
                    AccountManager accountManager = AccountManager.get(CloudGuideAutoBackupFragment.this.getActivity());
                    Bundle bundle = new Bundle();
                    if (CloudGuideAutoBackupFragment.this.mSource != null) {
                        bundle.putString("stat_key_source", CloudGuideAutoBackupFragment.this.mSource.name());
                    }
                    accountManager.addAccount("com.xiaomi", (String) null, (String[]) null, bundle, CloudGuideAutoBackupFragment.this.getActivity(), new AccountManagerCallback<Bundle>() {
                        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                            if (CloudGuideAutoBackupFragment.this.getActivity() != null) {
                                CloudGuideAutoBackupFragment.this.getActivity().setResult(SyncUtil.existXiaomiAccount(CloudGuideAutoBackupFragment.this.getActivity()) ? -1 : 1);
                                CloudGuideAutoBackupFragment.this.getActivity().finish();
                            }
                        }
                    }, (Handler) null);
                } else if (SyncUtil.setSyncAutomatically(CloudGuideAutoBackupFragment.this.getActivity(), true)) {
                    CloudGuideAutoBackupFragment.this.getActivity().setResult(-1);
                    CloudGuideAutoBackupFragment.this.getActivity().finish();
                }
            }
        });
        this.mAdapter = new AutoBackupChooserAdapter(getActivity(), this);
        setListAdapter(this.mAdapter);
        return inflate;
    }

    public void onDestroy() {
        AutoBackupChooserAdapter autoBackupChooserAdapter = this.mAdapter;
        if (autoBackupChooserAdapter != null) {
            autoBackupChooserAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        if (!this.mAdapter.isAutoUploadedAlbum(i)) {
            this.mAdapter.reverseAutoBackupUiState(view, i);
            onAutoBackupStateChanged(i, this.mAdapter.getAutoBackupUiState(view));
        } else if (this.mAdapter.isCameraAlbum(i)) {
            ToastUtils.makeText((Context) getActivity(), (int) R.string.camera_needs_auto_backup_tip);
        } else if (this.mAdapter.isBabyAlbum(i)) {
            ToastUtils.makeText((Context) getActivity(), (int) R.string.baby_album_needs_auto_backup_tip);
        } else if (this.mAdapter.isOwnerShareAlbum(i)) {
            ToastUtils.makeText((Context) getActivity(), (int) R.string.share_album_needs_auto_upload_tip);
        } else {
            this.mAdapter.reverseAutoBackupUiState(view, i);
            onAutoBackupStateChanged(i, this.mAdapter.getAutoBackupUiState(view));
        }
    }

    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().pause();
        GallerySamplingStatHelper.recordPageEnd(getActivity(), "cloud_guide_autobackup");
    }

    public void onResume() {
        super.onResume();
        ImageLoader.getInstance().resume();
        GallerySamplingStatHelper.recordPageStart(getActivity(), "cloud_guide_autobackup");
    }
}
