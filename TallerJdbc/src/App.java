
import java.util.*;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EstudianteDAO dao = new EstudianteDAO();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    insertarEstudiante();
                    break;
                case 2:
                    actualizarEstudiante();
                    break;
                case 3:
                    eliminarEstudiante();
                    break;
                case 4:
                    consultarTodos();
                    break;
                case 5:
                    consultarPorEmail();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Insertar Estudiante");
        System.out.println("2. Actualizar Estudiante");
        System.out.println("3. Eliminar Estudiante");
        System.out.println("4. Consultar todos los estudiantes");
        System.out.println("5. Consultar Estudiante por email");
        System.out.println("6. Salir del programa");
    }

    private static void insertarEstudiante() {
        System.out.println("\n--- Insertar Estudiante ---");
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String correo = leerTexto("Correo: ");
        int edad = leerEntero("Edad: ");
        EstadoCivil estadoCivil = leerEstadoCivil();
        Estudiante est = new Estudiante(nombre, apellido, correo, edad, estadoCivil);
        if (dao.insertar(est)) {
            System.out.println("Estudiante insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el estudiante.");
        }
    }

    private static void actualizarEstudiante() {
        System.out.println("\n--- Actualizar Estudiante ---");
        String correo = leerTexto("Correo del estudiante a actualizar: ");
        Estudiante existente = dao.consultarPorCorreo(correo);
        if (existente == null) {
            System.out.println("No existe un estudiante con ese correo.");
            return;
        }
        String nombre = leerTexto("Nuevo nombre (actual: " + existente.getNombre() + "): ");
        String apellido = leerTexto("Nuevo apellido (actual: " + existente.getApellido() + "): ");
        int edad = leerEntero("Nueva edad (actual: " + existente.getEdad() + "): ");
        EstadoCivil estadoCivil = leerEstadoCivil();
        existente.setNombre(nombre);
        existente.setApellido(apellido);
        existente.setEdad(edad);
        existente.setEstadoCivil(estadoCivil);
        if (dao.actualizar(existente)) {
            System.out.println("Estudiante actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el estudiante.");
        }
    }

    private static void eliminarEstudiante() {
        System.out.println("\n--- Eliminar Estudiante ---");
        String correo = leerTexto("Correo del estudiante a eliminar: ");
        if (dao.eliminarPorCorreo(correo)) {
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el estudiante.");
        }
    }

    private static void consultarTodos() {
        System.out.println("\n--- Lista de Estudiantes ---");
        List<Estudiante> lista = dao.consultarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante e : lista) {
                System.out.println(e);
            }
        }
    }

    private static void consultarPorEmail() {
        System.out.println("\n--- Consultar Estudiante por Email ---");
        String correo = leerTexto("Correo: ");
        Estudiante est = dao.consultarPorCorreo(correo);
        if (est == null) {
            System.out.println("No existe un estudiante con ese correo.");
        } else {
            System.out.println(est);
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static EstadoCivil leerEstadoCivil() {
        System.out.println("Estados civiles disponibles:");
        for (EstadoCivil ec : EstadoCivil.values()) {
            System.out.println("- " + ec.name());
        }
        while (true) {
            String valor = leerTexto("Estado civil: ").toUpperCase();
            try {
                return EstadoCivil.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Valor inválido. Intente de nuevo.");
            }
        }
    }
}
