package com.best.todo;


import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;


public class EntryCreator extends Activity {
    public static final String TAG = "ActionBottomDialogue";
    Button backButton;
    Button addButton;
    private Database database;
    EditText name;
    EditText deadline;

    public static EntryCreator newInstance() {
        return new EntryCreator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_creator);

        backButton = findViewById(R.id.btnCancel);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name = findViewById(R.id.editTextName);
        deadline = findViewById(R.id.editTextDate);
        database = new Database(this);
        database.openDatabase();
        addButton = findViewById((R.id.btnCreate));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryName = name.getText().toString();
                String entryDeadline = deadline.getText().toString();
                ToDoEntry entry = new ToDoEntry();
                entry.setName(entryName);
                entry.setDeadline(entryDeadline);
                entry.setStatus(0);
                database.addEntry(entry);

                finish();
            }

        });
    }
}

