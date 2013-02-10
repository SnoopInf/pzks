package edu.kpi.pzks.gui.modelview;

import java.awt.Graphics2D;
import javax.swing.JPopupMenu;

/**
 *
 * @author Aloren
 */
public interface GraphObjectView {

    void paint(Graphics2D g2);

    boolean contains(int x, int y);

    JPopupMenu getPopupMenu();
}
