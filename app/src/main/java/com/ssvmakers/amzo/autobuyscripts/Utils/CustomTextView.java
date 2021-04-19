package com.ssvmakers.amzo.autobuyscripts.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

//import android.support.v7.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setFont();
    }

    private void setFont() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Mark Simonson - Proxima Nova Regular.otf"));
    }
}
