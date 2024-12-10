package Empleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mariadb://localhost:3307/db_empleados";
    private static final String USER = "root";
    private static final String PWD = "";
    private static Connection con;

    private DB(){
        try {
            con = DriverManager.getConnection(URL, USER, PWD);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(con == null){
            new DB();
        }
        return con;
    }
}
