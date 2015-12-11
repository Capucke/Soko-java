package menus.frontMenu;

import gameGraphics.SokoFenetre;

public class QuitItem extends FrontMenuItem {

	private static final long serialVersionUID = 9L;

	public QuitItem(SokoFenetre sokoFen, boolean isSelected) {
		super(sokoFen, "QUITTER", isSelected);
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().dispose();
	}

}
