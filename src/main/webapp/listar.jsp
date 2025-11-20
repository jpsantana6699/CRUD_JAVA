<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Itens - Catálogo</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>📚 Catálogo de Livros e Filmes 🎬</h1>
            <nav>
                <a href="index.jsp">Início</a>
                <a href="cadastrar">Cadastrar</a>
                <a href="listar" class="active">Listar Todos</a>
                <a href="buscar.jsp">Buscar</a>
            </nav>
        </div>
    </header>
    
    <main class="container">
        <h2>📋 Todos os Itens Cadastrados</h2>
        
        <!-- Mensagem de sucesso -->
        <c:if test="${not empty mensagemSucesso}">
            <div class="alert alert-success">
                <c:out value="${mensagemSucesso}" />
            </div>
        </c:if>
        
        <!-- Mensagem de erro -->
        <c:if test="${not empty erro}">
            <div class="alert alert-error">
                <c:out value="${erro}" />
            </div>
        </c:if>
        
        <!-- Verifica se há itens -->
        <c:choose>
            <c:when test="${empty itens}">
                <div class="alert alert-info">
                    <p>Nenhum item cadastrado ainda.</p>
                    <a href="cadastrar" class="btn btn-primary">Cadastrar Primeiro Item</a>
                </div>
            </c:when>
            <c:otherwise>
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
                
                <div class="table-info">
                    <p>Total de itens: <strong>${itens.size()}</strong></p>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 Catálogo de Mídia - Projeto Acadêmico ABP</p>
        </div>
    </footer>
    
    <script src="js/script.js"></script>
</body>
</html>
