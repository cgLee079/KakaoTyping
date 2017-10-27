package PlayPanel.CenterPanel;

import java.util.Vector;

import com.cgLee079.kakaotp.graphic.GlobalGraphic;
import com.cgLee079.kakaotp.graphic.GraphicPanel;

import PlayPanel.PlayPanel;

public class CenterPanel extends GraphicPanel{
	public HeartGagePanel heartGagePa;	
	private Vector<FallWordLabel> fallWordLabelArray;	
	public PlayPanel p;
	public CenterPanel(String path, String FILENAME, int width, int height){
		super(path,FILENAME,width,height);
		setLayout(null);
				
		fallWordLabelArray= new Vector<FallWordLabel>();
	
		this.p=p;
		
		heartGagePa = new HeartGagePanel(path,"HeartGageGra",200,30);
		heartGagePa.setLocation(150,10);
		add(heartGagePa);
			
	}
	
	public Vector<FallWordLabel> getLabelArray(){return fallWordLabelArray;}
	
	public void clearLabel(){
		for(int index=0; index<fallWordLabelArray.size();index++){
			fallWordLabelArray.get(index).setVisible(false);
			fallWordLabelArray.get(index).setValid(false);
		}
		
		fallWordLabelArray.removeAllElements();	
	}
		
}
