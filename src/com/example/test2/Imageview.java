package com.example.test2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Imageview extends Activity {
	private ImageView imgPreview;
	private Button sendButton;
	private String fname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		
		sendButton = (Button) findViewById(R.id.send);
		imgPreview = (ImageView) findViewById(R.id.imageView1);
		fname = getIntent().getExtras().getString("name");
		
		previewCapturedImage();
		
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendImage();
				
			}
		});
	}
	
	private void previewCapturedImage() {
		try {
			// hide video preview
			//videoPreview.setVisibility(View.GONE);

			imgPreview.setVisibility(View.VISIBLE);

			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();

			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 8;

			final Bitmap bitmap = BitmapFactory.decodeFile(fname,options);

			imgPreview.setImageBitmap(bitmap);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	private void sendImage(){
		Class myClass;
		try {
			myClass = Class.forName("com.example.test2.SentList");
			Intent myIntent = new Intent(Imageview.this, myClass);
//			Bundle fname = new Bundle();
//			fname.putString("name", fileUri.getPath());
			
			DbChatlist db = new DbChatlist(this);
			db.addNewChat(1, fname);
			startActivity(myIntent);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}