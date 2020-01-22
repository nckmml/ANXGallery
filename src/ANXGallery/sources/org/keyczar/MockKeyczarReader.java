package org.keyczar;

import java.util.HashMap;
import java.util.Map;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.exceptions.BadVersionException;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.KeyczarReader;
import org.keyczar.util.Util;

public class MockKeyczarReader implements KeyczarReader {
    private Map<Integer, KeyczarKey> keys = new HashMap();
    private KeyMetadata kmd;
    private Map<Integer, KeyczarKey> publicKeys = new HashMap();
    private KeyMetadata publicKmd = null;

    public MockKeyczarReader(String str, KeyPurpose keyPurpose, KeyType keyType) {
        this.kmd = new KeyMetadata(str, keyPurpose, keyType);
    }

    public boolean addKey(int i, KeyStatus keyStatus) throws KeyczarException {
        this.keys.put(Integer.valueOf(i), KeyczarKey.genKey(this.kmd.getType()));
        return this.kmd.addVersion(new KeyVersion(i, keyStatus, false));
    }

    public boolean addKey(int i, KeyStatus keyStatus, int i2) throws KeyczarException {
        this.keys.put(Integer.valueOf(i), KeyczarKey.genKey(this.kmd.getType(), i2));
        return this.kmd.addVersion(new KeyVersion(i, keyStatus, false));
    }

    public boolean existsVersion(int i) {
        return this.keys.containsKey(Integer.valueOf(i));
    }

    public boolean exportedPublicKeySet() {
        return this.publicKmd != null;
    }

    public String getKey() throws KeyczarException {
        return getKey(KeyMetadata.read(getMetadata()).getPrimaryVersion().getVersionNumber());
    }

    public String getKey(int i) throws KeyczarException {
        if (this.keys.containsKey(Integer.valueOf(i))) {
            return this.keys.get(Integer.valueOf(i)).toString();
        }
        throw new BadVersionException((byte) i);
    }

    public int getKeySize(int i) {
        return this.keys.get(Integer.valueOf(i)).size();
    }

    public String getMetadata() {
        return Util.gson().toJson((Object) this.kmd);
    }

    public KeyStatus getStatus(int i) {
        return this.kmd.getVersion(i).getStatus();
    }

    public boolean hasPublicKey(int i) {
        KeyczarPrivateKey keyczarPrivateKey = (KeyczarPrivateKey) this.keys.get(Integer.valueOf(i));
        KeyczarPublicKey keyczarPublicKey = (KeyczarPublicKey) this.publicKeys.get(Integer.valueOf(i));
        return (keyczarPrivateKey == null || keyczarPublicKey == null || !keyczarPublicKey.equals(keyczarPrivateKey.getPublic())) ? false : true;
    }

    public String name() {
        return this.kmd.getName();
    }

    public int numKeys() {
        return this.keys.size();
    }

    public KeyPurpose purpose() {
        return this.kmd.getPurpose();
    }

    public void removeKey(int i) {
        this.keys.remove(Integer.valueOf(i));
    }

    public void setKey(int i, KeyczarKey keyczarKey) {
        this.keys.put(Integer.valueOf(i), keyczarKey);
    }

    public void setMetadata(KeyMetadata keyMetadata) {
        this.kmd = keyMetadata;
    }

    public void setPublicKey(int i, KeyczarKey keyczarKey) {
        this.publicKeys.put(Integer.valueOf(i), keyczarKey);
    }

    public void setPublicKeyMetadata(KeyMetadata keyMetadata) {
        this.publicKmd = keyMetadata;
    }

    public KeyType type() {
        return this.kmd.getType();
    }
}
