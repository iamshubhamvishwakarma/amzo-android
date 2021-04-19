package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.CustomWebView;

public class NormalWebViewActivity extends AppCompatActivity {
    CustomWebView webView;

    RelativeLayout loader_relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        String storeUrl = getIntent().getStringExtra("storeUrl");
        webView = findViewById(R.id.normalWebview);
        webView.setGestureDetector(new GestureDetector(new CustomeGestureDetector()));
        loader_relative = findViewById(R.id.loader_relative);
        setUpWebView(storeUrl);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void setUpWebView(String storeUrl) {
        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loader_relative.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loader_relative.setVisibility(View.GONE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 60) {
                    loader_relative.setVisibility(View.GONE);
                }
                Log.d("progress", "--" + newProgress);
            }
        });
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setScrollbarFadingEnabled(false);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.requestFocusFromTouch();
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        CookieManager.getInstance().setAcceptCookie(true);
        webView.loadUrl(storeUrl);
    }

    public void goToBack(View view) {
        super.onBackPressed();
    }

    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null || e2 == null) return false;
            if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
            else {
                try {
                    if (e1.getY() - e2.getY() > 20) {
                        // Hide Actionbar
                        getSupportActionBar().hide();
                        webView.invalidate();
                        return false;
                    } else if (e2.getY() - e1.getY() > 20) {
                        // Show Actionbar
                        getSupportActionBar().show();
                        webView.invalidate();
                        return false;
                    }

                } catch (Exception e) {
                    webView.invalidate();
                }
                return false;
            }


        }
    }
}