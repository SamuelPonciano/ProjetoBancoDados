package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarUsuario {
    public static void criar(Statement stmt) throws SQLException {
        stmt.executeUpdate(
            "CREATE USER 'administrador'@'localhost' IDENTIFIED BY 'admin123';" + 
            "GRANT ALL PRIVILEGES ON *.* TO 'administrador'@'localhost' WITH GRANT OPTION;"
        );

        stmt.executeUpdate(
            "CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'gerente123';" +
            "GRANT SELECT, UPDATE, DELETE ON venda.* TO 'gerente'@'localhost';"
        );

        stmt.executeUpdate(
            "CREATE USER 'funcionario'@'localhost' IDENTIFIED BY 'func123';" +
            "GRANT SELECT, INSERT ON venda.* TO 'funcionario'@'localhost';"
        );
    }
}