package edu.purdue.vieck.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;


public class BudgetActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabLayout;
    private DatabaseHandler mDatabaseHandler;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String[] list = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        adapter.addFragment(new ChartFragment(), "Chart");
        ArrayList<BudgetElement> months = mDatabaseHandler.getAllMonths();
        //for (int i = 0; i < list.length; i++)
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
}
