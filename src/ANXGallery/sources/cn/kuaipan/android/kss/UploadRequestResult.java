package cn.kuaipan.android.kss;

import android.util.Log;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.kss.IKssUploadRequestResult;
import cn.kuaipan.android.utils.ApiDataHelper;
import cn.kuaipan.android.utils.Encode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadRequestResult implements IKssUploadRequestResult, KssDef {
    private IKssUploadRequestResult.Block[] blocks;
    private final String file_meta;
    private String[] node_urls;
    private final byte[] secure_key;

    public UploadRequestResult(Map<String, Object> map) throws KscException {
        this.secure_key = Encode.hexStringToByteArray(ApiDataHelper.asString(map, "secure_key"));
        this.file_meta = ApiDataHelper.asString(map, "file_meta");
        Collection<Map> collection = (Collection) map.get("block_metas");
        int i = 0;
        if (collection != null) {
            this.blocks = new IKssUploadRequestResult.Block[collection.size()];
            int i2 = 0;
            for (Map map2 : collection) {
                boolean z = ApiDataHelper.asNumber(map2.get("is_existed"), 0).intValue() != 0;
                this.blocks[i2] = new IKssUploadRequestResult.Block(ApiDataHelper.asString(map2, z ? "commit_meta" : "block_meta"), z);
                i2++;
            }
        }
        Collection<String> collection2 = (Collection) map.get("node_urls");
        if (collection2 != null) {
            this.node_urls = new String[collection2.size()];
            for (String str : collection2) {
                this.node_urls[i] = str;
                i++;
            }
        }
    }

    public IKssUploadRequestResult.Block getBlock(int i) {
        IKssUploadRequestResult.Block[] blockArr = this.blocks;
        if (blockArr == null || i >= blockArr.length) {
            return null;
        }
        return blockArr[i];
    }

    public int getBlockCount() {
        IKssUploadRequestResult.Block[] blockArr = this.blocks;
        if (blockArr == null) {
            return 0;
        }
        return blockArr.length;
    }

    public String getFileMeta() {
        return this.file_meta;
    }

    public String[] getNodeUrls() {
        return this.node_urls;
    }

    public byte[] getSecureKey() {
        return this.secure_key;
    }

    public boolean isCompleted() {
        IKssUploadRequestResult.Block[] blockArr = this.blocks;
        if (blockArr == null) {
            return true;
        }
        for (IKssUploadRequestResult.Block block : blockArr) {
            if (!block.exist) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            jSONObject.put("secure_key", Encode.byteArrayToHexString(this.secure_key));
            jSONObject.put("file_meta", this.file_meta);
            jSONObject.put("node_urls", this.node_urls != null ? new JSONArray(Arrays.asList(this.node_urls)) : new JSONArray());
            JSONArray jSONArray = new JSONArray();
            if (this.blocks != null) {
                for (IKssUploadRequestResult.Block block : this.blocks) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("is_existed", block.exist ? 1 : 0);
                    if (block.exist) {
                        jSONObject2.put("commit_meta", block.meta);
                    } else {
                        jSONObject2.put("block_meta", block.meta);
                    }
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("block_metas", jSONArray);
        } catch (JSONException unused) {
            Log.w("UploadRequestResult", "Failed generate Json String for UploadRequestResult");
            jSONObject = null;
        }
        return String.valueOf(jSONObject);
    }
}
