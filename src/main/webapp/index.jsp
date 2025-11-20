<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CatÃ¡logo de Livros e Filmes</title>
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
                <a href="buscar.jsp">Buscar</a>
            </nav>
        </div>
    </header>
    <main class="container">
        <section class="hero">
            <h2>Bem-vindo ao CatÃ¡logo de MÃ­dia</h2>
            <p>Organize sua coleÃ§Ã£o de livros e filmes em um sÃ³ lugar!</p>
            <div class="cards">
                <div class="card">
                    <h3>ðŸ“– Cadastrar</h3>
                    <p>Adicione novos livros e filmes ao seu catÃ¡logo</p>
                    <a href="cadastrar" class="btn btn-primary">Cadastrar Novo Item</a>
                </div>
                <div class="card">
                    <h3>ðŸ“‹ Listar</h3>
                    <p>Veja todos os itens cadastrados no catÃ¡logo</p>
                    <a href="listar" class="btn btn-secondary">Ver Todos os Itens</a>
                </div>
                <div class="card">
                    <h3>ðŸ” Buscar</h3>
                    <p>Pesquise por tÃ­tulo, autor ou diretor</p>
                    <a href="buscar.jsp" class="btn btn-info">Buscar Itens</a>
                </div>
            </div>
        </section>
        <section class="info">
            <h3>Funcionalidades do Sistema</h3>
            <ul>
                <li>âœ… Cadastro completo de livros e filmes</li>
                <li>âœ… Listagem organizada de todos os itens</li>
                <li>âœ… Busca por tÃ­tulo ou autor/diretor</li>
                <li>âœ… EdiÃ§Ã£o de informaÃ§Ãµes dos itens</li>
                <li>âœ… ExclusÃ£o de itens</li>
                <li>âœ… Interface responsiva e fÃ¡cil de usar</li>
            </ul>
        </section>
    </main>
    <footer>
        <div class="container">
            <p>&copy; 2025 CatÃ¡logo de MÃ­dia - Projeto AcadÃªmico ABP</p>
            <p>Desenvolvido por JoÃ£o Pedro Santana</p>
        </div>
    </footer>
    <script src="js/script.js"></script>
</body>
</html>
