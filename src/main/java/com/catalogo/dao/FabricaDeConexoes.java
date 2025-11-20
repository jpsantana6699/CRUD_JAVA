package com.catalogo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FabricaDeConexoes {
    private static final String URL = "jdbc:mysql:
    private static final String USUARIO = "root";
    private static final String SENHA = "Admin";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver do MySQL", e);
        }
    }
    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw e;
        }
    }
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexÃ£o: " + e.getMessage());
            }
        }
    }
    public static boolean testarConexao() {
        try (Connection conn = getConexao()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Falha no teste de conexÃ£o: " + e.getMessage());
            return false;
        }
    }
}
