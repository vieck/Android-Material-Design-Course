package edu.purdue.vieck.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vieck on 7/5/15.
 */
public class RecyclerAdapter {

    private Object[] mPokemonData;

    public RecyclerAdapter(Object[] mPokemonData) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
        }

    }

}
