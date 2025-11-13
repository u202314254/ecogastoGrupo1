package pe.edu.upc.dtos;

import java.time.LocalDate;

public class RecomendacionDTOList {
    private int idRecomendacion;
    private String descripcion;
    private String categoria;
    private LocalDate fechapublicacion;
    private String fuente;

    public RecomendacionDTOList(int idRecomendacion, String descripcion, String categoria, LocalDate fechapublicacion, String fuente) {
        this.idRecomendacion = idRecomendacion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechapublicacion = fechapublicacion;
        this.fuente = fuente;

    }

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
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