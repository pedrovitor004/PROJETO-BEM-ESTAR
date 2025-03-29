# 💖 Sistema de Cálculo de IMC com GUI - Java 💖

## Descrição
Este é um pequeno sistema desenvolvido em **Java** com **interface gráfica (GUI)** para auxiliar na saúde da mulher, permitindo que usuárias possam calcular seu **IMC (Índice de Massa Corporal)** e receber recomendações de dieta adequadas. O sistema também conta com funcionalidades como **login**, armazenamento de usuários e conexão com um banco de dados MySQL.

## 🌸 Funcionalidades
- **📊 Cálculo de IMC**: O sistema solicita o peso e a altura da usuária e classifica seu IMC em diferentes categorias (abaixo do peso, peso normal, sobrepeso e obesidade).
- **🥗 Recomendação de dieta**: Com base no IMC calculado, são fornecidas sugestões de dieta personalizada.
- **🔐 Sistema de login**: Os usuários podem criar contas e acessar seus dados.
- **🗄️ Banco de dados MySQL**: Armazena informações dos usuários e valida os logins.
- **🖥️ Interface gráfica intuitiva**: Desenvolvida com **Swing** e **WindowBuilder**, facilitando a interação do usuário.
- **🛠️ Gerenciamento do fluxo do código**: Estruturado de forma organizada para facilitar a manutenção e expansão do projeto.
- **📦 Geração de executável (.jar)**: Possibilita a execução do sistema em diferentes plataformas.

## 💻 Tecnologias Utilizadas
![Java](https://cdn-icons-png.flaticon.com/512/226/226777.png) ![Swing](https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/JavaFX_Logo.png/120px-JavaFX_Logo.png) ![MySQL](https://cdn-icons-png.flaticon.com/512/5968/5968313.png)

- **Java** (Programação Orientada a Objetos)
- **Swing** (Para desenvolvimento da interface gráfica)
- **WindowBuilder** (Facilitando a criação da GUI)
- **MySQL** (Banco de dados para armazenar informações dos usuários)
- **JDBC (Java Database Connectivity)** (Para conexão do Java com o MySQL)

## 🚀 Como Executar o Projeto
### Pré-requisitos
Certifique-se de ter instalado:
- **Java JDK** (versão 8 ou superior)
- **MySQL Server**
- **Eclipse IDE** (ou outra IDE compatível)

### Passos para Execução
1. Clone este repositório ou baixe o código-fonte.
2. Importe o projeto em sua IDE de preferência.
3. Configure a conexão com o banco de dados MySQL no código.
4. Crie o banco de dados e a tabela necessária executando o seguinte SQL:

```sql
CREATE DATABASE imc_db;
USE imc_db;
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL
);
```

5. Compile e execute o arquivo principal do projeto.
6. Utilize o sistema para calcular o IMC e receber recomendações!

## 💡 Contribuição
Contribuições são bem-vindas! Caso tenha ideias para melhorias ou correções, fique à vontade para abrir uma **issue** ou enviar um **pull request**.

## 👩‍💻 Autor
Projeto desenvolvido por **[Seu Nome]** durante estudos de **Java** e **programação orientada a objetos**.

## 📜 Licença
Este projeto está licenciado sob os termos da **MIT License**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
