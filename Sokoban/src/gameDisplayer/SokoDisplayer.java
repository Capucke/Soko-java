package gameDisplayer;

import java.awt.Image;

import gameGraphics.Displayer;
import gameGraphics.SokoFenetre;
import gameStructures.GameMatrix;
import gameStructures.SokoMatrix;
import gameDisplayer.ImageElement;

public class SokoDisplayer implements Displayer {

	private SokoMatrix soko;
	private SokoFenetre fen;

	public SokoDisplayer(SokoMatrix sokoMat, SokoFenetre sokoFen) {
		this.soko = sokoMat;
		this.fen = sokoFen;
		this.fen.addKeyListener(new SokoKeyListener(this));
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
		GameMatrix currMat = this.soko.getCurrMat();
		this.drawGame(currMat, this.soko.isComplete());
	}

	public void restart() {
		this.soko.reInitCurMat();
		this.display();
	}
	
	public void nextLevel(){
		this.soko.nextLevel();
		this.display();
	}
	
	public void previousLevel(){
		this.soko.previousLevel();
		this.display();
	}

	private void addImg(int i, int j, Image img) {
		this.fen.addImageElement(new ImageElement(80 * j, 80 * i, img, this.fen
				.getSokoPanel()));
	}
	
	private void addTxtFin(){
		int xTxt ;
		int yTxt ;
		int txtWidth = ImageElement.TXT_COMPLETE.getWidth(this.fen.getSokoPanel());
		int txtHeight = ImageElement.TXT_COMPLETE.getHeight(this.fen.getSokoPanel());
		int panelWidth = this.fen.getSokoPanel().getWidth();
		int panelHeight = this.fen.getSokoPanel().getHeight();
		
		xTxt = panelWidth/2 - (txtWidth/2);
		yTxt = panelHeight/2 - (txtHeight/2);
		
		this.fen.addImageElement(new ImageElement(xTxt, yTxt, ImageElement.TXT_COMPLETE, this.fen.getSokoPanel()));
	}

	private void drawGame(GameMatrix gameMat, boolean afficheTxtComplete) {
		int temp;

		for (int i = 0; i < gameMat.getNbLines(); i++) {
			for (int j = 0; j < gameMat.getNbCol(); j++) {
				temp = gameMat.getObj(i, j);

				if (temp == SokoMatrix.WALL) {
					this.addImg(i, j, ImageElement.WALL_IMG);
				} else {
					this.addImg(i, j, ImageElement.EMPTY_IMG);

					if (temp == SokoMatrix.GOAL || temp == SokoMatrix.GOAL_OK
							|| temp == SokoMatrix.SOKO_ON_GOAL) {
						this.addImg(i, j, ImageElement.GOAL_IMG);
					}
					if (temp == SokoMatrix.BAG || temp == SokoMatrix.GOAL_OK) {
						this.addImg(i, j, ImageElement.BAG_IMG);
					}
					if (temp == SokoMatrix.SOKO
							|| temp == SokoMatrix.SOKO_ON_GOAL) {
						this.addImg(i, j, ImageElement.SOKO_IMG);
					}

				}

			}
		}
		
		if (afficheTxtComplete){
			this.addTxtFin();
		}

	}

}
