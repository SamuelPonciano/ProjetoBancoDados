package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Views{

    public static void TotalVendaCliente(Connection conn) {
        String sql = "SELECT * FROM totalVenda_Cliente";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Total de Vendas por Cliente ---");
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nomeCliente = rs.getString("nome_cliente");
                int totalVendas = rs.getInt("total_vendas");
                double valorTotal = rs.getDouble("valor_total_gasto");

                System.out.println("ID Cliente: " + idCliente);
                System.out.println("Nome Cliente: " + nomeCliente);
                System.out.println("Total de Vendas: " + totalVendas);
                System.out.println("Valor Total Gasto: R$ " + valorTotal);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar a view totalVenda_Cliente: " + e.getMessage());
        }
    }

    public static void TotalVendasTransportadora(Connection conn) {
        String sql = "SELECT * FROM totalVendas_transportadora";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Total de Vendas por Transportadora ---");
            while (rs.next()) {
                int idTransportadora = rs.getInt("id_transportadora");
                String nomeTransportadora = rs.getString("nome_transportadora");
                int totalVendas = rs.getInt("total_vendas");
                double valorTotalVendido = rs.getDouble("valor_total_vendido");

                System.out.println("ID Transportadora: " + idTransportadora);
                System.out.println("Nome Transportadora: " + nomeTransportadora);
                System.out.println("Total de Vendas: " + totalVendas);
                System.out.println("Valor Total Vendido: R$ " + valorTotalVendido);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar a view totalVendas_transportadora: " + e.getMessage());
        }
    }

    
    public static void visualizarProdutosPorVendedor(Connection conn) {
    String sql = "SELECT * FROM view_produtos_por_vendedor";

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        System.out.println("\n--- Produtos Vendidos por Vendedor ---");
        while (rs.next()) {
            int idVendedor = rs.getInt("id_vendedor");
            String nomeVendedor = rs.getString("nome_vendedor");
            int idProduto = rs.getInt("id_produto");
            String nomeProduto = rs.getString("nome_produto");
            int quantidadeVendida = rs.getInt("quantidade_vendida");

            System.out.println("ID Vendedor: " + idVendedor);
            System.out.println("Nome Vendedor: " + nomeVendedor);
            System.out.println("ID Produto: " + idProduto);
            System.out.println("Nome Produto: " + nomeProduto);
            System.out.println("Quantidade Vendida: " + quantidadeVendida);
            System.out.println("---------------------------");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao consultar a view view_produtos_por_vendedor: " + e.getMessage());
    }
    }
}