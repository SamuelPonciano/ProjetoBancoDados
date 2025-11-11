package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarUsuario {
    public static void criar(Statement stmt) throws SQLException {

        stmt.executeUpdate("CREATE USER IF NOT EXISTS 'administrador'@'localhost' IDENTIFIED BY 'admin123'");
        stmt.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO 'administrador'@'localhost' WITH GRANT OPTION");

        stmt.executeUpdate("CREATE USER IF NOT EXISTS 'gerente'@'localhost' IDENTIFIED BY 'gerente123'");
        stmt.executeUpdate("GRANT SELECT, UPDATE, DELETE ON bancosistemaecommerce.* TO 'gerente'@'localhost'");

        stmt.executeUpdate("CREATE USER IF NOT EXISTS 'funcionario'@'localhost' IDENTIFIED BY 'func123'");
        stmt.executeUpdate("GRANT SELECT, INSERT ON bancosistemaecommerce.venda TO 'funcionario'@'localhost'");

        stmt.executeUpdate("FLUSH PRIVILEGES");
    }
}
