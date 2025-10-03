package pe.edu.upc.dtos;
import pe.edu.upc.entities.Meta;
import java.time.LocalDate;

public class RecomendacionDTO {
    private int idRecomendacion;
    private Meta meta;
    private String descripcion;
    private String categoria;
    private LocalDate fechapublicacion;
    private String fuente;

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(LocalDate fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
}
