package com.catalogo.model;
public class ItemMidia {
    private int id;
    private String titulo;
    private String autorDiretor;
    private int anoLancamento;
    private String genero;
    private String sinopse;
    private String tipoMidia; 
    public ItemMidia() {
    }
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
    public ItemMidia(String titulo, String autorDiretor, int anoLancamento, 
                     String genero, String sinopse, String tipoMidia) {
        this.titulo = titulo;
        this.autorDiretor = autorDiretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.sinopse = sinopse;
        this.tipoMidia = tipoMidia;
    }
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutorDiretor() {
        return autorDiretor;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public String getGenero() {
        return genero;
    }
    public String getSinopse() {
        return sinopse;
    }
    public String getTipoMidia() {
        return tipoMidia;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutorDiretor(String autorDiretor) {
        this.autorDiretor = autorDiretor;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }
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
