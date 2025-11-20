-- =========================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS
-- Catálogo de Livros e Filmes
-- Autor: João Pedro Santana
-- Data: 20/11/2025
-- =========================================

-- Cria o banco de dados (se não existir)
CREATE DATABASE IF NOT EXISTS catalogo_midia
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Seleciona o banco de dados
USE catalogo_midia;

-- Remove a tabela se já existir (cuidado: irá apagar todos os dados!)
DROP TABLE IF EXISTS item_midia;

-- Cria a tabela item_midia
CREATE TABLE item_midia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(100) NOT NULL,
    sinopse TEXT,
    tipo_midia VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Índices para melhorar performance nas buscas
    INDEX idx_titulo (titulo),
    INDEX idx_autor_diretor (autor_diretor),
    INDEX idx_tipo_midia (tipo_midia),
    
    -- Constraint para garantir que tipo_midia seja apenas 'Livro' ou 'Filme'
    CHECK (tipo_midia IN ('Livro', 'Filme'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================
-- DADOS DE EXEMPLO (OPCIONAL)
-- =========================================

-- Inserir livros de exemplo
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('Dom Casmurro', 'Machado de Assis', 1899, 'Romance', 'Romance clássico da literatura brasileira que narra a história de Bentinho e Capitu.', 'Livro'),
('1984', 'George Orwell', 1949, 'Ficção Científica', 'Distopia sobre um regime totalitário que controla todos os aspectos da vida.', 'Livro'),
('O Senhor dos Anéis', 'J.R.R. Tolkien', 1954, 'Fantasia', 'Épica aventura na Terra Média para destruir o Um Anel.', 'Livro'),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 1997, 'Fantasia', 'Um jovem bruxo descobre seu destino no mundo mágico.', 'Livro'),
('Orgulho e Preconceito', 'Jane Austen', 1813, 'Romance', 'História de amor entre Elizabeth Bennet e Mr. Darcy na Inglaterra do século XIX.', 'Livro');

-- Inserir filmes de exemplo
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('O Poderoso Chefão', 'Francis Ford Coppola', 1972, 'Drama', 'A saga da família Corleone e o império do crime na América.', 'Filme'),
('Pulp Fiction', 'Quentin Tarantino', 1994, 'Crime', 'Histórias entrelaçadas do submundo do crime em Los Angeles.', 'Filme'),
('Matrix', 'Lana e Lilly Wachowski', 1999, 'Ficção Científica', 'Um hacker descobre a verdadeira natureza da realidade.', 'Filme'),
('Inception', 'Christopher Nolan', 2010, 'Ficção Científica', 'Ladrões que invadem sonhos para roubar segredos corporativos.', 'Filme'),
('Parasita', 'Bong Joon-ho', 2019, 'Drama', 'Uma família pobre se infiltra na vida de uma família rica.', 'Filme');

-- =========================================
-- CONSULTAS ÚTEIS
-- =========================================

-- Listar todos os itens
SELECT * FROM item_midia ORDER BY titulo;

-- Contar total de livros e filmes
SELECT tipo_midia, COUNT(*) as total 
FROM item_midia 
GROUP BY tipo_midia;

-- Buscar por título ou autor/diretor
SELECT * FROM item_midia 
WHERE titulo LIKE '%Matrix%' 
   OR autor_diretor LIKE '%Nolan%';

-- Listar apenas livros
SELECT * FROM item_midia 
WHERE tipo_midia = 'Livro' 
ORDER BY ano_lancamento DESC;

-- Listar apenas filmes
SELECT * FROM item_midia 
WHERE tipo_midia = 'Filme' 
ORDER BY ano_lancamento DESC;

-- Buscar por gênero
SELECT * FROM item_midia 
WHERE genero = 'Ficção Científica' 
ORDER BY titulo;

-- Itens mais recentes
SELECT * FROM item_midia 
ORDER BY ano_lancamento DESC 
LIMIT 10;

-- =========================================
-- COMANDOS DE MANUTENÇÃO
-- =========================================

-- Ver estrutura da tabela
DESCRIBE item_midia;

-- Ver todos os dados
SELECT * FROM item_midia;

-- Limpar todos os dados (CUIDADO!)
-- TRUNCATE TABLE item_midia;

-- Deletar o banco (CUIDADO!)
-- DROP DATABASE catalogo_midia;
