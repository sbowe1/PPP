package com.revature;

import java.lang.String;

class User{

	private int userID; 
	private String userName, password;

	public User(){
	}

	public User(int userID, String userName, String password){
		this.userID = userID;
		this.userName = userName;
		this.password = password;
	}

	public int getUserID() { return userID; }

	public String getUserName() { return userName; }

	public String getPassword() { return password; }

	public void setUserID(int userID) { this.userID = userID; }

	public void setUserName(String userName) { this.userName = userName; }

	public void setPassword(String password) { this.password = password; }

	public String toString() { return userName; }

}