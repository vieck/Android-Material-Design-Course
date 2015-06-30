package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by vieck on 6/27/15.
 */
public class SaveFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    SaveAdapter mSaveAdapter;
    ImageView deleteAll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_colors_fragment, container, false);
        deleteAll = (ImageView) view.findViewById(R.id.delete_all);

        recyclerView = (RecyclerView) view.findViewById(R.id.saved_color_list);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mSaveAdapter = new SaveAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(mSaveAdapter);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveAdapter.databaseHandler.deleteAll();
                mSaveAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
