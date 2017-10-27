package PlayPanel.EastPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.cgLee079.kakaotp.graphic.GameFontB;
import com.cgLee079.kakaotp.graphic.GraphicPanel;

public class InformationPanel extends GraphicPanel{
	JLabel userla;
	
	InformationPanel(String path, String FILENAME, int width, int height){
		super(path,FILENAME,width,height);
		setBackground(null);
		setLocation(10,40);
		
		userla=new JLabel();
		userla.setHorizontalAlignment(SwingConstants.CENTER);
		userla.setFont(new GameFontB(17));
	
		add(userla);	

	}
	
	public void setUserText(String user){userla.setText(user);}
}