import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Procesos procesos = new Procesos();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios=procesos.usuarios();
        procesos.guardar(usuarios);
    }
}
