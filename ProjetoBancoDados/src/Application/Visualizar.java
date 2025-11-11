package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Visualizar {

    public static void visualizarClientes(Connection conn) {
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Clientes ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Sexo: %s | Idade: %d | Data Nasc: %s%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getInt("idade"),
                        rs.getDate("dataNasc"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar clientes: " + e.getMessage());
        }
    }

    public static void visualizarProdutos(Connection conn) {
        String sql = "SELECT * FROM produtos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Produtos ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Quantidade: %d | Valor: %.2f | Observações: %s%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getDouble("valor"),
                        rs.getString("observacoes"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar produtos: " + e.getMessage());
        }
    }

    public static void visualizarVendas(Connection conn) {
        String sql = "SELECT v.id, c.nome AS cliente, p.nome AS produto, v.quantidade, v.vendedor_id, v.data_venda " +
                     "FROM vendas v " +
                     "JOIN clientes c ON v.cliente_id = c.id " +
                     "JOIN produtos p ON v.produto_id = p.id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Vendas ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Cliente: %s | Produto: %s | Quantidade: %d | Vendedor ID: %d | Data: %s%n",
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("produto"),
                        rs.getInt("quantidade"),
                        rs.getInt("vendedor_id"),
                        rs.getDate("data_venda"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar vendas: " + e.getMessage());
        }
    }

    public static void visualizarFuncionarios(Connection conn) {
        String sql = "SELECT * FROM funcionario";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Funcionários ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Função: %s | Salário: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("funcao"),
                        rs.getDouble("salario"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar funcionários: " + e.getMessage());
        }
    }

    public static void visualizarTransportadoras(Connection conn) {
        String sql = "SELECT * FROM transportadoras";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Transportadoras ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Valor Frete: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("valor_frete"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar transportadoras: " + e.getMessage());
        }
    }
}
