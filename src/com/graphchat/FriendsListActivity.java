package com.graphchat;

import java.util.ArrayList;
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


public class FriendsListActivity extends Activity 
{
	private ListView friendsListView;
	//private GestureDetectorCompat gDetect;
	private FriendsAdapter fa;
	//List<chatFriend> friends;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friends_page);
		final List<chatFriend> friends = new ArrayList<chatFriend>();
		friendsListView = (ListView) findViewById(R.id.friendslv);
		final chatFriend f1 = new chatFriend("Sy","Adamowsky");
		final chatFriend f2 = new chatFriend("Garland","Chen");
		final chatFriend f3 = new chatFriend("Chris","O'Brien");
		final chatFriend f4 = new chatFriend("SY", "Luu");
		friends.add(f1); friends.add(f2); friends.add(f3); friends.add(f4);
		//System.out.println(f1.getFirstName() + " " + f1.getLastName());
		fa = new FriendsAdapter(getApplicationContext(), friends);
		
		friendsListView.setAdapter(fa);
		
		//gDetect = new GestureDetectorCompat(this, new GestureListener());
	}
	
	/*@Override
	public boolean onTouchEvent(MotionEvent event){
		  this.gDetect.onTouchEvent(event);
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
		public boolean onFling(MotionEvent event1, MotionEvent event2,
		    float velocityX, float velocityY) 
		{
			boolean forward = false;
			boolean backward = false;
			
			float horizontalDiff = event2.getX() - event1.getX();
			float verticalDiff = event2.getY() - event1.getY();
			
			float absHDiff = Math.abs(horizontalDiff);
			float absVDiff = Math.abs(verticalDiff);
			float absVelocityX = Math.abs(velocityX);
			float absVelocityY = Math.abs(velocityY);
			
			if(absHDiff>absVDiff && absHDiff>flingMin && absVelocityX>velocityMin){
				if(horizontalDiff>0) backward=true;
				else forward=true;
			}
			else if(absVDiff>flingMin && absVelocityY>velocityMin){
				  if(verticalDiff>0) backward=true;
				  else forward=true;
			}
			
			//user is cycling forward through friends
			
			return true;
		}
	}
	*/
	
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
