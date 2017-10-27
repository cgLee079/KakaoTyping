package PlayPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.cgLee079.kakaotp.dict.UserDictionary;
import com.cgLee079.kakaotp.graphic.GlobalGraphic;

import PlayPanel.CenterPanel.CenterPanel;
import PlayPanel.EastPanel.EastPanel;
import PlayPanel.NorthPanel.NorthPanel;
import PlayPanel.SouthPanel.SouthPanel;
import PlayPanel.WestPanel.WestPanel;

public class PlayPanel extends JPanel {
	public EastPanel ep;
	public NorthPanel np;
	public CenterPanel cp;
	public SouthPanel sp;
	public WestPanel wp;
	public Play play;
	public UserInfo userInfo;
	public UserDictionary dictionary;

	public PlayPanel() {
		this.setBackground(Color.WHITE);
		String npPath = GlobalGraphic.path + "NorthPanel/";
		String cpPath = GlobalGraphic.path + "CenterPanel/";
		wp = new WestPanel();
		sp = new SouthPanel();
		ep = new EastPanel();
		np = new NorthPanel(npPath, "background", 800, 60);
		cp = new CenterPanel(cpPath, "background", 500, 420);

		this.setLayout(new BorderLayout());
		add(sp, BorderLayout.SOUTH);
		add(ep, BorderLayout.EAST);
		add(np, BorderLayout.NORTH);
		add(cp, BorderLayout.CENTER);
		add(wp, BorderLayout.WEST);

	}

	public PlayPanel(String user, String character, Integer level, Double speed) {
		this();
		dictionary = new UserDictionary(user);
		play = new Play(level, speed);
		userInfo = new UserInfo(user, character);

		wp.speedPanel.setSpeedText(this.play.getSpeed().toString());
		np.levelPa.setLevelText(this.play.getLevel().toString());
		np.scorePa.setScoreText(this.play.getScore().toString());
		ep.infoPanel.setUserText(userInfo.getUser());

		play.startGame();
	}
}

/*
 * class ExitListener implements WindowListener{ public void
 * windowClosing(WindowEvent e){ try { MyDictionary.writeWordFromFile();} catch
 * (IOException e1) {e1.printStackTrace();}
 * 
 * System.exit(0); }
 * 
 * public void windowOpened(WindowEvent e){} public void
 * windowActivated(WindowEvent e){} public void windowDeactivated(WindowEvent
 * e){} public void windowClosed(WindowEvent e){} public void
 * windowIconified(WindowEvent e){} public void windowDeiconified(WindowEvent
 * e){} } }
 */
