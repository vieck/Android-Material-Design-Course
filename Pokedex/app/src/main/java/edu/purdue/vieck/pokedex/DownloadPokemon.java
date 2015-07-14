package edu.purdue.vieck.pokedex;

import com.pokejava.Pokedex;
import com.pokejava.Pokemon;

import java.util.ArrayList;

/**
 * Created by vieck on 7/8/15.
 */
public class DownloadPokemon {
    ArrayList<Pokemon> pokemonList;
    String mUrl;

    public DownloadPokemon() {
        pokemonList = new Pokedex().getPokemons();
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }
}
