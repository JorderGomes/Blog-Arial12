package br.ufc.quixada.blog.ui.testes;

import br.ufc.quixada.blog.dao.PostDAO;
// import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
// import br.ufc.quixada.blog.models.Usuario;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * InsereUsers
 */

@ComponentScan("br.ufc.quixada.blog")
public class BuscaPosts implements CommandLineRunner {

    @Autowired
    private PostDAO postDAO;

    private List<Post> posts = new ArrayList<>();

    @Override
    public void run(String... args) {
        Console console = System.console();
        
        System.out.println("Insira uma palavra de busca no títilo: ");
        String buscaTitulo = console.readLine();
        posts = postDAO.findByWordInTitle(buscaTitulo);
        for (Post post : posts) {
            System.out.println(post.getTitulo());
        }

        System.out.println("Insira uma palavra de busca no corpo: ");
        String buscaCorpo = console.readLine();
        posts = postDAO.findByWordInBody(buscaCorpo);
        for (Post post : posts) {
            System.out.println(post.getCorpo());
        }

        System.out.println("Insira uma valor para buscar por avaliação; ");
        Double av = Double.parseDouble(console.readLine());
        posts = postDAO.findPostsByMinRate(av);
        for (Post post : posts) {
            System.out.println(post.getTitulo() + " " +post.getRate());
        }

        int qtdPosts = postDAO.countPosts();
        System.out.println("Qtd posts cadastrados: " + qtdPosts);

        List<Map<String, Integer>> qtdByCategoria = postDAO.countPostsByCategoria();
        for(Map<String, Integer> currentCount : qtdByCategoria){
            System.out.println(currentCount.get("categoria") +": "+ currentCount.get("count"));
        }

    }
    
}