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

public class DbMsglist extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	 
    // Database Name
    private static final String DATABASE_NAME = "beta1";
 
    // Contacts table name
    private static final String MSG_TABLE = "msglist";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "m_id";
    private static final String FROM_ID = "sender_id";
    private static final String FROM_NAME = "sender_name";
    private static final String MSG = "sent_message";
    private static final String TIMESTAMP = "recieved_timestamp";
    
	public DbMsglist(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_SENTLIST = "CREATE TABLE " + MSG_TABLE + "("
                + KEY_ID + " INTEGER, " + FROM_ID + " INTEGER, " + FROM_NAME + " TEXT, " + MSG + " TEXT, " + TIMESTAMP + " TEXT " + ")";
		
		db.execSQL(CREATE_SENTLIST);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + MSG_TABLE);
		 
        // Create tables again
        onCreate(db);
		
	}
	public void create_table(){
		SQLiteDatabase db = this.getWritableDatabase();
		String CREATE_SENTLIST = "CREATE TABLE " + MSG_TABLE + "("
                + KEY_ID + " INTEGER, " + FROM_ID + " INTEGER, " + FROM_NAME + " TEXT, " + MSG + " TEXT, " + TIMESTAMP + " TEXT " + ")";
		
		db.execSQL(CREATE_SENTLIST);
	}
	
	public void del_table(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + MSG_TABLE);
	}
	
	public void addNewMsg(int m_id, int s_id, String from, String msg, String ts){
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_ID, m_id); // Contact Name
	    values.put(FROM_ID, s_id); // Contact Phone Number
	    values.put(FROM_NAME, from);
	    values.put(MSG, msg);
	    values.put(TIMESTAMP, ts);
	    // Inserting Row
	    db.insert(MSG_TABLE, null, values);
	    
	    Log.i("msg1", msg);
	    
	    db.close(); // Closing database connection
	}
	
//	public String getName(String id) {
//	    SQLiteDatabase db = this.getReadableDatabase();
//	    String query = "SELECT * FROM " + MSG_TABLE + " where id = " + id;
//	    Cursor cursor = db.rawQuery(query, null);
//	    if (cursor != null)
//	        cursor.moveToFirst();
//	    
//	    return cursor.getString(1);
//	    
//	}
	
	public List<String> getAllmsgs(int id){
		List<String> allmsgs = new ArrayList<String>();
		String Select_query = "SELECT * FROM " + MSG_TABLE + " WHERE m_id = " + id + " ORDER BY recieved_timestamp";
		
		SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(Select_query, null);
		
	    if (cursor.moveToFirst()) {
	        do {
	        	allmsgs.add(String.valueOf(cursor.getString(2)) + ": " + String.valueOf(cursor.getString(3)));
	        	Log.i("msg1", String.valueOf(cursor.getInt(3)));
	        }while(cursor.moveToNext());
	    }
		
	    return allmsgs;
	}

}
