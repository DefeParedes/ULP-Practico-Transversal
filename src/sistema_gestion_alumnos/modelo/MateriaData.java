package sistema_gestion_alumnos.modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaData {

    private Connection con = null;

    public MateriaData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarMateria(Materia materia){
        try{
            String sql = "INSERT INTO materia (nombre) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.executeUpdate(); 
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                materia.setId_materia(rs.getInt(1));
            else
                System.out.println("Error no hay ID");
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<>();
        try{
            String sql = "SELECT * FROM materia;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setNombre(resultSet.getString("nombre"));
                materia.setId_materia(resultSet.getInt("id_materia"));
                materias.add(materia);
            }
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener las materias: "+e.getMessage());
        }
        return materias;
    }
    
    public Materia buscarMateria(int id){
        Materia materia=null;
        String sql = "SELECT * FROM materia WHERE id_materia=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return materia;
    }

    public void actualizarMateria(Materia materia){
        try{
            String sql = ("UPDATE materia SET nombre=? WHERE id_materia=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getId_materia());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void borrarMateria (int id){
        String sql = "DELETE FROM materia WHERE id_materia=?";
        
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
