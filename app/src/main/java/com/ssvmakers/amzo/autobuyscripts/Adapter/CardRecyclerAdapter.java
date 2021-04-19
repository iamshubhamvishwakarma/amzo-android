package com.ssvmakers.amzo.autobuyscripts.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;
import com.ssvmakers.amzo.autobuyscripts.Activities.OnBoardScreenActivity;
import com.ssvmakers.amzo.autobuyscripts.Activities.WebViewActivity;
import com.ssvmakers.amzo.autobuyscripts.Model.VariantModel;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.CustomTextView;

import java.util.List;

//import com.crashlytics.android.answers.Answers;
//import com.crashlytics.android.answers.CustomEvent;

public class CardRecyclerAdapter extends Adapter<CardRecyclerAdapter.MyViewHolder> {
    private final String phoneName;
    SharedPreferences a;
    private final String MyPREFERENCES = "MyPREFERENCES";
    private final List<VariantModel> albumList;
    private final AppCompatActivity context;
    private View itemView;

    public CardRecyclerAdapter(AppCompatActivity activity, List<VariantModel> list, String str) {
        this.context = activity;
        this.albumList = list;
        this.phoneName = str;
    }

    public int getItemCount() {
        return this.albumList.size();
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final VariantModel variantModel = this.albumList.get(i);
        myViewHolder.o.setText(variantModel.getColor());
        Picasso.get().load(variantModel.getImage()).into(myViewHolder.p);
        myViewHolder.n.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
//                Answers.getInstance().logCustom((CustomEvent) ((CustomEvent) new CustomEvent("WebView Opened").putCustomAttribute("Phone Name", CardRecyclerAdapter.this.phoneName)).putCustomAttribute("Variant Color", variantModel.getColor()));
                CardRecyclerAdapter.this.a = CardRecyclerAdapter.this.context.getSharedPreferences(CardRecyclerAdapter.this.MyPREFERENCES, 0);
                Intent intent = !CardRecyclerAdapter.this.a.getBoolean(CardRecyclerAdapter.this.context.getString(R.string.isStart), false) ? new Intent(CardRecyclerAdapter.this.context, OnBoardScreenActivity.class) : new Intent(CardRecyclerAdapter.this.context, WebViewActivity.class);
                intent.putExtra(CardRecyclerAdapter.this.context.getString(R.string.site_name), variantModel.getSite());
                intent.putExtra(CardRecyclerAdapter.this.context.getString(R.string.variant_link), variantModel.getLink());
                CardRecyclerAdapter.this.context.startActivity(intent);
            }
        });
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_variant_list, viewGroup, false);
        return new MyViewHolder(this.itemView);
    }

    class MyViewHolder extends ViewHolder {
        CardView n;
        CustomTextView o;
        ImageView p;

        MyViewHolder(View view) {
            super(view);
            this.n = view.findViewById(R.id.cardItem);
            this.o = view.findViewById(R.id.ram);
            this.p = view.findViewById(R.id.image);
        }
    }
}
