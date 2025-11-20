-- =============================================
-- COMANDOS ÚTEIS - CATÁLOGO DE MÍDIA
-- Guia de referência rápida para MySQL
-- =============================================

-- ==========================================
-- SEÇÃO 1: VERIFICAÇÃO DO AMBIENTE
-- ==========================================

-- Verificar versão do MySQL
SELECT VERSION();

-- Mostrar bancos de dados existentes
SHOW DATABASES;

-- Verificar se o banco catalogo_midia existe
SHOW DATABASES LIKE 'catalogo_midia';

-- Usar o banco de dados
USE catalogo_midia;

-- Mostrar todas as tabelas do banco
SHOW TABLES;

-- Ver estrutura da tabela
DESCRIBE item_midia;
-- ou
SHOW COLUMNS FROM item_midia;

-- ==========================================
-- SEÇÃO 2: CONSULTAS BÁSICAS
-- ==========================================

-- Listar todos os itens
SELECT * FROM item_midia;

-- Listar apenas alguns campos
SELECT id, titulo, autor_diretor, tipo_midia FROM item_midia;

-- Contar total de itens
SELECT COUNT(*) as total FROM item_midia;

-- Contar por tipo
SELECT tipo_midia, COUNT(*) as quantidade 
FROM item_midia 
GROUP BY tipo_midia;

-- Listar apenas livros
SELECT * FROM item_midia WHERE tipo_midia = 'Livro';

-- Listar apenas filmes
SELECT * FROM item_midia WHERE tipo_midia = 'Filme';

-- Ordenar por título
SELECT * FROM item_midia ORDER BY titulo ASC;

-- Ordenar por ano (mais recentes primeiro)
SELECT * FROM item_midia ORDER BY ano_lancamento DESC;

-- ==========================================
-- SEÇÃO 3: BUSCAS E FILTROS
-- ==========================================

-- Buscar por título específico
SELECT * FROM item_midia WHERE titulo = 'Matrix';

-- Buscar por título parcial (LIKE)
SELECT * FROM item_midia WHERE titulo LIKE '%Matrix%';

-- Buscar por autor/diretor
SELECT * FROM item_midia WHERE autor_diretor LIKE '%Nolan%';

-- Buscar por gênero
SELECT * FROM item_midia WHERE genero = 'Ficção Científica';

-- Buscar por ano específico
SELECT * FROM item_midia WHERE ano_lancamento = 2010;

-- Buscar filmes entre anos
SELECT * FROM item_midia 
WHERE tipo_midia = 'Filme' 
AND ano_lancamento BETWEEN 2000 AND 2020;

-- Buscar múltiplos gêneros
SELECT * FROM item_midia 
WHERE genero IN ('Drama', 'Ficção Científica', 'Ação');

-- Buscar título OU autor
SELECT * FROM item_midia 
WHERE titulo LIKE '%Harry%' 
OR autor_diretor LIKE '%Rowling%';

-- ==========================================
-- SEÇÃO 4: INSERÇÃO DE DADOS
-- ==========================================

-- Inserir um livro
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia)
VALUES (
    'O Pequeno Príncipe',
    'Antoine de Saint-Exupéry',
    1943,
    'Fábula',
    'História de um príncipe que viaja por diversos planetas.',
    'Livro'
);

-- Inserir um filme
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia)
VALUES (
    'Interstellar',
    'Christopher Nolan',
    2014,
    'Ficção Científica',
    'Exploradores viajam através de um buraco de minhoca no espaço.',
    'Filme'
);

-- Inserir múltiplos itens de uma vez
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('A Culpa é das Estrelas', 'John Green', 2012, 'Romance', 'História de amor entre adolescentes com câncer.', 'Livro'),
('Vingadores: Ultimato', 'Russo Brothers', 2019, 'Ação', 'Os heróis enfrentam Thanos pela última vez.', 'Filme');

-- ==========================================
-- SEÇÃO 5: ATUALIZAÇÃO DE DADOS
-- ==========================================

-- Atualizar sinopse de um item
UPDATE item_midia 
SET sinopse = 'Nova sinopse aqui...' 
WHERE id = 1;

-- Atualizar múltiplos campos
UPDATE item_midia 
SET titulo = 'Novo Título',
    ano_lancamento = 2024
WHERE id = 5;

-- Atualizar todos os itens de um tipo
UPDATE item_midia 
SET genero = 'Ficção' 
WHERE genero = 'Ficcao';

-- ==========================================
-- SEÇÃO 6: EXCLUSÃO DE DADOS
-- ==========================================

-- Deletar item específico por ID
DELETE FROM item_midia WHERE id = 10;

-- CUIDADO: Deletar todos os livros (use com cautela!)
-- DELETE FROM item_midia WHERE tipo_midia = 'Livro';

-- CUIDADO: Deletar todos os dados (use com cautela!)
-- DELETE FROM item_midia;

-- ==========================================
-- SEÇÃO 7: ESTATÍSTICAS E ANÁLISES
-- ==========================================

-- Quantidade de itens por gênero
SELECT genero, COUNT(*) as quantidade
FROM item_midia
GROUP BY genero
ORDER BY quantidade DESC;

-- Quantidade de itens por década
SELECT 
    FLOOR(ano_lancamento / 10) * 10 as decada,
    COUNT(*) as quantidade
FROM item_midia
GROUP BY decada
ORDER BY decada;

-- Média de ano de lançamento
SELECT AVG(ano_lancamento) as ano_medio FROM item_midia;

-- Item mais antigo e mais recente
SELECT 
    MIN(ano_lancamento) as mais_antigo,
    MAX(ano_lancamento) as mais_recente
FROM item_midia;

-- Top 5 autores/diretores com mais obras
SELECT autor_diretor, COUNT(*) as obras
FROM item_midia
GROUP BY autor_diretor
ORDER BY obras DESC
LIMIT 5;

-- ==========================================
-- SEÇÃO 8: MANUTENÇÃO
-- ==========================================

-- Ver tamanho da tabela
SELECT 
    table_name AS 'Tabela',
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS 'Tamanho (MB)'
FROM information_schema.TABLES
WHERE table_schema = 'catalogo_midia'
AND table_name = 'item_midia';

-- Ver índices da tabela
SHOW INDEX FROM item_midia;

-- Otimizar tabela (reorganizar dados)
OPTIMIZE TABLE item_midia;

-- Analisar tabela (atualizar estatísticas)
ANALYZE TABLE item_midia;

-- ==========================================
-- SEÇÃO 9: BACKUP E RESTORE
-- ==========================================

-- Fazer backup (executar no terminal/cmd, não no MySQL)
-- mysqldump -u root -p catalogo_midia > backup_catalogo.sql

-- Restaurar backup (executar no terminal/cmd)
-- mysql -u root -p catalogo_midia < backup_catalogo.sql

-- ==========================================
-- SEÇÃO 10: TESTES E VALIDAÇÕES
-- ==========================================

-- Testar constraint de tipo_midia
-- Esta query deve falhar:
-- INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, tipo_midia)
-- VALUES ('Teste', 'Teste', 2024, 'Teste', 'DVD');

-- Verificar se há registros duplicados
SELECT titulo, autor_diretor, COUNT(*) as duplicados
FROM item_midia
GROUP BY titulo, autor_diretor
HAVING duplicados > 1;

-- Verificar registros com campos vazios
SELECT * FROM item_midia 
WHERE titulo IS NULL 
   OR autor_diretor IS NULL 
   OR ano_lancamento IS NULL;

-- ==========================================
-- SEÇÃO 11: LIMPEZA
-- ==========================================

-- Limpar todos os dados mas manter estrutura
TRUNCATE TABLE item_midia;

-- Deletar a tabela completamente
-- DROP TABLE item_midia;

-- Deletar o banco completamente (MUITO CUIDADO!)
-- DROP DATABASE catalogo_midia;

-- Recriar do zero (após drop)
-- SOURCE caminho/para/script-banco.sql;

-- ==========================================
-- SEÇÃO 12: USUÁRIOS E PERMISSÕES
-- ==========================================

-- Criar usuário para a aplicação
-- CREATE USER 'catalogo_user'@'localhost' IDENTIFIED BY 'senha123';

-- Dar permissões ao usuário
-- GRANT SELECT, INSERT, UPDATE, DELETE ON catalogo_midia.* TO 'catalogo_user'@'localhost';

-- Aplicar mudanças
-- FLUSH PRIVILEGES;

-- Ver usuários existentes
-- SELECT User, Host FROM mysql.user;

-- ==========================================
-- SEÇÃO 13: QUERIES AVANÇADAS
-- ==========================================

-- Busca full-text (requer configuração)
-- Criar índice full-text:
-- ALTER TABLE item_midia ADD FULLTEXT(titulo, sinopse);

-- Usar busca full-text:
-- SELECT * FROM item_midia 
-- WHERE MATCH(titulo, sinopse) AGAINST('busca');

-- Concatenar campos
SELECT CONCAT(titulo, ' - ', autor_diretor) as item_completo
FROM item_midia;

-- Busca case-insensitive explícita
SELECT * FROM item_midia 
WHERE LOWER(titulo) LIKE LOWER('%matrix%');

-- Paginação (primeiros 10 resultados)
SELECT * FROM item_midia 
ORDER BY titulo 
LIMIT 10 OFFSET 0;

-- Próxima página (resultados 11-20)
SELECT * FROM item_midia 
ORDER BY titulo 
LIMIT 10 OFFSET 10;

-- ==========================================
-- SEÇÃO 14: TROUBLESHOOTING
-- ==========================================

-- Ver erros recentes
SHOW WARNINGS;
SHOW ERRORS;

-- Ver processos em execução
SHOW PROCESSLIST;

-- Verificar variáveis de configuração
SHOW VARIABLES LIKE 'character_set%';
SHOW VARIABLES LIKE 'collation%';

-- Ver charset da tabela
SELECT 
    TABLE_NAME,
    TABLE_COLLATION
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'catalogo_midia';

-- ==========================================
-- NOTAS FINAIS
-- ==========================================

-- Sempre use transações para operações críticas:
-- START TRANSACTION;
-- UPDATE item_midia SET ... WHERE ...;
-- Se tudo OK: COMMIT;
-- Se erro: ROLLBACK;

-- Use EXPLAIN para analisar performance de queries:
-- EXPLAIN SELECT * FROM item_midia WHERE titulo LIKE '%teste%';

-- ==========================================
-- FIM DO ARQUIVO
-- ==========================================
-- 
-- Dica: Mantenha este arquivo à mão para consulta rápida!
-- 
-- Para mais informações:
-- https://dev.mysql.com/doc/
