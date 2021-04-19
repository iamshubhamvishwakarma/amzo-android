package com.ssvmakers.amzo.autobuyscripts.Activities.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ssvmakers.amzo.autobuyscripts.R;

import java.util.ArrayList;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_flipkart, R.string.tab_amazon, R.string.tata_cliq, R.string.tab_ajio, R.string.tab_myntra};
    private final Context mContext;
    private ArrayList<String> storeUrls = new ArrayList<String>();

    public SectionsPagerAdapter(Context context, FragmentManager fm, ArrayList<String> storeUrls) {
        super(fm);
        mContext = context;
        this.storeUrls = storeUrls;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).\
        PlaceholderFragment placeholderFragment = PlaceholderFragment.newInstance(position + 1, "google.com");
        Bundle bundle = new Bundle();
        bundle.putString("storeUrl", this.storeUrls.get(position));
        placeholderFragment.setArguments(bundle);
        return placeholderFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }
}