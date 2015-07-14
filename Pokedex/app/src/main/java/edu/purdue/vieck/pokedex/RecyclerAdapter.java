package edu.purdue.vieck.pokedex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pokejava.Pokemon;

import java.util.ArrayList;

/**
 * Created by vieck on 7/5/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Pokemon> mDataset;

    public RecyclerAdapter(ArrayList<Pokemon> mDataset) {
        this.mDataset = mDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView national_id, type, hex;
        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.pokemon_cardview);
            national_id = (TextView) v.findViewById(R.id.pokemon_national_id);
            type = (TextView) v.findViewById(R.id.pokemon_type);
        }
    }

    public void clear() {
        mDataset.clear();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pokedex, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Pokemon item = mDataset.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.cardView.getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
