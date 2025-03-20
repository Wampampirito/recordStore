# RecordStore

RecordStore es una API REST desarrollada con Spring Boot que permite gestionar productos relacionados con la música, como álbumes, vinilos, tornamesas, audífonos, bocinas y reproductores.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (en desarrollo)
- **JPA con estrategia de herencia JOINED**

## Estructura del proyecto

El proyecto utiliza una estructura basada en la herencia para la gestión de productos:

- **Entidades principales**:
  - `Product` (abstracta)
    - `Album`
      - `Vinyl`
    - `Player`
      - `Portable`
      - `Turntable`
    - `AudioEquipment` (abstracta)
      - `Headphone`
      - `Speaker`
- **Otras entidades**:
  - `User` (usuarios de la tienda)
  - `Order` (pedidos realizados por los usuarios)
  - `OrderProduct` (relación entre `Order` y `Product`)
  - `Wishlist` (lista de deseos de un usuario)
  - `WishlistProduct` (relación entre `Wishlist` y `Product`)

## Configuración y ejecución  

### Requisitos previos:

- **Java 17** o superior  
- **Maven**  

### Instalación y ejecución:

1. Clona el repositorio:
   ```bash  
   git clone https://github.com/tu_usuario/recordstore.git  
   cd recordstore  
   ```
2. Compila y ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
   La API estará disponible en: [http://localhost:8080](http://localhost:8080)

## Endpoints principales

- **POST** `/seeder/populate`: Pobla la base de datos con datos de prueba.
- **POST** `/wishlist/{userId}/add/{productId}`: Agregar un producto a la lista de deseos.
- **DELETE** `/wishlist/{userId}/remove/{productId}`: Eliminar un producto de la lista de deseos.
- **GET** `/wishlist/{userId}`: Obtener los productos de la lista de deseos de un usuario.
- **POST** `/orders/{userId}`: Crear un nuevo pedido.
- **GET** `/orders/{userId}`: Obtener los pedidos de un usuario.

## Notas sobre la persistencia

- Se usa **H2** como base de datos en desarrollo.
- `@Inheritance(strategy = InheritanceType.JOINED)` se usa para la herencia de productos.
- `@Enumerated(EnumType.STRING)` se usa para los enums.

## Mejoras futuras

- Implementación de seguridad y autenticación.
- Integración con una base de datos en producción (**MySQL** o **PostgreSQL**).
- Implementación completa de los servicios y controladores restantes.
  
Desarrollado por **Iván Egüed**.
