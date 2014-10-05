package com.utils;

import java.sql.Timestamp;

public class Message 
{
	public String user;
	public String contents;
	public Timestamp time;
	public String messageContent;
	public int numUpVotes;
	public int numDownVotes;
	
	public Message()
	{
		java.util.Date date = new java.util.Date();
        time = new Timestamp(date.getTime());
        user = "";
        messageContent = "";
        numUpVotes = 0;
        numDownVotes = 0;
	}
	public void setUser(String name) { user = name; }
	public void setMessageContent(String content) { messageContent = content; }
	public void incrementUpVotes () { numUpVotes++; }
	public void incrementDownVotes() { numDownVotes++; }
}