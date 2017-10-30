package com.cgLee079.kakaotp.play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.cgLee079.kakaotp.view.PauseFrame;
import com.cgLee079.kakaotp.view.PlayPanel;

public class KeyEventor extends KeyAdapter {
	PlayPanel playPanel;
	Play play;
	PauseFrame pauseFrame;
	
	public KeyEventor(PlayPanel playPanel, Play play){
		this.playPanel 	= playPanel;
		this.play 		= play;
	}
	
	public void keyPressed(KeyEvent e) {
		JTextField wordTextField = (JTextField) e.getSource();
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_ESCAPE:
			play.pauseGame();
			pauseFrame = new PauseFrame();
			break;

		case KeyEvent.VK_F1: // item1 모두 지우기
			play.useItem(0);
			break;

		case KeyEvent.VK_F2: // item2
			play.useItem(1);
			break;

		case KeyEvent.VK_F3: // item3
			play.useItem(2);
			break;

		case KeyEvent.VK_F4: // item4
			play.useItem(3);
			break;

		case KeyEvent.VK_ENTER: // Enter 입력시 단어 비교,
			String text = wordTextField.getText(); // TextField에서 입력값 받아옴
			play.checkFallingWord(text); // 단어 비교
			wordTextField.setText("");// textField 클리어
		}
	}
}
