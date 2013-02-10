package edu.kpi.pzks.gui.actions.ui;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.io.GraphSaver;
import edu.kpi.pzks.gui.io.impl.XmlGraphSaver;
import edu.kpi.pzks.gui.ui.MainFrame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Aloren
 */
public class SaveAsAction extends MainAction {

    public SaveAsAction(MainFrame mainFrame) {
        super(mainFrame);
//        this.putValue(NAME, "Save as");
//        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/SaveAs.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(new EditorFileFilter());
//        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            GraphSaver saver = new XmlGraphSaver();
            try {
                //TODO save graph according to file type?
                saver.saveToFile(mainFrame.getTaskPanel().getGraphView(), file);
                //graphPanel.setChanged(false);
                //mainFrame.setTitle(file.getName() + " - edited");
                //mainFrame.setTitle(file.getName() + " - edited");
            } catch (GraphException ex) {
                Logger.getLogger(SaveAsAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
