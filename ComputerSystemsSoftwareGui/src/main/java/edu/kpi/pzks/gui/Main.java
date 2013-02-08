package edu.kpi.pzks.gui;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame frame = new MainFrame(CONSTANTS.MAIN_TITLE);
        frame.setVisible(true);
    }
}
