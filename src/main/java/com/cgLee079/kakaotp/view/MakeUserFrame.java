package com.cgLee079.kakaotp.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicButton;
import com.cgLee079.kakaotp.graphic.GraphicRadioButton;
import com.cgLee079.kakaotp.io.UserManager;
import com.cgLee079.kakaotp.view.StartFrame.UserListPanel;

public class MakeUserFrame extends JFrame {
	UserListPanel userListPanel;
	ChoiceCharacterPanel choiceChracterPanel;
	UserInputPanel userInputPanel;
	SubmitPanel submitPanel;

	public MakeUserFrame() {
		setSize(400, 320);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);// 크기 고정
		setUndecorated(true);
		this.getContentPane().setBackground(GlobalGraphic.basic2);
		setVisible(true);

		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		this.setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 100, 100));
		setLayout(null);

		choiceChracterPanel = new ChoiceCharacterPanel();
		choiceChracterPanel.setLocation(0, 50);
		choiceChracterPanel.setSize(400, 150);

		userInputPanel = new UserInputPanel();
		userInputPanel.setLocation(0, 200);
		userInputPanel.setSize(400, 50);

		submitPanel = new SubmitPanel();
		submitPanel.setLocation(0, 250);
		submitPanel.setSize(400, 50);

		add(choiceChracterPanel);
		add(userInputPanel);
		add(submitPanel);
	}
	
	public MakeUserFrame(UserListPanel userListPanel){
		this();
		this.userListPanel = userListPanel;
	}

	class ChoiceCharacterPanel extends JPanel {
		JLabel choiceLabel;
		GraphicRadioButton[] choiceBtn;
		ButtonGroup chracterBtnGroup;

		private ChoiceCharacterPanel() {
			setSize(400, 250);
			setBackground(null);

			makeBtn();
		}

		private void makeBtn() {
			chracterBtnGroup = new ButtonGroup();

			String path = "images/MakeUserFrame/";
			choiceBtn = new GraphicRadioButton[3];
			choiceBtn[0] = new GraphicRadioButton(path, "MuziBtn", 100, 100);
			choiceBtn[1] = new GraphicRadioButton(path, "LyanBtn", 100, 100);
			choiceBtn[2] = new GraphicRadioButton(path, "ApeachBtn", 100, 100);

			for (int i = 0; i < 3; i++) {
				choiceBtn[i].addActionListener(new ChoiceActionListener());
			}

			for (int i = 0; i < 3; i++) {
				chracterBtnGroup.add(choiceBtn[i]);
				add(choiceBtn[i]);
			}
		}
		
		public String getCharaterSelect(){
			String charaterID = null;
			
			Enumeration<AbstractButton> enums = this.chracterBtnGroup.getElements();
			while (enums.hasMoreElements()) {
				GraphicRadioButton radiobtn = (GraphicRadioButton) enums.nextElement();
				if (radiobtn.isSelected()){
					charaterID = radiobtn.getId();
				}
			}
			
			return charaterID;
		}

		class ChoiceActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicRadioButton btn = (GraphicRadioButton) e.getSource();

			}
		}
	}

	class UserInputPanel extends JPanel {
		JTextField userTextField;

		private UserInputPanel() {
			setLayout(new FlowLayout());
			setBackground(null);
			userTextField = new JTextField("", 15);
			add(userTextField);
		}
	}
	
	class SubmitPanel extends JPanel {
		GraphicButton[] submitBtn;

		private SubmitPanel() {
			setLayout(null);
			setBackground(null);
			makeBtn();

			for (int i = 0; i < 2; i++) {
				submitBtn[i].setLocation(90 + (i * 120), 0);
				add(submitBtn[i]);
			}
		}

		private void makeBtn() {
			String path = "images/MakeUserFrame/";
			submitBtn = new GraphicButton[2];
			submitBtn[0] = new GraphicButton(path, "SubmitBtn", 100, 35);
			submitBtn[1] = new GraphicButton(path, "ConcealBtn", 100, 35);

			for (int i = 0; i < 2; i++) {
				submitBtn[i].addActionListener(new SubmitActionListener());
			}
		}

		class SubmitActionListener implements ActionListener {
			String character;

			public void actionPerformed(ActionEvent e) {

				GraphicButton btn = (GraphicButton) e.getSource();
				if (btn.getId().equals("SubmitBtn")) {

					String characterID = null;
					characterID = choiceChracterPanel.getCharaterSelect();
					if (characterID == null) {
						JOptionPane.showMessageDialog(null, "캐릭터를 선택해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (characterID.equals("MuziBtn")){
						character = "MUZI";
					} else if (characterID.equals("LyanBtn")){
						character = "LYAN";
					} else if (characterID.equals("ApeachBtn")){
						character = "APEACH";
					}

					String username = userInputPanel.userTextField.getText();
					if (username.equals("")) {
						JOptionPane.showMessageDialog(null, "이름을 입력해주세요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}

					UserManager userManager = UserManager.getInstance();
					userManager.writeUser(character, username);					
					userListPanel.updateUser();
				}

				JFrame topFrame = (JFrame) (SubmitPanel.this.getTopLevelAncestor());
				topFrame.dispose();
			}

			public void makeUserDictinary() throws IOException {

			}

		}

	}

}
