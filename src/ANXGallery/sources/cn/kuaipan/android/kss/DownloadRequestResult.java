package cn.kuaipan.android.kss;

import android.util.Log;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.kss.IKssDownloadRequestResult;
import cn.kuaipan.android.utils.ApiDataHelper;
import cn.kuaipan.android.utils.Encode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadRequestResult implements IKssDownloadRequestResult, KssDef {
    private IKssDownloadRequestResult.Block[] blocks;
    private byte[] secure_key;
    private final String stat;

    public DownloadRequestResult(Map<String, Object> map) throws KscException {
        this.stat = ApiDataHelper.asString(map, "stat");
        if ("OK".equalsIgnoreCase(this.stat)) {
            this.secure_key = Encode.hexStringToByteArray(ApiDataHelper.asString(map, "secure_key"));
            Collection<Map> collection = (Collection) map.get("blocks");
            if (collection != null) {
                this.blocks = new IKssDownloadRequestResult.Block[collection.size()];
                int i = 0;
                for (Map map2 : collection) {
                    String asString = ApiDataHelper.asString(map2, "sha1");
                    long longValue = ApiDataHelper.asNumber(map2.get("size"), 0).longValue();
                    String[] strArr = null;
                    Collection<String> collection2 = (Collection) map2.get("urls");
                    if (collection2 != null) {
                        strArr = new String[collection2.size()];
                        int i2 = 0;
                        for (String str : collection2) {
                            strArr[i2] = str;
                            i2++;
                        }
                    }
                    this.blocks[i] = new IKssDownloadRequestResult.Block(asString, strArr, longValue);
                    i++;
                }
            }
        }
    }

    public IKssDownloadRequestResult.Block getBlock(int i) {
        return this.blocks[i];
    }

    public int getBlockCount() {
        IKssDownloadRequestResult.Block[] blockArr = this.blocks;
        if (blockArr == null) {
            return -1;
        }
        return blockArr.length;
    }

    public String[] getBlockUrls(long j) {
        long j2 = 0;
        if (j < 0 || this.blocks == null) {
            return null;
        }
        int i = 0;
        while (true) {
            IKssDownloadRequestResult.Block[] blockArr = this.blocks;
            if (i >= blockArr.length) {
                return null;
            }
            long j3 = blockArr[i].size + j2;
            if (j >= j2 && j < j3) {
                return this.blocks[i].urls;
            }
            i++;
            j2 = j3;
        }
    }

    public String getHash() {
        StringBuilder sb = new StringBuilder();
        IKssDownloadRequestResult.Block[] blockArr = this.blocks;
        sb.append(blockArr == null ? 0 : blockArr.length);
        sb.append(':');
        sb.append(getTotalSize());
        sb.append(':');
        StringBuilder sb2 = new StringBuilder();
        IKssDownloadRequestResult.Block[] blockArr2 = this.blocks;
        if (blockArr2 != null) {
            for (IKssDownloadRequestResult.Block block : blockArr2) {
                sb2.append(block.sha1);
            }
        }
        sb.append(Encode.MD5Encode(sb2.toString().getBytes()));
        return sb.toString();
    }

    public String getMessage() {
        return this.stat;
    }

    public long getModifyTime() {
        return -1;
    }

    public byte[] getSecureKey() {
        return this.secure_key;
    }

    public int getStatus() {
        return "OK".equalsIgnoreCase(this.stat) ? 0 : -1;
    }

    public long getTotalSize() {
        IKssDownloadRequestResult.Block[] blockArr = this.blocks;
        long j = 0;
        if (blockArr == null) {
            return 0;
        }
        for (IKssDownloadRequestResult.Block block : blockArr) {
            j += block.size;
        }
        return j;
    }

    public String toString() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            jSONObject.put("stat", this.stat);
            jSONObject.put("secure_key", Encode.byteArrayToHexString(this.secure_key));
            JSONArray jSONArray = new JSONArray();
            if (this.blocks != null) {
                for (IKssDownloadRequestResult.Block block : this.blocks) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("sha1", block.sha1);
                    jSONObject2.put("size", block.size);
                    jSONObject2.put("urls", block.urls != null ? new JSONArray(Arrays.asList(block.urls)) : new JSONArray());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("blocks", jSONArray);
        } catch (JSONException unused) {
            Log.w("DownloadRequestResult", "Failed generate Json String for UploadRequestResult");
            jSONObject = null;
        }
        return String.valueOf(jSONObject);
    }
}
