package com.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.recipes.retrofit.model.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {

    private List<Recipe> recipesList;
    private LayoutInflater mInflater;
    private String amountString = "";
    private Context context;
    private OnNoteListener onNoteListener;

    RecipesAdapter(Context context, List<Recipe> recipesList, OnNoteListener onNoteListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.recipesList = recipesList;
        this.onNoteListener = onNoteListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipes_adapter, parent, false);
        return new MyViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Recipe recipe = recipesList.get(position);

        holder.name.setText(recipe.getName());
        holder.description.setText(recipe.getDescription());
        holder.difficulty.setText(recipe.getDifficulty().toString());

        Glide.with(this.context)
                .load(recipe.getImages().get(0))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, description, difficulty;
        public ImageView imageView;
        OnNoteListener onNoteListener;

        public MyViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            name = view.findViewById(R.id.name_recipes_adapter);
            description = view.findViewById(R.id.description_recipes_adapter);
            difficulty = view.findViewById(R.id.difficulty_recipes_adapter);
            imageView = view.findViewById(R.id.image_recipes_adapter);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
