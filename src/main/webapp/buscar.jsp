<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar - CatÃ¡logo</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>ðŸ“š CatÃ¡logo de Livros e Filmes ðŸŽ¬</h1>
            <nav>
                <a href="index.jsp">InÃ­cio</a>
                <a href="cadastrar">Cadastrar</a>
                <a href="listar">Listar Todos</a>
                <a href="buscar.jsp" class="active">Buscar</a>
            </nav>
        </div>
    </header>
    <main class="container">
        <h2>ðŸ” Buscar Itens</h2>
        <div class="search-container">
            <form action="buscar" method="get" class="search-form">
                <div class="form-row">
                    <div class="form-group flex-2">
                        <label for="termo">Buscar por TÃ­tulo ou Autor/Diretor:</label>
                        <input type="text" name="termo" id="termo" 
                               value="<c:out value='${termoBusca}' />"
                               placeholder="Digite o termo de busca...">
                    </div>
                    <div class="form-group">
                        <label for="tipo">Filtrar por Tipo:</label>
                        <select name="tipo" id="tipo">
                            <option value="Todos" ${empty tipoBusca ? 'selected' : ''}>Todos</option>
                            <option value="Livro" ${tipoBusca == 'Livro' ? 'selected' : ''}>ðŸ“– Livro</option>
                            <option value="Filme" ${tipoBusca == 'Filme' ? 'selected' : ''}>ðŸŽ¬ Filme</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>&nbsp;</label>
                        <button type="submit" class="btn btn-primary">
                            ðŸ” Buscar
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <c:if test="${not empty erro}">
            <div class="alert alert-error">
                <c:out value="${erro}" />
            </div>
        </c:if>
        <c:if test="${not empty mensagem}">
            <div class="alert alert-info">
                <c:out value="${mensagem}" />
            </div>
        </c:if>
        <c:if test="${not empty itens}">
            <div class="search-results">
                <h3>Resultados da Busca</h3>
                <p class="results-count">Encontrados: <strong>${itens.size()}</strong> item(ns)</p>
                <div class="table-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Tipo</th>
                                <th>TÃ­tulo</th>
                                <th>Autor/Diretor</th>
                                <th>Ano</th>
                                <th>GÃªnero</th>
                                <th>AÃ§Ãµes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${itens}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.tipoMidia == 'Livro'}">
                                                <span class="badge badge-livro">ðŸ“– Livro</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-filme">ðŸŽ¬ Filme</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><c:out value="${item.titulo}" /></td>
                                    <td><c:out value="${item.autorDiretor}" /></td>
                                    <td><c:out value="${item.anoLancamento}" /></td>
                                    <td><c:out value="${item.genero}" /></td>
                                    <td class="actions">
                                        <a href="editar?id=${item.id}" class="btn-icon" title="Editar">
                                            âœï¸
                                        </a>
                                        <a href="excluir?id=${item.id}" class="btn-icon btn-delete" 
                                           title="Excluir" onclick="return confirmarExclusao()">
                                            ðŸ—‘ï¸
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
    </main>
    <footer>
        <div class="container">
            <p>&copy; 2025 CatÃ¡logo de MÃ­dia - Projeto AcadÃªmico ABP</p>
        </div>
    </footer>
    <script src="js/script.js"></script>
</body>
</html>
