package com.best.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK_NAME = "name";
    private static final String STATUS = "status";
    private static final String DEADLINE = "deadline";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_NAME + " TEXT, "
            + STATUS + " INTEGER, " + DEADLINE + " TEXT)";

    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void addEntry(ToDoEntry entry){
        ContentValues cv = new ContentValues();
        cv.put(TASK_NAME, entry.getName());
        cv.put(DEADLINE, entry.getDeadline());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }

    public List<ToDoEntry> getAllEntries(){
        List<ToDoEntry> entryList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            cursor = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if(cursor != null){
                if(cursor.moveToFirst()){
                    do{
                        ToDoEntry entry = new ToDoEntry();
                        entry.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                        entry.setDeadline(cursor.getString(cursor.getColumnIndexOrThrow(DEADLINE)));
                        entry.setName(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME)));
                        entry.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(STATUS)));
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
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteEntry(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}
