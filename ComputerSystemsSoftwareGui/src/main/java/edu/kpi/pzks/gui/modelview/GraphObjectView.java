package edu.kpi.pzks.gui.modelview;

import java.awt.Graphics;
import javax.swing.JPopupMenu;

/**
 *
 * @author Aloren
 */
public interface GraphObjectView {

    void setSelected(boolean selected);

    boolean isSelected();

    void paint(Graphics g);

    boolean contains(int x, int y);

    JPopupMenu getPopupMenu();
}
