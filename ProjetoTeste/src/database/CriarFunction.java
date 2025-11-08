package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarFunction {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE FUNCTION Calcular_idade (p_id_cliente INT)" +
            "RETURNS INT" +
            "DETERMINISTIC" +
            "BEGIN" +
            "   DECLARE data_atual DATE;" +
            "   DECLARE idade INT;" +
            "   SELECT c.dataNasc INTO data_atual FROM Cliente AS c WHERE c.id = p_id_cliente;" +
            "   SET idade = TIMESTAMPDIFF(YEAR, data_atual, CURDATE());" +
            "   RETURN idade;" +
            "END"
        );

        stmt.executeUpdate(
            "CREATE FUNCTION Soma_fretes(destino VARCHAR(200)) " +
            "RETURNS DECIMAL(10,2) " + 
            "DETERMINISTIC " +
            "READS SQL DATA " +
            "BEGIN " +
            "   DECLARE total_frete DECIMAL(10,2); " +
            "   " +
            "   SELECT COALESCE(SUM(valor_cobrado), 0) " +
            "   INTO total_frete " +
            "   FROM venda" +
            "   WHERE DATE(data_hora) = destino; " +
            "   " +
            "   RETURN total_frete; " +
            "END"
        );
        
        stmt.executeUpdate(
            "CREATE FUNCTION Arrecadado(dia DATE, id_vendedor BIGINT) " +
            "RETURNS DECIMAL(10,2) " +
            "DETERMINISTIC " +
            "READS SQL DATA " +
            "BEGIN " +
            "   DECLARE total_vendas DECIMAL(10,2); " +
            "   " +
            "   SELECT COALESCE(SUM(valor_cobrado), 0) " +
            "   INTO total_vendas " +
            "   FROM venda " +
            "   WHERE DATE(data_hora) = dia AND id_vendedor = id_vendedor; " +
            "   " +
            "   RETURN total_vendas; " +
            "END"
        );


    }
}