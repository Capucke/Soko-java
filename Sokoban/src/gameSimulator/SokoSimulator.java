package gameSimulator;

import java.awt.Image;

import gameGraphics.Simulable;
import gameGraphics.SokoFenetre;
import gameStructures.GameMatrix;
import gameStructures.SokoMatrix;
import gameSimulator.ImageElement;

public class SokoSimulator implements Simulable {

	private SokoMatrix soko;
	private SokoFenetre fen;

	public SokoSimulator(SokoMatrix sokoMat, SokoFenetre sokoFen) {
		this.soko = sokoMat;
		this.fen = sokoFen;
	}

	private void addImg(int i, int j, Image img) {
		this.fen.addImageElement(new ImageElement(80 * j, 80 * i, img, this.fen
				.getSokoPanel()));
	}

	private void drawGame(GameMatrix gameMat) {
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
	}

	public void display() {
		GameMatrix currMat = this.soko.getCurrMat();
		this.drawGame(currMat);
	}

	public void restart() {
		this.soko.reInitCurMat();
		this.display();
	}

}
