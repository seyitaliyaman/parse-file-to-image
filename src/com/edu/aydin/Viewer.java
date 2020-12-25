package com.edu.aydin;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Viewer extends JFrame {


    public Viewer(DrawingPanelOld drawingPanel) throws IOException {

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setContentPane(drawingPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,heigth);
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        context.setParser(new ParseTextFile("file.adv")); //for type 1 and 3
        //context.setParser(new ParseBinaryFile("type6.advprog")); //for type 5 and 6
        /**
         * When you need to show type5 and type6 remove // on line 23 and comment line 22 with //
         * **/
        DrawingPanelOld drawingPanel = context.parseFile();
        new Viewer(drawingPanel);
    }
}