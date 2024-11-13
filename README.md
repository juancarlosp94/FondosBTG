# FondoBTG

## Descripción
FondoBTG es una aplicación de gestión de fondos de inversión que permite a los usuarios suscribirse y cancelar suscripciones a fondos específicos. La aplicación está desarrollada con Spring Boot y utiliza MongoDB como base de datos para almacenar información de los usuarios, fondos, y transacciones.

## Funcionalidades
Crear Usuario: Permite la creación de un nuevo usuario en la base de datos.
Eliminar Usuario: Elimina un usuario de la base de datos utilizando su identificador único.
Suscripción a Fondos: Permite a los usuarios suscribirse a un fondo específico, verificando que cumplan con el saldo mínimo requerido.
Cancelación de Suscripción: Cancela la suscripción de un usuario a un fondo específico, devolviendo el monto mínimo al saldo del usuario.
Diseño y Patrones de Diseño
La aplicación FondoBTG sigue principios de diseño y patrones de diseño comunes en el desarrollo de software:

Inyección de Dependencias (DI): Se utiliza la anotación @Autowired para la inyección automática de dependencias en las clases de servicio, facilitando la gestión de dependencias y el desacoplamiento de componentes.

Patrón Servicio: La clase UsuarioService implementa la lógica de negocio principal, actuando como un servicio que gestiona las operaciones relacionadas con los usuarios, como la suscripción y cancelación de fondos. Este patrón ayuda a mantener la separación de responsabilidades y hace que el código sea más modular y reutilizable.

Repositorio: La aplicación utiliza interfaces de repositorio (UsuarioRepository, TransaccionRepository) que heredan de MongoRepository. Esto facilita las operaciones CRUD en la base de datos MongoDB, alineándose con el patrón Repositorio.

Manejo de Errores: La aplicación incorpora manejo de errores mediante excepciones (try-catch) para capturar errores de formato de ID y manejar casos en los que un usuario o fondo no se encuentre en la base de datos.

## Instalación y Ejecución
Prerrequisitos
Java 11 o superior
Maven para la gestión de dependencias
MongoDB en ejecución localmente o configurado para acceso remoto

### Paso 1: Clonar el repositorio
```
   git clone https://github.com/usuario/FondoBTG.git
   cd FondoBTG
```

### Paso 2: Configurar MongoDB
Asegúrate de que MongoDB esté corriendo en tu máquina local en el puerto predeterminado 27017. Si deseas cambiar la configuración, actualiza el archivo application.properties en la carpeta src/main/resources.
```
   spring.data.mongodb.uri=mongodb://localhost:27017/fondosbtg
```

### Paso 3: Compilar y ejecutar la aplicación
Usa Maven para compilar el proyecto y ejecutar la aplicación.
```
mvn clean install
mvn spring-boot:run
```

La aplicación estará disponible en http://localhost:8080.

## Uso de la API

### Endpoints Principales

#### Crear Usuario

- URL: POST /usuarios
- Body: JSON con datos del usuario
- Descripción: Crea un nuevo usuario en el sistema.

#### Eliminar Usuario

- URL: DELETE /usuarios/{usuarioId}
- Descripción: Elimina un usuario de la base de datos.

#### Suscribir a Fondo

- URL: POST /usuarios/{usuarioId}/suscribir/{fondoId}
- Descripción: Permite a un usuario suscribirse a un fondo si tiene saldo suficiente.

#### Cancelar Suscripción

- URL: DELETE /usuarios/{usuarioId}/cancelar/{fondoId}
- Descripción: Cancela la suscripción de un usuario a un fondo y reembolsa el monto mínimo al saldo del usuario.

Ejemplo de Solicitud de Suscripción
Para suscribir a un usuario a un fondo, realiza una solicitud POST a:
```
POST http://localhost:8080/usuarios/{usuarioId}/suscribir/{fondoId}
```
## Manejo de Errores
La aplicación maneja errores comunes y devuelve mensajes claros para casos como:

- Formato de ID Inválido: Si el usuarioId o fondoId proporcionado no es un ID válido, se devolverá un mensaje indicando el error de formato.
- Saldo Insuficiente: Si el usuario no tiene saldo suficiente, la aplicación notificará que no puede suscribirse al fondo.
- Usuario o Fondo No Encontrado: Si el usuario o fondo no existe en la base de datos, se devolverá un mensaje indicando que no se encontró.


# Solucion a parte 2 del enunciado. Consulta SQL:

```
SELECT DISTINCT c.nombre
FROM Cliente c
JOIN Inscripcion i ON c.id = i.idCliente
JOIN Disponibilidad d ON i.idProducto = d.idProducto
JOIN Visitan v ON c.id = v.idCliente AND d.idSucursal = v.idSucursal;
```
