package edu.kpi.pzks.gui.utils;

import java.awt.Font;
import java.awt.geom.Point2D;

/**
 *
 * @author Aloren
 */
public class PaintUtils {

    public static int yCor(int len, double dir) {
        return (int) (len * Math.cos(dir));
    }

    public static int xCor(int len, double dir) {
        return (int) (len * Math.sin(dir));
    }

    public static Font getFont() {
        String fontFamily = CONSTANTS.FONT_FAMILY;
        int fontSize = CONSTANTS.FONT_SIZE;
        int fontWeight = CONSTANTS.FONT_WEIGHT;
        return new Font(fontFamily, fontWeight, fontSize);
    }

    public static Font getIdFont() {
        String fontFamily = CONSTANTS.FONT_ID_FAMILY;
        int fontSize = CONSTANTS.FONT_ID_SIZE;
        int fontWeight = CONSTANTS.FONT_ID_WEIGHT;
        return new Font(fontFamily, fontWeight, fontSize);
    }

    /**
     * Returns the length of a line ensuring it is not too small to render.
     */
    public static double getLength(double start, double end) {
        double length = end - start;
        // make sure dx is not zero or too small
        if (length < 0.01 && length > -0.01) {
            if (length > 0) {
                length = 0.01;
            } else if (length < 0) {
                length = -0.01;
            }
        }
        return length;
    }

    /**
     * Finds the intersection point between link and node.
     */
    public static Point2D.Double getEllipseIntersectionPoint(double theta,
            double ellipseWidth, double ellipseHeight) {
        double halfW = ellipseWidth / 2.0;
        double halfH = ellipseHeight / 2.0;
        double tanTheta = Math.tan(theta);

        double x = (halfW * halfH) / Math.sqrt(Math.pow(halfH, 2)
                + Math.pow(halfW, 2) * Math.pow(tanTheta, 2));
        if ((theta > Math.PI / 2.0 && theta < 1.5 * Math.PI)
                || (theta < -Math.PI / 2.0 && theta > -1.5 * Math.PI)) {
            x = -x;
        }
        double y = tanTheta * x;
        return new Point2D.Double(x, y);
    }
}
