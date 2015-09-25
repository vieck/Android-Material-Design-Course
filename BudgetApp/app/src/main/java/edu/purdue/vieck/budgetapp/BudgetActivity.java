package edu.purdue.vieck.budgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BudgetActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private DatabaseHandler mDatabaseHandler;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDatabaseHandler = new DatabaseHandler(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_layout);
        final Activity currentActivity = this;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.chart_nav_item:
                        intent = new Intent(currentActivity, BudgetActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        currentActivity.startActivity(intent);
                        break;
                    case R.id.nav_item_list:
                        intent = new Intent(currentActivity, DataActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        currentActivity.startActivity(intent);
                        break;
                }
                return true;
            }
        });
        mDatabaseHandler = new DatabaseHandler(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int i) {
                mViewPager.setCurrentItem(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_data) {
            SubmitFragment submitFragment = new SubmitFragment();
            submitFragment.show(getSupportFragmentManager(), "SubmitFragment");
            return true;
        } else if (id == R.id.action_delete) {
            mDatabaseHandler.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String[] list = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("month",-1);
        bundle.putInt("year", -1);
        chartFragment.setArguments(bundle);
        adapter.addFragment(chartFragment, "Chart");
        HashMap<Integer, List<BudgetElement>> years = mDatabaseHandler.getAllYears();
        ArrayList<Integer> uniqueMonths = new ArrayList<>();
        Integer[] keys = years.keySet().toArray(new Integer[years.keySet().size()]);

        //Insertion sorting hashmap keys
        for (int i = 0; i < keys.length; i++) {
            int temp = keys[i];
            for (int j = i + 1; j < keys.length; j++) {
                if (keys[j] > keys[i]) {
                    int hold = keys[i];
                    keys[i] = keys[j];
                    keys[j] = hold;
                }
            }
        }

        for (int i : keys) {
            List<BudgetElement> budgetElements = years.get(i);
            for (BudgetElement element : budgetElements) {
                bundle = new Bundle();
                bundle.putInt("year", i);
                bundle.putInt("month", element.getMonth());
                chartFragment = new ChartFragment();
                chartFragment.setArguments(bundle);
                Log.d("Tabs", "YEAR " + i + " AND  MONTH " + element.getMonth() + " SIZE " + years.size());
                if (!uniqueMonths.contains(element.getMonth())) {
                    adapter.addFragment(chartFragment, list[element.getMonth() - 1] + " " + i);
                    uniqueMonths.add(element.getMonth());
                }
            }
            uniqueMonths = new ArrayList<>();
        }

        for (int i = 0; i < list.length; i++)
            //adapter.addFragment(null, list[i]);
            viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
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
            mTitleList.add(title);
        }

        public void replaceFragment(Fragment fragment, String title) {
            for (int i = 0; i < mTitleList.size(); i++) {
                if (mTitleList.get(i).equals(title)) {
                    mFragmentList.set(i, fragment);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
