package com.google.zxing.qrcode.encoder;

final class MaskUtil {
    static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i;
            byte b = -1;
            int i4 = 0;
            for (int i5 = 0; i5 < width; i5++) {
                byte b2 = z ? array[i2][i5] : array[i5][i2];
                if (b2 == b) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i3 += (i4 - 5) + 3;
                    }
                    i4 = 1;
                    b = b2;
                }
            }
            if (i4 >= 5) {
                i3 += (i4 - 5) + 3;
            }
            i = i3;
        }
        return i;
    }

    static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height - 1) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < width - 1) {
                byte b = array[i][i4];
                int i5 = i4 + 1;
                if (b == array[i][i5]) {
                    int i6 = i + 1;
                    if (b == array[i6][i4] && b == array[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return i2 * 3;
    }

    static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            int i3 = i2;
            for (int i4 = 0; i4 < width; i4++) {
                byte[] bArr = array[i];
                int i5 = i4 + 6;
                if (i5 < width && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (isWhiteHorizontal(bArr, i4 - 4, i4) || isWhiteHorizontal(bArr, i4 + 7, i4 + 11))) {
                    i3++;
                }
                int i6 = i + 6;
                if (i6 < height && array[i][i4] == 1 && array[i + 1][i4] == 0 && array[i + 2][i4] == 1 && array[i + 3][i4] == 1 && array[i + 4][i4] == 1 && array[i + 5][i4] == 0 && array[i6][i4] == 1 && (isWhiteVertical(array, i4, i - 4, i) || isWhiteVertical(array, i4, i + 7, i + 11))) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2 * 40;
    }

    static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            byte[] bArr = array[i];
            int i3 = i2;
            for (int i4 = 0; i4 < width; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        int height2 = byteMatrix.getHeight() * byteMatrix.getWidth();
        return ((Math.abs((i2 * 2) - height2) * 10) / height2) * 10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        r3 = r3 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        r1 = r3 & 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        if (r1 != 0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        r1 = r1 & 1;
     */
    static boolean getDataMaskBit(int i, int i2, int i3) {
        int i4;
        int i5;
        switch (i) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i3 /= 2;
                i2 /= 3;
                break;
            case 5:
                int i6 = i3 * i2;
                i4 = (i6 & 1) + (i6 % 3);
                break;
            case 6:
                int i7 = i3 * i2;
                i5 = (i7 & 1) + (i7 % 3);
                break;
            case 7:
                i5 = ((i3 * i2) % 3) + ((i3 + i2) & 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + i);
        }
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (i >= 0 && i < bArr.length && bArr[i] == 1) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (i2 >= 0 && i2 < bArr.length && bArr[i2][i] == 1) {
                return false;
            }
            i2++;
        }
        return true;
    }
}
