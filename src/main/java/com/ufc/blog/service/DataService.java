package com.ufc.blog.service;

import com.ufc.blog.dao.ConvertCsvJson;
import com.ufc.blog.dao.ReadUserCsv;
import com.ufc.blog.dao.WriteUserCsv;
import com.ufc.blog.models.User;

public class DataService {
    WriteUserCsv writeUserCsv = new WriteUserCsv();
    ReadUserCsv readUserCsv = new ReadUserCsv();
    ConvertCsvJson convertCsvJson = new ConvertCsvJson();

    public void saveUser(User user) {
        writeUserCsv.saveUser(user);
    }

    public void countLines() {
        readUserCsv.countLines();
    }

}
