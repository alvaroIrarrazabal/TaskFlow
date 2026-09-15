# Sprint 01 — Task CRUD Full Stack

## Objetivo

Implementar la gestión completa de tareas mediante una arquitectura Full Stack utilizando Angular, Spring Boot y PostgreSQL.

## Alcance

### Backend

- Crear entidad Task.
- Crear enums TaskStatus y TaskPriority.
- Implementar Repository.
- Implementar Service.
- Implementar DTOs de creación, actualización y respuesta.
- Implementar Mapper.
- Implementar API REST.
- Implementar validaciones.
- Implementar manejo global de excepciones.
- Configurar integración con PostgreSQL.
- Habilitar comunicación con Angular mediante CORS.

### Frontend

- Crear modelos TypeScript.
- Crear TaskService.
- Consumir API mediante HttpClient.
- Implementar listado de tareas.
- Implementar creación de tareas.
- Implementar edición de tareas.
- Implementar eliminación de tareas.
- Implementar Reactive Forms.
- Implementar validaciones.
- Implementar estados de carga y mensajes de error.
- Configurar Angular Router.

## Endpoints implementados

- `GET /api/tasks`
- `GET /api/tasks/{id}`
- `POST /api/tasks`
- `PUT /api/tasks/{id}`
- `DELETE /api/tasks/{id}`

## Flujo de arquitectura

Angular
↓
HttpClient
↓
REST API
↓
Controller
↓
Service
↓
Mapper
↓
Repository
↓
JPA / Hibernate
↓
PostgreSQL

## Definition of Done

El Sprint se considera terminado cuando:

- Angular puede listar tareas desde PostgreSQL.
- Una tarea puede crearse desde Angular.
- Una tarea puede editarse desde Angular.
- Una tarea puede eliminarse desde Angular.
- Las validaciones funcionan correctamente.
- Los errores de la API son manejados.
- Los cambios permanecen después de recargar la aplicación.
- Frontend y backend se comunican correctamente.
- El código está versionado mediante Git.

## Estado

Completado.