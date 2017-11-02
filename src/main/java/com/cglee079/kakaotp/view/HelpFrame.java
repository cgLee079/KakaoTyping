package com.cglee079.kakaotp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cglee079.kakaotp.cswing.GraphicButton;
import com.cglee079.kakaotp.util.ColorManager;
import com.cglee079.kakaotp.util.MainPosition;
import com.cglee079.kakaotp.util.PathManager;

public class HelpFrame extends JFrame {
	private final String PATH = PathManager.HELP_FRAME;
	private final String PREFIX = "help";
	private final int NUM_OF_PAGE = 7;
	private int page;
	
	private ImageIcon[] image = new ImageIcon[NUM_OF_PAGE];
	private JLabel helpLabel;
	

	

	public HelpFrame() {
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(ColorManager.BASIC2);
		setVisible(true);
		setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 50, 50));
		setLocation(MainPosition.x - (this.getWidth() / 2), MainPosition.y - (this.getHeight() / 2));

		for (Integer page = 0; page < NUM_OF_PAGE; page++)
			image[page] = new ImageIcon(PATH + PREFIX + page + ".png");

		page = 0;
		helpLabel = new JLabel(image[page]);

		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setPreferredSize(new Dimension(750, 70));

		add(helpLabel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		revalidate();
	}

	class ButtonPanel extends JPanel {
		private GraphicButton leftBtn;
		private GraphicButton rightBtn;
		private GraphicButton confirmBtn;

		private ButtonPanel() {

			this.setBackground(null);
			leftBtn = new GraphicButton(PATH, "leftBtn", 70, 40);
			leftBtn.addActionListener(new ButtonActionListener());
			leftBtn.setEnabled(false);
			add(leftBtn);

			rightBtn = new GraphicButton(PATH, "rightBtn", 70, 40);
			rightBtn.addActionListener(new ButtonActionListener());
			add(rightBtn);

			confirmBtn = new GraphicButton(PATH, "confirmBtn", 100, 35);
			confirmBtn.addActionListener(new ButtonActionListener());
			add(confirmBtn);

		}

		class ButtonActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton) e.getSource();
				
				switch (btn.getId()) {
				case "leftBtn":
					if (page == NUM_OF_PAGE - 1) {
						rightBtn.setEnabled(true);
					}
					page--;
					helpLabel.setIcon(image[page]);
					if (page == 0) {
						leftBtn.setEnabled(false);
					}
					ButtonPanel.this.repaint();
					break;

				case "rightBtn":
					if (page == 0) {
						leftBtn.setEnabled(true);
					}
					page++;
					helpLabel.setIcon(image[page]);
					if (page == NUM_OF_PAGE - 1) {
						rightBtn.setEnabled(false);
					}
					ButtonPanel.this.repaint();
					break;

				case "confirmBtn":
					HelpFrame.this.dispose();
					break;
				}
			}
		}
	}
}
