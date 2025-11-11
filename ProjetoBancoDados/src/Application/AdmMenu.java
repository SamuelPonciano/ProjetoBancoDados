package Application;

import java.sql.Connection;
import java.util.Scanner;

public class AdmMenu {

    public static void menu(Connection conn, Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1 - Criar banco");
            System.out.println("2 - Destruir banco");
            System.out.println("3 - Cadastro");
            System.out.println("4 - Visualizar clientes");
            System.out.println("5 - Visualizar produtos");
            System.out.println("6 - Funções (Functions)");
            System.out.println("7 - Procedimentos (Procedures)");
            System.out.println("8 - Visualizar Views");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> database.InicializarBanco.criar();
                case 2 -> database.InicializarBanco.delete();
                case 3 -> cadastroMenu(conn, sc);
                case 4 -> Visualizar.visualizarClientes(conn);
                case 5 -> Visualizar.visualizarProdutos(conn);
                case 6 -> functionsMenu(conn, sc);
                case 7 -> proceduresMenu(conn, sc);
                case 8 -> viewsMenu(conn, sc);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void cadastroMenu(Connection conn, Scanner sc) {
        int opcao;
        System.out.println("\n--- MENU CADASTRO ---");
        System.out.println("1 - Cadastrar Funcionário");
        System.out.println("2 - Cadastrar Vendedor");
        System.out.println("3 - Cadastrar Produto");
        System.out.println("4 - Cadastrar Cliente");
        System.out.println("5 - Cadastrar Venda");
        System.out.println("6 - Cadastrar Transportadora");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> Cadastro.cadastrarFuncionario(conn, sc);
            case 2 -> Cadastro.cadastrarVendedor(conn, sc);
            case 3 -> Cadastro.cadastrarProduto(conn, sc);
            case 4 -> Cadastro.cadastrarCliente(conn, sc);
            case 5 -> Cadastro.cadastrarVenda(conn, sc);
            case 6 -> Cadastro.cadastrarTransportadora(conn, sc);
            case 0 -> {}
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void functionsMenu(Connection conn, Scanner sc) {
        int opcao;
        System.out.println("\n--- MENU FUNÇÕES ---");
        System.out.println("1 - Calcular idade");
        System.out.println("2 - Soma fretes");
        System.out.println("3 - Arrecadado");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> Functions.calculaIdade(conn, sc);
            case 2 -> Functions.somaFretes(conn, sc);
            case 3 -> Functions.arrecadado(conn, sc);
            case 0 -> {}
            default -> System.out.println("Opção inválida!");
        }
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

    public static void viewsMenu(Connection conn, Scanner sc) {
        int opcao;
        System.out.println("\n--- MENU VIEWS ---");
        System.out.println("1 - Total de vendas por cliente");
        System.out.println("2 - Total de vendas por transportadora");
        System.out.println("3 - Ver produtos por vendedor");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> Views.TotalVendaCliente(conn);
            case 2 -> Views.TotalVendasTransportadora(conn);
            case 3 -> Views.visualizarProdutosPorVendedor(conn);
            case 0 -> {}
            default -> System.out.println("Opção inválida!");
        }
    }
}
