package com.cglee079.kakaotp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.cglee079.kakaotp.cswing.GraphicButton;
import com.cglee079.kakaotp.cswing.GraphicPanel;
import com.cglee079.kakaotp.dict.UserDictionary;
import com.cglee079.kakaotp.font.GameFontP;
import com.cglee079.kakaotp.model.User;
import com.cglee079.kakaotp.util.ColorManager;
import com.cglee079.kakaotp.util.MainPosition;
import com.cglee079.kakaotp.util.PathManager;

public class WordPlusFrame extends JFrame {
	private final String PATH = PathManager.WORDPLUS_FRAME;
	private InputPanel inputPanel;
	private WordSetFrame wordSetFrame;
	private User user;
	private JTextArea koreanInput;
	private JTextArea englishInput;

	public WordPlusFrame(WordSetFrame wordListTable, User user) {
		this.user = user;
		this.wordSetFrame = wordListTable;

		setSize(400, 350);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
		setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 100, 100));
		setLocation(MainPosition.x - (this.getWidth() / 2), MainPosition.y - (this.getHeight() / 2));
		getContentPane().setBackground(ColorManager.BASIC3);

		GraphicPanel inputWordPanel = new GraphicPanel(PATH, "inputYourWord", 400, 70);
		add(inputWordPanel, BorderLayout.NORTH);

		inputPanel = new InputPanel();
		inputPanel.setPreferredSize(new Dimension(400, 210));
		add(inputPanel, BorderLayout.CENTER);

		SubmitPanel submitPanel = new SubmitPanel();
		submitPanel.setPreferredSize(new Dimension(400, 70));
		add(submitPanel, BorderLayout.SOUTH);

	}

	class InputPanel extends JPanel {
		InputPanel() {
			setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
			setBackground(null);

			koreanInput = new JTextArea();
			koreanInput.setPreferredSize(new Dimension(150, 200));
			koreanInput.setText("한글");
			koreanInput.setFont(new GameFontP(15));

			englishInput = new JTextArea();
			englishInput.setPreferredSize(new Dimension(150, 200));
			englishInput.setText("ENGLISH");
			englishInput.setFont(new GameFontP(15));

			add(koreanInput);
			add(englishInput);

		}
	}

	class SubmitPanel extends JPanel {
		private GraphicButton submitBtn;
		private GraphicButton concealBtn;

		private SubmitPanel() {
			setBackground(null);

			submitBtn = new GraphicButton(PATH, "SubmitBtn", 100, 35);
			submitBtn.addActionListener(new SubmitAction());

			concealBtn = new GraphicButton(PATH, "ConcealBtn", 100, 35);
			concealBtn.addActionListener(new SubmitAction());

			add(submitBtn);
			add(concealBtn);
			
		}

		class SubmitAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton) e.getSource();
				
				switch(btn.getId()) {
				case "SubmitBtn" :
					String korean[] = splite(koreanInput.getText());
					String english[] = splite(englishInput.getText());

					if (korean.length != english.length) {
						JOptionPane.showMessageDialog(WordPlusFrame.this, "단어 갯수가 달라요", "경고!", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String username = user.getUsername();
					UserDictionary userDictionary = new UserDictionary(username);
					for (int i = 0; i < korean.length; i++){
						userDictionary.add(korean[i], english[i], 0);
					}

					userDictionary.writeWordUserDictionary();
					
					wordSetFrame.loadDictionary(username);

					JOptionPane.showMessageDialog(WordPlusFrame.this, "단어가 추가되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
					break;
					
				case "ConcealBtn":
					break;
				}

				WordPlusFrame.this.dispose();
			}

			public String[] splite(String text) {
				String[] spliter;
				spliter = text.split("\n");
				return spliter;
			}
		}

	}
}
