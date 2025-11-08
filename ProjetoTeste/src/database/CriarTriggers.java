package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarTriggers {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE TRIGGER Add_funcionarioEspecial" + 
            "AFTER INSERT ON venda" +
            "FOR EACH ROW" +
            "BEGIN" +
            "   DECLARE total_bonus DECIMAL(10,2);" +
            "   IF NEW.valor_cobrado > 1000 THEN " +
            "       INSERT INTO funcionarioEspecial (id_funcionario, bonus) " +
            "       VALUES (NEW.id_vendedor, NEW.valor_cobrado * 0.05);" +
            "       ON DUPLICATE KEY UPDATE " +
            "       bonus = bonus + (NEW.valor_cobrado * 0.05);" +
            "   END IF;" +
            "   SELECT COALESCE(SUM(bonus), 0)" +
            "   INTO total_bonus " +
            "   FROM funcionarioEspecial " +

            "   SIGNAL SQLSTATE '01000' " +
            "   SET MESSAGE_TEXT = CONCAT('Total bonus do funcionario: ', total_bonus);" +
            "END"

        );

        stmt.executeUpdate(
            "CREATE TRIGGER Add_clienteEspecial " +
            "AFTER INSERT ON venda " +
            "FOR EACH ROW " +
            "BEGIN " +
            "   DECLARE total_cashback DECIMAL(10,2);" +
            "   IF NEW.valor_cobrado > 500 THEN" +
            "       INSERT INTO cliente_especial (id_cliente, cashback) " +
            "       VALUES (NEW.id_cliente, NEW.valor_cobrado * 0.02);" +
            "       ON DUPLICATE KEY UPDATE " +
            "       cashback = cashback + (NEW.valor_cobrado * 0.02);" +
            "   END IF;" +
            "   SELECT COALESCE(SUM(cashback), 0) " +
            "   INTO total_cashback " +
            "   FROM cliente_especial " +
            "   SIGNAL SQLSTATE '01000' " +
            "   SET MESSAGE_TEXT = CONCAT('Total cashback necessário: ', total_cashback);" +
            "END"
        );

        stmt.executeUpdate(
            "CREATE TRIGGER Remove_clienteEspecial " +
            "AFTER UPTADE ON venda " +
            "FOR EACH ROW " +
            "BEGIN " +
            "   IF NEW.cashback = 0 THEN " +
            "       DELETE FROM cliente_especial WHERE id_cliente = NEW.id_cliente; " +
            "   END IF; " +
            "END"
        );
    }

}