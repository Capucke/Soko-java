package menus.levelMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class LevelMenuKeyListener implements KeyListener {

	private LevelMenu levelMenu;
	private PagesMenu menuPages;

	// private ArrayList<LevelMenuItem> levelListe;
	// private ArrayList<PageMenuItem> pageListe;

	private LevelMenuItem[][][] levelMatrix;
	/*
	 * levelMatrix[i][j][p] : - ligne i - colonne j - page p
	 */

	// private int selectedLevel;

	private int selectedPage;
	private int selectedLine;
	private int selectedCol;

	private int lastItemPage;
	private int lastItemLine;
	private int lastItemCol;

	private int nbPages;

	public LevelMenuKeyListener(LevelMenu menu, PagesMenu pages) {
		this.levelMenu = menu;
		this.menuPages = pages;

		ArrayList<LevelMenuItem> levels = this.levelMenu.getLevelListe();

		int nbItemPerLine = this.levelMenu.getNbLevelPerLine();
		int nbItemPerCol = this.levelMenu.getNbLevelPerCol();

		this.nbPages = levels.size() / (nbItemPerCol * nbItemPerLine) + 1;
		this.levelMatrix = new LevelMenuItem[nbItemPerCol][nbItemPerLine][nbPages];
		this.selectedLine = 0;
		this.selectedCol = 0;
		this.selectedPage = 0;

		int i = 0;
		int curLine = 0;
		int curCol = 0;
		int curPage = 0;
		while (i < levels.size()) {
			curPage = i / (nbItemPerCol * nbItemPerLine);
			curLine = (i - (curPage * nbItemPerCol * nbItemPerLine))
					/ nbItemPerLine;
			curCol = i % nbItemPerLine;
			this.levelMatrix[curLine][curCol][curPage] = levels.get(i);
			this.levelMatrix[curLine][curCol][curPage].setSelected(false);
			i++;
		}
		this.lastItemPage = curPage;
		this.lastItemLine = curLine;
		this.lastItemCol = curCol;

		this.levelMatrix[0][0][0].setSelected(true);

	}

	private int switchPage(int wantedPage) {
		int nbItemPerPage = this.levelMenu.getNbLevelPerLine()
				* this.levelMenu.getNbLevelPerCol();
		int newPage = this.menuPages.switchPage(wantedPage, nbItemPerPage);
		this.levelMenu.switchPage(newPage);
		return newPage;
	}

	private void switchselectedLevel(int newSelectedLine, int newSelectedCol,
			int newSelectedPage) {

		int oldSelectedPage = this.selectedPage;
		int oldSelectedLine = this.selectedLine;
		int oldSelectedCol = this.selectedCol;

		if (newSelectedPage == this.selectedPage) {
			this.levelMatrix[this.selectedLine][this.selectedCol][this.selectedPage]
					.repaint();
			this.levelMatrix[newSelectedLine][newSelectedCol][newSelectedPage]
					.repaint();
		} else {
			newSelectedPage = this.switchPage(newSelectedPage);
		}

		this.selectedLine = newSelectedLine;
		this.selectedCol = newSelectedCol;
		this.selectedPage = newSelectedPage;

		this.levelMatrix[oldSelectedLine][oldSelectedCol][oldSelectedPage]
				.setSelected(false);
		this.levelMatrix[this.selectedLine][this.selectedCol][this.selectedPage]
				.setSelected(true);

		this.levelMenu.revalidate();
		this.levelMenu.repaint();
	}

	private void switchselectedLevel(int verticalDiff, int horizontalDiff) {
		int oldSelectedLine = this.selectedLine;
		int oldSelectedCol = this.selectedCol;
		int oldSelectedPage = this.selectedPage;

		int newSelectedLine = this.selectedLine;
		int newSelectedCol = this.selectedCol;
		int newSelectedPage = this.selectedPage;

		int nbItemTotal = this.levelMenu.getLevelListe().size();

		int nbItemPerLine = this.levelMenu.getNbLevelPerLine();
		int nbItemPerCol = this.levelMenu.getNbLevelPerCol();

		if (verticalDiff == 0) {
			// déplacement horizontal : level := level + horizonntalDiff

			// if ((horizontalDiff <= 0 & (oldSelectedCol >= -horizontalDiff))
			// || (horizontalDiff > 0 & (oldSelectedCol <= (nbItemPerLine - 1)
			// - horizontalDiff))) {
			// // opération simple
			// newSelectedCol = oldSelectedCol + horizontalDiff;
			//
			// } else {
			// on recherche le niveau directement suivant/précédent (quitte
			// à
			// changer de ligne ou de page
			int nbItemPerPage = nbItemPerLine * nbItemPerCol;
			int index = (oldSelectedPage * nbItemPerPage)
					+ (oldSelectedLine * nbItemPerLine)
					+ (oldSelectedCol % nbItemPerLine);

			int newIndex = index + horizontalDiff;
			while (newIndex < 0) {
				newIndex += nbItemTotal;
			}
			while (newIndex >= nbItemTotal) {
				newIndex -= nbItemTotal;
			}

			newSelectedPage = newIndex / nbItemPerPage;
			newSelectedLine = (newIndex - (newSelectedPage * nbItemPerPage))
					/ nbItemPerLine;
			newSelectedCol = newIndex % nbItemPerLine;

		} else if (horizontalDiff == 0) {
			// déplacement vertial : level := level + verticalDiff*nbItemPerLine
			int nbItemInCurCol;
			if (this.selectedPage != this.lastItemPage) {
				// on est sur une page complète
				nbItemInCurCol = nbItemPerCol;
			} else if (this.selectedCol <= this.lastItemCol) {
				// on est sur une colonne qui contient lastItemLine éléments
				nbItemInCurCol = this.lastItemLine + 1;
			} else {
				// on est sur une colonne qui contient (lastItemLine - 1) éléments
				nbItemInCurCol = this.lastItemLine;
			}
			newSelectedLine = (oldSelectedLine + verticalDiff + nbItemInCurCol)
					% nbItemPerCol;
		} else {
			// cas horizontalDiff et verticalDiff sont tous les deux != 0
			throw new IllegalArgumentException(
					"Problème déplacement horizontal ET vertical dans le menu");
		}

		this.switchselectedLevel(newSelectedLine, newSelectedCol,
				newSelectedPage);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_UP:
			this.switchselectedLevel(-1, 0);
			break;
		case KeyEvent.VK_DOWN:
			this.switchselectedLevel(1, 0);
			break;
		case KeyEvent.VK_LEFT:
			this.switchselectedLevel(0, -1);
			break;
		case KeyEvent.VK_RIGHT:
			this.switchselectedLevel(0, 1);
			break;
		case KeyEvent.VK_N:
			this.switchselectedLevel(0, 0, this.selectedPage + 1);
			break;
		case KeyEvent.VK_P:
			this.switchselectedLevel(0, 0, this.selectedPage - 1);
			break;
		case KeyEvent.VK_ENTER:
			this.levelMatrix[this.selectedLine][this.selectedCol][this.selectedPage]
					.actionIfSelected();
			break;
		default:
			return;
		}
		return;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

}
