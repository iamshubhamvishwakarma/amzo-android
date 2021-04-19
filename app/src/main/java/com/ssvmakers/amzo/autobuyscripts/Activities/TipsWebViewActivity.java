package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssvmakers.amzo.autobuyscripts.R;

public class TipsWebViewActivity extends AppCompatActivity {
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
        this.n.loadUrl(getIntent().getStringExtra(getString(R.string.tips)));
    }
}
