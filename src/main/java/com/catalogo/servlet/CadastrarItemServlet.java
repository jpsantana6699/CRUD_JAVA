package com.catalogo.servlet;

import com.catalogo.model.ItemMidia;
import com.catalogo.service.ItemMidiaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet responsável pelo cadastro de novos itens de mídia.
 * 
 * @author João Pedro Santana
 * @version 1.0
 */
public class CadastrarItemServlet extends HttpServlet {
    
    private ItemMidiaService service;
    
    @Override
    public void init() throws ServletException {
        service = new ItemMidiaService();
    }
    
    /**
     * Exibe o formulário de cadastro.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
    }
    
    /**
     * Processa o formulário de cadastro enviado pelo usuário.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Configura encoding UTF-8 para suportar caracteres especiais
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        try {
            // Captura e valida os parâmetros do formulário
            String titulo = request.getParameter("titulo");
            String autorDiretor = request.getParameter("autorDiretor");
            String anoStr = request.getParameter("anoLancamento");
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            String tipoMidia = request.getParameter("tipoMidia");
            
            // Validação básica no servidor
            if (titulo == null || titulo.trim().isEmpty() ||
                autorDiretor == null || autorDiretor.trim().isEmpty() ||
                anoStr == null || anoStr.trim().isEmpty() ||
                genero == null || genero.trim().isEmpty() ||
                tipoMidia == null || tipoMidia.trim().isEmpty()) {
                
                request.setAttribute("erro", "Todos os campos obrigatórios devem ser preenchidos!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            
            // Converte o ano para inteiro
            int anoLancamento;
            try {
                anoLancamento = Integer.parseInt(anoStr);
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Ano de lançamento inválido!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            
            // Cria o objeto ItemMidia
            ItemMidia item = new ItemMidia(
                titulo.trim(),
                autorDiretor.trim(),
                anoLancamento,
                genero.trim(),
                sinopse != null ? sinopse.trim() : "",
                tipoMidia
            );
            
            // Tenta cadastrar o item
            boolean sucesso = service.cadastrarItem(item);
            
            if (sucesso) {
                // Redireciona para a listagem com mensagem de sucesso
                response.sendRedirect("listar?sucesso=cadastro");
            } else {
                request.setAttribute("erro", "Erro ao cadastrar o item. Tente novamente.");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            }
            
        } catch (IllegalArgumentException e) {
            // Erro de validação
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            
        } catch (SQLException e) {
            // Erro de banco de dados
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao acessar o banco de dados: " + e.getMessage());
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
    }
}
