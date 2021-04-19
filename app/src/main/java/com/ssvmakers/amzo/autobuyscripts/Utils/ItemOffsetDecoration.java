package com.ssvmakers.amzo.autobuyscripts.Utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;

public class ItemOffsetDecoration extends ItemDecoration {
    private final int mItemOffset;

    private ItemOffsetDecoration(int i) {
        this.mItemOffset = i;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int i) {
        this(context.getResources().getDimensionPixelSize(i));
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.set(this.mItemOffset, this.mItemOffset, this.mItemOffset, this.mItemOffset);
    }
}
