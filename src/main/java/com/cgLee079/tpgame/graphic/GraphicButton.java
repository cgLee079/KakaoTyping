package com.cgLee079.tpgame.graphic;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GraphicButton extends JButton {
	String path;
	String FILENAME;

	public String getPath() {
		return path;
	}

	public String getFILENAME() {
		return FILENAME;
	}

	public GraphicButton(String path, String filename, int width, int height) {
		this.path = path;
		this.FILENAME = filename;

		setOpaque(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		this.setSize(width, height);

		setIcon(new ImageIcon(path + filename + ".png"));
		setRolloverIcon(new ImageIcon(path + filename + "_enter" + ".png"));
	}

}