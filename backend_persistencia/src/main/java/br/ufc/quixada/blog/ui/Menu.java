package br.ufc.quixada.blog.ui;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

/**
 * InsereUsers
 */
@ComponentScan("br.ufc.quixada.blog")
public class Menu implements CommandLineRunner {

    @Autowired
    private MenuPosts menuPosts;

    @Override
    public void run(String... args) {
        Console console = System.console();
        String opt = "";
        boolean run = true;
        while (run) {
            System.out.println("Selecione uam opção: ");
            System.out.println("1 - usuários");
            System.out.println("2 - posts");
            System.out.println("3 - comentários");
            System.out.println("0 - sair");
            opt = console.readLine();
            switch (opt) {
                case "1":
                    System.out.println("u");
                    break;
                case "2":
                    menuPosts.menu();
                    break;
                case "3":
                    System.out.println("c");
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

}