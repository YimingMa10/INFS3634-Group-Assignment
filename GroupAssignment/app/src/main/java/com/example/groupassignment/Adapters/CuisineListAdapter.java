package com.example.groupassignment.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.Activities.TypeBReceipeListActivity;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuisineListAdapter extends RecyclerView.Adapter<CuisineListAdapter.CuisineListViewHolder> {
    private Activity mParentActivity;
    private List<String> mCuisines;
    private String temp = null;

    public CuisineListAdapter(Activity mParentActivity, List<String> mCuisines){
        this.mParentActivity = mParentActivity;
        this.mCuisines = mCuisines;
    }

    public void setData(List<String> mCuisines){
        this.mCuisines.clear();
        this.mCuisines.addAll(mCuisines);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CuisineListAdapter.CuisineListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_ingredient, parent, false);
        return new CuisineListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineListAdapter.CuisineListViewHolder holder, int position) {
        String cuisine = mCuisines.get(position);
        holder.name.setText(cuisine);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = cuisine;
                new SearchByCuisineTask().execute(cuisine);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCuisines.size();
    }

    public class CuisineListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView name;

        public CuisineListViewHolder(View v) {
            super(v);
            view = v;
            name = view.findViewById(R.id.name);
        }
    }

    private class SearchByCuisineTask extends AsyncTask<String, Integer, List<RecipesTypeB.Recipe>> {

        @Override
        protected List<RecipesTypeB.Recipe> doInBackground(String... strings) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MealService service = retrofit.create(MealService.class);
                Call<RecipesTypeB> searchCall = service.searchRecipeByCuisine(strings[0]);
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
                TypeBReceipeListActivity.specialText = " with " + temp + " style";
                Intent intent = new Intent(mParentActivity.getApplicationContext() , TypeBReceipeListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mParentActivity.getApplicationContext().startActivity(intent);
            }
        }
    }
}
