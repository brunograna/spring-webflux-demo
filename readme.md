<h1 align="center">
    <img alt="Spring WebFlux" src="https://github.com/brunograna/Spring-WebFlux/blob/master/spring-webflux-logo.png" width="300px" />
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

## :rocket: Project Features (in progress)

* RestFull Reactive API utilizando:
    * Annotations
    * Handlers & Routers

:mag: Download the project and test by yourself.

## :dart: Development objective

- Java 11 with Spring Boot 2.4.5 and WebFlux and JUnit 5
- Develop a *Restfull API* utilizing Ports and Adapters Architecture
- Consume an external API _(in progress)_

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
    description: product identifier
  name:
    type: string
    description: product name
```

---

Developed by Bruno Garcia :wave: [Know more of me in my LinkedIn](https://www.linkedin.com/in/dev-brunogarcia/)