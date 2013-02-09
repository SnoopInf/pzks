package edu.kpi.pzks.gui.io.impl;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.io.GraphLoader;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.modelview.impl.LinkViewImpl;
import edu.kpi.pzks.gui.modelview.impl.NodeViewImpl;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aloren
 */
public class XmlGraphLoader implements GraphLoader {

    protected GraphView graphView;

    //TODO throw specific GraphException, so that client can handle it appropriately
    @Override
    public GraphView loadFromFile(File file) throws GraphException {
        try {
            parseDocument(file);
            return graphView;
        } catch (ParserConfigurationException | SAXException ex) {
            throw new GraphException("Errors in parsing file");
        } catch(FileNotFoundException ex) {
            throw new GraphException(ex);
        } catch(IOException ex) {
            throw new GraphException(ex);
        }
    }

    protected void parseDocument(File file)
            throws ParserConfigurationException, SAXException, FileNotFoundException, IOException {
        graphView = new GraphViewImpl(new Graph());
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        if (file.exists()) {
            sp.parse(file, new GraphXmlHandler());
        } else {
            throw new FileNotFoundException("No file to parse");
        }
    }

    private class GraphXmlHandler extends DefaultHandler {

        private Link link;
        private NodeView nodeView;
        private LinkView linkView;
        private Map<Integer, Link> idLinkMap = new HashMap<>();
        private DualHashBidiMap idNodeMap = new DualHashBidiMap();
        private Map<Integer, NodeView> idNodeViewMap = new HashMap<>();

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("node")) {
                int weigth = Integer.parseInt(attributes.getValue("weigth"));
                Node node = new Node(weigth);
                int id = Integer.parseInt(attributes.getValue("id"));
                idNodeMap.put(id, node);
            }
            
            if (qName.equalsIgnoreCase("link")) {
                int weigth = Integer.parseInt(attributes.getValue("weigth"));
                link = new Link(null, null, weigth);
                int id = Integer.parseInt(attributes.getValue("id"));
                idLinkMap.put(id, link);
            }
            if (qName.equalsIgnoreCase("fromNode")) {
                link.setFromNode((Node) idNodeMap.get(Integer.parseInt(attributes.getValue("id"))));
            } else if (qName.equalsIgnoreCase("toNode")) {
                link.setToNode((Node) idNodeMap.get(Integer.parseInt(attributes.getValue("id"))));
            }

            if (qName.equalsIgnoreCase("nodeView")) {
                final int nodeId = Integer.parseInt(attributes.getValue("nodeId"));
                Node node = (Node) idNodeMap.get(nodeId);
                nodeView = new NodeViewImpl(node);
                idNodeViewMap.put(nodeId, nodeView);
            }
            if (qName.equalsIgnoreCase("point")) {
                double y = Double.parseDouble(attributes.getValue("y"));
                double x = Double.parseDouble(attributes.getValue("x"));
                nodeView.setUpperLeftCorner(new Point((int) x, (int) y));
                graphView.addNodeView(nodeView);
            }

            if (qName.equalsIgnoreCase("linkView")) {
                Link curLink = idLinkMap.get(Integer.parseInt(attributes.getValue("linkId")));
                BidiMap inverseidNodeMap = idNodeMap.inverseBidiMap();
                int idFromNode = (Integer) inverseidNodeMap.get(curLink.getFromNode());
                int idToNode = (Integer) inverseidNodeMap.get(curLink.getToNode());
                NodeView fromNodeView = idNodeViewMap.get(idFromNode);
                NodeView toNodeView = idNodeViewMap.get(idToNode);
                linkView = new LinkViewImpl(fromNodeView, toNodeView, curLink);
            }
            if (qName.equalsIgnoreCase("bendPoint")) {
                double y = Double.parseDouble(attributes.getValue("y"));
                double x = Double.parseDouble(attributes.getValue("x"));
                linkView.setBendPoint((int) x, (int) y);
                graphView.addLinkView(linkView);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
        }
    }
}
