package com.miui.gallery.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
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
import android.view.WindowManager;
import com.miui.account.AccountHelper;
import com.miui.gallery.R;
import com.miui.gallery.activity.facebaby.BabyAlbumDetailActivity;
import com.miui.gallery.activity.facebaby.FacePageActivity;
import com.miui.gallery.adapter.AlbumPageAdapter;
import com.miui.gallery.loader.AlbumConvertCallback;
import com.miui.gallery.loader.AlbumSnapshotLoader;
import com.miui.gallery.loader.AsyncContentLoader;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.AlbumConstants;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.AlbumsCursorHelper;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ShareAlbumsCursorHelper;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.List;
import miui.app.ProgressDialog;

public class JumpDialogFragment extends GalleryDialogFragment {
    private Runnable mDelayVisibleRunnable = new Runnable() {
        public void run() {
            if (JumpDialogFragment.this.getActivity() != null) {
                JumpDialogFragment jumpDialogFragment = JumpDialogFragment.this;
                jumpDialogFragment.setDialogAlpha(jumpDialogFragment.getDialog(), 1.0f);
            }
        }
    };
    private HandleIntentCallback mHandleIntentCallback = new HandleIntentCallback() {
        public void onHandleFailed(final Context context, final String str) {
            ThreadManager.getMainHandler().post(new Runnable() {
                public void run() {
                    if (JumpDialogFragment.this.getActivity() != null) {
                        if (JumpDialogFragment.this.mObtainIntentCallBack != null) {
                            JumpDialogFragment.this.mObtainIntentCallBack.onHandleFailed(context, str);
                        } else if (!TextUtils.isEmpty(str)) {
                            ToastUtils.makeText((Context) JumpDialogFragment.this.getActivity(), (CharSequence) str);
                        }
                        JumpDialogFragment.this.dismissAllowingStateLoss();
                    }
                }
            });
        }

        public void onHandleIntent(final Intent intent) {
            ThreadManager.getMainHandler().post(new Runnable() {
                public void run() {
                    if (JumpDialogFragment.this.getActivity() != null) {
                        if (JumpDialogFragment.this.mObtainIntentCallBack != null) {
                            JumpDialogFragment.this.mObtainIntentCallBack.onHandleIntent(intent);
                        } else if (intent != null) {
                            JumpDialogFragment.this.startActivity(intent);
                        }
                        JumpDialogFragment.this.dismissAllowingStateLoss();
                    }
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public HandleIntentCallback mObtainIntentCallBack;

    private class AlbumJumpHelper implements LoaderManager.LoaderCallbacks {
        private Album mAlbum;
        private long mAlbumLoadTime;
        private Fragment mFragment;
        private HandleIntentCallback mHandleIntentCallback;
        private Cursor mShareAlbumCursor;
        private ShareAlbumsCursorHelper mShareAlbumsCursorHelper;
        private Uri mUri;
        private Bundle mUriParameters;

        public AlbumJumpHelper(Fragment fragment) {
            this.mFragment = fragment;
        }

        private Intent createJumpIntent() {
            Intent intent;
            Album album = this.mAlbum;
            if (album == null) {
                return null;
            }
            long albumId = album.getAlbumId();
            Intent shortCutIntent = getShortCutIntent(albumId, this.mAlbum.getLocalPath());
            if (shortCutIntent != null) {
                return shortCutIntent;
            }
            long attributes = this.mAlbum.getAttributes();
            String serverId = this.mAlbum.getServerId();
            String localizedAlbumName = this.mAlbum.getLocalizedAlbumName();
            String localPath = this.mAlbum.getLocalPath();
            if (!TextUtils.isEmpty(this.mAlbum.getBabyInfo())) {
                intent = new Intent(this.mFragment.getActivity(), BabyAlbumDetailActivity.class);
                intent.putExtra("people_id", this.mAlbum.getPeopleId());
                intent.putExtra("baby_info", this.mAlbum.getBabyInfo());
                intent.putExtra("thumbnail_info_of_baby", this.mAlbum.getThumbnailInfoOfBaby());
                intent.putExtra("baby_sharer_info", this.mAlbum.getBabyShareInfo());
            } else {
                intent = new Intent("com.miui.gallery.action.VIEW_ALBUM_DETAIL");
            }
            boolean equals = String.valueOf(2).equals(serverId);
            boolean isOtherShareAlbumId = ShareAlbumManager.isOtherShareAlbumId(this.mAlbum.getAlbumId());
            boolean isOwnerShareAlbum = this.mShareAlbumsCursorHelper.isOwnerShareAlbum(albumId);
            intent.putExtra("other_share_album", isOtherShareAlbumId);
            intent.putExtra("owner_share_album", isOwnerShareAlbum);
            intent.putExtra("is_local_album", !AlbumPageAdapter.isAutoUploadedAlbum(this.mAlbum));
            intent.putExtra("screenshot_album", equals);
            intent.putExtra("pano_album", AlbumsCursorHelper.isPanoAlbum(albumId));
            intent.putExtra("album_id", albumId);
            intent.putExtra("album_name", localizedAlbumName);
            intent.putExtra("album_unwriteable", this.mAlbum.isImmutable());
            if (equals) {
                String queryParameter = this.mUri.getQueryParameter("screenshotAppName");
                if (!TextUtils.isEmpty(queryParameter)) {
                    intent.putExtra("screenshot_app_name", queryParameter);
                    intent.putExtra("album_name", queryParameter);
                    intent.putExtra("album_unwriteable", true);
                }
            }
            intent.putExtra("album_server_id", serverId);
            intent.putExtra("attributes", attributes);
            intent.putExtra("album_local_path", localPath);
            return intent;
        }

        private String generateAlbumSelection(Bundle bundle) {
            if (bundle.getLong("id", -1) >= 0) {
                return String.format("%s=%s", new Object[]{"_id", Long.valueOf(bundle.getLong("id"))});
            } else if (!TextUtils.isEmpty(bundle.getString("serverId"))) {
                return String.format("%s='%s'", new Object[]{"serverId", bundle.getString("serverId")});
            } else {
                return String.format("%s='%s' COLLATE NOCASE", new Object[]{"local_path", bundle.getString("localPath")});
            }
        }

        private Intent getShortCutIntent(long j, String str) {
            if (AlbumsCursorHelper.isFaceAlbum(j)) {
                return new Intent("com.miui.gallery.action.VIEW_PEOPLE");
            }
            if (!AlbumsCursorHelper.isRecentAlbum(j)) {
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(GalleryContract.RecentAlbum.VIEW_PAGE_URI.buildUpon().appendQueryParameter("source", "album_page").build());
            intent.setPackage(this.mFragment.getActivity().getPackageName());
            return intent;
        }

        private void loadShareData() {
            Bundle bundle = new Bundle();
            bundle.putLong("id", this.mAlbum.getAlbumId());
            this.mFragment.getLoaderManager().initLoader(2, bundle, this);
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            if (i == 1) {
                this.mAlbumLoadTime = System.currentTimeMillis();
                AsyncContentLoader asyncContentLoader = new AsyncContentLoader(this.mFragment.getActivity(), new AlbumConvertCallback());
                asyncContentLoader.setUri(GalleryContract.Album.URI_ALL_EXCEPT_DELETED);
                asyncContentLoader.setProjection(AlbumConstants.PROJECTION);
                asyncContentLoader.setSelection(generateAlbumSelection(bundle));
                return asyncContentLoader;
            } else if (i == 2) {
                CursorLoader cursorLoader = new CursorLoader(this.mFragment.getActivity());
                cursorLoader.setUri(GalleryContract.Album.URI_SHARE_ALL);
                cursorLoader.setProjection(ShareAlbumsCursorHelper.SHARED_ALBUM_PROJECTION);
                cursorLoader.setSelection(String.format("%s>0 AND %s=%s", new Object[]{"count", "_id", Long.valueOf(bundle.getLong("id"))}));
                return cursorLoader;
            } else if (i != 3) {
                return null;
            } else {
                this.mAlbumLoadTime = System.currentTimeMillis();
                AlbumSnapshotLoader albumSnapshotLoader = new AlbumSnapshotLoader(this.mFragment.getActivity());
                albumSnapshotLoader.setSelection(generateAlbumSelection(bundle));
                return albumSnapshotLoader;
            }
        }

        public void onLoadFinished(Loader loader, Object obj) {
            int id = loader.getId();
            List list = null;
            if (id == 1) {
                if (obj != null) {
                    list = (List) obj;
                }
                if (list == null || list.size() <= 0) {
                    this.mHandleIntentCallback.onHandleFailed(this.mFragment.getActivity(), this.mFragment.getString(R.string.album_jump_failed));
                } else {
                    this.mAlbum = (Album) list.get(0);
                    loadShareData();
                }
                Log.d("JumpDialogFragment", "load album from db cost %d", (Object) Long.valueOf(System.currentTimeMillis() - this.mAlbumLoadTime));
            } else if (id == 2) {
                this.mShareAlbumCursor = (Cursor) obj;
                this.mShareAlbumsCursorHelper = new ShareAlbumsCursorHelper();
                this.mShareAlbumsCursorHelper.setSharedAlbums(this.mShareAlbumCursor);
                Intent createJumpIntent = createJumpIntent();
                if (createJumpIntent != null) {
                    this.mHandleIntentCallback.onHandleIntent(createJumpIntent);
                } else {
                    this.mHandleIntentCallback.onHandleFailed(this.mFragment.getActivity(), this.mFragment.getString(R.string.album_jump_failed));
                }
            } else if (id == 3) {
                if (obj != null) {
                    list = (List) obj;
                }
                if (list == null || list.size() <= 0) {
                    this.mFragment.getLoaderManager().initLoader(1, this.mUriParameters, this);
                } else {
                    this.mAlbum = (Album) list.get(0);
                    loadShareData();
                }
                Log.d("JumpDialogFragment", "load album from snapshot cost %d", (Object) Long.valueOf(System.currentTimeMillis() - this.mAlbumLoadTime));
            }
        }

        public void onLoaderReset(Loader loader) {
            int id = loader.getId();
            if (id == 1) {
                return;
            }
            if (id == 2) {
                this.mShareAlbumsCursorHelper = null;
                Cursor cursor = this.mShareAlbumCursor;
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        public void startLoading(HandleIntentCallback handleIntentCallback, Uri uri) {
            this.mUri = uri;
            String queryParameter = this.mUri.getQueryParameter("serverId");
            String queryParameter2 = this.mUri.getQueryParameter("id");
            String queryParameter3 = this.mUri.getQueryParameter("local_path");
            long longValue = !TextUtils.isEmpty(queryParameter2) ? Long.valueOf(queryParameter2).longValue() : -1;
            if (longValue >= 0 || !TextUtils.isEmpty(queryParameter) || !TextUtils.isEmpty(queryParameter3)) {
                Intent shortCutIntent = getShortCutIntent(longValue, queryParameter3);
                if (shortCutIntent != null) {
                    handleIntentCallback.onHandleIntent(shortCutIntent);
                    return;
                }
                this.mHandleIntentCallback = handleIntentCallback;
                this.mUriParameters = new Bundle();
                this.mUriParameters.putString("serverId", queryParameter);
                this.mUriParameters.putLong("id", longValue);
                this.mUriParameters.putString("localPath", queryParameter3);
                this.mFragment.getLoaderManager().initLoader(3, this.mUriParameters, this);
                return;
            }
            handleIntentCallback.onHandleFailed(this.mFragment.getActivity(), this.mFragment.getString(R.string.album_jump_failed));
        }
    }

    public interface HandleIntentCallback {
        void onHandleFailed(Context context, String str);

        void onHandleIntent(Intent intent);
    }

    private class PeopleJumpHelper implements LoaderManager.LoaderCallbacks {
        public final String[] PROJECTION = {"_id", "peopleName", "relationType", "microthumbfile", "thumbnailFile", "localFile", "faceXScale", "faceYScale", "faceWScale", "faceHScale", "serverId"};
        private Fragment mFragment;
        private HandleIntentCallback mHandleIntentCallback;

        public PeopleJumpHelper(Fragment fragment) {
            this.mFragment = fragment;
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(this.mFragment.getActivity());
            cursorLoader.setUri(GalleryContract.PeopleFace.PEOPLE_COVER_URI.buildUpon().appendQueryParameter("serverId", bundle.getString("serverId")).build());
            cursorLoader.setProjection(this.PROJECTION);
            return cursorLoader;
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x00ad  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x00b3  */
        public void onLoadFinished(Loader loader, Object obj) {
            Intent intent = null;
            if (obj != null) {
                Cursor cursor = (Cursor) obj;
                try {
                    if (cursor.moveToFirst()) {
                        Bundle bundle = new Bundle();
                        String string = cursor.getString(10);
                        String string2 = cursor.getString(0);
                        bundle.putString("server_id_of_album", string);
                        bundle.putString("local_id_of_album", string2);
                        String string3 = cursor.getString(1);
                        if (TextUtils.isEmpty(string3)) {
                            string3 = this.mFragment.getString(R.string.people_page_unname);
                        }
                        bundle.putString("album_name", string3);
                        bundle.putInt("relationType", cursor.getInt(2));
                        String string4 = cursor.getString(4);
                        if (TextUtils.isEmpty(string4)) {
                            string4 = cursor.getString(3);
                        }
                        if (TextUtils.isEmpty(string4)) {
                            string4 = cursor.getString(5);
                        }
                        bundle.putString("face_album_cover", string4);
                        bundle.putParcelable("face_position_rect", new RectF(cursor.getFloat(6), cursor.getFloat(7), cursor.getFloat(6) + cursor.getFloat(8), cursor.getFloat(7) + cursor.getFloat(9)));
                        Intent intent2 = new Intent();
                        try {
                            intent2.putExtras(bundle);
                            intent2.setClass(this.mFragment.getActivity(), FacePageActivity.class);
                            intent = intent2;
                        } catch (Exception unused) {
                            intent = intent2;
                            cursor.close();
                            if (intent != null) {
                            }
                        }
                    }
                } catch (Exception unused2) {
                    cursor.close();
                    if (intent != null) {
                    }
                }
            }
            if (intent != null) {
                this.mHandleIntentCallback.onHandleIntent(intent);
            } else {
                this.mHandleIntentCallback.onHandleFailed(this.mFragment.getActivity(), this.mFragment.getString(R.string.people_jump_failed));
            }
        }

        public void onLoaderReset(Loader loader) {
        }

        public void startLoading(HandleIntentCallback handleIntentCallback, String str) {
            this.mHandleIntentCallback = handleIntentCallback;
            Bundle bundle = new Bundle();
            bundle.putString("serverId", str);
            this.mFragment.getLoaderManager().initLoader(1, bundle, this);
        }
    }

    public static void enterPrivateAlbum(Activity activity) {
        JumpDialogFragment jumpDialogFragment = new JumpDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageType", 2);
        jumpDialogFragment.setArguments(bundle);
        jumpDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "JumpDialogFragment");
    }

    public static void obtainAlbumIntent(Activity activity, Uri uri, HandleIntentCallback handleIntentCallback) {
        JumpDialogFragment jumpDialogFragment = new JumpDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putInt("pageType", 0);
        jumpDialogFragment.setArguments(bundle);
        jumpDialogFragment.setHandleIntentCallback(handleIntentCallback);
        jumpDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "JumpDialogFragment");
    }

    /* access modifiers changed from: private */
    public void setDialogAlpha(Dialog dialog, float f) {
        if (dialog != null && dialog.getWindow() != null) {
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.alpha = f;
            dialog.getWindow().setAttributes(attributes);
        }
    }

    public static void showAlbumPage(Activity activity, Uri uri) {
        JumpDialogFragment jumpDialogFragment = new JumpDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putInt("pageType", 0);
        jumpDialogFragment.setArguments(bundle);
        jumpDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "JumpDialogFragment");
    }

    public static void showPeoplePage(Activity activity, String str) {
        JumpDialogFragment jumpDialogFragment = new JumpDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("serverId", str);
        bundle.putInt("pageType", 1);
        jumpDialogFragment.setArguments(bundle);
        jumpDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "JumpDialogFragment");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        int i = getArguments().getInt("pageType", -1);
        if (i == 0) {
            Log.d("JumpDialogFragment", "Jump to album page, album serverId = %s", (Object) getArguments().getString("serverId"));
            new AlbumJumpHelper(this).startLoading(this.mHandleIntentCallback, (Uri) getArguments().getParcelable("uri"));
        } else if (i == 1) {
            Log.d("JumpDialogFragment", "Jump to people page, people serverId = %s", (Object) getArguments().getString("serverId"));
            new PeopleJumpHelper(this).startLoading(this.mHandleIntentCallback, getArguments().getString("serverId"));
        } else if (i != 2) {
            Log.e("JumpDialogFragment", "Invalid page type %d", (Object) Integer.valueOf(i));
            dismiss();
        } else {
            Log.d("JumpDialogFragment", "Jump secret album");
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("cloud_guide_source", GalleryIntent.CloudGuideSource.SECRET);
            LoginAndSyncCheckFragment.checkLoginAndSyncState(this, bundle2);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            this.mHandleIntentCallback.onHandleIntent((Intent) null);
        } else if (i != 29) {
            if (i == 36) {
                IntentUtil.enterPrivateAlbum(getActivity());
                this.mHandleIntentCallback.onHandleIntent((Intent) null);
            }
        } else if (AccountHelper.getXiaomiAccount(getActivity()) != null) {
            AuthenticatePrivacyPasswordFragment.startAuthenticatePrivacyPassword(this);
        }
        super.onActivityResult(i, i2, intent);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ProgressDialog show = ProgressDialog.show(getActivity(), "", getActivity().getString(R.string.loading_dots), true, false);
        setDialogAlpha(show, 0.0f);
        setCancelable(false);
        return show;
    }

    public void onStart() {
        super.onStart();
        ThreadManager.getMainHandler().postDelayed(this.mDelayVisibleRunnable, (long) getResources().getInteger(17694722));
    }

    public void onStop() {
        super.onStop();
        ThreadManager.getMainHandler().removeCallbacks(this.mDelayVisibleRunnable);
    }

    public void setHandleIntentCallback(HandleIntentCallback handleIntentCallback) {
        this.mObtainIntentCallBack = handleIntentCallback;
    }
}
