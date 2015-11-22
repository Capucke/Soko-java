package mySokoban;

import gameGraphics.SokoFenetre;
import gameSimulator.SokoSimulator;
import gameStructures.SokoMatrix;

public class SokoGame {
	public static void main(String[] args){
		SokoMatrix game = new SokoMatrix("level10.txt");
		SokoFenetre window = new SokoFenetre();
		
		SokoSimulator simu = new SokoSimulator(game, window);
		window.setSimulable(simu);
		
		window.display();
		
	  }
}