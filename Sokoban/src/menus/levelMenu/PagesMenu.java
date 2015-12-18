package menus.levelMenu;

import filesManagement.ListeLevels;
import gameGraphics.SokoFenetre;
import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
import java.util.ArrayList;
//import java.awt.FlowLayout;

//import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PagesMenu extends JPanel {

	private static final long serialVersionUID = 14L;
	private SokoFenetre fen;

	private ArrayList<PageMenuItem> pageListe;
	
	private int selectedPage;

	public PagesMenu(Color bg, SokoFenetre window, int nbItemPerPage) {
		super();

		this.setBackground(bg);
		this.fen = window;

		this.initPages(nbItemPerPage);

		JPanel pagesPanel = new JPanel();
		pagesPanel.setBackground(bg);
		
		for (int i = 0; i < this.pageListe.size(); i++) {
			pagesPanel.add(this.pageListe.get(i));
		}
		
		PeachPanel peach = new PeachPanel(bg, window);
		
		this.add(peach.getEquiPanel(), BorderLayout.WEST);
		this.add(pagesPanel, BorderLayout.CENTER);
		this.add(peach, BorderLayout.EAST);

	}

	private void initPages(int nbItemPerPage) {
		this.pageListe = new ArrayList<PageMenuItem>(3);
		this.selectedPage = 0;

		int nbLevels = ListeLevels.NB_LEVELS;

		int nbPages = nbLevels / nbItemPerPage + 1;
		this.pageListe.add(new PageMenuItem(this.fen, this.getBackground(), 0,
				true));
		for (int p = 1; p < nbPages; p++) {
			this.pageListe.add(new PageMenuItem(this.fen, this.getBackground(),
					p));
		}

	}

	protected int switchPage(int wantedPage, int nbItemPerPage) {
		/*
		 * Renvoie le numéro de a age sur laquelle on se trouve réellement
		 */

		if (wantedPage == this.selectedPage) {
			return this.selectedPage;
		}

		int pageNum;

		if (wantedPage >= this.pageListe.size()) {
			pageNum = 0;
		} else if (wantedPage < 0) {
			pageNum = this.pageListe.size() - 1;
		} else {
			pageNum = wantedPage;
		}
		this.pageListe.get(this.selectedPage).setSelected(false);
		this.pageListe.get(pageNum).setSelected(true);
		this.pageListe.get(this.selectedPage).repaint();
		this.pageListe.get(pageNum).repaint();
		this.selectedPage = pageNum;

		return this.selectedPage;

	}

	public ArrayList<PageMenuItem> getPageListe() {
		return this.pageListe;
	}

}
