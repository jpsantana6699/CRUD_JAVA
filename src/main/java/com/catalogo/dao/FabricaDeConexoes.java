package com.catalogo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar conexões com o banco de dados MySQL.
 * Implementa o padrão Factory para criação de conexões.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class FabricaDeConexoes {
    
    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/catalogo_midia";
    private static final String USUARIO = "root";
    private static final String SENHA = "Admin";
    
    /**
     * Carrega o driver JDBC do MySQL
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver do MySQL", e);
        }
    }
    
    /**
     * Cria e retorna uma nova conexão com o banco de dados.
     * 
     * @return Connection objeto de conexão com o banco
     * @throws SQLException se houver erro ao conectar
     */
    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados de forma segura.
     * 
     * @param conexao Conexão a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    /**
     * Testa a conexão com o banco de dados.
     * 
     * @return true se a conexão foi estabelecida com sucesso
     */
    public static boolean testarConexao() {
        try (Connection conn = getConexao()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Falha no teste de conexão: " + e.getMessage());
            return false;
        }
    }
}
