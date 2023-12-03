package br.ufc.quixada.blog.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "br.ufc.quixada.blog")
@EntityScan("br.ufc.quixada.blog.models")
@EnableJpaRepositories("br.ufc.quixada.blog.dao.relational")
// @EnableMongoRepositories("br.ufc.quixada.blog.dao.mongo")
public class Application {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = 
			new SpringApplicationBuilder(Application.class);
		builder.run(args);
		System.out.println("Api is up");
	}
	
		

}