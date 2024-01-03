
# 🌐 Store API

### API RESTful com ênfase nos conhecimento obtidos em Java e na utilização de seu framework Spring Boot  .
[![Contributors][contributors-shield]][contributors-url] [![Forks][forks-shield]][forks-url] [![Stargazers][stars-shield]][stars-url] [![Issues][issues-shield]][issues-url] [![MIT License][license-shield]][license-url]



### 📑 Tópicos:
* [Instalação](#-instalação)
    * [Pré Requisitos](#-pré-requisitos-)
* [Endpoints](#-endpoints)
    * [Autenticação](#-autenticação)
    * [Produto](#-produto)
    * [Categoria](#-categoria)
* [Como usar?](#como-usar-)
* [Contatos](#-contatos)

## ⬇️ Instalação

### 📝  Pré requisitos: 

* #### 🎲 Banco de dados

Será necessário a realizar configuração do usuário e senha do cliente PostgreSQL.

````yaml
spring:
  datasource:
    url: jdbc:postgresql://127.0.0.1:5432/store-api
    username: # Database user
    password: # Database password user
    driver-class-name: org.postgresql.Driver
````

---

## 🚀 Endpoints

### 🔑 Autenticação

> ####  Registro
> 
> 
> 📍 **Endpoint:** http://localhost:8080/api/v1/auth/register
> 
> 📨 **Método:** ```POST```
> 
> 🔒 **Permissões permitidas:** ``ADMIN``
---
> ####  Login
> 📍 **Endpoint:** http://localhost:8080/api/v1/auth/login
>
> 📨 **Método:** ```POST```
>
> 🔒 **Permissões permitidas:** ``Todas``

---

## 📦 Produto

> #### Listar todos os produtos
> 📍 **Endpoint:** http://localhost:8080/api/v1/products
>
> 📨 **Método:** ```GET```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER`` ``CLIENT``
---
> #### Listar produto pelo id
> 📍 **Endpoint:** http://localhost:8080/api/v1/product/{id}
>
> 📨 **Método:** ```GET```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER`` ``CLIENT``
---
> #### Cadastrar produto
> 📍 **Endpoint:** http://localhost:8080/api/v1/product
>
> 📨 **Método:** ```POST```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER``
---
> #### Atualizar produto (PUT)
> 📍 **Endpoint:** http://localhost:8080/api/v1/product/{id}
>
> 📨 **Método:** ```PUT```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER``
---
> #### Atualizar produto (PATCH)
> 📍 **Endpoint:** http://localhost:8080/api/v1/product/{id}
>
> 📨 **Método:** ```PATCH```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER``

---

## 🔖 Categoria

> #### Listar todas as categorias
> 📍 **Endpoint:** http://localhost:8080/api/v1/categories
>
> 📨 **Método:** ```GET```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` ``SELLER`` ``CLIENT``
---
> #### Listar categoria pelo id
> 📍 **Endpoint:** http://localhost:8080/api/v1/category/{id}
>
> 📨 **Método:** ```GET```
>
> 🔒 **Permissões permitidas:** ``ADMIN`` 
---
> #### Cadastrar categoria
> 📍 **Endpoint:** http://localhost:8080/api/v1/category
>
> 📨 **Método:** ```POST```
>
> 🔒 **Permissões permitidas:** ``ADMIN``
---
> #### Atualizar categoria (PUT)
> 📍 **Endpoint:** http://localhost:8080/api/v1/category/{id}
>
> 📨 **Método:** ```PUT```
>
> 🔒 **Permissões permitidas:** ``ADMIN``
---
> #### Atualizar categoria (PATCH)
> 📍 **Endpoint:** http://localhost:8080/api/v1/category/{id}
>
> 📨 **Método:** ```PATCH```
>
> 🔒 **Permissões permitidas:** ``ADMIN``

---

## ❓Como usar ?

### 🪪 Autenticação no sistema

Para autenticar-se no sistema e acessar os recursos permitidos com base no nível de acesso, é necessário fornecer suas credenciais no mecanismo de autenticação.
> 📍 **Endpoint:** http://localhost:8080/api/v1/auth/login
>
> 📨 **Método:** ```POST```

Exemplo de uma requisição utilizando o Postman:

![Example Postman Login][example-postman-login]

Caso as credenciais sejam correspodentes com as cadastradas no sistema:

![Example Postman Response Login][example-postman-login-response]

Após realizado a autenticação vá até a sessão **Authorization** e defina o tipo de autenticatição para ``Bearer Token`` e informe o valor do campo ``token``:

![Example Config Bearer Token][example-config-bearer-token]

⚠️ **Será necessário repetir esse procedimento para os endpoints que requer autorização do usuário.**

---

### 🆕 Registro

Para efetuar o registro de um novo usuário, é necessário especificar tanto as credenciais do usuário quanto sua respectiva permissão:

![Example Postman Register][example-postman-register]


Na eventualidade de os dados fornecidos serem válidos e não haver registro do usuário no sistema:

![Example Postman Register Response][example-postman-register-response]

---

## 🧪 Tecnologias


[![Java][java-shield]][java-url]

> Adoção da linguagem de programação Java devido à sua aderência ao paradigma orientado a objetos (OOP) e à sua natureza multiplataforma.

[![Spring][spring-shield]][spring-url]


> Adoção do framework Spring Boot em virtude de sua eficácia no desenvolvimento de aplicações web e microsserviços, destacando-se pela habilidade de autoconfiguração de componentes.

[![Docker][docker-shield]][docker-url]


> Adoção do Docker em razão de sua infraestrutura fundamentada em containers, proporcionando virtualização e administração de serviços de forma mais eficiente, ágil e desacoplada.

[![Redis][redis-shield]][redis-url]

> Adoção do Redis devido à sua natureza como um armazenamento de estrutura de dados em memória. Isso possibilita a implementação de armazenamento em cache, alta disponibilidade e particionamento automático em diversos clusters.

[![Postgres][postgres-shield]][postgres-url]

> Adoção do banco de dados PostgreSQL em virtude de seu suporte a diversas otimizações de desempenho, flexibilidade, capacidade de personalização e aprimorada segurança.

---

## 📌 Contatos

[![LinkedIn][linkedin-shield]][linkedin-url] [![Gmail][gmail-shield]][gmail-url] [![Meu Portfolio][me-portfolio-shield]][me-portfolio-url]


[java-shield]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]:https://docs.oracle.com/en/java/javase/21/
[spring-shield]:  https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[spring-url]:https://docs.spring.io/spring-framework/reference/index.html
[docker-shield]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[docker-url]:https://docs.docker.com/?_gl=1*132rgci*_ga*MTQ3NTI5MTIwNC4xNzAxODczNjkz*_ga_XJWPQMJYHQ*MTcwMTg3MzY5Mi4xLjEuMTcwMTg3MzcxMS40MS4wLjA.
[redis-shield]: https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white
[redis-url]:https://redis.io/docs/about/
[postgres-shield]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[postgres-url]: https://www.postgresql.org/docs/
[contributors-shield]: https://img.shields.io/github/contributors/devrafaelsoares/store-api-restful.svg?style=for-the-badge
[contributors-url]: https://github.com/devrafaelsoares/store-api-restful/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/devrafaelsoares/store-api-restful.svg?style=for-the-badge
[forks-url]: https://github.com/devrafaelsoares/store-api-restful/network/members
[stars-shield]: https://img.shields.io/github/stars/store-api-restful/react-countdown.svg?style=for-the-badge
[stars-url]: https://github.com/devrafaelsoares/store-api-restful/stargazers
[issues-shield]: https://img.shields.io/github/issues/devrafaelsoares/store-api-restful.svg?style=for-the-badge
[issues-url]: https://github.com/devrafaelsoares/store-api-restful/issues
[license-shield]: https://img.shields.io/github/license/devrafaelsoares/store-api-restful.svg?style=for-the-badge
[license-url]: https://github.com/devrafaelsoares/store-api-restful/blob/master/LICENSE
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
[linkedin-url]: https://www.linkedin.com/in/rafael-henrique-soares-de-freitas-2a667a23a/
[gmail-shield]: https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white
[gmail-url]: mailto:rafael.soares.developer@gmail.com
[me-portfolio-shield]: https://img.shields.io/badge/website-000000?style=for-the-badge&logo=About.me&logoColor=white
[me-portfolio-url]: https://devrafaelsoares.github.io/portfolio/

[example-postman-login]: docs/images/example_postman_login.png
[example-postman-login-response]: docs/images/example_postman_login_response.png
[example-config-bearer-token]: docs/images/example_config_bearer_token.png
[example-postman-register]: docs/images/example_postman_register.png
[example-postman-register-response]: docs/images/example_postman_register_response.png
