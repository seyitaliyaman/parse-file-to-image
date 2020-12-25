package com.edu.aydin;

import java.io.IOException;

public class Context {
    private Parser parser;
    public void setParser(Parser parser){
        this.parser = parser;
    }
    public DrawingPanelOld parseFile() throws IOException {
        return parser.parse();
    }
}