<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Item - Catálogo</title>
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
                <a href="buscar.jsp">Buscar</a>
            </nav>
        </div>
    </header>
    
    <main class="container">
        <div class="form-container">
            <h2>✏️ Editar Item</h2>
            
            <!-- Exibe mensagem de erro se houver -->
            <c:if test="${not empty erro}">
                <div class="alert alert-error">
                    <c:out value="${erro}" />
                </div>
            </c:if>
            
            <c:if test="${not empty item}">
                <form action="editar" method="post" id="formEditar" onsubmit="return validarFormulario()">
                    <input type="hidden" name="id" value="${item.id}">
                    
                    <div class="form-group">
                        <label for="tipoMidia">Tipo de Mídia: <span class="obrigatorio">*</span></label>
                        <select name="tipoMidia" id="tipoMidia" required>
                            <option value="Livro" ${item.tipoMidia == 'Livro' ? 'selected' : ''}>📖 Livro</option>
                            <option value="Filme" ${item.tipoMidia == 'Filme' ? 'selected' : ''}>🎬 Filme</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="titulo">Título: <span class="obrigatorio">*</span></label>
                        <input type="text" name="titulo" id="titulo" required maxlength="255" 
                               value="<c:out value='${item.titulo}' />" placeholder="Digite o título">
                    </div>
                    
                    <div class="form-group">
                        <label for="autorDiretor">Autor/Diretor: <span class="obrigatorio">*</span></label>
                        <input type="text" name="autorDiretor" id="autorDiretor" required maxlength="255"
                               value="<c:out value='${item.autorDiretor}' />" placeholder="Digite o nome do autor ou diretor">
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="anoLancamento">Ano de Lançamento: <span class="obrigatorio">*</span></label>
                            <input type="number" name="anoLancamento" id="anoLancamento" required 
                                   min="1800" max="2030" value="${item.anoLancamento}" placeholder="Ex: 2024">
                        </div>
                        
                        <div class="form-group">
                            <label for="genero">Gênero: <span class="obrigatorio">*</span></label>
                            <input type="text" name="genero" id="genero" required maxlength="100"
                                   value="<c:out value='${item.genero}' />" placeholder="Ex: Ficção, Drama, Ação">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="sinopse">Sinopse:</label>
                        <textarea name="sinopse" id="sinopse" rows="5" maxlength="5000"
                                  placeholder="Descreva brevemente o conteúdo (opcional)"><c:out value="${item.sinopse}" /></textarea>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            💾 Salvar Alterações
                        </button>
                        <a href="listar" class="btn btn-secondary">
                            ❌ Cancelar
                        </a>
                    </div>
                    
                    <p class="form-info">
                        <span class="obrigatorio">*</span> Campos obrigatórios
                    </p>
                </form>
            </c:if>
            
            <c:if test="${empty item}">
                <div class="alert alert-error">
                    <p>Item não encontrado!</p>
                    <a href="listar" class="btn btn-primary">Voltar para Listagem</a>
                </div>
            </c:if>
        </div>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 Catálogo de Mídia - Projeto Acadêmico ABP</p>
        </div>
    </footer>
    
    <script src="js/script.js"></script>
</body>
</html>
