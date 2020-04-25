package com.example.groupassignment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.groupassignment.Fragments.HomeFragment;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Ingredients;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StaticResource {
    public static List<Categories.Category> categoriesList;
    public static List<String> cuisinesList = new ArrayList<String>();
    public static List<Ingredients.Ingredient> ingredientsList;
}
