package PlayPanel.SouthPanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.cgLee079.kakaotp.graphic.GlobalGraphic;

public class SouthPanel extends JPanel{	
	private InputTextPanel inputTextPanel; 
	
	public SouthPanel(){		
		setBackground(GlobalGraphic.baseColor);
		setPreferredSize(new Dimension(0,40));
		inputTextPanel =new InputTextPanel();
		add(inputTextPanel);
	}
}
