# TaskFlow

TaskFlow es una aplicación Full Stack para la gestión de proyectos y tareas.

El sistema permitirá a los usuarios crear proyectos, administrar tareas,
establecer prioridades y controlar el progreso de su trabajo.

## Tecnologías implementadas

### Frontend

- Angular 18
- TypeScript
- Reactive Forms
- Angular HttpClient
- Angular Router

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Bean Validation

### Base de datos

- PostgreSQL

## Arquitectura

Angular
↓
REST API
↓
Spring Boot
↓
Spring Data JPA / Hibernate
↓
PostgreSQL

## Funcionalidades actuales

- Crear tareas.
- Listar tareas.
- Editar tareas.
- Eliminar tareas.
- Asignar prioridad.
- Gestionar estado de las tareas.
- Establecer fecha límite.
- Validar formularios.
- Manejar errores de la API.

## API REST

| Método | Endpoint | Descripción |

| GET | `/api/tasks` | Listar tareas |
| GET | `/api/tasks/{id}` | Obtener tarea por ID |
| POST | `/api/tasks` | Crear tarea |
| PUT | `/api/tasks/{id}` | Actualizar tarea |
| DELETE | `/api/tasks/{id}` | Eliminar tarea |

## Estado del proyecto

Actualmente TaskFlow cuenta con un CRUD Full Stack funcional integrado entre Angular, Spring Boot y PostgreSQL.

El frontend consume la API REST mediante HttpClient y utiliza Reactive Forms para la creación y edición de tareas.

## Próximamente

- Mejoras de interfaz de usuario.
- Filtros y búsqueda de tareas.
- Testing.
- Spring Security.
- Autenticación con JWT.
- Docker.
- Documentación OpenAPI / Swagger.
- Despliegue.

## Documentación

Las decisiones técnicas y el avance de los sprints se encuentran en la carpeta `docs/`.