package com.cgLee079.kakaotp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GameFontP;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicButton;
import com.cgLee079.kakaotp.graphic.GraphicPanel;
import com.cgLee079.kakaotp.play.FwLabel;
import com.cgLee079.kakaotp.play.KeyEventor;
import com.cgLee079.kakaotp.play.Play;

public class PlayPanel extends JPanel {
	private EastPanel eastPanel;
	private NorthPanel northPanel;
	private CenterPanel centerPanel;
	private SouthPanel southPanel;
	private WestPanel westPanel;
	private Play play;

	public PlayPanel() {
		this.setBackground(Color.WHITE);
		String npPath = GlobalGraphic.path + "NorthPanel/";
		String cpPath = GlobalGraphic.path + "CenterPanel/";
		westPanel 	= new WestPanel();
		southPanel 	= new SouthPanel();
		eastPanel 	= new EastPanel();
		northPanel 	= new NorthPanel(npPath, "background", 800, 60);
		centerPanel = new CenterPanel(cpPath, "background", 500, 420);

		this.setLayout(new BorderLayout());
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);

	}

	public PlayPanel(Play play) {
		this();
		this.play = play;
		play.setPlayPanel(this);
		addKeyListener(new KeyEventor((MainFrame)this.getTopLevelAncestor(), play));
		
		drawLevel(play.getLevel());
		drawPoint(play.getPoint());
		drawSpeed(play.getSpeed() + "");
		drawUsername(play.getUser().getUsername());

		play.startGame();
	}
	
	public void addKeyListener(KeyEventor keyEventor){
		JTextField wordTextField = southPanel.getWordTextField();
		wordTextField.addKeyListener(keyEventor);
		wordTextField.requestFocus();
	}
	
	public void drawLevel(int level){
		northPanel.getLevelPanel().getLevelLabel().setText(level + "");
	}
	
	public void drawPoint(int point){
		northPanel.getPointPanel().getPointLabel().setText(point + "");
	}
	
	public void drawSpeed(String speed){
		westPanel.getSpeedPanel().getSpeedLabel().setText(speed);
	}
	
	public void drawItemBtn(int index, boolean enabled){
		JButton itemBtn = westPanel.getItemPanel().getItemBtn(index);
		itemBtn.setEnabled(enabled);
	}
	
	public void drawFwLabel(FwLabel fwLabel){
		centerPanel.add(fwLabel);
	}
	
	public void drawUsername(String username){
		eastPanel.getInfoPanel().getUserLabel().setText(username);
	}
	
	public void drawPain(int value) { //체력 감소
		centerPanel.getHeartGagePanel().getHeartGageBar().setValue(value);
		centerPanel.getHeartGagePanel().getHeartGageBar().setString(value + "%");
	}

	public void drawGain(int value) { //체력 회복
		centerPanel.getHeartGagePanel().getHeartGageBar().setValue(value);
		centerPanel.getHeartGagePanel().getHeartGageBar().setString(value + "%");
	}
	
	public void addSuccessWord(String korean, String english) { //성공 단어 추가
		DefaultTableModel model = eastPanel.getSuccessWordPanel().getModel();
		String[] content = new String[2];
		content[0] = korean;
		content[1] = english;
		model.insertRow(0, content);
	}
	
	/**/
	class NorthPanel extends GraphicPanel {
		private LevelPanel levelPanel;
		private PointPanel scorePanel;

		private NorthPanel(String path, String filename, int width, int height) {
			super(path, filename, width, height);
			setLayout(null);

			String panelPath = GlobalGraphic.path + "NorthPanel/";
			levelPanel = new LevelPanel(panelPath, "levelBg", 130, 50);
			scorePanel = new PointPanel(panelPath, "scoreBg", 130, 50);

			levelPanel.setLocation(250, 0);
			scorePanel.setLocation(400, 0);

			add(levelPanel);
			add(scorePanel);
		}
		
		public LevelPanel getLevelPanel() {
			return levelPanel;
		}

		public PointPanel getPointPanel() {
			return scorePanel;
		}

		class LevelPanel extends GraphicPanel {
			private JLabel levelLabel;

			private LevelPanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				setLayout(null);
				levelLabel = new JLabel();
				levelLabel.setFont(new GameFontB(17));
				levelLabel.setSize(100, 40);
				levelLabel.setLocation(90, 8);
				add(levelLabel);
			}

			public JLabel getLevelLabel() {
				return levelLabel;
			}
		}

		class PointPanel extends GraphicPanel {
			private JLabel pointLabel;

			private PointPanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				setLayout(null);
				pointLabel = new JLabel();
				pointLabel.setFont(new GameFontB(17));
				pointLabel.setSize(100, 40);
				pointLabel.setLocation(90, 8);
				add(pointLabel);
			}

			public JLabel getPointLabel() {
				return pointLabel;
			}
		}
	}
	
	/**/
	class WestPanel extends JPanel {
		private SpeedPanel speedPanel;
		private ItemPanel itemPanel;

		private WestPanel() {
			setBackground(GlobalGraphic.baseColor);
			setPreferredSize(new Dimension(150, 0));

			String itemPath = GlobalGraphic.path + "WestPanel/";
			String speedPath = GlobalGraphic.path + "WestPanel/";

			speedPanel = new SpeedPanel(speedPath, "SpeedPanel", 130, 150);
			itemPanel = new ItemPanel(itemPath, "ItemPanel", 130, 240);

			add(speedPanel);
			add(itemPanel);
		}

		
		public SpeedPanel getSpeedPanel() {
			return speedPanel;
		}

		public ItemPanel getItemPanel() {
			return itemPanel;
		}


		class SpeedPanel extends GraphicPanel {
			private JLabel speedLabel = new JLabel();

			SpeedPanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				setBackground(null);
				setLayout(null);
				setLocation(0, 30);

				speedLabel.setFont(new GameFontB(20));
				speedLabel.setLocation(40, 45);
				speedLabel.setSize(120, 50);
				speedLabel.setForeground(GlobalGraphic.basic);
				add(speedLabel);
			}

			public JLabel getSpeedLabel() {
				return speedLabel;
			}

		}

		class ItemPanel extends GraphicPanel {
			private GraphicButton itemBtn[] = new GraphicButton[4];

			private ItemPanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				setLayout(null);
				setBackground(null);

				String btnpath = GlobalGraphic.path + "WestPanel/";
				itemBtn[0] = new GraphicButton(btnpath, "ItemBtn1", 100, 35);
				itemBtn[1] = new GraphicButton(btnpath, "ItemBtn2", 100, 35);
				itemBtn[2] = new GraphicButton(btnpath, "ItemBtn3", 100, 35);
				itemBtn[3] = new GraphicButton(btnpath, "ItemBtn4", 100, 35);

				for (int i = 0; i < 4; i++) {
					itemBtn[i].setLocation(15, 10 + (i * 37));
					itemBtn[i].setEnabled(false);
					add(itemBtn[i]);
				}
			}

			// 해당 인덱스의 아이템 버튼 리턴
			public GraphicButton getItemBtn(int index) {
				return itemBtn[index];
			}
		}

	}
	
	/**/
	class CenterPanel extends GraphicPanel {
		private HeartGagePanel heartGagePanel;

		public CenterPanel(String path, String filename, int width, int height) {
			super(path, filename, width, height);
			setLayout(null);

			heartGagePanel = new HeartGagePanel(path, "HeartGageBg", 200, 30);
			heartGagePanel.setLocation(150, 10);
			add(heartGagePanel);

		}

		public HeartGagePanel getHeartGagePanel() {
			return heartGagePanel;
		}

		class HeartGagePanel extends GraphicPanel {
			private JProgressBar heartGageBar = new JProgressBar();

			private HeartGagePanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				
				int min = 0;
				int max = 100;
				
				heartGageBar = new JProgressBar();
				heartGageBar.setFont(new GameFontB(10));
				heartGageBar.setMinimum(min);
				heartGageBar.setMaximum(max);
				heartGageBar.setForeground(new Color(255, 100, 100));
				heartGageBar.setStringPainted(true);
				heartGageBar.setString(max + "%");
				heartGageBar.setPreferredSize(new Dimension(120, 17));
				heartGageBar.setValue(max);
				
				add(heartGageBar);
			}
			
			public JProgressBar getHeartGageBar() {
				return heartGageBar;
			}

		}
	}
	
	/**/
	class EastPanel extends JPanel {
		private InfoPanel infoPanel;
		private SuccessWordPanel successWordPanel;

		private EastPanel() {
			setBackground(GlobalGraphic.baseColor);
			setPreferredSize(new Dimension(150, 0));
			setLayout(null);

			String infoPath = GlobalGraphic.path + "EastPanel/";
			infoPanel = new InfoPanel(infoPath, "face", 130, 180);
			infoPanel.setLocation(10, 10);
			this.add(infoPanel);

			successWordPanel = new SuccessWordPanel();
			successWordPanel.setLocation(10, 200);
			successWordPanel.setSize(130, 210);
			this.add(successWordPanel);
		}
		
		public InfoPanel getInfoPanel() {
			return infoPanel;
		}

		public SuccessWordPanel getSuccessWordPanel() {
			return successWordPanel;
		}

		class InfoPanel extends GraphicPanel {
			private JLabel userLabel;

			private InfoPanel(String path, String filename, int width, int height) {
				super(path, filename, width, height);
				setBackground(null);
				setLocation(10, 40);

				userLabel = new JLabel();
				userLabel.setHorizontalAlignment(SwingConstants.CENTER);
				userLabel.setFont(new GameFontB(17));

				add(userLabel);
			}

			public JLabel getUserLabel() {
				return userLabel;
			}
		}
		
		class SuccessWordPanel extends JPanel {
			private JTable successWordTable;
			private DefaultTableModel model;
			
			private SuccessWordPanel() {
				setBackground(null);
				successWordTable = new JTable();

				successWordTable.setFont(new GameFontP(13));
				successWordTable.setBackground(Color.WHITE);
				successWordTable.setShowHorizontalLines(true);
				successWordTable.setShowVerticalLines(false);

				String[] header = { "한글", "영어" };
				model = new DefaultTableModel(header, 0);
				successWordTable.setModel(model);
				
				// 스크롤바 추가
				JScrollPane scroll = new JScrollPane(successWordTable);
				scroll.setPreferredSize(new Dimension(130, 200));

				add(scroll);
			}

			public DefaultTableModel getModel() {
				return model;
			}
		}
	}
	
	/**/
	class SouthPanel extends JPanel{	
		private JPanel inputWordPanel; 
		private JTextField wordTextField;
		
		public SouthPanel(){		
			setBackground(GlobalGraphic.baseColor);
			setPreferredSize(new Dimension(0,40));

			inputWordPanel = new JPanel();	
			inputWordPanel.setBackground(null);
			wordTextField = new JTextField("", 20);
			
			inputWordPanel.add(wordTextField);
			this.add(inputWordPanel);
		}

		public JTextField getWordTextField() {
			return wordTextField;
		}
	}

}
