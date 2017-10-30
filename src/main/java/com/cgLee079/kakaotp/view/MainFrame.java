package com.cgLee079.kakaotp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.cgLee079.kakaotp.graphic.GraphicButton;


public class MainFrame extends JFrame{
	private MainPanel mainPanel;
	
	public MainFrame(){
		setSize(800,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//크기 고정
		setUndecorated(true);
		setVisible(true);		
		this.setShape(new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30));
		
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);
		
		mainPanel = new MainPanel();
		this.setContentPane(mainPanel);
		
		createMenuBar();
	}
	
	private void createMenuBar(){
		JMenuBar menuBar 	= new JMenuBar();
		JMenu fileMenu 		= new JMenu("File");
		JMenu helpMenu 		= new JMenu("Help");
		JMenuItem regame 	= new JMenuItem("Regame");
		JMenuItem exit		= new JMenuItem("exit");
		JMenuItem version	= new JMenuItem("Version");
		JMenuItem developer	= new JMenuItem("Developer");
		
		menuBar.setPreferredSize(new Dimension(800,30));
		//파일 메뉴 생성
		fileMenu.add(regame);
	
		fileMenu.addSeparator();//구분선 추가
		fileMenu.add(exit);
						
		//파일 메뉴 단축키 설정
		regame.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		
		//add Listener
		version.addActionListener(new HelpActionListener());
		developer.addActionListener(new HelpActionListener());
						
		//도움 메뉴 생성
		helpMenu.add(version);
		helpMenu.add(developer);
				
		//메뉴를 메뉴바에 등록
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
				
		//메뉴바 추가
		setJMenuBar(menuBar);
	}
	
	class HelpActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String s = item.getText();
			if(s.equals("Version")){
				JOptionPane.showMessageDialog(null, "version. 1.00\n2016.05.23","Version",JOptionPane.INFORMATION_MESSAGE);
			} else if(s.equals("Developer")) {
				JOptionPane.showMessageDialog(null, "Hansung.Univ\nComputer Engneering\n\nLee Changoo / Seo Songi", "Developer", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	class MainPanel extends JPanel{
		
		MainPanel(){
			setLayout(null);
			MakeButton();
		}
		
		void MakeButton(){
			String path="images/MainFrame/MainPage/";
			
			GraphicButton btn[] = new GraphicButton[4];
			btn[0] = new GraphicButton(path, "Startbtn", 100, 35);
			btn[1] = new GraphicButton(path, "WordSetbtn", 100, 35);
			btn[2] = new GraphicButton(path, "Helpbtn", 100, 35);
			btn[3] = new GraphicButton(path, "Exitbtn", 100, 35);
			
			for(int i=0; i < 4; i++){
				btn[i].addActionListener(new MenuActionListener());
			}
			
			for(int i=0; i < 4; i++){
				btn[i].setLocation(330, 280+(i*40));
				add(btn[i]);
			}		
		}
				
		class MenuActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn = (GraphicButton)e.getSource();

				if(btn.getId().equals("Startbtn")) { new StartFrame((MainFrame) btn.getTopLevelAncestor()); }
				else if(btn.getId().equals("WordSetBtn")) { new WordSetFrame(); }
				else if(btn.getId().equals("Help"));
				else if(btn.getId().equals("Exitbtn")){ System.exit(0); }
				
			}
		}
				
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon background = new ImageIcon("images/MainFrame/MainPage/Background.png");	
			g.drawImage(background.getImage(), 0, 0, null);	
			setOpaque(false);
		}	
	}		
	
	public static void main(String[] args){
		/*System.setProperty("file.encoding","UTF-8");
		Field charset;
		try {
			charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null,null);
			
		} catch (NoSuchFieldException | SecurityException |IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		new MainFrame();
	}
}
