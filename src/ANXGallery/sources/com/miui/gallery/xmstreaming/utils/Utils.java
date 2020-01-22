package com.miui.gallery.xmstreaming.utils;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.miui.gallery.util.BaseMiscUtil;
import com.miui.gallery.util.StaticContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.keyczar.Keyczar;

public class Utils {
    private static final String ASSETS_START = "assets:/";

    public static String readFile(String str) {
        AssetManager assetManager = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(ASSETS_START)) {
            assetManager = StaticContext.sGetAndroidContext().getAssets();
        }
        return readFile(str, assetManager);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0040=Splitter:B:18:0x0040, B:24:0x0049=Splitter:B:24:0x0049} */
    private static String readFile(String str, AssetManager assetManager) {
        InputStream inputStream;
        FileNotFoundException e;
        IOException e2;
        InputStream inputStream2;
        InputStream inputStream3;
        InputStream inputStream4 = null;
        if (assetManager == null) {
            try {
                inputStream2 = new FileInputStream(new File(str));
            } catch (FileNotFoundException e3) {
                e = e3;
                inputStream3 = null;
                e.printStackTrace();
                BaseMiscUtil.closeSilently(inputStream);
                return null;
            } catch (IOException e4) {
                e2 = e4;
                inputStream = null;
                try {
                    e2.printStackTrace();
                    BaseMiscUtil.closeSilently(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStream4 = inputStream;
                    BaseMiscUtil.closeSilently(inputStream4);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                BaseMiscUtil.closeSilently(inputStream4);
                throw th;
            }
        } else {
            inputStream2 = assetManager.open(str.replace(ASSETS_START, ""));
        }
        try {
            byte[] bArr = new byte[inputStream2.available()];
            inputStream2.read(bArr);
            String str2 = new String(bArr, Keyczar.DEFAULT_ENCODING);
            BaseMiscUtil.closeSilently(inputStream2);
            return str2;
        } catch (FileNotFoundException e5) {
            FileNotFoundException fileNotFoundException = e5;
            inputStream3 = inputStream2;
            e = fileNotFoundException;
            e.printStackTrace();
            BaseMiscUtil.closeSilently(inputStream);
            return null;
        } catch (IOException e6) {
            IOException iOException = e6;
            inputStream = inputStream2;
            e2 = iOException;
            e2.printStackTrace();
            BaseMiscUtil.closeSilently(inputStream);
            return null;
        } catch (Throwable th3) {
            inputStream4 = inputStream2;
            th = th3;
            BaseMiscUtil.closeSilently(inputStream4);
            throw th;
        }
    }
}
