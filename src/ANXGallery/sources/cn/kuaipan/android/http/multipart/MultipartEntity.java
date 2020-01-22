package cn.kuaipan.android.http.multipart;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

public class MultipartEntity extends AbstractHttpEntity {
    private static byte[] MULTIPART_CHARS = EncodingUtils.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private boolean contentConsumed = false;
    private byte[] multipartBoundary;
    private HttpParams params;
    protected Part[] parts;

    public MultipartEntity(Part[] partArr) {
        setContentType("multipart/form-data");
        if (partArr != null) {
            this.parts = partArr;
            this.params = null;
            return;
        }
        throw new IllegalArgumentException("parts cannot be null");
    }

    private static byte[] generateMultipartBoundary() {
        Random random = new Random();
        byte[] bArr = new byte[(random.nextInt(11) + 30)];
        for (int i = 0; i < bArr.length; i++) {
            byte[] bArr2 = MULTIPART_CHARS;
            bArr[i] = bArr2[random.nextInt(bArr2.length)];
        }
        return bArr;
    }

    public void appendPart(Part[] partArr) {
        if (partArr != null && partArr.length > 0) {
            Part[] partArr2 = this.parts;
            if (partArr2 == null || partArr2.length <= 0) {
                this.parts = partArr;
                return;
            }
            this.parts = new Part[(partArr2.length + partArr.length)];
            System.arraycopy(partArr2, 0, this.parts, 0, partArr2.length);
            System.arraycopy(partArr, 0, this.parts, partArr2.length, partArr.length);
        }
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        if (isRepeatable() || !this.contentConsumed) {
            this.contentConsumed = true;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Part.sendParts(byteArrayOutputStream, this.parts, this.multipartBoundary);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
        throw new IllegalStateException("Content has been consumed");
    }

    public long getContentLength() {
        try {
            return Part.getLengthOfParts(this.parts, getMultipartBoundary());
        } catch (Exception e) {
            Log.e("Multipart", "An exception occurred while getting the length of the parts", e);
            return 0;
        }
    }

    public Header getContentType() {
        StringBuffer stringBuffer = new StringBuffer("multipart/form-data");
        stringBuffer.append("; boundary=");
        stringBuffer.append(EncodingUtils.getAsciiString(getMultipartBoundary()));
        return new BasicHeader("Content-Type", stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public byte[] getMultipartBoundary() {
        if (this.multipartBoundary == null) {
            String str = null;
            HttpParams httpParams = this.params;
            if (httpParams != null) {
                str = (String) httpParams.getParameter("http.method.multipart.boundary");
            }
            if (str != null) {
                this.multipartBoundary = EncodingUtils.getAsciiBytes(str);
            } else {
                this.multipartBoundary = generateMultipartBoundary();
            }
        }
        return this.multipartBoundary;
    }

    public boolean isRepeatable() {
        int i = 0;
        while (true) {
            Part[] partArr = this.parts;
            if (i >= partArr.length) {
                return true;
            }
            if (!partArr[i].isRepeatable()) {
                return false;
            }
            i++;
        }
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Part.sendParts(outputStream, this.parts, getMultipartBoundary());
    }
}
