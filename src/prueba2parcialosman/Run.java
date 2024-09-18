/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2parcialosman;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Run {

    private static Scanner scanner = new Scanner(System.in);
    private static Empresa empresa = new Empresa();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    crearProyecto();
                    break;
                case 3:
                    asignarTarea();
                    break;
                case 4:
                    marcarTareaCompletada();
                    break;
                case 5:
                    empresa.listarProyectos();
                    break;
                case 6:
                    empresa.guardarProyectos("proyectos.dat");
                    System.out.println("Proyectos guardados exitosamente.");
                    break;
                case 7:
                    empresa.cargarProyectos("proyectos.dat");
                    System.out.println("Proyectos cargados exitosamente.");
                    break;
                case 8:
                    empresa.simularTrabajoParalelo();
                    System.out.println("Simulación de trabajo paralelo iniciada.");
                    break;
                case 9:
                    empresa.guardarEnTexto();
                    System.out.println("Guardar Texto.");
                    break;
                case 0:
                    System.out.println("Muchas Gracias vuelve cuando quieras.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Prueba Técnica ---");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Crear proyecto");
        System.out.println("3. Asignar tarea");
        System.out.println("4. Marcar tarea como completada");
        System.out.println("5. Listar proyectos");
        System.out.println("6. Guardar proyectos");
        System.out.println("7. Cargar proyectos");
        System.out.println("8. Simular trabajo paralelo");
        System.out.println("9. Guardar Texto.");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarEmpleado() {
        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Rol del empleado: ");
        String rol = scanner.nextLine();
        empresa.agregarEmpleado(new Empleado(nombre, rol));
        System.out.println("Empleado agregado exitosamente.");
    }

    private static void crearProyecto() {
        System.out.print("ID del proyecto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre del proyecto: ");
        String nombre = scanner.nextLine();

        System.out.println("Empleados disponibles:");
        List<Empleado> empleados = (List<Empleado>) empresa.getEmpleados();
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println(i + ". " + empleados.get(i).getNombre());
        }
        System.out.print("Seleccione el líder del equipo (número): ");
        int liderIndex = scanner.nextInt();
        scanner.nextLine();

        if (liderIndex >= 0 && liderIndex < empleados.size()) {
            empresa.crearProyecto(id, nombre, empleados.get(liderIndex));
            System.out.println("Proyecto creado exitosamente.");
        } else {
            System.out.println("Índice de líder no válido.");
        }
    }

    private static void asignarTarea() {
        System.out.println("Proyectos disponibles:");
        List<Proyecto> proyectos = (List<Proyecto>) empresa.getProyectos();
        for (int i = 0; i < proyectos.size(); i++) {
            System.out.println(i + ". " + proyectos.get(i).getNombre());
        }
        System.out.print("Seleccione el proyecto (número): ");
        int proyectoIndex = scanner.nextInt();
        scanner.nextLine();

        if (proyectoIndex >= 0 && proyectoIndex < proyectos.size()) {
            Proyecto proyecto = proyectos.get(proyectoIndex);

            System.out.print("ID de la tarea: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Descripción de la tarea: ");
            String descripcion = scanner.nextLine();

            System.out.println("Empleados disponibles:");
            List<Empleado> empleados = (List<Empleado>) empresa.getEmpleados();
            for (int i = 0; i < empleados.size(); i++) {
                System.out.println(i + ". " + empleados.get(i).getNombre());
            }
            System.out.print("Seleccione el empleado asignado (número): ");
            int empleadoIndex = scanner.nextInt();
            scanner.nextLine();
            if (empleadoIndex >= 0 && empleadoIndex < empleados.size()) {
                Tarea tarea = new Tarea(id, descripcion);
                try {
                    empresa.asignarTarea(proyecto, tarea, empleados.get(empleadoIndex));
                    System.out.println("Tarea asignada exitosamente.");
                } catch (Exception e) {
                    System.out.println("Error al asignar tarea: " + e.getMessage());
                }
            } else {
                System.out.println("Índice de empleado no válido.");
            }
        } else {
            System.out.println("Índice de proyecto no válido.");
        }
    }

    private static void marcarTareaCompletada() {
        System.out.println("Proyectos disponibles:");
        List<Proyecto> proyectos = (List<Proyecto>) empresa.getProyectos();
        for (int i = 0; i < proyectos.size(); i++) {
            System.out.println(i + ". " + proyectos.get(i).getNombre());
        }
        System.out.print("Seleccione el proyecto (número): ");
        int proyectoIndex = scanner.nextInt();
        scanner.nextLine();

        if (proyectoIndex >= 0 && proyectoIndex < proyectos.size()) {
            Proyecto proyecto = proyectos.get(proyectoIndex);

            System.out.println("Tareas pendientes:");
            List<Tarea> tareasPendientes = proyecto.getTareas().stream()
                    .filter(t -> !t.isCompletada())
                    .collect(Collectors.toList());

            for (int i = 0; i < tareasPendientes.size(); i++) {
                System.out.println(i + ". " + tareasPendientes.get(i).getDescripcion());
            }

            System.out.print("Seleccione la tarea a completar (número): ");
            int tareaIndex = scanner.nextInt();
            scanner.nextLine();

            if (tareaIndex >= 0 && tareaIndex < tareasPendientes.size()) {
                empresa.completarTarea(tareasPendientes.get(tareaIndex));
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("Índice de tarea no válido.");
            }
        } else {
            System.out.println("Índice de proyecto no válido.");
        }

    }

}
