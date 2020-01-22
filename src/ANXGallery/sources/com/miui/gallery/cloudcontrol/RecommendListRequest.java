package com.miui.gallery.cloudcontrol;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.miui.gallery.net.BaseGalleryRequest;
import com.miui.gallery.net.base.ErrorCode;
import java.util.Locale;
import org.json.JSONObject;

public class RecommendListRequest extends BaseGalleryRequest {

    public static class Builder {
        private int mMethod = 1001;
        private String mUrl;

        public RecommendListRequest build() {
            return new RecommendListRequest(this);
        }

        public int getMethod() {
            return this.mMethod;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public Builder setMethod(boolean z) {
            if (z) {
                this.mMethod = 1001;
            } else {
                this.mMethod = 0;
            }
            return this;
        }

        public Builder setUrl(String str) {
            this.mUrl = str;
            return this;
        }
    }

    private RecommendListRequest(Builder builder) {
        super(builder.getMethod(), builder.getUrl());
        addParam("lang", String.format("%s_%s", new Object[]{Locale.getDefault().getLanguage(), Locale.getDefault().getCountry()}));
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(JSONObject jSONObject) throws Exception {
        try {
            deliverResponse((RecommendListResponse) fromJson(jSONObject.toString(), new TypeToken<RecommendListResponse>() {
            }.getType()));
        } catch (JsonParseException e) {
            e.printStackTrace();
            deliverError(ErrorCode.PARSE_ERROR, e.getMessage(), jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
            deliverError(ErrorCode.HANDLE_ERROR, e2.getMessage(), jSONObject);
        }
    }
}
