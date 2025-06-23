# Market API

Este proyecto es una aplicación backend desarrollada en Java con **Spring Boot**, que expone dos APIs principales para la gestión de un sistema de mercado:

- CRUD de Usuarios
- CRUD de Productos

## 🚀 Tecnologías Utilizadas

- Java 17
- Spring Boot
- Maven o Gradle
- PostgreSQL (base de datos principal)
- Spring Data JPA
- H2 Database (opcional para pruebas locales)
- Springdoc OpenAPI (Swagger UI)

## 📁 Estructura del Proyecto

- UserController y ProductController: Controladores REST que exponen los endpoints para operaciones CRUD.
- UserService y ProductService: Capa de servicio con lógica de negocio.
- UserRepository y ProductRepository: Interfaces de acceso a datos usando Spring Data JPA.
- User y Product: Entidades JPA que representan las tablas de la base de datos.

## ✅ Requisitos Previos

- Java 17 o superior
- Maven o Gradle
- Base de Datos PostgreSQL (o H2 para pruebas)
- IDE recomendado: IntelliJ IDEA o VSCode

## ⚙️ Instrucciones de Configuración

### 1. Clonar el Repositorio

git clone https://github.com/tu_usuario/market-api.git
cd market-api

### 2. Configurar la Base de Datos

Edita el archivo `src/main/resources/application.yml` o `application.properties` con tus credenciales de PostgreSQL. Ejemplo:

spring:
  datasource:
    url: jdbc:postgresql://35.223.30.52:5432/xxxxxxxx
    username: xxxxxxxxxx
    password: xxxxxxxxxx
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      format_sql: true
server:
  port: 8080

> Si prefieres usar H2, cambia el datasource.url a jdbc:h2:mem:testdb y habilita spring.h2.console.enabled=true

### 3. Instalar Dependencias

Con Maven:
mvn install

Con Gradle:
gradle build

### 4. Ejecutar la Aplicación

Con Maven:
mvn spring-boot:run

Con Gradle:
gradle bootRun

La API estará disponible en: http://localhost:8080

---

## 🔌 Endpoints Disponibles

### CRUD de Usuarios (/api/usuarios)

GET    /api/usuarios        - Listar todos los usuarios
POST   /api/usuarios        - Crear nuevo usuario
GET    /api/usuarios/{id}   - Obtener usuario por ID
PUT    /api/usuarios/{id}   - Actualizar usuario existente
DELETE /api/usuarios/{id}   - Eliminar usuario por ID

### CRUD de Productos (/api/productos)

GET    /api/productos       - Listar todos los productos
POST   /api/productos       - Crear nuevo producto
GET    /api/productos/{id}  - Obtener producto por ID
PUT    /api/productos/{id}  - Actualizar producto existente
DELETE /api/productos/{id}  - Eliminar producto por ID

---

## 📓 Documentación Swagger

Una vez levantado el proyecto, accede a la documentación Swagger/OpenAPI en:

http://localhost:8080/swagger-ui.html

---

## 🧪 Base de Datos H2 (para pruebas locales)

Puedes habilitar la consola web de H2 agregando en tu archivo de configuración:

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

Luego accede a: http://localhost:8080/h2-console

---

## 🤝 Contribuir

1. Haz un fork del proyecto.
2. Crea una nueva rama: git checkout -b feature/tu-feature
3. Realiza tus cambios y haz commit: git commit -m "Agrega nueva funcionalidad"
4. Haz push a tu rama: git push origin feature/tu-feature
5. Abre un Pull Request para revisión.



