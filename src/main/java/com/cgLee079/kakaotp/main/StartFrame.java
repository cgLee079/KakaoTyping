package com.cgLee079.kakaotp.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicButton;
import com.cgLee079.kakaotp.graphic.GraphicPanel;
import com.cgLee079.kakaotp.graphic.GraphicRadioButton;
import com.cgLee079.kakaotp.io.UserManager;

import PlayPanel.PlayPanel;

public class StartFrame extends JFrame {
	private Window f = this;
	private UserListPanel userListPanel;
	private LevelListPanel levelListPanel;
	private SubmitPanel submitPanel;

	public StartFrame() throws IOException {
		setSize(400, 250);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);// 크기 고정
		setUndecorated(true);
		setVisible(true);
		setContentPane(new StartPanel());
		getContentPane().setBackground(GlobalGraphic.basic2);
		
		this.setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 100, 100));
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

	}

	class StartPanel extends JPanel {
		StartPanel() throws IOException {
			setLayout(null);
			String path = "images/StartFrame/";
			userListPanel = new UserListPanel(path, "UserListPa", 300, 40);
			userListPanel.setLocation(40, 50);
			add(userListPanel);

			levelListPanel = new LevelListPanel();
			levelListPanel.setSize(400, 70);
			levelListPanel.setLocation(0, 110);

			submitPanel = new SubmitPanel(userListPanel, levelListPanel);
			submitPanel.setSize(400, 50);
			submitPanel.setLocation(50, 180);

			add(levelListPanel, BorderLayout.CENTER);
			add(submitPanel, BorderLayout.SOUTH);
		}
	}

	class LevelListPanel extends JPanel {
		String level;
		GraphicRadioButton levelbtn[];
		ButtonGroup levelBtnGroup;

		LevelListPanel() {
			setBackground(null);
			makeBtn();
		}

		void makeBtn() {
			levelBtnGroup = new ButtonGroup();
			levelbtn = new GraphicRadioButton[3];
			levelbtn[0] = new GraphicRadioButton("images/StartFrame/", "levelBtn1", 70, 35);
			levelbtn[1] = new GraphicRadioButton("images/StartFrame/", "levelBtn2", 70, 35);
			levelbtn[2] = new GraphicRadioButton("images/StartFrame/", "levelBtn3", 70, 35);

			for (int i = 0; i < 3; i++) {
				levelBtnGroup.add(levelbtn[i]);
				add(levelbtn[i]);
			}
		}
		
		public String getLevelSelect(){
			String levelbtn = null;
			Enumeration<AbstractButton> enums = levelBtnGroup.getElements();
			while (enums.hasMoreElements()) {
				GraphicRadioButton radiobtn = (GraphicRadioButton) enums.nextElement();
				if (radiobtn.isSelected()) {
					levelbtn = radiobtn.getId();
				}
			}
			
			return levelbtn;
		}
		
	}

	class SubmitPanel extends JPanel {
		UserListPanel userListPanel;
		LevelListPanel levelListPanel;
		String username;
		String character;
		GraphicButton[] submitBtn;

		SubmitPanel() {
			setLayout(null);
			setBackground(null);
			makeBtn();
		}
		
		public SubmitPanel(UserListPanel userListPanel, LevelListPanel levelListPanel){
			this();
			this.userListPanel = userListPanel;
			this.levelListPanel = levelListPanel;
		}

		void makeBtn() {
			String path = "images/StartFrame/";
			submitBtn = new GraphicButton[2];
			submitBtn[0] = new GraphicButton(path, "SubmitBtn", 100, 35);
			submitBtn[1] = new GraphicButton(path, "ConcealBtn", 100, 35);

			for (int i = 0; i < 2; i++) {
				submitBtn[i].addActionListener(new SubmitAction());
			}

			for (int i = 0; i < 2; i++) {
				submitBtn[i].setLocation(40 + (i * 120), 0);
				add(submitBtn[i]);
			}
		}

		class SubmitAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton) e.getSource();
				if (btn.getId() == "SubmitBtn") {

					String[] spliter;

					String user = SubmitPanel.this.userListPanel.getSelectedItem();
					if (user == null) {
						JOptionPane.showMessageDialog(null, "유저를 선택해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					spliter = user.split("\t");
					character = spliter[0];
					username = spliter[1];

					if (character.equals("MUZI.")) {
						GlobalGraphic.baseColor = new Color(251, 233, 13);
						GlobalGraphic.path = "images/MainFrame/Muzi/";
					} else if (character.equals("LYAN.")) {
						GlobalGraphic.baseColor = new Color(215, 209, 137);
						GlobalGraphic.path = "images/MainFrame/Lyan/";
					} else if (character.equals("APEACH.")) {
						GlobalGraphic.baseColor = new Color(247, 171, 171);
						GlobalGraphic.path = "images/MainFrame/Apeach/";
					}

					String levelID =  SubmitPanel.this.levelListPanel.getLevelSelect();
					int level = 0;
					double speed = 0;
					
					if (levelID == null) {
						JOptionPane.showMessageDialog(null, "레벨을 선택해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (levelID.equals("levelBtn1")) {
						level = 1;
						speed = 10;
					} else if (levelID.equals("levelBtn1")) {
						level = 5;
						speed = 20;
					} else if (levelID.equals("levelBtn3")) {
						level = 10;
						speed = 30;
					}

					MainFrame.mf.setContentPane(MainFrame.mf.playPanel = new PlayPanel(username, character, level, speed));
				}
				JFrame topFrame = (JFrame) (SubmitPanel.this.getTopLevelAncestor());
				topFrame.dispose();

			}
		}
	}

	class UserListPanel extends GraphicPanel {
		JComboBox<String> userComboBox;

		UserListPanel(String path, String FILENAME, int width, int height) throws IOException {
			super(path, FILENAME, width, height);
			setLayout(null);
			this.setBackground(null);
			userComboBox = new JComboBox<String>();
			userComboBox.setSize(150, 20);
			userComboBox.setLocation(70, 10);
			userComboBox.setBackground(Color.WHITE);
			userComboBox.setSelectedIndex(-1);
			userComboBox.setSelectedItem(-1);

			updateUser();

			this.add(userComboBox);

			String btnPath = "images/StartFrame/";
			GraphicButton wordPlusBtn = new GraphicButton(btnPath, "UserPlusBtn", 30, 30);
			wordPlusBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					new MakeUserFrame(UserListPanel.this);
				}
			});
			
			wordPlusBtn.setLocation(240, 5);

			this.add(wordPlusBtn);
		}

		public void updateUser(){
			userComboBox.removeAllItems();
			userComboBox.addItem(null);
			
			UserManager userManager = UserManager.getInstance();
			ArrayList<String> users = userManager.readUser();
			
			int size = users.size();
			for(int i = 0; i < size ; i++){
				userComboBox.addItem(users.get(i));
			}
		}

		public String getSelectedItem() {
			return (String) userComboBox.getSelectedItem();
		}
	}

}
