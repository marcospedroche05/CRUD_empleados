package Empleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner sc;
    private static EmpleadosDAO empleadoDAO;
    private static DepartamentosDAO departamentosDAO;

    public Menu(){
        sc = new Scanner(System.in);
        empleadoDAO = new EmpleadosDAO();
        departamentosDAO = new DepartamentosDAO();
    }

    public void muestraMenuGeneral(){
        System.out.println("Bienvenido al sistema.");
        System.out.println("---------------------------");
        System.out.println("Elige una opción: ");
        System.out.println();
        System.out.println("1. Gestionar empleados");
        System.out.println("2. Gestionar departamentos");
        System.out.println("3. Salir");
        System.out.println("-------------------------");
        opcionGeneral();
    }

    public void opcionGeneral(){
        int opcion = sc.nextInt();
        switch (opcion){
            case 1: muestraMenuEmpleados(); break;
            case 2: muestraMenuDepartamentos(); break;
            case 3: salir(); break;
            default: muestraMenuGeneral(); opcionGeneral(); break;
        }

    }

    //MENU EMPLEADOS
    public void muestraMenuEmpleados(){
        System.out.println("GESTION DE EMPLEADOS.");
        System.out.println("---------------------------");
        System.out.println("Elige una opción: ");
        System.out.println();
        System.out.println("1. Mostrar todos los empleados");
        System.out.println("2. Añadir empleado");
        System.out.println("3. Modificar empleado por nombre y edad");
        System.out.println("4. Asignar departamento a un empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("6. Mostrar empleado");
        System.out.println("7. Atrás");
        System.out.println("-------------------------");
        opcionEmpleado();
    }

    public void opcionEmpleado(){
        switch (sc.nextInt()){
            case 1: muestraTodosE(); break;
            case 2: addEmpleado(); break;
            case 3: modificaE(); break;
            case 4: asignaDeptE(); break;
            case 5: eliminaE(); break;
            case 6: muestraE(); break;
            case 7: atras(); break;
            default: muestraMenuEmpleados(); break;
        }
        muestraMenuEmpleados();
    }

    //---------------------------------------------------------------

    //MENU DEPARTAMENTOS
    public void muestraMenuDepartamentos(){
        System.out.println("GESTION DE DEPARTAMENTOS.");
        System.out.println("---------------------------");
        System.out.println("Elige una opción: ");
        System.out.println();
        System.out.println("1. Mostrar todos los departamentos");
        System.out.println("2. Añadir departamento");
        System.out.println("3. Modificar departamento");
        System.out.println("4. Mostrar empleados de un departamento");
        System.out.println("5. Eliminar departamento");
        System.out.println("6. Atrás");
        System.out.println("-------------------------");
        opcionDepartamento();
    }

    public void opcionDepartamento(){
        switch (sc.nextInt()){
            case 1: muestraTodosD(); break;
            case 2: addDepartamento(); break;
            case 3: modificaD(); break;
            case 4: empleadosD(); break;
            case 5: eliminaD(); break;
            case 6: atras(); break;
            default: muestraMenuDepartamentos(); break;
        }
        muestraMenuDepartamentos();
    }

    //---------------------------------------------------------------


    // OPERACIONES EMPLEADOS

    public void muestraTodosE(){
        try {
            ArrayList<DTOEmpleados> listEmpleados = empleadoDAO.getAllEmpleados();
            System.out.println("LISTA DE TODOS LOS EMPLEADOS");
            System.out.println("--------------------------------------");
            for(DTOEmpleados empleado: listEmpleados){
                System.out.println(empleado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addEmpleado(){
        System.out.println("\nIntroduce el nombre del empleado: ");
        String nombre = sc.next();
        System.out.println("Introduce la edad del empleado: ");
        int edad = sc.nextInt();
        System.out.println("Introduce el departamento del empleado: ");
        int departamento = sc.nextInt();
        try {
            empleadoDAO.insertaEmpleado(new DTOEmpleados(nombre, edad, departamento));
            System.out.println("Empleado añadido correctamente");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modificaE(){
        System.out.println("\nIntroduce el id del empleado: ");
        int id = sc.nextInt();
        System.out.println("Introduce su nuevo nombre: ");
        String nombre = sc.next();
        System.out.println("Introduce su nueva edad: ");
        int edad = sc.nextInt();
        try {
            empleadoDAO.modficaEmpleado(id, nombre, edad);
            System.out.println("Empleado modificado correctamente");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void asignaDeptE(){
        System.out.println("\nIntroduce el id del empleado: ");
        int id = sc.nextInt();
        System.out.println("Introduce el id del nuevo departamento que va a asignar al empleado: ");
        int idDepartamento = sc.nextInt();
        try {
            empleadoDAO.modificaEmpleadoDept(id, idDepartamento);
            System.out.println("Empleado asignado correctamente al nuevo departamento.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void eliminaE(){
        System.out.println("\nIntroduce el id del empleado: ");
        int id = sc.nextInt();
        try {
            empleadoDAO.eliminaEmpleado(id);
            System.out.println("Empleado eliminado correctamente.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void muestraE(){
        System.out.println("\nIntroduce el id del empleado: ");
        int id = sc.nextInt();
        try {
            DTOEmpleados empleado = empleadoDAO.getEmpleadosById(id);
            System.out.println(empleado);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------


    // OPERACIONES DEPARTAMENTOS

    public void muestraTodosD(){
        try {
            ArrayList<DTODepartamentos> listaDepartamentos = departamentosDAO.getAllDepartamentos();
            for(DTODepartamentos departamento: listaDepartamentos){
                System.out.println(departamento);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addDepartamento(){
        System.out.println("\nIntroduce el nombre del departamento: ");
        String nombre = sc.next();
        DTODepartamentos departamento = new DTODepartamentos(nombre, new ArrayList<>());
        try {
            departamentosDAO.insertaDepartamento(departamento);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void modificaD(){
        System.out.println("\nIntroduce el id del departamento: ");
        int id = sc.nextInt();
        System.out.println("Introduce el nuevo nombre: ");
        String nombre = sc.next();
        try {
            departamentosDAO.modificaDepartamento(id, nombre);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void empleadosD(){
        System.out.println("\nIntroduce el id del departamento: ");
        int id = sc.nextInt();
        try {
            DTODepartamentos departamento = departamentosDAO.getDepartamentoById(id);
            for (DTOEmpleados empleado: departamento.getEmpleados()){
                System.out.println(empleado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void eliminaD(){
        System.out.println("\nIntroduce el id del departamento: ");
        int id = sc.nextInt();
        try {
            departamentosDAO.eliminaDepartamento(id);
            System.out.println("Departamento eliminado correctamente");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------

    public void atras(){
        muestraMenuGeneral();
    }

    //SALIR DEL PROGRAMA
    public void salir(){
        System.out.println("\nSALIENDO DEL PROGRAMA...");
        sc.close();
    }
}
