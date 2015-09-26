package edu.purdue.vieck.budgetapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.purdue.vieck.budgetapp.DatabaseHandler;
import edu.purdue.vieck.budgetapp.R;
import edu.purdue.vieck.budgetapp.Adapters.DataAdapter;

/**
 * Created by mvieck on 9/22/2015.
 */
public class DataFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    DatabaseHandler mDatabaseHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.data_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDataAdapter = new DataAdapter(getActivity(), "");
        mRecyclerView.setAdapter(mDataAdapter);
        mDatabaseHandler = new DatabaseHandler(getActivity());
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void filterQuery(String filter) {
        mDataAdapter = new DataAdapter(getActivity(), filter);
        mRecyclerView.setAdapter(mDataAdapter);
        Log.d("DataFragment", "Filter " + filter);
    }
}
