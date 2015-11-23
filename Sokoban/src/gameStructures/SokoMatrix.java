package gameStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SokoMatrix {

	private GameMatrix curMat;
	private GameMatrix startMat;
	private int nbGoalOkStart;
	private int nbGoal;
	private int nbGoalOk;
	private int sokoI = -1;
	private int sokoJ = -1;

	public final static int EMPTY = 0;
	public final static int SOKO = 1;
	public final static int BAG = 2;
	public final static int GOAL = 3;
	public final static int GOAL_OK = 4;
	public final static int SOKO_ON_GOAL = 5;
	public final static int WALL = 6;

	public SokoMatrix(String levelName) {
		this.initStartMatrix(this.getBuffLevel(levelName));
		this.reInitCurMat();
	}

	public GameMatrix getCurrMat() {
		return this.curMat;
	}

	public boolean isComplete() {
		return (this.nbGoal == this.nbGoalOk);
	}

	public void reInitCurMat() {
		this.nbGoalOk = this.nbGoalOkStart;
		for (int i = 0; i < this.startMat.getNbLines(); i++) {
			for (int j = 0; j < this.startMat.getNbCol(); j++) {
				this.curMat.setObj(i, j, this.startMat.getObj(i, j));
			}
		}
	}

	/**
	 * Déplace le personnage Soko vers le haut si possible, en mettant à jour
	 * les valeurs de sokoI et sokoJ
	 */
	public void moveUp() {
		if (!this.canMoveUp()) {
			return;
		}

		int currObj = this.curMat.getObj(sokoI, sokoJ);
		int upObj = this.curMat.getObj(sokoI - 1, sokoJ);

		/*
		 * -EMPTY -SOKO BAG -GOAL GOAL_OK -SOKO_ON_GOAL -WALL
		 */
		if (upObj == BAG || upObj == GOAL_OK) {
			int upUpObj = this.curMat.getObj(sokoI - 2, sokoJ);

			switch (upObj) {
			case BAG:
				switch (upUpObj) {
				case EMPTY:
					this.curMat.setObj(sokoI - 2, sokoJ, BAG);
					this.curMat.setObj(sokoI - 1, sokoJ, EMPTY);
					break;
				case GOAL:
					this.curMat.setObj(sokoI - 2, sokoJ, GOAL_OK);
					this.curMat.setObj(sokoI - 1, sokoJ, EMPTY);
					this.nbGoalOk++;
					break;
				default:
					throw new IllegalStateException(
							"Impossible : objet incompatible avec canMoveObjUp() en ("
									+ (sokoI - 2) + ", " + sokoJ + ")\n"
									+ "valeur attendue : EMPTY ou GOAL");
				}
				break;
			case GOAL_OK:
				switch (upUpObj) {
				case EMPTY:
					this.curMat.setObj(sokoI - 2, sokoJ, BAG);
					this.curMat.setObj(sokoI - 1, sokoJ, GOAL);
					this.nbGoalOk--;
					break;
				case GOAL:
					this.curMat.setObj(sokoI - 2, sokoJ, GOAL_OK);
					this.curMat.setObj(sokoI - 1, sokoJ, GOAL);
					break;
				default:
					throw new IllegalStateException(
							"Impossible : objet incompatible avec canMoveObjUp() en ("
									+ (sokoI - 2) + ", " + sokoJ + ")\n"
									+ "valeur attendue : EMPTY ou GOAL");
				}
				break;
			default:
				throw new IllegalStateException("Impossible : objet double");
			}

		}

		if (currObj == SOKO_ON_GOAL) {
			this.curMat.setObj(sokoI, sokoJ, GOAL);
		} else {
			this.curMat.setObj(sokoI, sokoJ, EMPTY);
		}

		if (this.curMat.getObj(sokoI - 1, sokoJ) == GOAL) {
			this.curMat.setObj(sokoI - 1, sokoJ, SOKO_ON_GOAL);
		} else {
			this.curMat.setObj(sokoI - 1, sokoJ, SOKO);
		}

		this.sokoI--;
	}

	/**
	 * Procédure pour déterminer si le personnage Soko peut bouger vers le haut
	 * 
	 * @return true si Soko peut bouger vers le heut, false sinon
	 */
	public boolean canMoveUp() {
		if (sokoI == 0) {
			return false;
		}
		int upObj = this.curMat.getObj(sokoI - 1, sokoJ);

		boolean canMoveUp;

		switch (upObj) {
		case WALL:
			canMoveUp = false;
			break;
		case EMPTY:
		case GOAL:
			canMoveUp = true;
			break;
		case SOKO:
		case SOKO_ON_GOAL:
			throw new IllegalStateException(
					"Impossible : il y a 2 soko sur le plateau !");
		case BAG:
		case GOAL_OK:
			canMoveUp = this.canMoveObjUp(sokoI - 1, sokoJ);
			break;
		default:
			throw new IllegalStateException(
					"Impossible : il y a un état qui ne représente pas un objet!");
		}

		return canMoveUp;

	}

	/**
	 * Procédure pour déterminer si un objet placé aux coordonnées (i,j)
	 * 
	 * @return true si l'objet peut être déplacé vers le heut, false sinon
	 */
	private boolean canMoveObjUp(int i, int j) {
		if (i == 0) {
			return false;
		}
		int upObj = this.curMat.getObj(i - 1, j);

		boolean canMoveUp;

		switch (upObj) {
		case WALL:
		case BAG:
		case GOAL_OK:
			canMoveUp = false;
			break;
		case EMPTY:
		case GOAL:
			canMoveUp = true;
			break;
		case SOKO:
		case SOKO_ON_GOAL:
			throw new IllegalStateException(
					"Impossible : il y a 2 soko sur le plateau !");
		default:
			throw new IllegalStateException(
					"Impossible : il y a un état qui ne représente pas un objet!");
		}
		return canMoveUp;
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

			currCharInt = buffLevel.read();
			currChar = (char) currCharInt;

			String heightString = new String();
			while (currChar != '\n') {
				heightString += new String(Character.toString(currChar));
				currCharInt = buffLevel.read();
				currChar = (char) currCharInt;
			}
			int height = Integer.parseInt(heightString);

			this.curMat = new GameMatrix(width, height, SokoMatrix.WALL + 1);
			this.startMat = new GameMatrix(width, height, SokoMatrix.WALL + 1);
			this.nbGoal = 0;
			this.nbGoalOk = 0;
			this.nbGoalOkStart = 0;

			/*
			 * Remplissage de la matrice de départ
			 */

			int i = 0;
			int j = 0;

			boolean murRencontre = false;

			boolean widthOK = false;

			currCharInt = buffLevel.read();

			while (currCharInt != -1) {
				currChar = (char) currCharInt;

				if (currChar == '\n') {
					if (j == width) {
						widthOK = true;
					}
					while (j < width) {
						this.startMat.setObj(i, j, WALL);
						j += 1;
					}
					i += 1;
					j = 0;
					murRencontre = false;
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
						murRencontre = true;
						this.startMat.setObj(i, j, WALL);
					} else if (currChar == '$') {
						this.startMat.setObj(i, j, BAG);
					} else if (currChar == '.') {
						this.startMat.setObj(i, j, GOAL);
						this.nbGoal += 1;
					} else if (currChar == '*') {
						this.startMat.setObj(i, j, GOAL_OK);
						this.nbGoal += 1;
						this.nbGoalOk += 1;
						this.nbGoalOkStart += 1;
					} else if (currChar == '@') {
						this.startMat.setObj(i, j, SOKO);
						if (this.sokoI == -1 & this.sokoJ == -1) {
							this.sokoI = i;
							this.sokoJ = j;
						} else {
							throw new IllegalArgumentException(
									"Le fichier passé en paramètre est au mauvais"
											+ " format : il y a deux Soko (@ ou +)");
						}
					} else if (currChar == '+') {
						this.startMat.setObj(i, j, SOKO_ON_GOAL);
						this.nbGoal += 1;
						if (this.sokoI == -1 & this.sokoJ == -1) {
							this.sokoI = i;
							this.sokoJ = j;
						} else {
							throw new IllegalArgumentException(
									"Le fichier passé en paramètre est au mauvais"
											+ " format : il y a deux Soko (@ ou +)");
						}
					} else if (currChar == ' ') {
						if (!murRencontre) {
							this.startMat.setObj(i, j, WALL);
						} else {
							this.startMat.setObj(i, j, EMPTY);
						}
					} else {
						throw new IllegalArgumentException(
								"Le fichier passé en paramètre est au mauvais"
										+ " format : caractère inconnu : "
										+ Character.toString(currChar));
					}

					j += 1;

				}
				currCharInt = buffLevel.read();

			}

			if (i == height - 1) {
				while (j < width) {
					this.startMat.setObj(i, j, WALL);
					j += 1;
				}
			} else if (i < height - 1) {
				throw new IllegalArgumentException(
						"Le fichier passé en paramètre est au mauvais format"
								+ ": height incorrect (trop grand)\n"
								+ "La lecture du fichier se termine avec i = "
								+ i);
			}

			if (!widthOK) {
				throw new IllegalArgumentException(
						"Le fichier passé en paramètre est au mauvais format"
								+ ": width incorrect (trop grand)");
			}

			if (this.sokoI == -1 || this.sokoJ == -1) {
				throw new IllegalArgumentException(
						"Le fichier passé en paramètre est au mauvais"
								+ " format : il n'y a pas de Soko (@)");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				buffLevel.close();
			} catch (IOException e) {
			}

		}

	}

}