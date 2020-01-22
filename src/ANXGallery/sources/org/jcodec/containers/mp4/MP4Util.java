package org.jcodec.containers.mp4;

import java.io.File;
import java.io.IOException;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.containers.mp4.boxes.Box;
import org.jcodec.containers.mp4.boxes.Header;
import org.jcodec.containers.mp4.boxes.MetaBox;

public class MP4Util {

    public static class Atom {
        private Header header;
        /* access modifiers changed from: private */
        public long offset;

        public Atom(Header header2, long j) {
            this.header = header2;
            this.offset = j;
        }

        public Header getHeader() {
            return this.header;
        }

        public Box parseBox(SeekableByteChannel seekableByteChannel) throws IOException {
            seekableByteChannel.setPosition(this.offset + this.header.headerSize());
            return BoxUtil.parseBox(NIOUtils.fetchFromChannel(seekableByteChannel, (int) this.header.getBodySize()), this.header, BoxFactory.getDefault());
        }
    }

    public static Atom findFirstAtom(String str, SeekableByteChannel seekableByteChannel, long j, long j2) throws IOException {
        seekableByteChannel.setPosition(j);
        long j3 = j;
        while (j3 < seekableByteChannel.size() && j3 < j + j2) {
            seekableByteChannel.setPosition(j3);
            Header read = Header.read(NIOUtils.fetchFromChannel(seekableByteChannel, 16));
            if (read == null) {
                return null;
            }
            if (str.equals(read.getFourcc())) {
                return new Atom(read, j3);
            }
            j3 += read.getSize();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    public static MetaBox parseMeta(File file) throws IOException {
        FileChannelWrapper fileChannelWrapper;
        try {
            fileChannelWrapper = NIOUtils.readableChannel(file);
            try {
                MetaBox parseMetaChannel = parseMetaChannel(fileChannelWrapper);
                if (fileChannelWrapper != null) {
                    fileChannelWrapper.close();
                }
                return parseMetaChannel;
            } catch (Throwable th) {
                th = th;
                if (fileChannelWrapper != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannelWrapper = null;
            if (fileChannelWrapper != null) {
                fileChannelWrapper.close();
            }
            throw th;
        }
    }

    public static MetaBox parseMetaChannel(SeekableByteChannel seekableByteChannel) throws IOException {
        Atom findFirstAtom = findFirstAtom("moov", seekableByteChannel, 0, seekableByteChannel.size());
        if (findFirstAtom == null) {
            return null;
        }
        long headerSize = findFirstAtom.getHeader().headerSize();
        Atom findFirstAtom2 = findFirstAtom(MetaBox.fourcc(), seekableByteChannel, findFirstAtom.offset + headerSize, findFirstAtom.getHeader().getSize() - headerSize);
        if (findFirstAtom2 != null && findFirstAtom2.getHeader().getBodySize() < 131072) {
            return (MetaBox) findFirstAtom2.parseBox(seekableByteChannel);
        }
        return null;
    }
}
