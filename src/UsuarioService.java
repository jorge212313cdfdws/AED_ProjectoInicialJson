import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsuarioService {

    private final List<Usuario> usuarios;
    private final String FILE_PATH = "usuarios.json";
    private final Gson gson = new Gson();

    public UsuarioService() {
        usuarios = cargarDatos();
    }

    private List<Usuario> cargarDatos() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            List<Usuario> data = gson.fromJson(reader, new TypeToken<List<Usuario>>(){}.getType());
            return data != null ? data : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void guardarDatos() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            System.out.println("Error guardando datos");
        }
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios.");
            return;
        }
        usuarios.forEach(u -> System.out.println(u));
    }

    public void agregarUsuario(String nombre, String email) {
        int id = generarNuevoId();
        Usuario nuevo = new Usuario(id, nombre, email);
        usuarios.add(nuevo);
        guardarDatos();
        System.out.println("Usuario aÃ±adido.");
    }

    public void modificarUsuario(int id, String nuevoEmail) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setEmail(nuevoEmail);
                guardarDatos();
                System.out.println("Usuario modificado.");
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    public void eliminarUsuario(int id) {
        boolean eliminado = usuarios.removeIf(u -> u.getId() == id);
        if (eliminado) {
            guardarDatos();
            System.out.println("Usuario eliminado.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                System.out.println("Usuario encontrado");
                System.out.println("id: " + u.getId());
                System.out.println("nombre: " + u.getNombre());
                System.out.println("email: " + u.getEmail());
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    private int generarNuevoId() {
        return usuarios.stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0) + 1;
    }
}