package com.cglee079.kakaotp.graphic;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GraphicButton extends JButton {
	String path;
	String id;

	public String getPath() {
		return path;
	}

	public String getId() {
		return id;
	}

	public GraphicButton(String path, String filename, int width, int height) {
		this.path = path;
		this.id = filename;

		setOpaque(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setSize(width, height);

		setIcon(new ImageIcon(path + filename + ".png"));
		setRolloverIcon(new ImageIcon(path + filename + "_enter" + ".png"));
	}

}