package com.example.groupassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.groupassignment.Adapters.NameSearchAdapter;
import com.example.groupassignment.Models.Recipe;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.R;
import com.example.groupassignment.StaticResource;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    public static List<Recipe> recipes;
    private RecyclerView recyclerView;
    private NameSearchAdapter mAdapter;
    private TextView resultFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        recyclerView = findViewById(R.id.rv_search_result);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new NameSearchAdapter(getApplicationContext() ,new ArrayList<Recipe>());
        mAdapter.setData(recipes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        resultFound = findViewById(R.id.resultFound);

        resultFound.setText("Total " + recipes.size() + " recipes found.");
    }
}
