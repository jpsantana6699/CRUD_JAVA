package com.catalogo.servlet;
import com.catalogo.service.ItemMidiaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
public class ExcluirItemServlet extends HttpServlet {
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
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                response.sendRedirect("listar?erro=id_invalido");
                return;
            }
            int id = Integer.parseInt(idStr);
            boolean sucesso = service.excluirItem(id);
            if (sucesso) {
                response.sendRedirect("listar?sucesso=exclusao");
            } else {
                response.sendRedirect("listar?erro=exclusao_falhou");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listar?erro=id_invalido");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("listar?erro=" + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("listar?erro=erro_banco_dados");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
