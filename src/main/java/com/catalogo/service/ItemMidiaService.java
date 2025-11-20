package com.catalogo.service;
import com.catalogo.dao.ItemMidiaDAO;
import com.catalogo.model.ItemMidia;
import java.sql.SQLException;
import java.util.List;
public class ItemMidiaService {
    private ItemMidiaDAO dao;
    public ItemMidiaService() {
        this.dao = new ItemMidiaDAO();
    }
    public boolean cadastrarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        validarItem(item);
        return dao.inserir(item);
    }
    public boolean atualizarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        validarItem(item);
        if (item.getId() <= 0) {
            throw new IllegalArgumentException("ID invÃ¡lido para atualizaÃ§Ã£o");
        }
        return dao.atualizar(item);
    }
    public boolean excluirItem(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invÃ¡lido");
        }
        return dao.excluir(id);
    }
    public List<ItemMidia> listarTodosItens() throws SQLException {
        return dao.listarTodos();
    }
    public ItemMidia buscarItemPorId(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invÃ¡lido");
        }
        return dao.buscarPorId(id);
    }
    public List<ItemMidia> buscarItensPorTermo(String termo) throws IllegalArgumentException, SQLException {
        if (termo == null || termo.trim().isEmpty()) {
            throw new IllegalArgumentException("Termo de busca nÃ£o pode ser vazio");
        }
        return dao.buscarPorTermo(termo.trim());
    }
    public List<ItemMidia> buscarItensPorTipo(String tipo) throws IllegalArgumentException, SQLException {
        if (!validarTipoMidia(tipo)) {
            throw new IllegalArgumentException("Tipo de mÃ­dia invÃ¡lido. Use 'Livro' ou 'Filme'");
        }
        return dao.buscarPorTipo(tipo);
    }
    private void validarItem(ItemMidia item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item nÃ£o pode ser nulo");
        }
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("TÃ­tulo Ã© obrigatÃ³rio");
        }
        if (item.getTitulo().length() > 255) {
            throw new IllegalArgumentException("TÃ­tulo muito longo (mÃ¡ximo 255 caracteres)");
        }
        if (item.getAutorDiretor() == null || item.getAutorDiretor().trim().isEmpty()) {
            throw new IllegalArgumentException("Autor/Diretor Ã© obrigatÃ³rio");
        }
        if (item.getAutorDiretor().length() > 255) {
            throw new IllegalArgumentException("Nome do autor/diretor muito longo (mÃ¡ximo 255 caracteres)");
        }
        int anoAtual = java.time.Year.now().getValue();
        if (item.getAnoLancamento() < 1800 || item.getAnoLancamento() > anoAtual + 5) {
            throw new IllegalArgumentException("Ano de lanÃ§amento invÃ¡lido (1800 - " + (anoAtual + 5) + ")");
        }
        if (item.getGenero() == null || item.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("GÃªnero Ã© obrigatÃ³rio");
        }
        if (item.getGenero().length() > 100) {
            throw new IllegalArgumentException("GÃªnero muito longo (mÃ¡ximo 100 caracteres)");
        }
        if (!validarTipoMidia(item.getTipoMidia())) {
            throw new IllegalArgumentException("Tipo de mÃ­dia invÃ¡lido. Use 'Livro' ou 'Filme'");
        }
        if (item.getSinopse() != null && item.getSinopse().length() > 5000) {
            throw new IllegalArgumentException("Sinopse muito longa (mÃ¡ximo 5000 caracteres)");
        }
    }
    private boolean validarTipoMidia(String tipo) {
        return tipo != null && (tipo.equals("Livro") || tipo.equals("Filme"));
    }
}
