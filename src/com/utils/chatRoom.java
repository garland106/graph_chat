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
	public ArrayList<String> getNamesOfFriendsInRoom()
	{
		ArrayList<String> returnMe = new ArrayList<String>();
		for(int i = 0; i < friendsInRoom.size(); i++) { returnMe.add(friendsInRoom.get(i).firstname + " " + friendsInRoom.get(i).lastname); }
		return returnMe;
	}
}
