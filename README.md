# 📚🎬 Catálogo de Livros e Filmes

**Projeto Acadêmico - ABP (Aprendizagem Baseada em Projetos)**

Sistema web desenvolvido em Java para catalogar e gerenciar livros e filmes, implementando operações CRUD completas com interface web intuitiva.

---

## 👨‍🎓 Informações do Projeto

- **Autor:** João Pedro Santana
- **Curso:** Ciência da Computação
- **Data:** Novembro de 2025
- **Disciplina:** Programação Web / Java Web

---

## 🎯 Funcionalidades

### ✅ CRUD Completo
- ✔️ **Create** - Cadastrar novos livros e filmes
- ✔️ **Read** - Listar todos os itens cadastrados
- ✔️ **Update** - Editar informações dos itens
- ✔️ **Delete** - Excluir itens do catálogo

### 🔍 Busca Avançada
- Buscar por título
- Buscar por autor/diretor
- Filtrar por tipo de mídia (Livro ou Filme)

### 🛡️ Segurança
- Proteção contra **SQL Injection** usando PreparedStatement
- Validação de dados no frontend e backend
- Escape de HTML nas páginas JSP com `<c:out>`

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 8+** - Linguagem de programação
- **Servlets** - Controladores da aplicação
- **JDBC** - Conexão com banco de dados
- **JSP** - JavaServer Pages para views
- **JSTL** - JSP Standard Tag Library

### Frontend
- **HTML5** - Estrutura das páginas
- **CSS3** - Estilização responsiva
- **JavaScript** - Validação de formulários

### Banco de Dados
- **MySQL 8.0** - Sistema de gerenciamento de banco de dados

### Servidor
- **Apache Tomcat 9.0** - Servidor de aplicações Java

---

## 📁 Estrutura do Projeto

```
catalogo-midia/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── catalogo/
│       │           ├── model/
│       │           │   └── ItemMidia.java         # Classe modelo
│       │           ├── dao/
│       │           │   ├── ItemMidiaDAO.java      # Operações BD
│       │           │   └── FabricaDeConexoes.java # Conexão BD
│       │           ├── servlet/
│       │           │   ├── CadastrarItemServlet.java
│       │           │   ├── ListarItensServlet.java
│       │           │   ├── EditarItemServlet.java
│       │           │   ├── ExcluirItemServlet.java
│       │           │   └── BuscarItemServlet.java
│       │           └── service/
│       │               └── ItemMidiaService.java  # Lógica de negócio
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml                        # Configuração
│           ├── css/
│           │   └── estilo.css                     # Estilos
│           ├── js/
│           │   └── script.js                      # JavaScript
│           ├── cadastro.jsp                       # Formulário cadastro
│           ├── listar.jsp                         # Listagem
│           ├── editar.jsp                         # Formulário edição
│           ├── buscar.jsp                         # Busca
│           └── index.jsp                          # Página inicial
├── lib/                                           # Bibliotecas JAR
├── script-banco.sql                               # Script SQL
└── README.md                                      # Este arquivo
```

---

## 💾 Banco de Dados

### Tabela: `item_midia`

| Campo          | Tipo         | Descrição                    |
|----------------|--------------|------------------------------|
| id             | INT          | Chave primária (auto-inc)    |
| titulo         | VARCHAR(255) | Título do livro/filme        |
| autor_diretor  | VARCHAR(255) | Nome do autor ou diretor     |
| ano_lancamento | INT          | Ano de lançamento            |
| genero         | VARCHAR(100) | Gênero da obra               |
| sinopse        | TEXT         | Descrição resumida (opcional)|
| tipo_midia     | VARCHAR(50)  | "Livro" ou "Filme"           |
| data_cadastro  | TIMESTAMP    | Data do cadastro             |

---

## 🚀 Como Instalar e Executar

### 📋 Pré-requisitos

1. **Java JDK 8 ou superior**
   - Baixar em: https://www.oracle.com/java/technologies/downloads/
   
2. **Apache Tomcat 9.0 ou superior**
   - Baixar em: https://tomcat.apache.org/download-90.cgi
   
3. **MySQL 8.0 ou superior**
   - Baixar em: https://dev.mysql.com/downloads/mysql/
   
4. **IDE (Recomendado: IntelliJ IDEA ou Eclipse)**

### 📦 Bibliotecas Necessárias (JAR)

Baixe e coloque na pasta `lib/`:

1. **MySQL Connector/J**
   - Link: https://dev.mysql.com/downloads/connector/j/
   - Arquivo: `mysql-connector-java-8.0.33.jar`

2. **JSTL (JSP Standard Tag Library)**
   - Link: https://tomcat.apache.org/download-taglibs.cgi
   - Arquivos:
     - `jstl-1.2.jar`
     - `standard-1.1.2.jar`

### 🔧 Configuração do Banco de Dados

1. **Iniciar o MySQL**

2. **Executar o script SQL:**
   ```bash
   mysql -u root -p < script-banco.sql
   ```

3. **Configurar credenciais** (se necessário):
   
   Editar o arquivo `FabricaDeConexoes.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/catalogo_midia";
   private static final String USUARIO = "root";
   private static final String SENHA = "sua_senha";
   ```

### 🏃 Executando o Projeto

#### Opção 1: Via IDE (IntelliJ/Eclipse)

1. Importar o projeto
2. Configurar o Tomcat no projeto
3. Adicionar as bibliotecas JAR ao classpath
4. Run/Debug o projeto
5. Acessar: `http://localhost:8080/catalogo-midia/`

#### Opção 2: Deploy Manual no Tomcat

1. Compilar o projeto gerando o arquivo WAR
2. Copiar o WAR para `tomcat/webapps/`
3. Iniciar o Tomcat:
   ```bash
   # Windows
   cd caminho/para/tomcat/bin
   startup.bat
   
   # Linux/Mac
   cd caminho/para/tomcat/bin
   ./startup.sh
   ```
4. Acessar: `http://localhost:8080/catalogo-midia/`

---

## 📱 Como Usar

### 1️⃣ Cadastrar Item
- Acesse "Cadastrar" no menu
- Preencha o formulário com:
  - Tipo de mídia (Livro ou Filme)
  - Título
  - Autor/Diretor
  - Ano de lançamento
  - Gênero
  - Sinopse (opcional)
- Clique em "Cadastrar"

### 2️⃣ Listar Itens
- Acesse "Listar Todos" no menu
- Visualize todos os itens em uma tabela
- Use os ícones para editar ✏️ ou excluir 🗑️

### 3️⃣ Buscar Itens
- Acesse "Buscar" no menu
- Digite um termo de busca (título ou autor/diretor)
- Ou filtre por tipo de mídia
- Clique em "Buscar"

### 4️⃣ Editar Item
- Na listagem, clique no ícone ✏️
- Modifique os campos desejados
- Clique em "Salvar Alterações"

### 5️⃣ Excluir Item
- Na listagem, clique no ícone 🗑️
- Confirme a exclusão

---

## 🔒 Segurança Implementada

### SQL Injection Prevention
```java
// ✅ CORRETO - Usando PreparedStatement
String sql = "SELECT * FROM item_midia WHERE titulo = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, titulo);

// ❌ ERRADO - Vulnerável a SQL Injection
String sql = "SELECT * FROM item_midia WHERE titulo = '" + titulo + "'";
```

### Validações
- **Frontend:** JavaScript valida campos antes do envio
- **Backend:** Service valida regras de negócio
- **Banco:** Constraints e tipos de dados adequados

### Escape de HTML
```jsp
<!-- ✅ CORRETO - Previne XSS -->
<c:out value="${item.titulo}" />

<!-- ❌ ERRADO - Vulnerável a XSS -->
${item.titulo}
```

---

## 🎨 Interface

### Design Responsivo
- ✅ Funciona em desktops, tablets e smartphones
- ✅ Layout adaptativo com CSS Grid e Flexbox
- ✅ Cores e animações suaves

### Experiência do Usuário
- ✅ Navegação intuitiva
- ✅ Feedback visual para ações
- ✅ Mensagens de sucesso e erro claras
- ✅ Ícones e emojis para melhor compreensão

---

## 🧪 Testando o Sistema

### Testes Manuais

1. **Cadastro:**
   - Testar com dados válidos ✅
   - Testar com campos vazios ❌
   - Testar com ano inválido ❌

2. **Listagem:**
   - Verificar ordenação
   - Testar com banco vazio

3. **Busca:**
   - Buscar por títulos existentes
   - Buscar por termos inexistentes
   - Filtrar por tipo

4. **Edição:**
   - Editar e salvar
   - Cancelar edição

5. **Exclusão:**
   - Confirmar exclusão
   - Cancelar exclusão

---

## 📊 Diferenciais do Projeto

✨ **Código Limpo e Organizado**
- Seguindo padrões MVC
- Comentários Javadoc
- Nomenclatura clara

✨ **Segurança em Primeiro Lugar**
- PreparedStatement em todas as queries
- Validações em múltiplas camadas

✨ **Interface Profissional**
- Design moderno e responsivo
- Animações CSS suaves
- UX otimizada

✨ **Boas Práticas**
- Separação de responsabilidades
- Tratamento de exceções
- Código reutilizável

---

## 🐛 Solução de Problemas

### Erro: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solução:** Verifique se o `mysql-connector-java.jar` está no classpath

### Erro: "Could not create connection to database"
**Solução:** 
- Verifique se o MySQL está rodando
- Confirme usuário/senha em `FabricaDeConexoes.java`
- Verifique se o banco `catalogo_midia` existe

### Erro 404: Página não encontrada
**Solução:**
- Verifique se o Tomcat está rodando
- Confirme o contexto da aplicação na URL

### Erro: "JSTL not found"
**Solução:** Adicione `jstl-1.2.jar` e `standard.jar` ao classpath

---

## 📚 Referências

- [Oracle Java Documentation](https://docs.oracle.com/javase/8/docs/)
- [Java Servlets Tutorial](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [JSP Tutorial](https://docs.oracle.com/javaee/7/tutorial/jsf-intro.htm)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)

---

## 📝 Licença

Este projeto é de uso acadêmico e educacional.

---

## 👨‍💻 Autor

**João Pedro Santana**
- 📧 Email: joao.santana@exemplo.com
- 🎓 Projeto Integrador - ABP

---

## 🙏 Agradecimentos

Agradeço aos professores e colegas que contribuíram para o desenvolvimento deste projeto acadêmico.

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela!**

---

*Desenvolvido com ☕ e 💻 por João Pedro Santana - 2025*
