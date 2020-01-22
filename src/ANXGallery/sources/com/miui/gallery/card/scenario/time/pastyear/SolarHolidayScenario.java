package com.miui.gallery.card.scenario.time.pastyear;

import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.HolidayConfig;
import com.miui.gallery.card.scenario.time.HolidayScenario;
import com.miui.gallery.util.assistant.HolidaysUtil;
import java.util.LinkedList;
import java.util.List;
import miui.date.Calendar;

public class SolarHolidayScenario extends HolidayScenario {
    private final List<HolidayConfig> mHolidayConfig = new LinkedList();

    public SolarHolidayScenario() {
        super(102, 1, 7);
        this.mHolidayConfig.add(new HolidayConfig(200, Reader.READ_DONE, 1));
        this.mHolidayConfig.add(new HolidayConfig(201, Reader.READ_DONE, 1));
        this.mHolidayConfig.add(new HolidayConfig(202, Reader.READ_DONE, 3));
        this.mHolidayConfig.add(new HolidayConfig(203, Reader.READ_DONE, 1));
        this.mHolidayConfig.add(new HolidayConfig(204, 2, 7));
        this.mHolidayConfig.add(new HolidayConfig(205, Reader.READ_DONE, 1));
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        int holidayFromRecord = getHolidayFromRecord(record, false);
        return holidayFromRecord != -1 ? HolidaysUtil.getHolidayNameForStory(getYearFromRecord(record), holidayFromRecord) : "";
    }

    /* access modifiers changed from: protected */
    public String getPrimaryKey() {
        return Integer.toString(getHolidayFromTargetTime(getTargetTime(), false));
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        long currentTimeMillis = DateUtils.getCurrentTimeMillis();
        Calendar calendar = new Calendar();
        calendar.setTimeInMillis(currentTimeMillis);
        List<Long> recordTargetTimesFromRecordAndCards = getRecordTargetTimesFromRecordAndCards(list, list2);
        for (int i = 1; i <= 8; i++) {
            this.mYear = i;
            if (HolidaysUtil.getHoliday(calendar) == -1) {
                return false;
            }
            long lastNYearDateTime = DateUtils.getLastNYearDateTime(this.mYear, currentTimeMillis);
            long[] startEndTime = getStartEndTime(lastNYearDateTime, this.mHolidayConfig, false);
            if (!recordTargetTimesFromRecordAndCards.contains(Long.valueOf(lastNYearDateTime))) {
                setStartTime(startEndTime[0]);
                setEndTime(startEndTime[1]);
                setTargetTime(lastNYearDateTime);
                List<Long> loadMediaItem = loadMediaItem();
                if (loadMediaItem != null && loadMediaItem.size() >= getMinImageCount()) {
                    return true;
                }
            }
        }
        return false;
    }
}
