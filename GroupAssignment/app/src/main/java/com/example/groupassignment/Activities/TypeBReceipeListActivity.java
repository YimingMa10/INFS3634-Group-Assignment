package com.example.groupassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.groupassignment.Adapters.NameSearchAdapter;
import com.example.groupassignment.Adapters.TypeBRecipeAdapter;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;

import java.util.ArrayList;
import java.util.List;

public class TypeBReceipeListActivity extends AppCompatActivity {

    public static List<RecipesTypeB.Recipe> recipes;
    private RecyclerView recyclerView;
    private TypeBRecipeAdapter mAdapter;
    private TextView resultFound;
    public static String specialText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typr_b_receipe_list);

        recyclerView = findViewById(R.id.rv_search_result);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new TypeBRecipeAdapter(getApplicationContext() ,new ArrayList<RecipesTypeB.Recipe>());
        mAdapter.setData(recipes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        resultFound = findViewById(R.id.resultFound);

        String resultText = "";
        if(specialText != null) {
            resultText = "Total " + recipes.size() + " recipes found" + specialText + ".";
        } else {
            resultText = "Total " + recipes.size() + " recipes found.";
        }

        resultFound.setText(resultText);
    }
}
