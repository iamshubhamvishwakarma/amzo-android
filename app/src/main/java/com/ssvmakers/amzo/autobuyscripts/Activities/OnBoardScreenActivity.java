package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.ssvmakers.amzo.autobuyscripts.Model.OnBoardModel;
import com.ssvmakers.amzo.autobuyscripts.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardScreenActivity extends AppCompatActivity {
    List<OnBoardModel> a;
    String b;
    String c;
    ViewPager d;
    private final String MyPREFERENCES = "MyPREFERENCES";

    public void goToOnWeb(View view) {
        if (this.d.getCurrentItem() == 2) {
            Editor edit = getSharedPreferences(this.MyPREFERENCES, 0).edit();
            edit.putBoolean(getString(R.string.isStart), true);
            edit.apply();
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra(getString(R.string.site_name), this.b);
            intent.putExtra(getString(R.string.variant_link), this.c);
            startActivity(intent);
            finish();
            return;
        }
        this.d.setCurrentItem(this.d.getCurrentItem() + 1);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_onboard);
        this.b = getIntent().getStringExtra(getString(R.string.site_name));
        this.c = getIntent().getStringExtra(getString(R.string.variant_link));
        this.d = findViewById(R.id.viewPager);
        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        this.a = new ArrayList();
        this.a.add(new OnBoardModel("", getResources().getColor(R.color.red), R.drawable.signin, getString(R.string.text1), "", getString(R.string.text7)));
        this.a.add(new OnBoardModel("", getResources().getColor(R.color.sandal), R.drawable.afterthat, getString(R.string.text2), getString(R.string.text4), ""));
        this.a.add(new OnBoardModel(getString(R.string.title), getResources().getColor(R.color.blue), R.drawable.stars, getString(R.string.text3), getString(R.string.text6), ""));
        this.d.setAdapter(new CustomPagerAdapter(this, this, (byte) 0));
        this.d.setOffscreenPageLimit(3);
        circleIndicator.setViewPager(this.d);
        super.onCreate(bundle);
    }

    private class CustomPagerAdapter extends PagerAdapter {
        private ImageView displayArt;
        private RelativeLayout layout;
        private final Context mContext;
        private final LayoutInflater mLayoutInflater;
        private TextView text1;
        private TextView text2;
        private TextView text3;
        private TextView title;

        private CustomPagerAdapter(Context context) {
            this.mContext = context;
            this.mLayoutInflater = (LayoutInflater) this.mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        /* synthetic */ CustomPagerAdapter(OnBoardScreenActivity onBoardScreenActivity, Context context, byte b) {
            this(context);
        }

        private void FieldIntialization(View view) {
            this.layout = view.findViewById(R.id.layout);
            this.displayArt = view.findViewById(R.id.image);
            this.title = view.findViewById(R.id.title);
            this.text1 = view.findViewById(R.id.text1);
            this.text2 = view.findViewById(R.id.text2);
            this.text3 = view.findViewById(R.id.text3);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((RelativeLayout) obj);
        }

        public int getCount() {
            return OnBoardScreenActivity.this.a.size();
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            OnBoardModel onBoardModel = OnBoardScreenActivity.this.a.get(i);
            View inflate = this.mLayoutInflater.inflate(R.layout.item_viewpager, viewGroup, false);
            FieldIntialization(inflate);
            Picasso.get().load(onBoardModel.getImage()).into(this.displayArt);
            this.layout.setBackgroundColor(onBoardModel.getOnBoardColor());
            this.title.setText(onBoardModel.getOnBoardTitle());
            this.text1.setText(onBoardModel.getOnBoardText1());
            this.text2.setText(onBoardModel.getOnBoardText2());
            this.text3.setText(onBoardModel.getOnBoardText3());
            viewGroup.addView(inflate);
            return inflate;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }
}
