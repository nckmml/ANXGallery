package com.miui.gallery.card.scenario.time.free;

import android.text.TextUtils;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.TimeScenario;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class TagMonthlyScenario extends TimeScenario {
    private final List<ImageFeature> mSelectedImages = new ArrayList();
    private final Integer[] mTags;

    public TagMonthlyScenario(int i, Integer[] numArr) {
        super(i, 4, 4);
        this.mTags = numArr;
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        long recordStartTime = getRecordStartTime(record);
        return recordStartTime > 0 ? DateUtils.getYearMonthLocale(recordStartTime) : "";
    }

    /* access modifiers changed from: protected */
    public long getFirstImageTimeOfTags(Integer[] numArr) {
        GalleryEntityManager instance = GalleryEntityManager.getInstance();
        String join = TextUtils.join(",", numArr);
        String format = String.format(Locale.US, "%s,%s", new Object[]{0, 1});
        List<ImageFeature> query = instance.query(ImageFeature.class, String.format("resultFlag & " + Integer.toString(16) + " > 0" + " AND " + "version" + "=" + 2 + " AND " + "(" + "sceneTagA" + " IN (%s)" + " OR " + "sceneTagB" + " IN (%s)" + " OR " + "sceneTagC" + " IN (%s)" + ")", new Object[]{join, join, join}), (String[]) null, "imageDatetime asc", format);
        if (MiscUtil.isValid(query)) {
            return query.get(0).getImageDateTime();
        }
        return 0;
    }

    public List<Long> loadMediaItem() {
        return getImageIdsFromImageFeatures(this.mSelectedImages);
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        List<Long> recordStartTimesFromRecordAndCards = getRecordStartTimesFromRecordAndCards(list, list2, false);
        long j = 0;
        for (Long next : recordStartTimesFromRecordAndCards) {
            if (next.longValue() > j) {
                j = next.longValue();
            }
        }
        this.mSelectedImages.clear();
        long currentTimeMillis = DateUtils.getCurrentTimeMillis();
        if (j <= 0) {
            j = getFirstImageTimeOfTags(this.mTags);
        }
        long j2 = j;
        while (j2 < currentTimeMillis - 5184000000L) {
            long firstDayOfMonthTime = DateUtils.getFirstDayOfMonthTime(j2);
            long lastDayEndOfMonthTime = DateUtils.getLastDayEndOfMonthTime(j2);
            long j3 = lastDayEndOfMonthTime;
            List<ImageFeature> cameraMediaIdsByStartEndTimeTags = getCameraMediaIdsByStartEndTimeTags(this.mTags, firstDayOfMonthTime, lastDayEndOfMonthTime);
            if (cameraMediaIdsByStartEndTimeTags == null || cameraMediaIdsByStartEndTimeTags.size() <= getMinImageCount() || recordStartTimesFromRecordAndCards.contains(Long.valueOf(firstDayOfMonthTime))) {
                j2 = DateUtils.getNextMonthTime(j2);
            } else {
                this.mSelectedImages.addAll(cameraMediaIdsByStartEndTimeTags);
                setStartTime(firstDayOfMonthTime);
                setEndTime(j3);
                return true;
            }
        }
        return false;
    }
}
