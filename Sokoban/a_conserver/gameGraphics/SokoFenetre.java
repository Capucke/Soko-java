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

/*
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
*/

public class SokoFenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    //private JScrollPane scrollPane;
    private SokoPanel simuPanel;
    
    /*
    private JPanel controlPanel;
    private JButton restartButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JLabel speedLabel;
    private JSpinner speedSpinner;
    private JButton exitButton;
    
    private static final int INIT_SPEED = 100;
    private static final int MIN_SPEED = 10;
    private static final int MAX_SPEED = 10000;
    private static final int STEP_SPEED = 10;
    
    private Timer timer;
    */
    
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
        
        /*
        JPanel spinnerPanel = new JPanel(new GridLayout(1, 2));

        this.speedLabel = new JLabel("Durée d\'un pas de simulation (ms) :");
        this.speedSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 10000, 10));
        spinnerPanel.add(this.speedLabel);
        spinnerPanel.add(this.speedSpinner);
        this.restartButton = new JButton("Début");
        this.playPauseButton = new JButton("Lecture");
        this.nextButton = new JButton("Suivant");
        this.exitButton = new JButton("Quitter");
        this.controlPanel = new JPanel();
        this.controlPanel.add(spinnerPanel);
        this.controlPanel.add(this.restartButton);
        this.controlPanel.add(this.playPauseButton);
        this.controlPanel.add(this.nextButton);
        this.controlPanel.add(this.exitButton);
        */
        
        this.getContentPane().setLayout(new BorderLayout());
        
        // à commenter pour remettre le scroll
        this.getContentPane().add(this.simuPanel, "Center");
        
        // à décommenter pour remettre le scroll
        //this.getContentPane().add(this.scrollPane, "Center");
        
        /*
        this.getContentPane().add(this.controlPanel, "South");
        
        SokoFenetre.DisplayControler controler = new SokoFenetre.DisplayControler(this);

        this.restartButton.setActionCommand("restart");
        this.restartButton.addActionListener(controler);
        this.playPauseButton.setActionCommand("playPause");
        this.playPauseButton.addActionListener(controler);
        this.nextButton.setActionCommand("next");
        this.nextButton.addActionListener(controler);
        this.speedSpinner.addChangeListener(controler);
        this.exitButton.setActionCommand("exit");
        this.exitButton.addActionListener(controler);
        */
        
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

    /*
    private class DisplayControler implements ActionListener, ChangeListener {
        private boolean play;

        public DisplayControler(SokoFenetre display) {
            this.init();
        }

        private void init() {
            this.play = false;
            SokoFenetre.this.timer.stop();
            SokoFenetre.this.timer.restart();
            SokoFenetre.this.timer.stop();
            SokoFenetre.this.playPauseButton.setText("Lecture");
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "playPause") {
                if(this.play) {
                    SokoFenetre.this.timer.stop();
                    SokoFenetre.this.playPauseButton.setText("Lecture");
                } else {
                    SokoFenetre.this.timer.restart();
                    SokoFenetre.this.playPauseButton.setText("Pause");
                }

                this.play = !this.play;
            } else if(e.getActionCommand() == "next") {
                SokoFenetre.this.next();
            } else if(e.getActionCommand() == "restart") {
                this.init();
                SokoFenetre.this.simulator.restart();
                SokoFenetre.this.simuPanel.repaint();
                SokoFenetre.this.repaint();
            } else if(e.getActionCommand() == "exit") {
                System.exit(0);
            }

        }

        public void stateChanged(ChangeEvent e) {
            SokoFenetre.this.timer.setDelay(((SpinnerNumberModel)SokoFenetre.this.speedSpinner.getModel()).getNumber().intValue());
        }
    }
    */


}
