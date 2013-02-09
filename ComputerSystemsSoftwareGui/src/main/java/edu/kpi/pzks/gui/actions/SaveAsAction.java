package edu.kpi.pzks.gui.actions;

import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 *
 * @author Aloren
 */
public class SaveAsAction extends MainAction {

    public SaveAsAction(GraphPanel graphPanel) {
        super(graphPanel);
        this.putValue(NAME, "Save as");
        this.putValue(AbstractAction.SMALL_ICON, new ImageIcon(this.getClass().getResource("/icons/SaveAs.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException();
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(new EditorFileFilter());
//        fileChooser.showSaveDialog(null);
//        File file = fileChooser.getSelectedFile();
//        if (file != null) {
//            GraphSaver saver = new XmlGraphSaver(null);
//            try {
//                saver.saveToFile(file);
//                graphPanel.setChanged(false);
//                mainFrame.setCurrentFile(file);
//                mainFrame.setTitle(file.getName() + " - edited");
//            } catch (FileNotFoundException e1) {
//            } catch (XMLStreamException e1) {
//            }
//        }
    }
}
