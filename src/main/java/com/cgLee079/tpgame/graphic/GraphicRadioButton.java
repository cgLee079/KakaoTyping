package com.cgLee079.tpgame.graphic;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class GraphicRadioButton extends JRadioButton {
	private String path;
	private String FILENAME;

	public String getPath() {
		return path;
	}

	public String getFILENAME() {
		return FILENAME;
	}

	public GraphicRadioButton(String path, String filename, int width, int height) {
		this.path = path;
		this.FILENAME = filename;

		setOpaque(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setSize(width, height);

		setIcon(new ImageIcon(path + filename + ".png"));
		setSelectedIcon(new ImageIcon(path + filename + "_select" + ".png"));
		setRolloverIcon(new ImageIcon(path + filename + "_enter" + ".png"));
	}
}