package edu.kpi.pzks.gui.modelview;

import java.awt.Point;

/**
 *
 * @author Aloren
 */
public interface ILinkView extends IGraphObjectView {
    
    	public Link getLink();
	public void setPoint(int x, int y);
	public Point getPoint();
    
}
