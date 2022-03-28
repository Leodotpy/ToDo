package com.best.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.best.todo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_entries);
    }
}