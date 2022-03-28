package com.best.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Navigation extends AppCompatActivity {

    Button switchToCompletedEntries;
    Button backButton;

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
    }

    private void switchToCompletedEntries() {
        Intent switchActivityIntent = new Intent(this, CompletedEntriesActivity.class);
        startActivity(switchActivityIntent);
    }
}