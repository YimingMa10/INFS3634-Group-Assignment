package com.example.groupassignment.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.groupassignment.Adapters.CategoryListAdapter;
import com.example.groupassignment.Adapters.IngredientListAdapter;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.Models.Ingredients;
import com.example.groupassignment.R;
import com.example.groupassignment.StaticResource;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class IngredientFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientListAdapter mAdapter;
    private SearchView searchView;
    private TextView notice;

    public IngredientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        recyclerView = view.findViewById(R.id.rv_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new IngredientListAdapter(getActivity(), new ArrayList<Ingredients.Ingredient>());
        mAdapter.setData(StaticResource.ingredientsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        notice = view.findViewById(R.id.notice);

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Ingredients.Ingredient> tempIngredientList = new ArrayList<Ingredients.Ingredient>();
                for(Ingredients.Ingredient i : StaticResource.ingredientsList){
                    if(i.getIngredientName().toLowerCase().contains(newText.toLowerCase())){
                        tempIngredientList.add(i);
                    }
                }

                if(newText != null){
                    if(tempIngredientList.size() > 0) {
                        mAdapter.setData(tempIngredientList);
                        notice.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        notice.setText("No Ingredient found.");
                        notice.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                } else {
                    mAdapter.setData(StaticResource.ingredientsList);
                    notice.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });


        return view;
    }
}
