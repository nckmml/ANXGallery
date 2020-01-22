package com.miui.gallery.cloudcontrol;

import android.text.TextUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Type;

public class FeatureProfile {
    private static final String JSON_TAG_NAME = "name";
    private static final String JSON_TAG_STATUS = "status";
    private static final String JSON_TAG_STRATEGY = "strategy";
    @SerializedName("name")
    protected String mName;
    @SerializedName("status")
    protected String mStatus;
    @SerializedName("strategy")
    protected String mStrategy;

    public static class Deserializer implements JsonDeserializer<FeatureProfile> {
        public FeatureProfile deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            FeatureProfile featureProfile = new FeatureProfile();
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.has(FeatureProfile.JSON_TAG_NAME)) {
                featureProfile.setName(asJsonObject.getAsJsonPrimitive(FeatureProfile.JSON_TAG_NAME).getAsString());
            }
            if (asJsonObject.has(FeatureProfile.JSON_TAG_STRATEGY)) {
                JsonElement jsonElement2 = asJsonObject.get(FeatureProfile.JSON_TAG_STRATEGY);
                if (jsonElement2.isJsonPrimitive()) {
                    featureProfile.setStrategy(jsonElement2.getAsString());
                } else {
                    featureProfile.setStrategy(jsonElement2.toString());
                }
            }
            if (asJsonObject.has(FeatureProfile.JSON_TAG_STATUS)) {
                featureProfile.setStatus(asJsonObject.getAsJsonPrimitive(FeatureProfile.JSON_TAG_STATUS).getAsString());
            }
            return featureProfile;
        }
    }

    public enum Status {
        ENABLE("enable"),
        DISABLE("disable"),
        REMOVE("remove"),
        UNAVAILABLE("unavailable");
        
        private String value;

        private Status(String str) {
            this.value = str;
        }

        public static Status fromValue(String str) {
            for (Status status : values()) {
                if (status.value.equals(str)) {
                    return status;
                }
            }
            return UNAVAILABLE;
        }

        public String getValue() {
            return this.value;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FeatureProfile)) {
            return false;
        }
        FeatureProfile featureProfile = (FeatureProfile) obj;
        return TextUtils.equals(this.mName, featureProfile.getName()) && TextUtils.equals(this.mStrategy, featureProfile.getStrategy()) && TextUtils.equals(this.mStatus, featureProfile.getStatus());
    }

    public String getName() {
        return this.mName;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String getStrategy() {
        return this.mStrategy;
    }

    public int hashCode() {
        String str = this.mName;
        int hashCode = str != null ? 527 + str.hashCode() : 17;
        String str2 = this.mStatus;
        if (str2 != null) {
            hashCode = (hashCode * 31) + str2.hashCode();
        }
        String str3 = this.mStrategy;
        return str3 != null ? (hashCode * 31) + str3.hashCode() : hashCode;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    public void setStrategy(String str) {
        this.mStrategy = str;
    }

    public String toString() {
        return "FeatureProfile{mName='" + this.mName + '\'' + ", mStatus='" + this.mStatus + '\'' + ", mStrategy='" + this.mStrategy + '\'' + '}';
    }
}
