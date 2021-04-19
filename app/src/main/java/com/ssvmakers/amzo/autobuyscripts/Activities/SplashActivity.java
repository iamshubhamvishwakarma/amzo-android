package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ssvmakers.amzo.autobuyscripts.R;

//import com.crashlytics.android.Crashlytics;

//import io.fabric.sdk.android.Fabric;
//import io.fabric.sdk.android.Kit;

public class SplashActivity extends AppCompatActivity {
    Handler a;
    Runnable b;

    public void gotoTerms(View view) {
        this.a.removeCallbacks(this.b);
        startActivity(new Intent(this, TermsActivity.class));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().hide();
//        Fabric.with(this, new Kit[]{new Crashlytics()});
        setContentView(R.layout.activity_splash);
        this.a = new Handler();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        this.b = new Runnable() {
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, Main2Activity.class));
                SplashActivity.this.finish();
            }
        };
        this.a.postDelayed(this.b, 3000);
        super.onResume();
    }
}
