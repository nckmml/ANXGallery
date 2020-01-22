package com.miui.gallery.util.logger;

import android.os.SystemClock;
import android.util.Printer;
import com.miui.gallery.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class TimingLogger {
    private String mLabel;
    ArrayList<String> mSplitLabels;
    ArrayList<Long> mSplits;
    private String mTag;

    public TimingLogger(String str, String str2) {
        reset(str, str2);
    }

    private long cost() {
        ArrayList<Long> arrayList = this.mSplits;
        return arrayList.get(arrayList.size() - 1).longValue() - this.mSplits.get(0).longValue();
    }

    private String dump() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(" ");
        printWriter.println(this.mLabel + ": begin");
        long longValue = this.mSplits.get(0).longValue();
        long elapsedRealtime = this.mSplits.size() > 1 ? longValue : SystemClock.elapsedRealtime();
        for (int i = 1; i < this.mSplits.size(); i++) {
            elapsedRealtime = this.mSplits.get(i).longValue();
            long longValue2 = this.mSplits.get(i - 1).longValue();
            printWriter.println(this.mLabel + ":      " + (elapsedRealtime - longValue2) + " ms, " + this.mSplitLabels.get(i));
        }
        printWriter.println(this.mLabel + ": end, " + (elapsedRealtime - longValue) + " ms");
        printWriter.flush();
        return stringWriter.toString();
    }

    public void addSplit(String str) {
        this.mSplits.add(Long.valueOf(SystemClock.elapsedRealtime()));
        this.mSplitLabels.add(str);
    }

    public long dump(Printer printer) {
        if (printer == null) {
            return dumpToLog();
        }
        printer.println(dump());
        return cost();
    }

    public long dumpToLog() {
        Log.d(this.mTag, dump());
        return cost();
    }

    public void reset() {
        ArrayList<Long> arrayList = this.mSplits;
        if (arrayList == null) {
            this.mSplits = new ArrayList<>();
            this.mSplitLabels = new ArrayList<>();
        } else {
            arrayList.clear();
            this.mSplitLabels.clear();
        }
        addSplit((String) null);
    }

    public void reset(String str, String str2) {
        this.mTag = str;
        this.mLabel = str2;
        reset();
    }
}
