package com.edu.aydin;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *This class customizes the parse method itself by implementing the Parser interface.
 *This class reads the binary data in the given file.
 *It processes the data and creates visuals according to the information in the file.
 * **/

public class ParseBinaryFile implements Parser {

    private String fileType;
    private int width;
    private int height;
    private int whiteSpace;
    private int maxNum;
    private int[] imageArray;
    private Color[] colorArray;
    private BufferedInputStream bufferedInputStream;

    public ParseBinaryFile(String filename){
        try {
            this.bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DrawingPanelOld parse() throws IOException {
        fileType = getFileType();
        passWhiteSpace();
        width = readNumber();
        passWhiteSpace();
        height = readNumber();
        passWhiteSpace();
        maxNum = readNumber();
        colorArray = new Color[width*height];
        switch (fileType){
            case "5":
                imageArray = new int[width*height];
                for(int i=0; i<(width*height); i++){
                    imageArray[i] = bufferedInputStream.read();
                    colorArray[i] = new Color(imageArray[i],imageArray[i],imageArray[i]);
                }
                break;
            case "6":
                imageArray = new int[width*height*3];
                int k = 0;
                for(int i=0; i<(width*height*3); i++)
                    imageArray[i] = bufferedInputStream.read();

                for(int j=0; j<(width*height*3); j+=3)
                    colorArray[k++] = new Color(imageArray[j],imageArray[j+1],imageArray[j+2]);
                break;
        }

        return new DrawingPanelOld(width,height,colorArray,imageArray,fileType);
    }

    private void passWhiteSpace() {
        try {
            whiteSpace = bufferedInputStream.read();
            while(Character.isWhitespace(whiteSpace))
                whiteSpace = bufferedInputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int readNumber() {
        String wstr = "";
        try {
            while(!Character.isWhitespace(whiteSpace)) {
                wstr = wstr + (whiteSpace-'0');
                whiteSpace = bufferedInputStream.read();
            }
        }catch(IOException e2) {}
        return Integer.parseInt(wstr);
    }

    private String getFileType() {
        byte [] fileType = new byte[1];
        try {
            bufferedInputStream.read(fileType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(fileType);
    }

}
