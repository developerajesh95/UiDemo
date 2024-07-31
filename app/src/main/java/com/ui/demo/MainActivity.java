package com.ui.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText editTextName;
    private Button buttonSubmit;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter myRecyclerAdapter;
    private List<UserInfo> userInfoList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        recyclerView = findViewById(R.id.recyclerView);
        userInfoList = new ArrayList<>();
        showList();

        /*editTextName = findViewById(R.id.editTextName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(v -> {
            String text = editTextName.getText().toString();
            if (text.isEmpty()) {
                editTextName.setError("Please enter your name");
            } else {
               goToInformationActivity(text);
            }
        });*/
    }

    private void goToInformationActivity(String name) {
        Intent intent = new Intent(this, InformationActivity.class);
        storeDataToPreference(name);
        startActivity(intent);
    }

    private void storeDataToPreference(String name) {
        MySharedPreferences.getInstance(this).saveStringValue("name", name);
        /*SharedPreferences sharedPreferences = getSharedPreferences("UIDemo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();*/
    }


    private void showList() {
        userInfoList.add(new UserInfo("Rajesh", "25"));
        userInfoList.add(new UserInfo("Matteo", "24"));
        userInfoList.add(new UserInfo("Max", "27"));
        userInfoList.add(new UserInfo("Julia", "19"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myRecyclerAdapter = new MyRecyclerAdapter(this, userInfoList);
        recyclerView.setAdapter(myRecyclerAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}