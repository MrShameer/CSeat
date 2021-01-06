package com.example.cseat.ui.main;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cseat.R;
import com.example.cseat.SectionPelajar;
import com.example.cseat.TabPelajar;
import com.example.cseat.SectionMaterials;
import com.example.cseat.SectionRPI;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final TabPelajar mContext;

    public SectionsPagerAdapter(TabPelajar context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        //Log.d("qwe","in "+position);

        if(position == 0){
            return new SectionPelajar();
        }
        else if (position == 1){
            return new SectionRPI();
        }
        else if (position == 2){
            return  new SectionMaterials();
        }

        return PlaceholderFragment.newInstance(position + 1);
        //return new SectionPelajar();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}