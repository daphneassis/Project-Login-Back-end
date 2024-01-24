Projeto de Cadastro e Login de Usuário com acesso a funcionalidades distintas para USER e ADMIN. 
Tela de Cadastro, Login, Tela com o texto "Hola Mundo" após autenticação.
-> Se cadastrado como USER-> Botão Trocar Senha-> Pré-visualização- Salvar ou Cancelar-> Salvamento no Bd
-> Se cadastrado como ADMIN-> Botão Lista Usuários -> Deletar usuário e Trocar Senha, como no anterior.

Tecnologias Utilizadas:
React e Java Spring Boot com Spring Security. API funcionando integrada no banco de dados do PgAdmin/Postgres

#Postgres conexão
Foi utilizada conexão com o PgAdmin/Postgres; 

Parâmetros de conexão: 
Criação do bd “userdb”
spring.datasource.username=postgres
spring.datasource.password=suasenha
#JPA create
spring.jpa.hibernate.ddl-auto=create
Para construir o bd ao buildar o projeto

1.Alimentação do banco de dados (caso prefira popular por aqui)

#Scripts do Postgres/console
INSERT INTO public.users (email, password, name, cpf, role_name)
VALUES
('aninha@gmail.com', '$2a$10$H9hKqTi86UXwZZlJDU1x/e7AAwnvEr83tDHh2CL6aOVr03wow6CKu', 'Aninha', '11111', 'ADMIN'),
('angelica@gmail.com', '$2a$10$MYVaz.vQ07n0Xt/nYGeENutJ28N8SvjUNsXQalgW0DgTtwl546Nq.', 'Angelica', '3333', 'ADMIN'),
('marcia@gmail.com', '$2a$10$P.puv5nMbRXGbAk46nKqCegmRp9fcxLvqMNMt2/If5xIKG3VhNXBu', 'Márcia', '5555', 'USER'),
('soraya@gmail.com', '$2a$10$Un3DGshSKkKmbAHq39EcVuBzFUjltpuShgGTho7NVHbxxsxRL8HUe', 'Lucia', '3222', 'USER');

Script sem a criptografia de senhas:
INSERT INTO public.users (email, password, name, cpf, role_name)
VALUES
('aninha@gmail.com', 'senha123', 'Aninha', '11111', 'ADMIN'),
('angelica@gmail.com', ‘senha456', 'Angelica', '3333', 'ADMIN'),
('marcia@gmail.com', ‘senha789', 'Márcia', '5555', 'USER'),
('soraya@gmail.com', ‘senha10', 'Lucia', '3222', 'USER');

No caso de precisar novas senhas criptografadas, basta rodar no LoginApplication:
System.out.println(new BCryptPasswordEncoder().encode("senhaqq"));

Testes pelo Postman:
Salvar pelo menos um usuário pro SpringSecurity validar como Aninha(1ª linha)

#Save
GET http://localhost:8080/api/user/save
Pede autenticação:
Authorization: 
username:Aninha
password: senha123

   No Body:

O sistema aceita um cadastro de usuário por vez:
 {
    "email": "rodrigo@gmail.com",
    "password": "senha1",
    "username": "Ro1",
    "cpf": "122211",
    "role": "USER"
  }
  {
    "email": "joana@gmail.com",
    "password": "senha2",
    "username": "Joana2",
    "cpf": "334422",
    "role": "USER"
  }
  {
    "email": "marcio@gmail.com",
    "password": "senha3",
    "username": "Marcio3",
    "cpf": "556677",
    "role": "ADMIN"
  }

Os dados inseridos devem ser únicos, retorno de sucesso ou erro 

#Login
POST http://localhost:8080/api/user/login
Pede autenticação:

Authorization: 
username:Aninha
password: senha123
   No Body(modelo):
 {
    "email": "rodrigo@gmail.com",
    "password": "senha1"
}

#Trazer a lista de cadastrados como Users
GET http://localhost:8080/api/user/listUsers
Authorization: 
username:Aninha
password: senha123
Exemplo de retorno:

[
    {
        "userId": 1,
        "username": "Aninha",
        "email": "aninha@gmail.com",
        "password": "$2a$10$H9hKqTi86UXwZZlJDU1x/e7AAwnvEr83tDHh2CL6aOVr03wow6CKu",
        "cpf": "4545",
        "role": "USER"
    },
    {
        "userId": 3,
        "username": "Márcia",
        "email": "marcia@gmail.com",
        "password": "$2a$10$P.puv5nMbRXGbAk46nKqCegmRp9fcxLvqMNMt2/If5xIKG3VhNXBu",
        "cpf": "5555",
        "role": "USER"
    },
    {
        "userId": 4,
        "username": "Lucia",
        "email": "soraya@gmail.com",
        "password": "$2a$10$Un3DGshSKkKmbAHq39EcVuBzFUjltpuShgGTho7NVHbxxsxRL8HUe",
        "cpf": "3222",
        "role": "USER"
    },
    {
        "userId": 6,
        "username": "Ro1",
        "email": "rodrigo@gmail.com",
        "password": "$2a$10$6l4wJ8SGVn8Xf1XrhXJR1.NILgfixiCD1gyMOQdyEwirNehMbakia",
        "cpf": "122211",
        "role": "USER"
    },
    {
        "userId": 8,
        "username": "xxx",
        "email": "marcelo@gmail.com",
        "password": "$2a$10$w.YRz4Nndb0q/ughEfq7TeVaDYoVJT.osBOCiS5T4elAH5cl/6oWO",
        "cpf": "56565",
        "role": "USER"
    }
]

#Deleta o usuário 
DELETE http://localhost:8080/api/user/delete/{userId}
No casso, passar o número do id que se pretende deletar após a “/”
Por exemplo, o 3:
http://localhost:8080/api/user/delete/3

Pede autenticação:
Authorization: 
username:Aninha
password: senha123

#Atualiza a senha
PUT http://localhost:8080/api/user/changepass
Pede autenticação:
Authorization: 
username:Aninha
password: senha123
 {
"email": "rodrigo@gmail.com",
"oldPass": "senha1",
"newPass": "senha9090"
}

________

Pelo Browser, rodando o React, não precisa da autenticação de usuário.
Preenchendo os dados do formulário, cai na página de Login.
Enviando os dados, caso usuário e senha estejam como no banco, abre a tela de HelloWorld
-> Se cadastrado como USER-> Botão Trocar Senha-> Pré-visualização- Salvar ou Cancelar
-> Se cadastrado como ADMIN-> Botão para Deletar usuário e Trocar Senha, como no anterior.

_________

Em WebSecurityConfig, comentado o caminho para configuração de autorização em Memória:
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN");

Em properties, caso queiram usuário e senha setados no Spring Security
spring.security.user.name=user
spring.security.user.password=password
