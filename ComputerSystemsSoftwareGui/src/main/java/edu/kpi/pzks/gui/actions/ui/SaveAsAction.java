package edu.kpi.pzks.gui.actions.ui;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.io.GraphSaver;
import edu.kpi.pzks.gui.io.impl.XmlGraphSaver;
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
public class SaveAsAction extends MainAction {

    private GraphSaver saver = new XmlGraphSaver();

    public SaveAsAction(MainFrame mainFrame) {
        super(mainFrame);
//        this.putValue(NAME, "Save as");
//        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/SaveAs.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter defaultTaskFileFilter = new GraphFileFilter(FileExtension.task);
        fileChooser.addChoosableFileFilter(defaultTaskFileFilter);
        fileChooser.addChoosableFileFilter(new GraphFileFilter(FileExtension.system));
        fileChooser.setFileFilter(defaultTaskFileFilter);
        int returnVal = fileChooser.showSaveDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                try {
                    FileExtension selectedExt = ((GraphFileFilter) fileChooser.getFileFilter()).getExtension();
                    saveGraph(selectedExt, selectedFile);
                    //graphPanel.setChanged(false);
                    //mainFrame.setTitle(file.getName() + " - edited");
                } catch (GraphException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error while saving graph to file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void saveGraph(FileExtension selectedExt, File selectedFile) throws GraphException {
        GraphPanel graphPanel = null;
        if (selectedExt.equals(FileExtension.task)) {
            graphPanel = mainFrame.getTaskPanel();
        } else if (selectedExt.equals(FileExtension.system)) {
            graphPanel = mainFrame.getSystemPanel();
        }
        selectedFile = checkExtension(selectedFile, selectedExt);
        saver.saveToFile(graphPanel.getGraphView(), selectedFile);
    }

    private File checkExtension(File selectedFile, FileExtension selectedExt) {
        String neededExt = "." + selectedExt.toString();
        if (!selectedFile.getAbsolutePath().endsWith(neededExt)) {
            return new File(selectedFile.getAbsolutePath() + neededExt);
        }
        return selectedFile;
    }
}
