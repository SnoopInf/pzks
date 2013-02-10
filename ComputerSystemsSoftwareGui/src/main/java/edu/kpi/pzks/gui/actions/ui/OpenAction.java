package edu.kpi.pzks.gui.actions.ui;

import edu.kpi.pzks.gui.io.GraphLoader;
import edu.kpi.pzks.gui.io.impl.XmlGraphLoader;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.ui.MainFrame;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Aloren
 */
public class OpenAction extends MainAction {

    public OpenAction(MainFrame mainFrame) {
        super(mainFrame);
//        this.putValue(NAME, "Open");
//        this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
//        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/Open.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
//        fileChooser.addChoosableFileFilter(new PNFileFilter());
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File xmlFile = fileChooser.getSelectedFile();
                GraphLoader loader = new XmlGraphLoader();
                GraphView loadedGraphView = loader.loadFromFile(xmlFile);
                //TODO set to panel according to the type of file?
//                graphPanel.setGraphView(loadedGraphView);
//                panel.prepareForOpenedGraph(gr);
            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error occured while opening the file."
//                        + "Contact program developer to fix the bug.", "Error",
//                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
