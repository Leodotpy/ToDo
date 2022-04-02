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

public class CEAdapter extends RecyclerView.Adapter<CEAdapter.ViewHolder1> {

    private List<ToDoEntry> todoList;
    private cmplDatabase cdb;
    private Database db;
    private CompletedEntriesActivity activity;

    public CEAdapter(cmplDatabase cdb, Database db, CompletedEntriesActivity activity) {
        this.db = db;
        this.cdb = cdb;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_layout, parent, false);
        return new ViewHolder1(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder1 holder, int position) {
        db.openDatabase();
        cdb.openDatabase();

        final ToDoEntry item = todoList.get(position);
        holder.entry.setText(item.getName());
        holder.deadline.setText(item.getDeadline());
        holder.entry.setChecked(toBoolean(item.getStatus()));
        holder.entry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                }
                else {
                    cdb.updateStatus(item.getId(), 0);
                    db.addEntry(new ToDoEntry(item));
                    deleteEntry(holder.getAdapterPosition());
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
        cdb.deleteEntry(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        CheckBox entry;
        TextView deadline;

        ViewHolder1(View view) {
            super(view);
            entry = view.findViewById(R.id.todoCheckBox);
            deadline = view.findViewById(R.id.textDeadline);
        }
    }
}
