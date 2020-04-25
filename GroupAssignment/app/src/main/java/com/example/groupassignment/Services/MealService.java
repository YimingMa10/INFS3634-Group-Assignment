package com.example.groupassignment.Services;

import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Cuisines;
import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.Models.RecipesTypeB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("api/json/v1/1/categories.php")
    Call<Categories> getCategoryList();

    @GET("/api/json/v1/1/list.php")
    Call<Cuisines> getCuisineList(@Query("a") String query);

    @GET("/api/json/v1/1/list.php")
    Call<Ingredients> getIngredientList(@Query("i") String query);

    @GET("/api/json/v1/1/search.php")
    Call<Recipes> searchRecipeByName(@Query("s") String name);

    @GET("/api/json/v1/1/random.php")
    Call<Recipes> getRandomRecipe();

    @GET("/api/json/v1/1/filter.php")
    Call<RecipesTypeB> searchRecipeByCategory(@Query("c") String category);

    @GET("/api/json/v1/1/lookup.php")
    Call<Recipes> searchRecipeById(@Query("i") String id);

    @GET("/api/json/v1/1/filter.php")
    Call<RecipesTypeB> searchRecipeByIngredient(@Query("i") String ingredient);

    @GET("/api/json/v1/1/filter.php")
    Call<RecipesTypeB> searchRecipeByCuisine(@Query("a") String cuisine);
}
