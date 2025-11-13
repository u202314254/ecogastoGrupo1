package pe.edu.upc.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "insignia")
public class Insignia {
    @Id
    private int idInsignia;

    @ManyToOne
    @JoinColumn(name = "id_meta")
    private Meta meta;

    @Column(name = "nombre_logro", length=20, nullable=false)
    private String nombre_logro;

    @Column(name = "descripcion", length=50,  nullable=false)
    private String descripcion;

    @Column(name = "puntos", nullable=false)
    private int puntos;

    public Insignia() {
    }

    public Insignia(int idInsignia, Meta meta, String nombre_logro, String descripcion, int puntos) {
        this.idInsignia = idInsignia;
        this.meta = meta;
        this.nombre_logro = nombre_logro;
        this.descripcion = descripcion;
        this.puntos = puntos;
    }

    public int getIdInsignia() {
        return idInsignia;
    }

    public void setIdInsignia(int idInsignia) {
        this.idInsignia = idInsignia;
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

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}