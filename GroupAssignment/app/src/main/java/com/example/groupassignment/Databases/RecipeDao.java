package com.example.groupassignment.Databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.groupassignment.Models.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipe();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRecipes(List<Recipe> recipes);

    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    Recipe getRecipeById(String id);

    @Query("DELETE FROM recipe WHERE recipeId = :id")
    void removeRecipebyId(String id);

    // Delete all records currently in the DB
    @Query("DELETE FROM recipe")
    void clearTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);
}
