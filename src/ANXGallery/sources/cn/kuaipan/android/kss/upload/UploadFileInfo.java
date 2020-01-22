package cn.kuaipan.android.kss.upload;

import android.text.TextUtils;
import android.util.Log;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.exception.KscRuntimeException;
import cn.kuaipan.android.kss.KssDef;
import cn.kuaipan.android.utils.Encode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadFileInfo implements KssDef {
    private final ArrayList<BlockInfo> mBlockInfos = new ArrayList<>();
    private String mSha1;

    public static class BlockInfo {
        public final String md5;
        public final String sha1;
        public final int size;

        private BlockInfo(String str, String str2, int i) {
            this.sha1 = str;
            this.md5 = str2;
            this.size = i;
        }
    }

    private UploadFileInfo() {
    }

    public UploadFileInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mSha1 = jSONObject.optString("sha1");
            JSONArray optJSONArray = jSONObject.optJSONArray("block_infos");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    String str2 = null;
                    String optString = optJSONObject == null ? null : optJSONObject.optString("sha1");
                    if (optJSONObject != null) {
                        str2 = optJSONObject.optString("md5");
                    }
                    int i2 = -1;
                    if (optJSONObject != null) {
                        i2 = optJSONObject.optInt("size", -1);
                    }
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(str2) && i2 >= 0) {
                        appendBlock(optString, str2, (long) i2);
                    }
                }
            }
        } catch (JSONException e) {
            Log.w("UploadFileInfo", "Failed parser UploadFileInfo from a String. The String:" + str, e);
        }
    }

    private JSONArray getBlockInfos() {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<BlockInfo> it = this.mBlockInfos.iterator();
            while (it.hasNext()) {
                BlockInfo next = it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sha1", next.sha1);
                jSONObject.put("md5", next.md5);
                jSONObject.put("size", next.size);
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Throwable unused) {
            Log.w("UploadFileInfo", "Failed generate Json String for UploadRequestInfo");
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x012e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0131, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0132, code lost:
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0135, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0136, code lost:
        r1 = null;
        r2 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0135 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:1:0x0004] */
    public static UploadFileInfo getFileInfo(File file) throws KscException, InterruptedException {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        File file2 = file;
        try {
            FileInputStream fileInputStream3 = new FileInputStream(file2);
            try {
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                MessageDigest instance = MessageDigest.getInstance("SHA1");
                MessageDigest instance2 = MessageDigest.getInstance("SHA1");
                MessageDigest instance3 = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[8192];
                int i = 1;
                long j = 0;
                while (true) {
                    int read = fileInputStream3.read(bArr);
                    fileInputStream = fileInputStream3;
                    if (read < 0) {
                        break;
                    }
                    j += (long) read;
                    try {
                        instance.update(bArr, 0, read);
                        long j2 = ((long) i) * 4194304;
                        if (j < j2) {
                            instance2.update(bArr, 0, read);
                            instance3.update(bArr, 0, read);
                            fileInputStream3 = fileInputStream;
                        } else {
                            int i2 = read - ((int) (j - j2));
                            int i3 = i + 1;
                            StringBuilder sb = new StringBuilder();
                            sb.append("blockoffset: ");
                            sb.append(i2);
                            sb.append(" len: ");
                            sb.append(read);
                            sb.append(" pos: ");
                            sb.append(j);
                            sb.append(" blockIndex");
                            sb.append(i3);
                            sb.append(bArr);
                            sb.append(" blockOffset > input.length: ");
                            int i4 = i3;
                            sb.append(((long) i2) > ((long) bArr.length));
                            Log.d("UploadFileInfo", sb.toString());
                            instance2.update(bArr, 0, i2);
                            instance3.update(bArr, 0, i2);
                            uploadFileInfo.appendBlock(Encode.byteArrayToHexString(instance2.digest()), Encode.byteArrayToHexString(instance3.digest()), 4194304);
                            if (read > i2) {
                                int i5 = read - i2;
                                instance2.update(bArr, i2, i5);
                                instance3.update(bArr, i2, i5);
                            }
                            fileInputStream3 = fileInputStream;
                            i = i4;
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e = e;
                        FileInputStream fileInputStream4 = fileInputStream;
                        throw new KscRuntimeException(500005, (Throwable) e);
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                        String str = null;
                        throw KscException.newException(e, str);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            fileInputStream.close();
                        } catch (Throwable unused) {
                        }
                        throw th;
                    }
                }
                long j3 = ((long) i) * 4194304;
                if (j3 > j && j3 < j + 4194304) {
                    uploadFileInfo.appendBlock(Encode.byteArrayToHexString(instance2.digest()), Encode.byteArrayToHexString(instance3.digest()), j - (((long) (i - 1)) * 4194304));
                } else if (j == 0) {
                    throw new KscRuntimeException(500003, file2 + " read error.");
                }
                uploadFileInfo.setSha1(Encode.byteArrayToHexString(instance.digest()));
                try {
                    fileInputStream.close();
                } catch (Throwable unused2) {
                }
                return uploadFileInfo;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                fileInputStream = fileInputStream3;
                FileInputStream fileInputStream42 = fileInputStream;
                throw new KscRuntimeException(500005, (Throwable) e);
            } catch (IOException e4) {
                e = e4;
                fileInputStream = fileInputStream3;
                fileInputStream2 = fileInputStream;
                String str2 = null;
                throw KscException.newException(e, str2);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream3;
                fileInputStream.close();
                throw th;
            }
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new KscRuntimeException(500005, (Throwable) e);
        } catch (IOException e6) {
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = fileInputStream2;
            fileInputStream.close();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void appendBlock(String str, String str2, long j) {
        this.mBlockInfos.add(new BlockInfo(str, str2, (int) j));
    }

    public BlockInfo getBlockInfo(int i) {
        if (i >= this.mBlockInfos.size()) {
            return null;
        }
        return this.mBlockInfos.get(i);
    }

    public String getKssString() {
        JSONArray blockInfos = getBlockInfos();
        if (blockInfos == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("block_infos", blockInfos);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return String.valueOf(jSONObject);
        }
    }

    public String getSha1() {
        return this.mSha1;
    }

    /* access modifiers changed from: package-private */
    public void setSha1(String str) {
        this.mSha1 = str;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("block_infos", getBlockInfos());
            jSONObject.put("sha1", this.mSha1);
        } catch (Throwable unused) {
        }
        return String.valueOf(jSONObject);
    }
}
