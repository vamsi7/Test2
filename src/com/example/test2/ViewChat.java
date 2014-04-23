/* To-do in this file
 * 
 * 1. Include the functionality for the send button. i.e Take the msg and store it in the db
 * 2. Implement Unread messages feature
 * */


package com.example.test2;

import java.util.List;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ViewChat extends ListActivity {
	
	private ImageView imgPreview;
	private String fname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_chat);
	
		String chat_id = getIntent().getExtras().getString("chat_id");
		DbMsglist db = new DbMsglist(this);
		DbChatlist db1 = new DbChatlist(this);
		
		fname = db1.getName(chat_id);
		
		List<String> allmsgs = db.getAllmsgs(Integer.parseInt(chat_id));
		
		imgPreview = (ImageView) findViewById(R.id.imageView1);
		previewCapturedImage();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, allmsgs);
        setListAdapter(adapter);
	}

private void previewCapturedImage() {
	try {
		// hide video preview
		//videoPreview.setVisibility(View.GONE);

		imgPreview.setVisibility(View.VISIBLE);

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inSampleSize = 8;

		final Bitmap bitmap = BitmapFactory.decodeFile(fname,options);

		imgPreview.setImageBitmap(bitmap);
	} catch (NullPointerException e) {
		e.printStackTrace();
	}
}

}