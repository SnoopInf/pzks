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

            Element graphElement = doc.createElement("graph");
            doc.appendChild(graphElement);

            Graph graph = graphView.getGraph();
            
            Element dataElement = doc.createElement("data");
            Map<Node, Integer> idNodeMap = appendNodes(doc, dataElement, graph);
            Map<Link, Integer> idLinkMap = appendLinks(doc, dataElement, graph, idNodeMap);

            Element viewElement = doc.createElement("view");
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

    private Map<Node, Integer> appendNodes(Document doc, Element dataElement, Graph graph) throws DOMException {
        Element nodesElement = doc.createElement("nodes");
        dataElement.appendChild(nodesElement);
        Map<Node, Integer> idNodeMap = new HashMap<>();
        int nodeId = 0;
        for (Node node : graph.getNodes()) {
            idNodeMap.put(node, nodeId);
            Element nodeElement = doc.createElement("node");
            nodeElement.setAttribute("id", Integer.toString(nodeId));
            nodeElement.setAttribute("weigth", Integer.toString(node.getWeight()));
            nodesElement.appendChild(nodeElement);
            nodeId++;
        }
        return idNodeMap;
    }

    private Map<Link, Integer> appendLinks(Document doc, Element dataElement, Graph graph, Map<Node, Integer> idNodeMap) throws DOMException {
        Map<Link, Integer> idLinkMap = new HashMap<>();
        Element linksElement = doc.createElement("links");
        dataElement.appendChild(linksElement);
        int linkId = 0;
        for (Link link : graph.getLinks()) {
            idLinkMap.put(link, linkId);
            Element linkElement = doc.createElement("link");
            linkElement.setAttribute("id", Integer.toString(linkId));
            linkElement.setAttribute("weigth", Integer.toString(link.getWeight()));
            Node fromNode = link.getFromNode();
            Node toNode = link.getToNode();
            Element fromNodeElement = doc.createElement("fromNode");
            fromNodeElement.setAttribute("id", Integer.toString(idNodeMap.get(fromNode)));

            Element toNodeElement = doc.createElement("toNode");
            toNodeElement.setAttribute("id", Integer.toString(idNodeMap.get(toNode)));
            linkElement.appendChild(fromNodeElement);
            linkElement.appendChild(toNodeElement);
            linksElement.appendChild(linkElement);
            linkId++;
        }
        return idLinkMap;
    }

    private void appendNodeViews(GraphView graphView, Document doc, Element viewElement, Map<Node, Integer> idNodeMap) throws DOMException {
        Collection<NodeView> nodeViews = graphView.getNodeViews();
        Element nodeViewsElement = doc.createElement("nodeViews");
        for (NodeView nodeView : nodeViews) {
            Element nodeViewEl = doc.createElement("nodeView");
            Point point = nodeView.getUpperLeftCorner();
            Element pointEl = doc.createElement("point");
            pointEl.setAttribute("y", Double.toString(point.getY()));
            pointEl.setAttribute("x", Double.toString(point.getX()));
            nodeViewEl.setAttribute("nodeId", Integer.toString(idNodeMap.get(nodeView.getNode())));
            nodeViewEl.appendChild(pointEl);
            nodeViewsElement.appendChild(nodeViewEl);
        }
        viewElement.appendChild(nodeViewsElement);
    }

    private void appendLinkViews(GraphView graphView, Document doc, Element viewElement, Map<Link, Integer> idLinkMap) throws DOMException {
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
