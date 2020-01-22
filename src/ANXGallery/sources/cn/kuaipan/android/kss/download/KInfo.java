package cn.kuaipan.android.kss.download;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import cn.kuaipan.android.utils.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class KInfo {
    private final Bundle mData = new Bundle();
    private final File mFile;
    private final Properties mProp = new Properties();

    public KInfo(File file) {
        this.mFile = file;
    }

    private static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            bundle.writeToParcel(obtain, 0);
            return Base64.encodeToString(obtain.marshall(), 0);
        } finally {
            obtain.recycle();
        }
    }

    public static File getInfoFile(File file) {
        String parent = file.getParent();
        String str = file.getName() + ".kinfo";
        if (!str.startsWith(".")) {
            str = "." + str;
        }
        return new File(parent, str);
    }

    private static Bundle stringToBundle(String str) {
        byte[] decode = Base64.decode(str, 0);
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            return (Bundle) Bundle.CREATOR.createFromParcel(obtain);
        } finally {
            obtain.recycle();
        }
    }

    public void delete() {
        this.mFile.delete();
    }

    public String getHash() {
        return this.mData.getString("hash");
    }

    public void load() {
        FileInputStream fileInputStream;
        IOException e;
        if (this.mFile.exists()) {
            try {
                fileInputStream = new FileInputStream(this.mFile);
                try {
                    this.mProp.load(fileInputStream);
                    String property = this.mProp.getProperty("data");
                    if (!TextUtils.isEmpty(property)) {
                        Bundle stringToBundle = stringToBundle(property);
                        this.mData.clear();
                        this.mData.putAll(stringToBundle);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.w("KInfo", "Failed load kinfo from " + this.mFile, e);
                        fileInputStream.close();
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            } catch (IOException e3) {
                IOException iOException = e3;
                fileInputStream = null;
                e = iOException;
                Log.w("KInfo", "Failed load kinfo from " + this.mFile, e);
                fileInputStream.close();
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream = null;
                th = th3;
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
            try {
                fileInputStream.close();
            } catch (Throwable unused2) {
            }
        }
    }

    public boolean loadToMap(LoadMap loadMap) {
        return loadMap.load(this.mData.getBundle("load_map"));
    }

    public void save() {
        FileOutputStream fileOutputStream;
        IOException e;
        this.mProp.put("data", bundleToString(this.mData));
        try {
            fileOutputStream = new FileOutputStream(this.mFile);
            try {
                this.mProp.store(fileOutputStream, (String) null);
                fileOutputStream.flush();
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.w("KInfo", "Failed save kinfo to " + this.mFile, e);
                    fileOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            fileOutputStream = null;
            e = iOException;
            Log.w("KInfo", "Failed save kinfo to " + this.mFile, e);
            fileOutputStream.close();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            fileOutputStream = null;
            th = th3;
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
        try {
            fileOutputStream.close();
        } catch (Throwable unused2) {
        }
    }

    public void setHash(String str) {
        this.mData.putString("hash", str);
    }

    public void setLoadMap(LoadMap loadMap) {
        Bundle bundle = new Bundle();
        loadMap.save(bundle);
        this.mData.putBundle("load_map", bundle);
    }
}
