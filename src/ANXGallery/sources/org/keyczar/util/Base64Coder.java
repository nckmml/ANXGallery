package org.keyczar.util;

import org.keyczar.exceptions.Base64DecodingException;
import org.keyczar.i18n.Messages;

public class Base64Coder {
    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
    private static final byte[] DECODE = new byte[128];
    private static final char[] WHITESPACE = {9, 10, 13, ' ', 12};

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = DECODE;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        int i3 = 0;
        while (true) {
            char[] cArr = WHITESPACE;
            if (i3 >= cArr.length) {
                break;
            }
            DECODE[cArr[i3]] = -2;
            i3++;
        }
        while (true) {
            char[] cArr2 = ALPHABET;
            if (i < cArr2.length) {
                DECODE[cArr2[i]] = (byte) i;
                i++;
            } else {
                return;
            }
        }
    }

    private Base64Coder() {
    }

    @Deprecated
    public static byte[] decode(String str) throws Base64DecodingException {
        return decodeWebSafe(str);
    }

    public static byte[] decodeMime(String str) throws Base64DecodingException {
        return decodeWebSafe(str.replace('+', '-').replace('/', '_'));
    }

    public static byte[] decodeWebSafe(String str) throws Base64DecodingException {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        if (charArray[length - 1] == '=') {
            length--;
        }
        if (charArray[length - 1] == '=') {
            length--;
        }
        int i = 0;
        for (char isWhiteSpace : charArray) {
            if (isWhiteSpace(isWhiteSpace)) {
                i++;
            }
        }
        int i2 = length - i;
        int i3 = i2 % 4;
        int i4 = (i2 / 4) * 3;
        if (i3 != 1) {
            if (i3 == 2) {
                i4++;
            } else if (i3 == 3) {
                i4 += 2;
            }
            byte[] bArr = new byte[i4];
            int i5 = 0;
            int i6 = 0;
            byte b = 0;
            for (int i7 = 0; i7 < i2 + i; i7++) {
                if (!isWhiteSpace(charArray[i7])) {
                    b = (b << 6) | getByte(charArray[i7]);
                    i5++;
                }
                if (i5 == 4) {
                    int i8 = i6 + 1;
                    bArr[i6] = (byte) (b >> 16);
                    int i9 = i8 + 1;
                    bArr[i8] = (byte) (b >> 8);
                    bArr[i9] = (byte) b;
                    b = 0;
                    i6 = i9 + 1;
                    i5 = 0;
                }
            }
            if (i5 == 2) {
                bArr[i6] = (byte) (b >> 4);
            } else if (i5 == 3) {
                bArr[i6] = (byte) (b >> 10);
                bArr[i6 + 1] = (byte) (b >> 2);
            }
            return bArr;
        }
        throw new Base64DecodingException(Messages.getString("Base64Coder.IllegalLength", Integer.valueOf(i2)));
    }

    @Deprecated
    public static String encode(byte[] bArr) {
        return encodeWebSafe(bArr);
    }

    public static String encodeMime(byte[] bArr, boolean z) {
        String replace = encodeWebSafe(bArr).replace('-', '+').replace('_', '/');
        int length = replace.length() % 4;
        if (length == 0) {
            return replace;
        }
        if (length == 2) {
            return replace + "==";
        } else if (length == 3) {
            return replace + "=";
        } else {
            throw new RuntimeException("Bug in Base64 encoder");
        }
    }

    public static String encodeWebSafe(byte[] bArr) {
        int length = bArr.length / 3;
        int length2 = bArr.length % 3;
        int i = length * 4;
        if (length2 == 1) {
            i += 2;
        } else if (length2 == 2) {
            i += 3;
        }
        char[] cArr = new char[i];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            byte b = ((bArr[i3] & 255) << 16) | ((bArr[i5] & 255) << 8);
            int i7 = i6 + 1;
            byte b2 = b | (bArr[i6] & 255);
            int i8 = i4 + 1;
            char[] cArr2 = ALPHABET;
            cArr[i4] = cArr2[(b2 >> 18) & 63];
            int i9 = i8 + 1;
            cArr[i8] = cArr2[(b2 >> 12) & 63];
            int i10 = i9 + 1;
            cArr[i9] = cArr2[(b2 >> 6) & 63];
            i4 = i10 + 1;
            cArr[i10] = cArr2[b2 & 63];
            i2++;
            i3 = i7;
        }
        if (length2 > 0) {
            int i11 = i3 + 1;
            int i12 = (bArr[i3] & 255) << 16;
            if (length2 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            int i13 = i4 + 1;
            char[] cArr3 = ALPHABET;
            cArr[i4] = cArr3[(i12 >> 18) & 63];
            int i14 = i13 + 1;
            cArr[i13] = cArr3[(i12 >> 12) & 63];
            if (length2 == 2) {
                cArr[i14] = cArr3[(i12 >> 6) & 63];
            }
        }
        return new String(cArr);
    }

    private static byte getByte(int i) throws Base64DecodingException {
        if (i >= 0 && i <= 127) {
            byte[] bArr = DECODE;
            if (bArr[i] != -1) {
                return bArr[i];
            }
        }
        throw new Base64DecodingException(Messages.getString("Base64Coder.IllegalCharacter", Integer.valueOf(i)));
    }

    private static boolean isWhiteSpace(int i) {
        return DECODE[i] == -2;
    }
}
