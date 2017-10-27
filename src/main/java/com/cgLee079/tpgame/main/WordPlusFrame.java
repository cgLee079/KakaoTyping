package com.cgLee079.tpgame.main;

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
	private KoreaInputPanel pKoInput;
	private EnglishInputPanel pEnInput;
	private JButton btnPlus;
	private JLabel laTop;
	private JTextArea taKoInput;
	private JTextArea taEnInput;

	public WordPlusFrame() {
		setSize(400, 400);
		setLayout(new BorderLayout());
		setVisible(true);
		setBackground(new Color(240, 240, 255));
		setTitle("Word Plus");
		setResizable(false);

		pKoInput = new KoreaInputPanel();
		pEnInput = new EnglishInputPanel();
		btnPlus = new JButton("PLUS");
		laTop = new JLabel("Input Your Word");
		laTop.setFont(new Font("고딕", Font.BOLD, 25));
		add(laTop, BorderLayout.NORTH);
		add(pKoInput, BorderLayout.WEST);
		add(pEnInput, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
	}

	class KoreaInputPanel extends JPanel {
		KoreaInputPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(200, 300));
			Border border = BorderFactory.createEtchedBorder();
			border = BorderFactory.createTitledBorder("Korean");
			setBorder(border);

			taKoInput = new JTextArea();

			add(taKoInput, BorderLayout.CENTER);
		}
	}

	class EnglishInputPanel extends JPanel {
		EnglishInputPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			Border border = BorderFactory.createEtchedBorder();
			border = BorderFactory.createTitledBorder("English");
			setBorder(border);

			taEnInput = new JTextArea();
			add(taEnInput, BorderLayout.CENTER);
		}
	}
}
