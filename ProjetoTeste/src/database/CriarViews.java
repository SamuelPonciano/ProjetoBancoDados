package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarViews {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE VIEW totalVenda_Cliente AS SELECT " +
            "   c.id AS id_cliente, c.nome AS nome_cliente, " +
            "   COUNT(v.id) AS total_vendas, " +
            "   SUM(v.valor_cobrado) AS valor_total_gasto " +
            "FROM cliente c " +
            "JOIN venda v ON c.id = v.id_cliente " +
            "GROUP BY c.id, c.nome"
        );

        stmt.executeUpdate(
            "CREATE VIEW totalVendas_transportadora AS SELECT " +
            "   t.id AS id_transportadora, t.nome AS nome_transportadora, " +
            "   COUNT(v.id) AS total_vendas, " +
            "   SUM(v.valor_cobrado) AS valor_total_vendido " +
            "FROM transportadora t " +
            "JOIN venda v ON t.id = v.id_trans " +
            "GROUP BY t.id, t.nome"
        );

        stmt.executeUpdate(
            "CREATE VIEW cashback_clienteEspecial AS SELECT " +
            "   c.id AS id_cliente, c.nome AS nome_cliente, " +
            "   COUNT(v.id) AS compras_maior500, " +
            "   SUM(v.valor_cobrado * 0.02) AS cashback_total " +
            "FROM cliente c " +
            "JOIN venda v ON c.id = v.id_cliente " +
            "WHERE v.valor_cobrado > 500 " +
            "GROUP BY c.id, c.nome"
        );
    }
}