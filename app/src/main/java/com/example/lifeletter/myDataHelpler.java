package com.example.lifeletter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class myDataHelpler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "todolist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_LOCATION = "location";

    public myDataHelpler(@Nullable Context context) {
        super(context, "todolist.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_LOCATION + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String addOne(TodoList todolist){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,todolist.getName() );
        cv.put(COLUMN_TIME,todolist.getTime());
        cv.put(COLUMN_LOCATION,todolist.getLocation());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long insert =sqLiteDatabase.insert(TABLE_NAME,COLUMN_NAME,cv);
        if(insert==-1){
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }

    public String deleteOne(TodoList todolist){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        int delete=sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(todolist.getId())});
        if(delete==0){
            return "fail";
        }

        sqLiteDatabase.close();
        return "success";
    }

    public String updateOne(TodoList todolist){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,todolist.getName() );
        cv.put(COLUMN_TIME,todolist.getTime());
        cv.put(COLUMN_LOCATION,todolist.getLocation());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int update=sqLiteDatabase.update(TABLE_NAME,cv,COLUMN_ID+"=?",new String[]{String.valueOf(todolist.getId())});
        sqLiteDatabase.close();

        if (update==0){
            return"fail";
        }
        return "success";
    }

    public List<TodoList> getall(){
        TodoList todolist;
        List<TodoList> list = new ArrayList<>();

        String  sql="SELECT * FROM " +TABLE_NAME;

        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int timeIndex = cursor.getColumnIndex(COLUMN_TIME);
        int locationIndex = cursor.getColumnIndex(COLUMN_LOCATION);

        while(cursor.moveToNext()){

            todolist = new TodoList(cursor.getInt(idIndex),cursor.getString(nameIndex),cursor.getString(timeIndex),cursor.getString(locationIndex));
            list.add(todolist );
        }
        sqLiteDatabase.close();
        return list;
    }
}
