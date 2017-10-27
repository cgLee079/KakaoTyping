package PlayPanel.EastPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cgLee079.kakaotp.graphic.GameFontP;
public class SuccessWordPanel extends JPanel{
	public SuccessWordTable successWordTable;
	
	
	SuccessWordPanel(){		
		setBackground(null);
		
		successWordTable=new SuccessWordTable();
		
		//스크롤바 추가
		JScrollPane scroll=new JScrollPane(successWordTable);
		scroll.setPreferredSize(new Dimension(130,200));
		
		add(scroll);
	}
	
	public class SuccessWordTable extends JTable{
		private DefaultTableModel model;
		
		SuccessWordTable(){
			this.setFont(new GameFontP(13));
			this.setBackground(Color.WHITE);
			this.setShowHorizontalLines(true);
			this.setShowVerticalLines(false);
			
			String[] header={"한글","영어"};
			model= new DefaultTableModel(header,0);
			this.setModel(model);
		}
		
		public DefaultTableModel getModel(){return model;}  
		
		//테이블에 행 추가
		public void add(String korean, String english){
			String[] content=new String[2];
			content[0]=korean;
			content[1]=english;
			model.insertRow(0, content);		
		}
		
		

	}
}