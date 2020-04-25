package com.example.groupassignment.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groupassignment.Activities.RecipeDetailActivity;
import com.example.groupassignment.Models.Recipe;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.R;

import java.util.List;

public class NameSearchAdapter extends RecyclerView.Adapter<NameSearchAdapter.NameSearchViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipeList;

    public NameSearchAdapter(Context mContext, List<Recipe> recipeList){
        this.mContext = mContext;
        this.mRecipeList = recipeList;

    }
    public void setData(List<Recipe> recipeList){
        this.mRecipeList.clear();
        this.mRecipeList.addAll(recipeList);
        notifyDataSetChanged();
    }

    public static class NameSearchViewHolder extends RecyclerView.ViewHolder  {
        public View view;
        public TextView name, category, cuisine, tag;
        public ImageView image;

        public NameSearchViewHolder(View v) {
            super(v);
            view = v;
            name = view.findViewById(R.id.recipeName);
            category = view.findViewById(R.id.recipeCategory);
            cuisine = view.findViewById(R.id.recipeCuisine);
            tag = view.findViewById(R.id.recipeTag);
            image = view.findViewById(R.id.recipeImage);
        }
    }

    @Override
    public NameSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_search_name, parent, false);
        return new NameSearchViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NameSearchAdapter.NameSearchViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        if(recipe.getRecipeName().length() > 45){
            holder.name.setTextSize(15);
        }
        holder.name.setText(recipe.getRecipeName());
        holder.category.setText("Category: " + recipe.getRecipeCategory());
        holder.cuisine.setText("Cuisine: " + recipe.getRecipeCuisine());
        if(recipe.getRecipeTags() != null && !recipe.getRecipeTags().equals("")){
            holder.tag.setText("Tags: " + recipe.getRecipeTags());
        } else {
            holder.tag.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(recipe.getRecipeImage()).into(holder.image);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeDetailActivity.recipe = recipe;
                Intent intent = new Intent(v.getContext() , RecipeDetailActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
