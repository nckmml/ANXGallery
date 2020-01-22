package com.google.common.primitives;

public final class Ints {
    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Reader.READ_DONE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }
}
