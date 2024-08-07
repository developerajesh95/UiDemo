package com.ui.demo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ui.demo.R;
import com.ui.demo.interfaces.UserService;
import com.ui.demo.model.User;
import com.ui.demo.utils.RetrofitInstances;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTwo extends Fragment {


    private TextView tvFragmentTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeARequest();
    }

    private void initViews(View view) {
        tvFragmentTwo = view.findViewById(R.id.tvFragmentTwo);
        tvFragmentTwo.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void makeARequest() {
        UserService userService = RetrofitInstances.getInstance().create(UserService.class);
        Call<List<User>> users = userService.getUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    // Log the list of users or handle them as needed
                    for (User user: users) {
                        Log.d("FragmentTwo", "Users received: " + user.getName());
                    }
                } else {
                    // Handle the case where the response is unsuccessful or the body is null
                    Log.e("FragmentTwo", "Request failed with status: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                // Log the error or handle the failure case
                Log.e("FragmentTwo", "Network request failed", t);
            }
        });

    }
}