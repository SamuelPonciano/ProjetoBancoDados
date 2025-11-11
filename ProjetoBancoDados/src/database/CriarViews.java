package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarViews {
    public static void criar(Statement stmt) throws SQLException {
        // Total de vendas por cliente
        stmt.executeUpdate(
            "CREATE VIEW totalVenda_Cliente AS " +
            "SELECT c.id AS id_cliente, c.nome AS nome_cliente, " +
            "COUNT(v.id) AS total_vendas, " +
            "SUM(v.valor_cobrado) AS valor_total_gasto " +
            "FROM clientes c " +
            "JOIN venda v ON c.id = v.id_cliente " +
            "GROUP BY c.id, c.nome"
        );

        // Total de vendas por transportadora
        stmt.executeUpdate(
            "CREATE VIEW totalVendas_transportadora AS " +
            "SELECT t.id AS id_transportadora, t.nome AS nome_transportadora, " +
            "COUNT(v.id) AS total_vendas, " +
            "SUM(v.valor_cobrado) AS valor_total_vendido " +
            "FROM transportadora t " +
            "JOIN venda v ON t.id = v.id_trans " +
            "GROUP BY t.id, t.nome"
        );

        // Cashback para clientes especiais
        stmt.executeUpdate(
            "CREATE OR REPLACE VIEW view_produtos_por_vendedor AS " +
            "SELECT " +
            "    v.id AS id_vendedor, " +
            "    v.nome AS nome_vendedor, " +
            "    p.id AS id_produto, " +
            "    p.nome AS nome_produto, " +
            "    COUNT(vp.id_venda) AS quantidade_vendida " +
            "FROM vendedor v " +
            "LEFT JOIN venda ve ON v.id = ve.id_vendedor " +
            "LEFT JOIN vendas_produto vp ON ve.id = vp.id_venda " +
            "LEFT JOIN produtos p ON vp.id_produto = p.id " +
            "GROUP BY v.id, v.nome, p.id, p.nome " +
            "ORDER BY v.id, quantidade_vendida DESC"
        );

    }
}
