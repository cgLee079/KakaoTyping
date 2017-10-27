package com.cgLee079.tpgame.model;

public class UserInfo {
	private String character;
	private String name;
	private Integer score;
	
	UserInfo(){ };
	public UserInfo(String ch,String name,Integer score){
		this.character=ch;
		this.name=name;
		this.score=score;
	}
	
	public String getCharacter(){//캐릭터 타입 리턴
		return character;
	}
	
	public String getName(){//이름 리턴
		return name;
	}
	
	public Integer getScore(){//점수 리턴
		return score;
	}

}
