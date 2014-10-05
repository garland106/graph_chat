package com.graphchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class FriendsListActivity extends Activity 
{
	private ListView friendsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friends_page);
		friendsList = (ListView) findViewById(R.id.friendslv);
		
	}

	
}
