package gameGraphics;

import gameGraphics.DefaultSimulator;
import gameGraphics.Simulable;
import gameGraphics.SokoPanel;
import gameSimulator.ImageElement;
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import javax.swing.JFrame;
//import javax.swing.JScrollPane;
import javax.swing.UIManager;


public class SokoFenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    //private JScrollPane scrollPane;
    private SokoPanel simuPanel;
    
    private int panelWidth;
    private int panelHeight;
    private Simulable simulator;
    
    public SokoFenetre() {
        this(500, 500, Color.MAGENTA);
    }

    public SokoFenetre(int width, int height, Color bgColor) {
        this(width, height, bgColor, new DefaultSimulator());
    }

    public SokoFenetre(int width, int height, Color bgColor, Simulable simulator) {
        super("Sokoban - Judith ;)");
        this.setSimulable(simulator);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.simuPanel = new SokoPanel(width, height, bgColor);
        // this.scrollPane = new JScrollPane(this.simuPanel);
        // this.scrollPane.setPreferredSize(new Dimension(Math.min(800, width), Math.min(600, height)));
        this.panelWidth = width;
        this.panelHeight = height;
        this.simuPanel.setBackground(bgColor);
        
        this.getContentPane().setLayout(new BorderLayout());
        
        // à commenter pour remettre le scroll
        this.getContentPane().add(this.simuPanel, "Center");
        
        // à décommenter pour remettre le scroll
        //this.getContentPane().add(this.scrollPane, "Center");
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void setSimulable(Simulable simulator) {
        this.simulator = simulator;
    }

    public int getPanelWidth() {
        return this.panelWidth;
    }

    public int getPanelHeight() {
        return this.panelHeight;
    }

    public void addImageElement(ImageElement e) {
        this.simuPanel.addImageElement(e);
    }

    public void reset() {
        this.simuPanel.reset();
    }

    public void next() {
        this.simulator.next();
        this.simuPanel.repaint();
        this.repaint();
    }

}
