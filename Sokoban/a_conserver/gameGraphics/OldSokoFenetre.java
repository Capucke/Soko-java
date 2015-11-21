package gameGraphics;

import java.awt.Color;
import javax.swing.JFrame;
import gameGraphics.SokoPanel;

public class OldSokoFenetre extends JFrame {
	
	public OldSokoFenetre(){	   

		//Définit un titre pour notre fenêtre
		this.setTitle("Sokoban");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		this.setSize(600, 400);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocation(0,0);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SokoPanel pan = new SokoPanel();
		//On prévient notre JFrame que notre JPanel sera son content pane
		this.setContentPane(pan);
		pan.setBackground(Color.CYAN);

		
		//Et enfin, la rendre visible        
		this.setVisible(true);
		
	}
	  
}
