package com.example.groupassignment.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Recipe {
    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    @Expose
    private String recipeId;
    @SerializedName("strMeal")
    @Expose
    private String recipeName;
    @SerializedName("strDrinkAlternate")
    @Expose
    private String recipeDrinkAlternate;
    @SerializedName("strCategory")
    @Expose
    private String recipeCategory;
    @SerializedName("strArea")
    @Expose
    private String recipeCuisine;
    @SerializedName("strInstructions")
    @Expose
    private String recipeInstruction;
    @SerializedName("strMealThumb")
    @Expose
    private String recipeImage;
    @SerializedName("strTags")
    @Expose
    private String recipeTags;
    @SerializedName("strYoutube")
    @Expose
    private String recipeYoutube;
    @SerializedName("strIngredient1")
    @Expose
    private String recipeIngredient1;
    @SerializedName("strIngredient2")
    @Expose
    private String recipeIngredient2;
    @SerializedName("strIngredient3")
    @Expose
    private String recipeIngredient3;
    @SerializedName("strIngredient4")
    @Expose
    private String recipeIngredient4;
    @SerializedName("strIngredient5")
    @Expose
    private String recipeIngredient5;
    @SerializedName("strIngredient6")
    @Expose
    private String recipeIngredient6;
    @SerializedName("strIngredient7")
    @Expose
    private String recipeIngredient7;
    @SerializedName("strIngredient8")
    @Expose
    private String recipeIngredient8;
    @SerializedName("strIngredient9")
    @Expose
    private String recipeIngredient9;
    @SerializedName("strIngredient10")
    @Expose
    private String recipeIngredient10;
    @SerializedName("strIngredient11")
    @Expose
    private String recipeIngredient11;
    @SerializedName("strIngredient12")
    @Expose
    private String recipeIngredient12;
    @SerializedName("strIngredient13")
    @Expose
    private String recipeIngredient13;
    @SerializedName("strIngredient14")
    @Expose
    private String recipeIngredient14;
    @SerializedName("strIngredient15")
    @Expose
    private String recipeIngredient15;
    @SerializedName("strIngredient16")
    @Expose
    private String recipeIngredient16;
    @SerializedName("strIngredient17")
    @Expose
    private String recipeIngredient17;
    @SerializedName("strIngredient18")
    @Expose
    private String recipeIngredient18;
    @SerializedName("strIngredient19")
    @Expose
    private String recipeIngredient19;
    @SerializedName("strIngredient20")
    @Expose
    private String recipeIngredient20;
    @SerializedName("strMeasure1")
    @Expose
    private String ingredientMeasure1;
    @SerializedName("strMeasure2")
    @Expose
    private String ingredientMeasure2;
    @SerializedName("strMeasure3")
    @Expose
    private String ingredientMeasure3;
    @SerializedName("strMeasure4")
    @Expose
    private String ingredientMeasure4;
    @SerializedName("strMeasure5")
    @Expose
    private String ingredientMeasure5;
    @SerializedName("strMeasure6")
    @Expose
    private String ingredientMeasure6;
    @SerializedName("strMeasure7")
    @Expose
    private String ingredientMeasure7;
    @SerializedName("strMeasure8")
    @Expose
    private String ingredientMeasure8;
    @SerializedName("strMeasure9")
    @Expose
    private String ingredientMeasure9;
    @SerializedName("strMeasure10")
    @Expose
    private String ingredientMeasure10;
    @SerializedName("strMeasure11")
    @Expose
    private String ingredientMeasure11;
    @SerializedName("strMeasure12")
    @Expose
    private String ingredientMeasure12;
    @SerializedName("strMeasure13")
    @Expose
    private String ingredientMeasure13;
    @SerializedName("strMeasure14")
    @Expose
    private String ingredientMeasure14;
    @SerializedName("strMeasure15")
    @Expose
    private String ingredientMeasure15;
    @SerializedName("strMeasure16")
    @Expose
    private String ingredientMeasure16;
    @SerializedName("strMeasure17")
    @Expose
    private String ingredientMeasure17;
    @SerializedName("strMeasure18")
    @Expose
    private String ingredientMeasure18;
    @SerializedName("strMeasure19")
    @Expose
    private String ingredientMeasure19;
    @SerializedName("strMeasure20")
    @Expose
    private String ingredientMeasure20;
    @SerializedName("strSource")
    @Expose
    private String recipeSource;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDrinkAlternate() {
        return recipeDrinkAlternate;
    }

    public void setRecipeDrinkAlternate(String recipeDrinkAlternate) {
        this.recipeDrinkAlternate = recipeDrinkAlternate;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeCuisine() {
        return recipeCuisine;
    }

    public void setRecipeCuisine(String recipeCuisine) {
        this.recipeCuisine = recipeCuisine;
    }

    public String getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(String recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(String recipeTags) {
        this.recipeTags = recipeTags;
    }

    public String getRecipeYoutube() {
        return recipeYoutube;
    }

    public void setRecipeYoutube(String recipeYoutube) {
        this.recipeYoutube = recipeYoutube;
    }

    public String getRecipeIngredient1() {
        return recipeIngredient1;
    }

    public void setRecipeIngredient1(String recipeIngredient1) {
        this.recipeIngredient1 = recipeIngredient1;
    }

    public String getRecipeIngredient2() {
        return recipeIngredient2;
    }

    public void setRecipeIngredient2(String recipeIngredient2) {
        this.recipeIngredient2 = recipeIngredient2;
    }

    public String getRecipeIngredient3() {
        return recipeIngredient3;
    }

    public void setRecipeIngredient3(String recipeIngredient3) {
        this.recipeIngredient3 = recipeIngredient3;
    }

    public String getRecipeIngredient4() {
        return recipeIngredient4;
    }

    public void setRecipeIngredient4(String recipeIngredient4) {
        this.recipeIngredient4 = recipeIngredient4;
    }

    public String getRecipeIngredient5() {
        return recipeIngredient5;
    }

    public void setRecipeIngredient5(String recipeIngredient5) {
        this.recipeIngredient5 = recipeIngredient5;
    }

    public String getRecipeIngredient6() {
        return recipeIngredient6;
    }

    public void setRecipeIngredient6(String recipeIngredient6) {
        this.recipeIngredient6 = recipeIngredient6;
    }

    public String getRecipeIngredient7() {
        return recipeIngredient7;
    }

    public void setRecipeIngredient7(String recipeIngredient7) {
        this.recipeIngredient7 = recipeIngredient7;
    }

    public String getRecipeIngredient8() {
        return recipeIngredient8;
    }

    public void setRecipeIngredient8(String recipeIngredient8) {
        this.recipeIngredient8 = recipeIngredient8;
    }

    public String getRecipeIngredient9() {
        return recipeIngredient9;
    }

    public void setRecipeIngredient9(String recipeIngredient9) {
        this.recipeIngredient9 = recipeIngredient9;
    }

    public String getRecipeIngredient10() {
        return recipeIngredient10;
    }

    public void setRecipeIngredient10(String recipeIngredient10) {
        this.recipeIngredient10 = recipeIngredient10;
    }

    public String getRecipeIngredient11() {
        return recipeIngredient11;
    }

    public void setRecipeIngredient11(String recipeIngredient11) {
        this.recipeIngredient11 = recipeIngredient11;
    }

    public String getRecipeIngredient12() {
        return recipeIngredient12;
    }

    public void setRecipeIngredient12(String recipeIngredient12) {
        this.recipeIngredient12 = recipeIngredient12;
    }

    public String getRecipeIngredient13() {
        return recipeIngredient13;
    }

    public void setRecipeIngredient13(String recipeIngredient13) {
        this.recipeIngredient13 = recipeIngredient13;
    }

    public String getRecipeIngredient14() {
        return recipeIngredient14;
    }

    public void setRecipeIngredient14(String recipeIngredient14) {
        this.recipeIngredient14 = recipeIngredient14;
    }

    public String getRecipeIngredient15() {
        return recipeIngredient15;
    }

    public void setRecipeIngredient15(String recipeIngredient15) {
        this.recipeIngredient15 = recipeIngredient15;
    }

    public String getRecipeIngredient16() {
        return recipeIngredient16;
    }

    public void setRecipeIngredient16(String recipeIngredient16) {
        this.recipeIngredient16 = recipeIngredient16;
    }

    public String getRecipeIngredient17() {
        return recipeIngredient17;
    }

    public void setRecipeIngredient17(String recipeIngredient17) {
        this.recipeIngredient17 = recipeIngredient17;
    }

    public String getRecipeIngredient18() {
        return recipeIngredient18;
    }

    public void setRecipeIngredient18(String recipeIngredient18) {
        this.recipeIngredient18 = recipeIngredient18;
    }

    public String getRecipeIngredient19() {
        return recipeIngredient19;
    }

    public void setRecipeIngredient19(String recipeIngredient19) {
        this.recipeIngredient19 = recipeIngredient19;
    }

    public String getRecipeIngredient20() {
        return recipeIngredient20;
    }

    public void setRecipeIngredient20(String recipeIngredient20) {
        this.recipeIngredient20 = recipeIngredient20;
    }

    public String getIngredientMeasure1() {
        return ingredientMeasure1;
    }

    public void setIngredientMeasure1(String ingredientMeasure1) {
        this.ingredientMeasure1 = ingredientMeasure1;
    }

    public String getIngredientMeasure2() {
        return ingredientMeasure2;
    }

    public void setIngredientMeasure2(String ingredientMeasure2) {
        this.ingredientMeasure2 = ingredientMeasure2;
    }

    public String getIngredientMeasure3() {
        return ingredientMeasure3;
    }

    public void setIngredientMeasure3(String ingredientMeasure3) {
        this.ingredientMeasure3 = ingredientMeasure3;
    }

    public String getIngredientMeasure4() {
        return ingredientMeasure4;
    }

    public void setIngredientMeasure4(String ingredientMeasure4) {
        this.ingredientMeasure4 = ingredientMeasure4;
    }

    public String getIngredientMeasure5() {
        return ingredientMeasure5;
    }

    public void setIngredientMeasure5(String ingredientMeasure5) {
        this.ingredientMeasure5 = ingredientMeasure5;
    }

    public String getIngredientMeasure6() {
        return ingredientMeasure6;
    }

    public void setIngredientMeasure6(String ingredientMeasure6) {
        this.ingredientMeasure6 = ingredientMeasure6;
    }

    public String getIngredientMeasure7() {
        return ingredientMeasure7;
    }

    public void setIngredientMeasure7(String ingredientMeasure7) {
        this.ingredientMeasure7 = ingredientMeasure7;
    }

    public String getIngredientMeasure8() {
        return ingredientMeasure8;
    }

    public void setIngredientMeasure8(String ingredientMeasure8) {
        this.ingredientMeasure8 = ingredientMeasure8;
    }

    public String getIngredientMeasure9() {
        return ingredientMeasure9;
    }

    public void setIngredientMeasure9(String ingredientMeasure9) {
        this.ingredientMeasure9 = ingredientMeasure9;
    }

    public String getIngredientMeasure10() {
        return ingredientMeasure10;
    }

    public void setIngredientMeasure10(String ingredientMeasure10) {
        this.ingredientMeasure10 = ingredientMeasure10;
    }

    public String getIngredientMeasure11() {
        return ingredientMeasure11;
    }

    public void setIngredientMeasure11(String ingredientMeasure11) {
        this.ingredientMeasure11 = ingredientMeasure11;
    }

    public String getIngredientMeasure12() {
        return ingredientMeasure12;
    }

    public void setIngredientMeasure12(String ingredientMeasure12) {
        this.ingredientMeasure12 = ingredientMeasure12;
    }

    public String getIngredientMeasure13() {
        return ingredientMeasure13;
    }

    public void setIngredientMeasure13(String ingredientMeasure13) {
        this.ingredientMeasure13 = ingredientMeasure13;
    }

    public String getIngredientMeasure14() {
        return ingredientMeasure14;
    }

    public void setIngredientMeasure14(String ingredientMeasure14) {
        this.ingredientMeasure14 = ingredientMeasure14;
    }

    public String getIngredientMeasure15() {
        return ingredientMeasure15;
    }

    public void setIngredientMeasure15(String ingredientMeasure15) {
        this.ingredientMeasure15 = ingredientMeasure15;
    }

    public String getIngredientMeasure16() {
        return ingredientMeasure16;
    }

    public void setIngredientMeasure16(String ingredientMeasure16) {
        this.ingredientMeasure16 = ingredientMeasure16;
    }

    public String getIngredientMeasure17() {
        return ingredientMeasure17;
    }

    public void setIngredientMeasure17(String ingredientMeasure17) {
        this.ingredientMeasure17 = ingredientMeasure17;
    }

    public String getIngredientMeasure18() {
        return ingredientMeasure18;
    }

    public void setIngredientMeasure18(String ingredientMeasure18) {
        this.ingredientMeasure18 = ingredientMeasure18;
    }

    public String getIngredientMeasure19() {
        return ingredientMeasure19;
    }

    public void setIngredientMeasure19(String ingredientMeasure19) {
        this.ingredientMeasure19 = ingredientMeasure19;
    }

    public String getIngredientMeasure20() {
        return ingredientMeasure20;
    }

    public void setIngredientMeasure20(String ingredientMeasure20) {
        this.ingredientMeasure20 = ingredientMeasure20;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
