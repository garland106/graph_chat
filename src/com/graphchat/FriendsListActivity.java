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

import com.utils.chatFriend;

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
	
	private class FriendsAdapter extends ArrayAdapter
	{
		private List<chatFriend> mfriendslist;
		private Context mContext = null;
		private LayoutInflater inflater = null;
		
		private class ViewHolder 
		{
			ImageView img;
            TextView line1;
            TextView line2;
        }
		
		public FriendsAdapter(Context context, List<chatFriend> items) 
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
		public chatFriend getItem(int item) 
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
			
			chatFriend currentFriend = mfriendslist.get(position);
			viewholder.line1 = (TextView) view.findViewById(R.id.line1);
			viewholder.line2 = (TextView) view.findViewById(R.id.line2);
			viewholder.line1.setText(currentFriend.firstname);
			viewholder.line2.setText(currentFriend.lastname);
			view.setTag(viewholder);
			return view;
		}
		
	}


	
}
