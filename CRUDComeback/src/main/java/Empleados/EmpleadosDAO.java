package Empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadosDAO {
    private static Connection con;

    public EmpleadosDAO() {
        con = DB.getConnection();
    }

    public ArrayList<DTOEmpleados> getAllEmpleados() throws SQLException {
        ArrayList<DTOEmpleados> empleados = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados");
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            DTOEmpleados empleado = new DTOEmpleados(rs.getString(2), rs.getInt(3), rs.getInt(4));
            empleado.setId(rs.getInt(1));
            empleados.add(empleado);
        }
        return empleados;
    }

    public ArrayList<DTOEmpleados> getEmpleadosByDept(int idDepartamento) throws SQLException {
        ArrayList<DTOEmpleados> empleados = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados WHERE dpto_id = ?");
        ps.setInt(1, idDepartamento);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            DTOEmpleados empleado = new DTOEmpleados(rs.getString(2), rs.getInt(3), rs.getInt(4));
            empleado.setId(rs.getInt(1));
            empleados.add(empleado);
        }
        return empleados;
    }

    public DTOEmpleados getEmpleadosById(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String nombre = rs.getString("nombre");
        int edad = rs.getInt("edad");
        int idDepartamento = rs.getInt("dpto_id");
        DTOEmpleados empleado = new DTOEmpleados(nombre, edad, idDepartamento);
        empleado.setId(rs.getInt("id"));
        return empleado;
    }

    public int insertaEmpleado(DTOEmpleados empleado) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO empleados VALUES (null, ?,?,?)");
        ps.setString(1, empleado.getNombre());
        ps.setInt(2, empleado.getEdad());
        ps.setInt(3, empleado.getIdDepartamento());
        return ps.executeUpdate();
    }

    public int eliminaEmpleado(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM empleados WHERE id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int modficaEmpleado(int id, String nombre, int edad) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE empleados SET nombre = ?, edad = ? WHERE id = ?");
        ps.setString(1, nombre);
        ps.setInt(2, edad);
        ps.setInt(3, id);
        return ps.executeUpdate();
    }

    public int modificaEmpleadoDept(int id, int idDept) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE empleados SET dpto_id = ? WHERE id = ?");
        ps.setInt(1, idDept);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }


}
