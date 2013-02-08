package edu.kpi.pzks.gui.ui;

import javax.swing.JFrame;

/**
 *
 * @author Aloren
 */
public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
