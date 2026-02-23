# Acceso a Datos: Persistencia con estructura en XML

Este proyecto es una aplicación de consola en **Java** desarrollada como parte de la **Actividad 3: "Creación de un fichero XML con información estructurada"** de la Unidad de Trabajo 1 (UT1) del módulo **Acceso a Datos**.

La aplicación permite la creación de usuarios, almacenando sus datos personales (Nombre, Dirección, Teléfono y Correo Electrónico) en un archivo XML con jerarquía. De esta forma, la información queda mucho mas ordenada y visual que un fichero de texto.

El objetivo principal es dominar el uso de la API **DOM (Document Object Model)** para la creación, lectura y manipulación de documentos XML en entornos Java.

## Características Principales

* **Estructura Jerárquica**: Implementación de etiquetas `<usuario>`, que contienen todos los datos de cada usuario , dentro de su raíz `<usuarios>`.
* **Persistencia**: El sistema detecta si el archivo `usuarios.xml` ya existe para cargar su contenido o si debe crear uno nuevo.
* **XML Formateado**: Configuración del motor `Transformer` para así generar un XML tabulado, más fácil de leer e interpretar.

## Estructura del Proyecto

* **`Main.java`**: Controla el menú interactivo principal y la ejecución de las funciones de la clase `XML.java`.
* **`XML.java`**: Gestiona toda la lógica que aplica al fichero XML, entre los que se incluye la inicialización, inserción y eliminado.

## Ejecución

Para ejecutar este programa, basta con descargar el fichero `Java-Estructura-XML_vx.x.x.jar` de las _releases_ y ejecutarlo con `java -jar Java-Estructura-XML_vx.x.x.jar` desde cualquier sistema con Java instalado.

Funciona con Windows, Mac y Linux.

___
Este proyecto sirve como evidencia del aprendizaje sobre el manejo de ficheros XML y la estructuración jerárquica de datos para la asignatura de Acceso a Datos.
