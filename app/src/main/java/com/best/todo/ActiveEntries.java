package com.best.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.best.todo.R;

public class ActiveEntries extends AppCompatActivity {

    Button switchToNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_entries);

        //switchToNav = findViewById(R.id.(insert button name));
        switchToNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToNavigation();
            }
        });
    }

    private void switchToNavigation() {
        Intent switchActivityIntent = new Intent(this, Navigation.class);
        startActivity(switchActivityIntent);
    }
}