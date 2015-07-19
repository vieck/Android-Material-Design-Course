package edu.purdue.vieck.budgetapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by vieck on 7/13/15.
 */
public class DataFragment extends Fragment {
    private Context mContext;
    private Spinner mSpinner;

    Calendar calendar;

    EditText amount, month, day, year;

    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        mContext = activity.getApplicationContext();
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        mSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, R.array.categoryarray,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        calendar = Calendar.getInstance();

        amount = (EditText) view.findViewById(R.id.edittext_amount);
        month = (EditText) view.findViewById(R.id.edittext_month);
        day = (EditText) view.findViewById(R.id.edittext_day);
        year = (EditText) view.findViewById(R.id.eddittext_year);

        month.setText(calendar.get(Calendar.MONTH) + "");
        day.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
        year.setText(calendar.get(Calendar.YEAR) + "");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.budget_recycler_view);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
