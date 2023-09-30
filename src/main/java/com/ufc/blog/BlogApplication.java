package com.ufc.blog;

import java.util.Scanner;

import com.ufc.blog.models.User;
// import com.ufc.blog.models.User;
import com.ufc.blog.service.DataService;

public class BlogApplication {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int choice;
		DataService service = new DataService();

		do {
			System.out.println("Menu:");
			System.out.println("1 - Salvar novo usuário");
			System.out.println("2 - Contar usuários");
			System.out.println("3 - Converter CSV para Json");
			System.out.println("4 - Converter CSV para XML");
			System.out.println("5 - Sair");
			System.out.print("Escolha uma opção: ");

			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					// Opção 1: Salvar novo usuário
					saveNewUser(service);
					break;
				case 2:
					// Opção 2: Contar usuários
					service.countLines();
					break;
				case 3:
					// Opção 3: Converter CSV para Json
					service.convertCsvToJson();
					break;
				case 4:
					// Opção 4: Converter CSV para XML
					service.convertCsvToXml();
					break;
				case 5:
					// Opção 5: Sair do programa
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		} while (choice != 5);
		scanner.close();
	}

	private static void saveNewUser(DataService service) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os dados do novo usuário:");

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Bio: ");
        String bio = scanner.nextLine();

        System.out.print("Taxa: ");
        float rate = scanner.nextFloat();

        User newUser = new User(name, password, email, bio, rate);
        service.saveUser(newUser);
		scanner.close();
    }

}
