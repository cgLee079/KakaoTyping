package PlayPanel.EastPanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.cgLee079.tpgame.graphic.GlobalGraphic;

public class EastPanel extends JPanel{
	public InformationPanel infoPanel;
	public SuccessWordPanel successWordPanel;

	public EastPanel(){
		setBackground(GlobalGraphic.character);
		setPreferredSize(new Dimension(150,0));
		setLayout(null);
		
		String infoPath=GlobalGraphic.path+"EastPanel/";
		infoPanel=new InformationPanel(infoPath,"face",130,180);
		infoPanel.setLocation(10,10);
		this.add(infoPanel);
		
		successWordPanel=new SuccessWordPanel();
		successWordPanel.setLocation(10,200);
		successWordPanel.setSize(130,210);
		this.add(successWordPanel);
	}
}
