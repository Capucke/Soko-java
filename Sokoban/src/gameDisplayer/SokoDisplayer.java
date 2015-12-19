package gameDisplayer;

import java.awt.Image;
import gameGraphics.SokoFenetre;
import gameStructures.GameMatrix;
import gameStructures.SokoGame;
import gameDisplayer.ImageElement;

public class SokoDisplayer {

	private SokoGame soko;
	private SokoFenetre fen;

	private int xSokoGame;
	private int ySokoGame;
	private int tailleImg;

	public SokoDisplayer(SokoGame sokoMat, SokoFenetre sokoFen) {
		this.fen = sokoFen;
		this.setGame(sokoMat);

		int maxWidth = sokoFen.getSokoGamePanel().getSokoPanelWidth()
				/ (sokoMat.getWidth()-2);
		int maxHeight = sokoFen.getSokoGamePanel().getSokoPanelHeight()
				/ (sokoMat.getHeight()-2);
		this.tailleImg = Math.min(maxWidth, maxHeight);
		if (this.tailleImg >= 80) {
			this.tailleImg = 80;
		} else if (this.tailleImg >= 70) {
			this.tailleImg = 70;
		} else if (this.tailleImg >= 60) {
			this.tailleImg = 60;
		} else if (this.tailleImg >= 50) {
			this.tailleImg = 50;
		} else {
			this.tailleImg = 40;
		}

		this.fen.getSokoGamePanel().addKeyListener(new SokoKeyListener(this));
	}

	public void setGame(SokoGame game) {
		this.soko = game;
	}

	public SokoFenetre getFenetre() {
		return this.fen;
	}

	public void calculOffSet(SokoGame game) {
		this.xSokoGame = (this.fen.getSokoGamePanel().getSokoPanelWidth() / 2)
				- (this.tailleImg * game.getWidth() / 2);
		this.ySokoGame = (this.fen.getSokoGamePanel().getSokoPanelHeight() / 2)
				- (this.tailleImg * game.getHeight() / 2);
	}

	public void move(int diffI, int diffJ) {
		if (!this.soko.isComplete()) {
			this.soko.move(diffI, diffJ);
			this.display();
		}
	}

	public void moveUp() {
		this.move(-1, 0);
	}

	public void moveDown() {
		this.move(1, 0);
	}

	public void moveLeft() {
		this.move(0, -1);
	}

	public void moveRight() {
		this.move(0, 1);
	}

	public void display() {
		this.fen.reset();
		this.drawGame(this.soko, this.soko.isComplete());
	}

	public void restart() {
		this.soko.reInitCurMat();
		this.display();
	}

	public void nextLevel() {
		this.soko.nextLevel();
		this.display();
	}

	public void previousLevel() {
		this.soko.previousLevel();
		this.display();
	}

	private void addImg(int i, int j, Image img) {
		this.fen.addImageElement(new ImageElement(this.tailleImg * j
				+ this.xSokoGame, this.tailleImg * i + this.ySokoGame, img,
				this.fen.getSokoGamePanel()));
	}

	private void addTxtFin() {
		int xTxt;
		int yTxt;
		int txtWidth = ImageElement.TXT_COMPLETE.getWidth(this.fen);
		int txtHeight = ImageElement.TXT_COMPLETE.getHeight(this.fen);

		txtWidth = 612;
		txtHeight = 85;

		int panelWidth = this.fen.getSokoGamePanel().getSokoPanelWidth();
		int panelHeight = this.fen.getSokoGamePanel().getSokoPanelHeight();

		xTxt = panelWidth / 2 - (txtWidth / 2);
		yTxt = panelHeight / 4 - (txtHeight / 4);

		// System.out.println("panelWidth : " + panelWidth);
		// System.out.println("txtWidth : " + txtWidth);
		// System.out.println("xTxt : " + xTxt);
		// System.out.println("panelHeight : " + panelHeight);
		// System.out.println("txtHeight : " + txtHeight);
		// System.out.println("yTxt : " + yTxt);

		this.fen.addImageElement(new ImageElement(xTxt, yTxt,
				ImageElement.TXT_COMPLETE, this.fen.getSokoGamePanel()));
	}
	
	private Image chargeImg(int gameElt){
		return ImageElement.chargeImg(gameElt, this.tailleImg);
	}

	private void drawGame(SokoGame game, boolean jeuTermine) {
		this.calculOffSet(game);

		GameMatrix gameMat = game.getCurrMat();
		int temp;

		Image EmptyImg = this.chargeImg(SokoGame.EMPTY);
		Image GoalImg = this.chargeImg(SokoGame.GOAL);
		Image BagImg = this.chargeImg(SokoGame.BAG);
		Image SokoImg = this.chargeImg(SokoGame.SOKO);

		for (int i = 0; i < gameMat.getNbLines(); i++) {
			for (int j = 0; j < gameMat.getNbCol(); j++) {
				temp = gameMat.getObj(i, j);

				if (temp != SokoGame.WALL) {
					this.addImg(i, j, EmptyImg);

					if (temp == SokoGame.GOAL || temp == SokoGame.GOAL_OK
							|| temp == SokoGame.SOKO_ON_GOAL) {
						this.addImg(i, j, GoalImg);
					}
					if (temp == SokoGame.BAG || temp == SokoGame.GOAL_OK) {
						this.addImg(i, j, BagImg);
					}
					if (temp == SokoGame.SOKO || temp == SokoGame.SOKO_ON_GOAL) {
						this.addImg(i, j, SokoImg);
					}

				}

			}
		}

		if (jeuTermine) {
			this.fen.addImageElement(new ImageElement(0, 0,
					ImageElement.FOND_ETOILE, this.fen.getSokoGamePanel()));
			this.addTxtFin();
		}

	}

}
