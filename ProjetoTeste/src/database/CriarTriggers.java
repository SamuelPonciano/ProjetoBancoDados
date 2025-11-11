package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarTriggers {
    public static void criar(Statement stmt) throws SQLException {

        // Trigger para adicionar bônus do funcionário
        stmt.executeUpdate(
            "CREATE TRIGGER Add_funcionario_especial " +
            "AFTER INSERT ON venda " +
            "FOR EACH ROW " +
            "INSERT INTO funcionario_especial (id_vendedor, bonus) " +
            "SELECT NEW.id_vendedor, NEW.valor_cobrado * 0.05 " +
            "FROM DUAL " +
            "WHERE NEW.valor_cobrado > 1000 " +
            "ON DUPLICATE KEY UPDATE bonus = bonus + (NEW.valor_cobrado * 0.05)"
        );

        // Trigger para adicionar cashback ao cliente
        stmt.executeUpdate(
            "CREATE TRIGGER Add_cliente_especial " +
            "AFTER INSERT ON venda " +
            "FOR EACH ROW " +
            "INSERT INTO cliente_especial (id_cliente, cashback) " +
            "SELECT NEW.id_cliente, NEW.valor_cobrado * 0.02 " +
            "FROM DUAL " +
            "WHERE NEW.valor_cobrado > 500 " +
            "ON DUPLICATE KEY UPDATE cashback = cashback + (NEW.valor_cobrado * 0.02)"
        );

        // Trigger para remover cliente especial quando cashback zerar
        stmt.executeUpdate(
            "CREATE TRIGGER Remove_cliente_especial " +
            "AFTER UPDATE ON venda " +
            "FOR EACH ROW " +
            "DELETE FROM cliente_especial " +
            "WHERE id_cliente = NEW.id_cliente AND NEW.valor_cobrado = 0"
        );

    }
}
