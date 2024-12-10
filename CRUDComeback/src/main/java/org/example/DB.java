package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mariadb://localhost:3307/db_exa_daw";
    private static final String USER = "root";
    private static final String PWD = "";
    private static Connection conn;

    private DB() {
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        if(conn == null) {
            new DB();
        }
        return conn;
    }
}
