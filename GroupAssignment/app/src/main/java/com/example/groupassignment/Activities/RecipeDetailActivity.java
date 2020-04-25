package com.example.groupassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groupassignment.Databases.RecipeDatabase;
import com.example.groupassignment.Models.Recipe;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.R;

import java.lang.reflect.Method;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    public static Recipe recipe = null;

    private ImageView recipeImage, btnLike, btnShare, btnGoogle;
    private TextView recipeName, recipeCategory, recipeCuisine, recipeTag, recipeYoutube,
            recipeInstruction, recipeIngredient, recipeSource, recipeLastModified, labelTag, labelYoutube,
            labelSource, labelLastModified;
    private RecipeDatabase recipeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeImage = findViewById(R.id.recipeImage);
        recipeName = findViewById(R.id.recipeName);
        recipeCategory = findViewById(R.id.recipeCategory);
        recipeCuisine = findViewById(R.id.recipeCuisine);
        recipeTag = findViewById(R.id.recipeTag);
        recipeYoutube = findViewById(R.id.youtubeLink);
        recipeInstruction = findViewById(R.id.recipeInstruction);
        recipeIngredient = findViewById(R.id.recipeIngredient);
        recipeSource = findViewById(R.id.recipeSource);
        recipeLastModified = findViewById(R.id.recipeLastModified);
        labelTag = findViewById(R.id.labelTag);
        labelYoutube = findViewById(R.id.labelYoutube);
        labelSource = findViewById(R.id.labelSource);
        labelLastModified = findViewById(R.id.labelLastModified);
        btnGoogle = findViewById(R.id.btnGoogle);


        Glide.with(getApplicationContext()).load(recipe.getRecipeImage()).into(recipeImage);
        recipeName.setText(recipe.getRecipeName());
        recipeCategory.setText(recipe.getRecipeCategory());
        recipeCuisine.setText(recipe.getRecipeCuisine());
        if(recipe.getRecipeTags() != null && !recipe.getRecipeTags().trim().equals("")) {
            recipeTag.setText(recipe.getRecipeTags());
        } else {
            recipeTag.setVisibility(View.GONE);
            labelTag.setVisibility(View.GONE);
        }

        if(recipe.getRecipeYoutube() != null && !recipe.getRecipeYoutube().trim().equals("")) {
            recipeYoutube.setText("Video Link");
            recipeYoutube.setPaintFlags(recipeYoutube.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            recipeYoutube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(recipe.getRecipeYoutube());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
            });
        } else {
            recipeYoutube.setVisibility(View.GONE);
            labelYoutube.setVisibility(View.GONE);
        }

        String[] splitInstruction = recipe.getRecipeInstruction().split("\n");
        String instructionText = "";
        for(String s: splitInstruction){
            if(s.trim() != null && !s.trim().equals("")){
                s = s.replaceAll("\n", "");
                instructionText += s;
                if(s.trim().length() > 5) {
                    instructionText += "\n\n";
                } else {
                    instructionText += "  ";
                }

            }
        }
        System.out.println(instructionText);
        recipeInstruction.setText(instructionText);

        String ingredientText = "";
        for(int i = 1; i <= 20;i++){
            try{
                Method getterIngredient = recipe.getClass().getMethod("getRecipeIngredient"+i);
                Method getterMeasure = recipe.getClass().getMethod("getIngredientMeasure"+i);
                if(getterIngredient.invoke(recipe).toString() != null && !getterIngredient.invoke(recipe).toString().trim().equals("")) {
                    ingredientText += i + ") " + getterIngredient.invoke(recipe).toString();
                    if(getterMeasure.invoke(recipe).toString() != null && !getterMeasure.invoke(recipe).toString().trim().equals("")) {
                        ingredientText +=  ": " + getterMeasure.invoke(recipe).toString() + "\n";
                    } else {
                        ingredientText += "\n";
                    }
                } else {
                    break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        recipeIngredient.setText(ingredientText);

        if(recipe.getRecipeSource() != null && !recipe.getRecipeSource().trim().equals("")) {
            recipeSource.setText("Source Link");
            recipeSource.setPaintFlags(recipeSource.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            recipeSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(recipe.getRecipeSource());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
            });
        } else {
            recipeSource.setVisibility(View.GONE);
            labelSource.setVisibility(View.GONE);
        }

        labelLastModified.setVisibility(View.GONE);
        if(recipe.getDateModified() != null && !recipe.getDateModified().trim().equals("")) {
            recipeLastModified.setText(recipe.getDateModified());
        } else {
            labelLastModified.setVisibility(View.GONE);
            recipeLastModified.setVisibility(View.GONE);
        }

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This recipe is amazing!!\nName: " +
                        recipe.getRecipeName() +
                        "\nLet's get the app WhatToEat and learn cooking!!");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        btnLike = findViewById(R.id.btnLike);
        recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());

        if(!checkFavourite(recipe.getRecipeId())){
            btnLike.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_like));
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFavourite(recipe.getRecipeId())){
                    recipeDatabase.recipeDao().insertRecipe(recipe);
                    btnLike.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_like));
                    Toast.makeText(getApplicationContext(),"Save liked.", Toast.LENGTH_SHORT).show();
                } else {
                    recipeDatabase.recipeDao().removeRecipebyId(recipe.getRecipeId());
                    btnLike.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_unlike));
                    Toast.makeText(getApplicationContext(),"Removed like.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/search?q=" + recipe.getRecipeName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private boolean checkFavourite(String checkId){
        List<Recipe> recipeList = recipeDatabase.recipeDao().getAllRecipe();
        for (Recipe r:recipeList) {
            if(r.getRecipeId().equals(checkId)){
                return false;
            }
        }

        return true;
    }
}
