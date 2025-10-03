package pe.edu.upc.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Recomendacion")

public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecomendacion;

    @ManyToOne
    @JoinColumn(name = "idMeta", nullable = false)
    private Meta meta;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "categoria",length = 200, nullable = false)
    private String categoria;

    @Column(name = "fechapublicacion", nullable = false)
    private LocalDate fechapublicacion;

    @Column(name = "fuente", length = 200, nullable = false)
    private String fuente;
    public Recomendacion() {}

    public Recomendacion(int idRecomendacion, Meta meta, String descripcion, String categoria, LocalDate fechapublicacion, String fuente) {
        this.idRecomendacion = idRecomendacion;
        this.meta = meta;
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
