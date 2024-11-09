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
