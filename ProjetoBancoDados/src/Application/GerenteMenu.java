package Application;

import java.sql.Connection;
import java.util.Scanner;

public class GerenteMenu {

    public static void menu(Connection conn, Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- MENU GERENTE ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Cadastrar Venda");
            System.out.println("4 - Cadastrar Transportadora");
            System.out.println("5 - Visualizar clientes");
            System.out.println("6 - Visualizar produtos");
            System.out.println("7 - Procedimentos (Procedures)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> Cadastro.cadastrarCliente(conn, sc);
                case 2 -> Cadastro.cadastrarProduto(conn, sc);
                case 3 -> Cadastro.cadastrarVenda(conn, sc);
                case 4 -> Cadastro.cadastrarTransportadora(conn, sc);
                case 5 -> Visualizar.visualizarClientes(conn);
                case 6 -> Visualizar.visualizarProdutos(conn);
                case 7 -> proceduresMenu(conn, sc);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void proceduresMenu(Connection conn, Scanner sc) {
        int opcao;
        System.out.println("\n--- MENU PROCEDURES ---");
        System.out.println("1 - Reajuste salários");
        System.out.println("2 - Sorteio clientes");
        System.out.println("3 - Estatísticas vendas");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> Procedures.reajusteSalarios(conn, sc);
            case 2 -> Procedures.sorteioClientes(conn, sc);
            case 3 -> Procedures.estatisticasVendas(conn);
            case 0 -> {}
            default -> System.out.println("Opção inválida!");
        }
    }
}
