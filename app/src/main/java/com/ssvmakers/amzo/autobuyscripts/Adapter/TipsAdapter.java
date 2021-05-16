package com.ssvmakers.amzo.autobuyscripts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssvmakers.amzo.autobuyscripts.Activities.NormalWebViewActivity;
import com.ssvmakers.amzo.autobuyscripts.Model.TipsModel;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.ItemOffsetDecoration;

import java.util.List;


public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private final Context context;
    List<TipsModel> tipsList;
    private View storeView;

    public int getItemCount() {
        return this.tipsList.size();
    }

    public TipsAdapter(Context context, List<TipsModel> tipsList) {
        this.context = context;
        this.tipsList = tipsList;
    }


    public void onBindViewHolder(final TipsAdapter.TipsViewHolder myViewHolder, int i) {
        final TipsModel tip;
        tip = this.tipsList.get(i);
//        Picasso.get().load(store.getImage()).into(myViewHolder.imageView);
        myViewHolder.heading.setText(tip.getTipsTitle());
        myViewHolder.desc.setText(tip.getTipsMessage());
        if (tip.getButtonText().isEmpty()) {
            myViewHolder.action.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.action.setText(tip.getButtonText());
        }
        myViewHolder.cardView.setCardBackgroundColor(Color.parseColor(tip.getPrimColor()));
        myViewHolder.action.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, NormalWebViewActivity.class);
                intent.putExtra("storeUrl", tip.getWebLink());
                TipsAdapter.this.context.startActivity(intent);
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

    public TipsAdapter.TipsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        this.storeView = from.inflate(R.layout.adapter_message_list, viewGroup, false);
        return new TipsAdapter.TipsViewHolder(this.storeView);
    }

    class TipsViewHolder extends RecyclerView.ViewHolder {
        TextView heading;
        TextView desc;
        Button action;
        CardView cardView;

        TipsViewHolder(View view) {
            super(view);
            this.heading = view.findViewById(R.id.heading);
            this.desc = view.findViewById(R.id.desc);
            this.action = view.findViewById(R.id.action);
            this.cardView = view.findViewById(R.id.layout);

        }
    }
}
