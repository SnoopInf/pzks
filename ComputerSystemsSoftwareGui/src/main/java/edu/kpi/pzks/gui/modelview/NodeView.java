package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Node;
import java.awt.Point;

/**
 *
 * @author Aloren
 */
public interface NodeView extends GraphObjectView {

    	public Point getUpperLeftCorner();
	public void setUpperLeftCorner(Point point);
	public int getWidth();
	public int getHeight();
	public String getName();
	public Node getNode();

}
