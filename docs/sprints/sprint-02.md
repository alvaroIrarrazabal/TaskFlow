# Sprint 02 — Advanced Task Management

## Objetivo

Mejorar la gestión de tareas incorporando búsqueda, filtros y ordenamiento,
permitiendo al usuario encontrar y organizar sus tareas de manera eficiente.

Además, profundizar en la integración entre Angular y Spring Boot mediante
parámetros HTTP y consultas dinámicas.

## Historias de usuario

### US-01 — Filtrar por estado

Como usuario,
quiero filtrar mis tareas por estado,
para visualizar únicamente las tareas que necesito revisar.

Estados:

- TODO
- IN_PROGRESS
- DONE

### US-02 — Filtrar por prioridad

Como usuario,
quiero filtrar mis tareas por prioridad,
para identificar rápidamente las tareas más importantes.

Prioridades:

- LOW
- MEDIUM
- HIGH

### US-03 — Buscar tareas

Como usuario,
quiero buscar tareas por título,
para encontrar una tarea específica rápidamente.

### US-04 — Ordenar tareas

Como usuario,
quiero ordenar mis tareas,
para visualizar primero las tareas más relevantes.

Ordenamientos iniciales:

- Fecha límite.
- Prioridad.

## Backend

Implementar soporte para consultas mediante parámetros HTTP.

Ejemplos:

GET /api/tasks?status=TODO

GET /api/tasks?priority=HIGH

GET /api/tasks?title=angular

El backend será responsable de consultar las tareas correspondientes
en PostgreSQL.

Conceptos a trabajar:

- @RequestParam
- Query Methods
- Spring Data JPA
- Optional
- Consultas combinadas
- Separación de responsabilidades

## Frontend

Implementar controles para:

- Buscar por título.
- Filtrar por estado.
- Filtrar por prioridad.
- Ordenar resultados.

Angular enviará los filtros al backend mediante parámetros HTTP.

Conceptos a trabajar:

- HttpParams
- Reactive Forms
- Estado de filtros
- TypeScript
- Observables
- Comunicación entre componentes y servicios

## Testing

Agregar pruebas iniciales sobre funcionalidades relevantes del Sprint.

El objetivo será comenzar a incorporar testing progresivamente,
sin intentar cubrir toda la aplicación en este Sprint.

## Definition of Done

El Sprint se considera terminado cuando:

- El usuario puede filtrar tareas por estado.
- El usuario puede filtrar tareas por prioridad.
- El usuario puede buscar tareas por título.
- El usuario puede ordenar tareas.
- Los filtros son procesados correctamente por el backend.
- Angular utiliza parámetros HTTP para realizar las consultas.
- Los filtros pueden combinarse.
- Existen pruebas iniciales de las nuevas funcionalidades.
- La documentación refleja los cambios realizados.
- El código está correctamente versionado con Git.

## Estado

Planificado.
