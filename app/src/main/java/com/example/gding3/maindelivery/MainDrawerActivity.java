package com.example.gding3.maindelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainDrawerActivity extends AppCompatActivity {
    private NavigationView navigationView;
    protected DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView txtName;

    private Fragment HomeFragment;
    private Fragment MyAccountFragment;
    private Fragment FavouritesFragment;


    //index to identify current nav menu item
    public static int navItemIndex = 0;

    //tags used to attch the fragments
    private static final String TAG_HOME = "Home";
    private static final String TAG_SETTINGS = "Settings";
    private static final String TAG_FAVOURITES = "Favourites";
    private static final String TAG_MY_ACCOUNT = "My Account";
    private static final String TAG_NOTIFICATIONS = "Notifications";
    public static String CURRENT_TAG = TAG_HOME;

    //flag to load home fragment when the user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    //toolbar titles respected to selected nav menu item
    private String[] activityTitles;


    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.menu_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = findViewById(R.id.MyMenuDrawer);
        navigationView = findViewById(R.id.nav_view);


        //Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.nav_header_textView);



        //load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.navi_itm_activity_titles);
        //load nav menu header data
        loadNavHeader();


        //intializing navi menu
        setUpNavigationView();
    }

    // load navi menu header info
    private void loadNavHeader() {
        navigationView.getMenu().getItem(3).setActionView(R.layout.navigation_drawer_dot);
    }


    //returns respected fragment that user selected from navimenu

    private void loadHomeFragment() {
        //selecting appropriate nav menu itm
        selectNavMenu();
        //set toolbar title
        setToolbarTitle();

        //if user select the current navigation menu again, dont do anything
        //just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            //show or hide the fab button
            toogleFab();
            return;


        }
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                //home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                //my account
                MyAccountFragment myAccountFragment= new MyAccountFragment();
                return myAccountFragment;
            case 2:
                //favourites
                FavouritesFragment favouritesFragment = new FavouritesFragment();
                return favouritesFragment;
            case 3:
                //settings
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new Fragment();
        }


    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);

    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);

    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_account:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MY_ACCOUNT;
                        break;
                    case R.id.nav_favourites:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_FAVOURITES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                    default:
                        navItemIndex = 0;
                }


                //checking if the item is in checked state or not
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {

                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();
                return true;

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {


        @Override
        public void onDrawerClosed (View drawerView){
            // code here will be triggered once the drawer closes as we dont want
            //anything to happen so we leave this blank
            super.onDrawerClosed(drawerView);
        }
        @Override
        public void onDrawerOpened (View drawerView){
            //leaving this blank
            super.onDrawerOpened(drawerView);
        }
    };
        //setting the actionbartoggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        //this code loads home fragment when back key is pressed
        //when user is in other fragment other than home
        if (shouldLoadHomeFragOnBackPress) {
            //checking if user is on other navigation menu
            //rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }
    //@Override
    public boolean onCreateOptionMenu(Menu menu) {
        //inflate the mnu this adds item to the action bar if it is present
        //show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        //when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //user is in notification frag
        //and selected 'Mark all as read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }
        //notification frag and "Clear All"
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
    //show or hide the fab
    private void toogleFab() {
        if (navItemIndex == 0) {
            fab.show();
        } else {
            fab.hide();
    }
}














}
