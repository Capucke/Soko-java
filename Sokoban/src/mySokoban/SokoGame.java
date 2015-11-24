package mySokoban;

import gameGraphics.SokoFenetre;
import gameDisplayer.SokoDisplayer;
import gameStructures.SokoMatrix;

public class SokoGame {
	public static void main(String[] args){
		SokoMatrix game = new SokoMatrix("level10.txt");
		SokoFenetre window = new SokoFenetre();
		
		SokoDisplayer disp = new SokoDisplayer(game, window);
		window.setDisplayer(disp);
		
		window.display();
		
	  }
}