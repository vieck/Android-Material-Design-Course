package edu.purdue.vieck.topgamesrssfeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vieck on 7/3/15.
 */
public class AndroidFragment extends Fragment {
    TextView mTitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.android_rss_feed, container, false);
        mTitle = (TextView) view.findViewById(R.id.android_title);
        return view;
    }
}
