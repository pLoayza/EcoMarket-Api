Market API
Este proyecto es una aplicación backend desarrollada en Java con Spring Boot, que expone dos APIs principales para la gestión de un sistema de mercado:

CRUD de Usuarios

CRUD de Productos

Tecnologías utilizadas
Java 17 (o la versión que uses)

Spring Boot 3.x

Spring Data JPA

Hibernate

Base de datos relacional (H2, MySQL, PostgreSQL, según configuración)

Maven / Gradle (según prefieras)

Estructura del proyecto
UserController: Controlador REST para la gestión de usuarios (Crear, Leer, Actualizar, Eliminar)

ProductController: Controlador REST para la gestión de productos (Crear, Leer, Actualizar, Eliminar)

UserService y ProductService: Lógica de negocio para usuarios y productos

UserRepository y ProductRepository: Interfaces para acceso a datos con Spring Data JPA

User y Product: Entidades JPA que representan las tablas de la base de datos

Funcionalidades
API de Usuarios
Permite realizar operaciones CRUD sobre los usuarios registrados en el sistema.

Crear usuario: POST /api/users

Obtener todos los usuarios: GET /api/users

Obtener usuario por ID: GET /api/users/{id}

Actualizar usuario: PUT /api/users/{id}

Eliminar usuario: DELETE /api/users/{id}

API de Productos
Permite realizar operaciones CRUD sobre los productos disponibles en el mercado.

Crear producto: POST /api/products

Obtener todos los productos: GET /api/products

Obtener producto por ID: GET /api/products/{id}

Actualizar producto: PUT /api/products/{id}

Eliminar producto: DELETE /api/products/{id}

Cómo ejecutar el proyecto
Clonar el repositorio

bash
Copiar
Editar
git clone https://github.com/tu_usuario/market-api.git
cd market-api
Configurar la base de datos en application.properties o application.yml

Ejecutar el proyecto con Maven o Gradle

bash
Copiar
Editar
./mvnw spring-boot:run
o

bash
Copiar
Editar
./gradlew bootRun
La aplicación estará disponible por defecto en:
http://localhost:8080

Ejemplos de petición
Crear un usuario
http
Copiar
Editar
POST /api/users
Content-Type: application/json

{
  "nombre": "Juan Perez",
  "email": "juan@example.com",
  "password": "123456"
}
Crear un producto
http
Copiar
Editar
POST /api/products
Content-Type: application/json

{
  "nombre": "Camiseta",
  "descripcion": "Camiseta 100% algodón",
  "precio": 15.99
}
Mejoras futuras
Autenticación y autorización (JWT)

Paginación y filtrado en listados

Validaciones avanzadas en datos de entrada

Documentación con Swagger/OpenAPI

Contacto
Para dudas o sugerencias, puedes contactarme en:
tu.email@dominio.com