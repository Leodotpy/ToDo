package com.best.todo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoEntry> todoList;
    private Database db;
    private ActiveEntries activity;

    public ToDoAdapter(Database db, ActiveEntries activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

        final ToDoEntry item = todoList.get(position);
        holder.entry.setText(item.getName());
        holder.deadline.setText(item.getDeadline());
        holder.entry.setChecked(toBoolean(item.getStatus()));
        holder.entry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                } else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    public Context getContext() {
        return activity;
    }

    public void setEntries(List<ToDoEntry> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteEntry(int position) {
        ToDoEntry item = todoList.get(position);
        db.deleteEntry(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox entry;
        TextView deadline;

        ViewHolder(View view) {
            super(view);
            entry = view.findViewById(R.id.todoCheckBox);
            deadline = view.findViewById(R.id.textDeadline);
        }
    }
}