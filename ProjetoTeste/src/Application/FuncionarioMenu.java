package Application;

import java.sql.Connection;
import java.util.Scanner;

public class FuncionarioMenu {

    public static void menu(Connection conn, Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Venda");
            System.out.println("3 - Visualizar Produtos");
            System.out.println("4 - Visualizar Clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> Cadastro.cadastrarCliente(conn, sc);
                case 2 -> Cadastro.cadastrarVenda(conn, sc);
                case 3 -> Visualizar.visualizarProdutos(conn);
                case 4 -> Visualizar.visualizarClientes(conn);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
