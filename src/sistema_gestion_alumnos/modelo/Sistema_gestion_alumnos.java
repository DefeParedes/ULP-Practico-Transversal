package sistema_gestion_alumnos.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Sistema_gestion_alumnos {
    
public static void main(String[] args) {
        PersonalAdministrativo personal = new PersonalAdministrativo();
        List<Materia> materias_cursadas;
        List<Alumno> alumnos_cursando;
        Alumno alu1;
        Materia materia1;
        Conexion con = new Conexion();
        AlumnoData aludata = new AlumnoData(con);
        MateriaData matedata = new MateriaData(con); 
        alu1 = aludata.buscarAlumno(39024206);
        materia1 = matedata.buscarMateria(2);
        materias_cursadas = personal.materiasCursadas(39024206);
        alumnos_cursando = personal.alumnosCursando("Matematica");
        /*for(Materia materia : materias_cursadas){
            System.out.println("Materia: "+materia.getNombre());
        }
        for(Alumno alumno : alumnos_cursando){
            System.out.println("Alumno: "+alumno.getNombre_completo());
        }*/
        //personal.inscribirAlumno(alu1, "Lengua");
        //personal.desinscribirAlumno(alu1,"Matematica");
        Alumno alu2 = new Alumno(true,Date.valueOf(LocalDate.of(1996, 11, 13)),3990742,"Erika Guzman");
        personal.bajaAlumno(alu2);
    }
}
