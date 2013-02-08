package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Link;
import java.awt.Point;

/**
 *
 * @author Aloren
 */
public interface LinkView extends GraphObjectView {

    	public Link getLink();
	public void setBendPoint(int x, int y);
	public Point getBendPoint();

}
