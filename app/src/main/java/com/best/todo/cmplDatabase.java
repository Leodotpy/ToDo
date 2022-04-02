package com.best.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class cmplDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String NAME1 = "newToDoListDatabase";
    private static final String TODO_TABLE1 = "todo";
    private static final String ID1 = "id";
    private static final String TASK_NAME1 = "name";
    private static final String STATUS1 = "status";
    private static final String DEADLINE1 = "deadline";
    private static final String CREATE_TODO_TABLE1 = "CREATE TABLE " + TODO_TABLE1 + "(" + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_NAME1 + " TEXT, "
            + STATUS1 + " INTEGER, " + DEADLINE1 + " TEXT)";

    private SQLiteDatabase db;

    public cmplDatabase(Context context) {
        super(context, NAME1, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE1);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void addEntry(ToDoEntry entry){
        ContentValues cv = new ContentValues();
        cv.put(TASK_NAME1, entry.getName());
        cv.put(DEADLINE1, entry.getDeadline());
        cv.put(STATUS1, 1);
        db.insert(TODO_TABLE1, null, cv);
    }

    public List<ToDoEntry> getAllEntries(){
        List<ToDoEntry> entryList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            cursor = db.query(TODO_TABLE1, null, null, null, null, null, null, null);
            if(cursor != null){
                if(cursor.moveToFirst()){
                    do{
                        ToDoEntry entry = new ToDoEntry();
                        entry.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID1)));
                        entry.setDeadline(cursor.getString(cursor.getColumnIndexOrThrow(DEADLINE1)));
                        entry.setName(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME1)));
                        entry.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(STATUS1)));
                        entryList.add(entry);
                    }
                    while(cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cursor != null;
            cursor.close();
        }
        return entryList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS1, status);
        db.update(TODO_TABLE1, cv, ID1 + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteEntry(int id){
        db.delete(TODO_TABLE1, ID1 + "= ?", new String[] {String.valueOf(id)});
    }
}

