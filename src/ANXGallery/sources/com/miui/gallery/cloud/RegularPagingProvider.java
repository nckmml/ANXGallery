package com.miui.gallery.cloud;

import android.database.Cursor;

public class RegularPagingProvider {
    private int mCurrentQueryCount;
    protected int mPage;
    private boolean mShouldContinue;
    private int mTotalLimit;
    private int mTotalQueriedCount;

    public RegularPagingProvider() {
        this(Reader.READ_DONE);
    }

    public RegularPagingProvider(int i) {
        this.mShouldContinue = true;
        this.mPage = 0;
        this.mTotalQueriedCount = 0;
        this.mCurrentQueryCount = 0;
        this.mTotalLimit = i;
    }

    public int getLimitGrownSize() {
        return 1;
    }

    public int getOffset() {
        return this.mTotalQueriedCount;
    }

    public int getQueryLimit() {
        this.mCurrentQueryCount = Math.min(getLimitGrownSize() * 1000, this.mTotalLimit - this.mTotalQueriedCount);
        return this.mCurrentQueryCount;
    }

    public boolean updateShouldContinue(long j) {
        boolean z = false;
        if (j == 0) {
            this.mShouldContinue = false;
        }
        int i = this.mTotalQueriedCount;
        int i2 = this.mCurrentQueryCount;
        this.mTotalQueriedCount = i + i2;
        if (j >= ((long) i2) && this.mTotalQueriedCount < this.mTotalLimit) {
            z = true;
        }
        this.mShouldContinue = z;
        this.mPage++;
        return this.mShouldContinue;
    }

    public boolean updateShouldContinue(Cursor cursor) {
        if (cursor != null) {
            return updateShouldContinue((long) cursor.getCount());
        }
        this.mShouldContinue = false;
        return this.mShouldContinue;
    }
}
