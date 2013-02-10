package edu.kpi.pzks.gui.ui.tools;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Aloren
 */
public interface Tool extends MouseListener, MouseMotionListener {
    
    void paint(Graphics2D g2);
    
}
