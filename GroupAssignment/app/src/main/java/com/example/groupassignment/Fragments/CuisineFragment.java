package com.example.groupassignment.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.groupassignment.Activities.TypeBReceipeListActivity;
import com.example.groupassignment.Adapters.CuisineListAdapter;
import com.example.groupassignment.Adapters.IngredientListAdapter;
import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.Models.RecipesTypeB;
import com.example.groupassignment.R;
import com.example.groupassignment.Services.MealService;
import com.example.groupassignment.StaticResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CuisineFragment extends Fragment {

    private RecyclerView recyclerView;
    private CuisineListAdapter mAdapter;

    public CuisineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuisine, container, false);

        recyclerView = view.findViewById(R.id.rv_cuisine);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new CuisineListAdapter(getActivity(), new ArrayList<String>());
        mAdapter.setData(StaticResource.cuisinesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
