package pe.edu.upc.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Consumo")

public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsumo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idRecurso", nullable = false)
    private Recurso recurso;

    @Column(name = "cantidad", nullable = false)
    private double cantidad;

    @Column(name = "costo", nullable = false)
    private double costo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;
    public Consumo() {}

    public Consumo(int idConsumo, Usuario usuario, Recurso recurso, double cantidad, double costo, LocalDate fecha, String descripcion) {
        this.idConsumo = idConsumo;
        this.usuario = usuario;
        this.recurso = recurso;
        this.cantidad = cantidad;
        this.costo = costo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
