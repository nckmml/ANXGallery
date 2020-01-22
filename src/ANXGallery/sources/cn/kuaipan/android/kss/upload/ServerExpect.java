package cn.kuaipan.android.kss.upload;

import android.util.Log;
import cn.kuaipan.android.http.KscHttpResponse;
import cn.kuaipan.android.kss.KssDef;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class ServerExpect implements KssDef {
    public boolean factoryMode = false;
    public long nextChunkSize = -1;
    public int uploadDelay = -1;

    private static int getInt(Header header) {
        if (header == null) {
            return -1;
        }
        try {
            return Integer.parseInt(header.getValue());
        } catch (NumberFormatException unused) {
            Log.w("ServerExpect", "Failed parser header: " + header);
            return -1;
        }
    }

    public static ServerExpect getServerExpect(KscHttpResponse kscHttpResponse) {
        HttpResponse response = kscHttpResponse == null ? null : kscHttpResponse.getResponse();
        if (response == null) {
            return null;
        }
        ServerExpect serverExpect = new ServerExpect();
        Header firstHeader = response.getFirstHeader("X-Factory-Mode");
        boolean z = false;
        if (firstHeader != null) {
            if (getInt(firstHeader) == 1) {
                z = true;
            }
            serverExpect.factoryMode = z;
            z = true;
        }
        Header firstHeader2 = response.getFirstHeader("X-Upload-Delay");
        if (firstHeader2 != null) {
            serverExpect.uploadDelay = getInt(firstHeader2);
            z = true;
        }
        Header firstHeader3 = response.getFirstHeader("X-Next-Chunk-Size");
        if (firstHeader3 != null) {
            serverExpect.nextChunkSize = (long) getInt(firstHeader3);
            z = true;
        }
        if (z) {
            return serverExpect;
        }
        return null;
    }

    public void validate() {
        long j = this.nextChunkSize;
        if (j >= 0) {
            this.nextChunkSize = j - (j % 65536);
            this.nextChunkSize = Math.min(this.nextChunkSize, MAX_CHUNKSIZE);
            this.nextChunkSize = Math.max(this.nextChunkSize, 65536);
        }
        int i = this.uploadDelay;
        if (i > 0 && !this.factoryMode) {
            this.uploadDelay = Math.min(i, 300);
        }
    }
}
