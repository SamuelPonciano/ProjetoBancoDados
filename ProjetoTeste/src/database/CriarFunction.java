package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarFunction {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE FUNCTION calcular_idade (p_id_cliente INT)" +
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
            "CREATE FUNCTION Soma_fretes(dia DATE) " +
            "RETURNS DECIMAL(10,2) " + 
            "DETERMINISTIC " +
            "BEGIN " +
            
        );


    }
}