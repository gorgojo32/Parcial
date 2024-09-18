/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2parcialosman;
import java.util.ArrayList;
import java.util.List;


public class Proyecto {
     private int id;
    private String nombre;
    private List<Tarea> tareas;
    private Empleado liderEquipo;

    public Proyecto(int id, String nombre, Empleado liderEquipo) {
        this.id = id;
        this.nombre = nombre;
        this.liderEquipo = liderEquipo;
        this.tareas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public Empleado getLiderEquipo() {
        return liderEquipo;
    }
}
