package Empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartamentosDAO {
    private static Connection con;

    public DepartamentosDAO() {
        this.con = DB.getConnection();
    }

    public ArrayList<DTODepartamentos> getAllDepartamentos() throws SQLException {
        ArrayList<DTODepartamentos> departamentos = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM departamento");
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            DTODepartamentos departamento = new DTODepartamentos(rs.getString(2), new ArrayList<>());
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM empleados WHERE dpto_id = ?");
            ps2.setInt(1, rs.getInt(1));
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()) {
                DTOEmpleados empleado = new DTOEmpleados(rs2.getString("nombre"), rs2.getInt("edad"), rs2.getInt("dpto_id"));
                departamento.getEmpleados().add(empleado);
            }
            departamento.setId(rs.getInt(1));
            departamentos.add(departamento);
        }
        return departamentos;
    }

    public DTODepartamentos getDepartamentoById(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM departamento WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            DTODepartamentos departamento = new DTODepartamentos(rs.getString(2), new ArrayList<>());
            departamento.setId(rs.getInt(1));
            return departamento;
        }
        else throw new SQLException();
    }

    public int insertaDepartamento(DTODepartamentos departamento) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO departamento (nombre) VALUES (?)");
        ps.setString(1, departamento.getNombre());
        return ps.executeUpdate();
    }

    public int eliminaDepartamento(int id) throws SQLException {
        DTODepartamentos departamento = getDepartamentoById(id);
        departamento.setEmpleados(null);
        PreparedStatement ps = con.prepareStatement("DELETE FROM departamento WHERE id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int modificaDepartamento(int idDept, String nombre) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE departamento SET nombre = ? WHERE id = ?");
        ps.setString(1, nombre);
        ps.setInt(2, idDept);
        return ps.executeUpdate();
    }

}
