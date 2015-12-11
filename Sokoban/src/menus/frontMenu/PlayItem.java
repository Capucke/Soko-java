package menus.frontMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

public class PlayItem extends FrontMenuItem {

	private static final long serialVersionUID = 8L;

	public PlayItem(SokoFenetre sokoFen, Color bg, boolean isSelected) {
		super(sokoFen, bg, "JOUER", isSelected);
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().displayLevelMenu();
	}

}
