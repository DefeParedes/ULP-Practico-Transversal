package sistema_gestion_alumnos.modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InscripcionData {
    private Connection con = null;

    public InscripcionData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarInscripcion(Inscripcion inscripcion){
        try{
            String sql = "INSERT INTO inscripcion (id_alumno,id_materia,nota) VALUES (? , ? , ?);";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getId_alumno());
            ps.setInt(2, inscripcion.getId_materia());
            ps.setDouble(3, inscripcion.getNota());
            ps.executeUpdate(); 
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                inscripcion.setId_inscripcion(rs.getInt(1));
            else
                System.out.println("Error no hay ID");
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> inscripciones = new ArrayList<>();
        try{
            String sql = "SELECT * FROM inscripcion;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Inscripcion inscripcion;
            while(resultSet.next()){
                inscripcion = new Inscripcion();
                inscripcion.setId_alumno(resultSet.getInt("id_alumno"));
                inscripcion.setId_materia(resultSet.getInt("id_materia"));
                inscripcion.setNota(resultSet.getDouble("nota"));
                inscripcion.setId_inscripcion(resultSet.getInt("id_inscripcion"));
                inscripciones.add(inscripcion);
            }
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener las materias: "+e.getMessage());
        }
        return inscripciones;
    }
    
    public Inscripcion buscarInscripciones(int id){
        Inscripcion inscripcion=null;
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                inscripcion = new Inscripcion();
                inscripcion.setId_materia(rs.getInt("id_materia"));
                inscripcion.setId_alumno(rs.getInt("id_alumno"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return inscripcion;
    }

    public void actualizarInscripcion(Inscripcion inscripcion){
        try{
            String sql = ("UPDATE inscripcion SET id_alumno=?, id_materia=?, nota=? WHERE id_inscripcion=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getId_alumno());
            ps.setInt(2, inscripcion.getId_materia());
            ps.setDouble(3, inscripcion.getNota());
            ps.setInt(4, inscripcion.getId_inscripcion());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void borrarInscripcion (int id){
        String sql = "DELETE FROM inscripcion WHERE id_inscripcion=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
