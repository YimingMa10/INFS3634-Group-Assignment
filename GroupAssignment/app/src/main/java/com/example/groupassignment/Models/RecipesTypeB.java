package com.example.groupassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesTypeB {
    @SerializedName("meals")
    @Expose
    private List<Recipe> recipeTypeBList = null;

    public List<Recipe> getRecipeTypeBList() {
        return recipeTypeBList;
    }

    public void setRecipeTypeBList(List<Recipe> recipeTypeBList) {
        this.recipeTypeBList = recipeTypeBList;
    }

    public class Recipe {

        @SerializedName("strMeal")
        @Expose
        private String recipeName;
        @SerializedName("strMealThumb")
        @Expose
        private String recipeImage;
        @SerializedName("idMeal")
        @Expose
        private String recipeId;

        public String getRecipeName() {
            return recipeName;
        }

        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public String getRecipeImage() {
            return recipeImage;
        }

        public void setRecipeImage(String recipeImage) {
            this.recipeImage = recipeImage;
        }

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }
    }
}
