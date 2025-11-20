package com.catalogo.servlet;
import com.catalogo.model.ItemMidia;
import com.catalogo.service.ItemMidiaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
public class EditarItemServlet extends HttpServlet {
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
            ItemMidia item = service.buscarItemPorId(id);
            if (item == null) {
                response.sendRedirect("listar?erro=item_nao_encontrado");
                return;
            }
            request.setAttribute("item", item);
            request.getRequestDispatcher("/editar.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("listar?erro=id_invalido");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao buscar item: " + e.getMessage());
            request.getRequestDispatcher("/listar.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String idStr = request.getParameter("id");
            String titulo = request.getParameter("titulo");
            String autorDiretor = request.getParameter("autorDiretor");
            String anoStr = request.getParameter("anoLancamento");
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            String tipoMidia = request.getParameter("tipoMidia");
            if (idStr == null || titulo == null || titulo.trim().isEmpty() ||
                autorDiretor == null || autorDiretor.trim().isEmpty() ||
                anoStr == null || anoStr.trim().isEmpty() ||
                genero == null || genero.trim().isEmpty() ||
                tipoMidia == null || tipoMidia.trim().isEmpty()) {
                request.setAttribute("erro", "Todos os campos obrigatÃ³rios devem ser preenchidos!");
                doGet(request, response);
                return;
            }
            int id = Integer.parseInt(idStr);
            int anoLancamento = Integer.parseInt(anoStr);
            ItemMidia item = new ItemMidia(
                id,
                titulo.trim(),
                autorDiretor.trim(),
                anoLancamento,
                genero.trim(),
                sinopse != null ? sinopse.trim() : "",
                tipoMidia
            );
            boolean sucesso = service.atualizarItem(item);
            if (sucesso) {
                response.sendRedirect("listar?sucesso=edicao");
            } else {
                request.setAttribute("erro", "Erro ao atualizar o item. Tente novamente.");
                request.setAttribute("item", item);
                request.getRequestDispatcher("/editar.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Dados numÃ©ricos invÃ¡lidos!");
            doGet(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao acessar o banco de dados: " + e.getMessage());
            doGet(request, response);
        }
    }
}
