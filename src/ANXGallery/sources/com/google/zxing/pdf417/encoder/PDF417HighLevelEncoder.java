package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final byte[] MIXED = new byte[128];
    private static final byte[] PUNCTUATION = new byte[128];
    private static final byte[] TEXT_MIXED_RAW;
    private static final byte[] TEXT_PUNCTUATION_RAW;

    static {
        byte[] bArr = new byte[30];
        byte b = 0;
        bArr[0] = 48;
        bArr[1] = 49;
        bArr[2] = 50;
        bArr[3] = 51;
        bArr[4] = 52;
        bArr[5] = 53;
        bArr[6] = 54;
        bArr[7] = 55;
        bArr[8] = 56;
        bArr[9] = 57;
        bArr[10] = 38;
        bArr[11] = 13;
        bArr[12] = 9;
        bArr[13] = 44;
        bArr[14] = 58;
        bArr[15] = 35;
        bArr[16] = 45;
        bArr[17] = 46;
        bArr[18] = 36;
        bArr[19] = 47;
        bArr[20] = 43;
        bArr[21] = 37;
        bArr[22] = 42;
        bArr[23] = 61;
        bArr[24] = 94;
        bArr[26] = 32;
        TEXT_MIXED_RAW = bArr;
        byte[] bArr2 = new byte[30];
        bArr2[0] = 59;
        bArr2[1] = 60;
        bArr2[2] = 62;
        bArr2[3] = 64;
        bArr2[4] = 91;
        bArr2[5] = 92;
        bArr2[6] = 93;
        bArr2[7] = 95;
        bArr2[8] = 96;
        bArr2[9] = 126;
        bArr2[10] = 33;
        bArr2[11] = 13;
        bArr2[12] = 9;
        bArr2[13] = 44;
        bArr2[14] = 58;
        bArr2[15] = 10;
        bArr2[16] = 45;
        bArr2[17] = 46;
        bArr2[18] = 36;
        bArr2[19] = 47;
        bArr2[20] = 34;
        bArr2[21] = 124;
        bArr2[22] = 42;
        bArr2[23] = 40;
        bArr2[24] = 41;
        bArr2[25] = 63;
        bArr2[26] = 123;
        bArr2[27] = 125;
        bArr2[28] = 39;
        TEXT_PUNCTUATION_RAW = bArr2;
        Arrays.fill(MIXED, (byte) -1);
        byte b2 = 0;
        while (true) {
            byte[] bArr3 = TEXT_MIXED_RAW;
            if (b2 >= bArr3.length) {
                break;
            }
            byte b3 = bArr3[b2];
            if (b3 > 0) {
                MIXED[b3] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr4 = TEXT_PUNCTUATION_RAW;
            if (b < bArr4.length) {
                byte b4 = bArr4[b];
                if (b4 > 0) {
                    PUNCTUATION[b4] = b;
                }
                b = (byte) (b + 1);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r3 = r3 + 1;
     */
    private static int determineConsecutiveBinaryCount(CharSequence charSequence, byte[] bArr, int i) throws WriterException {
        int i2;
        int length = charSequence.length();
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < 13 && isDigit(charAt) && (i2 = i3 + i4) < length) {
                charAt = charSequence.charAt(i2);
            }
            if (i4 >= 13) {
                return i3 - i;
            }
            char charAt2 = charSequence.charAt(i3);
            if (bArr[i3] != 63 || charAt2 == '?') {
                i3++;
            } else {
                throw new WriterException("Non-encodable character detected: " + charAt2 + " (Unicode: " + charAt2 + ')');
            }
        }
        return i3 - i;
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
            if (i3 >= 13) {
                return (i2 - i) - i3;
            }
            if (i3 <= 0) {
                if (!isText(charSequence.charAt(i2))) {
                    break;
                }
                i2++;
            }
        }
        return i2 - i;
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        int i5 = i2;
        StringBuilder sb2 = sb;
        if (i5 == 1 && i3 == 0) {
            sb2.append(913);
        } else {
            if (i5 % 6 == 0) {
                sb2.append(924);
            } else {
                sb2.append(901);
            }
        }
        if (i5 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i5) - i4 >= 6) {
                long j = 0;
                for (int i6 = 0; i6 < 6; i6++) {
                    j = (j << 8) + ((long) (bArr[i4 + i6] & 255));
                }
                for (int i7 = 0; i7 < 5; i7++) {
                    cArr[i7] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int length = cArr.length - 1; length >= 0; length--) {
                    sb2.append(cArr[length]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i5) {
            sb2.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        CharacterSetECI characterSetECIByName;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null) {
            encodingECI(characterSetECIByName.getValue(), sb);
        }
        int length = str.length();
        byte[] bArr = null;
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append(902);
            encodeNumeric(str, 0, length, sb);
        } else {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i);
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append(902);
                    i3 = 2;
                    encodeNumeric(str, i, determineConsecutiveDigitCount, sb);
                    i += determineConsecutiveDigitCount;
                    i2 = 0;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i);
                    if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                        if (i3 != 0) {
                            sb.append(900);
                            i2 = 0;
                            i3 = 0;
                        }
                        i2 = encodeText(str, i, determineConsecutiveTextCount, sb, i2);
                        i += determineConsecutiveTextCount;
                    } else {
                        if (bArr == null) {
                            bArr = str.getBytes(charset);
                        }
                        int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, bArr, i);
                        if (determineConsecutiveBinaryCount == 0) {
                            determineConsecutiveBinaryCount = 1;
                        }
                        if (determineConsecutiveBinaryCount == 1 && i3 == 0) {
                            encodeBinary(bArr, i, 1, 0, sb);
                        } else {
                            encodeBinary(bArr, i, determineConsecutiveBinaryCount, i3, sb);
                            i2 = 0;
                            i3 = 1;
                        }
                        i += determineConsecutiveBinaryCount;
                    }
                }
            }
        }
        return sb.toString();
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2 - 1) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder(String.valueOf('1'));
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f6 A[EDGE_INSN: B:68:0x00f6->B:53:0x00f6 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0011 A[SYNTHETIC] */
    private static int encodeText(CharSequence charSequence, int i, int i2, StringBuilder sb, int i3) {
        CharSequence charSequence2 = charSequence;
        int i4 = i2;
        StringBuilder sb2 = sb;
        StringBuilder sb3 = new StringBuilder(i4);
        int i5 = i3;
        int i6 = 0;
        while (true) {
            int i7 = i + i6;
            char charAt = charSequence2.charAt(i7);
            if (i5 != 0) {
                if (i5 != 1) {
                    if (i5 != 2) {
                        if (isPunctuation(charAt)) {
                            sb3.append((char) PUNCTUATION[charAt]);
                        } else {
                            sb3.append(29);
                        }
                    } else if (isMixed(charAt)) {
                        sb3.append((char) MIXED[charAt]);
                    } else if (isAlphaUpper(charAt)) {
                        sb3.append(28);
                    } else if (isAlphaLower(charAt)) {
                        sb3.append(27);
                    } else {
                        int i8 = i7 + 1;
                        if (i8 >= i4 || !isPunctuation(charSequence2.charAt(i8))) {
                            sb3.append(29);
                            sb3.append((char) PUNCTUATION[charAt]);
                        } else {
                            i5 = 3;
                            sb3.append(25);
                        }
                    }
                    i5 = 0;
                } else if (isAlphaLower(charAt)) {
                    if (charAt == ' ') {
                        sb3.append(26);
                    } else {
                        sb3.append((char) (charAt - 'a'));
                    }
                } else if (isAlphaUpper(charAt)) {
                    sb3.append(27);
                    sb3.append((char) (charAt - 'A'));
                } else if (isMixed(charAt)) {
                    sb3.append(28);
                    i5 = 2;
                } else {
                    sb3.append(29);
                    sb3.append((char) PUNCTUATION[charAt]);
                }
                i6++;
                if (i6 < i4) {
                    break;
                }
            } else {
                if (isAlphaUpper(charAt)) {
                    if (charAt == ' ') {
                        sb3.append(26);
                    } else {
                        sb3.append((char) (charAt - 'A'));
                    }
                } else if (isAlphaLower(charAt)) {
                    sb3.append(27);
                } else if (isMixed(charAt)) {
                    sb3.append(28);
                    i5 = 2;
                } else {
                    sb3.append(29);
                    sb3.append((char) PUNCTUATION[charAt]);
                }
                i6++;
                if (i6 < i4) {
                }
            }
            i5 = 1;
        }
        int length = sb3.length();
        char c = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 % 2 != 0) {
                c = (char) ((c * 30) + sb3.charAt(i9));
                sb2.append(c);
            } else {
                c = sb3.charAt(i9);
            }
        }
        if (length % 2 != 0) {
            sb2.append((char) ((c * 30) + 29));
        }
        return i5;
    }

    private static void encodingECI(int i, StringBuilder sb) throws WriterException {
        if (i >= 0 && i < 900) {
            sb.append(927);
            sb.append((char) i);
        } else if (i < 810900) {
            sb.append(926);
            sb.append((char) ((i / 900) - 1));
            sb.append((char) (i % 900));
        } else if (i < 811800) {
            sb.append(925);
            sb.append((char) (810900 - i));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i);
        }
    }

    private static boolean isAlphaLower(char c) {
        if (c != ' ') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isAlphaUpper(char c) {
        if (c != ' ') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != -1;
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != -1;
    }

    private static boolean isText(char c) {
        if (c == 9 || c == 10 || c == 13) {
            return true;
        }
        return c >= ' ' && c <= '~';
    }
}
