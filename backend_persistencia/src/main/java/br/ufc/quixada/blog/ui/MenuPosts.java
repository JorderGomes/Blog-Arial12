package br.ufc.quixada.blog.ui;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("br.ufc.quixada.blog")
@Component
public class MenuPosts {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    public void menu() {
        int idPost;
        String strBusca = "";
        List<Post> posts = new ArrayList<>();
        List<Map<String, Integer>> qtdByCategoria = new ArrayList<>();
        Post post;
        Optional<Usuario> usuarioOpt;
        Optional<Post> postOpt;
        Console console = System.console();
        String opt = "";
        boolean run = true;
        while (run) {
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Listar posts");
            System.out.println("2 - Buscar post por id");

            System.out.println("3 - Buscar por título");
            System.out.println("4 - Buscar por conteúdo");
            System.out.println("5 - Buscar por avaliação");
            System.out.println("6 - Quantos posts existem");
            System.out.println("7 - Quantos posts por categoria");

            System.out.println("8 - Salvar post");
            System.out.println("9 - Apagar post");
            System.out.println("10 - Editar post");
            System.out.println("0 - Menu anterior");
            opt = console.readLine();
            switch (opt) {
                case "1":
                    posts = postDAO.findAll();
                    for (Post p : posts) {
                        System.out.println(p.toString());
                    }
                    break;
                case "2":
                    idPost = Integer.parseInt(console.readLine("Insira um id: "));
                    postOpt = postDAO.findById(idPost);
                    if (postOpt.isPresent()) {
                        System.out.println(postOpt.get().toString());
                    } else {
                        System.out.println("Post não encontrado.");
                    }
                    break;
                case "3":
                    System.out.println("Insira o termo de busca: ");
                    strBusca = console.readLine();
                    posts = postDAO.findByWordInTitle(strBusca);
                    for (Post p : posts) {
                        System.out.println(p.getTitulo());
                    }
                    break;
                case "4":
                    System.out.println("Insira uma palavra de busca no corpo: ");
                    strBusca = console.readLine();
                    posts = postDAO.findByWordInBody(strBusca);
                    for (Post p : posts) {
                        System.out.println(p.getCorpo());
                    }
                    break;
                case "5":
                    System.out.println("Insira uma valor para buscar por avaliação; ");
                    Double av = Double.parseDouble(console.readLine());
                    posts = postDAO.findPostsByMinRate(av);
                    for (Post p : posts) {
                        System.out.println(p.getTitulo() + " " + p.getRate());
                    }
                    break;
                case "6":
                    int qtdPosts = postDAO.countPosts();
                    System.out.println("Qtd posts cadastrados: " + qtdPosts);
                    break;
                case "7":
                    qtdByCategoria = postDAO.countPostsByCategoria();
                    for (Map<String, Integer> currentCount : qtdByCategoria) {
                        System.out.println(currentCount.get("categoria") + ": " + currentCount.get("count"));
                    }
                    break;
                case "8":
                    post = null;
                    post = this.getPostData(console, post);
                    System.out.println("Insira id do usuário: ");
                    int idUsuario = Integer.parseInt(console.readLine());
                    usuarioOpt = this.userDAO.findById(idUsuario);
                    if (usuarioOpt.isPresent()) {
                        post.setUsuario(usuarioOpt.get());
                        postDAO.save(post);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case "9":
                    System.out.println("Insira id do post: ");
                    idPost = Integer.parseInt(console.readLine());
                    postDAO.deleteById(idPost);
                    break;
                case "10":
                    System.out.println("Insira id do post: ");
                    idPost = Integer.parseInt(console.readLine());
                    postOpt = this.postDAO.findById(idPost);
                    if (postOpt.isPresent()){
                        post = postOpt.get();
                        post = getPostData(console, post);
                        postDAO.save(post);
                    } else {
                        System.out.println("Post não encontrado");
                    }
                    break;
                case "0":
                    System.out.println("Saindo...");
                    run = false;
                    break;
                default:
                    System.out.println("Selecione uma opção");
                    break;
            }
        }

    }

    public Post getPostData(Console console, Post p) {
        String titulo = "";
        String corpo = "";
        String categoria = "";

        if (p == null) {
            System.out.println("Insira o título: ");
            titulo = console.readLine();
            System.out.println("Insira o corpo: ");
            corpo = console.readLine();
            System.out.println("Insira a categoria: ");
            categoria = console.readLine();
        } else {
            String str;
            System.out.println("Insira os valores ou deixe em branco para manter.");
            str = console.readLine("Título: ");
            titulo = (str == "") ? p.getTitulo() : str;
            str = console.readLine("Corpo: ");
            titulo = (str == "") ? p.getCorpo() : str;
            str = console.readLine("Categoria: ");
            titulo = (str == "") ? p.getCategoria() : str;
        }

        p = Post.builder()
                .titulo(titulo)
                .corpo(corpo)
                .categoria(categoria)
                .build();
        return p;
    }

}