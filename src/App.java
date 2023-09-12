import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        // do {
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
        // } while (repetir());
    }

    

    private static void menu() {
        ArrayList<String> canciones = new ArrayList<String>();
        String opcion = capturarOpcion();
        switch (opcion) {
            case "1": //Agregar cancion nueva
                agregarCancion(canciones);

            case "2": //Editar cancion"
                editarCancion(canciones);

        }
    }



    private static void editarCancion(ArrayList<String> canciones) {
        String cancionesString = "";
        for (int i = 0; i < canciones.size(); i++) {
            cancionesString += i + ". " + canciones.get(i) + "\n";
        }
        
        do {
            String indiceString = JOptionPane.showInputDialog("Seleccione la canción a  editar");
            canciones.set(0, cancionesString);
        } while(repetirSeccion("¿Editar otra cancion?", "Editar Canción"));
        menu();
    }



    private static void agregarCancion(ArrayList<String> canciones) {
        do {
            canciones.add(JOptionPane.showInputDialog("Ingrese una cancion nueva."));
            
        } while (repetirSeccion("¿Ingresar otra cancion?", "Agregar Canción"));
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
            opcion = JOptionPane.showInputDialog(
                "Bienvenido\n1.- Agregar cancion nueva.\n2.- Editar cancion.\n3.- Eliminar una cancion.\n4.- Ver canciones.\n5.- Eliminar todas las canciones\n6.- Salir.\nSelecciona una opcion");
            
                // salir si se ingresa 6
            if (opcion.equals("6")) System.exit(0);
            if (!opcion.matches("^[1-6]$")) JOptionPane.showMessageDialog(null, "Ingrese una opción valida.");
        } while (!opcion.matches("^[1-6]$"));
        return opcion;
    }



    private static boolean repetir() {
        Object[] opciones = { "Si", "No" };
        return JOptionPane.showOptionDialog(null,
                "¿Repetir Programa?",
                "Repetir?",
                0,
                3,
                null,
                opciones,
                opciones[0]) == 0;
        // menu para repetir el programa al seleccionar "Si"
    }
    private static void imprimirExcepcion(String e) {
        // muestra una ventana de tipo Error
        // con un mensaje personalizado de excepcion
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
    }

}