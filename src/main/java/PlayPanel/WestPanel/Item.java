package PlayPanel.WestPanel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.cgLee079.kakaotp.dict.UserDictionary;
import com.cgLee079.kakaotp.main.MainFrame;

import PlayPanel.PlayPanel;
import PlayPanel.CenterPanel.FallWordLabel;

public abstract class Item{
	private boolean enable;
	
	Item(){	enable=false;}	
	public abstract void call(); //item 사용	
	public boolean getEnable(){	return enable;}	//상태값 리턴
	public void setEnable(boolean enable){	this.enable=enable;	}//상태값 세팅
}

class Item1 extends Item{
	public void call() {
		PlayPanel playPanel=MainFrame.mf.playPanel;
		Vector<FallWordLabel> fallWordLabelArray = playPanel.cp.getLabelArray();
		String korean;
		String english;
		
		//떨어지는 모든 단어를 성공 단어에 추가
		for(int index=0; index<fallWordLabelArray.size();index++){
			//case 1: 한글 입력상태에서 아이템 사용
			//case 2: 영문 입력상테에서 아이템 사용
			FallWordLabel la=fallWordLabelArray.get(index);
			
			korean=la.getText();
			english=playPanel.dictionary.render(korean);
					
			//case2의 경우 - 모든 떨어지는 라벨 중 하나의 라벨은 영어를 가지고있음
			if(la.getLanguage()==false){
				english=korean;
				korean=playPanel.dictionary.renderReverse(english);
			}
			
			//성공 단어에 추가
			MainFrame.mf.playPanel.ep.successWordPanel.successWordTable.add(korean,english);
		}
				
		//모든 떨어지는 라벨 제거
		MainFrame.mf.playPanel.cp.clearLabel();	
				
		//item1 사용 불가 상태로
		setEnable(false);
	}
}

class Item2 extends Item{

	public void call() {
		MainFrame.mf.playPanel.play.pauseGame();
		
		Timer t= new Timer(false);
		TimerTask repairTask=new RepairTask();
		
		//5초후에 원래 속도로
		t.schedule(repairTask, 5000);

		//item2 사용 불가 상태로
		setEnable(false);
	}
	
	class RepairTask extends TimerTask{
		public void run() {
			MainFrame.mf.playPanel.play.resumeGame();
		}
	}

}

class Item3 extends Item{
	double speed;
	public void call() {
		speed=MainFrame.mf.playPanel.play.getSpeed();
		MainFrame.mf.playPanel.play.setSpeed(5.0);
		
		Timer t= new Timer(false);
		TimerTask repairTask=new RepairTask();
		
		//5초후에 원래 속도로
		t.schedule(repairTask, 5000);

		//item3 사용 불가 상태로
		setEnable(false);
	}
	
	class RepairTask extends TimerTask{
		public void run() {
			MainFrame.mf.playPanel.play.setSpeed(speed);
		}
	}
}

class Item4 extends Item{
	public void call() {
		MainFrame.mf.playPanel.cp.heartGagePa.heartgage.fullgain();
		setEnable(false);
	}	
}
