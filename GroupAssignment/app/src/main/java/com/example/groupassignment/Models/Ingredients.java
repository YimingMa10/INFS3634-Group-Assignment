package com.example.groupassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredients {
    @SerializedName("meals")
    @Expose
    private List<Ingredient> ingredients = null;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public class Ingredient {

        @SerializedName("idIngredient")
        @Expose
        private String ingredientId;
        @SerializedName("strIngredient")
        @Expose
        private String ingredientName;
        @SerializedName("strDescription")
        @Expose
        private String ingredientDescription;
        @SerializedName("strType")
        @Expose
        private String ingredientType;

        public String getIngredientId() {
            return ingredientId;
        }

        public void setIngredientId(String ingredientId) {
            this.ingredientId = ingredientId;
        }

        public String getIngredientName() {
            return ingredientName;
        }

        public void setIngredientName(String ingredientName) {
            this.ingredientName = ingredientName;
        }

        public String getIngredientDescription() {
            return ingredientDescription;
        }

        public void setIngredientDescription(String ingredientDescription) {
            this.ingredientDescription = ingredientDescription;
        }

        public String getIngredientType() {
            return ingredientType;
        }

        public void setIngredientType(String ingredientType) {
            this.ingredientType = ingredientType;
        }
    }

}
