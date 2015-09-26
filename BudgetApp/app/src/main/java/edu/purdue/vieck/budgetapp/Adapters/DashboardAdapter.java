package edu.purdue.vieck.budgetapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.purdue.vieck.budgetapp.R;
import edu.purdue.vieck.budgetapp.CustomObjects.DashboardItem;

/**
 * Created by mvieck on 9/25/2015.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.mViewHolder> {

    private Activity activity;
    private Context mContext;
    private List<DashboardItem> images;

    public DashboardAdapter(Activity activity, Context mContext, List<DashboardItem> images) {
        this.activity = activity;
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        mViewHolder viewHolder = new mViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, int position) {
        final DashboardItem item = images.get(position);
        holder.image.setImageDrawable(item.getImage());
        holder.label.setText(item.getLabel());
        holder.cardView.setCardBackgroundColor(item.getColor());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a specific fragment/replace the current one
                activity.startActivity(item.getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView label;

        public mViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cardview_dashboard);
            image = (ImageView) v.findViewById(R.id.imageview_dashboard_item);
            label = (TextView) v.findViewById(R.id.textview_dashboard_item);
        }
    }
}
