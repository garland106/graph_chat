package com.graphchat;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChatRoomListActivity extends Activity 
{
	private ListView chatRoomList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chatlist_page);
		chatRoomList = (ListView)findViewById(R.id.chatRoomlv);
	}
	
	private class ChatRoomAdapter extends ArrayAdapter
	{
		private List<chatRoom> chatRoomList;
		private Context mContext = null;
		private LayoutInflater inflater = null;
		
		private class ViewHolder 
		{
            TextView line1;
            TextView line2;
        }
		
		public ChatRoomAdapter(Context context, List<chatRoom> items) 
		{
			super(context, R.layout.chatroom_list_item, items);
			this.chatRoomList = items;
			this.mContext = context;
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() 
		{
			return chatRoomList.size();
		}

		@Override
		public chatRoom getItem(int item) 
		{
			return chatRoomList.get(item);
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
			view = inflater.inflate(R.layout.chatroom_list_item, parent, false);
			
			chatRoom currentChatRoom = chatRoomList.get(position);
			viewholder.line1 = (TextView) view.findViewById(R.id.line1);
			viewholder.line2 = (TextView) view.findViewById(R.id.line2);
			viewholder.line1.setText(currentChatRoom.getRoomName());
			viewholder.line2.setText(currentChatRoom.getNamesOfFriendsInRoom());
			view.setTag(viewholder);
			return view;
		}
		
	}
	
	public class chatRoom 
	{
		public String title;
		public String names;
		
		public chatRoom(String tit, String nameString)
		{
			title = tit;
			names = nameString;
		}
		
		public String getRoomName(){
			return title;
		}
		
		public String getNamesOfFriendsInRoom(){
			return names;
		}
		
	}

}
