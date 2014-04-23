package com.example.test2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	 
    // Database Name
    private static final String DATABASE_NAME = "beta1";
 
    // Contacts table name
    private static final String TABLE_SENT = "sentlist";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_SENTLIST = "CREATE TABLE " + TABLE_SENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT" + ")";
		
		db.execSQL(CREATE_SENTLIST);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENT);
		 
        // Create tables again
        onCreate(db);
		
	}
	
	public void del_table(){
		SQLiteDatabase db = this.getWritableDatabase();
	}
	
	public void addNewChat(int id, String fname){
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, fname); // Contact Name
	    //values.put(KEY_ID, null); // Contact Phone Number
	 
	    // Inserting Row
	    db.insert(TABLE_SENT, null, values);
	    db.close(); // Closing database connection
	}
	
	public String getName(String id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	    String query = "SELECT * FROM " + TABLE_SENT + " where id = " + id;
	    Cursor cursor = db.rawQuery(query, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	    
	    return cursor.getString(1);
	    
	}
	
	public List<String> getAllChats(){
		List<String> allChats = new ArrayList<String>();
		String Select_query = "SELECT * FROM " + TABLE_SENT;
		
		SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(Select_query, null);
		
	    if (cursor.moveToFirst()) {
	        do {
	        	allChats.add(String.valueOf(cursor.getInt(0)));
	        	Log.i("msg1", String.valueOf(cursor.getInt(0)));
	        }while(cursor.moveToNext());
	    }
		
	    return allChats;
	}

}
