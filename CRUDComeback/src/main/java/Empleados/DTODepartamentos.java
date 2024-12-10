package Empleados;

import java.util.ArrayList;

public class DTODepartamentos {
    private int id;
    private String nombre;
    private ArrayList<DTOEmpleados> empleados;

    public DTODepartamentos(String nombre, ArrayList<DTOEmpleados> empleados) {
        this.nombre = nombre;
        this.empleados = empleados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<DTOEmpleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<DTOEmpleados> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "DTODepartamentos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                '}';
    }
}
