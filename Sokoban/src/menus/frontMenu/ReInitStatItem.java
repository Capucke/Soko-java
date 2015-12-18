package menus.frontMenu;

import menus.levelMenu.LevelMenu;
import filesManagement.ListeLevels;
import gameGraphics.SokoFenetre;

public class ReInitStatItem extends FrontMenuItem {

	private static final long serialVersionUID = 8L;

	public ReInitStatItem(SokoFenetre sokoFen, boolean isSelected) {
		super(sokoFen,"Réinitialiser les statistiques", 50, isSelected);
	}

	@Override
	public void actionIfSelected() {
		ListeLevels.reInitStat();
		LevelMenu.reInitStat();
	}

}
