# Proyecto Backend en Spring Boot

Este proyecto es un backend desarrollado con **Spring Boot**, que expone endpoints para gestionar usuarios en la ruta `/auth/usuarios/**`.

## Requisitos Previos

Asegúrate de tener instalados los siguientes programas en tu máquina:

- **Java 17**
- **Maven 3.8+**
- **Git**
- Una herramienta para pruebas de API como Postman o cURL.

## Instalación

1. Clona este repositorio:

   ```bash
   git clone https://github.com/fjvc2891/ppartners.git
   cd ppartners
   ```

2. Construye el proyecto con Maven:

   ```bash
   mvn clean install
   ```

3. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

4. Accede al servidor en [http://localhost:8080](http://localhost:8080).

## Endpoints Principales

### **Autenticación**

#### `POST /auth/login`
- **Descripción**: Autentica un usuario y devuelve un token JWT.
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
    "username": "admin",
    "password": "admin123"
  }
  ```
- **Respuesta**:
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9..."
  }
  ```

#### `GET /auth/usuarios`
- **Descripción**: Obtiene la lista de usuarios (requiere autenticación).
- **Headers**:
  - `Authorization: Bearer <TOKEN>`
- **Respuesta**:
  ```json
  [
    {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com"
    }
  ]
  ```

### **Gestión de Usuarios**

#### `POST /auth/usuarios`
- **Descripción**: Crea un nuevo usuario.
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
    "username": "newuser",
    "password": "password123",
    "email": "newuser@example.com"
  }
  ```
- **Respuesta**:
  ```json
  {
    "id": 2,
    "username": "newuser",
    "email": "newuser@example.com"
  }
  ```

#### `GET /auth/usuarios/{id}`
- **Descripción**: Obtiene un usuario específico por su ID.
- **Headers**:
  - `Authorization: Bearer <TOKEN>`
- **Respuesta**:
  ```json
  {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com"
  }
  ```

## Seguridad

Este proyecto utiliza **JWT (JSON Web Tokens)** para proteger los endpoints. Asegúrate de incluir el token en el encabezado `Authorization` para acceder a los recursos protegidos:

```
Authorization: Bearer <TOKEN>
```

## Tecnologías Usadas

- **Spring Boot**: Framework principal.
- **Spring Security**: Gestión de autenticación y autorización.
- **JWT**: Manejo de autenticación con tokens.
- **Spring Data JPA**: Acceso a datos.
- **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
- **Lombok**: Reducción de código boilerplate.

## Ejecutar Pruebas

Ejecuta las pruebas unitarias con Maven:

```bash
mvn test
```

## Documentación de API

La documentación de la API está disponible en Swagger UI:

- URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Autor

**Francisco Javier Vivas Córdoba**  
[GitHub Profile](https://github.com/fjvc2891)

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
