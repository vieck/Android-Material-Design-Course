package edu.purdue.vieck.animationsondisplay;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vieck on 6/16/15.
 */
public class AnimationFragment extends Fragment {
    private List<RecycleViewAdapter.AnimationItem> items = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_drawer_layout, container, true);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.animations_list);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(items, getActivity());
        recyclerView.setAdapter(recycleViewAdapter);
        return v;
    }
}
