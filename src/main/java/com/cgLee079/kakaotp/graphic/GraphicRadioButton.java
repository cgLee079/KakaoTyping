package com.cgLee079.kakaotp.graphic;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class GraphicRadioButton extends JRadioButton {
	private String path;
	private String id;

	public String getPath() {
		return path;
	}

	public String getId() {
		return id;
	}

	public GraphicRadioButton(String path, String filename, int width, int height) {
		this.path = path;
		this.id = filename;

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