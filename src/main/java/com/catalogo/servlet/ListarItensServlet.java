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
public class ListarItensServlet extends HttpServlet {
    private ItemMidiaService service;
    @Override
    public void init() throws ServletException {
        service = new ItemMidiaService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            List<ItemMidia> itens = service.listarTodosItens();
            request.setAttribute("itens", itens);
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
                        request.setAttribute("mensagemSucesso", "Item excluÃ­do com sucesso!");
                        break;
                }
            }
            request.getRequestDispatcher("/listar.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar itens: " + e.getMessage());
            request.getRequestDispatcher("/listar.jsp").forward(request, response);
        }
    }
}
