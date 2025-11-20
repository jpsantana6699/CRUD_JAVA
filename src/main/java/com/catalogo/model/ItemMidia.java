package com.catalogo.model;

/**
 * Classe modelo que representa um item de mídia (Livro ou Filme) no catálogo.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class ItemMidia {
    
    private int id;
    private String titulo;
    private String autorDiretor;
    private int anoLancamento;
    private String genero;
    private String sinopse;
    private String tipoMidia; // "Livro" ou "Filme"
    
    /**
     * Construtor padrão vazio
     */
    public ItemMidia() {
    }
    
    /**
     * Construtor completo com todos os atributos
     * 
     * @param id Identificador único do item
     * @param titulo Título do livro ou filme
     * @param autorDiretor Nome do autor (livro) ou diretor (filme)
     * @param anoLancamento Ano de lançamento
     * @param genero Gênero da obra (ficção, drama, ação, etc)
     * @param sinopse Descrição resumida da obra
     * @param tipoMidia Tipo de mídia ("Livro" ou "Filme")
     */
    public ItemMidia(int id, String titulo, String autorDiretor, int anoLancamento, 
                     String genero, String sinopse, String tipoMidia) {
        this.id = id;
        this.titulo = titulo;
        this.autorDiretor = autorDiretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.sinopse = sinopse;
        this.tipoMidia = tipoMidia;
    }
    
    /**
     * Construtor sem ID (usado ao inserir novos registros)
     */
    public ItemMidia(String titulo, String autorDiretor, int anoLancamento, 
                     String genero, String sinopse, String tipoMidia) {
        this.titulo = titulo;
        this.autorDiretor = autorDiretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.sinopse = sinopse;
        this.tipoMidia = tipoMidia;
    }
    
    // ==================== GETTERS ====================
    
    /**
     * @return ID do item
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return Título do item
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * @return Nome do autor ou diretor
     */
    public String getAutorDiretor() {
        return autorDiretor;
    }
    
    /**
     * @return Ano de lançamento
     */
    public int getAnoLancamento() {
        return anoLancamento;
    }
    
    /**
     * @return Gênero da obra
     */
    public String getGenero() {
        return genero;
    }
    
    /**
     * @return Sinopse da obra
     */
    public String getSinopse() {
        return sinopse;
    }
    
    /**
     * @return Tipo de mídia (Livro ou Filme)
     */
    public String getTipoMidia() {
        return tipoMidia;
    }
    
    // ==================== SETTERS ====================
    
    /**
     * @param id Define o ID do item
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param titulo Define o título do item
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * @param autorDiretor Define o nome do autor ou diretor
     */
    public void setAutorDiretor(String autorDiretor) {
        this.autorDiretor = autorDiretor;
    }
    
    /**
     * @param anoLancamento Define o ano de lançamento
     */
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    
    /**
     * @param genero Define o gênero da obra
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    /**
     * @param sinopse Define a sinopse da obra
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    
    /**
     * @param tipoMidia Define o tipo de mídia
     */
    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }
    
    /**
     * Representação em String do objeto ItemMidia
     * 
     * @return String com informações do item
     */
    @Override
    public String toString() {
        return "ItemMidia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autorDiretor='" + autorDiretor + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", genero='" + genero + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", tipoMidia='" + tipoMidia + '\'' +
                '}';
    }
}
