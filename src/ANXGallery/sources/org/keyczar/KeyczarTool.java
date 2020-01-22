package org.keyczar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import org.keyczar.enums.Command;
import org.keyczar.enums.Flag;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.enums.RsaPadding;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.KeyczarReader;

public class KeyczarTool {
    private static MockKeyczarReader mock;

    /* renamed from: org.keyczar.KeyczarTool$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$enums$KeyPurpose = new int[KeyPurpose.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0089 */
        static {
            try {
                $SwitchMap$org$keyczar$enums$KeyPurpose[KeyPurpose.DECRYPT_AND_ENCRYPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$keyczar$enums$KeyPurpose[KeyPurpose.SIGN_AND_VERIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$keyczar$enums$KeyPurpose[KeyPurpose.TEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$org$keyczar$enums$Command = new int[Command.values().length];
            $SwitchMap$org$keyczar$enums$Command[Command.CREATE.ordinal()] = 1;
            $SwitchMap$org$keyczar$enums$Command[Command.ADDKEY.ordinal()] = 2;
            $SwitchMap$org$keyczar$enums$Command[Command.PUBKEY.ordinal()] = 3;
            $SwitchMap$org$keyczar$enums$Command[Command.PROMOTE.ordinal()] = 4;
            $SwitchMap$org$keyczar$enums$Command[Command.DEMOTE.ordinal()] = 5;
            $SwitchMap$org$keyczar$enums$Command[Command.REVOKE.ordinal()] = 6;
            $SwitchMap$org$keyczar$enums$Command[Command.USEKEY.ordinal()] = 7;
            $SwitchMap$org$keyczar$enums$Command[Command.IMPORT_KEY.ordinal()] = 8;
            try {
                $SwitchMap$org$keyczar$enums$Command[Command.EXPORT_KEY.ordinal()] = 9;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void addKey(String str, KeyStatus keyStatus, String str2, int i, String str3) throws KeyczarException {
        GenericKeyczar createGenericKeyczar = createGenericKeyczar(str, str2);
        if (i == -1) {
            createGenericKeyczar.addVersion(keyStatus, getPadding(str3));
        } else {
            createGenericKeyczar.addVersion(keyStatus, getPadding(str3), i);
        }
        updateGenericKeyczar(createGenericKeyczar, str2, str);
    }

    private static void create(String str, String str2, KeyPurpose keyPurpose, String str3) throws KeyczarException {
        if (keyPurpose != null) {
            int i = AnonymousClass1.$SwitchMap$org$keyczar$enums$KeyPurpose[keyPurpose.ordinal()];
            KeyMetadata keyMetadata = i != 1 ? i != 2 ? i != 3 ? null : new KeyMetadata(str2, KeyPurpose.TEST, DefaultKeyType.TEST) : str3 != null ? str3.equalsIgnoreCase("rsa") ? new KeyMetadata(str2, KeyPurpose.SIGN_AND_VERIFY, DefaultKeyType.RSA_PRIV) : str3.equalsIgnoreCase("ec") ? new KeyMetadata(str2, KeyPurpose.SIGN_AND_VERIFY, DefaultKeyType.EC_PRIV) : new KeyMetadata(str2, KeyPurpose.SIGN_AND_VERIFY, DefaultKeyType.DSA_PRIV) : new KeyMetadata(str2, KeyPurpose.SIGN_AND_VERIFY, DefaultKeyType.HMAC_SHA1) : str3 != null ? new KeyMetadata(str2, KeyPurpose.DECRYPT_AND_ENCRYPT, DefaultKeyType.RSA_PRIV) : new KeyMetadata(str2, KeyPurpose.DECRYPT_AND_ENCRYPT, DefaultKeyType.AES);
            if (keyMetadata != null) {
                MockKeyczarReader mockKeyczarReader = mock;
                if (mockKeyczarReader != null) {
                    mockKeyczarReader.setMetadata(keyMetadata);
                } else if (str != null) {
                    File file = new File(str + "meta");
                    if (!file.exists()) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write(keyMetadata.toString().getBytes(Keyczar.DEFAULT_ENCODING));
                            fileOutputStream.close();
                        } catch (IOException e) {
                            throw new KeyczarException(Messages.getString("KeyczarTool.UnableToWrite", file.toString()), e);
                        }
                    } else {
                        throw new KeyczarException(Messages.getString("KeyczarTool.FileExists", file));
                    }
                } else {
                    throw new KeyczarException(Messages.getString("KeyczarTool.MustDefineLocation", new Object[0]));
                }
            } else {
                throw new KeyczarException(Messages.getString("KeyczarTool.UnsupportedPurpose", keyPurpose));
            }
        } else {
            throw new KeyczarException(Messages.getString("KeyczarTool.MustDefinePurpose", new Object[0]));
        }
    }

    private static GenericKeyczar createGenericKeyczar(String str) throws KeyczarException {
        return createGenericKeyczar(str, (String) null);
    }

    private static GenericKeyczar createGenericKeyczar(String str, String str2) throws KeyczarException {
        MockKeyczarReader mockKeyczarReader = mock;
        if (mockKeyczarReader != null) {
            return new GenericKeyczar((KeyczarReader) mockKeyczarReader);
        }
        if (str != null) {
            KeyczarFileReader keyczarFileReader = new KeyczarFileReader(str);
            return new GenericKeyczar(str2 != null ? new KeyczarEncryptedReader(keyczarFileReader, new Crypter(str2)) : keyczarFileReader);
        }
        throw new KeyczarException(Messages.getString("KeyczarTool.NeedLocation", Messages.getString("KeyczarTool.Location", new Object[0])));
    }

    private static void demote(String str, int i) throws KeyczarException {
        if (i >= 0) {
            GenericKeyczar createGenericKeyczar = createGenericKeyczar(str);
            createGenericKeyczar.demote(i);
            updateGenericKeyczar(createGenericKeyczar, str);
            return;
        }
        throw new KeyczarException(Messages.getString("KeyczarTool.MissingVersion", new Object[0]));
    }

    private static void exportKey(String str, String str2, int i, String str3, String str4) throws KeyczarException {
        if (i >= 0) {
            GenericKeyczar createGenericKeyczar = createGenericKeyczar(str, str2);
            String pemString = createGenericKeyczar.getKey(createGenericKeyczar.getVersion(i)).getPemString(str4);
            try {
                File file = new File(str3);
                if (file.createNewFile()) {
                    new FileOutputStream(file).write(pemString.getBytes("UTF8"));
                } else {
                    throw new KeyczarException(Messages.getString("", file));
                }
            } catch (IOException e) {
                throw new KeyczarException(Messages.getString("", new Object[0]), e);
            }
        } else {
            throw new KeyczarException(Messages.getString("KeyczarTool.MissingVersion", new Object[0]));
        }
    }

    private static InputStream getFileStream(String str) throws KeyczarException {
        try {
            return new FileInputStream(str);
        } catch (FileNotFoundException unused) {
            throw new KeyczarException(Messages.getString("KeyczarTool.FileNotFound", str));
        }
    }

    /* JADX INFO: finally extract failed */
    private static GenericKeyczar getImportingKeyczar(String str, String str2, String str3, KeyPurpose keyPurpose) throws KeyczarException, IOException {
        RsaPadding padding = getPadding(str2);
        InputStream fileStream = getFileStream(str);
        try {
            GenericKeyczar genericKeyczar = new GenericKeyczar((KeyczarReader) new X509CertificateReader(keyPurpose, fileStream, padding));
            fileStream.close();
            return genericKeyczar;
        } catch (KeyczarException e) {
            if (e.getCause() instanceof CertificateException) {
                fileStream.close();
                InputStream fileStream2 = getFileStream(str);
                GenericKeyczar genericKeyczar2 = new GenericKeyczar((KeyczarReader) new PkcsKeyReader(keyPurpose, fileStream2, padding, str3));
                fileStream2.close();
                return genericKeyczar2;
            }
            throw e;
        } catch (Throwable th) {
            fileStream.close();
            throw th;
        }
    }

    public static MockKeyczarReader getMock() {
        return mock;
    }

    private static RsaPadding getPadding(String str) throws KeyczarException {
        if (str == null) {
            return null;
        }
        try {
            return RsaPadding.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException unused) {
            throw new KeyczarException(Messages.getString("InvalidPadding", str));
        }
    }

    private static void importKey(String str, String str2, KeyStatus keyStatus, String str3, String str4, String str5) throws KeyczarException, IOException {
        GenericKeyczar createGenericKeyczar = createGenericKeyczar(str, str3);
        KeyMetadata metadata = createGenericKeyczar.getMetadata();
        GenericKeyczar importingKeyczar = getImportingKeyczar(str2, str4, str5, metadata.getPurpose());
        KeyType type = importingKeyczar.getMetadata().getType();
        if (metadata.getType() != type && createGenericKeyczar.getVersions().isEmpty()) {
            metadata.setType(type);
        }
        createGenericKeyczar.addVersion(keyStatus, importingKeyczar.getPrimaryKey());
        updateGenericKeyczar(createGenericKeyczar, str3, str);
    }

    public static void main(String[] strArr) {
        String[] strArr2 = strArr;
        if (strArr2.length == 0) {
            printUsage();
            return;
        }
        try {
            Command command = Command.getCommand(strArr2[0]);
            HashMap hashMap = new HashMap();
            for (String str : strArr2) {
                if (str.startsWith("--")) {
                    String[] split = str.substring(2).split("=");
                    if (split.length > 1) {
                        hashMap.put(Flag.getFlag(split[0]), split[1]);
                    }
                }
            }
            String str2 = (String) hashMap.get(Flag.LOCATION);
            if (str2 != null && !str2.endsWith(File.separator)) {
                str2 = str2 + File.separator;
            }
            String str3 = str2;
            KeyPurpose purpose = KeyPurpose.getPurpose((String) hashMap.get(Flag.PURPOSE));
            KeyStatus status = KeyStatus.getStatus((String) hashMap.get(Flag.STATUS));
            String str4 = (String) hashMap.get(Flag.ASYMMETRIC);
            String str5 = (String) hashMap.get(Flag.CRYPTER);
            String str6 = (String) hashMap.get(Flag.DESTINATION);
            String str7 = (String) hashMap.get(Flag.NAME);
            String str8 = (String) hashMap.get(Flag.PADDING);
            String str9 = (String) hashMap.get(Flag.PASSPHRASE);
            String str10 = (String) hashMap.get(Flag.PEMFILE);
            String str11 = (String) hashMap.get(Flag.VERSION);
            int i = -1;
            if (hashMap.containsKey(Flag.SIZE)) {
                i = Integer.parseInt((String) hashMap.get(Flag.SIZE));
            }
            int i2 = i;
            switch (command) {
                case CREATE:
                    create(str3, str7, purpose, str4);
                    return;
                case ADDKEY:
                    addKey(str3, status, str5, i2, str8);
                    return;
                case PUBKEY:
                    publicKeys(str3, str6);
                    return;
                case PROMOTE:
                    promote(str3, Integer.parseInt(str11));
                    return;
                case DEMOTE:
                    demote(str3, Integer.parseInt(str11));
                    return;
                case REVOKE:
                    revoke(str3, Integer.parseInt(str11));
                    return;
                case USEKEY:
                    if (strArr2.length > 2) {
                        useKey(strArr2[1], str3, str6, str5);
                        return;
                    } else {
                        printUsage();
                        return;
                    }
                case IMPORT_KEY:
                    importKey(str3, str10, status, str5, str8, str9);
                    return;
                case EXPORT_KEY:
                    exportKey(str3, str5, Integer.parseInt(str11), str10, str9);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            printUsage();
        }
    }

    private static void printUsage() {
        ArrayList arrayList = new ArrayList();
        for (Command command : Command.values()) {
            arrayList.add(command.toString());
        }
        for (Flag flag : Flag.values()) {
            arrayList.add(flag.toString());
        }
        System.out.println(Messages.getString("KeyczarTool.Usage", arrayList.toArray()));
    }

    private static void promote(String str, int i) throws KeyczarException {
        if (i >= 0) {
            GenericKeyczar createGenericKeyczar = createGenericKeyczar(str);
            createGenericKeyczar.promote(i);
            updateGenericKeyczar(createGenericKeyczar, str);
            return;
        }
        throw new KeyczarException(Messages.getString("KeyczarTool.MissingVersion", new Object[0]));
    }

    private static void publicKeys(String str, String str2) throws KeyczarException {
        if (mock == null && str2 == null) {
            throw new KeyczarException(Messages.getString("KeyczarTool.MustDefineDestination", new Object[0]));
        }
        createGenericKeyczar(str).publicKeyExport(str2);
    }

    private static void revoke(String str, int i) throws KeyczarException {
        GenericKeyczar createGenericKeyczar = createGenericKeyczar(str);
        createGenericKeyczar.revoke(i);
        updateGenericKeyczar(createGenericKeyczar, str);
        MockKeyczarReader mockKeyczarReader = mock;
        if (mockKeyczarReader == null) {
            if (!new File(str + i).delete()) {
                throw new KeyczarException(Messages.getString("KeyczarTool.UnableToDelete", new Object[0]));
            }
            return;
        }
        mockKeyczarReader.removeKey(i);
    }

    public static void setReader(MockKeyczarReader mockKeyczarReader) {
        mock = mockKeyczarReader;
    }

    private static void updateGenericKeyczar(GenericKeyczar genericKeyczar, String str) throws KeyczarException {
        updateGenericKeyczar(genericKeyczar, (String) null, str);
    }

    private static void updateGenericKeyczar(GenericKeyczar genericKeyczar, String str, String str2) throws KeyczarException {
        MockKeyczarReader mockKeyczarReader = mock;
        if (mockKeyczarReader != null) {
            mockKeyczarReader.setMetadata(genericKeyczar.getMetadata());
            for (KeyVersion next : genericKeyczar.getVersions()) {
                mock.setKey(next.getVersionNumber(), genericKeyczar.getKey(next));
            }
        } else if (str != null) {
            genericKeyczar.writeEncrypted(str2, new Encrypter(str));
        } else {
            genericKeyczar.write(str2);
        }
    }

    private static void useKey(String str, String str2, String str3, String str4) throws KeyczarException {
        String str5;
        GenericKeyczar createGenericKeyczar = createGenericKeyczar(str2, str4);
        if (str3 != null) {
            KeyczarFileReader keyczarFileReader = new KeyczarFileReader(str2);
            KeyczarReader keyczarEncryptedReader = str4 != null ? new KeyczarEncryptedReader(keyczarFileReader, new Crypter(str4)) : keyczarFileReader;
            int i = AnonymousClass1.$SwitchMap$org$keyczar$enums$KeyPurpose[createGenericKeyczar.getMetadata().getPurpose().ordinal()];
            if (i == 1) {
                str5 = new Crypter(keyczarEncryptedReader).encrypt(str);
            } else if (i == 2) {
                str5 = new Signer(keyczarEncryptedReader).sign(str);
            } else {
                throw new KeyczarException(Messages.getString("KeyczarTool.UnsupportedPurpose", createGenericKeyczar.getMetadata().getPurpose()));
            }
            createGenericKeyczar.writeFile(str5, str3);
            return;
        }
        throw new KeyczarException(Messages.getString("KeyczarTool.MustDefinePublic", new Object[0]));
    }
}
