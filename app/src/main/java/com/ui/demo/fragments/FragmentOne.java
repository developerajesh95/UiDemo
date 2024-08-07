package com.ui.demo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.ui.demo.R;
import com.ui.demo.adapter.MyExpandableListAdapter;
import com.ui.demo.utils.ExpandableListDataItems;
import com.ui.demo.utils.VolleySingelton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentOne extends Fragment {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private TextView tvFragmentOne;
    private ProgressBar progressBar;
    private ExpandableListView expandableListView;

    private List<String> expandableTitleList;
    private HashMap<String, List<String>> expandableSubtitleList;

    private MyExpandableListAdapter myExpandableListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        makeARequest();
        expandableSubtitleList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<>(expandableSubtitleList.keySet());

        myExpandableListAdapter = new MyExpandableListAdapter(getContext(), expandableTitleList, expandableSubtitleList);
        expandableListView.setAdapter(myExpandableListAdapter);

        expandableListView.setOnGroupExpandListener(groupPosition -> {
            Toast.makeText(getContext(), "Expanded", Toast.LENGTH_SHORT).show();
        });
        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            Toast.makeText(getContext(), "Collapsed", Toast.LENGTH_SHORT).show();
        });

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            Toast.makeText(getContext(), "Group Clicked", Toast.LENGTH_SHORT).show();
            return false;
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(getContext(), "Child Clicked", Toast.LENGTH_SHORT).show();
            return false;
        });
    }



    private void initViews(View view) {
        tvFragmentOne = view.findViewById(R.id.tvFragmentOne);
        progressBar = view.findViewById(R.id.progressBar);
        expandableListView = view.findViewById(R.id.expandableListView);

        tvFragmentOne.setOnClickListener(v -> {
            FragmentTwo fragmentTwo = new FragmentTwo();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout, fragmentTwo).addToBackStack(null).commit();
        });
    }

    private void makeARequest() {
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BASE_URL, response -> {
            progressBar.setVisibility(View.GONE);
            tvFragmentOne.setVisibility(View.VISIBLE);
            tvFragmentOne.setText("We have response from server");
            Log.d("FragmentOne", "makeARequest: "+response);
        }, error -> {
            progressBar.setVisibility(View.GONE);
            tvFragmentOne.setVisibility(View.VISIBLE);
            tvFragmentOne.setText("We have an error from server");
            Log.d("FragmentOne", "makeARequest: "+error);
        });
        VolleySingelton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }

}