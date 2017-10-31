package com.cgLee079.kakaotp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import com.cgLee079.kakaotp.model.User;
import com.cgLee079.kakaotp.play.Play;

public class StartFrame extends JFrame {
	private MainFrame mainFrame;
	private UserListPanel userListPanel;
	private LevelListPanel levelListPanel;
	private SubmitPanel submitPanel;

	public StartFrame(){
		setSize(400, 250);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);// 크기 고정
		setUndecorated(true);
		setVisible(true);
		setContentPane(new StartPanel());
		getContentPane().setBackground(GlobalGraphic.basic2);
		
		setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 100, 100));
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

	}
	
	public StartFrame(MainFrame mainFrame){
		this();
		this.mainFrame = mainFrame;
		
		updateUser();
	}
	
	public void updateUser(){
		JComboBox<String>  userComboBox = userListPanel.getUserComboBox();
		
		userComboBox.removeAllItems();
		userComboBox.addItem(null);
		
		UserManager userManager = UserManager.getInstance();
		ArrayList<User> users = userManager.readUser();
		
		User user = null;
		int size = users.size();
		for(int i = 0; i < size ; i++){
			user = users.get(i);
			userComboBox.addItem(user.getUsername() + "\t" + user.getCharacter());
		}
	}
	
	public User getSelectedUser() {
		JComboBox<String>  userComboBox = userListPanel.getUserComboBox();
		
		String str = (String) userComboBox.getSelectedItem();
		if(str == null){
			return null;
		}
		
		String[] spliter = str.split("\t");
		
		String username = spliter[0];
		String character = spliter[1];
		
		return new User(username, character);
	}
	
	public String getSelectedLevel(){
		ButtonGroup levelBtnGroup = levelListPanel.getLevelBtnGroup();
		
		String levelID = null;
		Enumeration<AbstractButton> enums = levelBtnGroup.getElements();
		while (enums.hasMoreElements()) {
			GraphicRadioButton radiobtn = (GraphicRadioButton) enums.nextElement();
			if (radiobtn.isSelected()) {
				levelID = radiobtn.getId();
			}
		}
		
		return levelID;
	}

	class StartPanel extends JPanel {
		private StartPanel(){
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
		private String level;
		private GraphicRadioButton levelbtn[];
		private ButtonGroup levelBtnGroup;

		private LevelListPanel() {
			setBackground(null);
			makeBtn();
		}

		private void makeBtn() {
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
		
		public ButtonGroup getLevelBtnGroup() {
			return levelBtnGroup;
		}

	}

	class UserListPanel extends GraphicPanel {
		private JComboBox<String> userComboBox;

		private UserListPanel(String path, String filename, int width, int height) {
			super(path, filename, width, height);
			setLayout(null);
			setBackground(null);
			
			userComboBox = new JComboBox<String>();
			userComboBox.setSize(150, 20);
			userComboBox.setLocation(70, 10);
			userComboBox.setBackground(Color.WHITE);
			userComboBox.setSelectedIndex(-1);
			userComboBox.setSelectedItem(-1);

			this.add(userComboBox);

			String btnPath = "images/StartFrame/";
			GraphicButton wordPlusBtn = new GraphicButton(btnPath, "UserPlusBtn", 30, 30);
			wordPlusBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					new MakeUserFrame(StartFrame.this);
				}
			});
			
			wordPlusBtn.setLocation(240, 5);
			this.add(wordPlusBtn);
		}

		public JComboBox<String> getUserComboBox() {
			return userComboBox;
		}

	}
	
	class SubmitPanel extends JPanel {
		private UserListPanel userListPanel;
		private LevelListPanel levelListPanel;
		private GraphicButton[] submitBtn;

		private SubmitPanel() {
			setLayout(null);
			setBackground(null);
			
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
		
		private SubmitPanel(UserListPanel userListPanel, LevelListPanel levelListPanel){
			this();
			this.userListPanel = userListPanel;
			this.levelListPanel = levelListPanel;
		}

		class SubmitAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton) e.getSource();
				if (btn.getId() == "SubmitBtn") {
					
					User user = getSelectedUser();
					if (user == null) {
						JOptionPane.showMessageDialog(null, "유저를 선택해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}
						
					String character= user.getCharacter();
					if (character.equals("MUZI")) {
						GlobalGraphic.baseColor = new Color(251, 233, 13);
						GlobalGraphic.path = "images/MainFrame/Muzi/";
					} else if (character.equals("LYAN")) {
						GlobalGraphic.baseColor = new Color(215, 209, 137);
						GlobalGraphic.path = "images/MainFrame/Lyan/";
					} else if (character.equals("APEACH")) {
						GlobalGraphic.baseColor = new Color(247, 171, 171);
						GlobalGraphic.path = "images/MainFrame/Apeach/";
					}

					String levelID = getSelectedLevel();
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
					
					Play play = new Play(user, level, speed);
					mainFrame.setContentPane(new PlayPanel(play));
				}
				
				StartFrame.this.dispose();

			}
		}
	}

}
