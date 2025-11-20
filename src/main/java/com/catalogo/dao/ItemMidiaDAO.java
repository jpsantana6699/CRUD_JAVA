package com.catalogo.dao;

import com.catalogo.model.ItemMidia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de banco de dados
 * relacionadas aos itens de mídia. Implementa CRUD completo com segurança contra SQL Injection.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class ItemMidiaDAO {
    
    /**
     * Insere um novo item de mídia no banco de dados.
     * Utiliza PreparedStatement para prevenir SQL Injection.
     * 
     * @param item ItemMidia a ser inserido
     * @return true se a inserção foi bem-sucedida
     * @throws SQLException se houver erro na operação
     */
    public boolean inserir(ItemMidia item) throws SQLException {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            
            // Define os parâmetros de forma segura
            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir item: " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Atualiza os dados de um item de mídia existente.
     * Utiliza PreparedStatement para prevenir SQL Injection.
     * 
     * @param item ItemMidia com dados atualizados
     * @return true se a atualização foi bem-sucedida
     * @throws SQLException se houver erro na operação
     */
    public boolean atualizar(ItemMidia item) throws SQLException {
        String sql = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, " +
                     "genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());
            stmt.setInt(7, item.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar item: " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Exclui um item de mídia do banco de dados pelo ID.
     * Utiliza PreparedStatement para prevenir SQL Injection.
     * 
     * @param id ID do item a ser excluído
     * @return true se a exclusão foi bem-sucedida
     * @throws SQLException se houver erro na operação
     */
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM item_midia WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir item: " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Lista todos os itens de mídia cadastrados no banco.
     * 
     * @return Lista com todos os itens
     * @throws SQLException se houver erro na operação
     */
    public List<ItemMidia> listarTodos() throws SQLException {
        String sql = "SELECT * FROM item_midia ORDER BY titulo";
        List<ItemMidia> lista = new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemMidia item = extrairItemDoResultSet(rs);
                lista.add(item);
            }
            
            return lista;
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Busca um item específico pelo ID.
     * Utiliza PreparedStatement para prevenir SQL Injection.
     * 
     * @param id ID do item a ser buscado
     * @return ItemMidia encontrado ou null se não existir
     * @throws SQLException se houver erro na operação
     */
    public ItemMidia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extrairItemDoResultSet(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar item por ID: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Busca itens por termo (título ou autor/diretor).
     * Utiliza PreparedStatement para prevenir SQL Injection.
     * 
     * @param termo Termo de busca (título ou autor/diretor)
     * @return Lista de itens que correspondem ao termo
     * @throws SQLException se houver erro na operação
     */
    public List<ItemMidia> buscarPorTermo(String termo) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ? ORDER BY titulo";
        List<ItemMidia> lista = new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            
            // Adiciona % para busca parcial
            String termoBusca = "%" + termo + "%";
            stmt.setString(1, termoBusca);
            stmt.setString(2, termoBusca);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemMidia item = extrairItemDoResultSet(rs);
                lista.add(item);
            }
            
            return lista;
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por termo: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Busca itens por tipo de mídia (Livro ou Filme).
     * 
     * @param tipo Tipo de mídia ("Livro" ou "Filme")
     * @return Lista de itens do tipo especificado
     * @throws SQLException se houver erro na operação
     */
    public List<ItemMidia> buscarPorTipo(String tipo) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE tipo_midia = ? ORDER BY titulo";
        List<ItemMidia> lista = new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemMidia item = extrairItemDoResultSet(rs);
                lista.add(item);
            }
            
            return lista;
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por tipo: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            FabricaDeConexoes.fecharConexao(conexao);
        }
    }
    
    /**
     * Método auxiliar para extrair um ItemMidia do ResultSet.
     * 
     * @param rs ResultSet com os dados do item
     * @return ItemMidia criado a partir dos dados
     * @throws SQLException se houver erro ao ler os dados
     */
    private ItemMidia extrairItemDoResultSet(ResultSet rs) throws SQLException {
        ItemMidia item = new ItemMidia();
        item.setId(rs.getInt("id"));
        item.setTitulo(rs.getString("titulo"));
        item.setAutorDiretor(rs.getString("autor_diretor"));
        item.setAnoLancamento(rs.getInt("ano_lancamento"));
        item.setGenero(rs.getString("genero"));
        item.setSinopse(rs.getString("sinopse"));
        item.setTipoMidia(rs.getString("tipo_midia"));
        return item;
    }
}
