package com.ui.demo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ui.demo.adapter.MyRecyclerAdapter;
import com.ui.demo.localdb.MySharedPreferences;
import com.ui.demo.localdb.MySqlliteOpenHelper;
import com.ui.demo.R;
import com.ui.demo.model.UserInfo;
import com.ui.demo.adapter.ViewPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText editTextName;
    private Button buttonSubmit;
    private RecyclerView recyclerView;
    private FrameLayout frameFragment;
    private ViewPager2 viewPager;


    private MyRecyclerAdapter myRecyclerAdapter;
    private ViewPagerAdapter viewPagerAdapter;

    private List<UserInfo> userInfoList;
    private MySqlliteOpenHelper mySqlliteOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        Log.d(TAG, "onCreate: ");
        /*recyclerView = findViewById(R.id.recyclerView);
        userInfoList = new ArrayList<>();
        mySqlliteOpenHelper = new MySqlliteOpenHelper(this);*/
       /* saveDataInDb();
        showList();*/

        /*frameFragment = findViewById(R.id.frameLayout);

        FragmentOne fragmentOne = new FragmentOne();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentOne).commit();*/

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


        viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 1) {
                        tab.setText("Two");
                    } else {
                        tab.setText("One");
                    }
                }
        ).attach();
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

    private void saveDataInDb() {
        mySqlliteOpenHelper.insertData(new UserInfo("Rajesh", "25"));
        mySqlliteOpenHelper.insertData(new UserInfo("Matteo", "24"));
        mySqlliteOpenHelper.insertData(new UserInfo("Max", "27"));
        mySqlliteOpenHelper.insertData(new UserInfo("Julia", "19"));
    }


    private void showList() {
        /*userInfoList.add(new UserInfo("Rajesh", "25"));
        userInfoList.add(new UserInfo("Matteo", "24"));
        userInfoList.add(new UserInfo("Max", "27"));
        userInfoList.add(new UserInfo("Julia", "19"));*/
        userInfoList = mySqlliteOpenHelper.getUserInfo();
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