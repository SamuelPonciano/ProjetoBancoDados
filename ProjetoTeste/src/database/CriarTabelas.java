package database;

import java.sql.SQLException;
import java.sql.Statement;

class CriarTabelas {
    public static void criar(Statement stmt) throws SQLException {
        // Tabela de Planos
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "sexo ENUM('F', 'M', 'O'), " +
                        "idade int, " +
                        "dataNasc DATETIME" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS produtos(" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "quantidade INT NOT NULL, " +
                        "valor DECIMAL(10, 2) NOT NULL, " +
                        "observacoes VARCHAR(255), " +
                        "nome VARCHAR(100) NOT NULL, " +
                        "idvenda_produto BIGINT, " +
                        "FOREIGN KEY (idvenda_produto) REFERENCES vendas_produto(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS venda (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "endereco_destino VARCHAR(200) NOT NULL, " + 
                        "valor_cobrado DECIMAL(10, 2) NOT NULL, " +
                        "data_hora DATETIME, " +
                        "id_venda BIGINT, " +
                        "FOREIGN KEY (id_venda) REFERENCES vendas(id)" +
                        "id_vendedor BIGINT, " +
                        "FOREIGN KEY (id_vendedor) REFERENCES vendedor(id)" +
                        "id_cliente BIGINT, " +
                        "FOREIGN KEY (id_cliente) REFERENCES clientes(id)" +
                        "id_trans BIGINT, " +
                        "FOREIGN KEY (id_trans) REFERENCES transportadora(id)" +
                        "id_produto BIGINT, " +
                        "FOREIGN KEY (id_produto) REFERENCES produtos(id)" +
                        "idvenda_produto BIGINT, " +
                        "FOREIGN KEY (idvenda_produto) REFERENCES vendas_produto(id)" +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vendedor (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "nome VARCHAR(255) NOT NULL," +
                        "causa_social VARCHAR(255)," +
                        "tipo VARCHAR(255)," +
                        "nota_media DECIMAL(3, 2)," +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS transportadora (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "cidade VARCHAR(20) NOT NULL, " +
                        ")"
        );

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS vendas_produto (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "endereco_destino VARCHAR(200) NOT NULL, " +
                        "valor_cobrado DECIMAL(10, 2) NOT NULL, " +
                        "data_hora DATETIME, " +
                        "id_produto BIGINT, " +
                        "FOREIGN KEY (id_produto) REFERENCES produtos(id), " +
                        "id_venda BIGINT, " +
                        "FOREIGN KEY (id_venda) REFERENCES venda(id)" +
                        ")"
        );

        inserirDados(stmt);
    }

    private static  void inserirDados(Statement stmt){
        for(int i = 0; i<100; i++){
            String nome = "cliente" + i;

            String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?,?);";
            
        }

        for(int i = 0; i<20; i++){
            String nome = "produto" + i;
        }

        for(int i = 0; i<5; i++){
            String nome = "cargo" + i;

        }
    }
}