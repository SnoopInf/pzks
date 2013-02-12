package edu.kpi.pzks.gui.modelview;

import javax.swing.*;
import java.awt.*;

/**
 * @author Aloren
 */
public interface GraphObjectView {

    void paint(Graphics2D g2);

    boolean contains(int x, int y);

    JPopupMenu getPopupMenu();

    void setWeight(int weight);

    int getWeight();
}
