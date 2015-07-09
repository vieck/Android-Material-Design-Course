package edu.purdue.vieck.topgamesrssfeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vieck on 7/3/15.
 */
public class NewsFragment extends Fragment {
    TextView mTitle;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecycleAdapter mRecycleAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_rss_feed, container, false);
        mTitle = (TextView) view.findViewById(R.id.news_title);
        //mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        //mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecycleAdapter = new RecycleAdapter(new ArrayList<Item>());
        //mRecyclerView.setAdapter(mRecycleAdapter);
        return view;
    }
}
