# Documentación del Servidor AuthSpringVue

**AuthSpringVue** es una aplicación backend desarrollada con **Spring Boot** que proporciona un sistema de autenticación basado en JWT y una API REST para la gestión de libros. Este servidor interactúa con un frontend en Vue.js (u otro cliente) y utiliza una base de datos PostgreSQL para almacenar información de usuarios y libros. La aplicación implementa seguridad robusta, control de acceso basado en roles y manejo de errores estandarizado.

### Tecnologías principales

- **Spring Boot**: Framework principal para la API REST.
- **Spring Security**: Gestión de autenticación y autorización con JWT.
- **PostgreSQL**: Base de datos relacional.
- **Docker**: Para la gestión de servicios externos (base de datos).
- **Maven**: Gestión de dependencias y compilación.

## Estructura recomendada de carpetas

```
server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── io/github/angel/raa/
│   │   │       ├── controller/
│   │   │       ├── configuration/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── mapper/
│   │   │       ├── persistence/
│   │   │       │   ├── entity/
│   │   │       │   └── repository/
│   │   │       ├── service/
│   │   │       └── utils/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── io/github/angel/raa/
├── .env-template
├── docker-compose.yml
├── pom.xml
├── HELP.md
```

### Archivo `.env-template`

```env
# .env-template
# Copia este archivo como `.env` y configura los valores según tu entorno.

# Configuración de la base de datos (PostgreSQL)
DB_HOST=localhost                 # Host de la base de datos (ej: localhost, db.example.com)
DB_PORT=5432                     # Puerto de la base de datos (por defecto: 5432 para PostgreSQL)
DB_NAME=authspringvue            # Nombre de la base de datos
DB_USER=usuario                  # Usuario de la base de datos
DB_PASSWORD=contraseña           # Contraseña del usuario (cífrala en entornos de producción)

# Configuración de JWT
JWT_SECRET=tu_clave_secreta_jwt  # Clave secreta para firmar tokens JWT (mínimo 32 caracteres, usa un generador seguro)
JWT_EXPIRATION_MS=86400000       # Expiración del token en milisegundos (ej: 86400000 = 24 horas)

# Perfil de Spring
SPRING_PROFILES_ACTIVE=dev       # Perfil activo (dev, prod, test)
```

### API Endpoints

#### Rutas públicas (no requieren autenticación)

| Método | Endpoint                      | Descripción                     | Parámetros                                                                | Respuesta exitosa                                                                                                  |
| ------ | ----------------------------- | ------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| POST   | `/auth/register`              | Registra un nuevo usuario       | JSON: `{ "username": "string", "password": "string", "email": "string" }` | `201 Created` con `{ "id": "uuid", "username": "string", "email": "string" }`                                      |
| POST   | `/auth/login`                 | Autentica usuario y retorna JWT | JSON: `{ "username": "string", "password": "string" }`                    | `200 OK` con `{ "token": "jwt_token" }`                                                                            |
| GET    | `/books`                      | Lista todos los libros          | Ninguno                                                                   | `200 OK` con `[ { "id": "uuid", "title": "string", "author": "string", "isbn": "string", "available": boolean } ]` |
| GET    | `/books/title/{title}`        | Busca libros por título         | Path: `title` (string)                                                    | `200 OK` con `[ { ... } ]`                                                                                         |
| GET    | `/books/author?author=nombre` | Busca libros por autor          | Query: `author` (string)                                                  | `200 OK` con `[ { ... } ]`                                                                                         |
| GET    | `/books/isbn/{isbn}`          | Busca libro por ISBN            | Path: `isbn` (string)                                                     | `200 OK` con `{ ... }`                                                                                             |
| GET    | `/books/available`            | Lista libros disponibles        | Ninguno                                                                   | `200 OK` con `[ { ... } ]`                                                                                         |

**Ejemplo de solicitud (`POST /auth/register`):**

```bash
curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{"username": "john_doe", "password": "SecurePass123", "email": "john@example.com"}'
```

### Rutas protegidas (requieren autenticación JWT)

- `POST /books` — Crear libro (solo usuarios autenticados).
- `POST /books/bulk` — Crear varios libros (solo usuarios autenticados).
- `GET /books/me` — Libros del usuario autenticado.
- `PUT /books/{bookId}` — Actualizar libro (solo propietario).
- `DELETE /books/{bookId}` — Eliminar libro (solo propietario).

**Nota:** Para acceder a rutas protegidas, debes enviar el header:

```
Authorization: Bearer <token>
```

## Seguridad y configuración

- **Autenticación JWT:** Todas las rutas protegidas requieren token JWT válido.
- **Roles:** Control de acceso por roles (`ADMIN`, `USER`, `GUEST`).
- **Contraseñas:** Se valida que no estén comprometidas usando HaveIBeenPwned.
- **CORS:** Configurado para permitir peticiones desde el frontend.
- **Variables sensibles:** No subas `.env` con credenciales reales al repositorio.
- **Gestión de errores:** Respuestas claras para errores de autenticación y autorización.

## Inicialización del servidor

1. Copia `.env-template` como `.env` y configura tus variables.
2. Levanta la base de datos con Docker:
   ```sh
   docker-compose up -d
   ```
3. Instala dependencias y ejecuta el servidor:
   ```sh
   ./mvnw spring-boot:run
   ```

## Recomendaciones

- Mantén actualizado el secreto JWT y la expiración.
- Revisa los logs para detectar intentos de acceso no autorizado.
- Actualiza dependencias en `pom.xml` regularmente.
- Usa perfiles de Spring para separar configuración de
