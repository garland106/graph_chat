package com.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class ParseAPIUtils 
{
	public static ParseUser user;
	public static List<Message> messageList;
	
	public static boolean getUser()
	{
		user = ParseUser.getCurrentUser();
		if(user == null)
		{
			return false;
		}
		return true;
	}
	
	public static void login(String un, String pw, String dn, final Context context)
	{
		user = new ParseUser();
		user.setUsername(dn);
		user.setPassword(pw);
		user.setEmail(un);
		user.add(Constants.DISPLAY_NAME, dn);
		
		ParseUser.logInInBackground(dn, pw, new LogInCallback()
		{
			  public void done(ParseUser user, ParseException e) 
			  {
				  if (e == null) 
				    {
				    	Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
				    } 
				    else 
				    {
				    	Toast.makeText(context, "Invalid Login", Toast.LENGTH_SHORT).show();
				    }
			  }
			});
		getUser();
	}
	
	public static void register(String un, String pw, String dn, final Context context)
	{
		user = new ParseUser();
		user.setUsername(dn);
		user.setPassword(pw);
		user.setEmail(un);
		 
		user.signUpInBackground(new SignUpCallback() 
		{
		  public void done(ParseException e) 
		  {
		    if (e == null) 
		    {
		    	Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
		    } 
		    else 
		    {
		    	Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
		    }
		  }
		});
	}

	public static void sendMessage(String msg, EditText editText)
	{
		ParseObject newMsg = new ParseObject(Constants.MESSAGES_KEY);
		newMsg.put(Constants.USER_ID_KEY, user.getUsername());
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
					final List<Message> newMessages = new ArrayList<Message>();					
					int i = messages.size() - 1;
					for (; i >= 0; i--) 
					{
						final Message message = new Message();
						message.user = messages.get(i).getString(Constants.USER_ID_KEY);
						message.contents = messages.get(i).getString(Constants.MESSAGE_CONTENT);
						newMessages.add(message);
					}
					messageList.clear();
					messageList.addAll(newMessages);
				}
			}
		});
	}
}
