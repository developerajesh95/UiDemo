package com.ui.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformationActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information);

        textView = findViewById(R.id.textView);

        String value = getDataFromPreference();

        textView.setText("Hello! " + value);

    }


    private String getDataFromPreference() {
        String value = MySharedPreferences.getInstance(this).getStringValue("name");
        if (!value.isEmpty()) {
            return value;
        } else {
            return "";
        }
    }
}