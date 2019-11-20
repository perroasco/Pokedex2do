package org.fer.pokedex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.fer.pokedex.R;
import org.fer.pokedex.entities.PokemonType;
import org.fer.pokedex.utils.Utils;

import java.util.List;

public class DamageRelationAdapter extends RecyclerView.Adapter<DamageRelationAdapter.ViewHolder> {

    private PokemonType pokemonType;
    private LayoutInflater mInflater;
    private Context mContext;

    // data is passed into the constructor
    public DamageRelationAdapter(Context context, PokemonType pokemonType) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.pokemonType = pokemonType;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.damage_relation_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.relation.setText(PokemonType.translatedRelationNames[position]);

        List<String> relationTypes = pokemonType.getDamageRelations().get(PokemonType.relationNames[position]);
        holder.rvTypes.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rvTypes.setAdapter(new RowTypesAdapter(mContext, relationTypes));
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return pokemonType.getDamageRelations().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView relation;
        RecyclerView rvTypes;

        ViewHolder(View itemView) {
            super(itemView);
            relation = itemView.findViewById(R.id.relation);
            rvTypes = itemView.findViewById(R.id.rv_types);
        }
    }
}