package com.miui.gallery.hybrid.hybridclient.wrapper;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import miui.webkit.WebViewClient;

public class WebViewClientWrapper extends WebViewClient {
    private WebViewClient mWrappedWebViewClient;

    public WebViewClientWrapper(WebViewClient webViewClient) {
        this.mWrappedWebViewClient = webViewClient;
    }

    public void onPageFinished(WebView webView, String str) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        if (webViewClient != null) {
            webViewClient.onPageFinished(webView, str);
        } else {
            WebViewClientWrapper.super.onPageFinished(webView, str);
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        if (webViewClient != null) {
            webViewClient.onPageStarted(webView, str, bitmap);
        } else {
            WebViewClientWrapper.super.onPageStarted(webView, str, bitmap);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        if (webViewClient != null) {
            webViewClient.onReceivedError(webView, i, str, str2);
        } else {
            WebViewClientWrapper.super.onReceivedError(webView, i, str, str2);
        }
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        if (webViewClient != null) {
            webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
        } else {
            WebViewClientWrapper.super.onReceivedLoginRequest(webView, str, str2, str3);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        if (webViewClient != null) {
            webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
        } else {
            WebViewClientWrapper.super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        WebViewClient webViewClient = this.mWrappedWebViewClient;
        return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, str) : WebViewClientWrapper.super.shouldOverrideUrlLoading(webView, str);
    }
}
