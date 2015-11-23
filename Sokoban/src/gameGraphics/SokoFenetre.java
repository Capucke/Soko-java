package gameGraphics;

import gameGraphics.DefaultDisplayer;
import gameGraphics.Simulable;
import gameGraphics.SokoPanel;
import gameDisplayer.ImageElement;
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import javax.swing.JFrame;
//import javax.swing.JScrollPane;
import javax.swing.UIManager;


public class SokoFenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    //private JScrollPane scrollPane;
    private SokoPanel sokoPanel;
    
    private int panelWidth;
    private int panelHeight;
    private Simulable displayer;
    
    
    public SokoFenetre() {
        this(800, 800, Color.MAGENTA);
    }

    public SokoFenetre(int width, int height, Color bgColor) {
        this(width, height, bgColor, new DefaultDisplayer());
    }

    public SokoFenetre(int width, int height, Color bgColor, Simulable displayer) {
        super("Sokoban - Judith ;)");
        this.setSimulable(displayer);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.sokoPanel = new SokoPanel(width, height, bgColor);
        // this.scrollPane = new JScrollPane(this.sokoPanel);
        // this.scrollPane.setPreferredSize(new Dimension(Math.min(800, width), Math.min(600, height)));
        this.panelWidth = width;
        this.panelHeight = height;
        this.sokoPanel.setBackground(bgColor);
        
        this.getContentPane().setLayout(new BorderLayout());
        
        // à commenter pour remettre le scroll
        this.getContentPane().add(this.sokoPanel, "Center");
        
        // à décommenter pour remettre le scroll
        //this.getContentPane().add(this.scrollPane, "Center");
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void setSimulable(Simulable displayer) {
        this.displayer = displayer;
    }
    
    public SokoPanel getSokoPanel(){
    	return this.sokoPanel;
    }
    
    public int getPanelWidth() {
        return this.panelWidth;
    }

    public int getPanelHeight() {
        return this.panelHeight;
    }

    public void addImageElement(ImageElement e) {
        this.sokoPanel.addImageElement(e);
    }

    public void reset() {
        this.sokoPanel.reset();
    }

    public void display() {
        this.displayer.display();
        this.sokoPanel.repaint();
        this.repaint();
    }

}
