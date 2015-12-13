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

	public SokoDisplayer(SokoGame sokoMat, SokoFenetre sokoFen) {
		this.fen = sokoFen;
		this.setGame(sokoMat);
		this.fen.getSokoGamePanel().addKeyListener(new SokoKeyListener(this));
	}

	public void setGame(SokoGame game) {
		this.soko = game;
	}
	
	public SokoFenetre getFenetre(){
		return this.fen;
	}
	
	public void calculOffSet(SokoGame game){
		this.xSokoGame = (this.fen.getSokoGamePanel().getSokoPanelWidth()/2) - (80 * game.getWidth()/2);
		this.ySokoGame = (this.fen.getSokoGamePanel().getSokoPanelHeight()/2) - (80 * game.getHeight()/2);
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
		this.fen.addImageElement(new ImageElement(80 * j + this.xSokoGame, 80 * i + this.ySokoGame, img, this.fen
				.getSokoGamePanel()));
	}

	private void addTxtFin() {
		int xTxt;
		int yTxt;
		int txtWidth = ImageElement.TXT_COMPLETE.getWidth(this.fen
				.getSokoGamePanel());
		int txtHeight = ImageElement.TXT_COMPLETE.getHeight(this.fen
				.getSokoGamePanel());
		int panelWidth = this.fen.getSokoGamePanel().getWidth();
		int panelHeight = this.fen.getSokoGamePanel().getHeight();

		xTxt = panelWidth / 2 - (txtWidth / 2);
		yTxt = panelHeight / 2 - (txtHeight / 2);

		this.fen.addImageElement(new ImageElement(xTxt, yTxt,
				ImageElement.TXT_COMPLETE, this.fen.getSokoGamePanel()));
	}

	private void drawGame(SokoGame game, boolean afficheTxtComplete) {
		this.calculOffSet(game);
		
		GameMatrix gameMat = game.getCurrMat();
		int temp;

		for (int i = 0; i < gameMat.getNbLines(); i++) {
			for (int j = 0; j < gameMat.getNbCol(); j++) {
				temp = gameMat.getObj(i, j);

//				if (temp == SokoGame.WALL) {
//					this.addImg(i, j, ImageElement.WALL_IMG);
//				} else {
				if (temp != SokoGame.WALL) {
					this.addImg(i, j, ImageElement.EMPTY_IMG);

					if (temp == SokoGame.GOAL || temp == SokoGame.GOAL_OK
							|| temp == SokoGame.SOKO_ON_GOAL) {
						this.addImg(i, j, ImageElement.GOAL_IMG);
					}
					if (temp == SokoGame.BAG || temp == SokoGame.GOAL_OK) {
						this.addImg(i, j, ImageElement.BAG_IMG);
					}
					if (temp == SokoGame.SOKO
							|| temp == SokoGame.SOKO_ON_GOAL) {
						this.addImg(i, j, ImageElement.SOKO_IMG);
					}

				}

			}
		}

		if (afficheTxtComplete) {
			this.addTxtFin();
		}

	}

}
