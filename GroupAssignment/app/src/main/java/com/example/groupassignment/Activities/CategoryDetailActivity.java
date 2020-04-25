package com.example.groupassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryDetailActivity extends AppCompatActivity {

    public static Categories.Category category = null;
    private TextView categoryName, description, btnSearchByCategory, notice;
    private ImageView categoryImage;
    private ProgressBar progressBar;
    private View fadeBackground;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        frameLayout = findViewById(R.id.fragmentContainerTwo);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        fadeBackground = findViewById(R.id.fadeBackground);
        fadeBackground.animate().alpha(0.6f);

        notice = findViewById(R.id.notice);
        categoryName = findViewById(R.id.categoryName);
        categoryImage = findViewById(R.id.categoryImage);
        description = findViewById(R.id.description);

        categoryName.setText(category.getCategoryName());
        description.setText(category.getCategoryDescription());
        Glide.with(getApplicationContext()).load(category.getCategoryImage()).into(categoryImage);

        btnSearchByCategory = findViewById(R.id.btnSearchByCategory);
        btnSearchByCategory.setPaintFlags(btnSearchByCategory.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnSearchByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchByCategoryTask().execute(category.getCategoryName());
            }
        });
    }


    private class SearchByCategoryTask extends AsyncTask<String, Integer, List<RecipesTypeB.Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            fadeBackground.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<RecipesTypeB.Recipe> doInBackground(String... strings) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<RecipesTypeB> searchCall = service.searchRecipeByCategory(strings[0]);
                Response<RecipesTypeB> searchResponse = searchCall.execute();
                List<RecipesTypeB.Recipe> recipe = searchResponse.body().getRecipeTypeBList();
                return recipe;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<RecipesTypeB.Recipe> recipes) {
            if(recipes != null){
                TypeBReceipeListActivity.recipes = recipes;
                TypeBReceipeListActivity.specialText = " under " + category.getCategoryName() + " category";
                Intent intent = new Intent(getApplicationContext() , TypeBReceipeListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }  else {
                notice.setVisibility(View.VISIBLE);
                notice.setText("No recipe is found under this category.");
                notice.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notice.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
            }
            frameLayout.setVisibility(View.GONE);
            fadeBackground.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
