package edu.kpi.pzks.gui.ui.popups;

import edu.kpi.pzks.gui.modelview.GraphObjectView;
import edu.kpi.pzks.gui.utils.STRINGS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author aloren
 */
public class ChangeWeightDialog extends JDialog {

    private final GraphObjectView graphObjectView;
    private final JTextField weightField;

    public ChangeWeightDialog(final GraphObjectView graphObjectView) {
        setTitle(STRINGS.CHANGE_WEIGHT);
        this.graphObjectView = graphObjectView;
        this.weightField = new JTextField(Integer.toString(this.graphObjectView.getWeight()), 10);
        JPanel form = new JPanel();
        setLayout(new BorderLayout());
        add(form, BorderLayout.NORTH);
        form.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        form.add(new JLabel(STRINGS.WEIGHT + ": "), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 5);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        form.add(weightField, c);
        JButton okButton = new JButton(STRINGS.OKAY);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!weightField.getText().isEmpty()) {
                    try {
                        int weight = Integer.parseInt(weightField.getText());
                        graphObjectView.setWeight(weight);
                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ChangeWeightDialog.this, "message", "title", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton(STRINGS.CANCEL);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        add(buttonsPanel);
        setSize(200, 100);

//        pack();
    }
}
