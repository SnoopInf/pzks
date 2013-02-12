package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Aloren
 */
public interface GraphView {

    void paint(Graphics2D g2);

    Graph getGraph();

    NodeView getNodeViewAtPoint(int x, int y);

    LinkView getLinkViewAtPoint(int x, int y);

    void addNodeView(NodeView nodeView);

    void addLinkView(LinkView linkView);

    NodeView getNodeView(Node node);

    LinkView getLinkView(Link link);

    void removeNodeView(NodeView nodeView);

    void removeLinkView(LinkView linkView);

    Collection<LinkView> getLinkViews();

    Collection<NodeView> getNodeViews();

    void removeLinkViews(Set<LinkView> selectedLinkViews);

    void removeNodeViews(Set<NodeView> selectedNodeViews);
}
