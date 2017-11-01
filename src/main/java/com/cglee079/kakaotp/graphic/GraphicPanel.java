package com.cglee079.kakaotp.graphic;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraphicPanel extends JPanel {
	ImageIcon imgIcon;

	public GraphicPanel(String path, String filename, int width, int height) {
		imgIcon = new ImageIcon(path + filename + ".png");
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imgIcon.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}