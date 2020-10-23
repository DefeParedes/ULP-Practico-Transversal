package sistema_gestion_alumnos.modelo;

public class Inscripcion {
    private int id_inscripcion;
    private int id_alumno;
    private int id_materia;
    private double nota;

    public Inscripcion(int id_alumno, int id_materia, double nota) {
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public Inscripcion(int id_alumno, int id_materia) {
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
    }

    public Inscripcion(int id_inscripcion, int id_alumno, int id_materia, double nota) {
        this.id_inscripcion = id_inscripcion;
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }
 
    public Inscripcion() {
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public int getId_materia() {
        return id_materia;
    }

    public double getNota() {
        return nota;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
}
