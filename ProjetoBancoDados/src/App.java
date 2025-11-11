import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Application.AdmMenu;
import Application.GerenteMenu;
import Application.FuncionarioMenu;
import database.InicializarBanco;

public class App {
    public static void main(String[] args) {
        InicializarBanco.delete();
        InicializarBanco.criar();
        String url = "jdbc:mysql://localhost:3306/bancosistemaecommerce";
        Scanner sc = new Scanner(System.in);

        System.out.println("LOGIN");
        System.out.print("Usuário: ");
        String usuario = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {

            // Executa menu conforme tipo de usuário
            switch (usuario) {
                case "administrador" -> AdmMenu.menu(conn, sc);
                case "gerente" -> GerenteMenu.menu(conn, sc);
                case "funcionario" -> FuncionarioMenu.menu(conn, sc);
                default -> System.out.println("Usuário inválido!");
            }

        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            InicializarBanco.delete();
            System.out.println("Banco destruído ao finalizar o sistema.");
        }
    }
}
