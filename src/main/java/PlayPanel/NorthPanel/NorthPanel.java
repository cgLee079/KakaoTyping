package PlayPanel.NorthPanel;

import javax.swing.JLabel;

import com.cgLee079.tpgame.graphic.GameFontB;
import com.cgLee079.tpgame.graphic.GlobalGraphic;
import com.cgLee079.tpgame.graphic.GraphicPanel;
import com.cgLee079.tpgame.main.MainFrame;

public class NorthPanel extends GraphicPanel{
	public LevelPanel levelPa;
	public ScorePanel scorePa;
	
	public NorthPanel(String path, String FILENAME, int width, int height){
		super(path,FILENAME,width,height);
		setLayout(null);
		
		String paPath=GlobalGraphic.path+"NorthPanel/";
		levelPa=new LevelPanel(paPath,"levelGra",130,50);
		scorePa=new ScorePanel(paPath,"scoreGra",130,50);
		
		levelPa.setLocation(250,0);
		scorePa.setLocation(400,0);
		
		add(levelPa);
		add(scorePa);		
	}
	
	
	public class LevelPanel extends GraphicPanel{
		private JLabel levella;
		public LevelPanel(String path, String FILENAME, int width, int height) {
			super(path, FILENAME, width, height);
			setLayout(null);
			levella = new JLabel();
			levella.setFont(new GameFontB(17));
			levella.setSize(100,40);
			levella.setLocation(90,8);
			add(levella);
		}
		
		public void setLevelText(String level){
			levella.setText(level);
		}
		
	}
	
	public class ScorePanel extends GraphicPanel{
		private JLabel scorela;
		
		public ScorePanel(String path, String FILENAME, int width, int height) {
			super(path, FILENAME, width, height);
			setLayout(null);
			scorela = new JLabel();
			scorela.setFont(new GameFontB(17));
			scorela.setSize(100,40);
			scorela.setLocation(90,8);
			add(scorela);
		}
		
		public void setScoreText(String score){
			scorela.setText(score);
		}
	}
	
	
}
