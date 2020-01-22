package com.google.common.cache;

import java.util.Random;
import sun.misc.Unsafe;

abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;

    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;
        volatile long value;

        static {
            try {
                UNSAFE = Striped64.getUnsafe();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        Cell(long j) {
            this.value = j;
        }

        /* access modifiers changed from: package-private */
        public final boolean cas(long j, long j2) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j, j2);
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            Class<Striped64> cls = Striped64.class;
            baseOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.cache.Striped64.AnonymousClass1());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    public static Unsafe getUnsafe() {
        return Unsafe.getUnsafe();
    }

    /* access modifiers changed from: package-private */
    public final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    /* access modifiers changed from: package-private */
    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* access modifiers changed from: package-private */
    public abstract long fn(long j, long j2);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x008d, code lost:
        if (r1.cells != r9) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008f, code lost:
        r8 = new com.google.common.cache.Striped64.Cell[(r10 << 1)];
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0094, code lost:
        if (r11 >= r10) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0096, code lost:
        r8[r11] = r9[r11];
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009d, code lost:
        r1.cells = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00ec A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0022 A[SYNTHETIC] */
    public final void retryUpdate(long j, int[] iArr, boolean z) {
        int[] iArr2;
        int i;
        boolean z2;
        int length;
        boolean z3;
        int length2;
        long j2 = j;
        if (iArr == null) {
            iArr2 = new int[1];
            threadHashCode.set(iArr2);
            i = rng.nextInt();
            if (i == 0) {
                i = 1;
            }
            iArr2[0] = i;
        } else {
            i = iArr[0];
            iArr2 = iArr;
        }
        boolean z4 = z;
        while (true) {
            boolean z5 = false;
            while (true) {
                Cell[] cellArr = this.cells;
                if (cellArr != null && (length = cellArr.length) > 0) {
                    Cell cell = cellArr[(length - 1) & i];
                    if (cell != null) {
                        if (!z4) {
                            z4 = true;
                        } else {
                            long j3 = cell.value;
                            if (!cell.cas(j3, fn(j3, j2))) {
                                if (length < NCPU && this.cells == cellArr) {
                                    if (!z5) {
                                        z5 = true;
                                    } else if (this.busy == 0 && casBusy()) {
                                        try {
                                            break;
                                        } finally {
                                            this.busy = 0;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                        int i2 = i ^ (i << 13);
                        int i3 = i2 ^ (i2 >>> 17);
                        i = i3 ^ (i3 << 5);
                        iArr2[0] = i;
                    } else if (this.busy == 0) {
                        Cell cell2 = new Cell(j2);
                        if (this.busy == 0 && casBusy()) {
                            try {
                                Cell[] cellArr2 = this.cells;
                                if (cellArr2 != null && (length2 = cellArr2.length) > 0) {
                                    int i4 = (length2 - 1) & i;
                                    if (cellArr2[i4] == null) {
                                        cellArr2[i4] = cell2;
                                        z3 = true;
                                        if (!z3) {
                                            return;
                                        }
                                    }
                                }
                                z3 = false;
                                if (!z3) {
                                }
                            } finally {
                                this.busy = 0;
                            }
                        }
                    }
                    z5 = false;
                    int i22 = i ^ (i << 13);
                    int i32 = i22 ^ (i22 >>> 17);
                    i = i32 ^ (i32 << 5);
                    iArr2[0] = i;
                } else if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                    try {
                        if (this.cells == cellArr) {
                            Cell[] cellArr3 = new Cell[2];
                            cellArr3[i & 1] = new Cell(j2);
                            this.cells = cellArr3;
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            return;
                        }
                    } finally {
                        this.busy = 0;
                    }
                } else {
                    long j4 = this.base;
                    if (casBase(j4, fn(j4, j2))) {
                        return;
                    }
                }
            }
        }
    }
}
