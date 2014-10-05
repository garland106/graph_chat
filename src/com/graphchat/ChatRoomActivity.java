package com.graphchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.utils.Constants;
import com.utils.Message;
import com.utils.ParseAPIUtils;

public class ChatRoomActivity extends Activity 
{
	private static ListView chatRoomList;
	private Button sendButton;
	private EditText inputField;
	private static ChatRoomAdapter mAdapter;
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chatroom_page);
		
		//Makes Chatroom & sets adapter
		chatRoomList = (ListView) findViewById(R.id.lvChat);
		sendButton = (Button) findViewById(R.id.btnSend);
		inputField = (EditText) findViewById(R.id.editMessage);
		
		//Initializes messagelist variables
		ParseAPIUtils.messageList = new ArrayList<Message>();
		mAdapter = new ChatRoomAdapter(getApplicationContext(), ParseAPIUtils.messageList);
		chatRoomList.setAdapter(mAdapter);
		sendButton.setOnClickListener(sendListener);
		//Set handler to refresh & poll for new msgs
		handler.postDelayed(runnable, 100);
	}
	
	// Defines a runnable which is run every 100ms
	private Runnable runnable = new Runnable() {
	    @Override
	    public void run() 
	    {
	    	receiveMessage();
	    	handler.postDelayed(this, 100);
	    }
	};
	
	
	/**
	 * Parse API Function Calls
	 */
	public static void sendMessage(String msg, EditText editText)
	{
		ParseObject newMsg = new ParseObject(Constants.MESSAGES_KEY);
		newMsg.put(Constants.USER_ID_KEY, ParseAPIUtils.user.getUsername());
		newMsg.put(Constants.MESSAGE_CONTENT, msg);
		newMsg.saveInBackground(new SaveCallback() 
		{
			@Override
			public void done(ParseException e) 
			{
				receiveMessage();
			}
		});
		editText.setText("");
		editText.setHint("enter message here");
	}
	
	public static void receiveMessage()
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.MESSAGES_KEY);
		query.setLimit(Constants.MAX_MESSAGES);
		query.orderByDescending("createdAt");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> messages, ParseException e) 
			{
				if (e == null) 
				{
					if(messages.size() == ParseAPIUtils.messageList.size())
					{
						return;
					}
					else
					{
						final List<Message> newMessages = new ArrayList<Message>();					
						int i = messages.size() - 1;
						for (; i >= 0; i--) 
						{
							final Message message = new Message();
							message.user = messages.get(i).getString(Constants.USER_ID_KEY);
							message.contents = messages.get(i).getString(Constants.MESSAGE_CONTENT);
							newMessages.add(message);
						}
						ParseAPIUtils.messageList.clear();
						ParseAPIUtils.messageList.addAll(newMessages);
						mAdapter.notifyDataSetChanged();
				    	chatRoomList.invalidate();
					}
				}
			}
		});
	}
	
	
	/**
	 * @author garland
	 * Adapters && Button List adapters
	 */
	
	private OnClickListener sendListener = new View.OnClickListener() 
	{	
		@Override
		public void onClick(View v) 
		{
			String msg = inputField.getText().toString();
			if(msg == null)
			{
				return;
			}
			else if(!msg.equals(""))
			{
				sendMessage(msg, inputField);
				mAdapter.notifyDataSetChanged();
				chatRoomList.invalidate();
			}
		}
	};
	
	private class ChatRoomAdapter extends ArrayAdapter
	{
		private List<Message> msgList;
		private Context mContext = null;
		private LayoutInflater inflater = null;
		
		private class ViewHolder 
		{
			TextView displayname;
            TextView chatline;
        }
		
		public ChatRoomAdapter(Context context, List<Message> items) 
		{
			super(context, R.layout.chat_list_item, items);
			this.msgList = items;
			this.mContext = context;
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() 
		{
			return msgList.size();
		}

		@Override
		public Message getItem(int item) 
		{
			return msgList.get(item);
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
			view = inflater.inflate(R.layout.chat_list_item, parent, false);
			
			Message mMsg = msgList.get(position);
			viewholder.displayname = (TextView) view.findViewById(R.id.chatroomname);
			viewholder.chatline = (TextView) view.findViewById(R.id.chatline);
			viewholder.chatline.setText(mMsg.contents);
			viewholder.displayname.setText(mMsg.user);
			view.setTag(viewholder);
			return view;
		}
		
	}

}
