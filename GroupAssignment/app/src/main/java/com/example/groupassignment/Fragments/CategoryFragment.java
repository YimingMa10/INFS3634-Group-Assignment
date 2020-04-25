package com.example.groupassignment.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groupassignment.Adapters.CategoryListAdapter;
import com.example.groupassignment.Adapters.NameSearchAdapter;
import com.example.groupassignment.Models.Categories;
import com.example.groupassignment.R;
import com.example.groupassignment.StaticResource;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryListAdapter mAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.rv_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new CategoryListAdapter(getActivity(), new ArrayList<Categories.Category>());
        mAdapter.setData(StaticResource.categoriesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
