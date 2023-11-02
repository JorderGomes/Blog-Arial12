package com.ufc.blog.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ufc.blog.models.User;

// jorder
public class ConvertCsvJsonXml {

    public List<User> serializeCsvToList() {
        List<User> users = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("data.csv");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());

            for (CSVRecord csvRecord : csvParser) {
                // Ler os valores das colunas do CSV e criar um objeto User
                String name = csvRecord.get("name");
                String password = csvRecord.get("password");
                String email = csvRecord.get("email");
                String bio = csvRecord.get("bio");
                float rate = Float.parseFloat(csvRecord.get("rate"));

                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setEmail(email);
                user.setBio(bio);
                user.setRate(rate);

                users.add(user);

            }
            csvParser.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return users;
    }

    public void convertCsvToJson() {
        try {
            List<User> users = this.serializeCsvToList();
            File f = new File("users.json");
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.INDENT_OUTPUT);
            om.writeValue(f, users);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void convertCsvToXml() {
        try {
            List<User> users = this.serializeCsvToList();
            File f = new File("users.xml");
            XmlMapper xm = new XmlMapper();
            xm.enable(SerializationFeature.INDENT_OUTPUT);
            xm.writeValue(f, users);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
