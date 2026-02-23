package ut1.act3;

import java.util.Scanner;

public class Main {
    // Creamos el objeto que manejara el XML
    static XML xml = new XML();
    final static int TIEMPO_ESPERA = 1250;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String opcion_aux;
        int opcion;

        while (!salir) {
            limpiarPantalla();

            System.out.print("\t\t.:GESTIÓN XML:.\n1. Añadir usuario\n2. Borrar fichero XML\n3. Salir\n\nOpción: ");
            opcion_aux = sc.next();
            opcion = Integer.parseInt(opcion_aux);

            if (opcion > 0 && opcion < 4) {
                switch (opcion) {
                    case 1:
                        limpiarPantalla();

                        // Saltamos la primera linea restante en el Scanner para que nos deje escribir el nombre
                        sc.nextLine();

                        // Pedimos todos los datos con un Scanner
                        System.out.print("\t\t.:CREACIÓN USUARIO:.\n\nNombre usuario: ");
                        String nombre = sc.nextLine();
                        System.out.print("\nDirección: ");
                        String direccion = sc.nextLine();
                        System.out.print("\nTeléfono: ");
                        String telefono = sc.nextLine();
                        System.out.print("\nCorreo electrónico: ");
                        String email = sc.nextLine();

                        // Mandamos los valores recogidos a la función crearUsuario()
                        xml.crearUsuario(nombre, direccion, telefono, email);
                        break;
                    case 2:
                        xml.borrarFichero();
                        break;
                    case 3:
                        salir = true;

                        limpiarPantalla();
                        System.err.println("Saliendo...");
                        espera();
                        break;
                }
            } else {
                System.err.println("Solo se permite opciones 1-3\n");
                espera();

                // Pasamos la linea del escaner para que no entre en bucles de error
                sc.nextLine();
            }
        }
    }

    /**
     * Esta función limpia la pantalla dependiendo del sistema operativo que tengas
     */
    public static void limpiarPantalla() {
        try {
            // Obtenemos el sistema operativo desde el que se ejecuta el programa
            String so = System.getProperty("os.name").toLowerCase();

            // Si es windows lanzamos el comando cls para borrar la pantalla
            if (so.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Si es Linux o Mac, lanzamos una secuencia de caracteres ANSI que limpia y borra la pantalla
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error al limpiar la pantalla.\n" + e.getMessage());
        }
    }

    /**
     * Esta función ejecuta un bloque de código que para la ejecución de espera TIEMPO_ESPERA segundos
     */
    public static void espera() {
        try {
            Thread.sleep(TIEMPO_ESPERA);
        } catch (InterruptedException e) {
            System.err.println("No se ha podido hacer la pausa de " + TIEMPO_ESPERA);
        }
    }
}
