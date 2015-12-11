package menus.levelMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

import menus.MenuItem;

public class PageMenuItem extends MenuItem {

	private static final long serialVersionUID = 13L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	private int numeroPage;

	public PageMenuItem(SokoFenetre sokoFen, Color bg, int num) {
		this(sokoFen, bg, num, false);
	}

	public PageMenuItem(SokoFenetre sokoFen, Color bg, int num,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, Integer.toString(num), 30, selected);
		this.numeroPage = num;
	}
	
	public void setNumPage(int num){
		this.numeroPage = num;
	}
	
	public int getNumPage(){
		return this.numeroPage;
	}

	@Override
	public void actionIfSelected() {
		return;
	}

}
