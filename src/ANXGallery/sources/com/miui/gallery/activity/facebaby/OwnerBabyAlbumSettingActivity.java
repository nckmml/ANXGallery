package com.miui.gallery.activity.facebaby;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.DatabaseUtils;
import android.graphics.RectF;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CreateGroupItem;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.deprecated.NormalPeopleFaceMediaSet;
import com.miui.gallery.provider.deprecated.ThumbnailInfo;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.baby.FindFace2CreateBabyAlbum;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.ArrayList;
import miui.app.DatePickerDialog;
import miui.widget.DatePicker;

public class OwnerBabyAlbumSettingActivity extends BabyAlbumSettingActivityBase implements Preference.OnPreferenceChangeListener {
    private boolean mBabyFaceChanged;
    /* access modifiers changed from: private */
    public String mCoverFaceId;
    private DatePickerDialog.OnDateSetListener mDatelistener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            OwnerBabyAlbumSettingActivity ownerBabyAlbumSettingActivity = OwnerBabyAlbumSettingActivity.this;
            ownerBabyAlbumSettingActivity.mBirthdayYear = i;
            ownerBabyAlbumSettingActivity.mBirthdayMonth = i2 + 1;
            ownerBabyAlbumSettingActivity.mBirthdayDay = i3;
            ownerBabyAlbumSettingActivity.mBirthday = BabyAlbumSettingActivityBase.combine2Birthday(ownerBabyAlbumSettingActivity.mBirthdayYear, OwnerBabyAlbumSettingActivity.this.mBirthdayMonth, OwnerBabyAlbumSettingActivity.this.mBirthdayDay);
            OwnerBabyAlbumSettingActivity.this.mChooseDate.setSummary(OwnerBabyAlbumSettingActivity.this.mBirthday);
        }
    };
    private boolean mNeedSaveBabyFace;

    private boolean createOrBrowse() {
        return this.mBabyAlbumLocalId.longValue() == -1;
    }

    /* access modifiers changed from: private */
    public void loadAndSetBabyFace(ThreadPool.JobContext jobContext) {
        if (this.mCoverFaceBitmap == null) {
            final RectF[] rectFArr = new RectF[1];
            final String queryCoverImageOfOnePerson = FaceManager.queryCoverImageOfOnePerson(this.mPeopleId, rectFArr);
            this.mHandler.post(new Runnable() {
                public void run() {
                    ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(queryCoverImageOfOnePerson), OwnerBabyAlbumSettingActivity.this.mFace, BabyAlbumSettingActivityBase.sDisplayImageOptions, (ImageSize) null, rectFArr[0]);
                }
            });
        } else if (this.mFace != null) {
            this.mFace.setImageBitmap(this.mCoverFaceBitmap);
        }
    }

    /* access modifiers changed from: private */
    public void loadBabyFace(ThreadPool.JobContext jobContext) {
        if (!TextUtils.isEmpty(this.mCoverFaceId)) {
            final RectF[] rectFArr = new RectF[1];
            final String faceByFaceId = ThumbnailInfo.getFaceByFaceId(this.mCoverFaceId, rectFArr);
            this.mHandler.post(new Runnable() {
                public void run() {
                    ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(faceByFaceId), OwnerBabyAlbumSettingActivity.this.mFace, BabyAlbumSettingActivityBase.sDisplayImageOptions, (ImageSize) null, rectFArr[0]);
                }
            });
        }
    }

    private void refreshBabyFace() {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                OwnerBabyAlbumSettingActivity.this.loadBabyFace(jobContext);
                return null;
            }
        });
    }

    public static void saveInfo2Db(BabyInfo babyInfo, String str, Long l, String str2, ThumbnailInfo thumbnailInfo) {
        String str3 = "";
        if (thumbnailInfo == null) {
            ContentValues contentValues = new ContentValues();
            if (babyInfo != null) {
                str3 = babyInfo.toJSON();
            }
            contentValues.put("babyInfoJson", str3);
            contentValues.put("peopleId", str);
            GalleryUtils.safeUpdate(GalleryCloudUtils.CLOUD_URI, contentValues, "_id = ?", new String[]{str2});
        } else {
            String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(66);
            Object[] objArr = new Object[14];
            objArr[0] = "cloud";
            objArr[1] = "thumbnailInfo";
            objArr[2] = DatabaseUtils.sqlEscapeString(thumbnailInfo.toString());
            objArr[3] = "babyInfoJson";
            if (babyInfo != null) {
                str3 = DatabaseUtils.sqlEscapeString(babyInfo.toJSON());
            }
            objArr[4] = str3;
            objArr[5] = "peopleId";
            objArr[6] = str;
            objArr[7] = "editedColumns";
            objArr[8] = "editedColumns";
            objArr[9] = transformToEditedColumnsElement;
            objArr[10] = transformToEditedColumnsElement;
            objArr[11] = transformToEditedColumnsElement;
            objArr[12] = "_id";
            objArr[13] = str2;
            GalleryUtils.safeExec(String.format("update %s set %s=%s, %s=%s, %s='%s', %s=coalesce(replace(%s, '%s', '') || '%s', '%s') where %s=%s", objArr));
        }
        GalleryApp.sGetAndroidContext().getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, true);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity] */
    /* access modifiers changed from: private */
    public String validateInputTextAndUpdateButtonState(String str, AlertDialog alertDialog) {
        String checkFileNameValid = CreateGroupItem.checkFileNameValid(this, str);
        alertDialog.getButton(-1).setEnabled(TextUtils.isEmpty(checkFileNameValid));
        return checkFileNameValid;
    }

    /* access modifiers changed from: protected */
    public Intent getIntentToAutoUploadPage() {
        Intent intentToAutoUploadPage = super.getIntentToAutoUploadPage();
        intentToAutoUploadPage.putExtra("allow_to_reassociate", false);
        return intentToAutoUploadPage;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceResourceId() {
        return R.xml.owner_baby_album_preferences;
    }

    /* access modifiers changed from: protected */
    public void justSaveInfo2DbByJson() {
        if (!this.mHaveSaveBabyInfo && !createOrBrowse()) {
            if (babyInfoChanged() || this.mNeedSaveBabyFace) {
                saveInfo2Db(toBabyInfo(this.mPeopleId, 1), this.mPeopleId, this.mFaceAlbumLocalId, String.valueOf(this.mBabyAlbumLocalId), this.mThumbnailInfo);
                this.mHaveSaveBabyInfo = true;
                this.mNeedSaveBabyFace = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 0) {
            if (i != 31) {
                super.onActivityResult(i, i2, intent);
            } else if (i2 == -1 && intent != null) {
                this.mCoverFaceId = intent.getStringExtra("picked_face_id");
                if (!createOrBrowse()) {
                    if (this.mThumbnailInfo == null) {
                        this.mThumbnailInfo = new ThumbnailInfo(this.mBabyAlbumLocalId.longValue(), this.mIsOtherShareAlbum, (String) null);
                    }
                    this.mThumbnailInfo.setFaceId(this.mCoverFaceId);
                }
                this.mBabyFaceChanged = true;
                this.mNeedSaveBabyFace = true;
                refreshBabyFace();
            }
        } else if (i2 == -1 && intent != null) {
            this.mIsAutoupdate = Boolean.valueOf(((BabyInfo) intent.getParcelableExtra("baby-info")).mAutoupdate);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        this.mFaceAlbumLocalId = Long.valueOf(extras.getLong("faceAlbumLocalId", -1));
        if (extras.containsKey("faceAlbumServerId")) {
            this.mPeopleId = extras.getString("faceAlbumServerId");
        }
        this.mCoverFaceId = extras.getString("faceAlbumCoverFaceServerId");
        this.mBabyNicknamePre.setOnPreferenceChangeListener(this);
        this.mBabySexPre.setOnPreferenceChangeListener(this);
        this.mRelationPre.setOnPreferenceChangeListener(this);
        View findViewById = findViewById(R.id.finish_fill_baby);
        if (createOrBrowse()) {
            findViewById.setVisibility(0);
            this.mBirthday = extras.getString("birthday");
            getBirthYearMonthDay(this.mBirthday);
            this.mNickName = extras.getString("name");
            if (TextUtils.isEmpty(this.mNickName)) {
                this.mNickName = getString(R.string.face_album_not_named);
            }
        }
        findViewById.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r2v3, types: [com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r7v6, types: [android.content.Context, com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity] */
            public void onClick(View view) {
                if (OwnerBabyAlbumSettingActivity.this.mNickName.equalsIgnoreCase(OwnerBabyAlbumSettingActivity.this.getString(R.string.face_album_not_named))) {
                    ? r7 = OwnerBabyAlbumSettingActivity.this;
                    ToastUtils.makeText((Context) r7, (CharSequence) r7.getString(R.string.input_nickname_toast));
                    return;
                }
                OwnerBabyAlbumSettingActivity ownerBabyAlbumSettingActivity = OwnerBabyAlbumSettingActivity.this;
                BabyInfo babyInfo = ownerBabyAlbumSettingActivity.toBabyInfo(ownerBabyAlbumSettingActivity.mPeopleId, 0);
                String str = OwnerBabyAlbumSettingActivity.this.mNickName;
                NormalPeopleFaceMediaSet normalPeopleFaceMediaSet = new NormalPeopleFaceMediaSet(OwnerBabyAlbumSettingActivity.this.mFaceAlbumLocalId.longValue(), OwnerBabyAlbumSettingActivity.this.mPeopleId, OwnerBabyAlbumSettingActivity.this.mNickName);
                ? r2 = OwnerBabyAlbumSettingActivity.this;
                FindFace2CreateBabyAlbum.createBabyAlbumAndSaveBabyInfo(str, babyInfo, normalPeopleFaceMediaSet, r2, r2.mCoverFaceId);
            }
        });
        this.mFace.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity, android.app.Activity] */
            public void onClick(View view) {
                ? r0 = OwnerBabyAlbumSettingActivity.this;
                IntentUtil.pickFace(r0, r0.getString(R.string.set_face_image), OwnerBabyAlbumSettingActivity.this.mPeopleId, String.valueOf(OwnerBabyAlbumSettingActivity.this.mFaceAlbumLocalId), (ArrayList<Long>) null, 1, true);
            }
        });
        if (createOrBrowse()) {
            setCoverFaceAndBirthdayForCreateBaby();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.facebaby.BabyAlbumSettingActivityBase, com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        GallerySamplingStatHelper.recordPageEnd(this, "album_baby_share_owner_setting");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mBabySexPre) {
            this.mSex = (String) obj;
            ((ListPreference) this.mBabySexPre).setValue(this.mSex);
            this.mBabySexPre.setSummary(((ListPreference) this.mBabySexPre).getEntry());
            return true;
        } else if (preference != this.mRelationPre) {
            return true;
        } else {
            this.mRelation = (String) obj;
            ((ListPreference) this.mRelationPre).setValue(this.mRelation);
            this.mRelationText = (String) ((ListPreference) this.mRelationPre).getEntry();
            this.mRelationPre.setSummary(this.mRelationText);
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [com.miui.gallery.activity.facebaby.BabyAlbumSettingActivityBase, com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity, android.app.Activity] */
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        int i;
        int i2;
        int i3;
        if ("baby_birthday".equals(preference.getKey())) {
            if (this.mBirthdayYear == 0 || this.mBirthdayMonth == 0 || this.mBirthdayDay == 0) {
                int[] currentYearMonthDay = getCurrentYearMonthDay(System.currentTimeMillis());
                int i4 = currentYearMonthDay[0];
                int i5 = currentYearMonthDay[1];
                i = currentYearMonthDay[2];
                i2 = i4;
                i3 = i5;
            } else {
                int i6 = this.mBirthdayYear;
                i3 = this.mBirthdayMonth;
                i2 = i6;
                i = this.mBirthdayDay;
            }
            new DatePickerDialog(this, this.mDatelistener, i2, i3 - 1, i).show();
            return true;
        }
        if ("baby_name".equals(preference.getKey())) {
            rename(this.mNickName, this);
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.facebaby.BabyAlbumSettingActivityBase, com.miui.gallery.activity.facebaby.OwnerBabyAlbumSettingActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        GallerySamplingStatHelper.recordPageStart(this, "album_baby_share_owner_setting");
    }

    public void rename(String str, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater from = LayoutInflater.from(builder.getContext());
        builder.setTitle(R.string.input_baby_name);
        View inflate = from.inflate(R.layout.baby_name_input_dialog_view, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.custom_dialog_content);
        editText.setText(str);
        editText.selectAll();
        builder.setView(inflate);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String access$200 = OwnerBabyAlbumSettingActivity.this.validateInputTextAndUpdateButtonState(editText.getText().toString(), (AlertDialog) dialogInterface);
                if (!TextUtils.isEmpty(access$200)) {
                    ToastUtils.makeText((Context) activity, (CharSequence) access$200);
                    return;
                }
                OwnerBabyAlbumSettingActivity.this.mNickName = editText.getText().toString();
                OwnerBabyAlbumSettingActivity.this.mBabyNicknamePre.setSummary(OwnerBabyAlbumSettingActivity.this.mNickName);
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        final AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                String unused = OwnerBabyAlbumSettingActivity.this.validateInputTextAndUpdateButtonState(editText.getText().toString(), create);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                String access$200 = OwnerBabyAlbumSettingActivity.this.validateInputTextAndUpdateButtonState(editText.getText().toString(), create);
                if (!TextUtils.isEmpty(access$200)) {
                    ToastUtils.makeText((Context) activity, (CharSequence) access$200);
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        create.show();
        create.getWindow().setSoftInputMode(5);
    }

    /* access modifiers changed from: protected */
    public void saveBabyInfo() {
        if (createOrBrowse()) {
            return;
        }
        if (babyInfoChanged() || this.mBabyFaceChanged) {
            BabyInfo babyInfo = toBabyInfo(this.mPeopleId, 1);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("baby-info", babyInfo);
            bundle.putString("name", this.mNickName);
            bundle.putLong("babyAlbumLocalId", this.mBabyAlbumLocalId.longValue());
            bundle.putString("baby-people-id", this.mPeopleId);
            bundle.putLong("faceAlbumLocalId", this.mFaceAlbumLocalId.longValue());
            if (this.mThumbnailInfo != null) {
                bundle.putString("thumbnail_info_of_baby", this.mThumbnailInfo.toString());
            }
            intent.putExtras(bundle);
            setResult(12, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void setCoverFaceAndBirthdayForCreateBaby() {
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
            public Void run(ThreadPool.JobContext jobContext) {
                OwnerBabyAlbumSettingActivity.this.loadAndSetBabyFace(jobContext);
                int[] currentYearMonthDay = BabyAlbumSettingActivityBase.getCurrentYearMonthDay(FaceManager.queryTimeOfOldestImagesOfOnePerson(OwnerBabyAlbumSettingActivity.this.mPeopleId));
                int i = currentYearMonthDay[0];
                int i2 = currentYearMonthDay[1];
                int i3 = currentYearMonthDay[2];
                OwnerBabyAlbumSettingActivity.this.mBirthday = BabyAlbumSettingActivityBase.combine2Birthday(i, i2, i3);
                OwnerBabyAlbumSettingActivity ownerBabyAlbumSettingActivity = OwnerBabyAlbumSettingActivity.this;
                ownerBabyAlbumSettingActivity.getBirthYearMonthDay(ownerBabyAlbumSettingActivity.mBirthday);
                OwnerBabyAlbumSettingActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (OwnerBabyAlbumSettingActivity.this.mChooseDate != null) {
                            OwnerBabyAlbumSettingActivity.this.mChooseDate.setSummary(OwnerBabyAlbumSettingActivity.this.mBirthday);
                        }
                    }
                });
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setPreferencesValue() {
        super.setPreferencesValue();
        this.mBabyNicknamePre.setSummary(this.mNickName);
        this.mChooseDate.setSummary(this.mBirthday);
        ((ListPreference) this.mBabySexPre).setValue(this.mSex);
        this.mBabySexPre.setSummary(((ListPreference) this.mBabySexPre).getEntry());
        ((ListPreference) this.mRelationPre).setValue(this.mRelation);
        this.mRelationText = (String) ((ListPreference) this.mRelationPre).getEntry();
        this.mRelationPre.setSummary(this.mRelationText);
    }
}
