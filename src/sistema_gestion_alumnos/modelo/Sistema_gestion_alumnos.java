package sistema_gestion_alumnos.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema_gestion_alumnos {
    
public static void main(String[] args) {
        Inscripcion ins1 = new Inscripcion(39024206,11,10);
        Conexion con = new Conexion();
        InscripcionData insdata = new InscripcionData(con);
        
        insdata.guardarInscripcion(ins1);
        insdata.borrarInscripcion(1);
    }
}
