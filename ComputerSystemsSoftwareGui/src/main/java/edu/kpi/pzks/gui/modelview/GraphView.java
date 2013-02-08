package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.GraphObject;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.awt.Graphics;

/**
 *
 * @author Aloren
 */
public interface GraphView {

    	public void paint(Graphics g);
	public Graph getGraph();
	public Node getNodeAtPoint(int x, int y);
	public Link getLinkAtPoint(int x, int y);
	public GraphObjectView addViewByElement(GraphObject element);
	public GraphObjectView getViewByElement(GraphObject element);
	public GraphObjectView removeViewByElement(GraphObject element);
}
