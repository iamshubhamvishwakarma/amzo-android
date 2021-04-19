package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.ssvmakers.amzo.autobuyscripts.R;

public class PreSearchActivity extends AppCompatActivity {
    View search_button;
    SearchView search_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_search);
        search_button = findViewById(R.id.search_button);
        search_query = findViewById(R.id.search_query);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String s = search_query.getText().toString().trim();
//                doSearch(s);
            }
        });
//        search_query.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    private void doSearch(String ssearch_query) {
        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
        searchIntent.putExtra("searchQuery", ssearch_query);
        startActivity(searchIntent);
        this.finish();
    }
}