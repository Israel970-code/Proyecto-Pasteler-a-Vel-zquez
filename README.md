# Pastelería Velázquez

Aplicación web de gestión de pedidos para una pastelería, desarrollada con **Spring Boot**, **Thymeleaf** y **MySQL**.

Permite a los clientes registrarse, iniciar sesión y gestionar sus pedidos de productos de pastelería, mientras que los administradores pueden administrar usuarios y acceder a un panel de control.

---

## Características principales

### Para usuarios (clientes)
- **Registro de usuarios**: creación de cuenta con nombre, email, teléfono, dirección y contraseña.
- **Inicio de sesión / cierre de sesión**: autenticación basada en sesión HTTP.
- **Gestión de pedidos**:
  - Crear nuevos pedidos (cantidad + tipo de producto).
  - Listar los propios pedidos.
  - Editar pedidos existentes.
  - Eliminar pedidos.
- **Tipos de producto disponibles**:
  - Tarta
  - Donut
  - Napolitana
  - Magdalena
  - Bizcocho
  - Croissant
- Cada pedido incluye fechas automáticas (fecha de pedido y fecha estimada de entrega a +10 días).

### Para administradores
- **Panel de administración** (`/admin`):
  - Ver listado de todos los usuarios registrados.
  - Editar datos de usuarios.
  - Eliminar usuarios.
- Control de acceso por rol (`ADMIN` / `USER`).

---

## Tecnologías utilizadas

| Tecnología              | Uso                                      |
|-------------------------|------------------------------------------|
| **Java 17**             | Lenguaje principal                       |
| **Spring Boot 4**       | Framework de la aplicación               |
| **Spring Data JPA**     | Persistencia y repositorios              |
| **Spring MVC**          | Controladores y rutas                    |
| **Thymeleaf**           | Plantillas HTML del lado del servidor    |
| **MySQL**               | Base de datos relacional                 |
| **Hibernate**           | ORM (incluido con JPA)                   |
| **Maven**               | Gestión de dependencias y build          |
| **Jakarta Validation**  | Validación de datos de pedidos          |

---

## Estructura del proyecto

```
src/main/java/com/pasteleria/velazquez/
├── PasteleriaVelazquezSpringApplication.java   # Punto de entrada
├── controlador/
│   ├── LoginController.java                    # Login, registro y logout
│   ├── PedidoController.java                   # CRUD de pedidos
│   └── AdminController.java                    # Panel de administración
├── modelo/
│   ├── Contacto.java                           # Entidad Usuario/Contacto
│   ├── Pedido.java                             # Entidad Pedido
│   └── TipoProducto.java                       # Enum de productos
├── repositorio/
│   ├── ContactoRepository.java
│   └── PedidoRepository.java
├── service/
│   └── PasteleriaService.java                  # Lógica de negocio
└── utils/
    └── ConexionDB.java

src/main/resources/
├── application.properties                      # Configuración (BD, puerto, etc.)
└── templates/
    ├── login.html
    ├── registro.html
    ├── lista.html                              # Listado de pedidos del usuario
    ├── form.html                               # Formulario de pedido (crear/editar)
    ├── admin.html                              # Panel de administración
    └── formUsuario.html                        # Formulario de edición de usuario
```

---

## Requisitos previos

- **JDK 17** o superior
- **Maven 3.6+** (o usar el wrapper `mvnw` incluido)
- **MySQL 8** (o compatible) en ejecución
- Base de datos: se crea automáticamente si no existe (`pasteleriaVelazquez`)

---

## Configuración

Edita el archivo `src/main/resources/application.properties` según tu entorno:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pasteleriaVelazquez?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Madrid
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA

server.port=8081
```

---

## Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Israel970-code/Proyecto-Pasteler-a-Vel-zquez.git
   cd Proyecto-Pasteler-a-Vel-zquez
   ```

2. Asegúrate de que MySQL esté en marcha y ajusta las credenciales en `application.properties`.

3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
   o en Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

4. Abre el navegador en:
   ```
   http://localhost:8081/login
   ```

---

## Rutas principales

| Ruta                    | Descripción                              | Acceso          |
|-------------------------|------------------------------------------|-----------------|
| `/login`                | Formulario de inicio de sesión           | Público         |
| `/registro`             | Formulario de registro                   | Público         |
| `/logout`               | Cerrar sesión                            | Autenticado     |
| `/lista`                | Listado de pedidos del usuario           | Usuario         |
| `/nuevo`                | Crear nuevo pedido                       | Usuario         |
| `/editar/{id}`          | Editar un pedido                         | Usuario/Admin   |
| `/eliminar/{id}`        | Eliminar un pedido                       | Usuario         |
| `/guardar` / `/guardarPedido` | Guardar pedido                      | Usuario         |
| `/admin`                | Panel de administración de usuarios      | Solo Admin      |
| `/admin/editar/{id}`    | Editar usuario                           | Solo Admin      |
| `/admin/eliminar/{id}`  | Eliminar usuario                         | Solo Admin      |
| `/admin/guardar`        | Guardar cambios de usuario               | Solo Admin      |

---

## Modelo de datos

### Contacto (Usuario)
- `id`, `nombre`, `email`, `telefono`, `direccion`, `password`, `rol` (`USER` o `ADMIN`)

### Pedido
- `id`, `cantidad`, `tipoProducto`, `fecha1` (fecha del pedido), `fecha2` (entrega estimada), relación con `Contacto`

### TipoProducto (enum)
`TARTA`, `DONUT`, `NAPOLITANA`, `MAGDALENA`, `BIZCOCHO`, `CROISSANT`

---

## Notas

- La autenticación se realiza mediante sesión HTTP (sin Spring Security).
- Las contraseñas se almacenan en texto plano (adecuado solo para entornos de aprendizaje/demo).
- Hibernate crea/actualiza las tablas automáticamente (`ddl-auto=update`).
- El puerto por defecto es **8081**.

---

## Autor

Proyecto desarrollado como práctica de **DAW** (Desarrollo de Aplicaciones Web).

Repositorio: [Israel970-code/Proyecto-Pasteler-a-Vel-zquez](https://github.com/Israel970-code/Proyecto-Pasteler-a-Vel-zquez)
