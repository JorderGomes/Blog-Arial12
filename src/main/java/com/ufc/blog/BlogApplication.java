package com.ufc.blog;

import com.ufc.blog.models.User;
import com.ufc.blog.service.DataService;

public class BlogApplication {

	public static void main(String[] args) {
		DataService service = new DataService();
		User testUser = new User(
				"Eleonore2",
				"xL2|$UaKaev",
				"ebullan0@zdnet.com",
				"Re-engineered solution-oriented projection",
				3);

		service.saveUser(testUser);
	}

}
