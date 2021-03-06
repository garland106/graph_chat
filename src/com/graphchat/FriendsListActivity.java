package com.graphchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.utils.ParseAPIUtils;


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
		
		ParseAPIUtils.getUser();
		friendsLV = (ListView) findViewById(R.id.friendslistLV);
		newChatButton = (ImageButton) findViewById(R.id.AddChatButton);
		friendDetailList = new ArrayList<ChatFriend>();
		addFakeFriends();
		
		mAdapter = new FriendsAdapter(getApplicationContext(), friendDetailList);
		friendsLV.setAdapter(mAdapter);
		friendsLV.setOnItemClickListener(newchatlistener);
		newChatButton.setOnClickListener(addButtonListener);
	}
	
	private void addFakeFriends()
	{
		//make a fake friend
		ChatFriend c1 = new ChatFriend();
		c1.firstname = "Chris";
		c1.lastname = "O'Brien";
		ChatFriend c2 = new ChatFriend();
		c2.firstname = "Garland";
		c2.lastname = "Chen";
		ChatFriend c3 = new ChatFriend();
		c3.firstname = "Jefferson";
		c3.lastname = "Luu";
		ChatFriend c4 = new ChatFriend();
		c4.firstname = "Sy";
		c4.lastname = "Adamofsky";
		friendDetailList.add(c1);
		friendDetailList.add(c2);
		friendDetailList.add(c3);
		friendDetailList.add(c4);
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
            ImageView icon;
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
			
			ChatFriend current = 		mfriendslist.get(position);
			viewholder.line1 = (TextView) view.findViewById(R.id.line1);
			viewholder.line2 = (TextView) view.findViewById(R.id.line2);
			viewholder.icon = (ImageView) view.findViewById(R.id.friendIcon);
			viewholder.line1.setText(current.firstname);
			viewholder.line2.setText(current.lastname);
			String name = ParseAPIUtils.user.getUsername();
			if(name.toLowerCase(Locale.US).endsWith("cat")) viewholder.icon.setImageResource(R.drawable.cateasteregg);
			else viewholder.icon.setImageResource(R.drawable.defaultfriend);
			view.setTag(viewholder);
			return view;
		}
		
	}
}
