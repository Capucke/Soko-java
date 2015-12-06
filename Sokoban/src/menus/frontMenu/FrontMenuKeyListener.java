package menus.frontMenu;

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
	
	private void switchSelectedItem(int verticalDiff){
		/*
		 * verticalDiff < 0 : on sélectionne un élément plus haut
		 * verticalDiff > 0 : on sélectionne un élément plus bas
		 */
		int newSelectedItem;
		int oldSelectedItem;
		
		oldSelectedItem = this.selectedItem ;
		newSelectedItem = (this.itemListe.size() + this.selectedItem + verticalDiff)%this.itemListe.size();
		
		this.itemListe.get(oldSelectedItem).setSelected(false);
		this.itemListe.get(newSelectedItem).setSelected(true);
		this.itemListe.get(oldSelectedItem).repaint();
		this.itemListe.get(newSelectedItem).repaint();	
		this.selectedItem = newSelectedItem;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP:
			this.switchSelectedItem(-1);
			break;
		case KeyEvent.VK_DOWN:
			this.switchSelectedItem(1);
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
