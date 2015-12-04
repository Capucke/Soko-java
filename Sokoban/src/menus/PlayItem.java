package menus;

import gameGraphics.SokoFenetre;

import java.awt.Color;

public class PlayItem extends FrontMenuItem {

	private static final long serialVersionUID = 5L;
	private SokoFenetre fen ;

	public PlayItem(Color bg, SokoFenetre sokoFen, boolean isSelected) {
		super(bg, "JOUER", isSelected);
		this.fen = sokoFen;
	}

	@Override
	public void actionIfSelected() {
		this.fen.displayGame(2);
	}

}
