package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by vieck on 6/27/15.
 */
public class SaveFragment extends Fragment {

    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    SaveAdapter mSaveAdapter;
    ImageView deleteAll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.saved_colors_fragment, container, false);
        deleteAll = (ImageView) view.findViewById(R.id.delete_all_trashcan);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.saved_color_list);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mSaveAdapter = new SaveAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(mSaveAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSaveAdapter = new SaveAdapter(mContext);
                recyclerView.setAdapter(mSaveAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        final DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mSaveAdapter.databaseHandler.deleteAll();
                        mSaveAdapter.notifyDataSetChanged();
                        Snackbar.make(getView(),"All colors were deleted",Snackbar.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Snackbar.make(getView(),"No colors were deleted",Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
                builder.setMessage("Are you sure you want to delete all colors?")
                        .setPositiveButton("Yes", dialogListener)
                        .setNegativeButton("No",dialogListener).show();
            }
        });
        return view;
    }

    public void refreshColors() {
        try {
            mSaveAdapter = new SaveAdapter(mContext);
            recyclerView.setAdapter(mSaveAdapter);
        } catch (NullPointerException ex) {

        }
    }

    @Override
    public void onAttach(Activity activity) {
        mContext = activity.getApplicationContext();
        super.onAttach(activity);
    }
}
