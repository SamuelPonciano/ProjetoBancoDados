package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

public class InserirDados {
    public static void inserir(java.sql.Connection conn) throws SQLException {
        Random random = new Random();

        // Inserindo clientes
        String[] nomes = {"Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena", "Igor", "Juliana"};
        String[] sexos = {"F", "M", "O"};

        String sqlCliente = "INSERT INTO clientes (nome, sexo, idade, dataNasc) VALUES (?, ?, ?, ?)";
        PreparedStatement psCliente = conn.prepareStatement(sqlCliente);

        for (int i = 0; i < 100; i++) {
            String nome = nomes[random.nextInt(nomes.length)] + " " + nomes[random.nextInt(nomes.length)];
            String sexo = sexos[random.nextInt(sexos.length)];
            int idade = 18 + random.nextInt(60);
            LocalDate dataNasc = LocalDate.now().minusYears(idade).minusDays(random.nextInt(365));

            psCliente.setString(1, nome);
            psCliente.setString(2, sexo);
            psCliente.setInt(3, idade);
            psCliente.setDate(4, java.sql.Date.valueOf(dataNasc));

            psCliente.executeUpdate();
        }

        // Inserindo produtos
        String[] produtos = {"Arroz", "Feijão", "Macarrão", "Açúcar", "Sal", "Óleo", "Leite", "Café", "Pão", "Manteiga",
                             "Queijo", "Presunto", "Frango", "Carne", "Peixe", "Tomate", "Batata", "Cebola", "Alface", "Laranja"};

        String sqlProduto = "INSERT INTO produtos (nome, quantidade, valor, observacoes) VALUES (?, ?, ?, ?)";
        PreparedStatement psProduto = conn.prepareStatement(sqlProduto);

        for (String produto : produtos) {
            int quantidade = 10 + random.nextInt(50);
            double valor = 1 + (100 - 1) * random.nextDouble();
            String observacoes = "Produto " + produto;

            psProduto.setString(1, produto);
            psProduto.setInt(2, quantidade);
            psProduto.setDouble(3, valor);
            psProduto.setString(4, observacoes);

            psProduto.executeUpdate();
        }
    }
}
