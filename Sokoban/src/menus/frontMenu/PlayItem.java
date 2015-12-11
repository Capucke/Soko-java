package menus.frontMenu;

import gameGraphics.SokoFenetre;

public class PlayItem extends FrontMenuItem {

	private static final long serialVersionUID = 8L;

	public PlayItem(SokoFenetre sokoFen, boolean isSelected) {
		super(sokoFen,"JOUER", isSelected);
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().displayLevelMenu();
	}

}
