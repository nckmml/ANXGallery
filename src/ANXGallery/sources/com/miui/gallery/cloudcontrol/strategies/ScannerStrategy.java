package com.miui.gallery.cloudcontrol.strategies;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.cloudcontrol.Merger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScannerStrategy extends BaseStrategy {
    public static final Merger<ScannerStrategy> CLOUD_FIRST_MERGER = new Merger<ScannerStrategy>() {
        public ScannerStrategy merge(ScannerStrategy scannerStrategy, ScannerStrategy scannerStrategy2) {
            if (scannerStrategy2.mStreamFileStrategy == null && scannerStrategy.mStreamFileStrategy != null) {
                StreamFileStrategy unused = scannerStrategy2.mStreamFileStrategy = StreamFileStrategy.cloneFrom(scannerStrategy.mStreamFileStrategy);
            }
            FileSizeLimitStrategy unused2 = scannerStrategy2.mFileSizeLimitStrategy = FileSizeLimitStrategy.merge(scannerStrategy.mFileSizeLimitStrategy, scannerStrategy2.mFileSizeLimitStrategy);
            if (scannerStrategy2.mSpecialTypeMediaStrategy == null && scannerStrategy.mSpecialTypeMediaStrategy != null) {
                SpecialTypeMediaStrategy unused3 = scannerStrategy2.mSpecialTypeMediaStrategy = SpecialTypeMediaStrategy.cloneFrom(scannerStrategy.mSpecialTypeMediaStrategy);
            }
            return scannerStrategy2;
        }
    };
    /* access modifiers changed from: private */
    @SerializedName("file_size_limit")
    public FileSizeLimitStrategy mFileSizeLimitStrategy;
    /* access modifiers changed from: private */
    @SerializedName("special_type_media")
    public SpecialTypeMediaStrategy mSpecialTypeMediaStrategy;
    /* access modifiers changed from: private */
    @SerializedName("stream_file")
    public StreamFileStrategy mStreamFileStrategy;

    public static class FileSizeLimitStrategy {
        @SerializedName("image_max_size")
        private long mImageMaxSize;
        @SerializedName("video_max_size")
        private long mVideoMaxSize;

        public static FileSizeLimitStrategy createDefault() {
            FileSizeLimitStrategy fileSizeLimitStrategy = new FileSizeLimitStrategy();
            fileSizeLimitStrategy.mImageMaxSize = 100;
            fileSizeLimitStrategy.mVideoMaxSize = 4096;
            fileSizeLimitStrategy.doAdditionalProcessing();
            return fileSizeLimitStrategy;
        }

        static FileSizeLimitStrategy merge(FileSizeLimitStrategy fileSizeLimitStrategy, FileSizeLimitStrategy fileSizeLimitStrategy2) {
            if (fileSizeLimitStrategy == null) {
                return fileSizeLimitStrategy2;
            }
            if (fileSizeLimitStrategy2 == null) {
                return fileSizeLimitStrategy;
            }
            long j = fileSizeLimitStrategy.mImageMaxSize;
            if (j > fileSizeLimitStrategy2.mImageMaxSize) {
                fileSizeLimitStrategy2.mImageMaxSize = j;
            }
            long j2 = fileSizeLimitStrategy.mVideoMaxSize;
            if (j2 > fileSizeLimitStrategy2.mVideoMaxSize) {
                fileSizeLimitStrategy2.mVideoMaxSize = j2;
            }
            return fileSizeLimitStrategy2;
        }

        public void doAdditionalProcessing() {
            this.mImageMaxSize *= 1048576;
            this.mVideoMaxSize *= 1048576;
        }

        public long getImageMaxSize() {
            return this.mImageMaxSize;
        }

        public long getVideoMaxSize() {
            return this.mVideoMaxSize;
        }

        public String toString() {
            return "FileSizeLimitStrategy{mImageMaxSize=" + this.mImageMaxSize + ", mVideoMaxSize=" + this.mVideoMaxSize + '}';
        }
    }

    public static class SpecialTypeMediaStrategy {
        @SerializedName("hfr_120fps_lower_bound")
        private long mHfr120FpsLowerBound;
        @SerializedName("hfr_120fps_upper_bound")
        private long mHfr120FpsUpperBound;
        @SerializedName("hfr_240fps_lower_bound")
        private long mHfr240FpsLowerBound;
        @SerializedName("hfr_240fps_upper_bound")
        private long mHfr240FpsUpperBound;

        public static SpecialTypeMediaStrategy cloneFrom(SpecialTypeMediaStrategy specialTypeMediaStrategy) {
            SpecialTypeMediaStrategy specialTypeMediaStrategy2 = new SpecialTypeMediaStrategy();
            specialTypeMediaStrategy2.mHfr120FpsLowerBound = specialTypeMediaStrategy.mHfr120FpsLowerBound;
            specialTypeMediaStrategy2.mHfr120FpsUpperBound = specialTypeMediaStrategy.mHfr120FpsUpperBound;
            specialTypeMediaStrategy2.mHfr240FpsLowerBound = specialTypeMediaStrategy.mHfr240FpsLowerBound;
            specialTypeMediaStrategy2.mHfr240FpsUpperBound = specialTypeMediaStrategy.mHfr240FpsUpperBound;
            return specialTypeMediaStrategy2;
        }

        public static SpecialTypeMediaStrategy createDefault() {
            SpecialTypeMediaStrategy specialTypeMediaStrategy = new SpecialTypeMediaStrategy();
            specialTypeMediaStrategy.mHfr120FpsLowerBound = 101;
            specialTypeMediaStrategy.mHfr120FpsUpperBound = 139;
            specialTypeMediaStrategy.mHfr240FpsLowerBound = 161;
            specialTypeMediaStrategy.mHfr240FpsUpperBound = 239;
            return specialTypeMediaStrategy;
        }

        public long getHfr120FpsLowerBound() {
            return this.mHfr120FpsLowerBound;
        }

        public long getHfr120FpsUpperBound() {
            return this.mHfr120FpsUpperBound;
        }

        public long getHfr240FpsLowerBound() {
            return this.mHfr240FpsLowerBound;
        }

        public long getHfr240FpsUpperBound() {
            return this.mHfr240FpsUpperBound;
        }

        public String toString() {
            return "SpecialTypeMediaStrategy{mHfr120FpsLowerBound=" + this.mHfr120FpsLowerBound + ", mHfr120FpsUpperBound=" + this.mHfr120FpsUpperBound + ", mHfr240FpsLowerBound=" + this.mHfr240FpsLowerBound + ", mHfr240FpsUpperBound=" + this.mHfr240FpsUpperBound + '}';
        }
    }

    public static class StreamFileStrategy {
        @SerializedName("delay_milliseconds")
        private long mDelayMilliseconds;
        private transient HashMap<String, StreamFolder> mFoldersMap;
        @SerializedName("folders")
        private List<StreamFolder> mStreamFolders;

        public static StreamFileStrategy cloneFrom(StreamFileStrategy streamFileStrategy) {
            StreamFileStrategy streamFileStrategy2 = new StreamFileStrategy();
            streamFileStrategy2.mDelayMilliseconds = streamFileStrategy.mDelayMilliseconds;
            streamFileStrategy2.mStreamFolders = Lists.newArrayList(streamFileStrategy.mStreamFolders);
            streamFileStrategy2.mFoldersMap = Maps.newHashMap(streamFileStrategy.mFoldersMap);
            return streamFileStrategy2;
        }

        public static StreamFileStrategy createDefault() {
            StreamFileStrategy streamFileStrategy = new StreamFileStrategy();
            streamFileStrategy.mDelayMilliseconds = 2000;
            streamFileStrategy.mStreamFolders = new ArrayList();
            streamFileStrategy.mStreamFolders.add(new StreamFolder("bluetooth"));
            streamFileStrategy.doAdditionalProcessing();
            return streamFileStrategy;
        }

        /* access modifiers changed from: private */
        public void doAdditionalProcessing() {
            if (this.mStreamFolders != null) {
                HashMap<String, StreamFolder> hashMap = this.mFoldersMap;
                if (hashMap == null) {
                    this.mFoldersMap = new HashMap<>();
                } else {
                    hashMap.clear();
                }
                for (StreamFolder next : this.mStreamFolders) {
                    this.mFoldersMap.put(next.getPath(), next);
                }
            }
        }

        public long getDelayMilliseconds() {
            return this.mDelayMilliseconds;
        }

        public boolean isStreamFolder(String str) {
            HashMap<String, StreamFolder> hashMap = this.mFoldersMap;
            if (hashMap != null) {
                return hashMap.containsKey(str);
            }
            return false;
        }
    }

    public static class StreamFolder {
        @SerializedName("path")
        private String mPath;

        public StreamFolder(String str) {
            this.mPath = str;
        }

        public String getPath() {
            return this.mPath;
        }
    }

    public static ScannerStrategy createDefault() {
        ScannerStrategy scannerStrategy = new ScannerStrategy();
        scannerStrategy.mStreamFileStrategy = StreamFileStrategy.createDefault();
        scannerStrategy.mFileSizeLimitStrategy = FileSizeLimitStrategy.createDefault();
        scannerStrategy.mSpecialTypeMediaStrategy = SpecialTypeMediaStrategy.createDefault();
        return scannerStrategy;
    }

    public void doAdditionalProcessing() {
        super.doAdditionalProcessing();
        StreamFileStrategy streamFileStrategy = this.mStreamFileStrategy;
        if (streamFileStrategy != null) {
            streamFileStrategy.doAdditionalProcessing();
        }
        FileSizeLimitStrategy fileSizeLimitStrategy = this.mFileSizeLimitStrategy;
        if (fileSizeLimitStrategy != null) {
            fileSizeLimitStrategy.doAdditionalProcessing();
        }
    }

    public FileSizeLimitStrategy getFileSizeStrategy() {
        FileSizeLimitStrategy fileSizeLimitStrategy = this.mFileSizeLimitStrategy;
        return fileSizeLimitStrategy != null ? fileSizeLimitStrategy : FileSizeLimitStrategy.createDefault();
    }

    public SpecialTypeMediaStrategy getSpecialTypeMediaStrategy() {
        SpecialTypeMediaStrategy specialTypeMediaStrategy = this.mSpecialTypeMediaStrategy;
        return specialTypeMediaStrategy != null ? specialTypeMediaStrategy : SpecialTypeMediaStrategy.createDefault();
    }

    public StreamFileStrategy getStreamFileStrategy() {
        StreamFileStrategy streamFileStrategy = this.mStreamFileStrategy;
        return streamFileStrategy != null ? streamFileStrategy : StreamFileStrategy.createDefault();
    }
}
