package com.ssvmakers.amzo.autobuyscripts.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;
import com.ssvmakers.amzo.autobuyscripts.Activities.TipsWebViewActivity;
import com.ssvmakers.amzo.autobuyscripts.Model.AdModel;
import com.ssvmakers.amzo.autobuyscripts.Model.FlashSaleModel;
import com.ssvmakers.amzo.autobuyscripts.Model.FormModel;
import com.ssvmakers.amzo.autobuyscripts.Model.TipsModel;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.FlipAnimation;
import com.ssvmakers.amzo.autobuyscripts.Utils.ItemOffsetDecoration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class ItemDetailsAdapter extends Adapter<ItemDetailsAdapter.MyViewHolder> {
    int a = 0;
    private final List<AdModel> adModelList;
    private final List<FlashSaleModel> albumList;
    private final AppCompatActivity context;
    private final List<FlashSaleModel> finalList;
    private final List<FormModel> formModelList;
    private View itemView;
    private final List<TipsModel> tipsModelList;

    public ItemDetailsAdapter(AppCompatActivity activity, List<FlashSaleModel> list, List<TipsModel> list2, List<FormModel> list3, List<AdModel> list4, List<FlashSaleModel> list5) {
        this.context = activity;
        this.albumList = list;
        this.tipsModelList = list2;
        this.formModelList = list3;
        this.adModelList = list4;
        this.finalList = list5;
    }

    private void submitForm(String str) {
        new OkHttpClient().newCall(new Builder().url("http://fsh.lootalert.in/phoneRequest").post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("name", str).build()).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                call.cancel();
                ItemDetailsAdapter.this.context.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(ItemDetailsAdapter.this.context, R.string.form_request_failed, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onResponse(Call call, Response response) {
                call.cancel();
                ItemDetailsAdapter.this.context.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(ItemDetailsAdapter.this.context, R.string.form_request_success, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public int getItemCount() {
        return this.finalList.size();
    }

    public int getItemViewType(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.finalList.get(i).getAdapterType());
        stringBuilder.append("----P");
        stringBuilder.append(i);
        Log.e("--->", stringBuilder.toString());
        switch (this.finalList.get(i).getAdapterType()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return -1;
        }
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final FlashSaleModel FlashSaleModel;
        switch (myViewHolder.getItemViewType()) {
            case 0:
                Date parse;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append("---0");
                Log.e("--->", stringBuilder.toString());
                FlashSaleModel FlashSaleModel2 = this.finalList.get(i);
                myViewHolder.y.setCardBackgroundColor(Color.parseColor(this.finalList.get(i).getPrimaryColor()));
                myViewHolder.s.setText(FlashSaleModel2.getName());
                myViewHolder.p.setText(FlashSaleModel2.getName());
                try {
                    parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(FlashSaleModel2.getSalesTiming());
                } catch (ParseException e) {
                    e.printStackTrace();
                    parse = null;
                }
                new CountDownTimer(Long.valueOf(parse.getTime() - new Date().getTime()).longValue(), 1000) {
                    public void onFinish() {
                    }

                    public void onTick(long j) {
                        String stringBuilder;
                        StringBuilder stringBuilder2;
                        long toDays = TimeUnit.MILLISECONDS.toDays(j);
                        long toHours = TimeUnit.MILLISECONDS.toHours(j) % 24;
                        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j) % 60;
                        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j));
                        TextView textView = myViewHolder.t;
                        StringBuilder stringBuilder3 = new StringBuilder("Sale starts in \n");
                        if (toDays != 0) {
                            StringBuilder stringBuilder4 = new StringBuilder();
                            stringBuilder4.append(toDays);
                            stringBuilder4.append(" days");
                            stringBuilder = stringBuilder4.toString();
                        } else {
                            stringBuilder = "";
                        }
                        stringBuilder3.append(stringBuilder);
                        stringBuilder3.append(" ");
                        if (toHours != 0) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(toHours);
                            stringBuilder2.append(" hrs");
                            stringBuilder = stringBuilder2.toString();
                        } else {
                            stringBuilder = "";
                        }
                        stringBuilder3.append(stringBuilder);
                        stringBuilder3.append(" ");
                        if (toMinutes != 0) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(toMinutes);
                            stringBuilder2.append(" mins");
                            stringBuilder = stringBuilder2.toString();
                        } else {
                            stringBuilder = "";
                        }
                        stringBuilder3.append(stringBuilder);
                        stringBuilder3.append(" ");
                        if (toSeconds != 0) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(toSeconds);
                            stringBuilder2.append(" secs");
                            stringBuilder = stringBuilder2.toString();
                        } else {
                            stringBuilder = "";
                        }
                        stringBuilder3.append(stringBuilder);
                        textView.setText(stringBuilder3.toString());
                    }
                }.start();
                Picasso.get().load(FlashSaleModel2.getImage()).into(myViewHolder.n);
                setRecyclerViewLayoutManager(myViewHolder.q);
                myViewHolder.q.setAdapter(new CardRecyclerAdapter(this.context, FlashSaleModel2.getVariantModels(), FlashSaleModel2.getName()));
                myViewHolder.A.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        FlipAnimation flipAnimation = new FlipAnimation(myViewHolder.y, myViewHolder.z);
                        if (myViewHolder.y.getVisibility() == View.GONE) {
                            flipAnimation.reverse();
                        }
                        myViewHolder.A.startAnimation(flipAnimation);
                    }
                });
                return;
            case 1:
                FlashSaleModel = this.finalList.get(i);
                Picasso.get().load(FlashSaleModel.getBanner()).into(myViewHolder.C);
                myViewHolder.B.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(FlashSaleModel.getLink()));
                        ItemDetailsAdapter.this.context.startActivity(intent);
                    }
                });
                return;
            case 2:
                Button button;
                int i2;
                FlashSaleModel = this.finalList.get(i);
                myViewHolder.v.setText(FlashSaleModel.getTipsTitle());
                myViewHolder.w.setText(FlashSaleModel.getTipsMessage());
                myViewHolder.x.setText(FlashSaleModel.getButtonText());
                myViewHolder.u.setCardBackgroundColor(Color.parseColor(FlashSaleModel.getPrimColor()));
                myViewHolder.x.setTextColor(Color.parseColor(FlashSaleModel.getPrimColor()));
                if (FlashSaleModel.getButtonText().equalsIgnoreCase("")) {
                    button = myViewHolder.x;
                    i2 = 8;
                } else {
                    button = myViewHolder.x;
                    i2 = 0;
                }
                button.setVisibility(i2);
                myViewHolder.x.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(ItemDetailsAdapter.this.context, TipsWebViewActivity.class);
                        intent.putExtra(ItemDetailsAdapter.this.context.getString(R.string.tips), FlashSaleModel.getWebLink());
                        ItemDetailsAdapter.this.context.startActivity(intent);
                    }
                });
                return;
            case 3:
                FlashSaleModel = this.finalList.get(i);
                myViewHolder.G.setText(FlashSaleModel.getFormAction());
                myViewHolder.H.setText(FlashSaleModel.getFormMessage());
                myViewHolder.E.setCardBackgroundColor(Color.parseColor(FlashSaleModel.getPrimaryColor()));
                myViewHolder.F.setCardBackgroundColor(Color.parseColor(FlashSaleModel.getPrimaryColor()));
                myViewHolder.G.setTextColor(Color.parseColor(FlashSaleModel.getPrimaryColor()));
                myViewHolder.I.setTextColor(Color.parseColor(FlashSaleModel.getPrimaryColor()));
                myViewHolder.I.setHintTextColor(Color.parseColor(FlashSaleModel.getPrimaryColor()));
                myViewHolder.G.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        FlipAnimation flipAnimation = new FlipAnimation(myViewHolder.E, myViewHolder.F);
                        if (myViewHolder.E.getVisibility() == View.GONE) {
                            flipAnimation.reverse();
                        }
                        myViewHolder.D.startAnimation(flipAnimation);
                    }
                });
                myViewHolder.J.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        String trim = myViewHolder.I.getText().toString().trim();
                        if (trim.length() == 0) {
                            Toast.makeText(ItemDetailsAdapter.this.context, R.string.form_error, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ItemDetailsAdapter.this.submitForm(trim);
                        myViewHolder.D.startAnimation(new FlipAnimation(myViewHolder.F, myViewHolder.E));
                    }
                });
                return;
            default:
                return;
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from;
        int i2;
        if (i == 0) {
            from = LayoutInflater.from(viewGroup.getContext());
            i2 = R.layout.adapter_card_list;
        } else if (i == 2) {
            from = LayoutInflater.from(viewGroup.getContext());
            i2 = R.layout.adapter_message_list;
        } else if (i == 3) {
            from = LayoutInflater.from(viewGroup.getContext());
            i2 = R.layout.adapter_form_list;
        } else {
            from = LayoutInflater.from(viewGroup.getContext());
            i2 = R.layout.adapter_ad_list;
        }
        this.itemView = from.inflate(i2, viewGroup, false);
        return new MyViewHolder(this.itemView);
    }

    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int findFirstCompletelyVisibleItemPosition = recyclerView.getLayoutManager() != null ? ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() : 0;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(this.context, R.dimen.item_offset));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.scrollToPosition(findFirstCompletelyVisibleItemPosition);
    }

    class MyViewHolder extends ViewHolder {
        LinearLayout A;
        CardView B;
        ImageView C;
        LinearLayout D;
        CardView E;
        CardView F;
        Button G;
        TextView H;
        EditText I;
        Button J;
        ImageView n;
        TextView o;
        TextView p;
        RecyclerView q;
        CardView r;
        TextView s;
        TextView t;
        CardView u;
        TextView v;
        TextView w;
        Button x;
        CardView y;
        CardView z;

        MyViewHolder(View view) {
            super(view);
            this.u = view.findViewById(R.id.layout);
            this.v = view.findViewById(R.id.headerText);
            this.w = view.findViewById(R.id.textView);
            this.B = view.findViewById(R.id.ad_card);
            this.C = view.findViewById(R.id.adImage);
            this.x = view.findViewById(R.id.buttonPanel);
            this.n = view.findViewById(R.id.image);
            this.A = view.findViewById(R.id.mainCard);
            this.p = view.findViewById(R.id.variant_phone_name);
            this.o = view.findViewById(R.id.text);
            this.r = view.findViewById(R.id.cardItem);
            this.s = view.findViewById(R.id.phone_name);
            this.q = view.findViewById(R.id.variantRecycler);
            this.t = view.findViewById(R.id.sales_time);
            this.y = view.findViewById(R.id.cardItem);
            this.z = view.findViewById(R.id.secCardItem);
            this.D = view.findViewById(R.id.formMainCard);
            this.E = view.findViewById(R.id.formPrimaryCard);
            this.F = view.findViewById(R.id.formSecondaryCard);
            this.H = view.findViewById(R.id.formMessage);
            this.G = view.findViewById(R.id.formButton);
            this.I = view.findViewById(R.id.phone_edit_text);
            this.J = view.findViewById(R.id.formSubmitButton);
        }
    }
}
