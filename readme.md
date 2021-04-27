<h1 align="center">
    <img alt="Star Wars Ms" src="https://github.com/brunograna/Spring-WebFlux/blob/master/spring-webflux-logo.png" width="300px" />
</h1>

<h3 align="center">
  Projeto: Spring WebFlux
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Database: Mongodb" src="https://img.shields.io/badge/database-mongodb-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">

</p>

## :rocket: Features do Projeto (in progress)

* RestFull Reactive API utilizando:
    * Annotations
    * Handlers & Routers

:mag: Baixe o projeto e teste você mesmo.

## :dart: Objetivos do desenvolvimento

- Utilizar o Java 11 com Spring Boot 2.4.5 com WebFlux e JUnit 5
- Desenvolver uma *api rest* utilizando a Arquitetura Hexagonal (*Ports and Adapters Architecture*)
- Consumir uma api externa _(in progress)_

## :file_folder: Resources

**Base Url**

*Annotations Route*

```
${HOST_URL}/webflux/v1
```

*Handlers & Routers Route*

```
${HOST_URL}/webflux/v2
```

## /products

**Endpoint**

```
${HOST_URL}/webflux/v(version number)/products
```

**Json Schema Definition:**

```
type: object
properties:
  id:
    type: string
    description: identificador do produto
  name:
    type: string
    description: nome do produto
```

---

Desenvolvido por Bruno Garcia :wave: [Conheça mais sobre o meu trabalho no Linkedin](https://www.linkedin.com/in/dev-brunogarcia/)