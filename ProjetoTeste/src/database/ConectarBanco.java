package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectarBanco {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bancosistemaecommerce";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static final Properties connectionProperties = new Properties();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connectionProperties.put("user", USER);
            connectionProperties.put("password", PASS);
            connectionProperties.put("useSSL", "false");
            connectionProperties.put("autoReconnect", "true");
            connectionProperties.put("characterEncoding", "UTF-8");
            connectionProperties.put("useUnicode", "true");
            connectionProperties.put("serverTimezone", "UTC");
            connectionProperties.put("rewriteBatchedStatements", "true");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        CriarBanco.criar();
        return DriverManager.getConnection(DB_URL, connectionProperties);
    }

}