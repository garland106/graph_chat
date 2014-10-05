package com.graphchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FriendsListActivity extends Activity 
{
	private ListView friendsListView;
	private GestureListener gDetect;
	private FriendsAdapter fa;
	List<chatFriend> friends;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friends_page);
		friends = new ArrayList<chatFriend>();
		friendsListView = (ListView) findViewById(R.id.friendslv);
		
		final chatFriend f1 = new chatFriend("Sy","Adamowsky");
		for(int i=0; i<15; i++) friends.add(f1);
		
		fa = new FriendsAdapter(getApplicationContext(), friends);
		friendsListView.setAdapter(fa);
		
		gDetect = new GestureListener();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		  return super.onTouchEvent(event);
	}
	
	public class GestureListener extends GestureDetector.SimpleOnGestureListener 
	{
		private float flingMin = 100;
		private float velocityMin = 100;
		
		@Override
		public boolean onDown(MotionEvent event) {
		  return true;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2,
		    float velocityX, float velocityY) 
		{
			boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > flingMin && Math.abs(velocityX) > velocityMin) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } 
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
		}
		
		public void onSwipeRight() {
			FriendsListActivity.this.finish();
			
	    }

	    public void onSwipeLeft() {
	    	Toast.makeText(FriendsListActivity.this, "left", Toast.LENGTH_SHORT).show();
	    }
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

	public class chatFriend 
	{
		public String firstname;
		public String lastname;
		//picture
		public chatFriend(String fn, String ln)
		{
			firstname = fn;
			lastname  = ln;
		}
	}
	
}
