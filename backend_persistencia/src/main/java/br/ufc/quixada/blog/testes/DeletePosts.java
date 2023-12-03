package br.ufc.quixada.blog.testes;

// import br.ufc.quixada.blog.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import br.ufc.quixada.blog.dao.relational.PostDaoRelacional;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class DeletePosts implements CommandLineRunner {

    @Autowired
    private PostDaoRelacional postDAO;

    @Override
    public void run(String... args) {
        postDAO.deleteAll();
        System.out.println("Deu certo");
    }
    
}