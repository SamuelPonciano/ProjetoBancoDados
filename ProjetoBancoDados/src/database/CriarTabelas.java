package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelas {

    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "sexo ENUM('F','M','O'), " +
                        "idade INT, " +
                        "dataNasc DATE" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vendedor (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "causa_social VARCHAR(255), " +
                        "tipo VARCHAR(255), " +
                        "nota_media DECIMAL(3,2)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS transportadora (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "cidade VARCHAR(20) NOT NULL" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS venda (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "endereco_destino VARCHAR(200) NOT NULL, " +
                        "valor_cobrado DECIMAL(10,2) NOT NULL, " +
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
                        "valor DECIMAL(10,2) NOT NULL, " +
                        "observacoes VARCHAR(255)" +
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
                        "bonus DECIMAL(10,2) DEFAULT 0.00, " +
                        "FOREIGN KEY (id_vendedor) REFERENCES vendedor(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS cliente_especial (" +
                        "id_cliente INT PRIMARY KEY, " +
                        "cashback DECIMAL(5,2) DEFAULT 0.00, " +
                        "FOREIGN KEY (id_cliente) REFERENCES clientes(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS funcionario (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(100) NOT NULL, " +
                        "sexo ENUM('F','M','O'), " +
                        "cargo ENUM('vendedor','gerente','CEO'), " +
                        "salario DECIMAL(10,2) NOT NULL" +
                        ")"
        );
    }

}
