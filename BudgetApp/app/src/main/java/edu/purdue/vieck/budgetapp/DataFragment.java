package edu.purdue.vieck.budgetapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mvieck on 9/22/2015.
 */
public class DataFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DataRecyclerAdapter mDataRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.data_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDataRecyclerAdapter = new DataRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mDataRecyclerAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
