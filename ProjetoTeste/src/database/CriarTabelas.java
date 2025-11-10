package database;

import java.sql.SQLException;
import java.sql.Statement;

class CriarTabelas {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "sexo ENUM('F', 'M', 'O'), " +
                        "idade int, " +
                        "dataNasc DATETIME" +
                        ")"
        );
        
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vendedor (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "nome VARCHAR(255) NOT NULL," +
                        "causa_social VARCHAR(255)," +
                        "tipo VARCHAR(255)," +
                        "nota_media DECIMAL(3, 2)," +
                        ")"
        );
        
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS transportadora (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "cidade VARCHAR(20) NOT NULL, " +
                        ")"
        );
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS venda (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "endereco_destino VARCHAR(200) NOT NULL, " + 
                        "valor_cobrado DECIMAL(10, 2) NOT NULL, " +
                        "data_hora DATETIME, " +
                        "id_vendedor INT, " +
                        "id_cliente INT, " +
                        "id_trans INT, " +
                        "FOREIGN KEY (id_vendedor) REFERENCES vendedor(id), " +
                        "FOREIGN KEY (id_cliente) REFERENCES clientes(id), " +
                        "FOREIGN KEY (id_trans) REFERENCES transportadora(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS produtos (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(100) NOT NULL, " +
                        "quantidade INT NOT NULL, " +
                        "valor DECIMAL(10, 2) NOT NULL, " +
                        "observacoes VARCHAR(255), " +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vendas_produto (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "id_produto INT, " +
                        "id_venda INT, " +
                        "FOREIGN KEY (id_produto) REFERENCES produtos(id), " +
                        "FOREIGN KEY (id_venda) REFERENCES venda(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS funcionario_especial (" +
                        "id_vendedor INT PRIMARY KEY, " +
                        "bonus DECIMAL(10, 2) DEFAULT 0.00, " +
                        "FOREIGN KEY (id_vendedor) REFERENCES vendedor(id)" +
                ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS cliente_especial (" +
                        "id_cliente INT PRIMARY KEY, " +
                        "cashback DECIMAL(5, 2) DEFAULT 0.00, " +
                        "FOREIGN KEY (id_cliente) REFERENCES clientes(id)" +
                ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS funcionario (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(100) NOT NULL, " +
                "sexo ENUM('F', 'M', 'O'), " +
                "cargo ENUM('vendedor', 'gerente', 'CEO'), " +
                "salario DECIMAL(10, 2) NOT NULL " +
                
        ")"
        );

        inserirDados(stmt);
    }

    private static  void inserirDados(Statement stmt){
        for(int i = 0; i<100; i++){
            String nome = "cliente" + i;
            int idade = 18 + (i % 50);
            String sexo = (i % 2 == 0) ? "M" : "F";
            String dataNasc = "200" + (i % 10) + "-01-01";

            String sql = String.format(
                    "INSERT INTO clientes (nome, idade, sexo, dataNasc) VALUES ('%s', %d, '%s', '%s')",
                    nome, idade, sexo, dataNasc
            );
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i<20; i++){
            String nome = "produto" + i;
            int quantidade = 1 + (i % 10);
            double valor = 10.0 + (i * 2);
            String observacoes = "Observações do produto " + i;

            String sql = String.format(
                    "INSERT INTO produtos (nome, quantidade, valor, observacoes) VALUES ('%s', %d, %.2f, '%s')",
                    nome, quantidade, valor, observacoes
            );
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i<5; i++){
            // O que é cargo?

        }
    }
}