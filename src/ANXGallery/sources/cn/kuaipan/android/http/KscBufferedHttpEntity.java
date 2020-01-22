package cn.kuaipan.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

public class KscBufferedHttpEntity extends HttpEntityWrapper {
    private final RandomInputBuffer buffer;
    private final IOException err;

    public KscBufferedHttpEntity(HttpEntity httpEntity, NetCacheManager netCacheManager) {
        super(httpEntity);
        RandomInputBuffer randomInputBuffer;
        IOException iOException = null;
        if (!httpEntity.isRepeatable() || httpEntity.getContentLength() < 0) {
            try {
                randomInputBuffer = new RandomInputBuffer(httpEntity.getContent(), netCacheManager);
            } catch (IOException e) {
                randomInputBuffer = null;
                iOException = e;
            }
            this.buffer = randomInputBuffer;
        } else {
            this.buffer = null;
        }
        this.err = iOException;
    }

    public void consumeContent() throws IOException {
        RandomInputBuffer randomInputBuffer = this.buffer;
        if (randomInputBuffer != null) {
            randomInputBuffer.close();
        }
        KscBufferedHttpEntity.super.consumeContent();
    }

    public InputStream getContent() throws IOException {
        RandomInputBuffer randomInputBuffer = this.buffer;
        if (randomInputBuffer != null) {
            return new BufferInputStream(randomInputBuffer);
        }
        IOException iOException = this.err;
        if (iOException == null) {
            return this.wrappedEntity.getContent();
        }
        throw iOException;
    }

    public long getContentLength() {
        return this.wrappedEntity.getContentLength();
    }

    public boolean isChunked() {
        return this.buffer == null && this.wrappedEntity.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.buffer == null && this.wrappedEntity.isStreaming();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        } else if (this.buffer != null) {
            InputStream inputStream = null;
            try {
                inputStream = getContent();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } else {
            this.wrappedEntity.writeTo(outputStream);
        }
    }
}
