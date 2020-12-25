package com.edu.aydin;

import javax.swing.*;
import java.awt.*;


/**
 * This class allows the creation of the visual according to the width, height and color information.
 **/

public class DrawingPanelOld extends JPanel {

    private int width;
    private int height;
    private String fileType;
    private Color color[];
    private int[] imageArray;

    public DrawingPanelOld(int width, int height, Color[] color, int[] imageArray, String fileType) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.imageArray = imageArray;
        this.fileType = fileType;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.fileType) {
            case "1":
                for (int col = 0; col < width; col++) {
                    for (int row = 0; row < height; row++) {
                        g.setColor(imageArray[row * width + col] != 0 ? Color.BLACK : Color.WHITE);
                        g.fillRect(col, row, 1, 1);
                    }
                }
                break;
            default:
                for (int col = 0; col < width; col++) {
                    for (int row = 0; row < height; row++) {
                        g.setColor(color[row * width + col]);
                        g.fillRect(col, row, 1, 1);
                    }
                }
                break;
        }
    }
}
