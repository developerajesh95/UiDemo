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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.ui.demo.R;
import com.ui.demo.utils.VolleySingelton;


public class FragmentOne extends Fragment {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private TextView tvFragmentOne;
    private ProgressBar progressBar;

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
        makeARequest();
    }

    private void initViews(View view) {
        tvFragmentOne = view.findViewById(R.id.tvFragmentOne);
        progressBar = view.findViewById(R.id.progressBar);

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