package menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FrontMenuKeyListener implements KeyListener {
	
	private ArrayList<FrontMenuItem> itemListe;
	private int selectedItem;
	
	public FrontMenuKeyListener(ArrayList<FrontMenuItem> items, int numSelect){
		this.itemListe = items ;
		this.selectedItem = numSelect;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		int newSelectedItem;
		
		switch (key) {
		case KeyEvent.VK_UP:
			this.itemListe.get(selectedItem).setSelected(false);
			this.itemListe.get(selectedItem).repaint();
			newSelectedItem = (this.itemListe.size() + this.selectedItem - 1)%this.itemListe.size();
			this.selectedItem = newSelectedItem;
			this.itemListe.get(selectedItem).setSelected(true);
			this.itemListe.get(selectedItem).repaint();
			break;
		case KeyEvent.VK_DOWN:
			this.itemListe.get(selectedItem).setSelected(false);
			this.itemListe.get(selectedItem).repaint();
			newSelectedItem = (this.selectedItem + 1)%this.itemListe.size();
			this.selectedItem = newSelectedItem;
			this.itemListe.get(selectedItem).setSelected(true);
			this.itemListe.get(selectedItem).repaint();
			break;
		case KeyEvent.VK_ENTER:
			this.itemListe.get(selectedItem).actionIfSelected();
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
