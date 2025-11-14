package pe.edu.upc.dtos;

public class RecomendacionPorRecursoDTO {
    private String Descripcion;
    private String Fuente;
    private String NombreRecurso;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFuente() {
        return Fuente;
    }

    public void setFuente(String fuente) {
        Fuente = fuente;
    }

    public String getNombreRecurso() {
        return NombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        NombreRecurso = nombreRecurso;
    }
}
