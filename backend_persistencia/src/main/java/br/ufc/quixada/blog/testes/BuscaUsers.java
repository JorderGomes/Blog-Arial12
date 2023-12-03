package br.ufc.quixada.blog.testes;

import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;
import br.ufc.quixada.blog.models.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class BuscaUsers implements CommandLineRunner {

    @Autowired
    private UserDaoRelacional userDAO;

    @Override
    public void run(String... args) {
        // String email1 = "sarahlindona1@gmail.com",
        //         email2 = "sarahlindona2@gmail.com",
        //         email3 = "sarahlindona3@gmail.com";
        // System.out.println(userDAO.findFirstByEmail(email1).toString());
        // System.out.println(userDAO.findFirstByEmail(email2).toString());
        // System.out.println(userDAO.findFirstByEmail(email3).toString());
        List<Usuario> usuarios = userDAO.findAll();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
    }
    
}