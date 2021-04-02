package com.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.recipes.retrofit.model.Recipe;
import com.recipes.retrofit.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.OnNoteListener {

    FrameLayout progressBarHolder;
    RecyclerView recipesRecycler;
    private List<Recipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesRecycler = findViewById(R.id.recipes_recycler);
        progressBarHolder = findViewById(R.id.progress_bar_holder);
        progressBarHolder.setVisibility(View.VISIBLE);

        recipesList = new ArrayList<>();
        recipesRecycler.setLayoutManager(new LinearLayoutManager(this));
        RecipesAdapter recipesAdapter = new RecipesAdapter(this, recipesList, this);
        recipesRecycler.setAdapter(recipesAdapter);


        App.getCurrencyController().getAllRecipes().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (!response.isSuccessful()) {
                    progressBarHolder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Не удалось получить данные", Toast.LENGTH_LONG).show();
                    return;
                }

                ServerResponse serverResponse = response.body();
                if (serverResponse == null) {
                    progressBarHolder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Не удалось получить данные", Toast.LENGTH_LONG).show();
                    return;
                }

                recipesList.addAll(serverResponse.getRecipes());
                recipesAdapter.notifyDataSetChanged();

                progressBarHolder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable throwable) {
                progressBarHolder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onNoteClick(int position) {
        final Recipe recipe = recipesList.get(position);

        Intent intent =  new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("name", recipe.getName());
        intent.putExtra("description", recipe.getDescription());
        intent.putExtra("difficulty", recipe.getDifficulty());
        intent.putExtra("image", recipe.getImages().get(0));
        intent.putExtra("instructions", recipe.getInstructions());

        startActivity(intent);

    }

}