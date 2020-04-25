package com.example.groupassignment.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.groupassignment.Activities.RecipeDetailActivity;
import com.example.groupassignment.Activities.SearchResultActivity;
import com.example.groupassignment.Models.Recipe;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private Button btnSubmit;
    private EditText inputText;
    private Button btnRandom;
    private ProgressBar progressBar;
    private View fadeBackground;
    private TextView notice;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        inputText = view.findViewById(R.id.inputText);
        btnRandom = view.findViewById(R.id.btnRandom);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        fadeBackground = view.findViewById(R.id.fadeBackground);
        fadeBackground.animate().alpha(0.6f);
        notice = view.findViewById(R.id.notice);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = String.valueOf(inputText.getText());
                if(input != null){
                    if(!input.trim().equals("")){
                        Bundle arguments = new Bundle();
                        new SearchNameTask().execute(input.trim());
                    }
                }
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RandomRecipeTask().execute();
            }
        });

        return view;
    }

    private class RandomRecipeTask extends AsyncTask<Void, Integer, Recipe> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Recipe doInBackground(Void... aVoid) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Recipes> searchCall = service.getRandomRecipe();
                Response<Recipes> searchResponse = searchCall.execute();
                Recipe recipe = searchResponse.body().getRecipes().get(0);
                return recipe;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Recipe recipe) {
            fadeBackground.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            RecipeDetailActivity.recipe = recipe;
            Intent intent = new Intent(getContext() , RecipeDetailActivity.class);
            getContext().startActivity(intent);
        }
    }

    private class SearchNameTask extends AsyncTask<String, Integer, List<Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Recipe> doInBackground(String... strings) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Recipes> searchCall = service.searchRecipeByName(strings[0]);
                Response<Recipes> searchResponse = searchCall.execute();
                List<Recipe> recipe = searchResponse.body().getRecipes();
                return recipe;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            if(recipes != null){
                SearchResultActivity.recipes = recipes;
                Intent intent = new Intent(getContext() , SearchResultActivity.class);
                getContext().startActivity(intent);
            } else {
                notice.setText("No result found. Please try other name.");
                notice.setVisibility(View.VISIBLE);
                notice.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notice.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
            }
            fadeBackground.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
