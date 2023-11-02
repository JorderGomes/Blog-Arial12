package com.ufc.blog.dao;

import java.io.File;
import java.io.FileWriter;

import com.ufc.blog.models.User;

// Jorder
public class WriteUserCsv {

    public void newFile(String filename) {
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("Arquivo criado com sucesso.");
            } else {
                System.out.println("O arquivo já existe.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public void saveUser(User user) {
        try {
            FileWriter writer = new FileWriter("data.csv", true);
            writer.append(user.toStringCsv());
            writer.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
