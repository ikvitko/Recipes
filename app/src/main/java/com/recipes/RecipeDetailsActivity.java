package com.recipes;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("description")
                && getIntent().hasExtra("difficulty") && getIntent().hasExtra("image")
                && getIntent().hasExtra("instructions")){

            String recipeNameText = getIntent().getStringExtra("name");
            String descriptionText = getIntent().getStringExtra("description");
            String difficultyText = Integer.toString(getIntent().getIntExtra("difficulty", 0));
            String imageUrl = getIntent().getStringExtra("image");
            String instructionsText = getIntent().getStringExtra("instructions");

            TextView recipeName = findViewById(R.id.recipe_name);
            recipeName.setText(recipeNameText);

            TextView description = findViewById(R.id.description);
            description.setText(descriptionText);

            TextView difficulty = findViewById(R.id.difficulty);
            difficulty.setText("Difficulty: " + difficultyText);

            ImageView image = findViewById(R.id.image);
            Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .into(image);

            TextView instructions = findViewById(R.id.instructions);
            instructions.setText(instructionsText);
        }
    }
}