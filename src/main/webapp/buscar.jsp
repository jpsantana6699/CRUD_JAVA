<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar - Catálogo</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>📚 Catálogo de Livros e Filmes 🎬</h1>
            <nav>
                <a href="index.jsp">Início</a>
                <a href="cadastrar">Cadastrar</a>
                <a href="listar">Listar Todos</a>
                <a href="buscar.jsp" class="active">Buscar</a>
            </nav>
        </div>
    </header>
    
    <main class="container">
        <h2>🔍 Buscar Itens</h2>
        
        <!-- Formulário de busca -->
        <div class="search-container">
            <form action="buscar" method="get" class="search-form">
                <div class="form-row">
                    <div class="form-group flex-2">
                        <label for="termo">Buscar por Título ou Autor/Diretor:</label>
                        <input type="text" name="termo" id="termo" 
                               value="<c:out value='${termoBusca}' />"
                               placeholder="Digite o termo de busca...">
                    </div>
                    
                    <div class="form-group">
                        <label for="tipo">Filtrar por Tipo:</label>
                        <select name="tipo" id="tipo">
                            <option value="Todos" ${empty tipoBusca ? 'selected' : ''}>Todos</option>
                            <option value="Livro" ${tipoBusca == 'Livro' ? 'selected' : ''}>📖 Livro</option>
                            <option value="Filme" ${tipoBusca == 'Filme' ? 'selected' : ''}>🎬 Filme</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>&nbsp;</label>
                        <button type="submit" class="btn btn-primary">
                            🔍 Buscar
                        </button>
                    </div>
                </div>
            </form>
        </div>
        
        <!-- Mensagem de erro -->
        <c:if test="${not empty erro}">
            <div class="alert alert-error">
                <c:out value="${erro}" />
            </div>
        </c:if>
        
        <!-- Mensagem informativa -->
        <c:if test="${not empty mensagem}">
            <div class="alert alert-info">
                <c:out value="${mensagem}" />
            </div>
        </c:if>
        
        <!-- Resultados da busca -->
        <c:if test="${not empty itens}">
            <div class="search-results">
                <h3>Resultados da Busca</h3>
                <p class="results-count">Encontrados: <strong>${itens.size()}</strong> item(ns)</p>
                
                <div class="table-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Tipo</th>
                                <th>Título</th>
                                <th>Autor/Diretor</th>
                                <th>Ano</th>
                                <th>Gênero</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${itens}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.tipoMidia == 'Livro'}">
                                                <span class="badge badge-livro">📖 Livro</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-filme">🎬 Filme</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><c:out value="${item.titulo}" /></td>
                                    <td><c:out value="${item.autorDiretor}" /></td>
                                    <td><c:out value="${item.anoLancamento}" /></td>
                                    <td><c:out value="${item.genero}" /></td>
                                    <td class="actions">
                                        <a href="editar?id=${item.id}" class="btn-icon" title="Editar">
                                            ✏️
                                        </a>
                                        <a href="excluir?id=${item.id}" class="btn-icon btn-delete" 
                                           title="Excluir" onclick="return confirmarExclusao()">
                                            🗑️
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
            <p>&copy; 2025 Catálogo de Mídia - Projeto Acadêmico ABP</p>
        </div>
    </footer>
    
    <script src="js/script.js"></script>
</body>
</html>
