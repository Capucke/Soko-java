package gameStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class SokoMatrix {

	private GameMatrix curMat;
	private GameMatrix startMat;
	private int nbGoal;
	private int nbGoalOk;

	public final static int EMPTY = 0;
	public final static int SOKO = 1;
	public final static int BAG = 2;
	public final static int GOAL = 3;
	public final static int GOAL_OK = 4;
	public final static int WALL = 5;

	public SokoMatrix(String levelName) {
		this.initStartMatrix(this.getBuffLevel(levelName));
		this.reInitCurMat();
	}

	public void reInitCurMat() {
		for (int i = 0; i < this.startMat.getNbLines(); i++) {
			for (int j = 0; j < this.startMat.getNbCol(); j++) {
				this.curMat.setObj(i, j, this.startMat.getObj(i, j));
			}
		}
	}

	public BufferedReader getBuffLevel(String fileName) {
		InputStream fileStream = SokoMatrix.class
				.getResourceAsStream("/levels/" + fileName);
		BufferedReader buffLevel = new BufferedReader(new InputStreamReader(
				fileStream));

		return buffLevel;
	}

	private void initStartMatrix(BufferedReader buffLevel) {
		try {
			int currCharInt = buffLevel.read();
			char currChar = (char) currCharInt;

			// Obtention des dimensions de la matrice de jeu
			String widthString = new String();
			while (currChar != ' ') {
				widthString += new String(Character.toString(currChar));
				currCharInt = buffLevel.read();
				currChar = (char) currCharInt;
			}
			int width = Integer.parseInt(widthString);

			String heightString = new String();
			while (currChar != '\n') {
				heightString += new String(Character.toString(currChar));
				currCharInt = buffLevel.read();
				currChar = (char) currCharInt;
			}
			int height = Integer.parseInt(heightString);

			this.curMat = new GameMatrix(width, height, 6);
			this.startMat = new GameMatrix(width, height, 6);
			this.nbGoal = 0;
			this.nbGoalOk = 0;

			/*
			 * Remplissage de la matrice de départ
			 */

			int i = 0;
			int j = 0;
			
			boolean widthOK = false ;

			currCharInt = buffLevel.read();

			while (currCharInt != -1) {
				currChar = (char) currCharInt;

				if (currChar == '\n') {
					if (j == width){
						widthOK = true ;
					}
					while (j < width) {
						this.startMat.setObj(i, j, EMPTY);
						j += 1 ;
					}
					i += 1;
					j = 0;
				} else {

					if (j >= width) {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais"
										+ " format : width incorrect "
										+ "(trop petit)");
					}
					if (i >= height) {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais"
										+ " format : height incorrect "
										+ "(trop petit)");
					}

					if (currChar == '#') {
						this.startMat.setObj(i, j, WALL);
					} else if (currChar == '$') {
						this.startMat.setObj(i, j, BAG);
					} else if (currChar == '.') {
						this.startMat.setObj(i, j, GOAL);
						this.nbGoal += 1;
					} else if (currChar == '@') {
						this.startMat.setObj(i, j, SOKO);
					} else if (currChar == ' ') {
						this.startMat.setObj(i, j, EMPTY);
					} else {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais"
										+ " format : carctère inconnu : "
										+ Character.toString(currChar));
					}

					j += 1;

				}
				currCharInt = buffLevel.read();

			}

			if (!widthOK){
				throw new IllegalArgumentException(
						"Le fichier passé en paramètre est au mauvais format"
								+ ": width incorrect (trop grand)");
			}
			
			if (i == height - 1) {
				while (j < width) {
					this.startMat.setObj(i, j, EMPTY);
				}
			} else if (i < height - 1) {
				throw new IllegalArgumentException(
						"Le fichier passé en paramètre est au mauvais format"
								+ ": height incorrect (trop grand)\n"
								+ "La lecture du fichier se termine avec i = "
								+ i);
			}			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}