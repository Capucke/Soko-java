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

	private final int EMPTY = 0;
	private final int SOKO = 1;
	private final int BAG = 2;
	private final int GOAL = 3;
	private final int GOAL_OK = 4;
	private final int WALL = 5;

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

			currCharInt = buffLevel.read();

			while (currCharInt != -1) {
				currChar = (char) currCharInt;

				if (currChar == '\n') {
					while (j < width) {
						this.startMat.setObj(i, j, EMPTY);
					}
					i += 1;
					j = 0;
				} else {

					if (j >= width) {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais format : width incorrect");
					}
					if (i >= height) {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais format : height incorrect");
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
								"Le fichier passé en paramètre est au mauvais format : carctère inconnu : "
										+ Character.toString(currChar));
					}

					j += 1;

				}
				currCharInt = buffLevel.read();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}