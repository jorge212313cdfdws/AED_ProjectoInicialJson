import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UsuarioService service = new UsuarioService();

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Añadir usuario");
            System.out.println("3. Modificar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Buscar usuario");
            System.out.println("6. Salir");
            System.out.print("Elige opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    service.mostrarUsuarios();
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    service.agregarUsuario(nombre, email);
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idMod = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo email: ");
                    String nuevoEmail = sc.nextLine();
                    service.modificarUsuario(idMod, nuevoEmail);
                    break;

                case 4:
                    System.out.print("ID: ");
                    int idDel = sc.nextInt();
                    service.eliminarUsuario(idDel);
                    break;

                case 5:
                    System.out.print("ID: ");
                    int idBus = sc.nextInt();
                    service.buscarUsuario(idBus);
                    break;

                case 6:
                    System.out.println("Adios!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);

        sc.close();
    }
}