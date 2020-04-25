package com.example.groupassignment.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.Activities.AboutWhatToEat;
import com.example.groupassignment.Activities.TypeBReceipeListActivity;
import com.example.groupassignment.Adapters.NameSearchAdapter;
import com.example.groupassignment.Databases.RecipeDatabase;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Recipe;
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

public class MoreFragment extends Fragment {
    private RecyclerView recyclerView;
    private ConstraintLayout btnAbout;
    private Button btnClear;
    private NameSearchAdapter mAdapter;
    private RecipeDatabase recipeDatabase;
    private TextView notice;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        recyclerView = view.findViewById(R.id.rv_favourite);
        btnAbout = view.findViewById(R.id.btnAbout);
        btnClear = view.findViewById(R.id.btnClear);
        recipeDatabase = RecipeDatabase.getInstance(getContext());
        notice = view.findViewById(R.id.notice);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new NameSearchAdapter(getContext() ,new ArrayList<Recipe>());
        mAdapter.setData(recipeDatabase.recipeDao().getAllRecipe());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Remove all Favourite")
                        .setMessage("Are you sure want to remove all Favourite Recipes?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recipeDatabase.recipeDao().clearTable();
                                Toast.makeText(getContext(), "All Recipes Removed.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , AboutWhatToEat.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                 mAdapter.setData(recipeDatabase.recipeDao().getAllRecipe());
                                if(recipeDatabase.recipeDao().getAllRecipe().size() > 0){
                                    notice.setVisibility(View.GONE);
                                } else {
                                    notice.setVisibility(View.VISIBLE);
                                    notice.setText("The list is empty.\nGo search and add some recipes. ;)");
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        return view;
    }
}
