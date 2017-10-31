package com.cgLee079.kakaotp.model;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GameFontP;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;

public class FwLabel extends JLabel {
	// 라벨의 Item 보유
	private boolean itemHave = false;

	// 라벨의 유효성
	private boolean valid = true;

	// 라벨의 언어
	private boolean lenguage = true;

	public FwLabel(String text) {
		super(text);
		this.setSize(120, 40);

		if (Math.random() < 0.2) { // Item랜덤하게 생성
			itemHave = true;
		}

		if (itemHave){
			setHaveItem_k(); // Item을 가지고 있을때의 폰트
		} else{
			setKorean(); // 한글 폰트
		}
	}

	// Item 리턴
	public boolean getHaveItem() {
		return itemHave;
	}

	// Item 가지고 있을때 한글 폰트
	public void setHaveItem_k() {
		String path = GlobalGraphic.path + "CenterPanel/";
		this.setIcon(new ImageIcon(path + "itemIcon" + ".png"));
		this.setFont(new GameFontB(18));
	}

	// Item 가지고 있을 때 영어 폰트
	public void setHaveItem_e() {
		String path = GlobalGraphic.path + "CenterPanel/";
		this.setIcon(new ImageIcon(path + "itemIcon" + ".png"));
		this.setFont(new GameFontB(18));
		this.setForeground(Color.blue);
	}

	// 한글 폰트
	public void setKorean() {
		this.setFont(new GameFontP(15));
	}

	// 영문 폰트
	public void setEnglish() {
		this.setFont(new GameFontP(15));
		this.setForeground(Color.blue);// 영단어 입력시 글자색 변경
	}

	// 유효성 리턴
	public boolean getValid() {
		return valid;
	}

	// 유효성 설정
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean getLanguage() {
		return lenguage;
	}

	public void setLanguage(boolean lenguage) {
		this.lenguage = lenguage;
	}

}