package edu.kpi.pzks.gui.modelview;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Aloren
 */
public interface INodeView extends IGraphObjectView {
    
    	public Point getUpperLeftCorner();
	public void setUpperLeftCorner(Point point);
	public int getWidth();
	public int getHeight();
	public String getName();
	public Node getNode();
	public List<Node> getInputNodes();
	public List<Node> getOutputNodes();

}
