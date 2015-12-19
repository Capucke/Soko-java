package menus.levelMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class LevelMenuKeyListener implements KeyListener {

	private LevelMenu levelMenu;
	private PagesMenu menuPages;

	private LevelMenuItem[][][] levelMatrix;
	/*
	 * levelMatrix[p][i][j] : - page p - ligne i - colonne j
	 */

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

//		ArrayList<LevelMenuItem> levels = this.levelMenu.getLevelListe();
		ArrayList<LevelMenuItem> levels = LevelMenu.getLevelListe();

		int nbItemPerLine = this.levelMenu.getNbLevelPerLine();
		int nbItemPerCol = this.levelMenu.getNbLevelPerCol();

		this.nbPages = levels.size() / (nbItemPerCol * nbItemPerLine) + 1;
		this.levelMatrix = new LevelMenuItem[nbPages][nbItemPerCol][nbItemPerLine];
		this.selectedPage = 0;
		this.selectedLine = 0;
		this.selectedCol = 0;

		int i = 0;
		int curPage = 0;
		int curLine = 0;
		int curCol = 0;
		while (i < levels.size()) {
			curPage = i / (nbItemPerCol * nbItemPerLine);
			curLine = (i - (curPage * nbItemPerCol * nbItemPerLine))
					/ nbItemPerLine;
			curCol = i % nbItemPerLine;
			this.levelMatrix[curPage][curLine][curCol] = levels.get(i);
			this.levelMatrix[curPage][curLine][curCol].setSelected(false);
			i++;
		}
		this.lastItemPage = curPage;
		this.lastItemLine = curLine;
		this.lastItemCol = curCol;

		this.levelMatrix[0][0][0].setSelected(true);

	}

	private void switchSelectedLevel(int wantedPage, int wantedLine,
			int wantedCol) {

		int oldSelectedPage = this.selectedPage;
		int oldSelectedLine = this.selectedLine;
		int oldSelectedCol = this.selectedCol;

		int nbItemPerPage = this.levelMenu.getNbLevelPerLine()
				* this.levelMenu.getNbLevelPerCol();

		int newSelectedPage = this.menuPages.switchPage(wantedPage,
				nbItemPerPage);

		if (newSelectedPage == this.lastItemPage
				& ((wantedLine == this.lastItemLine & wantedCol > this.lastItemCol) || wantedLine > this.lastItemLine)) {
			this.selectedPage = this.menuPages.switchPage(0, nbItemPerPage);
			this.selectedLine = 0;
			this.selectedCol = 0;
		} else {
			this.selectedPage = newSelectedPage;
			this.selectedLine = wantedLine;
			this.selectedCol = wantedCol;
		}

		if (this.selectedPage != oldSelectedPage) {
			this.levelMenu.switchPage(this.selectedPage);
		}

		this.levelMatrix[oldSelectedPage][oldSelectedLine][oldSelectedCol]
				.setSelected(false);
		this.levelMatrix[this.selectedPage][this.selectedLine][this.selectedCol]
				.setSelected(true);
		this.levelMatrix[oldSelectedPage][oldSelectedLine][oldSelectedCol]
				.repaint();
		this.levelMatrix[this.selectedPage][this.selectedLine][this.selectedCol]
				.repaint();

		this.levelMenu.revalidate();
		this.levelMenu.repaint();
	}

	private void switchSelectedLevel(int verticalDiff, int horizontalDiff) {
		int oldSelectedPage = this.selectedPage;
		int oldSelectedLine = this.selectedLine;
		int oldSelectedCol = this.selectedCol;

		int newSelectedPage = this.selectedPage;
		int newSelectedLine = this.selectedLine;
		int newSelectedCol = this.selectedCol;

//		int nbItemTotal = this.levelMenu.getLevelListe().size();
		int nbItemTotal = LevelMenu.getLevelListe().size();

		int nbItemPerLine = this.levelMenu.getNbLevelPerLine();
		int nbItemPerCol = this.levelMenu.getNbLevelPerCol();

		if (verticalDiff == 0) {
			// déplacement horizontal : level := level + horizonntalDiff

			if ((horizontalDiff <= 0 & (oldSelectedCol >= -horizontalDiff))
					|| (horizontalDiff > 0 & (oldSelectedCol <= (nbItemPerLine - 1)
							- horizontalDiff))) {
				// opération simple
				newSelectedCol = oldSelectedCol + horizontalDiff;

			} else {
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
			}

		} else if (horizontalDiff == 0) {
			// déplacement vertial : level := level + verticalDiff*nbItemPerLine
			int nbItemInCurCol;
			if (this.selectedPage != this.lastItemPage) {
				// on est sur une page complète
				nbItemInCurCol = nbItemPerCol;
			} else if (this.selectedCol <= this.lastItemCol) {
				// on est sur une colonne qui contient lastItemLine+1 éléments
				nbItemInCurCol = this.lastItemLine + 1;
			} else {
				// on est sur une colonne qui contient lastItemLine
				// éléments
				nbItemInCurCol = this.lastItemLine;
			}
			newSelectedLine = (oldSelectedLine + verticalDiff + nbItemInCurCol)
					% nbItemInCurCol;
		} else {
			// cas horizontalDiff et verticalDiff sont tous les deux != 0
			throw new IllegalArgumentException(
					"Problème déplacement horizontal ET vertical dans le menu");
		}

		this.switchSelectedLevel(newSelectedPage, newSelectedLine,
				newSelectedCol);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_UP:
			this.switchSelectedLevel(-1, 0);
			break;
		case KeyEvent.VK_DOWN:
			this.switchSelectedLevel(1, 0);
			break;
		case KeyEvent.VK_LEFT:
			this.switchSelectedLevel(0, -1);
			break;
		case KeyEvent.VK_RIGHT:
			this.switchSelectedLevel(0, 1);
			break;
		case KeyEvent.VK_N:
			this.switchSelectedLevel(this.selectedPage + 1, 0, 0);
			break;
		case KeyEvent.VK_P:
			this.switchSelectedLevel(this.selectedPage - 1, 0, 0);
			break;
		case KeyEvent.VK_ENTER:
			this.levelMatrix[this.selectedPage][this.selectedLine][this.selectedCol]
					.actionIfSelected();
			break;
		case KeyEvent.VK_ESCAPE:
			this.levelMenu.getFenetre().displayFrontMenu();
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
