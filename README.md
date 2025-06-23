
# Market API

Este proyecto es una aplicación backend desarrollada en Java con **Spring Boot**, que expone dos APIs principales para la gestión de un sistema de mercado:

- **CRUD de Usuarios**
- **CRUD de Productos**

## Requisitos Previos

- **Java 17** o superior
- **Maven** o **Gradle** (para la gestión de dependencias)
- **Base de Datos** (se recomienda usar MySQL, PostgreSQL, o cualquier base de datos relacional compatible)

## Instrucciones de Configuración

### Clonar el Repositorio

Clona este repositorio en tu máquina local:

```bash
git clone https://github.com/tu_usuario/market-api.git
cd market-api
```

### Configurar el Proyecto

1. **Instalar las dependencias:**

Si usas Maven:

```bash
mvn install
```

O si prefieres Gradle:

```bash
gradle build
```

2. **Configurar el archivo de propiedades**:

Crea un archivo `application.properties` en el directorio `src/main/resources` y configura las variables de entorno necesarias (como base de datos, puerto, etc.).

Ejemplo de configuración para conexión a una base de datos MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/marketdb
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Ejecutar la Aplicación

Para iniciar el servidor de Spring Boot, ejecuta:

```bash
mvn spring-boot:run
```

O si usas Gradle:

```bash
gradle bootRun
```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080).

## Endpoints Disponibles

### CRUD de Usuarios

- **GET** `/api/usuarios` - Obtener todos los usuarios
- **POST** `/api/usuarios` - Crear un nuevo usuario
- **GET** `/api/usuarios/{id}` - Obtener un usuario por ID
- **PUT** `/api/usuarios/{id}` - Actualizar un usuario
- **DELETE** `/api/usuarios/{id}` - Eliminar un usuario

### CRUD de Productos

- **GET** `/api/productos` - Obtener todos los productos
- **POST** `/api/productos` - Crear un nuevo producto
- **GET** `/api/productos/{id}` - Obtener un producto por ID
- **PUT** `/api/productos/{id}` - Actualizar un producto
- **DELETE** `/api/productos/{id}` - Eliminar un producto

## Contribuir

1. Crea una nueva rama para tu feature
2. Realiza tus cambios
3. Envía un Pull Request con una descripción clara de los cambios
