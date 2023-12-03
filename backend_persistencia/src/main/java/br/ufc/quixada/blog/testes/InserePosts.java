package br.ufc.quixada.blog.testes;

import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;
// import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class InserePosts implements CommandLineRunner {

    // @Autowired
    // private PostDAO postDAO;

    @Autowired
    private UserDaoRelacional userDAO;

    @Override
    public void run(String... args) {
        Usuario usuario = userDAO.findAll().get(0);
        if (usuario != null) {
            // Usuario usuario = usuarioOpt.get();

            // Post p1 = new Post(null,
            //         "Primeiros passos no git",
            //         "O Git é um sistema de controle de versão para rastrear alterações no código-fonte, facilitando a colaboração entre desenvolvedores.",
            //         0, 
            //         "versionamento", 
            //         usuario,
            //         null);

            // Post p2 = new Post(null,
            //         "O que é github actions?",
            //         "GitHub Actions é um serviço de automação integrado ao GitHub que permite criar fluxos de trabalho personalizados para automatizar tarefas de desenvolvimento.",
            //         0, 
            //         "ci/cd", 
            //         usuario,
            //         null);

            // Post p3 = new Post(null,
            //         "Introdução ao Heroku",
            //         "Heroku é uma plataforma em nuvem que simplifica a implantação e hospedagem de aplicativos da web, oferecendo escalabilidade e suporte a várias linguagens de programação.",
            //         0, 
            //         "ci/cd", 
            //         usuario,
            //         null);

            // Post p4 = new Post(null,
            //         "Introdução à AWS",
            //         "A AWS (Amazon Web Services) é a principal provedora de serviços em nuvem, oferecendo uma ampla gama de recursos para empresas e desenvolvedores.",
            //         0, 
            //         "nuvem", 
            //         usuario,
            //         null);

            // postDAO.save(p1);
            // postDAO.save(p2);
            // postDAO.save(p3);
            // postDAO.save(p4);

            System.out.println("Deu certo");
        } else {
            System.out.println("Usuário não encontrado");
        }

    }

}