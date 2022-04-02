package com.best.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompletedEntriesActivity extends Activity {
    ImageButton backButton;
    private RecyclerView recyclerView;
    private CEAdapter adapter;
    private List<ToDoEntry> entryList;
    private cmplDatabase ceDatabase;
    private Database eDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_entries);

        ceDatabase = new cmplDatabase(this);
        ceDatabase.openDatabase();
        eDatabase = new Database(this);
        eDatabase.openDatabase();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CEAdapter(ceDatabase, eDatabase,this);
        recyclerView.setAdapter(adapter);

        entryList = ceDatabase.getAllEntries();
        adapter.setEntries(entryList);

        backButton = findViewById(R.id.btnNav);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        entryList = ceDatabase.getAllEntries();
        adapter.setEntries(entryList);
        adapter.notifyDataSetChanged();
    }

}
