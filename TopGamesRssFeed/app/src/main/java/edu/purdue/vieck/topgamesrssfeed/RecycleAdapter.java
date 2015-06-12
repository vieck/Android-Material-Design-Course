package edu.purdue.vieck.topgamesrssfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vieck on 6/7/15.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<RssDataParser.Item> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rssTitle, rssDescription;
        public ViewHolder(View v) {
            super(v);
            rssTitle = (TextView) v.findViewById(R.id.item_title);
            rssDescription = (TextView) v.findViewById(R.id.item_description);
        }
    }

    public void add(int position, RssDataParser.Item item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(RssDataParser.Item item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        mDataset.clear();
    }

    public RecycleAdapter(ArrayList<RssDataParser.Item> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_feed_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RssDataParser.Item item = mDataset.get(position);
        holder.rssTitle.setText(item.title);
        holder.rssDescription.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
