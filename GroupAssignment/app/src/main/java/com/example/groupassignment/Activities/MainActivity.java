package com.example.groupassignment.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.groupassignment.Fragments.CategoryFragment;
import com.example.groupassignment.Fragments.CuisineFragment;
import com.example.groupassignment.Fragments.HomeFragment;
import com.example.groupassignment.Fragments.IngredientFragment;
import com.example.groupassignment.Fragments.MoreFragment;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Cuisines;
import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;
import com.example.groupassignment.StaticResource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ProgressBar progressBar;
    private View fadeBackground;
    private boolean ingredientTracker = false;
    private boolean categoryTracker = false;
    private boolean cuisineTracker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        fadeBackground = findViewById(R.id.fadeBackground);
        fadeBackground.animate().alpha(0.6f);

        if(StaticResource.categoriesList == null) {
            new GetCategoriesTask().execute();
        }

        if(StaticResource.cuisinesList.size() < 1){
            new GetCuisinesTask().execute();
        }

        if(StaticResource.ingredientsList == null){
            new GetIngredientsTask().execute();
        }

        swapFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.home){
                    swapFragment(new HomeFragment());
                    return true;
                } else if(menuItem.getItemId() == R.id.category){
                    swapFragment(new CategoryFragment());
                    return true;
                } else if(menuItem.getItemId() == R.id.ingredient){
                    swapFragment(new IngredientFragment());
                    return true;
                } else if(menuItem.getItemId() == R.id.cuisine){
                    swapFragment(new CuisineFragment());
                    return true;
                } else if(menuItem.getItemId() == R.id.more){
                    swapFragment(new MoreFragment());
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSlot, fragment).commit();
    }

    private class GetCategoriesTask extends AsyncTask<Void, Void, List<Categories.Category>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            categoryTracker = true;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Categories.Category> doInBackground(Void... voids) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Categories> categoriesCall = service.getCategoryList();
                Response<Categories> categoriesResponse = categoriesCall.execute();
                List<Categories.Category> categories = categoriesResponse.body().getCategories();
                return categories;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Categories.Category> categories) {
            StaticResource.categoriesList = categories;
            categoryTracker = false;
            if(!ingredientTracker && !cuisineTracker){
                fadeBackground.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    private class GetCuisinesTask extends AsyncTask<Void, Void, List<Cuisines.Cuisine>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cuisineTracker = true;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Cuisines.Cuisine> doInBackground(Void... voids) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Cuisines> cuisinesCall = service.getCuisineList("list");
                Response<Cuisines> cuisinesResponse = cuisinesCall.execute();
                List<Cuisines.Cuisine> cuisine = cuisinesResponse.body().getCuisines();
                return cuisine;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Cuisines.Cuisine> cuisines) {
            for(Cuisines.Cuisine c : cuisines){
                StaticResource.cuisinesList.add(c.getCuisine());
            }
            cuisineTracker = false;
            if(!ingredientTracker && !categoryTracker){
                fadeBackground.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    private class GetIngredientsTask extends AsyncTask<Void, Void, List<Ingredients.Ingredient>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ingredientTracker = true;
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Ingredients.Ingredient> doInBackground(Void... voids) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Ingredients> ingredientsCall = service.getIngredientList("list");
                Response<Ingredients> ingredientsResponse = ingredientsCall.execute();
                List<Ingredients.Ingredient> ingredients = ingredientsResponse.body().getIngredients();
                return ingredients;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Ingredients.Ingredient> ingredients) {
            StaticResource.ingredientsList = ingredients;
            ingredientTracker = false;
            if(!categoryTracker && !cuisineTracker){
                fadeBackground.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }
}
