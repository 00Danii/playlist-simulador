import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Collections;

public class App {
    static ArrayList<String> canciones = new ArrayList<String>();
    static ArrayList<Lista> listas = new ArrayList<Lista>();

    public static void main(String[] args) {

        try {
            menuListas();
        } catch (NullPointerException e) {
            // Termina la ejecucion del programa al dar click sobre
            // Los botones Cerrar o Cancelar
            System.exit(0);
        } catch (Exception e) {
            // Mostrar una exepcion personalizada
            imprimirExcepcion(e.getMessage());
        }
    }

    private static void menuListas() {
        String opcion = capturarOpcionLista();
        switch (opcion) {
            case "1": // Agregar Lista nueva
                agregarLista();
                break;

            case "2": // Ver lista de Listas"
                if (listas.size() > 0) {
                    verCancionesListas();
                } else {
                    imprimirExcepcion("Sin Listas");
                    menuListas();
                }
                break;

            case "3": // Editar Lista
                if (listas.size() > 0) {
                    editarLista();
                } else {
                    imprimirExcepcion("Sin Listas");
                    menuListas();
                }
                break;
                
            case "4": // Eliminar una Lista
                if (listas.size() > 0) {
                    eliminarLista();
                } else {
                    imprimirExcepcion("Sin Listas");
                    menuListas();
                }
                break;
            case "5": // Eliminar Todo
                if (listas.size() > 0) {
                    eliminarTodasListas();
                } else {
                    imprimirExcepcion("Sin Listas");
                    menuListas();
                }
        }
    }

    private static void verCancionesListas() {
        int indice = getIndiceLista("visualizar");
        String nombre = listas.get(indice-1).getNombre();
        canciones = listas.get(indice-1).canciones;
        menuCanciones(nombre, canciones);
    }

    private static void eliminarTodasListas() {
        boolean respuesta = repetirSeccion("¿Desea eliminar todas las Play-List?", "Eliminar todo");
        if (respuesta) {
            listas.clear();
        }
        menuListas();
    }

    private static void eliminarLista() {
        do {
            int indice = getIndiceLista("eliminar");
            listas.remove(indice - 1);
        } while (repetirSeccion("¿Eliminar otra Play-List?", "Eliminar Play-List"));
        menuListas();
    }

    private static void editarLista() {
        do {
            int indice = getIndiceLista("editar");
            String nombreNuevo = "";
            do {
                nombreNuevo = entradaPersonalizadaLista("Ingrese el nuevo nombre de la lista");
                if (nombreNuevo.matches("^\\s*$")) {
                    imprimirExcepcion("El nombre no puede estar vacio");
                }
            } while (nombreNuevo.matches("^\\s*$"));
            listas.get(indice - 1).setNombre(nombreNuevo);
        } while (repetirSeccion("¿Editar otra lista?", "Editar Canción"));
        menuListas();
    }

    private static String getListasString() {
        String listasString = "";
        for (int i = 0; i < listas.size(); i++) {
            listasString += i + 1 + ". " + listas.get(i).getNombre() + "\n";
        }
        return listasString;
    }

    private static void agregarLista() {
        listas.add(new Lista(capturarNombre()));
        menuListas();
    }

    private static String capturarNombre() {
        String nombre = "";
        do {
            nombre = entradaPersonalizadaLista("Ingrese un nombre de lista nuevo");
            if (nombre.matches("^\\s*$")) {
                imprimirExcepcion("El nombre no puede estar vacio");
            }
        } while (nombre.matches("^\\s*$"));
        return nombre;
    }

    private static String capturarOpcionLista() {
        String opcion = "";
        do {
            opcion = JOptionPane.showInputDialog(
                    "Bienvenido al simulador de play-list\n1.- Agregar Play List.\n2.- Ver Listas.\n3.- Editar una Play List.\n4.- Eliminar una Lista.\n5.- Eliminar todas las Listas\n6.- Salir.");

            // salir si se ingresa 6
            if (opcion.equals("6"))
                System.exit(0);
            if (!opcion.matches("^[1-6]$"))
                imprimirExcepcion("Ingrese una opción valida.");
        } while (!opcion.matches("^[1-6]$"));
        return opcion;
    }

    private static void menuCanciones(String nombre, ArrayList<String> canciones2) {

        String opcion = capturarOpcion(nombre);
        switch (opcion) {
            case "1": // Agregar cancion nueva
                agregarCancion(nombre, canciones2);
                break;
            case "2": // Editar cancion"
                if (canciones.size() > 0) {
                    editarCancion(nombre, canciones2);
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menuCanciones(nombre, canciones2);
                }
                break;
            case "3": // Eliminar cancion
                if (canciones.size() > 0) {
                    eliminarCancion(nombre, canciones2);
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menuCanciones(nombre, canciones2);
                }
                break;
            case "4": // Ver canciones
                if (canciones.size() > 0) {
                    verCanciones(nombre, canciones2);
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menuCanciones(nombre, canciones2);
                }
                break;
            case "5": // Eliminar Todo
                if (canciones.size() > 0) {
                    eliminarTodasCanciones(nombre, canciones2);
                } else {
                    imprimirExcepcion("PlayList Vacia");
                    menuCanciones(nombre, canciones2);
                }
        }
    }

    private static void agregarCancion(String nombre, ArrayList<String> canciones2) {
        do {
            String nombreCancion = "";
            do {
                nombreCancion = entradaPersonalizada("Ingrese una cancion nueva", nombre, canciones2);
                if (nombreCancion.matches("^\\s*$")) {
                    imprimirExcepcion("El nombre no puede estar vacio");
                }
            } while (nombreCancion.matches("^\\s*$"));

            // Agrega la nueva cancion al ArrayList
            canciones.add(nombreCancion);
        } while (repetirSeccion("¿Ingresar otra cancion?", "Agregar Canción"));

        menuCanciones(nombre,canciones2);
    }

    private static void eliminarTodasCanciones(String nombre, ArrayList<String> canciones2) {
        boolean respuesta = repetirSeccion("¿Desea eliminar todas las canciones?", "Eliminar todo");
        if (respuesta) {
            canciones.clear();
        }

        menuCanciones(nombre, canciones2);
    }

    private static void eliminarCancion(String nombre, ArrayList<String> canciones2) {
        do {
            int indice = getIndice("eliminar", nombre, canciones2);
            canciones.remove(indice - 1);
        } while (repetirSeccion("¿Eliminar otra cancion?", "Eliminar Canción"));

        menuCanciones(nombre, canciones2);
    }

    private static void verCanciones(String nombre, ArrayList<String> canciones2) {
        JOptionPane.showMessageDialog(null, getCancionesString());

        menuCanciones(nombre, canciones2);
    }

    private static void editarCancion(String nombre, ArrayList<String> canciones2) {
        do {
            int indice = getIndice("editar", nombre, canciones2);
            String nombreNuevo = "";
            do {
                nombreNuevo = entradaPersonalizada("Ingrese el nuevo nombre de la cancion", nombre, canciones2);
                if (nombreNuevo.matches("^\\s*$")) {
                    imprimirExcepcion("El nombre no puede estar vacio");
                }
            } while (nombreNuevo.matches("^\\s*$"));
            canciones.set(indice - 1, nombreNuevo);
        } while (repetirSeccion("¿Editar otra cancion?", "Editar Canción"));

        menuCanciones(nombre, canciones2);
    }

    private static int getIndice(String accion, String nombre, ArrayList<String> canciones2) {
        String indiceString = "";
        int indiceInt = -1;
        do {
            do {
                indiceString = entradaPersonalizada(getCancionesString() + "Seleccione la canción a  " + accion, nombre, canciones2);
                if (!indiceString.matches("\\d+"))
                    imprimirExcepcion("Ingrese un numero de cancion valido");
            } while (!indiceString.matches("\\d+"));
            indiceInt = Integer.parseInt(indiceString);
            if (indiceInt < 1 || indiceInt > canciones2.size())
                imprimirExcepcion("Ingrese un numero de cancion valido");
        } while (indiceInt < 1 || indiceInt > canciones2.size());
        int indice = Integer.parseInt(indiceString);
        return indice;
    }

    private static int getIndiceLista(String accion) {
        String indiceString = "";
        int indiceInt = -1;
        do {
            do {
                indiceString = entradaPersonalizadaLista(getListasString() + "Seleccione la lista a  " + accion);
                if (!indiceString.matches("\\d+"))
                    imprimirExcepcion("Ingrese un numero de lista valido");
            } while (!indiceString.matches("\\d+"));
            indiceInt = Integer.parseInt(indiceString);
            if (indiceInt < 1 || indiceInt > listas.size())
                imprimirExcepcion("Ingrese un numero de lista valido");
        } while (indiceInt < 1 || indiceInt > listas.size());
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

    private static String capturarOpcion(String nombre) {
        String opcion = "";
        do {
            opcion = entradaPersonalizadaLista(
                    "Playlist " + nombre + "\n1.- Agregar cancion nueva.\n2.- Editar cancion.\n3.- Eliminar una cancion.\n4.- Ver canciones.\n5.- Eliminar todas las canciones\n6.- Volver a Play-Lists.");

            // salir si se ingresa 6
            if (opcion.equals("6"))
                menuListas();
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

    private static String entradaPersonalizada(String txt, String nombre, ArrayList<String> canciones2) {
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

        menuCanciones(nombre, canciones2);
        return null; // Si el usuario presiona Cancelar
    }

    private static String entradaPersonalizadaLista(String txt) {
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

        menuListas();
        return null; // Si el usuario presiona Cancelar
    }

}