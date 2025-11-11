package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Functions {

    public static void calculaIdade(Connection conn, Scanner sc) {
        try {
            System.out.print("Digite o ID do cliente: ");
            int idCliente = sc.nextInt();
            sc.nextLine();

            PreparedStatement stmt = conn.prepareStatement("SELECT Calcular_idade(?) AS idade");
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Idade do cliente: " + rs.getInt("idade") + " anos");
            } else {
                System.out.println("Cliente não encontrado!");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao calcular idade: " + e.getMessage());
        }
    }

    public static void somaFretes(Connection conn, Scanner sc) {
    try {
        System.out.print("Digite o endereço de destino: ");
        String destino = sc.nextLine();

        PreparedStatement stmt = conn.prepareStatement("SELECT Soma_fretes(?) AS total");
        stmt.setString(1, destino);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Total de fretes para \"" + destino + "\": R$ " + rs.getDouble("total"));
        } else {
            System.out.println("Nenhum frete encontrado para este destino.");
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println("Erro ao calcular soma de fretes: " + e.getMessage());
    }
}


   public static void arrecadado(Connection conn, Scanner sc) {
    try {
        System.out.print("Digite a data (YYYY-MM-DD): ");
        String data = sc.nextLine().trim();

        System.out.print("Digite o ID do vendedor: ");
        int idVendedor = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT Arrecadado(?, ?) AS total";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            stmt.setInt(2, idVendedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double total = rs.getDouble("total");
                    System.out.printf("💰 Total arrecadado pelo vendedor %d em %s: R$ %.2f%n", idVendedor, data, total);
                } else {
                    System.out.println("Nenhuma venda encontrada para este vendedor nesta data.");
                }
            }
        }

    } catch (SQLException e) {
        System.out.println("Erro ao calcular arrecadado: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}

}
