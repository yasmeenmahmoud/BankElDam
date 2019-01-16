package com.example.dell.bankeldam.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.example.dell.bankeldam.Fragments.About_Fragment;
import com.example.dell.bankeldam.Fragments.Callus_Fragment;
import com.example.dell.bankeldam.Fragments.CreateDonationRequest_Fragment;
import com.example.dell.bankeldam.Fragments.DonationDetails_Fragment;
import com.example.dell.bankeldam.Fragments.Editinfo_Fragment;
import com.example.dell.bankeldam.Fragments.Favourites_Fragment;
import com.example.dell.bankeldam.Fragments.NotificationList_Fragment;
import com.example.dell.bankeldam.Fragments.PostsDetails_Fragment;
import com.example.dell.bankeldam.Fragments.Report_Fragment;
import com.example.dell.bankeldam.Fragments.Tabs_fragment;
import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.R;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int mCurrentSelectedPosition = 0;
    Fragment fr;
    DrawerLayout drawer;
    public NavigationView navigationView;
    public ActionBarDrawerToggle toggle;
    Toolbar toolbar;
SharedPereferenceClass sharedPereferenceClass=new SharedPereferenceClass();
    @SuppressLint({"NewApi", "RestrictedApi"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.drawer_icon);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mCurrentSelectedPosition < 11 && mCurrentSelectedPosition > 0) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                LoadFragment(new Tabs_fragment());
                getSupportActionBar().setTitle(" ");
                mCurrentSelectedPosition = 0;
                toggle.setDrawerIndicatorEnabled(false);
                toggle.setHomeAsUpIndicator(R.drawable.drawer_icon);
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this, R.style.AlertDialogTheme);
                alertDialog.setTitle("Exit...");
                alertDialog.setMessage("Are you sure you want To Exit ?");
                alertDialog.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                alertDialog.show();

            }
        }
    }

    public void LoadFragment(Fragment fr) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fr);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notify) {
            LoadFragment(new NotificationList_Fragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.nav_main:
                mCurrentSelectedPosition = 0;
                fr = new Tabs_fragment();
                addFragment(new Tabs_fragment());
                break;
            case R.id.nav_information:
                mCurrentSelectedPosition = 1;
                fr = new Editinfo_Fragment();
                break;
            case R.id.nav_favorites:
                mCurrentSelectedPosition = 2;
                fr = new Favourites_Fragment();
                break;

            case R.id.nav_report:
                mCurrentSelectedPosition = 3;
                fr = new Report_Fragment();
                break;
            case R.id.nav_call:
                mCurrentSelectedPosition = 4;
                fr = new Callus_Fragment();
                break;
            case R.id.nav_about:
                mCurrentSelectedPosition = 5;
                fr = new About_Fragment();
                break;
            case R.id.nav_signout:
                mCurrentSelectedPosition = 6;
                Intent Logoutintent=new Intent(this,login.class);
                startActivity(Logoutintent);
                sharedPereferenceClass.storeKey(this, "client_register_success", "false");
                break;
            default:
                mCurrentSelectedPosition = 0;

        }
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fr);
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.flContent, fragment).commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CreateDonationRequest_Fragment) {
            mCurrentSelectedPosition = 7;
        } else if (fragment instanceof PostsDetails_Fragment) {
            mCurrentSelectedPosition =8;
        } else if (fragment instanceof DonationDetails_Fragment) {
            mCurrentSelectedPosition = 9;
        } else if (fragment instanceof NotificationList_Fragment) {
            mCurrentSelectedPosition = 10;
        }
    }


}
