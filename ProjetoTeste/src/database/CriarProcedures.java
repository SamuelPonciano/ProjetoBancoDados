package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarProcedures {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE PROCEDURE Reajuste(IN p_percentual DECIMAL(5,2), IN p_cargo VARCHAR(20)) " +
            "BEGIN " +
            "   UPDATE funcionario " +
            "   SET salario = salario + (salario * (p_percentual / 100)) " +
            "   WHERE cargo = p_cargo; " +
            "END"
        );

        stmt.executeUpdate(
            "CREATE PROCEDURE Sorteio() " +
            "BEGIN " +
            "   DECLARE sorteado INT; " +
            "   DECLARE valor_voucher DECIMAL(10,2); " +
            "   SELECT id INTO sorteado " +
            "   FROM clientes " +
            "   ORDER BY RAND() " +
            "   LIMIT 1; " +
            "   IF EXISTS(SELECT 1 FROM cliente_especial WHERE id_cliente = sorteado) THEN " +
            "       SET valor_voucher = 200.00; " +
            "   ELSE " +
            "       SET valor_voucher = 100.00; " +
            "   END IF; " +
            "   SELECT sorteado AS id_cliente, valor_voucher AS premio; " +
            "END"
        );

        stmt.executeUpdate(
            "CREATE PROCEDURE RegistrarVenda(IN produto_id INT, IN venda_id INT)" + 
            "BEGIN " +
            "   INSERT INTO venda_produtos (id_venda, id_produto) " +
            "   VALUES (venda_id, produto_id); " +
            "   UPDATE produtos " +
            "   SET quantidade = quantidade - 1 " +
            "   WHERE id = produto_id; " +
            "   SELECT 'Venda registrada e estoque atualizado' AS mensagem; " +
            "END"   
        );

        stmt.executeUpdate(
            "CREATE PROCEDURE EstatisticasVendas()" +
            "BEGIN " +
            "   DECLARE produto_Mais INT; " +
            "   DECLARE produto_Menos INT; " +
            " " + 
            "   SELECT vp.id_produto INTO produto_Mais " +
            "   FROM vendas_produto vp " +
            "   GROUP BY vp.id_produto " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 1; " +
            " " +
            "   SELECT vp.id_produto INTO produto_Menos " +
            "   FROM vendas_produto vp " +
            "   GROUP BY vp.id_produto " +
            "   ORDER BY COUNT(*) ASC " +
            "   LIMIT 1; " +
            " " +
            "   SELECT p.nome AS produto_mais_vendido, " +
            "       vdr.nome AS vendedor_associado, " +
            "       COUNT(vp.id_produto) AS total_vendas, " +
            "       SUM(v.valor_cobrado) AS valor_total, " +
            "       MONTH( v.data_venda) AS mes " +
            "       SUM(v.valor_cobrado) AS valor_mes" +
            "   FROM vendas_produto vp " +
            "   JOIN produtos p ON vp.id_produto = p.id " +
            "   JOIN venda v ON vp.id_venda = v.id " +
            "   JOIN vendedor vdr ON v.id_vendedor = vdr.id " + 
            "   WHERE vp.id_produto = produto_Mais " +
            "   GROUP BY p.nome, vdr.nome, MONTH(v.data_venda); " +
            "   ORDER BY valor_mes DESC; " +
            " " +
            "   SELECT p.nome AS produto_menos_vendido, " +
            "       COUNT(vp.id_produto) AS total_vendas, " +
            "       SUM(v.valor_cobrado) AS valor_total, " +
            "       MONTH( v.data_venda) AS mes, " +
            "       SUM(v.valor_cobrado) AS valor_mes," +
            "   FROM vendas_produto vp " +
            "   JOIN produtos p ON vp.id_produto = p.id " +
            "   JOIN venda v ON vp.id_venda = v.id " +
            "   WHERE vp.id_produto = produto_Menos " +
            "   GROUP BY p.nome, MONTH(v.data_venda) " +
            "   ORDER BY valor_mes ASC " +
            "END"
        );
    }
}