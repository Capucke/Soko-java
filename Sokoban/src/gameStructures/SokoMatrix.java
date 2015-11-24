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
	
	private int initSokoI = -1 ;
	private int initSokoJ = -1 ;

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
		this.sokoI = this.initSokoI ;
		this.sokoJ = this.initSokoJ ;
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
	public void move(int diffI, int diffJ) {
		if (!this.canMove(diffI, diffJ)) {
			return;
		}

		int currObj = this.curMat.getObj(sokoI, sokoJ);
		int nextObj = this.curMat.getObj(sokoI + diffI, sokoJ + diffJ);

		/*
		 * -EMPTY -SOKO BAG -GOAL GOAL_OK -SOKO_ON_GOAL -WALL
		 */
		if (nextObj == BAG || nextObj == GOAL_OK) {
			int nextNextObj = this.curMat.getObj(sokoI + (2 * diffI), sokoJ
					+ (2 * diffJ));

			switch (nextObj) {
			case BAG:
				switch (nextNextObj) {
				case EMPTY:
					this.curMat.setObj(sokoI + (2 * diffI),
							sokoJ + (2 * diffJ), BAG);
					this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, EMPTY);
					break;
				case GOAL:
					this.curMat.setObj(sokoI + (2 * diffI),
							sokoJ + (2 * diffJ), GOAL_OK);
					this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, EMPTY);
					this.nbGoalOk++;
					break;
				default:
					throw new IllegalStateException(
							"Impossible : objet incompatible avec canMoveObj() en ("
									+ (sokoI + (2 * diffI)) + ", "
									+ (sokoJ + (2 * diffJ)) + ")\n"
									+ "valeur attendue : EMPTY ou GOAL");
				}
				break;
			case GOAL_OK:
				switch (nextNextObj) {
				case EMPTY:
					this.curMat.setObj(sokoI + (2 * diffI),
							sokoJ + (2 * diffJ), BAG);
					this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, GOAL);
					this.nbGoalOk--;
					break;
				case GOAL:
					this.curMat.setObj(sokoI + (2 * diffI),
							sokoJ + (2 * diffJ), GOAL_OK);
					this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, GOAL);
					break;
				default:
					throw new IllegalStateException(
							"Impossible : objet incompatible avec canMoveObj() en ("
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

		if (this.curMat.getObj(sokoI + diffI, sokoJ + diffJ) == GOAL) {
			this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, SOKO_ON_GOAL);
		} else {
			this.curMat.setObj(sokoI + diffI, sokoJ + diffJ, SOKO);
		}

		this.sokoI = this.sokoI + diffI;
		this.sokoJ = this.sokoJ + diffJ;
	}

	/**
	 * Procédure pour déterminer si le personnage Soko peut bouger vers le haut
	 * 
	 * @return true si Soko peut bouger vers le heut, false sinon
	 */
	public boolean canMove(int diffI, int diffJ) {

		int nextSokoI = this.sokoI + diffI;
		int nextSokoJ = this.sokoJ + diffJ;

		if (nextSokoI < 0 || nextSokoJ < 0) {
			return false;
		}
		int nextObj = this.curMat.getObj(nextSokoI, nextSokoJ);

		boolean canMove;

		switch (nextObj) {
		case WALL:
			canMove = false;
			break;
		case EMPTY:
		case GOAL:
			canMove = true;
			break;
		case SOKO:
		case SOKO_ON_GOAL:
			throw new IllegalStateException(
					"Impossible : il y a 2 soko sur le plateau !");
		case BAG:
		case GOAL_OK:
			canMove = this.canMoveObj(sokoI + diffI, sokoJ + diffJ, diffI,
					diffJ);
			break;
		default:
			throw new IllegalStateException(
					"Impossible : il y a un état qui ne représente pas un objet!");
		}

		return canMove;

	}

	/**
	 * Procédure pour déterminer si un objet placé aux coordonnées (nextI,nextJ)
	 * (qui sont les prochaines coordonnées du personnage Soko) aux coordonnées
	 * (nextNextI,nextNextJ)
	 * 
	 * @return true si l'objet peut être déplacé, false sinon
	 */
	private boolean canMoveObj(int objI, int objJ, int diffI, int diffJ) {

		int nextObjI = objI + diffI;
		int nextObjJ = objJ + diffJ;

		if (nextObjI < 0 || nextObjJ < 0) {
			return false;
		}
		int nextObj = this.curMat.getObj(nextObjI, nextObjJ);

		boolean canMove;

		switch (nextObj) {
		case WALL:
		case BAG:
		case GOAL_OK:
			canMove = false;
			break;
		case EMPTY:
		case GOAL:
			canMove = true;
			break;
		case SOKO:
		case SOKO_ON_GOAL:
			throw new IllegalStateException(
					"Impossible : il y a 2 soko sur le plateau !");
		default:
			throw new IllegalStateException(
					"Impossible : il y a un état qui ne représente pas un objet!");
		}
		return canMove;
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
						if (this.initSokoI == -1 & this.initSokoJ == -1) {
							this.initSokoI = i;
							this.initSokoJ = j;
						} else {
							throw new IllegalArgumentException(
									"Le fichier passé en paramètre est au mauvais"
											+ " format : il y a deux Soko (@ ou +)");
						}
					} else if (currChar == '+') {
						this.startMat.setObj(i, j, SOKO_ON_GOAL);
						this.nbGoal += 1;
						if (this.initSokoI == -1 & this.initSokoJ == -1) {
							this.initSokoI = i;
							this.initSokoJ = j;
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

			if (this.initSokoI == -1 || this.initSokoJ == -1) {
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