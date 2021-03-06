package edu.kpi.pzks.gui.actions.ui;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.io.GraphLoader;
import edu.kpi.pzks.gui.io.impl.XmlGraphLoader;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.ui.panels.GraphPanel;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.utils.FileExtension;
import edu.kpi.pzks.gui.ui.utils.GraphFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author Aloren
 */
public class OpenAction extends MainAction {

    private GraphLoader loader = new XmlGraphLoader();

    public OpenAction(MainFrame mainFrame) {
        super(mainFrame);
//        this.putValue(NAME, "Open");
//        this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
//        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/Open.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter defaultTaskFileFilter = new GraphFileFilter(FileExtension.task);
        fileChooser.addChoosableFileFilter(defaultTaskFileFilter);
        fileChooser.addChoosableFileFilter(new GraphFileFilter(FileExtension.system));
        fileChooser.setFileFilter(defaultTaskFileFilter);
        int returnVal = fileChooser.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                FileExtension selectedExt = ((GraphFileFilter) fileChooser.getFileFilter()).getExtension();
                openGraph(selectedExt, selectedFile);
            } catch (GraphException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Error while opening graph!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openGraph(FileExtension selectedExt, File selectedFile) throws GraphException {
        GraphPanel graphPanel = null;
        if (selectedExt.equals(FileExtension.task)) {
            graphPanel = mainFrame.getTaskPanel();
        } else if (selectedExt.equals(FileExtension.system)) {
            graphPanel = mainFrame.getSystemPanel();
        }
        GraphView graphView = loader.loadFromFile(selectedFile);
        graphView.setBounds(graphPanel.getBounds());
        graphPanel.setGraphView(graphView);
        graphPanel.checkGraphIsValid();
//      graphPanel.prepareForOpenedGraph(gr);
        graphPanel.checkSize();
    }
}
