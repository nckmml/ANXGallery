package org.jcodec.containers.mp4.boxes;

import androidx.annotation.Keep;
import java.nio.ByteBuffer;
import org.jcodec.containers.mp4.Boxes;

@Keep
public class KeysBox extends NodeBox {
    private static final String FOURCC = "keys";

    private static class LocalBoxes extends Boxes {
        LocalBoxes() {
            this.mappings.put(MdtaBox.fourcc(), MdtaBox.class);
        }
    }

    public KeysBox(Header header) {
        super(header);
        this.factory = new SimpleBoxFactory(new LocalBoxes());
    }

    public static KeysBox createKeysBox() {
        return new KeysBox(Header.createHeader(FOURCC, 0));
    }

    public static String fourcc() {
        return FOURCC;
    }

    /* access modifiers changed from: protected */
    public void doWrite(ByteBuffer byteBuffer) {
        byteBuffer.putInt(0);
        byteBuffer.putInt(this.boxes.size());
        super.doWrite(byteBuffer);
    }

    public int estimateSize() {
        return super.estimateSize() + 8;
    }

    public void parse(ByteBuffer byteBuffer) {
        byteBuffer.getInt();
        byteBuffer.getInt();
        super.parse(byteBuffer);
    }
}
