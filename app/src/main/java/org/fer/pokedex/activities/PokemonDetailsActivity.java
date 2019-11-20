package org.fer.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.fer.pokedex.R;
import org.fer.pokedex.adapters.RowTypesAdapter;
import org.fer.pokedex.entities.PokemonDetails;
import org.fer.pokedex.interfaces.AsyncTaskHandler;
import org.fer.pokedex.network.PokemonDetailsAsyncTask;

import java.util.Arrays;

public class PokemonDetailsActivity extends AppCompatActivity implements AsyncTaskHandler {

    ImageView image, favorite;
    TextView name, types, weight, experience, id;
    RecyclerView rvDetailsTypes;

    String url;
    String pokemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        types = findViewById(R.id.detatils_type);
        weight = findViewById(R.id.weight);
        experience = findViewById(R.id.experience);
        id = findViewById(R.id.id);
        rvDetailsTypes = findViewById(R.id.rv_details_types);

        url = getIntent().getStringExtra("URL");

        PokemonDetailsAsyncTask pokemonDetailsAsyncTask = new PokemonDetailsAsyncTask();
        pokemonDetailsAsyncTask.handler = this;
        pokemonDetailsAsyncTask.execute(url);


    }

    @Override
    public void onTaskEnd(Object result) {
        PokemonDetails details = (PokemonDetails) result;
        pokemonName = details.getName();
        Glide.with(this).load(details.getImage()).into(image);
        name.setText(details.getName());
        weight.setText("Weight: " + details.getWeight());
        experience.setText("Base EXP: " + details.getBaseExperience());
        id.setText("ID: " + details.getId());

        rvDetailsTypes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDetailsTypes.setAdapter(new RowTypesAdapter(this, Arrays.asList(details.getTypes())));


    }
}
