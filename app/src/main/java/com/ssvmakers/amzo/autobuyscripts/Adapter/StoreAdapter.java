package com.ssvmakers.amzo.autobuyscripts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ssvmakers.amzo.autobuyscripts.Activities.NormalWebViewActivity;
import com.ssvmakers.amzo.autobuyscripts.Model.Store;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.ItemOffsetDecoration;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private final Context context;
    List<Store> storeList;
    private View storeView;

    public int getItemCount() {
        return this.storeList.size();
    }

    public StoreAdapter(Context context, List<Store> storeList) {
        this.context = context;
        this.storeList = storeList;
    }


    public void onBindViewHolder(final StoreAdapter.StoreViewHolder myViewHolder, int i) {
        final Store store;
        store = this.storeList.get(i);
        Picasso.get().load(store.getImage()).into(myViewHolder.imageView);
        myViewHolder.textView.setText(store.getName());
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, NormalWebViewActivity.class);
                intent.putExtra("storeUrl", store.getUrl());
                StoreAdapter.this.context.startActivity(intent);
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

    public StoreAdapter.StoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        this.storeView = from.inflate(R.layout.storelayout, viewGroup, false);
        return new StoreAdapter.StoreViewHolder(this.storeView);
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        StoreViewHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.storeImage);
            this.textView = view.findViewById(R.id.storeName);

        }
    }
}
