package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class PropertyOptions extends Options {
    public PropertyOptions() {
    }

    public PropertyOptions(int i) throws XMPException {
        super(i);
    }

    public void assertConsistency(int i) throws XMPException {
        if ((i & 256) > 0 && (i & 512) > 0) {
            throw new XMPException("IsStruct and IsArray options are mutually exclusive", 103);
        } else if ((i & 2) > 0 && (i & 768) > 0) {
            throw new XMPException("Structs and arrays can't have \"value\" options", 103);
        }
    }

    public boolean getHasLanguage() {
        return getOption(64);
    }

    /* access modifiers changed from: protected */
    public int getValidOptions() {
        return -2147475470;
    }

    public boolean isArray() {
        return getOption(512);
    }

    public boolean isArrayAltText() {
        return getOption(4096);
    }

    public boolean isArrayAlternate() {
        return getOption(2048);
    }

    public boolean isArrayOrdered() {
        return getOption(1024);
    }

    public boolean isCompositeProperty() {
        return (getOptions() & 768) > 0;
    }

    public boolean isQualifier() {
        return getOption(32);
    }

    public boolean isSchemaNode() {
        return getOption(Integer.MIN_VALUE);
    }

    public boolean isSimple() {
        return (getOptions() & 768) == 0;
    }

    public boolean isStruct() {
        return getOption(256);
    }

    public void mergeWith(PropertyOptions propertyOptions) throws XMPException {
        if (propertyOptions != null) {
            setOptions(propertyOptions.getOptions() | getOptions());
        }
    }

    public PropertyOptions setArray(boolean z) {
        setOption(512, z);
        return this;
    }

    public PropertyOptions setArrayAltText(boolean z) {
        setOption(4096, z);
        return this;
    }

    public PropertyOptions setArrayAlternate(boolean z) {
        setOption(2048, z);
        return this;
    }

    public PropertyOptions setArrayOrdered(boolean z) {
        setOption(1024, z);
        return this;
    }

    public PropertyOptions setHasLanguage(boolean z) {
        setOption(64, z);
        return this;
    }

    public PropertyOptions setHasQualifiers(boolean z) {
        setOption(16, z);
        return this;
    }

    public PropertyOptions setHasType(boolean z) {
        setOption(128, z);
        return this;
    }

    public PropertyOptions setQualifier(boolean z) {
        setOption(32, z);
        return this;
    }

    public PropertyOptions setSchemaNode(boolean z) {
        setOption(Integer.MIN_VALUE, z);
        return this;
    }

    public PropertyOptions setStruct(boolean z) {
        setOption(256, z);
        return this;
    }

    public PropertyOptions setURI(boolean z) {
        setOption(2, z);
        return this;
    }
}
