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

    void paint(Graphics g);

    Graph getGraph();

    NodeView getNodeViewAtPoint(int x, int y);

    LinkView getLinkViewAtPoint(int x, int y);

    void addNodeView(NodeView nodeView);

    void addLinkView(LinkView linkView);

    NodeView getNodeView(Node node);

    LinkView getLinkView(Link link);

    void removeNode(Node node);

    void removeLink(Link link);

    void removeNodeView(NodeView nodeView);

    void removeLinkView(LinkView linkView);
}
