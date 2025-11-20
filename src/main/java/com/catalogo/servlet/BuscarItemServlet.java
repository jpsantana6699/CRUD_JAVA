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
public class BuscarItemServlet extends HttpServlet {
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
            String termo = request.getParameter("termo");
            String tipo = request.getParameter("tipo");
            List<ItemMidia> resultados;
            if (tipo != null && !tipo.trim().isEmpty() && !tipo.equals("Todos")) {
                resultados = service.buscarItensPorTipo(tipo);
                request.setAttribute("tipoBusca", tipo);
            } else if (termo != null && !termo.trim().isEmpty()) {
                resultados = service.buscarItensPorTermo(termo);
                request.setAttribute("termoBusca", termo);
            } else {
                resultados = service.listarTodosItens();
            }
            request.setAttribute("itens", resultados);
            if (resultados.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum item encontrado.");
            }
            request.getRequestDispatcher("/buscar.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/buscar.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao realizar busca: " + e.getMessage());
            request.getRequestDispatcher("/buscar.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
