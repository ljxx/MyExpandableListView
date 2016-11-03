package com.alertviewdemo.www;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list_item_for_buddy);
		
		
		TextView nick = (TextView)findViewById(R.id.contact_list_item_name);
		ImageView head = (ImageView)findViewById(R.id.icon);	
		
		Bundle bundle = this.getIntent().getExtras();
		
		nick.setText(bundle.getString("userName"));
		head.setImageResource(bundle.getInt(("headImage")));
	}

}
