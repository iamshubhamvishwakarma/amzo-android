package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssvmakers.amzo.autobuyscripts.R;

import java.util.Calendar;
import java.util.Date;

public class AutoCheckOutSecond extends AppCompatActivity {
    public static final String mypreference = "mypref";

    public void addGiftCart(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.flipkart.com/account/giftcard"));
        startActivity(intent);
    }

    public void gotItScreen(View view) {
        findViewById(R.id.gotItButton);
        Date date = new Date();
        Calendar.getInstance().setTime(date);
        Editor edit = getSharedPreferences("mypref", 0).edit();
        edit.putLong(getString(R.string.time), date.getTime());
        edit.commit();
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_checkout_2);
        super.onCreate(bundle);
    }

    public void onDestroy() {
//        Main2Activity.mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public void onPause() {
//        Main2Activity.mRewardedVideoAd.pause(this);
        super.onPause();
    }

    public void onResume() {
//        Main2Activity.mRewardedVideoAd.resume(this);
        super.onResume();
    }

    public void saveAddr(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.flipkart.com/account/addresses"));
        startActivity(intent);
    }
}
