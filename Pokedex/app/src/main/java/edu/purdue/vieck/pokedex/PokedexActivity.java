package edu.purdue.vieck.pokedex;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.pokejava.Pokemon;

import java.util.ArrayList;

public class PokedexActivity extends Activity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerAdapter mRecycleAdapter;
    DownloadPokemon downloadPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        mRecyclerView = (RecyclerView) findViewById(R.id.pokemon_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        downloadPokemon = new DownloadPokemon();
        ArrayList<Pokemon> mPokemonList = downloadPokemon.getPokemonList();
        mRecycleAdapter = new RecyclerAdapter(mPokemonList);
        mRecyclerView.setAdapter(mRecycleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pokedex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
