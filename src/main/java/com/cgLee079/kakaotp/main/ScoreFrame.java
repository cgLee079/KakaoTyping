package com.cgLee079.kakaotp.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GameFontP;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicPanel;
import com.cgLee079.kakaotp.model.UserInfo;
import com.cgLee079.kakaotp.util.FileIO;

import PlayPanel.PlayPanel;

public class ScoreFrame extends JFrame {
	private FileIO fIO;
	public PlayPanel p;

	public ScoreFrame(PlayPanel p) {

		setSize(800, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBackground(com.cgLee079.kakaotp.graphic.GlobalGraphic.basic);

		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		this.p = p;
		fIO = new FileIO(p);

		String npPath = "images/ScoreFrame/NorthPanel/";
		NorthPanel north = new NorthPanel(npPath, "Background", 800, 60);
		CenterPanel center = new CenterPanel(p);

		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);

	}

	class NorthPanel extends GraphicPanel {
		NorthPanel(String path, String filename, int width, int height) {
			super(path, filename, width, height);
		}
	}

	class CenterPanel extends JPanel {
		String path = "images/ScoreFrame/CenterPanel/";
		public PlayPanel p;
		UserInfo myuser = fIO.getMyUser();

		CenterPanel(PlayPanel p) {
			this.p = p;
			setVisible(true);

			setBackground(GlobalGraphic.character);

			setLayout(null);
			setPreferredSize(new Dimension(500, 420));

			GradePanel gp = new GradePanel(path, "frame", 390, 410, this);
			MyGradePanel mgp = new MyGradePanel(path, "myScore", 320, 290, this);
			add(gp);
			add(mgp);

			String myCharacter = myuser.getCharacter(); // 캐릭터 이미지
			ImageIcon myChimage = new ImageIcon(path + myCharacter + "스코어.gif");
			JLabel myChLabel = new JLabel(myChimage);
			myChLabel.setLocation(630, 300);
			myChLabel.setSize(130, 130);

			add(myChLabel);
		}

		class GradePanel extends GraphicPanel {

			int num = 4;// 전체 화면에 표시할 등수 표시 갯수

			public GradePanel(String path, String FILENAME, int width, int height, CenterPanel p) {
				super(path, FILENAME, width, height);

				setVisible(true);
				setBackground(Color.white);
				setLayout(null);
				setGrade();
				setLocation(30, 30);
			}

			void setGrade() {

				ImageIcon images[] = new ImageIcon[num];
				ImageIcon gradeImg[] = new ImageIcon[num];

				JLabel faceLabel[] = new JLabel[num];
				JLabel scores[] = new JLabel[num];
				JLabel gradeLabel[] = new JLabel[num];
				JLabel nameLabel[] = new JLabel[num];

				String name = " ";// 이름 저장
				String faceType = " ";// 캐릭터 타입 저장

				for (int i = 0; i < num; i++) {

					gradeImg[i] = new ImageIcon(path + "트로피.png");
					gradeLabel[i] = new JLabel(gradeImg[i]);

					name = fIO.getUsers().get(i).getName();

					faceType = fIO.getUsers().get(i).getCharacter();
					images[i] = new ImageIcon(path + faceType + "Face.png");// faceType
																			// 별로
																			// 이모티콘
																			// 분류
					faceLabel[i] = new JLabel(images[i]);

					System.out.println(faceType);

					scores[i] = new JLabel(fIO.getUsers().get(i).getScore().toString());
					scores[i].setSize(100, 100);
					scores[i].setLocation(300, i * 100);
					scores[i].setFont(new GameFontB(15));

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
					add(scores[i]);
					add(nameLabel[i]);
				}
			}
		}

		class MyGradePanel extends GraphicPanel {

			MyGradePanel(String path, String FILENAME, int width, int height, CenterPanel p) {
				super(path, FILENAME, width, height);

				setVisible(true);
				setBackground(Color.white);
				setLayout(new GridLayout(3, 1));
				setLocation(450, 30);
				setMyGrade();

			}

			public void setMyGrade() {

				Integer myScore = myuser.getScore();
				String myName = myuser.getName();
				Integer myLevel = p.play.getLevel();

				JLabel myScoreLabel = new JLabel(myScore.toString());
				setGradeLabel(myScoreLabel, 250, 60, 50);

				JLabel myNameLabel = new JLabel("userName : " + myName);
				setGradeLabel(myNameLabel, 150, 50, 20);

				JLabel myLevelLabel = new JLabel("userlevel : " + myLevel);
				setGradeLabel(myLevelLabel, 100, 50, 20);

			}

			public void setGradeLabel(JLabel source, int x, int y, int fontSize) {
				source.setSize(x, y);
				source.setForeground(GlobalGraphic.character);
				source.setHorizontalAlignment(JLabel.CENTER);
				source.setVerticalAlignment(JLabel.CENTER);
				source.setFont(new GameFontP(fontSize));
				add(source);
			}
		}// MyGradePanel end

	}

}
