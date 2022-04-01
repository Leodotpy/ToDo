package com.best.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import android.widget.ToggleButton;

public class Navigation extends AppCompatActivity {

    Button switchToCompletedEntries;
    Button backButton;
    ToggleButton darkToggleButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        backButton = findViewById(R.id.btnList);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        switchToCompletedEntries = findViewById(R.id.btnCompleted);
        switchToCompletedEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCompletedEntries();
            }
        });


        // Dark Mode Toggle
        darkToggleButton = findViewById(R.id.btnToggleDark);

        darkToggleButton.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                    //uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    //uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                }
                //recreate();
            }

        });
    }

    private void switchToCompletedEntries() {
        Intent switchActivityIntent = new Intent(this, CompletedEntriesActivity.class);
        startActivity(switchActivityIntent);
    }
}