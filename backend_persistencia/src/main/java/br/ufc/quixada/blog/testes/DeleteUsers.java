package br.ufc.quixada.blog.testes;

import br.ufc.quixada.blog.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class DeleteUsers implements CommandLineRunner {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) {
        String email1 = "sarahlindona1@gmail.com",
                email2 = "sarahlindona2@gmail.com",
                email3 = "sarahlindona3@gmail.com";
        userDAO.delete(userDAO.findFirstByEmail(email1).get());
        userDAO.delete(userDAO.findFirstByEmail(email2).get());
        userDAO.delete(userDAO.findFirstByEmail(email3).get());
        System.out.println("Deu certo");
    }
    
}