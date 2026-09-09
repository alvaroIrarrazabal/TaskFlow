# ADR-001 — Technology Stack

## Estado

Accepted

## Contexto

TaskFlow necesita una arquitectura moderna que permita desarrollar
una aplicación Full Stack mantenible y escalable.

También debe servir como proyecto demostrable dentro de un portafolio
profesional de desarrollo de software.

## Decisión

Se utilizarán las siguientes tecnologías:

Frontend:
- Angular
- TypeScript
- Tailwind CSS

Backend:
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security

Base de datos:
- PostgreSQL

Infraestructura:
- Docker

## Razones

Angular permite desarrollar interfaces basadas en componentes y
aplicaciones SPA modernas.

Spring Boot facilita la construcción de APIs REST utilizando Java.

PostgreSQL proporciona una base de datos relacional robusta y permite
representar correctamente las relaciones existentes entre usuarios,
proyectos y tareas.

Docker permitirá estandarizar la ejecución de los servicios.

## Consecuencias

El proyecto tendrá una separación clara entre frontend, backend
y persistencia de datos.