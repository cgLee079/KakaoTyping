package com.cgLee079.kakaotp.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicButton;
import com.cgLee079.kakaotp.play.Play;

public class PauseFrame extends JFrame {
	private MainFrame mainFrame;
	private Play play;
	
	public PauseFrame() {
		setLayout(new FlowLayout());
		setSize(150, 180);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);// 크기 고정
		setUndecorated(true);
		setBackground(GlobalGraphic.baseColor);
		setVisible(true);
		setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 50, 50));
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		add(new ButtonPanel());
	}
	
	public PauseFrame(MainFrame mainFrame, Play play){
		this();
		this.play = play;
		this.mainFrame = mainFrame;
	}

	class ButtonPanel extends JPanel {
		private GraphicButton homeBtn;
		private GraphicButton resumeBtn;
		private GraphicButton exitBtn;

		private ButtonPanel() {
			setPreferredSize(new Dimension(200, 200));
			setBackground(null);
			
			homeBtn = new GraphicButton("images/PauseFrame/", "Homebtn", 120, 50);
			homeBtn.addActionListener(new ButtonActionListener());

			resumeBtn = new GraphicButton("images/PauseFrame/", "resumebtn", 120, 50);
			resumeBtn.addActionListener(new ButtonActionListener());

			exitBtn = new GraphicButton("images/PauseFrame/", "exitbtn", 120, 50);
			exitBtn.addActionListener(new ButtonActionListener());

			add(homeBtn);
			add(resumeBtn);
			add(exitBtn);
		}

		class ButtonActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton) e.getSource();
				if (btn.getId().equals("homebtn")){
					mainFrame.drawHome();
					PauseFrame.this.dispose();
				} else if (btn.getId().equals("resumebtn")){
					play.resumeGame();
					PauseFrame.this.dispose();
				} else if (btn.getId().equals("exitbtn")){
					System.exit(0);
				}
			}

		}
	}
}
