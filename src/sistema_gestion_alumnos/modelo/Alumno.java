package sistema_gestion_alumnos.modelo;

import java.sql.Date;

public class Alumno {
    private boolean activo;
    private Date nacimiento_alumno;
    private int id_alumno;
    private String nombre_completo;

    public Alumno(boolean activo, Date nacimiento_alumno, int id_alumno, String nombre_completo) {
        this.activo = activo;
        this.nacimiento_alumno = nacimiento_alumno;
        this.id_alumno = id_alumno;
        this.nombre_completo = nombre_completo;
    }

    public Alumno() {
    }
    
    public boolean isActivo() {
        return activo;
    }

    public Date getNacimiento_alumno() {
        return nacimiento_alumno;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setNacimiento_alumno(Date nacimiento_alumno) {
        this.nacimiento_alumno = nacimiento_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
}
