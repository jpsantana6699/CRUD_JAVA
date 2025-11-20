package com.catalogo.service;

import com.catalogo.dao.ItemMidiaDAO;
import com.catalogo.model.ItemMidia;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe de serviço que implementa a lógica de negócio da aplicação.
 * Atua como intermediária entre os Servlets e o DAO.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class ItemMidiaService {
    
    private ItemMidiaDAO dao;
    
    /**
     * Construtor que inicializa o DAO
     */
    public ItemMidiaService() {
        this.dao = new ItemMidiaDAO();
    }
    
    /**
     * Cadastra um novo item de mídia com validações.
     * 
     * @param item ItemMidia a ser cadastrado
     * @return true se o cadastro foi bem-sucedido
     * @throws IllegalArgumentException se os dados forem inválidos
     * @throws SQLException se houver erro no banco de dados
     */
    public boolean cadastrarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        // Validações de negócio
        validarItem(item);
        
        // Delega a operação para o DAO
        return dao.inserir(item);
    }
    
    /**
     * Atualiza os dados de um item existente com validações.
     * 
     * @param item ItemMidia com dados atualizados
     * @return true se a atualização foi bem-sucedida
     * @throws IllegalArgumentException se os dados forem inválidos
     * @throws SQLException se houver erro no banco de dados
     */
    public boolean atualizarItem(ItemMidia item) throws IllegalArgumentException, SQLException {
        // Validações de negócio
        validarItem(item);
        
        if (item.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização");
        }
        
        return dao.atualizar(item);
    }
    
    /**
     * Exclui um item pelo ID.
     * 
     * @param id ID do item a ser excluído
     * @return true se a exclusão foi bem-sucedida
     * @throws IllegalArgumentException se o ID for inválido
     * @throws SQLException se houver erro no banco de dados
     */
    public boolean excluirItem(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return dao.excluir(id);
    }
    
    /**
     * Lista todos os itens cadastrados.
     * 
     * @return Lista de todos os itens
     * @throws SQLException se houver erro no banco de dados
     */
    public List<ItemMidia> listarTodosItens() throws SQLException {
        return dao.listarTodos();
    }
    
    /**
     * Busca um item específico pelo ID.
     * 
     * @param id ID do item
     * @return ItemMidia encontrado ou null
     * @throws IllegalArgumentException se o ID for inválido
     * @throws SQLException se houver erro no banco de dados
     */
    public ItemMidia buscarItemPorId(int id) throws IllegalArgumentException, SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return dao.buscarPorId(id);
    }
    
    /**
     * Busca itens por termo de pesquisa.
     * 
     * @param termo Termo de busca (título ou autor/diretor)
     * @return Lista de itens encontrados
     * @throws IllegalArgumentException se o termo for vazio
     * @throws SQLException se houver erro no banco de dados
     */
    public List<ItemMidia> buscarItensPorTermo(String termo) throws IllegalArgumentException, SQLException {
        if (termo == null || termo.trim().isEmpty()) {
            throw new IllegalArgumentException("Termo de busca não pode ser vazio");
        }
        
        return dao.buscarPorTermo(termo.trim());
    }
    
    /**
     * Busca itens por tipo de mídia.
     * 
     * @param tipo Tipo de mídia ("Livro" ou "Filme")
     * @return Lista de itens do tipo especificado
     * @throws IllegalArgumentException se o tipo for inválido
     * @throws SQLException se houver erro no banco de dados
     */
    public List<ItemMidia> buscarItensPorTipo(String tipo) throws IllegalArgumentException, SQLException {
        if (!validarTipoMidia(tipo)) {
            throw new IllegalArgumentException("Tipo de mídia inválido. Use 'Livro' ou 'Filme'");
        }
        
        return dao.buscarPorTipo(tipo);
    }
    
    /**
     * Valida os dados de um item de mídia.
     * 
     * @param item ItemMidia a ser validado
     * @throws IllegalArgumentException se algum dado for inválido
     */
    private void validarItem(ItemMidia item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        
        // Valida título
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        
        if (item.getTitulo().length() > 255) {
            throw new IllegalArgumentException("Título muito longo (máximo 255 caracteres)");
        }
        
        // Valida autor/diretor
        if (item.getAutorDiretor() == null || item.getAutorDiretor().trim().isEmpty()) {
            throw new IllegalArgumentException("Autor/Diretor é obrigatório");
        }
        
        if (item.getAutorDiretor().length() > 255) {
            throw new IllegalArgumentException("Nome do autor/diretor muito longo (máximo 255 caracteres)");
        }
        
        // Valida ano de lançamento
        int anoAtual = java.time.Year.now().getValue();
        if (item.getAnoLancamento() < 1800 || item.getAnoLancamento() > anoAtual + 5) {
            throw new IllegalArgumentException("Ano de lançamento inválido (1800 - " + (anoAtual + 5) + ")");
        }
        
        // Valida gênero
        if (item.getGenero() == null || item.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("Gênero é obrigatório");
        }
        
        if (item.getGenero().length() > 100) {
            throw new IllegalArgumentException("Gênero muito longo (máximo 100 caracteres)");
        }
        
        // Valida tipo de mídia
        if (!validarTipoMidia(item.getTipoMidia())) {
            throw new IllegalArgumentException("Tipo de mídia inválido. Use 'Livro' ou 'Filme'");
        }
        
        // Sinopse é opcional, mas se fornecida, valida o tamanho
        if (item.getSinopse() != null && item.getSinopse().length() > 5000) {
            throw new IllegalArgumentException("Sinopse muito longa (máximo 5000 caracteres)");
        }
    }
    
    /**
     * Valida se o tipo de mídia é válido.
     * 
     * @param tipo Tipo de mídia a validar
     * @return true se for "Livro" ou "Filme"
     */
    private boolean validarTipoMidia(String tipo) {
        return tipo != null && (tipo.equals("Livro") || tipo.equals("Filme"));
    }
}
