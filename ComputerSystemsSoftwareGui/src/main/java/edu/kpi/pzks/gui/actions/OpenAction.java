package edu.kpi.pzks.gui.actions;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.io.GraphLoader;
import edu.kpi.pzks.gui.io.impl.XmlGraphLoader;
import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Aloren
 */
public class OpenAction extends MainAction {

    public OpenAction(GraphPanel graphPanel) {
        super(graphPanel);
        this.putValue(NAME, "Open");
        this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/Open.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException();
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
//        fileChooser.addChoosableFileFilter(new PNFileFilter());
//        int returnVal = fileChooser.showOpenDialog(null);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            try {
//                File xmlFile = fileChooser.getSelectedFile();
//                GraphLoader loader = new XmlGraphLoader();
//                Graph loadedGraph = loader.loadFromFile(xmlFile);
//                graphPanel.panel.setGraph(gr);
//                panel.prepareForOpenedGraph(gr);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error occured while opening the file."
//                        + "Contact program developer to fix the bug.", "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }
}
