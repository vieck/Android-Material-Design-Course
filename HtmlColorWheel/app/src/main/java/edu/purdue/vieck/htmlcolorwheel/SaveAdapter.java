package edu.purdue.vieck.htmlcolorwheel;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vieck on 6/27/15.
 */
public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {

    DatabaseHandler databaseHandler;
    ArrayList<CustomColor> customColors;
    public SaveAdapter(Context context) {
        databaseHandler = new DatabaseHandler(context);
        customColors = databaseHandler.getAllColors();
    }

    private void delete(int position, CustomColor color) {
        customColors.remove(position);
        databaseHandler.delete(color);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_color_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final CustomColor customColor = customColors.get(position);
        int color = Color.parseColor(customColor.getHexValue());
        viewHolder.name.setText(customColor.getName());
        viewHolder.hex.setText(customColor.getHexValue());
        viewHolder.name.setTextColor(color);
        viewHolder.hex.setTextColor(color);
        viewHolder.colorview.setBackgroundColor(color);
        viewHolder.trashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position, customColor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customColors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, colorview, hex;
        ImageView trashcan;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.saved_color_cardview);
            name = (TextView) v.findViewById(R.id.saved_name);
            colorview = (TextView) v.findViewById(R.id.saved_color);
            hex = (TextView) v.findViewById(R.id.saved_hex);
            trashcan = (ImageView) v.findViewById(R.id.trashcan);
        }
    }
}
