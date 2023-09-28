package com.ufc.blog;

import com.ufc.blog.dao.WriteUserCsv;

public class BlogApplication {

	public static void main(String[] args) {
		WriteUserCsv writer = new WriteUserCsv();
		writer.newFile("data.csv");
	}

}
