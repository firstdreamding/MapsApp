package com.example.gding3.maindelivery;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.Toolbar;


public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        configureNextButton();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //check first item in the navigation
        navigationView.setCheckedItem(R.id.nav_account);
        //open myaccount frag initially
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.account_setting_page, new MyAccountFragment());
        ft.commit();
    }

    private void configureNextButton() {
        Button nextButton = (Button) findViewById(R.id.deliveryDashboard);
        Button otherNextButton = (Button) findViewById(R.id.recipeDashboard);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
            }
        });
        otherNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, RecipeDashboardActivity.class));
            }
        });

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //creating fragment object
        Fragment fragment = null;


        if (id == R.id.nav_account) {
            fragment = new MyAccountFragment();

            // Handle the account action
        } else if (id == R.id.nav_favourites) {
            fragment = new FavouritesFragment();

        } else if (id == R.id.nav_home) {
            fragment = new HomeFragment();

        } else if (id == R.id.nav_notifications) {
            fragment = new NotificationFragment();

        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();

        }

        //fragment changing code
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.account_setting_page, fragment);
            ft.commit();
        }
        //cloding the drawer after selecting
        DrawerLayout drawer = findViewById(R.id.nav_account);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
