package com.example.dell.bankeldam.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.bankeldam.Fragments.DonationRequests_Fragment;
import com.example.dell.bankeldam.Fragments.Posts;

public class TabsPageAdapter extends FragmentStatePagerAdapter {
    String[] tabsarray= new String[]{"المقالات","طلبات التبرع"};
    public TabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsarray[position];
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                Posts fileone=new Posts();
                return fileone;
            case 1:
                DonationRequests_Fragment requstone=new DonationRequests_Fragment();
                return requstone;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
