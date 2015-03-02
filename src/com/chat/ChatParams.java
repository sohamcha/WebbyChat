package com.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatParams {
	
	private static List<String> users = new ArrayList<String>();
	private static Map<Integer,String> chatMap = new HashMap<Integer, String>();
	private static int chatCounter=0;
	private static StringBuffer listOfParticipants = new StringBuffer();
	
	

	public static int getChatCounter() {
		return chatCounter;
	}
	
	public static List<String> getUsers() {
		return users;
	}

	public static void setUsers(List<String> users) {
		ChatParams.users = users;
	}
	
	public static synchronized void setUsers(String user){
		users.add(user);
		System.out.println("User Joined: "+user);
		reCalculateParticipants();
	}
	
	public static synchronized void removeUsers(String user){
		users.remove(user);
		System.out.println("User Logged Out: "+user);
		reCalculateParticipants();
	}

	public static Map<Integer,String> getChatMap() {
		return chatMap;
	}

	public static void setChatMap(Map<Integer,String> chatMap) {
		ChatParams.chatMap = chatMap;
	}
	
	public static synchronized void addChat(String chat){
		chatMap.put(chatCounter++, chat);
	}
	

	public static StringBuffer getListOfParticipants() {
		return listOfParticipants;
	}

	public static void setListOfParticipants(StringBuffer listOfParticipants) {
		ChatParams.listOfParticipants = listOfParticipants;
	}

	public static String getParticipants(){
		return listOfParticipants.toString();
	}
	
	public static synchronized String getChatString(int counter){
		StringBuffer chatString = new StringBuffer();
		
		// JSON Parsing
		chatString.append("{\"chatCount\":\""+chatCounter+"\",\"chatString\":\"");
		
		
		
		for(int i=counter;i<chatCounter;i++){
			chatString.append(chatMap.get(i));
			chatString.append("\\n");
		}
		chatString.append("\"}");
		return chatString.toString();
	}

	public static synchronized void reCalculateParticipants(){
		listOfParticipants.delete(0, listOfParticipants.length());
		for(String user:users){
		listOfParticipants.append(user);
		listOfParticipants.append(System.getProperty("line.separator"));
		}
	}
}
