package com.store.okidosmobileapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.store.okidosmobileapp.TabFragment.int_items;

public class PagerAdapter  extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CategoriesFragment();
            case 2:
                return new NewStockFragment();



        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "HOME";
            case 1:
                return "CATEGORIES";
            case 2:
                return "NEW STOCK";


        }

        return null;
    }

}