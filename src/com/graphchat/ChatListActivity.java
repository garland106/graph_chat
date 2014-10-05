package com.graphchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class ChatListActivity extends Activity 
{
	private ListView chatRoomList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chatlist_page);
		chatRoomList = (ListView)findViewById(R.id.)
	}

}
