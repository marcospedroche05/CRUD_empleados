package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner teclado;
    private static DAOJuguete daoJuguete;

    public Menu(){
        teclado = new Scanner(System.in);
        daoJuguete = new DAOJuguete();
    }

    public void muestraMenu(){
        System.out.println("Bienvenido al sistema.");
        System.out.println("---------------------------");
        System.out.println("Elige una opción: ");
        System.out.println();
        System.out.println("1. Mostrar J");
        System.out.println("2. Añadir J");
        System.out.println("3. Modificar J por nombre");
        System.out.println("4. Eliminar J");
        System.out.println("5. Eliminar todos");
        System.out.println("6. Muestra ordenados por ID");
        System.out.println("7. Muestra ordenados por nombre");
        System.out.println("8. Salir");
        System.out.println("-------------------------");
    }

    public void elijeOpciones(){
        switch (teclado.nextInt()){
            case 1: muestraJ(); break;
            case 2: addJ(); break;
            case 3: modificaJ(); break;
            case 4: eliminaJ(); break;
            case 5: eliminaTodos(); break;
            case 6: muestraOrdenadosId(); break;
            case 7: muestraOrdenadosName(); break;
            default: salir(); break;
        }
    }

    public void muestraJ(){
        ArrayList<DTOJuguete> juguetes = daoJuguete.getAllJuguetes();
        for (DTOJuguete j : juguetes){
            System.out.println(j);
        }
    }

    public void muestraOrdenadosId(){
        ArrayList<DTOJuguete> juguetes = daoJuguete.getAllJuguetesOrdenadosById();
        for (DTOJuguete j : juguetes){
            System.out.println(j);
        }
    }

    public void muestraOrdenadosName(){
        ArrayList<DTOJuguete> juguetes = daoJuguete.getAllJuguetesOrdenadosByName();
        for (DTOJuguete j : juguetes){
            System.out.println(j);
        }
    }

    public void addJ(){
        System.out.println("\nIntroduce el ID del juguete: ");
        int id = teclado.nextInt();
        System.out.println("Introduce el nombre del juguete: ");
        String nombre = teclado.next();
        DTOJuguete juguete = new DTOJuguete(id, nombre);
        try {
            daoJuguete.addJuguete(juguete);
            System.out.println("\nJuguete añadido con éxito");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modificaJ(){
        System.out.println("\nIntroduce el ID del juguete a modificar: ");
        int id = teclado.nextInt();
        System.out.println("Introduce el nuevo nombre: ");
        String nombre = teclado.next();
        DTOJuguete juguete = new DTOJuguete(id, nombre);
        try {
            daoJuguete.updateJuguete(juguete);
            System.out.println("\nJuguete modificado con éxito.");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void eliminaJ(){
        System.out.println("\nIntroduce el ID del juguete a eliminar: ");
        int id = teclado.nextInt();
        try {
            daoJuguete.deleteJuguete(id);
            System.out.println("\nEl juguete se ha eliminado con éxito.");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void eliminaTodos(){
        try {
            daoJuguete.deleteAllJuguetes();
            System.out.println("\nTodos los juguetes eliminados con éxito.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void salir(){
        System.out.println("\nSaliendo del programa...");
    }
}
