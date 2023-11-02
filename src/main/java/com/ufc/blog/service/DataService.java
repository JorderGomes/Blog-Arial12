package com.ufc.blog.service;

import com.ufc.blog.dao.CompressCsv;
import com.ufc.blog.dao.ConvertCsvJsonXml;
import com.ufc.blog.dao.HashCsv;
import com.ufc.blog.dao.ReadUserCsv;
import com.ufc.blog.dao.WriteUserCsv;
import com.ufc.blog.models.User;

public class DataService {
    WriteUserCsv writeUserCsv = new WriteUserCsv();
    ReadUserCsv readUserCsv = new ReadUserCsv();
    ConvertCsvJsonXml convertCsvJson = new ConvertCsvJsonXml();

    public void saveUser(User user) {
        writeUserCsv.saveUser(user);
    }

    public void countLines() {
        readUserCsv.countLines();
    }

    public void convertCsvToJson() {
        convertCsvJson.convertCsvToJson();
    }

    public void convertCsvToXml() {
        convertCsvJson.convertCsvToXml();
    }

    public void compressCsv() {
        CompressCsv.ziparArquivoCsvDeUsuarios();
    }

    public void showHashCsv() {
        HashCsv.mostrarHashCsv();
    }

}
