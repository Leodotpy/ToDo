package com.best.todo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActiveEntries extends Activity {

    ImageButton switchToNav;
    FloatingActionButton switchToAddEntry;
    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private List<ToDoEntry> entryList;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_entries);

        database = new Database(this);
        database.openDatabase();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoAdapter(database,this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper swipeToDeleteFunction = new ItemTouchHelper(new SwipeToDelete(adapter));
        swipeToDeleteFunction.attachToRecyclerView(recyclerView);

        entryList = database.getAllEntries();
        adapter.setEntries(entryList);

        switchToAddEntry = findViewById(R.id.btnAddEntry);
        switchToAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToEntryCreator();
            }
        });

        switchToNav = findViewById(R.id.btnNav);
        switchToNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToNavigation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        entryList = database.getAllEntries();
        adapter.setEntries(entryList);
        adapter.notifyDataSetChanged();
    }

    private void switchToNavigation() {
        Intent switchActivityIntent = new Intent(this, Navigation.class);
        startActivity(switchActivityIntent);
    }
    private void switchToEntryCreator() {
        Intent switchActivityIntent = new Intent(this, EntryCreator.class);
        startActivity(switchActivityIntent);
    }



}
