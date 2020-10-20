package sistema_gestion_alumnos.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema_gestion_alumnos {
    
public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alu1 = new Alumno(true,Date.valueOf(LocalDate.of(1995, 5, 26)),39024206,"Ruben Federico Paredes");
        Alumno alu2 = new Alumno(false,Date.valueOf(LocalDate.of(1996, 11, 13)),39990742,"Erika Janet Guzman");
        Alumno alu3;
        Conexion con = new Conexion();
        AlumnoData aludata = new AlumnoData(con);
        aludata.guardarAlumno(alu1);
        aludata.guardarAlumno(alu2);
        alumnos = aludata.obtenerAlumnos();
        for(Alumno alumno : alumnos){
            System.out.println("Nombre completo del alumno: "+alumno.getNombre_completo());
            System.out.println("Fecha de nacimiento del alumno: "+alumno.getNacimiento_alumno().toString());
            System.out.println("Si el alumno se encuentra cursando: "+alumno.isActivo());
            System.out.println("Documento del alumno: "+alumno.getId_alumno());

        }
        alu3 = aludata.buscarAlumno(39024206);
        System.out.println("Nombre del alumno buscado: "+alu3.getNombre_completo());
        aludata.borrarAlumno(39024206);
        alu3.setActivo(false);
        aludata.actualizarAlumno(alu3);
    }
    
}
