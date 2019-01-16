package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.bankeldam.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tabs_fragment extends Fragment {


    public Tabs_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        view=inflater.inflate(R.layout.fragment_tabs_fragment, container, false);

        final TabLayout mytabs = view.findViewById(R.id.tabs);
        ViewPager pager = view.findViewById(R.id.viewpager);
        /* TabsPageAdapter pageAdapter = new TabsPageAdapter(getFragmentManager());
        pager.setAdapter(pageAdapter);
        mytabs.setupWithViewPager(pager);*/
        setupViewPager(pager);
        mytabs.setupWithViewPager(pager);
        FloatingActionButton floating=(FloatingActionButton)view.findViewById(R.id.fab);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.flContent, new CreateDonationRequest_Fragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mytabs.getTabAt(0).select();

        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getFragmentManager());

            // The view has RTL layout
            adapter.addFragment(new Posts(), getResources().getString(R.string.files));
            adapter.addFragment(new DonationRequests_Fragment(),getResources().getString(R.string.requests));

        viewPager.setAdapter(adapter);
    }

    static  class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
