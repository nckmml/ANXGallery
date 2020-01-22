package com.miui.gallery.ui.photodetail.usecase;

import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import com.miui.gallery.ui.photodetail.usecase.EditPhotoDateTime;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.reactivestreams.Publisher;

public class EditPhotoDateTimeAndRecord extends EditPhotoDateTime {
    protected long mAlbumId;
    private Calendar mSourceCalendar;
    protected long mSourceDateTime;
    private Calendar mUpdateCalendar;

    public EditPhotoDateTimeAndRecord(IPhotoRepository iPhotoRepository, long j, long j2) {
        super(iPhotoRepository);
        this.mAlbumId = j;
        this.mSourceDateTime = j2;
    }

    /* access modifiers changed from: private */
    public Map<String, String> generateRecordParam(String str, EditPhotoDateTime.RequestBean requestBean) {
        if (this.mSourceCalendar == null) {
            this.mSourceCalendar = Calendar.getInstance();
        }
        if (this.mUpdateCalendar == null) {
            this.mUpdateCalendar = Calendar.getInstance();
        }
        this.mUpdateCalendar.setTimeInMillis(requestBean.getNewTime());
        this.mSourceCalendar.setTimeInMillis(this.mSourceDateTime);
        HashMap hashMap = new HashMap();
        if (this.mSourceCalendar.get(1) != this.mUpdateCalendar.get(1)) {
            hashMap.put("edit_date_year", String.valueOf(this.mUpdateCalendar.get(1)));
        }
        if (this.mSourceCalendar.get(2) != this.mUpdateCalendar.get(2)) {
            hashMap.put("edit_date_month", String.valueOf(this.mUpdateCalendar.get(2)));
        }
        if (this.mSourceCalendar.get(5) != this.mUpdateCalendar.get(5)) {
            hashMap.put("edit_date_day", String.valueOf(this.mUpdateCalendar.get(5)));
        }
        if (this.mSourceCalendar.get(11) != this.mUpdateCalendar.get(11)) {
            hashMap.put("edit_date_hour", String.valueOf(this.mUpdateCalendar.get(11)));
        }
        hashMap.put("album_type", str + " | " + FileUtils.getParentFolderPath(requestBean.getPath()));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public Flowable<Boolean> buildUseCaseFlowable(final EditPhotoDateTime.RequestBean requestBean) {
        return super.buildUseCaseFlowable(requestBean).flatMap(new Function<Boolean, Publisher<String>>() {
            public Publisher<String> apply(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    return EditPhotoDateTimeAndRecord.this.mRepository.queryAlbumNameByAlbumId(Long.valueOf(EditPhotoDateTimeAndRecord.this.mAlbumId));
                }
                throw new IllegalStateException("edit photo datetime failed");
            }
        }).map(new Function<String, Boolean>() {
            public Boolean apply(String str) throws Exception {
                GallerySamplingStatHelper.recordCountEvent("photo_detail", "set_photo_datetime", EditPhotoDateTimeAndRecord.this.generateRecordParam(str, requestBean));
                return true;
            }
        });
    }
}
