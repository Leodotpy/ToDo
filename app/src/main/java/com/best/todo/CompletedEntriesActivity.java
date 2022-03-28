package com.best.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompletedEntriesActivity extends Activity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_entries);

        //backButton = findViewById(R.id.(insert button name here));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
