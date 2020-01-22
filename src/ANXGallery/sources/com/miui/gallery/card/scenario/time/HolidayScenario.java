package com.miui.gallery.card.scenario.time;

import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.assistant.HolidaysUtil;
import java.util.Iterator;
import java.util.List;
import miui.date.Calendar;

public abstract class HolidayScenario extends TimeScenario {
    protected int mYear = 1;

    public HolidayScenario(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return getRecordTargetTime(record) > 0 ? getDatePeriodFromRecord(record) : "";
    }

    /* access modifiers changed from: protected */
    public int getHolidayFromRecord(Record record, boolean z) {
        return getHolidayFromTargetTime(getRecordTargetTime(record), z);
    }

    /* access modifiers changed from: protected */
    public int getHolidayFromTargetTime(long j, boolean z) {
        if (j <= 0) {
            return -1;
        }
        Calendar calendar = new Calendar();
        calendar.setTimeInMillis(j);
        return z ? HolidaysUtil.getChineseHoliday(calendar) : HolidaysUtil.getHoliday(calendar);
    }

    /* access modifiers changed from: protected */
    public String getPrimaryKey() {
        return String.valueOf(this.mYear);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
    public long[] getStartEndTime(long j, List<HolidayConfig> list, boolean z) {
        int i;
        long[] jArr = new long[2];
        jArr[0] = DateUtils.getDateTime(j);
        jArr[1] = jArr[0] + 86400000;
        int chineseHoliday = z ? HolidaysUtil.getChineseHoliday(j) : HolidaysUtil.getHoliday(j);
        int i2 = Reader.READ_DONE;
        if (MiscUtil.isValid(list)) {
            Iterator<HolidayConfig> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                HolidayConfig next = it.next();
                if (next.getHoliday() == chineseHoliday) {
                    i = next.getMaxContinuousDay();
                    i2 = next.getPreviousMaxDay();
                    break;
                }
            }
            if (i > 1) {
                int i3 = 0;
                int i4 = 0;
                boolean z2 = true;
                boolean z3 = true;
                while (i3 + i4 < i - 1) {
                    if ((!z2 || !z3 || i3 > i4) && (!z2 || z3)) {
                        if ((!z2 || !z3 || i3 <= i4) && (z2 || !z3)) {
                            break;
                        }
                        i4++;
                        long j2 = ((long) i4) * 86400000;
                        List<Long> mediaIdsByStartEndTime = getMediaIdsByStartEndTime(jArr[0] + j2, jArr[1] + j2);
                        if (mediaIdsByStartEndTime == null || mediaIdsByStartEndTime.size() < getMinImageCount()) {
                            i4--;
                            z3 = false;
                        }
                    } else {
                        i3++;
                        long j3 = ((long) i3) * 86400000;
                        List<Long> mediaIdsByStartEndTime2 = getMediaIdsByStartEndTime(jArr[0] - j3, jArr[1] - j3);
                        if (mediaIdsByStartEndTime2 == null || mediaIdsByStartEndTime2.size() < getMinImageCount()) {
                            i3--;
                            z2 = false;
                        }
                        if (i3 >= i2) {
                            i3 = i2;
                            z2 = false;
                        }
                    }
                }
                jArr[0] = jArr[0] - (((long) i3) * 86400000);
                jArr[1] = jArr[1] + (((long) i4) * 86400000);
            }
            return jArr;
        }
        i = 1;
        if (i > 1) {
        }
        return jArr;
    }

    /* access modifiers changed from: protected */
    public int getYearFromRecord(Record record) {
        if (record != null) {
            try {
                return Integer.valueOf(record.getPrimaryKey()).intValue();
            } catch (NumberFormatException e) {
                String str = this.TAG;
                Log.e(str, "get year of record error:" + e);
            }
        }
        return 1;
    }
}
