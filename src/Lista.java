import java.util.ArrayList;

public class Lista {
    String nombre;
    ArrayList<String> canciones = new ArrayList<String>();

    public Lista(String nombre, ArrayList<String> canciones) {
        this.nombre = nombre;
        this.canciones = canciones;
    };

    public Lista(String nombre) {
        this.nombre = nombre;
    };


    public void setCanciones(ArrayList<String> canciones) {
        this.canciones = canciones;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getCanciones() {
        return this.canciones;
    }
}
