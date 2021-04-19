package com.ssvmakers.amzo.autobuyscripts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ssvmakers.amzo.autobuyscripts.Activities.NormalWebViewActivity;
import com.ssvmakers.amzo.autobuyscripts.Model.Deal;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.ItemOffsetDecoration;

import java.util.Currency;
import java.util.List;


public class GenricRecyclerAdapter extends RecyclerView.Adapter<GenricRecyclerAdapter.GenricViewHolder> {
    private final Context context;
    List<Deal> dealList;
    private View dealView;

    public int getItemCount() {
        return this.dealList.size();
    }

    public GenricRecyclerAdapter(Context context, List<Deal> dealList) {
        this.context = context;
        this.dealList = dealList;
    }


    public void onBindViewHolder(final GenricRecyclerAdapter.GenricViewHolder myViewHolder, int i) {
        final Deal deal;
        deal = this.dealList.get(i);
        Picasso.get().load(deal.getImage()).into(myViewHolder.imageView);
        myViewHolder.title.setText(deal.getName());
        Currency currency = Currency.getInstance("INR");
        String symbol = currency.getSymbol();
        myViewHolder.oldPrice.setText(symbol+deal.getOldPrice());
        myViewHolder.newPrice.setText(symbol+deal.getNewPrice());
        Picasso.get().load(deal.getStoreName()).into(myViewHolder.store);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, NormalWebViewActivity.class);
                intent.putExtra("storeUrl", deal.getUrl());
                GenricRecyclerAdapter.this.context.startActivity(intent);
            }
        });
        myViewHolder.grabDeal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, NormalWebViewActivity.class);
                intent.putExtra("storeUrl", deal.getUrl());
                GenricRecyclerAdapter.this.context.startActivity(intent);
            }
        });

    }

    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int findFirstCompletelyVisibleItemPosition = recyclerView.getLayoutManager() != null ? ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() : 0;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(this.context, R.dimen.item_offset));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.scrollToPosition(findFirstCompletelyVisibleItemPosition);
    }

    public GenricRecyclerAdapter.GenricViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        this.dealView = from.inflate(R.layout.deal_item, viewGroup, false);
        return new GenricRecyclerAdapter.GenricViewHolder(this.dealView);
    }

    class GenricViewHolder extends RecyclerView.ViewHolder {
        TextView oldPrice, newPrice, title;
        ImageView imageView, store;
        Button grabDeal;

        GenricViewHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.item_image);
            this.store = view.findViewById(R.id.storeName);
            this.title = view.findViewById(R.id.item_title);
            this.oldPrice = view.findViewById(R.id.old_price);
            this.newPrice = view.findViewById(R.id.newprice);
            this.grabDeal = view.findViewById(R.id.grabdealbtn);
            this.oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
}
