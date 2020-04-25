package com.example.groupassignment.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groupassignment.Activities.CategoryDetailActivity;
import com.example.groupassignment.Activities.RecipeDetailActivity;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Recipes;
import com.example.groupassignment.R;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {

    private Activity mParentActivity;
    private List<Categories.Category> mCategories;

    public CategoryListAdapter(Activity mParentActivity, List<Categories.Category> mCategories){
        this.mParentActivity = mParentActivity;
        this.mCategories = mCategories;
    }

    public void setData(List<Categories.Category> mCategories){
        this.mCategories.clear();
        this.mCategories.addAll(mCategories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListAdapter.CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_category, parent, false);
        return new CategoryListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.CategoryListViewHolder holder, int position) {
        Categories.Category category = mCategories.get(position);
        holder.name.setText(category.getCategoryName());
        Glide.with(mParentActivity).load(category.getCategoryImage()).into(holder.image);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDetailActivity.category = category;
                Intent intent = new Intent(mParentActivity , CategoryDetailActivity.class);
                mParentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView name;
        public ImageView image;

        public CategoryListViewHolder(View v) {
            super(v);
            view = v;
            name = view.findViewById(R.id.categoryName);
            image = view.findViewById(R.id.categoryImage);
        }
    }
}
