package edu.kpi.pzks.gui.io.impl;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.io.GraphSaver;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import java.awt.Point;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlGraphSaver implements GraphSaver {

    public XmlGraphSaver() {
    }

    @Override
    public void saveToFile(GraphView graphView, File file) throws GraphException {
        try {
            if (graphView == null) {
                throw new GraphException("GraphView is null");
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();

            Element graphElement = doc.createElement(XmlConst.GRAPH);
            graphElement.setAttribute(XmlConst.ORIENTED, Boolean.toString(graphView.getGraph().isOriented()));
            graphElement.setAttribute(XmlConst.TYPE, graphView.getType().toString());
            doc.appendChild(graphElement);

            Graph graph = graphView.getGraph();

            Element dataElement = doc.createElement(XmlConst.DATA);
            Map<Node, Integer> idNodeMap = appendNodes(doc, dataElement, graph);
            Map<Link, Integer> idLinkMap = appendLinks(doc, dataElement, graph, idNodeMap);

            Element viewElement = doc.createElement(XmlConst.VIEW);
            appendNodeViews(graphView, doc, viewElement, idNodeMap);
            appendLinkViews(graphView, doc, viewElement, idLinkMap);

            graphElement.appendChild(dataElement);
            graphElement.appendChild(viewElement);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException ex) {
            throw new GraphException("Error while parsing", ex);
        }
    }

    private Map<Node, Integer> appendNodes(Document doc,
            Element dataElement, Graph graph) throws DOMException {
        Element nodesElement = doc.createElement(XmlConst.NODES);
        dataElement.appendChild(nodesElement);
        Map<Node, Integer> idNodeMap = new HashMap<>();
        int nodeId = 0;
        for (Node node : graph.getNodes()) {
            idNodeMap.put(node, nodeId);
            Element nodeElement = doc.createElement(XmlConst.NODE);
            nodeElement.setAttribute(XmlConst.ID, Integer.toString(nodeId));
            nodeElement.setAttribute(XmlConst.WEIGHT, Integer.toString(node.getWeight()));
            nodesElement.appendChild(nodeElement);
            nodeId++;
        }
        return idNodeMap;
    }

    private Map<Link, Integer> appendLinks(Document doc,
            Element dataElement, Graph graph, Map<Node, Integer> idNodeMap) throws DOMException {
        Map<Link, Integer> idLinkMap = new HashMap<>();
        Element linksElement = doc.createElement(XmlConst.LINKS);
        dataElement.appendChild(linksElement);
        int linkId = 0;
        for (Link link : graph.getLinks()) {
            idLinkMap.put(link, linkId);
            Element linkElement = doc.createElement(XmlConst.LINK);
            linkElement.setAttribute(XmlConst.ID, Integer.toString(linkId));
            linkElement.setAttribute(XmlConst.WEIGHT, Integer.toString(link.getWeight()));
            Node fromNode = link.getFromNode();
            Node toNode = link.getToNode();
            Element fromNodeElement = doc.createElement(XmlConst.FROM_NODE);
            fromNodeElement.setAttribute(XmlConst.ID, Integer.toString(idNodeMap.get(fromNode)));

            Element toNodeElement = doc.createElement(XmlConst.TO_NODE);
            toNodeElement.setAttribute(XmlConst.ID, Integer.toString(idNodeMap.get(toNode)));
            linkElement.appendChild(fromNodeElement);
            linkElement.appendChild(toNodeElement);
            linksElement.appendChild(linkElement);
            linkId++;
        }
        return idLinkMap;
    }

    private void appendNodeViews(GraphView graphView, Document doc,
            Element viewElement, Map<Node, Integer> idNodeMap) throws DOMException {
        Collection<NodeView> nodeViews = graphView.getNodeViews();
        Element nodeViewsElement = doc.createElement(XmlConst.NODE_VIEWS);
        for (NodeView nodeView : nodeViews) {
            Element nodeViewEl = doc.createElement(XmlConst.NODE_VIEW);
            Point point = nodeView.getUpperLeftCorner();
            Element pointEl = doc.createElement(XmlConst.POINT);
            pointEl.setAttribute(XmlConst.Y, Double.toString(point.getY()));
            pointEl.setAttribute(XmlConst.X, Double.toString(point.getX()));
            nodeViewEl.setAttribute(XmlConst.NODE_ID, Integer.toString(idNodeMap.get(nodeView.getNode())));
            nodeViewEl.appendChild(pointEl);
            nodeViewsElement.appendChild(nodeViewEl);
        }
        viewElement.appendChild(nodeViewsElement);
    }

    private void appendLinkViews(GraphView graphView, Document doc,
            Element viewElement, Map<Link, Integer> idLinkMap) throws DOMException {
        Collection<LinkView> linkViews = graphView.getLinkViews();
        Element linkViewsElement = doc.createElement("linkViews");
        for (LinkView linkView : linkViews) {
            Element linkViewEl = doc.createElement("linkView");
            Point bendPoint = linkView.getBendPoint();
            Element bendPointEl = doc.createElement("bendPoint");
            bendPointEl.setAttribute("y", Double.toString(bendPoint.getY()));
            bendPointEl.setAttribute("x", Double.toString(bendPoint.getX()));
            linkViewEl.setAttribute("linkId", Integer.toString(idLinkMap.get(linkView.getLink())));
            linkViewEl.appendChild(bendPointEl);
            linkViewsElement.appendChild(linkViewEl);
        }
        viewElement.appendChild(linkViewsElement);
    }
}
