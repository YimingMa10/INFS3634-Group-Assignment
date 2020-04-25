package com.example.groupassignment.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.groupassignment.Models.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeDatabase instance;
    public static RecipeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, RecipeDatabase.class,
                    "RecipeDb").allowMainThreadQueries().build();
        }
        return instance;
    }
}
