# TaskFlow

TaskFlow es una aplicación web Full Stack para la gestión de tareas, desarrollada con **Angular 18, Spring Boot, Java 21 y PostgreSQL**.

Permite crear, consultar, editar y eliminar tareas, además de gestionar su estado, prioridad y fecha límite. También incorpora búsqueda, filtros y ordenamiento para facilitar la administración de las tareas.

## Demo

 **Aplicación:** https://taskflow-web-w9n3.onrender.com/tasks

 **API REST:** https://taskflow-api-bgbu.onrender.com/api/tasks

> La aplicación está desplegada en Render. El backend se ejecuta mediante Docker y utiliza PostgreSQL como base de datos.

## Tecnologías

### Frontend

- Angular 18
- TypeScript
- Tailwind CSS
- Reactive Forms
- Angular HttpClient
- Angular Router

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Bean Validation
- Maven

### Base de datos

- PostgreSQL

### Testing

- JUnit
- Mockito

### DevOps y despliegue

- Docker
- Render
- Git
- GitHub

## Arquitectura

TaskFlow utiliza una arquitectura cliente-servidor basada en una API REST.

```text
Angular 18
    │
    │ HTTP / JSON
    ▼
REST API
    │
    ▼
Spring Boot
    │
    ▼
Service Layer
    │
    ▼
Spring Data JPA / Hibernate
    │
    ▼
PostgreSQL
```

El frontend y el backend están desacoplados y se comunican mediante HTTP utilizando una API REST.

### Arquitectura de despliegue

```text
Frontend
Angular 18
    │
    ▼
Render Static Site


Backend
Spring Boot
    │
    ▼
Docker
    │
    ▼
Render Web Service
    │
    ▼
PostgreSQL
```

## Funcionalidades

- Crear tareas.
- Listar tareas.
- Editar tareas.
- Eliminar tareas.
- Gestionar estados: `TODO`, `IN_PROGRESS` y `DONE`.
- Asignar prioridades: `LOW`, `MEDIUM` y `HIGH`.
- Establecer fechas límite.
- Buscar tareas por título.
- Filtrar tareas por estado.
- Filtrar tareas por prioridad.
- Ordenar tareas por fecha límite.
- Ordenar tareas por prioridad.
- Validar formularios con Reactive Forms.
- Mostrar mensajes de éxito y error.
- Manejar errores provenientes de la API REST.
- Persistir la información en PostgreSQL.

## API REST

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/tasks` | Listar, buscar, filtrar y ordenar tareas |
| GET | `/api/tasks/{id}` | Obtener una tarea por ID |
| POST | `/api/tasks` | Crear una nueva tarea |
| PUT | `/api/tasks/{id}` | Actualizar una tarea existente |
| DELETE | `/api/tasks/{id}` | Eliminar una tarea |

### Parámetros de consulta

El endpoint `GET /api/tasks` permite utilizar parámetros opcionales para buscar, filtrar y ordenar las tareas.

Ejemplo:

```text
/api/tasks?status=TODO&priority=HIGH&search=Spring&sortBy=dueDate&direction=asc
```

Parámetros disponibles:

- `status`: filtra las tareas por estado.
- `priority`: filtra las tareas por prioridad.
- `search`: busca tareas por título.
- `sortBy`: define el campo utilizado para ordenar.
- `direction`: define el orden ascendente o descendente.

## Estructura del proyecto

```text
taskflow/
│
├── backend/
│   └── Spring Boot REST API
│
├── frontend/
│   └── Angular 18
│
├── docs/
│   └── Documentación técnica
│
└── README.md
```

## Testing

El backend cuenta con pruebas automatizadas utilizando **JUnit y Mockito** para validar parte de la lógica de negocio.

Para ejecutar los tests:

```bash
cd backend
./mvnw test
```

## Ejecución local

### Backend

Para ejecutar el backend se requiere:

- Java 21
- PostgreSQL
- Variables de entorno para la conexión a la base de datos

Ejecutar:

```bash
cd backend
./mvnw spring-boot:run
```

Por defecto, la API estará disponible en:

```text
http://localhost:8081
```

### Frontend

Instalar las dependencias:

```bash
cd frontend
npm install
```

Ejecutar Angular:

```bash
ng serve
```

El frontend estará disponible en:

```text
http://localhost:4200
```

## Estado del proyecto

TaskFlow cuenta actualmente con un **CRUD Full Stack funcional y desplegado**, con integración entre Angular, Spring Boot y PostgreSQL.

El frontend consume la API REST mediante `HttpClient` y utiliza `Reactive Forms` para la creación y edición de tareas.

El backend utiliza una arquitectura por capas con **Controller, Service, Repository y DTOs**, junto con Spring Data JPA e Hibernate para la persistencia.

La aplicación está desplegada en Render y el backend se encuentra contenerizado con Docker.

## Documentación

Las decisiones técnicas y el avance del proyecto se encuentran documentados en la carpeta [`docs/`](docs/).