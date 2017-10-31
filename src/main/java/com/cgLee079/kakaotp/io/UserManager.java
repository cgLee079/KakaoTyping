package com.cgLee079.kakaotp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cgLee079.kakaotp.model.User;

public class UserManager {
	private static UserManager instance = null;
	
	public static UserManager getInstance(){
		if(instance == null){
			instance = new UserManager();
		}
		return instance;
	}
	
	private UserManager(){
		
	}
	
	public synchronized ArrayList<User> readUser(){
		ArrayList<User> userList = new ArrayList<User>();
		BufferedReader in = null;
		String line = "";
		String[] spliter;
		String character;
		String username;
		
		try {
			in = new BufferedReader(new FileReader("resources/User.txt"));
			while ((line = in.readLine()) != null) {
				spliter = line.split("\t");
				character = spliter[0];
				username = spliter[1];
				userList.add(new User(username, character));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public synchronized void writeUser(String chracter, String username) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("resources/User.txt", true));
			out.write(chracter + "\t" + username);
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
