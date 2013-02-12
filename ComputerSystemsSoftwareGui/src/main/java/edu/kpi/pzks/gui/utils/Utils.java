/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kpi.pzks.gui.utils;

import javax.swing.ImageIcon;

/**
 *
 * @author Aloren
 */
public class Utils {
    
        /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}
