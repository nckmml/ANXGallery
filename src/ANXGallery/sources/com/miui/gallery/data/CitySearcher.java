package com.miui.gallery.data;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.data.CityDatabaseHelper;
import com.miui.gallery.util.Log;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import miui.util.IOUtils;

public class CitySearcher {
    private static CitySearcher sThis;
    CityDatabaseHelper.CityBoundRectList mCityBoundRects;
    ConcurrentMap<String, CityDatabaseHelper.CityBoundary> mCityBoundaries = new ConcurrentHashMap();
    private CityDatabaseHelper mDbHelper;
    PhotoGpsCache mPhotoGpsCache = new PhotoGpsCache();

    private static class DataBaseFileLoader {
        private Context mContext;
        private String mDataBasePath;

        public DataBaseFileLoader(Context context) {
            this.mContext = context;
            this.mDataBasePath = context.getApplicationInfo().dataDir + "/databases";
        }

        private boolean compareStream(InputStream inputStream, InputStream inputStream2) throws IOException {
            int read;
            if (!(inputStream instanceof BufferedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            if (!(inputStream2 instanceof BufferedInputStream)) {
                inputStream2 = new BufferedInputStream(inputStream2);
            }
            do {
                read = inputStream.read();
                if (read == -1) {
                    return inputStream2.read() == -1;
                }
            } while (read == inputStream2.read());
            return false;
        }

        private boolean copyFile() {
            FileOutputStream fileOutputStream;
            File file = new File(this.mDataBasePath);
            File file2 = new File(file, "city.db");
            File file3 = new File(file, "city.db.md5");
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (file2.exists() && !file2.delete()) {
                    return false;
                }
                if (file3.exists() && !file3.delete()) {
                    return false;
                }
                InputStream inputStream = null;
                try {
                    InputStream open = this.mContext.getAssets().open("city.db");
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                        try {
                            IOUtils.copy(open, fileOutputStream);
                            IOUtils.closeQuietly(open);
                            IoUtils.closeSilently(fileOutputStream);
                            try {
                                open = this.mContext.getAssets().open("city.db.md5");
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                                try {
                                    IOUtils.copy(open, fileOutputStream2);
                                    IOUtils.closeQuietly(open);
                                    IoUtils.closeSilently(fileOutputStream2);
                                    return true;
                                } catch (IOException e) {
                                    e = e;
                                    fileOutputStream = fileOutputStream2;
                                    try {
                                        Log.w("DataBaseFileLoader", (Throwable) e);
                                        IOUtils.closeQuietly(open);
                                        IoUtils.closeSilently(fileOutputStream);
                                        return false;
                                    } catch (Throwable th) {
                                        th = th;
                                        IOUtils.closeQuietly(open);
                                        IoUtils.closeSilently(fileOutputStream);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileOutputStream = fileOutputStream2;
                                    IOUtils.closeQuietly(open);
                                    IoUtils.closeSilently(fileOutputStream);
                                    throw th;
                                }
                            } catch (IOException e2) {
                                e = e2;
                                Log.w("DataBaseFileLoader", (Throwable) e);
                                IOUtils.closeQuietly(open);
                                IoUtils.closeSilently(fileOutputStream);
                                return false;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            inputStream = open;
                            try {
                                Log.w("DataBaseFileLoader", (Throwable) e);
                                IOUtils.closeQuietly(inputStream);
                                IoUtils.closeSilently(fileOutputStream);
                                return false;
                            } catch (Throwable th3) {
                                th = th3;
                                IOUtils.closeQuietly(inputStream);
                                IoUtils.closeSilently(fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            inputStream = open;
                            IOUtils.closeQuietly(inputStream);
                            IoUtils.closeSilently(fileOutputStream);
                            throw th;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        fileOutputStream = null;
                        inputStream = open;
                        Log.w("DataBaseFileLoader", (Throwable) e);
                        IOUtils.closeQuietly(inputStream);
                        IoUtils.closeSilently(fileOutputStream);
                        return false;
                    } catch (Throwable th5) {
                        th = th5;
                        fileOutputStream = null;
                        inputStream = open;
                        IOUtils.closeQuietly(inputStream);
                        IoUtils.closeSilently(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                    fileOutputStream = null;
                    Log.w("DataBaseFileLoader", (Throwable) e);
                    IOUtils.closeQuietly(inputStream);
                    IoUtils.closeSilently(fileOutputStream);
                    return false;
                } catch (Throwable th6) {
                    th = th6;
                    fileOutputStream = null;
                    IOUtils.closeQuietly(inputStream);
                    IoUtils.closeSilently(fileOutputStream);
                    throw th;
                }
            } catch (Exception e6) {
                Log.w("DataBaseFileLoader", (Throwable) e6);
                return false;
            }
        }

        private boolean isFileCopied() {
            InputStream inputStream;
            if (!new File(this.mDataBasePath, "city.db").exists()) {
                return false;
            }
            File file = new File(this.mDataBasePath, "city.db.md5");
            if (!file.exists()) {
                return false;
            }
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    InputStream open = this.mContext.getAssets().open("city.db.md5");
                    boolean compareStream = compareStream(fileInputStream2, open);
                    IOUtils.closeQuietly(fileInputStream2);
                    IOUtils.closeQuietly(open);
                    return compareStream;
                } catch (IOException e) {
                    e = e;
                    inputStream = null;
                    fileInputStream = fileInputStream2;
                    try {
                        Log.w("DataBaseFileLoader", (Throwable) e);
                        IOUtils.closeQuietly(fileInputStream);
                        IOUtils.closeQuietly(inputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(fileInputStream);
                        IOUtils.closeQuietly(inputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                    fileInputStream = fileInputStream2;
                    IOUtils.closeQuietly(fileInputStream);
                    IOUtils.closeQuietly(inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                Log.w("DataBaseFileLoader", (Throwable) e);
                IOUtils.closeQuietly(fileInputStream);
                IOUtils.closeQuietly(inputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                IOUtils.closeQuietly(fileInputStream);
                IOUtils.closeQuietly(inputStream);
                throw th;
            }
        }

        public String loadFile() {
            if (!isFileCopied() && !copyFile()) {
                return "/data/miui/gallery/city.db";
            }
            return this.mDataBasePath + "/" + "city.db";
        }
    }

    public static class PhotoGpsCache extends HashMap<String, String> {
        public static String makeKey(Coordinate coordinate) {
            if (coordinate == null || !coordinate.isValid()) {
                return "";
            }
            return "" + coordinate.latitude + "," + coordinate.longitude;
        }
    }

    private CitySearcher() {
        openDB(GalleryApp.sGetAndroidContext());
    }

    private synchronized void appendBoundaryList(ConcurrentMap<String, CityDatabaseHelper.CityBoundary> concurrentMap) {
        if (concurrentMap.size() >= 10) {
            this.mCityBoundaries = concurrentMap;
        } else {
            if (concurrentMap.size() + this.mCityBoundaries.size() > 10) {
                ArrayDeque arrayDeque = new ArrayDeque();
                for (String str : this.mCityBoundaries.keySet()) {
                    if (!concurrentMap.containsKey(str)) {
                        arrayDeque.add(str);
                    }
                }
                while (!arrayDeque.isEmpty() && concurrentMap.size() + arrayDeque.size() > 10) {
                    this.mCityBoundaries.remove(arrayDeque.poll());
                }
            }
            this.mCityBoundaries.putAll(concurrentMap);
        }
    }

    private ConcurrentMap<String, CityDatabaseHelper.CityBoundary> findBoundaries(CityDatabaseHelper.CityBoundRectList cityBoundRectList) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ArrayList arrayList = new ArrayList();
        for (String str : cityBoundRectList.keySet()) {
            CityDatabaseHelper.CityBoundary cityBoundary = (CityDatabaseHelper.CityBoundary) this.mCityBoundaries.get(str);
            if (cityBoundary == null) {
                arrayList.add(str);
            } else {
                concurrentHashMap.put(str, cityBoundary);
            }
        }
        ConcurrentMap<String, CityDatabaseHelper.CityBoundary> queryCityBoundary = this.mDbHelper.queryCityBoundary(arrayList);
        if (queryCityBoundary != null && !queryCityBoundary.isEmpty()) {
            concurrentHashMap.putAll(queryCityBoundary);
        }
        return concurrentHashMap;
    }

    private String findInBoundRects(int i, int i2, CityDatabaseHelper.CityBoundRectList cityBoundRectList) {
        ConcurrentMap<String, CityDatabaseHelper.CityBoundary> findBoundaries;
        if (cityBoundRectList == null || cityBoundRectList.isEmpty() || (findBoundaries = findBoundaries(cityBoundRectList)) == null || findBoundaries.isEmpty()) {
            return null;
        }
        appendBoundaryList(findBoundaries);
        for (CityDatabaseHelper.CityBoundary cityBoundary : findBoundaries.values()) {
            if (cityBoundary.containsIntCoordinate(i, i2)) {
                return cityBoundary.cityId;
            }
        }
        return null;
    }

    private synchronized CityDatabaseHelper.CityBoundRectList getCityBoundRects() {
        if (this.mCityBoundRects != null) {
            return this.mCityBoundRects;
        } else if (this.mDbHelper == null) {
            return null;
        } else {
            this.mCityBoundRects = this.mDbHelper.loadCityBoundRects();
            return this.mCityBoundRects;
        }
    }

    public static synchronized CitySearcher getInstance() {
        CitySearcher citySearcher;
        synchronized (CitySearcher.class) {
            if (sThis == null) {
                sThis = new CitySearcher();
            }
            citySearcher = sThis;
        }
        return citySearcher;
    }

    private synchronized boolean isDBOpened() {
        return this.mDbHelper != null && this.mDbHelper.isDbOpened();
    }

    public String decode(Coordinate coordinate) {
        if (coordinate == null || !isDBOpened()) {
            return null;
        }
        try {
            String makeKey = PhotoGpsCache.makeKey(coordinate);
            String str = (String) this.mPhotoGpsCache.get(makeKey);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            int convertIntLat = LocationUtil.convertIntLat(coordinate.latitude);
            int convertIntLat2 = LocationUtil.convertIntLat(coordinate.longitude);
            for (CityDatabaseHelper.CityBoundary cityBoundary : this.mCityBoundaries.values()) {
                if (cityBoundary.containsIntCoordinate(convertIntLat, convertIntLat2)) {
                    return cityBoundary.cityId;
                }
            }
            CityDatabaseHelper.CityBoundRectList cityBoundRects = getCityBoundRects();
            CityDatabaseHelper.CityBoundRectList cityBoundRectList = new CityDatabaseHelper.CityBoundRectList();
            for (CityDatabaseHelper.CityBoundRect cityBoundRect : cityBoundRects.values()) {
                if (cityBoundRect.containsIntCoordinate(convertIntLat, convertIntLat2)) {
                    cityBoundRectList.put(cityBoundRect.cityId, cityBoundRect);
                }
            }
            String findInBoundRects = findInBoundRects(convertIntLat, convertIntLat2, cityBoundRectList);
            if (TextUtils.isEmpty(findInBoundRects)) {
                return null;
            }
            this.mPhotoGpsCache.put(makeKey, findInBoundRects);
            return findInBoundRects;
        } catch (Exception e) {
            Log.w("CitySearcher", (Throwable) e);
            return null;
        }
    }

    public synchronized boolean openDB(Context context) {
        if (this.mDbHelper != null) {
            return this.mDbHelper.isDbOpened();
        }
        this.mDbHelper = new CityDatabaseHelper(context, new DataBaseFileLoader(context).loadFile());
        return this.mDbHelper.isDbOpened();
    }

    public synchronized void preLoadData() {
        if (isDBOpened()) {
            getCityBoundRects();
        }
    }
}
