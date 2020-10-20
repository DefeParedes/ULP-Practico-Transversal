package sistema_gestion_alumnos.modelo;

public class Materia {
    private int id_materia;
    private String nombre;

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia() {
    }

    public int getId_materia() {
        return id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
