package cn.kuaipan.android.http.multipart;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class StringPart extends PartBase {
    private byte[] content;
    private String value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringPart(String str, String str2, String str3) {
        super(str, "text/plain", str3 == null ? "US-ASCII" : str3, "8bit");
        if (str2 == null) {
            throw new IllegalArgumentException("Value may not be null");
        } else if (str2.indexOf(0) == -1) {
            this.value = str2;
        } else {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        }
    }

    private byte[] getContent() {
        if (this.content == null) {
            this.content = EncodingUtils.getBytes(this.value, getCharSet());
        }
        return this.content;
    }

    /* access modifiers changed from: protected */
    public long lengthOfData() {
        return (long) getContent().length;
    }

    /* access modifiers changed from: protected */
    public void sendData(OutputStream outputStream) throws IOException {
        outputStream.write(getContent());
    }
}
