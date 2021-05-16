package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabLayout;
import com.ssvmakers.amzo.autobuyscripts.Activities.ui.main.PlaceholderFragment;
import com.ssvmakers.amzo.autobuyscripts.Activities.ui.main.SectionsPagerAdapter;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.ApplicationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    //    ArrayList<String> siteList = new ArrayList<>(Arrays.asList("https://www.flipkart.com/search?q=", "https://www.amazon.in/s?k=", "https://www.tatacliq.com/search/?searchCategory=all&text=", "https://www.ajio.com/search/?text=", "https://www.myntra.com/"));
    ArrayList<String> siteList = new ArrayList<>();
    ArrayList<String> searchList = new ArrayList<>();
    ViewPager viewPager;
    RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String searchQuery = getIntent().getStringExtra("searchQuery");
        TextView toolbar = findViewById(R.id.title);
        viewPager = findViewById(R.id.view_pager);
        loadingLayout = findViewById(R.id.loadingLayout);

        toolbar.setText(searchQuery);
        getSearchUrls(searchQuery);
        viewPager.setOffscreenPageLimit(3);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (!PlaceholderFragment.handleBackPressed(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }

    public void goToBack(View view) {
        onBackPressed();
    }


    public void getSearchUrls(String query) {
        loadingLayout.setVisibility(View.VISIBLE);
        String tag_json_obj = "json_array_req";
        String TAG = "stores";
        String saleUrl = Main2Activity.URL_PREFIX.concat("homepage/search?q=").concat(query);


        JsonObjectRequest req = new JsonObjectRequest(saleUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("urls");
                            for (int i2 = 0; i2 < jsonArray.length(); i2++) {
                                JSONObject jSONObject = jsonArray.getJSONObject(i2);
                                siteList.add(jSONObject.getString("url"));
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        Log.i("siteData", siteList.size() + "");
                        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager(), siteList);
                        viewPager.setAdapter(sectionsPagerAdapter);
                        SearchActivity.this.loadingLayout.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                SearchActivity.this.loadingLayout.setVisibility(View.GONE);
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        ApplicationController.getInstance().addToRequestQueue(req);
    }

}