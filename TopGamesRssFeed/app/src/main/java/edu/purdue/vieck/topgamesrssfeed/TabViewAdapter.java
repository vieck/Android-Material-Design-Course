package edu.purdue.vieck.topgamesrssfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by vieck on 7/4/15.
 */
public class TabViewAdapter extends FragmentStatePagerAdapter {
    private CharSequence titles[];
    int tabNumbers;

    public TabViewAdapter(FragmentManager fragmentManager, CharSequence titles[], int tabNumbers) {
        super(fragmentManager);
        this.titles = titles;
        this.tabNumbers = tabNumbers;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 1 :
                AndroidFragment androidFragment = new AndroidFragment();
                return androidFragment;
            case 2 :
                GamingFragment gamingFragment = new GamingFragment();
                return gamingFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return tabNumbers;
    }
}
