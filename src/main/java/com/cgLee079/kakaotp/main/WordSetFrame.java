package com.cgLee079.kakaotp.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cgLee079.kakaotp.dict.UserDictionary;
import com.cgLee079.kakaotp.graphic.GameFontP;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicButton;
import com.cgLee079.kakaotp.graphic.GraphicPanel;

//AllWordList 프레임
public class WordSetFrame extends JFrame {
	WordListPanel wordListPanel;
	UserListPanel userListPanel;
	SetButtonPanel setButtonPanel;
	SubmitButtonPanel submitButtonPanel;

	public WordSetFrame() {
		setLayout(null);
		setSize(600, 350);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);// 크기 고정
		setUndecorated(true);
		getContentPane().setBackground(GlobalGraphic.basic2);
		setVisible(true);
		this.setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 100, 100));
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		wordListPanel = new WordListPanel();
		wordListPanel.setLocation(30, 20);
		wordListPanel.setSize(250, 305);

		userListPanel = new UserListPanel("images/WordSetFrame/", "UserListPanel", 250, 40);
		userListPanel.setLocation(300, 40);

		setButtonPanel = new SetButtonPanel();
		setButtonPanel.setLocation(300, 120);
		setButtonPanel.setSize(250, 150);

		submitButtonPanel = new SubmitButtonPanel();
		submitButtonPanel.setLocation(300, 270);
		submitButtonPanel.setSize(300, 100);

		add(wordListPanel);
		add(userListPanel);
		add(setButtonPanel);
		add(submitButtonPanel);

	}

	class WordListPanel extends JPanel {
		WordListTable wordListTable;

		WordListPanel() {
			setBackground(null);
			JScrollPane scroll = new JScrollPane(wordListTable = new WordListTable());

			scroll.setPreferredSize(new Dimension(230, 300));
			scroll.setAlignmentX(CENTER_ALIGNMENT);

			add(scroll);
		}

		class WordListTable extends JTable {
			DefaultTableModel model;
			String[] header = { "한글", "영어", "성공횟수" };

			WordListTable() {
				this.setFont(new GameFontP(13));
				// Table에 add할수 있도록
				model = new DefaultTableModel(header, 0);
				this.setModel(model);
			}

			// 더블클릭수정불가
			public boolean isCellEditable(int i, int c) {
				return false;
			}

			public void loadDictionary(String user) {
				model = new DefaultTableModel(header, 0);
				this.setModel(model);

				UserDictionary dictionary = new UserDictionary(user);
				// WordList에 모든 단어 add
				for (int index = 0; index < dictionary.getNumOfWord(); index++) {
					String content[] = new String[3];
					content[0] = dictionary.getWord(index);
					content[1] = dictionary.render(content[0]);
					content[2] = dictionary.getSuccess(content[0]).toString();
					model.insertRow(0, content);
				}

			}
		}
	}

	class UserListPanel extends GraphicPanel {
		private JComboBox<String> userComboBox;

		UserListPanel(String path, String FILENAME, int width, int height) {
			super(path, FILENAME, width, height);
			setLayout(null);
			this.setBackground(null);

			userComboBox = new JComboBox<String>();
			userComboBox.setSize(150, 20);
			userComboBox.setLocation(70, 10);
			userComboBox.setBackground(Color.WHITE);

			userComboBox.addItemListener(new UserListItemListener());

			try {
				readUser();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			add(userComboBox);
		}

		public void readUser() throws IOException {
			userComboBox.addItem(null);
			BufferedReader in = new BufferedReader(new FileReader("resources/User.txt"));
			String line = "";
			String[] spliter;

			while ((line = in.readLine()) != null) {
				String item;
				spliter = line.split("\t");
				item = spliter[0] + "." + "\t" + spliter[1];
				userComboBox.addItem(item);
			}

		}

		class UserListItemListener implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem() != null) {
					String userInfo = (String) e.getItem();
					String[] spliter = userInfo.split("\t");
					String user = spliter[1];

					WordSetFrame topFrame = (WordSetFrame) UserListPanel.this.getTopLevelAncestor();
					topFrame.wordListPanel.wordListTable.loadDictionary(user);
				}
			}
		}
	}

	class SetButtonPanel extends JPanel {
		GraphicButton wordPlusBtn;
		GraphicButton successResetBtn;

		SetButtonPanel() {
			setBackground(null);
			makeBtn();
		}

		void makeBtn() {
			wordPlusBtn = new GraphicButton("images/WordSetFrame/", "wordPlusBtn", 100, 40);
			successResetBtn = new GraphicButton("images/WordSetFrame/", "SuccessResetBtn", 100, 40);

			add(wordPlusBtn);
			add(successResetBtn);
		}

	}

	class SubmitButtonPanel extends JPanel {
		GraphicButton wordPlusBtn;
		GraphicButton successResetBtn;

		SubmitButtonPanel() {
			setBackground(null);
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			makeBtn();
		}

		void makeBtn() {
			wordPlusBtn = new GraphicButton("images/WordSetFrame/", "SubmitBtn", 100, 35);
			wordPlusBtn.addActionListener(new SubmitAction());

			successResetBtn = new GraphicButton("images/WordSetFrame/", "ConcealBtn", 100, 35);
			successResetBtn.addActionListener(new SubmitAction());

			add(wordPlusBtn);
			add(successResetBtn);
		}

		class SubmitAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				GraphicButton btn = (GraphicButton) e.getSource();
				if (btn.getId() == "SubmitBtn");
				else if (btn.getId() == "ConcealBtn");

				JFrame topFrame = (JFrame) (SubmitButtonPanel.this.getTopLevelAncestor());
				topFrame.dispose();
			}
		}
	}

}
