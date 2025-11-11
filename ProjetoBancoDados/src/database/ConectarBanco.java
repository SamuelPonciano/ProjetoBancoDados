package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BANCO = "bancosistemaecommerce";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            URL + BANCO + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
            USUARIO, SENHA
        );
    }
}
