package com.example.groupassignment.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groupassignment.Activities.RecipeDetailActivity;
import com.example.groupassignment.Activities.SearchResultActivity;
import com.example.groupassignment.Models.Recipe;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeBRecipeAdapter extends RecyclerView.Adapter<TypeBRecipeAdapter.TypeBViewHolder> {
    private Context mContext;
    private List<RecipesTypeB.Recipe> mRecipeList;

    public TypeBRecipeAdapter(Context mContext, List<RecipesTypeB.Recipe> recipeList){
        this.mContext = mContext;
        this.mRecipeList = recipeList;
    }

    public void setData(List<RecipesTypeB.Recipe> recipeList){
        this.mRecipeList.clear();
        this.mRecipeList.addAll(recipeList);
        notifyDataSetChanged();
    }

    public static class TypeBViewHolder extends RecyclerView.ViewHolder  {
        public View view;
        public TextView name;
        public ImageView image;

        public TypeBViewHolder(View v) {
            super(v);
            view = v;
            name = view.findViewById(R.id.recipeName);
            image = view.findViewById(R.id.recipeImage);
        }
    }

    @Override
    public TypeBRecipeAdapter.TypeBViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_type_b_result, parent, false);
        return new TypeBViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TypeBRecipeAdapter.TypeBViewHolder holder, int position) {
        RecipesTypeB.Recipe recipe = mRecipeList.get(position);
        if(recipe.getRecipeName().length() > 45){
            holder.name.setTextSize(15);
        }
        holder.name.setText(recipe.getRecipeName());
        Glide.with(mContext).load(recipe.getRecipeImage()).into(holder.image);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchRecipeTask().execute(recipe.getRecipeId());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    private class SearchRecipeTask extends AsyncTask<String, Void, List<Recipe>> {
        @Override
        protected List<Recipe> doInBackground(String... strings) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<Recipes> searchCall = service.searchRecipeById(strings[0]);
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
            RecipeDetailActivity.recipe = recipes.get(0);
            Intent intent = new Intent(mContext , RecipeDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
