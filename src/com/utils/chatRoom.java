package com.utils;

import java.util.ArrayList;

public class chatRoom 
{

	public static String name;
	public static ArrayList<chatFriend> friendsInRoom;
	
	public chatRoom()
	{ 
		name = "";
		friendsInRoom = new ArrayList<chatFriend>();
	}
	
	public void setName(String name) { this.name = name; }
	public void addFriend(chatFriend friend) { friendsInRoom.add(friend); }
	
	public String getRoomName() { return name; }
	public String getNamesOfFriendsInRoom()
	{
		String returnMe = "";
		for(int i = 0; i < friendsInRoom.size(); i++)
		{
			if(i != (friendsInRoom.size() - 1))
				returnMe += friendsInRoom.get(i).firstname + " " + friendsInRoom.get(i).lastname.charAt(0) + ", ";
			else
				returnMe += friendsInRoom.get(i).firstname + " " + friendsInRoom.get(i).lastname.charAt(0);
		}
		return returnMe;
	}
}
