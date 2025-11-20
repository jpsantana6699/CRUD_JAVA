package com.catalogo.servlet;
import com.catalogo.model.ItemMidia;
import com.catalogo.service.ItemMidiaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
public class CadastrarItemServlet extends HttpServlet {
    private ItemMidiaService service;
    @Override
    public void init() throws ServletException {
        service = new ItemMidiaService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String titulo = request.getParameter("titulo");
            String autorDiretor = request.getParameter("autorDiretor");
            String anoStr = request.getParameter("anoLancamento");
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            String tipoMidia = request.getParameter("tipoMidia");
            if (titulo == null || titulo.trim().isEmpty() ||
                autorDiretor == null || autorDiretor.trim().isEmpty() ||
                anoStr == null || anoStr.trim().isEmpty() ||
                genero == null || genero.trim().isEmpty() ||
                tipoMidia == null || tipoMidia.trim().isEmpty()) {
                request.setAttribute("erro", "Todos os campos obrigatÃ³rios devem ser preenchidos!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            int anoLancamento;
            try {
                anoLancamento = Integer.parseInt(anoStr);
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Ano de lanÃ§amento invÃ¡lido!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            ItemMidia item = new ItemMidia(
                titulo.trim(),
                autorDiretor.trim(),
                anoLancamento,
                genero.trim(),
                sinopse != null ? sinopse.trim() : "",
                tipoMidia
            );
            boolean sucesso = service.cadastrarItem(item);
            if (sucesso) {
                response.sendRedirect("listar?sucesso=cadastro");
            } else {
                request.setAttribute("erro", "Erro ao cadastrar o item. Tente novamente.");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao acessar o banco de dados: " + e.getMessage());
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
    }
}
