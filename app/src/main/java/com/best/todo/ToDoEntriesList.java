package com.best.todo;

import java.util.ArrayList;

public class ToDoEntriesList {
    ArrayList<ToDoEntry> entryList;

    public ToDoEntriesList () {
        this.entryList = new ArrayList<>();
    }

    public void addEntry(String name, String deadline) {
        this.entryList.add(new ToDoEntry(name, deadline));
    }

    public void removeEntry(String name) {
        for (int i = 0; i < entryList.size(); i++) {
            if (name.equals(entryList.get(i).getName())) {
                entryList.remove(entryList.get(i));
            }
        }
        entryList.trimToSize();
    }

    public ArrayList<ToDoEntry> getEntryList() {
        return entryList;
    }

    @Override
    public String toString() {
        return this.entryList.toString();
    }
}
