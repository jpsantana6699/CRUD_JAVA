<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Item - CatÃ¡logo</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>ðŸ“š CatÃ¡logo de Livros e Filmes ðŸŽ¬</h1>
            <nav>
                <a href="index.jsp">InÃ­cio</a>
                <a href="cadastrar" class="active">Cadastrar</a>
                <a href="listar">Listar Todos</a>
                <a href="buscar.jsp">Buscar</a>
            </nav>
        </div>
    </header>
    <main class="container">
        <div class="form-container">
            <h2>ðŸ“ Cadastrar Novo Item</h2>
            <c:if test="${not empty erro}">
                <div class="alert alert-error">
                    <c:out value="${erro}" />
                </div>
            </c:if>
            <form action="cadastrar" method="post" id="formCadastro" onsubmit="return validarFormulario()">
                <div class="form-group">
                    <label for="tipoMidia">Tipo de MÃ­dia: <span class="obrigatorio">*</span></label>
                    <select name="tipoMidia" id="tipoMidia" required>
                        <option value="">Selecione...</option>
                        <option value="Livro">ðŸ“– Livro</option>
                        <option value="Filme">ðŸŽ¬ Filme</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="titulo">TÃ­tulo: <span class="obrigatorio">*</span></label>
                    <input type="text" name="titulo" id="titulo" required maxlength="255" 
                           placeholder="Digite o tÃ­tulo">
                </div>
                <div class="form-group">
                    <label for="autorDiretor">Autor/Diretor: <span class="obrigatorio">*</span></label>
                    <input type="text" name="autorDiretor" id="autorDiretor" required maxlength="255"
                           placeholder="Digite o nome do autor ou diretor">
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="anoLancamento">Ano de LanÃ§amento: <span class="obrigatorio">*</span></label>
                        <input type="number" name="anoLancamento" id="anoLancamento" required 
                               min="1800" max="2030" placeholder="Ex: 2024">
                    </div>
                    <div class="form-group">
                        <label for="genero">GÃªnero: <span class="obrigatorio">*</span></label>
                        <input type="text" name="genero" id="genero" required maxlength="100"
                               placeholder="Ex: FicÃ§Ã£o, Drama, AÃ§Ã£o">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sinopse">Sinopse:</label>
                    <textarea name="sinopse" id="sinopse" rows="5" maxlength="5000"
                              placeholder="Descreva brevemente o conteÃºdo (opcional)"></textarea>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">
                        âœ… Cadastrar
                    </button>
                    <a href="listar" class="btn btn-secondary">
                        âŒ Cancelar
                    </a>
                </div>
                <p class="form-info">
                    <span class="obrigatorio">*</span> Campos obrigatÃ³rios
                </p>
            </form>
        </div>
    </main>
    <footer>
        <div class="container">
            <p>&copy; 2025 CatÃ¡logo de MÃ­dia - Projeto AcadÃªmico ABP</p>
        </div>
    </footer>
    <script src="js/script.js"></script>
</body>
</html>
