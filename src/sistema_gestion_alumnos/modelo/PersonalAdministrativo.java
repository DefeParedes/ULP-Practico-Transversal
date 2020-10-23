package sistema_gestion_alumnos.modelo;

import java.util.ArrayList;
import java.util.List;

public class PersonalAdministrativo {
    
    private final Conexion con = new Conexion();
    private final AlumnoData alumnodata = new AlumnoData(con);
    private final MateriaData materiadata = new MateriaData(con);
    private final InscripcionData inscripciondata = new InscripcionData(con);
    
    public List<Materia> materiasCursadas(int dni){
        List<Materia> materias_cursadas = new ArrayList<>();
        List<Materia> materias;
        List<Alumno> alumnos;
        List<Inscripcion> inscripciones;
        alumnos = alumnodata.obtenerAlumnos();
        inscripciones = inscripciondata.obtenerInscripciones();
        materias = materiadata.obtenerMaterias();
        boolean control=true;
        // RECORRER ARREGLO ALUMNOS EN BUSCA DEL ALUMNO CON EL DNI INGRESADO.
        for(Alumno alumno : alumnos){
            //COMPROBANDO DNI INGRESADO CON CADA ALUMNO.
            if(alumno.getId_alumno() == dni){
                control=false;
                //RECORRER TODAS LAS INSCRIPCIONES EN BUSCA DE AQUELLAS QUE CURSE DICHO ALUMNO.
                for(Inscripcion inscripcion : inscripciones){
                    if(alumno.getId_alumno() == inscripcion.getId_alumno()){
                        for(Materia materia : materias){
                            if(inscripcion.getId_materia() == materia.getId_materia()){
                                materias_cursadas.add(materia);
                            }
                        }
                    }
                }
            }
        }
        //SI NO ENCONTRO AL ALUMNO DEVOLVERA UNA LISTA VACIA CON UN CARTEL DE ERROR.
        if(control){
            materias_cursadas=null;
            System.out.println("No se encontro ningun alumno con dicho DNI.");
        }
        return materias_cursadas;
    }
    
    public List<Alumno> alumnosCursando(String nombre_materia){
        List<Alumno> alumnos_cursando = new ArrayList<>();
        List<Materia> materias;
        List<Alumno> alumnos;
        List<Inscripcion> inscripciones;
        alumnos = alumnodata.obtenerAlumnos();
        inscripciones = inscripciondata.obtenerInscripciones();
        materias = materiadata.obtenerMaterias();
        boolean control=true;
        // RECORRER ARREGLO MATERIAS EN BUSCA DE LOS ALUMNOS QUE LA CURSAN.
        for(Materia materia : materias){
            //COMPROBANDO ALUMNOS CON LAS INSCRIPCIONES A DICHAS MATERIAS.
            if(materia.getNombre().equals(nombre_materia)){
               for(Inscripcion inscripcion : inscripciones){
                    if(materia.getId_materia() == inscripcion.getId_materia()){
                        control=false;
                        for(Alumno alumno : alumnos){
                            if(inscripcion.getId_alumno() == alumno.getId_alumno()){
                                 alumnos_cursando.add(alumno);
                            }
                        }
                    }
                }
            }
        }
        //SI NO ENCONTRO AL ALUMNO DEVOLVERA UNA LISTA VACIA CON UN CARTEL DE ERROR.
        if(control){
            alumnos_cursando=null;
            System.out.println("No se encontraron alumnos cursando "+nombre_materia);
        }
        return alumnos_cursando;
    }
    
    public void inscribirAlumno(Alumno alumno,String materia_inscripta){
       List<Materia> materias;
       boolean control = true;
       materias = materiadata.obtenerMaterias();
       for(Materia materia : materias){
           if(materia.getNombre().equals(materia_inscripta)){
               control = false;
               Inscripcion inscripcion = new Inscripcion(alumno.getId_alumno(),materia.getId_materia());
               inscripciondata.guardarInscripcion(inscripcion);
           }
       }
       if(control){
           System.out.println("No se encontro la materia a inscribirse.");
       }
       else{
           System.out.println("El alumno ha sido inscripto exitosamente.");
       }
    }
    
    public void desinscribirAlumno(Alumno alumno,String materia_inscripta){
       List<Materia> materias;
       boolean control = true;
       materias = materiadata.obtenerMaterias();
       for(Materia materia : materias){
           if(materia.getNombre().equals(materia_inscripta)){
               control = false;
               List<Inscripcion> inscripciones;
               inscripciones = inscripciondata.obtenerInscripciones();
               for(Inscripcion inscripcion : inscripciones){
                   if(inscripcion.getId_alumno()==alumno.getId_alumno() && materia.getId_materia() == inscripcion.getId_materia()){
                       inscripciondata.borrarInscripcion(inscripcion.getId_inscripcion());
                   }
               }
           }
       }
       if(control){
           System.out.println("No se encontro la materia para desinscribirse.");
       }
       else{
           System.out.println("El alumno ha sido desinscripto exitosamente.");
       }
    }
    
    public void registrarNota(int id_alumno,String materia_inscripta,double nota){
        List<Inscripcion> inscripciones;
        List<Materia> materias;
        inscripciones = inscripciondata.obtenerInscripciones();
        materias = materiadata.obtenerMaterias();
        boolean control = true;
        for(Inscripcion inscripcion : inscripciones){
            for(Materia materia : materias){
                if(materia.getNombre().equals(materia_inscripta) && materia.getId_materia() == inscripcion.getId_materia() && id_alumno == inscripcion.getId_alumno()){
                    if(inscripcion.getNota()<nota){
                        Inscripcion inscripcionActualizada = new Inscripcion(inscripcion.getId_inscripcion(),id_alumno,materia.getId_materia(),nota);
                        inscripciondata.actualizarInscripcion(inscripcionActualizada);
                        control = false;
                    }
                }
            }
        }
        if(control){
            System.out.println("El usuario ingresado es incorrecto, la materia no existe o la nota es mas baja que la anterior.");
        }
    }
    
    public void altaAlumno(Alumno altaAlumno){
        List<Alumno> alumnos = alumnodata.obtenerAlumnos();
        boolean control=false;
        for(Alumno alumno : alumnos){
            if(alumno.getId_alumno()==altaAlumno.getId_alumno()){
                control=true;
            }
        }
        if(control){
            System.out.println("El alumno ya se encuentra inscripto.");
        }
        else{
            alumnodata.guardarAlumno(altaAlumno);
        }
    }
    
    public void bajaAlumno(Alumno bajaAlumno){
        List<Alumno> alumnos = alumnodata.obtenerAlumnos();
        boolean control = false;
        for(Alumno alumno : alumnos){
            if(alumno.getId_alumno() == bajaAlumno.getId_alumno()){
                control=true;
            }
        }
        if(control){
            alumnodata.borrarAlumno(bajaAlumno.getId_alumno());
        }
        else{
            System.out.println("El alumno no se encuentra inscripto.");
        }
    }
    
    public void modificarAlumno(Alumno modificarAlumno){
        List<Alumno> alumnos = alumnodata.obtenerAlumnos();
        boolean control = true;
        for(Alumno alumno : alumnos){
            if(alumno.getId_alumno() == modificarAlumno.getId_alumno()){
                control=false;
            }
        }
        if(control){
            System.out.println("El alumno no se encuentra inscripto.");
        }
        else{
            alumnodata.actualizarAlumno(modificarAlumno);
        }
    }
    
    public void altaMateria(Materia altaMateria){
        List<Materia> materias = materiadata.obtenerMaterias();
        boolean control=false;
        for(Materia materia : materias){
            if(materia.getNombre().equals(altaMateria.getNombre())){
                control=true;
            }
        }
        if(control){
            System.out.println("La materia ya existe.");
        }
        else{
            materiadata.guardarMateria(altaMateria);
        }
    }
    
    public void bajaMateria(Materia bajaMateria){
        List<Materia> materias = materiadata.obtenerMaterias();
        boolean control = false;
        for(Materia materia : materias){
            if(materia.getNombre().equals(bajaMateria.getNombre())){
                control=true;
            }
        }
        if(control){
            materiadata.borrarMateria(bajaMateria.getId_materia());
        }
        else{
            System.out.println("La materia no existe.");
        }
    }
    
    public void modificarMateria(Materia modificarMateria){
        List<Materia> materias = materiadata.obtenerMaterias();
        boolean control = true;
        for(Materia materia : materias){
            if(materia.getNombre().equals(modificarMateria.getNombre())){
                control=false;
            }
        }
        if(control){
            System.out.println("La materia no existe.");
        }
        else{
            materiadata.actualizarMateria(modificarMateria);
        }
    }
}
