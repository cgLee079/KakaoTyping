package com.cgLee079.tpgame.dict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class BasicDictionary {
	private Vector<String> list = new Vector<String>(); // 목록
	private HashMap<String, String> render = new HashMap<String, String>(); // 번역
	private HashMap<String, Integer> success = new HashMap<String, Integer>(); // 성공횟수

	public void MAKE_UserDictionary(String user) {
		try {
			readWordBasicDictionary();
			writeWordUserDictionary(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 파일에서 단어를 입력시킴
	public void readWordBasicDictionary() throws IOException {

		// readline 읽을 String변수
		String line = "";

		// 파일 데이터 저장 스트림

		BufferedReader in = new BufferedReader(new FileReader("resources/BasicDictionary.txt"));

		// 데이터 잘라줄 객체
		String[] spliter;

		String korean;
		String english;
		String successcount;

		// 파일 라인별로 읽음
		while ((line = in.readLine()) != null) {
			// 읽어온 라인데이터를 "탭"단위로 자름
			spliter = line.split("\t");

			korean = spliter[0];
			english = spliter[1];
			successcount = spliter[2];
			this.add(korean, english, Integer.parseInt(successcount));
		}

		// 스트림 종료
		in.close();
	}

	public void writeWordUserDictionary(String user) throws IOException {

		String korean;
		String english;
		String successcount;

		BufferedWriter out = new BufferedWriter(new FileWriter("resources/UserDictionary/" + user + "_Dictionary.txt"));
		String s;
		
		for (int i = 0; i < list.size(); i++) {
			korean = list.get(i);
			english = render.get(korean);
			successcount = (success.get(korean)).toString();

			s = korean + "\t" + english + "\t" + successcount;
			out.write(s);
			out.newLine();
		}
		out.close();

	}

	// 단어를 입력
	public void add(String korean, String english, Integer successcount) {
		list.add(korean);
		render.put(korean, english);
		success.put(korean, successcount);

	}

}
