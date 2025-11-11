package Application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Procedures {

    // Chama a procedure Reajuste
    public static void reajusteSalarios(Connection conn, Scanner sc) {
        try {
            System.out.print("Digite o percentual de reajuste: ");
            double percentual = sc.nextDouble();
            sc.nextLine();
            System.out.print("Digite o cargo: ");
            String cargo = sc.nextLine();

            CallableStatement stmt = conn.prepareCall("{CALL Reajuste(?, ?)}");
            stmt.setDouble(1, percentual);
            stmt.setString(2, cargo);
            stmt.execute();
            System.out.println("Reajuste aplicado com sucesso!");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar reajuste: " + e.getMessage());
        }
    }

    // Chama a procedure Sorteio
    public static void sorteioClientes(Connection conn, Scanner sc) {
        try {
            CallableStatement stmt = conn.prepareCall("{CALL Sorteio()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Cliente sorteado: " + rs.getInt("id_cliente"));
                System.out.println("Valor do voucher: R$ " + rs.getDouble("premio"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro no sorteio: " + e.getMessage());
        }
    }

    // Chama a procedure RegistrarVenda
    public static void registrarVenda(Connection conn, Scanner sc) {
        try {
            System.out.print("ID do produto: ");
            int produtoId = sc.nextInt();
            System.out.print("ID da venda: ");
            int vendaId = sc.nextInt();
            sc.nextLine();

            CallableStatement stmt = conn.prepareCall("{CALL RegistrarVenda(?, ?)}");
            stmt.setInt(1, produtoId);
            stmt.setInt(2, vendaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("mensagem"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    // Chama a procedure EstatisticasVendas
    public static void estatisticasVendas(Connection conn) {
    try {
        CallableStatement stmt = conn.prepareCall("{CALL EstatisticasVendas()}");
        ResultSet rs = stmt.executeQuery();

        System.out.println("--- Produto Mais Vendido ---");
        while (rs.next()) {
            System.out.println("Produto: " + rs.getString("produto_mais_vendido"));
            System.out.println("Vendedor: " + rs.getString("vendedor_associado"));
            System.out.println("Total vendido: " + rs.getInt("total_vendas"));
            System.out.println("Valor total: R$ " + rs.getDouble("valor_total"));
            System.out.println("Mês: " + rs.getInt("mes"));
            System.out.println("---------------------------");
        }

        if (stmt.getMoreResults()) {
            rs = stmt.getResultSet();
            System.out.println("--- Produto Menos Vendido ---");
            while (rs.next()) {
                System.out.println("Produto: " + rs.getString("produto_menos_vendido"));
                System.out.println("Total vendido: " + rs.getInt("total_vendas"));
                System.out.println("Vendedor: " + rs.getString("vendedor_associado"));
                System.out.println("Valor total: R$ " + rs.getDouble("valor_total"));
                System.out.println("Mês: " + rs.getInt("mes"));
                System.out.println("---------------------------");
            }
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        System.out.println("Erro ao executar estatísticas de vendas: " + e.getMessage());
    }
    }

}   