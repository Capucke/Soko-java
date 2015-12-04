package menus.frontMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

public class QuitItem extends FrontMenuItem {

	private static final long serialVersionUID = 5L;

	public QuitItem(SokoFenetre sokoFen, Color bg, boolean isSelected) {
		super(sokoFen, bg, "QUITTER", isSelected);
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().dispose();
	}

}
