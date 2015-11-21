//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.DefaultSimulator;
import gui.GraphicalElement;
import gui.Simulable;
import gui.SimulationPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUISimulator extends JFrame {
    private static final long serialVersionUID = 1L;
    private JScrollPane sp;
    private SimulationPanel simuPanel;
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
    private int panelWidth;
    private int panelHeight;
    private Timer timer;
    private Simulable simulator;

    public GUISimulator(int width, int height, Color bgColor) {
        this(width, height, bgColor, new DefaultSimulator());
    }

    public GUISimulator(int width, int height, Color bgColor, Simulable simulator) {
        super("Simulateur de Systèmes Multi-Agents");
        this.timer = new Timer(100, new GUISimulator.TimerListener((GUISimulator.TimerListener)null));
        this.timer.stop();
        this.setSimulable(simulator);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.simuPanel = new SimulationPanel(width, height, bgColor);
        this.sp = new JScrollPane(this.simuPanel);
        this.sp.setPreferredSize(new Dimension(Math.min(800, width), Math.min(600, height)));
        this.panelWidth = width;
        this.panelHeight = height;
        this.simuPanel.setBackground(bgColor);
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
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.sp, "Center");
        this.getContentPane().add(this.controlPanel, "South");
        GUISimulator.DisplayControler controler = new GUISimulator.DisplayControler(this);
        this.restartButton.setActionCommand("restart");
        this.restartButton.addActionListener(controler);
        this.playPauseButton.setActionCommand("playPause");
        this.playPauseButton.addActionListener(controler);
        this.nextButton.setActionCommand("next");
        this.nextButton.addActionListener(controler);
        this.speedSpinner.addChangeListener(controler);
        this.exitButton.setActionCommand("exit");
        this.exitButton.addActionListener(controler);
        this.setDefaultCloseOperation(3);
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

    public void addGraphicalElement(GraphicalElement e) {
        this.simuPanel.addGraphicalElement(e);
    }

    public void reset() {
        this.simuPanel.reset();
    }

    public void next() {
        this.simulator.next();
        this.simuPanel.repaint();
        this.repaint();
    }

    private class DisplayControler implements ActionListener, ChangeListener {
        private boolean play;

        public DisplayControler(GUISimulator display) {
            this.init();
        }

        private void init() {
            this.play = false;
            GUISimulator.this.timer.stop();
            GUISimulator.this.timer.restart();
            GUISimulator.this.timer.stop();
            GUISimulator.this.playPauseButton.setText("Lecture");
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "playPause") {
                if(this.play) {
                    GUISimulator.this.timer.stop();
                    GUISimulator.this.playPauseButton.setText("Lecture");
                } else {
                    GUISimulator.this.timer.restart();
                    GUISimulator.this.playPauseButton.setText("Pause");
                }

                this.play = !this.play;
            } else if(e.getActionCommand() == "next") {
                GUISimulator.this.next();
            } else if(e.getActionCommand() == "restart") {
                this.init();
                GUISimulator.this.simulator.restart();
                GUISimulator.this.simuPanel.repaint();
                GUISimulator.this.repaint();
            } else if(e.getActionCommand() == "exit") {
                System.exit(0);
            }

        }

        public void stateChanged(ChangeEvent e) {
            GUISimulator.this.timer.setDelay(((SpinnerNumberModel)GUISimulator.this.speedSpinner.getModel()).getNumber().intValue());
        }
    }

    private class TimerListener implements ActionListener {
        private TimerListener() {
        }

        public void actionPerformed(ActionEvent e) {
            GUISimulator.this.next();
        }
    }
}
