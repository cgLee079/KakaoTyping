package com.cgLee079.kakaotp.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cgLee079.kakaotp.model.Score;

import PlayPanel.PlayPanel;

public class ScoreManager {

	public ScoreManager() {
	}

	public List<Score> readScores() {
		List<Score> scores = new ArrayList<Score>();
		BufferedReader in = null;
		String[] split;
		String s;

		try {
			in = new BufferedReader(new FileReader("resources/Score.txt"));
			
			while ((s = in.readLine()) != null) {
				split = s.split("\t");
				if (split.length < 2) {
					scores.add(new Score(split[0], split[1], 0));
				} else {
					scores.add(new Score(split[0], split[1], Integer.parseInt(split[2])));
				}
			}

			in.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		Collections.sort(scores, new NoDescCompare());

		return scores;
	}

	public Score getMyScore() {
		Score myScore = null;
		BufferedReader in = null;
		String s = " ";
		
		int lastLine= 0;
		int line 	= 0;

		try {
			in = new BufferedReader(new FileReader("resources/Score.txt"));
			
			while (in.readLine() != null) {
				lastLine++;
			}
		
			in.reset();
	
			while ((s = in.readLine()) != null) {
				line++;
				if (lastLine == line) {
					String[] split = s.split("\t");
					myScore = new Score(split[0], split[1], Integer.parseInt(split[2]));
					System.out.println(line + "============" + split[0] + split[1]);
				}
			}
	
			in.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		return myScore;
	}

	class NoDescCompare implements Comparator<Score> {
		public int compare(Score arg0, Score arg1) {
			return arg0.getScore() > arg1.getScore() ? -1 : arg0.getScore() < arg1.getScore() ? 1 : 0;
		}
	}

}