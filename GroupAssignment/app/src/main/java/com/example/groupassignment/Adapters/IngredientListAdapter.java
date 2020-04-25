package com.example.groupassignment.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.Activities.IngredientDetailActivity;
import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.R;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientListViewHolder> {

    private Activity mParentActivity;
    private List<Ingredients.Ingredient> mIngredients;

    public IngredientListAdapter(Activity mParentActivity, List<Ingredients.Ingredient> mIngredients){
        this.mParentActivity = mParentActivity;
        this.mIngredients = mIngredients;
    }

    public void setData(List<Ingredients.Ingredient> mIngredients){
        this.mIngredients.clear();
        this.mIngredients.addAll(mIngredients);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientListAdapter.IngredientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_ingredient, parent, false);
        return new IngredientListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.IngredientListViewHolder holder, int position) {
        Ingredients.Ingredient ingredient = mIngredients.get(position);
        holder.name.setText(ingredient.getIngredientName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientDetailActivity.ingredient = ingredient;
                Intent intent = new Intent(mParentActivity , IngredientDetailActivity.class);
                mParentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class IngredientListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView name;

        public IngredientListViewHolder(View v) {
            super(v);
            view = v;
            name = view.findViewById(R.id.name);
        }
    }
}
