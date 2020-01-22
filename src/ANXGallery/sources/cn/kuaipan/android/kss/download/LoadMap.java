package cn.kuaipan.android.kss.download;

import android.os.Bundle;
import android.util.Log;
import cn.kuaipan.android.http.IKscTransferListener;
import cn.kuaipan.android.kss.IKssDownloadRequestResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class LoadMap {
    private final BlockSpace[] mBlocks;
    private final ArrayList<Space> mEmptySpaces = new ArrayList<>();
    private IKscTransferListener mListener;
    private final HashMap<Space, LoadRecorder> mRecorders = new HashMap<>();

    class BlockSpace {
        /* access modifiers changed from: private */
        public final long end;
        private final String sha1;
        /* access modifiers changed from: private */
        public final ArrayList<Space> spaces = new ArrayList<>();
        /* access modifiers changed from: private */
        public final long start;
        private int verifyFailCount = 0;
        private VerifyState verifyState;

        public BlockSpace(IKssDownloadRequestResult.Block block, long j) {
            this.sha1 = block.sha1;
            this.start = j;
            this.end = j + block.size;
            reset();
        }

        private boolean _verify(KssAccessor kssAccessor) {
            kssAccessor.lock();
            boolean z = false;
            try {
                String sha12 = kssAccessor.sha1(this.start, this.end - this.start);
                if (sha12 != null) {
                    z = sha12.equalsIgnoreCase(this.sha1);
                }
            } catch (Exception e) {
                Log.w("LoadMap", "Meet exception when verify sha1.", e);
            } catch (Throwable th) {
                kssAccessor.unlock();
                throw th;
            }
            kssAccessor.unlock();
            return z;
        }

        /* access modifiers changed from: private */
        public synchronized Space[] getAllSpaces() {
            return (Space[]) this.spaces.toArray(new Space[this.spaces.size()]);
        }

        /* access modifiers changed from: private */
        public synchronized void reset() {
            this.verifyState = VerifyState.NOT_VERIFY;
            this.spaces.clear();
            this.spaces.add(new Space(this, this.start, this.end));
        }

        /* access modifiers changed from: private */
        public synchronized long size() {
            long j;
            j = 0;
            Iterator<Space> it = this.spaces.iterator();
            while (it.hasNext()) {
                j += it.next().size();
            }
            return j;
        }

        /* access modifiers changed from: private */
        public synchronized boolean tryMerge(Space space) {
            if (space.size() <= 0) {
                this.spaces.remove(space);
                return true;
            }
            Iterator<Space> it = this.spaces.iterator();
            while (it.hasNext()) {
                Space next = it.next();
                if (next != space && next.tryMerge(space)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
            return true;
         */
        public synchronized boolean verify(KssAccessor kssAccessor, boolean z) throws IOException {
            if (this.verifyState == VerifyState.NOT_VERIFY && size() <= 0) {
                if (this.verifyFailCount < 2) {
                    this.verifyState = VerifyState.VERIFING;
                    boolean z2 = false;
                    try {
                        z2 = _verify(kssAccessor);
                        if (!z2) {
                            if (z) {
                                this.verifyFailCount++;
                            }
                            if (this.verifyFailCount >= 2) {
                                throw new IOException("Sha1 verify failed more than MAX_VERIFY_COUNT");
                            }
                        }
                        if (!z2) {
                            this.verifyState = VerifyState.NOT_VERIFY;
                        }
                    } finally {
                        if (z2) {
                            this.verifyState = VerifyState.VERIFIED;
                        } else {
                            this.verifyState = VerifyState.NOT_VERIFY;
                        }
                    }
                }
            }
        }

        public boolean isVerified() {
            return this.verifyState == VerifyState.VERIFIED;
        }

        public synchronized void setSpaces(long[] jArr) {
            this.spaces.clear();
            this.verifyState = VerifyState.NOT_VERIFY;
            if (jArr == null || jArr.length % 2 != 0) {
                this.spaces.add(new Space(this, this.start, this.end));
            }
            int length = jArr.length / 2;
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                this.spaces.add(new Space(this, jArr[i2], jArr[i2 + 1]));
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Block(");
            sb.append(this.start);
            sb.append("-");
            sb.append(this.end);
            sb.append("):");
            if (this.spaces.isEmpty()) {
                sb.append(this.verifyState);
            } else {
                sb.append(Arrays.toString(this.spaces.toArray()));
            }
            return sb.toString();
        }
    }

    class Space {
        private final BlockSpace block;
        /* access modifiers changed from: private */
        public long end;
        /* access modifiers changed from: private */
        public long start;

        public Space(BlockSpace blockSpace, long j, long j2) {
            if (j2 >= j) {
                this.block = blockSpace;
                this.start = j;
                this.end = j2;
                return;
            }
            throw new IndexOutOfBoundsException();
        }

        /* access modifiers changed from: private */
        public Space halve() {
            long j = this.start;
            long j2 = j + ((this.end - j) / 2);
            if (j2 % 1024 > 0) {
                j2 = ((j2 / 1024) + 1) * 1024;
            }
            Space space = new Space(this.block, j2, this.end);
            this.block.spaces.add(this);
            this.end = j2;
            return space;
        }

        /* access modifiers changed from: private */
        public boolean tryMerge() {
            return this.block.tryMerge(this);
        }

        /* access modifiers changed from: private */
        public boolean tryMerge(Space space) {
            if (space.start != this.end) {
                return false;
            }
            this.end = space.end;
            return true;
        }

        /* access modifiers changed from: package-private */
        public long getStart() {
            return this.start;
        }

        /* access modifiers changed from: package-private */
        public void remove(int i) {
            synchronized (this.block) {
                this.start = Math.min(this.start + ((long) i), this.end);
            }
        }

        /* access modifiers changed from: package-private */
        public long size() {
            long j;
            synchronized (this.block) {
                j = this.end - this.start;
            }
            return j;
        }

        public String toString() {
            return this.start + "-" + this.end;
        }
    }

    enum VerifyState {
        NOT_VERIFY,
        VERIFING,
        VERIFIED
    }

    public LoadMap(IKssDownloadRequestResult iKssDownloadRequestResult, IKscTransferListener iKscTransferListener) {
        int blockCount = iKssDownloadRequestResult.getBlockCount();
        this.mBlocks = new BlockSpace[blockCount];
        long j = 0;
        for (int i = 0; i < blockCount; i++) {
            IKssDownloadRequestResult.Block block = iKssDownloadRequestResult.getBlock(i);
            BlockSpace blockSpace = new BlockSpace(block, j);
            this.mBlocks[i] = blockSpace;
            this.mEmptySpaces.addAll(Arrays.asList(blockSpace.getAllSpaces()));
            j += block.size;
        }
        this.mListener = iKscTransferListener;
        if (iKscTransferListener != null) {
            iKscTransferListener.setReceiveTotal(iKssDownloadRequestResult.getTotalSize());
        }
    }

    private Space allocEmptySpace() {
        long j = -1;
        int i = -1;
        for (int i2 = 0; i2 < this.mEmptySpaces.size(); i2++) {
            long size = this.mEmptySpaces.get(i2).size();
            if (j < size) {
                i = i2;
                j = size;
            }
        }
        if (i >= 0) {
            return this.mEmptySpaces.remove(i);
        }
        return null;
    }

    private Space findMaxInUsedSpace() {
        long j = -1;
        Space space = null;
        for (Space next : this.mRecorders.keySet()) {
            long size = next.size();
            if (j < size) {
                space = next;
                j = size;
            }
        }
        return space;
    }

    public long getBlockStart(long j) {
        if (j >= 0) {
            long j2 = -1;
            BlockSpace[] blockSpaceArr = this.mBlocks;
            int length = blockSpaceArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                BlockSpace blockSpace = blockSpaceArr[i];
                if (j >= blockSpace.start && j < blockSpace.end) {
                    j2 = blockSpace.start;
                    break;
                }
                i++;
            }
            if (j2 >= 0) {
                return j2;
            }
            throw new IndexOutOfBoundsException();
        }
        Log.d("LoadMap", "start: " + j);
        throw new IndexOutOfBoundsException();
    }

    public void initSize(long j) {
        synchronized (this) {
            int length = this.mBlocks.length;
            this.mEmptySpaces.clear();
            if (this.mListener != null) {
                this.mListener.setReceivePos(0);
            }
            long j2 = 0;
            int i = 0;
            while (i < length) {
                BlockSpace blockSpace = this.mBlocks[i];
                blockSpace.reset();
                long access$700 = blockSpace.size() + j2;
                if (j >= access$700) {
                    blockSpace.setSpaces(new long[0]);
                    if (this.mListener != null) {
                        this.mListener.received(blockSpace.end - blockSpace.start);
                    }
                } else {
                    blockSpace.setSpaces(new long[]{j2, access$700});
                }
                this.mEmptySpaces.addAll(Arrays.asList(blockSpace.getAllSpaces()));
                i++;
                j2 = access$700;
            }
        }
    }

    public boolean isCompleted() {
        for (BlockSpace blockSpace : this.mBlocks) {
            if (blockSpace.size() > 0 || !blockSpace.isVerified()) {
                return false;
            }
        }
        return true;
    }

    public boolean load(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        try {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("blocks");
            int size = parcelableArrayList.size();
            if (size != this.mBlocks.length) {
                Log.w("LoadMap", "Block count is wrong in kinfo, ignore saved map");
                return false;
            }
            int i = 0;
            while (i < size) {
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i);
                long j = bundle2.getLong("block_start");
                long j2 = bundle2.getLong("block_end");
                BlockSpace blockSpace = this.mBlocks[i];
                if (blockSpace.start == j) {
                    if (blockSpace.end == j2) {
                        i++;
                    }
                }
                Log.w("LoadMap", "Block start/ends is wrong in kinfo, ignore saved map");
                return false;
            }
            synchronized (this) {
                this.mEmptySpaces.clear();
                if (this.mListener != null) {
                    this.mListener.setReceivePos(0);
                }
                long j3 = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    BlockSpace blockSpace2 = this.mBlocks[i2];
                    blockSpace2.reset();
                    blockSpace2.setSpaces(((Bundle) parcelableArrayList.get(i2)).getLongArray("space_info"));
                    this.mEmptySpaces.addAll(Arrays.asList(blockSpace2.getAllSpaces()));
                    if (this.mListener != null) {
                        j3 += (blockSpace2.end - blockSpace2.start) - blockSpace2.size();
                    }
                }
                if (!(this.mListener == null || j3 == 0)) {
                    this.mListener.received(j3);
                }
            }
            return true;
        } catch (Throwable unused) {
            Log.w("LoadMap", "Meet exception Block count is wrony in kinfo, ignore saved map");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized LoadRecorder obtainRecorder() {
        Space allocEmptySpace = allocEmptySpace();
        if (allocEmptySpace != null) {
            LoadRecorder loadRecorder = new LoadRecorder(this, allocEmptySpace);
            this.mRecorders.put(allocEmptySpace, loadRecorder);
            return loadRecorder;
        }
        Space findMaxInUsedSpace = findMaxInUsedSpace();
        if (findMaxInUsedSpace != null) {
            if (findMaxInUsedSpace.size() > 65536) {
                Space access$400 = findMaxInUsedSpace.halve();
                LoadRecorder loadRecorder2 = new LoadRecorder(this, access$400);
                this.mRecorders.put(access$400, loadRecorder2);
                return loadRecorder2;
            }
        }
        return null;
    }

    public void onSpaceRemoved(int i) {
        IKscTransferListener iKscTransferListener = this.mListener;
        if (iKscTransferListener != null) {
            iKscTransferListener.received((long) i);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void recycleRecorder(LoadRecorder loadRecorder) {
        Space space = loadRecorder.getSpace();
        if (this.mRecorders.remove(space) != null) {
            if (!space.tryMerge()) {
                this.mEmptySpaces.add(space);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resetBlock(int i) {
        if (i >= 0) {
            BlockSpace[] blockSpaceArr = this.mBlocks;
            if (i < blockSpaceArr.length) {
                BlockSpace blockSpace = blockSpaceArr[i];
                synchronized (blockSpace) {
                    for (Space space : blockSpace.getAllSpaces()) {
                        LoadRecorder remove = this.mRecorders.remove(space);
                        if (remove != null) {
                            remove.recycle();
                        }
                        this.mEmptySpaces.remove(space);
                    }
                    blockSpace.reset();
                    this.mEmptySpaces.addAll(Arrays.asList(blockSpace.getAllSpaces()));
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void save(Bundle bundle) {
        if (bundle != null) {
            ArrayList arrayList = new ArrayList(r0);
            for (BlockSpace blockSpace : this.mBlocks) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("block_start", blockSpace.start);
                bundle2.putLong("block_end", blockSpace.end);
                ArrayList access$800 = blockSpace.spaces;
                int size = access$800.size();
                long[] jArr = new long[(size * 2)];
                for (int i = 0; i < size; i++) {
                    Space space = (Space) access$800.get(i);
                    int i2 = i * 2;
                    jArr[i2] = space.start;
                    jArr[i2 + 1] = space.end;
                }
                bundle2.putLongArray("space_info", jArr);
                arrayList.add(bundle2);
            }
            bundle.putParcelableArrayList("blocks", arrayList);
        }
    }

    public String toString() {
        return Arrays.toString(this.mBlocks);
    }

    /* access modifiers changed from: package-private */
    public void verify(KssAccessor kssAccessor, boolean z) throws IOException {
        int i = 0;
        while (true) {
            BlockSpace[] blockSpaceArr = this.mBlocks;
            if (i < blockSpaceArr.length) {
                BlockSpace blockSpace = blockSpaceArr[i];
                if (!blockSpace.verify(kssAccessor, z)) {
                    resetBlock(i);
                    IKscTransferListener iKscTransferListener = this.mListener;
                    if (iKscTransferListener != null) {
                        iKscTransferListener.received(blockSpace.start - blockSpace.end);
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }
}
