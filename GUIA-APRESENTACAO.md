# 🎤 GUIA DE APRESENTAÇÃO DO PROJETO

## Catálogo de Livros e Filmes - Roteiro para Apresentação

---

## ⏱️ TEMPO ESTIMADO: 10-15 minutos

---

## 📋 ESTRUTURA DA APRESENTAÇÃO

### 1. INTRODUÇÃO (2 minutos)
### 2. DEMONSTRAÇÃO PRÁTICA (5-7 minutos)
### 3. ASPECTOS TÉCNICOS (3-4 minutos)
### 4. CONCLUSÃO (1-2 minutos)

---

## 🎯 1. INTRODUÇÃO (2 minutos)

### O que falar:

**"Bom dia/Boa tarde. Hoje vou apresentar o projeto Catálogo de Livros e Filmes."**

#### 1.1 Contexto do Projeto
- "Este é um sistema web desenvolvido em Java"
- "Permite catalogar e gerenciar uma coleção pessoal de livros e filmes"
- "Foi desenvolvido seguindo a metodologia ABP"

#### 1.2 Objetivo
- "O objetivo é demonstrar domínio em:"
  - Desenvolvimento Java Web
  - Padrão MVC
  - Integração com banco de dados
  - Segurança de aplicações web

#### 1.3 Funcionalidades Principais
- "O sistema implementa:"
  - ✅ CRUD completo (Create, Read, Update, Delete)
  - ✅ Sistema de busca
  - ✅ Interface responsiva
  - ✅ Validações e segurança

---

## 💻 2. DEMONSTRAÇÃO PRÁTICA (5-7 minutos)

### Preparação Antes da Apresentação:
- [ ] Tomcat rodando
- [ ] Banco de dados online
- [ ] Navegador aberto em: http://localhost:8080/catalogo-midia/
- [ ] Ter alguns itens já cadastrados
- [ ] Ter um item específico para demonstrar exclusão

### 2.1 Página Inicial (30 segundos)
**"Vamos começar pela página inicial."**

- Mostrar o layout limpo e organizado
- Destacar as opções de navegação
- Mencionar o design responsivo

### 2.2 Cadastro (2 minutos)
**"Primeiro, vou demonstrar o cadastro de um novo item."**

1. Clicar em "Cadastrar"
2. Mostrar o formulário:
   - "Observe os campos obrigatórios marcados com asterisco"
   - "Temos validação tanto no frontend quanto no backend"

3. Demonstrar validação:
   - Tentar enviar formulário vazio
   - Mostrar mensagem de erro
   - "O JavaScript valida antes mesmo de enviar ao servidor"

4. Preencher corretamente:
   - Tipo: Filme
   - Título: "Duna"
   - Diretor: "Denis Villeneuve"
   - Ano: 2021
   - Gênero: "Ficção Científica"
   - Sinopse: [breve]

5. Submeter: "Observem a mensagem de sucesso após o cadastro"

### 2.3 Listagem (1 minuto)
**"Agora vamos ver a listagem completa."**

- Clicar em "Listar Todos"
- Mostrar a tabela organizada
- Destacar:
  - Badges coloridos para tipo (Livro/Filme)
  - Ícones de ação (editar e excluir)
  - Organização clara das informações

### 2.4 Busca (1,5 minutos)
**"O sistema possui um mecanismo de busca flexível."**

1. Clicar em "Buscar"
2. Demonstrar busca por termo:
   - Digitar "Duna"
   - Submeter
   - Mostrar resultados

3. Demonstrar filtro por tipo:
   - Selecionar "Filme"
   - Submeter
   - Mostrar apenas filmes

### 2.5 Edição (1 minuto)
**"Podemos editar qualquer item facilmente."**

1. Na listagem, clicar em editar (ícone de lápis)
2. Mostrar formulário pré-preenchido
3. Alterar um campo (ex: ano)
4. Salvar
5. Mostrar mensagem de sucesso

### 2.6 Exclusão (30 segundos)
**"E por fim, a exclusão."**

1. Clicar em excluir (ícone de lixeira)
2. Mostrar confirmação JavaScript
3. Confirmar
4. Mostrar mensagem de sucesso e item removido

---

## 🔧 3. ASPECTOS TÉCNICOS (3-4 minutos)

### 3.1 Arquitetura (1 minuto)
**"O projeto segue o padrão MVC."**

Mostrar no código (ou slide):
```
Model (ItemMidia.java)
    ↓
Service (ItemMidiaService.java)
    ↓
DAO (ItemMidiaDAO.java)
    ↓
Controller (Servlets)
    ↓
View (JSP)
```

### 3.2 Tecnologias (1 minuto)
**"As principais tecnologias utilizadas foram:"**

- **Backend:**
  - Java 8+
  - Servlets
  - JDBC
  
- **Frontend:**
  - JSP com JSTL
  - HTML5/CSS3
  - JavaScript
  
- **Banco de Dados:**
  - MySQL
  - Modelagem normalizada

### 3.3 Segurança (1,5 minutos)
**"Segurança foi uma prioridade no desenvolvimento."**

#### SQL Injection Prevention:
Mostrar no código:
```java
// PreparedStatement usado em todas as queries
String sql = "SELECT * FROM item_midia WHERE titulo = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, titulo);
```

**"Isso previne ataques de SQL Injection."**

#### XSS Prevention:
Mostrar no JSP:
```jsp
<!-- JSTL <c:out> escapa HTML automaticamente -->
<c:out value="${item.titulo}" />
```

**"Isso previne ataques de Cross-Site Scripting."**

#### Validações em Camadas:
- "Validação no frontend (JavaScript)"
- "Re-validação no backend (Service)"
- "Constraints no banco de dados"

### 3.4 Banco de Dados (30 segundos)
**"O banco possui uma estrutura simples mas eficiente."**

Mostrar (código ou slide):
```sql
CREATE TABLE item_midia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255) NOT NULL,
    ...
)
```

- "Índices para performance em buscas"
- "Constraints para garantir integridade"

---

## 🎓 4. CONCLUSÃO (1-2 minutos)

### 4.1 Requisitos Atendidos
**"O projeto atendeu todos os requisitos propostos:"**

- ✅ CRUD completo funcional
- ✅ Interface web com JSP
- ✅ Sistema de busca
- ✅ Persistência em banco MySQL
- ✅ Segurança contra SQL Injection
- ✅ Padrão MVC implementado
- ✅ Código documentado

### 4.2 Diferenciais
**"Além dos requisitos, implementei:"**

- 📱 Design responsivo (mobile-friendly)
- 🎨 Interface moderna e intuitiva
- 🔒 Múltiplas camadas de validação
- 📚 Documentação completa
- ✨ Código limpo e comentado

### 4.3 Aprendizados
**"Durante o desenvolvimento, aprendi:"**

- Integração completa Java-Web-BD
- Importância da segurança em aplicações web
- Padrões de projeto na prática
- Desenvolvimento full-stack

### 4.4 Evolução Futura
**"Como melhorias futuras, poderia implementar:"**

- Sistema de autenticação de usuários
- Upload de imagens (capas)
- Sistema de avaliações
- API RESTful

### 4.5 Agradecimentos
**"Agradeço a atenção de todos."**

"Estou à disposição para perguntas."

---

## ❓ POSSÍVEIS PERGUNTAS E RESPOSTAS

### Sobre Tecnologia:

**P: Por que escolheu MySQL?**
R: "MySQL é amplamente usado, gratuito, tem boa documentação e integração fácil com Java via JDBC."

**P: Por que não usou framework como Spring?**
R: "O objetivo era aprender os fundamentos com Servlets puro e entender como funciona por baixo dos frameworks."

**P: Como garantiu a segurança?**
R: "Usando PreparedStatement para prevenir SQL Injection, JSTL <c:out> para prevenir XSS, e validações em múltiplas camadas."

### Sobre Arquitetura:

**P: O que é o padrão MVC?**
R: "É a separação em Model (dados), View (interface) e Controller (lógica). No projeto, ItemMidia é o Model, JSPs são as Views, e Servlets são os Controllers."

**P: Para que serve a camada Service?**
R: "A Service contém a lógica de negócio e validações, separando responsabilidades do Controller e do DAO."

### Sobre Funcionalidades:

**P: Como funciona a busca?**
R: "A busca usa SQL LIKE com PreparedStatement, procurando no título e no autor/diretor, e permite filtrar por tipo de mídia."

**P: É possível cadastrar outros tipos além de livro e filme?**
R: "No momento não, há uma constraint no banco que aceita apenas 'Livro' ou 'Filme', mas seria fácil expandir."

### Sobre Dificuldades:

**P: Qual foi a maior dificuldade?**
R: "Coordenar todas as tecnologias juntas e garantir que a comunicação entre camadas funcionasse corretamente."

**P: Quanto tempo levou para desenvolver?**
R: "Aproximadamente 50-60 horas, incluindo planejamento, desenvolvimento, testes e documentação."

---

## 📊 CHECKLIST PRÉ-APRESENTAÇÃO

### Ambiente:
- [ ] Tomcat iniciado e funcionando
- [ ] MySQL rodando
- [ ] Banco com dados de exemplo
- [ ] Navegador aberto na aplicação
- [ ] Código-fonte aberto (para mostrar)

### Material:
- [ ] Slides preparados (se houver)
- [ ] Documentação impressa (opcional)
- [ ] Backup do projeto (pen drive)

### Pessoal:
- [ ] Revisei o roteiro
- [ ] Testei a demonstração
- [ ] Preparei respostas para perguntas
- [ ] Cronometrei o tempo

---

## 💡 DICAS IMPORTANTES

### Durante a Apresentação:

1. **Fale com clareza e confiança**
   - Você conhece o projeto melhor que ninguém

2. **Mantenha contato visual**
   - Não fique o tempo todo olhando para a tela

3. **Demonstre com calma**
   - Não apresse as demonstrações
   - Dê tempo para a audiência processar

4. **Explique o "porquê"**
   - Não apenas "o que", mas "por que" fez assim

5. **Se algo der errado**
   - Mantenha a calma
   - Explique o que deveria acontecer
   - Mostre no código ou documentação

6. **Gerencie o tempo**
   - Fique atento ao relógio
   - Priorize demonstração prática

### Linguagem Corporal:

- 👍 Postura ereta e confiante
- 👍 Gestos naturais ao explicar
- 👍 Sorriso e entusiasmo
- 👎 Evite ficar de costas
- 👎 Não coloque mãos nos bolsos
- 👎 Não cruze os braços

---

## 🎬 FECHAMENTO

**Última frase sugerida:**

*"Este projeto representa não apenas o cumprimento dos requisitos acadêmicos, mas também meu comprometimento com qualidade, segurança e boas práticas de desenvolvimento. Muito obrigado pela atenção!"*

---

## ✅ RESULTADO ESPERADO

Após seguir este guia, você deve:
- ✅ Demonstrar domínio técnico
- ✅ Mostrar todas as funcionalidades
- ✅ Explicar decisões de arquitetura
- ✅ Responder perguntas com segurança
- ✅ Causar boa impressão

---

**BOA SORTE NA APRESENTAÇÃO! 🎉**

*Você se preparou bem e criou um projeto excelente. Acredite em você!*

---

*Guia criado em: 20/11/2025*
