package br.ufc.quixada.blog.testes;

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
    private UserDaoRelacional userDAO;

    @Override
    public void run(String... args) {
        String email1 = "sarahlindona1@gmail.com",
                email2 = "sarahlindona2@gmail.com",
                email3 = "sarahlindona3@gmail.com";
        userDAO.delete(userDAO.findFirstByEmail(email1));
        userDAO.delete(userDAO.findFirstByEmail(email2));
        userDAO.delete(userDAO.findFirstByEmail(email3));
        System.out.println("Deu certo");
    }
    
}