package com.ssvmakers.amzo.autobuyscripts.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.ssvmakers.amzo.autobuyscripts.Adapter.GenricRecyclerAdapter;
import com.ssvmakers.amzo.autobuyscripts.Adapter.ItemDetailsAdapter;
import com.ssvmakers.amzo.autobuyscripts.Adapter.SliderAdapter;
import com.ssvmakers.amzo.autobuyscripts.Adapter.StoreAdapter;
import com.ssvmakers.amzo.autobuyscripts.Adapter.TipsAdapter;
import com.ssvmakers.amzo.autobuyscripts.Model.Deal;
import com.ssvmakers.amzo.autobuyscripts.Model.FlashSaleModel;
import com.ssvmakers.amzo.autobuyscripts.Model.SliderItem;
import com.ssvmakers.amzo.autobuyscripts.Model.Store;
import com.ssvmakers.amzo.autobuyscripts.Model.TipsModel;
import com.ssvmakers.amzo.autobuyscripts.Model.VariantModel;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.ApplicationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import com.google.android.gms.ads.AdRequest.Builder;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.reward.RewardItem;
//import com.google.android.gms.ads.reward.RewardedVideoAd;
//import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class Main2Activity extends AppCompatActivity {
    public static final long MILLIS_PER_DAY = 86400000;
    private SimpleSearchView searchView;
    //    public static RewardedVideoAd mRewardedVideoAd = null;
    public static final String mypreference = "mypref";
    public static final String URL_PREFIX = "https://asia-south1-amzoautobuy.cloudfunctions.net/amzoautobuyapi/v1/";
    //    List<FlashSaleModel> saleList1;
    List<FlashSaleModel> saleListFinal;
    //    List<AdModel> adModels;
    List<VariantModel> variantModelList;
    //    List<TipsModel> tipsModels;
//   / List<FormModel> s;
    List<Store> storeList;
    List<Deal> dealList;
    ItemDetailsAdapter flashSaleAdapter;
    RecyclerView flashSaleRecycler, storeRecycler, dealRecycler, tipsRecycler;
    StoreAdapter storeAdapter;
    SwitchCompat v;
    FlashSaleModel w;
    SliderView sliderView;
    private SliderAdapter sliderAdapter;
    //    private String adUrl = "https://fsh.lootalert.in/ads";
//    private String deviceUrl = "https://fsh.lootalert.in/infobox?versionCode=";
    private RelativeLayout loadingLayout;
    private TextView sampText;
    private ImageView search_icon;
    private ArrayList tipsModelsList;


    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        this.v = findViewById(R.id.autoCheck);
        this.sampText = findViewById(R.id.sampText);
        searchView = findViewById(R.id.searchView);
        this.loadingLayout = findViewById(R.id.loadingLayout);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        this.setupSlider();
        int i = 0;
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.flashSaleRecycler = findViewById(R.id.flashcards);
        this.storeRecycler = findViewById(R.id.storeRecycler);
        this.dealRecycler = findViewById(R.id.dealRecycler);
        this.tipsRecycler = findViewById(R.id.tipsRecycler);
//        this.search_icon = findViewById(R.id.search_icon);
        ((ShimmerFrameLayout) findViewById(R.id.shimmer_view_container)).startShimmer();
        saleListFinal = new ArrayList<>();
//        this.saleList1 = new ArrayList();
        this.tipsModelsList = new ArrayList();
        this.tipsModelsList.add(new TipsModel(getString(R.string.tips_msg_2), getString(R.string.quick_tips), "", "#9f00ff", ""));
        this.tipsModelsList.add(new TipsModel("Do you want to know our policies regarding the collection, use, and disclosure of personal data?", "Privacy Policy", "Click here to read", "#ff9800", "https://privacy.amzo.citbit.in/"));
//        this.s = new ArrayList();
//        this.s.add(new FormModel(getString(R.string.form_model_message), "Let us know", "#2c3e50"));
//        new GetDeviceList(this, (byte) 0).execute(new Void[0]);
//        new GetMessages(this, (byte) 0).execute(new Void[0]);
        fetchSaleJsonResponse();
        fetchJsonResponse();
        fetchDealJsonResponse();
        setRecyclerViewLayoutManager(this.flashSaleRecycler);
        setStoreRecyclerViewLayoutManager(this.storeRecycler);
        setStoreRecyclerViewLayoutManager(this.dealRecycler);
        setStoreRecyclerViewLayoutManager(this.tipsRecycler);
        tipsRecycler.setAdapter(new TipsAdapter(this, tipsModelsList));
        long j = getSharedPreferences("mypref", 0).getLong(getString(R.string.time), 0);
        if (j != 0) {
            Date date = new Date();
            Calendar.getInstance().setTime(date);
            if (Math.abs(date.getTime() - j) > MILLIS_PER_DAY) {
                i = 1;
            }
            this.v.setChecked(true);
        }
//        search_icon.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Main2Activity.this.startSearchActivity();
//            }
//        });
//        this.v.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
//                if (z) {
////                    MobileAds.initialize(Main2Activity.this, "ca-app-pub-3800086660783473~1558974000");
////                    RewardedVideoAd rewardedVideoAdInstance = MobileAds.getRewardedVideoAdInstance(Main2Activity.this);
////                    Main2Activity.mRewardedVideoAd = rewardedVideoAdInstance;
////                    rewardedVideoAdInstance.setRewardedVideoAdListener(Main2Activity.this);
////                    Main2Activity.this.loadRewardedVideoAd();
//                    Main2Activity.this.startActivity(new Intent(Main2Activity.this, AutoCheckOutFirst.class));
//                }
//            }
//        });
//        this.sampText.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                String packageName = Main2Activity.this.getPackageName();
//                Main2Activity main2Activity;
//                StringBuilder stringBuilder;
//                try {
//                    main2Activity = Main2Activity.this;
//                    stringBuilder = new StringBuilder("market://details?id=");
//                    stringBuilder.append(packageName);
//                    main2Activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
//                } catch (ActivityNotFoundException unused) {
//                    main2Activity = Main2Activity.this;
//                    stringBuilder = new StringBuilder("https://play.google.com/store/apps/details?id=");
//                    stringBuilder.append(packageName);
//                    main2Activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
//                }
//            }
//        });
        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SimpleSearchView", "Submit:" + query);
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                searchIntent.putExtra("searchQuery", query);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SimpleSearchView", "Text changed:" + newText);
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {
                Log.d("SimpleSearchView", "Text cleared");
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        setupSearchView(menu);
        return true;
    }

    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
//        searchView.setTabLayout(tabLayout);

        // Adding padding to the animation because of the hidden menu item
        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x -= DimensUtils.convertDpToPx(40, this);
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setStoreRecyclerViewLayoutManager(RecyclerView storeRecycler) {
        int findFirstCompletelyVisibleItemPosition = storeRecycler.getLayoutManager() != null ? ((GridLayoutManager) storeRecycler.getLayoutManager()).findFirstCompletelyVisibleItemPosition() : 0;
        storeRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        recyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        storeRecycler.setNestedScrollingEnabled(false);
        storeRecycler.scrollToPosition(findFirstCompletelyVisibleItemPosition);


    }


//    public void onRewarded(RewardItem rewardItem) {
//        Log.e("RewardVideoTe", "OnRewarded");
//        Date date = new Date();
//        Calendar.getInstance().setTime(date);
//        Editor edit = getSharedPreferences("mypref", MODE_PRIVATE).edit();
//        edit.putLong(getString(R.string.time), date.getTime());
//        edit.apply();
//        this.v.setChecked(true);
//    }


    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int findFirstCompletelyVisibleItemPosition = recyclerView.getLayoutManager() != null ? ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() : 0;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        recyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.scrollToPosition(findFirstCompletelyVisibleItemPosition);
    }




    public void removeLastItem() {
        if (sliderAdapter.getCount() - 1 >= 0)
            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
    }

    public void addNewItem() {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Mega Feshion Sale 16-18 April");
        sliderItem.setStore("Amazon");
        sliderItem.setPageUrl("https://linksredirect.com/?cid=20962&source=linkkit&url=https%3A%2F%2Fwww.amazon.in%2Fb%2Fref%3Dsurl_fashion%3Fnode%3D6648217031%26pf_rd_r%3D9S52XW470Z44VYKH35E7%26pf_rd_p%3D178f18d7-de9e-4593-968a-0b845e9b1796");
        sliderItem.setImageUrl("https://images-eu.ssl-images-amazon.com/images/G/31/img21/Fashion/Gateway/Flip/MFS_April/GW_PC_BUNK_1500x600_1._CB655062810_.jpg");
        sliderAdapter.addItem(sliderItem);
    }

    public void setupSlider() {
        sliderView = findViewById(R.id.imageSlider);
        sliderAdapter = new SliderAdapter(this);
        renewItems();
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
    }

    private void fetchJsonResponse() {
        String tag_json_arry = "json_array_req";
        String TAG = "stores";
        String storeUrl = URL_PREFIX.concat("homepage/stores");


        JsonArrayRequest req = new JsonArrayRequest(storeUrl,
                response -> {

                    Log.d("debug", "response.toString()");
                    storeList = new ArrayList<>();
                    for (int i2 = 0; i2 < response.length(); i2++) {
                        JSONObject jSONObject = null;
                        try {
                            jSONObject = response.getJSONObject(i2);
                            Store s = new Store(jSONObject.getString("title"), jSONObject.getString("url"), jSONObject.getString("image"));
//                            Log.d("debug", s.getName()+ storeList.size());
                            storeList.add(s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Main2Activity.this.storeAdapter = new StoreAdapter(Main2Activity.this, storeList);
                    storeRecycler.setAdapter(Main2Activity.this.storeAdapter);

                }, error -> VolleyLog.d(TAG, "Error: " + error.getLocalizedMessage()));

// Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(req, tag_json_arry);

        /* Add your Requests to the RequestQueue to execute */
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    private void fetchDealJsonResponse() {
        String tag_json_arry = "json_array_req";
        String TAG = "stores";
        String storeUrl = URL_PREFIX.concat("homepage/Deals");


        JsonArrayRequest req = new JsonArrayRequest(storeUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        dealList = new ArrayList<>();
                        for (int i2 = 0; i2 < response.length(); i2++) {
                            JSONObject jSONObject = null;
                            try {
                                jSONObject = response.getJSONObject(i2);
                                Deal s = new Deal(jSONObject.getString("id"), jSONObject.getString("storeName"), jSONObject.getString("url"), jSONObject.getString("image"), jSONObject.getString("oldPrice"), jSONObject.getString("newPrice"), jSONObject.getString("name"), jSONObject.getString("primaryColor"));
                                dealList.add(s);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dealRecycler.setAdapter(new GenricRecyclerAdapter(Main2Activity.this, dealList));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Main2Activity.this.loadingLayout.setVisibility(View.GONE);
            }
        });

// Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(req, tag_json_arry);

        /* Add your Requests to the RequestQueue to execute */
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    private void fetchSaleJsonResponse() {
        String tag_json_arry = "json_array_req";
        String TAG = "stores";
        String saleUrl = URL_PREFIX.concat("homepage/saleItems");


        JsonArrayRequest req = new JsonArrayRequest(saleUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jSONArray) {


                        try {

                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                Main2Activity.this.w = new FlashSaleModel(jSONObject.getInt(Main2Activity.this.getString(R.string.flash_id)), jSONObject.getString(Main2Activity.this.getString(R.string.flash_name)), jSONObject.getString(Main2Activity.this.getString(R.string.flash_image)), jSONObject.getString(Main2Activity.this.getString(R.string.flash_site)), jSONObject.getString(Main2Activity.this.getString(R.string.primary_color)), jSONObject.getString(Main2Activity.this.getString(R.string.salesTiming)), 0);
                                Main2Activity.this.variantModelList = new ArrayList();
                                JSONArray jSONArray2 = jSONObject.getJSONArray(Main2Activity.this.getString(R.string.variant));
                                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                                    Main2Activity.this.variantModelList.add(new VariantModel(jSONObject2.getInt(Main2Activity.this.getString(R.string.variant_Device_id)), jSONObject2.getString(Main2Activity.this.getString(R.string.variant_name)), jSONObject2.getString(Main2Activity.this.getString(R.string.variant_image)), jSONObject2.getString(Main2Activity.this.getString(R.string.variant_link)), jSONObject.getString(Main2Activity.this.getString(R.string.flash_site))));
                                }
                                Main2Activity.this.w.setVariantModels(Main2Activity.this.variantModelList);
                                Main2Activity.this.saleListFinal.add(Main2Activity.this.w);
                            }
                            Main2Activity.this.loadingLayout.setVisibility(View.GONE);
                            Main2Activity.this.flashSaleAdapter = new ItemDetailsAdapter(Main2Activity.this, null, null, null, null, Main2Activity.this.saleListFinal);
                            Main2Activity.this.flashSaleRecycler.setAdapter(Main2Activity.this.flashSaleAdapter);

                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Main2Activity.this.loadingLayout.setVisibility(View.GONE);
            }
        });

// Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(req, tag_json_arry);

        /* Add your Requests to the RequestQueue to execute */
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    public void renewItems() {
        String tag_json_arry = "json_array_req";
        String TAG = "stores";
        String storeUrl = URL_PREFIX.concat("homepage/slides");

        List<SliderItem> sliderItemList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(storeUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        dealList = new ArrayList<>();
                        for (int i2 = 0; i2 < response.length(); i2++) {
                            JSONObject jSONObject = null;
                            try {
                                jSONObject = response.getJSONObject(i2);

                                SliderItem sliderItem = new SliderItem();
                                sliderItem.setDescription(jSONObject.getString("description"));
                                sliderItem.setStore("Amazon");
                                sliderItem.setPageUrl("https://linksredirect.com/?cid=20962&source=linkkit&url=https%3A%2F%2Fwww.amazon.in%2Fb%2Fref%3Dsurl_fashion%3Fnode%3D6648217031%26pf_rd_r%3D9S52XW470Z44VYKH35E7%26pf_rd_p%3D178f18d7-de9e-4593-968a-0b845e9b1796");
                                sliderItem.setImageUrl("https://images-eu.ssl-images-amazon.com/images/G/31/img21/Fashion/Gateway/Flip/MFS_April/GW_PC_BUNK_1500x600_1._CB655062810_.jpg");
                                sliderItemList.add(sliderItem);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        sliderAdapter.renewItems(sliderItemList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Main2Activity.this.loadingLayout.setVisibility(View.GONE);
            }
        });
        ApplicationController.getInstance().addToRequestQueue(req, tag_json_arry);

    }

}
