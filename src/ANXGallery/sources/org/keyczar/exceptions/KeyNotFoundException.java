package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;

public class KeyNotFoundException extends KeyczarException {
    private static final long serialVersionUID = -2745196315795456118L;

    KeyNotFoundException(String str) {
        super(str);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public KeyNotFoundException(byte[] bArr) {
        super(Messages.getString("KeyWithHashIdentifier", Integer.toHexString((bArr[3] & 255) | r0 | ((bArr[0] & 255) << 24) | ((bArr[2] & 255) << 8))));
        int i = (bArr[1] & 255) << 16;
    }
}
