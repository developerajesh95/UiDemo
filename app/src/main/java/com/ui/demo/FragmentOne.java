package com.ui.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentOne extends Fragment {

    private TextView tvFragmentOne;

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


    private void initViews(View view) {
        tvFragmentOne = view.findViewById(R.id.tvFragmentOne);

        tvFragmentOne.setOnClickListener(v -> {
            FragmentTwo fragmentTwo = new FragmentTwo();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout, fragmentTwo).addToBackStack(null).commit();
        });
    }
}