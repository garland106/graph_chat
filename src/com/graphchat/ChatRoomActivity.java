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

import com.parse.ParseObject;
import com.utils.Message;
import com.utils.ParseAPIUtils;

public class ChatRoomActivity extends Activity 
{
	private ListView chatRoomList;
	private List<String> text;
	private Button sendButton;
	private EditText inputField;
	private ChatRoomAdapter mAdapter;
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chatroom);
		
		//ParseAPIUtils.getUser();
		//Makes Chatroom & sets adapter
		chatRoomList = (ListView)findViewById(R.id.lvChat);
		sendButton = (Button) findViewById(R.id.btnSend);
		inputField = (EditText) findViewById(R.id.editMessage);
		ParseAPIUtils.messageList = new ArrayList<Message>();
		//text = new ArrayList<String>();
		//text.add("chris");
		//text.add("sy");
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
	    	ParseAPIUtils.receiveMessage(); 
	    	mAdapter.notifyDataSetChanged();
			chatRoomList.invalidate();
	    	handler.postDelayed(this, 10);
	    }
	};
	
	
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
				ParseAPIUtils.sendMessage(msg, inputField);
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
            TextView chatline;
        }
		
		public ChatRoomAdapter(Context context, List<Message> items) 
		{
			super(context, R.layout.chatroom_list_item, items);
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
			String content = mMsg.user + " " + mMsg.contents;
			viewholder.chatline = (TextView) view.findViewById(R.id.chatline);
			viewholder.chatline.setText(content);
			view.setTag(viewholder);
			return view;
		}
		
	}

}
