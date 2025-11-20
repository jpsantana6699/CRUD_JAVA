package com.catalogo.servlet;

import com.catalogo.model.ItemMidia;
import com.catalogo.service.ItemMidiaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet responsável por listar todos os itens de mídia cadastrados.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class ListarItensServlet extends HttpServlet {
    
    private ItemMidiaService service;
    
    @Override
    public void init() throws ServletException {
        service = new ItemMidiaService();
    }
    
    /**
     * Lista todos os itens de mídia cadastrados.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        try {
            // Busca todos os itens
            List<ItemMidia> itens = service.listarTodosItens();
            
            // Define os itens como atributo da requisição
            request.setAttribute("itens", itens);
            
            // Verifica se há mensagem de sucesso
            String sucesso = request.getParameter("sucesso");
            if (sucesso != null) {
                switch (sucesso) {
                    case "cadastro":
                        request.setAttribute("mensagemSucesso", "Item cadastrado com sucesso!");
                        break;
                    case "edicao":
                        request.setAttribute("mensagemSucesso", "Item atualizado com sucesso!");
                        break;
                    case "exclusao":
                        request.setAttribute("mensagemSucesso", "Item excluído com sucesso!");
                        break;
                }
            }
            
            // Encaminha para a página JSP
            request.getRequestDispatcher("/listar.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar itens: " + e.getMessage());
            request.getRequestDispatcher("/listar.jsp").forward(request, response);
        }
    }
}
