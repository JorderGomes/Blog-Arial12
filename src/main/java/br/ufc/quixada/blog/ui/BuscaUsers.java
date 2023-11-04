package br.ufc.quixada.blog.ui;

import br.ufc.quixada.blog.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class BuscaUsers implements CommandLineRunner {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) {
        String email1 = "sarahlindona1@gmail.com",
                email2 = "sarahlindona2@gmail.com",
                email3 = "sarahlindona3@gmail.com";
        System.out.println(userDAO.findFirstByEmail(email1).toString());
        System.out.println(userDAO.findFirstByEmail(email2).toString());
        System.out.println(userDAO.findFirstByEmail(email3).toString());
    }
    
}