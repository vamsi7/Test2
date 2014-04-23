package com.example.test2;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SentList extends ListActivity {

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sent_list);
	    
	    DbChatlist db = new DbChatlist(this);
	    List<String> allChats = db.getAllChats();
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, allChats);
	        setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Class myClass;
		try {
			String clickedId = ((TextView) v).getText().toString();
			myClass = Class.forName("com.example.test2.ViewChat");
			Intent myIntent = new Intent(SentList.this, myClass);
			myIntent.putExtra("chat_id", clickedId);
			startActivity(myIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
