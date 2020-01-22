package com.miui.gallery.picker.helper;

import android.database.DataSetObserver;
import java.util.List;

public interface Picker extends Iterable<String> {

    public enum ImageType {
        THUMBNAIL,
        ORIGIN,
        ORIGIN_OR_DOWNLOAD_INFO
    }

    public enum MediaType {
        IMAGE,
        VIDEO,
        ALL
    }

    public enum Mode {
        SINGLE,
        MULTIPLE,
        UNLIMITED
    }

    public enum ResultType {
        ID,
        LEGACY_GENERAL,
        LEGACY_MEDIA,
        OPEN_URI
    }

    int baseline();

    void cancel();

    int capacity();

    boolean clear();

    boolean contains(String str);

    int count();

    void done();

    String[] getFilterMimeTypes();

    ImageType getImageType();

    MediaType getMediaType();

    Mode getMode();

    ResultType getResultType();

    boolean isFull();

    boolean pick(String str);

    boolean pickAll(List<String> list);

    void registerObserver(DataSetObserver dataSetObserver);

    boolean remove(String str);

    boolean removeAll(List<String> list);

    void setFilterMimeTypes(String[] strArr);

    void setImageType(ImageType imageType);

    void setMediaType(MediaType mediaType);

    void setResultType(ResultType resultType);
}
