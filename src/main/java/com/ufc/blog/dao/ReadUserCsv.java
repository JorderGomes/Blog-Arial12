package com.ufc.blog.dao;

import java.io.BufferedReader;
import java.io.FileReader;

// Jorder
public class ReadUserCsv {
    public void countLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            int lineCounter = 0;
            while (reader.readLine() != null) {
                lineCounter++;
            }
            lineCounter--;
            System.out.println("Qtd entidades: " + lineCounter);
            reader.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
