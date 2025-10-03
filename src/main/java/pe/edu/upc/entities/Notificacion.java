package pe.edu.upc.entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idNotificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name="titulo", length=20,  nullable=false)
    private String titulo;

    @Column(name="descripcion", length=50,  nullable=false)
    private String descripcion;

    @Column(name="fecha", nullable=false)
    private LocalDate fecha;

    @Column(name="leido", nullable=false)
    private boolean leido;

    public Notificacion() {
    }

    public Notificacion(int idNotificacion, Usuario usuario, String titulo,
                        String descripcion, LocalDate fecha, boolean leido) {
        this.idNotificacion = idNotificacion;
        this.usuario = usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.leido = leido;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
