package org.keyczar;

import com.google.gson_nex.annotations.Expose;
import org.keyczar.annotations.Experimental;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.util.Util;

@Experimental
public class SessionMaterial {
    @Expose
    private AesKey key = null;
    @Expose
    private String nonce = "";

    public SessionMaterial() {
    }

    public SessionMaterial(AesKey aesKey, String str) {
        this.key = aesKey;
        this.nonce = str;
    }

    public static SessionMaterial read(String str) {
        return (SessionMaterial) Util.gson().fromJson(str, SessionMaterial.class);
    }

    public AesKey getKey() throws KeyczarException {
        AesKey aesKey = this.key;
        if (aesKey != null) {
            return aesKey;
        }
        throw new KeyczarException("Key has not been initialized");
    }

    public String getNonce() {
        return this.nonce;
    }

    public String toString() {
        return Util.gson().toJson((Object) this);
    }
}
