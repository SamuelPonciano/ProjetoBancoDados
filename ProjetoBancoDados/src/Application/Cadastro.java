package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Cadastro {

    public static void cadastrarCliente(Connection conn, Scanner sc) {
        try {
            System.out.println("Digite o nome do cliente:");
            String nome = sc.nextLine();
            System.out.println("Digite o sexo do cliente (F/M/O):");
            String sexo = sc.nextLine();
            System.out.println("Digite a idade do cliente:");
            int idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite a data de nascimento (YYYY-MM-DD):");
            String dataNasc = sc.nextLine();

            String sql = "INSERT INTO clientes (nome, sexo, idade, dataNasc) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sexo);
            ps.setInt(3, idade);
            ps.setString(4, dataNasc);
            ps.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public static void cadastrarProduto(Connection conn, Scanner sc) {
        try {
            System.out.println("Digite o nome do produto:");
            String nome = sc.nextLine();
            System.out.println("Digite a quantidade do produto:");
            int quantidade = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o valor do produto:");
            double valor = sc.nextDouble();
            sc.nextLine();
            System.out.println("Digite observações do produto:");
            String obs = sc.nextLine();

            String sql = "INSERT INTO produtos (nome, quantidade, valor, observacoes) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, quantidade);
            ps.setDouble(3, valor);
            ps.setString(4, obs);
            ps.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

  public static void cadastrarVenda(Connection conn, Scanner sc) {
    try {
        System.out.println("Digite o endereço de destino:");
        String endereco = sc.nextLine();

        System.out.println("Digite o valor cobrado:");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.println("Digite o ID do vendedor:");
        int idVendedor = sc.nextInt();

        System.out.println("Digite o ID do cliente:");
        int idCliente = sc.nextInt();

        System.out.println("Digite o ID da transportadora:");
        int idTrans = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o ID do produto vendido:");
        int idProduto = sc.nextInt();
        sc.nextLine();

        // Inserir venda
        String sql = "INSERT INTO venda (endereco_destino, valor_cobrado, data_hora, id_vendedor, id_cliente, id_trans) " +
                     "VALUES (?, ?, NOW(), ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, endereco);
        ps.setDouble(2, valor);
        ps.setInt(3, idVendedor);
        ps.setInt(4, idCliente);
        ps.setInt(5, idTrans);
        ps.executeUpdate();

        // Pegar ID da venda inserida
        ResultSet rs = ps.getGeneratedKeys();
        int vendaId = 0;
        if (rs.next()) {
            vendaId = rs.getInt(1);
        }

        // Inserir produto na venda_produto
        String sqlProduto = "INSERT INTO vendas_produto (id_produto, id_venda) VALUES (?, ?)";
        PreparedStatement psProduto = conn.prepareStatement(sqlProduto);
        psProduto.setInt(1, idProduto);
        psProduto.setInt(2, vendaId);
        psProduto.executeUpdate();

        System.out.println("Venda cadastrada com sucesso! ID da venda: " + vendaId);

    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar venda: " + e.getMessage());
    }
}


    public static void cadastrarFuncionario(Connection conn, Scanner sc) {
    try {
        System.out.println("Digite o nome do funcionário:");
        String nome = sc.nextLine();

        System.out.println("Sexo (F/M/O):");
        String sexo = sc.nextLine().toUpperCase();

        if (!sexo.equals("F") && !sexo.equals("M") && !sexo.equals("O")) {
            System.out.println("Sexo inválido!");
            return;
        }

        System.out.println("Cargo (vendedor / gerente / CEO):");
        String cargo = sc.nextLine().toLowerCase();

        if (!cargo.equals("vendedor") && !cargo.equals("gerente") && !cargo.equals("ceo")) {
            System.out.println("Cargo inválido!");
            return;
        }

        System.out.println("Digite o salário do funcionário:");
        double salario = sc.nextDouble();
        sc.nextLine(); // limpar o buffer

        String sql = "INSERT INTO funcionario (nome, sexo, cargo, salario) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, sexo);
        ps.setString(3, cargo);
        ps.setDouble(4, salario);
        ps.executeUpdate();

        System.out.println("Funcionário cadastrado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
    }
}


    public static void cadastrarVendedor(Connection conn, Scanner sc) {
    try {
        System.out.println("Digite o nome do vendedor:");
        String nome = sc.nextLine();

        System.out.println("Digite a causa social:");
        String causaSocial = sc.nextLine();

        System.out.println("Digite o tipo do vendedor:");
        String tipo = sc.nextLine();

        System.out.println("Digite a nota média do vendedor:");
        double notaMedia = sc.nextDouble();
        sc.nextLine(); // limpa o buffer

        String sql = "INSERT INTO vendedor (nome, causa_social, tipo, nota_media) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, causaSocial);
        ps.setString(3, tipo);
        ps.setDouble(4, notaMedia);

        ps.executeUpdate();
        System.out.println("Vendedor cadastrado com sucesso!");

    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar vendedor: " + e.getMessage());
    }
}


    public static void cadastrarTransportadora(Connection conn, Scanner sc) {
    try {
        System.out.println("Digite o nome da transportadora:");
        String nome = sc.nextLine();

        System.out.println("Digite a cidade da transportadora:");
        String cidade = sc.nextLine();

        String sql = "INSERT INTO transportadora (nome, cidade) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, cidade);

        ps.executeUpdate();
        System.out.println("Transportadora cadastrada com sucesso!");

    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar transportadora: " + e.getMessage());
    }
}

}
