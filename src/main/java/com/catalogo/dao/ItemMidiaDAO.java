package com.catalogo.dao;
import com.catalogo.model.ItemMidia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ItemMidiaDAO {
    public boolean inserir(ItemMidia item) throws SQLException {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
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
    public List<ItemMidia> buscarPorTermo(String termo) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ? ORDER BY titulo";
        List<ItemMidia> lista = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = FabricaDeConexoes.getConexao();
            stmt = conexao.prepareStatement(sql);
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
