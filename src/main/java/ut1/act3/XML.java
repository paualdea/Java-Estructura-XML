package ut1.act3;

// IMPORTS
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XML {
    // Creamos los objetos File con el directorio y fichero xml que vamos a manejar
    final File directorio = new File("./xml");
    final File rutaXML = new File(directorio, "usuarios.xml");

    // Estos son todos los objetos necesarios para manejar el XML
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document documento = null;
    Element usuarios;
    Source source;
    Result result;
    Transformer transformer;

    /**
     * Esta función constructora inicializa el sistema de ficheros de esta aplicación
     */
    public XML() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
        } catch (ParserConfigurationException e) {
            System.err.println("Error al crear los objetos para manejar XML");
        }

        if (!directorio.exists()) {
            try {
                directorio.mkdir();
            } catch (Exception e) {
                System.err.println("No se ha podido crear el directorio " + directorio);
            }
        }

        // Ahora, comprobamos si existe el fichero XML
        if(!rutaXML.exists()) {
            try {
                rutaXML.createNewFile();

                // Creamos la raíz
                documento = implementation.createDocument(null, "usuarios", null);

                source = new DOMSource(documento);
                result = new StreamResult(rutaXML);
                try {
                    transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source,result);
                } catch (TransformerException e) {
                    System.err.println("Error al crear el Transformer");
                }
            }
            catch (IOException e) {
                System.err.println("No se ha podido crear el fichero " + rutaXML);
            }
        } else {
            try {
                // En caso de que el fichero ya exista, el objeto documento lo lee
                documento = builder.parse(rutaXML);
            } catch (IOException | SAXException e) {
                System.err.println("Error al abrir el fichero XML");
            }
        }
    }

    /**
     * Esta función crea un objeto usuario con los datos que pasa el usuario por Scanner
     *
     * @param usuario
     * Nombre de usuario
     * @param direccion
     * Dirección del usuario
     * @param telefono
     * Télefono del usuario
     * @param email
     * Correo electrónico del usuario
     */
    public void crearUsuario(String usuario, String direccion, String telefono, String email) {
        // Creamos los elementos necesarios para registrar un usuario
        Element user = documento.createElement("usuario");

        // Al crear el elemento, añadimos tambien cómo hijo un objeto de tipo Text con el contenido de la variable
        Element nombre = documento.createElement("nombre");
        Text textNombre = documento.createTextNode(usuario);
        nombre.appendChild(textNombre);
        user.appendChild(nombre);

        Element direction = documento.createElement("direccion");
        Text textDirection = direction.getOwnerDocument().createTextNode(direccion);
        direction.appendChild(textDirection);
        user.appendChild(direction);

        Element phone = documento.createElement("telefono");
        Text textPhone = documento.createTextNode(telefono);
        phone.appendChild(textPhone);
        user.appendChild(phone);

        Element mail = documento.createElement("email");
        Text textMail = documento.createTextNode(email);
        mail.appendChild(textMail);
        user.appendChild(mail);

        // Añadimos este usuario a la raíz del fichero
        usuarios.appendChild(user);
        documento.getDocumentElement().appendChild(usuarios);

        source = new DOMSource(documento);
        result = new StreamResult(rutaXML);
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);
        } catch (TransformerException e) {
            System.err.println("Error al crear el Transformer");
        }
    }

    /**
     * Esta función borra el fichero XML actual y crea uno nuevo predeterminado
     */
    public void borrarFichero() {
        if (rutaXML.exists()) {
            rutaXML.delete();

            // Volvemos a crear la estructura básica del fichero
            try {
                rutaXML.createNewFile();

                // Creamos el elemento padre del fichero XML
                usuarios = documento.createElement("usuarios");

                source = new DOMSource(documento);
                result = new StreamResult(rutaXML);
                try {
                    transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source,result);
                } catch (TransformerException e) {
                    System.err.println("Error al crear el Transformer");
                }
            }
            catch (IOException e) {
                System.err.println("No se ha podido crear el fichero " + rutaXML);
            }
        } else {
            System.err.println("El fichero" + rutaXML + " no existe, no se borra nada");
        }
    }
}
