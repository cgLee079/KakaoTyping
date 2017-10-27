package PlayPanel.CenterPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GraphicPanel;
import com.cgLee079.kakaotp.main.MainFrame;

public class HeartGagePanel extends GraphicPanel{
	public HeartGage heartgage=new HeartGage();
	
	HeartGagePanel(String path, String FILENAME, int width, int height){
		super(path,FILENAME,width,height);
		heartgage=new HeartGage();
		heartgage.setFont(new GameFontB(10));
		add(heartgage);
		
	}
	
	public class HeartGage extends JProgressBar{
		int max=100;
		int min=0;
		Integer value=100;
		
		HeartGage(){
			setMinimum(min);
			setMaximum(max);
			setForeground(new Color(255,100,100));
			setStringPainted(true);
			setString(value.toString()+"%");
			setPreferredSize(new Dimension(120,17));
			setValue(value);
		
		}
		
		public void pain(){ //체력 감소
			value-=20;
			setValue(value);
			setString(value.toString()+"%");	
			
			if(value==0){ //체력이 없을 경우	
				MainFrame.mf.playPanel.play.gameOver();
				return;
			}
		}
		
		public void fullgain(){
			setValue(max);
			setString(100+"%");
		}
	}
}
