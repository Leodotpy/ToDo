package com.best.todo;

import java.util.ArrayList;

public class CompletedEntries {
    int completedEntriesInteger;
    ArrayList<ToDoEntry> completedList;

    public CompletedEntries() {
        this.completedEntriesInteger = 0;
        this.completedList = new ArrayList<>();
    }

    public int getCompletedEntriesInt() {
        return completedEntriesInteger;
    }

    public void addEntry(ToDoEntry entry) {
        this.completedList.add(entry);
        this.completedEntriesInteger += 1;
    }

    public void addEntry(String name, String deadline) {
        this.completedList.add(new ToDoEntry(name, deadline));
        this.completedEntriesInteger += 1;
    }

    public ArrayList<ToDoEntry> getCompletedList() {
        return completedList;
    }
    @Override
    public String toString() {
        return this.completedList.toString();
    }

}
