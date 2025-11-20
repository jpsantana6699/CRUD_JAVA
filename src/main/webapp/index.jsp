<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo de Livros e Filmes</title>
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
        <section class="hero">
            <h2>Bem-vindo ao Catálogo de Mídia</h2>
            <p>Organize sua coleção de livros e filmes em um só lugar!</p>
            
            <div class="cards">
                <div class="card">
                    <h3>📖 Cadastrar</h3>
                    <p>Adicione novos livros e filmes ao seu catálogo</p>
                    <a href="cadastrar" class="btn btn-primary">Cadastrar Novo Item</a>
                </div>
                
                <div class="card">
                    <h3>📋 Listar</h3>
                    <p>Veja todos os itens cadastrados no catálogo</p>
                    <a href="listar" class="btn btn-secondary">Ver Todos os Itens</a>
                </div>
                
                <div class="card">
                    <h3>🔍 Buscar</h3>
                    <p>Pesquise por título, autor ou diretor</p>
                    <a href="buscar.jsp" class="btn btn-info">Buscar Itens</a>
                </div>
            </div>
        </section>
        
        <section class="info">
            <h3>Funcionalidades do Sistema</h3>
            <ul>
                <li>✅ Cadastro completo de livros e filmes</li>
                <li>✅ Listagem organizada de todos os itens</li>
                <li>✅ Busca por título ou autor/diretor</li>
                <li>✅ Edição de informações dos itens</li>
                <li>✅ Exclusão de itens</li>
                <li>✅ Interface responsiva e fácil de usar</li>
            </ul>
        </section>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 Catálogo de Mídia - Projeto Acadêmico ABP</p>
            <p>Desenvolvido por João Pedro Santana</p>
        </div>
    </footer>
    
    <script src="js/script.js"></script>
</body>
</html>
