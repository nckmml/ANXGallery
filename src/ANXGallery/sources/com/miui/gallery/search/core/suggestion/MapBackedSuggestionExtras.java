package com.miui.gallery.search.core.suggestion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapBackedSuggestionExtras implements SuggestionExtras {
    private Map<String, String> mExtras;

    public MapBackedSuggestionExtras(String str, String str2) {
        this.mExtras = new HashMap(1);
        this.mExtras.put(str, str2);
    }

    public MapBackedSuggestionExtras(Map<String, String> map) {
        this.mExtras = map;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SuggestionExtras)) {
            return false;
        }
        if (obj == this || (getExtraColumnNames() == null && ((SuggestionExtras) obj).getExtraColumnNames() == null)) {
            return true;
        }
        SuggestionExtras suggestionExtras = (SuggestionExtras) obj;
        if (getExtraColumnNames() == null || suggestionExtras.getExtraColumnNames() == null) {
            return false;
        }
        for (String next : getExtraColumnNames()) {
            if (!getExtra(next).equals(suggestionExtras.getExtra(next))) {
                return false;
            }
        }
        return true;
    }

    public String getExtra(String str) {
        Map<String, String> map = this.mExtras;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public Collection<String> getExtraColumnNames() {
        Map<String, String> map = this.mExtras;
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    public void putExtra(String str, String str2) {
        if (this.mExtras == null) {
            this.mExtras = new HashMap(1);
        }
        this.mExtras.put(str, str2);
    }

    public String toString() {
        return super.toString() + ", data:" + this.mExtras;
    }
}
