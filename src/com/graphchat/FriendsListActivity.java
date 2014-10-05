package com.graphchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.ChatFriend;


public class FriendsListActivity extends Activity 
{
	private ListView friendsLV;
	private ImageButton newChatButton;
	private FriendsAdapter mAdapter;
	private List<ChatFriend> friendDetailList;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friends_page);
		
		friendsLV = (ListView) findViewById(R.id.friendslistLV);
		newChatButton = (ImageButton) findViewById(R.id.AddChatButton);
		friendDetailList = new ArrayList<ChatFriend>();
		
		//make a fake friend
		ChatFriend c = new ChatFriend();
		c.firstname = "John";
		c.lastname = "Smith";
		friendDetailList.add(c);
		
		mAdapter = new FriendsAdapter(getApplicationContext(), friendDetailList);
		friendsLV.setAdapter(mAdapter);
		friendsLV.setOnItemClickListener(newchatlistener);
		newChatButton.setOnClickListener(addButtonListener);
	}
	
	/**
	 * Item Clicker listener for the listview
	 */
	private OnItemClickListener newchatlistener = new OnItemClickListener() 
	{
		public void onItemClick(AdapterView parent, View v, int position,long id) 
		{
			String msg = "Added: " + friendDetailList.get(position).firstname + " "
					+ friendDetailList.get(position).lastname;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
	};
	
	/**
	 * ButtonClickListener for add button
	 */
	private OnClickListener addButtonListener = new View.OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			Intent intent = new Intent(FriendsListActivity.this, ChatRoomActivity.class);
			startActivity(intent);
		}
	};
	
	private class FriendsAdapter extends ArrayAdapter
	{
		private List<ChatFriend> mfriendslist;
		private Context mContext = null;
		private LayoutInflater inflater = null;
		
		private class ViewHolder 
		{
			ImageView img;
            TextView line1;
            TextView line2;
        }
		
		public FriendsAdapter(Context context, List<ChatFriend> items) 
		{
			super(context, R.layout.friends_list_item, items);
			this.mfriendslist = items;
			this.mContext = context;
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() 
		{
			return mfriendslist.size();
		}

		@Override
		public ChatFriend getItem(int item) 
		{
			return mfriendslist.get(item);
		}

		@Override
		public long getItemId(int id) 
		{
			return id;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) 
		{
			ViewHolder viewholder = new ViewHolder();
			view = inflater.inflate(R.layout.friends_list_item, parent, false);
			
			ChatFriend current = mfriendslist.get(position);
			viewholder.line1 = (TextView) view.findViewById(R.id.line1);
			viewholder.line2 = (TextView) view.findViewById(R.id.line2);
			viewholder.line1.setText(current.firstname);
			viewholder.line2.setText(current.lastname);
			view.setTag(viewholder);
			return view;
		}
		
	}
}
