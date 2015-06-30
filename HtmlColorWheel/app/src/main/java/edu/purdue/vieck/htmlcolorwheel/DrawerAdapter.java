package edu.purdue.vieck.htmlcolorwheel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vieck on 6/27/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    final String[] fragments = {"edu.purdue.vieck.htmlcolorwheel.SliderFragment", "edu.purdue.vieck.htmlcolorwheel.SaveFragment"};
    private String[] mDataset;
    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawerLayout;
    private Context mContext;

    public DrawerAdapter(Context context, FragmentManager fragmentManager, DrawerLayout drawerLayout, String[] mDataset) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mDrawerLayout = drawerLayout;
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.label.setText(mDataset[position]);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = mFragmentManager.beginTransaction();
                tx.replace(R.id.fragment_container, Fragment.instantiate(mContext, fragments[position]));
                tx.commit();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView label;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.drawer_card_view);
            label = (TextView) v.findViewById(R.id.item_title);
        }
    }
}
