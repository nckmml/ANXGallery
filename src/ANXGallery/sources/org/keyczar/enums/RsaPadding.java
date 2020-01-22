package org.keyczar.enums;

import java.security.interfaces.RSAPublicKey;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.util.Util;

public enum RsaPadding {
    OAEP("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING"),
    PKCS("RSA/ECB/PKCS1PADDING");
    
    private final String cryptAlgorithm;

    /* renamed from: org.keyczar.enums.RsaPadding$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$enums$RsaPadding = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$org$keyczar$enums$RsaPadding = new int[RsaPadding.values().length];
            $SwitchMap$org$keyczar$enums$RsaPadding[RsaPadding.OAEP.ordinal()] = 1;
            $SwitchMap$org$keyczar$enums$RsaPadding[RsaPadding.PKCS.ordinal()] = 2;
        }
    }

    private RsaPadding(String str) {
        this.cryptAlgorithm = str;
    }

    public byte[] computeFullHash(RSAPublicKey rSAPublicKey) throws KeyczarException {
        int i = AnonymousClass1.$SwitchMap$org$keyczar$enums$RsaPadding[ordinal()];
        if (i == 1) {
            return Util.prefixHash(Util.stripLeadingZeros(rSAPublicKey.getModulus().toByteArray()), Util.stripLeadingZeros(rSAPublicKey.getPublicExponent().toByteArray()));
        } else if (i == 2) {
            return Util.prefixHash(rSAPublicKey.getModulus().toByteArray(), rSAPublicKey.getPublicExponent().toByteArray());
        } else {
            throw new KeyczarException("Bug! Unknown padding type");
        }
    }

    public String getCryptAlgorithm() {
        return this.cryptAlgorithm;
    }
}
