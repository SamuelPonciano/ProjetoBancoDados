package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializarBanco {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DB_NAME = "bancosistemaecommerce";

    public static void criar() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            CriarTabelas.criar(stmt);
            InserirDados.inserir(conn);
            CriarFunction.criar(stmt);
            CriarTriggers.criar(stmt);
            CriarViews.criar(stmt);
            CriarProcedures.criar(stmt);
            CriarUsuario.criar(stmt);

            System.out.println("Banco de dados criado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DROP DATABASE IF EXISTS " + DB_NAME);
            System.out.println("Banco de dados DELETADO com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
