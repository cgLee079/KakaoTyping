package com.cgLee079.kakaotp.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GameFontP;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicPanel;
import com.cgLee079.kakaotp.io.ScoreManager;
import com.cgLee079.kakaotp.model.Score;
import com.cgLee079.kakaotp.play.PlayPanel;

public class ScoreFrame extends JFrame {
	private Score score;
	private int level;
	
	public ScoreFrame() {

		setSize(800, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBackground(GlobalGraphic.basic);

		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		String npPath = "images/ScoreFrame/NorthPanel/";
		NorthPanel northPanel = new NorthPanel(npPath, "Background", 800, 60);
		CenterPanel centerPanel = new CenterPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);

	}
	
	public ScoreFrame(Score score, int level){
		this();
		this.score = score;
		this.level = level;
	}

	class NorthPanel extends GraphicPanel {
		NorthPanel(String path, String filename, int width, int height) {
			super(path, filename, width, height);
		}
	}

	class CenterPanel extends JPanel {
		String path = "images/ScoreFrame/CenterPanel/";
		
		private CenterPanel() {
			setVisible(true);
			setBackground(GlobalGraphic.baseColor);

			setLayout(null);
			setPreferredSize(new Dimension(500, 420));

			GradePanel gp = new GradePanel(path, "frame", 390, 410, this);
			MyGradePanel mgp = new MyGradePanel(path, "myScore", 320, 290, this);
			this.add(gp);
			this.add(mgp);

			String myCharacter = score.getCharacter(); // 캐릭터 이미지
			ImageIcon myChimage = new ImageIcon(path + myCharacter + "스코어.gif");
			JLabel myChLabel = new JLabel(myChimage);
			myChLabel.setLocation(630, 300);
			myChLabel.setSize(130, 130);

			this.add(myChLabel);
		}

		class GradePanel extends GraphicPanel {

			int num = 4;// 전체 화면에 표시할 등수 표시 갯수

			public GradePanel(String path, String filename, int width, int height, CenterPanel p) {
				super(path, filename, width, height);

				setVisible(true);
				setBackground(Color.white);
				setLayout(null);
				setLocation(30, 30);
				
				drawGrade();
			}

			private void drawGrade() {
				ScoreManager scoreManager = ScoreManager.getInstance();
				ArrayList<Score> scores = scoreManager.getScores();
				Score score = null;
				
				ImageIcon[] images	 = new ImageIcon[num];
				ImageIcon[] gradeImg = new ImageIcon[num];

				JLabel[] faceLabel 	= new JLabel[num];
				JLabel[] scoreLabel = new JLabel[num];
				JLabel[] gradeLabel = new JLabel[num];
				JLabel[] nameLabel 	= new JLabel[num];

				String name = " ";// 이름 저장
				String faceType = " ";// 캐릭터 타입 저장

				for (int i = 0; i < num; i++) {
					score = scores.get(i);
					
					gradeImg[i] = new ImageIcon(path + "트로피.png");
					gradeLabel[i] = new JLabel(gradeImg[i]);

					name = score.getUsername();

					faceType = score.getCharacter();
					images[i] = new ImageIcon(path + faceType + "Face.png");// faceType 별로 이모티콘  분류
					faceLabel[i] = new JLabel(images[i]);

					scoreLabel[i] = new JLabel(score.getPoint().toString());
					scoreLabel[i].setSize(100, 100);
					scoreLabel[i].setLocation(300, i * 100);
					scoreLabel[i].setFont(new GameFontB(15));

					faceLabel[i].setSize(100, 100);
					faceLabel[i].setLocation(10, i * 100);

					gradeLabel[i].setSize(100, 100);
					gradeLabel[i].setLocation(85, i * 100);

					nameLabel[i] = new JLabel(name);
					nameLabel[i].setSize(100, 100);
					nameLabel[i].setLocation(180, i * 100);
					nameLabel[i].setFont(new GameFontB(15));

					add(faceLabel[i]);
					add(gradeLabel[i]);
					add(scoreLabel[i]);
					add(nameLabel[i]);
				}
			}
		}

		class MyGradePanel extends GraphicPanel {

			MyGradePanel(String path, String filename, int width, int height, CenterPanel p) {
				super(path, filename, width, height);

				setVisible(true);
				setBackground(Color.white);
				setLayout(new GridLayout(3, 1));
				setLocation(450, 30);
				setMyGrade();
			}

			public void setMyGrade() {

				Integer myScore = score.getPoint();
				String myName 	= score.getUsername();
				Integer myLevel = level;

				JLabel myScoreLabel = new JLabel(myScore.toString());
				setGradeLabel(myScoreLabel, 250, 60, 50);

				JLabel myNameLabel = new JLabel("userName : " + myName);
				setGradeLabel(myNameLabel, 150, 50, 20);

				JLabel myLevelLabel = new JLabel("userlevel : " + myLevel);
				setGradeLabel(myLevelLabel, 100, 50, 20);

			}

			public void setGradeLabel(JLabel source, int x, int y, int fontSize) {
				source.setSize(x, y);
				source.setForeground(GlobalGraphic.baseColor);
				source.setHorizontalAlignment(JLabel.CENTER);
				source.setVerticalAlignment(JLabel.CENTER);
				source.setFont(new GameFontP(fontSize));
				add(source);
			}
		}// MyGradePanel end

	}

}
