package edu.purdue.vieck.animationsondisplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vieck on 6/16/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<AnimationItem> mDataset = new ArrayList<>();
    private Context context;

    public RecycleViewAdapter(List<AnimationItem> mDataset, Context context) { this.mDataset = mDataset; this.context = context; }

    public static class AnimationItem {
        public String title;
        public AnimationItem(String title) {
            this.title = title;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.fragment_link);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final AnimationItem item = mDataset.get(i);
        viewHolder.name.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
