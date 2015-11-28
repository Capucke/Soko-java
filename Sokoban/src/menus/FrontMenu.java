package menus;

import gameGraphics.SokoFenetre;

import java.awt.Color;
import java.util.ArrayList;
//import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FrontMenu extends JPanel {

	private static final long serialVersionUID = 4L;
	private SokoFenetre fen;

	private ArrayList<FrontMenuItem> itemListe;
	private int selectedItem;

	public FrontMenu(Color bg, SokoFenetre window) {
		super();

		this.setBackground(bg);
		this.fen = window;

		// FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		// flow.setAlignOnBaseline(true);
		// this.setLayout(flow);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.initItems();

		for (int i = 0; i < this.itemListe.size(); i++) {
			this.add(this.itemListe.get(i));
		}

		this.addKeyListener(new FrontMenuKeyListener(this.itemListe, this.selectedItem));
		
	}

	private void initItems() {
		this.itemListe = new ArrayList<FrontMenuItem>(2);
		this.selectedItem = 0;

		FrontMenuItem play = new PlayItem(this.getBackground(), this.fen, true);
		FrontMenuItem quit = new QuitItem(this.getBackground(), this.fen, false);

		this.itemListe.add(play);
		this.itemListe.add(quit);
	}

}
