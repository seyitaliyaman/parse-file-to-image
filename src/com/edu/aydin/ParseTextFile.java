package com.edu.aydin;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ParseTextFile implements Parser {

    private String fileType;
    private int width;
    private int height;
    private int[] imageArray;
    private Color[] colorArray;
    private Scanner scanner;
    private int maxNum;

    public ParseTextFile(String filename){
        try {
            this.scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public DrawingPanelOld parse() throws IOException {

        fileType = scanner.next();
        width = scanner.nextInt();
        height = scanner.nextInt();

        colorArray = new Color[width*height];
        switch (fileType){
            case "1":
                imageArray = new int[width*height];
                for(int i=0; i<width*height; i++){
                    imageArray[i] = scanner.nextInt();
                }
                break;
            case "3":
                maxNum = scanner.nextInt();
                imageArray = new int[width*height*3];
                int k = 0;
                for(int i=0; i<(width*height*3); i++)
                    imageArray[i] = scanner.nextInt();

                for(int j=0; j<(width*height*3); j+=3)
                    colorArray[k++] = new Color(imageArray[j],imageArray[j+1],imageArray[j+2]);
                break;
        }
        return new DrawingPanelOld(width,height,colorArray,imageArray,fileType);
    }
}
