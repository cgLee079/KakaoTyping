package com.cgLee079.kakaotp.play;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import com.cgLee079.kakaotp.dict.UserDictionary;
import com.cgLee079.kakaotp.io.ScoreManager;
import com.cgLee079.kakaotp.main.MainFrame;
import com.cgLee079.kakaotp.main.ScoreFrame;
import com.cgLee079.kakaotp.model.FallingWordLabel;
import com.cgLee079.kakaotp.model.Score;
import com.cgLee079.kakaotp.model.User;

public class Play {
	PlayPanel playPanel;
	private User user;
	private UserDictionary dictionary;
	private boolean isplay;
	private boolean iskorean; // 입력 차례 (한글,영문)
	private int heart;
	private int level;
	private int score;
	private double speed;
	private int count;
	private boolean[] item;

	private Vector<FallingWordLabel> fallingWordLabels;
	private Vector<FallingAni> fallingAnis;
	private WordMaker wordMaker;
	private SpeedUpper speedUpper;

	public Play(User user, Integer level, Double speed) {
		this.user 		= user;
		this.heart		= 100;
		this.level 		= level;
		this.speed 		= speed;
		this.score 		= 0;
		this.isplay 	= true;
		this.iskorean 	= true; // 입력 차례 (한글,영문)
		this.count 		= 10;
		this.item		= new boolean[4];
		this.dictionary	= new UserDictionary(user.getUsername());
		this.fallingWordLabels	= new Vector<FallingWordLabel>();
		this.speedUpper 		= new SpeedUpper();
		this.fallingAnis 		= new Vector<FallingAni>();
		this.wordMaker			= new WordMaker(); // 단어 생성 시작
		
		Arrays.fill(item, false);
	}

	public void setKoreanTurn() {
		this.iskorean = true;
	} // 한글 입력 차례로

	public void setEnglishTurn() {
		this.iskorean = false;
	} // 영문 입력 차례로
	
	public void speedUp(double up) {
		this.speed += up;
	} // 속도 업

	public void levelUp() {
		level++;
		playPanel.drawLevel(level);
		clearFallingWordLabels();
	}

	public void scoreUp() {// 점수 증가
		count--;
		score += level * 5;
		
		playPanel.drawScore(score);
		
		if (count == 0) {
			levelUp();
			count = 10;
		}
	}
	
	public void pain(int value){
		this.heart -= value;
		playPanel.drawPain(value);
		
		if(heart <= 0){
			gameOver();
		}
	}
	
	public void gain(int value){
		this.heart += value;
		playPanel.drawGain(value);
	}

	public void clearFallingWordLabels() { // 떨어지는 단어 모두 삭제
		for (int index = 0; index < fallingWordLabels.size(); index++) {
			fallingWordLabels.get(index).setVisible(false);
			fallingWordLabels.get(index).setValid(false);
		}
		fallingWordLabels.removeAllElements();
	}
	
	public void checkFallingWord(String text) {
		String renderWord = dictionary.render(text); // 번역글자 : 한글 -> 영어 -> null

		if (!this.iskorean && renderWord != null) {// 영어 입력차례에서, 한글을  입력한 경우
			return ;
		}

		int size = fallingWordLabels.size();
		for (int index = 0; index < size; index++) { // 떨어지는  라벨들 중
			FallingWordLabel fwLabel = fallingWordLabels.get(index); // 떨어지는 라벨 
			String fallWord = fallingWordLabels.get(index).getText(); // 떨어지는 라벨의 단어

			if (fallWord.equalsIgnoreCase(text)) { // 떨어지는 단어와 입력 단어가 같을경우
				fwLabel.setText(renderWord); // 한글 -> 영어로, 영어-> null로

				if (fwLabel.getLanguage() == false) {
					InputEnglish(fwLabel);
				} else {
					InputKorean(fwLabel);
				}

				break;
			}
		}
	}

	public void InputEnglish(FallingWordLabel fwLabel) {
		String english = fwLabel.getText();
		fwLabel.setVisible(false);

		// 영어의 한글 값 저장
		String korean = dictionary.renderReverse(english);

		// 성공 단어에 추가
		playPanel.addSuccessWord(korean, english);

		// 단어 성공 횟수 증가
		dictionary.plusSuccess(korean);

		// 무효한 숫자로
		fwLabel.setValid(false);

		// Item확인, 생성
		if (fwLabel.getHaveItem()) {
			Random random = new Random();
			
			int num = random.nextInt(4);// 0-3 아이템 번호 제공
			int n = 0;
			while (item[num]) {
				num = random.nextInt(4);
				n++;
				if (n == 10) {
					break;
				}
			}

			item[num] = true;
			playPanel.drawItemBtn(num, true);
		}

		// 배열에서 제거
		fallingWordLabels.remove(fwLabel);

		// 점수 흭득
		scoreUp();

		// 한글 입력차례로 변환
		setKoreanTurn();
	}

	public void InputKorean(FallingWordLabel fwLabel) {
		fwLabel.setLanguage(false);
		if (fwLabel.getHaveItem()) {
			fwLabel.setHaveItem_e(); // 아이템을 가진 영단어 폰트 셋
		} else {
			fwLabel.setEnglish(); // 아이템 가지지 않은 영단어 폰트 셋
		}

		setEnglishTurn(); // 영어차례로 변환
	}
	
	public void startGame() {
		speedUpper.start();
		wordMaker.start();
	}

	public void stopGame() {
		speedUpper.interrupt();
		wordMaker.interrupt();
		
		for (int i = 0; i < fallingAnis.size(); i++){
			fallingAnis.get(i).interrupt();
		}
			
		new ScoreFrame(playPanel);
	}

	public void pauseGame() {
		this.isplay = false;
		speedUpper.suspend();
		wordMaker.suspend();
		for (int i = 0; i < fallingAnis.size(); i++)
			fallingAnis.get(i).suspend();
	}

	public void resumeGame() {
		this.isplay = true;
		speedUpper.resume();
		wordMaker.resume();
		for (int i = 0; i < fallingAnis.size(); i++)
			fallingAnis.get(i).resume();
	}

	
	public void gameOver() {
		stopGame();
		ScoreManager scoreManager = ScoreManager.getInstance();
		scoreManager.addScore(new Score(user.getCharacter(), user.getUsername(), score));
	}

	class SpeedUpper extends Thread {
		public void run() {
			while (true) {
				speedUp(0.01);
				playPanel.drawSpeed(speed);
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	class WordMaker extends Thread {
		public void run() {
			while (true) {
				FallingAni fallingAni = new FallingAni();
				fallingAnis.add(fallingAni);
				fallingAni.start();

				try {
					sleep(4000);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	// 라벨 하나 하나 떨어지는 쓰레드
	class FallingAni extends Thread {
		public void run() {
			// 좌표값 설정
			int x = (int) (Math.random() * 400);
			int y = 50;

			// 단어를 랜덤하게 받아와 라벨 생성.
			FallingWordLabel fwLabel = new FallingWordLabel(dictionary.rand());
			fwLabel.setLocation(x, y); // 위치 설정
			fallingWordLabels.add(fwLabel); // 떨어지는 라벨 추가
			playPanel.drawFallingWord(fwLabel);

			// y<410까지 떨어트림
			while (y < 410 && !Thread.currentThread().isInterrupted()) {
				y = (int) (y + speed);
				fwLabel.setLocation(x, y);
				try {
					sleep(1000); // 떨어지는 속도
				} catch (InterruptedException e) {
					return;
				}
			}

			if (y >= 410 && fwLabel.getValid() == true) {
				pain(20); // 체력 감소
				fallingWordLabels.remove(fwLabel); // 배열에서 제거

				// 영어 라벨이 다 떨어지면, 한글 차례로
				if (fwLabel.getLanguage() == false){
					iskorean = true;
				}
			}

			fallingAnis.remove(this);

			return;
		}

	}// FallingAni 클래스 끝
}
