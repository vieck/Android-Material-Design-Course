package edu.purdue.vieck.budgetapp;

import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

public class DashboardActivity extends Activity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

}
