package com.miui.gallery.projection;

import android.text.TextUtils;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.projection.IConnectController;
import com.miui.gallery.util.Log;
import java.util.Arrays;

/* compiled from: ConnectController */
class SlidingWindow {
    private int mCurrentIndex = 0;
    private boolean mCurrentIndexChanged = true;
    private int mEndIndex;
    private String[] mFiles = new String[151];
    private IConnectController.DataSet mMediaSet;
    private int mStartIndex;
    private int mTotalCount;

    SlidingWindow() {
    }

    private void doSlideWindow(int i) {
        IConnectController.DataSet dataSet = this.mMediaSet;
        if (dataSet == null) {
            reset();
            return;
        }
        this.mTotalCount = dataSet.getCount();
        if (this.mTotalCount <= 0) {
            reset();
            return;
        }
        int i2 = 0;
        this.mStartIndex = Math.max(i - 75, 0);
        this.mEndIndex = Math.min(i + 75 + 1, this.mTotalCount);
        for (int i3 = this.mStartIndex; i3 < this.mEndIndex; i3++) {
            BaseDataItem item = this.mMediaSet.getItem((BaseDataItem) null, i3);
            if (item != null) {
                String pathDisplayBetter = item.getPathDisplayBetter();
                if (!TextUtils.isEmpty(pathDisplayBetter)) {
                    this.mFiles[i2] = pathDisplayBetter;
                    i2++;
                }
            }
        }
        Log.i("SlidingWindow", "slideWindow, mStartIndex=" + this.mStartIndex + ", mEndIndex=" + this.mEndIndex);
    }

    private int indexOf(String str) {
        if (str == null) {
            return -1;
        }
        int i = 0;
        for (String str2 : this.mFiles) {
            if (str2 != null && str2.equals(str)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r3.mEndIndex;
     */
    private boolean isWindowDirty(int i) {
        int i2;
        int i3 = this.mStartIndex;
        return (i3 > 0 && i - i3 < 50) || (i2 < this.mTotalCount && i2 - i < 50);
    }

    private void reset() {
        this.mStartIndex = 0;
        this.mEndIndex = 0;
        Arrays.fill(this.mFiles, (Object) null);
        this.mCurrentIndex = 0;
        this.mCurrentIndexChanged = true;
    }

    private void slideWindowTo(int i) {
        int i2;
        IConnectController.DataSet dataSet = this.mMediaSet;
        if (dataSet == null) {
            reset();
            return;
        }
        int count = dataSet.getCount();
        if (count <= 0) {
            reset();
            return;
        }
        if (i < 0) {
            i = 0;
        } else if (i >= count) {
            i = count - 1;
        }
        int i3 = this.mStartIndex;
        int i4 = this.mEndIndex;
        if (i3 == i4 || count != (i2 = this.mTotalCount) || (i2 > i4 - i3 && isWindowDirty(i))) {
            doSlideWindow(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0087, code lost:
        return null;
     */
    public synchronized String getNext(String str, boolean z) {
        if (this.mCurrentIndexChanged) {
            this.mCurrentIndexChanged = false;
            slideWindowTo(this.mCurrentIndex);
        }
        if (this.mTotalCount > 0) {
            if (!TextUtils.isEmpty(str)) {
                int indexOf = indexOf(str);
                if (indexOf == -1) {
                    indexOf = this.mCurrentIndex - this.mStartIndex;
                } else if (indexOf == (this.mEndIndex - this.mStartIndex) - 1) {
                    if (this.mEndIndex < this.mTotalCount) {
                        slideWindowTo(this.mEndIndex - 1);
                        indexOf = indexOf(str);
                    } else if (!z) {
                        return null;
                    } else {
                        slideWindowTo(0);
                        indexOf = -1;
                    }
                }
                int i = indexOf + 1;
                if (z) {
                    if (i >= this.mFiles.length) {
                        i = 0;
                    }
                } else if (i >= this.mFiles.length) {
                    return null;
                }
                Log.i("SlidingWindow", "getNext: next=" + this.mFiles[i] + ", index=" + i);
                return this.mFiles[i];
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        return null;
     */
    public synchronized String getPrevious(String str, boolean z) {
        if (this.mCurrentIndexChanged) {
            this.mCurrentIndexChanged = false;
            slideWindowTo(this.mCurrentIndex);
        }
        if (this.mTotalCount > 0) {
            if (!TextUtils.isEmpty(str)) {
                int indexOf = indexOf(str);
                if (indexOf == 0) {
                    slideWindowTo(this.mStartIndex);
                    indexOf = indexOf(str);
                }
                if (indexOf == -1) {
                    indexOf = this.mCurrentIndex - this.mStartIndex;
                }
                int i = indexOf - 1;
                if (z) {
                    if (i < 0) {
                        i = this.mFiles.length - 1;
                    }
                } else if (i < 0) {
                    return null;
                }
                Log.i("SlidingWindow", "getPrevious: pre=" + this.mFiles[i] + ", index=" + i);
                return this.mFiles[i];
            }
        }
    }

    public synchronized void onCurrentIndexChanged(int i) {
        this.mCurrentIndexChanged |= this.mCurrentIndex != i;
        this.mCurrentIndex = i;
    }

    public synchronized void setMediaSet(IConnectController.DataSet dataSet) {
        if (!(this.mMediaSet == dataSet && dataSet == null)) {
            if (dataSet == null || this.mMediaSet != dataSet) {
                reset();
            }
            this.mMediaSet = dataSet;
        }
    }
}
