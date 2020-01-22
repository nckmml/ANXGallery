package com.miui.gallery.util;

import com.miui.gallery.util.BaseDocumentProviderUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
    private static final byte[] sAesIv = {17, 19, 33, 35, 49, 51, 65, 67, 81, 83, 97, 102, 103, 104, 113, 114};
    private static final byte[] sRandomKey = new byte[16];

    /* access modifiers changed from: private */
    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("IllegalArgument or Key length should be 16.");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        try {
            Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
            instance.init(2, secretKeySpec, new IvParameterSpec(sAesIv));
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            Log.e("CryptoUtil", (Throwable) e);
            return null;
        } catch (NoSuchPaddingException e2) {
            Log.e("CryptoUtil", (Throwable) e2);
            return null;
        } catch (InvalidKeyException e3) {
            Log.e("CryptoUtil", (Throwable) e3);
            return null;
        } catch (InvalidAlgorithmParameterException e4) {
            Log.e("CryptoUtil", (Throwable) e4);
            return null;
        } catch (IllegalBlockSizeException e5) {
            Log.e("CryptoUtil", (Throwable) e5);
            return null;
        } catch (BadPaddingException e6) {
            Log.e("CryptoUtil", (Throwable) e6);
            return null;
        }
    }

    public static boolean decryptFile(String str, String str2, byte[] bArr) {
        boolean z = false;
        if (bArr == null || bArr.length != 16) {
            return false;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        FileInputStream fileInputStream = null;
        try {
            final Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
            instance.init(2, secretKeySpec, new IvParameterSpec(sAesIv));
            final FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                Boolean bool = (Boolean) BaseDocumentProviderUtils.safeWriteFile(str2, new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
                    public Boolean handle(FileOutputStream fileOutputStream) {
                        CipherOutputStream cipherOutputStream = null;
                        try {
                            CipherOutputStream cipherOutputStream2 = new CipherOutputStream(fileOutputStream, instance);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = fileInputStream2.read(bArr);
                                    if (read != -1) {
                                        cipherOutputStream2.write(bArr, 0, read);
                                    } else {
                                        BaseMiscUtil.closeSilently(cipherOutputStream2);
                                        return true;
                                    }
                                }
                            } catch (Exception e) {
                                e = e;
                                cipherOutputStream = cipherOutputStream2;
                                try {
                                    Log.e("CryptoUtil", (Throwable) e);
                                    BaseMiscUtil.closeSilently(cipherOutputStream);
                                    return false;
                                } catch (Throwable th) {
                                    th = th;
                                    BaseMiscUtil.closeSilently(cipherOutputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                cipherOutputStream = cipherOutputStream2;
                                BaseMiscUtil.closeSilently(cipherOutputStream);
                                throw th;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Log.e("CryptoUtil", (Throwable) e);
                            BaseMiscUtil.closeSilently(cipherOutputStream);
                            return false;
                        }
                    }
                });
                if (bool != null && bool.booleanValue()) {
                    z = true;
                }
                BaseMiscUtil.closeSilently(fileInputStream2);
                return z;
            } catch (NoSuchAlgorithmException e) {
                e = e;
                fileInputStream = fileInputStream2;
                Log.e("CryptoUtil", (Throwable) e);
                BaseMiscUtil.closeSilently(fileInputStream);
                return false;
            } catch (NoSuchPaddingException e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                Log.e("CryptoUtil", (Throwable) e);
                BaseMiscUtil.closeSilently(fileInputStream);
                return false;
            } catch (InvalidKeyException e3) {
                e = e3;
                fileInputStream = fileInputStream2;
                Log.e("CryptoUtil", (Throwable) e);
                BaseMiscUtil.closeSilently(fileInputStream);
                return false;
            } catch (InvalidAlgorithmParameterException e4) {
                e = e4;
                fileInputStream = fileInputStream2;
                Log.e("CryptoUtil", (Throwable) e);
                BaseMiscUtil.closeSilently(fileInputStream);
                return false;
            } catch (FileNotFoundException e5) {
                e = e5;
                fileInputStream = fileInputStream2;
                Log.e("CryptoUtil", (Throwable) e);
                BaseMiscUtil.closeSilently(fileInputStream);
                return false;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                BaseMiscUtil.closeSilently(fileInputStream);
                throw th;
            }
        } catch (NoSuchAlgorithmException e6) {
            e = e6;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        } catch (NoSuchPaddingException e7) {
            e = e7;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        } catch (InvalidKeyException e8) {
            e = e8;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        } catch (InvalidAlgorithmParameterException e9) {
            e = e9;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        } catch (FileNotFoundException e10) {
            e = e10;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        } catch (Throwable th2) {
            th = th2;
            BaseMiscUtil.closeSilently(fileInputStream);
            throw th;
        }
    }

    public static boolean decryptFileHeader(String str, String str2, final byte[] bArr) {
        File file;
        if (bArr == null || bArr.length != 16) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            if (ExtraTextUtils.equalsIgnoreCase(str, str2)) {
                file = new File(str2 + "." + System.currentTimeMillis() + ".tmp");
            } else {
                file = new File(str2);
            }
            final FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                Boolean bool = (Boolean) BaseDocumentProviderUtils.safeWriteFile(file.getAbsolutePath(), new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: byte[]} */
                    /* JADX WARNING: Multi-variable type inference failed */
                    public Boolean handle(FileOutputStream fileOutputStream) {
                        FileChannel fileChannel;
                        FileChannel fileChannel2;
                        IOException e;
                        boolean z;
                        try {
                            fileChannel2 = fileInputStream2.getChannel();
                            try {
                                fileChannel = fileOutputStream.getChannel();
                                try {
                                    long size = fileChannel2.size();
                                    long transferTo = fileChannel2.transferTo(0, size, fileChannel);
                                    if (transferTo != size) {
                                        Log.e("CryptoUtil", "transfer error, expect count %s, actual count %s", Long.valueOf(size), Long.valueOf(transferTo));
                                        z = false;
                                    } else {
                                        int max = Math.max((int) Math.min(1024, fileChannel2.size()), 16);
                                        byte[] bArr = new byte[max];
                                        if (((long) fileInputStream2.read(bArr)) != Math.min((long) max, size)) {
                                            Log.e("CryptoUtil", "read encrypted header bytes error");
                                            z = false;
                                        } else {
                                            byte[] access$100 = CryptoUtil.decrypt(bArr, bArr);
                                            if (access$100 != null) {
                                                if (access$100.length == max) {
                                                    if (access$100.length == 16) {
                                                        int i = 15;
                                                        while (i >= 0 && access$100[i] == 0) {
                                                            i--;
                                                        }
                                                        if (i == -1) {
                                                            Log.e("CryptoUtil", "Encrypted file is malformed.");
                                                            z = false;
                                                        } else if (i < 15) {
                                                            max = i + 1;
                                                            byte[] bArr2 = new byte[max];
                                                            System.arraycopy(access$100, 0, bArr2, 0, max);
                                                            access$100 = bArr2.clone();
                                                            fileChannel.truncate((long) max);
                                                        }
                                                    }
                                                    if (fileChannel.write(ByteBuffer.wrap(access$100), 0) != max) {
                                                        Log.e("CryptoUtil", "write decrypted header bytes error");
                                                        z = false;
                                                    } else {
                                                        BaseMiscUtil.closeSilently(fileChannel2);
                                                        BaseMiscUtil.closeSilently(fileChannel);
                                                        return true;
                                                    }
                                                }
                                            }
                                            Log.e("CryptoUtil", "decrypt header bytes error");
                                            z = false;
                                        }
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    try {
                                        Log.e("CryptoUtil", (Throwable) e);
                                        z = false;
                                        BaseMiscUtil.closeSilently(fileChannel2);
                                        BaseMiscUtil.closeSilently(fileChannel);
                                        return z;
                                    } catch (Throwable th) {
                                        th = th;
                                        BaseMiscUtil.closeSilently(fileChannel2);
                                        BaseMiscUtil.closeSilently(fileChannel);
                                        throw th;
                                    }
                                }
                            } catch (IOException e3) {
                                e = e3;
                                fileChannel = null;
                                Log.e("CryptoUtil", (Throwable) e);
                                z = false;
                                BaseMiscUtil.closeSilently(fileChannel2);
                                BaseMiscUtil.closeSilently(fileChannel);
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                fileChannel = null;
                                BaseMiscUtil.closeSilently(fileChannel2);
                                BaseMiscUtil.closeSilently(fileChannel);
                                throw th;
                            }
                        } catch (IOException e4) {
                            fileChannel2 = null;
                            e = e4;
                            fileChannel = null;
                            Log.e("CryptoUtil", (Throwable) e);
                            z = false;
                            BaseMiscUtil.closeSilently(fileChannel2);
                            BaseMiscUtil.closeSilently(fileChannel);
                            return z;
                        } catch (Throwable th3) {
                            th = th3;
                            fileChannel = null;
                            fileChannel2 = null;
                            BaseMiscUtil.closeSilently(fileChannel2);
                            BaseMiscUtil.closeSilently(fileChannel);
                            throw th;
                        }
                        BaseMiscUtil.closeSilently(fileChannel2);
                        BaseMiscUtil.closeSilently(fileChannel);
                        return z;
                    }
                });
                if (bool != null) {
                    if (bool.booleanValue()) {
                        if (!ExtraTextUtils.equalsIgnoreCase(file.getAbsolutePath(), str2)) {
                            boolean renameFile = BaseFileUtils.renameFile(file, new File(str2));
                            BaseMiscUtil.closeSilently(fileInputStream2);
                            return renameFile;
                        }
                        BaseMiscUtil.closeSilently(fileInputStream2);
                        return true;
                    }
                }
                BaseFileUtils.deleteFile(file);
                BaseMiscUtil.closeSilently(fileInputStream2);
                return false;
            } catch (FileNotFoundException e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    Log.e("CryptoUtil", (Throwable) e);
                    BaseMiscUtil.closeSilently(fileInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                BaseMiscUtil.closeSilently(fileInputStream);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        if (!(bArr == null || bArr2 == null || bArr2.length != 16)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(sAesIv);
            try {
                Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
                instance.init(1, secretKeySpec, ivParameterSpec);
                return instance.doFinal(bArr);
            } catch (NoSuchAlgorithmException e) {
                Log.e("CryptoUtil", (Throwable) e);
            } catch (NoSuchPaddingException e2) {
                Log.e("CryptoUtil", (Throwable) e2);
            } catch (InvalidKeyException e3) {
                Log.e("CryptoUtil", (Throwable) e3);
            } catch (InvalidAlgorithmParameterException e4) {
                Log.e("CryptoUtil", (Throwable) e4);
            } catch (IllegalBlockSizeException e5) {
                Log.e("CryptoUtil", (Throwable) e5);
            } catch (BadPaddingException e6) {
                Log.e("CryptoUtil", (Throwable) e6);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: javax.crypto.CipherInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v46, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v47, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v49, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v50, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r6v14 */
    /* JADX WARNING: type inference failed for: r6v15, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r6v30 */
    /* JADX WARNING: type inference failed for: r6v36 */
    /* JADX WARNING: type inference failed for: r6v45 */
    /* JADX WARNING: type inference failed for: r6v52 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x0073=Splitter:B:46:0x0073, B:51:0x007c=Splitter:B:51:0x007c} */
    /* JADX WARNING: Unknown variable types count: 2 */
    public static boolean encryptFile(String str, String str2, byte[] bArr) {
        ? r6;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        ? r62;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        Object obj;
        boolean z = false;
        if (bArr == null || bArr.length != 16) {
            return false;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(sAesIv);
        FileInputStream fileInputStream = null;
        try {
            str3 = str;
            Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                final CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream2, instance);
                try {
                    Boolean bool = (Boolean) BaseDocumentProviderUtils.safeWriteFile(str2, new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
                        public Boolean handle(FileOutputStream fileOutputStream) {
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = cipherInputStream.read(bArr);
                                    if (read == -1) {
                                        return true;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                            } catch (Exception e) {
                                Log.e("CryptoUtil", (Throwable) e);
                                return false;
                            }
                        }
                    });
                    if (bool != null && bool.booleanValue()) {
                        z = true;
                    }
                    BaseMiscUtil.closeSilently(fileInputStream2);
                    r62 = cipherInputStream;
                } catch (NoSuchAlgorithmException e) {
                    e = e;
                    str10 = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    str4 = str10;
                    Log.e("CryptoUtil", (Throwable) e);
                    str8 = str4;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    r62 = str8;
                    BaseMiscUtil.closeSilently(r62);
                    return z;
                } catch (NoSuchPaddingException e2) {
                    e = e2;
                    str11 = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    str5 = str11;
                    Log.e("CryptoUtil", (Throwable) e);
                    str8 = str5;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    r62 = str8;
                    BaseMiscUtil.closeSilently(r62);
                    return z;
                } catch (InvalidKeyException e3) {
                    e = e3;
                    str12 = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    str6 = str12;
                    Log.e("CryptoUtil", (Throwable) e);
                    str8 = str6;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    r62 = str8;
                    BaseMiscUtil.closeSilently(r62);
                    return z;
                } catch (InvalidAlgorithmParameterException e4) {
                    e = e4;
                    str13 = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    str7 = str13;
                    str3 = str7;
                    Log.e("CryptoUtil", (Throwable) e);
                    str8 = str7;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    r62 = str8;
                    BaseMiscUtil.closeSilently(r62);
                    return z;
                } catch (FileNotFoundException e5) {
                    e = e5;
                    str14 = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    str9 = str14;
                    str3 = str9;
                    Log.e("CryptoUtil", (Throwable) e);
                    str8 = str9;
                    str3 = str9;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    r62 = str8;
                    BaseMiscUtil.closeSilently(r62);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    obj = cipherInputStream;
                    fileInputStream = fileInputStream2;
                    r6 = obj;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    BaseMiscUtil.closeSilently(r6);
                    throw th;
                }
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
                str10 = null;
                fileInputStream = fileInputStream2;
                str4 = str10;
                Log.e("CryptoUtil", (Throwable) e);
                str8 = str4;
                BaseMiscUtil.closeSilently(fileInputStream);
                r62 = str8;
                BaseMiscUtil.closeSilently(r62);
                return z;
            } catch (NoSuchPaddingException e7) {
                e = e7;
                str11 = null;
                fileInputStream = fileInputStream2;
                str5 = str11;
                Log.e("CryptoUtil", (Throwable) e);
                str8 = str5;
                BaseMiscUtil.closeSilently(fileInputStream);
                r62 = str8;
                BaseMiscUtil.closeSilently(r62);
                return z;
            } catch (InvalidKeyException e8) {
                e = e8;
                str12 = null;
                fileInputStream = fileInputStream2;
                str6 = str12;
                Log.e("CryptoUtil", (Throwable) e);
                str8 = str6;
                BaseMiscUtil.closeSilently(fileInputStream);
                r62 = str8;
                BaseMiscUtil.closeSilently(r62);
                return z;
            } catch (InvalidAlgorithmParameterException e9) {
                e = e9;
                str13 = null;
                fileInputStream = fileInputStream2;
                str7 = str13;
                str3 = str7;
                Log.e("CryptoUtil", (Throwable) e);
                str8 = str7;
                BaseMiscUtil.closeSilently(fileInputStream);
                r62 = str8;
                BaseMiscUtil.closeSilently(r62);
                return z;
            } catch (FileNotFoundException e10) {
                e = e10;
                str14 = null;
                fileInputStream = fileInputStream2;
                str9 = str14;
                str3 = str9;
                Log.e("CryptoUtil", (Throwable) e);
                str8 = str9;
                str3 = str9;
                BaseMiscUtil.closeSilently(fileInputStream);
                r62 = str8;
                BaseMiscUtil.closeSilently(r62);
                return z;
            } catch (Throwable th2) {
                th = th2;
                obj = null;
                fileInputStream = fileInputStream2;
                r6 = obj;
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(r6);
                throw th;
            }
        } catch (NoSuchAlgorithmException e11) {
            e = e11;
            str4 = null;
            Log.e("CryptoUtil", (Throwable) e);
            str8 = str4;
            BaseMiscUtil.closeSilently(fileInputStream);
            r62 = str8;
            BaseMiscUtil.closeSilently(r62);
            return z;
        } catch (NoSuchPaddingException e12) {
            e = e12;
            str5 = null;
            Log.e("CryptoUtil", (Throwable) e);
            str8 = str5;
            BaseMiscUtil.closeSilently(fileInputStream);
            r62 = str8;
            BaseMiscUtil.closeSilently(r62);
            return z;
        } catch (InvalidKeyException e13) {
            e = e13;
            str6 = null;
            Log.e("CryptoUtil", (Throwable) e);
            str8 = str6;
            BaseMiscUtil.closeSilently(fileInputStream);
            r62 = str8;
            BaseMiscUtil.closeSilently(r62);
            return z;
        } catch (InvalidAlgorithmParameterException e14) {
            e = e14;
            str7 = null;
            str3 = str7;
            Log.e("CryptoUtil", (Throwable) e);
            str8 = str7;
            BaseMiscUtil.closeSilently(fileInputStream);
            r62 = str8;
            BaseMiscUtil.closeSilently(r62);
            return z;
        } catch (FileNotFoundException e15) {
            e = e15;
            str9 = null;
            str3 = str9;
            Log.e("CryptoUtil", (Throwable) e);
            str8 = str9;
            str3 = str9;
            BaseMiscUtil.closeSilently(fileInputStream);
            r62 = str8;
            BaseMiscUtil.closeSilently(r62);
            return z;
        } catch (Throwable th3) {
            th = th3;
            r6 = str3;
            BaseMiscUtil.closeSilently(fileInputStream);
            BaseMiscUtil.closeSilently(r6);
            throw th;
        }
        BaseMiscUtil.closeSilently(r62);
        return z;
    }

    public static boolean encryptFileHeader(String str, String str2, final byte[] bArr) {
        File file;
        if (bArr == null || bArr.length != 16) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            if (ExtraTextUtils.equalsIgnoreCase(str, str2)) {
                file = new File(str2 + "." + System.currentTimeMillis() + ".tmp");
            } else {
                file = new File(str2);
            }
            final FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                Boolean bool = (Boolean) BaseDocumentProviderUtils.safeWriteFile(file.getAbsolutePath(), new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
                    public Boolean handle(FileOutputStream fileOutputStream) {
                        FileChannel fileChannel;
                        FileChannel fileChannel2;
                        IOException e;
                        boolean z;
                        try {
                            fileChannel2 = fileInputStream2.getChannel();
                            try {
                                fileChannel = fileOutputStream.getChannel();
                                try {
                                    long size = fileChannel2.size();
                                    long transferTo = fileChannel2.transferTo(0, size, fileChannel);
                                    if (transferTo != size) {
                                        Log.e("CryptoUtil", "transfer error, expect count %s, actual count %s", Long.valueOf(size), Long.valueOf(transferTo));
                                        z = false;
                                    } else {
                                        int max = Math.max((int) Math.min(1024, size), 16);
                                        byte[] bArr = new byte[max];
                                        if (((long) fileInputStream2.read(bArr)) != Math.min((long) max, size)) {
                                            Log.e("CryptoUtil", "read header bytes error");
                                            z = false;
                                        } else {
                                            byte[] access$000 = CryptoUtil.encrypt(bArr, bArr);
                                            if (access$000 != null) {
                                                if (access$000.length == max) {
                                                    if (fileChannel.write(ByteBuffer.wrap(access$000), 0) != max) {
                                                        Log.e("CryptoUtil", "write encrypted header bytes error");
                                                        z = false;
                                                    } else {
                                                        BaseMiscUtil.closeSilently(fileChannel2);
                                                        BaseMiscUtil.closeSilently(fileChannel);
                                                        return true;
                                                    }
                                                }
                                            }
                                            Log.e("CryptoUtil", "encrypt header bytes error");
                                            z = false;
                                        }
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    try {
                                        Log.e("CryptoUtil", (Throwable) e);
                                        z = false;
                                        BaseMiscUtil.closeSilently(fileChannel2);
                                        BaseMiscUtil.closeSilently(fileChannel);
                                        return z;
                                    } catch (Throwable th) {
                                        th = th;
                                        BaseMiscUtil.closeSilently(fileChannel2);
                                        BaseMiscUtil.closeSilently(fileChannel);
                                        throw th;
                                    }
                                }
                            } catch (IOException e3) {
                                e = e3;
                                fileChannel = null;
                                Log.e("CryptoUtil", (Throwable) e);
                                z = false;
                                BaseMiscUtil.closeSilently(fileChannel2);
                                BaseMiscUtil.closeSilently(fileChannel);
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                fileChannel = null;
                                BaseMiscUtil.closeSilently(fileChannel2);
                                BaseMiscUtil.closeSilently(fileChannel);
                                throw th;
                            }
                        } catch (IOException e4) {
                            fileChannel2 = null;
                            e = e4;
                            fileChannel = null;
                            Log.e("CryptoUtil", (Throwable) e);
                            z = false;
                            BaseMiscUtil.closeSilently(fileChannel2);
                            BaseMiscUtil.closeSilently(fileChannel);
                            return z;
                        } catch (Throwable th3) {
                            th = th3;
                            fileChannel = null;
                            fileChannel2 = null;
                            BaseMiscUtil.closeSilently(fileChannel2);
                            BaseMiscUtil.closeSilently(fileChannel);
                            throw th;
                        }
                        BaseMiscUtil.closeSilently(fileChannel2);
                        BaseMiscUtil.closeSilently(fileChannel);
                        return z;
                    }
                });
                if (bool != null) {
                    if (bool.booleanValue()) {
                        if (!ExtraTextUtils.equalsIgnoreCase(file.getAbsolutePath(), str2)) {
                            boolean renameFile = BaseFileUtils.renameFile(file, new File(str2));
                            BaseMiscUtil.closeSilently(fileInputStream2);
                            return renameFile;
                        }
                        BaseMiscUtil.closeSilently(fileInputStream2);
                        return true;
                    }
                }
                BaseFileUtils.deleteFile(file);
                BaseMiscUtil.closeSilently(fileInputStream2);
                return false;
            } catch (FileNotFoundException e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    Log.e("CryptoUtil", (Throwable) e);
                    BaseMiscUtil.closeSilently(fileInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                BaseMiscUtil.closeSilently(fileInputStream);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            Log.e("CryptoUtil", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        }
    }

    private static byte[] generateEmptyKey() {
        return new byte[16];
    }

    public static synchronized byte[] generateRandomKey() {
        byte[] generateEmptyKey;
        synchronized (CryptoUtil.class) {
            generateEmptyKey = generateEmptyKey();
            new SecureRandom().nextBytes(sRandomKey);
            System.arraycopy(sRandomKey, 0, generateEmptyKey, 0, sRandomKey.length);
        }
        return generateEmptyKey;
    }

    private static Cipher getCipher(byte[] bArr, int i) {
        Cipher cipher = null;
        if (bArr == null || bArr.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(sAesIv);
        try {
            cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(i, secretKeySpec, ivParameterSpec);
            return cipher;
        } catch (NoSuchAlgorithmException e) {
            Log.e("CryptoUtil", (Throwable) e);
            return cipher;
        } catch (NoSuchPaddingException e2) {
            Log.e("CryptoUtil", (Throwable) e2);
            return cipher;
        } catch (InvalidKeyException e3) {
            Log.e("CryptoUtil", (Throwable) e3);
            return cipher;
        } catch (InvalidAlgorithmParameterException e4) {
            Log.e("CryptoUtil", (Throwable) e4);
            return cipher;
        }
    }

    public static InputStream getDecryptCipherInputStream(String str, byte[] bArr) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(str);
        return bArr != null ? new CipherInputStream(fileInputStream, getCipher(bArr, 2)) : fileInputStream;
    }

    public static CipherInputStream getDecryptCipherInputStream(InputStream inputStream, byte[] bArr) {
        return new CipherInputStream(inputStream, getCipher(bArr, 2));
    }
}
