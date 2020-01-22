package org.keyczar;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keyczar.enums.RsaPadding;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.UnsupportedTypeException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyType;

public enum DefaultKeyType implements KeyType {
    AES((String) Arrays.asList(new Integer[]{128, 192, 256}), 0),
    HMAC_SHA1((String) Arrays.asList(new Integer[]{256}), 20),
    DSA_PRIV((String) Arrays.asList(new Integer[]{1024}), 48),
    DSA_PUB((String) Arrays.asList(new Integer[]{1024}), 48),
    RSA_PRIV((String) Arrays.asList(new Integer[]{4096, 2048, 1024}), (int) Arrays.asList(new Integer[]{512, 256, 128})),
    RSA_PUB((String) Arrays.asList(new Integer[]{4096, 2048, 1024}), (int) Arrays.asList(new Integer[]{512, 256, 128})),
    EC_PRIV((String) Arrays.asList(new Integer[]{256, 384, 521, 192}), 70),
    EC_PUB((String) Arrays.asList(new Integer[]{256, 384, 521, 192}), 70),
    TEST((String) Arrays.asList(new Integer[]{1}), 0);
    
    private static Map<String, KeyType> typeMapping;
    private final List<Integer> acceptableSizes;
    private final Map<Integer, Integer> outputSizeMap;

    private class DefaultKeyBuilder implements KeyType.Builder {
        private final RsaPadding padding;

        private DefaultKeyBuilder() {
            this.padding = null;
        }

        private DefaultKeyBuilder(RsaPadding rsaPadding) {
            this.padding = rsaPadding;
        }

        public KeyczarKey generate(int i) throws KeyczarException {
            switch (DefaultKeyType.this) {
                case AES:
                    return AesKey.generate(i);
                case HMAC_SHA1:
                    return HmacKey.generate(i);
                case DSA_PRIV:
                    return DsaPrivateKey.generate(i);
                case DSA_PUB:
                case RSA_PUB:
                    throw new KeyczarException(Messages.getString("KeyczarKey.PublicKeyExport", DefaultKeyType.this));
                case RSA_PRIV:
                    return RsaPrivateKey.generate(i, this.padding);
                default:
                    throw new UnsupportedTypeException(DefaultKeyType.this);
            }
        }

        public KeyczarKey read(String str) throws KeyczarException {
            switch (DefaultKeyType.this) {
                case AES:
                    return AesKey.read(str);
                case HMAC_SHA1:
                    return HmacKey.read(str);
                case DSA_PRIV:
                    return DsaPrivateKey.read(str);
                case DSA_PUB:
                    return DsaPublicKey.read(str);
                case RSA_PRIV:
                    return RsaPrivateKey.read(str);
                case RSA_PUB:
                    return RsaPublicKey.read(str);
                default:
                    throw new UnsupportedTypeException(DefaultKeyType.this);
            }
        }
    }

    private DefaultKeyType(List<Integer> list, int i) {
        this.outputSizeMap = new HashMap();
        this.acceptableSizes = list;
        for (Integer intValue : this.acceptableSizes) {
            this.outputSizeMap.put(Integer.valueOf(intValue.intValue()), Integer.valueOf(i));
        }
        addToMapping(name(), this);
    }

    private DefaultKeyType(List<Integer> list, List<Integer> list2) {
        this.outputSizeMap = new HashMap();
        this.acceptableSizes = list;
        for (int i = 0; i < list.size(); i++) {
            this.outputSizeMap.put(this.acceptableSizes.get(i), list2.get(i));
        }
        addToMapping(name(), this);
    }

    private static void addToMapping(String str, KeyType keyType) {
        if (typeMapping == null) {
            typeMapping = new HashMap();
        }
        typeMapping.put(str, keyType);
    }

    public static KeyType getTypeByName(String str) {
        return typeMapping.get(str);
    }

    public int defaultSize() {
        return this.acceptableSizes.get(0).intValue();
    }

    public List<Integer> getAcceptableSizes() {
        return Collections.unmodifiableList(this.acceptableSizes);
    }

    public KeyType.Builder getBuilder() {
        return new DefaultKeyBuilder();
    }

    public String getName() {
        return name();
    }

    public int getOutputSize() {
        return getOutputSize(defaultSize());
    }

    public int getOutputSize(int i) {
        return this.outputSizeMap.get(Integer.valueOf(i)).intValue();
    }

    /* access modifiers changed from: package-private */
    public KeyType.Builder getRsaBuilder(RsaPadding rsaPadding) throws KeyczarException {
        if (this == RSA_PRIV) {
            return new DefaultKeyBuilder(rsaPadding);
        }
        throw new KeyczarException(Messages.getString("InvalidKeyType", this));
    }

    public boolean isAcceptableSize(int i) {
        return this.acceptableSizes.contains(Integer.valueOf(i));
    }
}
