package cn.kuaipan.android.http.multipart;

import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class FilePart extends PartBase {
    private static final byte[] FILE_NAME_BYTES = EncodingUtils.getAsciiBytes("; filename=");
    private PartSource source;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FilePart(String str, PartSource partSource, String str2, String str3) {
        super(str, str2 == null ? "application/octet-stream" : str2, str3 == null ? "ISO-8859-1" : str3, "binary");
        if (partSource != null) {
            this.source = partSource;
            return;
        }
        throw new IllegalArgumentException("Source may not be null");
    }

    public FilePart(String str, File file) throws FileNotFoundException {
        this(str, new FilePartSource(file), (String) null, (String) null);
    }

    public FilePart(String str, String str2, byte[] bArr) {
        this(str, new ByteArrayPartSource(str2, bArr), (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public long lengthOfData() {
        return this.source.getLength();
    }

    /* access modifiers changed from: protected */
    public void sendData(OutputStream outputStream) throws IOException {
        if (lengthOfData() == 0) {
            Log.d("FilePart", "No data to send.");
            return;
        }
        byte[] bArr = new byte[4096];
        InputStream createInputStream = this.source.createInputStream();
        while (true) {
            try {
                int read = createInputStream.read(bArr);
                if (read >= 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            } finally {
                createInputStream.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void sendDispositionHeader(OutputStream outputStream) throws IOException {
        super.sendDispositionHeader(outputStream);
        String fileName = this.source.getFileName();
        if (fileName != null) {
            outputStream.write(FILE_NAME_BYTES);
            outputStream.write(QUOTE_BYTES);
            outputStream.write(EncodingUtils.getAsciiBytes(fileName));
            outputStream.write(QUOTE_BYTES);
        }
    }
}
