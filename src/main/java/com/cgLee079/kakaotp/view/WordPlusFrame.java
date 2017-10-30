package com.cgLee079.kakaotp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class WordPlusFrame extends JFrame {
	private KoreaInputPanel koInputPanel;
	private EnglishInputPanel enInputPanel;
	private JButton plusBtn;
	private JLabel topLabel;
	private JTextArea koTextArea;
	private JTextArea enTextArea;

	public WordPlusFrame() {
		setSize(400, 400);
		setLayout(new BorderLayout());
		setVisible(true);
		setBackground(new Color(240, 240, 255));
		setTitle("Word Plus");
		setResizable(false);

		koInputPanel = new KoreaInputPanel();
		enInputPanel = new EnglishInputPanel();
		plusBtn = new JButton("PLUS");
		topLabel = new JLabel("Input Your Word");
		topLabel.setFont(new Font("고딕", Font.BOLD, 25));
		add(topLabel, BorderLayout.NORTH);
		add(koInputPanel, BorderLayout.WEST);
		add(enInputPanel, BorderLayout.CENTER);
		add(plusBtn, BorderLayout.SOUTH);
	}

	class KoreaInputPanel extends JPanel {
		KoreaInputPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(200, 300));
			Border border = BorderFactory.createEtchedBorder();
			border = BorderFactory.createTitledBorder("Korean");
			setBorder(border);

			koTextArea = new JTextArea();

			add(koTextArea, BorderLayout.CENTER);
		}
	}

	class EnglishInputPanel extends JPanel {
		EnglishInputPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			Border border = BorderFactory.createEtchedBorder();
			border = BorderFactory.createTitledBorder("English");
			setBorder(border);

			enTextArea = new JTextArea();
			add(enTextArea, BorderLayout.CENTER);
		}
	}
}
