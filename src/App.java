import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Collections;

public class App {
    static ArrayList<String> canciones = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            menu();
        } catch (NullPointerException e) {
            // Termina la ejecucion del programa al dar click sobre
            // Los botones Cerrar o Cancelar
            System.exit(0);
        } catch (Exception e) {
            // Mostrar una exepcion personalizada
            imprimirExcepcion(e.getMessage());
        }
    }

    private static void menu() {

        String opcion = capturarOpcion();
        switch (opcion) {
            case "1": // Agregar cancion nueva
                agregarCancion();
                break;
            case "2": // Editar cancion"
                if (canciones.size() > 0) {
                    editarCancion();
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menu();
                }
                break;
            case "3": // Eliminar cancion
                if (canciones.size() > 0) {
                    eliminarCancion();
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menu();
                }
                break;
            case "4": // Ver canciones
                if (canciones.size() > 0) {
                    verCanciones();
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menu();
                }
                break;
            case "5": // Eliminar Todo
                if (canciones.size() > 0) {
                    eliminarTodasCanciones();
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menu();
                }
        }
    }


    private static void agregarCancion() {
        do {
            String nombreCancion = "";
            do {
                nombreCancion = entradaPersonalizada("Ingrese una cancion nueva");
                if (nombreCancion.matches("^\\s*$")) {
                    imprimirExcepcion("El nombre no puede estar vacio");
                }
            } while (nombreCancion.matches("^\\s*$"));

            // Agrega la nueva cancion al ArrayList
            canciones.add(nombreCancion);
        } while (repetirSeccion("¿Ingresar otra cancion?", "Agregar Canción"));
        
        menu();
    }

    private static void eliminarTodasCanciones() {
        boolean respuesta = repetirSeccion("¿Desea eliminar todas las canciones?", "Eliminar todo");
        if (respuesta) {
            canciones.clear();
        }

        menu();
    }

    private static void eliminarCancion() {
        do {
            int indice = getIndice("eliminar");
            canciones.remove(indice - 1);
        } while (repetirSeccion("¿Eliminar otra cancion?", "Eliminar Canción"));

        menu();
    }

    private static void verCanciones() {
        JOptionPane.showMessageDialog(null, getCancionesString());

        menu();
    }

    private static void editarCancion() {
        do {
            int indice = getIndice("editar");
            String nombreNuevo = "";
            do {
                nombreNuevo = entradaPersonalizada("Ingrese el nuevo nombre de la cancion");
                if (nombreNuevo.matches("^\\s*$")) {
                    imprimirExcepcion("El nombre no puede estar vacio");
                }
            } while (nombreNuevo.matches("^\\s*$"));
            canciones.set(indice - 1, nombreNuevo);
        } while (repetirSeccion("¿Editar otra cancion?", "Editar Canción"));

        menu();
    }

    private static int getIndice(String accion) {
        String indiceString = "";
        int indiceInt = -1;
        do {
            do {
                indiceString = entradaPersonalizada(getCancionesString() + "Seleccione la canción a  " + accion);
                if (!indiceString.matches("\\d+"))
                    imprimirExcepcion("Ingrese un numero de cancion valido");
            } while (!indiceString.matches("\\d+"));
            indiceInt = Integer.parseInt(indiceString);
            if (indiceInt < 1 || indiceInt > canciones.size())
                imprimirExcepcion("Ingrese un numero de cancion valido");
        } while (indiceInt < 1 || indiceInt > canciones.size());
        int indice = Integer.parseInt(indiceString);
        return indice;
    }

    private static String getCancionesString() {
        String cancionesString = "";
        Collections.sort(canciones);
        for (int i = 0; i < canciones.size(); i++) {
            cancionesString += i + 1 + ". " + canciones.get(i) + "\n";
        }
        return cancionesString;
    }

    private static boolean repetirSeccion(String mensaje, String titulo) {
        Object[] opciones = { "Si", "No" };
        return JOptionPane.showOptionDialog(null,
                mensaje,
                titulo,
                0,
                3,
                null,
                opciones,
                opciones[0]) == 0;
    }

    private static String capturarOpcion() {
        String opcion = "";
        do {
            opcion = entradaPersonalizada(
                    "Bienvenido al simulador de play-list\n1.- Agregar cancion nueva.\n2.- Editar cancion.\n3.- Eliminar una cancion.\n4.- Ver canciones.\n5.- Eliminar todas las canciones\n6.- Salir.");

            // salir si se ingresa 6
            if (opcion.equals("6"))
                System.exit(0);
            if (!opcion.matches("^[1-6]$"))
                imprimirExcepcion("Ingrese una opción valida.");
        } while (!opcion.matches("^[1-6]$"));
        return opcion;
    }

    private static void imprimirExcepcion(String e) {
        // muestra una ventana de tipo Error
        // con un mensaje personalizado de excepcion
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static String entradaPersonalizada(String txt) {
        JTextField textField = new JTextField();
        Object[] message = {
                txt, textField
        };

        int opcion = JOptionPane.showOptionDialog(
                null,
                message,
                "Entrada Personalizada",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);

        if (opcion == 0) {
            return textField.getText();
        }

        menu();
        return null; // Si el usuario presiona Cancelar
    }

}