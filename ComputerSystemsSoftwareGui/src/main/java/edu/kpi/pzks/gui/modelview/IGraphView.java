package edu.kpi.pzks.gui.modelview;

import java.awt.Graphics;

/**
 *
 * @author Aloren
 */
public interface IGraphView {
    
    	public void paint(Graphics g);
	public Graph getGraph();
	public Node getNodeAtPoint(int x, int y);
	public Link getLinkAtPoint(int x, int y);
	public IGraphObjectView addViewByElement(GraphObject element);
	public IGraphObjectView getViewByElement(GraphObject element);
	public IGraphObjectView removeViewByElement(GraphObject element);
    
}
