package edu.kpi.pzks.gui.ui.popups;

import edu.kpi.pzks.gui.modelview.GraphObjectView;
import edu.kpi.pzks.gui.utils.STRINGS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author aloren
 */
public abstract class GraphPopup extends JPopupMenu {

    protected final GraphObjectView view;

    public GraphPopup(GraphObjectView view) {
        this.view = view;
        JMenuItem weightItem = new JMenuItem(STRINGS.WEIGHT);
        weightItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeWeightDialog dialog = new ChangeWeightDialog(GraphPopup.this.view);
                dialog.setLocationRelativeTo(GraphPopup.this);
                dialog.setVisible(true);

            }
        });
        add(weightItem);
    }
}
