package pe.edu.upc.dtos;

import pe.edu.upc.entities.Meta;

public class InsigniaDTO {
    private int idInsignia;
    private Meta meta;
    private String nombre_logro;
    private String descripcion;
    private int puntos;

    public int getIdInsignia() {
        return idInsignia;
    }

    public void setIdInsignia(int idInsignia) {
        this.idInsignia = idInsignia;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getNombre_logro() {
        return nombre_logro;
    }

    public void setNombre_logro(String nombre_logro) {
        this.nombre_logro = nombre_logro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
