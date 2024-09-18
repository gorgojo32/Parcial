/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2parcialosman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpber
 */
public class Empleado implements Serializable{
    private String nombre;
    private String rol;
    private List<Tarea> tareasAsignadas;

    public Empleado(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.tareasAsignadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public List<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }
}
