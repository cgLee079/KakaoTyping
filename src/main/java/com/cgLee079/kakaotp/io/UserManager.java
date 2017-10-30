package com.cgLee079.kakaotp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public synchronized ArrayList<String> readUser(){
		ArrayList<String> userList = new ArrayList<String>();
		BufferedReader in = null;
		String line = "";
		String[] spliter;
		String user;
		
		try {
			in = new BufferedReader(new FileReader("resources/User.txt"));
			while ((line = in.readLine()) != null) {
				spliter = line.split("\t");
				user = spliter[0] + "." + "\t" + spliter[1];
				userList.add(user);
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
			
			out.write(chracter + "." + "\t" + username);
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
