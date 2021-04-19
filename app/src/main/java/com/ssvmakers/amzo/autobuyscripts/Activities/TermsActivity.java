package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssvmakers.amzo.autobuyscripts.R;

public class TermsActivity extends AppCompatActivity {
    WebView n;

    public void goToBack(View view) {
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_webview_terms);
        super.onCreate(bundle);
        this.n = findViewById(R.id.termsView);
        this.n.setWebViewClient(new WebViewClient());
        this.n.getSettings().setAppCacheEnabled(true);
        this.n.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        this.n.getSettings().setJavaScriptEnabled(true);
        this.n.getSettings().setBuiltInZoomControls(true);
        this.n.getSettings().setUseWideViewPort(true);
        this.n.setScrollBarStyle(33554432);
        this.n.setScrollbarFadingEnabled(false);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.n.loadUrl("http://flashsalehelper.co.in/");
    }
}
