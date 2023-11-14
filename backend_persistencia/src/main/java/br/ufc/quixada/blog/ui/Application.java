package br.ufc.quixada.blog.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("br.ufc.quixada.blog.models")
@EnableJpaRepositories("br.ufc.quixada.blog.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = 
			new SpringApplicationBuilder(Application.class);
		builder.run(args);
		System.out.println("Api is up");
	}
	
		

}
