package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssvmakers.amzo.autobuyscripts.R;

public class AutoCheckOutFirst extends AppCompatActivity {
    public void nextScreen(View view) {
        startActivity(new Intent(this, AutoCheckOutSecond.class));
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_checkout_1);
        super.onCreate(bundle);
    }
}
