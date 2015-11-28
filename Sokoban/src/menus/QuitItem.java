package menus;

import gameGraphics.SokoFenetre;

import java.awt.Color;

public class QuitItem extends FrontMenuItem {

	private static final long serialVersionUID = 5L;
	private SokoFenetre fen ;

	public QuitItem(Color bg, SokoFenetre sokoFen, boolean isSelected) {
		super(bg, "QUITTER", isSelected);
		this.fen = sokoFen;
	}

	@Override
	public void actionIfSelected() {
		this.fen.dispose();
	}

}
