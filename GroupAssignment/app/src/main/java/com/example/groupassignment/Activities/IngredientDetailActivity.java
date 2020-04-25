package com.example.groupassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientDetailActivity extends AppCompatActivity {

    public static Ingredients.Ingredient ingredient = null;
    private TextView ingredientName, description, btnSearchByIngredient, ingredientType, notice;
    private ImageView btnGoogle;
    private ProgressBar progressBar;
    private View fadeBackground;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        frameLayout = findViewById(R.id.fragmentContainerTwo);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        fadeBackground = findViewById(R.id.fadeBackground);
        fadeBackground.animate().alpha(0.6f);

        ingredientName = findViewById(R.id.name);
        ingredientType = findViewById(R.id.ingredientType);
        description = findViewById(R.id.description);
        notice = findViewById(R.id.notice);

        ingredientName.setText(ingredient.getIngredientName());
        if(ingredient.getIngredientDescription() == null || ingredient.getIngredientDescription().equals("")){
            description.setText("Sorry, no information found in database. :(");
            description.setTypeface(null, Typeface.ITALIC);
        } else {
            description.setText(ingredient.getIngredientDescription());
        }

        if(ingredient.getIngredientType() == null || ingredient.getIngredientType().equals("")){
            ingredientType.setText("Sorry, no information found in database. :(");
            ingredientType.setTypeface(null, Typeface.ITALIC);
        } else {
            ingredientType.setText(ingredient.getIngredientType());
        }

        btnGoogle = findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/search?q=" + ingredient.getIngredientName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        btnSearchByIngredient = findViewById(R.id.btnSearchByIngredient);
        btnSearchByIngredient.setPaintFlags(btnSearchByIngredient.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnSearchByIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchByIngredientTask().execute(ingredient.getIngredientName());
            }
        });
    }

    private class SearchByIngredientTask extends AsyncTask<String, Integer, List<RecipesTypeB.Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<RecipesTypeB.Recipe> doInBackground(String... strings) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<RecipesTypeB> searchCall = service.searchRecipeByIngredient(strings[0]);
                Response<RecipesTypeB> searchResponse = searchCall.execute();
                List<RecipesTypeB.Recipe> recipe = searchResponse.body().getRecipeTypeBList();
                return recipe;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<RecipesTypeB.Recipe> recipes) {
            if(recipes != null){
                TypeBReceipeListActivity.recipes = recipes;
                TypeBReceipeListActivity.specialText = " using " + ingredient.getIngredientName();
                Intent intent = new Intent(getApplicationContext() , TypeBReceipeListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            } else {
                notice.setVisibility(View.VISIBLE);
                notice.setText("No recipe is found using this ingredient.");
                notice.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notice.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
            }
            frameLayout.setVisibility(View.GONE);
            fadeBackground.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
