
package prueba2parcialosman;

import java.io.FileInputStream;

import java.io.FileOutputStream; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Empresa {
    private List<Proyecto> proyectos;
    private List<Empleado> empleados;
    private ExecutorService executorService;

    public Empresa() {
        this.proyectos = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void crearProyecto(int id, String nombre, Empleado liderEquipo) {
        Proyecto proyecto = new Proyecto(id, nombre, liderEquipo);
        proyectos.add(proyecto);
    }

    public void asignarTarea(Proyecto proyecto, Tarea tarea, Empleado empleado) throws Exception {
        if (proyecto.getTareas().stream().anyMatch(t -> t.getId() == tarea.getId())) {
            throw new Exception("Tarea duplicada en el proyecto");
        }
        proyecto.getTareas().add(tarea);
        tarea.setEmpleadoAsignado(empleado);
        empleado.getTareasAsignadas().add(tarea);
    }

    public void completarTarea(Tarea tarea) {
        tarea.setCompletada(true);
        notificarLiderEquipo(tarea);
    }

    private void notificarLiderEquipo(Tarea tarea) {
        Proyecto proyecto = proyectos.stream()
                .filter(p -> p.getTareas().contains(tarea))
                .findFirst()
                .orElse(null);

        if (proyecto != null) {
            System.out.println("Notificación: Tarea '" + tarea.getDescripcion() + "' completada en el proyecto '" + proyecto.getNombre() + "'");
        }
    }

    public void listarProyectos() {
        for (Proyecto proyecto : proyectos) {
            System.out.println("Proyecto: " + proyecto.getNombre() + " (ID: " + proyecto.getId() + ")");
            System.out.println("  Líder de equipo: " + proyecto.getLiderEquipo().getNombre());
            for (Tarea tarea : proyecto.getTareas()) {
                System.out.println("  Tarea: " + tarea.getDescripcion() + " (ID: " + tarea.getId() + ")");
                System.out.println("    Estado: " + (tarea.isCompletada() ? "Completada" : "Pendiente"));
                System.out.println("    Asignada a: " + (tarea.getEmpleadoAsignado() != null ? tarea.getEmpleadoAsignado().getNombre() : "No asignada"));
            }
            System.out.println();
        }
    }

    public void guardarProyectos(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(proyectos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public void cargarProyectos(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            proyectos = (List<Proyecto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void simularTrabajoParalelo() {
        for (Empleado empleado : empleados) {
            executorService.submit(() -> {
                for (Tarea tarea : empleado.getTareasAsignadas()) {
                    if (!tarea.isCompletada()) {
                        try {
                            Thread.sleep(new Random().nextInt(5000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        completarTarea(tarea);
                    }
                }
            });
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public void guardarEnTexto() {
    String desktopPath = "C:\\Users\\jpber\\OneDrive\\Desktop\\";
    String fileName = "proyectos_empresa.txt";
    String filePath = desktopPath + fileName;

    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        writer.println("EMPLEADOS:");
        for (Empleado empleado : empleados) {
            writer.println(empleado.getNombre() + "," + empleado.getRol());
        }

        writer.println("\nPROYECTOS:");
        for (Proyecto proyecto : proyectos) {
            writer.println("ID: " + proyecto.getId() + ", Nombre: " + proyecto.getNombre() + 
                           ", Líder: " + proyecto.getLiderEquipo().getNombre());
            writer.println("Tareas:");
            for (Tarea tarea : proyecto.getTareas()) {
                writer.println("  - ID: " + tarea.getId() + 
                               ", Descripción: " + tarea.getDescripcion() + 
                               ", Completada: " + tarea.isCompletada() + 
                               ", Asignada a: " + (tarea.getEmpleadoAsignado() != null ? 
                                                   tarea.getEmpleadoAsignado().getNombre() : "No asignada"));
            }
            writer.println();
        }

        System.out.println("Datos guardados en: " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
    
    
    
    
    
    
    
    
    
    
    
    
    

