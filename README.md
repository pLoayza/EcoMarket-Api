
# Market API

Este proyecto es una aplicación backend desarrollada en Java con **Spring Boot**, que expone dos APIs principales para la gestión de un sistema de mercado:

- **CRUD de Usuarios**
- **CRUD de Productos**

## Requisitos Previos

- **Java 17** o superior
- **Maven** 
- **Base de Datos** (se recomienda usar MySQL, PostgreSQL, o cualquier base de datos relacional compatible)

## Instrucciones de Configuración

### Clonar el Repositorio

Clona este repositorio en tu máquina local:

```bash
git clone https://github.com/tu_usuario/market-api.git
cd EcoMarket-Api
```

### Configurar el Proyecto

1. **Instalar las dependencias:**

Si usas Maven:

```bash
mvn install
```

2. **Configurar el archivo de propiedades**:

En el archivo `application.yml` en el directorio `src/main/resources` de cada api, configura las variables de entorno necesarias (como base de datos, puerto, etc.).

Ejemplo de configuración para conexión a una base de datos PostgreSQL:

```properties
spring:
profiles:
active: ${SPRING_PROFILES_ACTIVE:dev}
application:
name: usuarios
datasource:
url: jdbc:postgresql://ip:puerto/nombre_base_de_datos
username: usuario
password: contraseña
hikari:
maximum-pool-size: 1
jpa:
hibernate:
ddl-auto: create-drop
show-sql: true
properties:
format_sql: true
hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect

server:
port: puerto
```

### Ejecutar la Aplicación

Para iniciar el servidor de Spring Boot, en la api que se quiera ejecutar:

```bash
mvn spring-boot:run
```


La aplicación estará disponible en [http://localhost:puerto](http://localhost:8080).

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

- En ambos CRUD si no se especifíca el estado por defecto será un Boolean true
- Se recomienda no hacer delete si no hacer "soft delete", cambiando el estado
tanto de usuario como de producto
## Contribuir

1. Crea una nueva rama para tu feature
2. Realiza tus cambios
3. Envía un Pull Request con una descripción clara de los cambios
