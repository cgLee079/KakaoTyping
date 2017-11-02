package com.cglee079.kakaotp.graphic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.cglee079.kakaotp.sound.SoundPlayer;

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
		
		addMouseListener(new BtnMouseListener());
	}
	
	class BtnMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent e){
			SoundPlayer.play("btn_enter");			
		}
		public void mousePressed(MouseEvent e){
			SoundPlayer.play("btn_click");
		}
	}

}