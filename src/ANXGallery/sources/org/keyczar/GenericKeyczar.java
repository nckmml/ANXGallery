package org.keyczar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.enums.RsaPadding;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyczarReader;

public class GenericKeyczar extends Keyczar {

    /* renamed from: org.keyczar.GenericKeyczar$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$enums$KeyPurpose = new int[KeyPurpose.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$enums$KeyStatus = new int[KeyStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|5|6|7|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        static {
            try {
                $SwitchMap$org$keyczar$enums$KeyPurpose[KeyPurpose.DECRYPT_AND_ENCRYPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$keyczar$enums$KeyPurpose[KeyPurpose.SIGN_AND_VERIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$org$keyczar$enums$KeyStatus[KeyStatus.PRIMARY.ordinal()] = 1;
            $SwitchMap$org$keyczar$enums$KeyStatus[KeyStatus.ACTIVE.ordinal()] = 2;
            try {
                $SwitchMap$org$keyczar$enums$KeyStatus[KeyStatus.INACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public GenericKeyczar(String str) throws KeyczarException {
        super(str);
    }

    public GenericKeyczar(KeyczarReader keyczarReader) throws KeyczarException {
        super(keyczarReader);
    }

    private int numVersions() {
        return this.versionMap.size();
    }

    public void addVersion(KeyStatus keyStatus) throws KeyczarException {
        addVersion(keyStatus, (RsaPadding) null, this.kmd.getType().defaultSize());
    }

    public void addVersion(KeyStatus keyStatus, int i) throws KeyczarException {
        addVersion(keyStatus, (RsaPadding) null, i);
    }

    public void addVersion(KeyStatus keyStatus, KeyczarKey keyczarKey) {
        KeyVersion keyVersion = new KeyVersion(numVersions() + 1, keyStatus, false);
        if (keyStatus == KeyStatus.PRIMARY) {
            if (this.primaryVersion != null) {
                this.primaryVersion.setStatus(KeyStatus.ACTIVE);
            }
            this.primaryVersion = keyVersion;
        }
        addKey(keyVersion, keyczarKey);
    }

    public void addVersion(KeyStatus keyStatus, RsaPadding rsaPadding) throws KeyczarException {
        addVersion(keyStatus, rsaPadding, this.kmd.getType().defaultSize());
    }

    public void addVersion(KeyStatus keyStatus, RsaPadding rsaPadding, int i) throws KeyczarException {
        KeyczarKey genKey;
        do {
            genKey = KeyczarKey.genKey(this.kmd.getType(), rsaPadding, i);
        } while (getKey(genKey.hash()) != null);
        addVersion(keyStatus, genKey);
    }

    public void demote(int i) throws KeyczarException {
        KeyVersion version = getVersion(i);
        int i2 = AnonymousClass1.$SwitchMap$org$keyczar$enums$KeyStatus[version.getStatus().ordinal()];
        if (i2 == 1) {
            version.setStatus(KeyStatus.ACTIVE);
            this.primaryVersion = null;
        } else if (i2 == 2) {
            version.setStatus(KeyStatus.INACTIVE);
        } else if (i2 == 3) {
            throw new KeyczarException(Messages.getString("Keyczar.CantDemoteScheduled", new Object[0]));
        }
    }

    public KeyczarKey getKey(KeyVersion keyVersion) {
        return (KeyczarKey) this.versionMap.get(keyVersion);
    }

    public KeyMetadata getMetadata() {
        return this.kmd;
    }

    public KeyVersion getVersion(int i) throws KeyczarException {
        KeyVersion version = this.kmd.getVersion(i);
        if (version != null) {
            return version;
        }
        throw new KeyczarException(Messages.getString("Keyczar.NoSuchVersion", Integer.valueOf(i)));
    }

    public Set<KeyVersion> getVersions() {
        return Collections.unmodifiableSet(this.versionMap.keySet());
    }

    public boolean isAcceptablePurpose(KeyPurpose keyPurpose) {
        return true;
    }

    public void promote(int i) throws KeyczarException {
        KeyVersion version = getVersion(i);
        int i2 = AnonymousClass1.$SwitchMap$org$keyczar$enums$KeyStatus[version.getStatus().ordinal()];
        if (i2 == 1) {
            throw new KeyczarException(Messages.getString("Keyczar.CantPromotePrimary", new Object[0]));
        } else if (i2 == 2) {
            version.setStatus(KeyStatus.PRIMARY);
            if (this.primaryVersion != null) {
                this.primaryVersion.setStatus(KeyStatus.ACTIVE);
            }
            this.primaryVersion = version;
        } else if (i2 == 3) {
            version.setStatus(KeyStatus.ACTIVE);
        }
    }

    /* access modifiers changed from: package-private */
    public void publicKeyExport(String str) throws KeyczarException {
        if (str != null && !str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        KeyMetadata metadata = getMetadata();
        KeyMetadata keyMetadata = null;
        if (metadata.getType() == DefaultKeyType.DSA_PRIV) {
            if (metadata.getPurpose() == KeyPurpose.SIGN_AND_VERIFY) {
                keyMetadata = new KeyMetadata(metadata.getName(), KeyPurpose.VERIFY, DefaultKeyType.DSA_PUB);
            }
        } else if (metadata.getType() == DefaultKeyType.RSA_PRIV) {
            int i = AnonymousClass1.$SwitchMap$org$keyczar$enums$KeyPurpose[metadata.getPurpose().ordinal()];
            if (i == 1) {
                keyMetadata = new KeyMetadata(metadata.getName(), KeyPurpose.ENCRYPT, DefaultKeyType.RSA_PUB);
            } else if (i == 2) {
                keyMetadata = new KeyMetadata(metadata.getName(), KeyPurpose.VERIFY, DefaultKeyType.RSA_PUB);
            }
        }
        if (keyMetadata != null) {
            for (KeyVersion next : getVersions()) {
                KeyczarPublicKey keyczarPublicKey = ((KeyczarPrivateKey) getKey(next)).getPublic();
                if (KeyczarTool.getMock() == null) {
                    writeFile(keyczarPublicKey.toString(), str + next.getVersionNumber());
                } else {
                    KeyczarTool.getMock().setPublicKey(next.getVersionNumber(), keyczarPublicKey);
                }
                keyMetadata.addVersion(next);
            }
            if (KeyczarTool.getMock() == null) {
                writeFile(keyMetadata.toString(), str + "meta");
                return;
            }
            KeyczarTool.getMock().setPublicKeyMetadata(keyMetadata);
            return;
        }
        throw new KeyczarException(Messages.getString("KeyczarTool.CannotExportPubKey", metadata.getType(), metadata.getPurpose()));
    }

    public void revoke(int i) throws KeyczarException {
        if (getVersion(i).getStatus() == KeyStatus.INACTIVE) {
            this.kmd.removeVersion(i);
            return;
        }
        throw new KeyczarException(Messages.getString("Keyczar.CantRevoke", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void write(String str) throws KeyczarException {
        String keyMetadata = this.kmd.toString();
        writeFile(keyMetadata, str + "meta");
        for (KeyVersion next : getVersions()) {
            String keyczarKey = getKey(next).toString();
            writeFile(keyczarKey, str + next.getVersionNumber());
        }
    }

    /* access modifiers changed from: package-private */
    public void writeEncrypted(String str, Encrypter encrypter) throws KeyczarException {
        KeyMetadata metadata = getMetadata();
        metadata.setEncrypted(true);
        String keyMetadata = metadata.toString();
        writeFile(keyMetadata, str + "meta");
        for (KeyVersion next : getVersions()) {
            String encrypt = encrypter.encrypt(getKey(next).toString());
            writeFile(encrypt, str + next.getVersionNumber());
        }
    }

    /* access modifiers changed from: package-private */
    public void writeFile(String str, String str2) throws KeyczarException {
        File file = new File(str2);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            throw new KeyczarException(Messages.getString("KeyczarTool.UnableToWrite", file.toString()), e);
        }
    }
}
