package PlayPanel.SouthPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cgLee079.kakaotp.main.MainFrame;
import com.cgLee079.kakaotp.main.PauseFrame;

import PlayPanel.PlayPanel;
import PlayPanel.CenterPanel.FallWordLabel;
import PlayPanel.WestPanel.ItemPanel;	

class InputTextPanel extends JPanel{	
	private JTextField textInput;

	ItemPanel itemPanel;
	InputTextPanel(){

		setBackground(null);
		textInput = new JTextField("",20);
		textInput.addKeyListener(new InputTextListener());
		textInput.requestFocus();
		add(textInput);
		
	}
	
	class InputTextListener extends KeyAdapter{
		PlayPanel playPanel;
		ItemPanel itemPanel;
		PauseFrame pauseFrame=null;
		String text; //입력 단어
		
		public void keyPressed(KeyEvent e){
			MainFrame mf = (MainFrame)InputTextPanel.this.getTopLevelAncestor();
			playPanel = mf.playPanel;
			itemPanel = playPanel.wp.itemPanel;
			
			switch(e.getKeyCode()){
			case KeyEvent.VK_ESCAPE: 		
					playPanel.play.pauseGame();
					pauseFrame = new PauseFrame();
				break;
				
			case KeyEvent.VK_F1: //item1 모두 지우기
				if(itemPanel.getItem(0).getEnable()){
					itemPanel.getItem(0).call();
					itemPanel.getItemBtn(0).setEnabled(false);
					playPanel.play.setKoreanTurn();
				}
				break;		
				
			case KeyEvent.VK_F2: //item2
				if(itemPanel.getItem(1).getEnable())
					itemPanel.getItem(1).call();
					itemPanel.getItemBtn(1).setEnabled(false);
				break;	
				
			case KeyEvent.VK_F3: //item3			
				if(itemPanel.getItem(2).getEnable()){
					itemPanel.getItem(2).call();
					itemPanel.getItemBtn(2).setEnabled(false);
				}
				break;
				
			case KeyEvent.VK_F4: //item4
				if(itemPanel.getItem(3).getEnable()){
					itemPanel.getItem(3).call();
					itemPanel.getItemBtn(3).setEnabled(false);
				}
				break;
				
			case KeyEvent.VK_ENTER: //Enter 입력시  단어 비교, 	
				text=textInput.getText(); //TextField에서 입력값 받아옴
				matchFallWord(); //단어 비교
				textInput.setText("");//textField 클리어 
			}
		}
		
		//FallWord와 단어 비교
		void matchFallWord(){		
			String renderWord=playPanel.dictionary.render(text); 	//번역글자  :  한글 -> 영어 -> null
			
			if(playPanel.play.getTurn()==false && renderWord!=null) //영어 입력차례에서, 한글을 입력한 경우
				return;			

			Vector<FallWordLabel> fallWordLabelArray=playPanel.cp.getLabelArray();
			for(int index=0; index<fallWordLabelArray.size(); index++){ //떨어지는 라벨들 중
				FallWordLabel la=fallWordLabelArray.get(index); //떨어지는 라벨
				String fallWord=fallWordLabelArray.get(index).getText(); //떨어지는 라벨의 단어				
				
						
				if(fallWord.equalsIgnoreCase(text)){ //떨어지는 단어와 입력 단어가 같을경우	 					
					la.setText(renderWord); //한글 -> 영어로, 영어-> null로
					
					if(la.getLanguage()==false)	 
						InputEnglish(la); 	
					else
						InputKorean(la);		
					
					break;
				}
			}
		}
		
		void InputEnglish(FallWordLabel la){
			
			la.setVisible(false);
			
			//영어의 한글 값 저장
			String korean=playPanel.dictionary.renderReverse(text);
					
			//성공 단어에 추가
			playPanel.ep.successWordPanel.successWordTable.add(korean,text);
			
			//단어 성공 횟수 증가
			playPanel.dictionary.plusSuccess(korean);
			
			//무효한 숫자로
			la.setValid(false);
			
			//Item확인, 생성
	
			if(la.getHaveItem()){
				Random random=new Random();
				int num=random.nextInt(4);//0-3 아이템 번호 제공
				int n=0;
				while(itemPanel.getItem(num).getEnable()){
					num=random.nextInt(4);
					n++;
					if(n==10)break;
				}
				
				itemPanel.getItem(num).setEnable(true);
				itemPanel.getItemBtn(num).setEnabled(true);
				
			}
	
			//배열에서 제거
			playPanel.cp.getLabelArray().remove(la);
			
			//점수 흭득
			playPanel.play.scoreUp();
			
			//한글 입력차례로 변환
			playPanel.play.setKoreanTurn();
		}
		
		void InputKorean(FallWordLabel la){
			la.setLanguage(false);
			if(la.getHaveItem())
				la.setHaveItem_e(); //아이템을 가진 영단어 폰트 셋
			else
				la.setEnglish(); 	//아이템 가지지 않은 영단어 폰트 셋
			
			playPanel.play.setEnglishTurn(); //영어차례로 변환
		}
	}
}